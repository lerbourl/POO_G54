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
public class MoveToFireEvent extends MoveEvent {

	/**
	 * @param date
	 * @param robot
	 * @param direction
	 */
	public MoveToFireEvent(int date, Robot robot, Point p) throws DataFormatException {
		super(date, robot, p);
	}

	@Override
	public void execute(Simulator sim) {
		Point nextPosition;
		int travel_time;
		
		if (robot.getTargetFire() == null) {
			// the fire assignment is cancelled;
			this.robot.setState(RobotState.IDLE);
		} else {
			// the fire assignment has not been cancelled
			this.robot.setState(RobotState.MOVING_TO_FIRE);
			sim.moveRobot(this.robot, this.p);
			nextPosition = robot.getTargetFire().path.dequeueFirst();
			if (nextPosition == null) {
				// this robot has reached the fire tile
				try {
					sim.addEvent(new PouringEvent(date + robot.getPourTime(), robot, robot.getTargetFire().fire));
					robot.setNext_free_time(date + robot.getPourTime() + 1);
				} catch (DataFormatException e) {
					e.printStackTrace();
				}
			} else {
				// target not reached, continue the path...
				travel_time = (int) robot.getTimeType(robot.getCoord()) + 1;
				try {
					sim.addEvent(new MoveToFireEvent(date + travel_time, robot, nextPosition));
					robot.setNext_free_time(date + travel_time + 1);
				} catch (DataFormatException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
