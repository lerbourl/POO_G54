package poog54.dataclasses.robots;

import poog54.dataclasses.*;

/**
  * Represents a wheel firefighter.
  * This class inherits from the generic Robot class
  *
  * The wheel firefighter speed cannot exceed 80.
  * The wheel firefighter cannot go on water, rock and forest tiles.
  * 
  * The wheel firefighter can tank up on a tile adjacent to a water reserve
  * The tank capacity is 5000 liters
  * The tank up delay is 10 minutes
  * The pour out capacity is 100 liters
  * The pour out delay is 5 seconds
  * 
  * The firefighter is supposed to be 100% full when the simulation begins
 */

/**
 * @author POO_G54
 * 
 */
public class WheeledRob extends Robot {

	/**
	 * Constructor This constructor sets the speed and water capacity of a drone. It
	 * also executes the generic constructor method
	 */

	public WheeledRob(TheMap theMap, int xCoord, int yCoord) {
		super(theMap,  xCoord, yCoord);
		this.setImageFilePath("assets/wheeled-robot.png");
		this.setGraphic_priority(2);
		this.setSpeed(80);
		super.setPathFinder();
		this.water_capacity = 5000;
		this.water_level = 5000;
	}

	/**
	 * Path builder: provide the fastest path to the specified tile according to
	 * speeds & theMap
	 */
	/*
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
	*/
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
		this.speed = new Speed(speed, 0, 0, 0, speed);
	}

	@Override
	public void setSpeed() {
		setSpeed(80);
	};
}
