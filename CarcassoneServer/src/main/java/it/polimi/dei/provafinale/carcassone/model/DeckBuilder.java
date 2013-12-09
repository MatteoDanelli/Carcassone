package it.polimi.dei.provafinale.carcassone.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class build a deck from a file.
 * @author Matteo Danelli
 *
 */
public class DeckBuilder {


	/**
	 * Create a new Deck.
	 * First, it loads file from file path, 
	 * then it loads all tile from the file,
	 * after all close the fileStream.
	 * @param filepath of the deck file.
	 * @throws IllegalArgumentException if the file path is wrong.
	 * @throws IOException if some errors on reading file occurs.
	 */
	public Deck initializeDeck(String filepath) throws IllegalArgumentException, IOException {
		Deck buildedDeck = new Deck();
		BufferedReader fileStream;
		try {
			fileStream = loadFile(filepath);
			boolean correctLoad = loadTile(buildedDeck, fileStream); 
			while (correctLoad){
				correctLoad = loadTile(buildedDeck, fileStream);
			}
			closeFile(fileStream);
		} 
		catch (IOException e) {
			throw new IOException("Not able to create a Deck.", e);
		}
		catch (IllegalArgumentException e){
			throw new IllegalArgumentException("Error on input file", e);
		}
		return buildedDeck;
	}

	/**
	 * Load the file from the disk.
	 * @param filePath 		path of file with the textual list of tiles. 
	 * @return				returns the buffer linked to the file loaded.
	 * @throws IOException	if the opening of the file fails.
	 */
	private BufferedReader loadFile(String filePath) throws IOException {
		BufferedReader bufferedReader;
		try {
			FileReader fileReader = new FileReader(filePath);
			bufferedReader = new BufferedReader(fileReader);
		} catch (IOException e) {
			throw new IOException("Failed loading file\n", e);
		}
		return bufferedReader;
	}

	/**
	 * Close the stream of the file previously open.
	 * @param file				buffered reader of the file opened.
	 * @throws IOException		if the closing of the file fails.
	 */
	private void closeFile(BufferedReader file) throws IOException {
		try {
			file.close();
		} 
		catch (IOException e) {
			throw new IOException("Unable to close file\n", e);
		}
	}

	/**
	 * Read one line of the input file.
	 * Create a tile for each line read.
	 * Add this tile to the stackOfTile.
	 * @param buildedDeck 
	 * @param bufferedReader	buffer of the input file.
	 * @return					true if the line is read, 
	 * 							false if there aren't a line to be read.
	 * @throws Exception		if sometimes fails during the reading of the lines.
	 */
	private boolean loadTile(Deck buildedDeck, BufferedReader bufferedReader) throws IOException, IllegalArgumentException {
		String lineReaded = null;
		try { 
			lineReaded = bufferedReader.readLine();
			if (lineReaded == null) {
				return false;
			}
			else {
				ModelTile tile = new ModelTile(lineReaded);
				buildedDeck.add(tile);
				return true;
			}
		}
		catch (IOException e) {
			throw new IOException("Cannot read another line\n", e);
		}
		catch (IllegalArgumentException e){
			throw new IllegalArgumentException("Error creating tile: " + lineReaded, e);
		}
	}
}
