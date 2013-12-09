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
public class DeckTest {
	ModelTile t1 = new ModelTile("N=C S=S W=S E=N NS=0 NE=0 NW=0 WE=0 SE=0 SW=1");
	ModelTile t2 = new ModelTile("N=N S=S W=S E=S NS=0 NE=0 NW=0 WE=0 SE=0 SW=0");
	ModelTile t3 = new ModelTile("N=N S=S W=S E=N NS=0 NE=0 NW=0 WE=0 SE=0 SW=1");
	Deck testDeck = new Deck();
	Deck emptyDeck = new Deck();

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		testDeck.add(t1);
		testDeck.add(t2);
	}

	/**
	 * Test method for
	 * {@link it.polimi.dei.provafinale.carcassone.model.Deck#drawTile()}.
	 */
	
	 @Test 
	 public void testDrawTile() { 
		 testDeck.drawTile();	
		 assertEquals("Error drawing a tile", 1, testDeck.size());
	 }


	/**
	 * Test method for
	 * {@link it.polimi.dei.provafinale.carcassone.model.Deck#size()}.
	 */
	@Test
	public void testSize() {
		assertEquals("Error getting correct size", 2, testDeck.size());
	}

	/**
	 * Test method for
	 * {@link it.polimi.dei.provafinale.carcassone.model.Deck#isEmpty()}.
	 */
	@Test
	public void testIsEmpty() {
		assertFalse("Error testing a not empty deck", testDeck.isEmpty());
		assertTrue("Error testing an empty deck", emptyDeck.isEmpty());
	}

	/**
	 * Test method for
	 * {@link it.polimi.dei.provafinale.carcassone.model.Deck#add(it.polimi.dei.provafinale.carcassone.model.ModelTile)}
	 * .
	 */
	@Test
	public void testAdd() {
		testDeck.add(t3);
		assertEquals("Error adding tile to deck", 3, testDeck.size());
	}

	/**
	 * Test method for
	 * {@link it.polimi.dei.provafinale.carcassone.model.Deck#contains(it.polimi.dei.provafinale.carcassone.model.ModelTile)}
	 * .
	 */
	@Test
	public void testContains() {
		assertTrue("Error testing hte method contains() in deck",
				testDeck.contains(t2));
	}

	/**
	 * Test method for
	 * {@link it.polimi.dei.provafinale.carcassone.model.Deck#search(it.polimi.dei.provafinale.carcassone.model.ModelTile)}
	 * .
	 */
	@Test
	public void testSearch() {
		assertEquals("Error searching a tile in deck", 1, testDeck.search(t2));
		assertEquals("Error searching a non-existente tile in deck", -1,
				testDeck.search(t3));
	}

	/**
	 * Test method for
	 * {@link it.polimi.dei.provafinale.carcassone.model.Deck#removeTile(int)}.
	 */
	@Test
	public void testRemoveTile() {
		testDeck.removeTile(1);
		assertEquals("Error removing a tile from deck", 1, testDeck.size());
		testDeck.removeTile(0);
		assertEquals("Error removing a tile from deck", 0, testDeck.size());
	}

}
