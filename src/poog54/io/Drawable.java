/**
 * Drawable objects that return their drawable Image !
 */
package poog54.io;

import gui.GUISimulator;
import gui.ImageElement;

/**
 * @author louis
 *
 */
public abstract class Drawable {
	
	private String imageFilePath;
	private int line, column; //x -> column , y -> line
	
	protected Drawable(String ImageFilePath, int xCoord, int yCoord) {
		this.imageFilePath = ImageFilePath;
		this. line = xCoord;
		this.column = yCoord;
	}
	protected Drawable(int xCoord, int yCoord) {
		this. line = xCoord;
		this.column = yCoord;
	}
	/**
	 * @param imageFilePath the imageFilePath to set
	 */
	protected void setImageFilePath(String imageFilePath) {
		this.imageFilePath = imageFilePath;
	}
	/**
	 * @return the line
	 */
	public int getLine() {
		return line;
	}
	/**
	 * @param line the line to set
	 */
	protected void setLine(int xCoord) {
		this.line = xCoord;
	}
	/**
	 * @return the column
	 */
	public int getColumn() {
		return column;
	}
	/**
	 * @param column the column to set
	 */
	protected void setColumn(int yCoord) {
		this.column = yCoord;
	}
	public ImageElement getImage(GUISimulator gui, int rowsNumber, int factor) {
		int size = gui.getPanelHeight()/rowsNumber/factor;
		return new ImageElement(column * size, line * size, imageFilePath, size, size, gui);
		/* We have to swap column and line because this constructor works with : 
		 * column numbers as x-axis
		 * line numbers as y-axis										
		 */
	}
	
}
