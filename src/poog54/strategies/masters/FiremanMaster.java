/**
 * An abstract class to represents the firefighters' strategies
 */
package poog54.strategies.masters;

import java.awt.Point;
import java.util.ListIterator;
import java.util.PriorityQueue;

import poog54.dataclasses.*;
import poog54.dataclasses.robots.*;
import poog54.io.Simulator;
import poog54.strategies.PathFinder;

/**
 * @author POO_G54
 *
 */
 public abstract class FiremanMaster {
	protected SimulationData data;

	/**
	 * @param sim
	 */
	public FiremanMaster(Simulator sim) {
		this.data = sim.getData();
	}

	/**
	 * @return the data
	 */
	public SimulationData getData() {
		return data;
	}

	/**
	 * @param data
	 */
	public void setData(SimulationData data) {
		this.data = data;
	}
	
	/**
	 * affect a fire target to a robot
	 * @param robot
	 * @param sim
	 * 
	 */
	abstract public void orderRobotToFire(Robot robot, Simulator sim);

	/**
	 * @param robot
	 * @param p
	 * @return the closest fire tile from the specified point, that is not
	 *         necessarily the robot position
	 */
	public Target getClosestFire(Robot robot, Point p) {
		TheMap theMap = this.data.getMap();
		ListIterator<WildFire> wfListIt = this.data.getWfList().listIterator();
		PathFinder pathFinder = new PathFinder(theMap.getNbLines(), theMap.getNbColums());
		WildFire wf = wfListIt.next();
		Target target, closestTarget = new Target(wf, pathFinder.Astar(p, wf.getCoord(), robot.getAlgoMap()));

		// go through the fire list and select the fire closest to the current position of this robot
		while (wfListIt.hasNext()) {
			wf = wfListIt.next();
			target = new Target(wf, pathFinder.Astar(p, wf.getCoord(), robot.getAlgoMap()));
			if (target.getPath().getTraveltime() < closestTarget.getPath().getTraveltime()) {
				// this fire is closer to the robot
				closestTarget = target;
			}
		}
		return closestTarget;
	}

	/**
	 * @param robot
	 * @return the closest fire tile from current position of the robot
	 */
	public Target getClosestFire(Robot robot) {
		return getClosestFire(robot, robot.getCoord());
	}

	/**
	 * @param robot
	 * @param p
	 * @return the farthest fire tile from the specified point, that is not
	 *         necessarily the robot position
	 */
	public Target getFarthestFire(Robot robot, Point p) {
		TheMap theMap = this.data.getMap();
		ListIterator<WildFire> wfListIt = this.data.getWfList().listIterator();
		PathFinder pathFinder = new PathFinder(theMap.getNbLines(), theMap.getNbColums());
		WildFire wf = wfListIt.next();
		Target target, farthestTarget = new Target(wf, pathFinder.Astar(p, wf.getCoord(), robot.getAlgoMap()));

		// go through the fire list and select the fire farthest to the current position of this robot
		while (wfListIt.hasNext()) {
			wf = wfListIt.next();
			target = new Target(wf, pathFinder.Astar(p, wf.getCoord(), robot.getAlgoMap()));
			if (target.getPath().getTraveltime() > farthestTarget.getPath().getTraveltime()) {
				// this fire is farther from the robot
				farthestTarget = target;
			}
		}
		return farthestTarget;
	}

	/**
	 * @param robot
	 * @return the farthest fire tile from the current position of the robot
	 */
	public Target getFarthestFire(Robot robot) {
		return getFarthestFire(robot, robot.getCoord());
	}

	/**
	 * @param robot
	 * @return the fire to which the fewest firefighters are assigned
	 */
	public Target getFireWithFewestRob(Robot robot) {
		int currentNum, selectedNum; // num of assigned robots
		WildFire wf;
		Robot currentRob;
		Target currentFire, selectedFire;
		ListIterator<Robot> robotListIt;
		ListIterator<WildFire> wfListIt;

		selectedFire = null;
		selectedNum = Integer.MAX_VALUE;
		wfListIt = this.data.getWfList().listIterator();

		// searches the fire with the fewest assigned robots
		while (wfListIt.hasNext()) {
			currentNum = 0;
			wf = wfListIt.next();
			currentFire = new Target(wf, robot.getPathToPoint(wf.getCoord()));
			robotListIt = this.data.getRobotList().listIterator();

			// counts the num of assigned robots
			while (robotListIt.hasNext()) {
				currentRob = robotListIt.next();
				if (currentRob.getTargetFire() != null) {
					if (currentRob.getTargetFire().getFire() == currentFire.getFire()) {
						currentNum++;
					}
				}
			}

			// selects the appropriate fire
			if (currentNum < selectedNum) {
				selectedNum = currentNum;
				selectedFire = currentFire;
			}
		}
		return selectedFire;
	}

	/**
	 * @param robot
	 * @return the fire which is the farthest from any other robot (most
	 *         isolated)
	 */
	public Target getMostIsolatedFire(Robot robot) {
		Robot r;
		WildFire wf;
		double closestDistance;
		double currentDistance = 0;
		Target currentRobot;
		Target closestRobot = null;
		Target mostIsolatedFire = null;
		ListIterator<Robot> robListIt;
		ListIterator<WildFire> wfListIt = this.data.getWfList().listIterator();
		
		// skips small fires if remains fires with an intensity > robot water capacity
		boolean skipSmallFires = this.data.getWfList().get(0).getIntensity()>robot.getWaterCapacity();
		
		// get the closest robot for each fire
		// and select the fire for which the distance (cost) to the closest robot is max
		while (wfListIt.hasNext()) {
			wf = wfListIt.next();
			closestDistance = Double.MAX_VALUE;
			robListIt = this.data.getRobotList().listIterator();

			// get the closest robot for this fire
			while (robListIt.hasNext()) {
				r = robListIt.next();
				if (r.getClass() != robot.getClass()) {
					//skip robots of the same type / team
					currentRobot = new Target(wf, r.getPathToPoint(wf.getCoord()));
					if (currentRobot.getPath().getTraveltime() < closestDistance) {
						closestDistance = currentRobot.getPath().getTraveltime();
						closestRobot = currentRobot;
					}
				}
			}

			// get the most isolated fire
			// and select it if its intensity is > poured amount of water
			if (closestRobot.getPath().getTraveltime() > currentDistance
					&& (closestRobot.getFire().getIntensity() >= robot.getWaterAmount()
					|| !skipSmallFires)) {
				currentDistance = closestRobot.getPath().getTraveltime();
				mostIsolatedFire = new Target(wf, robot.getPathToPoint(wf.getCoord()));
			}
		}
		return mostIsolatedFire;
	}

	
	/**
	 * @param wfPriorityQueue
	 * @return closest fire from a water tile
	 */
	public Target getFireClosestFromWater(PriorityQueue<Target> wfPriorityQueue) {
		Target currentFire, selectedFire = null;
		while (selectedFire == null) {
			currentFire = wfPriorityQueue.peek();
			if (this.data.getWfList().contains(currentFire.getFire())) {
				// this fire is still glowing
				// -> select it !
				selectedFire = currentFire;
			} else {
				// this fire is already extinguished
				// -> remove it from the priority queue
				wfPriorityQueue.remove();
			}
		}
		return selectedFire;
	}

	/**
	 * @param robot
	 * @return get the fire priority queue for the specified robot
	 */
	public PriorityQueue<Target> getFirePriorityQueue(Robot robot) {	
		double distance;
		WildFire wf;
		Point waterTile;
		Target target, closestWaterTarget=null;		
		ListIterator<Point> waterListIt;
		ListIterator<WildFire> wfListIt = this.data.getWfList().listIterator();
		PathFinder pathFinder = new PathFinder(this.data.getMap().getNbLines(), this.data.getMap().getNbColums());
		
		// fires which are sorted according to their proximity to water tiles
		// the closest ones have the highest priority and are at the top of the list
		PriorityQueue<Target> robPriorityFires = new PriorityQueue<Target>(this.data.getWfList().size(), (wf1, wf2) -> {
			return wf1.getPath().getTraveltime() < wf2.getPath().getTraveltime() ? -1
					: wf1.getPath().getTraveltime() > wf2.getPath().getTraveltime() ? 1 : 0;
		});

		// Get the closest water tile for every wild fire
		while (wfListIt.hasNext()) {
			wf = wfListIt.next();
			distance = Double.MAX_VALUE;
			waterListIt = this.data.getMap().getWaterTileListIt();
			while (waterListIt.hasNext()) {
				waterTile = waterListIt.next();
				target = new Target(wf, pathFinder.Astar(waterTile, wf.getCoord(), robot.getAlgoMap()));
				if (target.getPath().getTraveltime() < distance) {
					// this water tile is closer...
					closestWaterTarget = target;
					distance = closestWaterTarget.getPath().getTraveltime();
				}
			}
			
			// build the list of fire, sorted by closest water tile distance
			robPriorityFires.add(closestWaterTarget);
		}
		return robPriorityFires;
	}
}