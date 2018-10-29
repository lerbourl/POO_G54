/**
 * This event is generated and queued when a robot has completely filled its water tank
 */
package poog54.dataclasses.events;

import java.util.ListIterator;
import java.util.zip.DataFormatException;
import poog54.dataclasses.robots.*;
import poog54.enums.RobotState;
import poog54.io.Simulator;

/**
 * @author POO_G54
 *
 */
public class TankFilledEvent extends DiscreteEvent {
	private Robot robot;

	/**
	 * @return the robot
	 */
	public Robot getRobot() {
		return robot;
	}

	/**
	 * @param date
	 * @param robot
	 */
	public TankFilledEvent(int date, Robot robot) throws DataFormatException {
		super(date);
		this.robot = robot;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see poog54.dataclasses.events.Event#execute()
	 */
	@Override
	public void execute(Simulator sim) {
		int nextFreeTime;
		Robot rob;
		ListIterator<Robot> robListIt;

		this.robot.setState(RobotState.IDLE);
		// get the list of IDLE robots and get the next free time of the group
		nextFreeTime = 0;
		robListIt = sim.getData().getRobotList().listIterator();
		while (robListIt.hasNext()) {
			rob = robListIt.next();
			if (rob.getState() == RobotState.IDLE) {
				if (robot.getNext_free_time() > nextFreeTime) {
					nextFreeTime = robot.getNext_free_time();
				}
			}
		}

		// ask for fire assignment
		try {
			sim.addEvent(new CarryOutStrategy(nextFreeTime));
		} catch (DataFormatException e) {
			e.printStackTrace();
		}
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.robot + " has finished tanking up";
	}

}
