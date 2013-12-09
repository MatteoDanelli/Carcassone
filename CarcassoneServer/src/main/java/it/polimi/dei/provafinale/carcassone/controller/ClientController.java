package it.polimi.dei.provafinale.carcassone.controller;

import it.polimi.dei.provafinale.carcassone.model.CardinalPosition;
import it.polimi.dei.provafinale.carcassone.model.Color;
import it.polimi.dei.provafinale.carcassone.model.Position;
import it.polimi.dei.provafinale.carcassone.view.TextualView;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.util.NoSuchElementException;

/**
 * This class is the client controller. 
 * It do the connections of server,
 * read player message and sends it to server.
 * Also manages the output on player console.
 * @author Canidio Andrea
 *
 */
public class ClientController extends ControllerFactory {

	private SocketWrapper socket;
	private MessageGenerator generator;
	private InputController inputController;
	private GameDisplayer displayer;
	private Color myColor, currentTurnColor;
	private MessageParser parser;

	private PrintStream systemOut = System.out;

	/**
	 * Initialize a client connecting to server ip and server port.
	 * @param ip server ip to connect.
	 * @param port port of the server opened.
	 * @throws IOException if the connection is invalid.
	 */
	public ClientController(InetAddress ip, int port) throws IOException {
		TextualView view = new TextualView();
		displayer = new GameDisplayer(view);
		try {
			socket = new SocketWrapper(ip, port);
		} catch (IOException e) {
			displayer.connectionRefused();
			return;
		}
		inputController = new InputController(view);
		generator = new MessageGenerator();
		parser = new MessageParser();
		parser.addObserver(displayer);
	}

	/**
	 * Read the user input, parse this input and play the game.
	 */
	@Override
	public void start() throws IOException {
		if(socket == null) {
			return;
		}
		String message = generator.generateConnect();
		socket.sendMessage(message);
		try {
			message = socket.readMessage();
			parser.parseMessage(message);
			String gameName = parser.getGameName();
			myColor = parser.getMyColor();
			systemOut.println("Connected to " + gameName);
			systemOut.println("My color is " + myColor);
			playGame();
		} catch (IllegalArgumentException e) {
			throw new IOException("Invalid request", e);
		} finally {
			socket.closeConnection();
		}
	}

	private void playGame() {
		boolean gameFinished = false;
		do {
			try {
				gameFinished = playTurn();
			} catch (NoSuchElementException e) {
				gameFinished = true;
			} 
		} while(!gameFinished);
		displayer.showGameState();
		displayer.showFinalPoint();
	}

	private boolean playTurn() {
		turnPhase();
		drawPhase();
		placePhase();
		return flagPhase();
	}

	private boolean flagPhase() {
		String command = MessageParser.MOVE_NOT_VALID;
		do {
			if(currentTurnColor.equals(myColor) && command.equals(MessageParser.MOVE_NOT_VALID)) {
				myFlagPhase();
			}
			String message = socket.readMessage();
			command = parser.parseMessage(message);
			if(command.equals(MessageParser.LOCK)) {
				reconnectionPhase();
			}
		} while(!command.equals(MessageParser.SCORE)&&!command.equals(MessageParser.END));
		displayer.showGameState();
		if(command.equals(MessageParser.END)) {
			return true;
		}
		return false;
	}

	private void reconnectionPhase() {
		String command;
		do {
			String message = socket.readMessage();
			command = parser.parseMessage(message);
		} while(!command.equals(MessageParser.LEAVE));
	}

	private void myFlagPhase() {
		if(inputController.wantPlaceFlag()) {
			CardinalPosition side = inputController.wherePlaceFlag();
			String message = generator.generateTile(side);
			socket.sendMessage(message);
		}
		else {
			String message = generator.generatePass();
			socket.sendMessage(message);
		}
	}

	private void placePhase() {
		String command;
		do {
			if(currentTurnColor.equals(myColor)) {
				myPlacePhase();
			}
			String message = socket.readMessage();
			command = parser.parseMessage(message);
			if(command.equals(MessageParser.MOVE_NOT_VALID)) {
				displayer.tileNotPlaceable();
			}
			else if(command.equals(MessageParser.LOCK)) {
				reconnectionPhase();
			}
			else {
				displayer.showGameState();
			}
		} while(!command.equals(MessageParser.UPDATE));
	}

	private void myPlacePhase() {
		String message;
		while(inputController.wantRotate()) {
			message = generator.generateRotate();
			socket.sendMessage(message);
			message = socket.readMessage();
			parser.parseMessage(message);
			displayer.showGameState();
		}
		Position toPlace = inputController.wherePlaceTile();
		message = generator.generatePlace(toPlace);
		socket.sendMessage(message);
	}

	private void drawPhase() {
		String message = socket.readMessage();
		parser.parseMessage(message);
		displayer.showGameState();
	}

	private void turnPhase() {
		String message = socket.readMessage();
		parser.parseMessage(message);
		currentTurnColor = parser.getCurrentColor();
	}
}
