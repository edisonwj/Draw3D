package org.edisonwj.draw3d;

import javafx.geometry.Point3D;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
/**
* Polyhedron3D class captures information defining a Polyhedron instantiation with specified:
* - xyz location of the Polyhedron center (user coordinate scale)
* - size (user coordinate scale)
* - xyz rotations (optional)
* - PhongMaterial (optional)
* - DrawMode setting (optional)
* - Cullface setting (optional)
* The Polyhedron3D object is drawn using a custom Polyhedron MeshView object.
* 
* The class implements defaults specified in Draw3DDefaults.
* 
* @author William Edison
* @version 1.06 February 2016
* 
*/
abstract class Polyhedron3D implements Draw3DDefaults {
	protected Point3D p;		// center location
	protected double size;	// size parameter
	protected double xr;		// x rotation
	protected double yr;		// y rotation
	protected double zr;		// z rotation
	protected PhongMaterial m = null;
	protected DrawMode dm = null;
	protected CullFace cf= null;
	
	/**
	 * Constructs a default Polyhedron3D object.
	 *  
	 */	
	public Polyhedron3D() {};
	
	/**
	 * Constructs a Polyhedron3D object given its center point location and size.
	 *  
	 * @param	p		Point3D Polyhedron center location in user coordinates
	 * @param	size	Size in user coordinates
	 */	
	public Polyhedron3D(Point3D p, double size) {
		this.p = p;
		this.size = size;
		this.xr = 0.0;
		this.yr = 0.0;
		this.zr = 0.0;
	}
	
	/**
	 * Constructs a Polyhedron3D object given its center point location, size, and PhongMaterial.
	 *  
	 * @param	p		Point3D Polyhedron center location in user coordinates
	 * @param	size	Size in user coordinates
	 * @param	m		PhongMaterial determining Polyhedron color
	 */	
	public Polyhedron3D(Point3D p, double size,
					   PhongMaterial m) {
		this(p, size);
		this.m = m;
	}
	
	/**
	 * Constructs a Polyhedron3D object given its center point location, size and
	 * x, y, z rotations.
	 *   
	 * @param	p		Point3D Polyhedron center location in user coordinates
	 * @param	size	Size in user coordinates
	 * @param	xr		Rotation in degrees about the x axis
	 * @param	yr		Rotation in degrees about the y axis
	 * @param	zr		Rotation in degrees about the z axis
	 */	
	public Polyhedron3D(Point3D p, double size,
					   double xr, double yr, double zr) {
		this(p, size);
		this.xr = xr;
		this.yr = yr;
		this.zr = zr;
	}
	
	/**
	 * Constructs a Polyhedron3D object given its center point location, size,
	 * x, y, z rotations and PhongMaterial.
	 *  
	 * @param	p		Point3D Polyhedron center location in user coordinates
	 * @param	size	Size in user coordinates
	 * @param	xr		Rotation in degrees about the x axis
	 * @param	yr		Rotation in degrees about the y axis
	 * @param	zr		Rotation in degrees about the z axis
	 * @param	m		PhongMaterial determining Polyhedron color
	 */
	public Polyhedron3D(Point3D p, double size,
			   double xr, double yr, double zr,
			   PhongMaterial m) {
		this(p, size, xr, yr, zr);
		this.m = m;
	}
	
	/**
	 * Constructs a Polyhedron3D object given its center point location, size,
	 * x, y, z rotations, PhongMaterial, DrawMode and CullFace parameters.
	 *  
	 * @param	p		Point3D Polyhedron center location in user coordinates
	 * @param	size	Size in user coordinates
	 * @param	xr		Rotation in degrees about the x axis
	 * @param	yr		Rotation in degrees about the y axis
	 * @param	zr		Rotation in degrees about the z axis
	 * @param	m		PhongMaterial determining Polyhedron color
	 * @param	dm		DrawMode parameter
	 * @param	cf		CullFace paramter
	 */
	public Polyhedron3D(Point3D p, double size,
			   double xr, double yr, double zr,
			   PhongMaterial m, DrawMode dm, CullFace cf) {
		this(p, size, xr, yr, zr, m);
		this.dm = dm;
		this.cf = cf;
	}
	
	/**
	 * Constructs a Polyhedron3D object given its center point location and size.
	 * 
	 * @param	x		Center point x coordinate in user coordinates
	 * @param	y		Center point y coordinate in user coordinates
	 * @param	z		Center point z coordinate in user coordinates
	 * @param	size	Size in user coordinates
	 */
	public Polyhedron3D(double x, double y, double z, double size) {
		this.p = new Point3D(x, y, z);
		this.size = size;
		this.xr = 0.0;
		this.yr = 0.0;
		this.zr = 0.0;
	}
	
	/**
	 * Constructs a Polyhedron3D object given its center point location, size,
	 * and PhongMaterial.
	 * 
	 * @param	x		Center point x coordinate in user coordinates
	 * @param	y		Center point y coordinate in user coordinates
	 * @param	z		Center point z coordinate in user coordinates
	 * @param	size	Size in user coordinates
	 * @param	m		PhongMaterial determining Polyhedron color
	 */
	public Polyhedron3D(double x, double y, double z, double size,
					   PhongMaterial m) {
		this(x, y, z, size);
		this.m = m;
	}
	
