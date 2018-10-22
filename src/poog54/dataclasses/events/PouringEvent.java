/**
 * This event is generated when a robot is able to pour its water tank
 */
package poog54.dataclasses.events;

import java.util.zip.DataFormatException;

import poog54.dataclasses.WildFire;
import poog54.dataclasses.robots.*;
import poog54.enums.RobotState;

/**
 * @author POO54
 *
 */
public class PouringEvent extends Event {
	private int amountOfWater;
	private Robot robot;
	private WildFire fire;

	/**
	 * @return the fire
	 */
	public WildFire getFire() {
		return fire;
	}

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
	 * @param fire
	 */
	public PouringEvent(int date, Robot robot, int amountOfWater, WildFire fire) throws DataFormatException {
		super(date);
		this.robot = robot;
		this.amountOfWater = amountOfWater;
		this.fire = fire;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see poog54.dataclasses.events.Event#execute()
	 */
	@Override
	public void execute() {
		this.robot.setState(RobotState.POURING_WATER);
		this.robot.setWater_level(this.robot.getWater_level() - this.amountOfWater);
		// @TODO
		// this.fire.setNeededWater(this.fire.getNeededWater()-this.amountOfWater)
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.robot + " pours " + this.amountOfWater + "L of water on " + fire;
	}
}
