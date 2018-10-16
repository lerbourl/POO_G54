package poog54.dataclasses.robots;

import poog54.dataclasses.*;

/**
  * Represents a caterpillar firefighter.
  * This class inherits from the generic Robot class
  *
  * The drone speed cannot exceed 80
  * The speed is 50% in forests
  * The caterpillar cannot go on rocks or water (speed=0)
  * 
  * The caterpillar can tank up on a tile adjacent to a water reserve
  * The tank capacity is 2000 liters
  * The tank up delay is 5 minutes
  * The pour out capacity is 100 liters
  * The pour out delay is 8 seconds
  * 
  * The firefighter is supposed to be 100% full when the simulation begins
 */

/**
 * @author POO_G54
 * 
 */
public class TrackedRob extends Robot {

	/**Constructor
	 * This constructor sets the speed and the water capacity of a drone.
	 * It also executes the generic constructor method
	 */

	public TrackedRob(TheMap theMap, Tile inital_location) {
		super(theMap, inital_location);
		setSpeed();
		this.water_capacity = 2000;
		this.water_level = 2000;

	}

	/**
	 * Path builder: provide the fastest path to the specified tile according to
	 * speeds & theMap
	 */
	@Override
	public Target buildTargetPath(Tile location) {
		Target target = new Target();
		target.path = new Tile[2];

		// TODO compute the fastest (full) path
		target.location = location;
		target.path[0] = this.location;
		target.path[1] = location;

		return target;
	}

	/**
	 * Tank up: fill tank
	 */
	@Override
	public void tankUp() {

		// TODO

	}

	/**
	 * Pouring water: extinguish fire
	 */
	@Override
	public void pourOut(int water_volume) {

		// TODO

	}

	@Override
	public void setSpeed(int speed) {
		/* no speed limitation here */
		if (speed > 80) speed = 80;
		this.speed = new Speed(speed, speed/2, 0, 0, speed);
	}
	@Override
	public void setSpeed() {setSpeed(60);};
}