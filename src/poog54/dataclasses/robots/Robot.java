package poog54.dataclasses.robots;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import poog54.dataclasses.*;
import poog54.enums.*;
import poog54.io.Drawable;
import poog54.strategies.AlgoTile;
import poog54.strategies.Path;
import poog54.strategies.PathFinder;

/**
  * Represents a basic firefighter robot.
  * This class is abstract because you can (should) not instantiate a generic robot,
  * more specific robot classes can.
  *
  * A robot has access to the following external data:
  * - the theMap (to locate fires, water points and compute trajectories)
  *
  * A robot moves one cell / tile at a time, as mentioned in the specifications.
  * A robot has no need to know the tile on which he is, he can have it thanks to the map !
 */

/**
 * @author POO_G54
 * 
 */

public abstract class Robot extends Drawable {

	/**
	 * The time the robot will be free to be involved into a new event an action
	 */
	private int next_free_time;

	/**
	 * time needed to pour an amount of water
	 */
	protected int pourTime;

	/**
	 * time needed to fill the water tank
	 */
	protected int tankUpTime;

	/** TheMap */
	protected TheMap theMap;

	/**
	 * Speeds: depends on the type of field
	 */
	protected Speed speed;

	/**
	 * Fire target location: position of the targeted fire on the theMap Target
	 * fire path: path to the assigned fire. path[0] is always the current
	 * location.
	 */
	protected Target targetFire;

	/**
	 * Nearest water location: position of the water reserve on the theMap and
	 * path to it path[0] is always the current location.
	 */
	protected Target targetWater;

	/**
	 * Water capacity: tank size
	 */
	protected int water_capacity;

	/**
	 * Water level: remaining water in the tank
	 */
	protected int water_level;

	/**
	 * Water amount: amount of water that a robot can pour at a time
	 */
	protected int water_amount;

	protected PathFinder pathFinder;

	/**
	 * Constructor with default speed Initialises the generic attributes of a
	 * firefighter:- theMap - initial location - state (IDLE)
	 */

	Robot(TheMap theMap, int xCoord, int yCoord) {
		super(xCoord, yCoord);
		// Speeds and water capacity must be set in the child constructors
		this.theMap = theMap;
		this.pathFinder = new PathFinder(theMap.getNbLines(), theMap.getNbColums());
		this.next_free_time = 1; // after init strategy
	}

	protected AlgoTile[][] getAlgoMap() {

		AlgoTile[][] algomap = new AlgoTile[this.theMap.getNbLines()][this.theMap.getNbColums()];

		for (int i = 0; i < this.theMap.getNbLines(); i++) {
			for (int j = 0; j < this.theMap.getNbColums(); j++) {
				algomap[i][j] = new AlgoTile(this.getTimeType(new Point(i, j)), new Point(i, j));
			}
		}
		return algomap;
	}

	/**
	 * @return the fire
	 */
	public Target getTargetFire() {
		return targetFire;
	}

	/**
	 * @return the water target
	 */
	public Target getTargetWater() {
		return this.targetWater;
	}

	public TheMap getMap() {
		return this.theMap;
	}

	/**
	 * search the closest water tile from the actual position
	 */
	public void locateClosestWaterTile() {
		ListIterator<Point> waterTileListIt;
		List<Point> waterTileList;
		Target mapTarget;
		Point waterPoint;
		Tile mapTile;
		int i,j;
		
		//read the map and get the water tiles position
		waterTileList = new ArrayList<Point>();
		for(i=0;i<this.theMap.getNbLines();i++){
			for(j=0;j<this.theMap.getNbLines();j++){
				mapTile=this.theMap.getTile(i, j);
				if(mapTile.getTypeField()==TypeField.EAU){
					waterTileList.add(mapTile.getCoord());
				}
			}
		}
		waterTileListIt=waterTileList.listIterator();
		waterPoint = waterTileListIt.next();
		this.targetWater = new Target(waterPoint, getPathToPoint(waterPoint));
		//enumerates water tiles and selects the closest
		while(waterTileListIt.hasNext()){
			waterPoint = waterTileListIt.next();
			mapTarget = new Target(waterPoint, getPathToPoint(waterPoint));
			if(mapTarget.getPath().getTraveltime()<this.targetWater.getPath().getTraveltime()){
				//this water tile is closer
				this.targetWater = new Target(waterPoint, getPathToPoint(waterPoint));
			}
		}
	}

