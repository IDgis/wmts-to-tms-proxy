package nl.idgis.proxy;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class RequestException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -506214930168372490L;

	public RequestException(String message) {
		super(message);
	}
}
