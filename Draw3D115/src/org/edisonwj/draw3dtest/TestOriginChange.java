package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Draw3D;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
*
* @author William Edison
* @version 1.01 August 2015
* 
*/
public class TestOriginChange extends Application {
	private Draw3D dt;

	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("Test Origin Change");
		primaryStage.show();
	}

	private void buildData() {

		dt.setAmbientLight(true);
		dt.setPointLight(false);
		dt.setCumulate(false);
		
		dt.setDrawColor(Color.RED);
        dt.drawVector(5, 5, 5);
        dt.drawVector(5,-5,5);
// --------------------------------------------------------------      
        dt.incrSequence();
        dt.setOriginView( 200,  200,  0);
		dt.setDrawColor(Color.RED);
        dt.drawVector(5, 5, 5);
        dt.drawVector(5,-5, 5);

        dt.incrSequence();
        dt.setDrawColor(Color.BLUE);
        dt.drawVector(-5,  5, 5);
        dt.drawVector(-5, -5, 5);
// --------------------------------------------------------------        
        dt.incrSequence();
        dt.setOriginView(-200, 200,  0);
		dt.setDrawColor(Color.RED);
        dt.drawVector(5, 5, 5);
        dt.drawVector(5,-5,5);
// -------------------------------------------------------------- 
        dt.incrSequence();
        dt.setCumulate(true);
        dt.setOriginView(-200, -200,  0);
		dt.setDrawColor(Color.RED);
        dt.drawVector(5, 5, 5);
        dt.drawVector(5,-5,5);
        
        dt.incrSequence();
        dt.setDrawColor(Color.GREEN);
        dt.drawVector(-5,  5, -5);
        dt.drawVector(-5, -5, -5);
// --------------------------------------------------------------         
        dt.incrSequence();
        dt.setOriginView( 200, -200,  0);
		dt.setDrawColor(Color.RED);
        dt.drawVector(5, 5, 5);
        dt.drawVector(5,-5,5);

	}
	
	public static void main(String[] args) {
		launch(args);
	}
}