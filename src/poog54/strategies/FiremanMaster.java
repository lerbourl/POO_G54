/**
 * An abstract class to represents the firefighters' strategies
 */
package poog54.strategies;

import java.awt.Point;
import java.util.zip.DataFormatException;

import poog54.dataclasses.*;
import poog54.dataclasses.events.*;
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
/*
	public void orderRobotToPath(Robot rob, Path path) {
		Point p;
		while (!path.getThepath().isEmpty()) {
			p = path.getThepath().removeFirst();
			orderMoveRobot(rob, p);
		}
	}
	
	// plus utilise car chaque MoveEvent genere l'evenement suivant
	private void orderMoveRobot(Robot rob, Point p) {
		int travel_time = (int) rob.getTimeType(p) + 1; // Arrondi au supérieur
		try {
			sim.addEvent(new MoveToFireEvent(rob.getNext_free_time()+travel_time, rob, p));
		} catch (DataFormatException e) {
			e.printStackTrace();
		}
		rob.setNext_free_time(rob.getNext_free_time() + travel_time + 1);
	}
*/

	abstract public void orderRobotToFire(Robot rob, Simulator sim);
}
