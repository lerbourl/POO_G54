package poog54.robots;
import poog54.dataclasses.Tile;
import poog54.enums.RobotState;
import gui.GUISimulator;

/**
  * Represents a basic firefighter robot.
  * This class is abstract because you can not (must) instantiate a generic robot.
  * Only more specific class robots can be.
  *
  * A robot has access to the following external data:
  * - the map (to locate fires, water points and calculate its trajectories)
  * - the graphic window (to be able to display its current position)
  *
  * A robot moves one cell / tile at a time, as mentioned in the specifications.
  * A robot refreshes its position and state at every simulation 'step' or 'cycle'.
 */

/**
 * @author POO_G54
 * 
 */

public abstract class Robot {

	/** Simulation window */
	protected GUISimulator gui;
	
	/** Current state:
	 * Idle, Moving to fire, moving to water reserve, pouring, tanking up
	 */
	protected RobotState state;

	/** Map */
	//TODO: REPLACE MAP[][] with correct class implementation
	protected Tile map[][];
	
	/** Current location:
	 * position on the map, active cell or tile
	 */
	protected Tile location;
	
	/** Speeds:
	 * depends on the type of field
	 */
	protected Speed speed;

	/** Fire target location:
	 * position of the targeted fire on the map
	 */
	protected Tile target_fire;

	/** Target fire path:
	 * path to the assigned fire.
	 * path[0] is always the current location.
	 */
	protected Tile target_fire_path[];

	/** Nearest water location:
	 * position of the water reserve on the map
	 */
	protected Tile target_water;

	/** Path to the water reserve
	 * path[0] is always the current location.
	 */
	protected Tile target_water_path[];
	
	/** Water capacity:
	 * tank size
	 */
	protected int water_capacity;

	/** Water level:
	 * remaining water in the tank
	 */
	protected int water_level;
	
	/** Constructor */
	//TODO: REPLACE MAP[][] with correct class implementation
	Robot(Tile map[][], Tile inital_location, int initial_water_level, GUISimulator gui){
		//Speeds and water capacity must be set in the overridden constructor
		this.gui = gui;
		this.map = map;
		this.location = inital_location;
		this.water_level = initial_water_level;
		this.state = RobotState.IDLE; // changes when the firefighter chief set a fire target 
	}
	
	/** Target fire assignment*/
	public void setTargetFire(Tile target_fire){
		this.target_fire = target_fire;
		this.target_fire_path = this.buildTargetPath(target_fire);
	}
	
	/** Target water assignment*/
	public void setTargetWater(Tile target_water){
		this.target_water = target_water;
		this.target_water_path = this.buildTargetPath(target_water);
	}

	/** Path builder:
	 *  provide the fastest path to the specified target according to speeds & map 
	 */
	public Tile[] buildTargetPath(Tile target){
		Tile path[]=new Tile[2];
		
		//TODO
		path[0]=this.location;
		path[1]=target;
		
		return path;
	}

	/** Tank up:
	 *  fill tank
	 */
	public void tankUp(){
		
		//TODO

	}

	/** Pouring water:
	 *  extinguish fire
	 */
	public void pouring(int water_volume){
		
		//TODO

	}

	/** Draw: display robot on the map */
	public void draw(){
		
		//TODO

	}
}
