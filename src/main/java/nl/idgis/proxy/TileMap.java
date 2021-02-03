package nl.idgis.proxy;

import nl.idgis.proxy.mapping.MatrixMapping;
import nl.idgis.proxy.mapping.MatrixMappings;

public class TileMap {

	private String name;
	private String srs;
	private String fileType;
	
	public TileMap(String name, String srs, String fileType) {
		this.name = name;
		this.srs = srs;
		this.fileType = fileType;
	}

	public String getMatrix(int z) throws WMTSPropertiesException {
		final String fullName = name + "@" + srs + "@" + fileType;
		
		MatrixMapping mapping = MatrixMappings.getMapping(fullName);
		
		if (mapping == null) {
			throw new WMTSPropertiesException("no mapping found for " + fullName);
		}
		
		return mapping.getMatrix(z);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSrs() {
		if (srs.startsWith("EPSG")) {
			return "EPSG:" + srs.substring(4);
		}
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
	
}
