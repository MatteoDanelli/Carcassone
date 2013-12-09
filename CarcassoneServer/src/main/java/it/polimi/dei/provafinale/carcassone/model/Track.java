package it.polimi.dei.provafinale.carcassone.model;

import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import javax.management.openmbean.KeyAlreadyExistsException;

/**
 * This class represent a track, a structure to save the elements (street or city) connected.
 * 
 * @author Matteo Danelli
 * @version 1.0
 *
 */
public class Track {
	
	private HashMap<Position, PieceOfTrack> track;
	
	private FieldType trackType;
	
	/**
	 * Initialize the track with the parameter passed. 
	 * @param trackType - the type of track
	 */
	public Track(FieldType trackType){ 
		track = new HashMap<Position, PieceOfTrack>();
		this.trackType = trackType;
	}
	
	/**
	 * Add a tileArea and the relative position to the track, previously created.
	 * @param position		position of tile which contains TileArea
	 * @param tileArea		TileArea to add
	 * @exception 		exception is thrown every time it has already present the tileArea in the track 
	 */
	public void add(Position position, TileArea tileArea)
			throws KeyAlreadyExistsException {
		//check if position and tileArea are already present in the track 
		if (track.containsKey(position)) {
			PieceOfTrack currentPiece = track.get(position);
			if (currentPiece.contains(tileArea)) {
				throw new KeyAlreadyExistsException("Element already present");
			}
			currentPiece.add(tileArea);
		} 
		else {
			PieceOfTrack newPiece = new PieceOfTrack();
			newPiece.add(tileArea);
			track.put(position, newPiece);
		}
	}
	
	/**
	 * Counts the flags of a chosen Color present on the track.
	 * @param flagColor			color chosen.
	 * @return					number of flags counted.
	 */
	public int countFlags(Color flagColor) {
		int countedFlags = 0;
		for (Position currentPosition : track.keySet()) {
			countedFlags += track.get(currentPosition).countFlags(flagColor);
		}
		return countedFlags;
	}
	
	/**
	 * Counts the tiles of the track. Useful for the points computation.
	 * @return		number of tiles counted.
	 */
	public int countTiles() {
		return track.size();
	}

	/**
	 * Return the fieldType of the track.
	 * @return
	 */
	public FieldType getType() {
		return trackType;
	}

	/**
	 * Check if the track has a flag on it.
	 * @return	true if the flag is present, else false.
	 */
	public boolean hasFlag() {
		for (Position currentPosition : track.keySet()) {
			PieceOfTrack currentPiece = track.get(currentPosition);
			if(currentPiece.hasFlag()) {
				return true;
			}
		}
		return false;
	}
		
	/**
	 * Check if a specific position of the track has a flag on it.
	 * @param selectedPosition	specific position to check.
	 * @return	true if track has a flag, else false.
	 */
	public boolean hasFlag(Position selectedPosition) throws InvalidKeyException{
		if (track.containsKey(selectedPosition)) {
		return track.get(selectedPosition).hasFlag();
		}
		else {
			throw new InvalidKeyException("Position not in the track");
		}
	}

	/**
	 * Check if a track is closed.
	 * A track is closed if and only if the track has a starting point and an end point.
	 * @return true if track closed, else false.
	 */
	public boolean isClosed() {
		for(Position currentPosition: track.keySet()) {
			List<TileArea> areas = track.get(currentPosition).getAreas();
			for(TileArea currentArea: areas) {
				CardinalPosition direction = currentArea.getAreaPosition();
				Position oppositePosition = currentPosition.generateNeighbour(direction);
				if(!track.containsKey(oppositePosition)) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Check if the track is of specified FieldType
	 * @param type	FiledType to compare.
	 * @return	true if is of type, else false.
	 */
	public boolean is(FieldType type) {
		return trackType.equals(type);
	}
	
	/**
	 * Calculate the Players with the maximum number of flag in this track.
	 * @return a list of Color, each associated to a Player.
	 */
	public List<Color> getMaximumFlagPlayers() {
		List<Color> maxPlayers = new ArrayList<Color>();
		int maxFlags= maximumFlagNumber();
		for(Color currentPlayer: Color.values()) {
			if(countFlags(currentPlayer) == maxFlags) {
				maxPlayers.add(currentPlayer);
			}
		}
		return maxPlayers;
	}
	
	/**
	 * Get all position covered by the track
	 * @return a set of position covered.
	 */
	public Set<Position> getAllPositions() {
		return track.keySet();
	}
	
	//Calculate the maximum number of flag on the track
	private int maximumFlagNumber() {
		int max = 0;
		for(Color currentColor: Color.values()) {
			int currentColorNumber = this.countFlags(currentColor);
			if(currentColorNumber > max) {
				max = currentColorNumber;
			}
		}
		return max;
	}

}
