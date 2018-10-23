/**
 * 
 */
package poog54.strategies;

import java.awt.Point;
import java.util.zip.DataFormatException;

import poog54.dataclasses.*;
import poog54.dataclasses.events.*;
import poog54.dataclasses.robots.*;
import poog54.enums.CardinalPoints;
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
		WildFire wf = data.getWfList().get(1);
		try {
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
			sim.addEvent(new MoveEvent(8, rob, CardinalPoints.EAST));
		} catch (DataFormatException e) {
			e.printStackTrace();
		}
	}
}
