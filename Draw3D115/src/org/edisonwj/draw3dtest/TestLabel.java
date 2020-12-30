    package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Draw3D;
import org.edisonwj.draw3d.Text3D;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;
/**
*
* @author William Edison
* @version 1.08 January 2017
*
*/
public class TestLabel extends Application {
	private Draw3D dt;

	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestLabel");
		primaryStage.show();
	}

	private void buildData() {

		dt.setAmbientLight(true);
		dt.setPointLight(false);
		dt.setCumulate(false);

		double[] v1 = {2,  2, 2};
		double[] v2 = {-2, 2, 2};

		dt.setSequencingOn();
		dt.setZaxisUp(true);
		dt.setDrawColor(Color.BLUE);
		dt.setLabelFont(Font.font("Regular", FontPosture.ITALIC,  14));
		dt.drawLabel(0, -10, 12, "#0 Z Up");
		dt.setDrawColor(Color.CORAL);
		dt.drawVector(v1);
		dt.drawVector(v2);
		dt.setLabelFont(Font.font("Regular", FontPosture.ITALIC,  12));
		dt.drawLabel(0, 1.6, 0.2, "Base Vectors");

		dt.incrSequence();
		dt.setZaxisUp(true);
		dt.setDrawColor(Color.BLUE);
		dt.setLabelFont(Font.font("Regular", FontPosture.ITALIC,  14));
		dt.drawLabel(0, -10, 12, "#1 Z Up");
		Text3D lab3D1 = new Text3D(new Point3D(2,8,2), 0, 180, 20, "Test1");
		lab3D1.setLabelFont(Font.font("Regular", 12));
		dt.drawLabel3D(lab3D1);
		Text3D lab3D2 = new Text3D(new Point3D(2,8,4), 0, 180, 20, "Test2");
		lab3D2.setLabelFont(Font.font("Regular", 14));
		dt.drawLabel3D(lab3D2);
		Text3D lab3D3 = new Text3D(new Point3D(2,8,6), 0, 180, 20, "Test3");
		lab3D3.setLabelFont(Font.font("Regular", 16));
		dt.drawLabel3D(lab3D3);

		dt.incrSequence();
		dt.setYaxisUp(true);
		dt.setDrawColor(Color.CRIMSON);
		dt.setLabelFont(Font.font("Regular", FontPosture.ITALIC,  14));
		dt.drawLabel(-10, 12, 0, "#1 Y Up");
		dt.setDrawColor(Color.CORAL);
		dt.drawVector(v1);
		dt.drawVector(v2);
		dt.setLabelFont(Font.font("Regular", FontPosture.ITALIC,  12));
		dt.drawLabel(1.6, 0.2, 0, "Base Vectors");

		dt.incrSequence();
		dt.setYaxisUp(true);
		dt.setDrawColor(Color.CRIMSON);
		dt.setLabelFont(Font.font("Regular", FontPosture.ITALIC,  14));
		dt.drawLabel(-10, 12, 0, "#1 Y Up");
		Text3D lab3Dy1 = new Text3D(new Point3D(8,2,2), 180, 20, 0, "Test1");
		lab3Dy1.setLabelFont(Font.font("Regular", 12));
		dt.drawLabel3D(lab3Dy1);
		Text3D lab3Dy2 = new Text3D(new Point3D(8,4,2), 180, 20, 0, "Test2");
		lab3Dy2.setLabelFont(Font.font("Regular", 14));
		dt.drawLabel3D(lab3Dy2);
		Text3D lab3Dy3 = new Text3D(new Point3D(8,6,2), 180, 20, 0, "Test3");
		lab3Dy3.setLabelFont(Font.font("Regular", 16));
		dt.drawLabel3D(lab3Dy3);
	}

	public static void main(String[] args) {
		launch(args);
	}
}