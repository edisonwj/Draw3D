package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Draw3D;
import org.edisonwj.draw3d.Line3D;
import org.edisonwj.draw3d.Rand;
import org.edisonwj.draw3d.Icosahedron3D;
import org.edisonwj.draw3d.Sphere3D;
import org.edisonwj.draw3d.Tetrahedron3D;

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
* @version 1.08 January 2017
* 
*/
public class TestIcosahedron2 extends Application {
	private Draw3D dt;
	double os = 1;
	double xrinit = 45;
	double yrinit = 45;
	double zrinit = 45;
	double xr = 0;
	double yr = 0;
	double zr = 0;
	boolean yUp = true;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestIcosahedron2");
		primaryStage.show();
	}
	
	private void buildData() {
		PhongMaterial blueMaterial = new PhongMaterial(Color.LIGHTBLUE);
		PhongMaterial redMaterial = new PhongMaterial(Color.RED);
        PhongMaterial lightgrayMaterial = new PhongMaterial(Color.LIGHTGRAY);

		Rand mr = new Rand();
		
		dt.setAmbientLight(false);
		dt.setPointLight(true);
		dt.setXYZRange(8, 8, 8);
		dt.setZaxisUp(true);
		
		// Draw Icosahedrons with Rotations
		dt.setSequencingOn();
/*	
		Icosahedron3D t3d10 = new Icosahedron3D(-6, 0, 0, os, 0, 0, 0, blueMaterial);
		t3d10.setDrawMode(DrawMode.FILL);
		dt.drawPolyhedron3D(t3d10,  true);
		
		Icosahedron3D t3d11 = new Icosahedron3D( -2, 0, 0, os, 45, 0, 0, blueMaterial);
		t3d11.setDrawMode(DrawMode.FILL);
		dt.drawPolyhedron3D(t3d11, true);
		
		Icosahedron3D t3d12 = new Icosahedron3D( 2, 0, 0, os, 0, 45, 0, blueMaterial);
		t3d12.setDrawMode(DrawMode.FILL);
		dt.drawPolyhedron3D(t3d12, true);
		
		Icosahedron3D t3d13 = new Icosahedron3D( 6, 0, 0, os, 45, 45, 0, blueMaterial);
		t3d13.setDrawMode(DrawMode.FILL);
		dt.drawPolyhedron3D(t3d13, true);
		
		Line3D ln1 = new Line3D(-6.0, 0.0, 1.5, -4.5, 0.0, 0.0);
		ln1.setLineRadius(2);
		ln1.setMaterial(new PhongMaterial(Color.RED));
		dt.drawLine3D(ln1);
		Sphere3D sp1 = new Sphere3D(-6.0, 0.0, 1.5, .1);
		dt.setMaterial(new PhongMaterial(Color.RED));
		dt.drawSphere3D(sp1);
		
		Line3D ln2 = new Line3D(6.8,1.06,0.75, 5.3,1.06,-0.75);
		ln2.setLineRadius(2);
		ln2.setMaterial(new PhongMaterial(Color.MAGENTA));
		dt.drawLine3D(ln2);
		
		dt.drawGrid3D(0, 4, new PhongMaterial(Color.LIGHTGRAY));
*/	
//		dt.incrSequence();
		dt.setYaxisUp(yUp);
		double xloc, ylab, zlab;
		
		Icosahedron3D t3d21 = new Icosahedron3D( 0, 6, 0, os, 0, 0, 0, blueMaterial);
		dt.drawPolyhedron3D(t3d21, true);
		dt.setDrawMode(DrawMode.FILL);
		dt.setLabelFontSize(12);
		
		// x rotations
		dt.drawPolyhedron3D(t3d21, true);
		initRotations();
		xloc = -8;
		ylab = 9;
		zlab = 9;	
		for (int i = 0; i < 5; i++) {
			Icosahedron3D t3d22 = new Icosahedron3D( xloc, 0, 0, os, xr, 0, 0, blueMaterial);
			dt.drawPolyhedron3D(t3d22, true);
			if (yUp) {
				dt.drawLabel(6, ylab, 0, "X=" + xr + " rotation");
				xloc += 4;
				ylab -= 2;
			}
			else {
				dt.drawLabel(0, 6, zlab, "X=" + xr + " rotation");
				xloc += 4;
				zlab -= 2;
			}
			xr += 45;
		}
		
		// y rotations
		dt.incrSequence();
		dt.drawPolyhedron3D(t3d21, true);
		initRotations();
		xloc = -8;
		ylab = 9;
		zlab = 9;
		for (int i = 0; i < 5; i++) {
			Icosahedron3D t3d23 = new Icosahedron3D( xloc, 0, 0, os, 0, yr, 0, blueMaterial);
			dt.drawPolyhedron3D(t3d23, true);
			if (yUp) {
				dt.drawLabel(6, ylab, 0, "Y=" + yr + " rotation");
				xloc += 4;
				ylab -= 2;
			}
			else {
				dt.drawLabel(0, 6, zlab, "Y=" + yr + " rotation");
				xloc += 4;
				zlab -= 2;
			}
			yr += 45;
		}
		
		// z rotations
		dt.incrSequence();
		dt.drawPolyhedron3D(t3d21, true);
		initRotations();
		xloc = -8;
		ylab = 9;
		zlab = 9;
		for (int i = 0; i < 5; i++) {
			Icosahedron3D t3d24 = new Icosahedron3D( xloc, 0, 0, os, 0, 0, zr, blueMaterial);
			dt.drawPolyhedron3D(t3d24, true);
			if (yUp) {
				dt.drawLabel(6, ylab, 0, "Z=" + zr + " rotation");
				xloc += 4;
				ylab -= 2;
			}
			else {
				dt.drawLabel(0, 6, zlab, "Z=" + zr + " rotation");
				xloc += 4;
				zlab -= 2;
			}	
			zr += 45;
		}
			
		dt.incrSequence();
		initRotations();
		dt.drawPolyhedron3D(t3d21, true);
		Icosahedron3D t3d25 = new Icosahedron3D( 0, 0, 0, os, xr, yr, 0, blueMaterial);
		t3d25.setDrawMode(DrawMode.FILL);
		dt.drawPolyhedron3D(t3d25, true);
		dt.drawLabel(6, 10, 0, "X=" + xr + ", Y=" + yr + " rotation");
		
		dt.incrSequence();
		dt.drawPolyhedron3D(t3d21, true);
		Icosahedron3D t3d26 = new Icosahedron3D( 0, 0, 0, os, xr, 0, zr, blueMaterial);
		t3d26.setDrawMode(DrawMode.FILL);
		dt.drawPolyhedron3D(t3d26, true);
		dt.drawLabel(6, 10, 0, "X=" + xr + ", Z=" + zr + " rotation");
		
		dt.incrSequence();
		dt.drawPolyhedron3D(t3d21, true);
		Icosahedron3D t3d27 = new Icosahedron3D( 0, 0, 0, os, 0, yr, zr, blueMaterial);
		t3d27.setDrawMode(DrawMode.FILL);
		dt.drawPolyhedron3D(t3d27, true);
		dt.drawLabel(6, 10, 0, "Y=" + yr + ", Z=" + zr + " rotation");
		
		dt.incrSequence();
		Icosahedron3D t3d28 = new Icosahedron3D( -6, -6, 0, os, xr, 0, 0, blueMaterial);
		t3d28.setDrawMode(DrawMode.FILL);
		dt.drawPolyhedron3D(t3d28, true);
		Icosahedron3D t3d29 = new Icosahedron3D( 0, -6, 0, os, 0, yr, 0, blueMaterial);
		t3d29.setDrawMode(DrawMode.FILL);
		dt.drawPolyhedron3D(t3d29, true);
		Icosahedron3D t3d30 = new Icosahedron3D( 6, -6, 0, os, 0, 0, zr, blueMaterial);
		t3d30.setDrawMode(DrawMode.FILL);
		dt.drawPolyhedron3D(t3d30, true);
		
		Icosahedron3D t3d32 = new Icosahedron3D( 0, 6, 0, os, 0, 0, 0, blueMaterial);
		dt.drawPolyhedron3D(t3d32, true);
		Icosahedron3D t3d31 = new Icosahedron3D( 0, 0, 0, os, xr, yr, zr, blueMaterial);
		dt.drawPolyhedron3D(t3d31, true);
		dt.drawLabel(6, 10, 0, "X=" + xr + ", Y=" + yr + ", Z= " + zr + " rotation");
		
//		Line3D ln2 = new Line3D(7.06,-0.75,0.75, 7.06,0.75,-0.75);
//		ln2.setLineRadius(2);
//		ln2.setMaterial(new PhongMaterial(Color.RED));
//		dt.drawLine3D(ln2);
//		Sphere3D sp2 = new Sphere3D(7.06,-0.75,0.75, .1);
//		sp2.setMaterial(new PhongMaterial(Color.RED));
//		dt.drawSphere3D(sp2);
		
//		Sphere3D sp2 = new Sphere3D(7.06,-0.75,0.75, .1);
//		sp2.setMaterial(new PhongMaterial(Color.RED));
//		dt.drawSphere3D(sp2);
		
/*
		
		for (int i = 0; i < 100; i++) {
			
			// Pick location
			double ox = mr.uniform(-8, 8);			// random x coordinate
			double oy = mr.uniform(-8, 8);			// random y coordinate
			double oz = mr.uniform(-8, 8);			// random z coordinate
			
			// Pick rotations
			double rx = mr.uniform(0, 360);			// rotation about x axis
			double ry = mr.uniform(0, 360);			// rotation about y axis
			double rz = mr.uniform(0, 360);			// rotation about z axis
			
			Icosahedron3D dod3d = new Icosahedron3D(ox, oy, oz, os, rx, ry, rz, blueMaterial);
			dod3d.setDrawMode(DrawMode.FILL);
			dt.drawPolyhedron3D(dod3d, true);
			System.out.println(dod3d);
//			dt.incrSequence();
		}
*/
	}
	
	private void initRotations() {
		xr = xrinit;
		yr = yrinit;
		zr = zrinit;
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}