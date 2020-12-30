package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Box3D;
import org.edisonwj.draw3d.Dodecahedron3D;
import org.edisonwj.draw3d.Draw3D;
import org.edisonwj.draw3d.Line3D;
import org.edisonwj.draw3d.Tetrahedron3D;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.text.Font;
import javafx.stage.Stage;
/**
*
* @author William Edison
* @version 1.08 January 2017
*
*/
public class TestGrid extends Application {
	private Draw3D dt;

	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestGrid");
		primaryStage.show();
	}

	private void buildData() {
		dt.setAmbientLight(true);
		dt.setPointLight(false);
		dt.setShowBoundaryCube(true);
		dt.setShowAxes(true);
		dt.setXYZRange(8,  8,  8);
		PhongMaterial m = new PhongMaterial(Color.LIGHTGRAY);
		boolean yUp = false;
		dt.setYaxisUp(yUp);
		
		for (int i = 0; i < 9; i++) {
			dt.drawGrid(i, 4, m);
			if (yUp)
				dt.drawLabel(4, 10, 0, "sector=" + i + ", div=4", Font.font("Regular", 14), Color.BLACK);
			else
				dt.drawLabel(0, 4, 10, "sector=" + i + ", div=4", Font.font("Regular", 14), Color.BLACK);
			dt.incrSequence();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
