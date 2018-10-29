/**
 * Represents the displacement of a robot, one tile at a time.
 * It provides information about the source and destination tile.
 */
package poog54.dataclasses.events;

import poog54.io.*;
import poog54.enums.*;
import poog54.dataclasses.*;
import poog54.dataclasses.robots.*;

import java.awt.Point;
import java.util.zip.DataFormatException;

/**
 * @author POO_G54
 *
 */
public class MoveToFireEvent extends MoveEvent {	
	private WildFire wf;
	
	/**
	 * @param date
	 * @param robot
	 * @param destination
	 */
	public MoveToFireEvent(int date, Robot robot, Point p, WildFire wf) throws DataFormatException {
		super(date, robot, p);
		this.wf = wf;
		System.out.println("-> add MTFEdrpf: (" + date + ") "+ robot);
	}

	/**
	 * Constructor
	 * move event : move a robot along its specified target path.
	 * @param robot
	 */
	public MoveToFireEvent(Robot robot) throws DataFormatException {
		this.robot = robot;
		this.wf = this.robot.getTargetFire().fire;
		this.p = this.robot.getTargetFire().path.dequeueFirst();
		this.date = this.robot.getNext_free_time() + (int) this.robot.getTimeType(robot.getCoord()) + 1;
		this.robot.setNext_free_time(this.date + 1);
		System.out.println("-> add MTFEr: (" + date + ") "+ robot);
	}

	/**
	 * Constructor
	 * move event : move a robot to a specified point.
	 * @param robot
	 * @param destination
	 */
	public MoveToFireEvent(Robot robot, Point p) throws DataFormatException {
		this.robot = robot;
		this.p = p;
		this.wf = this.robot.getTargetFire().fire;
		this.date = this.robot.getNext_free_time() + (int) this.robot.getTimeType(robot.getCoord()) + 1;
		this.robot.setNext_free_time(this.date + 1);
		System.out.println("-> add MTFErp: (" + date + ") "+ robot);
	}
	
	@Override
	public void execute(Simulator sim) {
		Point nextPosition;
		
		if (!sim.getFiremanMaster().getData().getWfList().contains(this.wf)) {
			// this fire has been extinguished and removed from the list
			if(this.robot.getTargetFire() == null){
				//the assigned target has been removed
				this.robot.setState(RobotState.IDLE);
				this.robot.setTargetFire(null);
			}
		} else {
			// the fire assignment has not been cancelled
			this.robot.setState(RobotState.MOVING_TO_FIRE);
			if (p != null) {
				sim.moveRobot(this.robot, this.p);
			}
			nextPosition = robot.getTargetFire().path.dequeueFirst();
			if (nextPosition == null || p == null) {
				// this robot has reached the fire tile
				try {
					sim.addEvent(new PouringEvent(robot.getNext_free_time() + robot.getPourTime(), robot,
							robot.getTargetFire().fire));
					robot.setNext_free_time(robot.getNext_free_time() + robot.getPourTime() + 1);
				} catch (DataFormatException e) {
					e.printStackTrace();
				}
			} else {
				// target not reached, continue along the path...
				try {
					sim.addEvent(new MoveToFireEvent(robot, nextPosition));
				} catch (DataFormatException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/*
	 * Print displacement details
	 */
	@Override
	public String toString() {
		if (robot.getTargetFire() == null) {
			return "fire assignment canceled for " + this.robot;
		} else {
			return super.toString();
		}
	}

}
