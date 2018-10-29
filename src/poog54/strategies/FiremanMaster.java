/**
 * An abstract class to represents the firefighters' strategies
 */
package poog54.strategies;

import poog54.dataclasses.*;
import poog54.dataclasses.robots.*;
import poog54.io.Simulator;

/**
 * @author POO_G54
 *
 */
abstract public class FiremanMaster {
	protected Simulator sim;
	protected SimulationData data;

	public FiremanMaster(Simulator sim) {
		this.sim = sim;
	}

	/**
	 * @return the data
	 */
	public SimulationData getData() {
		return data;
	}
	
	public void setData(SimulationData data) {
		this.data = data;
	}

	abstract public void orderRobotToFire(Robot rob, Simulator sim);
}
