package nl.idgis.proxy;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class WMTSException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1832143249831508129L;

	private int status;

	public WMTSException(int status) {
		super(String.format("WMTS returned HTTP status %d", status));
		this.status = status;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}
