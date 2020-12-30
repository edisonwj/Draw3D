package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Arrow3D;
import org.edisonwj.draw3d.Draw3D;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.stage.Stage;
/**
*
* @author William Edison
* @version 1.08 January 2017
* 
*/
public class TestViewSettings extends Application {
	private Draw3D dt;
		
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestView Settings");
		primaryStage.show();
	}
	
	private void buildData() {
		
		dt.setCumulate(false);
		dt.setLabelFontSize(12);
			    
		Arrow3D a = new Arrow3D(1, 1, 1, 5, 5, 5, new PhongMaterial(Color.RED));
		
		// show boundary cube
		System.out.println("Test show boundary cube setting");
		dt.setSequencingOn();
		dt.setShowBoundaryCube(true);
		dt.drawLabel(8, -6, -8, "Show boundary cube");
		dt.drawArrow3D(a);
		
		dt.incrSequence();
		dt.setShowBoundaryCube(false);
		dt.drawLabel(8, -6, -8, "Not show boundary cube");
		dt.drawArrow3D(a);
		
		dt.incrSequence();
		dt.setShowBoundaryCube(true);
		dt.drawLabel(8, -6, -8, "Show boundary cube");
		dt.drawArrow3D(a);
	
		// show axes
		System.out.println("Test show axes setting");
		dt.incrSequence();;
		dt.setShowAxes(true);
		dt.drawLabel(8, -6, -8, "Show axes");
		dt.drawArrow3D(a);
		
		dt.incrSequence();
		dt.setShowAxes(false);
		dt.drawLabel(8, -6, -8, "Not show axes");
		dt.drawArrow3D(a);
		
		dt.incrSequence();
		dt.setShowAxes(true);
		dt.drawLabel(8, -6, -8, "Show axes");  
		dt.drawArrow3D(a);
		
		// show tick marks
		System.out.println("Test show tick marks setting");
		dt.incrSequence();;
		dt.setShowTickMarks(true);
		dt.drawLabel(8, -6, -8, "Show tick marks");
		dt.drawArrow3D(a);
		
		dt.incrSequence();
		dt.setShowTickMarks(false);
		dt.drawLabel(8, -6, -8, "Not show tick marks");
		dt.drawArrow3D(a);
		
		dt.incrSequence();
		dt.setShowTickMarks(true);
		dt.drawLabel(8, -6, -8, "Show tick marks");
		dt.drawArrow3D(a);
		
		dt.incrSequence();
		dt.setShowAxes(false);
		dt.setShowTickMarks(true);
		dt.drawLabel(8, -6, -8, "Show tick marks and axes");
		dt.drawArrow3D(a);
		
		// Z axis up
		System.out.println("Test Z axis up setting");
		dt.incrSequence();
		dt.setZaxisUp(true);
		dt.drawLabel(8, -6, -8, "Show Z axis up");
		dt.drawArrow3D(a);
		
		dt.incrSequence();
		dt.setZaxisUp(false);
		dt.drawLabel(-6, -8, 8, "Not show Z axis up");
		dt.drawArrow3D(a);
		
		dt.incrSequence();
		dt.setZaxisUp(true);
		dt.drawLabel(8, -6, -8, "Show Z axis up");
		dt.drawArrow3D(a);
	
		// Y axis up
		System.out.println("Test Y axis up setting");
		dt.incrSequence();
		dt.setYaxisUp(true);
		dt.drawLabel(-6, -8, 8, "Show Y axis up");
		dt.drawArrow3D(a);
		
		dt.incrSequence();
		dt.setYaxisUp(false);
		dt.drawLabel(8, -6, -8, "Not show Y axis up");
		dt.drawArrow3D(a);
		
		dt.incrSequence();
		dt.setYaxisUp(true);
		dt.drawLabel(-6, -8, 8, "Show Y axis up");
		dt.drawArrow3D(a);
		
		// Default Grid
		dt.incrSequence();
		dt.setZaxisUp(true);
		dt.drawLabel(8, -6, 12, "Toggle View Option to Show or Not Show Default Grid");
		dt.drawArrow3D(a);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}