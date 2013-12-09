/**
 * 
 */
package it.polimi.dei.provafinale.carcassone.model;

import static org.junit.Assert.*;

import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.List;

import javax.management.openmbean.KeyAlreadyExistsException;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Matteo Danelli
 *
 */
public class TrackTest {

	private Track testedTrack1;
	private Track testedTrack2;
	
	private TileArea NC;
	private Position pos00;
	
	private TileArea SCG;
	private Position pos10;
	
	private TileArea NCbis;
	private Position pos01;
	
	private TileArea SSB;
	private TileArea WCG;
	private Position pos0_1;
	
	private List<Color> testList;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
			testedTrack1 = new Track(FieldType.TOWN);
			testedTrack2 = new Track(FieldType.STREET);
			NC = new TileArea("N=C");
			pos00 = new Position(0, 0);
			SCG = new TileArea("S=C+G");
			pos10 = new Position(1, 0);
			NCbis = new TileArea("N=C");
			pos01 = new Position(0, 1);
			SSB = new TileArea("S=S+B");
			WCG = new TileArea("W=C+G");
			pos0_1 = new Position(0,-1);

		}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.Track#Track(it.polimi.dei.provafinale.carcassone.model.FieldType)}.
	 */
	@Test
	public void testTrack() {
		assertEquals("Error testing Track constructor", FieldType.TOWN, testedTrack1.getType());
		assertEquals("Error testing Track constructor", FieldType.STREET, testedTrack2.getType());
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.Track#add(it.polimi.dei.provafinale.carcassone.model.Position, it.polimi.dei.provafinale.carcassone.model.TileArea)}.
	 */
	@Test
	public void testAdd() {
		boolean exceptionLauched = false;
		
		try{
			testedTrack1.add(pos00, NC);
		}
		catch(KeyAlreadyExistsException e){
			exceptionLauched = true;
		}
		assertFalse("Error on adding Position e TileArea in a track", exceptionLauched);
		
		exceptionLauched = false;
		try{
			testedTrack1.add(pos00, NC);
		}
		catch(KeyAlreadyExistsException e){
			exceptionLauched = true;
		}
		assertTrue("Error on adding Position e TileArea in a track", exceptionLauched);
		
		exceptionLauched = false;
		try{
			testedTrack1.add(pos10, SCG);
		}
		catch(KeyAlreadyExistsException e){
			exceptionLauched = true;
		}
		exceptionLauched = false;
		
		try{
			testedTrack1.add(pos01, NCbis);
		}
		catch(KeyAlreadyExistsException e){
			exceptionLauched = true;
		}
		assertFalse("Error on adding Position e TileArea in a track", exceptionLauched);
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.Track#countFlags(it.polimi.dei.provafinale.carcassone.model.Color)}.
	 */
	@Test
	public void testCountFlags() {
		testedTrack1.add(pos00, NC);
		assertEquals("Error counting flag in track", 0, testedTrack1.countFlags(Color.BLUE));
		assertEquals("Error counting flag in track", 0, testedTrack1.countFlags(Color.GREEN));
		testedTrack1.add(pos01, SCG);
		assertEquals("Error counting flag in track", 0, testedTrack1.countFlags(Color.BLUE));
		assertEquals("Error counting flag in track", 1, testedTrack1.countFlags(Color.GREEN));
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.Track#countTiles()}.
	 */
	@Test
	public void testCountTiles() {
		assertEquals("Error counting tiles in track", 0, testedTrack1.countTiles());
		testedTrack1.add(pos00, NC);
		assertEquals("Error counting tiles in track", 1, testedTrack1.countTiles());
		testedTrack1.add(pos01, NCbis);
		assertEquals("Error counting tiles in track", 2, testedTrack1.countTiles());
		testedTrack1.add(pos10, SCG);
		assertEquals("Error counting tiles in track", 3, testedTrack1.countTiles());
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.Track#hasFlag()}.
	 */
	@Test
	public void testHasFlag() {
		testedTrack1.add(pos00, NC);
		assertFalse("Error checking flag in track", testedTrack1.hasFlag());
		testedTrack1.add(pos01, NCbis);
		assertFalse("Error checking flag in track", testedTrack1.hasFlag());
		testedTrack1.add(pos10, SCG);
		assertTrue("Error checking flag in track", testedTrack1.hasFlag());
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.Track#hasFlag(it.polimi.dei.provafinale.carcassone.model.Position)}.
	 */
	@Test
	public void testHasFlagPosition() {
		boolean exceptionLauched = false;

		testedTrack1.add(pos00, NC);
		try {
			assertFalse("Error checking flag in one position of track", testedTrack1.hasFlag(pos00));
		} catch (InvalidKeyException e) {
			exceptionLauched = false;
		}
		assertFalse("Error checking flag in one position of track", exceptionLauched);
		
		testedTrack1.add(pos01, NCbis);
		try {
			assertFalse("Error checking flag in one position of track", testedTrack1.hasFlag(pos00));
		} catch (InvalidKeyException e) {
			exceptionLauched = false;
		}
		try {
			assertFalse("Error checking flag in one position of track", testedTrack1.hasFlag(pos01));
		} catch (InvalidKeyException e) {
			exceptionLauched = false;
		}
		assertFalse("Error checking flag in one position of track", exceptionLauched);

		testedTrack1.add(pos10, SCG);
		try {
			assertFalse("Error checking flag in one position of track", testedTrack1.hasFlag(pos00));
		} catch (InvalidKeyException e1) {
			exceptionLauched = false;
		}
		try {
			assertTrue("Error checking flag in one position of track", testedTrack1.hasFlag(pos10));
		} catch (InvalidKeyException e) {
		exceptionLauched = false;
		}
		assertFalse("Error checking flag in one position of track", exceptionLauched);
}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.Track#isClosed()}.
	 */
	@Test
	public void testIsClosed() {
		testedTrack1.add(pos00, NC);
		testedTrack1.add(pos01, SCG);
		assertTrue("Error checking a close track", testedTrack1.isClosed());
		testedTrack2.add(pos00, NC);
		testedTrack2.add(pos01, NCbis);
		assertFalse("Error checking a close track", testedTrack2.isClosed());
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.Track#is(it.polimi.dei.provafinale.carcassone.model.FieldType)}.
	 */
	@Test
	public void testIs() {
		assertTrue("Error check if it's a specific track", testedTrack1.is(FieldType.TOWN));
		assertTrue("Error check if it's a specific track", testedTrack1.is(FieldType.TOWN));
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.Track#getMaximumFlagPlayers()}.
	 */
	@Test
	public void testGetMaximumFlagPlayers() {
		testedTrack1.add(pos00, SCG);
		testedTrack1.add(pos01, SSB);
		testedTrack1.add(pos10, WCG);
		testList = new ArrayList <Color>();
		testList.add(Color.GREEN);
		assertEquals("Error getting players with max flag", testList, testedTrack1.getMaximumFlagPlayers());
		assertEquals("Error getting players with max flag", 1, testedTrack1.getMaximumFlagPlayers().size());
		
		testedTrack1.add(pos0_1, SSB);
		//I cannot test the order because i use equals of List and i don't know how it insert element (head/queue)
		assertEquals("Error getting players with max flag", 2, testedTrack1.getMaximumFlagPlayers().size());
	}

	/**
	 * Test method for {@link it.polimi.dei.provafinale.carcassone.model.Track#getAllPositions()}.
	 */
	@Test
	public void testGetAllPositions() {
		testedTrack1.add(pos00, NC);
		testedTrack1.add(pos01, NCbis);
		assertEquals("Error getting all position of track", 2, testedTrack1.getAllPositions().size());
		assertTrue("Error getting all position of track", testedTrack1.getAllPositions().contains(pos00));
		assertTrue("Error getting all position of track", testedTrack1.getAllPositions().contains(pos01));
		assertFalse("Error getting all position of track", testedTrack1.getAllPositions().contains(pos10));
		assertFalse("Error getting all position of track", testedTrack1.getAllPositions().isEmpty());
		
		testedTrack2.add(pos10, SCG);
		assertEquals("Error getting all position of track", 1, testedTrack2.getAllPositions().size());
		assertTrue("Error getting all position of track", testedTrack2.getAllPositions().contains(pos10));
	}

}
