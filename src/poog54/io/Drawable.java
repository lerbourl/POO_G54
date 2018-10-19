/**
 * Méthode commune aux objets dessinables !
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
	
	/**
	 * @return the line
	 */
	public int getLine() {
		return line;
	}
	/**
	 * @param line the line to set
	 */
	public void setLine(int xCoord) {
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
	public void setColumn(int yCoord) {
		this.column = yCoord;
	}
	protected Drawable(String ImageFilePath, int xCoord, int yCoord) {
		this.imageFilePath = ImageFilePath;
		this. line = xCoord;
		this.column = yCoord;
	}
	public ImageElement getImage(GUISimulator gui, int rowsNumber, int factor) {
		int size = gui.getPanelHeight()/rowsNumber/factor;
		return new ImageElement(column * size, line * size, imageFilePath, size, size, gui);
		/* have to swap column and line because this constructor works with : 
		 * column numbers as x-axis
		 * line numbers as y-axis										
		 */
	}
	
}
