/**
 * 
 */
package it.polimi.dei.provafinale.carcassone.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Matteo Danelli
 *
 */
public class TileConnectionTest {
	
	private TileConnection ns0;
	private TileConnection we1;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		ns0 = new TileConnection("NS=0");
		we1 = new TileConnection("WE=1");
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.TileConnection#TileConnection(java.lang.String)}.
	 */
	@Test
	public void testTileConnection() {
		// First Tile Connection
		assertNotNull("Error on tileConnection", ns0.getFromPosition());
		assertEquals("Error on tileConnection", CardinalPosition.NORTH, ns0.getFromPosition());
		assertNotNull("Error on tileConnection", ns0.getToPosition());
		assertEquals("Error on tileConnection", CardinalPosition.SOUTH, ns0.getToPosition());
		assertNotNull("Error on tileConnection", ns0.getConnectionType());
		assertEquals("Error on tileConnection", ConnectionType.NOTPRESENT, ns0.getConnectionType());
		// Second Tile Connection
		assertNotNull("Error on tileConnection", we1.getFromPosition());
		assertEquals("Error on tileConnection", CardinalPosition.WEST, we1.getFromPosition());
		assertNotNull("Error on tileConnection", we1.getToPosition());
		assertEquals("Error on tileConnection", CardinalPosition.EAST, we1.getToPosition());
		assertNotNull("Error on tileConnection", we1.getConnectionType());
		assertEquals("Error on tileConnection", ConnectionType.PRESENT, we1.getConnectionType());
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.TileConnection#isChosenConnection(it.polimi.dei.provafinale.carcassone.model.CardinalPosition, it.polimi.dei.provafinale.carcassone.model.CardinalPosition)}.
	 */
	@Test
	public void testIsChosenConnection() {
		assertTrue("Error on isChosenConnection", ns0.isChosenConnection(CardinalPosition.NORTH, CardinalPosition.SOUTH));
		assertFalse("Error on isChosenConnection", ns0.isChosenConnection(CardinalPosition.NORTH, CardinalPosition.EAST));
		assertFalse("Error on isChosenConnection", ns0.isChosenConnection(CardinalPosition.WEST, CardinalPosition.SOUTH));
		assertTrue("Error on isChosenConnection", we1.isChosenConnection(CardinalPosition.WEST, CardinalPosition.EAST));
		assertFalse("Error on isChosenConnection", we1.isChosenConnection(CardinalPosition.NORTH, CardinalPosition.WEST));
		assertFalse("Error on isChosenConnection", we1.isChosenConnection(CardinalPosition.WEST, CardinalPosition.SOUTH));
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.TileConnection#isPresent()}.
	 */
	@Test
	public void testIsPresent() {
		assertFalse("Error on testing isPresent", ns0.isPresent());
		assertTrue("Error on testing isPresent", we1.isPresent());
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.TileConnection#toString()}.
	 */
	@Test
	public void testToString() {
		// First Tile Connection
		assertNotNull("Error on toString", ns0.toString());
		assertEquals("Error on toString", "NS=0", ns0.toString());
		// Second Tile Connection
		assertNotNull("Error on toString", we1.toString());
		assertEquals("Error on toString", "WE=1", we1.toString());
	}

}
