package nl.idgis.proxy.mapping;

import java.util.HashMap;
import java.util.Map;

import nl.idgis.proxy.WMTSPropertiesException;

public class MatrixMappings {
	
	private static Map<String, MatrixMapping> mappings = new HashMap<>();
	
	public static void add(String name, MatrixMapping matrixMapping) throws WMTSPropertiesException {
		mappings.put(name, matrixMapping);
	}
	
	public static MatrixMapping getMapping(String tileMap) {
		return mappings.get(tileMap);
	}
}
