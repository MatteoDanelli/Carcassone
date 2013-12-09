/**
 * 
 */
package it.polimi.dei.provafinale.carcassone.model;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Matteo Danelli
 *
 */
 public class GameTest {
	
	Game testGame;
	ModelMap testMap;
	ModelTile tile1, tile1Rotated, tile2, tile3, tile4;
	Position p00, p10, p1_1, p0_1;
	ModelPlayer player1, player2;
	Color red = Color.RED;
	Color blue = Color.BLUE;
	Color player1Color, player2Color, player3Color;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		testGame = new Game();
		testGame.initializeGame();
		
		tile1 = new ModelTile("N=N S=C W=S E=S NS=0 NE=0 NW=0 WE=1 SE=0 SW=0");
		tile1Rotated = new ModelTile("N=S S=S W=C E=N NS=1 NE=0 NW=0 WE=0 SE=0 SW=0");
		tile2 = new ModelTile("N=S S=N W=S E=N NS=0 NE=0 NW=1 WE=0 SE=0 SW=0");
		tile3 = new ModelTile("N=C S=N W=N E=C NS=0 NE=1 NW=0 WE=0 SE=0 SW=0");
		tile4 = new ModelTile("N=N S=N W=C E=N NS=0 NE=0 NW=0 WE=0 SE=0 SW=0");
		
		p00 = new Position(0,0);
		p10 = new Position(1,0);
		p0_1 = new Position(0,-1);
		p1_1 = new Position(1,-1);
		
		//Red player
		player1Color = testGame.createPlayer();
		//Blu player
		player2Color = testGame.createPlayer();
		
		player1 = new ModelPlayer(player1Color);
		player2 = new ModelPlayer(player2Color);
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.Game#getPlacedTile(it.polimi.dei.provafinale.carcassone.model.Position)}.
	 */
	@Test
	public void testGetPlacedTile() {
		player1 = testGame.selectPlayer(player1Color);
		player1.setCurrentTile(tile1);
		testGame.putTileInPosition(player1.getFlagColor(), p10);
		assertEquals(tile1, player1.getCurrentTile());
		assertEquals(tile1, testGame.getPlacedTile(p10));
		
		player2 = testGame.selectPlayer(player2Color);
		player2.setCurrentTile(tile1Rotated);
		testGame.putTileInPosition(player2.getFlagColor(), p10);
		assertEquals(tile1Rotated, testGame.getPlacedTile(p10));

	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.Game#selectPlayer(it.polimi.dei.provafinale.carcassone.model.Color)}.
	 */
	@Test
	public void testSelectPlayer() {
		assertEquals(player1, testGame.selectPlayer(red));
		assertEquals(player2, testGame.selectPlayer(blue));
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.Game#createPlayer()}.
	 */
	@Test
	public void testCreatePlayer() {
		assertEquals(2, testGame.getPlayers().size());
		player3Color = testGame.createPlayer();
		assertEquals(3, testGame.getPlayers().size());
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.Game#initializeGame()}.
	 */
	@Test
	public void testInitializeGame() {
		boolean exceptionFlag = false;
		try {
			testGame.initializeGame();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			exceptionFlag=true;
		} catch (IOException e) {
			e.printStackTrace();
			exceptionFlag=true;
		}
		assertNotNull(testGame.getCurrentDeck());
		assertNotNull(testGame.getCurrentMap());
		assertFalse(exceptionFlag);
	}


	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.Game#nextTurn()}.
	 */
	@Test
	public void testNextTurn() {
		assertEquals(Color.RED, testGame.getTurnPlayer());
		testGame.nextTurn();
		assertEquals(blue, testGame.getTurnPlayer());
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.Game#isLastTurn()}.
	 */
	@Test
	public void testIsLastTurn() {
		for (int index = 0; index<55; index++) {
			testGame.nextTurn();
			testGame.drawTile(testGame.getTurnPlayer());
		}
		assertTrue(testGame.isLastTurn());
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.Game#rotateTile(it.polimi.dei.provafinale.carcassone.model.Color)}.
	 */
	@Test
	public void testRotateTile() {
		player1 = testGame.selectPlayer(player1Color);
		player1.setCurrentTile(tile1);
		testGame.rotateTile(player1Color);
		assertEquals(tile1Rotated, player1.getCurrentTile());
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.Game#putTileInPosition(it.polimi.dei.provafinale.carcassone.model.Color, it.polimi.dei.provafinale.carcassone.model.Position)}.
	 */
	@Test
	public void testPutTileInPosition() {

		player1 = testGame.selectPlayer(player1Color);
		assertEquals(tile1, testGame.getPlacedTile(p00));
		player1.setCurrentTile(tile1);
		testGame.putTileInPosition(player1Color, p10);
		assertEquals(tile1, testGame.getPlacedTile(p10));
		}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.Game#isAvailable(it.polimi.dei.provafinale.carcassone.model.Position)}.
	 */
	@Test
	public void testIsAvailable() {
		assertFalse(testGame.isAvailable(p00));
		assertTrue(testGame.isAvailable(p10));
		testGame.putTileInPosition(player1Color, p10);
		assertFalse(testGame.isAvailable(p10));
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.Game#isPlaceableWithRotations(it.polimi.dei.provafinale.carcassone.model.ModelTile)}.
	 */
	@Test
	public void testIsPlaceableWithRotations() {
		assertTrue(testGame.isPlaceableWithRotations(tile1));
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.Game#isPlaceable(it.polimi.dei.provafinale.carcassone.model.ModelTile)}.
	 */
	@Test
	public void testIsPlaceable() {
		assertTrue(testGame.isPlaceable(tile1));
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.Game#isSuitable(it.polimi.dei.provafinale.carcassone.model.Position, it.polimi.dei.provafinale.carcassone.model.ModelTile)}.
	 */
	@Test
	public void testIsSuitable() {
		assertTrue(testGame.isSuitable(p10, tile1));
		assertFalse(testGame.isSuitable(p10, tile3));
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.Game#isPlaceableHere(it.polimi.dei.provafinale.carcassone.model.ModelTile, it.polimi.dei.provafinale.carcassone.model.Position)}.
	 */
	@Test
	public void testIsPlaceableHere() {
		assertTrue(testGame.isPlaceableHere(tile1, p10));
		assertFalse(testGame.isPlaceableHere(tile1, p00));	
		}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.Game#calculateScore(it.polimi.dei.provafinale.carcassone.model.Position)}.
	 */
	@Test
	public void testCalculateScore() {
		player1 = testGame.selectPlayer(player1Color);
		player1.setCurrentTile(tile2);
		testGame.putTileInPosition(player1Color, p10);
		player1.setCurrentTile(tile3);
		testGame.putTileInPosition(player1Color, p0_1);
		player1.setCurrentTile(tile4);
		testGame.putTileInPosition(player1Color, p1_1);
		testGame.insertFlag(p10, CardinalPosition.NORTH, player1.getFlagColor());
		testGame.calculateScore(p00);
		assertEquals(0, player1.getPoints());
		testGame.insertFlag(p0_1, CardinalPosition.NORTH, player1.getFlagColor());
		testGame.calculateScore(p0_1);
		assertEquals(6, player1.getPoints());
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.Game#calculateFinalScores()}.
	 */
	@Test
	public void testCalculateFinalScores() {
		while(!testGame.isLastTurn()) {
			testGame.drawTile(player1Color);
		}
		player1 = testGame.selectPlayer(player1Color);
		player1.setCurrentTile(tile2);
		testGame.putTileInPosition(player1Color, p10);
		player1.setCurrentTile(tile3);
		testGame.putTileInPosition(player1Color, p0_1);
		player1.setCurrentTile(tile4);
		testGame.putTileInPosition(player1Color, p1_1);
		testGame.insertFlag(p10, CardinalPosition.WEST, player1.getFlagColor());
		testGame.insertFlag(p0_1, CardinalPosition.NORTH, player1.getFlagColor());
		testGame.calculateFinalScores();
		assertEquals(5, player1.getPoints());
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.Game#canPlaceFlag(it.polimi.dei.provafinale.carcassone.model.Position, it.polimi.dei.provafinale.carcassone.model.CardinalPosition)}.
	 */
	@Test
	public void testCanPlaceFlag() {
		player1 = testGame.selectPlayer(player1Color);
		player1.setCurrentTile(tile2);
		testGame.putTileInPosition(player1.getFlagColor(), p10);
		assertTrue(testGame.canPlaceFlag(p10, CardinalPosition.NORTH));
		assertFalse(testGame.canPlaceFlag(p10, CardinalPosition.SOUTH));
		
		player1.setCurrentTile(tile3);
		testGame.putTileInPosition(player1.getFlagColor(), p0_1);
		assertTrue(testGame.canPlaceFlag(p0_1, CardinalPosition.NORTH));
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.Game#canPlaceFlagInTile(it.polimi.dei.provafinale.carcassone.model.Position)}.
	 */
	@Test
	public void testCanPlaceFlagInTile() {
		player1 = testGame.selectPlayer(player1Color);
		player1.setCurrentTile(tile2);
		testGame.putTileInPosition(player1.getFlagColor(), p10);
		testGame.insertFlag(p10, CardinalPosition.NORTH, player1.getFlagColor());
		assertTrue(testGame.canPlaceFlagInTile(p00));
		assertFalse(testGame.canPlaceFlagInTile(p10));

	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.Game#insertFlag(it.polimi.dei.provafinale.carcassone.model.Position, it.polimi.dei.provafinale.carcassone.model.CardinalPosition, it.polimi.dei.provafinale.carcassone.model.Color)}.
	 */
	@Test
	public void testInsertFlag() {
		player1 = testGame.selectPlayer(player1Color);
		player1.setCurrentTile(tile2);
		testGame.putTileInPosition(player1.getFlagColor(), p10);
		testGame.insertFlag(p10, CardinalPosition.NORTH, player1.getFlagColor());
		assertTrue(testGame.getCurrentMap().getTileFromPosition(p10).hasFlag());
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.Game#getPlayers()}.
	 */
	@Test
	public void testGetPlayers() {
		assertEquals(2, testGame.getPlayers().size());
		testGame.createPlayer();
		assertEquals(3, testGame.getPlayers().size());

	}
	
	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.Game#removePlayer(Color)}.
	 */
	@Test
	public void testRemovePlayer() {
		player1 = testGame.selectPlayer(player1Color);
		player1.setCurrentTile(tile2);
		testGame.putTileInPosition(player1Color, p10);
		player1.setCurrentTile(tile3);
		testGame.putTileInPosition(player1Color, p0_1);
		player1.setCurrentTile(tile4);
		testGame.putTileInPosition(player1Color, p1_1);
		testGame.insertFlag(p10, CardinalPosition.NORTH, player1.getFlagColor());
		testGame.insertFlag(p0_1, CardinalPosition.NORTH, player1.getFlagColor());
		testGame.removePlayer(red);
		assertTrue(testGame.canPlaceFlag(p10, CardinalPosition.NORTH));
		assertEquals(1, testGame.getPlayers().size());
	} 

}
