package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Draw3D;
import org.edisonwj.draw3d.Line3D;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.stage.Stage;
/**
*
* @author William Edison
* @version 1.05 February 2016
* 
*/
public class TestAxesChangeALV extends Application {
	private Draw3D dt;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestAxesChangeALV");
		primaryStage.show();
	}
	
	private void buildData() {
		dt.setAmbientLight(true);
		dt.setPointLight(false);
		dt.setCumulate(false);
        dt.setArrowRadius(1);
        dt.setLineRadius(1);
        dt.setVectorRadius(1);
		
        dt.setSequencingOn();
        dt.setZaxisUp(true);
        dt.setDrawColor(Color.CRIMSON);
        dt.drawArrow(4, 0, 0, 9, 0, 5);
        dt.drawLine(2, 0, 0, 7, 0, 5);
        dt.drawVector(5, 0, 5);
        dt.setDrawColor(Color.LAWNGREEN);
        dt.drawArrow(0, 4, 0, 0, 9, 5);
        dt.drawLine(0, 2, 0, 0, 7, 5);
        dt.drawVector(0, 5, 5);
        dt.setDrawColor(Color.CORNFLOWERBLUE);
        dt.drawArrow(0, 0, 4, -5, 0, 9);
        dt.drawLine(0, 0, 2, -5, 0, 7);
        dt.drawVector(-5, 0, 5);
        
		dt.incrSequence();
        dt.setZaxisUp(true);
        dt.setDrawColor(Color.CRIMSON);
        dt.drawArrow(0, 0, 0, 5, 0, 0);
        dt.setDrawColor(Color.LAWNGREEN);
        dt.drawArrow(0, 0, 0, 0, 5, 0);
        dt.setDrawColor(Color.CORNFLOWERBLUE);
        dt.drawArrow(0, 0, 0, 0, 0, 5);
        dt.setDrawColor(Color.GOLDENROD);
        dt.drawArrow(0, 0, 0, 5, 5, 5);
        dt.setDrawColor(Color.BLACK);
        dt.drawArrow(2, 0, 2, 7, 0, 7);
        dt.setDrawColor(Color.TEAL);
        dt.drawArrow(-6, 3, -4, 5, 7, -2);
        
		dt.incrSequence();
        dt.setYaxisUp(true);
        dt.setDrawColor(Color.CRIMSON);
        dt.drawLine(0, 0, 0, 5, 0, 0);
        dt.setDrawColor(Color.LAWNGREEN);
        dt.drawLine(0, 0, 0, 0, 5, 0);
        dt.setDrawColor(Color.CORNFLOWERBLUE);
        dt.drawLine(0, 0, 0, 0, 0, 5);
        dt.setDrawColor(Color.GOLDENROD);
        dt.drawLine(0, 0, 0, 5, 5, 5);
        
		dt.incrSequence();
		dt.setYaxisUp(true);
        dt.setDrawColor(Color.CRIMSON);
        dt.drawVector(5, 0, 0);
        dt.setDrawColor(Color.LAWNGREEN);
        dt.drawVector(0, 5, 0);
        dt.setDrawColor(Color.CORNFLOWERBLUE);
        dt.drawVector(0, 0, 5);
        dt.setDrawColor(Color.GOLDENROD);
        dt.drawVector(5, 5, 5);
        
        dt.incrSequence();
		dt.setZaxisUp(true);
		dt.setDrawColor(Color.CRIMSON);
        dt.drawArrow(0, 0, 0, 5, 0, 0);
        dt.setDrawColor(Color.LAWNGREEN);
        dt.drawArrow(0, 0, 0, 0, 5, 0);
        dt.setDrawColor(Color.CORNFLOWERBLUE);
        dt.drawArrow(0, 0, 0, 0, 0, 5);
        dt.setDrawColor(Color.GOLDENROD);
        dt.drawArrow(0, 0, 0, 5, 5, 5);

	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}