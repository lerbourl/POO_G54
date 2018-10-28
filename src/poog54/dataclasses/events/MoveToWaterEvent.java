/**
 * Represents the displacement of a robot, one tile at a time.
 * It provides information about the source and destination tile.
 */
package poog54.dataclasses.events;

import poog54.io.*;
import poog54.dataclasses.robots.*;
import poog54.enums.RobotState;

import java.awt.Point;
import java.util.zip.DataFormatException;

/**
 * @author POO_G54
 *
 */
public class MoveToWaterEvent extends MoveEvent {

	/**
	 * @param date
	 * @param robot
	 * @param direction
	 */
	public MoveToWaterEvent(int date, Robot robot, Point p) throws DataFormatException {
		super(date, robot, p);
	}

	/**
	 * Constructor
	 * move event : move a robot along its specified target path.
	 * @param robot
	 */
	public MoveToWaterEvent(Robot robot) throws DataFormatException {
		this.robot = robot;
		this.p = this.robot.getTargetWater().path.dequeueFirst();
		this.date = this.robot.getNext_free_time() + (int) this.robot.getTimeType(robot.getCoord()) + 1;
		this.robot.setNext_free_time(this.date + 1);
	}

	/**
	 * Constructor
	 * move event : move a robot to a specified point.
	 * @param robot
	 * @param destination
	 */
	public MoveToWaterEvent(Robot robot, Point p) throws DataFormatException {
		this.robot = robot;
		this.p = p;
		this.date = this.robot.getNext_free_time() + (int) this.robot.getTimeType(robot.getCoord()) + 1;
		this.robot.setNext_free_time(this.date + 1);
	}
	
	@Override
	public void execute(Simulator sim) {
		Point nextPosition;
		
		sim.moveRobot(this.robot, this.p);
		nextPosition = robot.getTargetWater().path.dequeueFirst();
		if (nextPosition == null) {
			// this robot has reached the water tile
			try {
				sim.addEvent(new TankupEvent(date + robot.getTankUpTime(), robot));
				robot.setNext_free_time(date + robot.getTankUpTime() + 1);
			} catch (DataFormatException e) {
				e.printStackTrace();
			}
		} else {
			// target not reached, continue the path...
			this.robot.setState(RobotState.MOVING_TO_WATER);
			try {
				sim.addEvent(new MoveToWaterEvent(robot, nextPosition));
			} catch (DataFormatException e) {
				e.printStackTrace();
			}
		}
	}
}