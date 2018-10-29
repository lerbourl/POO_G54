/**
 * This event is generated and queued when robots has poured enough water to extinguish a fire
 */
package poog54.dataclasses.events;

import java.util.ListIterator;
import java.util.zip.DataFormatException;
import poog54.dataclasses.*;
import poog54.dataclasses.robots.Robot;
import poog54.enums.RobotState;
import poog54.io.Simulator;

/**
 * @author POO_G54
 *
 */
public class FireExtinguishedEvent extends DiscreteEvent {
	private WildFire fire;
	private Simulator sim;

	/**
	 * @return the fire
	 */
	public WildFire getFire() {
		return fire;
	}

	/**
	 * @param date
	 * @param fire
	 * @throws DataFormatException
	 */
	public FireExtinguishedEvent(int date, WildFire fire, Simulator sim) throws DataFormatException {
		super(date);
		this.fire = fire;
		this.sim = sim;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see poog54.dataclasses.events.Event#execute()
	 */
	@Override
	public void execute(Simulator sim) {
		int nextFreeTime;
		Robot robot;
		ListIterator<Robot> robotListIt;

		// stop any robot that is still assigned to this fire
		nextFreeTime = 0;
		robotListIt = this.sim.getData().getRobotList().listIterator();
		while (robotListIt.hasNext()) {
			robot = robotListIt.next();
			if ((      robot.getState() == RobotState.POURING
					|| robot.getState() == RobotState.MOVING_TO_FIRE)
					&& robot.getTargetFire().fire == this.fire) {
				robot.setState(RobotState.IDLE);
				robot.setTargetFire(null);
				if (robot.getNext_free_time() > nextFreeTime) {
					nextFreeTime = robot.getNext_free_time();
				}
			}
		}
		
		// re-affects robots to remaining fires
		try {
			sim.addEvent(new CarryOutStrategy(nextFreeTime));
		} catch (DataFormatException e) {
			e.printStackTrace();
		}
		
		// removes the fire from the list and from the screen
		sim.removeFire(fire);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "fire " + fire + " has been extinguished";
	}

}
