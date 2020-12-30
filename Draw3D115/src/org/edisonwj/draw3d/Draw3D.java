package org.edisonwj.draw3d;

import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.Executors;

import javax.imageio.*;

import javafx.concurrent.Task;
import javafx.geometry.Point3D;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SnapshotParameters;
import javafx.scene.SubScene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.Sphere;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
* This application provides a GUI and program interface for 3D drawing.
*
* @author William Edison
* @version 1.15 November 2020
*
* Copyright 2015 William J. Edison
* The code may be freely reused.
*
* Draw3D was developed, using JavaFX 8, in support of a colleague developing
* a linear algebra course to provide students/teachers with basic methods for
* displaying and working with three dimensional data involving vectors,
* lines, arrows, planes, and other geometric shapes and constructs. It is
* a work in progress and has evolved from a data entry and display tool to
* be primarily program driven. It provides methods for displaying three
* dimensional data, and supports the creation of a series of views
* (particularly for teachers) that can be paged through (forward and
* backward), rotated, zoomed, and moved around as a whole on the screen.
*
* Two sets of methods are provided. A basic set provides methods for drawing
* and setting parameters, such as color and size, related to drawing the
* objects. These methods are intended to free students from having to
* construct and manage objects, and just focus on mathematical constructs.
*
* Basic drawing methods are:
* - drawArrow(double x1, double y1, double z1,
*			  double x2, double y2, double z2)
* - drawBezier(Point3D[] p)
* - drawCircle(double x, double y, double z, double r)
* - drawCircle(double x, double y, double z, double r,
*    		   double rx, double ry, double rz)
* - drawCone(double x, double y, double z,
*			 double r, double h)
* - drawCone(double x, double y, double z,
*			 double r, double h,
*			 double rx, double ry, double rz)
* - drawCuboid(double x, double y, double z,
*			   double width, double height, double depth)
* - drawCuboid(double x, double y, double z,
*			   double width, double height, double depth,
*			   double rx, double ry, double rz)
* - drawCylinder(double x, double y, double z,
*				 double r, double h)
* - drawCylinder(double x, double y, double z,
*				 double r, double h,
*				 double rx, double ry, double rz)
* - drawGrid(int sector, int div,
* 			 PhongMaterial m)
* - drawLabel(double x, double y, double z, String s)
* - drawLabel(double x, double y, double z, String s,
* 			  Font f, Color c)
* - drawLabel(double x, double y, double z,
*			  double rx, double ry, double rz, String s)
* - drawLabel(double x, double y, double z,
*			  double rx, double ry, double rz, String s,
* 			  Font f, Color c)
* - drawLine(double x1, double y1, double z1,
*			 double x2, double y2, double z2)
* - drawLineEquation(double a1, double a2, double a3,
*					 double b1, double b2, double b3)
* - drawOval(double xi, double yi, double zi,
*			 double a, double b)
* - drawOval(double xi, double yi, double zi,
*			 double a, double b,
*			 double rx, double ry, double rz)
* - drawPlane(double a, double b, double c, double d)
* - drawPlane(double x1, double y1, double z1,
*			  double x2, double y2, double z2,
*			  double x3, double y3, double z3)
* - drawPoint(double x, double y, double z)
* - drawPolygon(Point3D[] v)
* - drawPolygon(Point3D[] v,
*	    		boolean drawEdges,
*				boolean drawVertices,
*				boolean drawVertexLabels)
* - drawRectangle(double x, double y, double z, double w, double h)
* - drawRectangle(double x, double y, double z, double w, double h,
*			      double rx, double ry, double rz)
* - drawSphere(double x, double y, double z, double r)
* - drawSquare(double x, double y, double z, double ln)
* - drawSquare(double x, double y, double z, double ln,
*			   double rx, double ry, double rz)
* - drawVector(double[] v)
* - drawVector(double x, double y, double z)
*
*
* - openFile(String filePath, String fileName)			Opens the specified file using the default
* 														application for that file type
* - openFile(String fileName)							Opens the specified file in executable directory
* 														using the default application for that file type
* - loadData(String filePath, String fileName)			Loads the specified data text file
* - loadData(String fileName)							Loads the specified data text file from the
* 														current executable directory
* - loadAlgorithm(String className)						Loads and returns, as an org.edisonwj.draw3D.Algoriththm
* 														object, the specified Algorithm class file from the current
* 														classPath. Multiple algorithms may be loaded and are run
* 														nearly concurrently.
* - saveData(String filePath, String fileName)			Saves the specified data text file
* - saveData(String fileName)							Saves the specified data text file to the
* 														current executable directory
* - saveScene(Scene scene, String format, String filePath, String fileName)
* 														Saves the current scene to the specified file
*														in format: bmp, gif, jpg, png
* - saveScene(Scene scene, String format, String fileName)
* 														Saves the current scene to the specified file in
* 														the current executable directory
*														in format: bmp, gif, jpg, png
*
* - saveSubScene(Scene scene, String format, String filePath, String fileName)
* 														Saves the current subScene to the specified file
*														in format: bmp, gif, jpg, png
* - saveSubScene(Scene scene, String format, String fileName)
* 														Saves the current subScene to the specified file in
* 														the current executable directory
*														in format: bmp, gif, jpg, png
*
* Drawing parameter setting methods are:
* (these parameter setting methods apply only to the basic drawing
*  methods listed above.)
* - setPointSize(double d)  	(pixels)
* - setLineRadius(double d)		(pixels)
* - setArrowRadius(double d)	(pixels)
* - setVectorRadius(double d)	(pixels)
* - setConeDivisions(int i)
* - setCylinderDivisions(int i)
* - setOvalDivisions(int i)
* - setSphereDivisions(int i)
* - setCullFace(CullFace cf )
* - setDrawMode(DrawMode dm)
* - setDrawColor(Color c)				Sets the default PhongMaterial diffuse Color
* - setDrawColor(Color c1, Color c2)	Sets the default PhongMaterial diffuse and
*										specular colors
* - setLabelFont(Font f)
* - setLabelFontSize(double d)
* - setMaterial(PhongMaterial m)		Sets the default PhongMaterial
* - setTexture(Image i)					Sets the default PhongMaterial with the image
*
* As well as the basic drawing methods above, another set is provided that
* allows for more extensive object customization. Most of the drawing
* methods above are implemented using the following methods:
* - drawArrow3D(Arrow3D a3d)
* - drawBox3D(Box3D b3d)
* - drawCone3D(Cone3D c3d)
* - drawCylinder3D(Cylinder3D c3d))
* - drawDrone3D(Drone3D d3d)
* - drawLabel3D(Text3D t3d)
* - drawLine3D(Line3D ln)
* - drawLine3DSet(Line3D[] ln)
* - drawMesh3D(Mesh3D m3d)
* - drawOval3D(Oval3D o3d)
* - drawPlane3D(Plane3D pl)
* - drawPoint3D(Point3D p)
* - drawPoint3DSet(Point3D[] p)
* - drawPolygon3D(Polygon3D p3d)
* - drawPolyhedron3D(Polyhedron3D p3d) where abstract Polyhedron3D is an instance of:
* 		Dodecahedron3D, Icosahedron3D, Octahedron3D, Tetrahedron3D
* - drawPolyhedronEdges3D(Polyhedron3D p3d)
* - drawPolyLine3D(Point3D[] p)
* - drawRectangle3D(Rectangle3D r)
* - drawSphere3D(Sphere3D s3d)
* - drawSphere3DSet(Sphere3D[] ss)
* - drawTriangle3D(Triangle3D t3d)
* - drawVector3D(Vector3D vc)
* - drawVector3DSet(Vector3D v)
* - rotateLine3D(Line3D ln, double rxd, double ryd, double rzd, boolean zUp)
*
* The following parameter setting methods apply to all drawing methods.
* - setDefaults()
* - setAmbientLight(boolean b)
* - setPointLight(boolean b)
* - setShowAxes(boolean b)
* - setShowYAxisUp(boolean b) -	X+ to right, Y+ upward, Z+ toward viewer
* - setShowYAxisUpNoZ(boolean b) - X+ to right, Y + upward, and no Z axis displayed
*							(Shape specification still requires x, y, and z.)
*							Camera specification are changed to center on origin
* - setShowZAxisUp(boolean b) -	X+ toward user, Y+ to right, Z+ upward
* - setXYaxesOnly(boolean b) - shows only X and Y axes centered on origin
* - setShowBoundaryCube(boolean b)
* - setBackgroundColor(Color c)
* - setCumulate(boolean b)
*
* - setStart(int i)			Specify initial view to display
* - setStart()				Start display with view zero
* - incrSequence()			Begin definition of a new view and mark it visible
*
* - setCamera(double x-angle, double y-angle, double z-distance)
* 		Sets camera location/orientation with JavaFX X axis positive
* 		to the right, Y axis positive down, and Z axis positive away
*		from the viewer.
* 		(-10, -200, -1000) is default and offsets camera from axes.
* 		(0, -180, -800) centers camera on Z axis.
* - setOriginView(int x, int y, int z)
* 		Sets view center point to specified screen position.
*		(0, 0, 0) pixels is default.
* - setScale(double scaleX, double scaleY, double scaleZ)
* 		Sets scale vales for x , y, z
* - setXYZRange(double minmaxX, double minmaxY, double minmaxZ)
* 		Sets user coordinate space ranges.
*		(10, 10, 10) is default.
*
* The following mouse and keyboard commands are available to modify views.
*
* Reset
* Double clicking the mouse stops executing concurrent tasks, clears
* current views, associated data structures and settings, and
* reinitialize the subScene.
*
* Rotation
* The mouse can be used to rotate the displayed items about all axes.
*
* Scale/Zoom
* The mouse wheel can be used to zoom in or out.
* (This can also be done with the keypad +/- keys.)
*
* Keyboard short cuts (alphabetic keys are not case sensitive)
* - B: moves backward to display previous data view.
* - C: toggles manual cumulate mode. When manual cumulate is on,
*	   it overrides program defined cumulate, and when moving forward
*	   the current data group(s) is retained for viewing, i.e. data
*	   groups are overlaid.
* - F: or Space: advances forward to display next data group.
* - R: resets origin to current default and redraws current data group.
* - T: generates a basic set of vector test data.
* - X: marks all currently displayed data groups invisible and advances
*	   to display the next data group.
* - Z: marks all data groups invisible, returns viewing to data group
*	  number 0, and displays it.
* - + on keypad: zooms in.
* - - on keypad: zooms out.
* - Up arrow: moves displayed data up in the view space. (NumLk must be off.)
* - Down arrow: moves displayed data down in the view space. (NumLk must be off.)
* - Left arrow: moves displayed data left in the view space. (NumLk must be off.)
* - Right arrow: moves displayed data right in the view space. (NumLk must be off.)
*
* Menu Bar
* - Main Menu
*
*	(Draw3D looks for a Resources directory for the Open and Load operations;
*	 if not found the current working directory is used. All other operations
*	 default to the current working directory, but the selection dialogue can
*	 be used to select any directory.)
*
*	- Open: opens the file selected from a File Chooser dialogue using the
*		    default application for the selected file type.
*	- Load Data: loads and displays data using the format described below.
*	- Run Algorithm: loads and executes an algorithm class file (contents
*					 described below).
*	- Load AlgorithmS: provides for loading multiple algorithms, one at a
*					   time, making them ready for execution.
*	- Run AlgorthmS: executes concurrently all of the Algorithm class files
*					 previously loaded.
*	- Save Data File: saves currently displayed data using the format
*					  described below.
*	- Save Scene: saves the current display in bmp, jpg, or png format.
*	- Save SubScene: saves the current display excluding the menu bar in
*					 bmp, jpg, png format.
*
* - View Menu
*	- Axes: show axes.
*	- Axis Y Up: display data with positive Y axis pointing up
*				 (Axis Y Up and Axis Z Up are mutually exclusive).
*	- Axis Y up and No Z: display data with positive Y axis pointing up,
*				X axis horizontal and no Z axis displayed.
*				(Shape specification still requires x, y, and z).
*	- Axis Z Up: display data with positive Z axis pointing up.
*	- Tick Marks: show tick marks on axes.
*	- Boundary Cube: display boundary cube based on minmaxX, minmaxY,
*					 and minmaxZ.
	- Default Grid: displays grid lines across all sectors with 4 lines per axis.
*	- Ambient Light: use ambient light.
*	- Point Light: use point light.
*
* - Help Menu
*	- Open Help Text: opens and displays this file.
*
* Algorithm Class File
*	The Algorithm class file must implement the Algorithm Interface which
*	supports animation style execution with several parameter values. The
*	class file must specify:
*	- iterations (int)	- the number of iterations to be executed
*	- delay (long)		- the wait time in milliseconds between each iteration
*	- clear flag (boolean) - indicates whether current display contents should
*							 be cleared before displaying the next iteration
*	- drone flag (boolean) - indicates the algorithm is implementing a drone
*	- id (int)			- identifier used only by drones
*	- info (double[])	- information array used only by drones
*	- vMaterial			- PhongMaterial to be used in display of the item
*						  (vMaterial can be changed at each iteration)
*						  (PhongMaterial can also be embedded in the returned
*						  objects.)
*
*  When executed by selecting the Run Algorithm Main Menu option, the selected
*  algorithm class file is executed returning an object of type listed below.
*
*  When the Load AlgorithmS Main Menu option is used to load two or more
*  algorithm class files (only two drone class files may be executed
*  concurrently), the loaded class files are executed concurrently when
*  Run AlgorithmS is selected. The concurrently executing class files return
*  objects of type listed below.
*
*  Currently supported return types are:
*	- Arrow3D
*	- Box3D
*	- Cone3D
*	- Cylinder3D
*	- Dodecahedron3D
*	- Drone3D
*	- Icosahedron3D
*	- Line3D
*	- Line3D[]
*	- Mesh3D
*	- Octahedron3D
*	- Oval3D
*	- Plane3D
*	- Point3D
*	- Point3D[]
*	- Polygon3D
*	- Rectangle3D
*	- Sphere3D
*	- Sphere3D[]
*	- Tetrahedron3D
*	- Text3D
*	- Triangle3D
*	- Vector3D
*	- Object[] Array containing the above listed 3D objects
*
* The Main Menu Load and Save data use the following formats:
*
*		The file data fields may be separated by a comma, blank, colon,
*		or semicolon and are formatted with an initial data type indicator
*		followed by parameters:
*
*		Comment: 	// indicates a comment line
*		DataGroup:	Starts a DataGroup/View definition
*		View:		showAxes-flag, showTickMarks-flag, axisZup-flag,
*					showBoundaryCubeflag, ambientLight-flag, pointLight-flag,
*					cumulate-flag
*		Camera:		x-angle, y-angle, z-distance
*		Origin:		x-coor, y-coor, z-coor (pixels)
*		Range:		x-minmax, y-minmax, z-minmax
*					(The range statement triggers rebuild of the axes, tick marks, boundary, and/or grid
*					based on the view, camera, and origin settings.)
*		Color:	Specifies the fill color for polygons
*		Title1:	Primarily for Rapport data, specifies text defining the type of data, e.g. PolyMap Voronoi
*		Title2:	Primarily for Rapport data, specifies text defining generation details for the data
*
* 		Arrow data:	Arrow: x1-coor, y1-ccor, z1-coor, x2-coor, y2-coor, z2-coor, drawMode, cullFace, material
* 		Box data:	Box: center-x-coor, center-y-coor, center-z-coor, width, height, depth,
*					x-rotation, y-rotation, z-rotation, drawMode, cullFace, material
*		Circle data: Circle: center x_coor, y-coor, z-coor, radius
* 		Cone data:	Cone: tip-x-coor, tip-y-coor, tip-z-coor, height, radius,
*					x-rotation, y-rotation, z-rotation, drawMode, cullFace, material
* 		Cylinder data:	Cylinder: center-x-coor, center-y-coor, center-z-coor, height, radius,
*						x-rotation, y-rotation, z-rotation, drawMode, cullFace, material
*		Dodecahedron data:	Dodecahedron: center-x-coor, center-y-coor, center-z-coor, size,
*							x-rotation, y-rotation, z-rotation, drawMode, cullFace, material
*		Icosahedron data:	Icosahedron: center-x-coor, center-y-coor, center-z-coor, size,
*							x-rotation, y-rotation, z-rotation, drawMode, cullFace, material
* 		Line data:	Line: x1-coor, y1-coor, z1-coor, x2-coor, y2-coor, z2-coor, drawMode, cullFace, material
*		Octahedron data:	Octahedron: center-x-coor, center-y-coor, center-z-coor, size,
*							x-rotation, y-rotation, z-rotation, drawMode, cullFace, material
* 		Oval data:	Oval: center-x1-coor, center-y1-coor, center-z1-coor, majorRadius, minorRadius,
*					x-rotation, y-rotation, z-rotation, drawMode, cullFace, material
* 		Plane data:	Plane: (plane equation coordinates) a, b, c, d, drawMode, cullFace, material
* 		Point data:	Point: x-coor, y-coor, z-coor, drawMode, cullFace, material
*		Polygon data:	Polygon: x-coor, y-coor, z-coor; ... #,
*						drawEdges, drawVertices, drawVertexLabels, drawMode, cullFace, material
*		PolyLine data: PolyLine: Series of point coordinates: x-coor, y-coor, z-coor; ...
*		Rectangle data: Rectangle: center-x-coor, center-y-coor, center-z-coor, length-extent, width-extent,
*						x-rotation, y-rotation- z-rotation, drawMode, cullFace, material
* 		Sphere data:	Sphere: center-x-coor, center-y-coor, center-z-coor, radius,
*						x-rotation, y-rotation, z-rotation, drawMode, cullFace, material
*		Tetrahedron data:	Tetrahedron: center-x-coor, center-y-coor, center-z-coor, size,
*							x-rotation, y-rotation, z-rotation, drawMode, cullFace, material
* 		Text data:	Text: center-x-coor; center-y-coor; center-z-coor;
*					x-rotation; y-rotation; z-rotation;
*					Font; Color; String (for Text only, values must be : or ; delimited)
* 		Triangle data:	Triangle: ( x1-coor, y1-coor, z1-coor ), ( x2-coor, y2-coor, z2-coor ), ( x3-coor, y3-coor, z3-coor ), #,
*						drawMode, cullFace, material
* 		Vector data:	Vector: endx-coor, endy-coor, endz-coor, drawMode, cullFace, material
*
*		See example data files: line check 1-4.txt, vector check 1-2.txt, shapes.txt.
*
* See JavaDoc for a full description of the classes and methods.
*
* Draw3D implements Draw3DDefaults to initialize state.
*
*/
public class Draw3D extends Application implements Draw3DDefaults {

	private String packagePrefix = "org.edisonwj.draw3d.";
	private String packagePath = "org\\edisonwj\\draw3d";

	private Desktop desktop;
    private Stage stage;
    private Scene scene;
    private BorderPane border;
	private Pane pane;
	private SubScene subScene;
	private Group subSceneRoot;
	private Group objectGroup;
	private Group axisGroup;
	private Group tickGroup;
	private Group boundaryCubeGroup;
	private Group defaultGridGroup;
	private PointLight plight1;
	private double plight1X;
	private double plight1Y;
	private double plight1Z;
	private PointLight plight2;
	private double plight2X;
	private double plight2Y;
	private double plight2Z;
	private AmbientLight alight;
    private PerspectiveCamera camera;
    private Xform cameraXform;

	private CheckMenuItem axesCheckItem;
	private CheckMenuItem axesYupCheckItem;
	private CheckMenuItem axesXYonlyCheckItem;
	private CheckMenuItem axesZupCheckItem;
	private CheckMenuItem tickCheckItem;
	private CheckMenuItem alightCheckItem;
	private CheckMenuItem plightCheckItem;
	private CheckMenuItem cubeCheckItem;
	private CheckMenuItem gridCheckItem;

	private boolean cumulate = CUMULATE_DEFAULT;
	private boolean manualCumulate = CUMULATE_MANUAL_DEFAULT;
	private int currentSequenceNum = 0;
	private int viewNumber = 0;
	private ArrayList<Group> dataGroup = new ArrayList<>();		// a list of the dataGroups of drawn objects for each view
	private ArrayList<Object> drawList;
	private ArrayList<ArrayList<Object>> saveDrawLists;
	private ArrayList<double[]> savedCamera;
	private ArrayList<int[]> savedOrigin;
	private ArrayList<double[]> savedRange;
	private ArrayList<double[]> savedScale;
	private ArrayList<boolean[]> savedViewSettings;
	private ArrayList<Task<Object>> algorithmTaskList = new ArrayList<>();	// a list of the algorithms that have been loaded
	private int droneIncrement = 0;
    private ArrayList<ArrayList<String>> viewClassList;
    private ArrayList<ArrayList<Algorithm>> viewAlgorithmList;
    private ArrayList<Integer> viewDroneCount;
    private ArrayList<ArrayList<Drone3D>> viewDroneState;

    private double mousePosX;
    private double mousePosY;
    private double mouseOldX;
    private double mouseOldY;
    private double mouseDeltaX;
    private double mouseDeltaY;

    private double minX = -MIN_MAX_X;
    private double maxX = MIN_MAX_X;
    private double minY = -MIN_MAX_Y;
    private double maxY = MIN_MAX_Y;
    private double minZ = -MIN_MAX_Z;
    private double maxZ = MIN_MAX_Z;

    private double sceneWidth = SCENE_WIDTH;
    private double sceneHeight = SCENE_HEIGHT;
    private double[] cameraView = {CAMERA_INITIAL_X_ANGLE, CAMERA_INITIAL_Y_ANGLE, CAMERA_INITIAL_DISTANCE};
    private int[] originView = Arrays.copyOf(DEFAULT_ORIGIN, DEFAULT_ORIGIN.length);
    private int[] origin = Arrays.copyOf(DEFAULT_ORIGIN, DEFAULT_ORIGIN.length);
    private Color backgroundColor = BACKGROUND_COLOR;
    private DrawMode drawMode = DrawMode.FILL;
    private CullFace cullFace = CullFace.NONE;
    private double pointSize = POINT_SIZE;
    private double lineRadius = LINE_RADIUS;
    private double arrowRadius = ARROW_RADIUS;
    private double arrowPointRadius = ARROW_POINT_RADIUS;
    private double arrowPointHeight = ARROW_POINT_HEIGHT;
    private double vectorRadius = VECTOR_RADIUS;
    private int avPointDivisions = AV_POINT_DIVISIONS;
    private int ovalDivisions = OVAL_DIVISIONS;
    private int coneDivisions = CONE_DIVISIONS;
    private int cylinderDivisions = CYLINDER_DIVISIONS;
    private int sphereDivisions = SPHERE_DIVISIONS;
    private double labelFontSize = LABEL_FONT_SIZE;
    private Font labelFont = LABEL_FONT;

    private PhongMaterial redMaterial = new PhongMaterial();
    private PhongMaterial greenMaterial = new PhongMaterial();
    private PhongMaterial blueMaterial = new PhongMaterial();
    private PhongMaterial blackMaterial = new PhongMaterial();
    private PhongMaterial greyMaterial = new PhongMaterial();
	private PhongMaterial salmonMaterial = new PhongMaterial();
	private PhongMaterial turquoiseMaterial = new PhongMaterial();
	private PhongMaterial magentaMaterial = new PhongMaterial();
	private PhongMaterial goldMaterial = new PhongMaterial();
	private PhongMaterial boundaryMaterial;
    private PhongMaterial drawMaterial;

	@Override
    public void start(Stage stage) throws Exception {
    	this.stage = stage;
    	scene = buildScene();
        stage.setTitle("Draw3D Version 1.14");
        stage.setScene(scene);
        stage.show();
    }

	/**
	 * Builds the Scene for display and use by Draw3D methods.
	 * By default the origin of the user space is located at the center of the display space
	 *
	 * @return	scene	Contains all of the groups and objects required for use of Draw3D methods.
	 */
	public Scene buildScene() {
    	desktop = Desktop.getDesktop();
    	border = new BorderPane();
        border.setTop(addMenuBar());
        scene = new Scene(border, sceneWidth, sceneHeight);
        buildSubScene();
        return scene;
	}

	/**
	 * Builds the SubScene, creates the data structures and groups required for Draw3D,
	 * and initializes to default state per the settings in Draw3DDefaults.
	 */
	public void buildSubScene() {
		setColors();
    	subSceneRoot = new Group();
        subScene = new SubScene(subSceneRoot, sceneWidth, sceneHeight,
        						true, SceneAntialiasing.BALANCED);
        subScene.setFill(backgroundColor);
    	objectGroup = new Group();
    	currentSequenceNum = 0;
    	viewNumber = 0;
    	Group aGroup = new Group();
    	aGroup.setVisible(true);
    	dataGroup.add(aGroup);
        objectGroup.getChildren().addAll(dataGroup.get(currentSequenceNum));
        objectGroup.setVisible(true);
        subSceneRoot.getChildren().addAll(objectGroup);

        axesCheckItem.setSelected(SHOW_AXES);
        tickCheckItem.setSelected(SHOW_TICK_MARKS);
        axesYupCheckItem.setSelected(!SHOW_Z_AXIS_UP);
        axesZupCheckItem.setSelected(SHOW_Z_AXIS_UP);
        axesXYonlyCheckItem.setSelected(SHOW_X_Y_ONLY);
        cubeCheckItem.setSelected(SHOW_BOUNDARY_CUBE);
        gridCheckItem.setSelected(SHOW_DEFAULT_GRID);
        alightCheckItem.setSelected(AMBIENT_LIGHT_ON);
        plightCheckItem.setSelected(POINT_LIGHT_ON);
        createAmbientLight();
        createPointLight();
    	setAmbientLight(alightCheckItem.isSelected(), false);
        setPointLight(plightCheckItem.isSelected(), false);

    	savedCamera = new ArrayList<double[]>();			// a list of the Camera default settings associated with each view
        saveCamera(0);
        savedOrigin = new ArrayList<int[]>();				// a list of the Origin default settings associated with each view
        saveOrigin(0);
        savedRange = new ArrayList<double[]>();				// a list of the Range settings associated with each view
    	saveRange(0);
    	savedScale =  new ArrayList<double[]>();			// a list of the Scale settings associated with each view
    	saveScale(0);
    	savedViewSettings = new ArrayList<boolean[]>();		// a list of the View settings associated with each view
    	saveViewSettings(0);

    	drawList = new ArrayList<Object>();					// a list of all the 3D objects in a view
    	saveDrawLists = new ArrayList<ArrayList<Object>>();	// a list of the 3D objects associated with each view
    	saveDrawLists.add(drawList);
    	viewClassList = new ArrayList<ArrayList<String>>();
    	viewClassList.add(new ArrayList<String>());
    	viewAlgorithmList = new ArrayList<ArrayList<Algorithm>>();
    	viewAlgorithmList.add(new ArrayList<Algorithm>());
    	viewDroneCount = new ArrayList<Integer>();
    	viewDroneCount.add(0);
    	viewDroneState = new ArrayList<ArrayList<Drone3D>>();
    	viewDroneState.add(new ArrayList<Drone3D>());

		buildCamera();
    	buildAxes();
    	buildTickMarks();
    	buildBoundaryCube();
    	buildDefaultGrid();

   		axisGroup.setVisible(axesCheckItem.isSelected());
		tickGroup.setVisible(tickCheckItem.isSelected());
		boundaryCubeGroup.setVisible(cubeCheckItem.isSelected());
		defaultGridGroup.setVisible(gridCheckItem.isSelected());
		objectGroup.setVisible(true);

        pane = new Pane();
        pane.getChildren().addAll(subScene);

        subScene.widthProperty().bind(
                pane.widthProperty());
        subScene.heightProperty().bind(
                pane.heightProperty());
        border.setCenter(pane);

        handleKeyboard(scene);
        handleMouse(subScene);

	}

	/**
	 * Builds and initializes the perspective Camera
	 */
	private void buildCamera() {
    	camera = new PerspectiveCamera(true);
        subScene.setCamera(camera);

        cameraXform = new Xform();
        subSceneRoot.getChildren().add(cameraXform);
        cameraXform.getChildren().add(camera);
        cameraXform.setRotateZ(180.0);

        camera.setNearClip(CAMERA_NEAR_CLIP);
        camera.setFarClip(CAMERA_FAR_CLIP);
        camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
        cameraXform.ry.setAngle(CAMERA_INITIAL_Y_ANGLE);
        cameraXform.rx.setAngle(CAMERA_INITIAL_X_ANGLE);
    }

