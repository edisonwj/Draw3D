package org.edisonwj.draw3dtest;

import java.util.ArrayList;

import org.edisonwj.draw3d.Arrow3D;
import org.edisonwj.draw3d.Box3D;
import org.edisonwj.draw3d.Draw3D;
import org.edisonwj.draw3d.Line3D;
import org.edisonwj.draw3d.Sphere3D;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.stage.Stage;
/**
* StudentBase class provides a template for students/teachers to use in creating
* programs to display three dimensional data. The user creates code in the buildData()
* method using the methods available in Draw3D for drawing lines, vectors, etc.
* 
* @author William Edison
* @version 1.05 September 2015
* 
*/
public class TestRoomScanWallTypesSample extends Application {
	private Draw3D dt;
	private PhongMaterial red = new PhongMaterial(Color.RED);
	private PhongMaterial green = new PhongMaterial(Color.GREEN);
	private PhongMaterial blue = new PhongMaterial(Color.BLUE);
	private PhongMaterial black = new PhongMaterial(Color.BLACK);
	private PhongMaterial lightgray = new PhongMaterial(Color.LIGHTGRAY);
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestRoomScanWallTypesSample");
		primaryStage.show();
	}
	
	private void buildData() {
	
		dt.setCumulate(false);
		dt.setYaxisUp(true);
		dt.setShowBoundaryCube(false);
		dt.setShowAxes(false);
		dt.setXYZRange(30,  30,  30);
		dt.setCamera(0.0,  -180.0,  -400.0);
		dt.setOriginView(-60, -50, 0);
		
		// Define Cells and Walls
		int hiboundX = 15;
		int loboundX = 0;
		int hiboundY = 10;
		int loboundY = 0;
		int range = 45;
		boolean detail = false;
					
		ArrayList<Wall> exWalls = new ArrayList<>();
		exWalls.add(new Wall(new Pointd(0,          0), new Pointd(hiboundX,           0)));
		exWalls.add(new Wall(new Pointd(0, hiboundY-1), new Pointd(hiboundX,  hiboundY-1)));
		exWalls.add(new Wall(new Pointd(0,          0), new Pointd(0       ,   hiboundY)));
		exWalls.add(new Wall(new Pointd(hiboundX-1, 0), new Pointd(hiboundX-1, hiboundY)));
		
		ArrayList<Wall> inWalls = new ArrayList<>();
    	inWalls.add(new Wall(new Pointd( 8,  1), new Pointd( 8, 7)));
    	inWalls.add(new Wall(new Pointd( 11,  5), new Pointd( 14, 5)));
    	inWalls.add(new Wall(new Pointd( 1,  4), new Pointd( 4, 4)));
    	inWalls.add(new Wall(new Pointd( 5,  6), new Pointd( 5, 9)));
				
		ArrayList<RoomCamera> cameraList = new ArrayList<>();
		// Exterior wall cameras
		cameraList.add(new RoomCamera( new Pointd( 2, 1), 10));
		cameraList.add(new RoomCamera( new Pointd( 2, 9), 190));
		cameraList.add(new RoomCamera( new Pointd( 2, 9), 270));
		cameraList.add(new RoomCamera( new Pointd( 1, 7), 330));
		cameraList.add(new RoomCamera( new Pointd( 1, 7), 280));
		cameraList.add(new RoomCamera( new Pointd( 14, 4), 260));
		// Horizontal left lower corner 8
		cameraList.add(new RoomCamera( new Pointd( 11, 5), 350));
		cameraList.add(new RoomCamera( new Pointd( 11, 5), 100));
		cameraList.add(new RoomCamera( new Pointd( 11, 5), 180));
		// Horizontal left upper corner 9
		cameraList.add(new RoomCamera( new Pointd( 11, 6), 260));
		cameraList.add(new RoomCamera( new Pointd( 11, 6), 10));
		cameraList.add(new RoomCamera( new Pointd( 11, 6), 180));
		// Horizontal right lower corner 10
		cameraList.add(new RoomCamera( new Pointd( 4, 4), 190));
		cameraList.add(new RoomCamera( new Pointd( 4, 4), 80));
		cameraList.add(new RoomCamera( new Pointd( 4, 4), 0));
		// Horizontal right upper corner 11
		cameraList.add(new RoomCamera( new Pointd( 4, 5), 170));
		cameraList.add(new RoomCamera( new Pointd( 4, 5), 280));
		cameraList.add(new RoomCamera( new Pointd( 4, 5), 0));
		// Vertical bottom left corner 12
		cameraList.add(new RoomCamera( new Pointd( 5, 6), 350));
		cameraList.add(new RoomCamera( new Pointd( 5, 6), 100));
		cameraList.add(new RoomCamera( new Pointd( 5, 6), 270));
		// Vertical bottom right corner 13
		cameraList.add(new RoomCamera( new Pointd( 6, 6), 80));
		cameraList.add(new RoomCamera( new Pointd( 6, 6), 190));
		cameraList.add(new RoomCamera( new Pointd( 6, 6), 270));
		// Vertical top left corner 14
		cameraList.add(new RoomCamera( new Pointd( 8, 7), 260));
		cameraList.add(new RoomCamera( new Pointd( 8, 7), 10));
		cameraList.add(new RoomCamera( new Pointd( 8, 7), 90));
		cameraList.add(new RoomCamera( new Pointd( 8, 7), 180));
		// Vertical top right corner 15
		cameraList.add(new RoomCamera( new Pointd( 9, 7), 170));
		cameraList.add(new RoomCamera( new Pointd( 9, 7), 280));
		cameraList.add(new RoomCamera( new Pointd( 9, 7), 90));

		ArrayList<RoomCamera> cameraShortList = new ArrayList<>();
		cameraShortList.add(cameraList.get(0));
		
		for (RoomCamera c : cameraList) {
			drawWallsGrid(0, hiboundX, 0, hiboundY, exWalls, inWalls);
			drawCamera(range, c, exWalls, inWalls, hiboundX, hiboundY);
			cameraShortList.set(0,c);
			int[][] coverList = RoomCameraGeometry.findCover(hiboundX, hiboundY, range,
												 cameraShortList,
												 exWalls,
												 inWalls,
												 detail);
			
			for (int i = 0; i < hiboundX; i++) {
				for (int j = 0; j < hiboundY; j++) {
					int cover = coverList[i][j];
					if (cover == -1) {
						Point3D cellCenter = new Point3D(i+.5, j+.5, 0);
						dt.drawBox3D(new Box3D(cellCenter, 1, 1, .1, lightgray));				
					}
					else if (cover == 1) {
						Point3D cellCenter = new Point3D(i+.5, j+.5, 0);
						dt.drawSphere3D(new Sphere3D(cellCenter, .2, green));
					}
				}
			}
			dt.incrSequence();
		}		
	}
	
	private void drawWallsGrid(int loboundX, int hiboundX, int loboundY, int hiboundY,			
			   ArrayList<Wall> exWalls, ArrayList<Wall> inWalls) {

		// Define and Draw Grid
		dt.setLineRadius(.2);
		dt.setDrawColor(Color.GRAY);
		int cellsize = 1;
		
		// Draw grid
		for (int i=loboundX+cellsize; i<hiboundX; i=i+cellsize) {
		dt.drawLine(i, loboundY, 0, i, hiboundY, 0);
		}
		for (int i=loboundY; i < hiboundY; i=i+cellsize) {
		dt.drawLine(loboundX, i, 0, hiboundX, i, 0);
		}
		
		// Draw exterior walls
		for (Wall wall : exWalls) {
		//System.out.println("exerior start= " + wall.start + ", end= " + wall.end);
		
		if (wall.start.gety() == wall.end.gety()) { 				// if horizontal wall
		drawHorizontalWall(wall);
		}
		
		else if (wall.start.getx() == wall.end.getx()) {			// if vertical wall
		drawVerticalWall(wall);		
		}
		}
		
		// Draw interior walls		
		for (Wall wall : inWalls) {
		//System.out.println("interior start= " + wall.start + ", end= " + wall.end);
		
		if (wall.start.gety() == wall.end.gety()) { 				// if horizontal wall
		drawHorizontalWall(wall);
		}
		
		else if (wall.start.getx() == wall.end.getx()) {			// if vertical wall
		drawVerticalWall(wall);		
		}
		}
		}
		
		private void drawHorizontalWall(Wall wall) {
		Point3D wallstart = new Point3D(wall.start.getx(), wall.start.gety(), 0);
		Point3D wallend = new Point3D(wall.end.getx(), wall.end.gety(), 0);
		
		Line3D w0 = new Line3D(wallstart, wallend, black);
		Line3D w1 = new Line3D(wallend, new Point3D(wallend.getX(), wallend.getY()+1,0), black);
		Line3D w2 = new Line3D(new Point3D(wallend.getX(), wallend.getY()+1,0), new Point3D(wallstart.getX(), wallstart.getY()+1,0), black);
		Line3D w3 = new Line3D(new Point3D(wallstart.getX(), wallstart.getY()+1,0), wallstart, black);
		dt.drawLine3D(w0);
		dt.drawLine3D(w1);
		dt.drawLine3D(w2);
		dt.drawLine3D(w3);	
		}
		
		private void drawVerticalWall(Wall wall) {
		//System.out.println("drawVerticalWall= " + wall);
		Point3D wallstart = new Point3D(wall.start.getx(), wall.start.gety(), 0);
		Point3D wallend = new Point3D(wall.end.getx(), wall.end.gety(), 0);
		
		Line3D w0 = new Line3D(wallstart, wallend, black);
		//System.out.println("w0= " + w0);
		Line3D w1 = new Line3D(wallend, new Point3D(wallend.getX()+1, wallend.getY(),0), black);
		//System.out.println("w1= " + w1);
		Line3D w2 = new Line3D(new Point3D(wallend.getX()+1, wallend.getY(),0), new Point3D(wallstart.getX()+1, wallstart.getY(),0), black);
		//System.out.println("w2= " + w2);
		Line3D w3 = new Line3D(new Point3D(wallstart.getX()+1, wallstart.getY(),0), wallstart, black);
		//System.out.println("w3= " + w3);
		dt.drawLine3D(w0);
		dt.drawLine3D(w1);
		dt.drawLine3D(w2);
		dt.drawLine3D(w3);		
		}
	
	private void drawCamera(int range,
			RoomCamera camera,
			ArrayList<Wall> exWalls,
			ArrayList<Wall> inWalls,
			double hiboundX,
			double hiboundY) {

		Point3D cloc = new Point3D(camera.location.getx(), camera.location.gety(),0);
		Sphere3D s3d = new Sphere3D(cloc, .5, blue);
		dt.drawSphere3D(s3d);
		Pointd ep =  RoomCameraGeometry.findBoundaryEnd(camera, camera.orientation, hiboundX, hiboundY);
		Point3D eloc = new Point3D(ep.getx(), ep.gety(),0);
		dt.drawArrow3D(new Arrow3D(cloc, eloc, blue));
		
		int wallType = RoomCameraGeometry.findWallType(camera.location, exWalls, inWalls, hiboundX, hiboundY);
		dt.setDrawColor(Color.BLACK);
		dt.setLabelFontSize(10);
		dt.drawLabel(-4,15,0,"wallType= "+wallType);
		System.out.println("wallType = " + wallType);
		
		int b1 = RoomCameraGeometry.findBoundaryAngle(range, camera, wallType, true);
		Pointd b1cp = RoomCameraGeometry.findBoundaryEnd(camera, b1, hiboundX, hiboundY);
		Point3D b1cp3 = new Point3D(b1cp.getx(), b1cp.gety(), 0);
		Line3D b1c = new Line3D(cloc, b1cp3, green);
		b1c.setLineRadius(.5);
		dt.drawLine3D(b1c);
		
		int b2 = RoomCameraGeometry.findBoundaryAngle(range, camera, wallType, false);
		Pointd b2cp = RoomCameraGeometry.findBoundaryEnd(camera, b2, hiboundX, hiboundY);
		Point3D b2cp3 = new Point3D(b2cp.getx(), b2cp.gety(), 0);
		Line3D b2c = new Line3D(cloc, b2cp3, red);
		b2c.setLineRadius(.5);
		dt.drawLine3D(b2c);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}