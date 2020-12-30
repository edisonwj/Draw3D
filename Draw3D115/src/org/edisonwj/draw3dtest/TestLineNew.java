package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Draw3D;
import org.edisonwj.draw3d.Line3D;
import org.edisonwj.draw3d.Tetrahedron3D;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
/**
*
* @author William Edison
* @version 1.08 January 2017
*
*/
public class TestLineNew extends Application {
	private Draw3D dt;

	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestLine");
		primaryStage.show();
	}

	private void buildData() {
		dt.setAmbientLight(true);
		dt.setPointLight(false);
		dt.setXYZRange(8, 8, 8);
		dt.setZaxisUp(true);
		dt.setLineRadius(2);
		dt.setShowAxes(false);

		// xz plane
		dt.setSequencingOn();

	

		Line3D ln10 = new Line3D(0,-8,2, 0,-8,4);
		Line3D ln11 = new Line3D(0,-6,2, 0,-6,4);
		Line3D ln12 = new Line3D(0,-4,2, 0,-4,4);
		Line3D ln13 = new Line3D(0,-2,2, 0,-2,4);
		Line3D ln14 = new Line3D(0,2,2, 0,2,4);
		Line3D ln15 = new Line3D(0,4,2, 0,4,4);
		Line3D ln16 = new Line3D(0,6,2, 0,6,4);
		Line3D ln17 = new Line3D(0,8,2, 0,8,4);
		
		
		double rad = .75;
		ln10.setLineRadius(rad);
		ln11.setLineRadius(rad);
		ln12.setLineRadius(rad);
		ln13.setLineRadius(rad);
		ln14.setLineRadius(rad);
		ln15.setLineRadius(rad);
		ln16.setLineRadius(rad);
		ln17.setLineRadius(rad);
		
		ln10.setMaterial(new PhongMaterial(Color.BLUE));
		ln11.setMaterial(new PhongMaterial(Color.STEELBLUE));
		ln12.setMaterial(new PhongMaterial(Color.GREEN));
		ln13.setMaterial(new PhongMaterial(Color.DARKGREEN));
		ln14.setMaterial(new PhongMaterial(Color.RED));
		ln15.setMaterial(new PhongMaterial(Color.CRIMSON));
		ln16.setMaterial(new PhongMaterial(Color.MAGENTA));
		ln17.setMaterial(new PhongMaterial(Color.SALMON));
		
		dt.drawLine3D(ln10);
		dt.drawLine3D(ln11);
		dt.drawLine3D(ln12);
		dt.drawLine3D(ln13);
		dt.drawLine3D(ln14);
		dt.drawLine3D(ln15);
		dt.drawLine3D(ln16);
		dt.drawLine3D(ln17);
	}

	public static void main(String[] args) {
		launch(args);
	}
}