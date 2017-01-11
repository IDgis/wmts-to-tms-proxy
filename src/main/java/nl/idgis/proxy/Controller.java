package nl.idgis.proxy;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller implements ErrorController {

	private static final Logger log = LoggerFactory.getLogger(Controller.class);
	private static final String ERROR_PATH = "/error";
	private static final String TILE_MAP_RESOURCE_FILE_FOLDER_PATH = "./config/tileMapResourceFiles";

//	@RequestMapping(value = "/")
//	public ResponseEntity<String> noServiceType() {
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no service type specified");
//	}
//
//	@RequestMapping(value = "/{serviceType}")
//	public ResponseEntity<String> noVersion(@PathVariable String serviceType) {
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no version specified");
//	}
//

	@RequestMapping(value = ERROR_PATH)
	public ResponseEntity<String> error() {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("HTTP 500: internal server error");
	}
	
	@Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
	
	@RequestMapping(value = "/{serviceType}/{version}")
	public ResponseEntity<String> doGetTMSCapabilities(@PathVariable String serviceType, @PathVariable String version) {
		log.info("requesting tile map capabilities");
		
		if (!"tms".equalsIgnoreCase(serviceType)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(String.format("serviceType should be TMS instead of %s for this proxy", serviceType));
		}

		if (!"1.0.0".equals(version)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(String.format("TMS version should be 1.0.0 instead of %s for this proxy", version));
		}

		return ResponseEntity.ok("TMS capabilities should appear here");
	}

	@RequestMapping(value = "/{serviceType}/{version}/{tileMap}")
	public ResponseEntity<String> doGetTileMapCapabilities(@PathVariable String serviceType,
			@PathVariable String version, @PathVariable String tileMap) {
		log.info("requesting tile map resource file");
		
		if (!"tms".equalsIgnoreCase(serviceType)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(String.format("serviceType should be TMS instead of %s for this proxy", serviceType));
		}

		if (!"1.0.0".equals(version)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(String.format("TMS version should be 1.0.0 instead of %s for this proxy", version));
		}
		
		final TileMapSource source = new TileMapSource(tileMap);
		final String filePath = String.format("%s/%s", TILE_MAP_RESOURCE_FILE_FOLDER_PATH, source.getFileName());
		
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
		} catch (IOException e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(String.format("could not read file %s", source.getFileName()));
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					log.warn("input stream from %s was not closed: %s", filePath, e.getMessage());
				}
			}
		}
		

		return ResponseEntity.ok(xml);
	}

	@RequestMapping(value = "/{serviceType}/{version}/{tileMapString}/{z}/{x}/{y}.{type}")
	public ResponseEntity<?> doGetTile(@PathVariable String serviceType, @PathVariable String version,
			@PathVariable String tileMapString, @PathVariable String x, @PathVariable String y, @PathVariable String z,
			@PathVariable String type) throws WMTSURLException {
		log.info("requesting image");
		
		if (!"tms".equalsIgnoreCase(serviceType)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(String.format("service type should be TMS instead of %s for this proxy", serviceType));
		}

		if (!"1.0.0".equals(version)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(String.format("TMS version should be 1.0.0 instead of %s for this proxy", version));
		}

		if (!Utils.isInteger(x)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(String.format("x should be an integer (current value: %s)", x));
		}

		if (!Utils.isInteger(y)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(String.format("y should be an integer (current value: %s)", y));
		}

		if (!Utils.isInteger(z)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(String.format("z should be an integer (current value: %s)", z));
		}

		final String[] tileMapSpecs = tileMapString.split("@");

		if (tileMapSpecs.length < 3) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(String.format("tile map %s should match format {name}@{srs}@{fileType}", tileMapString));
		}

		if (!tileMapSpecs[2].equalsIgnoreCase(type)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(String.format("tile map type %s does not match file type %s", tileMapSpecs[2], type));
		}

		final TileMap tileMap = new TileMap(tileMapSpecs[0], tileMapSpecs[1], tileMapSpecs[2]);
		
		final int ix = Integer.parseInt(x);
		final int iy = Integer.parseInt(y);
		final int iz = Integer.parseInt(z);

		log.info(String.format("tms x: %s", x));
		log.info(String.format("tms y: %s", y));
		log.info(String.format("tms z: %s", z));
		
		Tile tile;
		
		try {
			tile = new Tile(ix, iy, iz);
		} catch (TMSException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		log.info("request is valid");
		
		WMTSProperties wmtsProps = WMTSPropertiesContainer.getProperties(tileMapString.replace(":", "%3A"));
		
		if (wmtsProps == null) {
			return ResponseEntity.badRequest().body(String.format("TileMap %s not found", tileMapString));
		}
		
		try {
			
			String url = createWMTSURL(serviceType, tileMap, tile,
				wmtsProps.getBaseUrl(), wmtsProps.getVersion(), wmtsProps.getMatrixMapping());
			
			log.info(String.format("wmts url: %s", url));
			
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			
			final int responseCode = connection.getResponseCode();
			
			if (responseCode != 200) {
				throw new WMTSException(responseCode);
			}
			
			InputStream inputStream = connection.getInputStream();
			InputStreamResource resource = new InputStreamResource(inputStream);
			
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
			
			return new ResponseEntity<>(resource, headers, HttpStatus.OK);
		} catch (WMTSURLException | IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(String.format("internal error: %s", e.getMessage()));
		} catch (WMTSException e) {
			return ResponseEntity.status(HttpStatus.valueOf(e.getStatus())).body(String.format("WMTS returned http status %d", e.getStatus()));
		}
			
//			return ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT)
//					.body(String.format("<head><meta http-equiv=\"refresh\" content=\"0; url=%s\" /></head>",
//							createWMTSURL(serviceType, tileMap, Integer.parseInt(x), Integer.parseInt(y),
//									Integer.parseInt(z), wmtsBaseUrl, wmtsVersion)));
		
	}

	private String createWMTSURL(String serviceType, TileMap tileMap, Tile tile, String wmtsBaseUrl,
			String wmtsVersion, MatrixMapping mapping) throws WMTSURLException {
		final String tileMapName = tileMap.getSource().getName();
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

		log.info("creating WMTS URL with base URL: " + wmtsBaseUrl);
		
		String tileMatrixSet = mapping.get(tileMapSRS);

		if (tileMatrixSet == null) {
			throw new WMTSURLException(String.format("TileMatrixSet not resolved with SRS %s", tileMapSRS));
		}

		String wmtsUrl = wmtsBaseUrl.endsWith("?") ? wmtsBaseUrl : wmtsBaseUrl + "?";
		
		int row = Utils.wmtsRow(tile.getY(), tile.getZ());
		int col = tile.getX();
		int zoom = tile.getZ();
		
		log.info(String.format("wmts row: %d", row));
		log.info(String.format("wmts col: %d", col));
		log.info(String.format("wmts zoom: %d", zoom));

		wmtsUrl += String.format(
				"SERVICE=WMTS&VERSION=%1$s&REQUEST=GetTile&LAYER=%2$s&STYLE=default&TILEMATRIXSET=%3$s&TILEMATRIX=%4$s:%5$d&TILEROW=%6$d&TILECOL=%7$d&FORMAT=image/%8$s",
				wmtsVersion, tileMapName, tileMatrixSet, tileMapSRS, zoom, row, col, tileMapFileType);

		return wmtsUrl;
	}

}
