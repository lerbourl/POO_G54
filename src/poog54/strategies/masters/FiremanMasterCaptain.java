/**
 * Strategy Captain:
 * a robot is assigned to its closest fire
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
public class FiremanMasterCaptain extends FiremanMaster {

	/**
	 * @param sim
	 */
	public FiremanMasterCaptain(Simulator sim) {
		super(sim);
	}

	@Override
	public void orderRobotToFire(Robot rob, Simulator sim) {
		Target closestTarget;

		//A robot is assigned to its closest fire
		//whatever is its type,
		//and whatever is the number of firefighters already assigned to this fire
		closestTarget = getClosestFire(rob);
		System.out.println("t=" + sim.getDate() + ": " + rob + " assigned to fire (" + closestTarget.getFire() + ")");
		rob.setTargetFire(closestTarget.getFire());
		try {
			sim.addEvent(new MoveToFireEvent(rob));
		} catch (DataFormatException e) {
			e.printStackTrace();
		}
	}
}
