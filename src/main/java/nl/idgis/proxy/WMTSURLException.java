package nl.idgis.proxy;

public class WMTSURLException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1572138196945856623L;
	
	public WMTSURLException(String s) {
		super(s);
	}

	public WMTSURLException(Exception e) {
		super(e.getMessage(), e);
	}
	
}
