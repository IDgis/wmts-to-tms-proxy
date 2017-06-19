package nl.idgis.proxy.tmrf;

import java.util.ArrayList;
import java.util.List;

public class TileMapResourceFile {
	
	private String name;
	private List<TileSet> tileSets;
	
	public TileMapResourceFile(String name) {
		this.name = name;
		
		tileSets = new ArrayList<>();
	}

	public void addTileSet(TileSet tileSet) {
		tileSets.add(tileSet);
	}
	
	public TileSet getTileSet(String matrix) {
		for (TileSet tileSet : tileSets) {
			if (matrix.equals(tileSet.getTileMatrix())) {
				return tileSet;
			}
		}
		
		return null;
	}
	
	public int getTileSetCount() {
		return tileSets.size();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
