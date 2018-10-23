/**
 * This event is generated when a robot is able to fill its water tank
 */
package poog54.dataclasses.events;

import java.util.zip.DataFormatException;

import poog54.dataclasses.robots.Robot;

/**
 * @author POO54
 *
 */
public class TankupEvent extends Event {
	private int amountOfWater;
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
	public TankupEvent(int date, Robot robot, int amountOfWater) throws DataFormatException {
		super(date);
		this.robot = robot;
		this.amountOfWater = amountOfWater;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see poog54.dataclasses.events.Event#execute()
	 */
	@Override
	public void execute() {
		this.robot.setWater_level(this.robot.getWater_level() + this.amountOfWater);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.robot + " fills " + this.amountOfWater + "L of water in its tank";
	}

}