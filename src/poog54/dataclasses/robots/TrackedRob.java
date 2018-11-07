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
	 * Constructor This constructor sets the speed and the water capacity of a
	 * tracked robot. It also executes the generic constructor method
	 * @param theMap 
	 * @param xCoord 
	 * @param yCoord 
	 */

	public TrackedRob(TheMap theMap, int xCoord, int yCoord) {
		super(theMap,  xCoord, yCoord);
		this.setImageFilePath("assets/tracked-robot.png");
		this.setGraphic_priority(2);
		setSpeed();
		this.waterCapacity = 2000;
		this.waterLevel = 2000;
		this.waterAmount = 100;
		this.pourTime = 8;
		this.tankUpTime = 5*60; // 5min
	}

	@Override
	public void setSpeed(int speed) {
		/* no speed limitation here */
		if (speed > 80)
			speed = 80;
		this.speed = new Speed(speed, speed / 2, 0, 0, speed);
	}

	@Override
	public void setSpeed() {
		setSpeed(60);
	};
	
	@Override
	public String toString() {
		return "Tracked " + super.toString();
	}
}
