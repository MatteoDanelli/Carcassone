package it.polimi.dei.provafinale.carcassone.model;

import java.util.List;
import java.util.Set;

import javax.management.openmbean.KeyAlreadyExistsException;

/**
 * This class contains all informations about a map.
 * @author Andrea Canidio
 * @version 1.1
 */

public class ModelMap extends AbstractMap {

	/**
	 * Constructor of the class.
	 */
	public ModelMap() {
		super();
	}

	/**
	 * REtrieve a tile from position passed. 
	 * Check if the position is available, else throws an exception.
	 * @param positioToCheck.
	 * @return ModelTile tile got.
	 * @exception NullPointerException if there isn't a tile in that position.
	 * 
	 */
	@Override
	public ModelTile getTileFromPosition(Position positionToCheck) throws NullPointerException {
		if(thereIsTileIn(positionToCheck)) {
			return (ModelTile) super.getTileFromPosition(positionToCheck);
		}
		throw new NullPointerException("No Tile placed in this Position");
	}

	/**
	 * Check if the currentTile, and all its rotations, are placeable somewhere
	 * in the map.
	 * 
	 * @param currentTile
	 * @return true if it's placeable, else false.
	 */
	public boolean isPlaceableWithRotations(ModelTile currentTile) {
		ModelTile copiedTile = new ModelTile(currentTile.toString());
		do {
			if (isPlaceable(copiedTile)) {
				return true;
			}
			copiedTile = copiedTile.rotate();
		} while (!copiedTile.equals(currentTile));
		return false;
	}

	/**
	 * Check if the currentTile is placeable somewhere in the map.
	 * 
	 * @param currentTile
	 * @return true if it's placeable, else false.
	 */
	public boolean isPlaceable(ModelTile currentTile) {
		for (Position currentPosition : getAvailableCells()) {
			if (isSuitable(currentPosition, currentTile)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Check if the tileToInsert is suitable in the positionToInsert. More info:
	 * tile is suitable if it's near a compatible tile.
	 * 
	 * @param positionToInsert
	 * @param tileToInsert
	 * @return true if position is compatible, else false.
	 */
	public boolean isSuitable(Position positionToInsert, ModelTile tileToInsert) {
		Position[] neighbourPositions = positionToInsert.generateNeighbours();
		for (Position currentPosition : neighbourPositions) {
			if (thereIsTileIn(currentPosition)) {
				ModelTile currentTile = (ModelTile) getTilesPlaced().get(currentPosition);
				CardinalPosition relativePosition = positionToInsert
						.hasRelativePositionRespect(currentPosition);
				if (!currentTile.isCompatible(tileToInsert, relativePosition)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Give all the placed Position. A position is in the returned set if has a
	 * tile on it.
	 * 
	 * @return a set of position placed.
	 */
	public Set<Position> getAllPlacedPositions() {
		return getTilesPlaced().keySet();
	}

	/**
	 * From a starting position and from a starting tileArea generate the track connected to the element 
	 * in that tileArea.
	 * @param start starting TileArea.
	 * @param startPosition starting position of the tile in the map.
	 * @return the Track generated.
	 */
	public Track generateTrack(TileArea start, Position startPosition) {
		Track generatedTrack = new Track(start.getAreaType());
		exploreTrack(generatedTrack, start, startPosition);
		return generatedTrack;
	}

	private boolean exploreTrack(Track currentTrack, TileArea start, Position startPosition) {
		boolean trackModified = false;
		ModelTile startTile = (ModelTile) getTilesPlaced().get(startPosition);
		List<TileArea> areas = startTile.getConnectedAreas(start);
		areas.add(start);
		for (TileArea currentArea : areas) {
			try {
				currentTrack.add(startPosition, currentArea);
				trackModified = true;
			} catch (KeyAlreadyExistsException e) {

			}
		}
		if (trackModified) {
			for (TileArea currentArea : areas) {
				try {
					CardinalPosition currentOrientation = currentArea.getAreaPosition();
					Position nextPosition = startPosition.generateNeighbour(currentOrientation);
					ModelTile nextTile = this.getTileFromPosition(nextPosition);
					TileArea nextArea = nextTile.getTileArea(currentOrientation.opposite());
					exploreTrack(currentTrack, nextArea, nextPosition);
				} catch (NullPointerException e) {

				}
			}
		}
		return true;
	}
}
