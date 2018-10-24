/**
 * 
 */
package poog54.io;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.ListIterator;
import java.util.PriorityQueue;
import java.util.zip.DataFormatException;

import gui.*;
import poog54.dataclasses.*;
import poog54.dataclasses.robots.*;
import poog54.strategies.FiremanMaster;
import poog54.dataclasses.events.*;

/**
 * @author POO_G54
 *
 */
public class Simulator implements Simulable {

	private String filepath;
	private FiremanMaster firemanmaster;

	/** L'interface graphique e */
	private GUISimulator gui;

	/** Les donnees de simulation associees */
	private SimulationData data;

	/** Discrete event management */
	private int date; // current simulation date
	private PriorityQueue<DiscreteEvent> eventQueue; // events are ordered in a
														// chronological way

	/**
	 * @param gui
	 * @throws DataFormatException
	 * @throws FileNotFoundException
	 */
	public Simulator(GUISimulator gui, String filepath) throws FileNotFoundException, DataFormatException {
		this.gui = gui;
		this.filepath = filepath;
		this.gui.setSimulable(this); // association a la gui!
		this.eventQueue = new PriorityQueue<DiscreteEvent>(11, (e1, e2) -> {
			return e1.getDate() < e2.getDate() ? -1 : e1.getDate() > e2.getDate() ? 1 : 0;
		});
		this.firemanmaster = new FiremanMaster(this);
		restart();
	}

	public FiremanMaster getFiremanMaster() {
		return this.firemanmaster;
	}

	private void loadData() throws FileNotFoundException, DataFormatException {
		this.data = OurDataReader.DataFromFile(this.filepath);
	}

	private void drawTheMapOnFire() {
		ListIterator<Drawable> drawit = data.getDrawablesIt();
		while (drawit.hasNext()) {
			gui.addGraphicalElement(drawit.next().getImage(gui, data.getMap().getNbLines(), 1));
		}
	}

	public void moveRobot(Robot rob, Point p) {
		gui.addGraphicalElement(rob.getTile().getImage(gui, data.getMap().getNbLines(), 1));
		rob.move(p);
		gui.addGraphicalElement(rob.getTile().getImage(gui, data.getMap().getNbLines(), 1));
		gui.addGraphicalElement(rob.getImage(gui, data.getMap().getNbLines(), 1));
	}

	public void removeFire(WildFire wf) {
		if (!data.getWfList().isEmpty() && data.getWfList().contains(wf)) {
			Tile t = data.getMap().getTile(wf.getCoord());
			data.getWfList().remove(wf);
			gui.addGraphicalElement(t.getImage(gui, data.getMap().getNbLines(), 1));
		}
	}

	/**
	 * @return true if there is no more event to process
	 */
	public boolean endOfSimulation() {
		return this.eventQueue.isEmpty();
	}

	/**
	 * add an event into the priority queue
	 */
	public void addEvent(DiscreteEvent e) {
		this.eventQueue.add(e);
	}

	/**
	 * runs all events in a chronological order until the current date
	 */
	public void processEvents() {
		DiscreteEvent event;
		while ((this.eventQueue.peek() != null) && (this.eventQueue.peek().getDate() <= this.date)) {
			event = this.eventQueue.poll();
			event.execute(this);
			System.out.println(event);
		}
	}

	@Override
	public void next() {
		if (!endOfSimulation()) {
			this.date++;
			System.out.println("t=" + this.date);
			processEvents();
		} else
			System.out.println("\nFin des évenements !");
	}

	@Override
	public void restart() {
		try {
			loadData();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DataFormatException e) {
			e.printStackTrace();
		}
		drawTheMapOnFire();
		this.eventQueue.clear();
		this.date = -1;
		this.firemanmaster.setData(this.data);
		try {
			addEvent(new CarryOutStrategy(0, this.firemanmaster));
		} catch (DataFormatException e) {
			e.printStackTrace();
		}
	}
}
