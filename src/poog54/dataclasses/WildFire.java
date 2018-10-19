package poog54.dataclasses;

import poog54.dataclasses.Tile;
import poog54.io.Drawable;

/**
 * @author Rey-Ricord Yoann 
 * 
 */

public class WildFire extends Drawable{

	private Tile pos;
	private int intensity;
	
	public WildFire(Tile pos, int intensity) {
		super("assets/fire.png", pos.getLine(), pos.getColumn());
		this.intensity = intensity;
		this.pos = pos;
	}
	
	public Tile getPos() {
		return pos;
	}
	
	public int getintensity() {
		return intensity;
	}
	
	
}
