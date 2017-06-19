package nl.idgis.proxy.tmrf;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import nl.idgis.proxy.WMTSPropertiesException;

public class TileMapResourceFiles {
	
	private static List<TileMapResourceFile> tileMapResourceFileList;
	
	public static void add(String name, InputStream inputStream) throws WMTSPropertiesException {
		if (tileMapResourceFileList == null) {
			init();
		}
		
		try {
			tileMapResourceFileList.add(readTileMapResourceFile(name, inputStream));
		} catch (XMLStreamException | WMTSPropertiesException e) {
			throw new WMTSPropertiesException("an error occurred while parsing TileMap resource file", e);
		}
	}

	private static TileMapResourceFile readTileMapResourceFile(String name, InputStream inputStream) throws XMLStreamException, WMTSPropertiesException {
		TileMapResourceFile newFile = new TileMapResourceFile(name);
		
		XMLInputFactory inFactory = XMLInputFactory.newInstance();
		XMLStreamReader reader = inFactory.createXMLStreamReader(inputStream);
		
		int event = 0;
		
		
		
		while (reader.hasNext()) {
			event = reader.next();
			
			switch (event) {
			case XMLStreamReader.START_ELEMENT:
				if ("TileSet".equals(reader.getLocalName())) {
					final String href = reader.getAttributeValue(null, "href");
					final String tileMatrix = href.substring(href.lastIndexOf('/') + 1);
					
					final int order = Integer.parseInt(reader.getAttributeValue(null, "order"));
					
					newFile.addTileSet(new TileSet(tileMatrix, order));
				}
				break;
			}
		}
		
		if (newFile.getTileSetCount() == 0) {
			throw new WMTSPropertiesException("no tile sets found for TileMap resource file");
		}
		
		return newFile;
	}

	private static void init() {
		tileMapResourceFileList = new ArrayList<>();
	}

	public static TileMapResourceFile getTileMapResourceFile(String name) {
		for (TileMapResourceFile file : tileMapResourceFileList) {
			if (name.equals(file.getName())) {
				return file;
			}
		}
		
		return null;
	}
	
}
