package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Draw3D;
import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
*
* @author William Edison
* @version 1.00 July 2015
* 
*/
public class TestSizeDivisions extends Application {
	private Draw3D dt;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestSizeDivisions");
		primaryStage.show();
	}
	
	private void buildData() {
		dt.setAmbientLight(false);
		dt.setPointLight(true);
		dt.setCumulate(false);

		dt.setSequencingOn();
		dt.drawVector(5, 0, 5);
		dt.drawVector(-5, 0, 5);
        dt.setDrawColor(Color.BLUE);
        dt.drawLine(1, 0, 0, 6, 0, 5);
        dt.drawLine(-1, 0, 0, -6, 0, 5); 
        dt.setDrawColor(Color.GREEN);
        dt.drawArrow(2, 0, 0, 7, 0, 5);
        dt.drawArrow(-2, 0, 0, -7, 0, 5);

        dt.incrSequence();
        dt.setDrawColor(Color.RED);
        dt.drawVector(5, 0, 5);
        dt.setVectorRadius(1);
		dt.drawVector(-5, 0, 5);
        
        dt.incrSequence();
        dt.setDrawColor(Color.GREEN);
		dt.drawLine(1, 0, 0, 6, 0, 5);
		dt.setLineRadius(1);
		dt.drawLine(-1, 0, 0, -6, 0, 5);

		dt.incrSequence();
		dt.setVectorRadius(1);
		dt.setDrawColor(Color.RED);
		dt.drawVector(5, 0, 5);
		dt.drawVector(-5, 0, 5);
        dt.setDrawColor(Color.BLUE);
        dt.setLineRadius(1);
        dt.drawLine(1, 0, 0, 6, 0, 5);
        dt.drawLine(-1, 0, 0, -6, 0, 5); 
        dt.setDrawColor(Color.GREEN);
		dt.setArrowRadius(1);
        dt.drawArrow(2, 0, 0, 7, 0, 5);
        dt.drawArrow(-2, 0, 0, -7, 0, 5);
 
        // Test point sizes       
        dt.incrSequence();
        dt.setDrawColor(Color.GREEN);
        dt.drawPoint(1,1,1);
        dt.setPointSize(2);
        dt.drawPoint(2, 2, 2);
        dt.setPointSize(4);
        dt.drawPoint(4, 4, 4);
        
        dt.setDrawColor(Color.FIREBRICK);
        dt.setPointSize(4);
        dt.drawPoint3D(new Point3D(-4,4,4));
        dt.setPointSize(6);
        dt.setDrawColor(Color.SIENNA);
        dt.drawPoint3D(new Point3D(-4,-4,4));
       
        // Test sphere divisions
        dt.incrSequence();
        dt.setDrawColor(Color.MAGENTA);
        dt.drawSphere(5,5,5,2);
        dt.setSphereDivisions(4);
        dt.drawSphere(-5,  5,  5,  2);
        dt.setSphereDivisions(8);
        dt.drawSphere(-5,-5,5,2);
        
        // Test cone divisions
        dt.incrSequence();
        dt.setDrawColor(Color.CORNFLOWERBLUE);
        dt.drawCone(5,5,5,2,4);
        dt.setConeDivisions(4);
        dt.drawCone(-5,5,5, 2,4);
        dt.setConeDivisions(8);
        dt.drawCone(-5,-5,5,2,4);
        
        // Test oval divisions
        dt.incrSequence();
        dt.setDrawColor(Color.SALMON);
        dt.drawOval(5,5,5,2,4);
        dt.setOvalDivisions(4);
        dt.drawOval(-5,5,5, 2,4);
        dt.setOvalDivisions(8);
        dt.drawOval(-5,-5,5,2,4);
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}