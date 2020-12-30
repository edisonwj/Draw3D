package org.edisonwj.draw3d;

import javafx.collections.ObservableFloatArray;
import javafx.geometry.Point3D;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
/**
* Dodecahedron class extends MeshView and provides for creation of a Dodecahedron mesh object.
* 
* The class implements defaults specified in Draw3DDefaults.
* 
* @author William Edison
* @version 1.06 February 2016
* 
*/
public class Dodecahedron extends MeshView implements Draw3DDefaults{
	private final TriangleMesh dodecahedronMesh;
	private final double size;
	
	/**
	 * Constructs a Dodecahedron MeshView object.
	 * 
	 * @param	size		Double size in pixels (screen coordinates), edge-length is 2*size/phi
	 * 						where phi is (1 + Math.sqrt(5))/2
	 */
	public Dodecahedron(double size) {
		this.size = size;
		dodecahedronMesh = buildDodecahedron(this.size);
		this.setMesh(dodecahedronMesh);
	}
	
    /**
     * Creates a Dodecahedron mesh centered at the origin (0,0,0).
     *
     * @param	s		Double size (in pixels)
     * 
     * @return	mesh	TriangleMesh for the specified Dodecahedron
     */
	private TriangleMesh buildDodecahedron(double s) {
		
		TriangleMesh mesh = new TriangleMesh();
	
		// Define vertices of the Dodecahedron
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

		for (int i = 0; i < vertices.length; i++) {
			mesh.getPoints().addAll((float)vertices[i].getX(),
									(float)vertices[i].getY(),
									(float)vertices[i].getZ());
		}
	
		// Set default texture coordinates
		mesh.getTexCoords().addAll(0,0);
	
		// Build mesh - Front faces
		mesh.getFaces().addAll( 8, 0,  0, 0, 12, 0);
		mesh.getFaces().addAll( 8, 0, 12, 0, 14, 0);
		mesh.getFaces().addAll( 8, 0, 14, 0,  4, 0);
		
		mesh.getFaces().addAll( 8, 0, 10, 0,  2, 0);
		mesh.getFaces().addAll( 8, 0,  2, 0, 16, 0);
		mesh.getFaces().addAll( 8, 0, 16, 0,  0, 0);
		
		mesh.getFaces().addAll( 8, 0,  4, 0, 18, 0);
		mesh.getFaces().addAll( 8, 0, 18, 0,  6, 0);
		mesh.getFaces().addAll( 8, 0,  6, 0, 10, 0);
		
		mesh.getFaces().addAll(12, 0,  0, 0, 16, 0);
		mesh.getFaces().addAll(12, 0, 16, 0, 17, 0);
		mesh.getFaces().addAll(12, 0, 17, 0,  1, 0);
		
		mesh.getFaces().addAll(13, 0,  3, 0, 17, 0);
		mesh.getFaces().addAll(13, 0, 17, 0, 16, 0);
		mesh.getFaces().addAll(13, 0, 16, 0,  2, 0);
		
		mesh.getFaces().addAll(10, 0,  6, 0, 15, 0);
		mesh.getFaces().addAll(10, 0, 15, 0, 13, 0);
		mesh.getFaces().addAll(10, 0, 13, 0,  2, 0);
	
		// Build mesh - Back faces
		mesh.getFaces().addAll( 9, 0,  5, 0, 14, 0);
		mesh.getFaces().addAll( 9, 0, 14, 0, 12, 0);
		mesh.getFaces().addAll( 9, 0, 12, 0,  1, 0);
		
		mesh.getFaces().addAll( 9, 0, 11, 0,  7, 0);
		mesh.getFaces().addAll( 9, 0,  7, 0, 19, 0);
		mesh.getFaces().addAll( 9, 0, 19, 0,  5, 0);
		
		mesh.getFaces().addAll( 9, 0,  1, 0, 17, 0);
		mesh.getFaces().addAll( 9, 0, 17, 0,  3, 0);
		mesh.getFaces().addAll( 9, 0,  3, 0, 11, 0);
		
		mesh.getFaces().addAll(11, 0,  3, 0, 13, 0);
		mesh.getFaces().addAll(11, 0, 13, 0, 15, 0);
		mesh.getFaces().addAll(11, 0, 15, 0,  7, 0);
	
		mesh.getFaces().addAll(14, 0,  5, 0, 19, 0);
		mesh.getFaces().addAll(14, 0, 19, 0, 18, 0);
		mesh.getFaces().addAll(14, 0, 18, 0,  4, 0);
		
		mesh.getFaces().addAll(15, 0,  6, 0, 18, 0);
		mesh.getFaces().addAll(15, 0, 18, 0, 19, 0);
		mesh.getFaces().addAll(15, 0, 19, 0,  7, 0);

		return mesh;
	}
	
	/**
	 * Returns the set of vertices of the Dodecahedron.
	 * 
	 * @return	points	Point3D[] array of Dodecahedron vertices
	 */
	public Point3D[] getPoints() {
		ObservableFloatArray pts = dodecahedronMesh.getPoints();
		int numPoints = dodecahedronMesh.getPointElementSize()+1;
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