	/**
	 * Builds and initializes the x, y, and z axes using the current values
	 * for the origin and for minX, maxX, minY, maxY, minZ, maxZ.
	 * At present, Abs(minX) must be equal to maxX, and similarly for the other axes.
	 *
	 * Visibility of axes can be selected or de-selected with a View Menu option
	 * or with the setShowAxes() method. Changes made with View Menu options apply
	 * only to the currently displayed view and are not saved.
	 */
	private void buildAxes() {

    	axisGroup = new Group();

    	// Build axes
    	Cylinder xAxis = new Cylinder(AXIS_RADIUS, AXIS_LENGTH);
    	xAxis.setMaterial(redMaterial);
        Cylinder yAxis = new Cylinder(AXIS_RADIUS, AXIS_LENGTH);
        yAxis.setMaterial(greenMaterial);
        Cylinder zAxis = new Cylinder(AXIS_RADIUS, AXIS_LENGTH);
        zAxis.setMaterial(blueMaterial);

        Text xpLabel = null, xnLabel = null;
        Text ypLabel = null, ynLabel = null;
        Text zpLabel = null, znLabel = null;

    	if ( axesZupCheckItem.isSelected() ) {
	        xAxis.getTransforms().addAll(new Translate(origin[0], origin[1], origin[2]),
	        	new Rotate(-90,Rotate.X_AXIS));

	        yAxis.getTransforms().addAll(new Translate(origin[0], origin[1], origin[2]),
				new Rotate(90,Rotate.Z_AXIS));

	    	zAxis.getTransforms().addAll(new Translate(origin[0], origin[1], origin[2]));

	        // Build labels
	    	xpLabel = new Text(Double.toString(maxX) + " X");
	        xpLabel.setFont(new Font(10));
	        xpLabel.setTranslateX(origin[0]);
	        xpLabel.setTranslateY(origin[1]);
	        xpLabel.setTranslateZ(origin[2]+(AXIS_LENGTH/2.0));
	        xpLabel.setRotationAxis(Rotate.X_AXIS);
	        xpLabel.setRotate(180.0);

	    	xnLabel = new Text(Double.toString(minX) + " X");
	        xnLabel.setFont(new Font(10));
	        xnLabel.setFont(new Font(10));
	        xnLabel.setTranslateX(origin[0]);
	        xnLabel.setTranslateY(origin[1]);
	        xnLabel.setTranslateZ(origin[2]+(-AXIS_LENGTH/2.0));
	        xnLabel.setRotationAxis(Rotate.X_AXIS);
	        xnLabel.setRotate(180.0);

	        ypLabel = new Text(Double.toString(maxY) + " Y");
	        ypLabel.setFont(new Font(10));
	        ypLabel.setTranslateX(origin[0]+(AXIS_LENGTH/2.0));
	        ypLabel.setTranslateY(origin[1]);
	        ypLabel.setTranslateZ(origin[2]);
	        ypLabel.setRotationAxis(Rotate.X_AXIS);
	        ypLabel.setRotate(180.0);

	        ynLabel = new Text(Double.toString(minY) + " Y");
	        ynLabel.setFont(new Font(10));
	        ynLabel.setTranslateX(origin[0]+(-20.0-AXIS_LENGTH/2.0));
	        ynLabel.setTranslateY(origin[1]);
	        ynLabel.setTranslateZ(origin[2]);
	        ynLabel.setRotationAxis(Rotate.X_AXIS);
	        ynLabel.setRotate(180.0);

	        zpLabel = new Text(Double.toString(maxZ) + " Z");
	        zpLabel.setFont(new Font(10));
	        zpLabel.setTranslateX(origin[0]);
	        zpLabel.setTranslateY(origin[1]+(10.0+AXIS_LENGTH/2.0));
	        zpLabel.setTranslateZ(origin[2]);
	        zpLabel.setRotationAxis(Rotate.X_AXIS);
	        zpLabel.setRotate(180.0);

	        znLabel = new Text(Double.toString(minZ) + " Z");
	        znLabel.setFont(new Font(10));
	        znLabel.setTranslateX(origin[0]);
	        znLabel.setTranslateY(origin[1]+(-AXIS_LENGTH/2.0));
	        znLabel.setTranslateZ(origin[2]);
	        znLabel.setRotationAxis(Rotate.X_AXIS);
	        znLabel.setRotate(180.0);
    	}
    	else if( axesYupCheckItem.isSelected() ) {
        	xAxis.getTransforms().addAll(new Translate(origin[0], origin[1], origin[2]),
        								 new Rotate(90,Rotate.Z_AXIS));
	        yAxis.getTransforms().addAll(new Translate(origin[0], origin[1], origin[2]));
	    	zAxis.getTransforms().addAll(new Translate(origin[0], origin[1], origin[2]),
					 new Rotate(-90,Rotate.X_AXIS));

	        // Build labels
	    	xpLabel = new Text(Double.toString(maxX) + " X");
	        xpLabel.setFont(new Font(10));
	        xpLabel.setTranslateX(origin[0]+(AXIS_LENGTH/2.0));
	        xpLabel.setTranslateY(origin[1]);
	        xpLabel.setTranslateZ(origin[2]);
	        xpLabel.setRotationAxis(Rotate.X_AXIS);
	        xpLabel.setRotate(180.0);

	    	xnLabel = new Text(Double.toString(minX) + " X");
	        xnLabel.setFont(new Font(10));
	        xnLabel.setTranslateX(origin[0]+(-20.0-AXIS_LENGTH/2.0));
	        xnLabel.setTranslateY(origin[1]);
	        xnLabel.setTranslateZ(origin[2]);
	        xnLabel.setRotationAxis(Rotate.X_AXIS);
	        xnLabel.setRotate(180.0);

	        ypLabel = new Text(Double.toString(maxY) + " Y");
	        ypLabel.setFont(new Font(10));
	        ypLabel.setTranslateX(origin[0]);
	        ypLabel.setTranslateY(origin[1]+(10.0+AXIS_LENGTH/2.0));
	        ypLabel.setTranslateZ(origin[2]);
	        ypLabel.setRotationAxis(Rotate.X_AXIS);
	        ypLabel.setRotate(180.0);

	        ynLabel = new Text(Double.toString(minY) + " Y");
	        ynLabel.setFont(new Font(10));
	        ynLabel.setTranslateX(origin[0]);
	        ynLabel.setTranslateY(origin[1]+(1-AXIS_LENGTH/2.0));
	        ynLabel.setTranslateZ(origin[2]);
	        ynLabel.setRotationAxis(Rotate.X_AXIS);
	        ynLabel.setRotate(180.0);

        	zpLabel = new Text(Double.toString(maxZ) + " Z");
	        zpLabel.setFont(new Font(10));
	        zpLabel.setTranslateX(origin[0]);
	        zpLabel.setTranslateY(origin[1]);
	        zpLabel.setTranslateZ(origin[2]+(AXIS_LENGTH/2.0));
	        zpLabel.setRotationAxis(Rotate.X_AXIS);
	        zpLabel.setRotate(180.0);

	        znLabel = new Text(Double.toString(minZ) + " Z");
	        znLabel.setFont(new Font(10));
	        znLabel.setTranslateX(origin[0]);
	        znLabel.setTranslateY(origin[1]);
	        znLabel.setTranslateZ(origin[2]+(-AXIS_LENGTH/2.0));
	        znLabel.setRotationAxis(Rotate.X_AXIS);
	        znLabel.setRotate(180.0);
    	}
    	else {
    		System.out.println("Build axes error");
    	}

    	if ( axesXYonlyCheckItem.isSelected() ) {
	        axisGroup.getChildren().addAll(xAxis, yAxis);
	        axisGroup.getChildren().addAll(xpLabel, xnLabel, ypLabel, ynLabel);
    	}
        else {
	        axisGroup.getChildren().addAll(xAxis, yAxis, zAxis);
	        axisGroup.getChildren().addAll(xpLabel, xnLabel, ypLabel, ynLabel, zpLabel, znLabel);
        }
        objectGroup.getChildren().addAll(axisGroup);
    }

	/**
	 * Builds tick marks to be applied to the axes.
	 *
	 * Visibility of tick marks can be selected or de-selected with a View Menu option
	 * or with the setShowTickMarks() method. Changes made with View Menu options apply
	 * only to the currently displayed view.
	 */
    private void buildTickMarks() {

    	tickGroup = new Group();

    	 if ( axesZupCheckItem.isSelected() ) {
	    	// Build X axis ticks
	    	double tlx = (TICK_MARK_LENGTH * (maxX - minX)) / AXIS_LENGTH;
		 	double spacingX = setSpacing(maxX);
		 	for (double i = minX; i <= maxX; i += spacingX) {
				if (i == 0.0) {
					continue;
				}
	    		double x1 = i;
	    		double y1 = 0.0;
	    		double z1 = -tlx/2.0;
	    		double x2 = i;
	    		double y2 = 0.0;
	    		double z2 = tlx/2.0;
				Line3D ln = new Line3D(new Point3D(x1, y1, z1), new Point3D(x2, y2, z2), redMaterial);
				drawTick3D(ln);
		 	}

	    	// Build Y axis ticks
		 	double tly = (TICK_MARK_LENGTH * (maxY - minY)) / AXIS_LENGTH;
	     	double spacingY = setSpacing(maxY);
	     	for (double i = minY; i <= maxY; i += spacingY) {
	    		if (i == 0.0) {
	    			continue;
	    		}
	    		double x1 = 0.0;
	    		double y1 = i;
	    		double z1 = -tly/2.0;
	    		double x2 = 0.0;
	    		double y2 = i;
	    		double z2 = tly/2.0;
				Line3D ln = new Line3D(new Point3D(x1, y1, z1), new Point3D(x2, y2, z2), greenMaterial);
				drawTick3D(ln);
	     	}

	    	// Build Z axis ticks
	     	double tlz = (TICK_MARK_LENGTH * (maxZ - minZ)) / AXIS_LENGTH;
	     	double spacingZ = setSpacing(maxZ);
	     	for (double i = minZ; i <= maxZ; i += spacingZ) {
	    		if (i == 0.0) {
	    			continue;
	    		}
	    		double x1 = 0.0;
	    		double y1 = -tlz/2.0;
	    		double z1 = i;
	    		double x2 = 0.0;
	    		double y2 = tlz/2.0;
	    		double z2 = i;
				Line3D ln = new Line3D(new Point3D(x1, y1, z1), new Point3D(x2, y2, z2), blueMaterial);
				drawTick3D(ln);
	     	}
    	 }
    	 else if ( axesYupCheckItem.isSelected() || axesXYonlyCheckItem.isSelected() ) {
 	    	// Build X axis ticks
 	    	double tlx = (TICK_MARK_LENGTH * (maxX - minX)) / AXIS_LENGTH;
	     	double spacingX = setSpacing(maxX);
	     	for (double i = minX; i <= maxX; i += spacingX) {
	    		if (i == 0.0) {
	    			continue;
	    		}
	    		double x1 = i;
	    		double y1 = -tlx/2.0;
	    		double z1 = 0.0;
	    		double x2 = i;
	    		double y2 = tlx/2.0;
	    		double z2 = 0.0;
				Line3D ln = new Line3D(new Point3D(x1, y1, z1), new Point3D(x2, y2, z2), redMaterial);
				drawTick3D(ln);
 		 	}

 	    	// Build Y axis ticks
 		 	double tly = (TICK_MARK_LENGTH * (maxY - minY)) / AXIS_LENGTH;
	     	double spacingY = setSpacing(maxY);
	     	for (double i = minY; i <= maxY; i += spacingY) {
	    		if (i == 0.0) {
	    			continue;
	    		}
	    		double x1 = -tly/2.0;
	    		double y1 = i;
	    		double z1 = 0.0;
	    		double x2 = tly/2.0;
	    		double y2 = i;
	    		double z2 = 0.0;
				Line3D ln = new Line3D(new Point3D(x1, y1, z1), new Point3D(x2, y2, z2), greenMaterial);
				drawTick3D(ln);
 	     	}

 	    	// Build Z axis ticks
	     	if (!axesXYonlyCheckItem.isSelected()) {
	 	     	double tlz = (TICK_MARK_LENGTH * (maxZ - minZ)) / AXIS_LENGTH;
		     	double spacingZ = setSpacing(maxZ);
		     	for (double i = minZ; i <= maxZ; i += spacingZ) {
		    		if (i == 0.0) {
		    			continue;
		    		}
		    		double y1 = -tlz/2.0;
		    		double z1 = i;
		    		double x1 = 0.0;
		    		double y2 = tlz/2.0;
		    		double z2 = i;
		    		double x2 = 0.0;
					Line3D ln = new Line3D(new Point3D(x1, y1, z1), new Point3D(x2, y2, z2), blueMaterial);
					drawTick3D(ln);
		    	}
	     	}
    	 }
    	 else {
    		 System.out.println("Build TickMarks error");
    	 }

        objectGroup.getChildren().addAll(tickGroup);
    }

	/**
	 * Computes the spacing for tick marks based on the parameter d
	 * @param d	parameter indicating the approximate maximum coordinate value
	 * 			for an axis
	 * @return	s	value indicating tick mark spacing on a coordinate axis
	 */
	private double setSpacing(double d) {
    	double s = 1.0;
    	if (d <= 1.0) {
    		s = 0.1;
    	}
    	else if (d <= 5.0) {
    		s = 0.5;
    	}
    	else if (d <= 20.0) {
    		s = 1.0;
    	}
    	else if (d <= 40.0) {
    		s = 5.0;
    	}
    	else {
    		s = 10.0;
    	}
    	return s;
    }

    /**
     * Rebuilds the Axes, TickMarks, Boundary Cube, and Default Grid in cases where the range
     * of values for the user xyz space may have changed, or when the view must be relocated
     * to a new origin.
     */

    private void reBuildAxesTicks() {
    	// Clear current axes/ticks
    	axisGroup.setVisible(false);
    	tickGroup.setVisible(false);
    	boundaryCubeGroup.setVisible(false);
    	defaultGridGroup.setVisible(false);

    	// Rebuild axes/ticks
		buildAxes();
		buildTickMarks();
		buildBoundaryCube();
		buildDefaultGrid();

		if (axesCheckItem.isSelected()) {
			axisGroup.setVisible(true);

			if (tickCheckItem.isSelected()) {
				tickGroup.setVisible(true);
			}
			else {
				tickGroup.setVisible(false);
			}
		}
		else {
			axisGroup.setVisible(false);
			tickGroup.setVisible(false);
			tickCheckItem.setSelected(false);
		}

		if (cubeCheckItem.isSelected()) {
			boundaryCubeGroup.setVisible(true);
		}
		else {
			boundaryCubeGroup.setVisible(false);
		}

		if (gridCheckItem.isSelected()) {
			defaultGridGroup.setVisible(true);
		}
		else {
			defaultGridGroup.setVisible(false);
		}
	}

	/**
	 * Builds the boundary cube showing the extent of the current user space.
	 *
	 * Visibility of the boundary cube can be selected or de-selected with a View Menu option
	 * or with the setShowBoundaryCube() method. Changes made with View Menu options apply
	 * only to the currently displayed view.
	 */
    private void buildBoundaryCube() {

    	boundaryCubeGroup = new Group();

		drawBoundary3D(new Line3D( maxX, maxY, maxZ, minX, maxY, maxZ), boundaryMaterial);
		drawBoundary3D(new Line3D( maxX, maxY, maxZ, maxX, minY, maxZ), boundaryMaterial);
		drawBoundary3D(new Line3D( maxX, maxY, maxZ, maxX, maxY, minZ), boundaryMaterial);

		drawBoundary3D(new Line3D( minX, minY, minZ, maxX, minY, minZ), boundaryMaterial);
		drawBoundary3D(new Line3D( minX, minY, minZ, minX, maxY, minZ), boundaryMaterial);
		drawBoundary3D(new Line3D( minX, minY, minZ, minX, minY, maxZ), boundaryMaterial);

		drawBoundary3D(new Line3D( maxX, maxY, minZ, maxX, minY, minZ), boundaryMaterial);
		drawBoundary3D(new Line3D( maxX, maxY, minZ, minX,  maxY,minZ), boundaryMaterial);

		drawBoundary3D(new Line3D( minX, minY, maxZ, minX, maxY, maxZ), boundaryMaterial);
		drawBoundary3D(new Line3D( minX, minY, maxZ, maxX, minY, maxZ), boundaryMaterial);

		drawBoundary3D(new Line3D( minX, maxY, maxZ, minX, maxY, minZ), boundaryMaterial);
		drawBoundary3D(new Line3D( maxX, minY, maxZ, maxX, minY, minZ), boundaryMaterial);

        objectGroup.getChildren().addAll(boundaryCubeGroup);
    }

    /**
     * Draws the Boundary Cube lines delimiting the user coordinate space
     * @param ln	A Line3D object marking one of the boundaries
     * @param m		PhongMaterial value determining the color of the boundary line
     */
	private void drawBoundary3D(Line3D ln, PhongMaterial m) {
	    Line3D lnt;
		lnt = new Line3D(new Point3D(dX(ln.getp1().getX()), dZ(ln.getp1().getZ()), dY(ln.getp1().getY())),
						 new Point3D(dX(ln.getp2().getX()), dZ(ln.getp2().getZ()), dY(ln.getp2().getY())));

	    // Compute length of line segment
		double r = lnt.length();
		// Compute mid-point between points
		Point3D mp = lnt.midpoint();
		// Compute spherical angles for mid-point oriented line
		double t = lnt.theta()*180.0/Math.PI;
		double p = lnt.phi()*180.0/Math.PI;
		// Create cylinder of required length
		Cylinder c = new Cylinder(BOUNDRY_RADIUS, r);
		c.setMaterial(m);
		// Transform cylinder translating center to computed mid-point and rotating as required
    	c.getTransforms().addAll(new Translate(origin[0]+mp.getY(), origin[1]+mp.getZ(), origin[2]+mp.getX()),
		         new Rotate(t-90,Rotate.Y_AXIS),
		         new Rotate(-p,Rotate.Z_AXIS));
		boundaryCubeGroup.getChildren().add(c);
	}

	/**
	 * Builds the default grid showing grid lines in the current user space.
	 *
	 * Visibility of the default grid can be selected or de-selected with a View Menu option.
	 * Changes made with View Menu options apply only to the currently displayed view.
	 * Non-default grid can be displayed using the public drawGrid method.
	 */
    private void buildDefaultGrid() {

    	defaultGridGroup = new Group();

    	int div = 4;
    	PhongMaterial m = new PhongMaterial(Color.LIGHTGRAY);

	    double loX = -getXMinMax();
	    double hiX =  getXMinMax();
	    double loY = -getYMinMax();
	    double hiY =  getYMinMax();
	    double loZ = -getZMinMax();
	    double hiZ =  getZMinMax();

	    double incrX = (hiX - loX) / div;
	    double incrY = (hiY - loY) / div;
	    double incrZ = (hiZ - loZ) / div;

	    double xv = 0;
	    double yv = 0;
	    double zv = 0;

	    // Draw lines perpendicular to the xy plane
	   	yv = loY;
	    for (int i = 0; i < div + 1; i++) {
	    	xv = loX;
	    	for (int j = 0; j < div + 1; j++) {
	    		defaultGridGroup.getChildren().add(line3DtoCylinder(new Line3D(new Point3D(xv, yv, loZ), new Point3D(xv, yv, hiZ)), m));
	    		xv += incrX;
	    	}
	    	yv += incrY;
	    }

	    // Draw lines perpendicular to the xz plane
	   	zv = loZ;
	   	for (int i = 0; i < div + 1; i++) {
	   		xv = loX;
	    	for (int j = 0; j < div + 1; j++) {
	    		defaultGridGroup.getChildren().add(line3DtoCylinder(new Line3D(new Point3D(xv, loY, zv), new Point3D(xv, hiY, zv)), m));
	    		xv += incrX;
	    	}
	    	zv += incrZ;
	   	}

	    // Draw lines perpendicular to the yz plane
	   	zv = loZ;
	   	for (int i = 0; i < div + 1; i++) {
	   		yv = loY;
	    	for (int j = 0; j < div + 1; j++) {
	    		defaultGridGroup.getChildren().add(line3DtoCylinder(new Line3D(new Point3D(loX, yv, zv), new Point3D(hiX, yv, zv)), m));
	    		yv += incrY;
	    	}
	    	zv += incrZ;
	   	}

    	objectGroup.getChildren().addAll(defaultGridGroup);
    }

    /**
     * Converts a Line3D object to the corresponding JavaFX Cylinder object for drawing.
     *
     * @param	ln	Line3D object
     * @param	m	PhongMaterial - color of the line
     *
     * @return	c	Cylinder object
     */
    private Cylinder line3DtoCylinder(Line3D ln, PhongMaterial m) {
	    Line3D lnt;			// Line with pixel coordinates
		lnt = new Line3D(new Point3D(dX(ln.getp1().getX()), dZ(ln.getp1().getZ()), dY(ln.getp1().getY())),
						 new Point3D(dX(ln.getp2().getX()), dZ(ln.getp2().getZ()), dY(ln.getp2().getY())));

	    // Compute length of line segment
		double r = lnt.length();
		// Compute mid-point between points
		Point3D mp = lnt.midpoint();
		// Compute spherical angles for mid-point oriented line
		double t = lnt.theta()*180.0/Math.PI;
		double p = lnt.phi()*180.0/Math.PI;
		// Create cylinder of required length
		Cylinder c = new Cylinder(BOUNDRY_RADIUS, r);
		c.setMaterial(m);
		// Transform cylinder translating center to computed mid-point and rotating as required
    	c.getTransforms().addAll(new Translate(origin[0]+mp.getY(), origin[1]+mp.getZ(), origin[2]+mp.getX()),
		         new Rotate(t-90,Rotate.Y_AXIS),
		         new Rotate(-p,Rotate.Z_AXIS));
    	return c;
    }

    /**
     * Draws grid lines in the user coordinate space.
     *
     * @param sector	int	code indicating the grid sector to draw
     * 				0 - grid lines across all sectors
     *				1 - +X, +Y, +Z sector
     *				2 - +X, -Y, +Z sector
     *				3 - -X, -Y, +Z sector
     *				4 - -X, +Y, +Z sector
     *				5 - +X, +Y, -Z sector
     *				6 - +X, -Y, -Z sector
     *				7 - -X, -Y, -Z sector
     *				8 - -X, +Y, -Z sector
     * @param	div		int specification of the number of divisions of the grid space
     *
     * @param	m		PhongMaterial value determining the color of the boundary line
     */
	public void drawGrid(int sector, int div, PhongMaterial m) {
	    double loX = 0;
	    double hiX = 0;
	    double loY = 0;
	    double hiY = 0;
	    double loZ = 0;
	    double hiZ = 0;

	    switch (sector) {
	    	case 0:	// All sectors
	    		loX = -getXMinMax();
	    		hiX =  getXMinMax();
	    		loY = -getYMinMax();
	    		hiY =  getYMinMax();
	    		loZ = -getZMinMax();
	    		hiZ =  getZMinMax();
	    		break;
	    	case 1:	// +X, +Y, +Z sector
	    		loX = 0;
	    		hiX = getXMinMax();
	    		loY = 0;
	    		hiY = getYMinMax();
	    		loZ = 0;
	    		hiZ = getZMinMax();
	    		break;
	    	case 2:	// +X, -Y, +Z sector
	    		loX =  0;
	    		hiX =  getXMinMax();
	    		loY = -getYMinMax();
	    		hiY =  0;
	    		loZ =  0;
	    		hiZ =  getZMinMax();
	    		break;
	    	case 3:	// -X, -Y, +Z sector
	    		loX = -getXMinMax();
	    		hiX =  0;
	    		loY = -getYMinMax();
	    		hiY =  0;
	    		loZ =  0;
	    		hiZ =  getZMinMax();
	    		break;
	    	case 4:	// -X, +Y, +Z sector
	    		loX = -getXMinMax();
	    		hiX =  0;
	    		loY =  0;
	    		hiY =  getYMinMax();
	    		loZ =  0;
	    		hiZ =  getZMinMax();
	    		break;
	    	case 5:	// +X, +Y, -Z sector
	    		loX =  0;
	    		hiX =  getXMinMax();
	    		loY =  0;
	    		hiY =  getYMinMax();
	    		loZ = -getZMinMax();
	    		hiZ =  0;
	    		break;
	    	case 6:	// +X, -Y, -Z sector
	    		loX =  0;
	    		hiX =  getXMinMax();
	    		loY = -getYMinMax();
	    		hiY =  0;
	    		loZ = -getZMinMax();
	    		hiZ =  0;
	    		break;
	    	case 7:	// -X, -Y, -Z sector
	    		loX = -getXMinMax();
	    		hiX =  0;
	    		loY = -getYMinMax();
	    		hiY =  0;
	    		loZ = -getZMinMax();
	    		hiZ =  0;
	    		break;
	    	case 8:	// -X, +Y, -Z sector
	    		loX = -getXMinMax();
	    		hiX =  0;
	    		loY =  0;
	    		hiY =  getYMinMax();
	    		loZ = -getZMinMax();
	    		hiZ =  0;
	    		break;
	    }

	    double incrX = (hiX - loX) / div;
	    double incrY = (hiY - loY) / div;
	    double incrZ = (hiZ - loZ) / div;

	    double xv = 0;
	    double yv = 0;
	    double zv = 0;

	    // Draw lines perpendicular to the xy plane
	   	yv = loY;
	    for (int i = 0; i < div + 1; i++) {
	    	xv = loX;
	    	for (int j = 0; j < div + 1; j++) {
	    		drawLine3D(new Line3D(new Point3D(xv, yv, loZ), new Point3D(xv, yv, hiZ), m));
	    		xv += incrX;
	    	}
	    	yv += incrY;
	    }

	    // Draw lines perpendicular to the xz plane
	   	zv = loZ;
	   	for (int i = 0; i < div + 1; i++) {
	   		xv = loX;
	    	for (int j = 0; j < div + 1; j++) {
	    		drawLine3D(new Line3D(new Point3D(xv, loY, zv), new Point3D(xv, hiY, zv), m));
	    		xv += incrX;
	    	}
	    	zv += incrZ;
	   	}

	    // Draw lines perpendicular to the yz plane
	   	zv = loZ;
	   	for (int i = 0; i < div + 1; i++) {
	   		yv = loY;
	    	for (int j = 0; j < div + 1; j++) {
	    		drawLine3D(new Line3D(new Point3D(loX, yv, zv), new Point3D(hiX, yv, zv), m));
	    		yv += incrY;
	    	}
	    	zv += incrZ;
	   	}
	}

    /**
     * Sets the scale associated with a view.
     *
     * @param	scaleX	Scale value for x coordinate.
     * @param	scaleY	Scale value for y coordinate.
     * @param	scaleZ	Scale value for z coordinate.
	 */
	public void setScale(double scaleX, double scaleY, double scaleZ) {
        objectGroup.setScaleX(scaleX);
        objectGroup.setScaleY(scaleY);
        objectGroup.setScaleZ(scaleZ);
        saveScale(currentSequenceNum);
	}

	/**
	 * Saves the current scale values in the savedScale ArrayList
	 * associating them with the specified sequence/view number.
	 *
	 * @param seqNum	int valued sequence/view number for the saved scale values
	 */
	private void saveScale(int seqNum) {
    	double[] scaleArray = new double[3];
    	scaleArray[0] = objectGroup.getScaleX();
    	scaleArray[1] = objectGroup.getScaleY();
    	scaleArray[2] = objectGroup.getScaleZ();
    	savedScale.add(seqNum, scaleArray);
    }

	/**
	 * Gets the saved scale values from the savedScale ArrayList
	 * for the specified sequence/view number.
	 *
	 * @param seqNum	int valued sequence/view number for the saved scale values
	 */
    private void getScale(int seqNum) {
    	double[] scaleArray = savedScale.get(seqNum);
		objectGroup.setScaleX(scaleArray[0]);
		objectGroup.setScaleY(scaleArray[1]);
		objectGroup.setScaleZ(scaleArray[2]);
    }

    /**
     * Sets the camera location associated with a view.
     *
     * @param	x	Camera angle relative to the x axis in screen coordinate space
     * @param	y	Camera angle relative to the y axis in screen coordinate space
     * @param	z	Camera distance on the z axis in screen coordinate space (pixels)
     * 				Negative toward the viewer and positive going into the screen.
	 */
	public void setCamera(double x, double y, double z) {
		cameraView[0] = x;
		cameraView[1] = y;
		cameraView[2] = z;
        cameraXform.rx.setAngle(x);
        cameraXform.ry.setAngle(y);
        camera.setTranslateZ(z);
        saveCamera(currentSequenceNum);

	}

	/**
	 * Saves camera location settings in the savedCamera ArrayList
	 * associating it with the specified sequence/view number.
	 *
	 * @param seqNum	int valued sequence/view number for the saved camera values
	 */
	private void saveCamera(int seqNum) {
    	double[] cameraArray = new double[3];
		cameraArray[0] = cameraView[0];
		cameraArray[1] = cameraView[1];
		cameraArray[2] = cameraView[2];
    	savedCamera.add(seqNum, cameraArray);
    }

	/**
	 * Gets the saved values of cameraView from the savedCamera ArrayList
	 * entry for the specified sequence/view number
	 *
	 * @param seqNum	int valued sequence/view number for the saved camera values
	 */
    private void getCamera(int seqNum) {
		cameraView = Arrays.copyOf(savedCamera.get(seqNum),  savedCamera.get(seqNum).length);
        cameraXform.rx.setAngle(cameraView[0]);
        cameraXform.ry.setAngle(cameraView[1]);
        camera.setTranslateZ(cameraView[2]);
    }

    /**
     * Sets the default origin (center) x, y, z coordinates in screen space (pixels).
     * Each view in a sequence may have a different default origin. The general default is: DEFAULT_ORIGIN.
     * Dynamic user changes to the view origin using the arrow keys do NOT change the default origin settings
     * associated with a view.
     *
     * @param	x	int value for the origin (center) x coordinate in pixels
     * @param	y	int value for the origin (center) y coordinate in pixels
     * @param	z	int value for the origin (center) z coordinate in pixels
     */
	public void setOriginView(int x, int y, int z) {
//		System.out.println("/ncurrent origin= " + origin[0] + ", " + origin[1] + ", " + origin[2]);
		originView = new int[] {x, y, z};
		origin = Arrays.copyOf(originView,  originView.length);
    	saveOrigin(currentSequenceNum);
	}

