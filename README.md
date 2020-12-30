# Draw3D
JavaFX application for displaying and manipulating three dimensional data.
Draw3D can be invoked directly to load and display data from an appropriatley
formatted text file, or its methods can be used by JavaFX programs to create 
a sequence of displays. Some may include animations.

Execute ASampler.java to see a summary of capabilities.
A number of test and other programs are provided to also illustrate Draw3D use.

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
