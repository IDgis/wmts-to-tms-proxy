package nl.idgis.proxy;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class WMTSCapabilities {

	private String url;
	private String version;
	
	public WMTSCapabilities(String url, String version) {
		if (url == null || url.isEmpty()) {
			throw new NullPointerException("WMTS URL is null or empty");
		}
		
		if (version == null || version.isEmpty()) {
			throw new NullPointerException("WMTS version is null or empty");
		}
		
		this.url = url;
		this.version = version;
	}
	
	public InputStream getInputStream() throws MalformedURLException, IOException {
		return new URL(String.format("%s?SERVICE=WMTS&VERSION=1.0.0&REQUEST=GetCapabilities", url)).openStream();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
}
