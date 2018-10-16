package poog54.dataclasses.robots;

import poog54.dataclasses.*;

/**
  * Represents a paw firefighter.
  * This class inherits from the generic Robot class
  *
  * The paw firefighter speed cannot exceed 30.
  * The speed is divided by 3 on rocks.
  * The paw firefighter cannot go on water tiles.
  * 
  * 
  * The paw firefighter never tanks up
  * The tank capacity is supposed infinite
  * The pour out capacity is 10 liters
  * The pour out delay is 1 second
  * 
 */

/**
 * @author POO_G54
 * 
 */
public class WalkingRob extends Robot{
	
	/** Constructor with default speed
	 *  This constructor sets the specific speed and water capacity of a drone.
	 *  It also executes the generic constructor method
	 */

	WalkingRob(Map map, Tile inital_location) {
		this(map, inital_location, 30);
	}
	
	/** Constructor with custom speed
	 *  This constructor sets the specific speed and water capacity of a drone.
	 *  It also executes the generic constructor method
	 */

	WalkingRob(Map map, Tile inital_location, int custom_speed) {
		super(map, inital_location, custom_speed);
		
		int speed;
		if (custom_speed > 30) {
			speed = 30;
		}
		else {
			speed = custom_speed;
		}
		this.speed.empty_field = speed;
		this.speed.forest = speed;
		this.speed.house = speed;
		this.speed.rock = speed/3;
		this.speed.water = 0;

		this.water_capacity = Integer.MAX_VALUE;
		this.water_level = Integer.MAX_VALUE;

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
		//Should not decrease water level

	}
}
