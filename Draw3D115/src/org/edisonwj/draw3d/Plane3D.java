package org.edisonwj.draw3d;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
/**
* Plane3D class captures information defining a Plane3D instantiation with three specified points:
* - Point3D p1 (in user coordinate scale)
* - Point3D p2 (in user coordinate scale)
* - Point3D p3 (in user coordinate scale)
* or with coordinates of the plane equation:
* - a the coefficient of x in the plane equation - ax + by + cz = d
* - b the coefficient of y in the plane equation
* - c the coefficient of z in the plane equation
* - d coefficient in the plane equation
*
* - PhongMaterial (optional)
* - DrawMode setting (optional)
* - Cullface setting (optional)
* 
* The Plane3D object is drawn as a convex polygon bounded by the current user coordinate space.
* 
* The class implements defaults specified in Draw3DDefaults.
* 
* @author William Edison
* @version 1.14 February 2018
* 
*/
public class Plane3D implements Draw3DDefaults {
	private Point3D p1;				// points defining the plane
	private Point3D p2;
	private Point3D p3;
	private double a;				// x coefficient for plane equation - ax + by + cz = d
	private double b;				// y coefficient for plane equation
	private double c;				// z coefficient for plane equation
	private double d;				// d coefficient for plane equation
	private double p;				// distance to origin from associated plane
	private Vector3D normal;		// normal vector
	private Vector3D unitNormal;	// unit normal vector
	private PhongMaterial m;
	private DrawMode dm;
	private CullFace cf;
	private Point3D[] ei;			// boundary edge intersection points
	private int[][] tri;			// Triangulation
	
	/**
	 * Constructs a Plane3D object given three defining points.
	 *  
	 * @param	p1		Point3D point1 location in user coordinates
	 * @param	p2		Point3D point2 location in user coordinates
	 * @param	p3		Point3D point3 location in user coordinates
	 */	
    public Plane3D(Point3D p1, Point3D p2, Point3D p3) {
    	this.p1 = p1;
    	this.p2 = p2;
    	this.p3 = p3;
    	this.m = null;
    	findNormals();
	}
    
	/**
	 * Constructs a Plane3D object given three defining points and PhongMaterial.
	 *  
	 * @param	p1		Point3D point1 location in user coordinates
	 * @param	p2		Point3D point2 location in user coordinates
	 * @param	p3		Point3D point3 location in user coordinates
	 * @param	m		PhongMaterial determining plane color
	 */	
    public Plane3D(Point3D p1, Point3D p2, Point3D p3, PhongMaterial m) {
    	this(p1, p2, p3);
    	this.m = m;
	}
    
	/**
	 * Constructs a Plane3D object given a Point3d[] Array containing three defining points.
	 *  
	 * @param	ps		Point3D[] Array containing three defining points
	 */	
    public Plane3D(Point3D[] ps) {
    	this(ps[0], ps[1], ps[2]);
    	this.m = null;
    }
    
	/**
	 * Constructs a Plane3D object given a Point3d[] Array containing three defining points
	 * and PhongMaterial
	 *  
	 * @param	ps		Point3D[] Array containing three defining points
	 * @param	m		PhongMaterial determining plane color
	 */	
    public Plane3D(Point3D[] ps, PhongMaterial m) {
    	this(ps[0], ps[1], ps[2]);
    	this.m = m;
	}
   
	/**
	 * Constructs a Plane3D object given the coordinates of three defining points.
	 * 
	 * @param	x1		Point1 x coordinate in user coordinates
	 * @param	y1		Point1 y coordinate in user coordinates
	 * @param	z1		Point1 z coordinate in user coordinates
	 * @param	x2		Point2 x coordinate in user coordinates
	 * @param	y2		Point2 y coordinate in user coordinates
	 * @param	z2		Point2 z coordinate in user coordinates
	 * @param	x3		Point3 x coordinate in user coordinates
	 * @param	y3		Point3 y coordinate in user coordinates
	 * @param	z3		Point3 z coordinate in user coordinates
	 */
	public Plane3D(double x1, double y1, double z1,
			   double x2, double y2, double z2,
			   double x3, double y3, double z3) {
		this.p1 = new Point3D(x1, y1, z1);
		this.p2 = new Point3D(x2, y2, z2);
		this.p3 = new Point3D(x3, y3, z3);
		this.m = null;
		findNormals();
	}
	
