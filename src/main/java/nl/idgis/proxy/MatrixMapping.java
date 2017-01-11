package nl.idgis.proxy;

import java.util.HashMap;

public class MatrixMapping extends HashMap<String, String> implements Mapping {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7687943036375522407L;
	
	private boolean initialized;

	@Override
	public void initialize(String matrixMappingString) throws MappingException {
		if (matrixMappingString != null) {
			putAll(splitString(matrixMappingString));
		}
		
		initialized = true;
	}

	@Override
	public boolean isInitialized() {
		return initialized;
	}
	
	private static HashMap<String, String> splitString(String matrixMappingString) throws MappingException {
		HashMap<String, String> mappings = new HashMap<>();
		
		if (matrixMappingString == null || matrixMappingString.isEmpty()) {
			throw new MappingException("matrix mapping string is null or empty");
		}
		
		for (String mapping : matrixMappingString.split(",")) {
			final int index = mapping.indexOf('=');
			mappings.put(mapping.substring(0, index), mapping.substring(index + 1));
		}
		
		return mappings;
	}
	
}
