package it.polimi.dei.provafinale.carcassone.controller;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;

/**
 * This class manages the connection between client and server.
 * For each client we create a server socket.
 * 
 * @author Matteo Danelli
 *
 */
public class ConnectionHandler extends Observable implements Runnable {

	private PrintStream systemOut = System.out;
	private ServerSocket clientReceiver;

	/**
	 * Initialize a server socket on the port passed and add an observer.
	 * @param port port of server.
	 * @param server observer to register.
	 * @throws IOException if it's impossible open the port on server.
	 */
	public ConnectionHandler(int port, Observer server) throws IOException {
		clientReceiver = new ServerSocket(port);
		addObserver(server);
	}

	/**
	 * Launch the server thread. 
	 * Wait for players with an accept.
	 */
	@Override
	public void run() {
		systemOut.println("Waiting for players");
		while(true) {
			try {
				Socket connection = clientReceiver.accept();
				setChanged();
				notifyObservers(new SocketWrapper(connection));
			} catch (IOException e) {
				Logger.getAnonymousLogger().warning(e.getLocalizedMessage());
			}
		}
	}

}
