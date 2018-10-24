package poog54.dataclasses;

import poog54.enums.*;
import poog54.io.Drawable;

/**
 * Represents a tile of the theMap
 */

/**
 * @author Rey-Ricord Yoann
 * 
 */

public class Tile extends Drawable {
	/** Type of the field */
	TypeField type;
	/** Image ressource */
	String filepath;

	/** Tile constructor */

	public Tile(int xCoord, int yCoord, TypeField type) {
		super(TileImageFilePath(type), 0,xCoord, yCoord);
		this.type = type;
	}

	private static String TileImageFilePath(TypeField type) {
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

	/** Access to Type Field */
	public TypeField getTypeField() {
		return this.type;
	}
}