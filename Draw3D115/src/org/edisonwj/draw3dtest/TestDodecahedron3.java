package org.edisonwj.draw3dtest;

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
* @version 1.08 January 2017
* 
*/
public class TestDodecahedron3 extends Application {
	private Draw3D dt;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestDodecahedron3");
		primaryStage.show();
	}
	
	private void buildData() {
        PhongMaterial lightgrayMaterial = new PhongMaterial(Color.LIGHTGRAY);
		
		dt.setAmbientLight(false);
		dt.setPointLight(true);
		dt.setYaxisUp(true);
		
		double s = 1.5;
		
		// Draw Dodecahedrons with Rotations

//		Dodecahedron3D o3d5 = new Dodecahedron3D(0, 0, 0, s, lightgrayMaterial);
//		dt.drawPolyhedron3D(o3d5, true);
		
//		dt.incrSequence();
		Dodecahedron3D o3d10 = new Dodecahedron3D(9, 0, 0, s, 0, 0, 0, lightgrayMaterial);
		dt.drawPolyhedron3D(o3d10, true);
		Dodecahedron3D o3d11 = new Dodecahedron3D(6, 0, 0, s, 40, 0, 0, lightgrayMaterial);
		dt.drawPolyhedron3D(o3d11, true);
		Dodecahedron3D o3d12 = new Dodecahedron3D(3, 0, 0, s, 80, 0, 0, lightgrayMaterial);
		dt.drawPolyhedron3D(o3d12, true);
		Dodecahedron3D o3d13 = new Dodecahedron3D(0, 0, 0, s, 120, 0, 0, lightgrayMaterial);
		dt.drawPolyhedron3D(o3d13, true);
		Dodecahedron3D o3d14 = new Dodecahedron3D(-3, 0, 0, s, 180, 0, 0, lightgrayMaterial);
		dt.drawPolyhedron3D(o3d14, true);
		Dodecahedron3D o3d15 = new Dodecahedron3D(-6, 0, 0, s, 260, 0, 0, lightgrayMaterial);
		dt.drawPolyhedron3D(o3d15, true);
		Dodecahedron3D o3d16 = new Dodecahedron3D(-9, 0, 0, s, 340, 0, 0, lightgrayMaterial);
		dt.drawPolyhedron3D(o3d16, true);
		
		dt.incrSequence();
		Dodecahedron3D o3d20 = new Dodecahedron3D(0, 9, 0, s, 0, 0, 0, lightgrayMaterial);
		dt.drawPolyhedron3D(o3d20, true);
		Dodecahedron3D o3d21 = new Dodecahedron3D(0, 6, 0, s, 40, 0, 0, lightgrayMaterial);
		dt.drawPolyhedron3D(o3d21, true);
		Dodecahedron3D o3d22 = new Dodecahedron3D(0, 3, 0, s, 80, 0, 0, lightgrayMaterial);
		dt.drawPolyhedron3D(o3d22, true);
		Dodecahedron3D o3d23 = new Dodecahedron3D(0, 0, 0, s, 120, 0, 0, lightgrayMaterial);
		dt.drawPolyhedron3D(o3d23, true);
		Dodecahedron3D o3d24 = new Dodecahedron3D(0, -3, 0, s, 180, 0, 0, lightgrayMaterial);
		dt.drawPolyhedron3D(o3d24, true);
		Dodecahedron3D o3d25 = new Dodecahedron3D(0, -6, 0, s, 260, 0, 0, lightgrayMaterial);
		dt.drawPolyhedron3D(o3d25, true);
		Dodecahedron3D o3d26 = new Dodecahedron3D(0, -9, 0, s, 340, 0, 0, lightgrayMaterial);
		dt.drawPolyhedron3D(o3d26, true);
		
		dt.incrSequence();
		Dodecahedron3D o3d30 = new Dodecahedron3D(0, 0, 9, s, 0, 0, 0, lightgrayMaterial);
		dt.drawPolyhedron3D(o3d30, true);
		Dodecahedron3D o3d31 = new Dodecahedron3D(0, 0, 6, s, 40, 0, 0, lightgrayMaterial);
		dt.drawPolyhedron3D(o3d31, true);
		Dodecahedron3D o3d32 = new Dodecahedron3D(0, 0, 3, s, 80, 0, 0, lightgrayMaterial);
		dt.drawPolyhedron3D(o3d32, true);
		Dodecahedron3D o3d33 = new Dodecahedron3D(0, 0, 0, s, 120, 0, 0, lightgrayMaterial);
		dt.drawPolyhedron3D(o3d33, true);
		Dodecahedron3D o3d34 = new Dodecahedron3D(0, 0, -3, s, 180, 0, 0, lightgrayMaterial);
		dt.drawPolyhedron3D(o3d34, true);
		Dodecahedron3D o3d35 = new Dodecahedron3D(0, 0, -6, s, 260, 0, 0, lightgrayMaterial);
		dt.drawPolyhedron3D(o3d35, true);
		Dodecahedron3D o3d36 = new Dodecahedron3D(0, 0, -9, s, 340, 0, 0, lightgrayMaterial);
		dt.drawPolyhedron3D(o3d36, true);
		
		dt.incrSequence();
		Dodecahedron3D o3d40 = new Dodecahedron3D(9, 0, 0, s, 0, 0, 0, lightgrayMaterial);
		dt.drawPolyhedron3D(o3d40, true);
		Dodecahedron3D o3d41 = new Dodecahedron3D(3, 0, 0, s, 20, 0, 0, lightgrayMaterial);
		dt.drawPolyhedron3D(o3d41, true);
		Dodecahedron3D o3d42 = new Dodecahedron3D(-3, 0, 0, s, 20, 10, 0, lightgrayMaterial);
		dt.drawPolyhedron3D(o3d42, true);
		Dodecahedron3D o3d43 = new Dodecahedron3D(-9, 0, 0, s, 20, 10, 30, lightgrayMaterial);
		dt.drawPolyhedron3D(o3d43, true);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}