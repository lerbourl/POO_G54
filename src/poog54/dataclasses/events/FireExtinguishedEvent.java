/**
 * This event is generated and queued when robots has poured enough water to extinguish a fire
 */
package poog54.dataclasses.events;

import java.util.zip.DataFormatException;
import poog54.dataclasses.*;
import poog54.io.Simulator;

/**
 * @author POO_G54
 *
 */
public class FireExtinguishedEvent extends DiscreteEvent {
	private WildFire fire;

	/**
	 * @return the fire
	 */
	public WildFire getFire() {
		return fire;
	}

	/**
	 * @param date
	 * @param fire
	 * @throws DataFormatException
	 */
	public FireExtinguishedEvent(int date, WildFire fire) throws DataFormatException {
		super(date);
		this.fire = fire;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see poog54.dataclasses.events.Event#execute()
	 */
	@Override
	public void execute(Simulator sim) {
		//removes the fire from the list and from the screen
		sim.removeFire(fire);
		//re-affects robots to remaining fires
		try {
			sim.addEvent(new CarryOutStrategy(0, sim.getFiremanMaster()) );
		} catch (DataFormatException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return fire + " has been extinguished";
	}

}
