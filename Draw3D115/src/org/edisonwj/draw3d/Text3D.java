package org.edisonwj.draw3d;

import java.util.StringTokenizer;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
/**
* Text3D class captures information defining a JavaFX Text instantiation with specified:
* - xyz location of the Text center point (user coordinate scale)
* - xyz rotations (optional)
* - string of text content
* - PhongMaterial (optional)
* - DrawMode setting (optional)
* - Cullface setting (optional)
* Label3D objects are drawn as javafx.scene.text.Text objects.
* 
* The class implements defaults specified in Draw3DDefaults.
* 
* @author William Edison
* @version 1.01 August 2015
* 
*/
public class Text3D implements Draw3DDefaults {
	private Point3D p;
	private double xr;		// x rotation
	private double yr;		// y rotation
	private double zr;		// z rotation
	private String t;
	private Font labelFont = LABEL_FONT;
	private Color color;
	
	/**
	 * Constructs a Text3D object given its center point location and text contents.
	 *  
	 * @param	p		Point3D text center location in user coordinates
	 * @param	t		String textual contents of the label
	 */	
	public Text3D(Point3D p, String t) {
		this.p = p;
		this.t = t;
		this.xr = 0.0;
		this.yr = 0.0;
		this.zr = 0.0;
		this.color = null;
	}
	
	/**
	 * Constructs a Text3D object given its center point location and text contents.
	 *  
	 * @param	p		Point3D text center location in user coordinates
	 * @param	t		String textual contents of the label
	 * @param	color	Color of the text string
	 */
	public Text3D(Point3D p, String t, Color color) {
		this(p, t);
		this.color = color;
	}
	
	/**
	 * Constructs a Text3D object given its center point location, text contents
	 * and x, y, z rotations.
	 *  
	 * @param	p		Point3D text center location in user coordinates
	 * @param	t		String textual contents of the label
	 * @param	xr		Rotation in degrees about the x axis
	 * @param	yr		Rotation in degrees about the y axis
	 * @param	zr		Rotation in degrees about the z axis
	 */	
	public Text3D(Point3D p, double xr, double yr, double zr, String t) {
		this(p, t);
		this.xr = xr;
		this.yr = yr;
		this.zr = zr;
		this.color = null;
	}
	
	/**
	 * Constructs a Text3D object given its center point location, text contents,
	 * x, y, z rotations and Color.
	 *  
	 * @param	p		Point3D text center location in user coordinates
	 * @param	t		String textual contents of the label
	 * @param	xr		Rotation in degrees about the x axis
	 * @param	yr		Rotation in degrees about the y axis
	 * @param	zr		Rotation in degrees about the z axis
	 * @param	color	Color of the text string
	 */	
	public Text3D(Point3D p, double xr, double yr, double zr, String t, Color color) {
		this(p, xr, yr, zr, t);
		this.color = color;
	}
	
	/**
	 * Constructs a Text3D object given its center point coordinates and text contents.
	 *  
	 * @param	x		Center point x coordinate in user coordinates
	 * @param	y		Center point y coordinate in user coordinates
	 * @param	z		Center point z coordinate in user coordinates
	 * @param	t		String textual contents of the label
	 */	
	public Text3D(double x, double y, double z, String t) {
		this.p = new Point3D(x, y, z);
		this.t = t;
		this.xr = 0.0;
		this.yr = 0.0;
		this.zr = 0.0;
		this.color = null;
	}
	
	/**
	 * Constructs a Text3D object given its center point coordinates, text contents
	 * and Color.
	 *  
	 * @param	x		Center point x coordinate in user coordinates
	 * @param	y		Center point y coordinate in user coordinates
	 * @param	z		Center point z coordinate in user coordinates
	 * @param	t		String textual contents of the label
	 * @param	color	Color of the text string
	 */
	public Text3D(double x, double y, double z, String t, Color color) {
		this(x, y, z, t);
		this.color = color;
	}
	
	/**
	 * Constructs a Text3D object given its center point coordinates, text contents,
	 * and x, y, z rotations.
	 *  
	 * @param	x		Center point x coordinate in user coordinates
	 * @param	y		Center point y coordinate in user coordinates
	 * @param	z		Center point z coordinate in user coordinates
	 * @param	xr		Rotation in degrees about the x axis
	 * @param	yr		Rotation in degrees about the y axis
	 * @param	zr		Rotation in degrees about the z axis
	 * @param	t		String textual contents of the label
	 */
	public Text3D(double x, double y, double z, double xr, double yr, double zr, String t) {
		this(x, y, z, t);
		this.xr = xr;
		this.yr = yr;
		this.zr = zr;
		this.color = null;
	}
	