	/**
	 * Constructs a Plane3D object given the coordinates of three defining points
	 * and PhongMaterial.
	 * 
	 * @param	x1		Point1 x coordinate in user coordinates
	 * @param	y1		Point1 y coordinate in user coordinates
	 * @param	z1		Point1 z coordinate in user coordinates
	 * @param	x2		Point2 x coordinate in user coordinates
	 * @param	y2		Point2 y coordinate in user coordinates
	 * @param	z2		Point2 z coordinate in user coordinates
	 * @param	x3		Point3 x coordinate in user coordinates
	 * @param	y3		Point3 y coordinate in user coordinates
	 * @param	z3		Point3 z coordinate in user coordinates
	 * @param	m		PhongMaterial determining plane color
	 */
	public Plane3D(double x1, double y1, double z1,
				   double x2, double y2, double z2,
				   double x3, double y3, double z3,
				   PhongMaterial m) {
		this(x1, y1, z1, x2, y2, z2, x3, y3, z3);
		this.m = m;
	}
	
	/**
	 * Constructs a Plane3D object given the coefficients of a plane equation:
	 * ax + by + cz = d 
	 * 
	 * @param	a	Coefficient a of the plane equation
	 * @param	b	Coefficient b of the plane equation
	 * @param	c	Coefficient c of the plane equation
	 * @param	d	Coefficient d of the plane equation
	 */
    public Plane3D(double a, double b, double c, double d) {
    	this.a = a;
    	this.b = b;
    	this.c = c;
    	this.d = d;
    	this.m = null; 
    	setUnitNormal();
    }
   
	/**
	 * Constructs a Plane3D object given the coefficients of a plane equation and PhongMaterial.
	 * ax + by + cz = d 
	 * 
	 * @param	a	Coefficient a of the plane equation
	 * @param	b	Coefficient b of the plane equation
	 * @param	c	Coefficient c of the plane equation
	 * @param	d	Coefficient d of the plane equation
	 * @param	m		PhongMaterial determining plane color
	 */
    public Plane3D(double a, double b, double c, double d, PhongMaterial m) {
    	this(a, b, c, d);
    	this.m = m; 
    }
    
	/**
	 * Constructs a Plane3D object given specification of the parameters
	 * in Save Data File format
	 * 
	 * @param	lineString	Plane: format data string
	 */
    public Plane3D(String lineString) {
		StringTokenizer st = new StringTokenizer(lineString, ",;: ");
		String dataType = st.nextToken().trim();
		if (dataType.equals("Plane")) {
			this.a = Double.valueOf(st.nextToken()).doubleValue();
			this.b = Double.valueOf(st.nextToken()).doubleValue();
			this.c = Double.valueOf(st.nextToken()).doubleValue();
			this.d = Double.valueOf(st.nextToken()).doubleValue();
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
	    	this.setUnitNormal();
		}
    }
    
    /**
     * Gets point 1 of three defining points for Plane
     * @return	p1	Point3D point defining Plane
     */
    public Point3D getP1() {
    	return p1;
    }
    
    /**
     * Gets point 2 of three defining points for Plane
     * @return	p2	Point3D point defining Plane
     */
    public Point3D getP2() {
    	return p2;
    }
    
    /**
     * Gets point 3 of three defining points for Plane
     * @return	p3	Point3D point defining Plane
     */
    public Point3D getP3() {
    	return p3;
    }
    
    /**
     * Gets the Plane PhongMaterial property
     *
     * @return	m	PhongMaterial value of the Plane
     */
	public PhongMaterial getMaterial() {
		return m;
	}
	
    /**
     * Sets the Plane PhongMaterial property
     *
     * @param	m	PhongMaterial property of the Plane
     */
	public void setMaterial(PhongMaterial m) {
		this.m = m;
	}
	
    /**
     * Gets the Plane DrawMode property
     *
     * @return	dm	DrawMode property of the Plane
     */
	public DrawMode getDrawMode() {
		return dm;
	}
	
    /**
     * Sets the Plane DrawMode property
     *
     * @param	dm	DrawMode property of the Plane
     */
	public void setDrawMode(DrawMode dm) {
		this.dm = dm;
	}

