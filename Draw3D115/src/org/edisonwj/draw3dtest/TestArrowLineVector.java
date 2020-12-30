package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Draw3D;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
*
* @author William Edison
* @version 1.04 February 2016
* 
*/
public class TestArrowLineVector extends Application {
	private Draw3D dt;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestArrowLineVector");
		primaryStage.show();
	}
	
	private void buildData() {
		dt.setAmbientLight(true);
		dt.setPointLight(false);

		dt.setDrawColor(Color.RED);
		
        dt.drawVector(5, 0, 5);
        dt.drawLine(1, 0, 0, 6, 0, 5);
        dt.drawArrow(2, 0, 0, 7, 0, 5);
        dt.drawVector(5, 0, -5);
        dt.drawLine(1, 0, 0, 6, 0, -5);
        dt.drawArrow(2, 0, 0, 7, 0, -5);  

        dt.drawVector(-5, 0, 5);
        dt.drawLine(-1, 0, 0, -6, 0, 5);
        dt.drawArrow(-2, 0, 0, -7, 0, 5);
        dt.drawVector(-5, 0, -5);
        dt.drawLine(-1, 0, 0, -6, 0, -5);
        dt.drawArrow(-2, 0, 0, -7, 0, -5);
   
        dt.setDrawColor(Color.BLUE);
        
        dt.drawVector(0, 5, 5);
        dt.drawLine(0, 1, 0, 0, 6, 5);
        dt.drawArrow(0, 2, 0, 0, 7, 5);    
        dt.drawVector(0, 5, -5);
        dt.drawLine(0, 1, 0, 0, 6, -5);
        dt.drawArrow(0, 2, 0, 0, 7, -5);

	    dt.drawVector(0, -5, 5);
	    dt.drawLine(0, -1, 0, 0, -6, 5);
	    dt.drawArrow(0, -2, 0, 0, -7, 5);    
	    dt.drawVector(0, -5, -5);
	    dt.drawLine(0, -1, 0, 0, -6, -5);
	    dt.drawArrow(0, -2, 0, 0, -7, -5);

		dt.setDrawColor(Color.MAGENTA);
		
	    dt.drawVector(5, 5, 5);
	    dt.drawLine(0, 0, 1, 5, 5, 6);
	    dt.drawArrow(0, 0, 2, 5, 5, 7);
	    dt.drawVector(-5, -5, 5);
	    dt.drawLine(0, 0, 1, -5, -5, 6);
	    dt.drawArrow(0, 0, 2, -5, -5, 7);
	    
	    dt.drawVector(5, 5, -5);
	    dt.drawLine(0, 0, -1, 5, 5, -6);
	    dt.drawArrow(0, 0, -2, 5, 5, -7);
	    dt.drawVector(-5, -5, -5);
	    dt.drawLine(0, 0, -1, -5, -5, -6);
	    dt.drawArrow(0, 0, -2, -5, -5, -7);
    
	    dt.setDrawColor(Color.SALMON);
	    
       	dt.drawArrow(-6, 6, 6, 6, 6, 6);
       	dt.drawArrow(6, 6, 6, 6, -6, 6);
       	dt.drawArrow(6, -6, 6, -6, -6, 6);
       	dt.drawArrow(-6, -6, 6, -6, 6, 6);
       	
       	dt.drawArrow(-6, 6, -6, 6, 6, -6);
       	dt.drawArrow(6, 6, -6, 6, -6, -6);
       	dt.drawArrow(6, -6, -6, -6, -6, -6);
       	dt.drawArrow(-6, -6, -6, -6, 6, -6);

	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}