package it.polimi.dei.provafinale.carcassone.model;

/**
 * This class represent a displayed player during the game.
 * Contains all informations about a player which must be displayer to others player.
 * @author Canidio Andrea
 *
 */
public class DisplayedPlayer extends AbstractPlayer{

	/**
	 * Initialize the displatedPlayer with the parameter passed.
	 * @param flagColor flag of the Player.
	 * @param points points of the Player.
	 * @param availableFlags available flags of the player.
	 */
	public DisplayedPlayer(Color flagColor, int points, int availableFlags) {
		super(flagColor);
		this.setPoints(points);
		this.setAvailableFlags(availableFlags);
	}
}
