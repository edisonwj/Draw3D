package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Draw3D;
import org.edisonwj.draw3d.Triangle3D;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.stage.Stage;
/**
*
* @author William Edison
* @version 1.00 February 2016
* 
*/
public class TestTriangle extends Application {
	private Draw3D dt;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestTriangle");
		primaryStage.show();
	}
	
	private void buildData() {
		dt.setAmbientLight(true);
		dt.setPointLight(false);
		
		Triangle3D t1 = new Triangle3D( new Point3D(0, 0, 0),
										new Point3D(2, 0, 0),
										new Point3D(2, 2, 0),
										new PhongMaterial(Color.CORNFLOWERBLUE));
		dt.drawTriangle3D(t1);
		
		Triangle3D t2 = new Triangle3D( new Point3D(2,2,2),
										new Point3D(2,4,2),
										new Point3D(1,3,3),
										new PhongMaterial(Color.CRIMSON));
		dt.drawTriangle3D(t2);
		
		Triangle3D t3 = new Triangle3D( new Point3D(0, 0, 0),
										new Point3D(2, 0, 0),
										new Point3D(2, 0, -2),
										new PhongMaterial(Color.GOLDENROD));
		dt.drawTriangle3D(t3);

		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}