package it.polimi.dei.provafinale.carcassone.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Andrea Canidio
 * 
 */
public class ConnectionTypeTest {

	private ConnectionType notPres;
	private ConnectionType pres;

	/**
	 * In the SetUp it has been instantiated all the possibles values of
	 * ConnectionType.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		notPres = ConnectionType.NOTPRESENT;
		pres = ConnectionType.PRESENT;
	}

	/**
	 * Test method for
	 * {@link it.polimi.dei.provafinale.carcassone.model.ConnectionType#getConnectionCode()}
	 * . 
	 * This method tests that all the values returned of the previously
	 * instantiated values are the expected for all the enumerations.
	 */
	@Test
	public void testGetConnectionCode() {
		assertEquals("Error getting correct ConnectionCode", '0', notPres.getConnectionCode());
		assertEquals("Error getting correct ConnectionCode", '1', pres.getConnectionCode());
	}

	/**
	 * Test method for
	 * {@link it.polimi.dei.provafinale.carcassone.model.ConnectionType#recognizeConnectionCode(char)}
	 * . 
	 * This test is created for checking if, for all the possibles codes
	 * defined, it is returned the correct ConnectionType
	 */
	@Test
	public void testRecognizeConnectionCode() {
		assertEquals("Error recognizing connectionCode", ConnectionType.NOTPRESENT, ConnectionType.recognizeConnectionCode('0'));
		assertEquals("Error recognizing connectionCode", ConnectionType.PRESENT, ConnectionType.recognizeConnectionCode('1'));
	}
}
