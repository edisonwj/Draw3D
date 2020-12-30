package org.edisonwj.draw3d;

import java.util.StringTokenizer;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
/**
* Icosahedron3D class captures information defining a Icosahedron instantiation with specified:
* - xyz location of the Icosahedron center (user coordinate scale)
* - size (user coordinate scale), edge-length is 2*size
* - xyz rotations (optional)
* - PhongMaterial (optional)
* - DrawMode setting (optional)
* - Cullface setting (optional)
* The Icosahedron3D object is drawn using a custom Icosahedron MeshView object.
* 
* The class implements defaults specified in Draw3DDefaults.
* 
* @author William Edison
* @version 1.07 March 2016
* 
*/
public class Icosahedron3D extends Polyhedron3D implements Draw3DDefaults {
	
//	protected Point3D p;		// Polyhedron3D center location
//	protected double size;		// Polyhedron3D size parameter
//	protected double xr;		// Polyhedron3Dx rotation
//	protected double yr;		// Polyhedron3Dy rotation
//	protected double zr;		// Polyhedron3Dz rotation
//	protected PhongMaterial m;	// Polyhedron3D material
//	protected DrawMode dm;		// Polyhedron3D DrawMode
//	protected CullFace cf;		// Polyhedron3D CullFace
	
	/**
	 * Constructs an Icosahedron3D object given its center point location and size.
	 *  
	 * @param	p		Point3D Icosahedron center location in user coordinates
	 * @param	size	Size in user coordinates
	 */	
	public Icosahedron3D(Point3D p, double size) {
		super(p, size);
	}
	
	/**
	 * Constructs an Icosahedron3D object given its center point location, size, and PhongMaterial.
	 *  
	 * @param	p		Point3D Icosahedron center location in user coordinates
	 * @param	size	Size in user coordinates
	 * @param	m		PhongMaterial determining Icosahedron color
	 */	
	public Icosahedron3D(Point3D p, double size,
					   PhongMaterial m) {
		super(p, size, m);
	}
	
	/**
	 * Constructs an Icosahedron3D object given its center point location, size and
	 * x, y, z rotations.
	 *   
	 * @param	p		Point3D Icosahedron center location in user coordinates
	 * @param	size	Size in user coordinates
	 * @param	xr		Rotation in degrees about the x axis
	 * @param	yr		Rotation in degrees about the y axis
	 * @param	zr		Rotation in degrees about the z axis
	 */	
	public Icosahedron3D(Point3D p, double size,
					   double xr, double yr, double zr) {
		super(p, size, xr, yr, zr);
	}
	
	/**
	 * Constructs an Icosahedron3D object given its center point location, size,
	 * x, y, z rotations and PhongMaterial.
	 *  
	 * @param	p		Point3D Icosahedron center location in user coordinates
	 * @param	size	Size in user coordinates
	 * @param	xr		Rotation in degrees about the x axis
	 * @param	yr		Rotation in degrees about the y axis
	 * @param	zr		Rotation in degrees about the z axis
	 * @param	m		PhongMaterial determining Icosahedron color
	 */
	public Icosahedron3D(Point3D p, double size,
			   double xr, double yr, double zr,
			   PhongMaterial m) {
		super(p, size, xr, yr, zr, m);
	}
	
	/**
	 * Constructs an Icosahedron3D object given its center point location and size.
	 * 
	 * @param	x		Center point x coordinate in user coordinates
	 * @param	y		Center point y coordinate in user coordinates
	 * @param	z		Center point z coordinate in user coordinates
	 * @param	size	Size in user coordinates
	 */
	public Icosahedron3D(double x, double y, double z, double size) {
		super(x, y, z, size);

	}
	
	/**
	 * Constructs an Icosahedron3D object given its center point location, size,
	 * and PhongMaterial.
	 * 
	 * @param	x		Center point x coordinate in user coordinates
	 * @param	y		Center point y coordinate in user coordinates
	 * @param	z		Center point z coordinate in user coordinates
	 * @param	size	Size in user coordinates
	 * @param	m		PhongMaterial determining Icosahedron color
	 */
	public Icosahedron3D(double x, double y, double z, double size,
					   PhongMaterial m) {
		super(x, y, z, size, m);
	}
	
	/**
	 * Constructs an Icosahedron3D object given its center point location, size and
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
	public Icosahedron3D(double x, double y, double z, double size,
					   double xr, double yr, double zr) {
		super(x, y, z, size, xr, yr, zr);
	}
	
	/**
	 * Constructs an Icosahedron3D object given its center point location, size,
	 * x, y, z rotations and PhongMaterial.
	 * 
	 * @param	x		Center point x coordinate in user coordinates
	 * @param	y		Center point y coordinate in user coordinates
	 * @param	z		Center point z coordinate in user coordinates
	 * @param	size	Size in user coordinates
	 * @param	xr		Rotation in degrees about the x axis
	 * @param	yr		Rotation in degrees about the y axis
	 * @param	zr		Rotation in degrees about the z axis
	 * @param	m		PhongMaterial determining Icosahedron color
	 */
	public Icosahedron3D(double x, double y, double z, double size,
			   double xr, double yr, double zr,
			   PhongMaterial m) {
		super(x, y, z, size, xr, yr, zr, m);

	}
	
