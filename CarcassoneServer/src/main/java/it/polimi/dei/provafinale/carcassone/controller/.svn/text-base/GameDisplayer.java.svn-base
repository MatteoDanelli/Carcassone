package it.polimi.dei.provafinale.carcassone.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import it.polimi.dei.provafinale.carcassone.model.Color;
import it.polimi.dei.provafinale.carcassone.model.DisplayedMap;
import it.polimi.dei.provafinale.carcassone.model.DisplayedPlayer;
import it.polimi.dei.provafinale.carcassone.model.DisplayedTile;
import it.polimi.dei.provafinale.carcassone.model.PlacedTile;
import it.polimi.dei.provafinale.carcassone.model.Position;
import it.polimi.dei.provafinale.carcassone.model.RemovedPlayer;
import it.polimi.dei.provafinale.carcassone.view.TextualView;

/**
 * This class manages all the view of the game. 
 * It uses patter observer/observable and uses it to do update.
 * @author Canidio Andrea
 *
 */

public class GameDisplayer implements Observer {
	
	//constants 
	private static final int MINAVAILABLEFLAGS = 0;
	private static final int MAXAVAILABLEFLAGS = 7;

	private TextualView view;
	private DisplayedMap currentMap;
	private DisplayedTile currentTile;
	private List<DisplayedPlayer> players;
	private Color currentTurn;
	
	/**
	 * Initialize the gameDisplayer from the view passed as parameter.
	 * @param view view passed: textual/client
	 */
	public GameDisplayer(TextualView view) {
		this.view = view;
		players = new ArrayList<DisplayedPlayer>();
		currentMap = new DisplayedMap();
		currentTurn = Color.RED;
	}
	
	/**
	 * Show final points of all players.
	 */
	public void showFinalPoint() {
		view.showFinalPoint(players);
	}
	
	/**
	 * Show some informations about the actual state of game.
	 */
	public void showGameState() {
		DisplayedPlayer currentPlayer = null;
		for(DisplayedPlayer selectedPlayer: players) {
			if(selectedPlayer.getFlagColor() == currentTurn) {
				currentPlayer = selectedPlayer;
				break;
			}
		}
		if(currentPlayer != null) {
			view.showGameState(currentPlayer, currentMap, currentTile);
		}
	}
	
	/**
	 * Show a warning when tile isn't placeable.
	 */
	public void tileNotPlaceable() {
		view.tileNotPlaceable();
	}
	
	/**
	 * Show a warning when flag isn't placeable.
	 */
	public void flagNotPlaceable() {
		view.flagNotPlaceable();
	}

	/**
	 * Show a warning when position isn't suitable.
	 */
	public void positionNotSuitable(Position currentPosition) {
		view.positionNotSuitable(currentPosition);
	}

	/**
	 * Show a warning when the connection was refused for some reasons.
	 */
	public void connectionRefused() {
		view.connectionRefused();
	}
	
	/**
	 * Show information about the connection of the player in that game.
	 * @param gameName name of the game.
	 * @param myColor player to select.
	 */
	public void showConnectionInformation(String gameName, Color myColor) {
		view.showConnectionInformation(gameName, myColor);
	}

	/**
	 * Manages the update of the observable
	 */
	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof PlacedTile) {
			PlacedTile modifiedTile = (PlacedTile) arg;
			updateGameState(modifiedTile);
			return;
		}
		if(arg instanceof Color) {
			Color currentTurn = (Color) arg;
			this.currentTurn = currentTurn;
			return;
		}
		if(arg instanceof RemovedPlayer) {
			RemovedPlayer thisOne = (RemovedPlayer) arg;
			remove(thisOne);
			return;
		}
		if(arg instanceof DisplayedPlayer) {
			managePlayer((DisplayedPlayer)arg);
			return;
		}
		if(arg instanceof DisplayedTile) {
			DisplayedTile currentTile = (DisplayedTile) arg;
			this.currentTile = currentTile;
			return;
		}
		if(arg instanceof Integer) {
			Integer playersNumber = (Integer) arg;
			for(int i = 0; i < playersNumber; i++) {
				players.add(new DisplayedPlayer(Color.getColorFrom(i), MINAVAILABLEFLAGS, MAXAVAILABLEFLAGS));
			}
		}
	}

	//Remove player from the player which are playing.
	private void remove(RemovedPlayer thisOne) {
		for(int i = 0; i < players.size(); i++) {
			if(players.get(i).getFlagColor() == thisOne.getFlagColor()) {
				players.remove(i);
				return;
			}
		}
	}

	private void updateGameState(PlacedTile modifiedTile) {
		Position position = modifiedTile.getPosition();
		DisplayedTile currentTile = modifiedTile.getTile();
		if(currentMap.thereIsTileIn(position)) {
			DisplayedTile previousTile = currentMap.getTileFromPosition(position);
			updatePlayer(currentTile, previousTile);
		}
		currentMap.putTileInPosition(position, currentTile);
	}

	private void updatePlayer(DisplayedTile currentTile, DisplayedTile previousTile) {
		if(previousTile.hasFlag() && !currentTile.hasFlag()) {
			Color currentFlag = previousTile.getFlag();
			DisplayedPlayer currentPlayer = selectPlayer(currentFlag);
			currentPlayer.receiveFlag();
		}
		else if(!previousTile.hasFlag() && currentTile.hasFlag()) {
			Color currentFlag = currentTile.getFlag();
			DisplayedPlayer currentPlayer = selectPlayer(currentFlag);
			currentPlayer.placeFlag();
		}
	}
	
	private DisplayedPlayer selectPlayer(Color currentColor) {
		for(DisplayedPlayer currentPlayer: players) {
			if(currentPlayer.getFlagColor() == currentColor) {
				return currentPlayer;
			}
		}
		return null;
	}

	private void managePlayer(DisplayedPlayer modifiedPlayer) {
		boolean found = substitutePlayer(modifiedPlayer);
		if(!found) {
			players.add(modifiedPlayer);
		}
	}

	private boolean substitutePlayer(DisplayedPlayer newPlayer) {
		boolean found = false;
		for(int i = 0; i < players.size() && !found; i++) {
			if(newPlayer.getFlagColor() == players.get(i).getFlagColor()) {
				found = true;
				int points = newPlayer.getPoints();
				players.get(i).setPoints(points);
			}
		}
		return found;
	}
}
