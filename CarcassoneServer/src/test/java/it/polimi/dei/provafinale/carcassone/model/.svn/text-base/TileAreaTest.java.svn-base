package it.polimi.dei.provafinale.carcassone.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This test class tests the methods of the Field class.
 * 
 * @author Matteo Danelli
 * @version 1.0
 */
public class TileAreaTest {

	private TileArea nn;

	private TileArea sc;

	private TileArea nsg;

	/**
	 * Given some chosen patterns this setUp method instantiate the Field
	 * objects for the tests.
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		nn = new TileArea("N=N");
		sc = new TileArea("S=C");
		nsg = new TileArea("N=S+G");
	}

	/**
	 * Test method for the Field constructor. It controls if four of the
	 * instantiated objects are rights.
	 */
	@Test
	public void testTileArea() {
		// First Tile Area
		assertNotNull(nn.getAreaPosition());
		assertEquals(CardinalPosition.NORTH, nn.getAreaPosition());
		assertNotNull(nn.getAreaType());
		assertEquals(FieldType.FIELD, nn.getAreaType());
		assertNull(nn.getAreaFlag());
		// Second Tile Area
		assertNotNull(sc.getAreaPosition());
		assertEquals(CardinalPosition.SOUTH, sc.getAreaPosition());
		assertNotNull(sc.getAreaType());
		assertEquals(FieldType.TOWN, sc.getAreaType());
		assertNull(sc.getAreaFlag());
		// Third Tile Area
		assertNotNull(nsg.getAreaPosition());
		assertEquals(CardinalPosition.NORTH, nsg.getAreaPosition());
		assertNotNull(nsg.getAreaType());
		assertEquals(FieldType.STREET, nsg.getAreaType());
		assertNotNull(nsg.getAreaFlag());
		assertEquals(Color.GREEN, nsg.getAreaFlag());
	}

	/**
	 * Test method for assigning a Type to a Field
	 */
	@Test
	public void testToString() {
		assertEquals("N=N", nn.toString());
		assertEquals("N=S+G", nsg.toString());
	}
}
