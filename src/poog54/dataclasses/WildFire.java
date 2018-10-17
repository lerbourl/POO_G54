package poog54.dataclasses;

import poog54.dataclasses.Tile;

/**
 * @author Rey-Ricord Yoann 
 * 
 */

public class WildFire {

	private Tile pos;
	private int intensity;
	
	public WildFire(Tile pos, int intensity) {
		this.intensity=intensity;
		this.pos=pos;
	}
	
	public Tile getPos() {
		return pos;
	}
	
	public int getintensity() {
		return intensity;
	}
	
	
}
