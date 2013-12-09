package it.polimi.dei.provafinale.carcassone.controller;

import it.polimi.dei.provafinale.carcassone.model.CardinalPosition;
import it.polimi.dei.provafinale.carcassone.model.Position;
import it.polimi.dei.provafinale.carcassone.view.TextualView;

/**
 * This class manages and controls all the input.
 * @author Matteo Danelli
 *
 */
public class InputController {

	//constants
	private static final int INITALCHAR = 0;
	private static final String NO = "N";
	private static final String YES = "Y";
	private static final int MAXNUMBEROFPLAYERS = 5;
	private static final int MINNUMBEROFPLAYERS = 2;
	private static final int CHARACTER = 1;

	private TextualView currentView;

	/**
	 * Select the correct view to control.
	 * @param currentView view to control:textual/client
	 */
	public InputController(TextualView currentView) {
		this.currentView = currentView;
	}

	/**
	 * Ask to the user the numbers of Player.
	 * Check if the number insert is consistent.
	 * @return	Numbers of player inserted.
	 */
	public int getPlayersNumber() {
		int availablePlayers = currentView.askNumberOfPlayer();
		while (availablePlayers < MINNUMBEROFPLAYERS || availablePlayers > MAXNUMBEROFPLAYERS) {
			currentView.showErrorMessage();
			availablePlayers = currentView.askNumberOfPlayer();
		}
		return availablePlayers;
	}

	/**  
	 * Ask to user if he want rotate.
	 * Check the correctness of user answer.
	 * @return	true if user wants rotate, else false.
	 */

	public boolean wantRotate() {
		//The user input can be uppercase or lowercase.
		String answer = currentView.wantRotate().toUpperCase();
		while(!isYesNo(answer)) {
			currentView.showErrorMessage();
			answer = currentView.wantRotate().toUpperCase();
		}
		if(answer.equals(YES)) {
			return true;
		}
		return false;
	}

	/**
	 * Ask to user if he want place flag.
	 * Check the correctness of the answer.
	 * @return true if user wants place flag, else false.
	 */
	public boolean wantPlaceFlag() {
		//The user input can be uppercase or lowercase.
		String answer = currentView.wantPlaceFlag().toUpperCase();
		while(!isYesNo(answer)) {
			currentView.showErrorMessage();
			answer = currentView.wantPlaceFlag().toUpperCase();
		}
		if(answer.equals(YES)) {
			return true;
		}
		return false;
	}

	/**
	 * Ask where the user want place flag.
	 * Check also the correctness of the input.
	 * @return	CardinalPosition of where place flag.
	 */
	public CardinalPosition wherePlaceFlag() {
		//The user input can be uppercase or lowercase.
		String answer = currentView.wherePlaceFlag().toUpperCase();
		while(!isCardinalPosition(answer)) {
			currentView.showErrorMessage();
			answer = currentView.wherePlaceFlag().toUpperCase();
		}
		return CardinalPosition.recognizePositionCode(answer.charAt(INITALCHAR));
	}


	/**
	 * Ask the user where place tile shown.
	 * Check correctness of user input.
	 * @return	Position of where place tile.
	 */
	public Position wherePlaceTile() {
		String answer = currentView.wherePlaceTile();
		while(!isPosition(answer)) {
			currentView.showErrorMessage();
			answer = currentView.wherePlaceTile();
		}
		return new Position(answer);
	}

	/**
	 * Check if the parameter equals to "y" or "n".
	 * @param answer
	 * @return	true if input correct, else false.
	 */
	private boolean isYesNo(String answer) {
		return (answer.equals(YES) || answer.equals(NO));
	}

	/**
	 * Check if it's possible create a cardinal position from the parameter passed as string.
	 * @param answer
	 * @return	true if input correct, else false.
	 */
	private boolean isCardinalPosition(String answer) {
		if(answer.length() != CHARACTER) {
			return false;
		}
		try {
			//Catch only the first character inserted.
			CardinalPosition.recognizePositionCode(answer.charAt(INITALCHAR));
		}
		catch(IllegalArgumentException e) {
			return false;
		}
		return true;
	}

	/**
	 * Check if it's possible create a position from the parameter passed as string.
	 * @param answer
	 * @return	true if input correct, else false.
	 */
	private boolean isPosition(String answer) {
		try {
			new Position(answer);
		}
		catch(IllegalArgumentException e) {
			return false;
		}
		return true;
	}
}
