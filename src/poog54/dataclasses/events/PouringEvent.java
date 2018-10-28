/**
 * This event is generated when a robot is able to pour its water tank
 */
package poog54.dataclasses.events;

import java.awt.Point;
import java.util.zip.DataFormatException;

import poog54.dataclasses.WildFire;
import poog54.dataclasses.robots.*;
import poog54.enums.RobotState;
import poog54.io.Simulator;

/**
 * @author POO_G54
 *
 */
public class PouringEvent extends DiscreteEvent {
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
	public PouringEvent(int date, Robot robot, WildFire fire) throws DataFormatException {
		super(date);
		this.robot = robot;
		this.fire = fire;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see poog54.dataclasses.events.Event#execute()
	 */
	@Override
	public void execute(Simulator sim) {
		Point nextPosition;
		int travel_time;

		if (robot.getTargetFire() == null) {
			// the fire assignment is cancelled;
			this.robot.setState(RobotState.IDLE);
		} else {
			// the fire assignment has not been cancelled
			this.robot.setState(RobotState.POURING);
			this.robot.pourOut();
			this.fire.setIntensity(this.fire.getIntensity() - this.robot.getWater_amount());
			try {
				if (robot.getWater_level() <= 0) {
					// the tank is empty
					robot.setState(RobotState.MOVING_TO_WATER);
					robot.locateClosestWaterTile();
					nextPosition = robot.getTargetWater().path.dequeueFirst();
					travel_time = (int) robot.getTimeType(robot.getCoord()) + 1;
					try {
						sim.addEvent(new MoveToWaterEvent(date + travel_time, robot, nextPosition));
						robot.setNext_free_time(date + travel_time + 1);
					} catch (DataFormatException e) {
						e.printStackTrace();
					}
				} else {
					// pour another time
					// this order will be cancelled if the fire is extinguished
					sim.addEvent(new PouringEvent(date + robot.getPourTime(), robot, this.fire));
					robot.setNext_free_time(date + robot.getPourTime() + 1);
				}
				if (this.fire.getIntensity() <= 0) {
					// this fire is now extinguished
					sim.addEvent(new FireExtinguishedEvent(this.date, this.fire, sim));
				}
			} catch (DataFormatException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.robot + " pours " + this.robot.getWater_amount() + "L of water on " + fire;
	}
}
