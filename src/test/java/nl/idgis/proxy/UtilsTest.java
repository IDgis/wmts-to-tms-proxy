package nl.idgis.proxy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class UtilsTest {

	@Test
	public void testVersionValidator() {
		assertTrue(Utils.isValidVersion("0.0.0"));
		assertFalse(Utils.isValidVersion("00.00.00"));
		assertFalse(Utils.isValidVersion("a.a.a"));
		assertFalse(Utils.isValidVersion("-.-.-"));
		assertFalse(Utils.isValidVersion("0.0"));
		assertFalse(Utils.isValidVersion("0.0.0.0"));
		assertFalse(Utils.isValidVersion(""));
		assertFalse(Utils.isValidVersion(null));
		assertFalse(Utils.isValidVersion("0-0-0"));
	}
	
	@Test
	public void testIntegerValidator() {
		assertTrue(Utils.isInteger("0"));
		assertFalse(Utils.isInteger("-0"));
		assertFalse(Utils.isInteger("a"));
		assertFalse(Utils.isInteger("0.0"));
	}
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testWmtsRow() {
		assertEquals(Utils.wmtsRow(3, 3), 4);

		exception.expect(IllegalArgumentException.class);
		Utils.wmtsRow(3, -1);
	}
	
}