	/**
	 * Constructs an Icosahedron3D object given specification of the parameters
	 * in Save Data File format
	 * 
	 * @param	lineString	Icosahedron: format data string
	 */
	public Icosahedron3D(String lineString) {
		super();
		StringTokenizer st = new StringTokenizer(lineString, ",;: ");
		String dataType = st.nextToken().trim();
		if (dataType.equals("Icosahedron")) {
			double x = Double.valueOf(st.nextToken()).doubleValue();
			double y = Double.valueOf(st.nextToken()).doubleValue();
			double z = Double.valueOf(st.nextToken()).doubleValue();
			this.p = new Point3D(x, y, z);
			this.size = Double.valueOf(st.nextToken()).doubleValue();
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
				PhongMaterial m = new PhongMaterial();
				String cs = st.nextToken();
				m.setDiffuseColor(Color.web(cs));
				if (st.hasMoreTokens()) {
					m.setSpecularColor(Color.web(st.nextToken()));
				}
				this.m = m;
			}
		}
	}
	
    /**
     * Gets the Icosahedron vertices
     *
     * @return	v 	Vertices of the Icosahedron in user coordinates 
     * 				without translation or rotation
     */
	public Point3D[] getVertices() {
		double s = size;
		double phi = (1 + Math.sqrt(5))/2;
		double p = s*phi;
		Point3D[] v = { new Point3D( 0,  p,  s),	//  0
						new Point3D( 0,  p, -s),	//  1
						new Point3D( 0, -p,  s),	//  2
						new Point3D( 0, -p, -s),	//  3
						new Point3D(-p,  s,  0),	//  4
						new Point3D(-p, -s,  0),	//  5
						new Point3D( p,  s,  0),	//  6
						new Point3D( p, -s,  0),	//  7
						new Point3D(-s,  0,  p),	//  8
						new Point3D( s,  0,  p),	//  9
						new Point3D(-s,  0, -p),	// 10
						new Point3D( s,  0, -p)		// 11
					 };	
		return v;
	}
	
    /**
     * Gets the Icosahedron edges
     *
     * @return	e 	Edges of the Icosahedron in user coordinates 
     * 				without translation or rotation
     */
	public Line3D[] getEdges() {
		Point3D[] v = getVertices();
		Line3D[] e = {  new Line3D(v[0],  v[1]),
						new Line3D(v[2],  v[3]), 
						new Line3D(v[4],  v[5]), 
						new Line3D(v[6],  v[7]),  
						new Line3D(v[8],  v[9]), 
						new Line3D(v[10], v[11]),
						new Line3D(v[0],  v[4]), 
						new Line3D(v[0],  v[6]), 
						new Line3D(v[0],  v[8]),  
						new Line3D(v[0],  v[9]), 
						new Line3D(v[1],  v[4]),  
						new Line3D(v[1],  v[6]), 
						new Line3D(v[1],  v[10]), 
						new Line3D(v[1],  v[11]), 
						new Line3D(v[2],  v[5]), 
						new Line3D(v[2],  v[7]),
						new Line3D(v[2],  v[8]),
						new Line3D(v[2],  v[9]), 
						new Line3D(v[3],  v[5]),
						new Line3D(v[3],  v[7]),
						new Line3D(v[3],  v[10]),
						new Line3D(v[3],  v[11]),
						new Line3D(v[4],  v[8]),
						new Line3D(v[4],  v[10]),
						new Line3D(v[5],  v[8]),
						new Line3D(v[5],  v[10]),
						new Line3D(v[6],  v[9]),
						new Line3D(v[6],  v[11]),
						new Line3D(v[7],  v[9]),
						new Line3D(v[7],  v[11]),
						new Line3D(v[3],  v[5])
					};
		return e;
	}
	
    /**
     * Returns a String containing all Icosahedron3D values for saving to a data file.
     * The String is processed by the Icosahedron3D(String) constructor to recreate
     * a Icosahedron3D object when reading a saved data file.
     * 
     * @return	String capturing all Icosahedron3D field values
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
		return "Icosahedron: " + p.getX() + ", " + p.getY() + ", " + p.getZ() +
			   ", " + size +
			   ", " + xr + ", " + yr + ", " + zr +
			   ", " + dm + ", " + cf + ", " + ms;
	}
	
    /**
     * Returns a String for printing all Icosahedron3D values
     *
     * @return	String for printing all Icosahedron3D field values
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
		return "Icosahedron: p= " + p.toString() +
			   ", size = " + size +
			   ", xr= " + xr + ", yr= " + yr + ", zr= " + zr +
			   ", dm= " + dm + ", cf= " + cf + ", " + ms;
	}
}