package it.polimi.dei.provafinale.carcassone.model;

/**
 * This enumeration is created to manage the possibility in a TileConnection of
 * having only two values of presence and no presence.
 * 
 * @author Matteo Danelli, Andrea Canidio
 */
public enum ConnectionType {

	PRESENT,

	NOTPRESENT;

	/**
	 * This is the character code representing the no presence of a connection,
	 * as specified in the protocol.
	 */
	private static final char NOTPRESENTCODE = '0';

	/**
	 * This is the character code representing the presence of a connection, as
	 * specified in the protocol.
	 */
	private static final char PRESENTCODE = '1';

	/**
	 * This getter method returns a character depending on the enumeration
	 * instantiate.
	 * 
	 * @return The specific character code associated to the enumeration value.
	 *         ('1' or '0').
	 */
	public char getConnectionCode() {
		if (this == PRESENT) {
			return PRESENTCODE;
		}
		return NOTPRESENTCODE;
	}

	/**
	 * This static method is created for recognizing a specified character as
	 * input and returning the associated value of the enumeration.
	 * 
	 * @param connCode
	 *            is the character code in input.
	 * @return the value of the enumeration associated
	 * @throws IllegalArgumentException
	 *             the exception is thrown if and only if it is put as parameter
	 *             a not valid code.
	 */
	public static ConnectionType recognizeConnectionCode(char connCode) throws IllegalArgumentException {
		if (connCode == PRESENT.getConnectionCode()) {
			return PRESENT;
		} 
		if (connCode == NOTPRESENT.getConnectionCode()) {
			return NOTPRESENT;
		}
		throw new IllegalArgumentException("Invalid Connection Code");
	}
}
