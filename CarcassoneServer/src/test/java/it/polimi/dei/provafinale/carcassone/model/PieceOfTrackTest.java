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
public class PieceOfTrackTest {
	
	TileArea tileArea1 = new TileArea("N=C");
	TileArea tileArea2 = new TileArea("S=S");
	TileArea tileArea3 = new TileArea("W=N");
	TileArea tileArea4 = new TileArea("E=N");
	TileArea tileArea5 = new TileArea("N=C+G");
	TileArea tileArea6 = new TileArea("S=S+R");
	TileArea tileArea7 = new TileArea("W=N+B");
	TileArea tileArea8 = new TileArea("E=N+G");
	PieceOfTrack piece1 = new PieceOfTrack();
	PieceOfTrack piece2 = new PieceOfTrack();

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.PieceOfTrack#add(it.polimi.dei.provafinale.carcassone.model.TileArea)}.
	 */
	@Test
	public void testAdd() {
		piece1.add(tileArea1);	
		piece1.add(tileArea3);			
		piece1.add(tileArea7);		
		assertTrue("Error on adding a tileArea", piece1.contains(tileArea1));
		assertTrue("Error on adding a tileArea", piece1.contains(tileArea3));
		assertTrue("Error on adding a tileArea", piece1.contains(tileArea7));
		assertFalse("Error on adding a tileArea", piece1.contains(tileArea5));
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.PieceOfTrack#contains(it.polimi.dei.provafinale.carcassone.model.TileArea)}.
	 */
	@Test
	public void testContains() {
		piece1.add(tileArea1);
		piece1.add(tileArea5);	
		assertTrue("Error on contains check", piece1.contains(tileArea1));
		assertTrue("Error on contains check", piece1.contains(tileArea5));
		assertFalse("Error on contains check", piece1.contains(tileArea3));
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.PieceOfTrack#countFlags(it.polimi.dei.provafinale.carcassone.model.Color)}.
	 */
	@Test
	public void testCountFlags() {
		piece1.add(tileArea1);
		piece1.add(tileArea5);
		piece1.add(tileArea6);
		piece2.add(tileArea2);
		piece2.add(tileArea5);
		piece2.add(tileArea5);
		piece2.add(tileArea8);
		assertEquals("Error counting flags", 1, piece1.countFlags(Color.GREEN));
		assertEquals("Error counting flags", 1, piece1.countFlags(Color.RED));
		assertEquals("Error counting flags", 0, piece1.countFlags(Color.YELLOW));
		assertEquals("Error counting flags", 3, piece2.countFlags(Color.GREEN));
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.PieceOfTrack#hasFlag()}.
	 */
	@Test
	public void testHasFlag() {
		piece1.add(tileArea1);
		piece1.add(tileArea5);
		piece1.add(tileArea6);
		piece2.add(tileArea2);
		piece2.add(tileArea3);
		piece2.add(tileArea4);
		assertTrue("Error checking flag", piece1.hasFlag());
		assertFalse("Error checking flag", piece2.hasFlag());
	}

}
