package it.polimi.dei.provafinale.carcassone.model;

/**
 * @author Canidio Andrea
 * 
 */
public abstract class AbstractPlayer {

	private Color flagColor;
	private int points;
	private int availableFlags;

	protected AbstractPlayer(Color flagColor) {
		this.flagColor = flagColor;
	}

	public Color getFlagColor() {
		return flagColor;
	}
	
	public void setFlagColor(Color flagColor) {
		this.flagColor = flagColor;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getAvailableFlags() {
		return availableFlags;
	}
	
	public void setAvailableFlags(int availableFlags) {
		this.availableFlags = availableFlags;
	}

	/**
	 * Give a flag to the player.
	 * 
	 * @throws IllegalAccessException
	 *             if the player has the max number of flags.
	 */
	public void receiveFlag() {
		availableFlags++;
	}

	/**
	 * Pick a flag from the player.
	 */
	public void placeFlag() {
		availableFlags--;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof AbstractPlayer)) {
			return false;
		}
		AbstractPlayer other = (AbstractPlayer) obj;
		if (!this.flagColor.equals(other.flagColor)) {
			return false;
		}
		if (this.points != other.points) {
			return false;
		}
		if (this.availableFlags != other.availableFlags) {
			return false;
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		return flagColor.ordinal();
	}
}
