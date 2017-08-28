package nl.idgis.proxy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import nl.idgis.proxy.mapping.MatrixMapping;
import nl.idgis.proxy.mapping.MatrixMappings;

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
			
			log.info("loading property files");
			
			try {
				loadProperties(tileMapResourceFileDirFiles);
			} catch (IOException e) {
				throw new WMTSPropertiesException("unable to load properties from property files");
			}
			
			log.info("loading TileMap resource files");
			
			for (File file : tileMapResourceFileDirFiles) {
				if (Utils.hasExtension(file, "xml")) {
					String tileMap = file.getName().substring(0, file.getName().lastIndexOf('.'));

					// Check if tile map resource file has a corresponding properties file
					if (WMTSPropertiesContainer.getProperties(tileMap) == null) {
						throw new WMTSPropertiesException(String.format("tile map resource file %s has no corresponding properties file", file.getName()));
					}
					
					WMTSProperties properties = WMTSPropertiesContainer.getProperties(tileMap);
					
					if (properties == null) {
						throw new WMTSPropertiesException("no properties found for tile map " + tileMap);
					}

					MatrixMappings.add(tileMap.replace("%3A", ":"), new MatrixMapping(properties.getMatrixMapping()));
				}
			}
			
		} catch (WMTSPropertiesException e) {
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
	
	private static void loadProperties(File[] tileMapResourceFileDirFiles) throws FileNotFoundException, IOException, WMTSPropertiesException {
		for (File file : tileMapResourceFileDirFiles) {
			if (Utils.hasExtension(file, "properties")) {
				log.info(String.format("properties file found: %s", file.getName()));
				Properties props = new Properties();
				props.load(new FileInputStream(file));
				
				String baseUrl = props.getProperty(WMTSProperties.BASE_URL);
				String version = props.getProperty(WMTSProperties.VERSION);
				String tileMap = file.getName().substring(0, file.getName().lastIndexOf('.'));
				String matrixMapping = props.getProperty(WMTSProperties.MATRIX_MAPPING);
				
				if (baseUrl == null || baseUrl.isEmpty()) {
					throw new WMTSPropertiesException(String.format("%s property is null or empty", WMTSProperties.BASE_URL));
				}
				
				if (version == null || version.isEmpty()) {
					log.warn(String.format("%s property is null or empty (set to default 1.0.0)", WMTSProperties.VERSION));
					version = "1.0.0";
				}
				
				if (matrixMapping == null || matrixMapping.isEmpty()) {
					log.warn(String.format("%s property is null or empty (set to default 0=0;1=1 etc)", WMTSProperties.VERSION));
					matrixMapping = "0=0;1=1;2=2;3=3;4=4;5=5;6=6;7=7;8=8;9=9;10=10;11=11;12=12;13=13;14=14;15=15;16=16";
				}
				
				WMTSProperties wmtsProps = new WMTSProperties();
				wmtsProps.setBaseUrl(baseUrl);
				wmtsProps.setVersion(version);
				wmtsProps.setTileMap(tileMap);
				wmtsProps.setMatrixMapping(matrixMapping);
				
				WMTSPropertiesContainer.addProperties(wmtsProps);
			}
		}
	}
}
