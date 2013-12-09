package it.polimi.dei.provafinale.carcassone.controller;

import java.util.List;
import it.polimi.dei.provafinale.carcassone.model.CardinalPosition;
import it.polimi.dei.provafinale.carcassone.model.Color;
import it.polimi.dei.provafinale.carcassone.model.DisplayedTile;
import it.polimi.dei.provafinale.carcassone.model.ModelPlayer;
import it.polimi.dei.provafinale.carcassone.model.Position;
import it.polimi.dei.provafinale.carcassone.model.ModelTile;

/**
 * This class is the generator of message of the protocol between server and client.
 * 
 * @author Matteo Danelli
 *
 */

public class MessageGenerator {
	
	//All messages
	private static final String SCORE = "score:";
	private static final String TILE = "tile:";
	private static final String PASS = "pass";
	private static final String PLACE = "place:";
	private static final String ROTATE = "rotate";
	private static final String CONNECT = "connect";
	private static final String LEAVE = "leave:";
	private static final String LOCK = "lock";
	private static final String UPDATE = "update:";
	private static final String ROTATED = "rotated:";
	private static final String MOVE_NOT_VALID = "move not valid";
	private static final String NEXT = "next:";
	private static final String TURN = "turn:";
	private static final String END = "end:";
	private static final char EQUAL = '=';
	private static final char COMMA = ',';
	private static final String START = "start:";

	/**
	 * Generates the message "connect".
	 * @return the string generated.
	 */
	public String generateConnect() {
		return CONNECT;
	}
	
	/**
	 * Generates the message "rotate".
	 * @return the string generated.
	 */
	public String generateRotate() {
		return ROTATE;
	}
	
	/**
	 * Generate the string for placing a tile in the position passed.
	 * A string like "place: x,y".
	 * @param currentPosition position where place the tile.
	 * @return the string generated.
	 */
	public String generatePlace(Position currentPosition) {
		StringBuilder message = new StringBuilder();
		message.append(PLACE);
		message.append(currentPosition.toString());
		return message.toString();
	}
	
	/**
	 * Generate the message "pass".
	 * @return the string generated.
	 */
	public String generatePass() {
		return PASS;
	}
	
	/**
	 * Generate the string like "tile: side".
	 * @param currentPosition position of the tile
	 * @return the string generated.
	 */
	public String generateTile(CardinalPosition currentPosition) {
		StringBuilder message = new StringBuilder();
		message.append(TILE);
		message.append(currentPosition.getPositionCode());
		return message.toString();
	}
	
	/**
	 * Generates the string for select the turn.
	 * A string like "turn: color".
	 * @param currentTurn color of the player which must play.
	 * @return the string generated.
	 */
	public String generateTurn(Color currentTurn) {
		StringBuilder message = new StringBuilder();
		message.append(TURN);
		message.append(currentTurn.toString().toLowerCase());
		return message.toString();
	}
	
	/**
	 * Generate the start message: "start: tile, name, color, num".
	 * @param firstTile first tile drawn.
	 * @param gameName name of the current game.
	 * @param currentColor color of the current player.
	 * @param playersNumber number of players in the game.
	 * @return the string generated.
	 */
	public String generateStart(ModelTile firstTile, String gameName, Color currentColor, int playersNumber) {
		StringBuilder message = new StringBuilder();
		message.append(START);
		message.append(firstTile.toString());
		message.append(COMMA);
		message.append(gameName);
		message.append(COMMA);
		message.append(currentColor.toString().toLowerCase());
		message.append(COMMA);
		message.append(playersNumber);
		return message.toString();
	}
	
	/**
	 * Generate the string which represents the next tile.
	 * @param drawedTile tile drawed.
	 * @return the string generated.
	 */
	public String generateNext(ModelTile drawedTile) {
		StringBuilder message = new StringBuilder();
		message.append(NEXT);
		message.append(drawedTile.toString());
		return message.toString();
	}
	
	/**
	 * Generate a message like: "score: score".
	 * @param players current player.
	 * @return the string generated.
	 */
	public String generateScore(List<ModelPlayer> players) {
		StringBuilder message = new StringBuilder();
		message.append(SCORE);
		message.append(generateScores(players));
		return message.toString();
	}
	
	/**
	 * Generate the string which represents the final score.
	 * @param players player which have played.
	 * @return the string generated.
	 */
	public String generateFinalScore(List<ModelPlayer> players) {
		StringBuilder message = new StringBuilder();
		message.append(END);
		message.append(generateScores(players));
		return message.toString();
	}
	
	/**
	 * Generate a string of move not valid.
	 * @return the string generated.
	 */
	public String generateMoveNotValid() {
		return MOVE_NOT_VALID;
	}
	
	/**
	 * Generated the string which represents the tile rotated.
	 * It's like: "rotated: tile".
	 * @param rotatedTile tile rotated to represent.
	 * @return the string generated.
	 */
	public String generateRotated(ModelTile rotatedTile) {
		StringBuilder message = new StringBuilder();
		message.append(ROTATED);
		message.append(rotatedTile.toString());
		return message.toString();
	}
	
	/**
	 * Generate the update message, like "update: tile,x,y".
	 * @param modifiedTile tile updated.
	 * @param atPosition position updated.
	 * @return the string generated.
	 */
	public String generateUpdate(DisplayedTile modifiedTile, Position atPosition) {
		StringBuilder message = new StringBuilder();
		message.append(UPDATE);
		message.append(modifiedTile.toString());
		message.append(COMMA);
		message.append(atPosition.toString());
		return message.toString();
	}
	
	/**
	 * Generates a message of lock.
	 * @return the string generated.
	 */
	public String generateLock() {
		return LOCK;
	}
	
	/**
	 * Generates the leave message for a player.
	 * @param left player who have left the game.
	 * @return the string generated.
	 */
	public String generateLeave(Color left) {
		StringBuilder message = new StringBuilder();
		message.append(LEAVE);
		message.append(left.toString().toLowerCase());
		return message.toString();
	}
	
	
	private String generateScores(List<ModelPlayer> players) {
		StringBuilder message = new StringBuilder();
		for(ModelPlayer currentPlayer: players) {
			Color currentColor = currentPlayer.getFlagColor();
			message.append(currentColor.toString().toLowerCase());
			message.append(EQUAL);
			message.append(currentPlayer.getPoints());
			message.append(COMMA);
		}
		int lastCharacter = message.length()-1;
		message.deleteCharAt(lastCharacter);
		return message.toString();
	}
}