    /**
     * Gets the Plane CullFace property
     *
     * @return	cf	CullFace property of the Plane
     */
	public CullFace getCullFace() {
		return cf;
	}
	
    /**
     * Sets the Plane CullFace property
     *
     * @param	cf	CullFace property of the Plane
     */
	public void setCullFace(CullFace cf) {
		this.cf = cf;
	}
    
    /**
     * Computes the normal vector for the Plane based on the three defining points.
     * Sets the a, b, c, d field values for the plane equation:
     * ax + by + cz = d 
     */
    private void findNormals() {
    	// Derived normal
    	Vector3D v1 = Vector3D.subtract(p1, p2);
    	Vector3D v2 = Vector3D.subtract(p1, p3);
    	normal = Vector3D.crossProduct(v1, v2);
    	// Check for collinear points and if collinear use the origin as a point
    	if (normal.getX() == 0 && normal.getY() == 0 && normal.getZ() == 0 ) {
    		System.out.println("Specified points are collinear. Drawn plane will be origin aligned.");
    		Point3D p0 = new Point3D(0, 0, 0);
        	v1 = Vector3D.subtract(p0, p2);
        	v2 = Vector3D.subtract(p0, p3);
        	normal = Vector3D.crossProduct(v1, v2);
    	}
    	a = normal.getX();
    	b = normal.getY();
    	c = normal.getZ();
    	d = normal.getX()*(p1.getX()) + normal.getY()*(p1.getY()) + normal.getZ()*(p1.getZ());
    	setUnitNormal();
//    	System.out.println(
//    			 "p1= " + p1.toString() +
//    			"\np2= " + p2.toString() +
//    			"\np3= " + p3.toString() +  			
//    			"\nv1= " + v1.toString() + 
//    			"\nv2= " + v2.toString() +
//    			"\nnormal= " + normal.toString() +
//    			"\na= " + a + " b= " + b + " c= " + c + " d= " + d +
//    			"\ndenom= " + Math.sqrt(a*a + b*b + c*c));
    }
    
    /**
     * Computes the unit normal vector of the Plane.
     */
    private void setUnitNormal() {
    	double denom = Math.sqrt(a*a + b*b + c*c);
    	unitNormal = new Vector3D(a/denom, b/denom, c/denom);
    	p = d/denom;
    	if (normal == null) {
    		normal = unitNormal;
    	}
    }
    
    /**
     * Gets the normal vector of the Plane
     * @return	normal	Vector3D normal vector of the Plane
     */     
    public Vector3D getNormal() {
    	return normal;
    }
    
    /**
     * Gets the unit normal vector of the Plane
     * @return	unitNormal	Vector3D unit normal vector of the Plane
     */ 
    public Vector3D getUnitNormal() {
    	return unitNormal;
    }
    
    /**
     * Returns the d coefficient of the Plane equation
     * ax + by + cz = d
     *
     * @return	d	Double coefficent d
     */
    public double getD() {
    	return d;
    }
    
    /**
     * Returns the distance to the origin from the Plane
     *
     * @return	p	Double distance from the Plane to the origin
     */
    public double getP() {
    	return p;
    }
    
	/**
	 * Indicates whether Point3D P is in this.Plane3D
	 * @param	p	Point3D object to be tested for location in this Plane
	 * @return	boolean true if the point is in this Plane
	 * 			boolean false if the point is not in this Plane
	 */
    public boolean pointInPlane(Point3D p) {
    	double value = a*p.getX() + b*p.getY() + c*p.getZ();
    	if (Math.abs(value - d) < EPSILON)
    		return true;
    	else
    		return false;
    }
    
