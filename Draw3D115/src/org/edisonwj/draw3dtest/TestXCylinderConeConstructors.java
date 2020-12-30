package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Cone3D;
import org.edisonwj.draw3d.Cylinder3D;
import org.edisonwj.draw3d.Draw3D;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.stage.Stage;
/**
*
* @author William Edison
* @version 1.04 February 2016
* 
*/
public class TestXCylinderConeConstructors extends Application {
	private Draw3D dt;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestXCylinerConeConstructors");
		primaryStage.show();
	}
	
	private void buildData() {
		dt.setSequencingOn();
		dt.setAmbientLight(true);
		
		PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setDiffuseColor(Color.DARKRED);
        redMaterial.setSpecularColor(Color.RED);
//      System.out.println("redMaterial= " + redMaterial);

		PhongMaterial greenMaterial = new PhongMaterial();
        greenMaterial.setDiffuseColor(Color.DARKGREEN);
        greenMaterial.setSpecularColor(Color.GREEN);
//      System.out.println("greenMaterial= " + greenMaterial);

		PhongMaterial blueMaterial = new PhongMaterial();
        blueMaterial.setDiffuseColor(Color.DARKBLUE);
        blueMaterial.setSpecularColor(Color.BLUE);
//      System.out.println("blueMaterial= " + blueMaterial);
        
		Cylinder3D b1 = new Cylinder3D(new Point3D(5, -8.0, 7.5), .75, 3);
		dt.drawCylinder3D(b1);
		System.out.println(b1);
		Cylinder3D b2 = new Cylinder3D(new Point3D(5, -6.0, 7.5), .75, 3, redMaterial);
		dt.drawCylinder3D(b2);
		System.out.println(b2);
		
		Cylinder3D b3 = new Cylinder3D(new Point3D(5, -4.0, 7.5), .75, 3, 0, 0, 0);
		dt.drawCylinder3D(b3);
		System.out.println(b3);
		Cylinder3D b4 = new Cylinder3D(new Point3D(5, -2.0, 7.5), .75, 3, 0, 0, 0, redMaterial);
		dt.drawCylinder3D(b4);
		System.out.println(b4);

		Cylinder3D b5 = new Cylinder3D(5, 2.0, 7.5, .75, 3);
		dt.drawCylinder3D(b5);
		System.out.println(b5);
		Cylinder3D b6 = new Cylinder3D(5, 4.0, 7.5, .75, 3, redMaterial);
		dt.drawCylinder3D(b6);
		System.out.println(b6);
		
		Cylinder3D b7 = new Cylinder3D(5, 6.0, 7.5, .75, 3, 0, 0, 0);
		dt.drawCylinder3D(b7);
		System.out.println(b7);
		Cylinder3D b8 = new Cylinder3D(5, 8.0, 7.5, .75, 3, 0, 0, 0, redMaterial);
		dt.drawCylinder3D(b8);
		System.out.println(b8);
		
		Cylinder3D b9 = new Cylinder3D(new Point3D(5, -8.0, 2.5), .75, 3);
		dt.drawCylinder3D(b9);
		System.out.println(b9);
		Cylinder3D b10 = new Cylinder3D(new Point3D(5, -6.0, 2.5), .75, 3, redMaterial);
		dt.drawCylinder3D(b10);
		System.out.println(b10);
		
		Cylinder3D b11 = new Cylinder3D(new Point3D(5, -4.0, 2.5), .75, 3, 30, -30, 0);
		dt.drawCylinder3D(b11);
		System.out.println(b11);
		Cylinder3D b12 = new Cylinder3D(new Point3D(5, -2.0, 2.5), .75, 3, 30, -30, 0, redMaterial);
		dt.drawCylinder3D(b12);
		System.out.println(b12);

		Cylinder3D b13 = new Cylinder3D(5, 2.0, 2.5, .75, 3);
		dt.drawCylinder3D(b13);
		System.out.println(b13);
		Cylinder3D b14 = new Cylinder3D(5, 4.0, 2.5, .75, 3, redMaterial);
		dt.drawCylinder3D(b14);
		System.out.println(b14);
		
		Cylinder3D b15 = new Cylinder3D(5, 6.0, 2.5, .75, 3, 30, -30 ,0);
		dt.drawCylinder3D(b15);
		System.out.println(b15);
		Cylinder3D b16 = new Cylinder3D(5, 8.0, 2.5, .75, 3, 30, -30, 0, redMaterial);
		dt.drawCylinder3D(b16);
		System.out.println(b16);
        
		Cone3D r1 = new Cone3D(new Point3D(5, -8.0, -3), .75, 3);
		dt.drawCone3D(r1);
		System.out.println(r1);
		Cone3D r2 = new Cone3D(new Point3D(5, -6.0, -3), .75, 3, greenMaterial);
		dt.drawCone3D(r2);
		System.out.println(r2);
		
		Cone3D r3 = new Cone3D(new Point3D(5, -4.0, -3), .75, 3, 0, 0, 0);
		dt.drawCone3D(r3);
		System.out.println(r3);
		Cone3D r4 = new Cone3D(new Point3D(5, -2.0, -3), .75, 3, 0, 0, 0, greenMaterial);
		dt.drawCone3D(r4);
		System.out.println(r4);

		Cone3D r5 = new Cone3D(5, 2.0, -3, .75, 3);
		dt.drawCone3D(r5);
		System.out.println(r5);
		Cone3D r6 = new Cone3D(5, 4.0, -3, .75, 3, greenMaterial);
		dt.drawCone3D(r6);
		System.out.println(r6);
		
		Cone3D r7 = new Cone3D(5, 6.0, -3, .75, 3, 0, 0, 0);
		dt.drawCone3D(r7);
		System.out.println(r7);
		Cone3D r8 = new Cone3D(5, 8.0, -3, .75, 3, 0, 0, 0, greenMaterial);
		dt.drawCone3D(r8);
		System.out.println(r8);
		
		Cone3D r9 = new Cone3D(new Point3D(5, -8.0, -8), .75, 3);
		dt.drawCone3D(r9);
		System.out.println(r9);
		Cone3D r10 = new Cone3D(new Point3D(5, -6.0, -8), .75, 3, greenMaterial);
		dt.drawCone3D(r10);
		System.out.println(r10);
		
		Cone3D r11 = new Cone3D(new Point3D(5, -4.0, -8), .75, 3, 30, -30, 0);
		dt.drawCone3D(r11);
		System.out.println(r11);
		Cone3D r12 = new Cone3D(new Point3D(5, -2.0, -8), .75, 3, 30, -30, 0, greenMaterial);
		dt.drawCone3D(r12);
		System.out.println(r12);

		Cone3D r13 = new Cone3D(5, 2.0, -8, .75, 3);
		dt.drawCone3D(r13);
		System.out.println(r13);
		Cone3D r14 = new Cone3D(5, 4.0, -8, .75, 3, greenMaterial);
		dt.drawCone3D(r14);
		System.out.println(r14);
		
		Cone3D r15 = new Cone3D(5, 6.0, -8, .75, 3, 30, -30 ,0);
		dt.drawCone3D(r15);
		System.out.println(r15);
		Cone3D r16 = new Cone3D(5, 8.0, -8, .75, 3, 30, -30, 0, greenMaterial);
		dt.drawCone3D(r16);
		System.out.println(r16);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}