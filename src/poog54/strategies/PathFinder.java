package poog54.strategies;

import java.awt.Point;
import java.util.*;


public class PathFinder {

	private AlgoTile[][] grid;
	private PriorityQueue<AlgoTile> openlist;
	private boolean[][] closedtab;
	

	public PathFinder(AlgoTile[][] grid, int height,int width) {
		this.grid = grid;
		closedtab = new boolean[height][width];
		openlist = new PriorityQueue<AlgoTile>(height * width, (t1, t2) -> {
			return t1.getFinal_cost()<t2.getFinal_cost()?-1:t1.getFinal_cost()>t2.getFinal_cost()?1:0;
		});
	}
	
	public Path Astar(Point start, Point end) {
		Path path = new Path();
		
		return path;
	}
}