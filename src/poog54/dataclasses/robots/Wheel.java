package poog54.dataclasses.robots;

import gui.GUISimulator;
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
public class Wheel extends Robot{

	/** Constructor with custom speed
	 *  This constructor sets the specific speed and water capacity of a drone.
	 *  It also executes the generic constructor method
	 */
	Wheel(Map map, Tile inital_location, int custom_speed, GUISimulator gui) {
		super(map, inital_location, custom_speed, gui);
		
		int speed;
		if (custom_speed > 80) {
			speed = 80;
		}
		else {
			speed = custom_speed;
		}
		this.speed.empty_field = speed;
		this.speed.house = speed;
		this.speed.forest = 0;
		this.speed.water = 0;
		this.speed.rock = 0;

		this.water_capacity = 5000;
		this.water_level = 5000;
	}

	/** Draw: display a drone on the map */
	@Override
	public void draw(){
		
		//TODO

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
