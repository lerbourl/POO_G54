package poog54.dataclasses;

import poog54.enums.TypeField;
import poog54.io.Drawable;

/**
 * This class represents a tile of the map, it's a drawable object
 * 
 * @author POO_G54
 */
public class Tile extends Drawable {
	
	/* attributes */
	TypeField type;
	String filepath; // the path of the image

	/* methods */
	/**
	 * Constructor
	 * 
	 * @param xCoord
	 * @param yCoord
	 * @param type
	 */
	public Tile(int xCoord, int yCoord, TypeField type) {
		super(tileImageFilePath(type), 0, xCoord, yCoord);
		this.type = type;
	}

	private static String tileImageFilePath(TypeField type) {
		switch (type) {
		case EAU:
			return "assets/water.png";
		case FORET:
			return "assets/forest.png";
		case HABITAT:
			return "assets/village.png";
		case ROCHE:
			return "assets/mountain.png";
		case TERRAIN_LIBRE:
			return "assets/empty.png";
		default:
			return "";
		}
	}

	/**
	 * @return type
	 */
	public TypeField getTypeField() {
		return this.type;
	}
}