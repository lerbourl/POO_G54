package poog54.dataclasses.robots;

import java.awt.Point;
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
	private int nextFreeTime;

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
	 * Fire target location: position of the targeted fire on the theMap Target fire
	 * path: path to the assigned fire. path[0] is always the current location.
	 */
	protected Target targetFire;

	/**
	 * Nearest water location: position of the water reserve on the theMap and path
	 * to it path[0] is always the current location.
	 */
	protected Target targetWater;

	/**
	 * Water capacity: tank size
	 */
	protected int waterCapacity;

	/**
	 * Water level: remaining water in the tank
	 */
	protected int waterLevel;

	/**
	 * Water amount: amount of water that a robot can pour at a time
	 */
	protected int waterAmount;

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
		this.nextFreeTime = 1; // after init strategy
	}

	/**
	 * @return algomap
	 */
	public AlgoTile[][] getAlgoMap() {

		AlgoTile[][] algomap = new AlgoTile[this.theMap.getNbLines()][this.theMap.getNbColums()];

		for (int i = 0; i < this.theMap.getNbLines(); i++) {
			for (int j = 0; j < this.theMap.getNbColums(); j++) {
				algomap[i][j] = new AlgoTile(this.getCrossingTileTime(new Point(i, j)), new Point(i, j));
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

	/**
	 * search the closest water tile from the actual position
	 */
	public void locateClosestWaterTile() {
		ListIterator<Point> waterTileListIt;
		Target mapTarget;
		Point waterPoint;

		// read the map and get the water tiles position
		waterTileListIt = this.theMap.getWaterTileListIt();
		waterPoint = waterTileListIt.next();
		this.targetWater = new Target(waterPoint, getPathToPoint(waterPoint));
		// enumerates water tiles and selects the closest one
		while (waterTileListIt.hasNext()) {
			waterPoint = waterTileListIt.next();
			mapTarget = new Target(waterPoint, getPathToPoint(waterPoint));
			if (mapTarget.getPath().getTraveltime() < this.targetWater.getPath().getTraveltime()) {
				// this water tile is closer
				this.targetWater = mapTarget;
			}
		}
	}

	/**
	 * @return the next_free_time
	 */
	public int getNextFreeTime() {
		return nextFreeTime;
	}

	/**
	 * @param p
	 * @return the path from the current location of the robot and the specified destination
	 */
	public Path getPathToPoint(Point p) {
		Path path = pathFinder.Astar(this.getCoord(), p, this.getAlgoMap());
		if (path.getThepath().size() < 2) {
			// robots remains on the same tile
			path.getThepath().clear();
		} else {
			// stop before the tile
			path.removeLast();
		}
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

	/**
	 * @return speeds data
	 */
	public Speed getSpeed() {
		return speed;
	}

	/**
	 * @param p
	 * @return the time to enter a tile of the type, in seconds
	 */
	public int getCrossingTileTime(Point p) {
		if(p==null) return 0;
		TypeField type = this.theMap.getTile(p).getTypeField();
		double speed = 0;
		switch (type) {
		case EAU:
			speed = this.getSpeed().getSpeedWater();
			break;
		case FORET:
			speed = this.getSpeed().getSpeedForest();
			break;
		case ROCHE:
			speed = this.getSpeed().getSpeedRock();
			break;
		case HABITAT:
			speed = this.getSpeed().getSpeedHouse();
			break;
		case TERRAIN_LIBRE:
			speed = this.getSpeed().getSpeedEmptyField();
			break;
		default:
			break;
		}
		if (speed == 0) return Integer.MAX_VALUE;
		else return (int) (this.theMap.getTileSize() / (speed * 1000 / 3600));
		/* t (s) = d (metres ) / v (km / h)*/
	}

	/**
	 * @return the amount of water that a robot can pour out
	 */
	public int getWaterAmount() {
		return waterAmount;
	}

	/**
	 * @return the water_capacity
	 */
	public int getWaterCapacity() {
		return waterCapacity;
	}

	/**
	 * @return the water_level
	 */
	public int getWaterLevel() {
		return waterLevel;
	}

	/**
	 * @param p
	 */
	public void move(Point p) {
		if (p != null) {
			if (this.theMap.tileIsIn(p)) {
				this.setCoord(p);
			} else
				System.out.println("Move robot on non allowed tile");
		} else {
			System.out.println("t=" + (this.nextFreeTime - 1) + ": " + this + " remains on the same tile");
		}
	}

	/**
	 * Pouring water: extinguish fire
	 */
	public void pourOut() {
		if (this.waterLevel > 0) {
			this.waterLevel -= this.waterAmount;
			this.targetFire.getFire().setIntensity(this.targetFire.getFire().getIntensity() - this.waterAmount);
		}
	}

	/**
	 * @param next_free_time the next_free_time to set
	 */
	public void setNextFreeTime(int next_free_time) {
		this.nextFreeTime = next_free_time;
	}

	/**
	 * 
	 */
	public abstract void setSpeed();

	/**
	 * Speed assignment Abstract cause it depends on the Robot Type
	 * @param speed 
	 */
	public abstract void setSpeed(int speed);

	/** Target fire assignment 
	 * @param fire
	 */
	public void setTargetFire(WildFire fire) {
		if (fire == null) {
			// cancel fire assignment
			this.targetFire = null;
		} else {
			this.targetFire = new Target(fire, getPathToPoint(fire.getCoord()));
		}
	}

	/**
	 * Tank up: fill tank
	 */
	public void tankUp() {

		waterLevel = waterCapacity;

	}

	@Override
	public String toString() {
		return "robot [" + this.getCoord().x + ";" + this.getCoord().y + "] <" + this.waterLevel + "L>";
	}
}
