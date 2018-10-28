/**
 * This event must be the first to be instantiated.
 * It triggers the execution of the fire extinction strategy,
 * and generate new events in the list
 */
package poog54.dataclasses.events;

import java.util.zip.DataFormatException;

import poog54.io.*;

/**
 * @author POO_G54
 *
 */
public class CarryOutStrategy extends DiscreteEvent {

	/**
	 * @param date
	 */
	public CarryOutStrategy(int date) throws DataFormatException {
		super(date);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see poog54.dataclasses.events.Event#execute()
	 */
	@Override
	public void execute(Simulator sim) {
		if (sim.getFiremanMaster().getData().getWfList().isEmpty()) {
			System.out.println("Tous les feux sont éteints. Beau travail les gars !\nFin de la simulation.");
			sim.clearAllEvents();
		}
		else sim.getFiremanMaster().performStrategy();
	}

	@Override
	public String toString() {
		return "Carry out strategy";
	}

}