	/**
	 * Saves the current values of originView X, Y, Z screen coordinates (pixels)
	 * in the savedOrigin ArrayList associating them with the specified sequence/view number.
	 *
	 * @param seqNum	int valued sequence/view number for the saved origin values
	 */
	private void saveOrigin(int seqNum) {
    	int[] originArray = new int[3];
		originArray[0] = originView[0];
		originArray[1] = originView[1];
		originArray[2] = originView[2];
    	savedOrigin.add(seqNum, originArray);
    }

	/**
	 * Gets the saved values of originView X, Y, Z screen coordinates (pixels)
	 * from the savedOrigin ArrayList entry for the specified sequence/view number
	 *
	 * @param seqNum	int valued sequence/view number for the saved origin values
	 */
    private void getOrigin(int seqNum) {
    	int[] originArray = savedOrigin.get(seqNum);
		originView 	= Arrays.copyOf(originArray,  originArray.length);
		origin 			= Arrays.copyOf(originArray,  originArray.length);
    }

    /**
     * Sets the user coordinate range for x, y, and z dimensions. Each dimension can be different.
     * For each dimension, the minimum and maximum must have the same absolute value.
     * Each view in a sequence may have a different range.
     *
     * @param	minmaxX	Double minimum and maximum value for the x dimension
     * @param	minmaxY	Double minimum and maximum value for the y dimension
     * @param	minmaxZ	Double minimum and maximum value for the z dimension
     */
    public void setXYZRange(double minmaxX, double minmaxY, double minmaxZ) {
    	minX = -minmaxX;
    	maxX =  minmaxX;
    	minY = -minmaxY;
    	maxY =  minmaxY;
    	minZ = -minmaxZ;
    	maxZ =  minmaxZ;
    	saveRange(currentSequenceNum);

  		objectGroup.getChildren().remove(axisGroup);
  		if (axesCheckItem.isSelected()) {
//  			objectGroup.getChildren().remove(axisGroup);
    		buildAxes();
    	}

  		objectGroup.getChildren().remove(tickGroup);
    	if (axesCheckItem.isSelected() && tickCheckItem.isSelected()) {
//    		objectGroup.getChildren().remove(tickGroup);
    		buildTickMarks();
    	}
    }

	/**
	 * Saves the current values of minX, maxX, minY, maxY, minX, and maxX
	 * in the savedRange ArrayList associating them with the specified sequence/view number.
	 *
	 * @param seqNum	int valued sequence/view number for the saved range values
	 */
	private void saveRange(int seqNum) {
    	double[] rangeArray = new double[6];
		rangeArray[0] = minX;
		rangeArray[1] = maxX;
		rangeArray[2] = minY;
		rangeArray[3] = maxY;
		rangeArray[4] = minZ;
		rangeArray[5] = maxZ;
    	savedRange.add(seqNum,  rangeArray);
//    	System.out.println("saveRange seqNum= " + seqNum +
//    						", Ranges: " + maxX + ", " + maxY + ", " + maxZ);
    }

	/**
	 * Gets the saved values of minX, maxX, minY, maxY, minX, and maxX
	 * from the savedRange ArrayList entry for the specified sequence/view number
	 *
	 * @param seqNum	int valued sequence/view number for the saved range values
	 */
    private void getRange(int seqNum) {
    	double[] rangeArray = savedRange.get(seqNum);
		minX = rangeArray[0];
		maxX = rangeArray[1];
		minY = rangeArray[2];
		maxY = rangeArray[3];
		minZ = rangeArray[4];
		maxZ = rangeArray[5];
//    	System.out.println("getRange seqNum= " + seqNum +
//				", Ranges: " + maxX + ", " + maxY + ", " + maxZ);
    }

	/**
	 * Gets the current minimum/maximum value for the X coordinate in user space.
	 *
	 * @return maxX	double current minimum/maximum value for the X coordinate
	 */
    public double getXMinMax() {
    	return maxX;
    }

	/**
	 * Gets the current minimum/maximum value for the Y coordinate in user space.
	 *
	 * @return maxY	double current minimum/maximum value for the Y coordinate
	 */
    public double getYMinMax() {
    	return maxY;
    }

	/**
	 * Gets the current minimum/maximum value for the Z coordinate in user space.
	 *
	 * @return maxZ	double current minimum/maximum value for the Z coordinate
	 */
    public double getZMinMax() {
    	return maxZ;
    }

    /**
	 * Saves the current values of the View Menu Settings in the savedViewSettings ArrayList
	 * associating them with the specified sequence/view number. Changes to these settings
	 * can be effected either via the View Menu or via the associated methods.
	 *
	 * @param seqNum	int valued sequence/view number for the saved View Menu settings
     */
    private void saveViewSettings(int seqNum) {
//  	System.out.println("\nsaveViewSettings seqNum= " + seqNum);
    	boolean[] viewSettings = new boolean[9];
        viewSettings[0] = axesCheckItem.isSelected();
        viewSettings[1] = tickCheckItem.isSelected();
        viewSettings[2] = axesZupCheckItem.isSelected();
        viewSettings[3] = cubeCheckItem.isSelected();
        viewSettings[4] = alightCheckItem.isSelected();
        viewSettings[5] = plightCheckItem.isSelected();
        viewSettings[6] = cumulate;
        viewSettings[7] = gridCheckItem.isSelected();
        viewSettings[8] = axesXYonlyCheckItem.isSelected();

        savedViewSettings.add(seqNum, viewSettings);

//      System.out.println(
//    					"\n axesCheckItem= " + viewSettings[0] +
//    					"\n tickCheckItem= " + viewSettings[1] +
//    					"\n axesYupCheckItem= " + !viewSettings[2] +
//    					"\n axesZupCheckItem= " + viewSettings[2] +
//    					"\n cubeCheckItem= " + viewSettings[3] +
//    					"\n alightCheckItem= " + viewSettings[4] +
//    					"\n plightCheckItem= " + viewSettings[5] +
//     					"\n cumulate= " + viewSettings[6] +
//						"\n gridCheckItem= " + viewSettings[7]
//        				"\n axesXYonlyCheckItem= " + viewSettings[8];
    }

    /**
	 * Gets the saved values of the View Menu Settings from the savedViewSettings ArrayList
	 * entry for the specified sequence/view number. Changes to these settings
	 * can be effected either via the View Menu or via the associated methods.
	 *
	 * @param seqNum	int valued sequence/view number for the saved View Menu settings
     */
    private void getViewSettings(int seqNum) {
//  	System.out.println("\ngetViewSettings seqNum= " + seqNum);
    	boolean[] viewSettings = savedViewSettings.get(seqNum);
    	axesCheckItem.setSelected(viewSettings[0]);
    	tickCheckItem.setSelected(viewSettings[1]);
    	axesYupCheckItem.setSelected(!viewSettings[2]);
    	axesZupCheckItem.setSelected(viewSettings[2]);
    	cubeCheckItem.setSelected(viewSettings[3]);
    	alightCheckItem.setSelected(viewSettings[4]);
    	setAmbientLight(viewSettings[4],false);
    	plightCheckItem.setSelected(viewSettings[5]);
    	setPointLight(viewSettings[5],false);
    	cumulate = viewSettings[6];
    	gridCheckItem.setSelected(viewSettings[7]);
    	axesXYonlyCheckItem.setSelected(viewSettings[8]);

//  	System.out.println(
//    					"\n axesCheckItem= " + axesCheckItem.isSelected() +
//    					"\n tickCheckItem= " + tickCheckItem.isSelected() +
//    					"\n axesYupCheckItem= " + axesYupCheckItem.isSelected() +
//     	  		  		"\n axesZupCheckItem= " + axesZupCheckItem.isSelected() +
//    		  			"\n cubeCheckItem= " + cubeCheckItem.isSelected() +
//    					"\n alightCheckItem= " + alightCheckItem.isSelected() +
//    					"\n plightCheckItem= " + plightCheckItem.isSelected() +
//						"\n cumulate = " + cumulate +
//						"\n gridCheckItem= " + gridCheckItem.isSelected() );
//						"\n axesXYonlyCheckItem= " + axesXYonlyCheckItem.isSelected();
    }

    /**
 	 * Sets the View Menu Settings from the View Settings array
 	 *
 	 * @param 	viewSettings	boolean array of view settings
      */
     	private void setViewSettings(boolean[] viewSettings) {
//     		System.out.println("\nsetViewSettings");
//     		System.out.println(
//     				"\n axesCheckItem= " + viewSettings[0] +
//     				"\n tickCheckItem= " + viewSettings[1] +
//     				"\n axesYupCheckItem= " + !viewSettings[2] +
//     				"\n axesZupCheckItem= " + viewSettings[2] +
//     				"\n cubeCheckItem= " + viewSettings[3] +
//     				"\n alightCheckItem= " + viewSettings[4] +
//     				"\n plightCheckItem= " + viewSettings[5] +
//     				"\n cumulate= " + viewSettings[6] +
//     				"\n gridCheckItem= " + viewSettings[7] +
//		     		"\n axesXYonlyCheckItem= " + viewSettings[8]);

     		axesCheckItem.setSelected(viewSettings[0]);
     		tickCheckItem.setSelected(viewSettings[1]);
     		axesYupCheckItem.setSelected(!viewSettings[2]);
     		axesZupCheckItem.setSelected(viewSettings[2]);
     		cubeCheckItem.setSelected(viewSettings[3]);
     		alightCheckItem.setSelected(viewSettings[4]);
     		setAmbientLight(viewSettings[4],false);
     		plightCheckItem.setSelected(viewSettings[5]);
     		setPointLight(viewSettings[5],false);
     		cumulate = viewSettings[6];
     		gridCheckItem.setSelected(viewSettings[7]);
     		axesXYonlyCheckItem.setSelected(viewSettings[8]);
     		saveViewSettings(currentSequenceNum);
     }

    /**
     * Draws a test set of eight vectors, one in each quadrant of the user coordinate space.
     */
	public void buildTest() {
        drawVector3D(new Vector3D( maxX/2.0, minY/2.0, maxZ/2.0, salmonMaterial));
        drawVector3D(new Vector3D( maxX/2.0, minY/2.0, minZ/2.0, turquoiseMaterial));
        drawVector3D(new Vector3D( minX/2.0, minY/2.0, maxZ/2.0, magentaMaterial));
        drawVector3D(new Vector3D( minX/2.0, minY/2.0, minZ/2.0, goldMaterial));

        drawVector3D(new Vector3D( minX/2.0,  maxY/2.0, minZ/2.0, salmonMaterial));
        drawVector3D(new Vector3D( minX/2.0,  maxY/2.0, maxZ/2.0, turquoiseMaterial));
        drawVector3D(new Vector3D( maxX/2.0,  maxY/2.0, minZ/2.0, magentaMaterial));
        drawVector3D(new Vector3D( maxX/2.0,  maxY/2.0, maxZ/2.0, goldMaterial));
    }

