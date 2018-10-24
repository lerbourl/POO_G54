/**
 * 
 */
package poog54.strategies;

import java.awt.Point;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author ensimag
 *
 */
public class Path {
	private Deque<Point> thepath;
	private double traveltime;

	
	public Path() {
		this.thepath = new ArrayDeque<Point>();
		this.traveltime = 0;
	}
	
	public Deque<Point> getThepath() {
		return thepath;
	}

	
	public double getTraveltime() {
		return traveltime;
	}
	
	public void incTravelTime(double traveltime) {
		this.traveltime += traveltime;
	}
	
	public void addFirst(Point e) {
		this.thepath.addFirst(e);
	}
	
	@Override 
	public String toString() {
		return "Time travel =" + this.traveltime +"list = " + thepath.toString();
	}
}
