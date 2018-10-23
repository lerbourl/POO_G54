/**
 * 
 */
package poog54.strategies;

import java.awt.Point;
import java.util.zip.DataFormatException;

import poog54.dataclasses.SimulationData;
import poog54.dataclasses.events.*;
import poog54.dataclasses.robots.Robot;
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
	public void initStrategy() {
		Robot rob = data.getRobotList().get(1);
		try {
			sim.eventAdd(new DestinationReachedEvent(1, rob, new Point(0,0)));
			sim.eventAdd(new FireExtinguishedEvent(10, data.getWfList().get(0)));
			sim.eventAdd(new DestinationReachedEvent(3, rob, new Point(2,0)));
			sim.eventAdd(new DestinationReachedEvent(2, rob, new Point(1,0)));
			sim.eventAdd(new DestinationReachedEvent(4, rob, new Point(3,0)));
			sim.eventAdd(new FireExtinguishedEvent(2, data.getWfList().get(1)));
		} catch (DataFormatException e) {
			e.printStackTrace();
		}
	}
}
