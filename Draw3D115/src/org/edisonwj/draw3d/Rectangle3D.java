package org.edisonwj.draw3d;

import java.util.StringTokenizer;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
/**
* Rectangle3D class captures information defining a JavaFX Box instantiation
* with specified width and height and near zero depth:
* - xyz location of center point (user coordinate scale)
* - width (user coordinate scale)
* - height (user coordinate scale)
* - xyz rotations (optional)
* - PhongMaterial (optional)
* - DrawMode setting (optional)
* - Cullface setting (optional)
* Rectangle3D objects are drawn as javafx.scene.shape.box objects with zero depth.
* 
* The class implements defaults specified in Draw3DDefaults.
* 
* @author William Edison
* @version 1.00 July 2015
* 
*/
public class Rectangle3D implements Draw3DDefaults {
	private Point3D p;
	private double width;
	private double height;
	private double xr;		// x rotation
	private double yr;		// y rotation
	private double zr;		// z rotation
	private PhongMaterial m;
	private DrawMode dm;
	private CullFace cf;
	
	/**
	 * Constructs a Rectangle3D object given its center point, width and height.
	 * 
	 * @param	p		Point3D center point of the rectangle
	 * @param	width	Width of the rectangle in user coordinates
	 * @param	height	Height of the rectangle in user coordinates
	 */
	public Rectangle3D(Point3D p, double width, double height) {
		this.p = p;
		this.width = width;
		this.height = height;
		this.xr = 0.0;
		this.yr = 0.0;
		this.zr = 0.0;
		this.m = null;
	}
	
	/**
	 * Constructs a Rectangle3D object given its center point, width, height
	 * and PhongMaterial.
	 * 
	 * @param	p		Point3D center point of the rectangle
	 * @param	width	Width of the rectangle in user coordinates
	 * @param	height	Height of the rectangle in user coordinates
	 * @param	m		PhongMaterial determining rectangle color
	 */
	public Rectangle3D(Point3D p, double width, double height,
					   PhongMaterial m) {
		this(p, width, height);
		this.m = m;
	}
	
	/**
	 * Constructs a Rectangle3D object given its center point, width, height
	 * and x, y, z, rotations.
	 * 
	 * @param	p		Point3D center point of the rectangle
	 * @param	width	Width of the rectangle in user coordinates
	 * @param	height	Height of the rectangle in user coordinates
	 * @param	xr		Rotation in degrees about the x axis
	 * @param	yr		Rotation in degrees about the y axis
	 * @param	zr		Rotation in degrees about the z axis
	 */
	public Rectangle3D(Point3D p, double width, double height,
					   double xr, double yr, double zr) {
		this(p, width, height);
		this.xr = xr;
		this.yr = yr;
		this.zr = zr;
		this.m = null;
	}
	
	/**
	 * Constructs a Rectangle3D object given its center point, width, height,
	 * x, y, z, rotations and PhongMaterial.
	 * 
	 * @param	p		Point3D center point of the rectangle
	 * @param	width	Width of the rectangle in user coordinates
	 * @param	height	Height of the rectangle in user coordinates
	 * @param	xr		Rotation in degrees about the x axis
	 * @param	yr		Rotation in degrees about the y axis
	 * @param	zr		Rotation in degrees about the z axis
	 * @param	m		PhongMaterial determining rectangle color
	 */
	public Rectangle3D(Point3D p, double width, double height,
			   double xr, double yr, double zr,
			   PhongMaterial m) {
		this(p, width, height, xr, yr, zr);
		this.m = m;
	}
	
	/**
	 * Constructs a Rectangle3D object given its center point coordinates, width and height.
	 * 
	 * @param	x		Center point x coordinate in user coordinates
	 * @param	y		Center point y coordinate in user coordinates
	 * @param	z		Center point z coordinate in user coordinates
	 * @param	width	Width of the rectangle in user coordinates
	 * @param	height	Height of the rectangle in user coordinates
	 */
	public Rectangle3D(double x, double y, double z, double width, double height) {
		this.p = new Point3D(x, y, z);
		this.width = width;
		this.height = height;
		this.xr = 0.0;
		this.yr = 0.0;
		this.zr = 0.0;
		this.m = null;
	}
	
