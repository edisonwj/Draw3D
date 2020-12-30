package org.edisonwj.draw3dtest;

import java.util.ArrayList;

import org.edisonwj.draw3d.Arrow3D;
import org.edisonwj.draw3d.Box3D;
import org.edisonwj.draw3d.Draw3D;
import org.edisonwj.draw3d.Line3D;
import org.edisonwj.draw3d.Rand;
import org.edisonwj.draw3d.Sphere3D;
import org.edisonwj.draw3dtest.Pointd;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.stage.Stage;
/**
* Test room defined with a set of walls for coverage by a set of cameras.
* 
* @author William Edison
* @version 1.05 February 2016
* 
*/
public class TestRoomScanComposite extends Application {
	private Draw3D dt;
	private Rand mr = new Rand();
	private PhongMaterial red = new PhongMaterial(Color.RED);
	private PhongMaterial green = new PhongMaterial(Color.GREEN);
	private PhongMaterial blue = new PhongMaterial(Color.BLUE);
	private PhongMaterial black = new PhongMaterial(Color.BLACK);
	private PhongMaterial gray = new PhongMaterial(Color.GRAY);
	private PhongMaterial lightgray = new PhongMaterial(Color.LIGHTGRAY);
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestRoomScanComposite");
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
		int hiboundX = 32;
		int loboundX = 00;
		int hiboundY = 22;
		int loboundY = 00;
		int range = 40;
		boolean detail = true;
					
		ArrayList<Wall> exWalls = new ArrayList<>();
		exWalls.add(new Wall(new Pointd(0,          0), new Pointd(hiboundX,           0)));
		exWalls.add(new Wall(new Pointd(0, hiboundY-1), new Pointd(hiboundX,   hiboundY-1)));
		exWalls.add(new Wall(new Pointd(0,          0), new Pointd(0       ,   hiboundY)));
		exWalls.add(new Wall(new Pointd(hiboundX-1, 0), new Pointd(hiboundX-1, hiboundY)));
		
		ArrayList<Wall> inWalls = new ArrayList<>();
		inWalls.add(new Wall(new Pointd(6,   1), new Pointd( 6, 12)));
		inWalls.add(new Wall(new Pointd(10, 16), new Pointd(10, 21)));
		inWalls.add(new Wall(new Pointd(14, 10), new Pointd(21, 10)));

		// Define Cameras
		ArrayList<RoomCamera> cameraList = new ArrayList<>();
		cameraList.add(new RoomCamera(new Pointd ( 1, 21), 310));
		cameraList.add(new RoomCamera(new Pointd ( 6, 12),  90));
		cameraList.add(new RoomCamera(new Pointd (31,  1), 140));
		cameraList.add(new RoomCamera(new Pointd (11, 21), 310));
		cameraList.add(new RoomCamera(new Pointd (31, 21), 220));
		
		if (cameraList.size() > 32) { 
			System.out.println("Detail mode supports a maximum of 32 cameras");
			return;
		}
		drawWallsGrid(0, hiboundX, 0, hiboundY, exWalls, inWalls);
		
		PhongMaterial[] cameraColor = new PhongMaterial[cameraList.size()];		
		int count = 0;
		for (RoomCamera c : cameraList) {
			cameraColor[count] = new PhongMaterial(setColor());
			drawCamera(range, c, cameraColor[count], exWalls, inWalls, hiboundX, hiboundY);
			count++;
		}
	
		int[][] coverList = RoomCameraGeometry.findCover(  hiboundX, hiboundY, range,
														cameraList,
														exWalls,
														inWalls,
														detail);
		
		int inWallCount = 0;
		int notCoveredCount = 0;
		int coveredCount = 0;
		int[] cameraCount = new int[cameraList.size()];
		
