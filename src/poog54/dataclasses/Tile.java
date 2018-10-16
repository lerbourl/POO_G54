package poog54.dataclasses;
import poog54.enums.TypeField;

/**
 * Represents a tile of the theMap
 */

/**
 * @author Rey-Ricord Yoann 
 * 
 */


public class Tile {
	/** Coordinates */
	private int line, column;
	/** Type of the field  */
	TypeField type;
	
	/** Access to line*/
	int getLine(){
		return this.line;
	}
	/** Acces to column*/
	int getColumn(){
		return this.column;
	}
	/** Acces to Type Field*/
	TypeField getTypeField(){
		return this.type;
	}
}