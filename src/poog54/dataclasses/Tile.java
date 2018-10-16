package poog54.dataclasses;
import poog54.enums.*;

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
	
	/** Tile constructor */

	public Tile(int line, int column, TypeField type) {
		this.line=line;
		this.column=column;
		this.type=type;
	}
	
	/** Access to line*/
	public int getLine(){
		return this.line;
	}
	/** Acces to column*/
	
	public int getColumn(){
		return this.column;
	}
	/** Acces to Type Field*/
	
	public TypeField getTypeField(){
		return this.type;
	}
}