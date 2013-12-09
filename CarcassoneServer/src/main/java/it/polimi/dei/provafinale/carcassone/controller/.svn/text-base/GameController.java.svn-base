package it.polimi.dei.provafinale.carcassone.controller;

import it.polimi.dei.provafinale.carcassone.model.CardinalPosition;
import it.polimi.dei.provafinale.carcassone.model.Color;
import it.polimi.dei.provafinale.carcassone.model.DisplayedTile;
import it.polimi.dei.provafinale.carcassone.model.Game;
import it.polimi.dei.provafinale.carcassone.model.ModelPlayer;
import it.polimi.dei.provafinale.carcassone.model.PlacedTile;
import it.polimi.dei.provafinale.carcassone.model.Position;
import it.polimi.dei.provafinale.carcassone.model.ModelTile;

import java.io.IOException;
import java.io.PrintStream;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Observer;
import java.util.StringTokenizer;
import java.util.logging.Logger;

/**
 * This class manage all the game using the pattern observer/observable.
 * @author Andrea Canidio, Matteo Danelli
 *
 */
public class GameController implements Runnable, Observer{

	//constants
	private static final int MINPLAYABLEPLAYERS = 1;
	private static final int INITIALCHAR = 0;

	//commands
	private static final String TILE = "tile";
	private static final String PASS = "pass";
	private static final String ENDSTRING = "\n";
	private static final String PATTERN = ":,\n";
	private static final String PLACE = "place";
	private static final String ROTATE = "rotate";

	private PrintStream systemOut = System.out;

	private String gameName;
	private Map<Color, SocketWrapper> clientList;
	private Game currentGame;
	private MessageGenerator messageGenerator;

	/**
	 * Initialize a game with a progressive name and a list of client.
	 * @param gameName game1, game2, ..
	 * @param clientsList list of client who want play.
	 * @throws IllegalArgumentException 
	 * @throws IOException
	 */
	public GameController(String gameName, List<SocketWrapper> clientsList) throws IllegalArgumentException, IOException {
		this.gameName = gameName;
		this.clientList = new HashMap<Color, SocketWrapper>();
		currentGame = new Game();
		currentGame.addObserver(this);
		messageGenerator = new MessageGenerator();
		int playersCounter = 0;
		for(SocketWrapper currentSocket: clientsList) {
			Color currentColor = Color.getColorFrom(playersCounter);
			this.clientList.put(currentColor, currentSocket);
			currentGame.createPlayer();
			playersCounter++;
		}
		currentGame.initializeGame();
		currentGame.shuffleDeck();
	}


	/**
	 * Before starting the game print a message.
	 */
	@Override
	public void run() {
		systemOut.println(gameName + " initialized");
		systemOut.println(clientList.size() + " players");
		sendStarts();
		try {
			playGame();
		} catch (Exception e) {
			Logger.getAnonymousLogger().warning(e.getLocalizedMessage());
		} finally {
			closeSockets();
		}
	}