	/**
	 * @return the next_free_time
	 */
	public int getNext_free_time() {
		return next_free_time;
	}

	public Path getPathToPoint(Point p) {
		Path path = pathFinder.Astar(this.getCoord(), p, this.getAlgoMap());
		path.removeLast(); // stop before the tile
		return path;
	}

	/**
	 * @return the time needed to pour an amount of water (in s)
	 */
	public int getPourTime() {
		return pourTime;
	}

	/**
	 * @return the time needed to fill the water tank (in s)
	 */
	public int getTankUpTime() {
		return tankUpTime;
	}

	public Speed getSpeed() {
		return speed;
	}

	public Tile getTile() {
		return this.theMap.getTile(this.getCoord().x, this.getCoord().y);
	}

	/* Return the time to enter a tile of the type, in seconds */
	public double getTimeType(Point p) {
		TypeField type = this.theMap.getTile(p).getTypeField();

		double speed = 0.000001; // for null speeds

		switch (type) {
		case EAU:
			speed += this.getSpeed().getSpeedWater();
			break;
		case FORET:
			speed += this.getSpeed().getSpeedForest();
			break;
		case ROCHE:
			speed += this.getSpeed().getSpeedRock();
			break;
		case HABITAT:
			speed += this.getSpeed().getSpeedHouse();
			break;
		case TERRAIN_LIBRE:
			speed += this.getSpeed().getSpeedEmptyField();
			break;
		default:
			break;
		}
		return this.theMap.getTileSize() / (speed * 1000 / 3600) ;
		/* t (s) = d (metres ) / v (km / h)*/
	}

	/**
	 * @return the amount of water that a robot can pour out
	 */
	public int getWater_amount() {
		return water_amount;
	}

	/**
	 * @return the water_capacity
	 */
	public int getWater_capacity() {
		return water_capacity;
	}

	/**
	 * @return the water_level
	 */
	public int getWater_level() {
		return water_level;
	}

	public void move(Point p) {
		if (p != null) {
			if (this.theMap.tileIsIn(p)) {
				this.setCoord(p);
			} else
				System.out.println("Move robot on non allowed tile");
		} else {
			System.out.println(this + " remains on the same tile");
		}
	}

	/**
	 * Pouring water: extinguish fire
	 */
	public void pourOut() {
		if (this.water_level > 0) {
			this.water_level -= this.water_amount;
			this.targetFire.getFire().setIntensity(this.targetFire.getFire().getIntensity() - this.water_amount);
		}
	}

	/**
	 * @param next_free_time
	 *            the next_free_time to set
	 */
	public void setNext_free_time(int next_free_time) {
		this.next_free_time = next_free_time;
	}

	public abstract void setSpeed();

	/**
	 * Speed assignment Abstract cause it depends on the Robot Type
	 */
	public abstract void setSpeed(int speed);

	/** Target fire assignment */
	public void setTargetFire(WildFire fire) {
		if (fire == null) {
			// cancel fire assignment
			this.targetFire = null;
		} else {
			this.targetFire = new Target(fire, getPathToPoint(fire.getCoord()));
		}
	}
	
	/** Target fire assignment */
	public void setTargetFire(Target fire) {
			this.targetFire = fire;
	}
	
	/** Target water assignment */
	public void setTargetWater(Point water) {
		if (water == null) {
			// reset water tile ref.
			this.targetWater = null;
		} else {
			this.targetWater = new Target(water, getPathToPoint(water));
		}
	}

	/**
	 * set the amount of water that a robot can pour
	 * 
	 * @param water_amount
	 */
	public void setWater_amount(int water_amount) {
		this.water_capacity = water_amount;
	}

	/**
	 * @param water_capacity
	 *            the water_capacity to set
	 */
	public void setWater_capacity(int water_capacity) {
		this.water_capacity = water_capacity;
	}

	/**
	 * @param water_level
	 *            the water_level to set
	 */
	public void setWater_level(int water_level) {
		this.water_level = water_level;
	}

	/**
	 * Tank up: fill tank
	 */
	public void tankUp() {

		water_level = water_capacity;

	}
	@Override
	public String toString() {
		return "robot [" 
	            + this.getCoord().x + ";" + this.getCoord().y + "] <"
				+ this.water_level + "L>";
	}
}
