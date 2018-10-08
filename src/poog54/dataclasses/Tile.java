package poog54.dataclasses;
import poog54.enums.TypeField;

/**
 * Represents a tile of the map
 */

/**
 * @author Rey-Ricord Yoann 
 * 
 */


public class Tile {
	/** Coordinates */
	int line, column;
	/** Type of the field  */
	TypeField nature;
	
	/** Access to line*/
	private int getLine(){
		return this.line;
	}
	/** Acces to column*/
	private int getColumn(){
		return this.column;
	}
	/** Acces to Type Field*/
	private TypeField getTypeField(){
		return this.nature;
	}
}