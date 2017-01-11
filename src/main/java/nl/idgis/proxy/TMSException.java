package nl.idgis.proxy;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class TMSException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5284457879254199356L;

	public TMSException(String message) {
		super(message);
	}
	
	public TMSException(Throwable cause) {
		super(cause);
	}
	
}
