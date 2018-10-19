package poog54.dataclasses;

import poog54.enums.*;

/**
 * @author Rey-Ricord Yoann 
 * 
 */

public class TheMap {
	
	private int tileSize;
	private int nbLines,nbColums;
	private Tile TileMatrix[][];
	
	
	public TheMap(int tileSize,int nbLines, int nbColums, Tile TileMatrix[][]){
		this.tileSize=tileSize;
		this.nbLines=nbLines;
		this.nbColums=nbColums;
		this.TileMatrix=TileMatrix;
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
		
		switch(dir) {
		
		/** North */
			case NORTH : 
				if(src.getxCoord() == 0) {
					return false;
				}
				else {
					return true;
				}
				
		/** South */	
			case SOUTH :
				if(src.getyCoord() == nbLines - 1) {
					return false;
				}else {
					return true;
				}
		/** East */				
			case EAST : 
				if(src.getxCoord() == nbColums - 1){
					return false; 
				}else {
					return true;
				}
		/** West */
			case WEST :
				if (src.getyCoord() == 0) {
					return false;
				}else {
					return true;
				}
				
			default : return false;
		}
	
	}


 public Tile getNeighbour(Tile src, CardinalPoints dir) {
		/** Is there any Neighbour ?? */	
		if (hasNeighbour(src,dir)) {
			
			switch(dir) {
			
			/** North */
				case NORTH : 
						return TileMatrix[src.getxCoord()+1][src.getyCoord()];
					
			/** South */	
				case SOUTH :
						return TileMatrix[src.getxCoord()-1][src.getyCoord()];					
					
			/** East */				
				case EAST : 
						return TileMatrix[src.getxCoord()][src.getyCoord()-1];
					
			/** West */
					
				case WEST :
						return TileMatrix[src.getxCoord()][src.getyCoord()+1];
							
				default : return TileMatrix[src.getxCoord()][src.getyCoord()]; /** erreur !!!! */
			}
			
			
			
		}
		
		return TileMatrix[src.getxCoord()][src.getyCoord()]; /** erreur !!!! */
	
 	}
}
