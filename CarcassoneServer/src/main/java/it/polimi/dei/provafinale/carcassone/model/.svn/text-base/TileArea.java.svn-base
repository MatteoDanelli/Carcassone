package it.polimi.dei.provafinale.carcassone.model;

/**
 * This Field class represent the whole informations contained in a field, that
 * is one of the four parts of a Tile. The two informations stored are the type
 * of the Field (represented by the Enum FieldType) and the presence of a marker
 * for the Player who want to reserve the field.
 * 
 * @author Andrea Canidio
 * @version 1.0
 */
public class TileArea {

	//constants
	private static final int MAXPATTERNLENGTH = 5;
	private static final int MINPATTERNLENGTH = 3;
	private static final int PRIMENUMBER3 = 47;
	private static final int PRIMENUMBER2 = 31;
	private static final int PRIMENUMBER1 = 17;
	private static final String PLUS = "+";
	private static final int AREAFLAGINDEX = 4;
	private static final int AREATYPEINDEX = 2;
	private static final int AREAPOSITIONINDEX = 0;

	private final FieldType areaType;
	private final Color areaFlag;
	private final CardinalPosition areaPosition;

	/**
	 * Getter of areaType.
	 * @return FieldType got.
	 */
	public FieldType getAreaType() {
		return areaType;
	}

	/**
	 * Getter of areaFlag.
	 * @return Color of flag got.
	 */
	public Color getAreaFlag() {
		return areaFlag;
	}

	/**
	 * Getter of areaPosition.
	 * @return CardinalPosition got.
	 */
	public CardinalPosition getAreaPosition() {
		return areaPosition;
	}

	/**
	 * Constructor of the class.
	 * Create a tileArea from a string pattern.
	 * @param areaPattern
	 * @throws IllegalArgumentException if param not valid
	 */
	public TileArea(String areaPattern) throws IllegalArgumentException {
		if (areaPattern.length() != MINPATTERNLENGTH && areaPattern.length() != MAXPATTERNLENGTH) {
			throw new IllegalArgumentException("Invalid Area Pattern: "
					+ areaPattern);
		}
		try {
			areaPosition = CardinalPosition.recognizePositionCode(areaPattern
					.charAt(AREAPOSITIONINDEX));
			areaType = FieldType.recognizeFieldCode(areaPattern
					.charAt(AREATYPEINDEX));
			if (areaPattern.length() > MINPATTERNLENGTH) {
				areaFlag = Color.recognizeColorCode(areaPattern
						.charAt(AREAFLAGINDEX));
			}
			else {
				areaFlag = null; 
			}
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Invalid Area Pattern: "
					+ areaPattern, e);
		}
	}	

	/**
	 * 
	 * Check if the type passed equals to this.
	 * @return true if the two FieldType equals, else false.
	 */
	public boolean getAreaType(FieldType type) {
		return areaType.equals(type);
	}

	/**
	 * Check if tileArea has a flag on it.
	 * @return	true if it has a flag, else false.
	 */
	public boolean hasFlag() {
		return (areaFlag != null); 
	}

	/**
	 * Check if two tileArea are of same type.
	 * @param otherArea
	 * @return	true if they are of same type, else false
	 */
	public boolean hasSameType(TileArea otherArea) {
		if (this.areaType == otherArea.areaType) {
			return true;
		}
		return false;
	}

	/**
	 * Check if tileArea is at the same position of the cardinalPosition passed
	 * @param cardinalPosition
	 * @return true if they're the same, else false.
	 */
	public boolean isChosenArea(CardinalPosition cardinalPosition) {
		if (this.areaPosition == cardinalPosition) {
			return true; 
		}
		return false;
	}

	/**
	 * Generate a string which represent the tileArea.
	 */
	@Override
	public String toString() {
		String areaRepresentation = areaPosition.getPositionCode() + "="
				+ areaType.getFieldCode();
		if (this.hasFlag()) {
			areaRepresentation += PLUS + areaFlag.getColorCode();
		}
		return areaRepresentation;
	}

	/**
	 * Two tileArea equals if and only if are of same areaType, are in same CardinalPosition and same flag placed on it.
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof TileArea)) {
			return false;
		}
		TileArea other = (TileArea) obj;
		if (this.areaType != other.areaType) {
			return false;
		}
		if (this.areaPosition != other.areaPosition) {
			return false;
		}
		if (this.areaFlag != other.areaFlag) {
			return false; 
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = PRIMENUMBER1 * areaPosition.hashCode() + PRIMENUMBER2 * areaType.hashCode();
		if (areaFlag != null) {
			hash += PRIMENUMBER3 * areaFlag.hashCode();
		}
		return hash;
	}
}
