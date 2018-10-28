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
	public void performStrategy() {
		Robot robot;
		Target target, closestTarget;
		WildFire wf;
		ListIterator<Robot> robotListIt;
		ListIterator<WildFire> wfListIt;

		// all available robots are affected to their closest fire
		if (!this.data.getWfList().isEmpty()) {
			// there are remaining fires
			robotListIt = this.data.getRobotList().listIterator();
			while (robotListIt.hasNext()) {
				robot = robotListIt.next();
				if (robot.getState() == RobotState.IDLE) {
					// this robot has no assigned fire
					wfListIt = this.data.getWfList().listIterator();
					wf = wfListIt.next();
					closestTarget = new Target(wf, robot.getPathToPoint(wf.getCoord()));
					while (wfListIt.hasNext()) {
						wf = wfListIt.next();
						target = new Target(wf, robot.getPathToPoint(wf.getCoord()));
						if (target.path.getTraveltime() < closestTarget.path.getTraveltime()) {
							closestTarget = target;
						}
					}
					System.out.println(robot + " assigned to fire (" + closestTarget.fire + ")");
					robot.setTargetFire(closestTarget.fire);
					robot.setState(RobotState.MOVING_TO_FIRE);
					try {
						sim.addEvent(new MoveToFireEvent(robot));
					} catch (DataFormatException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}