    /**
     * Finds the Plane/Boundary intersection points.
     * Adapted from: Gernot Hoffman, Cube Plane Intersection, http://docs-hoffmann.de/cubeplane12112006.pdf
     * See also:	 Dan Sunday, Intersections of Lines and Planes, http://geomalgorithms.com/a05-_intersect-1.html
     * 
     * The Boundary cube is defined by the parameters:
     * @param minX	double minimum value of the x axis (in user coordinate space)
     * @param maxX	double maximum value of the x axis (in user coordinate space)
     * @param minY	double minimum value of the y axis (in user coordinate space)
     * @param maxY	double maximum value of the y axis (in user coordinate space)
     * @param minZ	double minimum value of the z axis (in user coordinate space)
     * @param maxZ	double maximum value of the z axis (in user coordinate space)
     * @return	finalIntersections	Point3D array of Plane/Boundary intersection points
     */       
    public Point3D[] findPlaneBoxIntersections( double minX, double maxX,
    											double minY, double maxY,
    											double minZ, double maxZ) {
			
    	// Define box vertices 
		double[][] vtx = {  {maxX,  maxY,  maxZ},
				   			{maxX,  minY,  maxZ},
				   			{maxX,  maxY,  minZ},
				   			{minX,  maxY,  maxZ},
				   			{minX,  minY,  maxZ},
				   			{maxX,  minY,  minZ},
				   			{minX,  maxY,  minZ},
				   			{minX,  minY,  minZ} };
		
		// Define intersection search paths from vertex 0 to 7	
		int[][] edges =  { {0, 1},
						   {1, 4},
						   {4, 7},
						   {1, 5},
						   {0, 2},
						   {2, 5},
						   {5, 7},
						   {2, 6},
						   {0, 3},
						   {3, 6},
						   {6, 7},
						   {3, 4} };
		
		// Search for intersections
		Set<Point3D> intersections = new HashSet<Point3D>();
		Point3D[] finalIntersections = null;
//		System.out.println("edges.length= " + edges.length);
		for(int i = 0; i < edges.length; i++) {
//			System.out.println("edge[" + i + "]= " + "(v" + edges[i][0] + " v" + edges[i][1] + ")");
			double[] v1 = vtx[edges[i][0]];
			double[] v2 = vtx[edges[i][1]];
			Point3D vi = checkIntersection(v1, v2);
			if (vi != null) {
					intersections.add(vi);
			}
		}
		if (intersections.size() > 0) {
		// Trim and sort array
			finalIntersections = intersections.toArray(new Point3D[intersections.size()]);
			if (finalIntersections.length > 3) {
				sortIntersections(finalIntersections);
			}
//			for (int i = 0; i < finalIntersections.length; i++) {
//				System.out.println("finalIntersections[" + i + "]= " + finalIntersections[i]);
//			}
		}
		else {
			System.out.println("No intersections with display space found");
			intersections = null;
		}

		
		this.ei = finalIntersections;
		
		return finalIntersections;
	}
    
    /**
     * Gets intersections of the Plane with the Boundary Cube.
     * The Point3D array is populated by DrawPlane3D() or by invoking findPlaneBoxIntersections().
     * 
     * @return ei	Point3D array of Plane/Boundary intersections
     */
    public Point3D[] getIntersections() {
    	return ei;
    }
       
    /**
     * Checks for intersection of the Plane with the boundary edge determined by the input points.
     * Adapted from: Gernot Hoffman, Cube Plane Intersection, http://docs-hoffmann.de/cubeplane12112006.pdf
     * See also:	 Dan Sunday, Intersections of Lines and Planes, http://geomalgorithms.com/a05-_intersect-1.html 
     * 
     * @param	v1	Point3D corner point of the boundary cube
     * @param	v2	Point3D corner point of the boundary cube
     * @return	result	Point3d Plane/Boundary intersection point or null if no intersection
     */
	private Point3D checkIntersection(double[] p1, double[] p2) {		
//		System.out.println("Check Intersection:\n" + new Point3D(p1[0], p1[1], p1[2]) +
//				  							  "\n" + new Point3D(p2[0], p2[1], p2[2]));	
		Vector3D v1 = new Vector3D(p1);
		Vector3D v2 = new Vector3D(p2);
//		System.out.println("v1= " + v1.toString() +
//				"\nv2= " + v2.toString() +
//				"\nsubtract(v2, v1)= " + Vector3D.subtract(v2, v1) + 
//				"\np= " + p + " unitNormal= " + unitNormal.toString() + 
//				"\ndotProduct(unitNormal, v1)= " + Vector3D.dotProduct(unitNormal,  v1) + 
//				"\ndotProduct(unitNormal, subtract(v2,v1)= " + Vector3D.dotProduct(unitNormal, Vector3D.subtract(v2, v1)));
		double lambda = ( p - Vector3D.dotProduct(unitNormal, v1) ) / 
						( Vector3D.dotProduct(unitNormal, Vector3D.subtract(v2, v1)));
//	  	System.out.println("lambda= " + lambda);
		if (lambda >= 0.0 && lambda <= 1.0) {		
			Vector3D v3 = Vector3D.add(v1, Vector3D.multiply(lambda, Vector3D.subtract(v2, v1))); 
//			System.out.println("Intersection found:\n" + v3.getX() + ", " + v3.getY() + ", " + v3.getZ());
			return new Point3D(v3.getX(), v3.getY(), v3.getZ());
		}
		else {
			return null;
		}
	}
	
