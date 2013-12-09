package it.polimi.dei.provafinale.carcassone.model;

/**
 * This enumeration represents the types of fields that can be placed in an area
 * of a tile.
 * 
 * @author Matteo Danelli, Andrea Canidio
 */
public enum FieldType {

	STREET,

	FIELD,

	TOWN;

	private static final char TOWNCODE = 'C';
	private static final char FIELDCODE = 'N';
	private static final char STREETCODE = 'S';

	/**
	 * This method returns the character code representing the current FieldType
	 * object.
	 * 
	 * @return the specified character code in {N, C, S}
	 */
	public char getFieldCode() {
		if (this == STREET) {
			return STREETCODE;
		} else if (this == FIELD) {
			return FIELDCODE;
		}
		return TOWNCODE;
	}

	/**
	 * This method recognize a specified character in input and return the
	 * associated enumeration value
	 * 
	 * @param fieldCode
	 *            a character representing the field code.
	 * @return the field type enumeration value associated to the code in input.
	 * @throws IllegalArgumentException
	 *             an exception is thrown every time a not specified code is
	 *             inserted.
	 */
	public static FieldType recognizeFieldCode(char fieldCode) throws IllegalArgumentException {
		if (fieldCode == FieldType.STREET.getFieldCode()) {
			return FieldType.STREET;
		}
		if (fieldCode == FieldType.TOWN.getFieldCode()) {
			return FieldType.TOWN;
		}
		if (fieldCode == FieldType.FIELD.getFieldCode()) {
			return FieldType.FIELD;
		}
		throw new IllegalArgumentException("Invalid Field Code");
	}

	/**
	 * This method returns the representation of a field in a textual
	 * representation. These because for STREET and TOWN the representation is
	 * the same as code but for a FIELD the representation is an empty space.
	 * 
	 * @return a string representing a specific fieldtype.
	 * @throws IllegalArgumentException
	 *             an exception is thrown when the value of the enumeration is
	 *             not recognized.
	 */
	public String getRepresentation() throws IllegalArgumentException {
		switch (this) {
		case STREET:
			return "S";
		case TOWN:
			return "C";
		case FIELD:
			return " ";
		}
		throw new IllegalArgumentException("Invalid FieldType");
	}
}
