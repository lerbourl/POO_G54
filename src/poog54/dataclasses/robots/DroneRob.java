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
public class DroneRob extends Robot{
	
	/** Constructor with default speed
	 *  This constructor sets the specific speed and water capacity of a drone.
	 *  It also executes the generic constructor method
	 */
	
	DroneRob(Map map, Tile inital_location) {
		this(map, inital_location, 100);
	}
	
	/** Constructor with custom speed
	 *  This constructor sets the specific speed and water capacity of a drone.
	 *  It also executes the generic constructor method
	 */

	DroneRob(Map map, Tile inital_location, int custom_speed) {
		super(map, inital_location, custom_speed);
		
		int speed;
		if (custom_speed > 150) {
			speed = 150;
		}
		else {
			speed = custom_speed;
		}
		this.speed.empty_field = speed;
		this.speed.forest = speed;
		this.speed.house = speed;
		this.speed.rock = speed;
		this.speed.water = speed;

		this.water_capacity = 10000;
		this.water_level = 10000;

	}

	/** Path builder: provide the fastest path to the specified tile according to speeds & map 
	 */
	@Override
	public Target buildTargetPath(Tile location){
		Target target = new Target();
		target.path = new Tile[2];
		
		//TODO compute the fastest (full) path
		target.location = location;
		target.path[0]=this.location;
		target.path[1]=location;
		
		return target;
	}

	/** Tank up: fill tank
	 */
	@Override
	public void tankUp(){
		
		//TODO

	}

	/** Pouring water: extinguish fire
	 */
	@Override
	public void pourOut(int water_volume){
		
		//TODO

	}
}
