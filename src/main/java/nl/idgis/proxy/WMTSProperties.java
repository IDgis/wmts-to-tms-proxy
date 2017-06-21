package nl.idgis.proxy;

public class WMTSProperties {

	public static final String BASE_URL = "baseUrl";
	public static final String VERSION = "version";
	public static final String MATRIX_MAPPING = "matrixMapping";
	
	private String baseUrl;
	private String version;
	private String tileMap;
	private String matrixMapping;

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getTileMap() {
		return tileMap;
	}

	public void setTileMap(String tileMap) {
		this.tileMap = tileMap;
	}

	public String getMatrixMapping() {
		return matrixMapping;
	}

	public void setMatrixMapping(String matrixMapping) {
		this.matrixMapping = matrixMapping;
	}
	
}
