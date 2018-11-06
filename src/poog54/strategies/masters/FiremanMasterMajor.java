/**
 * Strategy Major:
 * -> a Walking robot is always assigned to its closest fire (Sergeant strategy)
 * -> a Drone robot is always assigned to its farthest fire
 * -> the other robots are assigned to their closest and unassigned fire if there are any
 * or the fires with the fewest affected robots (Captain strategy)
 */

package poog54.strategies.masters;

import java.util.zip.DataFormatException;

import poog54.dataclasses.events.MoveToFireEvent;
import poog54.dataclasses.robots.*;
import poog54.io.Simulator;

/**
 * @author POO_G54
 *
 */
public class FiremanMasterMajor extends FiremanMaster {

	/**
	 * @param sim
	 */
	public FiremanMasterMajor(Simulator sim) {
		super(sim);
	}

	@Override
	public void orderRobotToFire(Robot rob, Simulator sim) {
		Target assignedFire;

		if (!this.data.getWfList().isEmpty()) {
			// there are remaining fires
			
			// strategy depends on the robot type
			switch (rob.getClass().getName()) {
			
			case "poog54.dataclasses.robots.WalkingRob":
				// get closest fire
				assignedFire = getClosestFire(rob);
				break;
				
			case "poog54.dataclasses.robots.DroneRob":
				// get farthest fire
				assignedFire = getFarthestFire(rob);
				break;
				
			default:
				// get the fire with the fewest assigned robots
				assignedFire = getFireWithFewestRob(rob);
			}
			
			// assigns the selected fire to the robot
			System.out.println("t=" + sim.getDate() + ": " + rob + " assigned to " + assignedFire.getFire());
			rob.setTargetFire(assignedFire.getFire());
			try {
				sim.addEvent(new MoveToFireEvent(rob));
			} catch (DataFormatException e) {
				e.printStackTrace();
			}
		}
	}
}
