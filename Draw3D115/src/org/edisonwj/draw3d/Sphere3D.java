package org.edisonwj.draw3d;

import java.util.StringTokenizer;

import javafx.geometry.Point3D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
/**
* Sphere3D class captures information defining a JavaFX Sphere instantiation with specified:
* - xyz location of the Sphere center point (user coordinate scale)
* - radius (user coordinate scale)
* - number of divisions
* - PhongMaterial (optional)
* - DrawMode setting (optional)
* - Cullface setting (optional)
* Sphere3D objects are drawn as javafx.scene.shape.sphere objects.
* 
* The class implements defaults specified in Draw3DDefaults.
* 
* @author William Edison
* @version 1.14 March 2018
* 
*/
public class Sphere3D implements Draw3DDefaults {
	private Point3D p;
	private double radius;
	private double xr;		// x rotation
	private double yr;		// y rotation
	private double zr;		// z rotation
	private int sphereDivisions = SPHERE_DIVISIONS;
	private PhongMaterial m;
	private DrawMode dm;
	private CullFace cf;
	
	/**
	 * Constructs a Sphere3D object given its center point location and radius.
	 *  
	 * @param	p		Point3D sphere center location in user coordinates
	 * @param	radius	Radius of the sphere in user coordinates
	 */	
	public Sphere3D(Point3D p, double radius) {
		this.p = p;
		this.radius = radius;
		this.xr = 0.0;
		this.yr = 0.0;
		this.zr = 0.0;
		this.m = null;
	}
	
	/**
	 * Constructs a Sphere3D object given its center point location, radius and PhongMaterial.
	 *  
	 * @param	p		Point3D sphere center location in user coordinates
	 * @param	radius	Radius of the sphere in user coordinates
	 * @param	m		PhongMaterial determining sphere color
	 */	
	public Sphere3D(Point3D p, double radius,
					   PhongMaterial m) {
		this(p, radius);
		this.m = m;
	}
	
	/**
	 * Constructs a Sphere3D object given its center point location, radius
	 * and x, y, z rotations.
	 *  
	 * @param	p		Point3D sphere center location in user coordinates
	 * @param	radius	Radius of the sphere in user coordinates
	 * @param	xr		Rotation in degrees about the x axis
	 * @param	yr		Rotation in degrees about the y axis
	 * @param	zr		Rotation in degrees about the z axis
	 */	
	public Sphere3D(Point3D p, double radius,
					   double xr, double yr, double zr) {
		this(p, radius);
		this.xr = xr;
		this.yr = yr;
		this.zr = zr;
		this.m = null;
	}
	
	/**
	 * Constructs a Sphere3D object given its center point location, radius,
	 * x, y, z rotations and PhongMaterial.
	 *  
	 * @param	p		Point3D sphere center location in user coordinates
	 * @param	radius	Radius of the sphere in user coordinates
	 * @param	xr		Rotation in degrees about the x axis
	 * @param	yr		Rotation in degrees about the y axis
	 * @param	zr		Rotation in degrees about the z axis
	 * @param	m		PhongMaterial determining sphere color
	 */	
	public Sphere3D(Point3D p, double radius,
			   double xr, double yr, double zr,
			   PhongMaterial m) {
		this(p, radius, xr, yr, zr);
		this.m = m;
	}
	
	/**
	 * Constructs a Sphere3D object given its center point coordinates and radius.
	 * 
	 * @param	x		Center point x coordinate in user coordinates
	 * @param	y		Center point y coordinate in user coordinates
	 * @param	z		Center point z coordinate in user coordinates
	 * @param	radius	Radius of the sphere in user coordinates
	 */
	public Sphere3D(double x, double y, double z, double radius) {
		this.p = new Point3D(x, y, z);
		this.radius = radius;
		this.xr = 0.0;
		this.yr = 0.0;
		this.zr = 0.0;
		this.m = null;
	}
	
	/**
	 * Constructs a Sphere3D object given its center point coordinates, radius
	 * and PhongMaterial.
	 * 
	 * @param	x		Center point x coordinate in user coordinates
	 * @param	y		Center point y coordinate in user coordinates
	 * @param	z		Center point z coordinate in user coordinates
	 * @param	radius	Radius of the sphere in user coordinates
	 * @param	m		PhongMaterial determining sphere color
	 */
	public Sphere3D(double x, double y, double z, double radius,
					   PhongMaterial m) {
		this(x, y, z, radius);
		this.m = m;
	}
	
	/**
	 * Constructs a Sphere3D object given its center point coordinates, radius
	 * and x, y, z rotations.
	 * 
	 * @param	x		Center point x coordinate in user coordinates
	 * @param	y		Center point y coordinate in user coordinates
	 * @param	z		Center point z coordinate in user coordinates
	 * @param	radius	Radius of the sphere in user coordinates
	 * @param	xr		Rotation in degrees about the x axis
	 * @param	yr		Rotation in degrees about the y axis
	 * @param	zr		Rotation in degrees about the z axis
	 */
	public Sphere3D(double x, double y, double z, double radius,
					   double xr, double yr, double zr) {
		this(x, y, z, radius);
		this.xr = xr;
		this.yr = yr;
		this.zr = zr;
		this.m = null;
	}
	
