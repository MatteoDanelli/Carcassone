package it.polimi.dei.provafinale.carcassone.controller;

import java.io.IOException;
import it.polimi.dei.provafinale.carcassone.model.CardinalPosition;
import it.polimi.dei.provafinale.carcassone.model.Color;
import it.polimi.dei.provafinale.carcassone.model.Game;
import it.polimi.dei.provafinale.carcassone.model.Position;
import it.polimi.dei.provafinale.carcassone.model.ModelTile;
import it.polimi.dei.provafinale.carcassone.view.TextualView;

/**
 * This class manages the game on textual version.
 * @author Matteo Danelli, Andrea Canidio
 *
 */
public class TextualController extends ControllerFactory {

	private InputController inputController;
	private TextualView textualView;
	private Game currentGame;
	private GameDisplayer displayer;

	/**
	 * Create the textual controller with a view, a game, an inputcontroller and the andd also observer.
	 * @throws IllegalArgumentException
	 */
	public TextualController() throws IllegalArgumentException{
		textualView = new TextualView();
		currentGame= new Game();
		inputController = new InputController(textualView);
		displayer = new GameDisplayer(textualView);
		currentGame.addObserver(displayer);
	}

	/**
	 * Starts the controller and play the game.
	 */
	@Override
	public void start() throws IllegalArgumentException, IOException{
		int playersNumber = inputController.getPlayersNumber();
		//Create all player insert by user
		for(int i = 0; i < playersNumber; i++) {
			currentGame.createPlayer();
		}
		//Load the deck into current game
		currentGame.initializeGame();
		currentGame.shuffleDeck();

		/*// Discarding many tiles for littler play
		for(int i = 0; i < 50; i++) {
			drawTile(Color.RED);
		}*/

		playGame();
	}		

	private void playGame() {
		while(!currentGame.isLastTurn()) {
			Color currentColor = currentGame.getTurnPlayer();
			playTurn(currentColor);
			currentGame.nextTurn();
		}
		currentGame.calculateFinalScores();
		displayer.showFinalPoint();
	}

	private void playTurn(Color currentColor) {
		// Draw a tile and assign it to currentPlayer
		ModelTile currentTile = drawTile(currentColor);
		boolean placed;
		do {
			placed = false;
			displayer.showGameState();
			currentTile = rotate(currentColor, currentTile);
			if(currentGame.isPlaceable(currentTile)) {
				Position currentPosition = placeTile(currentColor, currentTile);
				if (currentGame.canPlaceFlagInTile(currentPosition)) {
					placeFlag(currentColor, currentPosition);
				}
				currentGame.calculateScore(currentPosition);
				displayer.showGameState();
				placed = true;
			}
			else {
				displayer.tileNotPlaceable();
			}
		} while(!placed);
	}

	private void placeFlag(Color currentColor, Position currentPosition) {
		while(inputController.wantPlaceFlag()) {
			CardinalPosition flagPosition = inputController.wherePlaceFlag();
			if (currentGame.canPlaceFlag(currentPosition, flagPosition)) {
				currentGame.insertFlag(currentPosition, flagPosition, currentColor);
				displayer.showGameState();
				break;
			}
			else {
				displayer.flagNotPlaceable();					
			}
		}
	}

	private Position placeTile(Color currentColor, ModelTile currentTile) {
		Position currentPosition;
		boolean inserted;
		do {
			inserted = false;
			currentPosition = inputController.wherePlaceTile();
			if(currentGame.isPlaceableHere(currentTile, currentPosition)) {
				currentGame.putTileInPosition(currentColor, currentPosition);
				displayer.showGameState();
				inserted = true;
			}
			else {
				displayer.positionNotSuitable(currentPosition);
			}
		} while(!inserted);
		return currentPosition;
	}

	private ModelTile rotate(Color currentColor, ModelTile currentTile) {
		while(inputController.wantRotate()) {
			currentTile = currentGame.rotateTile(currentColor);
			displayer.showGameState();
		}
		return currentTile;
	}

	private ModelTile drawTile(Color currentColor) {
		ModelTile currentTile;
		do {
			currentTile = currentGame.drawTile(currentColor);
		} while(!currentGame.isPlaceableWithRotations(currentTile));
		return currentTile;
	}
} 