/**
 * Represents the displacement of a robot, one tile at a time.
 * It provides information about the source and destination tile.
 */
package poog54.dataclasses.events;

import poog54.io.*;
import poog54.dataclasses.robots.*;

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

	@Override
	public void execute(Simulator sim) {
		Point nextPosition;
		int travel_time;
		
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
			travel_time = (int) robot.getTimeType(robot.getCoord()) + 1;
			try {
				sim.addEvent(new MoveToWaterEvent(date + travel_time, robot, nextPosition));
				robot.setNext_free_time(date + travel_time + 1);
			} catch (DataFormatException e) {
				e.printStackTrace();
			}
		}
	}
}