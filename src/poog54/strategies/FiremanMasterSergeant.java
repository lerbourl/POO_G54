/**
 * Strategy First Class:
 * a robot is assigned to the 1st unassigned fire
 */

package poog54.strategies;

import java.util.*;
import java.util.zip.DataFormatException;

import poog54.enums.*;
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
		Robot robot;
		WildFire wf;
		ListIterator<Robot> robotListIt;
		ListIterator<WildFire> wfListIt;

		//each robot is assigned to the next available fire.
		//it loops back on the fire list if there are more robots than fires
		if (!this.data.getWfList().isEmpty()) {
			// there are remaining fires
			wfListIt = this.data.getWfList().listIterator();
			wf = wfListIt.next();
			robotListIt = this.data.getRobotList().listIterator();
			while (robotListIt.hasNext()) {
				robot = robotListIt.next();
				if (robot.getState() == RobotState.IDLE) {
					// this robot has no assigned fire
					System.out.println(robot + " assigned to fire (" + wf + ")");
					robot.setTargetFire(wf);
					try {
						sim.addEvent(new MoveToFireEvent(robot));
					} catch (DataFormatException e) {
						e.printStackTrace();
					}
					//get next fire
					if(!wfListIt.hasNext()){
						//loops on the wild fire list as long as there are robots left 
						wfListIt = this.data.getWfList().listIterator();
					}
					wf = wfListIt.next();
				}
			}
		}
	}
}
