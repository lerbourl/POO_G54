/**
 * 
 */
package poog54.strategies;

import java.awt.Point;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author POO_G54
 *
 */
public class Path {
	private Deque<Point> thePath;
	private double travelTime;

	/**
	 * 
	 */
	public Path() {
		this.thePath = new ArrayDeque<Point>();
		this.travelTime = 0;
	}

	/**
	 * @param e
	 */
	public void addFirst(Point e) {
		this.thePath.addFirst(e);
	}

	/**
	 * @return head of the queue
	 */
	public Point dequeueFirst() {
		return this.thePath.pollFirst();
	}

	/**
	 * @return thePath, result of A* algorithm
	 */
	public Deque<Point> getThepath() {
		return thePath;
	}

	/**
	 * @return travel time
	 */
	public double getTraveltime() {
		return travelTime;
	}

	/**
	 * @param traveltime
	 */
	public void incTravelTime(double traveltime) {
		this.travelTime += traveltime;
	}

	/**
	 * @return true if empty
	 */
	public boolean isEmpty() {
		return this.thePath.isEmpty();
	}

	/**
	 * @return tail of the queue
	 */
	public Point removeLast() {
		return this.thePath.pollLast();
	}

	@Override
	public String toString() {
		return "Time travel =" + this.travelTime + "list = " + thePath.toString();
	}
}