	/**
	 * Update the observable object.
	 */
	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof PlacedTile) {
			Position modifiedPosition = ((PlacedTile)arg).getPosition();
			DisplayedTile modifiedTile = ((PlacedTile)arg).getTile();
			if(!modifiedPosition.equals(new Position(0, 0))) {
				sendUpdate(modifiedPosition, modifiedTile);
			}
		}
	}

	private void sendStart(Color currentColor, SocketWrapper socket) {
		ModelTile startTile = currentGame.getPlacedTile(new Position(0, 0));
		int playersNumber = currentGame.getPlayers().size();
		String message = messageGenerator.generateStart(startTile ,gameName, currentColor, playersNumber);
		socket.sendMessage(message);
	}

	private ModelTile drawTile(Color currentColor) {
		ModelTile currentTile;
		do {
			currentTile = currentGame.drawTile(currentColor);
		} while(!currentGame.isPlaceableWithRotations(currentTile));
		return currentTile;
	}

	private void closeSockets() {
		for(SocketWrapper currentSocket: clientList.values()) {
			try {
				currentSocket.closeConnection();
			} catch (IOException e) {
				Logger.getAnonymousLogger().warning(e.getLocalizedMessage());
			}
		}
	}


	private void sendTurn() {
		Color currentTurn = currentGame.getTurnPlayer();
		String message = messageGenerator.generateTurn(currentTurn);
		sendToAll(message);
	}

	private void playGame() {
		while(!currentGame.isLastTurn() && hasMoreThanOnePlayer()) {
			Color currentColor = currentGame.getTurnPlayer();
			playTurn(currentColor);
			currentGame.nextTurn();
		}
		currentGame.calculateFinalScores();
		sendFinalScores();
	}

	private boolean hasMoreThanOnePlayer() {
		return currentGame.getPlayers().size() > MINPLAYABLEPLAYERS;
	}

	private void sendFinalScores() {
		List<ModelPlayer> players = currentGame.getPlayers();
		String message = messageGenerator.generateFinalScore(players);
		sendToAll(message);
	}

	private void sendToAll(String message) {
		for(SocketWrapper currentSocket: clientList.values()) {
			currentSocket.sendMessage(message);
		}
	}

	private void playTurn(Color currentTurn) {
		sendTurn();
		ModelTile drawedTile = drawTile(currentTurn);
		sendNext(drawedTile);
		Position placed = null;
		try {
			placed = placePhase(currentTurn);
			flagPhase(currentTurn, placed);
		} catch (SocketTimeoutException e) {
			currentGame.removePlayer(currentTurn);
			sendLeave(currentTurn);
		}
		if(placed != null) {
			currentGame.calculateScore(placed);
		}
		if(hasMoreThanOnePlayer() && !currentGame.isLastTurn()) {
			sendScores();
		}
	}

	private void sendLeave(Color currentTurn) {
		String message = messageGenerator.generateLeave(currentTurn);
		sendToAll(message);
	}

	private void sendScores() {
		List<ModelPlayer> players = currentGame.getPlayers();
		String message = messageGenerator.generateScore(players);
		sendToAll(message);
	}

	private void flagPhase(Color currentTurn, Position placed) throws SocketTimeoutException {
		boolean proceed;
		do {
			proceed = false;
			try {
				String receiveMessage = readMessageFrom(currentTurn);
				proceed = parseFlagMessage(receiveMessage, currentTurn, placed);
			}
			catch (NoSuchElementException e) {
				waitReconnection(currentTurn);
			}
		} while(!proceed);
	}

	private boolean parseFlagMessage(String receiveMessage, Color currentTurn, Position placed) {
		if(receiveMessage.equals(PASS)) {
			return true;
		}
		boolean returnDelims = true;
		StringTokenizer cutter = new StringTokenizer(receiveMessage, PATTERN, returnDelims);
		if(cutter.hasMoreTokens()) {
			String command = cutter.nextToken();
			if(!command.equals(TILE)) {
				sendMoveNotValid();
				return false;
			}		
		}
		cutter.nextToken();
		try {
			char sideToRecognize = cutter.nextToken(ENDSTRING).charAt(INITIALCHAR);
			CardinalPosition side = CardinalPosition.recognizePositionCode(sideToRecognize);
			ModelPlayer currentPlayer = currentGame.selectPlayer(currentTurn);
			if(currentGame.canPlaceFlag(placed, side) && currentPlayer.hasMoreFlags()) {
				currentGame.insertFlag(placed, side, currentTurn);
				ModelTile flagTile = currentGame.getPlacedTile(placed);
				sendUpdate(placed, flagTile.display());
				return true;
			}
		} catch (IllegalArgumentException e) {
			// Nothing to do because it's automatically managed by the next lines
		}
		sendMoveNotValid();
		return false;
	}

	private void waitReconnection(Color currentTurn) throws SocketTimeoutException{
		sendLock();
		throw new SocketTimeoutException(currentTurn + " disconnected");
	}

	private void sendLock() {
		String message = messageGenerator.generateLock();
		sendToAll(message);
	}

	private Position placePhase(Color currentTurn) throws SocketTimeoutException {
		Position placed;
		do {
			placed = null;
			try {
				String receivedMessage = readMessageFrom(currentTurn);
				placed = parseTileMessage(receivedMessage,currentTurn);
			}
			catch (NoSuchElementException e) {
				waitReconnection(currentTurn);
			}
		} while(placed == null);
		return placed;
	}

	private Position parseTileMessage(String receivedMessage, Color currentTurn) {
		if(receivedMessage.equals(ROTATE)) {
			rotate(currentTurn);
			return null;
		}
		// I want the delimiter character returned with the token
		boolean returnDelims = true;
		StringTokenizer cutter = new StringTokenizer(receivedMessage,PATTERN,returnDelims);
		if(cutter.hasMoreTokens()) {
			String command = cutter.nextToken();
			if(!command.equals(PLACE)) {
				sendMoveNotValid();
				return null;
			}
		}
		cutter.nextToken();
		String positionPattern = cutter.nextToken(ENDSTRING);
		Position targetPosition;
		try {
			targetPosition = new Position(positionPattern);
		} catch (IllegalArgumentException e) {
			sendMoveNotValid();
			return null;
		}
		return placeTile(targetPosition);
	}

	private Position placeTile(Position targetPosition) {
		if(currentGame.isAvailable(targetPosition)) {
			Color currentColor = currentGame.getTurnPlayer();
			ModelPlayer currentPlayer = currentGame.selectPlayer(currentColor);
			ModelTile tileToPlace = currentPlayer.getCurrentTile();
			if(currentGame.isPlaceableHere(tileToPlace, targetPosition)) {
				currentGame.putTileInPosition(currentColor, targetPosition);
				sendUpdate(targetPosition, tileToPlace.display());
				return targetPosition;
			}
		}
		sendMoveNotValid();
		return null;
	}

	private void sendMoveNotValid() {
		String message = messageGenerator.generateMoveNotValid();
		sendToAll(message);
	}

	private void sendUpdate(Position targetPosition, DisplayedTile modifiedTile) {
		String message = messageGenerator.generateUpdate(modifiedTile, targetPosition);
		sendToAll(message);
	}

	private void rotate(Color currentTurn) {
		ModelTile rotatedTile = currentGame.rotateTile(currentTurn);
		String message = messageGenerator.generateRotated(rotatedTile);
		sendToAll(message);
	}

	private String readMessageFrom(Color currentPlayer) throws NoSuchElementException{
		SocketWrapper currentSocket = clientList.get(currentPlayer);
		return currentSocket.readMessage();
	}

	private void sendNext(ModelTile drawedTile) {
		String message = messageGenerator.generateNext(drawedTile);
		sendToAll(message);
	}

	private void sendStarts() {
		for(Color currentColor: clientList.keySet()) {
			SocketWrapper currentSocket = clientList.get(currentColor);
			sendStart(currentColor, currentSocket);
		}
	}
}
