package poog54.dataclasses;

import java.awt.Point;
import java.util.*;
import java.util.Map.Entry;

import poog54.enums.*;
import poog54.io.Drawable;

/**
 * @author Rey-Ricord Yoann
 * 
 */

public class TheMap {

	private int tileSize;
	private int nbLines, nbColums;
	private Map<Point, Tile> TileMatrix;

	public TheMap(int tileSize, int nbLines, int nbColums, Map<Point, Tile> TileMatrix) {
		this.tileSize = tileSize;
		this.nbLines = nbLines;
		this.nbColums = nbColums;
		this.TileMatrix = TileMatrix;
	}
	
	public List<Drawable> getDrawableList(){
		Set<Entry<Point, Tile>> tmpSet = this.TileMatrix.entrySet();
		List<Drawable> l = new ArrayList<Drawable>();
		Iterator<Entry<Point, Tile>> it = tmpSet.iterator();
		while(it.hasNext()) {
			l.add((Drawable) it.next().getValue());
		}
		return l;
	}

	public int getTileSize() {
		return this.tileSize;
	}

	public int getNbLines() {
		return this.nbLines;
	}

	public int getnbColums() {
		return this.nbColums;
	}

	public Tile getTile(int xCoord, int yCoord) {
		return TileMatrix.get(new Point(xCoord, yCoord));
	}

	public boolean hasNeighbour(Tile src, CardinalPoints dir) {
		switch (dir) {

		/** North */
		case NORTH:
			if (src.getCoord().y == 0) {
				return false;
			} else {
				return true;
			}

			/** South */
		case SOUTH:
			if (src.getCoord().y == nbLines - 1) {
				return false;
			} else {
				return true;
			}
			/** East */
		case EAST:
			if (src.getCoord().x == nbColums - 1) {
				return false;
			} else {
				return true;
			}
			/** West */
		case WEST:
			if (src.getCoord().x == 0) {
				return false;
			} else {
				return true;
			}

		default:
			return false;
		}

	}

	public Tile getNeighbour(Tile src, CardinalPoints dir) {
		/** Is there any Neighbour ?? */
		if (hasNeighbour(src, dir)) {
			Point p = new Point();
			switch (dir) {
			/** North */
			case NORTH:
				p = new Point(src.gety() + 1, src.getx());
				/** South */
			case SOUTH:
				p = new Point(src.gety() - 1, src.getx());
				/** East */
			case EAST:
				p = new Point(src.gety(), src.getx() + 1);
				/** West */
			case WEST:
				p = new Point(src.gety(), src.getx() - 1);
			}
			return TileMatrix.get(p);
		}
		return null; //error
	}
}
