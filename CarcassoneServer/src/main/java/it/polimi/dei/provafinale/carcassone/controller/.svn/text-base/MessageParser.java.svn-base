package it.polimi.dei.provafinale.carcassone.controller;

import it.polimi.dei.provafinale.carcassone.model.Color;
import it.polimi.dei.provafinale.carcassone.model.DisplayedPlayer;
import it.polimi.dei.provafinale.carcassone.model.DisplayedTile;
import it.polimi.dei.provafinale.carcassone.model.PlacedTile;
import it.polimi.dei.provafinale.carcassone.model.Position;
import it.polimi.dei.provafinale.carcassone.model.RemovedPlayer;

import java.util.IllegalFormatException;
import java.util.IllegalFormatFlagsException;
import java.util.Observable;
import java.util.StringTokenizer;

/**
 * @author Andrea Canidio
 * 
 */
public class MessageParser extends Observable {

	private static final String EQUAL = "=";

	public static final String END = "end";

	public static final String NEXT = "next";

	public static final String LEAVE = "leave";

	public static final String LOCK = "lock";

	public static final String SCORE = "score";

	public static final String UPDATE = "update";

	public static final String MOVE_NOT_VALID = "move not valid";

	public static final String ROTATED = "rotated";

	public static final String TURN = "turn";

	public static final String START = "start";

	private static final String ENDSTRING = "\n";

	private static final String COMMA = ",";

	private static final String COLON = ":";

	private String gameName;

	private Color myColor;

	private Color currentTurn;

	public String getGameName() {
		return gameName;
	}

	public Color getMyColor() {
		return myColor;
	}

	public Color getCurrentColor() {
		return currentTurn;
	}

	@Override
	public void notifyObservers(Object arg) {
		if (countObservers() != 0) {
			setChanged();
			super.notifyObservers(arg);
		}
	}

	/**
	 * This method parse one of the specified commands in the protocol and
	 * provide to notify the arguments to the registered Observers, if any.
	 * 
	 * 
	 * @param message The message to parse.
	 * @return The understood command.
	 * @throws IllegalArgumentException If the command is not well formed or is specified a not understood 
	 */
	public String parseMessage(String message) throws IllegalArgumentException {
		if (message.startsWith(START)) {
			parseStartMessage(message);
			return START;
		}
		if (message.startsWith(TURN)) {
			parseTurnMessage(message);
			return TURN;
		}
		if (message.startsWith(NEXT)) {
			parseNextMessage(message);
			return NEXT;
		}
		if (message.startsWith(ROTATED)) {
			parseRotatedMessage(message);
			return ROTATED;
		}
		if (message.startsWith(UPDATE)) {
			parseUpdateMessage(message);
			return UPDATE;
		}
		if (message.equals(MOVE_NOT_VALID)) {
			return MOVE_NOT_VALID;
		}
		if (message.startsWith(SCORE)) {
			parseScoreMessage(message);
			return SCORE;
		}
		if (message.equals(LOCK)) {
			return LOCK;
		}
		if (message.startsWith(LEAVE)) {
			parseLeaveMessage(message);
			return LEAVE;
		}
		if (message.startsWith(END)) {
			parseEndMessage(message);
			return END;
		}
		throw new IllegalArgumentException("Command not correct");
	}

	private void parseEndMessage(String message)
			throws IllegalArgumentException {
		try {
			StringTokenizer cutter = parseCommand(message, END);
			parseScores(cutter.nextToken(ENDSTRING));
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(message, e);
		}
	}

	private void parseLeaveMessage(String message)
			throws IllegalArgumentException {
		try {
			StringTokenizer cutter = parseCommand(message, LEAVE);
			String colorPattern = cutter.nextToken(ENDSTRING);
			Color leaveColor = Color.recognizeColorName(colorPattern);
			notifyObservers(new RemovedPlayer(leaveColor));
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(message, e);
		}
	}

