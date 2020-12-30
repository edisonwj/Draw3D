package org.edisonwj.draw3d;

import java.util.StringTokenizer;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
/**
* Cylinder3D class captures information defining a JavaFX Cylinder instantiation with specified:
* - xyz location of the cylinder center point (user coordinate scale)
* - radius (user coordinate scale)
* - height (user coordinate scale)
* - xyz rotations (optional)
* - number of divisions
* - PhongMaterial (optional)
* - DrawMode setting (optional)
* - Cullface setting (optional)
* The Cylinder3D object is drawn as a javafx.scene.shape.Cylinder.
* 
* The class implements defaults specified in Draw3DDefaults.
* 
* @author William Edison
* @version 1.00 July 2015
* 
*/
public class Cylinder3D implements Draw3DDefaults {
	private Point3D p;
	private double radius;
	private double height;
	private double xr;		// x rotation
	private double yr;		// y rotation
	private double zr;		// z rotation
	private int cylinderDivisions = CYLINDER_DIVISIONS;
	private PhongMaterial m;
	private DrawMode dm;
	private CullFace cf;
	
	/**
	 * Constructs a Cylinder3D object given its center point location, radius and height.
	 *  
	 * @param	p		Point3D cylinder center location in user coordinates
	 * @param	radius	Radius of the cylinder in user coordinates
	 * @param	height	Height of the cylinder in user coordinates
	 */	
	public Cylinder3D(Point3D p, double radius, double height) {
		this.p = p;
		this.radius = radius;
		this.height = height;
		this.xr = 0.0;
		this.yr = 0.0;
		this.zr = 0.0;
		this.m = null;
	}
	
	/**
	 * Constructs a Cylinder3D object given its center point location, radius, height
	 * and PhongMaterial.
	 *  
	 * @param	p		Point3D cylinder center location in user coordinates
	 * @param	radius	Radius of the cylinder in user coordinates
	 * @param	height	Height of the cylinder in user coordinates
	 * @param	m		PhongMaterial determining cylinder color
	 */	
	public Cylinder3D(Point3D p, double radius, double height,
					   PhongMaterial m) {
		this(p, radius, height);
		this.m = m;
	}
	
	/**
	 * Constructs a Cylinder3D object given its center point location, radius, height
	 * and x, y, z rotations.
	 *   
	 * @param	p		Point3D cylinder tip location in user coordinates
	 * @param	radius	Radius of the cylinder in user coordinates
	 * @param	height	Height of the cylinder in user coordinates
	 * @param	xr		Rotation in degrees about the x axis
	 * @param	yr		Rotation in degrees about the y axis
	 * @param	zr		Rotation in degrees about the z axis
	 */	
	public Cylinder3D(Point3D p, double radius, double height,
					   double xr, double yr, double zr) {
		this(p, radius, height);
		this.xr = xr;
		this.yr = yr;
		this.zr = zr;
		this.m = null;
	}
	
	/**
	 * Constructs a Cylinder3D object given its center point location, radius, height,
	 * x, y, z rotations and PhongMaterial.
	 *   
	 * @param	p		Point3D cylinder tip location in user coordinates
	 * @param	radius	Radius of the cylinder in user coordinates
	 * @param	height	Height of the cylinder in user coordinates
	 * @param	xr		Rotation in degrees about the x axis
	 * @param	yr		Rotation in degrees about the y axis
	 * @param	zr		Rotation in degrees about the z axis
	 * @param	m		PhongMaterial determining cylinder color
	 */	
	public Cylinder3D(Point3D p, double radius, double height,
			   double xr, double yr, double zr,
			   PhongMaterial m) {
		this(p, radius, height, xr, yr, zr);
		this.m = m;
	}
	
