package it.polimi.dei.provafinale.carcassone.model;

import it.polimi.dei.provafinale.utils.Strings;

/**
 * This class represent a map displayed on the output.
 * @author Canidio Andrea
 *
 */
public class DisplayedMap extends AbstractMap {

	//constants
	private static final int CELLWIDTH = 6;
	private static final int TILEWIDTH = 5;
	private static final int TILEHEIGHT = 13;
	private static final String NOBORDER = " ";
	private static final String INITIALIZATION = "";
	private static final String VISIBLEBORDER = "#";
	private static final String INVISIBLEBORDER = ".";
	private static final String ANGLE = "+";
	private int minimXLimit;
	private int maximXLimit;
	private int minimYLimit;
	private int maximYLimit;

	/**
	 * Initialize the displayedMap.
	 */
	public DisplayedMap() {
		super();
		minimXLimit = 0;
		maximXLimit = 0;
		minimYLimit = 0;
		maximYLimit = 0;
	}

	/**
	 * Give a textual representation of the map, useful for the textualView.
	 * @return
	 */
	public String toTextualRepresentation() {
		StringBuffer mapRepresentation = new StringBuffer();
		updateMapLimits();
		for (int yIndex = maximYLimit; yIndex >= (minimYLimit); yIndex--) {
			String lineRepresentation[] = new String[CELLWIDTH];
			for (int i = 0; i < lineRepresentation.length; i++) {
				lineRepresentation[i] = INITIALIZATION;
			}
			for (int xIndex = minimXLimit; xIndex <= (maximXLimit+1); xIndex++) {
				Position thisOne = new Position(xIndex, yIndex);
				String[] cellRepresentation = generateCellRepresentation(thisOne);
				Strings.appendStringArray(lineRepresentation, cellRepresentation);
			}
			for (int i = 0; i < lineRepresentation.length; i++){
				mapRepresentation.append(lineRepresentation[i]);
				mapRepresentation.append("\n");
			}
		}
		for (int xIndex = minimXLimit; xIndex <= (maximXLimit+1); xIndex++) {
			Position thisOne = new Position(xIndex, minimYLimit-1);
			mapRepresentation.append(generateAngle(thisOne));
			String[] lastBorderRepresentation = generateBorder(thisOne, CardinalPosition.NORTH);
			mapRepresentation.append(lastBorderRepresentation[0]);
		}
		return mapRepresentation.toString();
	}

	//Generate only the representation of the tile without border.
	private String[] generateCellRepresentation(Position pos) {
		String[] cellRepresentation = new String[CELLWIDTH];
		for (int i = 0; i < CELLWIDTH; i++) {
			cellRepresentation[i] = INITIALIZATION;
		}
		cellRepresentation[0]+=generateAngle(pos);
		Strings.appendStringArray(cellRepresentation, generateBorder(pos, CardinalPosition.NORTH));
		Strings.appendStringArray(cellRepresentation, generateBorder(pos, CardinalPosition.WEST));
		String[] tileRepresentation;
		if (thereIsTileIn(pos)) {
			tileRepresentation = ((DisplayedTile)getTilesPlaced().get(pos)).toInternalRepresentation();
		}
		else if (isAnAvailablePosition(pos)) {
			tileRepresentation = generateAvailablePositionPattern(pos);
		}
		else {
			tileRepresentation = generateVoidPattern();
		}
		for (int i = 0; i < TILEWIDTH; i++) {
			cellRepresentation[i + 1] += tileRepresentation[i];
		}
		return cellRepresentation;
	}

	//Generate the angle of the tile, based on the tile near.
	private String generateAngle(Position pos){
		if(haveAngle(pos)) {
			return ANGLE;
		}
		else {
			return NOBORDER;
		}
	}