	/**
	 * Sorts the array of Plane/Boundary intersection points to create a convex polygon.
	 * The sort is done in the "main plane" which is orthogonal to the longest component of the normal vector, i.e.
	 * if nx = max then use the yz plane
	 * if ny = max then use the xz plane
	 * if nz = max then use the xy plane
	 * Adapted from: Gernot Hoffman, Cube Plane Intersection, http://docs-hoffmann.de/cubeplane12112006.pdf
	 * 
	 * @param	vi	Point3D array of Plane/Boundary intersection points
	 * @return	vi	Point3D array of sorted Plane/Boundary intersection points	
	 */
	private Point3D[] sortIntersections(Point3D[] vi) {
		
//		System.out.println("Sort Input");
//		for (int i = 0; i < vi.length; i++) {
//			System.out.println("vi[" + i + "]: " + vi[i]);
//		}
    	
		if ( Math.abs(unitNormal.getX()) >= Math.abs(unitNormal.getY()) &&
				Math.abs(unitNormal.getX()) >= Math.abs(unitNormal.getZ()) ) {
//			System.out.println("X is longest component.");
			double my = meanY(vi);
			double mz = meanZ(vi);
			double ai = 0.0;
			double aj = 0.0;
			for (int i = 0; i < vi.length-1; i++) {
				ai = Math.atan2(vi[i].getY()-my, vi[i].getZ()-mz);
				for (int j = i+1; j < vi.length; j++) {
					aj = Math.atan2(vi[j].getY()-my, vi[j].getZ()-mz);
					if (aj < ai) {
						Point3D t = vi[i];
						vi[i] = vi[j];
						vi[j] = t;
						ai = aj;
					}
				}
			}
		}
		else if ( Math.abs(unitNormal.getY()) >= Math.abs(unitNormal.getX()) &&
			Math.abs(unitNormal.getY()) >= Math.abs(unitNormal.getZ()) ) {
//			System.out.println("Y is longest component.");
			double mx = meanX(vi);
			double mz = meanZ(vi);
			double ai = 0.0;
			double aj = 0.0;
			for (int i = 0; i < vi.length-1; i++) {
				ai = Math.atan2(vi[i].getZ()-mz, vi[i].getX()-mx);
				for (int j = i+1; j < vi.length; j++) {
					aj = Math.atan2(vi[j].getZ()-mz, vi[j].getX()-mx);
					if (aj < ai) {
						Point3D t = vi[i];
						vi[i] = vi[j];
						vi[j] = t;
						ai = aj;
					}
				}	
			}
		}
		else if ( Math.abs(unitNormal.getZ()) >= Math.abs(unitNormal.getX()) &&
				Math.abs(unitNormal.getZ()) >= Math.abs(unitNormal.getY()) ) {
//			System.out.println("Z is longest component.");
			double mx = meanX(vi);
			double my = meanY(vi);
			double ai = 0.0;
			double aj = 0.0;
			for (int i = 0; i < vi.length-1; i++) {
				ai = Math.atan2(vi[i].getY()-my, vi[i].getX()-mx);
				for (int j = i+1; j < vi.length; j++) {
					aj = Math.atan2(vi[j].getY()-my, vi[j].getX()-mx);
					if (aj < ai) {
						Point3D t = vi[i];
						vi[i] = vi[j];
						vi[j] = t;
						ai = aj;
					}	
				}
			}
		}
		else {
			System.out.println("No sort performed");
		}
//		System.out.println("Sort Output");
//    	for (int i = 0; i < vi.length; i++) {
//			System.out.println("vi[" + i + "]: " + vi[i]);
//		}
		
		return vi;
	}
	
