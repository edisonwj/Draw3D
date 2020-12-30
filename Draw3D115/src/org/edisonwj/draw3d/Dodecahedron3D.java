package org.edisonwj.draw3d;

import java.util.StringTokenizer;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
/**
* Dodecahedron3D class captures information defining a Dodecahedron instantiation with specified:
* - xyz location of the Dodecahedron center (user coordinate scale)
* - size  (user coordinate scale), edge-length is 2*size/phi 
* 								   where phi is (1 + Math.sqrt(5))/2
* - xyz rotations (optional)
* - PhongMaterial (optional)
* - DrawMode setting (optional)
* - Cullface setting (optional)
* The Dodecahedron3D object is drawn using a custom Dodecahedron MeshView object.
* 
* The class implements defaults specified in Draw3DDefaults.
* 
* @author William Edison
* @version 1.07 March 2016
* 
*/
public class Dodecahedron3D extends Polyhedron3D implements Draw3DDefaults {
	
//	protected Point3D p;		// Polyhedron3D center location
//	protected double size;		// Polyhedron3D size parameter
//	protected double xr;		// Polyhedron3Dx rotation
//	protected double yr;		// Polyhedron3Dy rotation
//	protected double zr;		// Polyhedron3Dz rotation
//	protected PhongMaterial m;	// Polyhedron3D material
//	protected DrawMode dm;		// Polyhedron3D DrawMode
//	protected CullFace cf;		// Polyhedron3D CullFace
	
	/**
	 * Constructs a Dodecahedron3D object given its center point location and size.
	 *  
	 * @param	p		Point3D Dodecahedron center location in user coordinates
	 * @param	size	Size in user coordinates
	 */	
	public Dodecahedron3D(Point3D p, double size) {
		super(p, size);
	}
	
	/**
	 * Constructs a Dodecahedron3D object given its center point location, size, and PhongMaterial.
	 *  
	 * @param	p		Point3D Dodecahedron center location in user coordinates
	 * @param	size	Size in user coordinates
	 * @param	m		PhongMaterial determining Dodecahedron color
	 */	
	public Dodecahedron3D(Point3D p, double size,
					   PhongMaterial m) {
		super(p, size, m);
	}
	
	/**
	 * Constructs a Dodecahedron3D object given its center point location, size and
	 * x, y, z rotations.
	 *   
	 * @param	p		Point3D Dodecahedron center location in user coordinates
	 * @param	size	Size in user coordinates
	 * @param	xr		Rotation in degrees about the x axis
	 * @param	yr		Rotation in degrees about the y axis
	 * @param	zr		Rotation in degrees about the z axis
	 */	
	public Dodecahedron3D(Point3D p, double size,
					   double xr, double yr, double zr) {
		super(p, size, xr, yr, zr);
	}
	
	/**
	 * Constructs a Dodecahedron3D object given its center point location, size,
	 * x, y, z rotations and PhongMaterial.
	 *  
	 * @param	p		Point3D Dodecahedron center location in user coordinates
	 * @param	size	Size in user coordinates
	 * @param	xr		Rotation in degrees about the x axis
	 * @param	yr		Rotation in degrees about the y axis
	 * @param	zr		Rotation in degrees about the z axis
	 * @param	m		PhongMaterial determining Dodecahedron color
	 */
	public Dodecahedron3D(Point3D p, double size,
			   double xr, double yr, double zr,
			   PhongMaterial m) {
		super(p, size, xr, yr, zr, m);
	}
	
	/**
	 * Constructs a Dodecahedron3D object given its center point location and size.
	 * 
	 * @param	x		Center point x coordinate in user coordinates
	 * @param	y		Center point y coordinate in user coordinates
	 * @param	z		Center point z coordinate in user coordinates
	 * @param	size	Size in user coordinates
	 */
	public Dodecahedron3D(double x, double y, double z, double size) {
		super(x, y, z, size);
	}
	
	/**
	 * Constructs a Dodecahedron3D object given its center point location, size,
	 * and PhongMaterial.
	 * 
	 * @param	x		Center point x coordinate in user coordinates
	 * @param	y		Center point y coordinate in user coordinates
	 * @param	z		Center point z coordinate in user coordinates
	 * @param	size	Size in user coordinates
	 * @param	m		PhongMaterial determining Dodecahedron color
	 */
	public Dodecahedron3D(double x, double y, double z, double size,
					   PhongMaterial m) {
		super(x, y, z, size, m);
	}
	
	/**
	 * Constructs a Dodecahedron3D object given its center point location, size and
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
	public Dodecahedron3D(double x, double y, double z, double size,
					   double xr, double yr, double zr) {
		super(x, y, z, size, xr, yr, zr);
	}
	
	/**
	 * Constructs a Dodecahedron3D object given its center point location, size,
	 * x, y, z rotations and PhongMaterial.
	 * 
	 * @param	x		Center point x coordinate in user coordinates
	 * @param	y		Center point y coordinate in user coordinates
	 * @param	z		Center point z coordinate in user coordinates
	 * @param	size	Size  in user coordinates
	 * @param	xr		Rotation in degrees about the x axis
	 * @param	yr		Rotation in degrees about the y axis
	 * @param	zr		Rotation in degrees about the z axis
	 * @param	m		PhongMaterial determining Dodecahedron color
	 */
	public Dodecahedron3D(double x, double y, double z, double size,
			   double xr, double yr, double zr,
			   PhongMaterial m) {
		super(x, y, z, size, xr, yr, zr, m);
	}
	