	/**
	 * Constructs a Rectangle3D object given its center point coordinates, width, height
	 * and PhongMaterial.
	 * 
	 * @param	x		Center point x coordinate in user coordinates
	 * @param	y		Center point y coordinate in user coordinates
	 * @param	z		Center point z coordinate in user coordinates
	 * @param	width	Width of the rectangle in user coordinates
	 * @param	height	Height of the rectangle in user coordinates
	 * @param	m		PhongMaterial determining rectangle color
	 */
	public Rectangle3D(double x, double y, double z, double width, double height,
					   PhongMaterial m) {
		this(x, y, z, width, height);
		this.m = m;
	}
	
	/**
	 * Constructs a Rectangle3D object given its center point coordinates, width, height
	 * and x, y, z rotations
	 * 
	 * @param	x		Center point x coordinate in user coordinates
	 * @param	y		Center point y coordinate in user coordinates
	 * @param	z		Center point z coordinate in user coordinates
	 * @param	width	Width of the rectangle in user coordinates
	 * @param	height	Height of the rectangle in user coordinates
	 * @param	xr		Rotation in degrees about the x axis
	 * @param	yr		Rotation in degrees about the y axis
	 * @param	zr		Rotation in degrees about the z axis
	 */
	public Rectangle3D(double x, double y, double z, double width, double height,
					   double xr, double yr, double zr) {
		this(x, y, z, width, height);
		this.xr = xr;
		this.yr = yr;
		this.zr = zr;
		this.m = null;
	}
	
	/**
	 * Constructs a Rectangle3D object given its center point coordinates, width, height,
	 * x, y, z rotations and PhongMaterial.
	 * 
	 * @param	x		Center point x coordinate in user coordinates
	 * @param	y		Center point y coordinate in user coordinates
	 * @param	z		Center point z coordinate in user coordinates
	 * @param	width	Width of the rectangle in user coordinates
	 * @param	height	Height of the rectangle in user coordinates
	 * @param	xr		Rotation in degrees about the x axis
	 * @param	yr		Rotation in degrees about the y axis
	 * @param	zr		Rotation in degrees about the z axis
	 * @param	m		PhongMaterial determining rectangle color
	 */
	public Rectangle3D(double x, double y, double z, double width, double height,
			   double xr, double yr, double zr,
			   PhongMaterial m) {
		this(x, y, z, width, height, xr, yr, zr);
		this.m = m;
	}
	
	/**
	 * Constructs a Rectangle3D object given specification of the parameters
	 * in Save Data File format.
	 * 
	 * @param	lineString	Rectangle: format data string
	 */
	public Rectangle3D(String lineString) {
		StringTokenizer st = new StringTokenizer(lineString, ",;: ");
		String dataType = st.nextToken().trim();
		if (dataType.equals("Rectangle")) {
			double x = Double.valueOf(st.nextToken()).doubleValue();
			double y = Double.valueOf(st.nextToken()).doubleValue();
			double z = Double.valueOf(st.nextToken()).doubleValue();
			this.p = new Point3D(x, y, z);
			this.width = Double.valueOf(st.nextToken()).doubleValue();
			this.height = Double.valueOf(st.nextToken()).doubleValue();
			this.xr = 0.0;
			this.yr = 0.0; 
			this.zr = 0.0;
			if (st.hasMoreTokens()) {
				this.xr = Double.valueOf(st.nextToken()).doubleValue();
				this.yr = Double.valueOf(st.nextToken()).doubleValue();
				this.zr = Double.valueOf(st.nextToken()).doubleValue();
			}
			if (st.hasMoreTokens()) {
				dm = DrawMode.valueOf(st.nextToken());
			}
			if (st.hasMoreTokens()) {
				cf = CullFace.valueOf(st.nextToken());
			}
			if (st.hasMoreTokens()) {
				this.m = new PhongMaterial();
				this.m.setDiffuseColor(Color.web(st.nextToken()));
				if (st.hasMoreTokens()) {
					this.m.setSpecularColor(Color.web(st.nextToken()));
				}
			}
			else {
				this.m = null;
			}
		}
	}
	
