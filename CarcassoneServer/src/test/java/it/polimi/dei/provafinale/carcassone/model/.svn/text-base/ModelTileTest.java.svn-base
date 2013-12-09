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
public class ModelTileTest {

	private ModelTile tile1;
	private ModelTile tile1Equals;
	private ModelTile tile1Rotated;
	private ModelTile tile2;
	private ModelTile tile2Rotated;
	private ModelTile tile3;
	private ModelTile tile3WithFlag;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		tile1 = new ModelTile("N=N S=C W=S E=S NS=0 NE=0 NW=0 WE=1 SE=0 SW=0");
		tile1Equals = new ModelTile("N=N S=C W=S E=S NS=0 NE=0 NW=0 WE=1 SE=0 SW=0");
		tile1Rotated = new ModelTile("N=S S=S W=C E=N NS=1 NE=0 NW=0 WE=0 SE=0 SW=0");
		tile2 = new ModelTile("N=S S=S+G W=S E=S NS=0 NE=0 NW=1 WE=0 SE=1 SW=0");
		tile2Rotated = new ModelTile("N=S S=S W=S+G E=S NS=0 NE=1 NW=0 WE=0 SE=0 SW=1");
		tile3 = new ModelTile("N=C S=S W=C E=S NS=0 NE=0 NW=1 WE=0 SE=1 SW=0");
		tile3WithFlag = new ModelTile("N=C+B S=S W=C E=S NS=0 NE=0 NW=1 WE=0 SE=1 SW=0");
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.ModelTile#toString()}.
	 */
	@Test
	public void testToString() {
		assertEquals("Error toString", "N=N S=C W=S E=S NS=0 NE=0 NW=0 WE=1 SE=0 SW=0", tile1.toString());
		assertEquals("Error toString", "N=S S=S+G W=S E=S NS=0 NE=0 NW=1 WE=0 SE=1 SW=0", tile2.toString());
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.ModelTile#rotate()}.
	 */
	@Test
	public void testRotate() {
		assertEquals("Error rotating tile", tile1Rotated, tile1.rotate());
		assertEquals("Error rotating tile", tile2Rotated, tile2.rotate());
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.ModelTile#getTileArea(it.polimi.dei.provafinale.carcassone.model.CardinalPosition)}.
	 */
	@Test
	public void testGetTileArea() {
		TileArea north = new TileArea("N=N");		
		TileArea south = new TileArea("S=C");
		TileArea west = new TileArea("W=S");
		TileArea east = new TileArea("E=S");

		assertEquals("Error getting correct tileArea", north, tile1.getTileArea(CardinalPosition.NORTH));
		assertEquals("Error getting correct tileArea", south, tile1.getTileArea(CardinalPosition.SOUTH));
		assertEquals("Error getting correct tileArea", west, tile1.getTileArea(CardinalPosition.WEST));
		assertEquals("Error getting correct tileArea", east, tile1.getTileArea(CardinalPosition.EAST));
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.ModelTile#getTileConnection(it.polimi.dei.provafinale.carcassone.model.CardinalPosition, it.polimi.dei.provafinale.carcassone.model.CardinalPosition)}.
	 */
	@Test
	public void testGetTileConnection() {
		TileConnection NSNotPresent = new TileConnection("NS=0");
		TileConnection NENotPresent = new TileConnection("NE=0");
		TileConnection NWNotPresent = new TileConnection("NW=0");
		TileConnection WEPresent = new TileConnection("WE=1");
		TileConnection SENotPresent = new TileConnection("SE=0");
		TileConnection SWNotPresent = new TileConnection("SW=0");
		
		assertEquals("Error getting correct TileConnection", NSNotPresent, tile1.getTileConnection(CardinalPosition.NORTH, CardinalPosition.SOUTH));
		assertEquals("Error getting correct TileConnection", NENotPresent, tile1.getTileConnection(CardinalPosition.NORTH, CardinalPosition.EAST));
		assertEquals("Error getting correct TileConnection", NWNotPresent, tile1.getTileConnection(CardinalPosition.NORTH, CardinalPosition.WEST));
		assertEquals("Error getting correct TileConnection", WEPresent, tile1.getTileConnection(CardinalPosition.WEST, CardinalPosition.EAST));
		assertEquals("Error getting correct TileConnection", SENotPresent, tile1.getTileConnection(CardinalPosition.SOUTH, CardinalPosition.EAST));
		assertEquals("Error getting correct TileConnection", SWNotPresent, tile1.getTileConnection(CardinalPosition.SOUTH, CardinalPosition.WEST));
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.ModelTile#getIndipendentAreas()}.
	 */
	@Test
	public void testGetIndipendentAreas() {
		assertEquals("Error getting independent areas", 2, tile1.getIndipendentAreas().size());
		assertEquals("Error getting independent areas", 2, tile2.getIndipendentAreas().size());
		assertEquals("Error getting independent areas", 2, tile3.getIndipendentAreas().size());
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.ModelTile#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		assertTrue("Error on equals override", tile1.equals(tile1Equals));
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.ModelTile#getConnectedAreas(it.polimi.dei.provafinale.carcassone.model.TileArea)}.
	 */
	@Test
	public void testGetConnectedAreas() {
		assertEquals("Error getting connected areas", 1, tile1.getConnectedAreas(tile1.getTileArea(CardinalPosition.WEST)).size());
		assertEquals("Error getting connected areas", 0, tile1.getConnectedAreas(tile1.getTileArea(CardinalPosition.SOUTH)).size());
		assertEquals("Error getting connected areas", 0, tile1.getConnectedAreas(tile1.getTileArea(CardinalPosition.NORTH)).size());
		assertEquals("Error getting connected areas", 1, tile2.getConnectedAreas(tile1.getTileArea(CardinalPosition.NORTH)).size());
		assertEquals("Error getting connected areas", 1, tile2.getConnectedAreas(tile1.getTileArea(CardinalPosition.SOUTH)).size());
		assertEquals("Error getting connected areas", 1, tile3.getConnectedAreas(tile1.getTileArea(CardinalPosition.NORTH)).size());
		assertEquals("Error getting connected areas", 1, tile3.getConnectedAreas(tile1.getTileArea(CardinalPosition.SOUTH)).size());
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.ModelTile#isCompatible(it.polimi.dei.provafinale.carcassone.model.ModelTile, it.polimi.dei.provafinale.carcassone.model.CardinalPosition)}.
	 */
	@Test
	public void testIsCompatible() {
		//tile1-tile2
		assertTrue("Error on compatible tiles", tile1.isCompatible(tile2, CardinalPosition.EAST));
		assertTrue("Error on compatible tiles", tile1.isCompatible(tile2, CardinalPosition.WEST));
		assertFalse("Error on compatible tiles", tile1.isCompatible(tile2, CardinalPosition.NORTH));
		assertFalse("Error on compatible tiles", tile1.isCompatible(tile2, CardinalPosition.SOUTH));
		//tile2-tile3
		assertTrue("Error on compatible tiles", tile2.isCompatible(tile3, CardinalPosition.NORTH));
		assertTrue("Error on compatible tiles", tile2.isCompatible(tile3, CardinalPosition.WEST));
		assertFalse("Error on compatible tiles", tile2.isCompatible(tile3, CardinalPosition.SOUTH));
		assertFalse("Error on compatible tiles", tile2.isCompatible(tile3, CardinalPosition.EAST));
		//tile3-tile1
		assertTrue("Error on compatible tiles", tile3.isCompatible(tile1, CardinalPosition.EAST));
		assertTrue("Error on compatible tiles", tile3.isCompatible(tile1, CardinalPosition.NORTH));
		assertFalse("Error on compatible tiles", tile3.isCompatible(tile1, CardinalPosition.WEST));
		assertFalse("Error on compatible tiles", tile3.isCompatible(tile1, CardinalPosition.SOUTH));
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.ModelTile#putFlag(it.polimi.dei.provafinale.carcassone.model.Color, it.polimi.dei.provafinale.carcassone.model.CardinalPosition)}.
	 */
	@Test
	public void testPutFlag() {
		assertEquals("Error putting flag on tile", tile3WithFlag, tile3.putFlag(Color.BLUE, CardinalPosition.NORTH));
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.ModelTile#removeFlag()}.
	 */
	@Test
	public void testRemoveFlag() {
		assertEquals("Error removing flag from tile", tile3, tile3WithFlag.removeFlag());
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.ModelTile#hasFlag()}.
	 */
	@Test
	public void testHasFlag() {
		assertTrue("Error checking flag on tile", tile3WithFlag.hasFlag());
		assertTrue("Error checking flag on tile", tile2.hasFlag());
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.ModelTile#getFlag()}.
	 */
	@Test
	public void testGetFlag() {
		assertEquals("Error getting flag from tile", Color.GREEN, tile2.getFlag());
		assertEquals("Error getting flag from tile", Color.BLUE, tile3WithFlag.getFlag());
	}

}
