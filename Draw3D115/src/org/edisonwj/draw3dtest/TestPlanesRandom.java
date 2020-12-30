package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Arrow3D;
import org.edisonwj.draw3d.Draw3D;
import org.edisonwj.draw3d.Plane3D;
import org.edisonwj.draw3d.Rand;
import org.edisonwj.draw3d.Text3D;
import org.edisonwj.draw3d.Vector3D;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.text.Font;
import javafx.stage.Stage;
/**
*
* @author William Edison
* @version 1.00 July 2015
* 
*/
public class TestPlanesRandom extends Application {
	private Draw3D dt;
	private Rand mr;
	private int iterations = 100;
	private double a = 2;
	private double b = 3;
	private double c = 4;
	private double d = 5;
	
	@Override
	public void start(Stage primaryStage) {
		mr = new Rand();
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestPlanesRandom");
		primaryStage.show();
	}
	
	private void buildData() {
			    
		dt.setCumulate(false);
		dt.setAmbientLight(true);
		dt.setPointLight(false);
		dt.setShowBoundaryCube(true);
		Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.MAGENTA,
						  Color.SALMON, Color.ORCHID, Color.AZURE, Color.TEAL};
		
		for (int i = 0; i < iterations; i++) {
			dt.incrSequence();
			setPlane();
			Plane3D pl = new Plane3D(a, b, c, d, new PhongMaterial(Color.ANTIQUEWHITE));
			dt.drawPlane3D(pl);
			String label = String.format("Plane #=%3d, d=%6.2f, p=%6.2f", i, d, pl.getP());
			Text3D labelText = new Text3D(10, -10, -12, 0, 180, 18, label, Color.BLACK);
			labelText.setLabelFont(Font.font("Regular", 14));
			dt.drawLabel3D(labelText);
			Vector3D un = Vector3D.multiply(10.0,  pl.getUnitNormal());
			un.setMaterial(new PhongMaterial(Color.MAGENTA));
			dt.drawVector3D(un);
						
			Point3D[] ei = pl.getIntersections();
			int[][] t = pl.planeTriangulate();
			System.out.println("t.length= " + t.length);
			for (int j = 0; j < t.length; j++) {
				dt.setDrawColor(colors[j]);
				dt.drawArrow3D(new Arrow3D(ei[t[j][0]], ei[t[j][1]]));
				dt.drawArrow3D(new Arrow3D(ei[t[j][1]], ei[t[j][2]]));
				dt.drawArrow3D(new Arrow3D(ei[t[j][2]], ei[t[j][0]]));
			}
		}
	}
	
	private void setPlane() {
		a = mr.uniform(0.0, 10.0);
		if (mr.uniform() < .5) a = -a;
		b = mr.uniform(0.0, 10.0);
		if (mr.uniform() < .5) b = -b;
		c = mr.uniform(0.0, 10.0);
		if (mr.uniform() < .5) c = -c;
		d = mr.uniform(0.0, 10.0);
		if (mr.uniform() < .5) d = -d;
		System.out.println("Plane: a= " + a + ", b= " + b + ", c= " + c + ", d= " + d);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}