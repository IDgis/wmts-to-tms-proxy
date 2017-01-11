package nl.idgis.proxy;

public class TileMapSource {
	
	public static final String FORMAT = "xml";

	private String name;
	
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
