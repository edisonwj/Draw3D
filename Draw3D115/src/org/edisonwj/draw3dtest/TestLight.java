package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Draw3D;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.DrawMode;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
/**
*
* @author William Edison
* @version 1.04 July 2016
* 
*/

public class TestLight extends Application {

	private Draw3D dt;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("ALightTest");
		primaryStage.show();
	}
	
	private void buildData() {
		
		// Illustrate controls
		// #0
		System.out.println("\nSetup View 0");
		dt.setSequencingOn();
		dt.setCumulate(false);
		dt.setAmbientLight(false);
		dt.setPointLight(true);
		dt.setDrawColor(Color.CORNFLOWERBLUE);
		dt.setDrawMode(DrawMode.FILL);
		dt.drawCylinder(8, 2, 4, 2, 3);
		dt.setDrawColor(Color.BLUE);
		dt.setLabelFont(Font.font("Regular", FontPosture.ITALIC,  14));
		dt.drawLabel(2, -10, 12, 0, 180, 18, "#0 Title 0");
		
		// Draw cumulative set of vectors
		double[] v1 = {2,  2, 2};
		double[] v2 = {-2, 2, 2};
		double[] v3 = add(v1, v2);
		
		// #1
		System.out.println("\nSetup View 1");
		dt.incrSequence();
		dt.setCumulate(false);
		dt.setAmbientLight(true);
		dt.setPointLight(false);
		dt.setDrawColor(Color.BLUE);
		dt.setLabelFont(Font.font("Regular", FontPosture.ITALIC,  14));
		dt.drawLabel(2, -10, 12, 0, 180, 18, "#1-4 Cumulative Display of Various Vectors");
		dt.setLabelFont(Font.font("Regular", 10));
		dt.setDrawColor(Color.CORAL);
		dt.drawVector(v1);
		dt.drawVector(v2);
		dt.drawLabel(0, 1.5, 0.5, 0, 180, 18, "Base Vectors");
		
		// #2
		System.out.println("\nSetup View 2");
		dt.incrSequence();
		dt.setCumulate(true);
		dt.setDrawColor(Color.RED);
		dt.drawVector(v3);
		dt.drawLabel(v3[0], v3[1], v3[2], 0, 180, 18, "Vector Addition");
		
		// #3
		System.out.println("\nSetup View 3");
		dt.incrSequence();
		dt.setCumulate(false);
		dt.setAmbientLight(false);
		dt.setPointLight(true);
		dt.setDrawColor(Color.GREEN);
		dt.drawCylinder(8, 2, 4, 1, 2);

	}
	public double[] add(double[] v1, double[] v2) {
		double[] v3 = new double[3];
		v3[0] = v1[0] + v2[0];
		v3[1] = v1[1] + v2[1];
		v3[2] = v1[2] + v2[2];
		return v3;
	}
	
	public double[] crossProduct(double[] v1, double[] v2) {
		double[] v3 = new double[3];
		v3[0] = v1[1] * v2[2] - v1[2] * v2[1];
		v3[1] = v1[2] * v2[0] - v1[0] * v2[2];
		v3[2] = v1[0] * v2[1] - v1[1] * v2[0];      
		return v3;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}