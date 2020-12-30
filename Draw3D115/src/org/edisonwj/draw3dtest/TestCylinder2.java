package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Cylinder3D;
import org.edisonwj.draw3d.Draw3D;
import org.edisonwj.draw3d.Line3D;
import org.edisonwj.draw3d.Rand;
import org.edisonwj.draw3d.Octahedron3D;
import org.edisonwj.draw3d.Sphere3D;
import org.edisonwj.draw3d.Tetrahedron3D;
import org.edisonwj.draw3d.Text3D;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.scene.text.Font;
import javafx.stage.Stage;
/**
*
* @author William Edison
* @version 1.08 January 2017
*
*/
public class TestCylinder2 extends Application {
	private Draw3D dt;
	double or = 1;
	double oh = 2;
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
		primaryStage.setTitle("TestCylinder2");
		primaryStage.show();
	}

	private void buildData() {
		PhongMaterial blueMaterial = new PhongMaterial(Color.ANTIQUEWHITE);
		PhongMaterial blackMaterial = new PhongMaterial(Color.BLACK);
        PhongMaterial lightgrayMaterial = new PhongMaterial(Color.LIGHTGRAY);

		dt.setAmbientLight(true);
		dt.setPointLight(false);
		dt.setDrawColor(Color.BLACK);
		dt.setLabelFontSize(14);
		dt.setXYZRange(8, 8, 8);
		Image i24 = new Image("/Resources/mercator.jpg");
		dt.setTexture(i24);
		int cyldiv = 8;
		dt.setYaxisUp(yUp);

		dt.setSequencingOn();
		double xloc, ylab, zlab;

		Cylinder3D t3d21 = new Cylinder3D( 0, 6, 0, or, oh, 0, 0, 0);
		t3d21.setCylinderDivisions(cyldiv);
		dt.drawCylinder3D(t3d21);
		dt.setDrawMode(DrawMode.FILL);

		// x rotations
		dt.drawCylinder3D(t3d21);
		initRotations();
		xloc = -8;
		ylab = 9;
		zlab = 9;
		for (int i = 0; i < 5; i++) {
			Cylinder3D t3d22 = new Cylinder3D( xloc, 0, 0, or, oh, xr, 0, 0);
			t3d22.setCylinderDivisions(cyldiv);
			dt.drawCylinder3D(t3d22);
			if (yUp) {
				dt.drawLabel(6, ylab, 0, "X=" + xr + " rotation", new Font("Regular", 14), Color.BLACK);
				xloc += 4;
				ylab -= 2;
			}
			else {
				dt.drawLabel(0, 6, zlab, "X=" + xr + " rotation", new Font("Regular", 14), Color.BLACK);
				xloc += 4;
				zlab -= 2;
			}
			xr += 45;
		}

		// y rotations
		dt.incrSequence();
		dt.drawCylinder3D(t3d21);
		initRotations();
		xloc = -8;
		ylab = 9;
		zlab = 9;
		for (int i = 0; i < 5; i++) {
			Cylinder3D t3d23 = new Cylinder3D( xloc, 0, 0, or, oh, 0, yr, 0);
			t3d23.setCylinderDivisions(cyldiv);
			dt.drawCylinder3D(t3d23);
			if (yUp) {
				dt.drawLabel(6, ylab, 0, "Y=" + yr + " rotation", new Font("Regular", 14), Color.BLACK);;
				xloc += 4;
				ylab -= 2;
			}
			else {
				dt.drawLabel(0, 6, zlab, "Y=" + yr + " rotation", new Font("Regular", 14), Color.BLACK);
				xloc += 4;
				zlab -= 2;
			}
			yr += 45;
		}

		// z rotations
		dt.incrSequence();
		dt.drawCylinder3D(t3d21);
		initRotations();
		xloc = -8;
		ylab = 9;
		zlab = 9;
		for (int i = 0; i < 5; i++) {
			Cylinder3D t3d24 = new Cylinder3D( xloc, 0, 0, or, oh, 0, 0, zr);
			t3d24.setCylinderDivisions(cyldiv);
			dt.drawCylinder3D(t3d24);
			if (yUp) {
				dt.drawLabel(6, ylab, 0, "Z=" + zr + " rotation", new Font("Regular", 14), Color.BLACK);
				xloc += 4;
				ylab -= 2;
			}
			else {
				dt.drawLabel(0, 6, zlab, "Z=" + zr + " rotation", new Font("Regular", 14), Color.BLACK);
				xloc += 4;
				zlab -= 2;
			}
			zr += 45;
		}

		dt.incrSequence();
		initRotations();
		dt.drawCylinder3D(t3d21);
		Cylinder3D t3d25 = new Cylinder3D( 0, 0, 0, or, oh, xr, yr, 0);
		t3d25.setCylinderDivisions(cyldiv);
		t3d25.setDrawMode(DrawMode.FILL);
		dt.drawCylinder3D(t3d25);
		dt.drawLabel(6, 10, 0, "X=" + xr + ", Y=" + yr + " rotation", new Font("Regular", 14), Color.BLACK);

		dt.incrSequence();
		dt.drawCylinder3D(t3d21);
		Cylinder3D t3d26 = new Cylinder3D( 0, 0, 0, or, oh, xr, 0, zr);
		t3d26.setCylinderDivisions(cyldiv);
		t3d26.setDrawMode(DrawMode.FILL);
		dt.drawCylinder3D(t3d26);
		dt.drawLabel(6, 10, 0, "X=" + xr + ", Z=" + zr + " rotation", new Font("Regular", 14), Color.BLACK);

		dt.incrSequence();
		dt.drawCylinder3D(t3d21);
		Cylinder3D t3d27 = new Cylinder3D( 0, 0, 0, or, oh, 0, yr, zr);
		t3d27.setCylinderDivisions(cyldiv);
		t3d27.setDrawMode(DrawMode.FILL);
		dt.drawCylinder3D(t3d27);
		dt.drawLabel(6, 10, 0, "Y=" + yr + ", Z=" + zr + " rotation", new Font("Regular", 14), Color.BLACK);

		dt.incrSequence();
		Cylinder3D t3d28 = new Cylinder3D( -6, -6, 0, or, oh, xr, 0, 0);
		t3d28.setCylinderDivisions(cyldiv);
		t3d28.setDrawMode(DrawMode.FILL);
		dt.drawCylinder3D(t3d28);
		Cylinder3D t3d29 = new Cylinder3D( 0, -6, 0, or, oh, 0, yr, 0);
		t3d29.setCylinderDivisions(cyldiv);
		t3d29.setDrawMode(DrawMode.FILL);
		dt.drawCylinder3D(t3d29);
		Cylinder3D t3d30 = new Cylinder3D( 6, -6, 0, or, oh, 0, 0, zr);
		t3d30.setCylinderDivisions(cyldiv);
		t3d30.setDrawMode(DrawMode.FILL);
		dt.drawCylinder3D(t3d30);

		Cylinder3D t3d32 = new Cylinder3D( 0, 6, 0, or, oh, 0, 0, 0);
		t3d32.setCylinderDivisions(cyldiv);
		dt.drawCylinder3D(t3d32);
		Cylinder3D t3d31 = new Cylinder3D( 0, 0, 0, or, oh, xr, yr, zr);
		t3d31.setCylinderDivisions(cyldiv);
		dt.drawCylinder3D(t3d31);
		dt.drawLabel(6, 10, 0, "X=" + xr + ", Y=" + yr + ", Z= " + zr + " rotation", new Font("Regular", 14), Color.BLACK);
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