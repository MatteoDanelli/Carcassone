package it.polimi.dei.provafinale.carcassone.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This test allows to control that all the functionalities of FieldType are
 * correctly implemented.
 * 
 * @author Andrea Canidio
 * 
 */
public class FieldTypeTest {

	private FieldType street;
	private FieldType town;
	private FieldType field;

	/**
	 * In the SetUp it has been instantiated all the types of value of the
	 * enumeration in different variables.
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		street = FieldType.STREET;
		town = FieldType.TOWN;
		field = FieldType.FIELD;
	}

	/**
	 * Test method for
	 * {@link it.polimi.dei.provafinale.carcassone.model.FieldType#getFieldCode()}
	 * . 
	 * This method tests that every enumeration value returns the correct
	 * code.
	 */
	@Test
	public void testGetFieldCode() {
		assertEquals(FieldType.STREET.getFieldCode(), street.getFieldCode());
		assertEquals(FieldType.TOWN.getFieldCode(), town.getFieldCode());
		assertEquals(FieldType.FIELD.getFieldCode(), field.getFieldCode());
	}

	/**
	 * Test method for
	 * {@link it.polimi.dei.provafinale.carcassone.model.FieldType#recognizeFieldCode(char)}
	 * . 
	 * This method tests that for each character code specified the correct
	 * FieldType is returned.
	 */
	@Test
	public void testRecognizeFieldCode() {
		assertEquals(FieldType.STREET, FieldType.recognizeFieldCode(FieldType.STREET.getFieldCode()));
		assertEquals(FieldType.TOWN, FieldType.recognizeFieldCode(FieldType.TOWN.getFieldCode()));
		assertEquals(FieldType.FIELD, FieldType.recognizeFieldCode(FieldType.FIELD.getFieldCode()));
	}
	
	/**
	 * Test method for
	 * {@link it.polimi.dei.provafinale.carcassone.model.FieldType#getRepresentation()}
	 * . 
	 * It tests for all values if the expected representation of all FieldTypes is
	 * returned.
	 */
	@Test
	public void testGetRepresentation() {
		assertEquals(FieldType.STREET.getRepresentation(), street.getRepresentation());
		assertEquals(FieldType.TOWN.getRepresentation(), town.getRepresentation());
		assertEquals(FieldType.FIELD.getRepresentation(), field.getRepresentation());
	}
}