	//Generate the border of the tile, different from position to position.
	private String[] generateBorder(Position thisOne, CardinalPosition direction) throws IllegalArgumentException{
		String[] border = new String[CELLWIDTH];
		Position neighbour = thisOne.generateNeighbour(direction);
		String borderPattern = INITIALIZATION;
		for(int i = 0; i < border.length; i++) {
			border[i] = INITIALIZATION;
		}
		if(haveInvisibleBorder(thisOne, neighbour)) {
			borderPattern = INVISIBLEBORDER;
		}
		else if(haveVisibleBorder(thisOne, neighbour)) {
			borderPattern = VISIBLEBORDER;
		}
		else if(haveNoBorder(thisOne, neighbour)) {
			borderPattern = NOBORDER;
		}
		switch(direction){
		case NORTH:
		case SOUTH:				
			while (border[0].length() < TILEHEIGHT) {
				border[0] += borderPattern;
			}
			break;
		case EAST:
		case WEST:
			for(int i = 1;i < CELLWIDTH; i++) {
				border[i]+=borderPattern;
			}
			break;
		default:
			throw new IllegalArgumentException("Illegal Cardinal Position");
		}
		return border;
	}

	//Check if the tile has a visible border, or if the border must is blank.
	private boolean haveVisibleBorder(Position one, Position another){
		if(!one.isNear(another)) {
			return false;
		}
		if(thereIsTileIn(one)&&isAnAvailablePosition(another)) {
			return true;
		}
		if(thereIsTileIn(another)&&isAnAvailablePosition(one)) {
			return true;
		}
		return false;
	}

	private void updateMapLimits() {
		for(Position currentPosition: getAvailableCells()) {
			if (currentPosition.getCoordinateX() < minimXLimit) {
				minimXLimit = currentPosition.getCoordinateX();
			}
			if (currentPosition.getCoordinateX() > maximXLimit) {
				maximXLimit = currentPosition.getCoordinateX();
			}
			if (currentPosition.getCoordinateY() < minimYLimit) {
				minimYLimit = currentPosition.getCoordinateY();
			}
			if (currentPosition.getCoordinateY() > maximYLimit) {
				maximYLimit = currentPosition.getCoordinateY();
			}
		}
	}

	private String[] generateVoidPattern() {
		String[] repr = new String[TILEWIDTH];
		for (int i = 0; i < repr.length; i++) {
			repr[i] = INITIALIZATION;
			while (repr[i].length() < TILEHEIGHT) {
				repr[i] += NOBORDER;
			}
		}
		return repr;
	}

	private String[] generateAvailablePositionPattern(Position pos) {
		String[] repr = new String[TILEWIDTH];
		for (int i = 0; i < repr.length; i++) {
			repr[i] = INITIALIZATION;
			while (repr[i].length() < TILEHEIGHT) {
				if (repr[i].length() == 1 && i == 2) {
					repr[i] += "(" + pos.toString() + ")";
				}
				else {
					repr[i] += NOBORDER;
				}
			}
		}
		return repr;
	}

	private boolean haveInvisibleBorder(Position one, Position another){
		if(!one.isNear(another)) {
			return false;
		}
		if(thereIsTileIn(one)&&thereIsTileIn(another)) {
			return true;
		}
		if(isAnAvailablePosition(one)&&!isInMap(another)) {
			return true;
		}
		if(isAnAvailablePosition(another)&&!isInMap(one)) {
			return true;
		}
		if(isAnAvailablePosition(one)&&isAnAvailablePosition(another)) {
			return true;
		}
		return false;
	}

	private boolean haveNoBorder(Position one, Position another){
		if(haveInvisibleBorder(one, another) || haveVisibleBorder(one, another)) {
			return false;
		}
		return true;
	}

	private boolean haveAngle(Position pos){
		if(isInMap(pos)) {
			return true;
		}
		if(isInMap(pos.generateNeighbour(CardinalPosition.NORTH))) {
			return true;
		}
		if(isInMap(pos.generateNeighbour(CardinalPosition.WEST))) {
			return true;
		}
		Position angleNeighbour = pos.generateNeighbour(CardinalPosition.NORTH).
				generateNeighbour(CardinalPosition.WEST);
		if(isInMap(angleNeighbour)) {
			return true;
		}
		return false;
	}

	private boolean isInMap(Position key) {
		return thereIsTileIn(key)||isAnAvailablePosition(key);
	}

	/**
	 * Getter of a tile from a given position.
	 * @param positionToCheck	
	 * @return	tile found
	 */
	@Override
	public DisplayedTile getTileFromPosition(Position positionToCheck) {
		return (DisplayedTile) super.getTileFromPosition(positionToCheck);
	}
}
