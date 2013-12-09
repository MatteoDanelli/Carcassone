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
public class DeckBuilderTest {
	
	private String filepath = "./src/test/resources/carcassone.txt";
	private DeckBuilder deckBuilder = new DeckBuilder();
	private Deck deckBuilded = new Deck();
	private ModelTile tile1, tile2;
	private ModelTile tile3;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		deckBuilded = deckBuilder.initializeDeck(filepath);
		tile1 = new ModelTile("N=C S=S W=S E=N NS=0 NE=0 NW=0 WE=0 SE=0 SW=1");
		tile2 = new ModelTile("N=C S=S W=S E=N NS=0 NE=0 NW=0 WE=0 SE=0 SW=1");
		tile3 = new ModelTile("N=C S=S W=S E=S NS=0 NE=0 NW=0 WE=0 SE=0 SW=0");
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.DeckBuilder#initializeDeck(java.lang.String)}.
	 */
	@Test
	public void testInitializeDeck() {
		assertEquals("Error initializing deck", 7, deckBuilded.size());
		assertTrue("Error initializing deck", deckBuilded.contains(tile1));
		assertTrue("Error initializing deck", deckBuilded.contains(tile2));
		assertFalse("Error initializing deck", deckBuilded.contains(tile3));
		assertFalse("Error initializing deck", deckBuilded.isEmpty());
	}

}
