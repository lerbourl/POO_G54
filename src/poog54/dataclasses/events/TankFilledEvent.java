/**
 * This event is generated and queued when a robot has completely filled its water tank
 */
package poog54.dataclasses.events;

import java.util.zip.DataFormatException;
import poog54.dataclasses.robots.*;
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
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.robot + " has finished filling up";
	}

}
