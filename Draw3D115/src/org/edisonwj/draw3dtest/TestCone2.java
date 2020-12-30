package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Cone3D;
import org.edisonwj.draw3d.Cylinder3D;
import org.edisonwj.draw3d.Draw3D;
import org.edisonwj.draw3d.Line3D;
import org.edisonwj.draw3d.Rand;
import org.edisonwj.draw3d.Octahedron3D;
import org.edisonwj.draw3d.Sphere3D;
import org.edisonwj.draw3d.Tetrahedron3D;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
public class TestCone2 extends Application {
	private Draw3D dt;
	double or = 1;
	double oh = 2;
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
		primaryStage.setTitle("TestCone2");
		primaryStage.show();
	}
	
	private void buildData() {
		PhongMaterial blueMaterial = new PhongMaterial(Color.LIGHTBLUE);
		PhongMaterial redMaterial = new PhongMaterial(Color.RED);
        PhongMaterial lightgrayMaterial = new PhongMaterial(Color.LIGHTGRAY);
		
		dt.setAmbientLight(false);
		dt.setPointLight(true);
		dt.setDrawColor(Color.BLACK);
		dt.setLabelFontSize(14);
		dt.setXYZRange(8, 8, 8);

		int conediv = 8;
		dt.setYaxisUp(yUp);
		
		dt.setSequencingOn();
		double xloc, ylab, zlab;
		
		Cone3D t3d21 = new Cone3D( 0, 6, 0, or, oh, 0, 0, 0, blueMaterial);
		t3d21.setConeDivisions(conediv);
		dt.drawCone3D(t3d21);
		dt.setDrawMode(DrawMode.FILL);
		
		// x rotations
		dt.drawCone3D(t3d21);
		initRotations();
		xloc = -8;
		ylab = 9;
		zlab = 9;	
		for (int i = 0; i < 5; i++) {
			Cone3D t3d22 = new Cone3D( xloc, 0, 0, or, oh, xr, 0, 0, blueMaterial);
			t3d22.setConeDivisions(conediv);
			dt.drawCone3D(t3d22);
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
		dt.drawCone3D(t3d21);
		initRotations();
		xloc = -8;
		ylab = 9;
		zlab = 9;
		for (int i = 0; i < 5; i++) {
			Cone3D t3d23 = new Cone3D( xloc, 0, 0, or, oh, 0, yr, 0, blueMaterial);
			t3d23.setConeDivisions(conediv);
			dt.drawCone3D(t3d23);
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
		dt.drawCone3D(t3d21);
		initRotations();
		xloc = -8;
		ylab = 9;
		zlab = 9;
		for (int i = 0; i < 5; i++) {
			Cone3D t3d24 = new Cone3D( xloc, 0, 0, or, oh, 0, 0, zr, blueMaterial);
			t3d24.setConeDivisions(conediv);
			dt.drawCone3D(t3d24);
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
		dt.drawCone3D(t3d21);
		Cone3D t3d25 = new Cone3D( 0, 0, 0, or, oh, xr, yr, 0, blueMaterial);
		t3d25.setConeDivisions(conediv);
		t3d25.setDrawMode(DrawMode.FILL);
		dt.drawCone3D(t3d25);
		dt.drawLabel(6, 10, 0, "X=" + xr + ", Y=" + yr + " rotation");
		
		dt.incrSequence();
		dt.drawCone3D(t3d21);
		Cone3D t3d26 = new Cone3D( 0, 0, 0, or, oh, xr, 0, zr, blueMaterial);
		t3d26.setConeDivisions(conediv);
		t3d26.setDrawMode(DrawMode.FILL);
		dt.drawCone3D(t3d26);
		dt.drawLabel(6, 10, 0, "X=" + xr + ", Z=" + zr + " rotation");
		
		dt.incrSequence();
		dt.drawCone3D(t3d21);
		Cone3D t3d27 = new Cone3D( 0, 0, 0, or, oh, 0, yr, zr, blueMaterial);
		t3d27.setConeDivisions(conediv);
		t3d27.setDrawMode(DrawMode.FILL);
		dt.drawCone3D(t3d27);
		dt.drawLabel(6, 10, 0, "Y=" + yr + ", Z=" + zr + " rotation");
		
		dt.incrSequence();
		Cone3D t3d28 = new Cone3D( -6, -6, 0, or, oh, xr, 0, 0, blueMaterial);
		t3d28.setConeDivisions(conediv);
		t3d28.setDrawMode(DrawMode.FILL);
		dt.drawCone3D(t3d28);
		Cone3D t3d29 = new Cone3D( 0, -6, 0, or, oh, 0, yr, 0, blueMaterial);
		t3d29.setConeDivisions(conediv);
		t3d29.setDrawMode(DrawMode.FILL);
		dt.drawCone3D(t3d29);
		Cone3D t3d30 = new Cone3D( 6, -6, 0, or, oh, 0, 0, zr, blueMaterial);
		t3d30.setConeDivisions(conediv);
		t3d30.setDrawMode(DrawMode.FILL);
		dt.drawCone3D(t3d30);
		
		Cone3D t3d32 = new Cone3D( 0, 6, 0, or, oh, 0, 0, 0, blueMaterial);
		t3d32.setConeDivisions(conediv);
		dt.drawCone3D(t3d32);
		Cone3D t3d31 = new Cone3D( 0, 0, 0, or, oh, xr, yr, zr, blueMaterial);
		t3d31.setConeDivisions(conediv);
		dt.drawCone3D(t3d31);
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