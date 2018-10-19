package poog54.dataclasses;

import poog54.enums.*;

/**
 * @author Rey-Ricord Yoann
 * 
 */

public class TheMap {

	private int tileSize;
	private int nbLines, nbColums;
	private Tile TileMatrix[][];

	public TheMap(int tileSize, int nbLines, int nbColums, Tile TileMatrix[][]) {
		this.tileSize = tileSize;
		this.nbLines = nbLines;
		this.nbColums = nbColums;
		this.TileMatrix = TileMatrix;
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

	public Tile getTile(int line, int column) {
		return TileMatrix[line][column];
	}

	public boolean hasNeighbour(Tile src, CardinalPoints dir) {

		switch (dir) {

		/** North */
		case NORTH:
			if (src.getLine() == 0) {
				return false;
			} else {
				return true;
			}

			/** South */
		case SOUTH:
			if (src.getColumn() == nbLines - 1) {
				return false;
			} else {
				return true;
			}
			/** East */
		case EAST:
			if (src.getLine() == nbColums - 1) {
				return false;
			} else {
				return true;
			}
			/** West */
		case WEST:
			if (src.getColumn() == 0) {
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

			switch (dir) {

			/** North */
			case NORTH:
				return TileMatrix[src.getLine() + 1][src.getColumn()];

			/** South */
			case SOUTH:
				return TileMatrix[src.getLine() - 1][src.getColumn()];

			/** East */
			case EAST:
				return TileMatrix[src.getLine()][src.getColumn() - 1];

			/** West */

			case WEST:
				return TileMatrix[src.getLine()][src.getColumn() + 1];

			default:
				return TileMatrix[src.getLine()][src.getColumn()]; /** erreur !!!! */
			}

		}

		return TileMatrix[src.getLine()][src.getColumn()]; /** erreur !!!! */

	}
}
