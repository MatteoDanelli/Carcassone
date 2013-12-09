package it.polimi.dei.provafinale.carcassone.model;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Canidio Andrea
 *
 */
public abstract class AbstractMap {

	private Map<Position, AbstractTile> tilesPlaced;
	
	private Set<Position> availableCells;
	
	public Map<Position, AbstractTile> getTilesPlaced() {
		return tilesPlaced;
	}

	public void setTilesPlaced(Map<Position, AbstractTile> tilesPlaced) {
		this.tilesPlaced = tilesPlaced;
	}

	public Set<Position> getAvailableCells() {
		return availableCells;
	}

	public void setAvailableCells(Set<Position> availableCells) {
		this.availableCells = availableCells;
	}
	
	protected AbstractMap() {
		tilesPlaced = new LinkedHashMap<Position, AbstractTile>();
		setAvailableCells(new HashSet<Position>());
	}
	
	/**
	 * Getter of a tile from a given position.
	 * @param positionToCheck	
	 * @return	tile found
	 */
	protected AbstractTile getTileFromPosition(Position positionToCheck) {
		return tilesPlaced.get(positionToCheck);
	}
	
	/**
	 * Put a tile in the positionToInsert. WARNING: it doesn't check if the
	 * position is available, or if the tile is correctly positioned.
	 * 
	 * @param positionToInsert
	 * @param tileToInsert
	 * @throws IllegalArgumentException
	 *             if the tile, or position, is wrong.
	 */

	public void putTileInPosition(Position positionToInsert, AbstractTile tileToInsert) {
		tilesPlaced.put(positionToInsert, tileToInsert);
		// Add the neighbourCell to the availableCells
		Position neighbours[] = positionToInsert.generateNeighbours();
		for (Position pos : neighbours) {
			if (!thereIsTileIn(pos)) {
				getAvailableCells().add(pos);
			}
		}
		// Remove the selected Position from available cells
		if (isAnAvailablePosition(positionToInsert)) {
			getAvailableCells().remove(positionToInsert);
		}
	}
	
	/**
	 * Check if the position of parameter is occupied.
	 * @param positionToCheck
	 * @return true if position occupied, false if it's free.
	 */
	public boolean thereIsTileIn(Position positionToCheck) {
		return tilesPlaced.containsKey(positionToCheck);
	}
	
	/**
	 * Check if the positionToCheck is available.
	 * Available means that the player can put a tile there.
	 * 
	 * @param positionToCheck
	 * @return true if it's available, else false.
	 */
	protected boolean isAnAvailablePosition(Position positionToCheck) {
		return getAvailableCells().contains(positionToCheck);
	}
}
