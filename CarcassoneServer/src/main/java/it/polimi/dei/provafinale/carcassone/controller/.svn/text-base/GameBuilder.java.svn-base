package it.polimi.dei.provafinale.carcassone.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.TimerTask;
import java.util.logging.Logger;

/**
 * This class build the game once received the string "connect".
 * Also add each client which connects to server.
 * @author Matteo Danelli
 *
 */
public class GameBuilder extends TimerTask{

	private static final String CONNECT = "connect";
	private static final String GAME = "game";

	private List<SocketWrapper> clientsList;
	private static int currentGameID = 0;

	/**
	 * Initialize the game with a socketWrapper.
	 */
	public GameBuilder() {
		clientsList = new ArrayList<SocketWrapper>();
	}

	/**
	 * Add to server a connection
	 * @param connection connection between server and client.
	 * @return true if the connection was added, else false.
	 */
	public boolean addConnection(SocketWrapper connection) {
		try {
			String message = connection.readMessage();
			if(message.equals(CONNECT)) {
				clientsList.add(connection);
				return true;
			}
			else {
				// TODO Manage reconnection
				throw new NoSuchElementException();
			}
		}
		catch (NoSuchElementException e) {
			Logger.getAnonymousLogger().warning(e.getLocalizedMessage());
			try {
				connection.closeConnection();
			} catch (IOException e1) {
				Logger.getAnonymousLogger().warning(e.getLocalizedMessage());
			}
		}
		return false;
	}

	/**
	 * Return the number of clients connected.
	 * @return number of clients connected.
	 */
	public int listSize() {
		return clientsList.size();
	}

	/**
	 * Create a thread of the current game with the player who play.
	 */
	@Override
	public void run() {
		try {
			currentGameID++;
			String gameName = GAME + currentGameID;
			Thread newGame = new Thread(new GameController(gameName, clientsList),gameName);
			newGame.start();
			clientsList = new ArrayList<SocketWrapper>();
		}
		catch (IllegalArgumentException e) {
			Logger.getAnonymousLogger().warning(e.getLocalizedMessage());
		}
		catch (IOException e) {
			Logger.getAnonymousLogger().warning(e.getLocalizedMessage());
		}

	}

}
