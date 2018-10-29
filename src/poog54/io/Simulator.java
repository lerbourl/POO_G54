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
import poog54.strategies.*;
import poog54.dataclasses.*;
import poog54.dataclasses.robots.*;
import poog54.dataclasses.events.*;

/**
 * @author POO_G54
 *
 */
public class Simulator implements Simulable {

	/** All data associated with the simulator */
	private SimulationData data;

	/** Discrete event management */
	private int date; // current simulation date
	private PriorityQueue<DiscreteEvent> eventQueue; // events are ordered in a chronological way

	/** Firefighters' strategy management */
	private FiremanMaster firemanmaster;
	
	/** Graphical interface management */
	private GUISimulator gui;
	private Map<Point, PriorityQueue<Drawable>> DrawableMap; // Drawables on a map, Queued by graphic_priority
	private String filepath; // path to the map file

	/**
	 * @param gui
	 * @param file path
	 * @param strategy
	 * @throws DataFormatException
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 */
	public Simulator(GUISimulator gui, String filepath, String strategy)
			throws FileNotFoundException, DataFormatException, ClassNotFoundException {
		this.gui = gui;
		this.filepath = filepath;
		this.gui.setSimulable(this);
		/* first of the queue will be the lowest date event */
		this.eventQueue = new PriorityQueue<DiscreteEvent>(11, (e1, e2) -> {
			return e1.getDate() < e2.getDate() ? -1 : e1.getDate() > e2.getDate() ? 1 : 0;
		});
		this.DrawableMap = new LinkedHashMap<Point, PriorityQueue<Drawable>>();
		
		switch (strategy) {
		case "first_class":
			this.firemanmaster = new FiremanMasterFirstClass(this);
			break;
		case "sergeant":
			this.firemanmaster = new FiremanMasterSergeant(this);
			break;
		case "captain":
			this.firemanmaster = new FiremanMasterCaptain(this);
			break;
			default:
				throw new ClassNotFoundException("Bad strategy class \"" + strategy + "\"\n" +
						                         "Valid args: first_class, sergeant, captain, major, colonel, general");
		}
		restart();
	}

	/**
	 * add an event into the priority queue
	 */
	public void addEvent(DiscreteEvent e) {
		this.eventQueue.add(e);
		System.out.println("NEW EVENT ADDED AT " + e.getDate()+ " : "+ e.toString());
	}

	/**
	 * clear event queue
	 */
	public void clearAllEvents() {
		this.eventQueue.clear();
	}
	
	private void draw(Drawable d) {
		Iterator<Drawable> it = drawableMapFill(d);
		while (it.hasNext()) {
			gui.addGraphicalElement(it.next().getImage(gui, data.getMap().getNbLines()));
		}
	}

	private void undraw(Drawable d) {
		Iterator<Drawable> it = drawableMapRemove(d);
		while (it.hasNext()) {
			gui.addGraphicalElement(it.next().getImage(gui, data.getMap().getNbLines()));
		}
	}
	
	private Iterator<Drawable> drawableMapFill(Drawable val) {
		Point key = val.getCoord();
		PriorityQueue<Drawable> queue = this.DrawableMap.get(key);
		if (queue == null) {
			/* first of the queue will be the lowest graphic priority */
			queue = new PriorityQueue<Drawable>(11, (e1, e2) -> {
				return e1.getGraphic_priority() < e2.getGraphic_priority() ? -1
						: e1.getGraphic_priority() > e2.getGraphic_priority() ? 1 : 0;
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

	/**
	 * @return true if there is no more event to process
	 */
	public boolean endOfSimulation() {
		return this.eventQueue.isEmpty();
	}

	public FiremanMaster getFiremanMaster() {
		return this.firemanmaster;
	}

	public SimulationData getData() {
		return this.data;
	}
	
	private void initTheMapOnFire() {
		ListIterator<Drawable> drawit = data.getDrawablesIt();
		while (drawit.hasNext()) {
			this.draw(drawit.next());
		}
	}

	private void loadData() throws FileNotFoundException, DataFormatException {
		this.data = OurDataReader.DataFromFile(this.filepath);
	}

	public void moveRobot(Robot rob, Point p) {
		undraw(rob);
		rob.move(p);
		draw(rob);
	}

	@Override
	public void next() {
		if (!endOfSimulation()) {
			this.date+=10;
			System.out.println("t=" + this.date);
			processEvents();
		}
	}

	/**
	 * runs all events in a chronological order until the current date
	 */
	public void processEvents() {
		DiscreteEvent event;
		while ((this.eventQueue.peek() != null) && (this.eventQueue.peek().getDate() <= this.date)) {
			event = this.eventQueue.poll();
			System.out.println(event);
			event.execute(this);
		}
	}

	public void removeFire(WildFire wf) {
		if (!data.getWfList().isEmpty() && data.getWfList().contains(wf)) {
			this.undraw(wf);
			data.getWfList().remove(wf);
		}
	}
	private void initSimulation() {
		// create initial event
		Iterator<Robot> it = this.getData().getRobotList().iterator();
		while(it.hasNext()) {
			this.firemanmaster.orderRobotToFire(it.next(), this);
		}
	}
	
	public void clearEvents(){
		this.eventQueue.clear();
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
		this.initTheMapOnFire();	
		clearEvents();
		this.date = -1;
		this.initSimulation();
	}

	/**
	 * @return the firemanmaster
	 */
	public FiremanMaster getFiremanmaster() {
		return firemanmaster;
	}
}
