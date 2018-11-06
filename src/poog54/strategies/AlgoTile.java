package poog54.strategies;

import java.awt.Point;

/**
 * @author POO_G54
 *
 */
public class AlgoTile {
	
	private double heuristicCost;
	private double timeCost;
	private double finalCost;
	private AlgoTile parent;
	private Point coord;
	
	/**
	 * @param time_cost
	 * @param p
	 */
	public AlgoTile(double time_cost, Point p) {
		this.heuristicCost = 0;
		this.timeCost = time_cost;
		this.finalCost = 0;
		this.parent = null;
		this.coord = p ;
	}
	
	public double getHeuristic_cost() {
		return heuristicCost;
	}
	public void setHeuristic_cost(double heuristic_cost) {
		this.heuristicCost = heuristic_cost;
	}
	public double getTime_cost() {
		return timeCost;
	}
	public void setTime_cost(double time_cost) {
		this.timeCost = time_cost;
	}
	public double getFinal_cost() {
		return finalCost;
	}
	/**
	 * @param final_cost
	 */
	public void setFinal_cost(double final_cost) {
		this.finalCost = final_cost;
	}
	public AlgoTile getParent() {
		return parent;
	}
	public void setParent(AlgoTile parent) {
		this.parent = parent;
	}

	/**
	 * @return
	 */
	public Point getCoord() {
		return coord;
	}
	
	
}
