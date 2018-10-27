package poog54.strategies;

import java.awt.Point;
import java.util.*;


public class PathFinder {

	private AlgoTile[][] grid;
	private PriorityQueue<AlgoTile> openlist;
	private boolean[][] closedtab;
	
	private int height,width;
	

	public PathFinder(AlgoTile[][] grid, int height,int width) {
		this.grid = grid;
		closedtab = new boolean[height][width];
		openlist = new PriorityQueue<AlgoTile>(height * width, (t1, t2) -> {
			return t1.getFinal_cost()<t2.getFinal_cost()?-1:t1.getFinal_cost()>t2.getFinal_cost()?1:0;
		});
		this.height = height;
		this.width = width;
	}
	
	public void updateCost(AlgoTile current, AlgoTile t, double cost) {
		if(t == null || closedtab[t.getCoord().x][t.getCoord().y]) return;
		double t_final_cost = t.getHeuristic_cost()+cost;
		
		boolean test = openlist.contains(t);
		if(!test ||t_final_cost<t.getFinal_cost()) {
			t.setFinal_cost(t_final_cost);
			t.setParent(current);
			if(!test)openlist.add(t);
		}
	}
	
	public double getCostTime(AlgoTile current) {
		return grid[current.getCoord().x][current.getCoord().y].getTime_cost();
	}
	
	
	private void setHeuristicValues(Point end) {
		
		for(int i = 0; i < this.height ;++i){
            for(int j = 0 ; j < this.width ; ++j){
                grid[i][j].setHeuristic_cost(Math.abs(i-end.x)+Math.abs(j-end.y));
            }
		}
	}

	
	
	public Path Astar(Point start, Point end) {
		Path path = new Path();
		setHeuristicValues(end);
		this.openlist.add(grid[start.x][start.y]);
		AlgoTile current = null;
		boolean exit_while=false;
		
		while(exit_while!=true) {
			current = openlist.poll();
			if(current == null)break;
			closedtab[current.getCoord().x][current.getCoord().y]=true;
			
			if(current.equals(grid[end.x][end.y])) {
				exit_while=true;
			}
			
			AlgoTile t;
			
			if(current.getCoord().x-1>=0) {
				t = grid[current.getCoord().x-1][current.getCoord().y];				
				updateCost(current,t,current.getFinal_cost()+getCostTime(t)); 
				}
			
			if(current.getCoord().x+1 < grid.length) {
				t = grid[current.getCoord().x+1][current.getCoord().y];
				updateCost(current,t,current.getFinal_cost()+getCostTime(t)); 
				}
			
			if(current.getCoord().y-1>=0) {
				t = grid[current.getCoord().x][current.getCoord().y-1];
				updateCost(current,t,current.getFinal_cost()+getCostTime(t)); 
				}
			
			if(current.getCoord().y+1<grid[0].length) {
				t = grid[current.getCoord().x][current.getCoord().y+1];
				updateCost(current,t,current.getFinal_cost()+getCostTime(t)); 
				}
		}
			
		 while(!current.equals(grid[start.x][start.y])){
	         path.addFirst(current.getCoord());
             path.incTravelTime(current.getTime_cost());
             current = current.getParent();
         }
		return path;
	}


}	