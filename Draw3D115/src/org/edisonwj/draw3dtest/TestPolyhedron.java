package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Draw3D;
import org.edisonwj.draw3d.Dodecahedron3D;
import org.edisonwj.draw3d.Icosahedron3D;
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
* @version 1.06 February 2016
* 
*/
public class TestPolyhedron extends Application {
	private Draw3D dt;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestPolyhedron");
		primaryStage.show();
	}
	
	private void buildData() {
		PhongMaterial blueMaterial = new PhongMaterial(Color.BLUE);
		PhongMaterial redMaterial = new PhongMaterial(Color.RED);
        PhongMaterial lightgrayMaterial = new PhongMaterial(Color.LIGHTGRAY);
        PhongMaterial antiquewhiteMaterial = new PhongMaterial(Color.ANTIQUEWHITE);
		
		dt.setAmbientLight(false);
		dt.setPointLight(true);
		dt.setShowAxes(false);
		
		// Get Dodecahedron Vertices
		dt.setSequencingOn();
		double s = 3;
		double p = (1 + Math.sqrt(5))/2;
		Dodecahedron3D d3d0 = new Dodecahedron3D(0, 0, 0, s, new PhongMaterial(Color.TRANSPARENT));
		Point3D[] vertices = d3d0.getVertices();
		dt.drawPolyhedron3D(d3d0, true);
		dt.setLabelFontSize(12);
		for (int i = 0; i < vertices.length; i++) {
			dt.drawSphere3D(new Sphere3D(vertices[i], .05));
			dt.drawLabel(vertices[i].getX(), vertices[i].getY(), vertices[i].getZ(), "  v"+i);
		}
		
		Tetrahedron3D t3d = new Tetrahedron3D(-8,-10,8,s,lightgrayMaterial);
		dt.drawPolyhedron3D(t3d,true);
		
		Octahedron3D o3d = new Octahedron3D(6,-4,-6,1.25*s,lightgrayMaterial);
		dt.drawPolyhedron3D(o3d,true);
		
		Icosahedron3D i3d = new Icosahedron3D(-2,8,8,.5*s,lightgrayMaterial);
		dt.drawPolyhedron3D(i3d,true);
		
		Dodecahedron3D d3d = new Dodecahedron3D(2,8,-8,.5*s,lightgrayMaterial);
		dt.drawPolyhedron3D(d3d,true);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}