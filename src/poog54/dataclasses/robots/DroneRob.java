package poog54.dataclasses.robots;

import poog54.dataclasses.*;

/**
  * Represents a drone firefighter.
  * This class inherits from the generic Robot class
  *
  * The drone speed is the same whatever is the field type.
  * The speed cannot exceed 150.
  * 
  * The drone can tank up in a water tile
  * The tank capacity is 10000 liters
  * The tank up delay is 30 minutes
  * The pour out capacity is 10000 liters
  * The pour out delay is 30 seconds
  * 
  * The firefighter is supposed to be 100% full when the simulation begins
 */

/**
 * @author POO_G54
 * 
 */
public class DroneRob extends Robot {

	/**
	 * Constructor This constructor sets the speed and water capacity of a drone. It
	 * also executes the generic constructor method
	 */

	public DroneRob(TheMap theMap, int xCoord, int yCoord) {
		super(theMap,  xCoord, yCoord);
		this.setImageFilePath("assets/drone-robot.png");
		this.setGraphic_priority(2);
		setSpeed();
		super.setPathFinder();
		this.water_capacity = 10000;
		this.water_level = 10000;
		this.water_amount = 10000;
		this.pourTime = 30;
		this.tankUpTime = 1800; // 30min
	}

	@Override
	public void setSpeed(int speed) {
		/* no speed limitation here */
		if (speed > 150)
			speed = 150;
		this.speed = new Speed(speed, speed, speed, speed, speed);
	}

	@Override
	public void setSpeed() {
		setSpeed(100);
	};

	@Override
	public String toString() {
		return "Drone robot [" 
	            + this.getCoord().x + ";" + this.getCoord().y + "] (" 
				+ this.state + ") <"
				+ this.water_level + ">";
	}

}