	/**
	 * Sets up keyboard handlers for various key triggered events.
	 * @param scene	Draw3D Scene Group
	 */
	private void handleKeyboard(Scene scene) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
//            	System.out.println("ViewNumber=" + viewNumber + ", Key Event Code= " + event.getCode());
            	Group aGroup;
                switch (event.getCode()) {
                case B:
//                	System.out.println("viewNumber: " + viewNumber + ", case B");
            		for (Task<Object> task : algorithmTaskList) {
            			System.out.println("Task cancel: " + task);
            			task.cancel();
            		}
            		algorithmTaskList.clear();
                	if (viewNumber > 0) {
                    	for (int i = viewNumber; i < dataGroup.size(); i++ ) {
                    		aGroup = dataGroup.get(i);
                    		aGroup.setVisible(false);
                    	}
                		viewNumber--;
                    	getCamera(viewNumber);
                    	getOrigin(viewNumber);
                		getRange(viewNumber);
                		getScale(viewNumber);
                    	getViewSettings(viewNumber);
                     	aGroup = dataGroup.get(viewNumber);
                    	aGroup.setVisible(true);
                   		reBuildAxesTicks();
//                     	reDrawList();
                	}
                	else {
                		System.out.println("No lower numbered dataGroups; viewNumber= " + viewNumber);
                	}
                    break;
                case C:
                	manualCumulate = !manualCumulate;
                	System.out.println("Manual cumulate toggled - now= " + manualCumulate);
            		break;
                case F:
                case SPACE:
//                	System.out.println("viewNumber: " + viewNumber + ", case F");
            		for (Task<Object> task : algorithmTaskList) {
            			System.out.println("Task cancel: " + task);
            			task.cancel();
            		}
            		algorithmTaskList.clear();
                	if (viewNumber < dataGroup.size()-1) {
                    	viewNumber++;
                    	getCamera(viewNumber);
                    	getOrigin(viewNumber);
                		getRange(viewNumber);
                		getScale(viewNumber);
                    	getViewSettings(viewNumber);
                		if (!manualCumulate) {
                   			if (!cumulate) {
	                        	for (int i = 0; i < dataGroup.size(); i++ ) {
	                        		aGroup = dataGroup.get(i);
	                        		aGroup.setVisible(false);
	                        	}
                   			}
                		}
                     	aGroup = dataGroup.get(viewNumber);
                     	aGroup.setVisible(true);
                    	reBuildAxesTicks();
                     	reDrawList();

                     	ArrayList<String> classList = viewClassList.get(viewNumber);
                     	ArrayList<Algorithm> algorithmList = viewAlgorithmList.get(viewNumber);

                      	if (!algorithmList.isEmpty()) {
                     		dataGroup.get(viewNumber).getChildren().clear();	// Enables algorithm rerun
                     		algorithmTaskList.clear();
                      		for (Algorithm algorithm: algorithmList) {
                      			algorithmTaskList.add(buildAlgorithmTask(algorithm));
                      			if (algorithm.isDrone()) {
                      				dataGroup.get(viewNumber+algorithm.getId()+1).setVisible(true);
                      			}
		                	}
			            	for (int i = 0; i < algorithmTaskList.size(); i++) {
                            	System.out.println("Run: " + classList.get(i) + ", viewNumber: " + viewNumber);
                        		Executors.newSingleThreadExecutor().execute(algorithmTaskList.get(i));
                     		}
			            	algorithmTaskList.clear();
                		}
                	}
                	else {
                		System.out.println("No additional dataGroups; viewNumber= " + viewNumber);
                	}
                    break;
                case R:
    	            objectGroup.setScaleX(1.0);
    	            objectGroup.setScaleY(1.0);
    	            objectGroup.setScaleZ(1.0);
                	getCamera(viewNumber);
                	getOrigin(viewNumber);
            		getRange(viewNumber);
            		getScale(viewNumber);
                   	getViewSettings(viewNumber);
                	reBuildAxesTicks();
                	reDrawList();
                	break;
                case T:
                	currentSequenceNum = viewNumber;
                	buildTest();
                	break;
                case Z:
                	viewNumber = 0;
                	for (int i = 0; i < dataGroup.size(); i++ ) {
                		aGroup = dataGroup.get(i);
                		aGroup.setVisible(false);
                		aGroup.getChildren().clear();
                	}
                	getCamera(viewNumber);
                	getOrigin(viewNumber);
            		getRange(viewNumber);
            		getScale(viewNumber);
                   	getViewSettings(viewNumber);
                	aGroup = dataGroup.get(viewNumber);
                	aGroup.setVisible(true);
            		reBuildAxesTicks();
            		reDrawList();
                	System.out.println("Make " + viewNumber + " Visible");
                	break;
                case X:
                	System.out.println("viewNumber= " + viewNumber);
                	for (int i = 0; i <= viewNumber; i++ ) {
                		aGroup = dataGroup.get(i);
                		aGroup.setVisible(false);
                		aGroup.getChildren().clear();
                	}
                	if (viewNumber < dataGroup.size()-1) {
                    	viewNumber++;
                    	getCamera(viewNumber);
                    	getOrigin(viewNumber);
                		getRange(viewNumber);
                		getScale(viewNumber);
                    	getViewSettings(viewNumber);
                    	aGroup = dataGroup.get(viewNumber);
	                	aGroup.setVisible(true);
                    	reBuildAxesTicks();
	                }
                	break;
                case RIGHT:
                	origin[0] += 10;
                	reBuildAxesTicks();
                	reDrawList();
                	break;
                case LEFT:
                	origin[0] -= 10;
                	reBuildAxesTicks();
                	reDrawList();
                	break;
                case UP:
                	origin[1] += 10;
                	reBuildAxesTicks();
                	reDrawList();
                	break;
                case DOWN:
                	origin[1] -= 10;
                	reBuildAxesTicks();
                	reDrawList();
                	break;
        		case ADD:
    	            objectGroup.setScaleX(objectGroup.getScaleX() + SCROLL_DELTA*SCROLL_FACTOR);
    	            objectGroup.setScaleY(objectGroup.getScaleY() + SCROLL_DELTA*SCROLL_FACTOR);
    	            objectGroup.setScaleZ(objectGroup.getScaleZ() + SCROLL_DELTA*SCROLL_FACTOR);
    	            break;
                case SUBTRACT:
    	            objectGroup.setScaleX(objectGroup.getScaleX() - SCROLL_DELTA*SCROLL_FACTOR);
    	            objectGroup.setScaleY(objectGroup.getScaleY() - SCROLL_DELTA*SCROLL_FACTOR);
    	            objectGroup.setScaleZ(objectGroup.getScaleZ() - SCROLL_DELTA*SCROLL_FACTOR);
    	            break;
    	        // keys to experiment with moving point light source
                case OPEN_BRACKET:
                	plight1X = plight1X - 10;
                	System.out.println("plight1X= " + plight1X);
                	plight1.setTranslateX(plight1X);
        	        plight1.setTranslateY(plight1Y);
        	        plight1.setTranslateZ(plight1Z);
        	        break;
                case CLOSE_BRACKET:
                	plight1X = plight1X + 10;
                	System.out.println("plight1X= " + plight1X);
                	plight1.setTranslateX(plight1X);
        	        plight1.setTranslateY(plight1Y);
        	        plight1.setTranslateZ(plight1Z);
        	        break;
                case SEMICOLON:
                	plight1Y = plight1Y - 10;
                	System.out.println("plight1Y= " + plight1Y);
                	plight1.setTranslateX(plight1X);
        	        plight1.setTranslateY(plight1Y);
        	        plight1.setTranslateZ(plight1Z);
                	break;
                case QUOTE:
                   	plight1Y = plight1Y + 10;
                   	System.out.println("plight1Y= " + plight1Y);
                	plight1.setTranslateX(plight1X);
        	        plight1.setTranslateY(plight1Y);
        	        plight1.setTranslateZ(plight1Z);
        	        break;
                case COMMA:
                	plight1Z = plight1Z - 10;
                	System.out.println("plight1Z= " + plight1Z);
                	plight1.setTranslateX(plight1X);
        	        plight1.setTranslateY(plight1Y);
        	        plight1.setTranslateZ(plight1Z);
        	        break;
                case PERIOD:
                	plight1Z = plight1Z + 10;
                	System.out.println("plight1Z= " + plight1Z);
                	plight1.setTranslateX(plight1X);
        	        plight1.setTranslateY(plight1Y);
        	        plight1.setTranslateZ(plight1Z);
        	        break;

        	    default:
        	    	System.out.println("Undefined key");
        	    	break;
                }
            }
        });
    }

	/**
	 * Sets up mouse handlers for various mouse triggered events.
	 * @param scene	Draw3D Scene Group
	 */
	private void handleMouse(SubScene scene) {
		scene.setOnScroll(new EventHandler<ScrollEvent>() {
	        @Override public void handle(ScrollEvent se) {
	        	objectGroup.setScaleX(objectGroup.getScaleX() + se.getDeltaY()*SCROLL_FACTOR);
	            objectGroup.setScaleY(objectGroup.getScaleY() + se.getDeltaY()*SCROLL_FACTOR);
	            objectGroup.setScaleZ(objectGroup.getScaleZ() + se.getDeltaY()*SCROLL_FACTOR);
	        }
	    });

//		scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
//          @Override public void handle(MouseEvent me) {
//            	if (me.getClickCount() == 1) {
//                    clearView();
//              }
//          }
//      });

		scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent me) {
            	if (me.getClickCount() > 1) {
                    reset();
                }
            }
        });

        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent me) {
                mousePosX = me.getSceneX();
                mousePosY = me.getSceneY();
                mouseOldX = me.getSceneX();
                mouseOldY = me.getSceneY();
            }
        });

        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent me) {
                mouseOldX = mousePosX;
                mouseOldY = mousePosY;
                mousePosX = me.getSceneX();
                mousePosY = me.getSceneY();
                mouseDeltaX = -(mousePosX - mouseOldX);
                mouseDeltaY = -(mousePosY - mouseOldY);

                double modifier = 1.0;

            	if (me.isPrimaryButtonDown()) {
                    cameraXform.ry.setAngle(cameraXform.ry.getAngle() - mouseDeltaX*MOUSE_SPEED*modifier*ROTATION_SPEED);
                    cameraXform.rx.setAngle(cameraXform.rx.getAngle() + mouseDeltaY*MOUSE_SPEED*modifier*ROTATION_SPEED);
//                   System.out.println("X= " + cameraXform.rx.getAngle() +
//                    		", Y= " + cameraXform.ry.getAngle() +
//                    		", Z= " + cameraXform.rz.getAngle());
            	}
            }
        });
    }

	/**
	 * Defines a variety of PhongMaterial based colors.
	 */
	private void setColors() {

		redMaterial.setDiffuseColor(Color.DARKRED);
	    redMaterial.setSpecularColor(Color.RED);

	    greenMaterial.setDiffuseColor(Color.DARKGREEN);
	    greenMaterial.setSpecularColor(Color.GREEN);

	    blueMaterial.setDiffuseColor(Color.DARKBLUE);
	    blueMaterial.setSpecularColor(Color.BLUE);

		blackMaterial.setDiffuseColor(Color.BLACK);
		blackMaterial.setSpecularColor(Color.BLACK);

		greyMaterial.setDiffuseColor(Color.DARKGREY);
		greyMaterial.setSpecularColor(Color.GREY);

		salmonMaterial.setDiffuseColor(Color.SALMON);
		salmonMaterial.setSpecularColor(Color.LIGHTSALMON);

		turquoiseMaterial.setDiffuseColor(Color.DARKTURQUOISE);
		turquoiseMaterial.setSpecularColor(Color.TURQUOISE);

		magentaMaterial.setDiffuseColor(Color.DARKMAGENTA);
		magentaMaterial.setSpecularColor(Color.MAGENTA);

	    goldMaterial.setDiffuseColor(Color.DARKGOLDENROD);
	    goldMaterial.setSpecularColor(Color.GOLDENROD);

		boundaryMaterial = greyMaterial;
		drawMaterial = blackMaterial;
	}

    /**
     * Stops executing concurrent tasks and clears all data structure
     * content for the current view.
     */
    private void clearView() {
		System.out.println("Clear View");

		for (Task<Object> task : algorithmTaskList) {
			task.cancel();
		}
		algorithmTaskList.clear();

		dataGroup.set(viewNumber, null);
		saveDrawLists.get(viewNumber).clear();
		savedCamera.set(viewNumber, null);
		saveDrawLists.set(viewNumber, null);
		savedOrigin.set(viewNumber, null);
		savedRange.set(viewNumber, null);
		savedScale.set(viewNumber, null);
		savedViewSettings.set(viewNumber, null);

		viewClassList.set(viewNumber, null);
	    viewAlgorithmList.set(viewNumber, null);
	    viewDroneCount.set(viewNumber, 0);
	    viewDroneState.set(viewNumber, null);
	    droneIncrement = 0;
	}

    /**
     * Stops executing concurrent tasks, clears all data structures of current content
     * and re-initializes the SubScene
     */
    private void reset() {
		System.out.println("Clear");

		for (Task<Object> task : algorithmTaskList) {
			task.cancel();
		}
		algorithmTaskList.clear();
		viewDroneCount.set(viewNumber,  0);
		viewDroneState.get(viewNumber).clear();

		dataGroup.clear();
		saveDrawLists.clear();
		savedOrigin.clear();
		savedRange.clear();
		savedScale.clear();
		savedViewSettings.clear();

		viewNumber = 0;
		currentSequenceNum = 0;

		setDefaults();

		buildSubScene();
	}

    /**
     * Set defaults:
     * -View settings
     * -Origin
     * -Scale
     * -XYZ Range
     * -Camera position
     * -Drawing settings
     */
    public void setDefaults() {
        axesCheckItem.setSelected(SHOW_AXES);
        tickCheckItem.setSelected(SHOW_TICK_MARKS);
        axesYupCheckItem.setSelected(!SHOW_Z_AXIS_UP);
        axesZupCheckItem.setSelected(SHOW_Z_AXIS_UP);
        axesXYonlyCheckItem.setSelected(SHOW_X_Y_ONLY);
        cubeCheckItem.setSelected(SHOW_BOUNDARY_CUBE);
        gridCheckItem.setSelected(SHOW_DEFAULT_GRID);
        alightCheckItem.setSelected(AMBIENT_LIGHT_ON);
        plightCheckItem.setSelected(POINT_LIGHT_ON);
    	cumulate = CUMULATE_DEFAULT;
    	manualCumulate = CUMULATE_MANUAL_DEFAULT;
    	saveViewSettings(currentSequenceNum);

    	setOriginView(DEFAULT_ORIGIN[0], DEFAULT_ORIGIN[1], DEFAULT_ORIGIN[2]);
    	setScale(1, 1, 1);
    	setXYZRange(MIN_MAX_X, MIN_MAX_Y, MIN_MAX_Z);

		cameraView[0] = CAMERA_INITIAL_X_ANGLE;
		cameraView[1] = CAMERA_INITIAL_Y_ANGLE;
		cameraView[2] = CAMERA_INITIAL_DISTANCE;
        cameraXform.rx.setAngle(CAMERA_INITIAL_X_ANGLE);
        cameraXform.ry.setAngle(CAMERA_INITIAL_Y_ANGLE);
        camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
    	saveCamera(currentSequenceNum);

    	drawMaterial = blackMaterial;
        drawMode = DrawMode.FILL;
        cullFace = CullFace.NONE;
        pointSize = POINT_SIZE;
        lineRadius = LINE_RADIUS;
        arrowRadius = ARROW_RADIUS;
        arrowPointRadius = ARROW_POINT_RADIUS;
        arrowPointHeight = ARROW_POINT_HEIGHT;
        vectorRadius = VECTOR_RADIUS;
        avPointDivisions = AV_POINT_DIVISIONS;
        ovalDivisions = OVAL_DIVISIONS;
        coneDivisions = CONE_DIVISIONS;
        cylinderDivisions = CYLINDER_DIVISIONS;
        sphereDivisions = SPHERE_DIVISIONS;
        labelFontSize = LABEL_FONT_SIZE;
        labelFont = LABEL_FONT;

  		if (axesCheckItem.isSelected()) {
  			objectGroup.getChildren().remove(axisGroup);
    		buildAxes();
    	}
    	if (axesCheckItem.isSelected() && tickCheckItem.isSelected()) {
    		objectGroup.getChildren().remove(tickGroup);
    		buildTickMarks();
    	}
    }

    /**
     * Creates ambient lighting for views.
     */
    private void createAmbientLight() {
	    alight = new AmbientLight(Color.WHITE);
	    alight.getScope().add(objectGroup);
	    subSceneRoot.getChildren().add(alight);
    }

    /**
     * Activates (true value) ambient light for a view.
     *
     * @param	b		boolean value that for true turns on ambient light and for false turns it off
     */
	public void setAmbientLight(boolean b) {
		setAmbientLight(b, true);
	}

    /**
     * Activates (true value) ambient light for a view.
     *
     * @param	b		boolean value that for true turns on ambient light and for false turns it off
     * @param	save	boolean value that for true causes settings save action
     */
	private void setAmbientLight(boolean b, boolean save) {
		alightCheckItem.setSelected(b);
		alight.setLightOn(b);
//		System.out.println("\nsetAmbientLight: b= " + b + ", alightCheckItem= " + alightCheckItem.isSelected() + ", alight:=" + alight + ", save= "+ save);
//		System.out.println("alight.isLightOn= " + alight.isLightOn());

		if (save) {
			saveViewSettings(currentSequenceNum);
		}
	}

    /**
     * Creates point lighting for views.
     */
	private void createPointLight() {
        plight1 = new PointLight(Color.WHITE);
//      plight1X = sceneWidth/2;
//		plight1Y = sceneHeight/2;
//		plight1Z = -600;
		plight1X = POINT_LIGHT_1_X;
		plight1Y = POINT_LIGHT_1_Y;
		plight1Z = POINT_LIGHT_1_Z;
		plight1.getTransforms().addAll(new Translate(plight1X, plight1Y, plight1Z));

        plight2 = new PointLight(Color.WHITE);
//      plight1X = sceneWidth/2;
//		plight1Y = sceneHeight/2;
//		plight1Z = 600;
		plight2X = POINT_LIGHT_2_X;
		plight2Y = POINT_LIGHT_2_Y;
		plight2Z = POINT_LIGHT_2_Z;
		plight2.getTransforms().addAll(new Translate(plight2X, plight2Y, plight2Z));

        subSceneRoot.getChildren().addAll(plight1, plight2);
	}

    /**
     * Activates (true value) point lights for a view.
     * Two point light sources are used:
     * - one near the point forward, slightly to the left and above center
     * - the other point to the back, slightly to the right, and below center
     *
     * @param  b	boolean value that for true turns on point lights and for false turns then off
     */
	public void setPointLight(boolean b) {
		setPointLight(b, true);
	}

    /**
     * Activates (true value) point lights for a view.
     * Two point light sources are used:
     * - one near the point forward, slightly to the left and above center
     * - the other point to the back, slightly to the right, and below center
     *
     * @param	b		boolean value that for true turns on point lights and for false turns then off
     * @param	save	boolean value that for true causes settings save action
     */
	private void setPointLight(boolean b, boolean save) {
		plightCheckItem.setSelected(b);
		plight1.setLightOn(b);
		plight2.setLightOn(b);
//		System.out.println("\nsetPointLight: b= " + b + ", plightCheckItem= " + plightCheckItem.isSelected() + ", plight1= " + plight1 + ", plight2= " + plight2 + ", save= "+ save);
//		System.out.println("plight1.isLightOn= " + plight1.isLightOn() + ", plight2.isLightOn= " + plight2.isLightOn());

		if (save) {
			saveViewSettings(currentSequenceNum);
		}
	}

    /**
     * Sets the default (0) initially displayed view number in a sequence of views.
	 */
    public void setStart() {
    	setStart(0);
    }

    /**
     * Sets the initially displayed view number in a sequence of views.
     *
     * @param	num	Integer number of the initially displayed view
	 */
    public void setStart(int num) {
    	Group aGroup;
    	for (int i = 0; i < dataGroup.size(); i++ ) {
    		aGroup = dataGroup.get(i);
    		aGroup.setVisible(false);
    	}
    	viewNumber = num;
    	getCamera(viewNumber);
    	getOrigin(viewNumber);
		getRange(viewNumber);
		getScale(viewNumber);
       	getViewSettings(viewNumber);
    	reBuildAxesTicks();
    	aGroup = dataGroup.get(viewNumber);
    	aGroup.setVisible(true);
    }

    /**
     * Activates (true value) display of axes.
     *
     * @param  b	boolean value that for true turns on axes display and for false turns axes display off
     */
	public void setShowAxes(boolean b) {
		axesCheckItem.setSelected(b);
    	axisGroup.setVisible(b);
    	if (!axesCheckItem.isSelected()) {
            tickCheckItem.setSelected(false);
            tickGroup.setVisible(false);
    	}
    	saveViewSettings(currentSequenceNum);
    }

    /**
     * Activates (true value) display of tick marks.
     *
     * @param  b	boolean value that for true turns on tick mark display and for false turns tick mark display off
     */
	public void setShowTickMarks(boolean b) {
		tickCheckItem.setSelected(b);
		tickGroup.setVisible(b);
    	if (tickCheckItem.isSelected() && !axesCheckItem.isSelected()) {
    		axesCheckItem.setSelected(true);
    		axisGroup.setVisible(true);
    	}
    	saveViewSettings(currentSequenceNum);
    }

    /**
     * Activates (true value) display of coordinate axes with the positive Y axis pointing up.
     * Changing axes orientation does not change shapes which are defined independent of the
     * displayed axes orientation. Axes orientation change does alter shape location and
     * rotations whether done by invoking the setYaxisUp method or by selecting the orientation
     * on the View Menu. Changing orientation using the View Menu does not adjust Label rotations.
     *
     * @param  b	boolean value that for true turns on axes display with Y up and for false sets axes display to Z up
     */
    public void setYaxisUp(boolean b) {
    	axesYupCheckItem.setSelected(b);
    	axesZupCheckItem.setSelected(!b);
    	axesXYonlyCheckItem.setSelected(false);
    	saveViewSettings(currentSequenceNum);
    	reBuildAxesTicks();
    }

    /**
     * Activates (true value) display of coordinate axes with the positive Y axis pointing up
     * and no Z axis displayed. Shape parameters still require x, y, and z specification.
     * Changing axes orientation does not change shapes which are defined independent of the
     * displayed axes orientation. Axes orientation change does alter shape location and
     * rotations whether done by invoking the setYaxisUp method or by selecting the orientation
     * on the View Menu. Changing orientation using the View Menu does not adjust Label rotations.
     *
     * @param  b	boolean value that for true turns on axes display with Y up and for false sets axes display to Z up
     */
    public void setXYaxesOnly(boolean b) {
    	axesYupCheckItem.setSelected(b);
    	axesZupCheckItem.setSelected(!b);
    	axesXYonlyCheckItem.setSelected(b);
    	setCamera(0.0, -180.0,  -1100.0);
    	saveViewSettings(currentSequenceNum);
    	reBuildAxesTicks();
    }

    /**
     * Activates (true value) display of coordinate axes with the positive Z axis pointing up.
     * Changing axes orientation does not change shapes which are defined independent of the
     * displayed axes orientation. Axes orientation change does alter shape location and
     * rotations whether done by invoking the setYaxisUp method or by selecting the orientation
     * on the View Menu. Changing orientation using the View Menu does not adjust Label rotations.
     *
     * @param  b	boolean value that for true turns on axes display with Z up and for false sets axes display to Y up
     */
    public void setZaxisUp(boolean b) {
    	axesYupCheckItem.setSelected(!b);
    	axesZupCheckItem.setSelected(b);
    	axesXYonlyCheckItem.setSelected(false);
    	saveViewSettings(currentSequenceNum);
    	reBuildAxesTicks();
    }

    /**
     * Activates (true value) display of the boundary cube determined by the current values
     * of minX, maxX, minY, maxY, minZ, and maxZ
     *
     * @param  b	boolean value that for true turns on boundary cube display and false turns it off
     */
	public void setShowBoundaryCube(boolean b) {
    	cubeCheckItem.setSelected(b);
    	boundaryCubeGroup.setVisible(b);
    	saveViewSettings(currentSequenceNum);
	}

    /**
     * Specifies the radius (pixels) to be used with basic drawPoint() methods.
     *
     * @param  d  radius in pixels of point objects
     */
	public void setPointSize(double d) {
		pointSize = d;
	}

    /**
     * Specifies the radius (pixels) to be used with basic drawLine() methods.
     *
     * @param  d  radius in pixels of line objects
     */
    public void setLineRadius(double d) {
    	lineRadius = d;
    }

    /**
     * Specifies the radius (pixels) to be used with basic drawArrow() methods.
     *
     * @param  d  radius in pixels of arrow objects
     */
    public void setArrowRadius(double d) {
    	arrowRadius = d;
    }

    /**
     * Specifies the radius (pixels) to be used with basic drawVector() methods.
     *
     * @param  d  radius in pixels of vector objects
     */
    public void setVectorRadius(double d) {
    	vectorRadius = d;
    }

    /**
     * Specifies the number of divisions to be used with basic drawCone() methods.
     *
     * @param  i  Number of divisions to be used in drawing cones
     */
    public void setConeDivisions(int i) {
    	coneDivisions = i;
    }

    /**
     * Specifies the number of divisions to be used with basic drawCylinder() methods.
     *
     * @param  i  Number of divisions to be used in drawing cylinders
     */
    public void setCylinderDivisions(int i) {
    	cylinderDivisions = i;
    }

    /**
     * Specifies the number of divisions to be used with basic drawOval() methods.
     *
     * @param  i  Number of divisions to be used in drawing ovals
     */
    public void setOvalDivisions(int i) {
    	ovalDivisions = i;
    }

    /**
     * Specifies the number of divisions to be used with basic drawSphere() methods.
     *
     * @param  i  Number of divisions to be used in drawing spheres
     */
    public void setSphereDivisions(int i) {
    	sphereDivisions = i;
    }

    /**
     * Sets the scene Background Color.
     *
     * @param  c  Color specification for background color
     */
    public void setBackgroundColor(Color c) {
    	backgroundColor = c;
    	subScene.setFill(backgroundColor);
    }

    /**
     * Sets the default CullFace value.
     *
     * @param  cf  default CullFace value
     */
    public void setCullFace(CullFace cf) {
    	cullFace = cf;
    }

    /**
     * Sets the default DrawMode value.
     *
     * @param  dm  default DrawMode value
     */
    public void setDrawMode(DrawMode dm) {
    	drawMode = dm;
    }

    /**
     * Creates a new default PhongMaterial object (drawMaterial) to be used in drawing all objects
     * and sets the default diffuse color.
     *
     * @param  c1  default PhongMaterial (drawMaterial) Diffuse Color is set to this value.
     */
	public void setDrawColor(Color c1) {
		drawMaterial = new PhongMaterial();
		drawMaterial.setDiffuseColor(c1);
	}

    /**
     * Creates a new default PhongMaterial object (drawMaterial) to be used in drawing all objects
     * and sets the default diffuse and specular colors.
     *
     * @param  c1  default PhongMaterial (drawMaterial) Diffuse Color is set to this value.
     * @param  c2  default PhongMaterial (drawMaterial) Specular Color is set to this value.
     */
	public void setDrawColor(Color c1, Color c2) {
		drawMaterial = new PhongMaterial();
		drawMaterial.setDiffuseColor(c1);
		drawMaterial.setSpecularColor(c2);
	}

    /**
     * Sets the default Label font to "Regular" with specified size.
     *
     * @param  d  font size
     */
	public void setLabelFontSize(double d) {
		labelFont = Font.font("Regular", d);
	}

    /**
     * Sets the default Label font and size.
     *
     * @param  f  specifies default Label font with format: Font.font("name", size)
     */
	public void setLabelFont(Font f) {
		labelFont = f;
	}

    /**
     * Sets the default PhongMaterial value.
     * The PhongMaterial diffuse.color and specular.color (if defined) values will be used by default
     *
     * @param  m  PhongMaterial
     */
	public void setMaterial(PhongMaterial m) {
		drawMaterial = m;
	}

    /**
     * Sets the default Texture value.
     * The specified Texture value will be used by default
     *
     * @param  image	Image texture to be applied
     */
	public void setTexture(Image image) {
		drawMaterial = new PhongMaterial();
		drawMaterial.setDiffuseMap(image);
	}

    /**
     * Indicates the beginning of a sequence of views with associated data objects.
     * This method is now redundant. Sequencing is always on by default.
     */
    public void setSequencingOn() {
		currentSequenceNum = 0;
	}

    /**
     * Initiates a new view and makes it immediately visible while making the
     * previous view invisible in a sequence of views with associated data objects.
     * This enables automatically saving scene/subscene contents to a file
     * during initial creation.
     */
	public void incrSequence() {
		if (droneIncrement > 0) {
			for (int i = 0; i < droneIncrement; i++) {
				Group aGroup = dataGroup.get(currentSequenceNum);
				aGroup.setVisible(false);
				currentSequenceNum++;
			}
		}
		Group aGroup = dataGroup.get(currentSequenceNum);
		aGroup.setVisible(false);
		currentSequenceNum++;
		saveCamera(currentSequenceNum);
		saveOrigin(currentSequenceNum);
		saveRange(currentSequenceNum);
		saveScale(currentSequenceNum);
		saveViewSettings(currentSequenceNum);
		aGroup = new Group();
		aGroup.setVisible(true);
		dataGroup.add(aGroup);
		objectGroup.getChildren().addAll(aGroup);
		drawList = new ArrayList<Object>();
		saveDrawLists.add(drawList);
		viewClassList.add(new ArrayList<String>());
		viewAlgorithmList.add(new ArrayList<Algorithm>());
		viewDroneCount.add(0);
		viewDroneState.add(new ArrayList<Drone3D>());
	}

	/**
	 * Activates (true value) overlay of subsequent views in the sequence of displays.
	 *
	 * @param b		boolean value indicating cumulation of views is on (true) or off (false)
	 */
	public void setCumulate(boolean b) {
//		System.out.println("\nsetCumulate b= " + b);
		cumulate = b;
		saveViewSettings(currentSequenceNum);
	}

    /**
     * Converts an x value in screen coordinate space to user space.
     *
     * @param	xs	x value to be converted
     * @return	x	coordinate value in user space
     */
    public double uX(double xs) { return xs * (maxX - minX) / AXIS_LENGTH; }

    /**
     * Converts x coordinate value from user to screen coordinate space.
     *
     * @param	xu	x coordinate to be converted
     * @return	x	coordinate value in screen coordinate space
     */
    public double dX(double xu) { return xu * AXIS_LENGTH / (maxX - minX); }

    /**
     * Converts y coordinate value from user to screen coordinate space.
     *
     * @param	yu	y coordinate to be converted
     * @return	y	coordinate value in screen coordinate space
     */
    public double dY(double yu) { return yu * AXIS_LENGTH / (maxY - minY); }

    /**
     * Converts z coordinate value from user to screen coordinate space.
     *
     * @param	zu	z coordinate to be converted
     * @return	z	coordinate value in screen coordinate space
     */
    public double dZ(double zu) { return zu * AXIS_LENGTH / (maxZ - minZ); }

    /**
     * Constructs an Arrow3D object with specified start and end points and draws it.
     * The object is drawn with the color specified by drawMaterial as set by setDrawColor().
     *
     * @param  x1  defines start point x coordinate
     * @param  y1  defines start point y coordinate
     * @param  z1  defines start point z coordinate
     * @param  x2  defines end point x coordinate
     * @param  y2  defines end point y coordinate
     * @param  z2  defines end point z coordinate
     */
    public void drawArrow(double x1, double y1, double z1, double x2, double y2, double z2) {
		Arrow3D a3d = new Arrow3D(x1, y1, z1, x2, y2, z2);
		a3d.setMaterial(drawMaterial);
		a3d.setArrowRadius(arrowRadius);
		drawArrow3D(a3d);
	}

    /**
     * Draws an Arrow3D object.
     *
     * @param  a3d  specifies an Arrow3D object with start point, end point,
     * 				x-rotation, y-rotation, z-rotation and drawing parameters.
     */
	public void drawArrow3D(Arrow3D a3d) {
    	double x1 = a3d.getp1().getX();
    	double y1 = a3d.getp1().getY();
    	double z1 = a3d.getp1().getZ();
    	double x2 = a3d.getp2().getX();
    	double y2 = a3d.getp2().getY();
    	double z2 = a3d.getp2().getZ();
    	double ar = a3d.getArrowRadius();
    	arrowPointRadius = (ar/ARROW_RADIUS)*ARROW_POINT_RADIUS;
    	arrowPointHeight = (ar/ARROW_RADIUS)*ARROW_POINT_HEIGHT;

    	PhongMaterial m = a3d.getMaterial();
    	if (m == null) {
    		m = drawMaterial;
    		a3d.setMaterial(m);
    	}
		if (errorCheck(x1, y1, z1) || errorCheck(x2, y2, z2)) {
    		return;
    	}

        Arrow3D a3dt;
    	if ( axesZupCheckItem.isSelected() ) {
    	// System.out.println("\nArrow: Z up: " +
    	//						"x1= " + x1 + ", y1= " + y1 + ", z1= " + z1 +
    	//					  ", x2= " + x2 + ", y2= " + y2 + ", z2= " + z2);
			a3dt = new Arrow3D(new Point3D(dX(x1), dY(y1), dZ(z1)),
	   		   		   		   new Point3D(dX(x2), dY(y2), dZ(z2)));
    	}
    	else {
    	// System.out.println("\nArrow: Y up: " +
    	//						"x1= " + x1 + ", y1= " + y1 + ", z1= " + z1 +
    	//					  ", x2= " + x2 + ", y2= " + y2 + ", z2= " + z2);
			a3dt = new Arrow3D(new Point3D(dZ(z1), dX(x1), dY(y1)),
	   		   		   		   new Point3D(dZ(z2), dX(x2), dY(y2)));
    	}
    	// System.out.println("a3dt= p1" + a3dt.getp1().toString() + ", p2= " + a3dt.getp2().toString());;

    	// Compute length shortening to allow for point
    	double alf = a3dt.length();
    	double alp = (alf - arrowPointHeight/3.0)/alf;
    	Arrow3D a3ds = a3dt.multiply(alp);

        // Compute length of line segment
    	double r = a3ds.length();
    	// Compute mid-point between points
    	Point3D mp = a3ds.midpoint();
    	// System.out.println("Arrow: mp= " + mp.toString());
    	// Compute spherical angles for mid-point oriented vector
    	double t = a3ds.theta()*180.0/Math.PI;
    	double p = a3ds.phi()*180.0/Math.PI;
       	// System.out.println("Arrow: rho= " + r + " theta= " + t + " phi=" + p);

    	// Create cylinder of required length
     	Cylinder a = new Cylinder(ar, r);
    	a.setMaterial(m);

    	// Create arrow point
    	Cone c = new Cone(arrowPointRadius, arrowPointHeight, avPointDivisions);
    	c.setMaterial(m);

    	// System.out.println("mp: Y (X)= " + mp.getY() + ", Z (Y)=" + mp.getZ() + ", X (Z)=" + mp.getX());
    	// System.out.println("Y-rotate= " + (t-90) + ", Z-rotate= " + -p);

    	a.getTransforms().addAll(new Translate(origin[0]+mp.getY(),
    										   origin[1]+mp.getZ(),
    										   origin[2]+mp.getX()),
    										   new Rotate(t-90,Rotate.Y_AXIS),
    										   new Rotate(-p,Rotate.Z_AXIS));

    	c.getTransforms().addAll(new Translate(origin[0]+a3dt.getp2().getY(),
				   							   origin[1]+a3dt.getp2().getZ(),
				   							   origin[2]+a3dt.getp2().getX()),
				   							   new Rotate(t,Rotate.Y_AXIS),
				   							   new Rotate(p+180,Rotate.X_AXIS));

		drawList.add(a3d);
    	Group aGroup = dataGroup.get(currentSequenceNum);
    	aGroup.getChildren().addAll(a, c);

    }

    /**
     * Draws a Bezier curve defined by the specified array of control points.
     * The object is drawn with the color specified by drawMaterial as set by setDrawColor().
     *
     * @param	p	Point3D[] array of control points defining the curve
     */
	public void drawBezier(Point3D[] p) {
		int iter = 100;
		Point3D[] bezierCurve = new Point3D[iter];
		bezierCurve[0] = p[0];
		for (int j = 0; j < iter; j++) {
			double t = j * 1.0/iter;
			Point3D[] tmp = p.clone();
		    int i = tmp.length - 1;
		    int ix = p.length - 1;
		    while (i > 0) {
		        for (int k = 0; k < i; k++) {
	//				tmp[k] = tmp[k] + t * (tmp[k+1] - tmp[k])
		            tmp[k] = tmp[k].add(tmp[k+1].subtract(tmp[k]).multiply(t));
		        }
		        i--;
		    }
		    bezierCurve[j] = tmp[0];
		}
		for (int j = 0; j < iter-1; j++) {
			drawLine3D(new Line3D(bezierCurve[j], bezierCurve[j+1], drawMaterial));
		}
	}

    /**
	 * Draws a Box3D object.
	 *
     * @param  b3d  specifies a Box3D object with center point, width, height, and depth,
     * 				x-rotation, y-rotation, z-rotation and drawing parameters.
	 */
	public void drawBox3D(Box3D b3d) {
		drawBox3D(b3d, false);
	}

    /**
     * Draws a Box3D object, optionally with edges highlighted.
     *
     * @param	b3d		specifies a Box3D object with center point, width, height, and depth,
     * 					x-rotation, y-rotation, z-rotation and drawing parameters.
     * @param	showEdges	boolean value indicating whether to show edges or not
     */
    public void drawBox3D(Box3D b3d, boolean showEdges) {
    	double xi = b3d.getP().getX();
    	double yi = b3d.getP().getY();
    	double zi = b3d.getP().getZ();
    	double w  = b3d.getWidth();
    	double h  = b3d.getHeight();
    	double d  = b3d.getDepth();
    	double rx = b3d.getXr();
    	double ry = b3d.getYr();
    	double rz = b3d.getZr();

    	PhongMaterial m = b3d.getMaterial();
    	if (m == null) {
    		m = drawMaterial;
    		b3d.setMaterial(m);
    	}
    	DrawMode dm = b3d.getDrawMode();
    	if (dm == null) {
    		dm = drawMode;
    		b3d.setDrawMode(dm);
    	}
    	CullFace cf = b3d.getCullFace();
    	if (cf == null) {
    		cf = cullFace;
    		b3d.setCullFace(cf);
    	}
    	if (errorCheck(xi, yi, zi)) {
        		return;
        }

    	double x = 0.0;
    	double y = 0.0;
    	double z = 0.0;
    	double rxf = 0.0;
    	double ryf = 0.0;
    	double rzf = 0.0;
    	Box b;
    	if ( axesZupCheckItem.isSelected() ) {
    		b = new Box(dY(w), dZ(h), dX(d));
    		x = dY(yi);
        	y = dZ(zi);
        	z = dX(xi);
    		rxf = ry;
    		ryf = rz;
    		rzf = rx;
    	}
    	else {
    		b = new Box(dX(w), dY(h), dZ(d));
    		x = dX(xi);
    		y = dY(yi);
    		z = dZ(zi);
    		rxf = rx;
    		ryf = ry;
    		rzf = rz;
    	}

    	b.setMaterial(m);
		b.setDrawMode(dm);
		b.setCullFace(cf);

    	if ( axesZupCheckItem.isSelected() ) {
	    	b.getTransforms().addAll(new Translate(origin[0]+x, origin[1]+y, origin[2]+z),
	    							 new Rotate(ryf,Rotate.Y_AXIS),
	    							 new Rotate(rxf,Rotate.X_AXIS),
	    							 new Rotate(rzf,Rotate.Z_AXIS));
    	}
    	else {
        	b.getTransforms().addAll(new Translate(origin[0]+x, origin[1]+y, origin[2]+z),
					 				 new Rotate(rzf,Rotate.Z_AXIS),
					 				 new Rotate(ryf,Rotate.Y_AXIS),
					 				 new Rotate(rxf,Rotate.X_AXIS));
    	}

    	drawList.add(b3d);
    	Group aGroup = dataGroup.get(currentSequenceNum);
    	aGroup.getChildren().addAll(b);

		if (showEdges) {
			drawBoxEdges3D(b3d);
		}
    }

    /**
	 * Draws the edges of Box3D
	 *
	 * @param	b3d			specifies a BOX3D object with center point, size,
	 * 						x-rotation, y-rotation, z-rotation and drawing parameters.
	 */
	public void drawBoxEdges3D(Box3D b3d) {
		double xi = b3d.getP().getX();
		double yi = b3d.getP().getY();
		double zi = b3d.getP().getZ();
		double rx = b3d.getXr();
		double ry = b3d.getYr();
		double rz = b3d.getZr();

		Line3D[] edges = b3d.getEdges();
		Line3D edge, tedge, redge;

		System.out.println("zUp=" + axesZupCheckItem.isSelected());
		for (int ei = 0; ei < edges.length; ei++) {
			edge = edges[ei];
			if (ei==0)
				System.out.println("edge1=" + edge);

			if (axesZupCheckItem.isSelected()) {
				Point3D str = edge.getp1();
				Point3D end = edge.getp2();
				Point3D newstr = new Point3D(str.getZ(), str.getX(), str.getY());
				Point3D newend = new Point3D(end.getZ(), end.getX(), end.getY());
				edge = new Line3D(newstr, newend);
			}

			if (ei==0)
				System.out.println("edge2=" + edge);

			// Rotate edge if necessary and translate
			if (rx != 0 || ry != 0 || rz != 0) {
				redge = rotateLine3D(edge, rx, ry, rz);

				tedge = new Line3D(redge.getp1().getX()+xi,
								   redge.getp1().getY()+yi,
								   redge.getp1().getZ()+zi,
								   redge.getp2().getX()+xi,
								   redge.getp2().getY()+yi,
								   redge.getp2().getZ()+zi);
			}
			else {
				tedge = new Line3D(edge.getp1().getX()+xi,
						   		   edge.getp1().getY()+yi,
						   		   edge.getp1().getZ()+zi,
						   		   edge.getp2().getX()+xi,
						   		   edge.getp2().getY()+yi,
						   		   edge.getp2().getZ()+zi);
			}

//			System.out.println("ei=" + ei + ", " + tedge);

			// Color edges
			if (ei==0) {
				tedge.setMaterial(new PhongMaterial(Color.RED));
				tedge.setLineRadius(2);
			}

			else if (ei == 1) {
				tedge.setMaterial(new PhongMaterial(Color.GREEN));
				tedge.setLineRadius(2);
			}
			else if (ei == 2) {
				tedge.setMaterial(new PhongMaterial(Color.BLUE));
				tedge.setLineRadius(2);
			}
			else if (ei == 3) {
				tedge.setMaterial(new PhongMaterial(Color.GOLDENROD));
				tedge.setLineRadius(2);
			}
			else if (ei == 4) {
				tedge.setMaterial(new PhongMaterial(Color.CORAL));
				tedge.setLineRadius(2);
			}
			else if (ei == 5) {
				tedge.setMaterial(new PhongMaterial(Color.TURQUOISE));
				tedge.setLineRadius(2);
			}
			else if (ei == 6) {
				tedge.setMaterial(new PhongMaterial(Color.MAGENTA));
				tedge.setLineRadius(2);
			}
			else if (ei == 7) {
				tedge.setMaterial(new PhongMaterial(Color.CRIMSON));
				tedge.setLineRadius(2);
			}
			else if (ei == 8) {
				tedge.setMaterial(new PhongMaterial(Color.BROWN));
				tedge.setLineRadius(2);
			}
			else if (ei == 9) {
				tedge.setMaterial(new PhongMaterial(Color.THISTLE));
				tedge.setLineRadius(2);
			}
			else if (ei == 10) {
				tedge.setMaterial(new PhongMaterial(Color.YELLOW));
				tedge.setLineRadius(2);
			}
			else if (ei == 11) {
				tedge.setMaterial(new PhongMaterial(Color.LAWNGREEN));
				tedge.setLineRadius(2);
			}
			else {
				tedge.setMaterial(new PhongMaterial(Color.TURQUOISE));
			}

			drawLine3D(tedge);
		}
	}

	 /**
     * Constructs a circle object (JavaFX Cylinder w/ height .1) with specified center point
     * and radius and draws it. Rotations default to zero.
     * The object is drawn with the color specified by drawMaterial as set by setDrawColor().
     *
     * @param  x  specifies center point x coordinate
     * @param  y  specifies center point y coordinate
     * @param  z  specifies center point z coordinate
     * @param  r  specifies radius
     *
     */
    public void drawCircle(double x, double y, double z, double r) {
    	Cylinder3D c3d = new Cylinder3D(x, y, z, r, 0, 90.0, 0.0, 0.0, drawMaterial);
    	c3d.setDrawMode(drawMode.LINE);
    	drawCylinder3D(c3d);
    }

    /**
     * Constructs a circle object (JavaFX Cylinder w/ height .1) with specified center point, radius, and
	 * x-rotation, y-rotation, z-rotation and draws it.
     * The object is drawn with the color specified by drawMaterial as set by setDrawColor().
     *
     * @param	x	specifies center point x coordinate
     * @param	y	specifies center point y coordinate
     * @param	z	specifies center point z coordinate
     * @param	r	specifies radius
     * @param	rx	x-rotation
     * @param	ry	y-rotation
     * @param	rz	z-rotation
     *
     */
    public void drawCircle(double x, double y, double z, double r,
    					   double rx, double ry, double rz) {
    	drawCylinder3D(new Cylinder3D(x, y, z, r, 0, rx, ry, rz, drawMaterial));
    }

    /**
     * Constructs a Cone3D object with point at specified coordinates, radius, and height and draws it.
     * Rotations default to zero.
     * The object is drawn with the color specified by drawMaterial as set by setDrawColor().
     *
     * @param	x	specifies center point x coordinate
     * @param	y	specifies center point y coordinate
     * @param	z	specifies center point z coordinate
     * @param	r	specifies radius
     * @param	h	specifies height
     *
     */
    public void drawCone(double x, double y, double z,
			 double r, double h) {
		Cone3D c3d = new Cone3D(x, y, z, r, h);
		c3d.setMaterial(drawMaterial);
		c3d.setConeDivisions(coneDivisions);
		drawCone3D(c3d);
	}

    /**
     * Constructs a Cone3D object with point at specified coordinates, radius, height, and
     * xyz rotations and draws it.
     * The object is drawn with the color specified by drawMaterial as set by setDrawColor().
     *
     * @param	x	specifies center point x coordinate
     * @param	y	specifies center point y coordinate
     * @param	z	specifies center point z coordinate
     * @param	r	specifies radius
     * @param	h	height
     * @param	rx	x-rotation
     * @param	ry	y-rotation
     * @param	rz	z-rotation
     */
	public void drawCone(double x, double y, double z,
				 double r, double h,
				 double rx, double ry, double rz) {
		Cone3D c3d = new Cone3D(x, y, z, r, h, rx, ry, rz);
		c3d.setMaterial(drawMaterial);
		c3d.setConeDivisions(coneDivisions);
		drawCone3D(c3d);
	}

	/**
	 * Draws a Cone3D object.
	 *
	 * @param	c3d	specifies a Cone3D object with tip point, radius, height,
	 * 				x-rotation, y-rotation, z-rotation and drawing parameters.
	 */
	public void drawCone3D(Cone3D c3d) {
		double xi = c3d.getP().getX();
		double yi = c3d.getP().getY();
		double zi = c3d.getP().getZ();
		double r  = c3d.getRadius();
		double h  = c3d.getHeight();
		double rx = c3d.getXr();
		double ry = c3d.getYr();
		double rz = c3d.getZr();
		int cd    = c3d.getConeDivisions();
		PhongMaterial m = c3d.getMaterial();
		if (m == null) {
			m = drawMaterial;
			c3d.setMaterial(m);
		}
		DrawMode dm = c3d.getDrawMode();
		if (dm == null) {
			dm = drawMode;
			c3d.setDrawMode(dm);
		}
		CullFace cf = c3d.getCullFace();
		if (cf == null) {
			cf = cullFace;
			c3d.setCullFace(cf);
		}
		if (errorCheck(xi, yi, zi)) {
			return;
		}

    	double x;
    	double y;
    	double z;
    	double rxf = 0.0;
    	double ryf = 0.0;
    	double rzf = 0.0;
    	Cone c;
    	if ( axesZupCheckItem.isSelected() ) {
    		c = new Cone(dX(r), dZ(h), cd);
    		x = dY(yi);
        	y = dZ(zi);
        	z = dX(xi);
    		rxf = ry;
    		ryf = rz;
    		rzf = rx;
      	}
    	else {
    		c = new Cone(dX(r), dY(h), cd);
    		x = dX(xi);
        	y = dY(yi);
        	z = dZ(zi);
    		rxf = rx;
    		ryf = ry;
    		rzf = rz;
    	}

		c.setMaterial(m);
		c.setDrawMode(dm);
		c.setCullFace(cf);

    	if ( axesZupCheckItem.isSelected() ) {
	    	c.getTransforms().addAll(new Translate(origin[0]+x, origin[1]+y, origin[2]+z),
	    							 new Rotate(ryf,Rotate.Y_AXIS),
	    							 new Rotate(rxf,Rotate.X_AXIS),
	    							 new Rotate(rzf,Rotate.Z_AXIS));
    	}
    	else {
        	c.getTransforms().addAll(new Translate(origin[0]+x, origin[1]+y, origin[2]+z),
					 				 new Rotate(rzf,Rotate.Z_AXIS),
					 				 new Rotate(ryf,Rotate.Y_AXIS),
					 				 new Rotate(rxf,Rotate.X_AXIS));
    	}

		drawList.add(c3d);
		Group aGroup = dataGroup.get(currentSequenceNum);
		aGroup.getChildren().addAll(c);
	}

    /**
     * Constructs a Box3D object with specified center point, width, height, and depth
     * and draws it. Rotations default to zero.
     * The object is drawn with the color specified by drawMaterial as set by setDrawColor().
     *
     * @param	x	specifies center point x coordinate
     * @param	y	specifies center point y coordinate
     * @param	z	specifies center point z coordinate
     * @param	width	specifies width
     * @param	height	specifies height
     * @param	depth	specifies depth
     */
	public void drawCuboid( double x, double y, double z,
			double width, double height, double depth) {
		drawBox3D(new Box3D(x, y, z, width, height, depth, drawMaterial));
	}

    /**
     * Constructs a Box3D object with specified center point, width, height, depth, and
	 * x-rotation, y-rotation, z-rotation and draws it.
     * The object is drawn with the color specified by drawMaterial as set by setDrawColor().
     *
     * @param	x	specifies center point x coordinate
     * @param	y	specifies center point y coordinate
     * @param	z	specifies center point z coordinate
     * @param	width	specifies width
     * @param	height	specifies height
     * @param	depth	specifies depth
     * @param	rx	x-rotation
     * @param	ry	y-rotation
     * @param	rz	z-rotation
     */
	public void drawCuboid( double x, double y, double z,
				double width, double height, double depth,
				double rx, double ry, double rz) {
		drawBox3D(new Box3D(x, y, z, width, height, depth, rx, ry, rz, drawMaterial));
	}

    /**
     * Constructs a Cylinder3D object with specified center point, radius, and height
     * and draws it. Rotations default to zero.
     * The object is drawn with the color specified by drawMaterial as set by setDrawColor().
     *
     * @param	x	specifies center point x coordinate
     * @param	y	specifies center point y coordinate
     * @param	z	specifies center point z coordinate
     * @param	r	specifies radius
     * @param	h	specifies height
     *
     */
	public void drawCylinder(double x, double y, double z,
					 double r, double h) {
		Cylinder3D c3d = new Cylinder3D(x, y, z, r, h);
		c3d.setMaterial(drawMaterial);
		c3d.setCylinderDivisions(cylinderDivisions);
    	drawCylinder3D(c3d);
	}

    /**
     * Constructs a Cylinder3D object with specified center point, radius, height,
     * x-rotation, y-rotation, z-rotation and draws it.
     * The object is drawn with the color specified by drawMaterial as set by setDrawColor().
     *
     * @param	x	specifies center point x coordinate
     * @param	y	specifies center point y coordinate
     * @param	z	specifies center point z coordinate
     * @param	r	specifies radius
     * @param	h	specifies height
     * @param	rx	specifies x-rotation
     * @param	ry	specifies y-rotation
     * @param	rz	specifies z-rotation
     */
	public void drawCylinder(double x, double y, double z,
				 double r, double h,
				 double rx, double ry, double rz) {
		Cylinder3D c3d = new Cylinder3D(x, y, z, r, h, rx, ry, rz);
		c3d.setMaterial(drawMaterial);
		c3d.setCylinderDivisions(cylinderDivisions);
    	drawCylinder3D(c3d);
	}

    /**
     * Draws a Cylinder3D object.
     *
     * @param  c3d  specifies a Cylinder3D object with center point, radius, height,
     *				x-rotation, y-rotation, z-rotation and drawing parameters.
     */
	public void drawCylinder3D(Cylinder3D c3d) {
		double xi = c3d.getP().getX();
		double yi = c3d.getP().getY();
		double zi = c3d.getP().getZ();
		double r  = c3d.getRadius();
		double h  = c3d.getHeight();
		double rx = c3d.getXr();
		double ry = c3d.getYr();
		double rz = c3d.getZr();
		int cd    = c3d.getCylinderDivisions();
		PhongMaterial m = c3d.getMaterial();
		if (m == null) {
			m = drawMaterial;
			c3d.setMaterial(m);
		}
		DrawMode dm = c3d.getDrawMode();
		if (dm == null) {
			dm = drawMode;
			c3d.setDrawMode(dm);
		}
		CullFace cf = c3d.getCullFace();
		if (cf == null) {
			cf = cullFace;
			c3d.setCullFace(cf);
		}
		if (errorCheck(xi, yi, zi)) {
			return;
		}

    	Cylinder c;
    	double x = 0.0;
    	double y = 0.0;
    	double z = 0.0;
    	double rxf = 0.0;
    	double ryf = 0.0;
    	double rzf = 0.0;
    	if ( axesZupCheckItem.isSelected() ) {
    		c = new Cylinder(dX(r), dZ(h), cd);
    		x = dY(yi);
        	y = dZ(zi);
        	z = dX(xi);
    		rxf = ry;
    		ryf = rz;
    		rzf = rx;
    	}
    	else {
    		c = new Cylinder(dX(r), dY(h), cd);
    		x = dX(xi);
    		y = dY(yi);
    		z = dZ(zi);
    		rxf = rx;
    		ryf = ry;
    		rzf = rz;
    	}

		c.setMaterial(m);
		c.setDrawMode(dm);
		c.setCullFace(cf);

    	if ( axesZupCheckItem.isSelected() ) {
	    	c.getTransforms().addAll(new Translate(origin[0]+x, origin[1]+y, origin[2]+z),
	    							 new Rotate(ryf,Rotate.Y_AXIS),
	    							 new Rotate(rxf,Rotate.X_AXIS),
	    							 new Rotate(rzf,Rotate.Z_AXIS));
    	}
    	else {
        	c.getTransforms().addAll(new Translate(origin[0]+x, origin[1]+y, origin[2]+z),
					 				 new Rotate(rzf,Rotate.Z_AXIS),
					 				 new Rotate(ryf,Rotate.Y_AXIS),
					 				 new Rotate(rxf,Rotate.X_AXIS));
    	}

		drawList.add(c3d);
		Group aGroup = dataGroup.get(currentSequenceNum);
		aGroup.getChildren().addAll(c);
	}

    /**
     * Draws a Drone3D object.
     *
     * @param  d3d  specifies a Drone3D object with xyz location, xyz rotations, and drawing parameters.
     */
	public void drawDrone3D(Drone3D d3d) {
		drawDrone3D(viewNumber, d3d);
	}

    /**
     * Draws a Drone3D object.
     *
     * @param	view	specifies the view number to use for drawing
     * @param	d3d		specifies a Drone3D object with xyz location, xyz rotations, and drawing parameters.
     */
	public void drawDrone3D(int view, Drone3D d3d) {
		double xi = d3d.getP().getX();
		double yi = d3d.getP().getY();
		double zi = d3d.getP().getZ();
		double radius = d3d.getRadius();
		double height = d3d.getHeight();
		double rx = d3d.getXr();
		double ry = d3d.getYr();
		double rz = d3d.getZr();
		PhongMaterial m1 = d3d.getMaterial1();
		if (m1 == null) {
			m1 = drawMaterial;
			d3d.setMaterial1(m1);
		}
		PhongMaterial m2 = d3d.getMaterial2();
		if (m2 == null) {
			m2 = drawMaterial;
			d3d.setMaterial2(m2);
		}

    	double x = 0.0;
    	double y = 0.0;
    	double z = 0.0;
    	double rxf = 0.0;
    	double ryf = 0.0;
    	double rzf = 0.0;
    	if ( axesZupCheckItem.isSelected() ) {
    		x = dY(yi);
    		y = dZ(zi);
    		z = dX(xi);
    		rxf = ry;
    		ryf = rz;
    		rzf = rx;
    	}
    	else {
    		x = dX(xi);
    		y = dY(yi);
    		z = dZ(zi);
    		rxf = rx;
    		ryf = ry;
    		rzf = rz;
    	}

		Cylinder cy1 = new Cylinder(2, radius);
		Cylinder cy2 = new Cylinder(2, radius);
		Cylinder cy3 = new Cylinder(2, height);
		Cone cn1 = new Cone(height/2,height,8);
		Cone cn2 = new Cone(height/2,height,8);
		Cone cn3 = new Cone(height/2,height,8);
		Cone cn4 = new Cone(height/2,height,8);
		cy1.getTransforms().addAll(new Rotate( 90,Rotate.Z_AXIS));
		cy2.getTransforms().addAll(new Rotate(-90,Rotate.X_AXIS));
		cn1.getTransforms().addAll(new Translate( 24, 0,  0),new Rotate(180,Rotate.X_AXIS));
		cn2.getTransforms().addAll(new Translate(  0, 0, 24),new Rotate(180,Rotate.X_AXIS));
		cn3.getTransforms().addAll(new Translate(-24, 0,  0),new Rotate(180,Rotate.X_AXIS));
		cn4.getTransforms().addAll(new Translate(  0, 0,-24),new Rotate(180,Rotate.X_AXIS));
		cy1.setMaterial(m1);
		cy2.setMaterial(m1);
		cy3.setMaterial(m1);
		cn1.setMaterial(m2);
		cn2.setMaterial(m2);
		cn3.setMaterial(m2);
		cn4.setMaterial(m2);
		Group droneGroup = new Group();
		droneGroup.getChildren().addAll(cy1, cy2, cy3, cn1, cn2, cn3, cn4);
		droneGroup.getTransforms().addAll(new Translate(origin[0]+x, origin[1]+y, origin[2]+z),
				 new Rotate(ryf,Rotate.Y_AXIS),
				 new Rotate(rzf,Rotate.Z_AXIS),
				 new Rotate(rxf,Rotate.X_AXIS));

		ArrayList<Object> newDrawList = new ArrayList<>();
		newDrawList.add(d3d);
		saveDrawLists.set(view,newDrawList);
		Group aGroup = dataGroup.get(view);
		aGroup.getChildren().addAll(droneGroup);
	}

    /**
     * Constructs a Text3D object and draws it with center at xyz location.
     * If Z axis is up, the label is rotated 180 degrees about the y axis,
     * else it is rotated 180 degrees about the x axis.
     * The current value of drawMaterial determines the color.
     * The current value of labelFont determines the font and font size.
     *
     * @param	x	x coordinate of the center of the Text label
     * @param	y	y coordinate of the center of the Text label
     * @param	z	z coordinate of the center of the Text label
     * @param	s	String contents of the label
     */
    public void drawLabel(double x, double y, double z, String s) {
    	Text3D t3d;
    	if ( axesZupCheckItem.isSelected() ) {
    		t3d = new Text3D(x, y, z, 0, 180, 0, s);
    	}
    	else {
    		t3d = new Text3D(x, y, z, 180, 0, 0, s);
    	}
    	t3d.setColor(drawMaterial.getDiffuseColor());
    	t3d.setLabelFont(labelFont);
    	drawLabel3D(t3d);
    }

    /**
     * Constructs a Text3D object and draws it with center at xyz location
     * with the specified color and size.
     * If Z axis is up, the label is rotated 180 degrees about the y axis,
     * else it is rotated 180 degrees about the x axis.
     *
     * @param	x	x coordinate of the center of the Text label
     * @param	y	y coordinate of the center of the Text label
     * @param	z	z coordinate of the center of the Text label
     * @param	s	String contents of the label
     * @param	f	Font for the label
     * @param	c	Color for the label
     *
     */
    public void drawLabel(double x, double y, double z,
    					  String s,
    					  Font f,
    					  Color c) {
    	Text3D t3d;
    	if ( axesZupCheckItem.isSelected() ) {
    		t3d = new Text3D(x, y, z, 0, 180, 0, s);
    	}
    	else {
    		t3d = new Text3D(x, y, z, 180, 0, 0, s);
    	}
    	t3d.setLabelFont(f);
    	t3d.setColor(c);
    	drawLabel3D(t3d);
    }

    /**
     * Constructs a Text3D object and draws it with center at xyz location and xyz rotation specifications.
     * The current value of drawMaterial determines the color.
     * The current value of labelFont determines the font and font size.
     *
     * @param	x	x coordinate of the center of the Text label
     * @param	y	y coordinate of the center of the Text label
     * @param	z	z coordinate of the center of the Text label
     * @param	rx	degrees of rotation about the x axis
     * @param	ry	degrees of rotation about the y axis
     * @param	rz	degrees of rotation about the z axis
     * @param	s	String contents of the label
     */
    public void drawLabel(double x, double y, double z, double rx, double ry, double rz, String s) {
    	Text3D t3d = new Text3D(x, y, z, rx, ry, rz, s);
    	t3d.setColor(drawMaterial.getDiffuseColor());
    	t3d.setLabelFont(labelFont);
    	drawLabel3D(t3d);
    }

    /**
     * Constructs a Text3D object and draws it with center at xyz location and xyz rotation
     * with the specified color and size.
     *
     * @param	x	x coordinate of the center of the Text label
     * @param	y	y coordinate of the center of the Text label
     * @param	z	z coordinate of the center of the Text label
     * @param	rx	degrees of rotation about the x axis
     * @param	ry	degrees of rotation about the y axis
     * @param	rz	degrees of rotation about the z axis
     * @param	s	String contents of the label
     * @param	f	Font for the label
     * @param	c	Color for the label
     */
    public void drawLabel(double x, double y, double z,
    					  double rx, double ry, double rz,
    					  String s,
    					  Font f,
    					  Color c) {
    	Text3D t3d = new Text3D(x, y, z, rx, ry, rz, s);
    	t3d.setLabelFont(f);
    	t3d.setColor(c);
    	drawLabel3D(t3d);
    }

    /**
     * Draws a Text3D object according to its xyz location, xyz rotation, String contents,
     * with default or object specific font and color specifications.
     *
     * @param	t3d	Text3D object to be drawn
     */
    public void drawLabel3D(Text3D t3d) {
    	drawLabel3D(t3d, true);
    }

    /**
     * Draws a Text3D object according to its xyz location, xyz rotation, String contents,
     * with font and color specifications.
     *
     * @param	t3d		Text3D object to be drawn
     * @param	save	boolean flag that suppresses, when false, adding the drawn item to the drawList
     */
    private void drawLabel3D(Text3D t3d, boolean save) {
    	double x  = t3d.getP().getX();
    	double y  = t3d.getP().getY();
    	double z  = t3d.getP().getZ();
     	double rx = t3d.getXr();
    	double ry = t3d.getYr();
    	double rz = t3d.getZr();
    	String s  = t3d.getText();
    	Font lf   = t3d.getLabelFont();
    	if (lf == null) {
    		lf = labelFont;
    		t3d.setLabelFont(lf);
    	}
    	Color c   = t3d.getColor();
    	if (c == null) {
    		c = drawMaterial.getDiffuseColor();
    		t3d.setColor(c);
    	}

    	if (errorCheck(x, y, z)) {
    		return;
    	}

    	double xt;
    	double yt;
    	double zt;
    	double rxf = 0.0;
    	double ryf = 0.0;
    	double rzf = 0.0;
    	Text t = new Text(s);
    	if ( axesZupCheckItem.isSelected() ) {
    		xt = dY(y);
        	yt = dZ(z);
        	zt = dX(x);
//        	if (rx == 180 && ry == 0) {
//        		rx = 0;
//        		ry = 180;
//        	}
    		rxf = ry;
    		ryf = rz;
    		rzf = rx;
      	}
    	else {
    		xt = dX(x);
        	yt = dY(y);
        	zt = dZ(z);
//        	if (rx == 0 & ry == 180) {
//        		rx = 180;
//        		ry = 0;
//        	}
    		rxf = rx;
    		ryf = ry;
    		rzf = rz;
    	}

        t.setFont(lf);
        t.setFill(c);

    	if ( axesZupCheckItem.isSelected() ) {
	    	t.getTransforms().addAll(new Translate(origin[0]+xt, origin[1]+yt, origin[2]+zt),
	    							 new Rotate(ryf,Rotate.Y_AXIS),
	    							 new Rotate(rxf,Rotate.X_AXIS),
	    							 new Rotate(rzf,Rotate.Z_AXIS));
//	       	System.out.println("x=" + yt + ", y=" + zt + ", z=" + xt +
//					   		   ", rxf=" + rxf + ", ryf=" + ryf + ", rzf=" + rzf);
    	}
    	else {
        	t.getTransforms().addAll(new Translate(origin[0]+xt, origin[1]+yt, origin[2]+zt),
					 				 new Rotate(rzf,Rotate.Z_AXIS),
					 				 new Rotate(ryf,Rotate.Y_AXIS),
					 				 new Rotate(rxf,Rotate.X_AXIS));
//        	System.out.println("x=" + xt + ", y=" + yt + ", z=" + zt +
//        					   ", rxf=" + rxf + ", ryf=" + ryf + ", rzf=" + rzf);

    	}

    	if (save) {
    		drawList.add(t3d);
    	}
    	Group aGroup = dataGroup.get(currentSequenceNum);
    	aGroup.getChildren().addAll(t);
    }

    /**
     * Constructs a line object with specified start and end points and draws it.
     * The object is drawn with the color specified by drawMaterial as set by setDrawColor().
     *
     * @param  x1  defines start point x coordinate
     * @param  y1  defines start point y coordinate
     * @param  z1  defines start point z coordinate
     * @param  x2  defines end point x coordinate
     * @param  y2  defines end point y coordinate
     * @param  z2  defines end point z coordinate
     *
     * Uses value lineRadius for radius of line
     * Uses value drawMaterial for drawing color
     */
    public void drawLine(double x1, double y1, double z1, double x2, double y2, double z2) {
		Line3D l3D = new Line3D(x1, y1, z1, x2, y2, z2);
		l3D.setMaterial(drawMaterial);
		l3D.setLineRadius(lineRadius);
		drawLine3D(l3D);
	}

    /**
     * Draws a Line3D object.
     *
     * @param	ln	specifies a Line3D object with start point, end point, drawing parameters.
     */
    public void drawLine3D(Line3D ln) {
    	drawLine3D(ln, true);
    }

    /**
     * Draws a Line3D object.
     *
     * @param	ln		specifies a Line3D object with start point, end point, drawing parameters.
     * @param	save	boolean flag that suppresses, when false, adding the drawn item to the drawList
     */
    private void drawLine3D(Line3D ln, boolean save) {
//    	System.out.println("\nln= " + ln);
    	double x1 = ln.getp1().getX();
    	double y1 = ln.getp1().getY();
    	double z1 = ln.getp1().getZ();
    	double x2 = ln.getp2().getX();
    	double y2 = ln.getp2().getY();
    	double z2 = ln.getp2().getZ();
    	double lr = ln.getLineRadius();
    	PhongMaterial m = ln.getMaterial();
    	if (m == null) {
    		m = drawMaterial;
    		ln.setMaterial(m);
    	}
    	if (errorCheck(x1, y1, z1) || errorCheck(x2, y2, z2)) {
    		return;
    	}

    	// Convert to pixels with JavaFX axes orientation
      	Line3D lnt;
    	if ( axesZupCheckItem.isSelected() ) {
			lnt = new Line3D(new Point3D(dX(x1), dY(y1), dZ(z1)),
  		   		 	 		 new Point3D(dX(x2), dY(y2), dZ(z2)));
    	}
    	else {
			lnt = new Line3D(new Point3D(dZ(z1), dX(x1), dY(y1)),
		   		   		 	 new Point3D(dZ(z2), dX(x2), dY(y2)));
    	}
//    	System.out.println("lnt= " + lnt);

        // Compute length of line segment
    	double r = lnt.length();
    	// Compute mid-point between points
    	Point3D mp = lnt.midpoint();
//    	System.out.println("Line mp= " + mp.toString());
    	// Compute spherical angles for mid-point oriented vector
    	double t = lnt.theta()*180.0/Math.PI;
    	double p = lnt.phi()*180.0/Math.PI;
//    	System.out.println("Line: rho= " + r + " theta= " + t + " phi=" + p);

    	// Create cylinder of required length
     	Cylinder c = new Cylinder(lr, r);
    	c.setMaterial(m);

    	c.getTransforms().addAll(new Translate(origin[0]+mp.getY(),
				   							   origin[1]+mp.getZ(),
				   							   origin[2]+mp.getX()),
				   				 new Rotate(t-90,Rotate.Y_AXIS),
				   				 new Rotate(-p,Rotate.Z_AXIS));

//    	System.out.println("Translate: tx= " + mp.getY() + ", ty= " + mp.getZ() + ", tz= " + mp.getX());
//    	System.out.println("Rotate: Y_AXIS= " + (t-90) + ", Z_AXIS= " + -p);

    	if (save) {
    		drawList.add(ln);
    	}
    	Group aGroup = dataGroup.get(currentSequenceNum);
    	aGroup.getChildren().addAll(c);
    }

    /**
     * Draws a set of Line3D line segments with specified color
     *
     * @param	ln	Array of Line3D object to be drawn
     */
	public void drawLine3DSet(Line3D[] ln) {
		for (int i = 0; i < ln.length; i++) {
			if (ln[i].getMaterial() == null) {
				ln[i].setMaterial(drawMaterial);
			}
			drawLine3D(ln[i]);
	   	}
	}

	/**
	 * Constructs a Line3D object given the symmetric line equation coefficients, finds the intersection
	 * points with the current boundary of the user coordinate space, and draws the line between the
	 * intersection points.
	 * Symmetric line equation: (x-a1)/b1 = (y-a2)/b2 = (z-a3)/b3, b1!=0, b2!=0, b3!=0
	 *
	 * @param a1	a1 coordinate of Cartesian line equation
	 * @param a2	a2 coordinate of Cartesian line equation
	 * @param a3	a3 coordinate of Cartesian line equation
	 * @param b1	b1 coordinate of Cartesian line equation
	 * @param b2	b2 coordinate of Cartesian line equation
	 * @param b3	b3 coordinate of Cartesian line equation
	 */
	public void drawLineEquation(double a1, double a2, double a3, double b1, double b2, double b3) {
		Line3D ln = new Line3D(a1, a2, a3, b1, b2, b3, true);
		Point3D[] intersections = ln.findLineBoundaryIntersections(minX,  maxX,  minY,  maxY,  minZ,  maxZ);
		if (intersections != null) {
			drawLine3D(new Line3D(intersections[0], intersections[1], drawMaterial));
		}
		else {
//			System.out.println("Line is outside boundaries of current user space. ln= " + ln.toString());
		}
	}

    /**
     * Draws a variable Mesh3D object. (Under construction)
     *
     * @param  m3d  specifies a Mesh3D object with location point,
     * 				x-rotation, y-rotation, z-rotation and drawing parameters.
     */
	public void drawMesh3D(Mesh3D m3d) {
		double xi = m3d.getP().getX();
		double yi = m3d.getP().getY();
		double zi = m3d.getP().getZ();
		double rx = m3d.getXr();
		double ry = m3d.getYr();
		double rz = m3d.getZr();
	   	PhongMaterial m = m3d.getMaterial();
    	if (m == null) {
    		m = drawMaterial;
    		m3d.setMaterial(m);
    	}
    	DrawMode dm = m3d.getDrawMode();
    	if (dm == null) {
    		dm = drawMode;
    		m3d.setDrawMode(dm);
    	}
    	CullFace cf = m3d.getCullFace();
    	if (cf == null) {
    		cf = cullFace;
    		m3d.setCullFace(cf);
    	}

		if (errorCheck(xi, yi, zi)) {
			return;
		}

    	double x = 0.0;
    	double y = 0.0;
    	double z = 0.0;
    	double rxf = 0.0;
    	double ryf = 0.0;
    	double rzf = 0.0;
    	if ( axesZupCheckItem.isSelected() ) {
    		x = dY(yi);
    		y = dZ(zi);
    		z = dX(xi);
    		rxf = ry;
    		ryf = rz;
    		rzf = rx;
    	}
    	else {
    		x = dX(xi);
    		y = dY(yi);
    		z = dZ(zi);
    		rxf = rx;
    		ryf = ry;
    		rzf = rz;
    	}

		TriangleMesh mesh = m3d.getMesh();
	   	MeshView meshView = new MeshView(mesh);
		meshView.setMaterial(m);
		meshView.setDrawMode(dm);
		meshView.setCullFace(cf);

//    	meshView.getTransforms().addAll(new Translate(origin[0]+x, origin[1]+y, origin[2]+z),
//									    new Rotate(ryf,Rotate.Y_AXIS),
//									    new Rotate(rzf,Rotate.Z_AXIS),
//									    new Rotate(rxf,Rotate.X_AXIS));

    	if ( axesZupCheckItem.isSelected() ) {
	    	meshView.getTransforms().addAll(new Translate(origin[0]+x, origin[1]+y, origin[2]+z),
	    							 		new Rotate(ryf,Rotate.Y_AXIS),
	    							 		new Rotate(rxf,Rotate.X_AXIS),
	    							 		new Rotate(rzf,Rotate.Z_AXIS));
    	}
    	else {
        	meshView.getTransforms().addAll(new Translate(origin[0]+x, origin[1]+y, origin[2]+z),
					 				 		new Rotate(rzf,Rotate.Z_AXIS),
					 				 		new Rotate(ryf,Rotate.Y_AXIS),
					 				 		new Rotate(rxf,Rotate.X_AXIS));
    	}

    	drawList.add(m3d);
		Group aGroup = dataGroup.get(currentSequenceNum);
    	aGroup.getChildren().addAll(meshView);
	}

    /**
     * Constructs an oval object with specified center points, major radius, minor radius,
     * and drawing parameters and draws it. Rotations default to zero.
     * The object is drawn with the color specified by drawMaterial as set by setDrawColor().
     *
     * @param	xi	specifies center point x coordinate
     * @param	yi	specifies center point y coordinate
     * @param	zi	specifies center point z coordinate
     * @param	a	specifies major radius
     * @param	b	specifies minor radius
     */
	public void drawOval(double xi, double yi, double zi, double a, double b) {
    	Oval3D o3d = new Oval3D(xi, yi, zi, a, b);
    	o3d.setMaterial(drawMaterial);
    	o3d.setOvalDivisions(ovalDivisions);
    	drawOval3D(o3d);
    }

    /**
     * Constructs an Oval3D object with specified center points, major radius, minor radius,
     * x-rotation, y-rotation, z-rotation and draws it.
     * The object is drawn with the color specified by drawMaterial as determined by setDrawColor().
     *
     * @param	xi	specifies center point x coordinate
     * @param	yi	specifies center point y coordinate
     * @param	zi	specifies center point z coordinate
     * @param	a	specifies major radius
     * @param	b	specifies minor radius
     * @param	rx	x-rotation
     * @param	ry	y-rotation
     * @param	rz	z-rotation
     */
    public void drawOval(double xi, double yi, double zi, double a, double b, double rx, double ry, double rz) {
    	Oval3D o3d = new Oval3D(xi, yi, zi, a, b, rx, ry, rz);
    	o3d.setMaterial(drawMaterial);
    	o3d.setOvalDivisions(ovalDivisions);
    	drawOval3D(o3d);
    }

    /**
     * Draws an Oval3D object.
     *
     * @param	o3d	specifies an Oval3D object with center point, major radius, minor radius,
     *				x-rotation, y-rotation, z-rotation and drawing parameters.
     */
    public void drawOval3D(Oval3D o3d) {
		double xi = o3d.getP().getX();
		double yi = o3d.getP().getY();
		double zi = o3d.getP().getZ();
		double rmj  = o3d.getMajorRadius();
		double rmn  = o3d.getMinorRadius();
		double rx = o3d.getXr();
		double ry = o3d.getYr();
		double rz = o3d.getZr();
		int od    = o3d.getOvalDivisions();
		PhongMaterial m = o3d.getMaterial();
		if (m == null) {
			m = drawMaterial;
			o3d.setMaterial(m);
		}
		DrawMode dm = o3d.getDrawMode();
		if (dm == null) {
			dm = drawMode;
			o3d.setDrawMode(dm);
		}
		CullFace cf = o3d.getCullFace();
		if (cf == null) {
			cf = cullFace;
			o3d.setCullFace(cf);
		}
		if (errorCheck(xi, yi, zi)) {
			return;
		}

		Oval o;
    	double x = 0.0;
    	double y = 0.0;
    	double z = 0.0;
    	double rxf = 0.0;
    	double ryf = 0.0;
    	double rzf = 0.0;
    	if ( axesZupCheckItem.isSelected() ) {
    		o = new Oval(dY(rmj), dX(rmn), od);
    		x = dY(yi);
    		y = dZ(zi);
    		z = dX(xi);
    		rxf = ry;
    		ryf = rz;
    		rzf = rx;
    	}
    	else {
    		o = new Oval(dX(rmj), dZ(rmn), od);
    		x = dX(xi);
    		y = dY(yi);
    		z = dZ(zi);
    		rxf = rx;
    		ryf = ry;
    		rzf = rz;
    	}
		o.setMaterial(m);
		o.setDrawMode(dm);
		o.setCullFace(cf);

    	o.getTransforms().addAll(new Translate(origin[0]+x, origin[1]+y, origin[2]+z),
				 				 new Rotate(ryf,Rotate.Y_AXIS),
				 				 new Rotate(rzf,Rotate.Z_AXIS),
				 				 new Rotate(rxf,Rotate.X_AXIS));

		drawList.add(o3d);
		Group aGroup = dataGroup.get(currentSequenceNum);
		aGroup.getChildren().addAll(o);
	}

    /**
     * Constructs a plane object with specified plane equation coordinates and draws it.
     * The general equation of a plane is ax + by + cz + d = 0.
     * The object is drawn with the color specified by drawMaterial as set by setDrawColor().
     *
     * @param	a	x coefficient of plane equation
     * @param	b	y coefficient of plane equation
     * @param	c	z coefficient of plane equation
     * @param	d	coefficient of equation
     */
    public void drawPlane(double a, double b, double c, double d) {
    	drawPlane3D(new Plane3D(a, b, c, d, drawMaterial));
    }

    /**
     * Constructs a plane object using specification of three points in the plane and draws it.
     * The general equation of a plane is ax + by + cz + d = 0.
     * The object is drawn with the color specified by drawMaterial as set by setDrawColor().
     *
     * @param	x1	point 1 x coordinate
     * @param	y1	point 1 y coordinate
     * @param	z1	point 1 z coordinate
     * @param	x2	point 2 x coordinate
     * @param	y2	point 2 y coordinate
     * @param	z2	point 2 z coordinate
     * @param	x3	point 3 x coordinate
     * @param	y3	point 3 y coordinate
     * @param	z3	point 3 z coordinate
     */
    public void drawPlane(double x1, double y1, double z1,
    					  double x2, double y2, double z2,
    					  double x3, double y3, double z3) {
    	drawPlane3D(new Plane3D(x1, y1, z1, x2, y2, z2, x3, y3, z3, drawMaterial));
    }

    /**
     * Draws a plane object terminated at the bounding box defined by minmaxX, minmaxY, minmaxZ.
     *
     * @param	pl	specifies a plane object with drawing parameters.
     */
    public void drawPlane3D(Plane3D pl) {
    	PhongMaterial m = pl.getMaterial();
    	if (m == null) {
    		m = drawMaterial;
    		pl.setMaterial(m);
    	}
    	DrawMode dm = pl.getDrawMode();
    	if (dm == null) {
    		dm = drawMode;
    		pl.setDrawMode(dm);
    	}
    	CullFace cf = pl.getCullFace();
    	if (cf == null) {
    		cf = cullFace;
    		pl.setCullFace(cf);
    	}

 //   	System.out.println(pl);
    	// Find plane intersections with edges of view space
    	Point3D[] ei = pl.findPlaneBoxIntersections( minX, maxX, minY, maxY, minZ, maxZ);
    	if (ei == null) {
    		System.out.println("Plane outside of view space");
    		return;
    	}

//		Show intersections
//    	if (showPlaneCubeIntersections) {
//    		setPointSize(2);
//    		for (int i = 0; i < ei.length; i++) {
//    			System.out.println("Intersection: " + ei[i].toString());
//    			drawPoint3D(ei[i], new PhongMaterial(Color.BLACK));
//    		}
//    		setPointSize(pointRadius);
//    	}

    	// Draw projections
    	/*
    	int lc = pl.findLongestComponent();
    	Vector3D unitNormal = pl.getUnitNormal();
       	for (int i = 0; i < ei.length; i++) {
    		if (lc == 1) {									// X is longest
    			drawLine3D(new Line3D(ei[i].getX(),ei[i].getY(),ei[i].getZ(), 0.0,ei[i].getY(),ei[i].getZ(), turquoiseMaterial ));
    			drawLine3D(new Line3D(0.0, 0.0, 0.0, 					     0.0,ei[i].getY(),ei[i].getZ(), turquoiseMaterial ));
    		}
    		else if (lc == 2) {								// Y is longest
       			drawLine3D(new Line3D(ei[i].getX(),ei[i].getY(),ei[i].getZ(), ei[i].getX(), 0.0, ei[i].getZ(), turquoiseMaterial ));
    			drawLine3D(new Line3D(0.0, 0.0, 0.0, 						 ei[i].getX(), 0.0, ei[i].getZ(), turquoiseMaterial ));
        		}
    		else if (lc == 3) {								// Z is longest
    			drawLine3D(new Line3D(ei[i].getX(),ei[i].getY(),ei[i].getZ(), ei[i].getX(),ei[i].getY(), 0.0, turquoiseMaterial));
    			drawLine3D(new Line3D(0.0, 0.0, 0.0, 					     ei[i].getX(),ei[i].getY(), 0.0, turquoiseMaterial ));
            }
    	}
		*/

    	if (ei.length > 1) {
		// Build mesh
		TriangleMesh mesh = new TriangleMesh();

		// Add intersection points to mesh
		for (int i = 0; i < ei.length; i++) {
			if ( axesZupCheckItem.isSelected() ) {
				mesh.getPoints().addAll(
						(float)dY(ei[i].getY()) + origin[0],
		 				(float)dZ(ei[i].getZ()) + origin[1],
		 				(float)dX(ei[i].getX()) + origin[2]);
			}
			else {
				mesh.getPoints().addAll(
		 				(float)dX(ei[i].getX()) + origin[0],
		 				(float)dY(ei[i].getY()) + origin[1],
		 				(float)dZ(ei[i].getZ()) + origin[2]);
			}
		}

		// Get default Tex Coords
		mesh.getTexCoords().addAll(0,0);
/*
		mesh.getTexCoords().addAll(

			      0.5f, 1.0f,                                 // Base face, rear point (left face, right point; right face, left point), point 0.

			      0.75f, (float)(1.0 - Math.sqrt (3.0) / 4.0),    // Base face, right point (right face, right point; front face, right point), point 1.

			      0.25f, (float)(1.0 - Math.sqrt (3.0) / 4.0),    // Base face, left point (left face, left point; front face, left point), point 2.

			      1.0f, 1.0f,                                 // Right face, top point, point 3.

			      0.5f, (float)(1.0 - Math.sqrt (3.0) / 2.0),     // Front face, top point, point 4.

			      0.0f, 1.0f                                  // Left face, top point, point 5.

			    );
*/

		// Get triangle patterns depending on number of intersections
		int[][] t = pl.planeTriangulate();

		// Add faces to mesh
		for (int i = 0; i < t.length; i++) {
			 mesh.getFaces().addAll(t[i][0], 0, t[i][1], 0, t[i][2], 0);
//			 System.out.println("Adding faces[" + i + "]: " + t[i][0] + ", 0, " + t[i][1] + ", 0, " + t[i][2] + ", 0, " );
		}

		MeshView meshView = new MeshView(mesh);
		meshView.setMaterial(m);
		meshView.setDrawMode(dm);
		meshView.setCullFace(cf);

    	drawList.add(pl);
		Group aGroup = dataGroup.get(currentSequenceNum);
    	aGroup.getChildren().addAll(meshView);
    	}
    	else {			// Single vertex intersection point
    		System.out.println("Single vertext intersection point");
    	}
    }

    /**
     * Displays a series of points lying in the specified Plane.
     *
     * @param pl	Plane3D object defining a Plane
     */
    public void drawPlanePoints(Plane3D pl) {
        for (double dx=minX; dx<=maxX; dx=dx+.5) {
        	for (double dy=minY; dy<=maxY; dy=dy+.5) {
        		for (double dz=minZ; dz<=maxZ; dz=dz+.5) {
        			Point3D pt = new Point3D(dx, dy, dz);
		    	    if (pl.pointInPlane(pt)) {
		    	    	drawPoint3D(pt);
		    	    }
        		}
        	}
    	}
    }

    /**
     * Constructs a Sphere3D object corresponding to the point using the coordinates
     * specified as its center and with radius of size pointSize (in pixels).
     * Pointsize is converted to user coordinates before calling drawSphere3D.
     * The object is drawn with the color specified by drawMaterial.
     *
     * @param	x	y coordinate of point
     * @param	y	y coordinate of point
     * @param	z	z coordinate of point
     */
    public void drawPoint(double x, double y, double z) {
    	Sphere3D s3d = new Sphere3D(new Point3D(x, y, z), uX(pointSize), drawMaterial);
    	drawSphere3D(s3d);
	}

    /**
     * Constructs a Sphere3D object corresponding to the coordinates of the Point3D
     * specified as its center and with radius of size pointSize (in pixels).
     * Pointsize is converted to user coordinates before calling drawSphere3D.
     * The object is drawn with the color specified by drawMaterial.
     *
     * @param	p	Point3D specifying point location
     */
    public void drawPoint3D(Point3D p) {
    	Sphere3D s3d = new Sphere3D(p, uX(pointSize), drawMaterial);
    	drawSphere3D(s3d);
    }

    /**
     * Draws a set of points with specified color.
     *
     * @param	p	Point3D[] array specifying point locations
     */
    public void drawPoint3DSet(Point3D[] p) {
    	Point3D pt;
    	Sphere3D s3d;
    	for (int i = 0; i < p.length; i++) {
    		pt = p[i];
    		if (pt == null) {
    			System.out.println("null point i= " + i);
    			return;
    		}
        	if (errorCheck(pt.getX(), pt.getY(), pt.getZ())) {
        		return;
        	}
        	s3d = new Sphere3D(pt, uX(pointSize), drawMaterial);
        	drawSphere3D(s3d);
    	}
     }

    /**
     * Constructs a polygon object using the Point3D array of vertices and draws it.
     * The object is drawn with the color specified by drawMaterial = setDrawColor().
     *
     * @param	v	Point3D array containing polygon vertices in counter clockwise order
     */
    public void drawPolygon(Point3D[] v) {
    	drawPolygon3D(new Polygon3D(v));
    }

    /**
     * Constructs a polygon object using the Point3D array of vertices and draws it.
     * The object is drawn with the color specified by drawMaterial = setDrawColor().
     * Specified flags indicate whether to draw with edges, vertices, and or vertex labels.
     *
     * @param	v	Point3D array containing polygon vertices in counter clockwise order
     * @param	drawEdges			Boolean flag indicating whether to draw edges
     * @param	drawVertices		Boolean flag indicating whether to draw vertices
     * @param	drawVertexLabels	Boolean flag indicating whether to draw vertex labels
     */
    public void drawPolygon(Point3D[] v,
    		boolean drawEdges, boolean drawVertices, boolean drawVertexLabels) {
    	drawPolygon3D(new Polygon3D(v, drawEdges, drawVertices, drawVertexLabels));
    }

    /**
     * Draws the polygon object specified.
     *
     * @param	p3d	Polygon3D object to be drawn
     */
    public void drawPolygon3D(Polygon3D p3d) {
    	PhongMaterial m = p3d.getMaterial();
    	if (m == null) {
    		m = drawMaterial;
    		p3d.setMaterial(m);
    	}
    	DrawMode dm = p3d.getDrawMode();
    	if (dm == null) {
    		dm = drawMode;
    		p3d.setDrawMode(dm);
    	}
    	CullFace cf = p3d.getCullFace();
    	if (cf == null) {
    		cf = cullFace;
    		p3d.setCullFace(cf);
    	}

    	Point3D[] v = p3d.getV();
/*
       	// Draw projections
    	int lc = p3d.findLongestComponent();
       	for (int i = 0; i < v.length; i++) {
    		if (lc == 1) {									// X is longest
    			drawLine3D(new Line3D(v[i].getX(),v[i].getY(),v[i].getZ(), 0.0,v[i].getY(),v[i].getZ(), turquoiseMaterial ));
    			drawLine3D(new Line3D(0.0, 0.0, 0.0, 					   0.0,v[i].getY(),v[i].getZ(), turquoiseMaterial ));
    		}
    		else if (lc == 2) {								// Y is longest
       			drawLine3D(new Line3D(v[i].getX(),v[i].getY(),v[i].getZ(),v[i].getX(), 0.0, v[i].getZ(), turquoiseMaterial ));
    			drawLine3D(new Line3D(0.0, 0.0, 0.0, 					  v[i].getX(), 0.0, v[i].getZ(), turquoiseMaterial ));
        		}
    		else if (lc == 3) {								// Z is longest
    			drawLine3D(new Line3D(v[i].getX(),v[i].getY(),v[i].getZ(),v[i].getX(),v[i].getY(), 0.0, turquoiseMaterial));
    			drawLine3D(new Line3D(0.0, 0.0, 0.0, 					  v[i].getX(),v[i].getY(), 0.0, turquoiseMaterial ));
            }
    	}
*/

    	if (v.length > 1) {
    		// Build mesh
    		TriangleMesh mesh = new TriangleMesh();
    		// Add polygon vertices to mesh
			for (int i = 0; i < v.length; i++) {
				if ( axesZupCheckItem.isSelected() ) {
					mesh.getPoints().addAll(
							(float)dY(v[i].getY()) + origin[0],
			 				(float)dZ(v[i].getZ()) + origin[1],
			 				(float)dX(v[i].getX()) + origin[2]);
				}
				else {
					mesh.getPoints().addAll(
				 				(float)dX(v[i].getX()) + origin[0],
				 				(float)dY(v[i].getY()) + origin[1],
				 				(float)dZ(v[i].getZ()) + origin[2]);
				}
			}

    		// Get default Tex Coords
    		mesh.getTexCoords().addAll(0,0);

    		// Get triangle patterns depending on number of vertices
    		int[][] t;
    		if (p3d.isConvexPoly()) {
    			t = p3d.triangulateConvexPolygon();
    		}
    		else {
    			t = p3d.triangulatePolygon();
    		}

    		// Add faces to mesh
    		for (int i = 0; i < t.length; i++) {
    			 mesh.getFaces().addAll(t[i][0], 0, t[i][1], 0, t[i][2], 0);
//   			 System.out.println("Adding faces[" + i + "]: " + t[i][0] + ", 0, " + t[i][1] + ", 0, " + t[i][2] + ", 0, " );
    		}

    		MeshView meshView = new MeshView(mesh);
    		meshView.setMaterial(m);
    		meshView.setDrawMode(dm);
    		meshView.setCullFace(cf);
/*
			int npoints = mesh.getPoints().size();
			float[] points = new float[npoints];
			mesh.getPoints().toArray(points);
			System.out.println("\npolygon npoints= " + npoints);
			for (int i = 0; i < npoints; i=i+3) {
				System.out.println("(" + points[i] +
								  ", " + points[i+1] +
								  ", " + points[i+2] +
								  ")");
			}
			Iterator itr = meshView.getTransforms().iterator();
			while (itr.hasNext()) {
				System.out.println(itr.next());
			}
*/
        	drawList.add(p3d);
    		Group aGroup = dataGroup.get(currentSequenceNum);
        	aGroup.getChildren().addAll(meshView);

        	if (p3d.getDrawEdges() || p3d.getDrawVertices() || p3d.getDrawVertexLabels()) {

	    		for (int i = 0; i < v.length; i++) {
	    			if (p3d.getDrawVertices()) {
	    				Sphere3D vertex = new Sphere3D(v[i], uX(VERTEX_SIZE), blackMaterial);
	    				drawSphere3D(vertex, false);
	    			}
	    			if (p3d.getDrawVertexLabels()) {
	    				Text3D label = null;
	    				if (axesYupCheckItem.isSelected()) {
		    				label = new Text3D(v[i].getX(), v[i].getY(), v[i].getZ(),
	    										  180, 0, 0,
	    										  ("v" + i),
	    										  Color.BLACK);
	    				}
	    				else {
		    				label = new Text3D(v[i].getX(), v[i].getY(), v[i].getZ(),
												  0, 180, 0,
												  ("v" + i),
												  Color.BLACK);

	    				}
	    				label.setLabelFont(Font.font("Regular", 10));
	    				drawLabel3D(label, false);
	    			}
	    			if (p3d.getDrawEdges()) {
	    				PhongMaterial edgeMaterial = p3d.getEdgeMaterial();
	    				Line3D edge = new Line3D(v[i].getX(), v[i].getY(), v[i].getZ(),
	    										 v[next(v.length, i)].getX(), v[next(v.length, i)].getY(), v[next(v.length, i)].getZ(),
	    										 edgeMaterial);
	    				edge.setLineRadius(EDGE_RADIUS);
	    				drawLine3D(edge, false);
	    			}
        		}
    		}
    	}
    }

    /**
	 * Draws a Polyhedron3D object.
	 *
	 * @param	p3d	specifies a Polyhedron3D object with center point, size,
	 * 				x-rotation, y-rotation, z-rotation and drawing parameters.
	 */
	public void drawPolyhedron3D(Polyhedron3D p3d) {
		drawPolyhedron3D(p3d, false);
	}

	/**
	 * Draws a Polyhedron3D object.
	 *
	 * @param	p3d			specifies a Polyahedron3D object with center point, size,
	 * 						x-rotation, y-rotation, z-rotation and drawing parameters.
	 * @param	showEdges	Boolean value indicating whether to show the
	 *						Polyhedron edges (true) or not (false).
	 */
	public void drawPolyhedron3D(Polyhedron3D p3d, boolean showEdges) {
		double xi = p3d.getP().getX();
		double yi = p3d.getP().getY();
		double zi = p3d.getP().getZ();
		double s  = p3d.getSize();
		double rx = p3d.getXr();
		double ry = p3d.getYr();
		double rz = p3d.getZr();
		PhongMaterial m = p3d.getMaterial();
		if (m == null) {
			m = drawMaterial;
			p3d.setMaterial(m);
		}
		DrawMode dm = p3d.getDrawMode();
		if (dm == null) {
			dm = drawMode;
			p3d.setDrawMode(dm);
		}
		CullFace cf = p3d.getCullFace();
		if (cf == null) {
			cf = cullFace;
			p3d.setCullFace(cf);
		}
		if (errorCheck(xi, yi, zi)) {
			return;
		}

    	double x;
    	double y;
    	double z;
    	double rxf = 0.0;
    	double ryf = 0.0;
    	double rzf = 0.0;
    	if ( axesZupCheckItem.isSelected() ) {
    		x = dY(yi);
        	y = dZ(zi);
        	z = dX(xi);
    		rxf = ry;
    		ryf = rz;
    		rzf = rx;
      	}
    	else {
    		x = dX(xi);
        	y = dY(yi);
        	z = dZ(zi);
    		rxf = rx;
    		ryf = ry;
    		rzf = rz;
    	}

    	MeshView t = null;
    	if 		(p3d instanceof Dodecahedron3D)
    		t = new Dodecahedron(dX(s));
    	else if (p3d instanceof Icosahedron3D)
    		t = new Icosahedron(dX(s));
    	else if (p3d instanceof Octahedron3D)
    		t = new Octahedron(dX(s));
    	else if (p3d instanceof Tetrahedron3D)
    		t = new Tetrahedron(dX(s));

		t.setMaterial(m);
		t.setDrawMode(dm);
		t.setCullFace(cf);

		// The following rotation sequences apply rotations in an X, Y, Z order.
    	if ( axesZupCheckItem.isSelected() ) {
    		t.getTransforms().addAll(new Translate(origin[0]+x, origin[1]+y, origin[2]+z),
					 				 new Rotate(ryf,Rotate.Y_AXIS),
					 				 new Rotate(rxf,Rotate.X_AXIS),
					 				 new Rotate(rzf,Rotate.Z_AXIS));
    	}
    	else {
			t.getTransforms().addAll(new Translate(origin[0]+x, origin[1]+y, origin[2]+z),
									 new Rotate(rzf,Rotate.Z_AXIS),
									 new Rotate(ryf,Rotate.Y_AXIS),
									 new Rotate(rxf,Rotate.X_AXIS));
    	}

		drawList.add(p3d);
		Group aGroup = dataGroup.get(currentSequenceNum);
		aGroup.getChildren().addAll(t);

		if (showEdges) {
			drawPolyhedronEdges3D(p3d);
		}
	}

	/**
	 * Draws the edges of a Polyhedron3D object
	 *
	 * @param	p3d			specifies a Polyhedron3D object with center point, size,
	 * 						x-rotation, y-rotation, z-rotation and drawing parameters.
	 */
	public void drawPolyhedronEdges3D(Polyhedron3D p3d) {
		double xi = p3d.getP().getX();
		double yi = p3d.getP().getY();
		double zi = p3d.getP().getZ();
		double rx = p3d.getXr();
		double ry = p3d.getYr();
		double rz = p3d.getZr();

		Line3D[] edges = p3d.getEdges();
		Line3D edge, tedge, redge;
		for (int ei = 0; ei < edges.length; ei++) {
			edge = edges[ei];
//			System.out.println(edge);

			// Rotate edge if necessary and translate
			if (rx != 0 || ry != 0 || rz != 0) {
				redge = rotateLine3D(edge, rx, ry, rz);

				tedge = new Line3D(redge.getp1().getX()+xi,
								   redge.getp1().getY()+yi,
								   redge.getp1().getZ()+zi,
								   redge.getp2().getX()+xi,
								   redge.getp2().getY()+yi,
								   redge.getp2().getZ()+zi);
			}
			else {
				tedge = new Line3D(edge.getp1().getX()+xi,
								   edge.getp1().getY()+yi,
								   edge.getp1().getZ()+zi,
								   edge.getp2().getX()+xi,
								   edge.getp2().getY()+yi,
								   edge.getp2().getZ()+zi);
			}

//			System.out.println("ei=" + ei + ", " + tedge);
/*
			// Color edges
			tedge.setLineRadius(2);
			if (ei==0) {
				tedge.setMaterial(new PhongMaterial(Color.RED));
			}

			else if (ei == 1) {
				tedge.setMaterial(new PhongMaterial(Color.GREEN));
			}
			else if (ei == 2) {
				tedge.setMaterial(new PhongMaterial(Color.BLUE));
			}
			else if (ei == 3) {
				tedge.setMaterial(new PhongMaterial(Color.GOLDENROD));
			}
			else if (ei == 4) {
				tedge.setMaterial(new PhongMaterial(Color.CORAL));
			}
			else if (ei == 5) {
				tedge.setMaterial(new PhongMaterial(Color.TURQUOISE));
			}
			else if (ei == 6) {
				tedge.setMaterial(new PhongMaterial(Color.MAGENTA));
			}
			else if (ei == 7) {
				tedge.setMaterial(new PhongMaterial(Color.CRIMSON));
			}
			else if (ei == 8) {
				tedge.setMaterial(new PhongMaterial(Color.BROWN));
			}
			else if (ei == 9) {
				tedge.setMaterial(new PhongMaterial(Color.THISTLE));
			}
			else if (ei == 10) {
				tedge.setMaterial(new PhongMaterial(Color.YELLOW));
			}
			else if (ei == 11) {
				tedge.setMaterial(new PhongMaterial(Color.LAWNGREEN));
			}
			else {
				tedge.setMaterial(new PhongMaterial(Color.TURQUOISE));
			}
*/
			drawLine3D(tedge);
		}
	}

    /**
     * Draws a polyline as specified by the parameter array of points.
     *
     * @param p	Point3D{} array of points defining successive line segments to be displayed
     */
    public void drawPolyLine3D(Point3D[] p) {
    	for (int i = 0; i < p.length-1; i++) {
    		drawLine3D(new Line3D(p[i], p[i+1]));
    	}
    }

    /**
     * Constructs a rectangle object with specified center point, width, and height,
     * and draws it. Rotations default to zero.
     * The object is drawn with the color specified by drawMaterial = setDrawColor().
     *
     * @param	x	specifies center point x coordinate
     * @param	y	specifies center point y coordinate
     * @param	z	specifies center point z coordinate
     * @param	w	specifies width
     * @param	h	height
     */
    public void drawRectangle(double x, double y, double z, double w, double h) {
    	drawRectangle3D(new Rectangle3D(x, y, z, w, h, drawMaterial));
    }

    /**
     * Constructs a rectangle object with specified center point, width, height,
     * x rotation, y rotation, and z rotation and draws it.
     * The object is drawn with the color specified by drawMaterial = setDrawColor().
     *
     * @param	x	specifies center point x coordinate
     * @param	y	specifies center point y coordinate
     * @param	z	specifies center point z coordinate
     * @param	w	specifies width
     * @param	h	height
     * @param	rx	x-rotation
     * @param	ry	y-rotation
     * @param	rz	z-rotation
     */
    public void drawRectangle(double x, double y, double z, double w, double h,
			  				  double rx, double ry, double rz) {
    	drawRectangle3D(new Rectangle3D(x, y, z, w, h, rx, ry, rz, drawMaterial));
    }

    /**
     * Draws the rectangle object specified.
     *
     * @param	r	Rectangle3D object to be drawn
     */
    public void drawRectangle3D(Rectangle3D r) {
   	   	double xi = r.getP().getX();
    	double yi = r.getP().getY();
    	double zi = r.getP().getZ();
    	double rx = r.getXr();
    	double ry = r.getYr();
    	double rz = r.getZr();
    	double w  = r.getWidth();
    	double h  = r.getHeight();

    	Box3D b = new Box3D(xi, yi, zi, w, 0, h, rx, ry, rz);

    	PhongMaterial m = r.getMaterial();
    	if (m == null) {
    		b.setMaterial(drawMaterial);
    	}
    	else {
    		b.setMaterial(m);
    	}
    	DrawMode dm = r.getDrawMode();
    	if (dm == null) {
    		b.setDrawMode(drawMode);
    	}
    	else {
    		b.setDrawMode(dm);
    	}
    	CullFace cf = r.getCullFace();
    	if (cf == null) {
    		b.setCullFace(cullFace);
    	}
    	else {
    		b.setCullFace(cf);
    	}
    	if (errorCheck(xi, yi, zi)) {
    		return;
    	}

    	drawBox3D(b);
    }

    /**
     * Constructs a Sphere3D object with specified center point and radius and draws it.
     * The object is drawn with the color specified by drawMaterial = setDrawColor().
     *
     * @param	x	specifies center point x coordinate
     * @param	y	specifies center point y coordinate
     * @param	z	specifies center point z coordinate
     * @param	r	specifies radius
     */
    public void drawSphere(double x, double y, double z, double r) {
		Sphere3D s3d = new Sphere3D(x, y, z, r);
		s3d.setMaterial(drawMaterial);
		s3d.setSphereDivisions(sphereDivisions);
    	drawSphere3D(s3d);
    }

    /**
     * Draws the Sphere3D object specified.
     *
     * @param	s3d		Sphere3D object to be drawn
     */
    public void drawSphere3D(Sphere3D s3d) {
    	drawSphere3D(s3d, true);
    }

    /**
     * Draws the Sphere3D object specified.
     *
     * @param	s3d		Sphere3D object to be drawn
     * @param	save
     */
    private void drawSphere3D(Sphere3D s3d, boolean save) {
    	double xi = s3d.getP().getX();
    	double yi = s3d.getP().getY();
    	double zi = s3d.getP().getZ();
    	double r  = s3d.getRadius();
     	double rx = s3d.getXr();
    	double ry = s3d.getYr();
    	double rz = s3d.getZr();
    	int sd	  = s3d.getSphereDivisions();
    	PhongMaterial m = s3d.getMaterial();
    	if (m == null) {
    		m = drawMaterial;
    		s3d.setMaterial(m);
    	}
    	DrawMode dm = s3d.getDrawMode();
    	if (dm == null) {
    		dm = drawMode;
    		s3d.setDrawMode(dm);
    	}
    	CullFace cf = s3d.getCullFace();
    	if (cf == null) {
    		cf = cullFace;
    		s3d.setCullFace(cf);
    	}
    	if (errorCheck(xi, yi, zi)) {
    		return;
    	}

    	double x = 0.0;
    	double y = 0.0;
    	double z = 0.0;
    	double rxf = 0.0;
    	double ryf = 0.0;
    	double rzf = 0.0;
    	Sphere s;
    	if ( axesZupCheckItem.isSelected() ) {
    		s = new Sphere(dX(r), sd);
    		x = dY(yi);
        	y = dZ(zi);
        	z = dX(xi);
    		rxf = ry;
    		ryf = rz;
    		rzf = rx;
    	}
    	else {
    		s = new Sphere(dX(r), sd);
    		x = dX(xi);
    		y = dY(yi);
    		z = dZ(zi);
    		rxf = rx;
    		ryf = ry;
    		rzf = rz;
    	}

       	s.setMaterial(m);
		s.setDrawMode(dm);
		s.setCullFace(cf);

    	if ( axesZupCheckItem.isSelected() ) {
	    	s.getTransforms().addAll(new Translate(origin[0]+x, origin[1]+y, origin[2]+z),
	    							 new Rotate(ryf,Rotate.Y_AXIS),
	    							 new Rotate(rxf,Rotate.X_AXIS),
	    							 new Rotate(rzf,Rotate.Z_AXIS));
    	}
    	else {
        	s.getTransforms().addAll(new Translate(origin[0]+x, origin[1]+y, origin[2]+z),
					 				 new Rotate(rzf,Rotate.Z_AXIS),
					 				 new Rotate(ryf,Rotate.Y_AXIS),
					 				 new Rotate(rxf,Rotate.X_AXIS));
    	}

    	if (save) {
    		drawList.add(s3d);
    	}
    	Group aGroup = dataGroup.get(currentSequenceNum);
    	aGroup.getChildren().addAll(s);
    }

    /**
     * Draws a set of Sphere3D objects
     *
     * @param	ss	Array of Sphere3D objects to be drawn
     */
    public void drawSphere3DSet(Sphere3D[] ss) {
		for (int i = 0; i < ss.length; i++) {
			if (ss[i].getMaterial() == null) {
				ss[i].setMaterial(drawMaterial);
			}
        	drawSphere3D(ss[i]);
    	}
     }

    /**
     * Constructs a square object (Rectangle3D) with specified center point and side length
	 * and draws it.
     * The object is drawn with the color specified by drawMaterial as set by setDrawColor().
     *
     * @param	x	specifies center point x coordinate
     * @param	y	specifies center point y coordinate
     * @param	z	specifies center point z coordinate
     * @param	ln	specifies side length
     *
     */
    public void drawSquare(double x, double y, double z, double ln) {
    	drawRectangle3D(new Rectangle3D(x, y, z, ln, ln, drawMaterial));
    }

    /**
     * Constructs a square object (Rectangle3D) with specified center point, side length,
	 * x-rotation, y-rotation, z-rotation and draws it.
     * The object is drawn with the color specified by drawMaterial as set by setDrawColor().
     *
     * @param	x	specifies center point x coordinate
     * @param	y	specifies center point y coordinate
     * @param	z	specifies center point z coordinate
     * @param	ln	specifies side length
     * @param	rx	x-rotation
     * @param	ry	y-rotation
     * @param	rz	z-rotation
     *
     */
    public void drawSquare(double x, double y, double z, double ln,
    					   double rx, double ry, double rz) {
    	drawRectangle3D(new Rectangle3D(x, y, z, ln, ln, rx, ry, rz, drawMaterial));
    }

    /**
     * Draws tick marks on the axes.
     *
     * @param ln	Line3D specification of a single tick mark
     * @param m		PhongMaterial specifying the color of the tick mark
     */
    private void drawTick3D(Line3D ln) {
    	double x1 = ln.getp1().getX();
    	double y1 = ln.getp1().getY();
    	double z1 = ln.getp1().getZ();
    	double x2 = ln.getp2().getX();
    	double y2 = ln.getp2().getY();
    	double z2 = ln.getp2().getZ();
    	double lr = ln.getLineRadius();
    	PhongMaterial m = ln.getMaterial();

   		Line3D lnt;
    	if ( axesZupCheckItem.isSelected() ) {
       		lnt = new Line3D(new Point3D(dX(x1), dY(y1), dZ(z1)),
       						 new Point3D(dX(x2), dY(y2), dZ(z2)));
    	}
    	else {
    		lnt = new Line3D(new Point3D(dZ(z1), dX(x1), dY(y1)),
			   		   	     new Point3D(dZ(z2), dX(x2), dY(y2)));
    	}

        // Compute length of line segment
    	double r = lnt.length();
//      System.out.println("\nrho= " + r);
    	// Compute mid-point between points
    	Point3D mp = lnt.midpoint();
//      System.out.println("mp= " + mp.toString());
    	// Compute spherical angles for mid-point oriented vector
    	double t = lnt.theta()*180.0/Math.PI;
    	double p = lnt.phi()*180.0/Math.PI;
    	// System.out.println("theta= " + t + " phi=" + p);
    	// Create cylinder of required length
     	Cylinder c = new Cylinder(lr, r);
    	c.setMaterial(m);
    	// Transform cylinder translating center to computed mid-point and rotating as required
    	c.getTransforms().addAll(new Translate(origin[0]+mp.getY(), origin[1]+mp.getZ(), origin[2]+mp.getX()),
		         new Rotate(t-90,Rotate.Y_AXIS),
		         new Rotate(-p,Rotate.Z_AXIS));

    	tickGroup.getChildren().add(c);
    }

    /**
     * Draws a Triangle3D object.
     *
     * @param	t3d	specifies a Triangle3D object with vertices and drawing parameters.
     */
    public void drawTriangle3D(Triangle3D t3d) {
		Point3D[] v = t3d.getV();

		PhongMaterial m = t3d.getMaterial();
		if (m == null) {
			m = drawMaterial;
			t3d.setMaterial(m);
		}
		DrawMode dm = t3d.getDrawMode();
		if (dm == null) {
			dm = drawMode;
			t3d.setDrawMode(dm);
		}
		CullFace cf = t3d.getCullFace();
		if (cf == null) {
			cf = cullFace;
			t3d.setCullFace(cf);
		}

		Point3D[] vp = new Point3D[v.length];
		for (int i = 0; i < v.length; i++) {
			errorCheck(v[i].getX(), v[i].getY(), v[i].getZ());
			if ( axesZupCheckItem.isSelected() ) {
				vp[i] = new Point3D(dY(v[i].getY()) + origin[0],
									dZ(v[i].getZ()) + origin[1],
									dX(v[i].getX()) + origin[2]);
			}
			else {
				vp[i] = new Point3D(dX(v[i].getX()) + origin[0],
				 					dY(v[i].getY()) + origin[1],
				 					dZ(v[i].getZ()) + origin[2]);
			}


		}

		Triangle t = new Triangle(vp);

		t.setMaterial(m);
		t.setDrawMode(dm);
		t.setCullFace(cf);

		drawList.add(t3d);
		Group aGroup = dataGroup.get(currentSequenceNum);
		aGroup.getChildren().addAll(t);
	}

    /**
     * Constructs a Vector3D object with specified end points and draws it.
     * The object is drawn with the color specified by drawMaterial = setDrawColor().
     *
     * @param	v	specifies double[3] array containing x, y, z coordinates of vector end point
     */
    public void drawVector(double[] v) {
		Vector3D v3D = new Vector3D(v);
		v3D.setMaterial(drawMaterial);
		v3D.setVectorRadius(vectorRadius);
		drawVector3D(v3D);
    }

    /**
     * Constructs a Vector3D object with specified end point and draws it.
     * The object is drawn with the color specified by drawMaterial as set by setDrawColor().
     *
     * @param	x	specifies vector end point x coordinate
     * @param	y	specifies vector end point y coordinate
     * @param	z	specifies vector end point z coordinate
     */
    public void drawVector(double x, double y, double z) {
		Vector3D v3D = new Vector3D(x, y, z);
		v3D.setMaterial(drawMaterial);
		v3D.setVectorRadius(vectorRadius);
		drawVector3D(v3D);
    }

    /**
     * Draws the Vector3D object specified.
     *
     * @param	vc	Vector3D object to be drawn with Cartesian coordinates
     */
    public void drawVector3D(Vector3D vc) {
    	double x = vc.getX();
    	double y = vc.getY();
    	double z = vc.getZ();
    	double vr = vc.getVectorRadius();
    	double vectorPointRadius = (vr/VECTOR_RADIUS)*VECTOR_POINT_RADIUS;
    	double vectorPointHeight = (vr/VECTOR_RADIUS)*VECTOR_POINT_HEIGHT;

    	PhongMaterial m = vc.getMaterial();
    	if (m == null) {
    		m = drawMaterial;
    		vc.setMaterial(m);
    	}
    	if (errorCheck(x, y, z)) {
    		return;
    	}

    	double x1;
    	double y1;
    	double z1;
    	if ( axesZupCheckItem.isSelected() ) {
    	// System.out.println("\nVector: Z up: " +
		//			"x= " + x + ", y= " + y + ", z1= " + z);
        	x1 = dX(x);
        	y1 = dY(y);
        	z1 = dZ(z);
      	}
    	else {
    	// System.out.println("\nVector: Y up: " +
		//			"x= " + x + ", y= " + y + ", z= " + z);
        	x1 = dZ(z);
        	y1 = dX(x);
        	z1 = dY(y);
    	}

    	Vector3D vt = new Vector3D(new Point3D(x1, y1, z1));
    	// System.out.println("vt= " + vt);

    	// Compute shortening to allow for point
    	double vlf = vt.length();
    	double vlp = (vlf - vectorPointHeight/3.0)/vlf;
    	Vector3D va = Vector3D.multiply(vlp, vt);

    	double x2 = va.getX()/2.0;
    	double y2 = va.getY()/2.0;
    	double z2 = va.getZ()/2.0;
    	// System.out.println("Vector: mp=" + x2 + ", " + y2 + ", " + z2);

    	Vector3D vs = cartesianToSpherical(va);
    	double r = vs.getX();
    	double t = vs.getY()*180.0/Math.PI;
    	double p = vs.getZ()*180.0/Math.PI;
    	// System.out.println("Vector: rho= " + r + " theta= " + t + " phi=" + p);

    	// Create cylinder of required length
     	Cylinder v = new Cylinder(vr, r);
    	v.setMaterial(m);

    	// Create vector point
    	Cone c = new Cone(vectorPointRadius, vectorPointHeight, avPointDivisions);
    	c.setMaterial(m);

    	// System.out.println("mp: Y (X)= " + y2 + ", Z (Y)=" + z2 + ", X (Z)=" + x2);
    	// System.out.println("Y-rotate= " + (t-90) + ", Z-rotate= " + -p);

    	v.getTransforms().addAll(new Translate(origin[0]+y2,
    										   origin[1]+z2,
    										   origin[2]+x2),
    										   new Rotate(t-90,Rotate.Y_AXIS),
    										   new Rotate(-p,Rotate.Z_AXIS));


    	c.getTransforms().addAll(new Translate(origin[0]+y1,
    										   origin[1]+z1,
    										   origin[2]+x1),
    										   new Rotate(t,Rotate.Y_AXIS),
    										   new Rotate(p+180,Rotate.X_AXIS));

    	drawList.add(vc);
    	Group aGroup = dataGroup.get(currentSequenceNum);
    	aGroup.getChildren().addAll(v, c);
    }

    /**
     * Draws a set of Vector3D objects as specified in the parameter array.
     * @param v	Vector3D{} array of Vector3D objects
     */
    public void drawVector3DSet(Vector3D[] v) {
    	for (int i = 0; i < v.length; i++) {
    		drawVector3D(v[i]);
    	}
    }

    /**
     * Converts Cartesion to spherical coordinates
     * @param	cartCoords	Vector3D with Cartesian coordinates
     * @return	new Vector3D object with sperical coordinates
     */
    private Vector3D cartesianToSpherical(Vector3D cartCoords) {
    	double x = cartCoords.getX();
    	double y = cartCoords.getY();
    	double z = cartCoords.getZ();

    	double rho, theta, phi;

        rho = Math.sqrt((x * x) + (y * y) + (z * z));
        theta = Math.atan2(y , x);
        phi = Math.acos(z / rho);

        return new Vector3D(rho, theta, phi);
    }

    /**
     * Converts spherical to Cartesion coordinates
     * @param	sphereCoords	Vector3D with spherical coordinates
     * @return	new Vector3D object with Cartesian coordinates
     */
    private Vector3D sphericalToCartesian(Vector3D sphereCoords) {
        double a, x, y, z;
        y = sphereCoords.getX() * Math.sin(sphereCoords.getZ());
        a = sphereCoords.getX() * Math.cos(sphereCoords.getZ());
        x = a * Math.cos(sphereCoords.getY());
        z = a * Math.sin(sphereCoords.getY());
        return new Vector3D(x, y, z);
    }

    /**
     * Re-draws all objects from the currently visible data groups as recorded in the
     * respective dataGroup items to the current view.
     */
    private void reDrawList() {
    	// Redraw all currently visible dataGroups
    	int saveViewNumber = viewNumber;
    	for (int i = 0; i < dataGroup.size(); i++) {
    		Group aGroup = dataGroup.get(i);
    		if (aGroup.isVisible()) {
    			aGroup.setVisible(false);
   				aGroup.getChildren().clear();
    			aGroup.setVisible(true);
    			ArrayList<Object> oldDrawList = saveDrawLists.get(i);
    			drawList = new ArrayList<>();
    			saveDrawLists.set(i, drawList);
    			viewNumber = i;
    			currentSequenceNum = i;
    			for (Object object : oldDrawList) {
    	    		reDraw(object);
    			}
    		}
    	}
    	viewNumber = saveViewNumber;
    }

    /**
     * Invoked by reDrawList() to re-draw the respective object types
     */
    private void reDraw(Object object) {
    	if      (object instanceof Arrow3D)     	drawArrow3D((Arrow3D) object);
        else if (object instanceof Box3D)       	drawBox3D((Box3D) object);
        else if (object instanceof Cone3D)      	drawCone3D((Cone3D) object);
        else if (object instanceof Cylinder3D)  	drawCylinder3D((Cylinder3D) object);
        else if (object instanceof Dodecahedron3D)	drawPolyhedron3D((Dodecahedron3D) object);
        else if (object instanceof Drone3D)     	drawDrone3D((Drone3D) object);
        else if (object instanceof Icosahedron3D)	drawPolyhedron3D((Icosahedron3D) object);
        else if (object instanceof Line3D)      	drawLine3D((Line3D) object);
        else if (object instanceof Mesh3D)  		drawMesh3D((Mesh3D) object);
        else if (object instanceof Octahedron3D)	drawPolyhedron3D((Octahedron3D) object);
        else if (object instanceof Oval3D)      	drawOval3D((Oval3D) object);
        else if (object instanceof Plane3D)     	drawPlane3D((Plane3D) object);
        else if (object instanceof Point3D)     	drawPoint3D((Point3D) object);
        else if (object instanceof Polygon3D)   	drawPolygon3D((Polygon3D) object);
        else if (object instanceof Rectangle3D) 	drawRectangle3D((Rectangle3D) object);
        else if (object instanceof Sphere3D)    	drawSphere3D((Sphere3D) object);
        else if (object instanceof Tetrahedron3D)	drawPolyhedron3D((Tetrahedron3D) object);
        else if (object instanceof Text3D)      	drawLabel3D((Text3D) object);
        else if (object instanceof Triangle3D)  	drawTriangle3D((Triangle3D) object);
        else if (object instanceof Vector3D)    	drawVector3D((Vector3D) object);
        else System.out.println("reDraw - Unknown object: " + object.toString());
    }

    /**
     * Flags coordinates exceeding current x,y,z minimums or maximums.
     * Drawing of the respective objects continues.
     */
    private boolean errorCheck(double x, double y, double z) {
    	if (x < minX || x > maxX ||
    		y < minY || y > maxY ||
    		z < minZ || z > maxZ    ) {
 //   		System.out.println("Coordinate out of bounds: (x, y, z)= " + "(" + x + ", " + y + ", " + z +  ")\n" +
 //  						   "minmaxX= -+" + maxX + ", minmaxY= -+" + maxY  + ", minmaxZ= -+" + maxZ);
    	}
    	return false;
    }

    /**
     * Creates MenuBar items and defines associated actions.
     */
    private MenuBar addMenuBar() {
        MenuBar menuBar = new MenuBar();
        menuBar.setStyle("-fx-background-color: lightblue");
        Menu mainMenu = new Menu("Main");

        MenuItem openItem = new MenuItem("Open");
        openItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	FileChooser fileChooser = new FileChooser();
            	fileChooser.setTitle("Open Data File");
            	String currentDirectory = findDefaultDirectory("Resource");
            	fileChooser.setInitialDirectory(new File(currentDirectory));
            	fileChooser.getExtensionFilters().addAll(
            	         new FileChooser.ExtensionFilter("Data Files", "*.txt"),
            	         new FileChooser.ExtensionFilter("Image Files", "*.bmp", "*.png", "*.jpg", "*.gif"),
            	         new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
            	         new FileChooser.ExtensionFilter("All Files", "*.*"));
            	File file = fileChooser.showOpenDialog(stage);
                if (file != null) {
                	System.out.println("Open file: " + file.toString());
                    openFile(file);
                }
            }
        });

        MenuItem loadDataItem = new MenuItem("Load Data");
        loadDataItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	FileChooser fileChooser = new FileChooser();
            	fileChooser.setTitle("Load Data File");
            	String currentDirectory = findDefaultDirectory("Resource");
            	fileChooser.setInitialDirectory(new File(currentDirectory));
            	fileChooser.getExtensionFilters().addAll(
            	         new FileChooser.ExtensionFilter("Data Files", "*.txt"));
            	File file = fileChooser.showOpenDialog(stage);
                if (file != null) {
                	System.out.println("Load file: " + file.toString());
                	readData(file);
                }
            }
        });

        MenuItem runAlgorithmItem = new MenuItem("Run Algorithm");
        runAlgorithmItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent ae) {
            	if (algorithmTaskList.size() > 0) {
            		System.out.println("Algorithms now in task list must be run before single algorithms");
            	}
            	else {
	            	FileChooser fileChooser = new FileChooser();
	            	fileChooser.setTitle("Run Algorithm");
	            	String currentDirectory = findDefaultDirectory("Executable");
	            	fileChooser.setInitialDirectory(new File(currentDirectory));
	            	fileChooser.getExtensionFilters().addAll(
	            	         new FileChooser.ExtensionFilter("Class Files", "*.class"));
	            	File file = fileChooser.showOpenDialog(stage);
	            	if (file != null) {
	            		String absolutePath = file.getAbsolutePath();
	            		String fileName = file.getName();
		            	StringTokenizer st = new StringTokenizer(fileName, ".");
		            	String className = st.nextToken();
	            		if (absolutePath.contains(packagePath)) {
	            			className = packagePrefix + className;
	            		}
		        		Algorithm algorithm = null;
		        		if (className != null) {
		        			try {
		        				algorithm = (Algorithm) (Class.forName(className)).newInstance();
		        				if (algorithm.isDrone()) {
		        					viewDroneCount.set(viewNumber, 1);
		        					viewDroneState.get(viewNumber).clear();
		        					viewDroneState.get(viewNumber).add(new Drone3D(0));
		        				}
		        				System.out.println("Running: " + className);
//		        				currentSequenceNum = viewNumber;
		        				Task<Object> algorithmTask = buildAlgorithmTask(algorithm);
        						algorithmTaskList.add(algorithmTask);
		        				Executors.newSingleThreadExecutor().execute(algorithmTask);
		        				algorithmTaskList.clear();
		        			} catch (Exception e) {
		        				System.out.println("Failed to load " + className);
		        				e.printStackTrace();
		        			}
	            		}
	            	}
            	}
            }
        });

        MenuItem loadAlgorithmsItem = new MenuItem("Load AlgorithmS");
        loadAlgorithmsItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent ae) {
            	FileChooser fileChooser = new FileChooser();
            	fileChooser.setTitle("Load AlgorithmS");
            	String currentDirectory = findDefaultDirectory("Executable");
            	fileChooser.setInitialDirectory(new File(currentDirectory));
            	fileChooser.getExtensionFilters().addAll(
            	         new FileChooser.ExtensionFilter("Class Files", "*.class"));
            	File file = fileChooser.showOpenDialog(stage);
            	if (file != null) {
            		String absolutePath = file.getAbsolutePath();
	            	String fileName = file.getName();
	            	StringTokenizer st = new StringTokenizer(fileName, ".");
	            	String className = st.nextToken();
            		if (absolutePath.contains(packagePath)) {
            			className = packagePrefix + className;
            		}
	        		Algorithm algorithm = null;
	        		if (className != null) {
	        			buildTask: try {
	        				algorithm = (Algorithm) (Class.forName(className)).newInstance();
	        				if (algorithm.isDrone() ) {
	        					int vDroneCount = viewDroneCount.get(viewNumber);
			        			if (vDroneCount < 2) {
			        				vDroneCount++;
			        				algorithm.setId(vDroneCount-1);
			        				viewDroneCount.set(viewNumber, vDroneCount);
			        				viewDroneState.get(viewNumber).add(new Drone3D(vDroneCount-1));
			            			setupDroneViews(viewNumber, vDroneCount);
			        			}
			        			else {
			        				System.out.println("Only two drones allowed.");
			        				algorithm = null;
			        				break buildTask;
			        			}
			        		}
	            			Task<Object> algorithmTask = buildAlgorithmTask(algorithm);
			        		algorithmTaskList.add(algorithmTask);
	        			} catch (Exception e) {
	        				System.out.println("Failed to load " + className);
	        				e.printStackTrace();
	        			}
            		}
            	}
            }
        });

        MenuItem runAlgorithmsItem = new MenuItem("Run AlgorithmS");
        runAlgorithmsItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent ae) {
            	System.out.println("runAlgorithmsItem algorithmTaskList.size= " + algorithmTaskList.size());
            	for (int i = 0; i < algorithmTaskList.size(); i++) {
                	System.out.println("Run Algorithm i= " + i);
            		Executors.newSingleThreadExecutor().execute(algorithmTaskList.get(i));
            	}
            	algorithmTaskList.clear();
            }
        });

        MenuItem saveDataItem = new MenuItem("Save Data File");
        saveDataItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	FileChooser fileChooser = new FileChooser();
            	fileChooser.setTitle("Save Data File");
            	String currentDirectory = findDefaultDirectory("Resource");
            	fileChooser.setInitialDirectory(new File(currentDirectory));
            	fileChooser.getExtensionFilters().addAll(
            	         new FileChooser.ExtensionFilter("Data Files", "*.txt"));
            	File file = fileChooser.showSaveDialog(stage);
                if (file != null) {
                	System.out.println("Save file: " + file.toString());
                	writeData(file);
                }
            }
        });

        Menu saveSceneMenu = new Menu("Save Scene");
        MenuItem bmpItem = new MenuItem("bmp");
        MenuItem jpgItem = new MenuItem("jpg");
        MenuItem pngItem = new MenuItem("png");
        MenuItem gifItem = new MenuItem("gif");
        saveSceneMenu.getItems().addAll(bmpItem, jpgItem, pngItem, gifItem);

        bmpItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
	        	saveScene(scene, "bmp");
	        }
	    });

        jpgItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
	        	saveScene(scene, "jpg");
	        }
	    });

        pngItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
	        	saveScene(scene, "png");
	        }
	    });

        gifItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
	        	saveScene(scene, "gif");
	        }
	    });

        Menu saveSubSceneMenu = new Menu("Save SubScene");
        MenuItem bmpsubItem = new MenuItem("bmp");
        MenuItem jpgsubItem = new MenuItem("jpg");
        MenuItem pngsubItem = new MenuItem("png");
        MenuItem gifsubItem = new MenuItem("gif");
        saveSubSceneMenu.getItems().addAll(bmpsubItem, jpgsubItem, pngsubItem, gifsubItem);

        bmpsubItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
	        	saveSubScene(subScene, "bmp");
	        }
	    });

        jpgsubItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
	        	saveSubScene(subScene, "jpg");
	        }
	    });

        pngsubItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
	        	saveSubScene(subScene, "png");
	        }
	    });

        gifsubItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
	        	saveSubScene(subScene, "gif");
	        }
	    });

        mainMenu.getItems().addAll( openItem, loadDataItem,
        							runAlgorithmItem, loadAlgorithmsItem, runAlgorithmsItem,
        						    saveDataItem, saveSceneMenu, saveSubSceneMenu);

        Menu viewMenu = new Menu("View");
        axesCheckItem = new CheckMenuItem("Axes");
        axesYupCheckItem = new CheckMenuItem("Axis Y Up");
        axesZupCheckItem = new CheckMenuItem("Axis Z Up");
        axesXYonlyCheckItem = new CheckMenuItem("Axes X and Y Only");
        axesZupCheckItem = new CheckMenuItem("Axis Z Up");
        tickCheckItem = new CheckMenuItem("Tick Marks");
        alightCheckItem = new CheckMenuItem("Ambient Light");
        plightCheckItem = new CheckMenuItem("Point Light");
        cubeCheckItem = new CheckMenuItem("Boundary Cube");
        gridCheckItem = new CheckMenuItem("Default Grid");

        viewMenu.getItems().addAll(axesCheckItem, axesYupCheckItem, axesZupCheckItem,
        						   axesXYonlyCheckItem, tickCheckItem, alightCheckItem,
        						   plightCheckItem, cubeCheckItem, gridCheckItem);

        Menu helpMenu = new Menu("Help");
        MenuItem helpItem = new MenuItem("Open Help Text");
        helpItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	String currentDirectory = findDefaultDirectory("Help");
        		String fileName = currentDirectory + "Help.txt";
        		File file = new File(fileName);
                if (file != null) {
                	System.out.println("Open help file: " + file.toString());
                    openFile(file);
                }
            }
        });
        helpMenu.getItems().addAll(helpItem);

        axesCheckItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	if (axesCheckItem.isSelected()) {
                    tickCheckItem.setSelected(true);
                    tickGroup.setVisible(true);
            	}
            	else {
                    tickCheckItem.setSelected(false);
                    tickGroup.setVisible(false);
            	}
            	axisGroup.setVisible(!axisGroup.isVisible());
	        }
	    });

        tickCheckItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	tickGroup.setVisible(!tickGroup.isVisible());
            	if (tickCheckItem.isSelected() && !axesCheckItem.isSelected()) {
            		axesCheckItem.setSelected(true);
            		axisGroup.setVisible(true);
            	}
	        }
	    });

        axesYupCheckItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	axesZupCheckItem.setSelected(!axesZupCheckItem.isSelected());
            	axesXYonlyCheckItem.setSelected(false);
            	reBuildAxesTicks();
            	reDrawList();
	        }
	    });

        axesXYonlyCheckItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	axesYupCheckItem.setSelected(axesXYonlyCheckItem.isSelected());
            	axesZupCheckItem.setSelected(!axesXYonlyCheckItem.isSelected());
            	reBuildAxesTicks();
            	reDrawList();
