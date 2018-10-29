/**
 * Strategy First Class:
 * a robot is assigned to the 1st unassigned fire
 */

package poog54.strategies;

import java.util.*;
import java.util.zip.DataFormatException;

import poog54.dataclasses.*;
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
		
		int currentNum, selectedNum; // num of assigned robots
		Robot currentRob;
		WildFire currentWF, selectedWF;
		ListIterator<Robot> robotListIt;
		ListIterator<WildFire> wfListIt;

		if (!this.sim.getData().getWfList().isEmpty()) {
			// there are remaining fires
			selectedWF = null;
			selectedNum = Integer.MAX_VALUE;
			wfListIt = this.sim.getData().getWfList().listIterator();
			
			// searches the fire with the fewest assigned robots
			while (wfListIt.hasNext()) {
				currentNum = 0;
				currentWF = wfListIt.next();
				robotListIt = this.sim.getData().getRobotList().listIterator();
				
				// counts the num of assigned robots
				while (robotListIt.hasNext()) {
					currentRob = robotListIt.next();
					if (currentRob.getTargetFire() != null) {
						if (currentRob.getTargetFire().getFire() == currentWF) {
							currentNum++;
						}
					}
				}
				
				// selects the appropriate fire
				if (currentNum < selectedNum) {
					selectedWF = currentWF;
				}
			}
			
			// assigns the selected fire to the robot
			System.out.println(rob + " assigned to " + selectedWF);
			rob.setTargetFire(selectedWF);
			try {
				sim.addEvent(new MoveToFireEvent(rob));
			} catch (DataFormatException e) {
				e.printStackTrace();
			}
		}
	}
}
