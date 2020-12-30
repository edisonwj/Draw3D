package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Draw3D;
import org.edisonwj.draw3d.Oval3D;
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
public class TestXOvalConstructors extends Application {
	private Draw3D dt;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestXOvalConstructors");
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
        
		Oval3D b1 = new Oval3D(new Point3D(5, -8.0, 7.5), .75, 2, greenMaterial);
		dt.drawOval3D(b1);
		System.out.println(b1);
		Oval3D b2 = new Oval3D(new Point3D(5, -6.0, 7.5), .75, 2, redMaterial);
		dt.drawOval3D(b2);
		System.out.println(b2);
		
		Oval3D b3 = new Oval3D(new Point3D(5, -4.0, 7.5), .75, 2, 90, 0, 0, greenMaterial);
		dt.drawOval3D(b3);
		System.out.println(b3);
		Oval3D b4 = new Oval3D(new Point3D(5, -2.0, 7.5), .75, 2, 90, 0, 0, redMaterial);
		dt.drawOval3D(b4);
		System.out.println(b4);

		Oval3D b5 = new Oval3D(5, 2.0, 7.5, .75, 2, greenMaterial);
		dt.drawOval3D(b5);
		System.out.println(b5);
		Oval3D b6 = new Oval3D(5, 4.0, 7.5, .75, 2, redMaterial);
		dt.drawOval3D(b6);
		System.out.println(b6);
		
		Oval3D b7 = new Oval3D(5, 6.0, 7.5, .75, 2, 90, 0, 0, greenMaterial);
		dt.drawOval3D(b7);
		System.out.println(b7);
		Oval3D b8 = new Oval3D(5, 8.0, 7.5, .75, 2, 90, 0, 0, redMaterial);
		dt.drawOval3D(b8);
		System.out.println(b8);
		
		Oval3D b9 = new Oval3D(new Point3D(5, -8.0, 2.5), .75, 2, greenMaterial);
		dt.drawOval3D(b9);
		System.out.println(b9);
		Oval3D b10 = new Oval3D(new Point3D(5, -6.0, 2.5), .75, 2, redMaterial);
		dt.drawOval3D(b10);
		System.out.println(b10);
		
		Oval3D b11 = new Oval3D(new Point3D(5, -4.0, 2.5), .75, 2, 30, -30, 0, greenMaterial);
		dt.drawOval3D(b11);
		System.out.println(b11);
		Oval3D b12 = new Oval3D(new Point3D(5, -2.0, 2.5), .75, 2, 30, -30, 0, redMaterial);
		dt.drawOval3D(b12);
		System.out.println(b12);

		Oval3D b13 = new Oval3D(5, 2.0, 2.5, .75, 2, greenMaterial);
		dt.drawOval3D(b13);
		System.out.println(b13);
		Oval3D b14 = new Oval3D(5, 4.0, 2.5, .75, 2, redMaterial);
		dt.drawOval3D(b14);
		System.out.println(b14);
		
		Oval3D b15 = new Oval3D(5, 6.0, 2.5, .75, 2, 30, -30 ,0, greenMaterial);
		dt.drawOval3D(b15);
		System.out.println(b15);
		Oval3D b16 = new Oval3D(5, 8.0, 2.5, .75, 2, 30, -30, 0, redMaterial);
		dt.drawOval3D(b16);
		System.out.println(b16);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}