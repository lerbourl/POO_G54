/**
 * Represents the displacement of a robot, one tile at a time.
 * It provides information about the source and destination tile.
 */
package poog54.dataclasses.events;

import poog54.io.*;
import poog54.enums.*;
import poog54.dataclasses.robots.*;

import java.util.zip.DataFormatException;


/**
 * @author POO_G54
 *
 */
public class MoveEvent extends DiscreteEvent {
	protected Robot robot;
	protected CardinalPoints direction;
	/**
	 * @return the robot
	 */
	public Robot getRobot() {
		return robot;
	}

	/**
	 * @param date
	 * @param robot
	 * @param direction
	 */
	public MoveEvent(int date, Robot robot, CardinalPoints direction) throws DataFormatException {
		super(date);
		this.robot = robot;
		this.direction=direction;
	}

	@Override
	public void execute(Simulator sim) {
		sim.moveRobot(this.robot, this.robot.getMap().getNeighbour(this.robot.getTile(), this.direction).getCoord());
	}

	/*
	 * Print displacement details
	 */
	@Override
	public String toString() {
		return "Move " + this.robot + " to " + this.direction;
	}

}
