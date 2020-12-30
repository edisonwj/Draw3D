package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Draw3D;
import org.edisonwj.draw3d.Line3D;
import org.edisonwj.draw3d.Tetrahedron3D;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
/**
*
* @author William Edison
* @version 1.08 January 2017
*
*/
public class TestLine extends Application {
	private Draw3D dt;

	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestLine");
		primaryStage.show();
	}

	private void buildData() {
		dt.setAmbientLight(true);
		dt.setPointLight(false);
		dt.setXYZRange(8, 8, 8);
		dt.setZaxisUp(true);
		dt.setLineRadius(2);

		// xz plane
		dt.setSequencingOn();

		Line3D lnx0 = new Line3D(0,0,0,6,0,6,new PhongMaterial(Color.RED));
		dt.drawLine3D(lnx0);

		Line3D lnx1 = new Line3D(0,0,0,-6,0,6,new PhongMaterial(Color.BLUE));
		dt.drawLine3D(lnx1);

		Line3D lnx2 = new Line3D(0,0,0,6,0,-6,new PhongMaterial(Color.GREEN));
		dt.drawLine3D(lnx2);

		Line3D lnx3 = new Line3D(0,0,0,-6,0,-6,new PhongMaterial(Color.YELLOW));
		dt.drawLine3D(lnx3);

		// yz plane
		dt.incrSequence();

		Line3D lny0 = new Line3D(0,0,0,0,6,6,new PhongMaterial(Color.RED));
		dt.drawLine3D(lny0);

		Line3D lny1 = new Line3D(0,0,0,0,-6,6,new PhongMaterial(Color.BLUE));
		dt.drawLine3D(lny1);

		Line3D lny2 = new Line3D(0,0,0,0,6,-6,new PhongMaterial(Color.GREEN));
		dt.drawLine3D(lny2);

		Line3D lny3 = new Line3D(0,0,0,0,-6,-6,new PhongMaterial(Color.YELLOW));
		dt.drawLine3D(lny3);

		// xy plane
		dt.incrSequence();

		Line3D lnz0 = new Line3D(0,0,0,6,6,0,new PhongMaterial(Color.RED));
		dt.drawLine3D(lnz0);

		Line3D lnz1 = new Line3D(0,0,0,6,-6,0,new PhongMaterial(Color.BLUE));
		dt.drawLine3D(lnz1);

		Line3D lnz2 = new Line3D(0,0,0,-6,6,0,new PhongMaterial(Color.GREEN));
		dt.drawLine3D(lnz2);

		Line3D lnz3 = new Line3D(0,0,0,-6,-6,0,new PhongMaterial(Color.YELLOW));
		dt.drawLine3D(lnz3);

		// Lines out of axial plane
		dt.incrSequence();
		dt.drawGrid(0, 8, new PhongMaterial(Color.LIGHTGRAY));

		Line3D ln10 = new Line3D(2,2,2,4,6,8,new PhongMaterial(Color.RED));
		dt.drawLine3D(ln10);

		Line3D ln11 = new Line3D(2,2,-2,4,6,-8,new PhongMaterial(Color.BLUE));
		dt.drawLine3D(ln11);

		Line3D ln12 = new Line3D(-2,2,-2,-4,6,-8,new PhongMaterial(Color.GREEN));
		dt.drawLine3D(ln12);

		Line3D ln13 = new Line3D(-2,2,2,-4,6,8,new PhongMaterial(Color.YELLOW));
		dt.drawLine3D(ln13);

		Line3D ln14 = new Line3D(2,-2,2,4,-6,8,new PhongMaterial(Color.RED));
		dt.drawLine3D(ln14);

		Line3D ln15 = new Line3D(2,-2,-2,4,-6,-8,new PhongMaterial(Color.BLUE));
		dt.drawLine3D(ln15);

		Line3D ln16 = new Line3D(-2,-2,-2,-4,-6,-8,new PhongMaterial(Color.GREEN));
		dt.drawLine3D(ln16);

		Line3D ln17 = new Line3D(-2,-2,2,-4,-6,8,new PhongMaterial(Color.YELLOW));
		dt.drawLine3D(ln17);

		// Check rotations

		Line3D ln20 = new Line3D(4,4,2, 4,4,6);
		Line3D ln21 = dt.rotateLine3D(ln20, 45,  0,  0);
		Line3D ln22 = dt.rotateLine3D(ln20,  0, 45,  0);
		Line3D ln23 = dt.rotateLine3D(ln20,  0,  0, 45);
		Line3D ln24 = dt.rotateLine3D(ln20, 45, 45,  0);
		Line3D ln25 = dt.rotateLine3D(ln20, 45,  0, 45);
		Line3D ln26 = dt.rotateLine3D(ln20,  0, 45, 45);
		Line3D ln27 = dt.rotateLine3D(ln20, 45, 45, 45);

		ln20.setLineRadius(1);
		ln21.setLineRadius(1);
		ln22.setLineRadius(1);
		ln23.setLineRadius(1);
		ln24.setLineRadius(1);
		ln25.setLineRadius(1);
		ln26.setLineRadius(1);
		ln27.setLineRadius(1);

		ln20.setMaterial(new PhongMaterial(Color.RED));
		ln21.setMaterial(new PhongMaterial(Color.BLUE));
		ln22.setMaterial(new PhongMaterial(Color.GREEN));
		ln23.setMaterial(new PhongMaterial(Color.YELLOW));
		ln24.setMaterial(new PhongMaterial(Color.MAGENTA));
		ln25.setMaterial(new PhongMaterial(Color.CYAN));
		ln26.setMaterial(new PhongMaterial(Color.GOLDENROD));
		ln27.setMaterial(new PhongMaterial(Color.BLACK));

		dt.incrSequence();
		dt.drawGrid(0, 8, new PhongMaterial(Color.LIGHTGRAY));
		dt.drawLine3D(ln20);
		dt.drawLine3D(ln21);
		dt.drawLabel(0, 8, 10, "x rotation");

		dt.incrSequence();
		dt.drawGrid(0, 8, new PhongMaterial(Color.LIGHTGRAY));
		dt.drawLine3D(ln20);
		dt.drawLine3D(ln22);
		dt.drawLabel(0, 8, 10, "y rotation");

		dt.incrSequence();
		dt.drawGrid(0, 8, new PhongMaterial(Color.LIGHTGRAY));
		dt.drawLine3D(ln20);
		dt.drawLine3D(ln23);
		dt.drawLabel(0, 8, 10, "z rotation");

		dt.incrSequence();
		dt.drawGrid(0, 8, new PhongMaterial(Color.LIGHTGRAY));
		dt.drawLine3D(ln20);
		dt.drawLine3D(ln24);
		dt.drawLabel(0, 8, 10, "xy rotation");

		dt.incrSequence();
		dt.drawGrid(0, 8, new PhongMaterial(Color.LIGHTGRAY));
		dt.drawLine3D(ln20);
		dt.drawLine3D(ln25);
		dt.drawLabel(0, 8, 10, "xz rotation");

		dt.incrSequence();
		dt.drawGrid(0, 8, new PhongMaterial(Color.LIGHTGRAY));
		dt.drawLine3D(ln20);
		dt.drawLine3D(ln26);
		dt.drawLabel(0, 8, 10, "yz rotation");

		dt.incrSequence();
		dt.drawGrid(0, 8, new PhongMaterial(Color.LIGHTGRAY));
		dt.drawLine3D(ln20);
		dt.drawLine3D(ln27);
		dt.drawLabel(0, 8, 10, "xyz rotation");

/*
		Line3D ln3 = new Line3D(new Point3D(2,-2,-2), new Point3D(-2,-2,2), new PhongMaterial(Color.CRIMSON));
		Line3D ln4 = dt.rotateLine3D(ln3, 0,20, 0);
		ln3.setLineRadius(2);
		ln4.setLineRadius(2);
		dt.setDrawColor(Color.CRIMSON);
		dt.drawLine3D(ln3);
		dt.setDrawColor(Color.BLUE);
		dt.drawLine3D(ln4);

		dt.incrSequence();
		Line3D ln5 = new Line3D(new Point3D(-2,-2,2), new Point3D(2,2,2), new PhongMaterial(Color.CRIMSON));
		Line3D ln6 = dt.rotateLine3D(ln5, 0,0, 20);
		ln5.setLineRadius(2);
		ln6.setLineRadius(2);
		dt.setDrawColor(Color.CRIMSON);
		dt.drawLine3D(ln5);
		dt.setDrawColor(Color.BLUE);
		dt.drawLine3D(ln6);

		dt.incrSequence();
		Line3D ln7 = new Line3D(new Point3D(-2,-2,-2), new Point3D(-2,2,2), new PhongMaterial(Color.CRIMSON));
		Line3D ln8 = dt.rotateLine3D(ln7, 20,20,0);
		ln7.setLineRadius(2);
		ln8.setLineRadius(2);
		dt.setDrawColor(Color.CRIMSON);
		dt.drawLine3D(ln7);
		dt.setDrawColor(Color.BLUE);
		dt.drawLine3D(ln8);

		dt.setDrawColor(Color.BLUE);
		Line3D ln1b = new Line3D(new Point3D(-2,2,2), new Point3D(-2,-2,-2), new PhongMaterial(Color.CRIMSON));
		Line3D ln2b = dt.rotateLine3D(ln1, 20, 0, 0);
		ln1b.setLineRadius(2);
		ln2b.setLineRadius(2);
		dt.drawLine3D(ln1b);
		dt.drawLine3D(ln2b);

//		Tetrahedron3D o3d6 = new Tetrahedron3D(-4, 0, 0, 2, 20, 0, 0, new PhongMaterial(Color.LIGHTGRAY));
//		dt.drawTetrahedron3D(o3d6);

//		dt.setDrawColor(Color.CRIMSON);
//		Line3D ln1 = new Line3D(new Point3D(0,0,0), new Point3D(4,0,0), new PhongMaterial(Color.CRIMSON));
//		ln1.setLineRadius(2);
//		dt.drawLine3D(ln1);

		Line3D ln1ry = dt.rotateLine3D(ln1, 0, 0, 45);
		ln1ry.setLineRadius(2);
		dt.drawLine3D(ln1ry);

		Line3D ln1rz = dt.rotateLine3D(ln1, 0, 45, 0);
		ln1rz.setLineRadius(2);
		dt.drawLine3D(ln1rz);

		Line3D ln1ryz = dt.rotateLine3D(ln1, 0, 45, 45);
		ln1ryz.setLineRadius(2);
		dt.drawLine3D(ln1ryz);

		Line3D ln2 = new Line3D(new Point3D(0,0,0), new Point3D(0,4,0), new PhongMaterial(Color.BLUE));
		ln2.setLineRadius(2);
		dt.drawLine3D(ln2);

		Line3D ln3 = new Line3D(new Point3D(0,0,0), new Point3D(0,0,4), new PhongMaterial(Color.GREEN));
		ln3.setLineRadius(2);
		dt.drawLine3D(ln3);

		Line3D ln4 = new Line3D(new Point3D(0,0,0), new Point3D(4,4,4), new PhongMaterial(Color.YELLOW));
		ln4.setLineRadius(2);
		dt.drawLine3D(ln4);
		Line3D ln5 = new Line3D(new Point3D(0,0,0), new Point3D(-4,-4,-4), new PhongMaterial(Color.YELLOW));
		ln5.setLineRadius(2);
		dt.drawLine3D(ln5);

		Line3D ln6 = new Line3D(new Point3D(0,0,0), new Point3D(-4,4,4), new PhongMaterial(Color.YELLOW));
		ln6.setLineRadius(2);
		dt.drawLine3D(ln6);
		Line3D ln7 = new Line3D(new Point3D(0,0,0), new Point3D(4,-4,-4), new PhongMaterial(Color.YELLOW));
		ln7.setLineRadius(2);
		dt.drawLine3D(ln7);

		Line3D ln8 = new Line3D(new Point3D(0,0,0), new Point3D(4,-4,4), new PhongMaterial(Color.MAGENTA));
		ln8.setLineRadius(2);
		dt.drawLine3D(ln8);
		Line3D ln9 = new Line3D(new Point3D(0,0,0), new Point3D(-4,4,-4), new PhongMaterial(Color.MAGENTA));
		ln9.setLineRadius(2);
		dt.drawLine3D(ln9);

		Line3D ln10 = new Line3D(new Point3D(0,0,0), new Point3D(-4,-4,4), new PhongMaterial(Color.MAGENTA));
		ln10.setLineRadius(2);
		dt.drawLine3D(ln10);
		Line3D ln11 = new Line3D(new Point3D(0,0,0), new Point3D(4,4,-4), new PhongMaterial(Color.MAGENTA));
		ln11.setLineRadius(2);
		dt.drawLine3D(ln11);

		Line3D ln12 = new Line3D(new Point3D(4,0,-1), new Point3D(4,0,1), new PhongMaterial(Color.TURQUOISE));
		ln12.setLineRadius(2);
		dt.drawLine3D(ln12);

		Line3D ln13 = new Line3D(new Point3D(6,0,-1), new Point3D(6,0,1), new PhongMaterial(Color.TURQUOISE));
		ln13.setLineRadius(2);
		dt.drawLine3D(ln13);

		Line3D ln14 = new Line3D(new Point3D(0,-4,-1), new Point3D(0,-4,1), new PhongMaterial(Color.TURQUOISE));
		ln14.setLineRadius(2);
		dt.drawLine3D(ln14);

		Line3D ln15 = new Line3D(new Point3D(0,-6,-1), new Point3D(0,-6,1), new PhongMaterial(Color.TURQUOISE));
		ln15.setLineRadius(2);
		dt.drawLine3D(ln15);

		Line3D ln16 = new Line3D(new Point3D(0,-1,4), new Point3D(0,1,4), new PhongMaterial(Color.TURQUOISE));
		ln16.setLineRadius(2);
		dt.drawLine3D(ln16);

		Line3D ln17 = new Line3D(new Point3D(0,-1,6), new Point3D(0,1,6), new PhongMaterial(Color.TURQUOISE));
		ln17.setLineRadius(2);
		dt.drawLine3D(ln17);

		Line3D ln18 = new Line3D(new Point3D(2, 0, 0), new Point3D(7, 0, 5), new PhongMaterial(Color.TURQUOISE));
		ln18.setLineRadius(2);
		dt.drawLine3D(ln18);

		Line3D ln19 = new Line3D(new Point3D(-2, 0, 0), new Point3D(-7, 0, 5), new PhongMaterial(Color.TURQUOISE));
		ln19.setLineRadius(2);
		dt.drawLine3D(ln19);
*/
	}

	public static void main(String[] args) {
		launch(args);
	}
}