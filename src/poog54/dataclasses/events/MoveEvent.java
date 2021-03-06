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
	 * Constructor
	 * move event : move a robot to the specified 'destination'. The destination is reached at 'date'.
	 * @param robot
	 * @param p
	 * @throws DataFormatException 
	 */
	public MoveEvent(Robot robot, Point p) throws DataFormatException {
		super(robot.getNextFreeTime() + robot.getCrossingTileTime(robot.getCoord()));
		this.robot = robot;
		this.p = p;
		if(this.p == null){
			// the robot remains on the same tile
			this.date = this.robot.getNextFreeTime();
		}
		this.robot.setNextFreeTime(this.date);
	}

	/*
	 * Print displacement details
	 */
	@Override
	public String toString() {
		return "Move " + this.robot + " to " + this.robot.getTargetFire().getLocation();
	}

}
