package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.*;

import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
*
* @author William Edison
* @version 1.14 October 2017
*
* Executes the Draw3DExample2Algorithm with out changing default parameters
* and with changes to the delay value and the color.*
*/
public class Draw3DExample2 extends Application {

    // Need to set the title of the frame.
    String title = "Draw3D Example 2";

    void drawingCommands ()
    {
		d3.setDrawColor(Color.BLUE);
		d3.setLabelFont(Font.font("Regular", 14));
    	
    	// Set algorithm background/comments
    	// (Background or comments must be in the previous view to algorithm execution.)
    	// (Use setCumulate(true) in algorithm view to retain them for the algorithm view
		// (Algorithms will be executed when paging forward)
		d3.setCumulate(false);
		d3.drawLabel(0, -12, 14, 0, 180, 16, "Algorithm Example");
		d3.drawLabel(0, -12, 13, 0, 180, 16, "(Algorithms are executed when paging forward.)");
		d3.drawLabel(0, -12, 12, 0, 180, 16, "Algorithm executed with default parameter values.");
	
		// Load algorithm without changing parameters
		d3.incrSequence();
		d3.setCumulate(true);
		Algorithm algorithmE1 = d3.loadAlgorithm("org.edisonwj.draw3dtest.Draw3DExample2Algorithm");

    	// Set algorithm background/comments
    	// (Background or comments must be in the previous view to algorithm execution.)
    	// (Use setCumulate(true) to retain them for the algorithm view
		// (Algorithms will be executed when the paging forward)
		d3.incrSequence();
		d3.setCumulate(false);
		d3.drawLabel(0, -12, 14, 0, 180, 16, "Algorithm Example");
		d3.drawLabel(0, -12, 13, 0, 180, 16, "(Algorithms are executed when paging forward.)");
		d3.drawLabel(0, -12, 12, 0, 180, 16, "Algorithm executed with changed delay and color.");
		
		// Load algorithm and change parameters
		d3.incrSequence();
		d3.setCumulate(true);
		Algorithm algorithmE2 = d3.loadAlgorithm("org.edisonwj.draw3dtest.Draw3DExample2Algorithm");
		algorithmE2.setDelay(200);
		PhongMaterial m = new PhongMaterial(Color.ORCHID);
		algorithmE2.setMaterial(m);
    }

    // No need to read further
    //////////////////////////////////////////////////////////

    Draw3D d3;

    void preambleCommands ()
    {
		d3.setAmbientLight(false);
		d3.setPointLight(true);
		d3.setSequencingOn();
    }

    public void start (Stage primaryStage)
    {
		d3 = new Draw3D ();
		Scene scene = d3.buildScene ();
		preambleCommands ();
		drawingCommands ();
		d3.setStart();
		primaryStage.setScene (scene);
		primaryStage.setTitle (title);
		primaryStage.show ();
    }

    public static void main (String[] argv)
    {
		launch (argv);
    }

}