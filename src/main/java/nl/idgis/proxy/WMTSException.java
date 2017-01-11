package nl.idgis.proxy;

public class WMTSException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1832143249831508129L;

	private int status;

	public WMTSException(int status) {
		super(String.format("HTTP status %d", status));
		this.status = status;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}
