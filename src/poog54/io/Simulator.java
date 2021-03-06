package poog54.io;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.ListIterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.zip.DataFormatException;

import gui.GUISimulator;
import gui.Simulable;
import poog54.dataclasses.SimulationData;
import poog54.dataclasses.WildFire;
import poog54.dataclasses.events.DiscreteEvent;
import poog54.dataclasses.robots.Robot;
import poog54.strategies.masters.FiremanMaster;
import poog54.strategies.masters.FiremanMasterCaptain;
import poog54.strategies.masters.FiremanMasterColonel;
import poog54.strategies.masters.FiremanMasterFirstClass;
import poog54.strategies.masters.FiremanMasterMajor;
import poog54.strategies.masters.FiremanMasterSergeant;

/**
 * This class runs the simulation, based on the SimulationData. The simulator
 * draws the elements on the screen, and process the simulation events.
 * 
 * @author POO_G54
 *
 */
public class Simulator implements Simulable {

	/* attributes */
	private SimulationData data;
	private int speedup; // speeding the simulation up
	private String filePath; // path to the map file
	/* events management */
	private int date; // current simulation date
	private PriorityQueue<DiscreteEvent> eventQueue; // events are ordered in a chronological way
	private FiremanMaster firemanMaster; // strategy management
	/* graphical interface management */
	private GUISimulator gui;
	private Map<Point, PriorityQueue<Drawable>> drawableMap; // Drawables on a map, Queued by graphic_priority

	/**
	 * Constructor
	 * 
	 * @param gui
	 * @param filepath
	 * @param strategy
	 * @param speedup
	 * @throws FileNotFoundException
	 * @throws DataFormatException
	 * @throws ClassNotFoundException
	 */
	public Simulator(GUISimulator gui, String filepath, String strategy, int speedup)
			throws FileNotFoundException, DataFormatException, ClassNotFoundException {
		this.gui = gui;
		if (speedup > 0)
			this.speedup = speedup;
		else
			this.speedup = 1;
		this.filePath = filepath;
		this.gui.setSimulable(this);
		/* first of the queue will be the lowest date event */
		this.eventQueue = new PriorityQueue<DiscreteEvent>(11, (e1, e2) -> {
			return e1.getDate() < e2.getDate() ? -1 : e1.getDate() > e2.getDate() ? 1 : 0;
		});
		this.drawableMap = new LinkedHashMap<Point, PriorityQueue<Drawable>>();

		switch (strategy) {
		case "first_class":
			this.firemanMaster = new FiremanMasterFirstClass(this);
			break;
		case "sergeant":
			this.firemanMaster = new FiremanMasterSergeant(this);
			break;
		case "captain":
			this.firemanMaster = new FiremanMasterCaptain(this);
			break;
		case "major":
			this.firemanMaster = new FiremanMasterMajor(this);
			break;
		case "colonel":
			this.firemanMaster = new FiremanMasterColonel(this);
			break;
		default:
			throw new ClassNotFoundException("Bad strategy class \"" + strategy + "\"\n"
					+ "Valid args: first_class, sergeant, captain, major, colonel, general");
		}
		restart();
	}

	/**
	 * add an event into the priority queue
	 * @param e 
	 */
	public void addEvent(DiscreteEvent e) {
		this.eventQueue.add(e);
		//System.out.println("NEW EVENT ADDED AT t=" + e.getDate() + " : " + e.toString());
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

	private Iterator<Drawable> drawableMapFill(Drawable val) {
		Point key = val.getCoord();
		PriorityQueue<Drawable> queue = this.drawableMap.get(key);
		if (queue == null) {
			/* first of the queue will be the lowest graphic priority */
			queue = new PriorityQueue<Drawable>(11, (e1, e2) -> {
				return e1.getGraphic_priority() < e2.getGraphic_priority() ? -1
						: e1.getGraphic_priority() > e2.getGraphic_priority() ? 1 : 0;
			});
			this.drawableMap.put(key, queue);
		}
		queue.add(val);
		return queue.iterator();
	}

	private Iterator<Drawable> drawableMapRemove(Drawable val) {
		Point key = val.getCoord();
		PriorityQueue<Drawable> queue = this.drawableMap.get(key);
		if (queue == null) {
			System.out.println("error ! Drawable not in a the drawable map!\n");
			return null;
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

	/**
	 * @return data
	 */
	public SimulationData getData() {
		return this.data;
	}

	/**
	 * @return date
	 */
	public int getDate() {
		return this.date;
	}

	/**
	 * @return firemanMaster
	 */
	public FiremanMaster getFiremanMaster() {
		return this.firemanMaster;
	}

	private void initSimulation() {
		// create initial event
		Iterator<Robot> it = this.getData().getRobotList().iterator();
		while (it.hasNext()) {
			this.firemanMaster.orderRobotToFire(it.next(), this);
		}
	}

	private void initTheMapOnFire() {
		ListIterator<Drawable> drawit = data.getDrawablesIt();
		while (drawit.hasNext()) {
			this.draw(drawit.next());
		}
	}

	private void loadData() throws FileNotFoundException, DataFormatException {
		this.data = OurDataReader.DataFromFile(this.filePath);
	}

	/**
	 * Move a robot on the screen and into data
	 * @param rob
	 * @param p
	 */
	public void moveRobot(Robot rob, Point p) {
		undraw(rob);
		rob.move(p);
		draw(rob);
	}

	@Override
	public void next() {
		if (!endOfSimulation()) {
			this.date += this.speedup;
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
			System.out.println("t=" + event.getDate() + ": " + event);
			event.execute(this);
		}
	}

	/**
	 * remove a wildfire from the simulation
	 * @param wf
	 */
	public void removeFire(WildFire wf) {
		if (!data.getWfList().isEmpty() && data.getWfList().contains(wf)) {
			this.undraw(wf);
			data.getWfList().remove(wf);
		}
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
		this.drawableMap.clear();
		this.initTheMapOnFire();
		clearAllEvents();
		this.date = 0;
		this.firemanMaster.setData(this.data);
		this.initSimulation();
	}

	private void undraw(Drawable d) {
		Iterator<Drawable> it = drawableMapRemove(d);
		while (it.hasNext()) {
			gui.addGraphicalElement(it.next().getImage(gui, data.getMap().getNbLines()));
		}
	}
}