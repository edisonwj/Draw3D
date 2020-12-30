package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Draw3D;
import org.edisonwj.draw3d.Plane3D;
import org.edisonwj.draw3d.Vector3D;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
*
* @author William Edison
* @version 1.00 July 2015
* 
*/
public class TestPlaneEquation extends Application {
	private Draw3D dt;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestPlaneEquation");
		primaryStage.show();
	}
	
	private void buildData() {
	    
		dt.setCumulate(false);
//		dt.setAmbientLight(true);
//		dt.setPointLight(false);
		dt.setDrawColor(Color.LIGHTGRAY);
		
		dt.setShowBoundaryCube(true);
		
		Plane3D p01 = new Plane3D(1,1,1,0);
		Plane3D p02 = new Plane3D(1,1,1,5);
		Plane3D p03 = new Plane3D(1,1,1,10);
		Plane3D p04 = new Plane3D(1,1,1,15);
		Plane3D p05 = new Plane3D(1,1,1,20);
		Plane3D p06 = new Plane3D(1,1,1,25);
		Plane3D p07 = new Plane3D(1,1,1,30);
		Plane3D p08 = new Plane3D(1,1,1,35);
		Plane3D p09 = new Plane3D(new Point3D(0,0,0), new Point3D(10,-10, 10), new Point3D(-10,-10,10));
		Plane3D p10 = new Plane3D(new Point3D(0,0,0), new Point3D(10,10,10), new Point3D(-10,10,10));
		Plane3D p11 = new Plane3D(new Point3D(0,0,0), new Point3D(10,10,10), new Point3D(10,-10,10));
		Plane3D p12 = new Plane3D(new Point3D(0,0,0), new Point3D(-10,10,10), new Point3D(-10,-10,10));
		
		
		Vector3D un01 = Vector3D.multiply(10.0, p01.getUnitNormal());
		Vector3D un02 = Vector3D.multiply(10.0, p02.getUnitNormal());
		Vector3D un03 = Vector3D.multiply(10.0, p03.getUnitNormal());
		Vector3D un04 = Vector3D.multiply(10.0, p04.getUnitNormal());
		Vector3D un05 = Vector3D.multiply(10.0, p05.getUnitNormal());
		Vector3D un06 = Vector3D.multiply(10.0, p06.getUnitNormal());
		Vector3D un07 = Vector3D.multiply(10.0, p07.getUnitNormal());
		Vector3D un08 = Vector3D.multiply(10.0, p08.getUnitNormal());
		Vector3D un09 = Vector3D.multiply(10.0, p09.getUnitNormal());
		Vector3D un10 = Vector3D.multiply(10.0, p10.getUnitNormal());
		Vector3D un11 = Vector3D.multiply(10.0, p11.getUnitNormal());
		Vector3D un12 = Vector3D.multiply(10.0, p12.getUnitNormal());
		

		dt.setDrawColor(Color.LIGHTGRAY);
		dt.drawPlane3D(p01);
		dt.setDrawColor(Color.MAGENTA);
		dt.drawVector3D(un01);
		
		dt.incrSequence();
		dt.setDrawColor(Color.LIGHTGRAY);
		dt.drawPlane3D(p02);
		dt.setDrawColor(Color.MAGENTA);
		dt.drawVector3D(un02);
	
		dt.incrSequence();
		dt.setDrawColor(Color.LIGHTGRAY);
		dt.drawPlane3D(p03);
		dt.setDrawColor(Color.MAGENTA);
		dt.drawVector3D(un03);
	
		dt.incrSequence();
		dt.setDrawColor(Color.LIGHTGRAY);
		dt.drawPlane3D(p04);
		dt.setDrawColor(Color.MAGENTA);
		dt.drawVector3D(un04);
		
		dt.incrSequence();
		dt.setDrawColor(Color.LIGHTGRAY);
		dt.drawPlane3D(p05);
		dt.setDrawColor(Color.MAGENTA);
		dt.drawVector3D(un05);
		
		dt.incrSequence();
		dt.setDrawColor(Color.LIGHTGRAY);
		dt.drawPlane3D(p06);
		dt.setDrawColor(Color.MAGENTA);
		dt.drawVector3D(un06);
		
		dt.incrSequence();
		dt.setDrawColor(Color.LIGHTGRAY);
		dt.drawPlane3D(p07);
		dt.setDrawColor(Color.MAGENTA);
		dt.drawVector3D(un07);
		
		dt.incrSequence();
		dt.setDrawColor(Color.LIGHTGRAY);
		dt.drawPlane3D(p08);
		dt.setDrawColor(Color.MAGENTA);
		dt.drawVector3D(un08);
		
		dt.incrSequence();
		dt.setDrawColor(Color.LIGHTGRAY);
		dt.drawPlane3D(p09);
		dt.setDrawColor(Color.MAGENTA);
		dt.drawVector3D(un09);
		
		dt.incrSequence();
		dt.setDrawColor(Color.LIGHTGRAY);
		dt.drawPlane3D(p10);
		dt.setDrawColor(Color.MAGENTA);
		dt.drawVector3D(un10);
		
		dt.incrSequence();
		dt.setDrawColor(Color.LIGHTGRAY);
		dt.drawPlane3D(p11);
		dt.setDrawColor(Color.MAGENTA);
		dt.drawVector3D(un11);
		
		dt.incrSequence();
		dt.setDrawColor(Color.LIGHTGRAY);
		dt.drawPlane3D(p12);
		dt.setDrawColor(Color.MAGENTA);
		dt.drawVector3D(un12);
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}