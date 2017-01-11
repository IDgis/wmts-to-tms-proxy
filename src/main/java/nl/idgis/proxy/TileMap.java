package nl.idgis.proxy;

public class TileMap {

	private String srs;
	private String fileType;
	private TileMapSource source;
	
	public TileMap(String name, String srs, String fileType) {
		this.srs = srs;
		this.fileType = fileType;
		this.source = new TileMapSource(name);
	}

	public String getSrs() {
		return srs;
	}

	public void setSrs(String srs) {
		this.srs = srs;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public TileMapSource getSource() {
		return source;
	}

	public void setSource(TileMapSource source) {
		this.source = source;
	}
	
}
