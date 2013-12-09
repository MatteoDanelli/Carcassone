package it.polimi.dei.provafinale.carcassone.model;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Set;

/**
 * This class model all game. 
 * It contains information about player, deck and map.
 * It control the game and offers methods to play.
 * @author Matteo Danelli, Andrea Canidio
 */

public class Game extends Observable {

	//Constants
	private static final int CITYPOINT = 2;
	private static final Color FIRSTPLAYER = Color.RED;
	private static final int MAXPLAYERS = 5;
	private static final int POINT = 1;

	private ModelMap currentMap;
	private Deck currentDeck;
	private List<ModelPlayer> availablesPlayers;
	private Color currentTurn;

	// Relative file path of the Tile source file
	private static final String FILEPATH = "./src/main/resources/carcassone.txt";

	/**
	 * Initialize the map, a list of available players, the current turn and the deck.
	 */
	public Game() {
		currentMap = new ModelMap();
		availablesPlayers = new ArrayList<ModelPlayer>();
		currentTurn = FIRSTPLAYER;
		currentDeck = null; 
	}

	/**
	 * Getter of the current map of the game.
	 * @return current map.
	 */
	public ModelMap getCurrentMap() {
		return currentMap;
	}

	/**
	 * Getter of the current deck of the game.
	 * @return	current deck.
	 */
	public Deck getCurrentDeck() {
		return currentDeck;
	}


	/**
	 * Getter of the turn player.
	 * @return color of the player turn.
	 */
	public Color getTurnPlayer() {
		return currentTurn;
	}

	/**
	 * Get the placed tile at position passed.
	 * @param positionToSearch position to check
	 * @return	tile placed in positionToSearch
	 * @throws IllegalArgumentException if the position hasn't tile.
	 */
	public ModelTile getPlacedTile(Position positionToSearch) throws IllegalArgumentException{
		if(currentMap.thereIsTileIn(positionToSearch)) {
			return currentMap.getTileFromPosition(positionToSearch);
		}
		throw new IllegalArgumentException("No tile placed here");
	}


	/**
	 * Select, from the available players, the player assigned to playerColor.
	 * @param playerColor player to select.
	 * @return	the player selected.
	 * @throws IllegalArgumentException if player doesn't exist.
	 */
	public ModelPlayer selectPlayer(Color playerColor) throws IllegalArgumentException{
		for(ModelPlayer currentPlayer: availablesPlayers) {
			if(currentPlayer.getFlagColor() == playerColor) {
				return currentPlayer;
			}
		}
		throw new IllegalArgumentException("Player not exists.");
	}


	/**
	 * Draw a tile from current deck and assign it to playerColor
	 * @param playerColor
	 * @return tile draw
	 */
	public ModelTile drawTile(Color playerColor) {
		ModelTile currentTile = currentDeck.drawTile();
		ModelPlayer currentPlayer = selectPlayer(playerColor);
		currentPlayer.setCurrentTile(currentTile);
		if(countObservers() != 0) {
			setChanged();
			notifyObservers(currentTile.display());
		}
		return currentTile;
	}

	/**
	 * Create a player.
	 * @return	the color of player created.
	 * @throws IllegalStateException if the game has reached max players.
	 */
	public Color createPlayer() throws IllegalStateException{
		if(availablesPlayers.size() < MAXPLAYERS) {
			for(Color currentColor: Color.values()) {
				if(!existPlayer(currentColor)) {
					ModelPlayer createdPlayer = new ModelPlayer(currentColor);
					availablesPlayers.add(createdPlayer);
					if(countObservers() != 0) {
						setChanged();
						notifyObservers(createdPlayer.display());
					}
					return currentColor;
				}
			}
		}
		throw new IllegalStateException("Maximum number of players reached.");
	}

	/**
	 * Initialize the game create the deck and the map.
	 * The initialization is done at the starting of the game.
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	public void initializeGame() throws IllegalArgumentException, IOException {
		DeckBuilder deckBuilder = new DeckBuilder();
		currentDeck = deckBuilder.initializeDeck(FILEPATH);
		MapBuilder mapBuilder = new MapBuilder();
		currentMap = mapBuilder.initializeMap(currentDeck);
		Position startPosition = new Position(0, 0);
		DisplayedTile startTile = currentMap.getTileFromPosition(startPosition).display();
		if(countObservers() != 0) {
			setChanged();
			notifyObservers(new PlacedTile(startPosition, startTile));
		}
	}

	/**
	 * Shuffle the deck.
	 */
	public void shuffleDeck() {
		currentDeck.shuffle();
	}


	/**
	 * Current turn go next.
	 */
	public void nextTurn() {
		Color nextTurn = currentTurn.next();
		if(existPlayer(nextTurn)) {
			currentTurn = nextTurn;
		}
		else {
			currentTurn = Color.RED;
		}
		if(countObservers() != 0) {
			setChanged();
			notifyObservers(currentTurn);
		}
	}

	/**
	 * Check if this is last turn.
	 * @return true if it's last turn, else false.
	 */
	public boolean isLastTurn() {
		return isFinalScore();
	}

