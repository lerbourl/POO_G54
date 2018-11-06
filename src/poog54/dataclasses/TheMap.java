package poog54.dataclasses;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import poog54.io.Drawable;

/**
 * This class represents the map of Tiles
 * 
 * @author POO_G54
 */
public class TheMap {

	/* attributes */
	private int tileSize;
	private int nbLines, nbColums;
	private Map<Point, Tile> tileMatrix;
	private List<Point> waterTileList;

	/* methods */
	/**
	 * Constructor
	 * 
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

	private void buildWaterTileList() {
		Set<Entry<Point, Tile>> tmpSet = this.tileMatrix.entrySet();
		this.waterTileList = new ArrayList<Point>();
		Iterator<Entry<Point, Tile>> it = tmpSet.iterator();
		while (it.hasNext()) {
			this.waterTileList.add(it.next().getValue().getCoord());
		}
	}

	/**
	 * @return a list of all drawable tiles
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
	 * @return nbColumns
	 */
	public int getNbColums() {
		return this.nbColums;
	}

	/**
	 * @return nbLines
	 */
	public int getNbLines() {
		return this.nbLines;
	}

	/**
	 * @param xCoord
	 * @param yCoord
	 * @return tile at (x, y) coord
	 */
	public Tile getTile(int xCoord, int yCoord) {
		return this.tileMatrix.get(new Point(xCoord, yCoord));
	}

	/**
	 * @param p
	 * @return tile at point p
	 */
	public Tile getTile(Point p) {
		return this.tileMatrix.get(p);
	}

	/**
	 * @return tileMatrix
	 */
	public Map<Point, Tile> getTileMatrix() {
		return tileMatrix;
	}

	/**
	 * @return tileSize
	 */
	public int getTileSize() {
		return this.tileSize;
	}

	/**
	 * @return an interator on a list of all the watertiles
	 */
	public ListIterator<Point> getWaterTileListIt() {
		return this.waterTileList.listIterator();
	}

	/**
	 * @param p
	 * @return True if tileMatrix contain the Tile at point p
	 */
	public Boolean tileIsIn(Point p) {
		return this.tileMatrix.containsKey(p);
	}
}
