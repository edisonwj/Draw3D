package org.edisonwj.draw3d;

import javafx.collections.ObservableFloatArray;
import javafx.geometry.Point3D;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
/**
* Icosahedron class extends MeshView and provides for creation of a Icosahedron mesh object.
* 
* The class implements defaults specified in Draw3DDefaults.
* 
* @author William Edison
* @version 1.06 February 2016
* 
*/
public class Icosahedron extends MeshView implements Draw3DDefaults{
	private final TriangleMesh icosahedronMesh;
	private final double size;
	
	/**
	 * Constructs an Icosahedron MeshView object.
	 * 
	 * @param	size		Double size in pixels (screen coordinates), edge-length is 2*size
	 */
	public Icosahedron(double size) {
		this.size = size;
		icosahedronMesh = buildIcosahedron(this.size);
		this.setMesh(icosahedronMesh);
	}
	
    /**
     * Creates an Icosahedron mesh centered at the origin (0,0,0).
     *
     * @param	s		Double size (in pixels)
     * 
     * @return	mesh	TriangleMesh for the specified Icosahedron
     */
	private TriangleMesh buildIcosahedron(double s) {
		
		TriangleMesh mesh = new TriangleMesh();
	
		// Define vertices of the Icosahedron
		double phi = (1 + Math.sqrt(5))/2;
		double p = s*phi;
		Point3D[] vertices = {  new Point3D( 0,  p,  s),	//  0
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

		for (int i = 0; i < vertices.length; i++) {
			mesh.getPoints().addAll((float)vertices[i].getX(),
									(float)vertices[i].getY(),
									(float)vertices[i].getZ());
		}
	
		// Set default texture coordinates
		mesh.getTexCoords().addAll(0,0);
	
		// Build mesh - Front faces
		mesh.getFaces().addAll( 0, 0,  6, 0,  1, 0);
		mesh.getFaces().addAll( 0, 0,  1, 0,  4, 0);
		mesh.getFaces().addAll( 8, 0,  0, 0,  4, 0);
		mesh.getFaces().addAll( 8, 0,  9, 0,  0, 0);
		mesh.getFaces().addAll( 9, 0,  6, 0,  0, 0);
		mesh.getFaces().addAll( 8, 0,  4, 0,  5, 0);
		mesh.getFaces().addAll( 8, 0,  5, 0,  2, 0);
		mesh.getFaces().addAll( 8, 0,  2, 0,  9, 0);
		mesh.getFaces().addAll( 9, 0,  2, 0,  7, 0);
		mesh.getFaces().addAll( 9, 0,  7, 0,  6, 0);
				
		// Build mesh - Back faces
		mesh.getFaces().addAll( 1, 0, 10, 0,  4, 0);
		mesh.getFaces().addAll( 1, 0, 11, 0, 10, 0);
		mesh.getFaces().addAll( 1, 0,  6, 0, 11, 0);
		mesh.getFaces().addAll(10, 0,  5, 0,  4, 0);
		mesh.getFaces().addAll(10, 0,  3, 0,  5, 0);
		mesh.getFaces().addAll(10, 0, 11, 0,  3, 0);
		mesh.getFaces().addAll(11, 0,  7, 0,  3, 0);
		mesh.getFaces().addAll(11, 0,  6, 0,  7, 0);
		mesh.getFaces().addAll( 5, 0,  3, 0,  2, 0);
		mesh.getFaces().addAll( 7, 0,  2, 0,  3, 0);

		return mesh;
	}
	
	/**
	 * Returns the set of vertices of the Icosahedron.
	 * 
	 * @return	points	Point3D[] array of Icosahedron vertices
	 */	
	public Point3D[] getPoints() {
		ObservableFloatArray pts = icosahedronMesh.getPoints();
		int numPoints = icosahedronMesh.getPointElementSize()+1;
		// Seems to be a JavaFX 8 bug - returns one less than the number of points
		Point3D[] points = new Point3D[numPoints];
		float x=0, y=0, z=0;
		for (int i = 0; i < 3*numPoints; i=i+3) {
			x = pts.get(i);
			y = pts.get(i+1);
			z = pts.get(i+2); 
			points[i/3] = new Point3D(x, y, z);
		}
		return points;
	}
}