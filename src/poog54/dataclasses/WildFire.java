package poog54.dataclasses;

import poog54.io.Drawable;

/**
 * This class represents a WildFire, it's a drawable object
 * 
 * @author POO_G54
 *
 */
public class WildFire extends Drawable {

	/* attributes */
	private int intensity;

	/* methods */
	/**
	 * Constructor
	 * 
	 * @param xCoord
	 * @param yCoord
	 * @param intensity
	 */
	public WildFire(int xCoord, int yCoord, int intensity) {
		super("assets/fire.png", 1, xCoord, yCoord);
		this.intensity = intensity;
	}

	/**
	 * @return intensity
	 */
	public int getIntensity() {
		return intensity;
	}

	/**
	 * @param intensity
	 */
	public void setIntensity(int intensity) {
		this.intensity = intensity;
	}

	@Override
	public String toString() {
		return "Wild fire [" + this.getCoord().x + ";" + this.getCoord().y + "] <" + this.intensity + ">";
	}
}
