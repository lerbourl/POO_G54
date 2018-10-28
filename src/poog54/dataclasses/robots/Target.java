package poog54.dataclasses.robots;

import java.awt.Point;

import poog54.dataclasses.*;
import poog54.strategies.Path;

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
	public Point location;

	/**
	 * Path to the target: sequence of tile starting from the current location
	 */
	public Path path;
	
	/**
	 * Fire data
	 */
	public WildFire fire;
	
	/** Constructor */
	public Target(Point location, Path path) {
		this.location = location;
		this.path = path;
	}
	
	public Target(WildFire fire, Path path) {
		this(fire.getCoord(), path);
		this.fire = fire;
	}
}