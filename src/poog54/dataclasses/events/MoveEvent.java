/**
 * Represents the displacement of a robot, one tile at a time.
 * It provides information about the source and destination tile.
 */
package poog54.dataclasses.events;

import poog54.enums.*;

import java.util.zip.DataFormatException;

import poog54.dataclasses.*;
import poog54.dataclasses.robots.*;

/**
 * @author POO54
 *
 */
public class MoveEvent extends Event {
	protected Robot robot;
	protected Tile source;
	protected Tile destination;

	/**
	 * @return the robot
	 */
	public Robot getRobot() {
		return robot;
	}

	/**
	 * @return the source
	 */
	public Tile getSource() {
		return source;
	}

	/**
	 * @return the destination
	 */
	public Tile getDestination() {
		return destination;
	}

	/**
	 * @param date
	 * @param robot
	 * @param source
	 * @param destination
	 */
	public MoveEvent(int date, Robot robot, Tile source, Tile destination) throws DataFormatException {
		super(date);
		this.robot = robot;
		this.source = source;
		this.destination = destination;
	}

	@Override
	public void execute() {
		this.robot.setState(RobotState.MOVING_TO_TARGET_FIRE);
		this.robot.setCoord(this.destination);
		// @TODO this.source.draw();
		// this.robot.draw();
	}

	/*
	 * Print displacement details
	 */
	@Override
	public String toString() {
		return "Move " + this.robot + " from " + this.source + " to " + this.destination;
	}

}
