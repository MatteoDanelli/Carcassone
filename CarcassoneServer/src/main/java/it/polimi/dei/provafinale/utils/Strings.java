package it.polimi.dei.provafinale.utils;

/**
 * This class is an utils class. 
 * It's used to append a string array to a string array.
 * It's used in textualRepresentation of tile.
 * @author Andrea Canidio
 * 
 */
public class Strings {

	//Has default constructor.

	/**
	 * Metod to append an array of string.
	 * @param destination array to append.
	 * @param source array where append.
	 * @throws IllegalArgumentException if the length of two arrays are different.
	 */
	public static void appendStringArray(String destination[], String source[])	throws IllegalArgumentException {
		if (destination.length != source.length) {
			throw new IllegalArgumentException("Invalids String Arrays");
		}
		for (int i = 0; i < destination.length; i++) {
			destination[i] += source[i];
		}
	}
}
