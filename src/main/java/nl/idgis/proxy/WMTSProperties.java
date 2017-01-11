package nl.idgis.proxy;

public class WMTSProperties {

	public static final String BASE_URL = "baseUrl";
	public static final String MATRIX_MAPPING = "matrixMapping";
	public static final String VERSION = "version";
	
	private String baseUrl;
	private MatrixMapping matrixMapping;
	private String version;
	private String tileMap;

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public MatrixMapping getMatrixMapping() {
		return matrixMapping;
	}

	public void setMatrixMapping(MatrixMapping matrixMapping) {
		this.matrixMapping = matrixMapping;
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
	
}
