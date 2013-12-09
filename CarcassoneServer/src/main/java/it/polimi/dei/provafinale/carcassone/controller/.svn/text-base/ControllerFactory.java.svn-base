package it.polimi.dei.provafinale.carcassone.controller;


import java.io.IOException;
import java.net.InetAddress;

/**
 * This class manages only the type of game which must be launch.
 * The three possibilities are: textual, server side or client side.
 * @author Andrea Canidio
 *
 */
public abstract class ControllerFactory {

	/**
	 * This class gets the correct controller reading the parameter.
	 * @param version textual/server/,client
	 * @param ipPattern ip address
	 * @param portPatterns port of server
	 * @return	
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	public static ControllerFactory getController(String version, String ipPattern, String portPatterns) throws IllegalArgumentException, IOException{
		if(version.equalsIgnoreCase("-textual")){
			return new TextualController();
		}
		else if(version.equalsIgnoreCase("-server")){
			int port = Integer.parseInt(portPatterns);
			return new ServerController(port);
		}
		else if(version.equalsIgnoreCase("-client")){
			InetAddress ip = InetAddress.getByName(ipPattern);
			int port = Integer.parseInt(portPatterns);
			return new ClientController(ip, port);
		}
		throw new IllegalArgumentException("Unable to create Controller. Check the -version parameter.");
	}

	/**
	 * Signature of start method. 
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	public abstract void start() throws IllegalArgumentException, IOException;
}
