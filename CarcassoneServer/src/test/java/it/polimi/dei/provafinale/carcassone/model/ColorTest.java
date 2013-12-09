package it.polimi.dei.provafinale.carcassone.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This test controls that all the public methods of Color class work.
 * 
 * @author Matteo Danelli
 * @version 1.0
 */
public class ColorTest {

	private Color red;
	private Color blue;
	private Color black;
	private Color yellow;
	private Color green;

	/**
	 * The SetUp() method creates the five variables of type Color, one for each
	 * color in the Enum class.
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		red = Color.RED;
		blue = Color.BLUE;
		black = Color.BLACK;
		yellow = Color.YELLOW;
		green = Color.GREEN;
	}

	/**
	 * Test method for
	 * {@link it.polimi.dei.provafinale.carcassone.model.Color#getColorName()}.
	 * 
	 * This test controls that, for every Color, the name returned is the one
	 * expected.
	 */
	@Test
	public void testGetColorName() {
		assertEquals("Error getting colorName", "red", red.getColorName());
		assertEquals("Error getting colorName", "black", black.getColorName());
		assertEquals("Error getting colorName", "yellow", yellow.getColorName());
		assertEquals("Error getting colorName", "green", green.getColorName());
		assertEquals("Error getting colorName", "blue", blue.getColorName());
	}

	/**
	 * Test method for
	 * {@link it.polimi.dei.provafinale.carcassone.model.Color#getColorCode()}.
	 * 
	 * This test controls that, for every Color, the code returned is the one
	 * expected.
	 */
	@Test
	public void testGetColorCode() {
		assertEquals("Error getting ColorCode", 'K', black.getColorCode());
		assertEquals("Error getting ColorCode", 'B', blue.getColorCode());
		assertEquals("Error getting ColorCode", 'Y', yellow.getColorCode());
		assertEquals("Error getting ColorCode", 'R', red.getColorCode());
		assertEquals("Error getting ColorCode", 'G', green.getColorCode());

	}

	/**
	 * Test method for
	 * {@link it.polimi.dei.provafinale.carcassone.model.Color#recognizeColorCode(char)}.
	 * This test controls that, for every Color, the code returned is the one
	 * expected.
	 */
	@Test
	public void testRecognizeColorCode() {
		char redCode = Color.RED.getColorCode();
		char blackCode = Color.BLACK.getColorCode();
		char blueCode = Color.BLUE.getColorCode();
		char greenCode = Color.GREEN.getColorCode();
		char yellowCode = Color.YELLOW.getColorCode();
		
		assertEquals("Error getting colorCode", Color.RED, Color.recognizeColorCode(redCode));
		assertEquals("Error getting colorCode", Color.BLACK, Color.recognizeColorCode(blackCode));
		assertEquals("Error getting colorCode", Color.BLUE, Color.recognizeColorCode(blueCode));
		assertEquals("Error getting colorCode", Color.GREEN, Color.recognizeColorCode(greenCode));
		assertEquals("Error getting colorCode", Color.YELLOW, Color.recognizeColorCode(yellowCode));
	}

	/**
	 * Test method for
	 * {@link it.polimi.dei.provafinale.carcassone.model.Color#recognizeColorName(String)}.
	 * This test controls that, for every Color, the name returned is the one
	 * expected.
	 */
	@Test
	public void testRecognizeColorName() {
		String redCode = Color.RED.getColorName();
		String blackCode = Color.BLACK.getColorName();
		String blueCode = Color.BLUE.getColorName();
		String greenCode = Color.GREEN.getColorName();
		String yellowCode = Color.YELLOW.getColorName();
		
		assertEquals("Error getting colorName", Color.RED, Color.recognizeColorName(redCode));
		assertEquals("Error getting colorName", Color.BLACK, Color.recognizeColorName(blackCode));
		assertEquals("Error getting colorName", Color.BLUE, Color.recognizeColorName(blueCode));
		assertEquals("Error getting colorName", Color.GREEN, Color.recognizeColorName(greenCode));
		assertEquals("Error getting colorName", Color.YELLOW, Color.recognizeColorName(yellowCode));
	}
	
	
	/**
	 * Test method for
	 * {@link it.polimi.dei.provafinale.carcassone.model.Color#next()}.
	 * This test controls that, for every number, the Color returned is the one
	 * expected.
	 */
	@Test
	public void testNext() {
		Color red = Color.RED;
		Color black = Color.BLACK;
		Color blue = Color.BLUE;
		Color green = Color.GREEN;
		Color yellow = Color.YELLOW;
		
		assertEquals("Error getting next color", blue, red.next());
		assertEquals("Error getting next color", green, blue.next());
		assertEquals("Error getting next color", yellow, green.next());
		assertEquals("Error getting next color", black, yellow.next());
	}
}
