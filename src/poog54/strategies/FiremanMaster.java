/**
 * An abstract class to represents the firefighters' strategies
 */
package poog54.strategies;

import java.awt.Point;
import java.util.ListIterator;

import poog54.dataclasses.*;
import poog54.dataclasses.robots.*;
import poog54.io.Simulator;

/**
 * @author POO_G54
 *
 */
abstract public class FiremanMaster {
	protected Simulator sim;
	protected SimulationData data;

	public FiremanMaster(Simulator sim) {
		this.sim = sim;
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

	abstract public void orderRobotToFire(Robot rob, Simulator sim);

	/**
	 * @param robot
	 * @param point
	 * @return the closest fire tile from the specified point, that is not
	 *         necessarily the robot position
	 */
	public Target getClosestFire(Robot rob, Point p) {
		TheMap theMap = this.data.getMap();
		ListIterator<WildFire> wfListIt = sim.getData().getWfList().listIterator();
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
		ListIterator<WildFire> wfListIt = sim.getData().getWfList().listIterator();
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
	 * @return the closest fire tile from current position of the robot
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
		wfListIt = this.sim.getData().getWfList().listIterator();

		// searches the fire with the fewest assigned robots
		while (wfListIt.hasNext()) {
			currentNum = 0;
			wf = wfListIt.next();
			currentFire = new Target(wf, rob.getPathToPoint(wf.getCoord()));
			robotListIt = this.sim.getData().getRobotList().listIterator();

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
		ListIterator<WildFire> wfListIt = sim.getData().getWfList().listIterator();

		// get the closest robot for each fire
		// and select the fire for which the distance to the closest robot is
		// max
		while (wfListIt.hasNext()) {
			wf = wfListIt.next();
			closestDistance = Double.MAX_VALUE;
			robListIt = sim.getData().getRobotList().listIterator();

			// get the closest robot for this fire
			while (robListIt.hasNext()) {
				r = robListIt.next();
				if (r != rob) {
					//skip the current drone robot
					currentRobot = new Target(wf, r.getPathToPoint(wf.getCoord()));
					if (currentRobot.getPath().getTraveltime() < closestDistance) {
						closestDistance = currentRobot.getPath().getTraveltime();
						closestRobot = currentRobot;
					}

				}
			}

			// get the most isolated fire
			if (closestRobot.getPath().getTraveltime() > currentDistance) {
				currentDistance = closestRobot.getPath().getTraveltime();
				mostIsolatedFire = new Target(wf, rob.getPathToPoint(wf.getCoord()));
			}
		}
		return mostIsolatedFire;
	}

	/**
	 * @param robot
	 * @return the fire which is the closest from any water tile
	 */
	public Target getFireClosestFromWater(Robot rob) {
		WildFire wf;
		ListIterator<Point> waterListIt;
		TheMap theMap = this.data.getMap();
		double currentDistance = Double.MAX_VALUE;
		ListIterator<WildFire> wfListIt = sim.getData().getWfList().listIterator();
		PathFinder pathFinder = new PathFinder(theMap.getNbLines(), theMap.getNbColums());
		Target currentWater, closestWater, selectedFire = null;

		// get the closest water tile for each fire
		while (wfListIt.hasNext()) {
			wf = wfListIt.next();
			waterListIt = sim.getData().getMap().getWaterTileListIt();
			closestWater = new Target(wf, pathFinder.Astar(wf.getCoord(), waterListIt.next(), rob.getAlgoMap()));

			// get the closest water tile for this fire
			while (waterListIt.hasNext()) {
				currentWater = new Target(wf, pathFinder.Astar(wf.getCoord(), waterListIt.next(), rob.getAlgoMap()));
				if (currentWater.getPath().getTraveltime() < closestWater.getPath().getTraveltime()) {
					closestWater = currentWater;
				}
			}

			// get the most isolated fire
			if (closestWater.getPath().getTraveltime() < currentDistance) {
				currentDistance = closestWater.getPath().getTraveltime();
				selectedFire = new Target(wf, rob.getPathToPoint(wf.getCoord()));
			}
		}
		return selectedFire;
	}
}