	/**
	 * Constructs a Polyhedron3D object given its center point location, size and
	 * x, y, z rotations.
	 * 
	 * @param	x		Center point x coordinate in user coordinates
	 * @param	y		Center point y coordinate in user coordinates
	 * @param	z		Center point z coordinate in user coordinates
	 * @param	size	Size in user coordinates
	 * @param	xr		Rotation in degrees about the x axis
	 * @param	yr		Rotation in degrees about the y axis
	 * @param	zr		Rotation in degrees about the z axis
	 */
	public Polyhedron3D(double x, double y, double z, double size,
					   double xr, double yr, double zr) {
		this(x, y, z, size);
		this.xr = xr;
		this.yr = yr;
		this.zr = zr;
	}
	
	/**
	 * Constructs a Polyhedron3D object given its center point location, size,
	 * x, y, z rotations and PhongMaterial.
	 * 
	 * @param	x		Center point x coordinate in user coordinates
	 * @param	y		Center point y coordinate in user coordinates
	 * @param	z		Center point z coordinate in user coordinates
	 * @param	size	Size in user coordinates
	 * @param	xr		Rotation in degrees about the x axis
	 * @param	yr		Rotation in degrees about the y axis
	 * @param	zr		Rotation in degrees about the z axis
	 * @param	m		PhongMaterial determining Polyhedron color
	 */
	public Polyhedron3D(double x, double y, double z, double size,
			   double xr, double yr, double zr,
			   PhongMaterial m) {
		this(x, y, z, size, xr, yr, zr);
		this.m = m;
	}
	
    /**
     * Gets the 3D Polyhedron location
     *
     * @return	p	Point3D location of the center of the Polyhedron
     */
	public Point3D getP() {
		return p;
	}
	
    /**
     * Sets the 3D Polyhedron location
     *
     * @param	p	Point3D location of the center of the Polyhedron
     */
	public void setP(Point3D p) {
		this.p = p;
	}
	
    /**
     * Sets size
     *
     * @param	size	Double value of the size in user coordinate space
     */
	public void setSize(double size) {
		this.size = size;
	}
    /**
     * Gets size
     *
     * @return	size	Double value of the size in user coordinate space
     */
	public double getSize() {
		return size;
	}
	
    /**
     * Gets the Polyhedron x axis rotation
     *
     * @return	xr	Double value of the Polyhedron x axis rotation
     */
	public double getXr() {
		return xr;
	}
	
    /**
     * Sets the Polyhedron x axis rotation
     *
     * @param	xr	Double value of the Polyhedron x axis rotation
     */
	public void setXr(double xr) {
		this.xr = xr;
	}
	
    /**
     * Gets the Polyhedron y axis rotation
     *
     * @return	yr	Double value of the Polyhedron y axis rotation
     */
	public double getYr() {
		return yr;
	}
	
    /**
     * Sets the Polyhedron y axis rotation
     *
     * @param	yr	Double value of the Polyhedron y axis rotation
     */
	public void setYr(double yr) {
		this.yr = yr;
	}
	
    /**
     * Gets the Polyhedron z axis rotation
     *
     * @return	zr	Double value of the Polyhedron z axis rotation
     */
	public double getZr() {
		return zr;
	}
	
    /**
     * Sets the Polyhedron z axis rotation
     *
     * @param	zr	Double value of the Polyhedron z axis rotation
     */
	public void setZr(double zr) {
		this.xr = zr;
	}
	
    /**
     * Gets the Polyhedron PhongMaterial property
     *
     * @return	m	PhongMaterial value of the Polyhedron
     */
	public PhongMaterial getMaterial() {
		return m;
	}
	
    /**
     * Sets the Polyhedron PhongMaterial property
     *
     * @param	m	PhongMaterial property of the Polyhedron
     */
	public void setMaterial(PhongMaterial m) {
		this.m = m;
	}
	
    /**
     * Gets the Polyhedron DrawMode property
     *
     * @return	dm	DrawMode property of the Polyhedron
     */
	public DrawMode getDrawMode() {
		return dm;
	}

    /**
     * Sets the Polyhedron DrawMode property
     *
     * @param	dm	DrawMode property of the Polyhedron
     */
	public void setDrawMode(DrawMode dm) {
		this.dm = dm;
	}

    /**
     * Gets the Polyhedron CullFace property
     *
     * @return	cf	CullFace property of the Polyhedron
     */
	public CullFace getCullFace() {
		return cf;
	}

    /**
     * Sets the Polyhedron CullFace property
     *
     * @param	cf	CullFace property of the Polyhedron
     */
	public void setCullFace(CullFace cf) {
		this.cf = cf;
	}
	
    /**
     * Gets the Polyhedron vertices
     *
     * @return	v 	Vertices of the Polyhedron in user coordinates 
     * 				without translation or rotation
     */
	abstract Point3D[] getVertices();
	
    /**
     * Gets the Polyhedron edges
     *
     * @return	e 	Edges of the Polyhedron in user coordinates 
     * 				without translation or rotation
     */
	abstract Line3D[] getEdges();
	
    /**
     * Returns a String containing all Polyhedron3D values for saving to a data file.
     * The String is processed by the Polyhedron3D(String) constructor to recreate
     * a Polyhedron3D object when reading a saved data file.
     * 
     * @return	String capturing all Polyhedron3D field values
     */
	abstract String outString();
}