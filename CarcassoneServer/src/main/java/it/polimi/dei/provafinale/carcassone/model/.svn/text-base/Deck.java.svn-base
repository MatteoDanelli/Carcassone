package it.polimi.dei.provafinale.carcassone.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This class represent the deck like a collection of tiles.
 *  
 * @author Matteo Danelli
 */

public class Deck {

	private static final int EMPTY = 0;

	private static final int NEXT = 0;

	//It represent all tiles present in the deck.
	private List<ModelTile> stackOfTiles;

	/**
	 * Initialize the deck like a collection of tile.
	 * @return
	 */
	public Deck() {
		stackOfTiles = new ArrayList<ModelTile>();
	}

	/**
	 * Draw a tile from the deck, unless it's empty.
	 * @throws NoSuchElementException if deck is empty.
	 */
	public ModelTile drawTile() throws NoSuchElementException{
		if(this.isEmpty()){
			throw new NoSuchElementException("Deck is empty");
		}
		return stackOfTiles.remove(NEXT);
	}

	/**
	 * Calculate the size of deck.
	 * Size is the number of tile in the deck.
	 * @return	numbers of Tile in the deck.
	 */
	public int size(){
		return stackOfTiles.size();
	}

	/**
	 * Check if the deck has almost one tile.
	 * @return	true if empty deck, else false.
	 */
	public boolean isEmpty(){
		return this.size() == EMPTY;
	}

	/**
	 * Add a Tile to the deck.
	 * @param newTile tile to add.
	 */
	public void add(ModelTile newTile) {
		stackOfTiles.add(newTile);
	}

	/**
	 * Check if a tile is present in the deck.
	 * @param thisTile tile to check.
	 * @return	true if deck contains tile, else false.
	 */
	public boolean contains(ModelTile thisTile) {
		return stackOfTiles.contains(thisTile);
	}

	/**
	 * Search a tile in the deck.
	 * @param currentTile tile to search.
	 * @return	last index of the tile found.
	 */
	public int search(ModelTile currentTile) {
		return stackOfTiles.lastIndexOf(currentTile);
	}

	//NB: index starts from zero to size-1
	/**
	 * Remove a tile from the deck.
	 * @param index index of tile to search.
	 * @return	the tile removed.
	 */
	public ModelTile removeTile(int index) {
		return stackOfTiles.remove(index);
	}

	/**
	 * Shuffle the tiles present in the deck.
	 */
	public void shuffle() {
		Collections.shuffle(stackOfTiles);
	}
}
