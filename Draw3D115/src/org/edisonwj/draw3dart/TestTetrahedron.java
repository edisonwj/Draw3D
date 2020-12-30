package org.edisonwj.draw3dart;

import org.edisonwj.draw3d.Draw3D;
import org.edisonwj.draw3d.Line3D;
import org.edisonwj.draw3d.Octahedron3D;
import org.edisonwj.draw3d.Sphere3D;
import org.edisonwj.draw3d.Tetrahedron3D;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.stage.Stage;
/**
*
* @author William Edison
* @version 1.07 March 2016
* 
*/
public class TestTetrahedron extends Application {
	private Draw3D dt;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestTetrahedron");
		primaryStage.show();
	}
	
	private void buildData() {
		dt.setAmbientLight(false);
		dt.setPointLight(true);
		
		// Get Tetrahedron Vertices
		double s = 2;

		Tetrahedron3D t3d0 = new Tetrahedron3D(0, 0, 0, s, new PhongMaterial(Color.BLUE));
		Point3D[] vertices = t3d0.getVertices();
		
		double elDistance = vertices[0].distance(vertices[1]);
		double elComputed = 2*s*Math.sqrt(2);
		System.out.println("s= " + s +  ", edge-length-distance= " + elDistance +
										", edge-length-computed= " + elComputed);
	
		// Draw Front Tetrahedron Faces
		dt.setSequencingOn();
		dt.setLabelFontSize(12);
		for (int i = 0; i < vertices.length; i++) {
			dt.drawSphere3D(new Sphere3D(vertices[i], .05));
			dt.drawLabel(vertices[i].getX(), vertices[i].getY(), vertices[i].getZ(), "  v"+i);
		}
		Tetrahedron3D t3d1 = new Tetrahedron3D(0, 0, 0, s, new PhongMaterial(Color.BLUE));
		t3d1.setDrawMode(DrawMode.LINE);
		t3d1.setCullFace(CullFace.BACK);
		dt.drawPolyhedron3D(t3d1);
		
		// Draw Back Tetrahedron Faces
		dt.incrSequence();
		dt.setLabelFontSize(12);
		for (int i = 0; i < vertices.length; i++) {
			dt.drawSphere3D(new Sphere3D(vertices[i], .05));
			dt.drawLabel(vertices[i].getX(), vertices[i].getY(), vertices[i].getZ(), "  v"+i);
		}
		Tetrahedron3D t3d2 = new Tetrahedron3D(0, 0, 0, s, new PhongMaterial(Color.RED));
		t3d2.setDrawMode(DrawMode.LINE);
		t3d2.setCullFace(CullFace.FRONT);
		dt.drawPolyhedron3D(t3d2);
		
		// Draw All Tetrahedron Faces
		dt.incrSequence();
		dt.setLabelFontSize(12);
		for (int i = 0; i < vertices.length; i++) {
			dt.drawSphere3D(new Sphere3D(vertices[i], .05));
			dt.drawLabel(vertices[i].getX(), vertices[i].getY(), vertices[i].getZ(), "  v"+i);
		}
		Tetrahedron3D t3d3 = new Tetrahedron3D(0, 0, 0, s, new PhongMaterial(Color.BLUE));
		t3d3.setDrawMode(DrawMode.LINE);
		t3d3.setCullFace(CullFace.NONE);
		dt.drawPolyhedron3D(t3d3);
		
		// Draw Tetrahedron Faces with Circumscribing Sphere
		dt.incrSequence();
		Tetrahedron3D t3d4 = new Tetrahedron3D(0, 0, 0, s, new PhongMaterial(Color.BLUE));
		t3d4.setDrawMode(DrawMode.LINE);
		t3d4.setCullFace(CullFace.NONE);
		dt.drawPolyhedron3D(t3d4);
		Sphere3D s3d1 = new Sphere3D(0, 0, 0, Math.sqrt(s*s+s*s+s*s), new PhongMaterial(Color.RED));
		s3d1.setDrawMode(DrawMode.LINE);
		s3d1.setSphereDivisions(16);
		dt.drawSphere3D(s3d1);

		// Draw Tetrahedrons with Rotations
		dt.incrSequence();
		Tetrahedron3D t3dxx1 = new Tetrahedron3D(6, 0, 0, s, new PhongMaterial(Color.LIGHTGRAY));
		dt.drawPolyhedron3D(t3dxx1, true);
		Tetrahedron3D t3dxx2 = new Tetrahedron3D(0, 0, 0, s, 20, 20, 20, new PhongMaterial(Color.LIGHTGRAY));
		dt.drawPolyhedron3D(t3dxx2, true);
		Tetrahedron3D t3dxx3 = new Tetrahedron3D(-6, 0, 0, s, 20, 20, 20, new PhongMaterial(Color.LIGHTGRAY));
		dt.drawPolyhedron3D(t3dxx3, true);
	
		dt.incrSequence();
		Tetrahedron3D t3d6 = new Tetrahedron3D(6, 0, 0, s, 0, 0, 0, new PhongMaterial(Color.LIGHTGRAY));
		dt.drawPolyhedron3D(t3d6, true);
		Tetrahedron3D t3d7 = new Tetrahedron3D(-6, 0, 0, s, 20, 0, 0, new PhongMaterial(Color.LIGHTGRAY));
		dt.drawPolyhedron3D(t3d7, true);
			
		dt.incrSequence();
		Tetrahedron3D t3d8 = new Tetrahedron3D(0, 6, 0, s, 0, 0, 0, new PhongMaterial(Color.LIGHTGRAY));
		dt.drawPolyhedron3D(t3d8, true);
		Tetrahedron3D t3d9 = new Tetrahedron3D(0, -6, 0, s, 0, 20, 0, new PhongMaterial(Color.LIGHTGRAY));
		dt.drawPolyhedron3D(t3d9, true);
		
		dt.incrSequence();
		Tetrahedron3D t3d10 = new Tetrahedron3D(0, 0, 6, s, 0, 0, 0, new PhongMaterial(Color.LIGHTGRAY));
		dt.drawPolyhedron3D(t3d10, true);
		Tetrahedron3D t3d11 = new Tetrahedron3D(0, 0, -6, s, 0, 0, 20, new PhongMaterial(Color.LIGHTGRAY));
		dt.drawPolyhedron3D(t3d11, true);
		
		Tetrahedron3D t3da = new Tetrahedron3D(0, 0, 0, s, 0, 0, 0, new PhongMaterial(Color.TRANSPARENT));//
		dt.drawPolyhedron3D(t3da, true);
		dt.setLabelFontSize(12);
		for (int i = 0; i < vertices.length; i++) {
			dt.drawSphere3D(new Sphere3D(vertices[i], .05));
			dt.drawLabel(vertices[i].getX(), vertices[i].getY(), vertices[i].getZ(), "  v"+i);
		}
		
		dt.incrSequence();
		Tetrahedron3D t3dx1 = new Tetrahedron3D(0, 6, 0, s, 0, 0, 0, new PhongMaterial(Color.LIGHTGRAY));
		dt.drawPolyhedron3D(t3dx1,true);
		Tetrahedron3D t3dx2 = new Tetrahedron3D(0, 0, 0, s, 45, 0, 0, new PhongMaterial(Color.LIGHTGRAY));
		dt.drawPolyhedron3D(t3dx2, true);
		
		dt.incrSequence();
		Tetrahedron3D t3dy1 = new Tetrahedron3D(0, 10, 0, s, 0, 0, 0, new PhongMaterial(Color.LIGHTGRAY));
		dt.drawPolyhedron3D(t3dy1, true);
		Tetrahedron3D t3dy2 = new Tetrahedron3D(0, 0, 0, s, 0, 45, 0, new PhongMaterial(Color.LIGHTGRAY));
		dt.drawPolyhedron3D(t3dy2, true);
		
		dt.incrSequence();
		Tetrahedron3D t3dz1 = new Tetrahedron3D(0, 0, 10, s, 0, 0, 0, new PhongMaterial(Color.LIGHTGRAY));
		dt.drawPolyhedron3D(t3dz1, true);
		Tetrahedron3D t3dz2 = new Tetrahedron3D(0, 0, 0, s, 0, 0, 45, new PhongMaterial(Color.LIGHTGRAY));
		dt.drawPolyhedron3D(t3dz2, true);
		
		dt.incrSequence();
		Tetrahedron3D t3d12 = new Tetrahedron3D(-5, -5, 5, s, 0, -20, 0, new PhongMaterial(Color.LIGHTGRAY));
		dt.drawPolyhedron3D(t3d12, true);
		Tetrahedron3D t3d13 = new Tetrahedron3D(5, 5, -5, s, 20, 0, 0, new PhongMaterial(Color.LIGHTGRAY));
		dt.drawPolyhedron3D(t3d13, true);

	}
	
	public static void main(String[] args) {
		launch(args);
	}
}