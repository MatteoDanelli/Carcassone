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
public class ModelMapTest {

		private ModelMap testMap = new ModelMap();
		Position p_10, p00, p10, p20, p01, p11, p21, p0_1, p1_1, p2_1, p0_2, p1_2, p02, p12;
		ModelTile testTile1, testTile2, testTile3, testTile4, testTile5, testTile6;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		p_10 = new Position (-1,0); 
		p01 = new Position (0,1); 
		p00 = new Position (0,0); 
		p10 = new Position (1,0); 
		p20 = new Position (2,0); 
		p0_1 = new Position (0,-1); 
		p1_1 = new Position (1,-1);
		p11 = new Position (1,1); 
		p2_1 = new Position (2,-1); 
		p21 = new Position (2, 1); 
		p0_2 = new Position (0,-2); 
		p02 = new Position (0,2);
		p1_2 = new Position(1, -2);
		testTile1 = new ModelTile ("N=N S=C W=S E=S NS=0 NE=0 NW=0 WE=1 SE=0 SW=0");
		testTile2 = new ModelTile("N=S S=S+G W=S E=S NS=0 NE=0 NW=0 WE=0 SE=0 SW=0");
		testTile3 = new ModelTile("N=C S=C W=N E=N NS=0 NE=0 NW=0 WE=0 SE=0 SW=0");
		testTile4 = new ModelTile("N=C S=S W=C+R E=S NS=0 NE=0 NW=1 WE=0 SE=1 SW=0");
		testTile5 = new ModelTile("N=C S=S W=S E=N NS=0 NE=0 NW=0 WE=0 SE=0 SW=1");
		testTile6 = new ModelTile("N=C S=C W=C E=C NS=1 NE=1 NW=1 WE=1 SE=1 SW=1");

	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.ModelMap#getTileFromPosition(it.polimi.dei.provafinale.carcassone.model.Position)}.
	 */
	@Test
	public void testGetTileFromPosition() {
		testMap.putTileInPosition(p00, testTile1);
		assertEquals("Error getting tile from position", testTile1, testMap.getTileFromPosition(p00));
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.ModelMap#thereIsTileIn(it.polimi.dei.provafinale.carcassone.model.Position)}.
	 */
	@Test
	public void testThereIsTileIn() {
		testMap.putTileInPosition(p00, testTile1);
		assertTrue("Error checking if the tile is in position", testMap.thereIsTileIn(p00));
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.ModelMap#isAnAvailablePosition(it.polimi.dei.provafinale.carcassone.model.Position)}.
	 */
	@Test
	public void testIsAnAvailablePosition() {
		assertFalse("Error checking available position", testMap.isAnAvailablePosition(p00));	
		testMap.putTileInPosition(p10, testTile1);
		assertFalse("Error checking available position", testMap.isAnAvailablePosition(p10));
		assertFalse("Error checking available position", testMap.isAnAvailablePosition(p01));
		assertFalse("Error checking available position", testMap.isAnAvailablePosition(p0_1));
		assertFalse("Error checking available position", testMap.isAnAvailablePosition(p2_1));
		assertFalse("Error checking available position", testMap.isAnAvailablePosition(p12));
		assertFalse("Error checking available position", testMap.isAnAvailablePosition(p0_2));
		assertTrue("Error checking available position", testMap.isAnAvailablePosition(p00));
		assertTrue("Error checking available position", testMap.isAnAvailablePosition(p20));	
		assertTrue("Error checking available position", testMap.isAnAvailablePosition(p1_1));	
		assertTrue("Error checking available position", testMap.isAnAvailablePosition(p11));	

	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.ModelMap#isPlaceableWithRotations(it.polimi.dei.provafinale.carcassone.model.ModelTile)}.
	 */
	@Test
	public void testIsPlaceableWithRotations() {
		testMap.putTileInPosition(p00, testTile1);		
		assertTrue("Error checking placeable with rotations", testMap.isPlaceableWithRotations(testTile1));
		assertTrue("Error checking placeable with rotations", testMap.isPlaceableWithRotations(testTile2));
		assertTrue("Error checking placeable with rotations", testMap.isPlaceableWithRotations(testTile3));
		assertTrue("Error checking placeable with rotations", testMap.isPlaceableWithRotations(testTile4));
		assertTrue("Error checking placeable with rotations", testMap.isPlaceableWithRotations(testTile5));
		testMap.putTileInPosition(p0_1, testTile5);		
		assertFalse("Error checking placeable with rotations", testMap.isPlaceableWithRotations(testTile6));
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.ModelMap#isPlaceable(it.polimi.dei.provafinale.carcassone.model.ModelTile)}.
	 */
	@Test
	public void testIsPlaceable() {
		testMap.putTileInPosition(p00, testTile1);	
		assertTrue("Error checking placeable", testMap.isPlaceable(testTile1));
		assertTrue("Error checking placeable", testMap.isPlaceable(testTile2));
		assertTrue("Error checking placeable", testMap.isPlaceable(testTile3));
		assertTrue("Error checking placeable", testMap.isPlaceable(testTile4));
		assertTrue("Error checking placeable", testMap.isPlaceable(testTile5));
		testMap.putTileInPosition(p10, testTile2);
		assertTrue("Error checking placeable", testMap.isPlaceable(testTile3));
		assertTrue("Error checking placeable", testMap.isPlaceable(testTile2));
		testMap.putTileInPosition(p0_1, testTile5);		
		assertFalse("Error checking placeable", testMap.isPlaceable(testTile6));
		assertFalse("Error checking placeable", testMap.isPlaceable(testTile3));
		assertTrue("Error checking placeable", testMap.isPlaceable(testTile4));
		assertTrue("Error checking placeable", testMap.isPlaceable(testTile5));
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.ModelMap#isSuitable(it.polimi.dei.provafinale.carcassone.model.Position, it.polimi.dei.provafinale.carcassone.model.ModelTile)}.
	 */
	@Test
	public void testIsSuitable() {
		testMap.putTileInPosition(p00, testTile1);	
		//Try to east
		assertTrue("Error checking suitable tile", testMap.isSuitable(p10, testTile1));
		assertTrue("Error checking suitable tile", testMap.isSuitable(p10, testTile2));
		assertFalse("Error checking suitable tile", testMap.isSuitable(p10, testTile3));
		assertFalse("Error checking suitable tile", testMap.isSuitable(p10, testTile4));
		assertTrue("Error checking suitable tile", testMap.isSuitable(p10, testTile5));
		//Try to west
		assertTrue("Error checking suitable tile", testMap.isSuitable(p_10, testTile1));
		assertTrue("Error checking suitable tile", testMap.isSuitable(p_10, testTile2));
		assertFalse("Error checking suitable tile", testMap.isSuitable(p_10, testTile3));
		assertTrue("Error checking suitable tile", testMap.isSuitable(p_10, testTile4));
		assertFalse("Error checking suitable tile", testMap.isSuitable(p_10, testTile5));
		//Try to north
		assertFalse("Error checking suitable tile", testMap.isSuitable(p01, testTile1));
		assertFalse("Error checking suitable tile", testMap.isSuitable(p01, testTile2));
		assertFalse("Error checking suitable tile", testMap.isSuitable(p01, testTile3));
		assertFalse("Error checking suitable tile", testMap.isSuitable(p01, testTile4));
		assertFalse("Error checking suitable tile", testMap.isSuitable(p01, testTile5));
		//Try to south
		assertFalse("Error checking suitable tile", testMap.isSuitable(p0_1, testTile1));
		assertFalse("Error checking suitable tile", testMap.isSuitable(p0_1, testTile2));
		assertTrue("Error checking suitable tile", testMap.isSuitable(p0_1, testTile3));
		assertTrue("Error checking suitable tile", testMap.isSuitable(p0_1, testTile4));
		assertTrue("Error checking suitable tile", testMap.isSuitable(p0_1, testTile5));
		}
	
	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.ModelMap#putTileInPosition(it.polimi.dei.provafinale.carcassone.model.Position, it.polimi.dei.provafinale.carcassone.model.ModelTile)}.
	 */
	@Test
	public void testPutTileInPosition() {
		testMap.putTileInPosition(p00, testTile1);
		testMap.putTileInPosition(p10, testTile1);
		testMap.putTileInPosition(p0_1, testTile4);
		testMap.putTileInPosition(p1_1, testTile1);
		//Check free cells
		assertFalse("Error testing putTileInPosition", testMap.thereIsTileIn(p_10));
		assertFalse("Error testing putTileInPosition", testMap.thereIsTileIn(p20));
		assertFalse("Error testing putTileInPosition", testMap.thereIsTileIn(p0_2));
		assertFalse("Error testing putTileInPosition", testMap.thereIsTileIn(p01));
		assertFalse("Error testing putTileInPosition", testMap.thereIsTileIn(p11));
		
		//Check not free cells
		assertTrue("Error testing putTileInPosition", testMap.thereIsTileIn(p00));
		assertTrue("Error testing putTileInPosition", testMap.thereIsTileIn(p10));
		assertTrue("Error testing putTileInPosition", testMap.thereIsTileIn(p0_1));
		assertTrue("Error testing putTileInPosition", testMap.thereIsTileIn(p1_1));
		
		//Check the correctness of insert
		assertEquals("Error testing putTileInPosition", testTile1, testMap.getTileFromPosition(p00));
		assertEquals("Error testing putTileInPosition", testTile1, testMap.getTileFromPosition(p10));
		assertEquals("Error testing putTileInPosition", testTile4, testMap.getTileFromPosition(p0_1));
		assertEquals("Error testing putTileInPosition", testTile1, testMap.getTileFromPosition(p1_1));
		}


	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.ModelMap#getAllPlacedPositions()}.
	 */
	@Test
	public void testGetAllPlacedPositions() {
		testMap.putTileInPosition(p00, testTile1);
		testMap.putTileInPosition(p10, testTile1);
		testMap.putTileInPosition(p0_1, testTile5);
		assertEquals("Error getting all placed position", 3, testMap.getAllPlacedPositions().size());
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.ModelMap#generateTrack(it.polimi.dei.provafinale.carcassone.model.TileArea, it.polimi.dei.provafinale.carcassone.model.Position)}.
	 */
	@Test
	public void testGenerateTrack() {
		testMap.putTileInPosition(p00, testTile1);
		testMap.putTileInPosition(p0_1, testTile1.rotate().rotate());
		TileArea startPoint = testTile2.getTileArea(CardinalPosition.NORTH);
		Track testedTrack = testMap.generateTrack(startPoint, p0_1);
		assertEquals("Error generating track", 2, testedTrack.countTiles());
		
		testMap.putTileInPosition(p10, testTile4.rotate());
		testMap.putTileInPosition(p20, testTile4);
		testMap.putTileInPosition(p11, testTile3);
		startPoint = testTile4.getTileArea(CardinalPosition.NORTH);
		testedTrack = testMap.generateTrack(startPoint, p10);
		assertEquals("Error generating track", 3, testedTrack.countTiles());
		
		testMap.putTileInPosition(p21, testTile3);
		testedTrack = testMap.generateTrack(startPoint, p10);
		assertEquals("Error generating track", 4, testedTrack.countTiles());
	}

}
