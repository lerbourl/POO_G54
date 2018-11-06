package poog54.dataclasses;

import java.awt.Point;
import java.util.*;
import java.util.Map.Entry;

import poog54.enums.*;
import poog54.io.Drawable;

/**
 * @author POO_G54
 * 
 */

public class TheMap {

	private int tileSize;
	private int nbLines, nbColums;
	private Map<Point, Tile> tileMatrix;
	private List<Point> waterTileList;

	/**
	 * @param tileSize
	 * @param nbLines
	 * @param nbColums
	 * @param TileMatrix
	 */
	public TheMap(int tileSize, int nbLines, int nbColums, Map<Point, Tile> TileMatrix) {
		this.tileSize = tileSize;
		this.nbLines = nbLines;
		this.nbColums = nbColums;
		this.tileMatrix = TileMatrix;
		this.buildWaterTileList();
	}

	/**
	 * @return
	 */
	public List<Drawable> getDrawableList() {
		Set<Entry<Point, Tile>> tmpSet = this.tileMatrix.entrySet();
		List<Drawable> l = new ArrayList<Drawable>();
		Iterator<Entry<Point, Tile>> it = tmpSet.iterator();
		while (it.hasNext()) {
			l.add(it.next().getValue());
		}
		return l;
	}

	/**
	 * @return
	 */
	public int getTileSize() {
		return this.tileSize;
	}

	/**
	 * @return
	 */
	public int getNbLines() {
		return this.nbLines;
	}

	/**
	 * @return
	 */
	public Map<Point, Tile> getTileMatrix() {
		return tileMatrix;
	}

	/**
	 * @return
	 */
	public int getNbColums() {
		return this.nbColums;
	}

	/**
	 * @param xCoord
	 * @param yCoord
	 * @return
	 */
	public Tile getTile(int xCoord, int yCoord) {
		return this.tileMatrix.get(new Point(xCoord, yCoord));
	}

	/**
	 * @param p
	 * @return
	 */
	public Tile getTile(Point p) {
		return this.tileMatrix.get(p);
	}

	/**
	 * @param p
	 * @return
	 */
	public Boolean tileIsIn(Point p) {
		return this.tileMatrix.containsKey(p);
	}

	/**
	 * @param src
	 * @param dir
	 * @return
	 */
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

	/**
	 * @param src
	 * @param dir
	 * @return
	 */
	public Tile getNeighbour(Tile src, CardinalPoints dir) {
		/** Is there any Neighbour ?? */
		if (hasNeighbour(src, dir)) {
			Point p;
			switch (dir) {
			/** North */
			case NORTH:
				p = new Point(src.getx(), src.gety() - 1);
				break;
			/** South */
			case SOUTH:
				p = new Point(src.getx(), src.gety() + 1);
				break;
			/** East */
			case EAST:
				p = new Point(src.getx() + 1, src.gety());
				break;
			/** West */
			case WEST:
				p = new Point(src.getx() - 1, src.gety());
				break;
			/** Unknown direction... don't move! */
			default:
				p = new Point(src.getx(), src.gety());
			}
			return tileMatrix.get(p);
		}
		return null; // error
	}
	
	private void buildWaterTileList(){
		Tile mapTile;
		int i,j;
		
		// read the water tiles position
		this.waterTileList = new ArrayList<Point>();
		for(i=0;i<getNbLines();i++){
			for(j=0;j<getNbLines();j++){
				mapTile=getTile(i, j);
				if(mapTile.getTypeField()==TypeField.EAU){
					this.waterTileList.add(mapTile.getCoord());
				}
			}
		}
	}

	/**
	 * @return
	 */
	public ListIterator<Point> getWaterTileListIt(){
		return this.waterTileList.listIterator();
	}
}
