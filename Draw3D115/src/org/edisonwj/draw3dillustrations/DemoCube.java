package org.edisonwj.draw3dillustrations;

import org.edisonwj.draw3d.Draw3D;
import org.edisonwj.draw3d.Line3D;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.stage.Stage;
/**
* Demonstrates running various Draw3D Algorithm class files with setting
* of algorithm parameters such as delay time, etc.
*
* @author William Edison
* @version 1.14 January 2018
*
*/
public class DemoCube extends Application {
	private Draw3D dt;

	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("AlgorithmDemo");
		primaryStage.show();
	}

	private void buildData() {
		dt.setPointLight(false);
		dt.setAmbientLight(true);
		dt.setXYZRange(12,  12,  12);
		
		Point3D[] p = {
				new Point3D(5,1,8),
				new Point3D(5,3,8),
				new Point3D(9,3,8),
				new Point3D(9,1,8),
				new Point3D(5,1,11),
				new Point3D(5,3,11),
				new Point3D(9,3,11),
				new Point3D(9,1,11)
		};
		dt.setPointSize(4);
		for (int i = 0; i < p.length;i++) {
			dt.drawPoint3D(p[i]);	
		}
		dt.drawPoint3DSet(p);
		dt.drawLine3D(new Line3D(p[0],p[1], new PhongMaterial(Color.RED)));
		dt.drawLine3D(new Line3D(p[1],p[2], new PhongMaterial(Color.BLUE)));
		dt.drawLine3D(new Line3D(p[2],p[3], new PhongMaterial(Color.GREEN)));
		dt.drawLine3D(new Line3D(p[3],p[0], new PhongMaterial(Color.YELLOW)));
		
		dt.drawLine3D(new Line3D(p[4],p[5], new PhongMaterial(Color.RED)));
		dt.drawLine3D(new Line3D(p[5],p[6], new PhongMaterial(Color.BLUE)));
		dt.drawLine3D(new Line3D(p[6],p[7], new PhongMaterial(Color.GREEN)));
		dt.drawLine3D(new Line3D(p[7],p[4], new PhongMaterial(Color.YELLOW)));
		
		dt.drawLine3D(new Line3D(p[0],p[4], new PhongMaterial(Color.RED)));
		dt.drawLine3D(new Line3D(p[1],p[5], new PhongMaterial(Color.BLUE)));
		dt.drawLine3D(new Line3D(p[2],p[6], new PhongMaterial(Color.GREEN)));
		dt.drawLine3D(new Line3D(p[3],p[7], new PhongMaterial(Color.YELLOW)));
		
	}						

	public static void main(String[] args) {
		launch(args);
	}
}