	/**
	 * Constructs a Cylinder3D object given its center point coordinates, radius and height.
	 * 
	 * @param	x		Center point x coordinate in user coordinates
	 * @param	y		Center point y coordinate in user coordinates
	 * @param	z		Center point z coordinate in user coordinates
	 * @param	radius	Radius of the cylinder in user coordinates
	 * @param	height	Height of the cylinder in user coordinates
	 */
	public Cylinder3D(double x, double y, double z, double radius, double height) {
		this.p = new Point3D(x, y, z);
		this.radius = radius;
		this.height = height;
		this.xr = 0.0;
		this.yr = 0.0;
		this.zr = 0.0;
		this.m = null;
	}
	
	/**
	 * Constructs a Cylinder3D object given its center point coordinates, radius, height
	 * and PhongMaterial.
	 * 
	 * @param	x		Center point x coordinate in user coordinates
	 * @param	y		Center point y coordinate in user coordinates
	 * @param	z		Center point z coordinate in user coordinates
	 * @param	radius	Radius of the cylinder in user coordinates
	 * @param	height	Height of the cylinder in user coordinates
	 * @param	m		PhongMaterial determining cylinder color
	 */
	public Cylinder3D(double x, double y, double z, double radius, double height,
					   PhongMaterial m) {
		this(x, y, z, radius, height);
		this.m = m;
	}
	
	/**
	 * Constructs a Cylinder3D object given its center point coordinates, radius, height
	 * and x, y, z rotations.
	 * 
	 * @param	x		Center point x coordinate in user coordinates
	 * @param	y		Center point y coordinate in user coordinates
	 * @param	z		Center point z coordinate in user coordinates
	 * @param	radius	Radius of the cylinder in user coordinates
	 * @param	height	Height of the cylinder in user coordinates
	 * @param	xr		Rotation in degrees about the x axis
	 * @param	yr		Rotation in degrees about the y axis
	 * @param	zr		Rotation in degrees about the z axis
	 */
	public Cylinder3D(double x, double y, double z, double radius, double height,
					   double xr, double yr, double zr) {
		this(x, y, z, radius, height);
		this.xr = xr;
		this.yr = yr;
		this.zr = zr;
		this.m = null;
	}
	
	/**
	 * Constructs a Cylinder3D object given its center point coordinates, radius, height,
	 * x, y, z rotations and PhongMaterial.
	 * 
	 * @param	x		Center point x coordinate in user coordinates
	 * @param	y		Center point y coordinate in user coordinates
	 * @param	z		Center point z coordinate in user coordinates
	 * @param	radius	Radius of the cylinder in user coordinates
	 * @param	height	Height of the cylinder in user coordinates
	 * @param	xr		Rotation in degrees about the x axis
	 * @param	yr		Rotation in degrees about the y axis
	 * @param	zr		Rotation in degrees about the z axis
	 * @param	m		PhongMaterial determining cylinder color
	 */
	public Cylinder3D(double x, double y, double z, double radius, double height,
			   double xr, double yr, double zr,
			   PhongMaterial m) {
		this(x, y, z, radius, height, xr, yr, zr);
		this.m = m;
	}
	
