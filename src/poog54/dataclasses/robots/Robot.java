package poog54.dataclasses.robots;
import poog54.dataclasses.Tile;
import poog54.enums.RobotState;
import gui.GUISimulator;

/**
  * Represents a basic firefighter robot.
  * This class is abstract because you can (should) not instantiate a generic robot,
  * more specific robot classes can.
  *
  * A robot has access to the following external data:
  * - the map (to locate fires, water points and compute trajectories)
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
	//TODO: REPLACE MAP[][] with correct class implementation louis : UTILE?
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
	 *  position of the targeted fire on the map
	 *  Target fire path:
	 *  path to the assigned fire.
	 *  path[0] is always the current location.
	 */
	protected Target fire;

	/** Nearest water location:
	 *  position of the water reserve on the map and path to it
	 *  path[0] is always the current location.
	 */
	protected Target water;
	
	/** Water capacity:
	 * tank size
	 */
	protected int water_capacity;

	/** Water level:
	 * remaining water in the tank
	 */
	protected int water_level;
	
	/** Constructor with default speed
	 *  Initialises the generic attributes of a firefighter:
	 *  - gui
	 *  - map
	 *  - initial location
	 *  - state (IDLE)
	 */
	//TODO: REPLACE MAP[][] with correct class implementation
	Robot(Tile map[][], Tile initial_location, GUISimulator gui){
		//Speeds and water capacity must be set in the child constructors
		this.gui = gui;
		this.map = map;
		this.location = initial_location;
		this.state = RobotState.IDLE; // changes when the firefighter chief set a fire target 
	}
	
	/** Constructor with custom speed
	 *  Initialises the generic attributes of a firefighter:
	 *  - gui
	 *  - map
	 *  - initial location
	 *  - state (IDLE)
	 */
	//TODO: REPLACE MAP[][] with correct class implementation
	Robot(Tile map[][], Tile initial_location, int custom_speed, GUISimulator gui){
		this(map, initial_location, gui);
	}
	
	/** Target fire assignment*/
	public void setTargetFire(Target fire){
		this.fire = fire;
	}
	
	/** Target water assignment*/
	public void setTargetWater(Target water){
		this.water = water;
	}

	/** Target builder:
	 *  - provides the fastest path to the specified tile according to speeds & map
	 *  - provides the time required to reach the target 
	 */
	public Target buildTargetPath(Tile location){
		Target target = new Target();
		target.path = new Tile[2];
		
		//TODO compute the fastest (full) path
		target.location = location;
		target.path[0]=this.location;
		target.path[1]=location;
		
		return target;
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
	public void pourOut(int water_volume){
		
		//TODO

	}

	/** Draw: display robot on the map */
	public void draw(){
		
		//TODO

	}
}