	/**
	 * Rotate this tile and assign it to playerColor
	 * @param playerColor
	 * @return tile rotated
	 */
	public ModelTile rotateTile(Color playerColor) {
		ModelPlayer chosenPlayer = selectPlayer(playerColor);
		ModelTile rotatedTile = chosenPlayer.getCurrentTile();
		rotatedTile = rotatedTile.rotate();
		chosenPlayer.setCurrentTile(rotatedTile);
		if(countObservers() != 0) {
			setChanged();
			notifyObservers(rotatedTile.display());
		}
		return rotatedTile;
	}

	/**
	 * Put the currentTile of the playerColor in the chosenPosition.
	 * NB: it don't check if the position is free, or available, or suitable to tile.
	 * @param playerColor current color of the player.
	 * @param chosenPosition position to insert the tile.
	 */
	public void putTileInPosition(Color playerColor, Position chosenPosition) {
		ModelPlayer chosenPlayer = selectPlayer(playerColor);
		ModelTile placedTile = chosenPlayer.getCurrentTile();
		currentMap.putTileInPosition(chosenPosition, placedTile);
		if(countObservers() != 0) {
			setChanged();
			notifyObservers(new PlacedTile(chosenPosition, placedTile.display()));
		}
	}

	/** 
	 * Check if the position passed is available.
	 * A position is available if it's near a position of a placed tile.
	 * @param selectedPosition position to check.
	 * @return true if the position is available, else false.
	 */
	public boolean isAvailable(Position selectedPosition) {
		return currentMap.isAnAvailablePosition(selectedPosition);
	}

	/**
	 * Check if the tile, or one of its rotations, is placeable somewhere in the map.
	 * @param currentTile tile to check.
	 * @return true if the tile is placeable, else false.
	 */
	public boolean isPlaceableWithRotations(ModelTile currentTile) {
		return currentMap.isPlaceableWithRotations(currentTile);
	}

	/**
	 * Check if the tile is placeable somewhere in the map.
	 * @param currentTile tile to check.
	 * @return	true if the tile is placeable, else false.
	 */
	public boolean isPlaceable(ModelTile currentTile) {
		return currentMap.isPlaceable(currentTile);
	}

	/**
	 * Check if the tile is suitable in the position passed.
	 * Suitable means that the tile is compatible with the neighbors tile.
	 * @param currentPosition position to check.
	 * @param currentTile	tile to check.
	 * @return	true if the tile is suitable to currentPosition, else false.
	 */
	public boolean isSuitable(Position currentPosition, ModelTile currentTile) {
		return currentMap.isSuitable(currentPosition, currentTile);
	}

	/**
	 * Check if the tile is placeable in the position passed.
	 * @param currentTile	tile to check.
	 * @param currentPosition	position to check.
	 * @return	true if the tile is placeable in the currentPosition, else false.
	 */
	public boolean isPlaceableHere(ModelTile currentTile, Position currentPosition) {
		return currentMap.isAnAvailablePosition(currentPosition) && isSuitable(currentPosition, currentTile);
	}

	/**
	 * Calculate the score starting position passed.
	 * @param lastPosition starting position
	 */
	public void calculateScore(Position lastPosition) {
		ModelTile lastTile = currentMap.getTileFromPosition(lastPosition);
		Set<TileArea> trackGenerators = lastTile.getIndipendentAreas();
		for(TileArea currentArea: trackGenerators) {
			calculateTrackScore(currentArea, lastPosition);
		}
		for(ModelPlayer selectedPlayer: availablesPlayers) {
			if(countObservers() != 0) {
				setChanged();
				notifyObservers(selectedPlayer.display());
			}
		}
	}

	/**
	 * Calculate the score at the end of the game.
	 */
	public void calculateFinalScores() {
		for(Position currentPosition: currentMap.getAllPlacedPositions()) {
			ModelTile currentTile = currentMap.getTileFromPosition(currentPosition);
			if(currentTile.hasFlag()) {
				calculateScore(currentPosition);
			}

		}
	}

	/**
	 * Check if it's possible place a flag in the position passed and in the cardinal position passed.
	 * @param currentPosition position to check.
	 * @param direction		cardinal position where insert flag.
	 * @return	true if flag placeable, else false.
	 */
	public boolean canPlaceFlag(Position currentPosition, CardinalPosition direction) {
		ModelTile selectedTile = currentMap.getTileFromPosition(currentPosition);
		TileArea startArea = selectedTile.getTileArea(direction);
		if(startArea.getAreaType(FieldType.FIELD)) {
			return false;
		}
		Track generatedTrack = currentMap.generateTrack(startArea, currentPosition);
		if(generatedTrack.hasFlag()) {
			return false;
		}
		return true;
	}

	/**
	 * Check if it's possible place a flag in the position passed.
	 * @param currentPosition position where place flag
	 * @return	true if it's possible place flag, else false.
	 */
	public boolean canPlaceFlagInTile(Position currentPosition) {
		for(CardinalPosition currentDirection: CardinalPosition.values()) {
			if(canPlaceFlag(currentPosition, currentDirection) && selectPlayer(currentTurn).hasMoreFlags()) {
				return true;
			}
		}
		return false;
	}


