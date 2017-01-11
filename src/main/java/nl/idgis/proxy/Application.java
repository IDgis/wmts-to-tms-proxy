package nl.idgis.proxy;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String args[]) {
		boolean shouldRun = true;
		
		try {
			log.info("loading WMTS properties");
			
			File tileMapResourceFileDir = new File("./config/tileMapResourceFiles");
			
			if (!tileMapResourceFileDir.exists()) {
				throw new WMTSPropertiesException("no tile map resource file directory found");
			}
			
			File[] tileMapResourceFileDirFiles = tileMapResourceFileDir.listFiles();
			
			for (File file : tileMapResourceFileDirFiles) {
				if (Utils.hasExtension(file, "properties")) {
					log.info(String.format("properties file found: %s", file.getName()));
					Properties props = new Properties();
					props.load(new FileInputStream(file));
					
					String baseUrl = props.getProperty(WMTSProperties.BASE_URL);
					String version = props.getProperty(WMTSProperties.VERSION);
					String matrixMappingString = props.getProperty(WMTSProperties.MATRIX_MAPPING);
					String tileMap = file.getName().substring(0, file.getName().lastIndexOf('.'));
					
					if (baseUrl == null || baseUrl.isEmpty()) {
						throw new WMTSPropertiesException(String.format("%s property is null or empty", WMTSProperties.BASE_URL));
					}
					
					if (version == null || version.isEmpty()) {
						log.warn(String.format("%s property is null or empty (set to default 1.0.0)", WMTSProperties.VERSION));
						version = "1.0.0";
					}
					
					MatrixMapping mapping = new MatrixMapping();
					
					if (matrixMappingString != null && !matrixMappingString.isEmpty()) {
						mapping.initialize(matrixMappingString);
					}
					
					WMTSProperties wmtsProps = new WMTSProperties();
					wmtsProps.setBaseUrl(baseUrl);
					wmtsProps.setVersion(version);
					wmtsProps.setTileMap(tileMap);
					wmtsProps.setMatrixMapping(mapping);
					
					WMTSPropertiesContainer.addProperties(wmtsProps);
				}
			}
			
			// Check if all tile map resource files have a corresponding properties file
			for (File file : tileMapResourceFileDirFiles) {
				if (Utils.hasExtension(file, "xml")) {
					String tileMap = file.getName().substring(0, file.getName().lastIndexOf('.'));
					
					if (WMTSPropertiesContainer.getProperties(tileMap) == null) {
						throw new WMTSPropertiesException(String.format("tile map resource file %s has no corresponding properties file", file.getName()));
					}
				}
			}
		} catch (WMTSPropertiesException | IOException | MappingException e) {
			log.error("could not load WMTS properties", e);
			shouldRun = false;
		}
		
		for (String arg : args) {
			log.info(String.format("arg: %s", arg));
		}
		
		if (shouldRun) {
			SpringApplication.run(Application.class);
		} else {
			log.warn("shouldRun is false, therefore application will not be run");
		}
		
	}
}
