/**
 * 
 */
package it.polimi.dei.provafinale.carcassone.model;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Canidio Andrea
 *
 */
public abstract class AbstractTile {

	protected static final String EMPTYSPACE = " ";
	protected static final int AREASNUMBER = 4;
	private static final int CONNECTIONSNUMBER = 6;
	private static final int MAXTOKENS = 10;
	
	private static final int PRIMENUMBER2 = 31;
	private static final int PRIMENUMBER1 = 17;
	
	/**
	 * Array representing the areas of a Tile.
	 */
	private List<TileArea> areas;
	
	public List<TileArea> getAreas() {
		return areas;
	}

	public void setAreas(List<TileArea> areas) {
		this.areas = areas;
	}

	public List<TileConnection> getConnections() {
		return connections;
	}

	public void setConnections(List<TileConnection> connections) {
		this.connections = connections;
	}

	/**
	 * Array representing the connections of a Tile.
	 */
	private List<TileConnection> connections;
	
	/**
	 * Constructor of a Tile. It instantiates a Tile based on pattern passed.
	 * 
	 * @param tilePattern
	 *            The String represents a Tile like the protocol specific.
	 * @throws IllegalArgumentException
	 *             This Exception is thrown every time that is a problem
	 *             registering data in the structure because of an invalid
	 *             pattern.
	 */
	protected AbstractTile(String tilePattern) throws IllegalArgumentException {
		areas = new ArrayList<TileArea>(AREASNUMBER);
		connections = new ArrayList<TileConnection>(CONNECTIONSNUMBER);
		StringTokenizer tokenizer = new StringTokenizer(tilePattern);
		if (tokenizer.countTokens() != MAXTOKENS) {
			throw new IllegalArgumentException("Invalid Tile Pattern: " + tilePattern);
		}
		try {
			for (int i = 0; i < AREASNUMBER; i++) {
				areas.add(new TileArea(tokenizer.nextToken()));
			}
			for (int i = 0; i < CONNECTIONSNUMBER; i++) {
				connections.add(new TileConnection(tokenizer.nextToken()));
			}
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Invalid Tile Pattern: " + tilePattern, e);
		}
	}
	
	/**
	 * The toString method create a String representing a Tile.
	 * The string created is conform to the requirement
	 * 
	 * @return The String representing the Tile.
	 */
	@Override
	public String toString() {
		StringBuffer tileRepresentation = new StringBuffer();
		for (TileArea currentArea: areas) {
			tileRepresentation.append(currentArea.toString());
			tileRepresentation.append(EMPTYSPACE);
		}
		for (TileConnection currentConnection: connections){
				tileRepresentation.append(currentConnection.toString());
				tileRepresentation.append(EMPTYSPACE);
		}
		int lastCharacterIndex = tileRepresentation.length() - 1;
		tileRepresentation.deleteCharAt(lastCharacterIndex);
		return tileRepresentation.toString();
	}
	
	/**
	 * Return the tileArea in the specified cardinalPosition of the tile.
	 * @param cardinalPosition CardinalPosition to check.
	 * @return TileArea founded.
	 * @throws IllegalArgumentException if the cardinal position isn't correct.
	 */
	public TileArea getTileArea(CardinalPosition cardinalPosition)
			throws IllegalArgumentException {
		for(TileArea currentArea: areas) {
			if(currentArea.isChosenArea(cardinalPosition)) {
				return currentArea;
			}
		}
		throw new IllegalArgumentException("Invalid Cardinal Position");
	}
	
	/**
	 * Return the tileConnection in the tile between start and end.
	 * @param start starting CardinalPosition.
	 * @param end ending CardinalPosition.
	 * @return TileConnection founded.
	 * @throws IllegalArgumentException if one parameter is invalid.
	 */
	public TileConnection getTileConnection(CardinalPosition start, CardinalPosition end)
			throws IllegalArgumentException {
		for(TileConnection currentConnection: connections) {
			if(currentConnection.isChosenConnection(start, end)) {
				return currentConnection;
			}
		}
		throw new IllegalArgumentException("Invalid Cardinal Positions");
	}
	
	/**
	 * Get the flag of the tile.
	 * @return the color of flag if have one.
	 */
	public Color getFlag() throws IllegalStateException{
		for(TileArea currentArea: areas) {
			if(currentArea.hasFlag()) {
				return currentArea.getAreaFlag();
			}
		}
		throw new IllegalStateException("This tile doesn't have a Flag.");
	}
	
	/**
	 * Check if the tile has a flag on it.
	 * @return true if the tile has a flag, else false.
	 */
	public boolean hasFlag() {
		for(TileArea currentArea: areas) {
			if(currentArea.hasFlag()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Two Tile are equals if and only if they have same TileAreas and TileConnections.
	 */
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof AbstractTile)) {
			return false;
		}
		AbstractTile other = (AbstractTile) obj;
		for (int i = 0; i < AREASNUMBER; i++) {
			if (!areas.get(i).equals(other.areas.get(i))) {
				return false;
			}
		}
		for (int i = 0; i < CONNECTIONSNUMBER; i++) {
			if (!connections.get(i).equals(other.connections.get(i))) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public int hashCode(){
		int hashCode = 0;
		int counter = AREASNUMBER;
		for(TileArea currentArea: areas) {
			hashCode += PRIMENUMBER1 * counter * currentArea.hashCode();
			counter--;
		}
		counter = CONNECTIONSNUMBER;
		for(TileConnection currentConnection: connections) {
			hashCode *= PRIMENUMBER2 * counter * currentConnection.hashCode();
			counter--;
		}
		return hashCode;
	}
	
	/*
	 * Check if two tiles are connected.
	 */
	protected boolean areConnected(CardinalPosition thisOne, CardinalPosition thisOther) {
		for(TileConnection currentConnection: connections) {
			if(currentConnection.isChosenConnection(thisOne, thisOther)) {
				return currentConnection.isPresent();
			}
				
		}
		return false;
	}
}
