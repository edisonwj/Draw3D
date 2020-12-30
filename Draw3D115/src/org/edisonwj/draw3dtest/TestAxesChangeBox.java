package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Box3D;
import org.edisonwj.draw3d.Cylinder3D;
import org.edisonwj.draw3d.Draw3D;
import org.edisonwj.draw3d.Line3D;
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
* @version 1.05 February 2016
* 
*/
public class TestAxesChangeBox extends Application {
	private Draw3D dt;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestAxesChangeBox");
		primaryStage.show();
	}
	
	private void buildData() {
		dt.setAmbientLight(true);
		dt.setPointLight(true);
		dt.setCumulate(false);
        
		dt.setSequencingOn();
        dt.setYaxisUp(true);
        dt.drawBox3D(new Box3D(new Point3D(8.5, 0, 0),
        		3, 2, 1,
        		0, 0, 0,
        		new PhongMaterial(Color.CRIMSON)));
        dt.drawBox3D(new Box3D(new Point3D(4.5, 0, 0),
        		3, 2, 1,
        		45, 0, 0,
        		new PhongMaterial(Color.CRIMSON)));
        dt.drawBox3D(new Box3D(new Point3D(0, 9, 0),
        		3, 2, 1,
        		0, 0, 0,
        		new PhongMaterial(Color.LAWNGREEN)));
        dt.drawBox3D(new Box3D(new Point3D(0, 5, 0),
        		3, 2, 1,
        		0, 45, 0,
        		new PhongMaterial(Color.LAWNGREEN)));
        dt.drawBox3D(new Box3D(new Point3D(0, 0, 8),
        		3, 2, 1,
        		0, 0, 0,
        		new PhongMaterial(Color.CORNFLOWERBLUE)));
        dt.drawBox3D(new Box3D(new Point3D(0, 0, 5),
        		3, 2, 1,
        		0, 0, 45,
        		new PhongMaterial(Color.CORNFLOWERBLUE)));
        
        dt.drawCylinder3D(new Cylinder3D(new Point3D(-6, 0, 0),
        		1, 2,
        		45, 0, 0,
        		new PhongMaterial(Color.CRIMSON)));
        dt.drawCylinder3D(new Cylinder3D(new Point3D(0, -6, 0),
        		1, 2,
        		0, 45, 0,
        		new PhongMaterial(Color.LAWNGREEN)));
        dt.drawCylinder3D(new Cylinder3D(new Point3D(0, 0, -6),
        		1, 2,
        		0, 0, 45,
        		new PhongMaterial(Color.CORNFLOWERBLUE)));
                
		dt.incrSequence();;
        dt.setZaxisUp(true);
        dt.drawBox3D(new Box3D(new Point3D(8.5, 0, 0),
        		3, 2, 1,
        		0, 0, 0,
        		new PhongMaterial(Color.CRIMSON)));
        dt.drawBox3D(new Box3D(new Point3D(4.5, 0, 0),
        		3, 2, 1,
        		45, 0, 0,
        		new PhongMaterial(Color.CRIMSON)));
        dt.drawBox3D(new Box3D(new Point3D(0, 9, 0),
        		3, 2, 1,
        		0, 0, 0,
        		new PhongMaterial(Color.LAWNGREEN)));
        dt.drawBox3D(new Box3D(new Point3D(0, 5, 0),
        		3, 2, 1,
        		0, 45, 0,
        		new PhongMaterial(Color.LAWNGREEN)));
        dt.drawBox3D(new Box3D(new Point3D(0, 0, 8),
        		3, 2, 1,
        		0, 0, 0,
        		new PhongMaterial(Color.CORNFLOWERBLUE)));
        dt.drawBox3D(new Box3D(new Point3D(0, 0, 5),
        		3, 2, 1,
        		0, 0, 45,
        		new PhongMaterial(Color.CORNFLOWERBLUE)));
        
        dt.drawCylinder3D(new Cylinder3D(new Point3D(-6, 0, 0),
        		1, 2,
        		45, 0, 0,
        		new PhongMaterial(Color.CRIMSON)));
        dt.drawCylinder3D(new Cylinder3D(new Point3D(0, -6, 0),
        		1, 2,
        		0, 45, 0,
        		new PhongMaterial(Color.LAWNGREEN)));
        dt.drawCylinder3D(new Cylinder3D(new Point3D(0, 0, -6),
        		1, 2,
        		0, 0, 45,
        		new PhongMaterial(Color.CORNFLOWERBLUE)));
        
        
        /*
        dt.drawBox3D(new Box3D(new Point3D(8, 0, 0),
        		4, 2, 1,
        		45, 0, 0,
        		new PhongMaterial(Color.CRIMSON))); 
        dt.drawBox3D(new Box3D(new Point3D(0, 8, 0),
        		4, 2, 1,
        		0, 45, 0,
        		new PhongMaterial(Color.LAWNGREEN)));
        dt.drawBox3D(new Box3D(new Point3D(0, 0, 8),
        		4, 2, 1,
        		0, 0, 45,
        		new PhongMaterial(Color.CORNFLOWERBLUE)));
        
        dt.drawCylinder3D(new Cylinder3D(new Point3D(5, 0, 0),
        		1, 2,
        		45, 0, 0,
        		new PhongMaterial(Color.CRIMSON)));
        dt.drawCylinder3D(new Cylinder3D(new Point3D(0, 4.5, 0),
        		1, 2,
        		0, 45, 0,
        		new PhongMaterial(Color.LAWNGREEN)));
        dt.drawCylinder3D(new Cylinder3D(new Point3D(0, 0, 5),
        		1, 2,
        		0, 0, 45,
        		new PhongMaterial(Color.CORNFLOWERBLUE)));
        
        dt.drawSphere3D(new Sphere3D(new Point3D(2, 0, 0),
        		1,
        		45, 0, 0,
        		new PhongMaterial(Color.CRIMSON)));
        dt.drawSphere3D(new Sphere3D(new Point3D(0, 2, 0),
        		1,
        		0, 45, 0,
        		new PhongMaterial(Color.LAWNGREEN)));
        dt.drawSphere3D(new Sphere3D(new Point3D(0, 0, 1),
        		1,
        		0, 0, 45,
        		new PhongMaterial(Color.CORNFLOWERBLUE)));
         */
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}