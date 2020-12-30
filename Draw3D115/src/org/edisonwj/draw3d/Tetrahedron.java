package org.edisonwj.draw3d;

import javafx.collections.ObservableFloatArray;
import javafx.geometry.Point3D;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
/**
* Tetrahedron class extends MeshView and provides for creation of a Tetrahedron mesh object.
* 
* The class implements defaults specified in Draw3DDefaults.
* 
* @author William Edison
* @version 1.06 February 2016
* 
*/
public class Tetrahedron extends MeshView implements Draw3DDefaults{
	private final TriangleMesh tetrahedronMesh;
	private final double size;
	
	/**
	 * Constructs a Tetrahedron MeshView object.
	 * 
	 * @param	size	Double size in pixels (screen coordinates), edge-length is 2*size*Sqrt(2)
	 */
	public Tetrahedron(double size) {
		this.size = size;	
		tetrahedronMesh = buildTetrahedron(this.size);
		this.setMesh(tetrahedronMesh);
	}
	
    /**
     * Creates a Tetrahedron mesh centered at the origin (0,0,0).
     * 
     * @return	mesh	TriangleMesh for the specified Tetrahedron
     */
	private TriangleMesh buildTetrahedron(double s) {
		
		TriangleMesh mesh = new TriangleMesh();

		// Define vertices of the Tetrahedron		
		Point3D[] vertices = {  new Point3D( s,  s,  s),	//  0
								new Point3D( s, -s, -s),	//  1
								new Point3D(-s,  s, -s),	//  2
								new Point3D(-s, -s,  s)		//  3
							 };
			
		// Define mesh points
		for (int i = 0; i < vertices.length; i++) {
			mesh.getPoints().addAll((float)vertices[i].getX(),
									(float)vertices[i].getY(),
									(float)vertices[i].getZ());
		}
	
		// Set default texture coordinates
		mesh.getTexCoords().addAll(0,0);
	
		// Build mesh - Front faces
		mesh.getFaces().addAll( 0, 0,  3, 0,  1, 0);
		mesh.getFaces().addAll( 0, 0,  2, 0,  3, 0);
			
		// Build mesh - Back face
		mesh.getFaces().addAll( 0, 0, 1, 0,  2, 0);
		mesh.getFaces().addAll( 1, 0, 3, 0,  2, 0);
		
		return mesh;
	}
	
	/**
	 * Returns the set of vertices of the Tetrahedron.
	 * 
	 * @return	points	Point3D[] array of Tetrahedron vertices
	 */
	public Point3D[] getPoints() {
		ObservableFloatArray pts = tetrahedronMesh.getPoints();
		int numPoints = tetrahedronMesh.getPointElementSize()+1;
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