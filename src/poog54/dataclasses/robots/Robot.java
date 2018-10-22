package poog54.dataclasses.robots;

import poog54.dataclasses.*;
import poog54.enums.*;
import poog54.io.Drawable;

/**
  * Represents a basic firefighter robot.
  * This class is abstract because you can (should) not instantiate a generic robot,
  * more specific robot classes can.
  *
  * A robot has access to the following external data:
  * - the theMap (to locate fires, water points and compute trajectories)
  *
  * A robot moves one cell / tile at a time, as mentioned in the specifications.
  * A robot has no need to know the tile on which he is, he can have it thanks to the map !
 */

/**
 * @author POO_G54
 * 
 */

public abstract class Robot extends Drawable {

	/**
	 * Constructor with default speed Initialises the generic attributes of a
	 * firefighter:- theMap - initial location - state (IDLE)
	 */

	Robot(TheMap theMap, int xCoord, int yCoord) {
		super(xCoord, yCoord);
		// Speeds and water capacity must be set in the child constructors
		this.theMap = theMap;
		this.state = RobotState.IDLE; // changes when the firefighter chief set a fire target
	}

	/**
	 * Current state: Idle, Moving to fire, moving to water reserve, pouring,
	 * tanking up
	 */

	protected RobotState state;

	/** TheMap */
	protected TheMap theMap;

	/**
	 * @return the state
	 */
	public RobotState getState() {
		return state;
	}

	/*
	 * Speeds: depends on the type of field
	 */
	protected Speed speed;

	/**
	 * Fire target location: position of the targeted fire on the theMap Target fire
	 * path: path to the assigned fire. path[0] is always the current location.
	 */
	protected Target fire;

	/**
	 * Nearest water location: position of the water reserve on the theMap and path
	 * to it path[0] is always the current location.
	 */
	protected Target water;

	/**
	 * Water capacity: tank size
	 */
	protected int water_capacity;

	/**
	 * Water level: remaining water in the tank
	 */
	protected int water_level;

	/**
	 * @param state the state to set
	 */
	public void setState(RobotState state) {
		this.state = state;
	}

	/**
	 * @return the fire
	 */
	public Target getFire() {
		return fire;
	}

	/**
	 * @param fire the fire to set
	 */
	public void setFire(Target fire) {
		this.fire = fire;
	}

	/**
	 * @return the water_capacity
	 */
	public int getWater_capacity() {
		return water_capacity;
	}

	/**
	 * @param water_capacity the water_capacity to set
	 */
	public void setWater_capacity(int water_capacity) {
		this.water_capacity = water_capacity;
	}

	/**
	 * @return the water_level
	 */
	public int getWater_level() {
		return water_level;
	}

	/**
	 * @param water_level the water_level to set
	 */
	public void setWater_level(int water_level) {
		this.water_level = water_level;
	}

	/**
	 * Speed assignment Abstract cause it depends on the Robot Type
	 */
	public abstract void setSpeed(int speed);

	public abstract void setSpeed();

	// UTILES !?

	/** Target fire assignment */
	public void setTargetFire(Target fire) {
		this.fire = fire;
	}

	/** Target water assignment */
	public void setTargetWater(Target water) {
		this.water = water;
	}

	/**
	 * Target builder: - provides the fastest path to the specified tile according
	 * to speeds & theMap - provides the time required to reach the target
	 */
	// TODO
	/*
	 * public Target buildTargetPath(Point location) { Target target = new Target();
	 * target.path = new Tile[2];
	 * 
	 * // TODO compute the fastest (full) path
	 * 
	 * target.location = location; target.path[0] = this.location; target.path[1] =
	 * location;
	 * 
	 * return target; }
	 */

	public Tile getTile() {
		return this.theMap.getTile(this.getCoord().x, this.getCoord().y);
	}

	
	public void move(CardinalPoints dir) {
		if (theMap.hasNeighbour(this.getTile(), dir)) {
			System.out.println(this.getCoord().toString());
			this.translate(dir);
			System.out.println(this.getCoord().toString());
		}
		else System.out.println("Move robot on non allowed tile");
	}

	/**
	 * Tank up: fill tank
	 */
	public void tankUp() {

		// TODO

	}

	/**
	 * Pouring water: extinguish fire
	 */
	public void pourOut(int water_volume) {

		// TODO

	}
}