	/**
	 * Insert flag of passed color in the currentPosition at cardinal position passed.
	 * @param currentPosition	position where insert flag.
	 * @param direction		cardinal position where insert flag.
	 * @param flag	color of the flag if i want insert.
	 */
	public void insertFlag(Position currentPosition, CardinalPosition direction, Color flag) {
		ModelTile currentTile = currentMap.getTileFromPosition(currentPosition);
		currentTile = currentTile.putFlag(flag, direction);
		currentMap.putTileInPosition(currentPosition, currentTile);
		ModelPlayer selectedPlayer = selectPlayer(flag);
		selectedPlayer.placeFlag();
		if(countObservers() != 0) {
			setChanged();
			notifyObservers(new PlacedTile(currentPosition, currentTile.display()));
		}
	}

	/**
	 * Remove the current player from the game.
	 * @param currentTurn color of the current player
	 */
	public void removePlayer(Color currentTurn) {
		for(Position currentPosition: currentMap.getAllPlacedPositions()) {
			ModelTile currentTile = currentMap.getTileFromPosition(currentPosition);
			if(currentTile.hasFlag() && currentTile.getFlag() == currentTurn) {
				currentTile = currentTile.removeFlag();
				currentMap.putTileInPosition(currentPosition, currentTile);
				if(countObservers() != 0) {
					setChanged();
					notifyObservers(new PlacedTile(currentPosition, currentTile.display()));
				}
			}
		}
		for(int i = 0; i < availablesPlayers.size(); i++) {
			ModelPlayer currentPlayer = availablesPlayers.get(i);
			if(currentPlayer.getFlagColor() == currentTurn) {
				availablesPlayers.remove(i);
			}
		}
	}

	/**
	 * Get a list of all available players.
	 * @return a list of Player.
	 */
	public List<ModelPlayer> getPlayers() {
		return availablesPlayers;
	}


	//Calculate the score of a track, starting from currentPosition in the startArea
	private void calculateTrackScore(TileArea startArea, Position currentPosition) {
		Track currentTrack = currentMap.generateTrack(startArea, currentPosition);
		if(mustCalculatePartialScore(currentTrack)) {
			int gainedPoints = calculatePartialPoints(currentTrack);
			assignPointsOf(currentTrack, gainedPoints);
			removeFlagsFrom(currentTrack);
		}
		else if(mustCalculateFinalScore(currentTrack)) {
			int gainedPoints = calculateFinalPoints(currentTrack);
			assignPointsOf(currentTrack, gainedPoints);
			removeFlagsFrom(currentTrack);
		}
	}

	//Calculate partial points from currentTrack
	private int calculatePartialPoints(Track currentTrack) {
		int gainedPoints = currentTrack.countTiles() * POINT;
		if(currentTrack.is(FieldType.TOWN)) {
			gainedPoints *= CITYPOINT;
		}
		return gainedPoints;
	}

	//Calculate the final points starting from currentTrack
	private int calculateFinalPoints(Track currentTrack) {
		return currentTrack.countTiles() * POINT;
	}

	//Assigns point to a track.
	private void assignPointsOf(Track currentTrack, int gainedPoints) {
		List<Color> players = currentTrack.getMaximumFlagPlayers();
		for(Color currentPlayer: players) {
			ModelPlayer selectedPlayer = selectPlayer(currentPlayer);
			selectedPlayer.addPoints(gainedPoints);
		}
	}

	//Remove the flag from a track.
	private void removeFlagsFrom(Track currentTrack) {
		Set<Position> listForRemoving =currentTrack.getAllPositions();
		for(Position positionToRemove: listForRemoving) {
			try {
				if(currentTrack.hasFlag(positionToRemove)) {
					ModelTile tileToRemove = currentMap.getTileFromPosition(positionToRemove);
					Color flag = tileToRemove.getFlag();
					ModelPlayer currentPlayer = selectPlayer(flag);
					currentPlayer.receiveFlag();
					tileToRemove = tileToRemove.removeFlag();
					currentMap.putTileInPosition(positionToRemove, tileToRemove);
					if(countObservers() != 0) {
						setChanged();
						notifyObservers(new PlacedTile(positionToRemove, tileToRemove.display()));
					}
				}
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		}
	}

	//Check if one player exists.
	private boolean existPlayer(Color playerColor) {
		for(ModelPlayer currentPlayer: availablesPlayers) {
			if(currentPlayer.getFlagColor() == playerColor) {
				return true;
			}
		}
		return false;
	}

	//Check if the deck is empty, so the score to compute is final.
	private boolean isFinalScore() {
		return currentDeck.isEmpty();
	}

	//Check if I want calculate the partial score of the turn.
	private boolean mustCalculatePartialScore(Track currentTrack) {
		return !isFinalScore() && currentTrack.isClosed() && currentTrack.hasFlag();
	}

	//Check if I want calculate final score, at the end of the game.
	private boolean mustCalculateFinalScore(Track currentTrack) {
		return isFinalScore() && currentTrack.hasFlag();
	}
}
