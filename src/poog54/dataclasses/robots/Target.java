package poog54.dataclasses.robots;

import poog54.dataclasses.Tile;

/**
 * Represents a target:
 * a fire or a water reserve
 */

/**
 * @author POO_G54
 * 
 */
public class Target {

	/** Target location */
	public Tile location;

	/**
	 * Path to the target: sequence of tile starting from the current location
	 */
	public Tile path[];

	/** Time: remaining time needed to reach the target location */
	public int time;

	/** Constructor */
	Target() {

	}
}