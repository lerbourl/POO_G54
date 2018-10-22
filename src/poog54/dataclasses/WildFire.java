/*
 * Represents a WildFire.
 * WildFire has no need to know the tile on which he is.
 * Extends Drawable : it has coordinates.
 */

package poog54.dataclasses;
import poog54.io.Drawable;

/**
 * @author Rey-Ricord Yoann
 * 
 */

public class WildFire extends Drawable {

	private int intensity;

	public WildFire(int xCoord, int yCoord, int intensity) {
		super("assets/fire.png", xCoord, yCoord);
		this.intensity = intensity;
	}

	public int getIntensity() {
		return intensity;
	}


	@Override
	public String toString() {
		return "WildFire\nfilepath : " + this.getImageFilePath() +
				"\nline : " + this.getCoord().y +
				"\nColumn : "+ this.getCoord().x + "\n";
	}
	
}
