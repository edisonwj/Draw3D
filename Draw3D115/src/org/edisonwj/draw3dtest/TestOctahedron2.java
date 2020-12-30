package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Draw3D;
import org.edisonwj.draw3d.Line3D;
import org.edisonwj.draw3d.Rand;
import org.edisonwj.draw3d.Octahedron3D;
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
public class TestOctahedron2 extends Application {
	private Draw3D dt;
	double os = 2.0;
	double xrinit = 45;
	double yrinit = 45;
	double zrinit = 45;
	double xr = 0;
	double yr = 0;
	double zr = 0;
	boolean yUp = false;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestOctahedron2");
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
		
		// Draw Octahedrons with Rotations
		dt.setSequencingOn();

		dt.setYaxisUp(yUp);
		double xloc, ylab, zlab;
		
		Octahedron3D t3d21 = new Octahedron3D( 0, 6, 0, os, 0, 0, 0, blueMaterial);
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
			Octahedron3D t3d22 = new Octahedron3D( xloc, 0, 0, os, xr, 0, 0, blueMaterial);
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
			Octahedron3D t3d23 = new Octahedron3D( xloc, 0, 0, os, 0, yr, 0, blueMaterial);
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
			Octahedron3D t3d24 = new Octahedron3D( xloc, 0, 0, os, 0, 0, zr, blueMaterial);
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
		Octahedron3D t3d25 = new Octahedron3D( 0, 0, 0, os, xr, yr, 0, blueMaterial);
		t3d25.setDrawMode(DrawMode.FILL);
		dt.drawPolyhedron3D(t3d25, true);
		dt.drawLabel(6, 10, 0, "X=" + xr + ", Y=" + yr + " rotation");
		
		dt.incrSequence();
		dt.drawPolyhedron3D(t3d21, true);
		Octahedron3D t3d26 = new Octahedron3D( 0, 0, 0, os, xr, 0, zr, blueMaterial);
		t3d26.setDrawMode(DrawMode.FILL);
		dt.drawPolyhedron3D(t3d26, true);
		dt.drawLabel(6, 10, 0, "X=" + xr + ", Z=" + zr + " rotation");
		
		dt.incrSequence();
		dt.drawPolyhedron3D(t3d21, true);
		Octahedron3D t3d27 = new Octahedron3D( 0, 0, 0, os, 0, yr, zr, blueMaterial);
		t3d27.setDrawMode(DrawMode.FILL);
		dt.drawPolyhedron3D(t3d27, true);
		dt.drawLabel(6, 10, 0, "Y=" + yr + ", Z=" + zr + " rotation");
		
		dt.incrSequence();
		Octahedron3D t3d28 = new Octahedron3D( -6, -6, 0, os, xr, 0, 0, blueMaterial);
		t3d28.setDrawMode(DrawMode.FILL);
		dt.drawPolyhedron3D(t3d28, true);
		Octahedron3D t3d29 = new Octahedron3D( 0, -6, 0, os, 0, yr, 0, blueMaterial);
		t3d29.setDrawMode(DrawMode.FILL);
		dt.drawPolyhedron3D(t3d29, true);
		Octahedron3D t3d30 = new Octahedron3D( 6, -6, 0, os, 0, 0, zr, blueMaterial);
		t3d30.setDrawMode(DrawMode.FILL);
		dt.drawPolyhedron3D(t3d30, true);
		
		Octahedron3D t3d32 = new Octahedron3D( 0, 6, 0, os, 0, 0, 0, blueMaterial);
		dt.drawPolyhedron3D(t3d32, true);
		Octahedron3D t3d31 = new Octahedron3D( 0, 0, 0, os, xr, yr, zr, blueMaterial);
		dt.drawPolyhedron3D(t3d31, true);
		dt.drawLabel(6, 10, 0, "X=" + xr + ", Y=" + yr + ", Z= " + zr + " rotation");
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