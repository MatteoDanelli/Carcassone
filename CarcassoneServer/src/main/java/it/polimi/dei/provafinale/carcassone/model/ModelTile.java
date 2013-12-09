package it.polimi.dei.provafinale.carcassone.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * The class models the Tile. It contains informations of the 4 areas and 6
 * connections.
 * 
 * @author Andrea Canidio, Matteo Danelli
 * @version 1.0
 */

public class ModelTile extends AbstractTile {

	//All constant used in the class
	private static final char EQUAL = '=';

	private static final int NORTH = 0;
	private static final int SOUTH = 1;
	private static final int WEST = 2;
	private static final int EAST = 3;

	private static final int NORTHSOUTH = 4;
	private static final int NORTHEAST = 5;
	private static final int NORTHWEST = 6;
	private static final int WESTEAST = 7;
	private static final int SOUTHEAST = 8;
	private static final int SOUTHWEST = 9;

	private static final int MAXLENGHTOFTILESTRING = 3;

	/**
	 * Create a tile from a string.
	 * @param tilePattern string which represents a tile.
	 * @throws IllegalArgumentException if the string isn't valid.-
	 */
	public ModelTile(String tilePattern) throws IllegalArgumentException {
		super(tilePattern);
	}

	/**
	 * This method rotate the Tile 90 degrees clockwise. 
	 * It creates a new Tile.
	 * 
	 * @return tile rotated
	 */
	protected ModelTile rotate() {
		StringTokenizer cutter = new StringTokenizer(this.toString(), " =\n");
		List<String> cardinals = new ArrayList<String>();
		List<String> characters = new ArrayList<String>();
		while (cutter.hasMoreTokens()) {
			cardinals.add(cutter.nextToken(" =\n"));
			if (cutter.hasMoreTokens()) {
				characters.add(cutter.nextToken(" =\n"));
			}
		}
		StringBuffer constructor = new StringBuffer();
		// Rotating the areas, one by one
		constructor.append(cardinals.get(NORTH));
		constructor.append(EQUAL);
		constructor.append(characters.get(WEST));
		constructor.append(EMPTYSPACE);
		constructor.append(cardinals.get(SOUTH));
		constructor.append(EQUAL);
		constructor.append(characters.get(EAST));
		constructor.append(EMPTYSPACE);
		constructor.append(cardinals.get(WEST));
		constructor.append(EQUAL);
		constructor.append(characters.get(SOUTH));
		constructor.append(EMPTYSPACE);
		constructor.append(cardinals.get(EAST));
		constructor.append(EQUAL);
		constructor.append(characters.get(NORTH));
		constructor.append(EMPTYSPACE);
		// Rotating the connections
		constructor.append(cardinals.get(NORTHSOUTH));
		constructor.append(EQUAL);
		constructor.append(characters.get(WESTEAST));
		constructor.append(EMPTYSPACE);
		constructor.append(cardinals.get(NORTHEAST));
		constructor.append(EQUAL);
		constructor.append(characters.get(NORTHWEST));
		constructor.append(EMPTYSPACE);
		constructor.append(cardinals.get(NORTHWEST));
		constructor.append(EQUAL);
		constructor.append(characters.get(SOUTHWEST));
		constructor.append(EMPTYSPACE);
		constructor.append(cardinals.get(WESTEAST));
		constructor.append(EQUAL);
		constructor.append(characters.get(NORTHSOUTH));
		constructor.append(EMPTYSPACE);
		constructor.append(cardinals.get(SOUTHEAST));
		constructor.append(EQUAL);
		constructor.append(characters.get(NORTHEAST));
		constructor.append(EMPTYSPACE);
		constructor.append(cardinals.get(SOUTHWEST));
		constructor.append(EQUAL);
		constructor.append(characters.get(SOUTHEAST));
		constructor.append(EMPTYSPACE);

		return new ModelTile(constructor.toString());
	}

