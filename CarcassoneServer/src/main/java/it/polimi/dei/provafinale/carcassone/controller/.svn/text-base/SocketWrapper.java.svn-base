package it.polimi.dei.provafinale.carcassone.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class manages the socket.
 * Contains method to write and read on the socket. 
 * @author Matteo Danelli
 *
 */

public class SocketWrapper {
	
	private Socket currentSocket;
	private Scanner socketIn;
	private PrintWriter socketOut;
	
	/**
	 * Initialize the class with the socket passed.
	 * @param currentSocket socket to manage.
	 * @throws IOException if there are some error on input/output stream.
	 */
	public SocketWrapper(Socket currentSocket) throws IOException {
		this.currentSocket = currentSocket;
		socketIn = new Scanner(currentSocket.getInputStream());
		socketOut = new PrintWriter(currentSocket.getOutputStream());
	}
	
	/**
	 * Initialize the class with an ip address and a port.
	 * @param address ip address
	 * @param port port to connect
	 * @throws IOException if there are some error on input/output stream.
	 */
	public SocketWrapper(InetAddress address, int port) throws IOException {
		currentSocket = new Socket(address, port);
		socketIn = new Scanner(currentSocket.getInputStream());
		socketOut = new PrintWriter(currentSocket.getOutputStream());
	}
	
	/**
	 * Read one line from the socket.
	 * @return the string read-
	 * @throws NoSuchElementException if there are nothing written on the socket.
	 */
	public String readMessage() throws NoSuchElementException{
		return socketIn.nextLine();
	}
	
	/**
	 * Write a message on the socket.
	 * @param message message to write.
	 */
	public void sendMessage(String message) {
		socketOut.println(message);
		socketOut.flush();
	}
	
	/**
	 * Close the socket connection.
	 * @throws IOException if there are errors closing the socket.
	 */
	public void closeConnection() throws IOException {
		socketIn.close();
		socketOut.close();
		currentSocket.close();
	}
}
