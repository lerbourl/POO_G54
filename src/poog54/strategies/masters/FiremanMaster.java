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
abstract public class FiremanMaster {
	protected SimulationData data;

	public FiremanMaster(Simulator sim) {
		this.data = sim.getData();
	}

	/**
	 * @return the data
	 */
	public SimulationData getData() {
		return data;
	}

	public void setData(SimulationData data) {
		this.data = data;
	}
	/**
	 * affect a fire target to a robot
	 * @param robot
	 * @param simulator
	 * 
	 */
	abstract public void orderRobotToFire(Robot rob, Simulator sim);

	/**
	 * @param robot
	 * @param point
	 * @return the closest fire tile from the specified point, that is not
	 *         necessarily the robot position
	 */
	public Target getClosestFire(Robot rob, Point p) {
		TheMap theMap = this.data.getMap();
		ListIterator<WildFire> wfListIt = this.data.getWfList().listIterator();
		PathFinder pathFinder = new PathFinder(theMap.getNbLines(), theMap.getNbColums());
		WildFire wf = wfListIt.next();
		Target target, closestTarget = new Target(wf, pathFinder.Astar(p, wf.getCoord(), rob.getAlgoMap()));

		while (wfListIt.hasNext()) {
			wf = wfListIt.next();
			target = new Target(wf, pathFinder.Astar(p, wf.getCoord(), rob.getAlgoMap()));
			if (target.getPath().getTraveltime() < closestTarget.getPath().getTraveltime()) {
				closestTarget = target;
			}
		}
		return closestTarget;
	}

	/**
	 * @param robot
	 * @return the closest fire tile from current position of the robot
	 */
	public Target getClosestFire(Robot rob) {
		return getClosestFire(rob, rob.getCoord());
	}

	/**
	 * @param robot
	 * @param point
	 * @return the farthest fire tile from the specified point, that is not
	 *         necessarily the robot position
	 */
	public Target getFarthestFire(Robot rob, Point p) {
		TheMap theMap = this.data.getMap();
		ListIterator<WildFire> wfListIt = this.data.getWfList().listIterator();
		PathFinder pathFinder = new PathFinder(theMap.getNbLines(), theMap.getNbColums());
		WildFire wf = wfListIt.next();
		Target target, farthestTarget = new Target(wf, pathFinder.Astar(p, wf.getCoord(), rob.getAlgoMap()));

		while (wfListIt.hasNext()) {
			wf = wfListIt.next();
			target = new Target(wf, pathFinder.Astar(p, wf.getCoord(), rob.getAlgoMap()));
			if (target.getPath().getTraveltime() > farthestTarget.getPath().getTraveltime()) {
				farthestTarget = target;
			}
		}
		return farthestTarget;
	}

	/**
	 * @param robot
	 * @return the farthest fire tile from the current position of the robot
	 */
	public Target getFarthestFire(Robot rob) {
		return getFarthestFire(rob, rob.getCoord());
	}

	/**
	 * @param robot
	 * @return the fire to which the fewest firefighters are assigned
	 */
	public Target getFireWithFewestRob(Robot rob) {
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
			currentFire = new Target(wf, rob.getPathToPoint(wf.getCoord()));
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
	public Target getMostIsolatedFire(Robot rob) {
		Robot r;
		WildFire wf;
		double closestDistance;
		double currentDistance = 0;
		Target currentRobot;
		Target closestRobot = null;
		Target mostIsolatedFire = null;
		ListIterator<Robot> robListIt;
		ListIterator<WildFire> wfListIt = this.data.getWfList().listIterator();

		// get the closest robot for each fire
		// and select the fire for which the distance to the closest robot is max
		while (wfListIt.hasNext()) {
			wf = wfListIt.next();
			closestDistance = Double.MAX_VALUE;
			robListIt = this.data.getRobotList().listIterator();

			// get the closest robot for this fire
			while (robListIt.hasNext()) {
				r = robListIt.next();
				if (r.getClass() != rob.getClass()) {
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
					&& closestRobot.getFire().getIntensity() >= rob.getWater_amount()) {
				currentDistance = closestRobot.getPath().getTraveltime();
				mostIsolatedFire = new Target(wf, rob.getPathToPoint(wf.getCoord()));
			}
		}
		
		if (mostIsolatedFire == null) {
			// there are only small fires remaining
			// select the closest
			mostIsolatedFire = getClosestFire(rob);
		}
		
		return mostIsolatedFire;
	}

	/**
	 * @param robot
	 * @return the fire which is the closest from any water tile
	 */
	public Target getFireClosestFromWater(Robot rob, PriorityQueue<Target> wfPriorityQueue) {
		Target currentFire, selectedFire = null;
		while (selectedFire == null) {
			currentFire = wfPriorityQueue.peek();
			if (this.data.getWfList().contains(currentFire.getFire())) {
				selectedFire = currentFire;
			} else {
				wfPriorityQueue.remove();
			}
		}
		return selectedFire;
	}

	/**
	 * @param robot
	 * @return get the fire priority queue for the specified robot
	 */
	public PriorityQueue<Target> getFirePriorityQueue(Robot rob) {	
		double distance;
		WildFire wf;
		Point waterTile;
		Target target, closestWaterTarget=null;		
		ListIterator<Point> waterListIt;
		ListIterator<WildFire> wfListIt = this.data.getWfList().listIterator();
		PathFinder pathFinder = new PathFinder(this.data.getMap().getNbLines(), this.data.getMap().getNbColums());
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
				target = new Target(wf, pathFinder.Astar(waterTile, wf.getCoord(), rob.getAlgoMap()));
				if (target.getPath().getTraveltime() < distance) {
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