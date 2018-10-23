/**
 * 
 */
package poog54.strategies;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author ensimag
 *
 */
public class Path {
	private Queue<Point> thepath;
	private double traveltime;
	public Path() {
		this.thepath = new LinkedList<Point>();
		this.traveltime = 0;
	}
	
	public Queue<Point> getThepath() {
		return thepath;
	}

	
	public double getTraveltime() {
		return traveltime;
	}
	
	public void setTraveltime(double traveltime) {
		this.traveltime = traveltime;
	}
	
	public void add(Point e) {
		this.thepath.add(e);
	}
	
}