	private void parseScoreMessage(String message)
			throws IllegalArgumentException {
		try {
			StringTokenizer cutter = parseCommand(message, SCORE);
			parseScores(cutter.nextToken(ENDSTRING));
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(message, e);
		}

	}

	private StringTokenizer parseCommand(String message, String chosenCommand)
			throws IllegalFormatException{
		boolean returnDelims = true;
		StringTokenizer cutter = new StringTokenizer(message, COLON,
				returnDelims);
		if (!cutter.hasMoreTokens()) {
			throw new IllegalFormatFlagsException("Invalid " + chosenCommand + " format");
		}
		cutter.nextToken();
		cutter.nextToken();
		return cutter;
	}

	private void parseScores(String score) {
		StringTokenizer cutter = new StringTokenizer(score, COMMA + ENDSTRING);
		try {
			do {
				String player = cutter.nextToken();
				parsePlayer(player);
			} while (cutter.hasMoreTokens());
		} catch (RuntimeException e) {
			throw new IllegalArgumentException(score, e);
		}
	}

	private void parsePlayer(String player) {
		boolean returnDelims = true;
		StringTokenizer cutter = new StringTokenizer(player, EQUAL,
				returnDelims);
		String colorPattern = cutter.nextToken();
		if (!cutter.nextToken().equals(EQUAL)) {
			throw new IllegalFormatFlagsException(player);
		}
		int points = Integer.parseInt(cutter.nextToken());
		Color flagColor = Color.recognizeColorName(colorPattern);
		if (countObservers() != 0) {
			setChanged();
			notifyObservers(new DisplayedPlayer(flagColor, points, 0));
		}
	}

	private void parseStartMessage(String message)
			throws IllegalArgumentException {
		try {
			StringTokenizer cutter = parseCommand(message, START);
			String tilePattern = cutter.nextToken(COMMA);
			cutter.nextToken();
			gameName = cutter.nextToken();
			cutter.nextToken();
			String color = cutter.nextToken();
			cutter.nextToken();
			String players = cutter.nextToken(ENDSTRING);
			myColor = Color.recognizeColorName(color);
			Integer playersNumber = Integer.valueOf(players);
			Position startPosition = new Position(0, 0);
			DisplayedTile startTile = new DisplayedTile(tilePattern);
			notifyObservers(new PlacedTile(startPosition, startTile));
			notifyObservers(playersNumber);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(message, e);
		}
	}

	private void parseTurnMessage(String message)
			throws IllegalArgumentException {
		try {
			StringTokenizer cutter = parseCommand(message, TURN);
			String turn = cutter.nextToken(ENDSTRING);
			currentTurn = Color.recognizeColorName(turn);
			notifyObservers(currentTurn);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(message, e);
		}
	}

	private void parseNextMessage(String message)
			throws IllegalArgumentException {
		try {
			StringTokenizer cutter = parseCommand(message, NEXT);
			String tile = cutter.nextToken(ENDSTRING);
			DisplayedTile drawedTile = new DisplayedTile(tile);
			notifyObservers(drawedTile);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(message, e);
		}
	}

	private void parseRotatedMessage(String message)
			throws IllegalArgumentException {
		try {
			StringTokenizer cutter = parseCommand(message, ROTATED);
			String tile = cutter.nextToken(ENDSTRING);
			DisplayedTile rotatedTile = new DisplayedTile(tile);
			notifyObservers(rotatedTile);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(message, e);
		}
	}

	private void parseUpdateMessage(String message)
			throws IllegalArgumentException {
		try {
			StringTokenizer cutter = parseCommand(message, UPDATE);
			String tile = cutter.nextToken(COMMA);
			cutter.nextToken();
			String position = cutter.nextToken(ENDSTRING);
			DisplayedTile placedTile = new DisplayedTile(tile);
			Position toPlace = new Position(position);
			notifyObservers(new PlacedTile(toPlace, placedTile));
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(message, e);
		}
	}
}
