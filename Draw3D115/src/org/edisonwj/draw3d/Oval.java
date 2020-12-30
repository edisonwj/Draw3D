package org.edisonwj.draw3d;

import javafx.scene.shape.Mesh;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
/**
* Oval class extends MeshView and provides for creation of an oval mesh object.
*
* The class implements defaults specified in Draw3DDefaults.
*  
* @author William Edison
* @version 1.00 July 2015
* 
*/
public class Oval extends MeshView implements Draw3DDefaults {
	private final Mesh ovalMesh;
	private final double majorRadius;
	private final double minorRadius;
	private final int divisions;
	
    /**
	 * Constructs an Oval MeshView object.
	 * Divisions is set to default.
     *
     * @param	majorRadius		Major radius for the Oval in pixels (screen coordinates)
     * @param	minorRadius		Minor radius for the Oval in pixels (screen coordinates)
     */
	public Oval(double majorRadius, double minorRadius) {
		this(majorRadius, majorRadius, OVAL_DIVISIONS);
	}
	
    /**
	 * Constructs an Oval MeshView object.
	 * Divisions is set to default.
     *
     * @param	majorRadius		Float major radius for the Oval in pixels (screen coordinates)
     * @param	minorRadius		Float minor radius for the Oval in pixels (screen coordinates)
     * @param	divisions		Integer number of divisions for Oval construction		
     */
	public Oval(double majorRadius, double minorRadius, int divisions) {
		this.majorRadius = majorRadius;
		this.minorRadius = minorRadius;
		this.divisions = divisions;
		ovalMesh = buildOval((float)this.majorRadius, (float)this.minorRadius, this.divisions);
		this.setMesh(ovalMesh);
	}
	
    /**
     * Creates an Oval mesh.
     * The Oval is constructed centered on (0,0,0) in the xz plane.
     *
     * @param	rmj		float majorRadius (in pixels)
     * @param	rmn		float minorRadius (in pixels)
     * @param	div		integer number of divisions
     * @return	mesh	TriangleMesh for the specified oval
     */
	private Mesh buildOval(float rmj, float rmn, int div) {	
		TriangleMesh mesh = new TriangleMesh();
	
		// Create points of oval
		double theta = 0.0;
		double increment = 2.0*Math.PI/div;
		float xf = 0.0f;
		float zf = 0.0f;
		for (int i = div; i > 0; i--) {
			xf = (float)(rmj*Math.cos(theta));
			zf = (float)(rmn*Math.sin(theta));
			mesh.getPoints().addAll(xf, 0, zf);
			theta = theta - increment;
		}
	
		// Place the center of the oval at (0, 0, 0). Element[div] in the mesh points array.
		mesh.getPoints().addAll(0, 0, 0);
	
		// Set default texture coordinates
		mesh.getTexCoords().addAll(0,0);
	
		// Build mesh
		for (int i = 0; i < div; i++) {
			mesh.getFaces().addAll(
					div,0,(i+1)%div,0,i,0 );
		}
		return mesh;
	}
}