	/**
	 * Constructs a Sphere3D object given its center point coordinates, radius,
	 * x, y, z rotations and PhongMaterial.
	 * 
	 * @param	x		Center point x coordinate in user coordinates
	 * @param	y		Center point y coordinate in user coordinates
	 * @param	z		Center point z coordinate in user coordinates
	 * @param	radius	Radius of the sphere in user coordinates
	 * @param	xr		Rotation in degrees about the x axis
	 * @param	yr		Rotation in degrees about the y axis
	 * @param	zr		Rotation in degrees about the z axis
	 * @param	m		PhongMaterial determining sphere color
	 */
	public Sphere3D(double x, double y, double z, double radius,
			   double xr, double yr, double zr,
			   PhongMaterial m) {
		this(x, y, z, radius, xr, yr, zr);
		this.m = m;
	}
	
	/**
	 * Constructs a Sphere3D object given specification of the parameters
	 * in Save Data File format
	 * 
	 * @param	lineString	Sphere: format data string
	 */
	public Sphere3D( String lineString) {
		StringTokenizer st = new StringTokenizer(lineString, ",;: ");
		String dataType = st.nextToken().trim();
		if (dataType.equals("Sphere")) {
			double x = Double.valueOf(st.nextToken()).doubleValue();
			double y = Double.valueOf(st.nextToken()).doubleValue();
			double z = Double.valueOf(st.nextToken()).doubleValue();
			this.p = new Point3D(x, y, z);
			this.radius = Double.valueOf(st.nextToken()).doubleValue();
			this.sphereDivisions = Integer.valueOf(st.nextToken());
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
     * Gets the 3D Sphere location
     *
     * @return	p	Point3D location of the center point of the Sphere
     */
	public Point3D getP() {
		return p;
	}
	
	/**
     * Gets the Sphere radius
     *
     * @return	radius	Double value of the radius in user coordinate space
     */
	public double getRadius() {
		return radius;
	}
	
	/**
     * Sets the Sphere radius
     *
     * @param	radius	Double value of the radius in user coordinate space
     */
	public void setRadius(double radius) {
		this.radius = radius;
	}
	
    /**
     * Gets the number of Sphere divisions
     *
     * @return	sphereDivisions	Integer value of the number of Sphere divisions
     */
	public int getSphereDivisions() {
		return sphereDivisions;
	}
	
    /**
     * Sets the number of Sphere divisions
     *
     * @param	sphereDivisions	Integer value of the number of Sphere divisions
     */
	public void setSphereDivisions(int sphereDivisions) {
		this.sphereDivisions = sphereDivisions;
	}
	
    /**
     * Gets the Sphere x axis rotation
     *
     * @return	xr	Double value of the Sphere x axis rotation
     */
	public double getXr() {
		return xr;
	}
	
    /**
     * Gets the Sphere y axis rotation
     *
     * @return	yr	Double value of the Sphere y axis rotation
     */
	public double getYr() {
		return yr;
	}
	
    /**
     * Gets the Sphere z axis rotation
     *
     * @return	zr	Double value of the Sphere z axis rotation
     */
	public double getZr() {
		return zr;
	}
	
    /**
     * Gets the Sphere PhongMaterial property
     *
     * @return	m	PhongMaterial value of the Sphere
     */
	public PhongMaterial getMaterial() {
		return m;
	}
	
    /**
     * Sets the Sphere PhongMaterial property
     *
     * @param	m	PhongMaterial property of the Sphere
     */
	public void setMaterial(PhongMaterial m) {
		this.m = m;
	}
	
	/**
     * Gets the Sphere DrawMode property
     *
     * @return	dm	DrawMode property of the Sphere
     */
	public DrawMode getDrawMode() {
		return dm;
	}
	
    /**
     * Sets the Sphere DrawMode property
     *
     * @param	dm	DrawMode property of the Sphere
     */
	public void setDrawMode(DrawMode dm) {
		this.dm = dm;
	}

    /**
     * Gets the Sphere CullFace property
     *
     * @return	cf	CullFace property of the Sphere
     */
	public CullFace getCullFace() {
		return cf;
	}
	
    /**
     * Sets the Sphere CullFace property
     *
     * @param	cf	CullFace property of the Sphere
     */
	public void setCullFace(CullFace cf) {
		this.cf = cf;
	}
	
    /**
     * Returns a String containing all Sphere3D values for saving to a data file.
     * The String is processed by the Sphere3D(String) constructor to recreate a Sphere3D
     * object when reading a saved data file.
     * 
     * @return	String capturing all Sphere3D field values
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
		return "Sphere: " + p.getX() + ", " + p.getY() + ", " + p.getZ() +
			   ", " + radius +
			   ", " + sphereDivisions +
			   ", " + xr + ", " + yr + ", " + zr + ", " +
			   ", " + dm + ", " + cf + ", " + ms;
	}
	
    /**
     * Returns a String for printing all Sphere3D values
     *
     * @return	String for printing all Sphere3D field values
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
		return "Sphere3D: p= " + p.toString() +
			   ", radius = " + radius +
			   ", sphereDivisions= " + sphereDivisions +
			   ", xr= " + xr + ", yr= " + yr + ", zr= " + zr +
			   ", dm= " + dm + ", cf= " + cf + ", " + ms;
	}
}