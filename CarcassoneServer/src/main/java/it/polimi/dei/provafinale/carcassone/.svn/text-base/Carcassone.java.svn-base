package it.polimi.dei.provafinale.carcassone;

import java.io.IOException;

import it.polimi.dei.provafinale.carcassone.controller.ControllerFactory;

/**
 * Main class of the game. Launch the game in different mode based of its parameters.
 * @author Matteo Danelli
 *
 */
public class Carcassone {

	private static final int THIRDPARAMETER = 2;
	private static final int SECONDPARAMETER = 1;
	private static final int FIRSTPARAMETER = 0;
	private static final int ONE = 1;
	private static final int TWO = 2;
	private static final int THREE = 3;
	private static final int SUCCESS = 0;
	private static final int ERROR = -1;

	/**
	 * Create a different view based on the parameters passed.
	 * It can create a textual game or a server side of a network game.
	 * After, launch the game.
	 * @param args	arguments passed from command line
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	public static void main(String[] args) {
		ControllerFactory selectedController;
		String version = null;
		String port = null;
		String ip = null;
		int parametersNumber = args.length;
		try{
			switch(parametersNumber){
			case ONE: {
				version = args[FIRSTPARAMETER];
			}
			break;
			case TWO: {
				version = args[FIRSTPARAMETER];
				port = args[SECONDPARAMETER];
			}
			break;
			case THREE: {
				version = args[FIRSTPARAMETER];
				ip = args[SECONDPARAMETER];
				port = args[THIRDPARAMETER];
			}
			break;
			default: {
				System.err.println("Unable to launch. Invalid number of parameters.");
				System.exit(ERROR);
			}
			}
			selectedController = ControllerFactory.getController(version, ip, port);
			selectedController.start();
			System.exit(SUCCESS);
		}
		catch(IllegalArgumentException e){
			e.printStackTrace();
			System.exit(ERROR);
		}
		catch(IOException e) {
			e.printStackTrace();
			System.exit(ERROR);
		}
	}

}
