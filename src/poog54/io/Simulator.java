/**
 * 
 */
package poog54.io;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.ListIterator;
import java.util.Map;
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
	private Map<Point, PriorityQueue<Drawable>> DrawableMap; // Drawables on a map, Queued by graphic_priority

	/**
	 * @param gui
	 * @throws DataFormatException
	 * @throws FileNotFoundException
	 */
	public Simulator(GUISimulator gui, String filepath) throws FileNotFoundException, DataFormatException {
		this.gui = gui;
		this.filepath = filepath;
		this.gui.setSimulable(this); // association a la gui!
		/* first of the queue will be the lowest date event */
		this.eventQueue = new PriorityQueue<DiscreteEvent>(11, (e1, e2) -> {
			return e1.getDate() < e2.getDate() ? -1 : e1.getDate() > e2.getDate() ? 1 : 0;
		});
		this.DrawableMap = new LinkedHashMap<Point, PriorityQueue<Drawable>>();
		this.firemanmaster = new FiremanMaster(this);
		restart();
	}
	
	private Iterator<Drawable> drawableMapFill(Drawable val) {
		Point key = val.getCoord();
		PriorityQueue<Drawable> queue = this.DrawableMap.get(key);
		if (queue == null) {
			/* first of the queue will be the lowest graphic priority */
			queue = new PriorityQueue<Drawable>(11, (e1, e2) -> {
				return e1.getGraphic_priority() < e2.getGraphic_priority() ? -1 :
						e1.getGraphic_priority() > e2.getGraphic_priority() ? 1 :
						0;
			});
			this.DrawableMap.put(key, queue);
		}
		queue.add(val);
		return queue.iterator();
	}
	private Iterator<Drawable> drawableMapRemove(Drawable val) {
		Point key = val.getCoord();
		PriorityQueue<Drawable> queue = this.DrawableMap.get(key);
		if (queue == null) {
			System.out.println("error ! Drawable not in a the drawable map!\n");
		}
		queue.remove(val);
		return queue.iterator();
	}
	
	public FiremanMaster getFiremanMaster() {
		return this.firemanmaster;
	}

	private void loadData() throws FileNotFoundException, DataFormatException {
		this.data = OurDataReader.DataFromFile(this.filepath);
	}

	private void initTheMapOnFire() {
		ListIterator<Drawable> drawit = data.getDrawablesIt();
		while (drawit.hasNext()) {
			this.draw(drawit.next());
		}
		System.out.println(this.DrawableMap);
	}
	
	private void draw(Drawable d) {
		Iterator<Drawable> it = drawableMapFill(d);
		while(it.hasNext()) {
			gui.addGraphicalElement(it.next().getImage(gui, data.getMap().getNbLines()));
		}
	}
	private void undraw(Drawable d) {
		Iterator<Drawable> it = drawableMapRemove(d);
		while(it.hasNext()) {
			gui.addGraphicalElement(it.next().getImage(gui, data.getMap().getNbLines()));
		}
	}
	public void moveRobot(Robot rob, Point p) {
		undraw(rob);
		rob.move(p);
		draw(rob);
	}

	public void removeFire(WildFire wf) {
		if (!data.getWfList().isEmpty() && data.getWfList().contains(wf)) {
			Tile t = data.getMap().getTile(wf.getCoord());
			data.getWfList().remove(wf);
			gui.addGraphicalElement(t.getImage(gui, data.getMap().getNbLines()));
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
		this.DrawableMap.clear();
		initTheMapOnFire();
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
