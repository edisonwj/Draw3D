package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Draw3D;
import org.edisonwj.draw3d.Sphere3D;
import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.stage.Stage;
/**
*
* @author William Edison
* @version 1.00 July 2015
* 
*/
public class TestXSphereConstructors extends Application {
	private Draw3D dt;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestXSphereTorusConstructors");
		primaryStage.show();
	}
	
	private void buildData() {
		dt.setSequencingOn();
		
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
        
		Sphere3D b1 = new Sphere3D(new Point3D(-8.0, 5, 7.5), .75);
		dt.drawSphere3D(b1);
		System.out.println(b1);
		Sphere3D b2 = new Sphere3D(new Point3D(-6.0, 5, 7.5), .75, redMaterial);
		dt.drawSphere3D(b2);
		System.out.println(b2);
		
		Sphere3D b3 = new Sphere3D(new Point3D(-4.0, 5, 7.5), .75, 90, 0, 0);
		dt.drawSphere3D(b3);
		System.out.println(b3);
		Sphere3D b4 = new Sphere3D(new Point3D(-2.0, 5, 7.5), .75, 90, 0, 0, redMaterial);
		dt.drawSphere3D(b4);
		System.out.println(b4);

		Sphere3D b5 = new Sphere3D(2.0, 5, 7.5, .75);
		dt.drawSphere3D(b5);
		System.out.println(b5);
		Sphere3D b6 = new Sphere3D(4.0, 5, 7.5, .75, redMaterial);
		dt.drawSphere3D(b6);
		System.out.println(b6);
		
		Sphere3D b7 = new Sphere3D(6.0, 5, 7.5, .75, 90, 0, 0);
		dt.drawSphere3D(b7);
		System.out.println(b7);
		Sphere3D b8 = new Sphere3D(8.0, 5, 7.5, .75, 90, 0, 0, redMaterial);
		dt.drawSphere3D(b8);
		System.out.println(b8);
		
		Sphere3D b9 = new Sphere3D(new Point3D(-8.0, 5, 2.5), .75);
		dt.drawSphere3D(b9);
		System.out.println(b9);
		Sphere3D b10 = new Sphere3D(new Point3D(-6.0, 5, 2.5), .75, redMaterial);
		dt.drawSphere3D(b10);
		System.out.println(b10);
		
		Sphere3D b11 = new Sphere3D(new Point3D(-4.0, 5, 2.5), .75, 30, -30, 0);
		dt.drawSphere3D(b11);
		System.out.println(b11);
		Sphere3D b12 = new Sphere3D(new Point3D(-2.0, 5, 2.5), .75, 30, -30, 0, redMaterial);
		dt.drawSphere3D(b12);
		System.out.println(b12);

		Sphere3D b13 = new Sphere3D(2.0, 5, 2.5, .75);
		dt.drawSphere3D(b13);
		System.out.println(b13);
		Sphere3D b14 = new Sphere3D(4.0, 5, 2.5, .75, redMaterial);
		dt.drawSphere3D(b14);
		System.out.println(b14);
		
		Sphere3D b15 = new Sphere3D(6.0, 5, 2.5, .75, 30, -30 ,0);
		dt.drawSphere3D(b15);
		System.out.println(b15);
		Sphere3D b16 = new Sphere3D(8.0, 5, 2.5, .75, 30, -30, 0, redMaterial);
		dt.drawSphere3D(b16);
		System.out.println(b16);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}