	/**
	 * Constructs a Cylinder3D object given specification of the parameters
	 * in Save Data File format
	 * 
	 * @param	lineString	Cylinder: format data string
	 */
	public Cylinder3D(String lineString) {
		StringTokenizer st = new StringTokenizer(lineString, ",;: ");
		String dataType = st.nextToken().trim();
		if (dataType.equals("Cylinder")) {
			double x = Double.valueOf(st.nextToken()).doubleValue();
			double y = Double.valueOf(st.nextToken()).doubleValue();
			double z = Double.valueOf(st.nextToken()).doubleValue();
			this.p = new Point3D(x, y, z);
			this.radius = Double.valueOf(st.nextToken()).doubleValue();
			this.height = Double.valueOf(st.nextToken()).doubleValue();
			this.cylinderDivisions = Integer.valueOf(st.nextToken());
			this.xr = 0.0;
			this.yr = 0.0; 
			this.zr = 0.0;
			if (st.hasMoreTokens()) {
				this.xr = Double.valueOf(st.nextToken()).doubleValue();
				this.yr = Double.valueOf(st.nextToken()).doubleValue();
				this.zr = Double.valueOf(st.nextToken()).doubleValue();
			}
			if (st.hasMoreTokens()) {
				this.dm = DrawMode.valueOf(st.nextToken());
			}
			if (st.hasMoreTokens()) {
				this.cf = CullFace.valueOf(st.nextToken());
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
     * Gets the 3D Cylinder location
     *
     * @return	p	Point3D location of the center point of the Cylinder
     */
	public Point3D getP() {
		return p;
	}
	
	/**
     * Gets the Cylinder radius
     *
     * @return	radius	Double value of the radius in user coordinate space
     */
	public double getRadius() {
		return radius;
	}
	
    /**
     * Gets the Cylinder height
     *
     * @return	height	Double value of the height in user coordinate space
     */
	public double getHeight() {
		return height;
	}
	
    /**
     * Gets the number of Cylinder divisions
     *
     * @return	cylinderDivisions	Integer value of the number of Cylinder divisions
     */
	public int getCylinderDivisions() {
		return cylinderDivisions;
	}
	
    /**
     * Sets the number of Cylinder divisions
     *
     * @param	cylinderDivisions	Integer value of the number of Cylinder divisions
     */
	public void setCylinderDivisions(int cylinderDivisions) {
		this.cylinderDivisions = cylinderDivisions;
	}
	
    /**
     * Gets the Cylinder x axis rotation
     *
     * @return	xr	Double value of the Cylinder x axis rotation
     */
	public double getXr() {
		return xr;
	}
	
    /**
     * Gets the Cylinder y axis rotation
     *
     * @return	yr	Double value of the Cylinder y axis rotation
     */
	public double getYr() {
		return yr;
	}
	
    /**
     * Gets the Cylinder z axis rotation
     *
     * @return	zr	Double value of the Cylinder z axis rotation
     */
	public double getZr() {
		return zr;
	}
	
    /**
     * Gets the Cylinder PhongMaterial property
     *
     * @return	m	PhongMaterial value of the Cylinder
     */
	public PhongMaterial getMaterial() {
		return m;
	}
	
    /**
     * Sets the Cylinder PhongMaterial property
     *
     * @param	m	PhongMaterial property of the Cylinder
     */
	public void setMaterial(PhongMaterial m) {
		this.m = m;
	}
	
    /**
     * Gets the Cylinder DrawMode property
     *
     * @return	dm	DrawMode property of the Cylinder
     */
	public DrawMode getDrawMode() {
		return dm;
	}
	
    /**
     * Sets the Cylinder DrawMode property
     *
     * @param	dm	DrawMode property of the Cylinder
     */
	public void setDrawMode(DrawMode dm) {
		this.dm = dm;
	}

    /**
     * Gets the Cylinder CullFace property
     *
     * @return	cf	CullFace property of the Cylinder
     */
	public CullFace getCullFace() {
		return cf;
	}
	
    /**
     * Sets the Cylinder CullFace property
     *
     * @param	cf	CullFace property of the Cylinder
     */
	public void setCullFace(CullFace cf) {
		this.cf = cf;
	}
	
    /**
     * Returns a String containing all Cylinder3D values for saving to a data file.
     * The String is processed by the Cylinder3D(String) constructor to recreate a Cylinder3D
     * object when reading a saved data file.
     * 
     * @return	String capturing all Cylinder3D field values
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
		return "Cylinder: " + p.getX() + ", " + p.getY() + ", " + p.getZ() +
			   ", " + radius + ", " + height +
			   ", " + cylinderDivisions +
			   ", " + xr + ", " + yr + ", " + zr +
			   ", " + dm + ", " + cf + ", " + ms;
	}
	
    /**
     * Returns a String for printing all Cylinder3D values
     *
     * @return	String for printing all Cylinder3D field values
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
		return "Cylinder3D: p= " + p.toString() +
			   ", radius = " + radius + ", height= " + height +
			   ", cylinderDivisions= " + cylinderDivisions +
			   ", xr= " + xr + ", yr= " + yr + ", zr= " + zr +
			   ", dm= " + dm + ", cf= " + cf + ", " + ms;
	}
}