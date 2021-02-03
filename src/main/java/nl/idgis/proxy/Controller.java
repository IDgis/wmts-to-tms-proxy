package nl.idgis.proxy;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

@RestController
public class Controller implements ErrorController {

	private static final Logger log = LoggerFactory.getLogger(Controller.class);
	private static final String ERROR_PATH = "/error";
	private static final String TILE_MAPS_PATH = "config/tileMapResourceFiles";
	private static final String CAPABILITIES_PATH = "config/Capabilities";
	private static final String CAPABILITIES = "Capabilities.xml";
	
	@Value("${debug}")
    private boolean debug;
	
	@Autowired
    private ErrorAttributes errorAttributes;

	@RequestMapping(value = ERROR_PATH)
	public ResponseEntity<String> error(HttpServletRequest request, HttpServletResponse response) {
		log.error(String.format("error occured from URL: %s", request.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI)));
		
		final int status = response.getStatus();
		final ErrorResponse errorResponse = new ErrorResponse(status, getErrorAttributes(request, debug));
		final String trace = errorResponse.getTrace();

		log.error(trace);
		
		if (errorResponse.getTrace() != null) {
			return ResponseEntity.status(status).body(errorResponse.getReponse());
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("HTTP 500");
		}
	}
	
	private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        return errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
    }
	
	@Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
	
	@RequestMapping(value = "/{serviceType}/{version:.+}")
	public ResponseEntity<String> doGetTMSCapabilities(@PathVariable String serviceType, @PathVariable String version) throws TMRFException {
		log.info("requesting tile map capabilities");
		
		validateRequest(serviceType, version);

        final String filePath = String.format("%s/%s", CAPABILITIES_PATH, CAPABILITIES);
        String xml;
        InputStream in = null;

        try {
            in = new FileInputStream(filePath);

            ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;

            while ((length = in.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }

            xml = result.toString("UTF-8");
        } catch (FileNotFoundException e) {
            log.error("file " + filePath + " not found", e);
            throw new TMRFException("Capabilities document not found");
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new TMRFException("Unable to return capabilities document");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    log.warn(String.format("input stream from %s was not closed: %s", filePath, e.getMessage()));
                }
            }
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/xml");
        return ResponseEntity.ok().headers(headers).body(xml);

	}

	@RequestMapping(value = "/{serviceType}/{version}/{tileMap}")
	public ResponseEntity<String> doGetTileMapCapabilities(@PathVariable String serviceType,
			@PathVariable String version, @PathVariable String tileMap) throws TMRFException {
		log.info(String.format("requesting tile map resource file for: %s", tileMap));
		
		validateRequest(serviceType, version);

		final String filePath = String.format("%s/%s.xml", TILE_MAPS_PATH, tileMap);
		
		String xml;
		
		InputStream in = null;
		
		try {
			in = new FileInputStream(filePath);
			
			ByteArrayOutputStream result = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int length;
			
			while ((length = in.read(buffer)) != -1) {
			    result.write(buffer, 0, length);
			}

			xml = result.toString("UTF-8");
		} catch (FileNotFoundException e) {
			log.error("file " + filePath + " not found", e);
			throw new TMRFException(String.format("tile map resource file %s not found", tileMap));
		} catch (IOException e) {
			log.error(e.getMessage());
			throw new TMRFException(String.format("unable to return tile map resource file: %s", e.getMessage()));
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					log.warn(String.format("input stream from %s was not closed: %s", filePath, e.getMessage()));
				}
			}
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/xml");
		return ResponseEntity.ok().headers(headers).body(xml);
	}

	@RequestMapping(value = "/{serviceType}/{version}/{tileMapString}/{z}/{x}/{y}.{type}")
	public ResponseEntity<?> doGetTile(@RequestHeader MultiValueMap<String, String> requestHeaders, @PathVariable String serviceType, @PathVariable String version,
									   @PathVariable String tileMapString, @PathVariable String z, @PathVariable String x, @PathVariable String y,
									   @PathVariable String type) {
		log.info(String.format("requesting image for: %s", tileMapString));

		// Log request url and headers
		String requestUrl = String.format("request: /%s/%s/%s/%s/%s/%s.%s", serviceType, version, tileMapString, z, x, y, type);
		log.debug(requestUrl);
		requestHeaders.forEach((key, value) -> log.debug(String.format("Ontvangen header '%s' = %s", key, value.stream().collect(Collectors.joining("|")))));

		
		validateRequest(serviceType, version);

		if (!Utils.isInteger(x)) {
			throw new RequestException(String.format("x should be an integer (current value: %s)", x));
		}

		if (!Utils.isInteger(y)) {
			throw new RequestException(String.format("y should be an integer (current value: %s)", y));
		}

		final String[] tileMapSpecs = tileMapString.split("@");

		if (tileMapSpecs.length < 3) {
			throw new RequestException(String.format("tile map %s should match format {name}@{srs}@{fileType}", tileMapString));
		}

		if (!tileMapSpecs[2].equalsIgnoreCase(type)) {
			throw new RequestException(String.format("tile map type %s does not match file type %s", tileMapSpecs[2], type));
		}

		final TileMap tileMap = new TileMap(tileMapSpecs[0], tileMapSpecs[1], tileMapSpecs[2]);
		
		final int ix = Integer.parseInt(x);
		final int iy = Integer.parseInt(y);
		final int iz = Integer.parseInt(z);

		log.debug(String.format("tms x: %s", x));
		log.debug(String.format("tms y: %s", y));
		log.debug(String.format("tms z: %s", z));
		
		Tile tile;
		
		tile = new Tile(ix, iy, iz);
		
		WMTSProperties wmtsProps = WMTSPropertiesContainer.getProperties(tileMapString);
		
		if (wmtsProps == null) {
			return ResponseEntity.badRequest().body(String.format("TileMap %s not found", tileMapString));
		}

		HttpURLConnection connection = null;

		try {
			
			String url = createWMTSURL(serviceType, tileMap, tile,
				wmtsProps.getBaseUrl(), wmtsProps.getVersion(), tileMap.getSrs());
			
			log.debug(String.format("wmts url: %s", url));
			
			connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			
			final int responseCode = connection.getResponseCode();
			
			if (responseCode != 200) {
				throw new WMTSException(responseCode);
			}

			byte[] buf = new byte[80];
			try(
				InputStream inputStream = connection.getInputStream();
				ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream()) {

				int retval;
				while ((retval = inputStream.read(buf)) != -1) {
					outputBuffer.write(buf, 0, retval);
				}

				HttpHeaders headers = new HttpHeaders();

				MediaType mediaType;

				if (type.equalsIgnoreCase("jpeg") || type.equalsIgnoreCase("jpg")) {
					mediaType = MediaType.IMAGE_JPEG;
				} else if (type.equalsIgnoreCase("png")) {
					mediaType = MediaType.IMAGE_PNG;
				} else {
					mediaType = MediaType.APPLICATION_OCTET_STREAM;
				}

				headers.setContentType(mediaType);

				headers.forEach((key, value) -> log.debug(String.format("Verstuurde header '%s' = %s", key, value.stream().collect(Collectors.joining("|")))));

				return new ResponseEntity<>(outputBuffer.toByteArray(), headers, HttpStatus.OK);
			}
		} catch (WMTSURLException | IOException | WMTSPropertiesException e) {
			throw new TMSException(e);
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}

	}

	private String createWMTSURL(String serviceType, TileMap tileMap, Tile tile, String wmtsBaseUrl,
			String wmtsVersion, String tileMatrixSet) throws WMTSURLException, WMTSPropertiesException {
		final String tileMapName = tileMap.getName();
		final String tileMapSRS = tileMap.getSrs();
		final String tileMapFileType = tileMap.getFileType();

		if (wmtsBaseUrl == null || wmtsBaseUrl.isEmpty()) {
			throw new NullPointerException("wmts base URL is null or empty");
		}

		if (wmtsVersion == null || wmtsVersion.isEmpty()) {
			throw new NullPointerException("wmts version is null or empty");
		}

		if (!Utils.isValidVersion(wmtsVersion)) {
			throw new WMTSURLException(String.format("wmts.version '%s' in config is not valid", wmtsVersion));
		}

		if (tileMapName == null || tileMapName.isEmpty()) {
			throw new WMTSURLException("TileMap name is null or empty");
		}

		if (tileMapSRS == null || tileMapSRS.isEmpty()) {
			throw new WMTSURLException("TileMap SRS is null or empty");
		}

		if (tileMapFileType == null || tileMapFileType.isEmpty()) {
			throw new WMTSURLException("TileMap file type is null or empty");
		}

		log.debug("creating WMTS URL with base URL: " + wmtsBaseUrl);

		if (tileMatrixSet == null) {
			throw new WMTSURLException(String.format("TileMatrixSet not resolved with SRS %s", tileMapSRS));
		}

		String wmtsUrl = wmtsBaseUrl.endsWith("?") ? wmtsBaseUrl : wmtsBaseUrl + "?";
		
		int row = Utils.wmtsRow(tile.getY(), tile.getZ());
		int col = tile.getX();
		String tileMatrix = tileMap.getMatrix(tile.getZ());
		
		log.debug(String.format("wmts row: %d", row));
		log.debug(String.format("wmts col: %d", col));
		log.debug(String.format("wmts tileMatrix: %s", tileMatrix));

		wmtsUrl += String.format(
				"SERVICE=WMTS&VERSION=%1$s&REQUEST=GetTile&LAYER=%2$s&STYLE=default&TILEMATRIXSET=%3$s&TILEMATRIX=%4$s&TILEROW=%5$d&TILECOL=%6$d&FORMAT=image/%7$s",
				wmtsVersion, tileMapName, tileMatrixSet, tileMatrix, row, col, tileMapFileType);

		return wmtsUrl;
	}
	
	private void validateRequest(String serviceType, String version) {
		if (!"tms".equalsIgnoreCase(serviceType)) {
			throw new RequestException(String.format("service type should be TMS instead of %s for this proxy", serviceType));
		}

		if (!"1.0.0".equals(version)) {
			throw new RequestException(String.format("TMS version should be 1.0.0 instead of %s for this proxy", version));
		}
	}

}
