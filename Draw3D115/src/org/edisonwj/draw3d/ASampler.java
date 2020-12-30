package org.edisonwj.draw3d;

import org.edisonwj.draw3d.Draw3D;
import org.edisonwj.draw3d.Polygon3D;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
/**
*
* @author William Edison
* @version 1.08 January 2017
*
*/

public class ASampler extends Application {

	private Draw3D dt;

	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("ASamplerOfBasicDrawingMethods");
		primaryStage.show();
	}

	private void buildData() {

		// Illustrate controls
		// #0
		dt.setSequencingOn();
		dt.setCumulate(false);
		dt.setAmbientLight(false);
		dt.setPointLight(true);
		dt.setDrawColor(Color.CORNFLOWERBLUE);
		dt.setDrawMode(DrawMode.FILL);
		dt.drawCylinder(8, 2, 4, 2, 3);
		dt.setDrawColor(Color.BLUE);
		dt.setLabelFont(Font.font("Regular", FontPosture.ITALIC,  14));
		dt.drawLabel(0, -12, 12, 0, 180, 16, "#0 Display controls");
		dt.setLabelFont(Font.font("Regular", 11));
		dt.drawLabel(0, -12, 11, 0, 180, 16, "Press F (or space bar) to move foward through views");
		dt.drawLabel(0, -12, 10, 0, 180, 16, "Press B to move backward through views");
		dt.drawLabel(0, -12,  9, 0, 180, 16, "Press keypad + or - to zoom in or out");
		dt.drawLabel(0, -12,  8, 0, 180, 16, "Press arrow keys to move display");
		dt.drawLabel(0, -12,  7, 0, 180, 16, "Use left mouse button to rotate display");
		dt.drawLabel(0, -12,  6, 0, 180, 16, "Press R to reset to initial view position");
		dt.drawLabel(0, -12,  5, 0, 180, 16, "Select Help Menu/Open Help Text for full description");

		// #1
		// Draw cumulative set of vectors
		double[] v1 = {2,  2, 2};
		double[] v2 = {-2, 2, 2};
		double[] v3 = add(v1, v2);
		double[] v4 = crossProduct(v1, v2);

		dt.incrSequence();
		dt.setCumulate(false);
		dt.setAmbientLight(true);
		dt.setPointLight(false);
		dt.setDrawColor(Color.BLUE);
		dt.setLabelFont(Font.font("Regular", FontPosture.ITALIC,  14));
		dt.drawLabel(0, -12, 12, 0, 180, 16, "#1-4 Cumulative Display of Various Vectors");
		dt.setLabelFont(Font.font("Regular", 12));
		dt.setDrawColor(Color.CORAL);
		dt.drawVector(v1);
		dt.drawVector(v2);
		dt.drawLabel(1.6, 2, 0.5, 0, 180, 16, "Base Vectors");

		// #2
		dt.incrSequence();
		dt.setCumulate(true);
		dt.setDrawColor(Color.RED);
		dt.drawVector(v3);
		dt.drawLabel(v3[0], v3[1], v3[2], 0, 180, 16, "Vector Addition");

		// #3
		dt.incrSequence();
		dt.setDrawColor(Color.BLUE);
		dt.drawArrow(v1[0], v1[1], v1[2], v3[0], v3[1], v3[2]);
		dt.drawArrow(v2[0], v2[1], v2[2], v3[0], v3[1], v3[2]);
		dt.drawLabel((v2[0]+.4), v2[1]+.6, v2[2], 0, 180,  16,  "Complete Parallelogram");

		// #4
		dt.incrSequence();
		dt.setDrawColor(Color.GOLDENROD);
		dt.drawVector(v4);
		dt.drawLabel(v4[0], v4[1], v4[2], 0,  180,  16,  "Vector Cross Product");

		// Lines, Arrows, and Vectors
		// #5
		dt.incrSequence();
		dt.setCumulate(false);
		dt.setDrawColor(Color.BLUE);
		dt.setLabelFont(Font.font("Regular", FontPosture.ITALIC,  14));
		dt.drawLabel(0, -12, 12, 0, 180, 16, "#5 Lines, Arrows, and Vectors");

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

       	dt.drawArrow(0,  0,  6,  5,  5,  6);
       	dt.drawArrow(0,  0,  6,  5, -5,  6);

		// Draw variety of shapes
		// #6
		dt.incrSequence();
		dt.setCumulate(false);
		dt.setAmbientLight(false);
		dt.setPointLight(true);
		dt.setDrawColor(Color.BLUE);
		dt.setLabelFont(Font.font("Regular", FontPosture.ITALIC,  14));
		dt.drawLabel(0, -12, 12, 0, 180, 16, "#6 3D Shapes in Various Modes");
		dt.setLabelFont(Font.font("Regular", 10));

		dt.setDrawColor(Color.CADETBLUE);
		dt.setDrawMode(DrawMode.FILL);
		dt.drawCone(8, 8, 0, 2, 3);

		dt.setDrawColor(Color.CADETBLUE);
		dt.setDrawMode(DrawMode.LINE);
		dt.drawCone(-3, 10, 0, 2, 3);

		dt.setDrawColor(Color.CORNFLOWERBLUE);
		dt.setDrawMode(DrawMode.FILL);
		dt.drawCylinder(8, -4 , 2, 2, 3);

		dt.setDrawColor(Color.CORNFLOWERBLUE);
		dt.setDrawMode(DrawMode.LINE);;
		dt.drawCylinder(-2, -4, 2, 2, 3);

		dt.setDrawColor(Color.TOMATO);
		dt.setDrawMode(DrawMode.FILL);
		dt.drawCuboid(4, -7, -5, 3, 4, 3);
		dt.drawLabel(4, -7, -8, "Fill");

		dt.setDrawColor(Color.TOMATO);
		dt.setDrawMode(DrawMode.LINE);
		dt.setCullFace(CullFace.BACK);
		dt.drawCuboid(-2, 2, -2.5, 2, 2.5, 2);
		dt.drawLabel(2, -2, -4.5, "No Fill, Cull None");

		dt.setDrawColor(Color.TOMATO);
		dt.setDrawMode(DrawMode.LINE);
		dt.setCullFace(CullFace.NONE);
		dt.drawCuboid(-2, -3.5, -6, 2, 2.5, 2);
		dt.drawLabel(-5, -2, -8, "No Fill, Cull Back");

		dt.setDrawColor(Color.GOLD);
		dt.setDrawMode(DrawMode.FILL);
		dt.drawSphere(8, 8, -4, 2);

		dt.setDrawColor(Color.GOLD);
		dt.setDrawMode(DrawMode.LINE);
		dt.drawSphere(-3, 10, -4, 2);

		dt.setDrawColor(Color.CRIMSON);
		dt.setDrawMode(DrawMode.FILL);
		dt.drawOval(7, 10, 6, 3, 1, 0, 45, 0);

		dt.setDrawColor(Color.CRIMSON);
		dt.setDrawMode(DrawMode.LINE);
		dt.drawOval(-4, 4, 8, 3, 1, 0, 45, 0);

		dt.setDrawColor(Color.DEEPPINK);
		dt.setDrawMode(DrawMode.FILL);
		dt.drawRectangle(7, -4, 6, 4, 2, 0, -45, 0);

		dt.setDrawColor(Color.DEEPPINK);
		dt.setDrawMode(DrawMode.LINE);
		dt.drawRectangle(-4, -6, 8, 4, 2,  0, -45, 0);

		dt.setDrawMode(DrawMode.FILL);
		dt.setCullFace(CullFace.NONE);

		// Draw vectors, lines, arrows of different sizes
		// #7
		dt.incrSequence();
		dt.setCumulate(false);
		dt.setDrawColor(Color.BLUE);
		dt.setLabelFont(Font.font("Regular", FontPosture.ITALIC,  14));;
		dt.drawLabel(-0, -12, 12, 0, 180, 16, "#7 Vectors, Lines, Arrows of Various Diameters with Grid");
		dt.setVectorRadius(.5);
		dt.setDrawColor(Color.RED);
		dt.drawVector(5, 0, 5);
		dt.drawVector(-5, 0, 5);
        dt.setDrawColor(Color.BLUE);
        dt.setLineRadius(1);
        dt.drawLine(1, 0, 0, 6, 0, 5);
        dt.drawLine(-1, 0, 0, -6, 0, 5);
        dt.setDrawColor(Color.GREEN);
		dt.setArrowRadius(2);
        dt.drawArrow(2, 0, 0, 7, 0, 5);
        dt.drawArrow(-2, 0, 0, -7, 0, 5);
        dt.drawGrid(0, 4, new PhongMaterial(Color.LIGHTGRAY));

        // Draw Polygons
        // #8
        dt.incrSequence();
        dt.setCumulate(false);
		dt.setPointLight(false);
		dt.setAmbientLight(true);
        dt.setShowBoundaryCube(true);
        dt.setDrawColor(Color.BLUE);
		dt.setLabelFont(Font.font("Regular", FontPosture.ITALIC,  14));
		dt.drawLabel(0, -12, 13, 0, 180, 16, "#8-9 Polygons with Bounding Box");
        dt.setDrawColor(Color.BLUE);
		dt.setLabelFont(Font.font("Regular", 12));;
		dt.drawLabel(0, -12, 12, 0, 180, 16, "2D polygon in XY plane");
		Point3D[] p1 = {
				new Point3D( -5.00,  -2.6, 0.0),
				new Point3D( -0.75,  -5.0, 0.0),
				new Point3D(  6.00,  -4.0, 0.0),
				new Point3D(  5.00,   5.4, 0.0),
//				new Point3D(  2.00,   2.4, 0.0),
				new Point3D(  3.00,   7.0, 0.0),
				new Point3D( -5.00,   5.0, 0.0) };
		dt.setDrawColor(Color.LIGHTGRAY);
		dt.drawPolygon(p1);

		// #9
		dt.incrSequence();
		dt.setCumulate(true);
		dt.setDrawColor(Color.BLUE);
		dt.setLabelFont(Font.font("Regular", 12));
		dt.drawLabel(0, -12, 11, 0, 180, 16, "2D polygon projected on 3D plane");
		Polygon3D p3d = new Polygon3D(p1);	// Uses none basic draw methods to project polygon
		p3d.setZ(4,  5,  6,  7);			// Project 2D polygon onto 3D plane
		Point3D[] p1z = p3d.getV();
		dt.setDrawColor(Color.CORNFLOWERBLUE);
		dt.drawPolygon(p1z);

		// #10
		dt.incrSequence();
		dt.setCumulate(false);
		dt.setYaxisUp(true);
		dt.setShowBoundaryCube(false);
		dt.setShowAxes(false);
		dt.setCamera(0.0, -180.0, -800.0);
		dt.setOriginView(-100, -100, -100);
		dt.setDrawColor(Color.ANTIQUEWHITE);
		Point3D[] p = { new Point3D( 3.0, 1.4, 0),	// p0
						new Point3D( 5.1, 2.3, 0),	// p1
						new Point3D( 5.8, 3.8, 0),	// p2
						new Point3D( 5.4, 5.2, 0),	// p3
						new Point3D( 4.8, 6.1, 0),	// p4
						new Point3D( 3.6, 7.1, 0),	// p5
						new Point3D( 2.1, 5.7, 0),	// p6
						new Point3D( 1.4, 4.2, 0),	// p7
						new Point3D( 1.0, 3.0, 0),	// p8
						new Point3D( 1.0, 1.0, 0)	// p9
						};

		dt.drawPolygon(p, true, true, true);
        dt.setDrawColor(Color.BLUE);
        dt.setLabelFont(Font.font("Regular", 12));
		dt.drawLabel(-6, 14, 0, 180, 0, 0, "#10 2D Polygon");
		dt.drawLabel(-4, 13, 0, 180, 0, 0, "setYaxisUp(true)");
		dt.drawLabel(-4, 12, 0, 180, 0, 0, "setCamera(0.0, -180.0, -800.0)");
		dt.drawLabel(-4, 11, 0, 180, 0, 0, "setOriginView(-100, -100, -100)");

        // Draw planes
		// #11
        dt.incrSequence();
        dt.setDefaults();
		dt.setAmbientLight(true);
		dt.setPointLight(false);
        dt.setShowBoundaryCube(true);
        dt.setDrawColor(Color.BLUE);
		dt.setLabelFont(Font.font("Regular", FontPosture.ITALIC,  14));
		dt.drawLabel(0, -12, 12, 0, 180, 16, "#11-12 Planes with Bounding Box");
        dt.setDrawColor(Color.LIGHTPINK);
		dt.drawPlane(4,5,6,7);

		// #12
		dt.incrSequence();
		dt.setCumulate(true);
		dt.setDrawColor(Color.ANTIQUEWHITE);
		dt.drawPlane(-4, 5, 6, 7);

		// Draw tetrahedron, octahedron, icosahedron, dodecahedron
		//#13
		dt.incrSequence();
        dt.setDefaults();
		dt.setAmbientLight(false);
		dt.setPointLight(true);
        dt.setShowBoundaryCube(false);
        dt.setShowAxes(false);
        dt.setDrawColor(Color.BLUE);
		dt.setLabelFont(Font.font("Regular", FontPosture.ITALIC,  14));
		dt.drawLabel(0, -12, 12, 0, 180, 16, "#13 Tetrahedron, Octahedron, Icosahedron, Dodecahedron");

		dt.setDrawColor(Color.BLACK);
		PhongMaterial lightgrayMaterial = new PhongMaterial(Color.LIGHTGRAY);
		double s = 3;
		Dodecahedron3D d3d0 = new Dodecahedron3D(0, 0, 0, s, new PhongMaterial(Color.TRANSPARENT));
		Point3D[] vertices = d3d0.getVertices();
		dt.drawPolyhedron3D(d3d0, true);
		dt.setLabelFontSize(12);
		for (int i = 0; i < vertices.length; i++) {
			dt.drawSphere3D(new Sphere3D(vertices[i], .05));
			dt.drawLabel(vertices[i].getX(), vertices[i].getY(), vertices[i].getZ(), "  v"+i);
		}

		Tetrahedron3D t3d = new Tetrahedron3D(-8,-10,8,s,lightgrayMaterial);
		dt.drawPolyhedron3D(t3d,true);

		Octahedron3D o3d = new Octahedron3D(6,-4,-6,1.25*s,lightgrayMaterial);
		dt.drawPolyhedron3D(o3d,true);

		Icosahedron3D i3d = new Icosahedron3D(-2,8,8,.5*s,lightgrayMaterial);
		dt.drawPolyhedron3D(i3d,true);

		Dodecahedron3D d3d = new Dodecahedron3D(2,8,-8,.5*s,lightgrayMaterial);
		dt.drawPolyhedron3D(d3d,true);


		// Change XYZ Ranges
		// #14
		dt.incrSequence();
		dt.setDefaults();
		dt.setCumulate(false);
		dt.setAmbientLight(true);
		dt.setPointLight(false);
        dt.setXYZRange(1, 1, 1);
        dt.setDrawColor(Color.BLUE);
		dt.setLabelFont(Font.font("Regular", FontPosture.ITALIC,  14));
		dt.drawLabel(0, -1.2, 1.2, 0, 180, 16, "#14 Change XYZ Ranges to -1 to 1");
		dt.drawLabel(0, -1.2, 1.1, 0, 180, 16, "(Ranges for X, Y, and Z can be different.)");
		dt.setDrawColor(Color.RED);
		dt.setArrowRadius(1);
		dt.drawArrow(0,0,.2,0,.6,.2);
		dt.setLabelFontSize(10);
		dt.drawLabel(0, .6, .3 , 0, 180, 16, "Arrow: (0, 0, .2) to (0, .6, .2)");

        // #15
        dt.incrSequence();
        dt.setXYZRange(100, 100, 100);
	    dt.setDrawColor(Color.BLUE);
		dt.setLabelFont(Font.font("Regular", FontPosture.ITALIC,  14));
		dt.drawLabel(0, -120, 120, 0, 180, 16, "#15 Change XYZ Ranges to -100 to 100");
		dt.setDrawColor(Color.RED);
		dt.drawArrow(0,0,40,0,60,40);
		dt.setLabelFontSize(10);
		dt.drawLabel(0, 60, 50 , 0, 180, 16, "Arrow: (0, 0, 40) to (0, 60, 40)");

        // #16
        dt.incrSequence();
        dt.setXYZRange(10, 10, 10);
	    dt.setDrawColor(Color.BLUE);
		dt.setLabelFont(Font.font("Regular", FontPosture.ITALIC,  14));
		dt.drawLabel(0, -12, 12, 0, 180, 16, "#16 Change XYZ Ranges to -10 to 10 (Default)");
		dt.setDrawColor(Color.RED);
		dt.drawArrow(0,0,6,0,6,6);
		dt.setLabelFontSize(10);
		dt.drawLabel(0, 6, 7 , 0, 180, 16, "Arrow: (0, 0, 6) to (0, 6, 6)");

        // Execute single animation
		// #17
		dt.incrSequence();
		dt.setDefaults();
        dt.setCumulate(false);
		dt.setDrawColor(Color.BLUE);
		dt.setLabelFont(Font.font("Regular", FontPosture.ITALIC,  14));
		dt.drawLabel(0, -12, 12, 0, 180, 16, "#17 Single Animation Example");
		dt.setLabelFont(Font.font("Regular", 12));
		dt.drawLabel(0, -12, 10, 0, 180, 16, "Select Main Menu/Run Algorithm");
		dt.drawLabel(0, -12,  9, 0, 180, 16, "Select from org/edisonwj/draw3d");
		dt.drawLabel(0, -12,  8, 0, 180, 16, "   AlgorithmBezierOrderN3D.class");
		dt.drawLabel(0, -12,  7, 0, 180, 16, "   or any other Algorithm class file");
		dt.drawLabel(0, -12,  6, 0, 180, 16, "(Algorithm execution will clear text)");

        // Execute multiple animations
		// #18
		dt.incrSequence();
        dt.setCumulate(false);
		dt.setDrawColor(Color.BLUE);
		dt.setLabelFont(Font.font("Regular", FontPosture.ITALIC,  14));
		dt.drawLabel(0, -12, 12, 0, 180, 16, "#18 Drone Annimation Example");
		dt.setLabelFont(Font.font("Regular", 12));
		dt.drawLabel(0, -12, 10, 0, 180, 16, "Select Main Menu/Load AlgorithmS");
		dt.drawLabel(0, -12,  9, 0, 180, 16, "   Select from org/edisonwj/draw3d");
		dt.drawLabel(0, -12,  8, 0, 180, 16, "       AlgorithmDrone1.class");
		dt.drawLabel(0, -12,  7, 0, 180, 16, "Select Main Menu/Load AlgorithmS");
		dt.drawLabel(0, -12,  6, 0, 180, 16, "   Select from org/edisonwj/draw3d");
		dt.drawLabel(0, -12,  5, 0, 180, 16, "       AlgorithmDrone2.class");
		dt.drawLabel(0, -12,  4, 0, 180, 16, "Select Main Menu/Run AlgorithmS");
		dt.drawLabel(0, -12,  3, 0, 180, 16, "Only two drone classes can be run at at time");
		dt.drawLabel(0, -12,  2, 0, 180, 16, "   due to the drone communication setup.");
		dt.drawLabel(0, -12,  1, 0, 180, 16, "Any number of non-drone algorithms can be run concurrently.");

		// Menu info
		// #19
		dt.incrSequence();
		dt.setDefaults();
		dt.setCumulate(false);
		dt.setShowAxes(false);
		dt.setShowBoundaryCube(false);
		dt.setDrawColor(Color.BLUE);
		dt.setLabelFont(Font.font("Regular", FontPosture.ITALIC,  14));
		dt.drawLabel(-0, -12, 12, 0, 180, 16, "#19 Menu Summary");
		dt.setLabelFont(Font.font("Regular", 12));
		dt.drawLabel(0, -8, 10, 0, 180, 18, "Main Menu provides options:");
		dt.drawLabel(0, -8,  9, 0, 180, 18, "    Open Files");
		dt.drawLabel(0, -8,  8, 0, 180, 18, "    Load Data");
		dt.drawLabel(0, -8,  7, 0, 180, 18, "    Load and Run Algorithm(S)");
		dt.drawLabel(0, -8,  6, 0, 180, 18, "    Save Data");
		dt.drawLabel(0, -8,  5, 0, 180, 18, "    Save Scene/SubScene Images");
		dt.drawLabel(0, -8,  4, 0, 180, 18, "View Menu provides dynamic options to:");
		dt.drawLabel(0, -8,  3, 0, 180, 18, "    Enable/Disable Axes Display");
		dt.drawLabel(0, -8,  2, 0, 180, 18, "    Enable/Disable Tick Mark Display");
		dt.drawLabel(0, -8,  1, 0, 180, 18, "    Z Axis Positive Up");
		dt.drawLabel(0, -8,  0, 0, 180, 18, "    Y Axis Positive Up");
		dt.drawLabel(0, -8, -1, 0, 180, 18, "    Enable/Disable Ambient Light");
		dt.drawLabel(0, -8, -2, 0, 180, 18, "    Enable/Disable Point Light");
		dt.drawLabel(0, -8, -3, 0, 180, 18, "    Enable/Disable Boundary Cube Display");
		dt.drawLabel(0, -8, -4, 0, 180, 18, "    Enable/Disable Default Grid Display");
		dt.drawLabel(0, -8, -5, 0, 180, 18, "Help Menu provides option to:");
		dt.drawLabel(0, -8, -6, 0, 180, 18, "    Open Help Text File");
		dt.drawLabel(0, -8, -7, 0, 180, 18, "    Help Text describes additional keyboard shortcuts");
		dt.drawLabel(0, -8, -8, 0, 180, 18, "    and additional non-basic methods.");
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
