package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Draw3D;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
/**
*
* @author William Edison
* @version 1.05 February 2016
* 
*/
public class TestFont extends Application {
	private Draw3D dt;
		
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestFont");
		primaryStage.show();
	}
	
	private void buildData() {
			    
		dt.setZaxisUp(true);
		dt.setShowBoundaryCube(false);
		
		dt.setLabelFontSize(10);
		dt.drawLabel(4, 6, -2, 0, 180, 16, "Test Text Regular 10");
		
		dt.setLabelFontSize(12);
		dt.drawLabel(4, 6, -4, 0, 180, 16, "Test Text Regular 12");
		
		dt.setLabelFontSize(14);
		dt.drawLabel(4, 6, -6, 0, 180, 16, "Test Text Regular 14");
		
		dt.setLabelFontSize(16);
		dt.drawLabel(4, 6, -8, 0, 180, 16, "Test Text Regular 16");
		
		dt.setLabelFont(Font.font ("Regular", 16));
		dt.drawLabel(4, -11, -4, 0, 180, 16, "Test Text Regular 16");
		
		dt.setLabelFont(Font.font ("Arial", 16));
		dt.drawLabel(4, -11, -6, 0, 180, 16, "Test Text Arial 16");
		
		dt.setLabelFont(Font.font ("Verdana", 16));
		dt.drawLabel(4, -11, -8, 0, 180, 16 ,"Test Text Verdana 16");
		
		dt.setLabelFont(Font.font ("Times New Roman", 16));
		dt.drawLabel(4, -11, -10, 0, 180, 16, "Test Text Times New Roman 16");

	}
	
	public static void main(String[] args) {
		launch(args);
	}
}