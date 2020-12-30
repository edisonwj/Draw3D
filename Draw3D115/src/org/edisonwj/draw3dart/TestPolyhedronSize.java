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
public class TestPolyhedronSize extends Application {
	private Draw3D dt;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestPolyhedronSize");
		primaryStage.show();
	}
	
	private void buildData() {
		PhongMaterial blueMaterial = new PhongMaterial(Color.BLUE);
		PhongMaterial redMaterial = new PhongMaterial(Color.RED);
        PhongMaterial lightgrayMaterial = new PhongMaterial(Color.LIGHTGRAY);
		
		dt.setAmbientLight(false);
		dt.setPointLight(true);
		
		double s = 2;
		Dodecahedron3D o3d13 = new Dodecahedron3D(5, 5, -5, s, 20, 0, 0, lightgrayMaterial);
		dt.drawPolyhedron3D(o3d13, true);
		System.out.println(o3d13);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}