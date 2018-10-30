/**
 * Strategy Captain:
 * a robot is assigned to its closest fire
 */

package poog54.strategies;

import java.util.zip.DataFormatException;
import poog54.dataclasses.events.MoveToFireEvent;
import poog54.dataclasses.robots.*;
import poog54.io.Simulator;

public class FiremanMasterCaptain extends FiremanMaster {

	public FiremanMasterCaptain(Simulator sim) {
		super(sim);
	}

	@Override
	public void orderRobotToFire(Robot rob, Simulator sim) {
		Target closestTarget;

		closestTarget = getClosestFire(rob);
		System.out.println("t=" + sim.getDate() + ": " + rob + " assigned to fire (" + closestTarget.getFire() + ")");
		rob.setTargetFire(closestTarget);
		try {
			sim.addEvent(new MoveToFireEvent(rob));
		} catch (DataFormatException e) {
			e.printStackTrace();
		}
	}
}
