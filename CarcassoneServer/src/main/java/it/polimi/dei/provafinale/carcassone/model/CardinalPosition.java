package it.polimi.dei.provafinale.carcassone.model;

/**
 * This enumeration Cardinal Position is created for modeling the possibles
 * representations of Cardinal Positions. It means that it can assume the values
 * NORTH, SOUTH, EAST and WEST.
 * 
 * @author Andrea Canidio, Matteo Danelli
 * @version 1.0
 */
public enum CardinalPosition {

	NORTH,

	SOUTH,

	WEST,

	EAST;

	/**
	 * This method is used to generate the code associated to a single Cardinal
	 * Position. This representation, specified in the specification document,
	 * is equals to the first letter of the enumeration name.
	 * 
	 * @return a single character (that it can be N, S, W, E).
	 */
	public char getPositionCode() {
		return this.name().charAt(0);
	}

	/**
	 * Method that recognize the Cardinal Position specified by the code that
	 * represents every single value of the enumeration.
	 * 
	 * @param positionCode
	 *            The code of the Cardinal Position.
	 * @return The enumeration value of the Cardinal Position.
	 * @throws IllegalArgumentException
	 *             if it is inserted a character not contemplated in the protocol.
	 */
	public static CardinalPosition recognizePositionCode(char positionCode) throws IllegalArgumentException {
		if (positionCode == CardinalPosition.NORTH.getPositionCode()) {
			return CardinalPosition.NORTH;
		} else if (positionCode == CardinalPosition.SOUTH.getPositionCode()) {
			return CardinalPosition.SOUTH;
		} else if (positionCode == CardinalPosition.WEST.getPositionCode()) {
			return CardinalPosition.WEST;
		} else if (positionCode == CardinalPosition.EAST.getPositionCode()) {
			return CardinalPosition.EAST;
		} else {
			throw new IllegalArgumentException("Invalid Position Code");
		}
	}

	/**
	 * This method returns a Cardinal Position that is the opposite value of the
	 * object. It means that if the value of the object is NORTH it returns
	 * SOUTH, if EAST it returns WEST and so on.
	 * 
	 * @return The value of a CardinalPosition that is the opposite of the
	 *         current object.
	 * @throws IllegalArgumentException
	 *             It is thrown if the current object is not initializes.
	 */
	public CardinalPosition opposite() throws IllegalArgumentException {
		switch (this) {
		case NORTH: {
			return SOUTH;
		}
		case SOUTH: {
			return NORTH;
		}
		case WEST: {
			return EAST;
		}
		case EAST: {
			return WEST;
		}
		default: {
			throw new IllegalArgumentException("Invalid Cardinal Position");
		}
		}
	}
}
