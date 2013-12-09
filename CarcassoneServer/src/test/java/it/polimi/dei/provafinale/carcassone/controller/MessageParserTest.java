package it.polimi.dei.provafinale.carcassone.controller;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

/**
 * @author Canidio Andrea
 *
 */
public class MessageParserTest {

	private MessageParser parser = new MessageParser();
	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.controller.MessageParser#parseMessage(java.lang.String)}.
	 */
	@Test
	public final void testParseMessage() {
		String command = null;
		// Testing Start Messages
		try {
			command = null;
			command = parser.parseMessage("start:N=N S=C W=S E=S NS=0 NE=0 NW=0 WE=1 SE=0 SW=0,game1,red,4");
			assertEquals(MessageParser.START, command);
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
		try {
			command = null;
			command = parser.parseMessage("start:N=N S=C W=S E=S NS=0 NE=0 NW=0 WE=1 SE=0 SW=0,red,4");
			fail();
		} catch (NoSuchElementException e) {
			assertEquals(null, command);
		}
		try {
			command = null;
			command = parser.parseMessage("start");
			fail();
		} catch (NoSuchElementException e) {
			assertEquals(null, command);
		}
		// Testing Turn Message
		try {
			command = null;
			command = parser.parseMessage("turn:red");
			assertEquals(MessageParser.TURN, command);
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
		try {
			command = null;
			command = parser.parseMessage("turn");
			fail();
		} catch (NoSuchElementException e) {
			assertEquals(null, command);
		}
		try {
			command = null;
			command = parser.parseMessage("turn:1");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, command);
		}
		// Testing Next Message
		try {
			command = null;
			command = parser.parseMessage("next:N=N S=C W=S E=S NS=0 NE=0 NW=0 WE=1 SE=0 SW=0");
			assertEquals(MessageParser.NEXT, command);
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
		try {
			command = null;
			command = parser.parseMessage("next");
			fail();
		} catch (NoSuchElementException e) {
			assertEquals(null, command);
		}
		try {
			command = null;
			command = parser.parseMessage("next:wrong format");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, command);
		}
		// Testing Rotated Format
		try {
			command = null;
			command = parser.parseMessage("rotated:N=N S=C W=S E=S NS=0 NE=0 NW=0 WE=1 SE=0 SW=0");
			assertEquals(MessageParser.ROTATED, command);
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
		try {
			command = null;
			command = parser.parseMessage("rotated");
			fail();
		} catch (NoSuchElementException e) {
			assertEquals(null, command);
		}
		try {
			command = null;
			command = parser.parseMessage("rotated:wrong format");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, command);
		}
		// Testing Update Message
		try {
			command = null;
			command = parser.parseMessage("update:N=N S=C W=S E=S NS=0 NE=0 NW=0 WE=1 SE=0 SW=0,1,0");
			assertEquals(MessageParser.UPDATE, command);
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
		try {
			command = null;
			command = parser.parseMessage("update");
			fail();
		} catch (NoSuchElementException e) {
			assertEquals(null, command);
		}
		try {
			command = null;
			command = parser.parseMessage("update:1,0,N=N S=C W=S E=S NS=0 NE=0 NW=0 WE=1 SE=0 SW=0");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, command);
		}
		// Testing Move Not Valid Message
		try {
			command = null;
			command = parser.parseMessage("move not valid");
			assertEquals(MessageParser.MOVE_NOT_VALID, command);
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
		// Testing Score Message
		try {
			command = null;
			command = parser.parseMessage("score:red=1,blue=2,green=3");
			assertEquals(MessageParser.SCORE, command);
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
		try {
			command = null;
			command = parser.parseMessage("score");
			fail();
		} catch (NoSuchElementException e) {
			assertEquals(null, command);
		}
		try {
			command = null;
			command = parser.parseMessage("score:red=red");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, command);
		}
		// Testing Lock Message
		try {
			command = null;
			command = parser.parseMessage("lock");
			assertEquals(MessageParser.LOCK, command);
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
		// Testing Leave Message
		try {
			command = null;
			command = parser.parseMessage("leave:red");
			assertEquals(MessageParser.LEAVE, command);
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
		try {
			command = null;
			command = parser.parseMessage("leave");
			fail();
		} catch (NoSuchElementException e) {
			assertEquals(null, command);
		}
		try {
			command = null;
			command = parser.parseMessage("leave:x");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, command);
		}
		// Testing End Message
		try {
			command = null;
			command = parser.parseMessage("end:red=1,blue=2,green=3");
			assertEquals(MessageParser.END, command);
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
		try {
			command = null;
			command = parser.parseMessage("end");
			fail();
		} catch (NoSuchElementException e) {
			assertEquals(null, command);
		}
		try {
			command = null;
			command = parser.parseMessage("end:x");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, command);
		}
		// Testing Unrecognized Command
		try {
			command = null;
			command = parser.parseMessage("not valid command");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, command);
		}
	}

}
