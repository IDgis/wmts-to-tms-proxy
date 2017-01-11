package nl.idgis.proxy;

import java.util.ArrayList;
import java.util.List;

public class WMTSPropertiesContainer {

	private static List<WMTSProperties> list = new ArrayList<>();
	
	public static WMTSProperties getProperties(String tileMap) {
		for (WMTSProperties properties : list) {
			if (properties.getTileMap().equals(tileMap)) {
				return properties;
			}
		}
		
		return null;
	}
	
	public static void addProperties(WMTSProperties properties) throws WMTSPropertiesException {
		if (getProperties(properties.getTileMap()) != null) {
			throw new WMTSPropertiesException("properties already exist");
		}
		
		list.add(properties);
	}
	
	public static void addAllProperties(List<WMTSProperties> propertiesList) throws WMTSPropertiesException {
		for (WMTSProperties properties : propertiesList) {
			addProperties(properties);
		}
	}
	
}
