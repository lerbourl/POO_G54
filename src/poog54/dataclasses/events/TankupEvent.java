/**
 * This event is generated when a robot is able to fill its water tank
 */
package poog54.dataclasses.events;

import java.util.zip.DataFormatException;

import poog54.dataclasses.robots.Robot;
import poog54.enums.RobotState;
import poog54.io.Simulator;

/**
 * @author POO_G54
 *
 */
public class TankupEvent extends DiscreteEvent {
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
	 * @param amountOfWater
	 */
	public TankupEvent(int date, Robot robot) throws DataFormatException {
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
		this.robot.setState(RobotState.TANK_UP);
		this.robot.tankUp();
		try {
			sim.addEvent(new TankFilledEvent(date + 1, robot));
			robot.setNext_free_time(date + 1);
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
		return this.robot + " fills " + robot.getWater_level() + "L of water in its tank";
	}

}
