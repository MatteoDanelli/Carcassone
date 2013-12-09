package it.polimi.dei.provafinale.carcassone.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This test is created for testing the functionalities of the CardinalPosition
 * class.
 * 
 * @author Andrea Canidio
 * @version 1.1
 */
public class CardinalPositionTest {

	private CardinalPosition north;
	private CardinalPosition south;
	private CardinalPosition east;
	private CardinalPosition west;

	/**
	 * The setUp method instantiate the four possibles values of the Cardinal
	 * Position Enumeration and then the tests will prove that the methods will
	 * returns the corrects values.
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		north = CardinalPosition.NORTH;
		south = CardinalPosition.SOUTH;
		east = CardinalPosition.EAST;
		west = CardinalPosition.WEST;
	}

	/**
	 * Test method for
	 * {@link it.polimi.dei.provafinale.carcassone.model.CardinalPosition#getPositionCode()}
	 * . 
	 * 
	 * It controls that the values returned are the ones expected. In other
	 * words that the returned value is N for NORTH and so on.
	 */
	@Test
	public void testGetPositionCode() {
		assertEquals("Error getting correct positionCode", 'N', north.getPositionCode());
		assertEquals("Error getting correct positionCode", 'S', south.getPositionCode());
		assertEquals("Error getting correct positionCode", 'E', east.getPositionCode());
		assertEquals("Error getting correct positionCode", 'W', west.getPositionCode());
	}

	/**
	 * Test method for
	 * {@link it.polimi.dei.provafinale.carcassone.model.CardinalPosition#recognizePositionCode(char)}
	 * . 
	 * 
	 * It controls that, for every code inserted the method returns the
	 * expected CardinalPosition.
	 */
	@Test
	public void testRecognizePositionCode() {
		char northChar = CardinalPosition.NORTH.getPositionCode();
		char southChar = CardinalPosition.SOUTH.getPositionCode();
		char eastChar = CardinalPosition.EAST.getPositionCode();
		char westChar = CardinalPosition.WEST.getPositionCode();

		assertEquals("Error recognizing CardinalPosition", CardinalPosition.NORTH, CardinalPosition.recognizePositionCode(northChar));
		assertEquals("Error recognizing CardinalPosition", CardinalPosition.SOUTH, CardinalPosition.recognizePositionCode(southChar));
		assertEquals("Error recognizing CardinalPosition", CardinalPosition.EAST, CardinalPosition.recognizePositionCode(eastChar));
		assertEquals("Error recognizing CardinalPosition", CardinalPosition.WEST, CardinalPosition.recognizePositionCode(westChar));
	}

	/**
	 * Test method for
	 * {@link it.polimi.dei.provafinale.carcassone.model.CardinalPosition#opposite()}
	 * . 
	 * 
	 * It controls that, for every CardinalPosition inserted, it returns
	 * the opposite CardinalPosition.
	 */
	@Test
	public void testOpposite() {
		assertEquals("Error testing opposite CardinalPosition", CardinalPosition.SOUTH, north.opposite());
		assertEquals("Error testing opposite CardinalPosition", CardinalPosition.NORTH, south.opposite());
		assertEquals("Error testing opposite CardinalPosition", CardinalPosition.EAST, west.opposite());
		assertEquals("Error testing opposite CardinalPosition", CardinalPosition.WEST, east.opposite());
	}

}
