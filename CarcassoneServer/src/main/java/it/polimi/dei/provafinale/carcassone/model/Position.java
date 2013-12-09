package it.polimi.dei.provafinale.carcassone.model;

import java.util.StringTokenizer;

/**
 * Position class represents the position of an element (for example a Tile)
 * that is calculated from the first Tile placed in a map as in a Cartesian
 * Diagram. So the first element at the right is (1,0).
 * 
 * @author Andrea Canidio
 * @version 2.0
 */
public class Position {

	private static final int PRIMENUMBER2 = 31;
	private static final int PRIMENUMBER1 = 17;
	private static final int NEIGHBOURSNUMBER = 4;
	private static final int EXPECTEDVALUES = 2;
	private final int coordinateX;
	private final int coordinateY;

	/**
	 * This method returns the distance on the x axis stored in the Position
	 * variable.
	 * 
	 * @return Returns the x distance.
	 */
	public int getCoordinateX() {
		return coordinateX;
	}

	/**
	 * This method returns the distance on the y axis stored in the Position
	 * variable.
	 * 
	 * @return Returns the y distance.
	 */
	public int getCoordinateY() {
		return coordinateY;
	}

	/**
	 * This Constructor allows you to initialize the new Position object to the
	 * two integers specified as parameters.
	 * 
	 * @param x
	 *            This is the distance on the x axis from the first Tile and can
	 *            be both positive (at right) or negative (at left).
	 * @param coordinateY
	 *            This is the distance on the y axis from the first Tile and can
	 *            be both positive (at top) or negative (at bottom).
	 */
	public Position(String posPattern) throws IllegalArgumentException {
		// Separate the parameter in coordinateX and coordinateY
		StringTokenizer tokenizer = new StringTokenizer(posPattern, ",");
		if (tokenizer.countTokens() != EXPECTEDVALUES) {
			throw new IllegalArgumentException("Invalid Position Pattern: " + posPattern);
		}
		try {
			coordinateX = Integer.parseInt(tokenizer.nextToken());
			coordinateY = Integer.parseInt(tokenizer.nextToken());
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Invalid Position Pattern: " + posPattern, e);
		}
	}

	/**
	 * This constructor create the position with the coordinateX and coordinateY
	 * passed like int
	 * 
	 * @param x
	 *            coordinateX
	 * @param y
	 *            coordinateY
	 */
	public Position(int x, int y) {
		this.coordinateX = x;
		this.coordinateY = y;
	}

	/**
	 * This method calculate the distance from another Tile that is the
	 * Manhattan Distance, so the distance on the x axis plus the distance on
	 * the y axis.
	 * 
	 * @param position
	 *            This is the specified position from which the distance is
	 *            calculated.
	 * @return It returns an integer that is the distance.
	 */
	public int distance(Position position) {
		// Manhattan distance
		int xDistance = Math.abs(coordinateX - position.coordinateX);
		int yDistance = Math.abs(coordinateY - position.coordinateY);
		return xDistance + yDistance;
	}

	/**
	 * This method compare two object comparing if they are both representing
	 * the same position.
	 * 
	 * @param obj
	 *            the object wanted to verify equality
	 * @return a boolean true if the objects are equals else false
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Position)) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		Position pos = (Position) obj;
		if (coordinateX != pos.coordinateX) {
			return false;
		}
		if (coordinateY != pos.coordinateY) {
			return false;
		}
		return true;
	}

	/**
	 * this method returns the string representation of a position in format x,y
	 * 
	 * @return the representation.
	 */
	@Override
	public String toString() {
		return coordinateX + "," + coordinateY;
	}

	/**
	 * @return array of position representing the neighbours of this one.
	 */
	public Position[] generateNeighbours() {
		Position neighbours[] = new Position[NEIGHBOURSNUMBER];
		CardinalPosition[] allValues = CardinalPosition.values();
		for (int i = 0; i < allValues.length; i++) {
			neighbours[i] = this.generateNeighbour(allValues[i]);
		}
		return neighbours;
	}

	/**
	 * Generate only one position in the cardinalPosition passed as parameter
	 * 
	 * @param direction
	 * @return position calculated
	 */
	public Position generateNeighbour(CardinalPosition direction) throws IllegalArgumentException{
		Position neighbourPosition = null;
		switch (direction) {
			case NORTH:{
				neighbourPosition = new Position(coordinateX, coordinateY + 1);
			}
			break;
			case SOUTH:{
				neighbourPosition = new Position(coordinateX, coordinateY - 1);
			}
			break;
			case WEST:{
				neighbourPosition = new Position(coordinateX - 1, coordinateY);
			}
			break;
			case EAST:{
				neighbourPosition = new Position(coordinateX + 1, coordinateY);	
			}
		}
		return neighbourPosition;
	}

	/**
	 * Two objects are near if and only if its distance equals 1.
	 * 
	 * @param otherPos
	 * @return
	 */
	public boolean isNear(Position otherPos) {
		if (this.distance(otherPos) == 1) {
			return true;
		}
		return false;
	}

	/**
	 * Override of hashcode used in map. We have an error if we don't implement
	 * this, because we have override also equals.
	 */
	@Override
	public int hashCode() {
		return PRIMENUMBER1 * coordinateX + PRIMENUMBER2 * coordinateY;
	}

	/**
	 * This method returns the relative position (CardinalPosition) of this
	 * object respect the one specified as parameter. This parameter position
	 * have to be placed near the other one.
	 * 
	 * @param comparablePosition
	 *            the position with which i have to compare
	 * @return the relative position represented by a CardinalPosition
	 * @throws IllegalArgumentException
	 *             an exception is thrown every time the other position is not
	 *             near our object.
	 */
	public CardinalPosition hasRelativePositionRespect(Position comparablePosition)
			throws IllegalArgumentException {
		if (!this.isNear(comparablePosition)) {
			throw new IllegalArgumentException("These positions are not near");
		}
		if (this.coordinateX > comparablePosition.coordinateX) {
			return CardinalPosition.EAST;
		}
		if (this.coordinateX < comparablePosition.coordinateX) {
			return CardinalPosition.WEST;
		}
		if (this.coordinateY > comparablePosition.coordinateY) {
			return CardinalPosition.NORTH;
		}
		return CardinalPosition.SOUTH;
	}
}