package poog54.dataclasses.robots;

import poog54.dataclasses.*;

/**
  * Represents a walking firefighter.
  * This class inherits from the generic Robot class
  *
  * The walking firefighter speed cannot exceed 30.
  * The speed is divided by 3 on rocks.
  * The walking firefighter cannot go on water tiles.
  * 
  * 
  * The walking firefighter never tanks up
  * The tank capacity is supposed infinite
  * The pour out capacity is 10 liters
  * The pour out delay is 1 second
  * 
 */

/**
 * @author POO_G54
 * 
 */
public class WalkingRob extends Robot {

	/**
	 * Constructor This constructor sets the speed and water capacity of a drone. It
	 * also executes the generic constructor method
	 */

	public WalkingRob(TheMap theMap, int xCoord, int yCoord) {
		super(theMap,  xCoord, yCoord);
		this.setImageFilePath("assets/walking-robot.png");
		this.setGraphic_priority(2);
		setSpeed();
		super.setPathFinder();
		this.water_capacity = Integer.MAX_VALUE;
		this.water_level = Integer.MAX_VALUE;
		this.water_amount = 10;
		this.pourTime = 1;
	}

	/**
	 * Pouring water: extinguish fire
	 */
	@Override
	public void pourOut() {
		// water level is never decreased
		this.targetFire.fire.setIntensity(this.targetFire.fire.getIntensity() - this.water_amount);
	}

	@Override
	public void setSpeed() {
		this.speed = new Speed(30, 30, 10, 0, 30);
	}

	@Override
	public void setSpeed(int speed) {
		setSpeed();
	};
	
	@Override
	public String toString() {
		return "Walking " + super.toString();
	}

}
