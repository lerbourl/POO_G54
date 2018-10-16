package poog54.dataclasses;

import poog54.dataclasses.Tile;

/**
 * @author Rey-Ricord Yoann 
 * 
 */

public class WildFire {

	private Tile pos;
	private int liter;
	
	public WildFire(Tile pos, int liter) {
		this.liter=liter;
		this.pos=pos;
	}
	
	public Tile getPos() {
		return pos;
	}
	
	public int getLiter() {
		return liter;
	}
	
	
}
