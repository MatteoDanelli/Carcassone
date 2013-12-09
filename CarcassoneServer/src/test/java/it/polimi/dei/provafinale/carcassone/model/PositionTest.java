package it.polimi.dei.provafinale.carcassone.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This test class tests the methods of the Position class.
 * 
 * @author Andrea Canidio, Matteo Danelli
 * @version 2.0
 */
public class PositionTest {

	private Position pos01;
	private Position pos01Equal;
	private Position pos00;

	/**
	 * This method creates the three positions needed in our tests.
	 */
	@Before
	public void setUp() {
		pos01 = new Position("0,1");
		pos01Equal = new Position("0,1");
		pos00 = new Position("0,0");
	}

	/**
	 * Test method for the Position constructor. It tests if the creation of a
	 * Position object with same parameters is not random and if the constructor
	 * create the expected objects.
	 */
	@Test
	public void testPosition() {
		// First Position
		assertNotNull(pos01.getCoordinateX());
		assertEquals(0, pos01.getCoordinateX());
		assertNotNull(pos01.getCoordinateY());
		assertEquals(1, pos01.getCoordinateY());
		// Second Position
		assertNotNull(pos00.getCoordinateX());
		assertEquals(0, pos00.getCoordinateX());
		assertNotNull(pos00.getCoordinateY());
		assertEquals(0, pos00.getCoordinateY());
	}

	/**
	 * Test method for distance method. It tests if, given some positions, the
	 * distances are the ones expected.
	 */
	@Test
	public void testDistance() {
		assertNotNull("Error calculating distance", pos01.distance(pos01));
		assertEquals("Error calculating distance", pos01.distance(pos01), 0);
		assertNotNull("Error calculating distance", pos01.distance(pos00));
		assertEquals("Error calculating distance", pos01.distance(pos00), 1);
		assertEquals("Error calculating distance", pos00.distance(pos01), 1);
		assertEquals("Error calculating distance", pos01.distance(pos00),
				pos00.distance(pos01));
	}

	@Test
	public void TestEquals() {
		assertTrue("Error of equals override", pos01.equals(pos01Equal));
	}

	@Test
	public void testToString() {
		assertNotNull("Error on toString override", pos00.toString());
		assertEquals("Error on toString override", "0,0", pos00.toString());
		assertNotNull("Error on toString override", pos01.toString());
		assertEquals("Error on toString override", "0,1", pos01.toString());
	}

	/**
	 * Test implicitly also the GenerateNeighbours(CardinalPosition position)
	 */
	@Test
	public void testGenerateNeighbours() {
		Position[] testArray = pos00.generateNeighbours();
		CardinalPosition[] destinations = CardinalPosition.values();
		for (int i = 0; i < testArray.length; i++) {
			assertEquals("Error generating neighbours",
					pos00.generateNeighbour(destinations[i]), testArray[i]);
		}
	}

}
