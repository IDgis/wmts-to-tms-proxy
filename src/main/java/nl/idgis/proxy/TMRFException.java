package nl.idgis.proxy;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 * TileMap resource file exception
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class TMRFException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2146785419530924768L;

	public TMRFException(String message) {
		super(message);
	}
}
