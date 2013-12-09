package it.polimi.dei.provafinale.carcassone.model;

/**
 * This Enum class allows to manage the color of the players. It has been
 * created because of the fact that the colors are predefined and have a
 * specific format.
 * 
 * @author Andrea Canidio
 * @version 1.0
 */
public enum Color {

	RED,

	BLUE,

	GREEN,

	YELLOW,

	BLACK;

	/**
	 * The getter method return the string lowercase associated with the Color.
	 */
	public String getColorName() {
		return this.toString().toLowerCase();
	}

	/**
	 * This getter return the color code used in the textual representation of
	 * the tile.
	 * 
	 * @return the character corresponding to the color. It's always upper case.
	 *         Black is associated with 'K', because the incompatibility with
	 *         BLUE = 'B'.
	 */
	public char getColorCode() {
		if (this == BLACK) {
			return 'K';
		} 
		else {
			return this.toString().charAt(0);
		}
	}

	/**
	 * Associates the character referred to a color with its word.
	 * 
	 * @param colorCode
	 *            char which represents the color.
	 * @throws IllegalArgumentException
	 *             if the char isn't recognized.
	 */
	public static Color recognizeColorCode(char colorCode)
			throws IllegalArgumentException {
		if (colorCode == Color.RED.getColorCode()) {
			return Color.RED;
		} 
		if (colorCode == Color.BLUE.getColorCode()) {
			return Color.BLUE;
		} 
		if (colorCode == Color.GREEN.getColorCode()) {
			return Color.GREEN;
		} 
		if (colorCode == Color.YELLOW.getColorCode()) {
			return Color.YELLOW;
		} 
		if (colorCode == Color.BLACK.getColorCode()) {
			return Color.BLACK;
		} 
		throw new IllegalArgumentException("Invalid Flag Code");
	}

	/**
	 * Each color of the enum is associated with a number.
	 * The method returns the color associated to the number passed.
	 * @param number number associated to color to search.
	 * @return	color associated to number passed.
	 * @throws IllegalArgumentException if the number passed is not valid.
	 */
	public static Color getColorFrom(int number) throws IllegalArgumentException{
		for(Color currentColor: Color.values()) {
			if(currentColor.ordinal() == number) {
				return currentColor;
			}
		}
		throw new IllegalArgumentException("Invalid Number");
	}

	/**
	 * Shift the color. 
	 * If this is Red, for example, the next is Blue.
	 * Useful for shifting player turns.
	 * @return
	 */
	public Color next() {
		return values()[(this.ordinal()+1)%values().length];
	}

	/**
	 * Recognize color from its name.
	 * @param color name of color to recognize.
	 * @return the color recognized.
	 * @throws IllegalArgumentException if the string hasn't an associated color.
	 */
	public static Color recognizeColorName(String color) throws IllegalArgumentException{
		if(color.equalsIgnoreCase(Color.RED.toString())) {
			return Color.RED;
		}
		if(color.equalsIgnoreCase(Color.BLUE.toString())) {
			return Color.BLUE;
		}
		if(color.equalsIgnoreCase(Color.YELLOW.toString())) {
			return Color.YELLOW;
		}
		if(color.equalsIgnoreCase(Color.GREEN.toString())) {
			return Color.GREEN;
		}
		if(color.equalsIgnoreCase(Color.BLACK.toString())) {
			return Color.BLACK;
		}
		throw new IllegalArgumentException("Color name not recognized");
	}
}
