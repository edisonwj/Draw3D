package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Box3D;
import org.edisonwj.draw3d.Cone3D;
import org.edisonwj.draw3d.Cylinder3D;
import org.edisonwj.draw3d.Draw3D;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.stage.Stage;
/**
*
* @author William Edison
* @version 1.05 February 2016
* 
*/
public class TestCone extends Application {
	private Draw3D dt;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestCone");
		primaryStage.show();
	}
	
	private void buildData() {
		dt.setAmbientLight(false);
		dt.setPointLight(true);
		
		Cone3D c3d1 = new Cone3D(4, 0, 0, 1, 2, 0, 0, 0, new PhongMaterial(Color.CRIMSON));
		c3d1.setConeDivisions(8);
		dt.drawCone3D(c3d1);
		
		Cone3D c3d2 = new Cone3D(6, 0, 0, 1, 2, 45, 0, 0, new PhongMaterial(Color.CRIMSON));
		c3d2.setConeDivisions(8);
		dt.drawCone3D(c3d2);
		
		Cone3D c3d3 = new Cone3D(8, 0, 0, 1, 2, 90, 0, 0, new PhongMaterial(Color.CRIMSON));
		c3d3.setConeDivisions(8);
		dt.drawCone3D(c3d3);
		
	
		Cone3D c3d4 = new Cone3D(0, 4, 0, 1, 2, 0, 0, 0, new PhongMaterial(Color.TEAL));
		c3d4.setConeDivisions(8);
		dt.drawCone3D(c3d4);
		
		Cone3D c3d5 = new Cone3D(0, 6, 0, 1, 2, 0, 45, 0, new PhongMaterial(Color.TEAL));
		c3d5.setConeDivisions(8);
		dt.drawCone3D(c3d5);
		
		Cone3D c3d6 = new Cone3D(0, 8, 0, 1, 2, 0, 90, 0, new PhongMaterial(Color.TEAL));
		c3d6.setConeDivisions(8);
		dt.drawCone3D(c3d6);
		
		
		Cone3D c3d7 = new Cone3D(0, 0, 4, 1, 2, 0, 0, 0, new PhongMaterial(Color.CORNFLOWERBLUE));
		c3d7.setConeDivisions(8);
		dt.drawCone3D(c3d7);
		
		Cone3D c3d8 = new Cone3D(0, 0, 6, 1, 2, 0, 0, 45, new PhongMaterial(Color.CORNFLOWERBLUE));
		c3d8.setConeDivisions(8);
		dt.drawCone3D(c3d8);
		
		Cone3D c3d9 = new Cone3D(0, 0, 8, 1, 2, 0, 0, 90, new PhongMaterial(Color.CORNFLOWERBLUE));
		c3d9.setConeDivisions(8);
		dt.drawCone3D(c3d9);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}