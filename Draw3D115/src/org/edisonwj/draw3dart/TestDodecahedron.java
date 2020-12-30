package org.edisonwj.draw3dart;

import org.edisonwj.draw3d.Draw3D;
import org.edisonwj.draw3d.Dodecahedron3D;
import org.edisonwj.draw3d.Sphere3D;

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
public class TestDodecahedron extends Application {
	private Draw3D dt;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestDodecahedron");
		primaryStage.show();
	}
	
	private void buildData() {
		PhongMaterial blueMaterial = new PhongMaterial(Color.BLUE);
		PhongMaterial redMaterial = new PhongMaterial(Color.RED);
        PhongMaterial lightgrayMaterial = new PhongMaterial(Color.LIGHTGRAY);
		
		dt.setAmbientLight(false);
		dt.setPointLight(true);
		
		// Get Dodecahedron Vertices
		dt.setSequencingOn();
		double s = 2;
		double p = (1 + Math.sqrt(5))/2;
		Dodecahedron3D d3d0 = new Dodecahedron3D(0, 0, 0, s, new PhongMaterial(Color.TRANSPARENT));
		Point3D[] vertices = d3d0.getVertices();
		
		double elDistance = vertices[0].distance(vertices[8]);
		double elComputed = 2*s/p;
		System.out.println("s= " + s +  ", edge-length-distance= " + elDistance +
										", edge-length-computed= " + elComputed);
		dt.drawPolyhedron3D(d3d0, true);
		dt.setLabelFontSize(12);
		for (int i = 0; i < vertices.length; i++) {
			dt.drawSphere3D(new Sphere3D(vertices[i], .05));
			dt.drawLabel(vertices[i].getX(), vertices[i].getY(), vertices[i].getZ(), "  v"+i);
		}
				
		dt.incrSequence();
		Dodecahedron3D d3d0f = new Dodecahedron3D(0, 0, 0, s, new PhongMaterial(Color.LIGHTGRAY));
		dt.drawPolyhedron3D(d3d0f, true);

		// Draw Front Dodecahedron Faces
		dt.incrSequence();
		dt.setLabelFontSize(12);
		for (int i = 0; i < vertices.length; i++) {
			dt.drawSphere3D(new Sphere3D(vertices[i], .05));
			dt.drawLabel(vertices[i].getX(), vertices[i].getY(), vertices[i].getZ(), "  v"+i);
		}
		Dodecahedron3D o3d1 = new Dodecahedron3D(0, 0, 0, s, redMaterial);
		o3d1.setDrawMode(DrawMode.LINE);
		o3d1.setCullFace(CullFace.BACK);
		dt.drawPolyhedron3D(o3d1);
		
		// Draw Back Dodecahedron Faces
		dt.incrSequence();
		dt.setLabelFontSize(12);
		for (int i = 0; i < vertices.length; i++) {
			dt.drawSphere3D(new Sphere3D(vertices[i], .05));
			dt.drawLabel(vertices[i].getX(), vertices[i].getY(), vertices[i].getZ(), "  v"+i);
		}
		Dodecahedron3D o3d2 = new Dodecahedron3D(0, 0, 0, s, redMaterial);
		o3d2.setDrawMode(DrawMode.LINE);
		o3d2.setCullFace(CullFace.FRONT);
		dt.drawPolyhedron3D(o3d2);
		
		// Draw All Dodecahedron Faces
		dt.incrSequence();
		dt.setLabelFontSize(12);
		for (int i = 0; i < vertices.length; i++) {
			dt.drawSphere3D(new Sphere3D(vertices[i], .05));
			dt.drawLabel(vertices[i].getX(), vertices[i].getY(), vertices[i].getZ(), "  v"+i);
		}
		Dodecahedron3D o3d3 = new Dodecahedron3D(0, 0, 0, s, redMaterial);
		o3d3.setDrawMode(DrawMode.LINE);
		o3d3.setCullFace(CullFace.NONE);
		dt.drawPolyhedron3D(o3d3);
		
		// Draw Dodecahedron Faces with Circumscribing Sphere
		dt.incrSequence();
		Dodecahedron3D o3d4 = new Dodecahedron3D(0, 0, 0, s, redMaterial);
		o3d4.setDrawMode(DrawMode.LINE);
		o3d4.setCullFace(CullFace.NONE);
		dt.drawPolyhedron3D(o3d4, true);
		Sphere3D s3d1 = new Sphere3D(0, 0, 0, Math.sqrt(s*s+s*s+s*s), blueMaterial);
		s3d1.setDrawMode(DrawMode.LINE);
		s3d1.setSphereDivisions(16);
		dt.drawSphere3D(s3d1);

		// Draw Dodecahedrons with Rotations
		dt.incrSequence();
		Dodecahedron3D o3d5 = new Dodecahedron3D(0, 0, 0, s, lightgrayMaterial);
		dt.drawPolyhedron3D(o3d5, true);
		
		dt.incrSequence();
		Dodecahedron3D o3d6 = new Dodecahedron3D(4, 0, 0, s, 0, 0, 0, lightgrayMaterial);
		dt.drawPolyhedron3D(o3d6, true);
		Dodecahedron3D o3d7 = new Dodecahedron3D(-4, 0, 0, s, 20, 0, 0, lightgrayMaterial);
		dt.drawPolyhedron3D(o3d7, true);
		
		dt.incrSequence();
		Dodecahedron3D o3d8 = new Dodecahedron3D(0, 4, 0, s, 0, 0, 0, lightgrayMaterial);
		dt.drawPolyhedron3D(o3d8, true);
		Dodecahedron3D o3d9 = new Dodecahedron3D(0, -4, 0, s, 20, 0, 0, lightgrayMaterial);
		dt.drawPolyhedron3D(o3d9, true);
		
		dt.incrSequence();
		Dodecahedron3D o3d10 = new Dodecahedron3D(0, 0, 4, s, 0, 0, 0, lightgrayMaterial);
		dt.drawPolyhedron3D(o3d10, true);
		Dodecahedron3D o3d11 = new Dodecahedron3D(0, 0, -4, s, 20, 0, 0, lightgrayMaterial);
		dt.drawPolyhedron3D(o3d11, true);
		System.out.println(o3d11);
		
		dt.incrSequence();
		Dodecahedron3D o3d12 = new Dodecahedron3D(-5, -5, 5, s, 0, -20, 0, lightgrayMaterial);
		dt.drawPolyhedron3D(o3d12, true);
		Dodecahedron3D o3d13 = new Dodecahedron3D(5, 5, -5, s, 20, 0, 0, lightgrayMaterial);
		dt.drawPolyhedron3D(o3d13, true);
		System.out.println(o3d13);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}