		for (int i = 0; i < hiboundX; i++) {
			for (int j = 0; j < hiboundY; j++) {
				long cover = coverList[i][j];
				if (cover == -1) {
					inWallCount++;
					Point3D cellCenter = new Point3D(i+.5, j+.5, 0);
					dt.drawBox3D(new Box3D(cellCenter, 1, 1, 0, lightgray));				
				}
				else if (cover == 0) {
					notCoveredCount++;
					Point3D cellCenter = new Point3D(i+.5, j+.5, 0);
					dt.drawSphere3D(new Sphere3D(cellCenter, .4, gray));
				}
				else if (cover > 0) {
					coveredCount++;
					if (!detail) {
						coveredCount++;
						Point3D cellCenter = new Point3D(i+.5, j+.5, 0);
						dt.drawSphere3D(new Sphere3D(cellCenter, .2, red));
					}
					else {
						ArrayList<Integer> coveredByCameras = new ArrayList<>();
						double increment = 1.0/cameraList.size();
						double radius = increment/2.0;
						double offset = 0.0;
						for (int k = 0; k < cameraList.size(); k++) {
							if ( ((cover>>>k) & 1) != 0) {
								cameraCount[k]++;
								coveredByCameras.add(k);
								offset = k*increment + radius;
								Point3D cellCenter = new Point3D(i+offset, j+.5, 0);
//								dt.drawSphere3D(new Sphere3D(cellCenter, radius, cameraColor[k]));
								dt.drawBox3D(new Box3D(cellCenter, 2*radius, .8, 0, cameraColor[k]));
							}
						}
						System.out.printf("i= %d j= %d cameras: ", i, j);
						for (int k = 0; k < coveredByCameras.size(); k++) {							
							System.out.printf("%d ",coveredByCameras.get(k));
						}
						System.out.println(" ");
					}
				}
			}
		}
		int check = inWallCount+coveredCount+notCoveredCount;
		int size = hiboundX*hiboundY;
		String hiLight;
		if (check != size)
			hiLight = "\n******";
		else
			hiLight= "\n";
		System.out.println(hiLight +
							"inWallCount= " + inWallCount +
							", coveredCount= " + coveredCount +
							", notCoveredCount= " + notCoveredCount +
							", check= " + check +
							", size= " + size );
		if (detail) {
			int cameraTotal = 0;
			for (int k = 0; k < cameraList.size(); k++) {
				System.out.println("cameraCount[" + k + "]= " + cameraCount[k]);
				cameraTotal += cameraCount[k];
			}
			System.out.println("cameraTotal= " + cameraTotal + ", duplicateCoverage= " + (cameraTotal-coveredCount));
		}
	}
		
	public static void main(String[] args) {
		launch(args);
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
		Point3D wallstart = new Point3D(wall.start.getx(), wall.start.gety(), 0);
		Point3D wallend = new Point3D(wall.end.getx(), wall.end.gety(), 0);
		
		Line3D w0 = new Line3D(wallstart, wallend, black);
		Line3D w1 = new Line3D(wallend, new Point3D(wallend.getX()+1, wallend.getY(),0), black);
		Line3D w2 = new Line3D(new Point3D(wallend.getX()+1, wallend.getY(),0), new Point3D(wallstart.getX()+1, wallstart.getY(),0), black);
		Line3D w3 = new Line3D(new Point3D(wallstart.getX()+1, wallstart.getY(),0), wallstart, black);
		dt.drawLine3D(w0);
		dt.drawLine3D(w1);
		dt.drawLine3D(w2);
		dt.drawLine3D(w3);		
	}

	private void drawCamera(int range,
							RoomCamera camera,
							PhongMaterial cameraColor,
							ArrayList<Wall> exWalls,
							ArrayList<Wall> inWalls,
							double hiboundX,
							double hiboundY) {
//		System.out.println("\ncamera= " + camera);
		
		Point3D cloc = new Point3D(camera.location.getx(), camera.location.gety(),0);
		Sphere3D s3d = new Sphere3D(cloc, .5, cameraColor);
		dt.drawSphere3D(s3d);
		Pointd ep =  RoomCameraGeometry.findBoundaryEnd(camera, camera.orientation, hiboundX, hiboundY);
		Point3D eloc = new Point3D(ep.getx(), ep.gety(),0);
		dt.drawArrow3D(new Arrow3D(cloc, eloc, cameraColor));
		
		int wallType = RoomCameraGeometry.findWallType(camera.location, exWalls, inWalls, hiboundX, hiboundY);
//		System.out.println("wallType= " + wallType);
//		dt.setDrawColor(Color.BLACK);
//		dt.setLabelFontSize(14);
//		dt.drawLabel(-8,30,0,"Wall #"+wallType);
		
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
	
}