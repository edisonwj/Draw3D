package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Draw3D;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
*
* @author William Edison
* @version 1.09 May 2017
* 
*/
public class TestMainOptions extends Application {
	private Draw3D dt;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestMainOptions");
		primaryStage.show();
	}
	
	private void buildData() {
		dt.setAmbientLight(false);
		dt.setPointLight(true);
		
		dt.setSequencingOn();
		dt.setCumulate(false);
		dt.openFile(null, "desert.jpg");
		dt.openFile("C:/Users/Bill/workspace_javafx_mars/imageprocessingapp500/src/resources", "flower.png");
		dt.openFile("line check all.txt");
			
		dt.incrSequence();
		dt.loadData("line check all.txt");
		
		dt.incrSequence();
		dt.buildTest();
		dt.saveData("testSaveData.txt");
		dt.saveData("c:/users/bill/documents/draw3dtest", "testSaveDataX.txt");
		
		dt.incrSequence();
		dt.loadData("testSaveData.txt");
		
		dt.incrSequence();
		dt.loadData("c:/users/bill/documents/draw3dtest", "testSaveDataX.txt");
		
		dt.incrSequence();
		dt.loadData("line check 1.txt");		// Find in Resources directory
		
//		dt.incrSequence();
//		dt.loadData("C:/Users/Bill/workspace_javafx_mars/draw3d108/src/resources", "line check 1.txt");
		
		dt.incrSequence();
		dt.buildTest();
		dt.saveScene("bmp", "testSaveScene.bmp");
		dt.saveScene("gif", "testSaveScene.gif");
		dt.saveScene("jpg", "testSaveScene.jpg");
		dt.saveScene("png", "testSaveScene.png");
		dt.saveScene("bmp", "c:/users/bill/documents/draw3dtest", "testSaveSceneX.bmp");
		
		dt.incrSequence();
		dt.openFile("testSaveScene.bmp");
		dt.openFile("testSaveScene.gif");
		dt.openFile("testSaveScene.jpg");
		dt.openFile("testSaveScene.png");
		dt.openFile("c:/users/bill/documents/draw3dtest", "testsaveSceneX.bmp");
		
		dt.incrSequence();
		dt.buildTest();
		dt.saveSubScene("bmp", "testSaveSubScene.bmp");
		dt.saveSubScene("gif", "testSaveSubScene.gif");
		dt.saveSubScene("jpg", "testSaveSubScene.jpg");
		dt.saveSubScene("png", "testSaveSubScene.png");
		dt.saveSubScene("bmp", "c:/users/bill/documents/draw3dtest", "testSaveSubSceneX.bmp");
		
		dt.incrSequence();
		dt.openFile("testSaveSubScene.bmp");
		dt.openFile("testSaveSubScene.gif");
		dt.openFile("testSaveSubScene.jpg");
		dt.openFile("testSaveSubScene.png");
		dt.openFile("c:/users/bill/documents/draw3dtest", "testSaveSubSceneX.bmp");
	
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}