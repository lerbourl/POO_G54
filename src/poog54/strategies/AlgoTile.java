package poog54.strategies;

public class AlgoTile {
	
	private double heuristic_cost;
	private double time_cost;
	private double final_cost;
	private AlgoTile parent;
	
	
	public AlgoTile(double time_cost) {
		this.heuristic_cost = 0;
		this.time_cost = time_cost;
		this.final_cost = 0;
		this.parent = null;
	}
	
	public double getHeuristic_cost() {
		return heuristic_cost;
	}
	public void setHeuristic_cost(double heuristic_cost) {
		this.heuristic_cost = heuristic_cost;
	}
	public double getTime_cost() {
		return time_cost;
	}
	public void setTime_cost(double time_cost) {
		this.time_cost = time_cost;
	}
	public double getFinal_cost() {
		return final_cost;
	}
	public void setFinal_cost(double final_cost) {
		this.final_cost = final_cost;
	}
	public AlgoTile getParent() {
		return parent;
	}
	public void setParent(AlgoTile parent) {
		this.parent = parent;
	}
	
	
}
