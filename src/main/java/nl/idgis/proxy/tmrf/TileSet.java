package nl.idgis.proxy.tmrf;

public class TileSet {
	private String tileMatrix;
	private int order;
	
	public TileSet(String tileMatrix, int order) {
		this.tileMatrix = tileMatrix;
		this.order = order;
	}

	public String getTileMatrix() {
		return tileMatrix;
	}

	public int getOrder() {
		return order;
	}
	
}
