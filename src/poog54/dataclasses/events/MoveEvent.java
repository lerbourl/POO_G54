/**
 * Represents the displacement of a robot, one tile at a time.
 * It provides information about the source and destination tile.
 */
package poog54.dataclasses.events;

import poog54.dataclasses.robots.*;
import java.awt.Point;
import java.util.zip.DataFormatException;


/**
 * @author POO_G54
 *
 */
public abstract class MoveEvent extends DiscreteEvent {
	protected Robot robot;
	protected Point p;
	/**
	 * @return the robot
	 */
	public Robot getRobot() {
		return robot;
	}
	
	/**
	 * Default constructor
	 */
	public MoveEvent() throws DataFormatException {
		super(0);
	}
	
	/**
	 * Constructor
	 * move event : move a robot to the specified 'destination'. The destination is reached at 'date'.
	 * @param date
	 * @param robot
	 * @param destination
	 */
	public MoveEvent(int date, Robot robot, Point p) throws DataFormatException {
		super(date);
		this.robot = robot;
		this.p = p;
	}

	/*
	 * Print displacement details
	 */
	@Override
	public String toString() {
		if(this.p!=null) {
			return "Move " + this.robot + " to [" + this.p.x + "," + this.p.y + "]";
		} else {
			return this.robot + "not moved";
		}
	}

}
