/**
 * 
 */
package it.polimi.dei.provafinale.carcassone.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Matteo
 *
 */
public class ModelPlayerTest {
	ModelPlayer p1  = new ModelPlayer (Color.RED);
	ModelPlayer p1Copy = new ModelPlayer (Color.RED);
	ModelPlayer p2 = new ModelPlayer(Color.BLACK);
	ModelTile testTile = new ModelTile("N=C S=S W=S E=N NS=0 NE=0 NW=0 WE=0 SE=0 SW=1");

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.ModelPlayer#getFlagColor()}.
	 */
	@Test
	public void testGetFlagColor() {
		assertEquals("Error getting color", Color.RED, p1.getFlagColor());
	}

	/**
	 * Tested also setPoints()
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.ModelPlayer#getPoints()}.
	 */
	@Test
	public void testGetPoints() {
		assertEquals("Error getting points", 0, p1.getPoints());
		p2.setPoints(15);
		assertEquals("Error getting points", 15, p2.getPoints());

	}

	/**
	 * Tested also setCurrentTile()
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.ModelPlayer#getCurrentTile()}.
	 */
	@Test
	public void testGetCurrentTile() {
		assertNull("Error getting default current Tile", p1.getCurrentTile());
		p2.setCurrentTile(testTile);
		assertEquals("Error getting current Tile", testTile, p2.getCurrentTile());
	}

	/**
	 * Tested also placeFlag() and receiveFlag()
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.ModelPlayer#getAvailableFlags()}.
	 * @throws IllegalAccessException 
	 */
	@Test
	public void testGetAvailableFlags() {
		assertEquals(7, p1.getAvailableFlags());
		if(p2.hasMoreFlags()) {
			p2.placeFlag();
		} 
		else {
			fail();
		}
		assertEquals(6, p2.getAvailableFlags());
		if(p2.hasMoreFlags()) {
			p2.placeFlag();
		} 
		else {
			fail();
		}
		assertEquals(5, p2.getAvailableFlags());
		p2.receiveFlag();
		assertEquals(6, p2.getAvailableFlags());

		
	}
	
	@Test
	public void testEquals() {
		assertEquals("Error on equals()", p1, p1Copy);
	}

}
