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
	private Point location;

	/**
	 * Path to the target: sequence of tile starting from the current location
	 */
	private Path path;
	
	/**
	 * @return the location
	 */
	public Point getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(Point location) {
		this.location = location;
	}
	/**
	 * @return the path
	 */
	public Path getPath() {
		return path;
	}
	/**
	 * @param path the path to set
	 */
	public void setPath(Path path) {
		this.path = path;
	}
	/**
	 * @return the fire
	 */
	public WildFire getFire() {
		return fire;
	}
	/**
	 * @param fire the fire to set
	 */
	public void setFire(WildFire fire) {
		this.fire = fire;
	}
	/**
	 * Fire data
	 */
	private WildFire fire;
	
	/** Constructor */
	//for a waterTile
	public Target(Point location, Path path) {
		this.location = location;
		this.path = path;
	}
	//for a fire
	public Target(WildFire fire, Path path) {
		this(fire.getCoord(), path);
		this.fire = fire;
	}
}