    /**
     * Gets the 3D Rectangle center location
     *
     * @return	p	Point3D location of the center of the Rectangle
     */
	public Point3D getP() {
		return p;
	}
	
    /**
     * Gets the Rectangle width
     *
     * @return	width	Double value of the width in user coordinate space
     */
	public double getWidth() {
		return width;
	}
	
    /**
     * Gets the Rectangle height
     *
     * @return	height	Double value of the height in user coordinate space
     */
	public double getHeight() {
		return height;
	}
	
    /**
     * Gets the Rectangle x axis rotation
     *
     * @return	xr	Double value of the Rectangle x axis rotation
     */
	public double getXr() {
		return xr;
	}
	
    /**
     * Gets the Rectangle y axis rotation
     *
     * @return	yr	Double value of the Rectangle y axis rotation
     */
	public double getYr() {
		return yr;
	}
	
    /**
     * Gets the Rectangle z axis rotation
     *
     * @return	zr	Double value of the Rectangle z axis rotation
     */
	public double getZr() {
		return zr;
	}
	
    /**
     * Gets the Rectangle PhongMaterial property
     *
     * @return	m	PhongMaterial value of the Rectangle
     */
	public PhongMaterial getMaterial() {
		return m;
	}
	
    /**
     * Sets the Rectangle PhongMaterial property
     *
     * @param	m	PhongMaterial property of the Rectangle
   	 */
   	public void setMaterial(PhongMaterial m) {
		this.m = m;
	}
	
   	/**
     * Gets the Rectangle DrawMode property
     *
     * @return	dm	DrawMode property of the Rectangle
     */
	public DrawMode getDrawMode() {
		return dm;
	}
	
    /**
     * Sets the Rectangle DrawMode property
     *
     * @param	dm	DrawMode property of the Rectangle
     */
	public void setDrawMode(DrawMode dm) {
		this.dm = dm;
	}
	
    /**
     * Gets the Rectangle CullFace property
     *
     * @return	cf	CullFace property of the Rectangle
     */
	public CullFace getCullFace() {
		return cf;
	}
	
    /**
     * Sets the Rectangle CullFace property
     *
     * @param	cf	CullFace property of the Rectangle
     */
	public void setCullFace(CullFace cf) {
		this.cf = cf;
	}
	
    /**
     * Returns a String containing all Rectangle3D values for saving to a data file.
     * The String is processed by the Rectangle3D(String) constructor to recreate
	 * a Rectangle3D object when reading a saved data file.
     *
     * @return	String capturing all Rectangle field values
     */
	public String outString() {
		String ms = null;
		if (m != null) {
			if (m.getDiffuseColor() != null) {
				ms = m.getDiffuseColor().toString();
			}
			if (m.getSpecularColor() != null) {
				ms = ms.concat(", " + m.getSpecularColor().toString());
			}
		}
		return "Rectangle: " + p.getX() + ", " + p.getY() + ", " + p.getZ() +
			   ", " + width + ", " + height +
			   ", " + xr + ", " + yr + ", " + zr + ", " +
			   ", " + dm + ", " + cf + ", " + ms;
	}
	
    /**
     * Returns a String for printing all Rectangle3D values
     *
     * @return	String for printing all Rectangle3D field values
     */
	public String toString() {
		String ms = null;
		if (m != null) {
			if (m.getDiffuseColor() != null) {
				ms = ", m= " + m.getDiffuseColor().toString();
			}
			if (m.getSpecularColor() != null) {
				ms = ms.concat(", " + m.getSpecularColor().toString());
			}
		}
		else {
			ms = ", m= " + m;
		}
		return "Rectangle3D: p= " + p.toString() +
			   ", width = " + width + ", height= " + height +
			   ", xr= " + xr + ", yr= " + yr + ", zr= " + zr +
			   ", dm= " + dm + ", cf= " + cf + ", " + ms;
	}
}