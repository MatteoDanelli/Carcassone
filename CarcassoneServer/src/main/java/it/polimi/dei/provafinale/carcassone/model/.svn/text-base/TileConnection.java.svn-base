package it.polimi.dei.provafinale.carcassone.model;

/**
 * @author   Andrea Canidio
 * @uml.dependency   supplier="it.polimi.dei.provafinale.carcassone.model.ConnectionType"
 */
public class TileConnection {

	private static final int PRIMENUMBER3 = 47;

	private static final int PRIMENUMBER2 = 31;

	private static final int PRIMENUMBER1 = 17;

	private static final int PATTERNLENGTH = 4;

	private static final int FROMPOSITIONINDEX = 0;

	private static final int TOPOSITIONINDEX = 1;

	private static final int PRESENCEINDEX = 3;

	private final CardinalPosition fromPosition;

	private final CardinalPosition toPosition;

	private final ConnectionType type;

	/**
	 * Constructor of the class.
	 * Create a tileConnection based on the connPattern
	 * @param connPattern. It must be like this: "NS=0"
	 * @throws IllegalArgumentException if connPattern is invalid
	 */
	public TileConnection(String connPattern) throws IllegalArgumentException {
		if (connPattern.length() != PATTERNLENGTH) {
			throw new IllegalArgumentException("Invalid Connection Pattern: "
					+ connPattern);
		}
		try {
			fromPosition = CardinalPosition.recognizePositionCode(connPattern
					.charAt(FROMPOSITIONINDEX));
			toPosition = CardinalPosition.recognizePositionCode(connPattern
					.charAt(TOPOSITIONINDEX));
			type = ConnectionType.recognizeConnectionCode(connPattern
					.charAt(PRESENCEINDEX));
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Invalid Connection Pattern: "
					+ connPattern, e);
		}
	}
	
	public CardinalPosition getFromPosition() {
		return fromPosition;
	}

	public CardinalPosition getToPosition() {
		return toPosition;
	}

	public ConnectionType getConnectionType() {
		return type;
	}

	/**
	 * Check if the connection is exactly the connection between first and second.
	 * @param first
	 * @param second
	 * @return	true if it's chosenConnection, else false.
	 */
	public boolean isChosenConnection(CardinalPosition first, CardinalPosition second) {
		if (this.fromPosition == first && this.toPosition == second) {
			return true;
		}
		if (this.fromPosition == second && this.toPosition == first) {
			return true;
		}
		return false;
	}

	/**
	 * Check if it's present the connection.
	 * @return	true if it's present, else false.
	 */
	public boolean isPresent() {
		if (this.type == ConnectionType.PRESENT) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		String connRepresentation = fromPosition.getPositionCode() + ""
				+ toPosition.getPositionCode() + "=" + type.getConnectionCode();
		return connRepresentation;
	}

	/**
	 * Two tileConnection are equals if and only if they have same start and end position,
	 * and they is the same type.
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof TileConnection)) {
			return false;
		}
		TileConnection other = (TileConnection) obj;
		if (this.fromPosition != other.fromPosition) {
			return false;
		}
		if (this.toPosition != other.toPosition) {
			return false;
		}
		if (this.type != other.type) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		return PRIMENUMBER1 * fromPosition.hashCode() + PRIMENUMBER2 * toPosition.hashCode() + PRIMENUMBER3 * type.hashCode();
	}
}
