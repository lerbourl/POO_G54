/**
 * This event is generated when a robot is able to fill its water tank
 */
package poog54.dataclasses.events;

import java.util.zip.DataFormatException;

import poog54.dataclasses.robots.Robot;
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
	 * @param robot
	 * @throws DataFormatException 
	 */
	public TankupEvent(Robot robot) throws DataFormatException {
		super(robot.getNextFreeTime() + robot.getTankUpTime());
		this.robot = robot;
		this.robot.setNextFreeTime(this.date);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see poog54.dataclasses.events.Event#execute()
	 */
	@Override
	public void execute(Simulator sim) {
		this.robot.tankUp();
		sim.getFiremanMaster().orderRobotToFire(robot, sim);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.robot + " fills " + robot.getWaterCapacity() + "L of water in its tank";
	}

}
