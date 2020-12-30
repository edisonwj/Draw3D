package org.edisonwj.draw3d;

import javafx.geometry.Point3D;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.TriangleMesh;
/**
* This class is still under construction.
* Mesh3D class captures information defining a Mesh3D instantiation with specified:
* - TriangleMesh definition of the object
* - xyz location of the object (user coordinate scale)
* - xyz rotations (optional)
* - PhongMaterial (optional)
* - DrawMode setting (optional)
* - Cullface setting (optional)
* The Mesh3D object is drawn using a custom TriangleMesh.
* 
* The class implements defaults specified in Draw3DDefaults.
* 
* @author William Edison
* @version 1.01 August 2015
* 
*/
public class Mesh3D implements Draw3DDefaults {
	private TriangleMesh mesh;
	private Point3D p;
	private double xr;		// x rotation
	private double yr;		// y rotation
	private double zr;		// z rotation
	private PhongMaterial m;
	private DrawMode dm;
	private CullFace cf;
	
	/**
	 * Constructs a Mesh3D object given a Mesh3D and its location.
	 * 
	 * @param	mesh	TriangleMesh
	 * @param	p		Point3D location of the Mesh3D object in user coordinates
	 */	
	public Mesh3D(TriangleMesh mesh, Point3D p) {
		this.mesh = mesh;
		this.p = p;
		this.xr = 0.0;
		this.yr = 0.0;
		this.zr = 0.0;
		this.m = null;
	}
	
	/**
	 * Constructs a Mesh3D object given a TriangleMesh, its location and
	 * x, y, z rotations.
	 *
	 * @param	mesh	TriangleMesh
	 * @param	p		Point3D location of the Mesh3D object in user coordinates
	 * @param	xr		Rotation in degrees about the x axis
	 * @param	yr		Rotation in degrees about the y axis
	 * @param	zr		Rotation in degrees about the z axis
	 */	
	public Mesh3D(TriangleMesh mesh, Point3D p,
					  double xr, double yr, double zr) {
		this(mesh, p);
		this.xr = xr;
		this.yr = yr;
		this.zr = zr;
		this.m = null;
	}
	
	/**
	 * Constructs a Mesh3D object given a TriangleMesh, its location,
	 * x, y, z rotations and PhongMaterial.
	 *
	 * @param	mesh	TriangleMesh
	 * @param	p		Point3D location of the Mesh3D object in user coordinates
	 * @param	xr		Rotation in degrees about the x axis
	 * @param	yr		Rotation in degrees about the y axis
	 * @param	zr		Rotation in degrees about the z axis
	 * @param	m		PhongMaterial determining Mesh3D object color
	 */
	public Mesh3D(TriangleMesh mesh, Point3D p,
			   		  double xr, double yr, double zr,
			   		  PhongMaterial m) {
		this(mesh, p, xr, yr, zr);
		this.m = m;
	}
		
    /**
     * Gets the TriangleMesh
     *
     * @return	mesh	TriangleMesh
     */
	public TriangleMesh getMesh() {
		return mesh;
	}

    /**
     * Gets the Mesh3D location
     *
     * @return	p	Point3D location of the Mesh3D object
     */
	public Point3D getP() {
		return p;
	}
	
    /**
     * Gets the Mesh3D x axis rotation
     *
     * @return	xr	Double value of the Mesh3D x axis rotation
     */
	public double getXr() {
		return xr;
	}
	
    /**
     * Gets the Mesh3D y axis rotation
     *
     * @return	yr	Double value of the Mesh3D y axis rotation
     */
	public double getYr() {
		return yr;
	}
	
    /**
     * Gets the Mesh3D z axis rotation
     *
     * @return	zr	Double value of the Mesh3D z axis rotation
     */
	public double getZr() {
		return zr;
	}
	
    /**
     * Gets the Mesh3D PhongMaterial property
     *
     * @return	m	PhongMaterial value of the Mesh3D
     */
	public PhongMaterial getMaterial() {
		return m;
	}
	
    /**
     * Sets the Mesh3D PhongMaterial property
     *
     * @param	m	PhongMaterial property of the Mesh3D
     */
	public void setMaterial(PhongMaterial m) {
		this.m = m;
	}
	
    /**
     * Gets the Mesh3D DrawMode property
     *
     * @return	dm	DrawMode property of the Mesh3D
     */
	public DrawMode getDrawMode() {
		return dm;
	}

    /**
     * Sets the Mesh3D DrawMode property
     *
     * @param	dm	DrawMode property of the Mesh3D
     */
	public void setDrawMode(DrawMode dm) {
		this.dm = dm;
	}

    /**
     * Gets the Mesh3D CullFace property
     *
     * @return	cf	CullFace property of the Mesh3D
     */
	public CullFace getCullFace() {
		return cf;
	}

    /**
     * Sets the Mesh3D CullFace property
     *
     * @param	cf	CullFace property of the Mesh3D
     */
	public void setCullFace(CullFace cf) {
		this.cf = cf;
	}
	
    /**
     * Returns a String for printing all Mesh3D values
     *
     * @return	String for printing all Mesh3D field values
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
		return "Mesh: p= " + p.toString() +
			   ", xr= " + xr + ", yr= " + yr + ", zr= " + zr +
			   ", dm= " + dm + ", cf= " + cf + ", " + ms;
	}
}