	/**
	 * Constructs a Dodecahedron3D object given specification of the parameters
	 * in Save Data File format
	 * 
	 * @param	lineString	Dodecahedron: format data string
	 */
	public Dodecahedron3D(String lineString) {
		super();
		StringTokenizer st = new StringTokenizer(lineString, ",;: ");
		String dataType = st.nextToken().trim();
		if (dataType.equals("Dodecahedron")) {
			double x = Double.valueOf(st.nextToken()).doubleValue();
			double y = Double.valueOf(st.nextToken()).doubleValue();
			double z = Double.valueOf(st.nextToken()).doubleValue();
			this.p = new Point3D(x, y, z);
			this.size = Double.valueOf(st.nextToken()).doubleValue();
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
				String cs = st.nextToken();
				this.m.setDiffuseColor(Color.web(cs));
				if (st.hasMoreTokens()) {
					this.m.setSpecularColor(Color.web(st.nextToken()));
				}
			}
						}
	}
	
	 /**
     * Gets the Dodecahedron vertices
     *
     * @return	v 	Vertices of the Dodecahedron in user coordinates 
     * 				without translation or rotation
     */
	public Point3D[] getVertices() {
		double s = this.size;
		double p = (1 + Math.sqrt(5))/2;
		Point3D[] vertices = {	new Point3D( s,  s,  s),		//  0
								new Point3D( s,  s, -s),		//  1
								new Point3D( s, -s,  s),		//  2
								new Point3D( s, -s, -s),		//  3
								new Point3D(-s,  s,  s),		//  4
								new Point3D(-s,  s, -s),		//  5
								new Point3D(-s, -s,  s),		//  6
								new Point3D(-s, -s, -s),		//  7
								
								new Point3D( 0,  s/p,  s*p),	//  8
								new Point3D( 0,  s/p, -s*p),	//  9
								new Point3D( 0, -s/p,  s*p),	// 10
								new Point3D( 0, -s/p, -s*p),	// 11
								
								new Point3D( s/p,  s*p, 0),		// 12
								new Point3D( s/p, -s*p, 0),		// 13
								new Point3D(-s/p,  s*p, 0),		// 14
								new Point3D(-s/p, -s*p, 0),		// 15
								
								new Point3D( s*p, 0,  s/p),		// 16
								new Point3D( s*p, 0, -s/p),		// 17
								new Point3D(-s*p, 0,  s/p),		// 18
								new Point3D(-s*p, 0, -s/p),		// 19
		 					};
		return vertices;
	}
	
    /**
     * Gets the Dodecahedron edges
     *
     * @return	e 	Edges of the Dodecahedron in user coordinates 
     * 				without translation or rotation
     */
	public Line3D[] getEdges() {
		Point3D[] v = getVertices();
		Line3D[] e = {  
						new Line3D( v[0],  v[8]),
						new Line3D( v[0],  v[12]),
						new Line3D( v[0],  v[16]),
						
						new Line3D( v[1],  v[9]),
						new Line3D( v[1],  v[12]),
						new Line3D( v[1],  v[17]),
						
						new Line3D( v[2],  v[10]),
						new Line3D( v[2],  v[13]),
						new Line3D( v[2],  v[16]),
						
						new Line3D( v[3],  v[11]),
						new Line3D( v[3],  v[13]),
						new Line3D( v[3],  v[17]),
						
						new Line3D( v[4],  v[8]),
						new Line3D( v[4],  v[14]),
						new Line3D( v[4],  v[18]),
						
						new Line3D( v[5],  v[9]),
						new Line3D( v[5],  v[14]),
						new Line3D( v[5],  v[19]),
						
						new Line3D( v[6],  v[10]),
						new Line3D( v[6],  v[15]),
						new Line3D( v[6],  v[18]),
						
						new Line3D( v[7],  v[11]),
						new Line3D( v[7],  v[15]),
						new Line3D( v[7],  v[19]),
						
						new Line3D( v[8],  v[10]),
						new Line3D( v[9],  v[11]),
						
						new Line3D( v[12], v[14]),
						new Line3D( v[13], v[15]),
						
						new Line3D( v[16], v[17]),
						new Line3D( v[18], v[19]),
						
					 };
		return e;
	}
	
    /**
     * Returns a String containing all Dodecahedron3D values for saving to a data file.
     * The String is processed by the Dodecahedron3D(String) constructor to recreate
     * a Dodecahedron3D object when reading a saved data file.
     * 
     * @return	String capturing all Dodecahedron3D field values
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
		return "Dodecahedron: " + p.getX() + ", " + p.getY() + ", " + p.getZ() +
			   ", " + size +
			   ", " + xr + ", " + yr + ", " + zr +
			   ", " + dm + ", " + cf + ", " + ms;
	}
	
    /**
     * Returns a String for printing all Dodecahedron3D values
     *
     * @return	String for printing all Dodecahedron3D field values
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
		return "Dodecahedron3D: p= " + p.toString() +
			   ", size = " + size +
			   ", xr= " + xr + ", yr= " + yr + ", zr= " + zr +
			   ", dm= " + dm + ", cf= " + cf + ", " + ms;
	}
}