	/**
	 * Finds the "main plane", i.e. the plane orthogonal to the longest component of the normal vector 
	 * @return	result 1 if x coordinate is longest
	 *			result 2 if y coordinate is longest
	 *			result 3 if z coordinate is longest
	 */
	public int findLongestComponent() {
		int result = 0;
		if ( Math.abs(unitNormal.getX()) >= Math.abs(unitNormal.getY()) &&
				Math.abs(unitNormal.getX()) >= Math.abs(unitNormal.getZ()) ) {
			result = 1;  // X longest
		}
		else if ( Math.abs(unitNormal.getY()) >= Math.abs(unitNormal.getX()) &&
			Math.abs(unitNormal.getY()) >= Math.abs(unitNormal.getZ()) ) {
			result = 2;  // Y longest
		}
		else if ( Math.abs(unitNormal.getZ()) >= Math.abs(unitNormal.getX()) &&
				Math.abs(unitNormal.getZ()) >= Math.abs(unitNormal.getY()) ) {
			result = 3;  // Z longest
		}
		return result;
	}
		
	/**
	 * Returns the mean x coordinate value from the provided set of Plane/Boundary intersection vertices. 
	 * @param	vi		Point3D array of Plane/Boundary intersection vertices
	 * @return	meanX	Double mean x value from the provided set of Plane/Boundary intersection vertices.
	 */
	public double meanX (Point3D[] vi) {
		double mean = 0.0;
		for (int i = 0; i < vi.length; i++) {
			mean = mean + vi[i].getX();
		}
		return mean = mean/vi.length;
	}
	
	/**
	 * Returns the mean y coordinate value from the provided set of Plane/Boundary intersection vertices. 
	 * @param	vi		Point3D array of Plane/Boundary intersection vertices
	 * @return	meanY	Double mean y value from the provided set of Plane/Boundary intersection vertices.
	 */
	public double meanY (Point3D[] vi) {
		double mean = 0.0;
		for (int i = 0; i < vi.length; i++) {
			mean = mean + vi[i].getY();
		}
		return mean = mean/vi.length;
	}
	
	/**
	 * Returns the mean z coordinate value from the provided set of Plane/Boundary intersection vertices. 
	 * @param	vi		Point3D array of Plane/Boundary intersection vertices
	 * @return	meanZ	Double mean z value from the provided set of Plane/Boundary intersection vertices.
	 */
	public double meanZ(Point3D[] vi) {
		double mean = 0.0;
		for (int i = 0; i < vi.length; i++) {
			mean = mean + vi[i].getZ();
		}
		return mean = mean/vi.length;
	}
	
	/**
	 * Triangulates the Plane.
	 * Vertex indices forming the triangles composing the convex Polygon defining the plane within
	 * the boundary box are stored in the array tri[][3].
	 * 
	 * @return	tri[ei.length-2][3] array of vertex indices with each row forming a sub-triangle
	 * 			of the Plane polygon
	 */
	public int[][] planeTriangulate() {
		tri = new int[ei.length-2][];
		
		for (int i = 0; i < ei.length-2; i++) {
			tri[i] = new int[3];
			tri[i][0] = 0;
			tri[i][1] = i+1;
			tri[i][2] = i+2;
		}
		return tri;
	}
	
    /**
     * Returns a String containing all Plane3D values for saving to a data file.
     * The String is processed by the Plane3D(String) constructor to recreate a Plane3D
     * object when reading a saved data file.
     * 
     * @return	String capturing all Plane3D field values
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
		return "Plane: " + a + ", " + b + ", " + c + ", " + d +
		 		   ", " + dm + ", " + cf + ", " + ms;
	}
	
    /**
     * Returns a String for printing all Plane3D values
     *
     * @return	String for printing all Plane3D field values
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
		return "Plane3D: a= " + a + ", b= " + b + ", c= " + c + ", d= " + d +
		       ", dm= " + dm + ", cf= " + cf + ", " + ms +
		       "\nnormal= " + normal +
				"\nunitnormal= " + unitNormal;
	}
}