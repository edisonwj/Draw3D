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
* @version 1.08 January 2017
* 
*/
public class TestCone3 extends Application {
	private Draw3D dt;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestCone3");
		primaryStage.show();
	}
	
	private void buildData() {
		dt.setAmbientLight(false);
		dt.setPointLight(true);
		
		Cone3D c3d1 = new Cone3D(4, 0, 0, .05, 2, 0, 0, 0, new PhongMaterial(Color.GOLDENROD));
		c3d1.setConeDivisions(16);
		dt.drawCone3D(c3d1);
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}