package org.edisonwj.draw3d;

import java.util.StringTokenizer;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
/**
* Line3D class captures information defining a Line3D instantiation with specified:
* - xyz location of the starting point (user coordinate scale)
* - xyz location of the ending point (user coordinate scale)
* - equation flag indicating a line defined by symmetric line equation coefficients
* - radius in pixels of the line
* - PhongMaterial (optional)
*
* Lines are created using JavaFX Cylinders.
*
* The class implements defaults specified in Draw3DDefaults.
*
* @author William Edison
* @version 1.08 January 2017
*
*/
public class Line3D implements Draw3DDefaults {
	private Point3D p1;			// start point
	private Point3D p2;			// end point
	boolean equation = false;	// indicates a line defined by symmetric line equation coefficients
	boolean edgeLine = false;	// indicates highlighting edge of a box or polyhedron
	private double lineRadius = LINE_RADIUS;
	private PhongMaterial m;

	/**
	 * Constructs a Line3D object given start point and end point.
	 *
	 * @param	p1	Point3D start point of the line
	 * @param	p2	Point3D end point of the line
	 */
	public Line3D(Point3D p1, Point3D p2) {
		this.p1 = p1;
		this.p2 = p2;
		this.m = null;
	}

	/**
	 * Constructs a Line3D object given start point, end point, and PhongMaterial.
	 *
	 * @param	p1	Point3D start point of the line
	 * @param	p2	Point3D end point of the line
	 * @param	m	PhongMaterial determining line color
	 */
	public Line3D(Point3D p1, Point3D p2, PhongMaterial m) {
		this(p1, p2);
		this.m = m;
	}

	/**
	 * Constructs a Line3D object given start point and end point.
	 *
	 * @param	x1	x coordinate of the start point
	 * @param	y1	y coordinate of the start point
	 * @param	z1	z coordinate of the start point
	 * @param	x2	x coordinate of the end point
	 * @param	y2	y coordinate of the end point
	 * @param	z2	z coordinate of the end point
	 */
	public Line3D(double x1, double y1, double z1,
			  	  double x2, double y2, double z2) {
		this.p1 = new Point3D(x1, y1, z1);
		this.p2 = new Point3D(x2, y2, z2);
		this.m = null;
	}

	/**
	 * Constructs a Line3D object given start point, end point, and PhongMaterial.
	 *
	 * @param	x1	x coordinate of the start point
	 * @param	y1	y coordinate of the start point
	 * @param	z1	z coordinate of the start point
	 * @param	x2	x coordinate of the end point
	 * @param	y2	y coordinate of the end point
	 * @param	z2	z coordinate of the end point
	 * @param	m	PhongMaterial determining line color
	 */
	public Line3D(double x1, double y1, double z1,
			  double x2, double y2, double z2,
			  PhongMaterial m) {
		this(x1, y1, z1, x2, y2, z2);
		this.m = m;
	}

	/**
	 * Constructs a Line3D object given the coefficients of the symmetric line equation.
	 * Symmetric line equation: (x-a1)/b1 = (y-a2)/b2 = (z-a3)/b3, b1!=0, b2!=0, b3!=0
	 * The coefficients are used to set token "start" and "end" points for the line.
	 * Drawing of the line based on the line equation extends the line to the boundary cube.
	 *
	 * @param	a1	coefficient of line equation
	 * @param	a2	coefficient of line equation
	 * @param	a3	coefficient of line equation
	 * @param	b1	coefficient of line equation
	 * @param	b2	coefficient of line equation
	 * @param	b3	coefficient of line equation
	 * @param	equation	boolean indicator of line equation construction
	 */
	public Line3D(double a1, double a2, double a3, double b1, double b2, double b3, boolean equation) {
		this.p1 = new Point3D(0, ((-a1*b2)/b1)+a2, ((-a1*b3)/b1)+a3);
		this.p2 = new Point3D(1, (((1-a1)*b2)/b1)+a2,(((1-a1)*b3)/b1)+a3);
		this.equation = equation;
		this.m = null;
	}

	/**
	 * Constructs a Line3D object given the coefficients of the symmetric line equation and its PhongMaterial.
	 * Symmetric line equation: (x-a1)/b1 = (y-a2)/b2 = (z-a3)/b3, b1!=0, b2!=0, b3!=0
	 * The coefficients are used to set token "start" and "end" points for the line.
	 * Drawing of the line based on the line equation extends the line to the boundary cube.
	 *
	 * @param	a1	coefficient of line equation
	 * @param	a2	coefficient of line equation
	 * @param	a3	coefficient of line equation
	 * @param	b1	coefficient of line equation
	 * @param	b2	coefficient of line equation
	 * @param	b3	coefficient of line equation
	 * @param	equation	boolean indicator of line equation construction
	 * @param	m	PhongMaterial determining line color
	 */
	public Line3D(double a1, double a2, double a3, double b1, double b2, double b3, boolean equation, PhongMaterial m) {
		this(a1, a2, a3, b1, b2, b3, equation);
		this.m = m;
	}

