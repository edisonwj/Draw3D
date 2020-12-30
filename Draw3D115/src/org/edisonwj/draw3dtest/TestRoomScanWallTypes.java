 package org.edisonwj.draw3dtest;

import java.util.ArrayList;

import org.edisonwj.draw3d.Arrow3D;
import org.edisonwj.draw3d.Draw3D;
import org.edisonwj.draw3d.Line3D;
import org.edisonwj.draw3d.Rand;
import org.edisonwj.draw3d.Sphere3D;
import org.edisonwj.draw3d.Text3D;
import org.edisonwj.draw3dtest.Pointd;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.stage.Stage;
/**
* Test for determining the camera location on various types of walls.
* 
* @author William Edison
* @version 1.05 February 2016
* 
*/
public class TestRoomScanWallTypes extends Application {
	private Draw3D dt;
	private Rand mr = new Rand();
	private PhongMaterial red = new PhongMaterial(Color.RED);
	private PhongMaterial green = new PhongMaterial(Color.GREEN);
	private PhongMaterial blue = new PhongMaterial(Color.BLUE);
	private PhongMaterial black = new PhongMaterial(Color.BLACK);
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestRoomScanWallTypes");
		primaryStage.show();
	}
	
	private void buildData() {
	
		dt.setCumulate(false);
		dt.setYaxisUp(true);
		dt.setShowBoundaryCube(false);
		dt.setShowAxes(false);
		dt.setXYZRange(30,  30,  30);
		dt.setCamera(0.0,  -180.0,  -600.0);
		dt.setOriginView(-100, -100, -100);
				
		// Define Cells and Walls
		int hiboundX = 22;
		int loboundX = 00;
		int hiboundY = 22;
		int loboundY = 00;
		int range = 40;
		boolean detail = true;
					
		ArrayList<Wall> exWalls = new ArrayList<>();
		exWalls.add(new Wall(new Pointd(0,          0), new Pointd(hiboundX,           0)));
		exWalls.add(new Wall(new Pointd(0, hiboundY-1), new Pointd(hiboundX,  hiboundY-1)));
		exWalls.add(new Wall(new Pointd(0,          0), new Pointd(0       ,   hiboundY)));
		exWalls.add(new Wall(new Pointd(hiboundX-1, 0), new Pointd(hiboundX-1, hiboundY)));
		
		ArrayList<Wall> inWalls = new ArrayList<>();
		inWalls.add(new Wall(new Pointd(6,   1), new Pointd( 6, 12)));
		inWalls.add(new Wall(new Pointd(10, 16), new Pointd(10, 21)));
		inWalls.add(new Wall(new Pointd(14, 10), new Pointd(21, 10)));
		inWalls.add(new Wall(new Pointd(1, 16), new Pointd(6, 16)));

		// Define Cameras
		ArrayList<RoomCamera> cameraList = createCameraList();
				
		for (int ci = 0; ci < cameraList.size(); ci++) {
			drawWallsGrid(0, hiboundX, 0, hiboundY, exWalls, inWalls);
			RoomCamera camera = cameraList.get(ci);
			drawCamera(range, camera, exWalls, inWalls, hiboundX, hiboundY);
			dt.incrSequence();
		}
	}
	
	private Color setColor() {
		int rRGB = mr.uniform(0,  255);
		int gRGB = mr.uniform(0,  255);
		int bRGB = mr.uniform(0,  255);
		return Color.rgb(rRGB, gRGB, bRGB);
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
//			System.out.println("exerior start= " + wall.start + ", end= " + wall.end);
			
			if (wall.start.gety() == wall.end.gety()) { 				// if horizontal wall
				drawHorizontalWall(wall);
			}

			else if (wall.start.getx() == wall.end.getx()) {			// if vertical wall
				drawVerticalWall(wall);		
			}
		}
		
		// Draw interior walls		
		for (Wall wall : inWalls) {
//			System.out.println("interior start= " + wall.start + ", end= " + wall.end);
			
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
//		System.out.println("drawVerticalWall= " + wall);
		Point3D wallstart = new Point3D(wall.start.getx(), wall.start.gety(), 0);
		Point3D wallend = new Point3D(wall.end.getx(), wall.end.gety(), 0);
		
		Line3D w0 = new Line3D(wallstart, wallend, black);
//		System.out.println("w0= " + w0);
		Line3D w1 = new Line3D(wallend, new Point3D(wallend.getX()+1, wallend.getY(),0), black);
//		System.out.println("w1= " + w1);
		Line3D w2 = new Line3D(new Point3D(wallend.getX()+1, wallend.getY(),0), new Point3D(wallstart.getX()+1, wallstart.getY(),0), black);
//		System.out.println("w2= " + w2);
		Line3D w3 = new Line3D(new Point3D(wallstart.getX()+1, wallstart.getY(),0), wallstart, black);
//		System.out.println("w3= " + w3);
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
//		System.out.println("\ncamera= " + camera);
		
		Point3D cloc = new Point3D(camera.location.getx(), camera.location.gety(),0);
		Sphere3D s3d = new Sphere3D(cloc, .5, blue);
		dt.drawSphere3D(s3d);
		Pointd ep =  RoomCameraGeometry.findBoundaryEnd(camera, camera.orientation, hiboundX, hiboundY);
		Point3D eloc = new Point3D(ep.getx(), ep.gety(),0);
		dt.drawArrow3D(new Arrow3D(cloc, eloc, blue));
		
		int wallType = RoomCameraGeometry.findWallType(camera.location, exWalls, inWalls, hiboundX, hiboundY);
		System.out.println("wallType= " + wallType);
		dt.setDrawColor(Color.BLACK);
		dt.setLabelFontSize(14);
		dt.drawLabel(-10, 36, 0, "Wall #"+wallType);
		
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
	
	private RoomCamera createCamera(ArrayList<Wall> exWalls, ArrayList<Wall> inWalls) {
		Pointd location;
		int orient = 0;
		double xCoor = 0.0;
		double yCoor = 0.0;
		
		if (mr.uniform() < .5) {
			int wallNum = mr.uniform(0,  exWalls.size()-1);
			Pointd start = exWalls.get(wallNum).start;
			Pointd end   = exWalls.get(wallNum).end;
			
			switch (wallNum) {
			case 0:
//				System.out.println("exWall 0");
				xCoor = Math.rint(mr.uniform(start.getx()+1, end.getx()-1));
				yCoor = start.gety()+1;
				orient = mr.uniform(0, 180);
				break;
			case 1:
//				System.out.println("exWall 1");
				xCoor =  Math.rint(mr.uniform(start.getx()+1, end.getx()-1));
				yCoor = start.gety();
				orient = mr.uniform(180, 360);
				break;
			case 2:
//				System.out.println("exWall 2");
				xCoor = start.getx()+1;
				yCoor =  Math.rint(mr.uniform(start.gety()+1, end.gety()));
				if (mr.uniform() < .5)
					orient = mr.uniform(0, 90);
				else
					orient = mr.uniform(270, 360);
				break;
			case 3:
//				System.out.println("exWall 3");
				xCoor = start.getx();
				yCoor =  Math.rint(mr.uniform(start.gety()+1, end.gety()));
				orient = mr.uniform(90, 270);
				break;
			}
		}
		else {
			int wallNum = mr.uniform(0,  inWalls.size()-1);
			Pointd start = inWalls.get(wallNum).start;
			Pointd end   = inWalls.get(wallNum).end;
//			System.out.println("inWall " + wallNum + " : " + start + ", " + end);
			
			if (start.getx() == end.getx()) {
				if (mr.uniform() < .5) {
					xCoor = start.getx();
					orient = mr.uniform(90, 270);
				}
				else {
					xCoor = start.getx()+1;
					if (mr.uniform() < .5)
						orient = mr.uniform(0, 90);
					else
						orient = mr.uniform(270, 360);
				}
				if (start.gety() < end.gety())
					yCoor =  Math.rint(mr.uniform(start.gety(), end.gety()));
				else
					yCoor =  Math.rint(mr.uniform(end.gety(), start.gety()));
			}
			else if (start.gety() == end.gety()) {
				if (mr.uniform() < .5) {
					yCoor = start.gety();
					orient = mr.uniform(180,360);
				}
				else {
					yCoor = start.gety()+1;
					orient = mr.uniform(0, 180);
				}
				if (start.getx() < end.getx())
					xCoor =  Math.rint(mr.uniform(start.getx(), end.getx()));
				else
					xCoor =  Math.rint(mr.uniform(end.getx(), start.getx()));
			}
		}
		location = new Pointd(xCoor, yCoor);
		return new RoomCamera( location,
								orient);
	}
	
	private ArrayList<RoomCamera> createCameraList() {
		// Define Cameras
		ArrayList<RoomCamera> cameraList = new ArrayList<>();
		
		cameraList.add(new RoomCamera(new Pointd (1,  21), 310));
		
		cameraList.add(new RoomCamera(new Pointd (12,  1), 90));		// Walltype 0
		cameraList.add(new RoomCamera(new Pointd (12,  1), 150));
		cameraList.add(new RoomCamera(new Pointd (12,  1), 30));
		cameraList.add(new RoomCamera(new Pointd (12,  1), 270));
		cameraList.add(new RoomCamera(new Pointd (12,  1), 0));
		cameraList.add(new RoomCamera(new Pointd (12,  1), 360));
		cameraList.add(new RoomCamera(new Pointd (12,  1), 180));
		cameraList.add(new RoomCamera(new Pointd (12,  1), -60));
		cameraList.add(new RoomCamera(new Pointd (12,  1), 300));
		
		cameraList.add(new RoomCamera(new Pointd (12, 21),  270));		// Walltype 1
		cameraList.add(new RoomCamera(new Pointd (12, 21),  330));
		cameraList.add(new RoomCamera(new Pointd (12, 21),  210));
		cameraList.add(new RoomCamera(new Pointd (12, 21),  90));
		cameraList.add(new RoomCamera(new Pointd (12, 21),  0));
		cameraList.add(new RoomCamera(new Pointd (12, 21),  360));
		cameraList.add(new RoomCamera(new Pointd (12, 21),  180));
		cameraList.add(new RoomCamera(new Pointd (12, 21),  60));
		
		cameraList.add(new RoomCamera(new Pointd ( 1, 14),  0));		// Walltype 2
		cameraList.add(new RoomCamera(new Pointd ( 1, 14), 60));
		cameraList.add(new RoomCamera(new Pointd ( 1, 14), 300));
		cameraList.add(new RoomCamera(new Pointd ( 1, 14), -60));
		cameraList.add(new RoomCamera(new Pointd ( 1, 14), 180));
		cameraList.add(new RoomCamera(new Pointd ( 1, 14), 90));
		cameraList.add(new RoomCamera(new Pointd ( 1, 14), 270));
		cameraList.add(new RoomCamera(new Pointd ( 1, 14), 200));
		
		cameraList.add(new RoomCamera(new Pointd (21,  8), 180));		// Walltype 3
		cameraList.add(new RoomCamera(new Pointd (21,  8), 240));
		cameraList.add(new RoomCamera(new Pointd (21,  8), 120));
		cameraList.add(new RoomCamera(new Pointd (21,  8), 0));
		cameraList.add(new RoomCamera(new Pointd (21,  8), 90));
		cameraList.add(new RoomCamera(new Pointd (21,  8), 270));
		cameraList.add(new RoomCamera(new Pointd (21,  8), -60));
		cameraList.add(new RoomCamera(new Pointd (21,  8), 300));
		
		cameraList.add(new RoomCamera(new Pointd (18, 11), 90));		// Walltype 4
		cameraList.add(new RoomCamera(new Pointd (18, 11), 150));
		cameraList.add(new RoomCamera(new Pointd (18, 11), 30));
		cameraList.add(new RoomCamera(new Pointd (18, 11), 270));
		cameraList.add(new RoomCamera(new Pointd (18, 11), 0));
		cameraList.add(new RoomCamera(new Pointd (18, 11), 360));
		cameraList.add(new RoomCamera(new Pointd (18, 11), 180));
		cameraList.add(new RoomCamera(new Pointd (18, 11), -60));
		cameraList.add(new RoomCamera(new Pointd (18, 11), 300));
		
		
		cameraList.add(new RoomCamera(new Pointd (18, 10), 270));		// Walltype 5
		cameraList.add(new RoomCamera(new Pointd (18, 10), 330));
		cameraList.add(new RoomCamera(new Pointd (18, 10), 210));
		cameraList.add(new RoomCamera(new Pointd (18, 10), 90));
		cameraList.add(new RoomCamera(new Pointd (18, 10), 0));
		cameraList.add(new RoomCamera(new Pointd (18, 10), 360));
		cameraList.add(new RoomCamera(new Pointd (18, 10), 180));
		cameraList.add(new RoomCamera(new Pointd (18, 10), 60));
		
		cameraList.add(new RoomCamera(new Pointd ( 7,  6), 0));			// Walltype 6
		cameraList.add(new RoomCamera(new Pointd ( 7,  6), 60));
		cameraList.add(new RoomCamera(new Pointd ( 7,  6), 300));
		cameraList.add(new RoomCamera(new Pointd ( 7,  6), -60));
		cameraList.add(new RoomCamera(new Pointd ( 7,  6), 180));
		cameraList.add(new RoomCamera(new Pointd ( 7,  6), 90));
		cameraList.add(new RoomCamera(new Pointd ( 7,  6), 270));
		cameraList.add(new RoomCamera(new Pointd ( 7,  6), 200));
		
		cameraList.add(new RoomCamera(new Pointd ( 6,  6), 180));		// Walltype 7
		cameraList.add(new RoomCamera(new Pointd ( 6,  6), 240));
		cameraList.add(new RoomCamera(new Pointd ( 6,  6), 120));
		cameraList.add(new RoomCamera(new Pointd ( 6,  6), 0));
		cameraList.add(new RoomCamera(new Pointd ( 6,  6), 90));
		cameraList.add(new RoomCamera(new Pointd ( 6,  6), 270));
		cameraList.add(new RoomCamera(new Pointd ( 6,  6), -60));
		cameraList.add(new RoomCamera(new Pointd ( 6,  6), 300));
		
		cameraList.add(new RoomCamera(new Pointd ( 14, 10), 180));		// Walltype 8
		cameraList.add(new RoomCamera(new Pointd ( 14, 10), 240));
		cameraList.add(new RoomCamera(new Pointd ( 14, 10), 120));
		cameraList.add(new RoomCamera(new Pointd ( 14, 10), 0));
		cameraList.add(new RoomCamera(new Pointd ( 14, 10), 90));
		cameraList.add(new RoomCamera(new Pointd ( 14, 10), 270));
		cameraList.add(new RoomCamera(new Pointd ( 14, 10), -60));
		cameraList.add(new RoomCamera(new Pointd ( 14, 10), 300));
		
		cameraList.add(new RoomCamera(new Pointd ( 14, 11), 180));
		cameraList.add(new RoomCamera(new Pointd ( 14, 11), 180));
		cameraList.add(new RoomCamera(new Pointd ( 14, 11), 240));
		cameraList.add(new RoomCamera(new Pointd ( 14, 11), 120));
		cameraList.add(new RoomCamera(new Pointd ( 14, 11), 0));
		cameraList.add(new RoomCamera(new Pointd ( 14, 11), 90));
		cameraList.add(new RoomCamera(new Pointd ( 14, 11), 270));
		cameraList.add(new RoomCamera(new Pointd ( 14, 11), -60));
		cameraList.add(new RoomCamera(new Pointd ( 14, 11), 300));
								
		cameraList.add(new RoomCamera(new Pointd ( 6, 16), 0));			// Walltype 9
		cameraList.add(new RoomCamera(new Pointd ( 6, 16), 60));
		cameraList.add(new RoomCamera(new Pointd ( 6, 16), 300));
		cameraList.add(new RoomCamera(new Pointd ( 6, 16), -60));
		cameraList.add(new RoomCamera(new Pointd ( 6, 16), 180));
		cameraList.add(new RoomCamera(new Pointd ( 6, 16), 90));
		cameraList.add(new RoomCamera(new Pointd ( 6, 16), 270));
		cameraList.add(new RoomCamera(new Pointd ( 6, 16), 200));
									
		cameraList.add(new RoomCamera(new Pointd ( 6, 17), 0));
		cameraList.add(new RoomCamera(new Pointd ( 6, 17), 60));
		cameraList.add(new RoomCamera(new Pointd ( 6, 17), 300));
		cameraList.add(new RoomCamera(new Pointd ( 6, 17), -60));
		cameraList.add(new RoomCamera(new Pointd ( 6, 17), 180));
		cameraList.add(new RoomCamera(new Pointd ( 6, 17), 90));
		cameraList.add(new RoomCamera(new Pointd ( 6, 17), 270));
		cameraList.add(new RoomCamera(new Pointd ( 6, 17), 200));
			
		cameraList.add(new RoomCamera(new Pointd ( 10, 16), 270));		// Walltype 10
		cameraList.add(new RoomCamera(new Pointd ( 10, 16), 330));
		cameraList.add(new RoomCamera(new Pointd ( 10, 16), 210));
		cameraList.add(new RoomCamera(new Pointd ( 10, 16), 90));
		cameraList.add(new RoomCamera(new Pointd ( 10, 16), 0));
		cameraList.add(new RoomCamera(new Pointd ( 10, 16), 360));
		cameraList.add(new RoomCamera(new Pointd ( 10, 16), 180));
		cameraList.add(new RoomCamera(new Pointd ( 10, 16), 60));
		
		cameraList.add(new RoomCamera(new Pointd ( 11, 16), 270));
		cameraList.add(new RoomCamera(new Pointd ( 11, 16), 330));
		cameraList.add(new RoomCamera(new Pointd ( 11, 16), 210));
		cameraList.add(new RoomCamera(new Pointd ( 11, 16), 90));
		cameraList.add(new RoomCamera(new Pointd ( 11, 16), 0));
		cameraList.add(new RoomCamera(new Pointd ( 11, 16), 360));
		cameraList.add(new RoomCamera(new Pointd ( 11, 16), 180));
		cameraList.add(new RoomCamera(new Pointd ( 11, 16), 60));
		
		cameraList.add(new RoomCamera(new Pointd ( 6, 12), 90));		// Walltype 11
		cameraList.add(new RoomCamera(new Pointd ( 6, 12), 150));
		cameraList.add(new RoomCamera(new Pointd ( 6, 12), 30));
		cameraList.add(new RoomCamera(new Pointd ( 6, 12), 270));
		cameraList.add(new RoomCamera(new Pointd ( 6, 12), 0));
		cameraList.add(new RoomCamera(new Pointd ( 6, 12), 360));
		cameraList.add(new RoomCamera(new Pointd ( 6, 12), 180));
		cameraList.add(new RoomCamera(new Pointd ( 6, 12), -60));
		cameraList.add(new RoomCamera(new Pointd ( 6, 12), 300));
		
		cameraList.add(new RoomCamera(new Pointd ( 7, 12), 90));
		cameraList.add(new RoomCamera(new Pointd ( 7, 12), 150));
		cameraList.add(new RoomCamera(new Pointd ( 7, 12), 30));
		cameraList.add(new RoomCamera(new Pointd ( 7, 12), 270));
		cameraList.add(new RoomCamera(new Pointd ( 7, 12), 0));
		cameraList.add(new RoomCamera(new Pointd ( 7, 12), 360));
		cameraList.add(new RoomCamera(new Pointd ( 7, 12), 180));
		cameraList.add(new RoomCamera(new Pointd ( 7, 12), -60));
		cameraList.add(new RoomCamera(new Pointd ( 7, 12), 300));
				
		return cameraList;
	}
		
	public static void main(String[] args) {
		launch(args);
	}
	
}