/**
 * An abstract class to represents the firefighters' strategies
 */
package poog54.strategies;

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

	public Target getClosestFire(Robot rob) {
		Target target, closestTarget;
		WildFire wf;
		ListIterator<WildFire> wfListIt = sim.getData().getWfList().listIterator();

		wf = wfListIt.next();
		closestTarget = new Target(wf, rob.getPathToPoint(wf.getCoord()));
		while (wfListIt.hasNext()) {
			wf = wfListIt.next();
			target = new Target(wf, rob.getPathToPoint(wf.getCoord()));
			if (target.getPath().getTraveltime() < closestTarget.getPath().getTraveltime()) {
				closestTarget = target;
			}
		}
		return closestTarget;
	}

	public Target getFarthestFire(Robot rob) {
		Target target, farthestTarget;
		WildFire wf;
		ListIterator<WildFire> wfListIt = sim.getData().getWfList().listIterator();

		wf = wfListIt.next();
		farthestTarget = new Target(wf, rob.getPathToPoint(wf.getCoord()));
		while (wfListIt.hasNext()) {
			wf = wfListIt.next();
			target = new Target(wf, rob.getPathToPoint(wf.getCoord()));
			if (target.getPath().getTraveltime() > farthestTarget.getPath().getTraveltime()) {
				farthestTarget = target;
			}
		}
		return farthestTarget;
	}

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
}
