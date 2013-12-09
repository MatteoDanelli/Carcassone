package it.polimi.dei.provafinale.carcassone.model;

/**
 * This class create the map with the first tile.
 * @author Matteo Danelli
 *
 */
public class MapBuilder {

	private static final int FIRSTYCORDINATE = 0;
	private static final int FIRSTXCORDINATE = 0;
	private static final String STARTERTILEPATTERN = "N=N S=C W=S E=S NS=0 NE=0 NW=0 WE=1 SE=0 SW=0";

	/**
	 * Create the map at the start of the game.
	 * Put the starter tile in the map. (It's always the same tile)
	 * @param currentDeck
	 * @return	The map created.
	 */
	public ModelMap initializeMap(Deck currentDeck){
		ModelMap buildedMap = new ModelMap();
		ModelTile firstTile = new ModelTile(STARTERTILEPATTERN);
		if(currentDeck.contains(firstTile)) {
			int firstTileIndex = currentDeck.search(firstTile);
			currentDeck.removeTile(firstTileIndex);
			Position firstPosition = new Position(FIRSTXCORDINATE, FIRSTYCORDINATE);
			buildedMap.putTileInPosition(firstPosition, firstTile);
		}
		return buildedMap;
	}
}
