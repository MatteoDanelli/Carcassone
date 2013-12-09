package it.polimi.dei.provafinale.carcassone.view;

import it.polimi.dei.provafinale.carcassone.model.Color;
import it.polimi.dei.provafinale.carcassone.model.DisplayedMap;
import it.polimi.dei.provafinale.carcassone.model.DisplayedPlayer;
import it.polimi.dei.provafinale.carcassone.model.DisplayedTile;
import it.polimi.dei.provafinale.carcassone.model.ModelPlayer;
import it.polimi.dei.provafinale.carcassone.model.Position;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

/**
 * This class control all the view game on the console.
 * @author Matteo Danelli
 * @version 1.0
 */
public class TextualView{

	private Scanner systemIn;
	private PrintStream systemOut;

	/**
	 * Initialize the scannerIn and the standard output.
	 */
	public TextualView(){
		systemIn = new Scanner(System.in);
		systemOut = System.out;
	}

	/**
	 * Ask to user the numbers of the players who want play game.
	 * Also read the answer.
	 * Print the ask on the stdout.
	 */
	public int askNumberOfPlayer() {
		systemOut.print("Insert number of players: ");
		return systemIn.nextInt();
	}

	/**
	 * Show to user a game state.
	 * It's the current player, the tile of that player and the current map.
	 * Print all on the stdout.
	 */
	public void showGameState(DisplayedPlayer currentPlayer, DisplayedMap currentMap, DisplayedTile currentTile) {
		showPlayer(currentPlayer);
		showTile(currentTile);
		drawMap(currentMap);
	}

	/**
	 * Show to user ending points, for all players.
	 * Print all on the stdout.
	 * @param the players who played.
	 */
	public void showFinalPoint (List<DisplayedPlayer> listOfPlayer) {
		systemOut.println("Final scores: ");
		for (DisplayedPlayer p : listOfPlayer) {
			systemOut.println("\t" + p.getFlagColor() + " totalized " + p.getPoints() + " points");
		}
	}

	/**
	 * Show to winner player, at the end of the game.
	 * Print this on the stdout.
	 */
	public void showWinner (ModelPlayer winner) {
		systemOut.println(winner.getFlagColor()  + "is the winner!");
	}

	/**
	 * Ask to user where he wants place tile shown.
	 * Also read the answer.
	 * Print question on the stdout.
	 */
	public String wherePlaceTile() {
		systemOut.print("Where do you want to place this tile? (X,Y) ");
		return systemIn.next();
	}

	/**
	 * Ask to user if he wants place tile shown.
	 * Also read the answer.
	 * Print question on the stdout.
	 */
	public String wantPlaceTile() {
		systemOut.print("Do you want to place a flag? (Y/N) ");
		return systemIn.next();
	}

	/**
	 * Ask to user if he wants place flag.
	 * Also read the answer.
	 * Print question on the stdout.
	 */
	public String wantPlaceFlag() {
		systemOut.print("Do you want to place a flag? (Y/N) ");
		return systemIn.next();
	}

	/**
	 * Ask to user where he wants place flag.
	 * Also read the answer.
	 * Print question on the stdout.
	 */
	public String wherePlaceFlag () {
		systemOut.print("Insert where place flag: (N/S/W/E) ");
		return systemIn.next();
	}

	/**
	 * Print a generic error message.
	 * Print it on the stdout.
	 */
	public void showErrorMessage() {
		systemOut.println("Error. Insert other time");
	}

	/**
	 * Print information that user has passed his turn.
	 * Print it on the stdout.
	 */
	public void passTurn(Color currentColor) {
		systemOut.println(currentColor + " has passed the turn.");
	}

	/**
	 * Print a warning when the flag isn't placeable.
	 * Print it on the stdout.
	 */
	public void flagNotPlaceable() {
		systemOut.println("Flag not placeable here!");
	}

	/**
	 * Print a warning.
	 * Print it on the stdout.
	 */
	public void positionNotSuitable(Position currentPosition) {
		systemOut.println(currentPosition + " not correct!");
	}

	/**
	 * Print a error message because input incorrect.
	 * Print it on the stdout.
	 */
	public void showInputError() {
		systemOut.println("Error. Retry with correct input.");
	}

	/**
	 * Ask user if he want rotate tile and read answer.
	 * Print on the stdout.
	 */
	public String wantRotate() {
		systemOut.println("Do you want to rotate? (y/n)");
		return systemIn.next();		 
	}

	/**
	 * Print a warning when the tile cannot be placed.
	 * Print it on the stdout.
	 */
	public void tileNotPlaceable() {
		systemOut.println("Tile cannot be placed");
	}

	/**
	 * Shows that a connection is refused
	 */
	public void connectionRefused() {
		systemOut.println("Connection refused");
	}

	/**
	 * shows the connections informations
	 */
	public void showConnectionInformation(String gameName, Color myColor) {
		systemOut.println("Connected to " + gameName);
		systemOut.println("My color is " + myColor);
	}

	private void showTile(DisplayedTile tile) {
		systemOut.println("Current tile:");
		systemOut.println(tile.toTextualRepresentation());
	} 

	private void drawMap(DisplayedMap map) {
		systemOut.println("Current map:");
		systemOut.println(map.toTextualRepresentation());
	}

	private void showPlayer (DisplayedPlayer player) {
		systemOut.println("\nThe current player is: " + player.getFlagColor());
		systemOut.println("\tAvailable flags: " + player.getAvailableFlags());
		systemOut.println("\tPoints: " + player.getPoints());
	}
}
