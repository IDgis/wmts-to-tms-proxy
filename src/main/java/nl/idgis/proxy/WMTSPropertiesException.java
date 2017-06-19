package nl.idgis.proxy;

public class WMTSPropertiesException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1142257771554469086L;
	
	public WMTSPropertiesException(String message) {
		super(message);
	}

	public WMTSPropertiesException(String message, Exception e) {
		super (message, e);
	}
	
}
