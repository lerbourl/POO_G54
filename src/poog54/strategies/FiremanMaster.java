/**
 * 
 */
package poog54.strategies;

import java.awt.Point;
import java.util.zip.DataFormatException;

import poog54.dataclasses.*;
import poog54.dataclasses.events.*;
import poog54.dataclasses.robots.*;
import poog54.io.Simulator;

/**
 * @author louis
 *
 */
public class FiremanMaster {
	private Simulator sim;
	private SimulationData data;
	public FiremanMaster(Simulator sim){
		this.sim = sim;
	}
	public void setData(SimulationData data) {
		this.data = data;
	}
	public void OrderRobotToPath(Robot rob, Path path) {
		Point p;
		while(!path.getThepath().isEmpty()) {
			p = path.getThepath().removeFirst();
			OrderMoveRobot(rob, p);
		}
	}
	private void OrderMoveRobot(Robot rob, Point p) {
		int travel_time = (int)rob.getTimeType(p) + 1; // Arrondi au supérieur
		try {
			sim.addEvent(new MoveEvent(rob.getNext_free_time(), rob, p));
			sim.addEvent(new DestinationReachedEvent(rob.getNext_free_time() + travel_time, rob, p));
		} catch (DataFormatException e) {
			e.printStackTrace();
		}
		System.out.println("arrive at " + (rob.getNext_free_time() + travel_time));
		rob.setNext_free_time(rob.getNext_free_time() + travel_time + 1);
	}
	public void initStrategy() {
		Robot rob1 = data.getRobotList().get(1);
		Robot rob2 = data.getRobotList().get(2);
		Robot rob3 = data.getRobotList().get(0);
		WildFire wf = data.getWfList().get(1);
		Point p = new Point(0, 6);
		OrderRobotToPath(rob1, rob1.getPathToPoint(p));
		OrderRobotToPath(rob2, rob2.getPathToPoint(p));
		OrderRobotToPath(rob3, rob3.getPathToPoint(p));
		/*
		sim.addEvent(new MoveEvent(1, rob, CardinalPoints.NORTH));
		sim.addEvent(new DestinationReachedEvent(9, rob, new Point(5,5)));
		sim.addEvent(new FireExtinguishedEvent(11, data.getWfList().get(0)));
		sim.addEvent(new PouringEvent(3, rob, 10, wf));
		sim.addEvent(new DestinationReachedEvent(2, rob, new Point(5,5)));
		sim.addEvent(new TankFilledEvent(7, rob));
		sim.addEvent(new MoveEvent(4, rob, CardinalPoints.WEST));
		sim.addEvent(new DestinationReachedEvent(5, rob, new Point(4,5)));
		sim.addEvent(new FireExtinguishedEvent(2, data.getWfList().get(1)));
		sim.addEvent(new TankupEvent(6, rob, 10));
		sim.addEvent(new PouringEvent(10, rob, 10, wf));
		*/
	}
}
