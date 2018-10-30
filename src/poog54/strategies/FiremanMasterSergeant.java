/**
 * Strategy Sergeant:
 * a robot is assigned to the 1st unassigned fire
 * or the fire with the fewest affected robots
 */

package poog54.strategies;

import java.util.zip.DataFormatException;

import poog54.dataclasses.events.MoveToFireEvent;
import poog54.dataclasses.robots.*;
import poog54.io.Simulator;

public class FiremanMasterSergeant extends FiremanMaster {

	public FiremanMasterSergeant(Simulator sim) {
		super(sim);
	}

	@Override
	public void orderRobotToFire(Robot rob, Simulator sim) {
		// a robot is affected to the 1st fire without assignment
		// or the fire with the fewest assigned firefighters
		Target assignedFire;

		if (!this.sim.getData().getWfList().isEmpty()) {
			// there are remaining fires

			assignedFire = getFireWithFewestRob(rob);

			// assigns the selected fire to the robot
			System.out.println("t=" + sim.getDate() + ": " + rob + " assigned to " + assignedFire);
			rob.setTargetFire(assignedFire);
			try {
				sim.addEvent(new MoveToFireEvent(rob));
			} catch (DataFormatException e) {
				e.printStackTrace();
			}
		}
	}
}