	/**
	 * Constructs a Line3D object given specification of the parameters
	 * in Save Data File format.
	 *
	 * @param	lineString	Line: format data string
	 */
	public Line3D(String lineString) {
		StringTokenizer st = new StringTokenizer(lineString, ",;: ");
		String dataType = st.nextToken().trim();
		if (dataType.equals("Line")) {
			double x1 = Double.valueOf(st.nextToken()).doubleValue();
			double y1 = Double.valueOf(st.nextToken()).doubleValue();
			double z1 = Double.valueOf(st.nextToken()).doubleValue();
			this.p1 = new Point3D(x1, y1, z1);
			double x2 = Double.valueOf(st.nextToken()).doubleValue();
			double y2 = Double.valueOf(st.nextToken()).doubleValue();
			double z2 = Double.valueOf(st.nextToken()).doubleValue();
			this.p2 = new Point3D(x2, y2, z2);
			if (st.hasMoreTokens()) {
				this.lineRadius = Double.valueOf(st.nextToken()).doubleValue();
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
     * Gets the Line3D starting point
     *
     * @return	p	Point3D location of Line starting point
     */
	public Point3D getp1() {
		return p1;
	}

    /**
     * Gets the Line3D ending point
     *
     * @return	p	Point3D location of Line end
     */
	public Point3D getp2() {
		return p2;
	}

    /**
     * Gets the Line3D PhongMaterial property
     *
     * @return	m	PhongMaterial value of the Line
     */
	public PhongMaterial getMaterial() {
		return m;
	}

    /**
     * Sets the Linew3D PhongMaterial property
     *
     * @param	m	PhongMaterial property of the Line
     */
	public void setMaterial(PhongMaterial m) {
		this.m = m;
	}

    /**
     * Gets the Line3D line radius (in pixels)
     *
     * @return	lineRadius	Double value of the Line radius
     */
	public double getLineRadius() {
		return lineRadius;
	}

    /**
     * Sets the Line3D line radius (in pixels)
     *
     * @param	lineRadius	Double value of the Line radius
     */
	public void setLineRadius(double lineRadius) {
		this.lineRadius = lineRadius;
	}

    /**
     * Provides the Line3D line length (in user coordinate space)
     *
     * @return	length	Double value of the Line length
     */
	public double length() {
		return Math.sqrt((p1.getX()-p2.getX())*(p1.getX()-p2.getX())
						+ (p1.getY()-p2.getY())*(p1.getY()-p2.getY())
						+ (p1.getZ()-p2.getZ())*(p1.getZ()-p2.getZ()));
	}

    /**
     * Provides the Line3D line mid-point (in user coordinate space)
     *
     * @return	midpoint	Point3D location of the Line midpoint
     */
	public Point3D midpoint() {
		double x, y, z;
		x = (p1.getX()+p2.getX()) / 2.0;
		y = (p1.getY()+p2.getY()) / 2.0;
		z = (p1.getZ()+p2.getZ()) / 2.0;

		return new Point3D(x, y, z);
	}

    /**
     * Provides the projected Line3D angle between xz axes
     *
     * @return	theta	Double value of angle of projected xz line
     */
	public double theta() {
		double x, y, z;
		// Relocate line start point to origin
		x = p2.getX() - p1.getX();
		y = p2.getY() - p1.getY();
		z = p2.getZ() - p1.getZ();

		// Compute theta
		return Math.atan2(y, x);
	}

    /**
     * Provides the Line3D angle with y axis
     *
     * @return	phi	Double value of angle of Line to y axis
     */
	public double phi() {
		double x, y, z;
		// Relocate line start point to origin
		x = p2.getX() - p1.getX();
		y = p2.getY() - p1.getY();
		z = p2.getZ() - p1.getZ();

		// Compute phi
		double rho = Math.sqrt(x*x + y*y + z*z);
		return Math.acos(z/rho);
	}

    /**
     * Returns a two point array specifying the intersection of the line determined
     * by points p1 and p2 with the boundary of the current user coordinate space as
     * determined by minX, maxX, minY, maxY, minZ, maxZ.
     * See: Dan Sunday, Intersections of Lines and Planes, http://geomalgorithms.com/a05-_intersect-1.html
     *
     * @param	minX	Double minimum x user coordinate value
     * @param	maxX	Double maximum x user coordinate value
     * @param	minY	Double minimum y user coordinate value
     * @param	maxY	Double maximum y user coordinate value
     * @param	minZ	Double minimum z user coordinate value
     * @param	maxZ	Double maximum z user coordinate value
     * @return	intersectionPoints	Point3D[2] array specifying the intersection points
     * 					of this.Line3D with the boundary user space
     */
	public Point3D[] findLineBoundaryIntersections(double minX, double maxX,
												   double minY, double maxY,
												   double minZ, double maxZ) {

		// Define boundary vertices, edges and planes
		Point3D[] vtx = {
				new Point3D(minX,  minY,  minZ),
				new Point3D(minX,  minY,  maxZ),
				new Point3D(minX,  maxY,  maxZ),
				new Point3D(minX,  maxY,  minZ),
				new Point3D(maxX,  minY,  minZ),
				new Point3D(maxX,  minY,  maxZ),
				new Point3D(maxX,  maxY,  maxZ),
				new Point3D(maxX,  maxY,  minZ) };

		Plane3D[] plane = {
				new Plane3D(vtx[0], vtx[1], vtx[2]),
				new Plane3D(vtx[3], vtx[2], vtx[6]),
				new Plane3D(vtx[7], vtx[6], vtx[5]),
				new Plane3D(vtx[4], vtx[5], vtx[1]),
				new Plane3D(vtx[0], vtx[3], vtx[7]),
				new Plane3D(vtx[1], vtx[2], vtx[6]) };

		Point3D[] intersectionPoints = new Point3D[2];
		int intersectionCount = 0;
		for (int i = 0; i < plane.length; i++) {
			Point3D v0 = plane[i].getP1();
			Vector3D n = plane[i].getNormal();
			Vector3D V0 = new Vector3D(v0);
			Vector3D P1 = new Vector3D(p1);
			Vector3D P2 = new Vector3D(p2);

			Vector3D u = Vector3D.subtract(P2,  P1);  // P2 - P1
			Vector3D w = Vector3D.subtract(P1,  V0);  // P1 - V0

			double D =  Vector3D.dotProduct(n, u);
			double N = -Vector3D.dotProduct(n, w);

			System.out.println("D= " + D + ", N= " + N);

			if (Math.abs(D) < EPSILON) {
				if (N == 0)
					System.out.println("plane i= " + i + " Line segment is in boundary plane: " + plane[i].toString());
				else
					System.out.println("plane i= " + i + " No line segment plane intersection");
			}
			else {

				double s = N / D;
				Vector3D su = Vector3D.multiply(s, u);

				Point3D pi = new Point3D(p1.getX() + su.getX(), p1.getY() + su.getY(), p1.getZ() + su.getZ());

				System.out.println("plane i= " + i + " intersection= " + pi);

				if (intersectionCount > 0 && pi.equals(intersectionPoints[0]))
					continue;

				if (pi.getX() <= maxX && pi.getX() >= minX &&
					pi.getY() <= maxY && pi.getY() >= minY &&
					pi.getZ() <= maxZ && pi.getZ() >= minZ  ) {
					intersectionPoints[intersectionCount++] = pi;
					if (intersectionCount == 2)
						return intersectionPoints;
				}
				else {
					System.out.println("Intersection point out of range");
				}
			}

		}
		if (intersectionCount == 1 && isCorner(vtx, intersectionPoints[0])) {
			intersectionPoints[1] = intersectionPoints[0];
		}
		else {
			intersectionPoints = null;
		}

		return intersectionPoints;
	}

	/**
	 * Checks to see whether an intersection point is a corner of the boundary
	 *
	 * @param vtx			Point3D array of boundary corner vertices
	 * @param intersection	Point3D intersection point of line with boundary
	 * @return
	 */
	private boolean isCorner(Point3D[] vtx, Point3D intersection) {
		for (int i = 0; i < vtx.length; i++) {
			if (intersection.equals(vtx[i])) {
				return true;
			}
		}
		return false;
	}

    /**
     * Returns a String containing all Line3D values for saving to a data file.
     * The String is processed by the Line3D(String) constructor to recreate
     * a Line3D object when reading a saved data file.
     *
     * @return	String capturing all Line3D field values
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
		return "Line: " + p1.getX() + ", " + p1.getY() + ", " + p1.getZ() +
			   ", " + p2.getX() + ", " + p2.getY() + ", " + p2.getZ() +
			   ", " + lineRadius + ", " + ms;
	}

    /**
     * Returns a String for printing all Line3D values
     *
     * @return	String for printing all Line3D field values
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
		return "Line3D: p1= " + p1.toString() + ", p2= " + p2.toString()
				+ " lineRadius= " + lineRadius + ms;
	}
}