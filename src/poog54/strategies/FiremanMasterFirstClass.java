/**
 * Strategy First Class:
 * the first available robot is assigned to the first available fire,
 * whatever are their relative positions on the map
 */

package poog54.strategies;

import java.util.*;
import java.util.zip.DataFormatException;

import poog54.enums.*;
import poog54.dataclasses.*;
import poog54.dataclasses.events.MoveToFireEvent;
import poog54.dataclasses.robots.*;
import poog54.io.Simulator;

public class FiremanMasterFirstClass extends FiremanMaster {

	public FiremanMasterFirstClass(Simulator sim) {
		super(sim);
	}

	@Override
	public void performStrategy() {
		int travel_time;
		Robot robot;
		WildFire fire;
		ListIterator<Robot> robotListIt;

		if (!this.data.getWfList().isEmpty()) {
			// there are remaining fires
			fire = this.data.getWfList().get(1);
			robotListIt = this.data.getRobotList().listIterator();
			while (robotListIt.hasNext()) {
				robot = robotListIt.next();
				if (robot.getState() == RobotState.IDLE) {
					// this robot has no assigned fire
					System.out.println(robot + " assigned to fire (" + fire.getCoord() + ")");
					robot.setTargetFire(fire);
					robot.setState(RobotState.MOVING_TO_FIRE);
					travel_time = (int) robot.getTimeType(robot.getCoord()) + 1;
					try {
						sim.addEvent(new MoveToFireEvent(robot.getNext_free_time() + travel_time, robot,
								robot.getTargetFire().path.dequeueFirst()));
						robot.setNext_free_time(robot.getNext_free_time() + travel_time + 1);
					} catch (DataFormatException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
