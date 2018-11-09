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
		this.coord = p;
	}

	/**
	 * @return coordinates
	 */
	public Point getCoord() {
		return coord;
	}

	/**
	 * @return final crossing cost for the Tile (A*)
	 */
	public double getFinal_cost() {
		return finalCost;
	}

	/**
	 * @return the heuristic cost for crossing the tile (for A* algorithm)
	 */
	public double getHeuristic_cost() {
		return heuristicCost;
	}

	/**
	 * @return Previous tile in the path
	 */
	public AlgoTile getParent() {
		return parent;
	}

	/**
	 * @return the time cost for crossing the tile (for A* algorithm)
	 */
	public double getTime_cost() {
		return timeCost;
	}

	/**
	 * @param final_cost
	 */
	public void setFinal_cost(double final_cost) {
		this.finalCost = final_cost;
	}

	/**
	 * @param heuristic_cost
	 */
	public void setHeuristic_cost(double heuristic_cost) {
		this.heuristicCost = heuristic_cost;
	}

	/**
	 * @param parent
	 */
	public void setParent(AlgoTile parent) {
		this.parent = parent;
	}

	/**
	 * @param time_cost
	 */
	public void setTime_cost(double time_cost) {
		this.timeCost = time_cost;
	}

}
