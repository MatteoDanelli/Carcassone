package it.polimi.dei.provafinale.carcassone.model;

/**
 * This class contains information of a tile to display.
 * @author Canidio Andrea
 *
 */
public class DisplayedTile extends AbstractTile {

	//constants
	private static final int ROW4 = 4;
	private static final int ROW2 = 2;
	private static final int TILEARRAYLENGTH = 5;

	private static final int SOUTHPOSITION = 6;
	private static final int WESTPOSITION = 0;
	private static final int NORTHPOSITION = 6;

	private static final int TILEHEIGHT = 13;
	private static final int ROWLENGTH = 13;

	/**
	 * Initialize the displayedTile from a pattern
	 * @param pattern pattern of the tile.
	 * @throws IllegalArgumentException if the pattern is invalid.
	 */
	public DisplayedTile(String pattern) throws IllegalArgumentException{
		super(pattern);
	}


	/**
	 * Create the representation of the present Tile that can be used in the composition of the Textual Map.
	 * Only the internal part of the tile, not border.
	 * 
	 * @return An array of 5 String representing the Tile.
	 */
	public String[] toInternalRepresentation() {
		String representations[] = new String[TILEARRAYLENGTH];
		String northRepresentation = getAreaRepresentation(CardinalPosition.NORTH);
		String southRepresentation = getAreaRepresentation(CardinalPosition.SOUTH);
		String westRepresentation = getAreaRepresentation(CardinalPosition.WEST);
		String eastRepresentation = getAreaRepresentation(CardinalPosition.EAST);
		for(int i = 0; i < representations.length; i++) {
			StringBuffer currentRepresentation = new StringBuffer();
			while(currentRepresentation.length() < TILEHEIGHT){
				if(i == 0 && currentRepresentation.length() == NORTHPOSITION) {
					currentRepresentation.append(northRepresentation);
				}
				else if(i == ROW2 && currentRepresentation.length() == WESTPOSITION) {
					currentRepresentation.append(westRepresentation);
				}
				else if(i == ROW2 && currentRepresentation.length() == (TILEHEIGHT-eastRepresentation.length())) {
					currentRepresentation.append(eastRepresentation);
				}
				else if(i == ROW4 && currentRepresentation.length() == SOUTHPOSITION) {
					currentRepresentation.append(southRepresentation);
				}
				else {
					currentRepresentation.append(EMPTYSPACE);
				}
			}
			representations[i] = currentRepresentation.toString();
		}
		return representations;
	}

	/**
	 * Generate a complete textual representation of the tile.
	 * The representation is adequate to protocol.
	 * It include borders.
	 * @return the string representing the tile.
	 */
	public String toTextualRepresentation() {
		StringBuffer tileRepresentation = new StringBuffer();
		tileRepresentation.append(this.horizontalBorder());
		String[] internalRepresentation = this.toInternalRepresentation();
		for(String currentLine: internalRepresentation) {
			tileRepresentation.append('#');
			tileRepresentation.append(currentLine);
			tileRepresentation.append('#');
			tileRepresentation.append('\n');
		}
		tileRepresentation.append(this.horizontalBorder());
		return tileRepresentation.toString();
	}


	/**
	 * This methods generates the Area Representation of an Area
	 * It's a pattern like "C1(B)" used in the textual representation.
	 * 
	 * @param cardPosition identify the area which representation you want.
	 * @return string representing the Area Representation like specified above.
	 * @throws IllegalArgumentException if the parameter is invalid.
	 * 
	 */
	private String getAreaRepresentation(CardinalPosition selectedPosition) throws IllegalArgumentException {
		TileArea selectedArea = this.getTileArea(selectedPosition);
		StringBuffer areaRepresentation = new StringBuffer();
		areaRepresentation.append(selectedArea.getAreaType().getRepresentation());
		int counter = 1;
		for(TileArea currentArea: getAreas()) {
			CardinalPosition currentPosition = currentArea.getAreaPosition();
			if(areConnected(currentPosition, selectedPosition) || currentPosition.equals(selectedPosition)) {
				break;
			}
			if(currentArea.hasSameType(selectedArea)) {
				counter++;
			}
		}
		if(selectedArea.getAreaType()!=FieldType.FIELD) {
			areaRepresentation.append(counter);
		}
		if(selectedArea.hasFlag()) {
			areaRepresentation.append("(" + selectedArea.getAreaFlag().getColorCode() + ")");
		}
		return areaRepresentation.toString();
	}


	//Generate the horizontal border of the tile.

	private String horizontalBorder() {
		StringBuffer border = new StringBuffer();
		border.append('+');
		for(int i =0; i < ROWLENGTH; i++){
			border.append('#');
		}
		border.append('+');
		border.append('\n');
		return border.toString();
	}
}
