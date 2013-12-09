/**
 * 
 */
package it.polimi.dei.provafinale.carcassone.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class model a piece of track. 
 * Piece of track means each part that make the track.
 * @author Andrea Canidio
 * @version 1.0
 */
public class PieceOfTrack {

	private List<TileArea> tileAreaList;

	/**
	 * Constructor of pieceOfTrack. Initialize the tileAreaList which belongs to it.
	 */
	public PieceOfTrack(){
		tileAreaList = new ArrayList<TileArea>();
	}

	/**
	 * Getter method.
	 * Give all areas of this piecOfTrack.
	 * @return 
	 */
	public List<TileArea> getAreas() {
		return tileAreaList;
	}

	/**
	 * Add a tileArea to the pieceOfTrack
	 * @param element
	 */
	public void add(TileArea element){
		tileAreaList.add(element);
	}

	/**
	 * Check if this contains the tileArea passed in parameter
	 * @param tileAreaToCheck
	 * @return
	 */
	public boolean contains(TileArea tileAreaToCheck){
		return tileAreaList.contains(tileAreaToCheck);
	}

	/**
	 * Count flags of a specific flagColor.
	 * @param flagColor
	 * @return number of flags counted.
	 */
	public int countFlags(Color flagColor){
		int flagCounter = 0;
		for(TileArea currentArea: tileAreaList){
			if(currentArea.hasFlag()){
				Color currentFlag = currentArea.getAreaFlag();
				if(currentFlag.equals(flagColor)){
					flagCounter++;
				}
			}
		}
		return flagCounter;
	}


	/**
	 * Check if this has a flag on it.
	 * @return true if it's a flag, else false.
	 */
	public boolean hasFlag() {
		for(TileArea currentArea: tileAreaList) {
			if(currentArea.hasFlag()) {
				return true;
			}
		}
		return false;
	}

}
