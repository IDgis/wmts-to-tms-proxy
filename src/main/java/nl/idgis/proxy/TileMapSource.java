package nl.idgis.proxy;

import nl.idgis.proxy.tmrf.TileMapResourceFile;
import nl.idgis.proxy.tmrf.TileMapResourceFiles;
import nl.idgis.proxy.tmrf.TileSet;

public class TileMapSource {
	
	public static final String FORMAT = "xml";
	public static final String TILE_MAP_RESOURCE_FILE_FOLDER_PATH = "./config/tileMapResourceFiles";

	private String name;
	
	public int getZoom(String matrix) {
		TileMapResourceFile tileMapResourceFile = TileMapResourceFiles.getTileMapResourceFile(name);
		
		if (tileMapResourceFile != null) {
			TileSet tileSet = tileMapResourceFile.getTileSet(matrix);
			
			if (tileSet != null) {
				return tileSet.getOrder();
			}
			
			throw new TMSException("TileSet not found for matrix " + matrix);
		}
		
		throw new TMSException("TileMap resource not found: " + name);
	}
	
	public TileMapSource(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getFileName() {
		return String.format("%s.%s", name.replaceAll(":", "%3A"), FORMAT);
	}

}
