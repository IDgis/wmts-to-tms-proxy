package nl.idgis.proxy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MatrixMappingTest {

	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testMatrixMapping() throws Exception {
		MatrixMapping matrixMapping = new MatrixMapping();
		matrixMapping.initialize("EPSG:28992=NLDEPSG28992Scale");
		assertEquals(matrixMapping.get("EPSG:28992"), "NLDEPSG28992Scale");
		
		matrixMapping = new MatrixMapping();
		matrixMapping.initialize(null);
		assertNull(matrixMapping.get("EPSG:28992"));
		
		matrixMapping = new MatrixMapping();
		exception.expect(MappingException.class);
		matrixMapping.initialize("");
	}
}
