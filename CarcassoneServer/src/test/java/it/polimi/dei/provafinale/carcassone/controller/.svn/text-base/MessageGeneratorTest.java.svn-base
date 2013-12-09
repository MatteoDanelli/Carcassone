package it.polimi.dei.provafinale.carcassone.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import it.polimi.dei.provafinale.carcassone.model.CardinalPosition;
import it.polimi.dei.provafinale.carcassone.model.Color;
import it.polimi.dei.provafinale.carcassone.model.ModelPlayer;
import it.polimi.dei.provafinale.carcassone.model.ModelTile;
import it.polimi.dei.provafinale.carcassone.model.Position;

import org.junit.Before;
import org.junit.Test;

/**
 * @author andrea90c
 *
 */
public class MessageGeneratorTest {

	private MessageGenerator generator;
	
	@Before
	public void setUp() {
		generator = new MessageGenerator();
	}
	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.controller.MessageGenerator#generateConnect()}.
	 */
	@Test
	public void testGenerateConnect() {
		assertEquals("connect", generator.generateConnect());
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.controller.MessageGenerator#generateRotate()}.
	 */
	@Test
	public void testGenerateRotate() {
		assertEquals("rotate", generator.generateRotate());
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.controller.MessageGenerator#generatePlace(it.polimi.dei.provafinale.carcassone.model.Position)}.
	 */
	@Test
	public void testGeneratePlace() {
		assertEquals("place:0,0", generator.generatePlace(new Position("0,0")));
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.controller.MessageGenerator#generatePass()}.
	 */
	@Test
	public void testGeneratePass() {
		assertEquals("pass", generator.generatePass());
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.controller.MessageGenerator#generateTile(it.polimi.dei.provafinale.carcassone.model.CardinalPosition)}.
	 */
	@Test
	public void testGenerateTile() {
		assertEquals("tile:N", generator.generateTile(CardinalPosition.NORTH));
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.controller.MessageGenerator#generateTurn(it.polimi.dei.provafinale.carcassone.model.Color)}.
	 */
	@Test
	public void testGenerateTurn() {
		assertEquals("turn:red", generator.generateTurn(Color.RED));
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.controller.MessageGenerator#generateStart(it.polimi.dei.provafinale.carcassone.model.ModelTile, java.lang.String, it.polimi.dei.provafinale.carcassone.model.Color, int)}.
	 */
	@Test
	public void testGenerateStart() {
		String tilePattern = "N=N S=C W=S E=S NS=0 NE=0 NW=0 WE=1 SE=0 SW=0";
		ModelTile tile = new ModelTile(tilePattern);
		String gameName = "game1";
		Color currentColor = Color.RED;
		int playersNumber = 2;
		String message ="start:" + tilePattern + "," + gameName + "," + currentColor.toString().toLowerCase() + "," +playersNumber;
		assertEquals(message, generator.generateStart(tile, gameName, currentColor, playersNumber));
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.controller.MessageGenerator#generateNext(it.polimi.dei.provafinale.carcassone.model.ModelTile)}.
	 */
	@Test
	public void testGenerateNext() {
		String tilePattern = "N=N S=C W=S E=S NS=0 NE=0 NW=0 WE=1 SE=0 SW=0";
		ModelTile tile = new ModelTile(tilePattern);
		String message = "next:" +tilePattern;
		assertEquals(message, generator.generateNext(tile));
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.controller.MessageGenerator#generateScore(java.util.List)}.
	 */
	@Test
	public void testGenerateScore() {
		List<ModelPlayer> players = new ArrayList<ModelPlayer>();
		players.add(new ModelPlayer(Color.RED));
		players.add(new ModelPlayer(Color.BLUE));
		String message = "score:red=0,blue=0";
		assertEquals(message, generator.generateScore(players));
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.controller.MessageGenerator#generateFinalScore(java.util.List)}.
	 */
	@Test
	public void testGenerateFinalScore() {
		List<ModelPlayer> players = new ArrayList<ModelPlayer>();
		players.add(new ModelPlayer(Color.RED));
		players.add(new ModelPlayer(Color.BLUE));
		String message = "end:red=0,blue=0";
		assertEquals(message, generator.generateFinalScore(players));
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.controller.MessageGenerator#generateMoveNotValid()}.
	 */
	@Test
	public void testGenerateMoveNotValid() {
		assertEquals("move not valid", generator.generateMoveNotValid());
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.controller.MessageGenerator#generateRotated(it.polimi.dei.provafinale.carcassone.model.ModelTile)}.
	 */
	@Test
	public void testGenerateRotated() {
		String tilePattern = "N=N S=C W=S E=S NS=0 NE=0 NW=0 WE=1 SE=0 SW=0";
		ModelTile tile = new ModelTile(tilePattern);
		String message = "rotated:" +tilePattern;
		assertEquals(message, generator.generateRotated(tile));
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.controller.MessageGenerator#generateUpdate(it.polimi.dei.provafinale.carcassone.model.DisplayedTile, it.polimi.dei.provafinale.carcassone.model.Position)}.
	 */
	@Test
	public void testGenerateUpdate() {
		String tilePattern = "N=N S=C W=S E=S NS=0 NE=0 NW=0 WE=1 SE=0 SW=0";
		ModelTile tile = new ModelTile(tilePattern);
		String message = "update:" +tilePattern + ",0,0";
		assertEquals(message, generator.generateUpdate(tile.display(), new Position("0,0")));
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.controller.MessageGenerator#generateLock()}.
	 */
	@Test
	public void testGenerateLock() {
		assertEquals("lock", generator.generateLock());
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.controller.MessageGenerator#generateLeave(it.polimi.dei.provafinale.carcassone.model.Color)}.
	 */
	@Test
	public void testGenerateLeave() {
		assertEquals("leave:red", generator.generateLeave(Color.RED));
	}

}