//            	setCamera(0.0, -180.0,  -1100.0);
	        }
	    });

        axesZupCheckItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	axesYupCheckItem.setSelected(!axesYupCheckItem.isSelected());
            	axesXYonlyCheckItem.setSelected(false);
            	reBuildAxesTicks();
            	reDrawList();
	        }
	    });

        alightCheckItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	setAmbientLight(alightCheckItem.isSelected(), false);
	        }
	    });

        plightCheckItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	setPointLight(plightCheckItem.isSelected(), false);
	        }
	    });

        cubeCheckItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                boundaryCubeGroup.setVisible(!boundaryCubeGroup.isVisible());
	        }
	    });

        gridCheckItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	defaultGridGroup.setVisible(!defaultGridGroup.isVisible());
	        }
	    });

        menuBar.getMenus().addAll(mainMenu, viewMenu, helpMenu);

        return menuBar;
    }

    private String findDefaultDirectory(String type) {
    	String currentDirectory = null;
    	String userDir = System.getProperty("user.dir");
    	if (type == "Resource") {
	    	currentDirectory = userDir + "\\src\\Resources";
	    	File dir = new File(currentDirectory);
			if (!dir.exists()) {
				currentDirectory = userDir + "\\Resources";
				dir = new File(currentDirectory);
				if (!dir.exists()) {
					currentDirectory = userDir;
				}
	    	}
    	}
    	else if (type == "Help") {
	    	currentDirectory = userDir + "\\bin\\org\\edisonwj\\draw3d\\";
	    	File dir = new File(currentDirectory);
			if (!dir.exists()) {
		    	currentDirectory = userDir + "\\org\\edisonwj\\draw3d\\";
		    	dir = new File(currentDirectory);
			}
    	}
    	else if (type == "Executable") {
	    	currentDirectory = userDir + "\\bin\\org\\edisonwj\\draw3d\\";
	    	File dir = new File(currentDirectory);
			if (!dir.exists()) {
				currentDirectory = userDir;
			}
    	}

    	return currentDirectory;
    }

	/**
	 * Opens the specified file using the default application for its extension.
	 *
	 * @param pathName	String specification of file path
	 * @param fileName	String specification of file name
	 */
	public void openFile(String pathName, String fileName) {
		String filePath = "";
		if (pathName == null) {
	    	filePath = findDefaultDirectory("Resource") + "/" + fileName;
		}
		else {
			filePath = pathName + "/" + fileName;
		}
		File file = new File(filePath);
    	System.out.println("Open file: " + file.toString());
        openFile(file);
	}

    /**
	 * Opens the specified file using the default application for its extension.
	 *
	 * @param fileName	String specification of file name in current working directory
	 * 					(or Resources directory if present)
     */
    public void openFile(String fileName) {
	    String filePath = findDefaultDirectory("Resource") + "/" + fileName;
		File file = new File(filePath);
    	System.out.println("Open file: " + file.toString());
    	openFile(file);
    }

    private void openFile(File filename) {
        try {
            desktop.open(filename);
        } catch (IOException ex) {
            Logger.getLogger(
                Draw3D.class.getName()).log(
                    Level.SEVERE, null, ex
                );
        }
    }

    /**
     * Loads the specified data file.
     *
	 * @param pathName	String specification of the full file path
	 * @param fileName	String specification of file name
     */
    public void loadData(String pathName, String fileName) {
		String filePath = pathName + "/" + fileName;
		File file = new File(filePath);
    	System.out.println("Load data: " + file.toString());
    	readData(file);
    }

    /**
     * Loads the specified data file.
     *
	 * @param fileName	String specification of file name in current working directory
	 * 					(or Resources directory if present)
     */
    public void loadData(String fileName) {
	    String filePath = findDefaultDirectory("Resource") + "/" + fileName;
		File file = new File(filePath);
    	System.out.println("Load data: " + file.toString());
    	readData(file);
    }

	private void readData(File file) {
		int saveDataGroupSize = dataGroup.size();
		try {
			String lineString;
			String dataType;
			BufferedReader in = new BufferedReader(new FileReader(file));

			while ((lineString = in.readLine()) != null) {
//				System.out.println("lineString: " + lineString);
				StringTokenizer st = new StringTokenizer(lineString, ",;: ");
				dataType = st.nextToken().trim();

				if (dataType.equals("//")) {
					continue;
				}
				else if (dataType.equals("DataGroup")) {
					for (int i = 0; i < dataGroup.size(); i++) {
						dataGroup.get(i).setVisible(false);
					}
					currentSequenceNum = dataGroup.size()-1;
					incrSequence();
					dataGroup.get(currentSequenceNum).setVisible(true);
					viewNumber = currentSequenceNum;
				}
				else if (dataType.equals("View")) {
					boolean[] viewSettings = new boolean[st.countTokens()];
					int i = 0;
					while (st.hasMoreTokens()) {
						viewSettings[i++] = Boolean.valueOf(st.nextToken());
					}
					setViewSettings(viewSettings);
				}
				else if (dataType.equals("Camera")) {
					double xAngle = Double.valueOf(st.nextToken()).doubleValue();
					double yAngle = Double.valueOf(st.nextToken()).doubleValue();
					double zDistance = Double.valueOf(st.nextToken()).doubleValue();
					setCamera(xAngle, yAngle, zDistance);
				}
				else if (dataType.equals("Origin")) {
					int xOrigin = Integer.valueOf(st.nextToken());
					int yOrigin = Integer.valueOf(st.nextToken());
					int zOrigin = Integer.valueOf(st.nextToken());
					setOriginView(xOrigin, yOrigin, zOrigin);
				}
				else if (dataType.equals("Range")) {
					double minmaxX = Double.valueOf(st.nextToken()).doubleValue();
					double minmaxY = Double.valueOf(st.nextToken()).doubleValue();
					double minmaxZ = Double.valueOf(st.nextToken()).doubleValue();
					setXYZRange(minmaxX, minmaxY, minmaxZ);
					reBuildAxesTicks();
				}
				else if (dataType.equals("Color")) {
					Color c1 = Color.web(st.nextToken());
					setDrawColor(c1);
					if (st.hasMoreTokens()) {
						Color c2 = Color.web(st.nextToken());
						setDrawColor(c1, c2);
					}
				}
				else if (dataType.equals("LineRadius")) {
					double r = Double.valueOf(st.nextToken()).doubleValue();
					setLineRadius(r);
				}
				else if (dataType.equals("Title1")) {
					StringTokenizer tst = new StringTokenizer(lineString, ":");
					String t1 = tst.nextToken().trim();
					String t2 = tst.nextToken().trim();
					setLabelFont(Font.font("Regular", 10));
					drawLabel(0, 1.4, 0, 180, 0, 0, t2);
				}
				else if (dataType.equals("Title2")) {
					StringTokenizer tst = new StringTokenizer(lineString, ":");
					String t1 = tst.nextToken().trim();
					String t2 = tst.nextToken().trim();
					setLabelFont(Font.font("Regular", 10));
					drawLabel(0, 1.3, 0, 180, 0, 0, t2);
				}
				else if (dataType.equals("Arrow")) {
					drawArrow3D(new Arrow3D(lineString));
				}
				else if (dataType.equals("Box")) {
					drawBox3D(new Box3D(lineString));
				}
				else if (dataType.equals("Circle")) {
					double x, y, z, r;
					x = Double.valueOf(st.nextToken()).doubleValue();
					y = Double.valueOf(st.nextToken()).doubleValue();
					z = Double.valueOf(st.nextToken()).doubleValue();
					r = Double.valueOf(st.nextToken()).doubleValue();
					drawCircle(x, y, z, r);
				}
				else if (dataType.equals("Cone")) {
					drawCone3D(new Cone3D(lineString));
				}
				else if (dataType.equals("Cylinder")) {
					drawCylinder3D(new Cylinder3D(lineString));
				}
				else if (dataType.equals("Dodecahedron")) {
					drawPolyhedron3D(new Dodecahedron3D(lineString));
				}
				else if (dataType.equals("Drone")) {
					drawDrone3D(new Drone3D(lineString));
				}
				else if (dataType.equals("Icosahedron")) {
					drawPolyhedron3D(new Icosahedron3D(lineString));
				}
				else if (dataType.equals("Line")) {
					drawLine3D(new Line3D(lineString));
				}
				else if (dataType.equals("Octahedron")) {
					drawPolyhedron3D(new Octahedron3D(lineString));
				}
				else if (dataType.equals("Oval")) {
					drawOval3D(new Oval3D(lineString));
				}
				else if (dataType.equals("Plane")) {
					drawPlane3D(new Plane3D(lineString));
				}
				else if (dataType.equals("Polygon")) {
					drawPolygon3D(new Polygon3D(lineString));
				}
				else if (dataType.equals("PolyLine")) {
					Point3D[] v;
					ArrayList<Point3D> vTemp = new ArrayList<>();
					double x, y, z;
					while (st.hasMoreTokens()) {
						x = Double.valueOf(st.nextToken()).doubleValue();
						y = Double.valueOf(st.nextToken()).doubleValue();
						z = Double.valueOf(st.nextToken()).doubleValue();
						vTemp.add(new Point3D(x, y, z));
					}
					v = vTemp.toArray(new Point3D[vTemp.size()]);
					drawPolyLine3D(v);
				}
				else if (dataType.equals("Point")) {
					double x, y, z;
					x = Double.valueOf(st.nextToken()).doubleValue();
					y = Double.valueOf(st.nextToken()).doubleValue();
					z = Double.valueOf(st.nextToken()).doubleValue();
					Point3D p = new Point3D(x, y, z);
					drawPoint3D(p);
				}
				else if (dataType.equals("Rectangle")) {
					drawRectangle3D(new Rectangle3D(lineString));
				}
				else if (dataType.equals("Sphere")) {
					drawSphere3D(new Sphere3D(lineString));
				}
				else if (dataType.equals("Tetrahedron")) {
					drawPolyhedron3D(new Tetrahedron3D(lineString));
				}
				else if (dataType.equals("Text")) {
					drawLabel3D(new Text3D(lineString));
				}
				else if (dataType.equals("Triangle")) {
					drawTriangle3D(new Triangle3D(lineString));
				}
				else if (dataType.equals("Vector")) {
					drawVector3D(new Vector3D(lineString));
				}
				else {
					System.out.println("Unknown data type");
				}
			}
			in.close();
		}
		catch (IOException e) {
			System.out.println("File error: " + e);
		}
		setStart(saveDataGroupSize);
	}

	/**
	 * Loads the specified Algorithm class.
	 *
	 * @param	className	String name of the Algorithm class file to be executed
	 * @return	algorithm	Returns loaded Algorithm class object
	 */
	public Algorithm loadAlgorithm(String className) {
		System.out.println("Loading: " + className + ", currentSequenceNumber: " + currentSequenceNum);
		Algorithm algorithm = null;
		loadAlgorithm: try {
			ArrayList<String> classList = viewClassList.get(currentSequenceNum);
			classList.add(className);
			algorithm = (Algorithm) (Class.forName(className)).newInstance();
			ArrayList<Algorithm> algorithmList = viewAlgorithmList.get(currentSequenceNum);
			algorithmList.add(algorithm);
			if (algorithm.isDrone() ) {
				int vDroneCount = viewDroneCount.get(currentSequenceNum);
    			if (vDroneCount < 2) {
    				vDroneCount++;
    				algorithm.setId(vDroneCount-1);
    				viewDroneCount.set(currentSequenceNum, vDroneCount);
    				viewDroneState.get(currentSequenceNum).add(new Drone3D(vDroneCount-1));
        			setupDroneViews(currentSequenceNum, vDroneCount);
    			}
    			else {
    				System.out.println("Only two drones allowed for concurrent execution.");
    				algorithm = null;
    				break loadAlgorithm;
    			}
    		}
		} catch (Exception e) {
			System.out.println("Failed to load " + className);
			e.printStackTrace();
		}

		return algorithm;
	}

    /**
     * Sets up views for Drone3D executions
     *
     * @param droneCount	int count of drones
     */
	private void setupDroneViews(int viewNum, int droneCount) {
		ArrayList<String> dummyClassList = new ArrayList<>(1);
		ArrayList<Algorithm> dummyAlgorithmList = new ArrayList<>(1);
		ArrayList<Object> dummyDroneList = new ArrayList<>();
		Group aGroup = new Group();
		aGroup.setVisible(true);
		viewDroneCount.add(0);
		viewDroneState.add(new ArrayList<Drone3D>());

		viewClassList.add(dummyClassList);
		viewAlgorithmList.add(dummyAlgorithmList);
		saveDrawLists.add(dummyDroneList);
		dataGroup.add(aGroup);
		objectGroup.getChildren().addAll(aGroup);


		saveCamera(viewNum+droneCount);
    	saveOrigin(viewNum+droneCount);
    	saveRange(viewNum+droneCount);
    	saveScale(viewNum+droneCount);
		saveViewSettings(viewNum+droneCount);

		droneIncrement = droneCount;
    }

	/**
	 * Saves the current scene with the specified format to the specified file.
	 *
	 * @param format	String "bmp", "jpg", "png", "gif"
	 * @param pathName	String specification of file path
	 * @param fileName	String specification of file name
	 */
	public void saveScene(String format, String pathName, String fileName) {
		String filePath = "";
		if (pathName == null) {
	    	filePath = findDefaultDirectory("Resource") + "/" + fileName;
		}
		else {
			filePath = pathName + "/" + fileName;
		}
		File file = new File(filePath);
		doSaveScene(scene, format, file);
	}

	/**
	 * Saves the current scene with the specified format to the specified file.
	 *
	 * @param format	String "bmp", "jpg", "png", "gif"
	 * @param fileName	String specification of file name in current working directory
	 * 					(or Resources directory if present)
     */
    public void saveScene(String format, String fileName) {
	    String filePath = findDefaultDirectory("Resource") + "/" + fileName;
		File file = new File(filePath);
		doSaveScene(scene, format, file);
	}

	private void saveScene(Scene scene, String format) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Scene " + format);
        String currentDirectory = findDefaultDirectory("Resource");
        fileChooser.setInitialDirectory(new File(currentDirectory));
        fileChooser.getExtensionFilters().addAll(
        		new FileChooser.ExtensionFilter("Image Files", "*." + format));
        File file = fileChooser.showSaveDialog(stage);
        doSaveScene(scene, format, file);
	}

	private void doSaveScene(Scene scene, String format, File file) {
        System.out.println("Save Scene file: " + file);
        if (file != null) {
        	WritableImage writableImage=scene.snapshot(null);
        	if (format == "png" || format == "gif") {
            	try {
            		ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), format, file);
            	} catch (IOException ex) {
            		System.out.println(ex.getMessage());
            	}
        	}
        	else if (format == "bmp" || format == "jpg") {
	        	BufferedImage image = SwingFXUtils.fromFXImage(writableImage, null);  // Get buffered image.
	        	BufferedImage imageRGB = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.OPAQUE); // Remove alpha-channel from buffered image.
	        	Graphics2D graphics = imageRGB.createGraphics();
	        	graphics.drawImage(image, 0, 0, null);
	        	try {
	        		ImageIO.write(imageRGB, format, file);
            	} catch (IOException ex) {
            		System.out.println(ex.getMessage());
            	}
	        	graphics.dispose();
        	}
        }
	}

	/**
	 * Saves the current subScene with the specified format to the specified file.
	 *
	 * @param format	String "bmp", "jpg", "png", "gif"
	 * @param pathName	String specification of file path
	 * @param fileName	String specification of file name
	 */
	public void saveSubScene(String format, String pathName, String fileName) {
		String filePath = "";
		if (pathName == null) {
	    	filePath = findDefaultDirectory("Resource") + "/" + fileName;
		}
		else {
			filePath = pathName + "/" + fileName;
		}
		File file = new File(filePath);
		doSaveSubScene(subScene, format, file);
	}

	/**
	 * Saves the current subScene with the specified format to the specified file.
	 *
	 * @param format	String "bmp", "jpg", "png", "gif"
	 * @param fileName	String specification of file name in current working directory
	 * 					(or Resources directory if present)
     */
    public void saveSubScene(String format, String fileName) {
	    String filePath = findDefaultDirectory("Resource") + "/" + fileName;
		File file = new File(filePath);
		doSaveSubScene(subScene, format, file);
	}

	private void saveSubScene(SubScene scene, String format) {
		System.out.println("format=" + format);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save SubScene " + format);
        String currentDirectory = findDefaultDirectory("Resource");
        fileChooser.setInitialDirectory(new File(currentDirectory));
        fileChooser.getExtensionFilters().addAll(
        		new FileChooser.ExtensionFilter("Image Files", "*." + format));
        File file = fileChooser.showSaveDialog(stage);
        doSaveSubScene(scene, format, file);
	}

	private void doSaveSubScene(SubScene scene, String format, File file) {
        System.out.println("Save SubScene file: " + file);
        if (file != null) {
        	WritableImage writableImage=scene.snapshot(new SnapshotParameters(), null);
        	if (format == "png" || format == "gif") {
            	try {
            		ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), format, file);
            	} catch (IOException ex) {
            		System.out.println(ex.getMessage());
            	}
        	}
        	else if (format == "bmp" || format == "jpg") {
	        	BufferedImage image = SwingFXUtils.fromFXImage(writableImage, null);  // Get buffered image.
	        	BufferedImage imageRGB = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.OPAQUE); // Remove alpha-channel from buffered image.
	        	Graphics2D graphics = imageRGB.createGraphics();
	        	graphics.drawImage(image, 0, 0, null);
	        	try {
	        		ImageIO.write(imageRGB, format, file);
            	} catch (IOException ex) {
            		System.out.println(ex.getMessage());
            	}
	        	graphics.dispose();
        	}
        }
	}

	/**
	 * Saves current view data to the specified data file.
	 *
	 * @param pathName	String specification of file path
	 * @param fileName	String specification of file name
	 */
	public void saveData(String pathName, String fileName) {
		String filePath = "";
		if (pathName == null) {
	    	filePath = findDefaultDirectory("Resource") + "/" + fileName;
		}
		else {
			filePath = pathName + "/" + fileName;
		}
		File file = new File(filePath);
    	System.out.println("Save data file: " + file.toString());
        writeData(file);
	}

    /**
     * Saves current view data to the specified data file.
     *
	 * @param fileName	String specification of file name in current working directory
	 * 					(or Resources directory if present)
     */
    public void saveData(String fileName) {
	    String filePath = findDefaultDirectory("Resource") + "/" + fileName;
		File file = new File(filePath);
    	System.out.println("Save data file: " + file.toString());
    	writeData(file);
    }

    private void writeData(File file) {
		try {
			PrintWriter out = new PrintWriter(file);

//			saveDrawLists.add(drawList);
			if (saveDrawLists.size() > 0) {
				for (int i = 0; i < saveDrawLists.size(); i++ ) {
					Group aGroup = dataGroup.get(i);
					if (!aGroup.isVisible()) {
						continue;
					}
					ArrayList<Object> aDrawList = saveDrawLists.get(i);
					out.println("DataGroup: " + i);

					out.println("View: " +
					        axesCheckItem.isSelected() + ", " +
			        		tickCheckItem.isSelected() + ", " +
			        		axesZupCheckItem.isSelected() + ", " +
			        		cubeCheckItem.isSelected() + ", " +
			        		alightCheckItem.isSelected() + ", " +
			        		plightCheckItem.isSelected() + ", " +
			        		cumulate + ", " +
			        		gridCheckItem.isSelected() + ", " +
			        		axesXYonlyCheckItem.isSelected() );

					out.println("Camera: " +
								cameraXform.rx.getAngle() + ", " +
								cameraXform.ry.getAngle() + ", " +
								camera.getTranslateZ());

					out.println("Origin: " +
								origin[0] + ", " +
								origin[1] + ", " +
								origin[2]);

					out.println("Range: " +
								maxX +", " +
								maxY +", " +
								maxZ);

					for (int j = 0; j < aDrawList.size(); j++) {
						Object object = aDrawList.get(j);
						if (object instanceof Point3D) {
							Point3D p = (Point3D)object;
							out.printf("Point: %5.1f,%5.1f,%5.1f%n",
									p.getX(), p.getY(), p.getZ());
						}
						else if (object instanceof Arrow3D) {
							out.println(((Arrow3D)object).outString());
						}
						else if (object instanceof Box3D) {
							out.println(((Box3D)object).outString());
						}
						else if (object instanceof Cone3D) {
							out.println(((Cone3D)object).outString());
						}
						else if (object instanceof Cylinder3D) {
							out.println(((Cylinder3D)object).outString());
						}
						else if (object instanceof Dodecahedron3D) {
							out.println(((Dodecahedron3D)object).outString());
						}
						else if (object instanceof Drone3D) {
							out.println(((Drone3D)object).outString());
						}
						else if (object instanceof Icosahedron3D) {
							out.println(((Icosahedron3D)object).outString());
						}
						else if (object instanceof Line3D) {
							out.println(((Line3D)object).outString());
						}
						else if (object instanceof Octahedron3D) {
							out.println(((Octahedron3D)object).outString());
						}
						else if (object instanceof Oval3D) {
							out.println(((Oval3D)object).outString());
						}
						else if (object instanceof Plane3D) {
							out.println(((Plane3D)object).outString());
						}
						else if (object instanceof Polygon3D) {
							out.println(((Polygon3D)object).outString());
						}
						else if (object instanceof Rectangle3D) {
							out.println(((Rectangle3D)object).outString());
						}
						else if (object instanceof Sphere3D) {
							out.println(((Sphere3D)object).outString());
						}
						else if (object instanceof Tetrahedron3D) {
							out.println(((Tetrahedron3D)object).outString());
						}
						else if (object instanceof Text3D) {
							out.println(((Text3D)object).outString());
						}
						else if (object instanceof Triangle3D) {
							out.println(((Triangle3D)object).outString());
						}
						else if (object instanceof Vector3D) {
							out.println(((Vector3D)object).outString());
						}
						else
							System.out.println("writeData: Unknown object: " + object.toString() );
					}
				}
			}
			out.close();
		}
		catch (IOException e) {
			System.out.println("File error: " + e);
		}
    }

    /**
     * Builds Tasks corresponding to algorithm classes loaded via the Main Menu
     * @param algorithm	The specific Algorithm class to be executed by this task,
     * 					being an implementation of the Algorithm interface, and
     * 					providing for concurrent execution of multiple Algorithm tasks.
     * 					A special case supports the execution of two communicating
     * 					Drone3D Algorithms
     * @return	algorithmTask	Task ready for execution via the Run Algorithm
     * 							or Run AlgorithmS selection of the Main Menu
     */
    private Task<Object> buildAlgorithmTask(Algorithm algorithm) {
    		if (!Arrays.equals(origin, originView)) {
    			origin = Arrays.copyOf(originView, originView.length);
    			reBuildAxesTicks();
    		}

	        Task<Object> algorithmTask = new Task<Object>() {
            @Override public Object call() throws Exception {
            	int iterations = algorithm.getIterations();
              	long delay = algorithm.getDelay();
                for (int i = 0; i < iterations; i++) {
                	if (isCancelled()) {
	                    updateMessage("Cancelled");
                		break;
                	}
                 	Object to = algorithm.processAlgorithm(i);
                 	PhongMaterial tMaterial = algorithm.getMaterial(i);
                 	if (tMaterial != null) {
                 		drawMaterial = tMaterial;
                 	}
	            	Platform.runLater(new Runnable() {
	            		@Override public void run() {
	              			int id = algorithm.getId();

	              			int view = viewNumber;
	              			int vDroneCount = viewDroneCount.get(viewNumber);
	              			if (algorithm.isDrone() && vDroneCount > 1) {
	              				algorithm.setInfo(extractDroneState((id+1)%2));
	              				view = viewNumber + id + 1;
	              			}

	              			if (algorithm.getClear()) {
		               			Group aGroup = dataGroup.get(view);
	              				aGroup.getChildren().clear();
	              			}

	                    	Object[] ta;
	                    	Object t;

	                      	if (!( to instanceof Object[]) ) {
	                      		ta = new Object[1];
	                      		ta[0] = to;
	                      	}
	                      	else {
	                      		ta = (Object[])to;
	                      	}

	                      	for (int j = 0; j < ta.length; j++) {
	                      		t = ta[j];
	                        	if      (t instanceof Arrow3D)     { drawArrow3D((Arrow3D) t); }
	                            else if (t instanceof Box3D)       		{ drawBox3D((Box3D) t); }
	                            else if (t instanceof Cone3D)      		{ drawCone3D((Cone3D) t); }
	                            else if (t instanceof Cylinder3D)  		{ drawCylinder3D((Cylinder3D) t); }
	                            else if (t instanceof Dodecahedron3D)	{ drawPolyhedron3D((Dodecahedron3D) t); }
	                            else if (t instanceof Drone3D) 			{
	                            	viewDroneState.get(viewNumber).set(id, (Drone3D) t);
	            					drawDrone3D(view, (Drone3D) t);
	                            }
	                            else if (t instanceof Icosahedron3D)	{ drawPolyhedron3D((Icosahedron3D) t); }
	                            else if (t instanceof Line3D)      		{ drawLine3D((Line3D) t); }
	                            else if (t instanceof Line3D[])			{ drawLine3DSet((Line3D[]) t); }
	                            else if (t instanceof Mesh3D)      		{ drawMesh3D((Mesh3D) t); }
	                            else if (t instanceof Octahedron3D)		{ drawPolyhedron3D((Octahedron3D) t); }
	                            else if (t instanceof Oval3D)      		{ drawOval3D((Oval3D) t); }
	                            else if (t instanceof Plane3D)     		{ drawPlane3D((Plane3D) t); }
	                            else if (t instanceof Point3D)     		{ drawPoint3D((Point3D) t); }
	                            else if (t instanceof Point3D[])   		{ drawPoint3DSet((Point3D[]) t); }
	                            else if (t instanceof Polygon3D)   		{ drawPolygon3D((Polygon3D) t); }
	                            else if (t instanceof Rectangle3D) 		{ drawRectangle3D((Rectangle3D) t); }
	                            else if (t instanceof Sphere3D)    		{ drawSphere3D((Sphere3D) t); }
	                            else if (t instanceof Sphere3D[])  		{ drawSphere3DSet((Sphere3D[]) t); }
	                            else if (t instanceof Tetrahedron3D)	{ drawPolyhedron3D((Tetrahedron3D) t); }
	                            else if (t instanceof Text3D)      		{ drawLabel3D((Text3D) t); }
	                            else if (t instanceof Triangle3D)  		{ drawTriangle3D((Triangle3D) t); }
	                            else if (t instanceof Vector3D)    		{ drawVector3D((Vector3D) t); }
	                            else { System.out.println("Unknown shape: " + t.toString()); }
	                      	}
	            		}
	            	});
	            	try {
                         Thread.sleep(delay);
                    } catch (InterruptedException e) {
		                if (isCancelled()) {
		                    updateMessage("Cancelled");
		                    break;
		                }
                    }
                }
                return null;
            }
        };
        return algorithmTask;
    }

    /**
     * Private method that extracts the current DroneState from Drone id supporting
     * setting the currently executing drones info field with the extracted information.
     *
     * @param	id	Integer drone id
     */
    private double[] extractDroneState(int id) {
    	Drone3D d3d = viewDroneState.get(viewNumber).get(id);
    	double[] info = new double[4];
    	info[0] = (double)id;
    	info[1] = d3d.getP().getX();
    	info[2] = d3d.getP().getY();
    	info[3] = d3d.getP().getZ();
    	return info;

    }

	/**
	 * Return next index cycling back to zero after n-1.
	 *
	 * @return	i	Next index value in cyclic list.
	 */
	private int next(int length, int i)
	{
		return i == (length-1) ? 0 : i + 1;
	}


	/**
	 * Performs matrix multiplication of vector with a transform matrix
	 *
	 * @param v	vector array
	 * @param m	transform matrix
	 *
	 * @return transformed vector
	 */
    private double[] matrixMultiply(double[] v, double[][] m) {
    	double[] t = new double[3];
    	t[0] = v[0]*m[0][0] + v[1]*m[1][0] + v[2]*m[2][0];
    	t[1] = v[0]*m[0][1] + v[1]*m[1][1] + v[2]*m[2][1];
    	t[2] = v[0]*m[0][2] + v[1]*m[1][2] + v[2]*m[2][2];
    	return t;
    }

    /**
     * Computes a new Line3D object with start and end coordinates
     * transformed by rotations on each axis about the origin.
     * Intended for rotating edges of objects with center at the origin.
     *
     * @param 	ln		Line to be rotated
     * @param 	rxd		Rotation with respect to x axis
     * @param 	ryd		Rotation with respect to y axis
     * @param 	rzd		Rotation with respect to z axis
     *
     * @return	Rotated line
     */
	public Line3D rotateLine3D(Line3D ln, double rxd, double ryd, double rzd) {
		boolean zUp = axesZupCheckItem.isSelected();

		double[] strRx, endRx, strRy, endRy, strRz, endRz;

		double rx = rxd*Math.PI/180;
    	double[][] mx = {{    1.0,         0.0,        0.0      },
				 		 {    0.0,    Math.cos(rx), Math.sin(rx)},
				 		 {    0.0,   -Math.sin(rx), Math.cos(rx)}};

		double ry = ryd*Math.PI/180;
    	double[][] my = {{ Math.cos(ry),  0.0,     -Math.sin(ry)},
					 	 {    0.0,        1.0,        0.0       },
					 	 { Math.sin(ry),  0.0,      Math.cos(ry)}};

		double rz = rzd*Math.PI/180;
    	double[][] mz = {{ Math.cos(rz), Math.sin(rz), 0.0},
						 {-Math.sin(rz), Math.cos(rz), 0.0},
						 {    0.0,        0.0,         1.0}};

		double[] str = { ln.getp1().getX(),
				 		 ln.getp1().getY(),
				 		 ln.getp1().getZ()};

		double[] end = { ln.getp2().getX(),
						 ln.getp2().getY(),
						 ln.getp2().getZ()};

		Point3D newstr, newend;

		if (zUp) {
//			System.out.println("rotateLine zUp: " + rxd + ", " + ryd + ", " + rzd);
			strRx = matrixMultiply(str, mx);
			endRx = matrixMultiply(end, mx);

			strRy = matrixMultiply(strRx, my);
			endRy = matrixMultiply(endRx, my);

			strRz = matrixMultiply(strRy, mz);
			endRz = matrixMultiply(endRy, mz);

			newstr = new Point3D(strRz[0],
								 strRz[1],
								 strRz[2]);

			newend = new Point3D(endRz[0],
								 endRz[1],
								 endRz[2]);
		}
		else {
			strRx = matrixMultiply(str, mx);
			endRx = matrixMultiply(end, mx);

			strRy = matrixMultiply(strRx, my);
			endRy = matrixMultiply(endRx, my);

			strRz = matrixMultiply(strRy, mz);
			endRz = matrixMultiply(endRy, mz);

			newstr = new Point3D(strRz[0],
								 strRz[1],
								 strRz[2]);

			newend = new Point3D(endRz[0],
								 endRz[1],
								 endRz[2]);
		}

		Line3D out = new Line3D(newstr, newend);

		return out;
	}

    public static void main(String[] args) {
        launch(args);
    }
}