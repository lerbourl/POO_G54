/**
 * Strategy First Class:
 * the first available robot is assigned to the first available fire,
 * whatever are their relative positions on the map
 */

package poog54.strategies;

import java.util.zip.DataFormatException;

import poog54.dataclasses.*;
import poog54.dataclasses.events.MoveToFireEvent;
import poog54.dataclasses.robots.*;
import poog54.io.Simulator;

public class FiremanMasterFirstClass extends FiremanMaster {

	public FiremanMasterFirstClass(Simulator sim) {
		super(sim);
	}

	@Override
	public void orderRobotToFire(Robot rob, Simulator sim) {
		WildFire wf = null;

		// all available robots are affected to the 1st available fire
		if (!this.sim.getData().getWfList().isEmpty()) {
			// there are remaining fires
			wf = this.sim.getData().getWfList().get(0);
			System.out.println(rob + " assigned to " + wf);
			rob.setTargetFire(wf);
			try {
				sim.addEvent(new MoveToFireEvent(rob));
			} catch (DataFormatException e) {
				e.printStackTrace();
			}
		}
	}
}

