package poog54.dataclasses;

import poog54.enums.*;

/**
 * @author Rey-Ricord Yoann 
 * 
 */

public class TheMap {
	
	private int tileSize;
	private int nbLines,nbColumn;
	private Tile tab[][];
	
	
	public TheMap(int tileSize,int nbLines, int nbColumn, Tile tab[][]) {
		this.tileSize=tileSize;
		this.nbLines=nbLines;
		this.nbColumn=nbColumn;
		this.tab=tab;
	}

	public int getTileSize() {
		return this.tileSize;
	}
	
	public int getNbLines() {
		return this.nbLines;
	}
	
	public int getNbColumn() {
		return this.nbColumn;
	}
	
	public Tile getTile(int line, int column) {
		return tab[line][column];
	}
	
	public boolean neighbourhood(Tile src, CardinalPoints dir) {
		
		switch(dir) {
		
		/** North */
			case NORTH : 
				if(src.getLine()==0) {
					return false;
				}
				else {
					return true;
				}
				
		/** South */	
			case SOUTH :
				if(src.getLine()==nbLines) {
					return false;
				}else {
					return true;
				}
		/** East */				
			case EAST : 
				if(src.getColumn()==nbColumn){
					return false; 
				}else {
					return true;
				}
		/** West */
			case WEST :
				if (src.getColumn()==0) {
					return false;
				}else {
					return true;
				}
				
			default : return false;
		}
	
	}


 public Tile getNeighbour(Tile src, CardinalPoints dir) {
	 
	
		/** Is there any Neighbour ?? */	
		
		if (neighbourhood(src,dir)) {
			
			switch(dir) {
			
			/** North */
				case NORTH : 
						return tab[src.getLine()+1][src.getColumn()];
					
			/** South */	
				case SOUTH :
						return tab[src.getLine()-1][src.getColumn()];					
					
			/** East */				
				case EAST : 
						return tab[src.getLine()][src.getColumn()-1];
					
			/** West */
					
				case WEST :
						return tab[src.getLine()][src.getColumn()+1];
							
				default : return tab[src.getLine()][src.getColumn()]; /** erreur !!!! */
			}
			
			
			
		}
		
		return tab[src.getLine()][src.getColumn()]; /** erreur !!!! */
	
 	}
}
