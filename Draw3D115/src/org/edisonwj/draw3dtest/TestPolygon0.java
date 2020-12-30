package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Arrow3D;
import org.edisonwj.draw3d.Draw3D;
import org.edisonwj.draw3d.Line3D;
import org.edisonwj.draw3d.Polygon3D;
import org.edisonwj.draw3d.Sphere3D;
import org.edisonwj.draw3d.Triangle3D;
import org.edisonwj.draw3d.Vector3D;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.stage.Stage;
/**
*
* @author William Edison
* @version 1.08 January 2017
*
*/
public class TestPolygon0 extends Application {
	private Draw3D dt;

	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestPolygon0");
		primaryStage.show();
	}

	private void buildData() {

		dt.setCumulate(false);
		dt.setAmbientLight(true);
		dt.setPointLight(false);

		dt.setShowBoundaryCube(true);

		Point3D[] p = { new Point3D( 3, 1, 0),
						new Point3D( 7, 1, 0),
						new Point3D( 9, 6, 0),
						new Point3D( 8, 9, 0),
						new Point3D( 4, 7, 0),
						new Point3D( 1, 3, 0) };

		for (int i = 0; i < p.length; i++) {
			dt.drawSphere3D(new Sphere3D(p[i], .2));
		}

		Polygon3D p3d = new Polygon3D(p, new PhongMaterial(Color.ANTIQUEWHITE));
		dt.drawPolygon3D(p3d);

		dt.setDrawColor(Color.ALICEBLUE);
		dt.setYaxisUp(true);

		dt.incrSequence();
		dt.drawPolygon(p, true, false, false);

		dt.incrSequence();
		dt.drawPolygon(p, false, true, false);

		dt.incrSequence();
		dt.drawPolygon(p, false, false, true);

		dt.incrSequence();
		dt.drawPolygon(p, true, true, true);

	}

	public static void main(String[] args) {
		launch(args);
	}
}