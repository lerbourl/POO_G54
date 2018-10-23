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

	/** L'interface graphique associ�e */
	private GUISimulator gui;

	/** Les donn�es de simulation associ�es */
	private SimulationData data;

	/** Discrete event management */
	private int date; // current simulation date
	private PriorityQueue<DiscreteEvent> eventQueue; // events are ordered in a chronological way

	/**
	 * @param gui
	 * @throws DataFormatException
	 * @throws FileNotFoundException
	 */
	public Simulator(GUISimulator gui, String filepath) throws FileNotFoundException, DataFormatException {
		this.gui = gui;
		this.date = 0;
		this.filepath = filepath;
		this.gui.setSimulable(this); // association a la gui!
		this.eventQueue = new PriorityQueue<DiscreteEvent>(11, (e1, e2) -> {
			return e1.getDate() < e2.getDate() ? -1 : e1.getDate() > e2.getDate() ? 1 : 0;
		});
		firemanmaster = new FiremanMaster(this);
		restart();
	}
	
public void eventAdd(DiscreteEvent e) {
	this.eventQueue.add(e);
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
	 * runs all events in a chronological order until the current date
	 */
	public void processEvents() {
		while ((this.eventQueue.peek() != null) && (this.eventQueue.peek().getDate() <= this.date)) {
			this.eventQueue.poll().execute(this);
		}
	}

	@Override
	public void next() {
		if (!endOfSimulation()) {
			this.date++;
			System.out.println(this.date);
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
		firemanmaster.setData(this.data);
		firemanmaster.initStrategy();
	}
}
