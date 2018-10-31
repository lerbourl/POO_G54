/**
 * Strategy Major:
 * -> a Walking robot is always assigned to its closest fire
 * -> a Drone robot is assigned to the fire which is the farthest from all the other robots
 * -> the other robots are assigned to fires which are the closest from the water tiles
 */

package poog54.strategies;

import java.util.zip.DataFormatException;

import poog54.dataclasses.events.MoveToFireEvent;
import poog54.dataclasses.robots.*;
import poog54.io.Simulator;

public class FiremanMasterColonel extends FiremanMaster {

	public FiremanMasterColonel(Simulator sim) {
		super(sim);
	}

	@Override
	public void orderRobotToFire(Robot rob, Simulator sim) {
		Target assignedFire;

		if (!this.sim.getData().getWfList().isEmpty()) {
			// there are remaining fires
			
			// strategy depends on the robot type
			switch (rob.getClass().getName()) {
			
			case "poog54.dataclasses.robots.WalkingRob":
				// get closest fire
				assignedFire = getClosestFire(rob);
				break;
				
			case "poog54.dataclasses.robots.DroneRob":
				// get farthest fire
				assignedFire = getMostIsolatedFire(rob);
				break;
				
			default:
				// get the fire which is the closest from a water tile
				assignedFire = getFireClosestFromWater(rob);
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
