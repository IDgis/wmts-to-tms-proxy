package nl.idgis.proxy;

public interface Mapping {
	
	public void initialize(String mappingString) throws MappingException;
	
	public boolean isInitialized();
	
}
