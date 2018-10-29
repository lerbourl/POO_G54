/**
 * Strategy First Class:
 * a robot is assigned to its closest fire
 */

package poog54.strategies;

import java.util.*;
import java.util.zip.DataFormatException;

import poog54.enums.*;
import poog54.dataclasses.*;
import poog54.dataclasses.events.MoveToFireEvent;
import poog54.dataclasses.robots.*;
import poog54.io.Simulator;

public class FiremanMasterCaptain extends FiremanMaster {

	public FiremanMasterCaptain(Simulator sim) {
		super(sim);
	}

	@Override
	public void orderRobotToFire(Robot rob, Simulator sim) {
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
		System.out.println(rob + " assigned to fire (" + closestTarget.getFire() + ")");
		rob.setTargetFire(closestTarget.getFire());
		try {
			sim.addEvent(new MoveToFireEvent(rob));
		} catch (DataFormatException e) {
			e.printStackTrace();
		}
	}
}
