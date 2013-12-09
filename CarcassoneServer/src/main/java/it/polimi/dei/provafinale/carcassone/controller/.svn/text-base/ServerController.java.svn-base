package it.polimi.dei.provafinale.carcassone.controller;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.logging.Logger;

/**
 * This class manages the controller server-side.
 * @author Andrea Canidio
 *
 */
public class ServerController extends ControllerFactory implements Observer{

	private static final int TIMEOUT = 30;
	private static final int MILLISECONDS = 1000;
	private static final int MINPLAYERS = 2;
	private static final int MAXPLAYERS = 5;

	private int port;
	private Timer timer;
	private GameBuilder builder;

	/**
	 * Initialize the server on the port specified and build a game.
	 * @param port port of the server.
	 */
	public ServerController(int port){
		this.port = port;
		builder = new GameBuilder();
	}

	/**
	 * Create a thread for every game.
	 */
	@Override
	public void start() {
		try {
			Thread handler = new Thread(new ConnectionHandler(port, this));
			handler.start();
			handler.join();
		} catch (IOException e) {
			Logger.getAnonymousLogger().warning(e.getLocalizedMessage());
		} catch (InterruptedException e) {
			Logger.getAnonymousLogger().warning(e.getLocalizedMessage());
		}
	}

	/**
	 * Listener of and observer object.
	 */
	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof SocketWrapper) {
			if(builder.listSize() == 0) {
				builder = new GameBuilder();
			}
			SocketWrapper receivedSocket = (SocketWrapper) arg;
			if(builder.addConnection(receivedSocket)) {
				if(builder.listSize() == MINPLAYERS) {
					timer = new Timer();
					timer.schedule(builder, TIMEOUT * MILLISECONDS);
				}
				else if(builder.listSize() == MAXPLAYERS) {
					builder.run();
					timer.cancel();
				}
			}
		}
	}
}