	/**
	 * Constructs a Text3D object given its center point coordinates, text contents
	 * x, y, z rotations and Color.
	 *  
	 * @param	x		Center point x coordinate in user coordinates
	 * @param	y		Center point y coordinate in user coordinates
	 * @param	z		Center point z coordinate in user coordinates
	 * @param	xr		Rotation in degrees about the x axis
	 * @param	yr		Rotation in degrees about the y axis
	 * @param	zr		Rotation in degrees about the z axis
	 * @param	t		String textual contents of the label
	 * @param	c		Color of the text string
	 */
	public Text3D(double x, double y, double z, double xr, double yr, double zr, String t, Color c) {
		this(x, y, z, xr, yr, zr, t);
		this.color = c;
	}
	
	/**
	 * Constructs a Text3D object given specification of the parameters
	 * in Save Data File format.
	 * 
	 * @param	lineString	Text: format data string
	 */
	public Text3D(String lineString) {
		StringTokenizer st = new StringTokenizer(lineString, ":;");
		String dataType = st.nextToken().trim();
		if (dataType.equals("Text")) {
			double x = Double.valueOf(st.nextToken()).doubleValue();
			double y = Double.valueOf(st.nextToken()).doubleValue();
			double z = Double.valueOf(st.nextToken()).doubleValue();
			this.p = new Point3D(x, y, z);
			this.xr = Double.valueOf(st.nextToken()).doubleValue();
			this.yr = Double.valueOf(st.nextToken()).doubleValue();
			this.zr = Double.valueOf(st.nextToken()).doubleValue();
			this.labelFont = Font.font(st.nextToken());
			String cs = st.nextToken().trim();
			if (cs.equals("null")) {
				color = null;
			}
			else {
				color = Color.web(cs);
			}
			this.t = "";
			while (st.hasMoreTokens()) {
				t = t.concat(st.nextToken());
			}
		}
	}
	
    /**
     * Gets the 3D Text location
     *
     * @return	p	Point3D location of the center point of the Text string
     */
	public Point3D getP() {
		return p;
	}
	
    /**
     * Gets the Text x axis rotation
     *
     * @return	xr	Double value of the Text x axis rotation
     */
	public double getXr() {
		return xr;
	}
	
    /**
     * Gets the Text y axis rotation
     *
     * @return	yr	Double value of the Text y axis rotation
     */
	public double getYr() {
		return yr;
	}
	
    /**
     * Gets the Text z axis rotation
     *
     * @return	zr	Double value of the Text z axis rotation
     */
	public double getZr() {
		return zr;
	}
	
    /**
     * Gets the Text string
     *
     * @return	t	String contents
     */
	public String getText() {
		return t;
	}
	
    /**
     * Gets the Font associated with the Text string
     *
     * @return	labelFont	Font for the Text string
     */
	public Font getLabelFont() {
		return labelFont;
	}
	
    /**
     * sets the Font associated with the Text string
     *
     * @param	labelFont	Font for the Text string
     */
	public void setLabelFont(Font labelFont) {
		this.labelFont = labelFont;
	}
	
    /**
     * Gets the Color associated with the Text string
     *
     * @return	color	Color for the Text string
     */
	public Color getColor() {
		return color;
	}
	
    /**
     * Sets the Color associated with the Text string
     *
     * @param	color	Color for the Text string
     */
	public void setColor(Color color) {
		this.color = color;
	}
	
    /**
     * Returns a String containing all Text3D values for saving to a data file.
     * The String is processed by the Text3D(String) constructor to recreate a Text3D
     * object when reading a saved data file.
     * 
     * @return	String capturing all Text3D field values
     */
	public String outString() {
		String cs = null;
		if (color != null) {
			cs = "; " + color.toString();
		}
		else {
			cs = "; null";
		}
		return  "Text: " + p.getX() + "; " + p.getY() + "; " + p.getZ() +
				"; " + xr + "; " + yr + "; " + zr +
				"; " + labelFont.toString() + cs +
				"; " + t;
	}
	
    /**
     * Returns a String for printing all Text3D values
     *
     * @return	String for printing all Text3D field values
     */
	public String toString() {
		String cs = null;
		if (color != null) {
			cs = ", color= " + color.toString();
		}
		else {
			cs = ", color= null";
		}

		return "Text3D: p= " + p.toString() +
			    ", xr= " + xr + ", yr= " + yr + ", zr= " + zr +
			    ", labelFont= " + labelFont.toString() + cs +
			    ", text= " + t;
	}
}