	/**
	 * Get all independent areas of a tile. Independent means not connected.
	 * 
	 * @return a set of TileArea
	 */
	public Set<TileArea> getIndipendentAreas() {
		Set<TileArea> currentList = new HashSet<TileArea>();
		for (CardinalPosition currentPosition : CardinalPosition.values()) {
			TileArea currentArea = this.getTileArea(currentPosition);
			if (!currentArea.getAreaType(FieldType.FIELD) && isIndipendent(currentArea)) {
				currentList.add(currentArea);
			}
		}
		return currentList;
	}

	/**
	 * Get the TileArea connected to the TileArea passed as parameter.
	 * 
	 * @param selectedArea
	 *            TileArea to compare.
	 * @return list of TileArea connected to TileArea passed.
	 */
	public List<TileArea> getConnectedAreas(TileArea selectedArea) {
		CardinalPosition selectedPosition = selectedArea.getAreaPosition();
		List<TileArea> connectedAreas = new ArrayList<TileArea>();
		for (CardinalPosition currentPosition: CardinalPosition.values()) {
			if (this.areConnected(currentPosition, selectedPosition)) {
				TileArea currentArea = this.getTileArea(currentPosition);
				connectedAreas.add(currentArea);
			}
		}
		return connectedAreas;
	}

	/**
	 * Check if the tile is compatible with the Tile passed in the cardinal
	 * position passed.
	 * 
	 * @param comparedTile
	 *            tile to compare with this.
	 * @param relativePosition
	 *            position of this tile to compare.
	 * @return true if the two tiles are compatible, else false.
	 */
	public boolean isCompatible(ModelTile comparedTile,
			CardinalPosition relativePosition) {
		TileArea myTileArea = this.getTileArea(relativePosition);
		TileArea otherTileArea = comparedTile.getTileArea(relativePosition
				.opposite());
		return myTileArea.hasSameType(otherTileArea);
	}

	/**
	 * Add a flag on a cardinal position of the tile
	 * 
	 * @param flag
	 *            color of the flag.
	 * @param direction
	 *            cardinal position in which place the flag.
	 * @return tile with flag update.
	 */
	public ModelTile putFlag(Color flag, CardinalPosition direction) {
		StringTokenizer cutter = new StringTokenizer(this.toString());
		StringBuffer constructor = new StringBuffer();
		for (int i = 0; cutter.hasMoreTokens(); i++) {
			constructor.append(cutter.nextToken());
			if (i == direction.ordinal()) {
				constructor.append('+');
				constructor.append(flag.getColorCode());
			}
			constructor.append(EMPTYSPACE);
		}
		return new ModelTile(constructor.toString());
	}

	/**
	 * Generate the displayed tile of the current tile.
	 * Useful for show the textual representation of the tile.
	 * @return the displayed tile created.
	 */
	public DisplayedTile display() {
		return new DisplayedTile(this.toString());
	}

	/**
	 * Remove a flag from the tile.
	 * 
	 * @return updated tile without flag.
	 */
	public ModelTile removeFlag() {
		StringTokenizer cutter = new StringTokenizer(this.toString());
		StringBuffer constructor = new StringBuffer();
		int tokenCounter = 0;
		while (cutter.hasMoreTokens()) {
			String token = cutter.nextToken();
			tokenCounter++;
			if (tokenCounter <= AREASNUMBER) {
				constructor.append(token.substring(0, MAXLENGHTOFTILESTRING));
			} else {
				constructor.append(token);
			}
			constructor.append(EMPTYSPACE);
		}
		return new ModelTile(constructor.toString());
	}

	/*
	 * Check if an area is not connected to others.
	 */
	private boolean isIndipendent(TileArea currentArea) {
		CardinalPosition currentPosition = currentArea.getAreaPosition();
		for (CardinalPosition precedentPosition : CardinalPosition.values()) {
			if (currentPosition == precedentPosition) {
				break;
			} else if (areConnected(currentPosition, precedentPosition)) {
				return false;
			}
		}
		return true;
	}

}
