 package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Draw3D;
import org.edisonwj.draw3d.Polygon3D;
import org.edisonwj.draw3d.Rand;
import org.edisonwj.draw3d.Triangle3D;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.stage.Stage;
/**
*
* @author William Edison
* @version 1.00 July 2015
* 
*/
public class TestPolygon4 extends Application {
	private Draw3D dt;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestPolygon3");
		primaryStage.show();
	}
	
	private void buildData() {
	    
		dt.setCumulate(false);
		dt.setShowBoundaryCube(true);
		dt.setXYaxesOnly(true);
		dt.setShowBoundaryCube(false);
/*
		Point3D[] tv = new Point3D[17];
		tv[0] = new Point3D(3,3,0);
		tv[1] = new Point3D(3,5,0);
		tv[2] = new Point3D(0,5,0);
		tv[3] = new Point3D(0,1,0);
		tv[4] = new Point3D(-2,2,0);
		tv[5] = new Point3D(-3,4,0);
		tv[6] = new Point3D(-5,2,0);
		tv[7] = new Point3D(-3,2,0);
		tv[8] = new Point3D(-5,1,0);
		tv[9] = new Point3D(-5,-1,0);
		tv[10] = new Point3D(0,0,0);
		tv[11] = new Point3D(-3,-2,0);
		tv[12] = new Point3D(3,-2,0);
		tv[13] = new Point3D(4,2,0);
		tv[14] = new Point3D(5,1,0);
		tv[15] = new Point3D(7,4,0);
		tv[16] = new Point3D(6,5,0);
*/		
		Point3D[] tv = new Point3D[19];
		tv[0] = new Point3D(3,3,0);
		tv[1] = new Point3D(3,5,0);
		tv[2] = new Point3D(2,5,0);
		tv[3] = new Point3D(0,5,0);
		tv[4] = new Point3D(0,1,0);
		tv[5] = new Point3D(-2,2,0);
		tv[6] = new Point3D(-3,4,0);
		tv[7] = new Point3D(-5,2,0);
		tv[8] = new Point3D(-3,2,0);
		tv[9] = new Point3D(-5,1,0);
		tv[10] = new Point3D(-5,-1,0);
		tv[11] = new Point3D(0,0,0);
		tv[12] = new Point3D(-3,-2,0);
		tv[13] = new Point3D(1,-2,0);
		tv[14] = new Point3D(3,-2,0);
		tv[15] = new Point3D(4,2,0);
		tv[16] = new Point3D(5,1,0);
		tv[17] = new Point3D(7,4,0);
		tv[18] = new Point3D(6,5,0);
		
		Polygon3D p1 = new Polygon3D(tv, true, true, false);
		p1.setDrawMode(DrawMode.LINE);
//		p1.setMaterial(new PhongMaterial(Color.CORNFLOWERBLUE));
		dt.drawPolygon3D(p1);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}