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

	/**
	 * Constructor with default speed This constructor sets the specific speed and
	 * water capacity of a caterpillar. It also executes the generic constructor
	 * method
	 */

	TrackedRob(Map map, Tile inital_location) {
		this(map, inital_location, 60);
	}

	/**
	 * Constructor with custom speed This constructor sets the specific speed and
	 * water capacity of a drone. It also executes the generic constructor method
	 */

	TrackedRob(Map map, Tile inital_location, int custom_speed) {
		super(map, inital_location, custom_speed);

		int speed;
		if (custom_speed > 80) {
			speed = 80;
		} else {
			speed = custom_speed;
		}
		this.speed.empty_field = speed;
		this.speed.forest = speed / 2;
		this.speed.house = speed;
		this.speed.rock = 0;
		this.speed.water = 0;

		this.water_capacity = 2000;
		this.water_level = 2000;

	}

	/**
	 * Path builder: provide the fastest path to the specified tile according to
	 * speeds & map
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
}
