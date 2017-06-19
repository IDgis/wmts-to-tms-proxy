package nl.idgis.proxy;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.InputStream;

import org.junit.Test;

import nl.idgis.proxy.tmrf.TileMapResourceFile;
import nl.idgis.proxy.tmrf.TileMapResourceFiles;
import nl.idgis.proxy.tmrf.TileSet;

public class TileMapResourceFilesTest {
	
	public static String FILE_NAME = "test@EPSG%3A28992@jpeg";
	public static String EXTENSION = ".xml";

	@Test
	public void testParsing() throws Exception {
		InputStream stream = Application.class.getClassLoader().getResourceAsStream(FILE_NAME + EXTENSION);
		
		assertNotNull(stream);
		
		TileMapResourceFiles.add(FILE_NAME, stream);
		
		TileMapResourceFile tmrf = TileMapResourceFiles.getTileMapResourceFile(FILE_NAME);
		
		assertNotNull(tmrf);
		
		TileSet tileSet = tmrf.getTileSet("D");
		
		assertNotNull(tileSet);
		
		assertTrue(tileSet.getOrder() == 3);
		assertNull(tmrf.getTileSet("R"));
		assertNull(tmrf.getTileSet("0"));
	}
}
