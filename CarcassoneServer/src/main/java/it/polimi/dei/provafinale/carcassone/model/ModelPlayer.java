package it.polimi.dei.provafinale.carcassone.model;

/**
 * This class represent a player, not a client. 
 * A player intended as instance of the class who plays.
 * 
 * @author Matteo Danelli
 * 
 */

public class ModelPlayer extends AbstractPlayer {

	//constants
	private static final int MAXFLAGS = 7;
	private static final int MINFLAGS = 0;
	private static final int STARTINGPOINT = 0;

	private ModelTile currentTile;

	/**
	 * Constructor of the class. Assigns the starting points to zero and the
	 * availableFlag to seven.
	 * 
	 * @param name
	 * @param flagColor
	 */
	public ModelPlayer(Color flagColor) {
		super(flagColor);
		setPoints(STARTINGPOINT);
		currentTile = null;
		setAvailableFlags(MAXFLAGS);
	}

	/**
	 * Getter of the currentTile
	 * @return ModelTile got.
	 */
	public ModelTile getCurrentTile() {
		return currentTile;
	}

	/**
	 * Set the currentTile to the player
	 * @param currentTile ModelTile to set.
	 */
	public void setCurrentTile(ModelTile currentTile) {
		this.currentTile = currentTile;
	}

	/**
	 * Check if the Player has some flags.	 * 
	 * @return true if has some flags, else false.
	 */
	public boolean hasMoreFlags() {
		if (getAvailableFlags() > MINFLAGS) {
			return true;
		}
		return false;
	}

	/**
	 * Add points to the player.
	 * 
	 * @param gainedPoints
	 */
	public void addPoints(int gainedPoints) {
		setPoints(getPoints() + gainedPoints);
	}

	/**
	 * Show information of a player: flagColor, Points, AvailableFlags.
	 * @return DisplayerPlayer created.
	 */
	public DisplayedPlayer display() {
		return new DisplayedPlayer(getFlagColor(), getPoints(), getAvailableFlags());
	}

}
