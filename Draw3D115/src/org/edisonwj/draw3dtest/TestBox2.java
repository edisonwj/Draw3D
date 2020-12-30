package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Box3D;
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
public class TestBox2 extends Application {
	private Draw3D dt;
	double ow = 1;
	double oh = 2;
	double od = 3;
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
		primaryStage.setTitle(this.getClass().getName());
		primaryStage.show();
	}

	private void buildData() {
		PhongMaterial blueMaterial = new PhongMaterial(Color.LIGHTBLUE);

		dt.setAmbientLight(false);
		dt.setPointLight(true);
		dt.setXYZRange(8, 8, 8);

		dt.setSequencingOn();
		dt.setDrawMode(DrawMode.FILL);
		dt.setLabelFontSize(14);
		dt.setYaxisUp(yUp);

		double xloc, ylab, zlab;

		Box3D t3d20 = new Box3D( 0, 0, 0, ow, oh, od, 0, 0, 0, blueMaterial);
		dt.drawBox3D(t3d20);


		// x rotations
		dt.incrSequence();
		Box3D t3d21 = new Box3D( 0, 6, 0, ow, oh, od, 0, 0, 0, blueMaterial);
		dt.drawBox3D(t3d21);

		initRotations();
		xloc = -8;
		ylab = 9;
		zlab = 9;
		for (int i = 0; i < 5; i++) {
			Box3D t3d22 = new Box3D( xloc, 0, 0, ow, oh, od, xr, 0, 0, blueMaterial);
			dt.drawBox3D(t3d22);
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
		dt.drawBox3D(t3d21);
		initRotations();
		xloc = -8;
		ylab = 9;
		zlab = 9;
		for (int i = 0; i < 5; i++) {
			Box3D t3d23 = new Box3D( xloc, 0, 0, ow, oh, od, 0, yr, 0, blueMaterial);
			dt.drawBox3D(t3d23);
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
		dt.drawBox3D(t3d21);
		initRotations();
		xloc = -8;
		ylab = 9;
		zlab = 9;
		for (int i = 0; i < 5; i++) {
			Box3D t3d24 = new Box3D( xloc, 0, 0, ow, oh, od, 0, 0, zr, blueMaterial);
			dt.drawBox3D(t3d24);
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
		dt.drawBox3D(t3d21);
		Box3D t3d251 = new Box3D( 0, 0, 0, ow, oh, od, xr, yr, 0, blueMaterial);
		t3d251.setDrawMode(DrawMode.FILL);
		dt.drawBox3D(t3d251);
		Box3D t3d25 = new Box3D(0, -6, 0, ow, oh, od, xr, 0, 0, blueMaterial);
		t3d25.setDrawMode(DrawMode.FILL);
		dt.drawBox3D(t3d25);
		dt.drawLabel(6, 10, 0, "X=" + xr + ", Y=" + yr + " rotation");

		dt.incrSequence();
		dt.drawBox3D(t3d21);
		Box3D t3d261 = new Box3D( 0, -6, 0, ow, oh, od, xr, 0, 0, blueMaterial);
		t3d261.setDrawMode(DrawMode.FILL);
		dt.drawBox3D(t3d261);
		Box3D t3d26 = new Box3D( 0, 0, 0, ow, oh, od, xr, 0, zr, blueMaterial);
		t3d26.setDrawMode(DrawMode.FILL);
		dt.drawBox3D(t3d26);
		dt.drawLabel(6, 10, 0, "X=" + xr + ", Z=" + zr + " rotation");

		dt.incrSequence();
		dt.drawBox3D(t3d21);
		Box3D t3d271 = new Box3D( 0, -6, 0, ow, oh, od, 0, yr, 0, blueMaterial);
		t3d271.setDrawMode(DrawMode.FILL);
		dt.drawBox3D(t3d271);
		Box3D t3d27 = new Box3D( 0, 0, 0, ow, oh, od, 0, yr, zr, blueMaterial);
		t3d27.setDrawMode(DrawMode.FILL);
		dt.drawBox3D(t3d27);
		dt.drawLabel(6, 10, 0, "Y=" + yr + ", Z=" + zr + " rotation");

		dt.incrSequence();
		Box3D t3d28 = new Box3D( -8, -6, 0, ow, oh, od, xr, 0, 0, blueMaterial);
		t3d28.setDrawMode(DrawMode.FILL);
		dt.drawBox3D(t3d28);
		Box3D t3d29 = new Box3D( 0, -6, 0, ow, oh, od, 0, yr, 0, blueMaterial);
		t3d29.setDrawMode(DrawMode.FILL);
		dt.drawBox3D(t3d29);
		Box3D t3d30 = new Box3D( 8, -6, 0, ow, oh, od, 0, 0, zr, blueMaterial);
		t3d30.setDrawMode(DrawMode.FILL);
		dt.drawBox3D(t3d30);

		Box3D t3d32 = new Box3D( 0, 6, 0, ow, oh, od, 0, 0, 0, blueMaterial);
		dt.drawBox3D(t3d32);
		Box3D t3d31 = new Box3D( 0, 0, 0, ow, oh, od, xr, yr, zr, blueMaterial);
		dt.drawBox3D(t3d31);
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
