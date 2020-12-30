package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Draw3D;
import org.edisonwj.draw3d.Line3D;
import org.edisonwj.draw3d.Rand;
import org.edisonwj.draw3d.Sphere3D;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.stage.Stage;
/**
* Test specifying lines using the coefficients
* of the three dimensional symmetric equation.
* Tests for detecting lines that are:
* - collinear with a boundary edge
* - within a boundary plane
* - intersecting the boundary only at a corner vertex
* Uses a randomly generated set for all others.
* 
* @author William Edison
* @version 1.04 February 2016
* 
*/
public class TestLineEquation extends Application {
	private Draw3D dt = new Draw3D();
	private Rand mr = new Rand();
	
	private double minmax = 10.0;
	
	private double a1 = 0;
	private double a2 = 0;
	private double a3 = 0;
	private double b1 = 0;
	private double b2 = 0;
	private double b3 = 0;
	
	@Override
	public void start(Stage primaryStage) {
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestLineEquation");
		primaryStage.show();
	}	
	
	private void buildData() {
		dt.setShowBoundaryCube(true);
		dt.setXYZRange(minmax, minmax, minmax);
		
		// Test plane 0 edge collinear
		Line3D ln00 = new Line3D(-10,-10,-5,-10,-10,5);
		ln00.setMaterial(new PhongMaterial(Color.MAGENTA));
		ln00.setLineRadius(1);
		System.out.println("\nln00= " + ln00);

		Point3D[] pi00 = ln00.findLineBoundaryIntersections(-minmax, minmax, -minmax, minmax, -minmax, minmax);
		Line3D ln00i = new Line3D(pi00[0], pi00[1]);
		ln00i.setMaterial(new PhongMaterial(Color.BLUE));
		ln00i.setLineRadius(1);
		System.out.println("intersections= " + pi00[0] + " " + pi00[1]);
		
		dt.drawLine3D(ln00i);
		dt.drawLine3D(ln00);
		
//		dt.incrSequence();
		Line3D ln01 = new Line3D(-10,-5,10,-10,5,10);
		ln01.setMaterial(new PhongMaterial(Color.MAGENTA));
		ln01.setLineRadius(1);
		System.out.println("\nln01= " + ln01);

		Point3D[] pi01 = ln01.findLineBoundaryIntersections(-minmax, minmax, -minmax, minmax, -minmax, minmax);
		Line3D ln01i = new Line3D(pi01[0], pi01[1]);
		ln01i.setMaterial(new PhongMaterial(Color.BLUE));
		ln01i.setLineRadius(1);
		System.out.println("intersections= " + pi01[0] + " " + pi01[1]);
		
		dt.drawLine3D(ln01i);
		dt.drawLine3D(ln01);
		
//		dt.incrSequence();
		Line3D ln02 = new Line3D(-10,10,5,-10,10,-5);
		ln02.setMaterial(new PhongMaterial(Color.MAGENTA));
		ln02.setLineRadius(1);
		System.out.println("\nln02= " + ln02);

		Point3D[] pi02 = ln02.findLineBoundaryIntersections(-minmax, minmax, -minmax, minmax, -minmax, minmax);
		Line3D ln02i = new Line3D(pi02[0], pi02[1]);
		ln02i.setMaterial(new PhongMaterial(Color.BLUE));
		ln02i.setLineRadius(1);
		System.out.println("intersections= " + pi02[0] + " " + pi02[1]);
		
		dt.drawLine3D(ln02i);
		dt.drawLine3D(ln02);
		
//		dt.incrSequence();
		Line3D ln03 = new Line3D(-10,5,-10,-10,-5,-10);
		ln03.setMaterial(new PhongMaterial(Color.MAGENTA));
		ln03.setLineRadius(1);
		System.out.println("\nln03= " + ln03);

		Point3D[] pi03 = ln03.findLineBoundaryIntersections(-minmax, minmax, -minmax, minmax, -minmax, minmax);
		Line3D ln03i = new Line3D(pi03[0], pi03[1]);
		ln03i.setMaterial(new PhongMaterial(Color.BLUE));
		ln03i.setLineRadius(1);
		System.out.println("intersections= " + pi03[0] + " " + pi03[1]);

		dt.drawLine3D(ln03i);
		dt.drawLine3D(ln03);

		// Test plane 2 edge collinear
//		dt.incrSequence();
		Line3D ln04 = new Line3D(10,-10,-5,10,-10,5);
		ln04.setMaterial(new PhongMaterial(Color.MAGENTA));
		ln04.setLineRadius(1);
		System.out.println("\nln04= " + ln04);

		Point3D[] pi04 = ln04.findLineBoundaryIntersections(-minmax, minmax, -minmax, minmax, -minmax, minmax);
		Line3D ln04i = new Line3D(pi04[0], pi04[1]);
		ln04i.setMaterial(new PhongMaterial(Color.BLUE));
		ln04i.setLineRadius(1);
		System.out.println("intersections= " + pi04[0] + " " + pi04[1]);
		
		dt.drawLine3D(ln04i);
		dt.drawLine3D(ln04);
		
//		dt.incrSequence();
		Line3D ln05 = new Line3D(10,-5,10,10,5,10);
		ln05.setMaterial(new PhongMaterial(Color.MAGENTA));
		ln05.setLineRadius(1);
		System.out.println("\nln05= " + ln05);

		Point3D[] pi05 = ln05.findLineBoundaryIntersections(-minmax, minmax, -minmax, minmax, -minmax, minmax);
		Line3D ln05i = new Line3D(pi05[0], pi05[1]);
		ln05i.setMaterial(new PhongMaterial(Color.BLUE));
		ln05i.setLineRadius(1);
		System.out.println("intersections= " + pi05[0] + " " + pi05[1]);
		
		dt.drawLine3D(ln05i);
		dt.drawLine3D(ln05);
		
//		dt.incrSequence();
		Line3D ln06 = new Line3D(10,10,5,10,10,-5);
		ln06.setMaterial(new PhongMaterial(Color.MAGENTA));
		ln06.setLineRadius(1);
		System.out.println("\nln06= " + ln06);

		Point3D[] pi06 = ln06.findLineBoundaryIntersections(-minmax, minmax, -minmax, minmax, -minmax, minmax);
		Line3D ln06i = new Line3D(pi06[0], pi06[1]);
		ln06i.setMaterial(new PhongMaterial(Color.BLUE));
		ln06i.setLineRadius(1);
		System.out.println("intersections= " + pi06[0] + " " + pi06[1]);
		
		dt.drawLine3D(ln06i);
		dt.drawLine3D(ln06);
		
//		dt.incrSequence();
		Line3D ln07 = new Line3D(10,5,-10,10,-5,-10);
		ln07.setMaterial(new PhongMaterial(Color.MAGENTA));
		ln07.setLineRadius(1);
		System.out.println("\nln07= " + ln07);

		Point3D[] pi07 = ln07.findLineBoundaryIntersections(-minmax, minmax, -minmax, minmax, -minmax, minmax);
		Line3D ln07i = new Line3D(pi07[0], pi07[1]);
		ln07i.setMaterial(new PhongMaterial(Color.BLUE));
		ln07i.setLineRadius(1);
		System.out.println("intersections= " + pi07[0] + " " + pi07[1]);
		
		dt.drawLine3D(ln07i);
		dt.drawLine3D(ln07);
		
		// Test plane 3 edge collinear
//		dt.incrSequence();
		Line3D ln08 = new Line3D(5,-10,-10,-5,-10,-10);
		ln08.setMaterial(new PhongMaterial(Color.MAGENTA));
		ln08.setLineRadius(1);
		System.out.println("\nln08= " + ln08);

		Point3D[] pi08 = ln08.findLineBoundaryIntersections(-minmax, minmax, -minmax, minmax, -minmax, minmax);
		Line3D ln08i = new Line3D(pi08[0], pi08[1]);
		ln08i.setMaterial(new PhongMaterial(Color.BLUE));
		ln08i.setLineRadius(1);
		System.out.println("intersections= " + pi08[0] + " " + pi08[1]);
		
		dt.drawLine3D(ln08i);
		dt.drawLine3D(ln08);
		
//		dt.incrSequence();
		Line3D ln09 = new Line3D(5,-10,10,-5,-10,10);
		ln09.setMaterial(new PhongMaterial(Color.MAGENTA));
		ln09.setLineRadius(1);
		System.out.println("\nln09= " + ln09);

		Point3D[] pi09 = ln09.findLineBoundaryIntersections(-minmax, minmax, -minmax, minmax, -minmax, minmax);
		Line3D ln09i = new Line3D(pi09[0], pi09[1]);
		ln09i.setMaterial(new PhongMaterial(Color.BLUE));
		ln09i.setLineRadius(1);
		System.out.println("intersections= " + pi09[0] + " " + pi09[1]);
		
		dt.drawLine3D(ln09i);
		dt.drawLine3D(ln09);
		
		// Test plane 1 edge collinear
//		dt.incrSequence();
		Line3D ln10 = new Line3D(5,10,10,-5,10,10);
		ln10.setMaterial(new PhongMaterial(Color.MAGENTA));
		ln10.setLineRadius(1);
		System.out.println("\nln10= " + ln10);

		Point3D[] pi10 = ln10.findLineBoundaryIntersections(-minmax, minmax, -minmax, minmax, -minmax, minmax);
		Line3D ln10i = new Line3D(pi10[0], pi10[1]);
		ln10i.setMaterial(new PhongMaterial(Color.BLUE));
		ln10i.setLineRadius(1);
		System.out.println("intersections= " + pi10[0] + " " + pi10[1]);
		
		dt.drawLine3D(ln10i);
		dt.drawLine3D(ln10);
		
//		dt.incrSequence();
		Line3D ln11 = new Line3D(5,10,-10,-5,10,-10);
		ln11.setMaterial(new PhongMaterial(Color.MAGENTA));
		ln11.setLineRadius(1);
		System.out.println("\nln11= " + ln11);

		Point3D[] pi11 = ln11.findLineBoundaryIntersections(-minmax, minmax, -minmax, minmax, -minmax, minmax);
		Line3D ln11i = new Line3D(pi11[0], pi11[1]);
		ln11i.setMaterial(new PhongMaterial(Color.BLUE));
		ln11i.setLineRadius(1);
		System.out.println("intersections= " + pi11[0] + " " + pi11[1]);
		
		dt.drawLine3D(ln11i);
		dt.drawLine3D(ln11);
		
		// Test in plane 4
		dt.incrSequence();
		Line3D ln12 = new Line3D(5,5,-10,-5,-5,-10);
		ln12.setMaterial(new PhongMaterial(Color.MAGENTA));
		ln12.setLineRadius(1);
		System.out.println("\nln12= " + ln12);

		Point3D[] pi12 = ln12.findLineBoundaryIntersections(-minmax, minmax, -minmax, minmax, -minmax, minmax);
		Line3D ln12i = new Line3D(pi12[0], pi12[1]);
		ln12i.setMaterial(new PhongMaterial(Color.BLUE));
		ln12i.setLineRadius(1);
		System.out.println("intersections= " + pi12[0] + " " + pi12[1]);
		
		dt.drawLine3D(ln12i);
		dt.drawLine3D(ln12);
		
		Line3D ln13 = new Line3D(-5,5,-10,5,-5,-10);
		ln13.setMaterial(new PhongMaterial(Color.MAGENTA));
		ln13.setLineRadius(1);
		System.out.println("\nln13= " + ln13);

		Point3D[] pi13 = ln13.findLineBoundaryIntersections(-minmax, minmax, -minmax, minmax, -minmax, minmax);
		Line3D ln13i = new Line3D(pi13[0], pi13[1]);
		ln13i.setMaterial(new PhongMaterial(Color.BLUE));
		ln13i.setLineRadius(1);
		System.out.println("intersections= " + pi13[0] + " " + pi13[1]);
		
		dt.drawLine3D(ln13i);
		dt.drawLine3D(ln13);
		
		Line3D ln14 = new Line3D(5,5,-10,5,-5,-10);
		ln14.setMaterial(new PhongMaterial(Color.MAGENTA));
		ln14.setLineRadius(1);
		System.out.println("\nln14= " + ln14);

		Point3D[] pi14 = ln14.findLineBoundaryIntersections(-minmax, minmax, -minmax, minmax, -minmax, minmax);
		Line3D ln14i = new Line3D(pi14[0], pi14[1]);
		ln14i.setMaterial(new PhongMaterial(Color.BLUE));
		ln14i.setLineRadius(1);
		System.out.println("intersections= " + pi14[0] + " " + pi14[1]);
		
		dt.drawLine3D(ln14i);
		dt.drawLine3D(ln14);
		
		Line3D ln15 = new Line3D(-5,5,-10,5,5,-10);
		ln15.setMaterial(new PhongMaterial(Color.MAGENTA));
		ln15.setLineRadius(1);
		System.out.println("\nln15= " + ln15);

		Point3D[] pi15 = ln15.findLineBoundaryIntersections(-minmax, minmax, -minmax, minmax, -minmax, minmax);
		Line3D ln15i = new Line3D(pi15[0], pi15[1]);
		ln15i.setMaterial(new PhongMaterial(Color.BLUE));
		ln15i.setLineRadius(1);
		System.out.println("intersections= " + pi15[0] + " " + pi15[1]);
		
		dt.drawLine3D(ln15i);
		dt.drawLine3D(ln15);

		// Test in plane 5
//		dt.incrSequence();
		Line3D ln16 = new Line3D(5,5,10,-5,-5,10);
		ln16.setMaterial(new PhongMaterial(Color.MAGENTA));
		ln16.setLineRadius(1);
		System.out.println("\nln16= " + ln16);

		Point3D[] pi16 = ln16.findLineBoundaryIntersections(-minmax, minmax, -minmax, minmax, -minmax, minmax);
		Line3D ln16i = new Line3D(pi16[0], pi16[1]);
		ln16i.setMaterial(new PhongMaterial(Color.BLUE));
		ln16i.setLineRadius(1);
		System.out.println("intersections= " + pi16[0] + " " + pi16[1]);
		
		dt.drawLine3D(ln16i);
		dt.drawLine3D(ln16);
		
		Line3D ln17 = new Line3D(-5,5,10,5,-5,10);
		ln17.setMaterial(new PhongMaterial(Color.MAGENTA));
		ln17.setLineRadius(1);
		System.out.println("\nln17= " + ln17);

		Point3D[] pi17 = ln17.findLineBoundaryIntersections(-minmax, minmax, -minmax, minmax, -minmax, minmax);
		Line3D ln17i = new Line3D(pi17[0], pi17[1]);
		ln17i.setMaterial(new PhongMaterial(Color.BLUE));
		ln17i.setLineRadius(1);
		System.out.println("intersections= " + pi17[0] + " " + pi17[1]);
		
		dt.drawLine3D(ln17i);
		dt.drawLine3D(ln17);
		
		Line3D ln18 = new Line3D(5,5,10,5,-5,10);
		ln18.setMaterial(new PhongMaterial(Color.MAGENTA));
		ln18.setLineRadius(1);
		System.out.println("\nln18= " + ln18);

		Point3D[] pi18 = ln18.findLineBoundaryIntersections(-minmax, minmax, -minmax, minmax, -minmax, minmax);
		Line3D ln18i = new Line3D(pi18[0], pi18[1]);
		ln18i.setMaterial(new PhongMaterial(Color.BLUE));
		ln18i.setLineRadius(1);
		System.out.println("intersections= " + pi18[0] + " " + pi18[1]);
		
		dt.drawLine3D(ln18i);
		dt.drawLine3D(ln18);
		
		Line3D ln19 = new Line3D(-5,5,10,5,5,10);
		ln19.setMaterial(new PhongMaterial(Color.MAGENTA));
		ln19.setLineRadius(1);
		System.out.println("\nln19= " + ln19);

		Point3D[] pi19 = ln19.findLineBoundaryIntersections(-minmax, minmax, -minmax, minmax, -minmax, minmax);
		Line3D ln19i = new Line3D(pi19[0], pi19[1]);
		ln19i.setMaterial(new PhongMaterial(Color.BLUE));
		ln19i.setLineRadius(1);
		System.out.println("intersections= " + pi19[0] + " " + pi19[1]);
		
		dt.drawLine3D(ln19i);
		dt.drawLine3D(ln19);
		
		// Test in plane 0 
//		dt.incrSequence();
		Line3D ln20 = new Line3D(-10,5,5,-10,-5,-5);
		ln20.setMaterial(new PhongMaterial(Color.MAGENTA));
		ln20.setLineRadius(1);
		System.out.println("\nln20= " + ln20);

		Point3D[] pi20 = ln20.findLineBoundaryIntersections(-minmax, minmax, -minmax, minmax, -minmax, minmax);
		Line3D ln20i = new Line3D(pi20[0], pi20[1]);
		ln20i.setMaterial(new PhongMaterial(Color.BLUE));
		ln20i.setLineRadius(1);
		System.out.println("intersections= " + pi20[0] + " " + pi20[1]);
		
		dt.drawLine3D(ln20i);
		dt.drawLine3D(ln20);
		
		Line3D ln21 = new Line3D(-10,-5,5,-10,5,-5);
		ln21.setMaterial(new PhongMaterial(Color.MAGENTA));
		ln21.setLineRadius(1);
		System.out.println("\nln21= " + ln21);

		Point3D[] pi21 = ln21.findLineBoundaryIntersections(-minmax, minmax, -minmax, minmax, -minmax, minmax);
		Line3D ln21i = new Line3D(pi21[0], pi21[1]);
		ln21i.setMaterial(new PhongMaterial(Color.BLUE));
		ln21i.setLineRadius(1);
		System.out.println("intersections= " + pi21[0] + " " + pi21[1]);
		
		dt.drawLine3D(ln21i);
		dt.drawLine3D(ln21);
		
		Line3D ln22 = new Line3D(-10,5,5,-10,5,-5);
		ln22.setMaterial(new PhongMaterial(Color.MAGENTA));
		ln22.setLineRadius(1);
		System.out.println("\nln22= " + ln22);

		Point3D[] pi22 = ln22.findLineBoundaryIntersections(-minmax, minmax, -minmax, minmax, -minmax, minmax);
		Line3D ln22i = new Line3D(pi22[0], pi22[1]);
		ln22i.setMaterial(new PhongMaterial(Color.BLUE));
		ln22i.setLineRadius(1);
		System.out.println("intersections= " + pi22[0] + " " + pi22[1]);
		
		dt.drawLine3D(ln22i);
		dt.drawLine3D(ln22);
		
		Line3D ln23 = new Line3D(-10,5,5,-10,-5,5);
		ln23.setMaterial(new PhongMaterial(Color.MAGENTA));
		ln23.setLineRadius(1);
		System.out.println("\nln23= " + ln23);

		Point3D[] pi23 = ln23.findLineBoundaryIntersections(-minmax, minmax, -minmax, minmax, -minmax, minmax);
		Line3D ln23i = new Line3D(pi23[0], pi23[1]);
		ln23i.setMaterial(new PhongMaterial(Color.BLUE));
		ln23i.setLineRadius(1);
		System.out.println("intersections= " + pi23[0] + " " + pi23[1]);
		
		dt.drawLine3D(ln23i);
		dt.drawLine3D(ln23);
		
		// Test in plane 2 
//		dt.incrSequence();
		Line3D ln24 = new Line3D(10,5,5,10,-5,-5);
		ln24.setMaterial(new PhongMaterial(Color.MAGENTA));
		ln24.setLineRadius(1);
		System.out.println("\nln24= " + ln24);

		Point3D[] pi24 = ln24.findLineBoundaryIntersections(-minmax, minmax, -minmax, minmax, -minmax, minmax);
		Line3D ln24i = new Line3D(pi24[0], pi24[1]);
		ln24i.setMaterial(new PhongMaterial(Color.BLUE));
		ln24i.setLineRadius(1);
		System.out.println("intersections= " + pi24[0] + " " + pi24[1]);
		
		dt.drawLine3D(ln24i);
		dt.drawLine3D(ln24);
		
		Line3D ln25 = new Line3D(10,-5,5,10,5,-5);
		ln25.setMaterial(new PhongMaterial(Color.MAGENTA));
		ln25.setLineRadius(1);
		System.out.println("\nln25= " + ln25);

		Point3D[] pi25 = ln25.findLineBoundaryIntersections(-minmax, minmax, -minmax, minmax, -minmax, minmax);
		Line3D ln25i = new Line3D(pi25[0], pi25[1]);
		ln25i.setMaterial(new PhongMaterial(Color.BLUE));
		ln25i.setLineRadius(1);
		System.out.println("intersections= " + pi25[0] + " " + pi25[1]);
		
		dt.drawLine3D(ln25i);
		dt.drawLine3D(ln25);
		
		Line3D ln26 = new Line3D(10,5,5,10,5,-5);
		ln26.setMaterial(new PhongMaterial(Color.MAGENTA));
		ln26.setLineRadius(1);
		System.out.println("\nln26= " + ln26);

		Point3D[] pi26 = ln26.findLineBoundaryIntersections(-minmax, minmax, -minmax, minmax, -minmax, minmax);
		Line3D ln26i = new Line3D(pi26[0], pi26[1]);
		ln26i.setMaterial(new PhongMaterial(Color.BLUE));
		ln26i.setLineRadius(1);
		System.out.println("intersections= " + pi26[0] + " " + pi26[1]);
		
		dt.drawLine3D(ln26i);
		dt.drawLine3D(ln26);
		
		Line3D ln27 = new Line3D(10,5,5,10,-5,5);
		ln27.setMaterial(new PhongMaterial(Color.MAGENTA));
		ln27.setLineRadius(1);
		System.out.println("\nln27= " + ln27);

		Point3D[] pi27 = ln27.findLineBoundaryIntersections(-minmax, minmax, -minmax, minmax, -minmax, minmax);
		Line3D ln27i = new Line3D(pi27[0], pi27[1]);
		ln27i.setMaterial(new PhongMaterial(Color.BLUE));
		ln27i.setLineRadius(1);
		System.out.println("intersections= " + pi27[0] + " " + pi27[1]);
		
		dt.drawLine3D(ln27i);
		dt.drawLine3D(ln27);
		
		// Test in plane 1 
//		dt.incrSequence();
		Line3D ln28 = new Line3D(5,10,5,-5,10,-5);
		ln28.setMaterial(new PhongMaterial(Color.MAGENTA));
		ln28.setLineRadius(1);
		System.out.println("\nln28= " + ln28);

		Point3D[] pi28 = ln28.findLineBoundaryIntersections(-minmax, minmax, -minmax, minmax, -minmax, minmax);
		Line3D ln28i = new Line3D(pi28[0], pi28[1]);
		ln28i.setMaterial(new PhongMaterial(Color.BLUE));
		ln28i.setLineRadius(1);
		System.out.println("intersections= " + pi28[0] + " " + pi28[1]);
		
		dt.drawLine3D(ln28i);
		dt.drawLine3D(ln28);
		
		Line3D ln29 = new Line3D(-5,10,5,5,10,-5);
		ln29.setMaterial(new PhongMaterial(Color.MAGENTA));
		ln29.setLineRadius(1);
		System.out.println("\nln29= " + ln29);

		Point3D[] pi29 = ln29.findLineBoundaryIntersections(-minmax, minmax, -minmax, minmax, -minmax, minmax);
		Line3D ln29i = new Line3D(pi29[0], pi29[1]);
		ln29i.setMaterial(new PhongMaterial(Color.BLUE));
		ln29i.setLineRadius(1);
		System.out.println("intersections= " + pi29[0] + " " + pi29[1]);
		
		dt.drawLine3D(ln29i);
		dt.drawLine3D(ln29);
		
		Line3D ln30 = new Line3D(5,10,5,5,10,-5);
		ln30.setMaterial(new PhongMaterial(Color.MAGENTA));
		ln30.setLineRadius(1);
		System.out.println("\nln30= " + ln30);

		Point3D[] pi30 = ln30.findLineBoundaryIntersections(-minmax, minmax, -minmax, minmax, -minmax, minmax);
		Line3D ln30i = new Line3D(pi30[0], pi30[1]);
		ln30i.setMaterial(new PhongMaterial(Color.BLUE));
		ln30i.setLineRadius(1);
		System.out.println("intersections= " + pi30[0] + " " + pi30[1]);
		
		dt.drawLine3D(ln30i);
		dt.drawLine3D(ln30);
		
		Line3D ln31 = new Line3D(5,10,5,-5,10,5);
		ln31.setMaterial(new PhongMaterial(Color.MAGENTA));
		ln31.setLineRadius(1);
		System.out.println("\nln31= " + ln31);

		Point3D[] pi31 = ln31.findLineBoundaryIntersections(-minmax, minmax, -minmax, minmax, -minmax, minmax);
		Line3D ln31i = new Line3D(pi31[0], pi31[1]);
		ln31i.setMaterial(new PhongMaterial(Color.BLUE));
		ln31i.setLineRadius(1);
		System.out.println("intersections= " + pi31[0] + " " + pi31[1]);
		
		dt.drawLine3D(ln31i);
		dt.drawLine3D(ln31);
		
		// Test in plane 3 
//		dt.incrSequence();
		Line3D ln33 = new Line3D(5,-10,5,-5,-10,-5);
		ln33.setMaterial(new PhongMaterial(Color.MAGENTA));
		ln33.setLineRadius(1);
		System.out.println("\nln33= " + ln33);

		Point3D[] pi33 = ln33.findLineBoundaryIntersections(-minmax, minmax, -minmax, minmax, -minmax, minmax);
		Line3D ln33i = new Line3D(pi33[0], pi33[1]);
		ln33i.setMaterial(new PhongMaterial(Color.BLUE));
		ln33i.setLineRadius(1);
		System.out.println("intersections= " + pi33[0] + " " + pi33[1]);
		
		dt.drawLine3D(ln33i);
		dt.drawLine3D(ln33);
		
		Line3D ln34 = new Line3D(-5,-10,5,5,-10,-5);
		ln34.setMaterial(new PhongMaterial(Color.MAGENTA));
		ln34.setLineRadius(1);
		System.out.println("\nln34= " + ln34);

		Point3D[] pi34 = ln34.findLineBoundaryIntersections(-minmax, minmax, -minmax, minmax, -minmax, minmax);
		Line3D ln34i = new Line3D(pi34[0], pi34[1]);
		ln34i.setMaterial(new PhongMaterial(Color.BLUE));
		ln34i.setLineRadius(1);
		System.out.println("intersections= " + pi34[0] + " " + pi34[1]);
		
		dt.drawLine3D(ln34i);
		dt.drawLine3D(ln34);
		
		Line3D ln35 = new Line3D(5,-10,5,5,-10,-5);
		ln35.setMaterial(new PhongMaterial(Color.MAGENTA));
		ln35.setLineRadius(1);
		System.out.println("\nln35= " + ln35);

		Point3D[] pi35 = ln35.findLineBoundaryIntersections(-minmax, minmax, -minmax, minmax, -minmax, minmax);
		Line3D ln35i = new Line3D(pi35[0], pi35[1]);
		ln35i.setMaterial(new PhongMaterial(Color.BLUE));
		ln35i.setLineRadius(1);
		System.out.println("intersections= " + pi35[0] + " " + pi35[1]);
		
		dt.drawLine3D(ln35i);
		dt.drawLine3D(ln35);
		
		Line3D ln36 = new Line3D(5,-10,5,-5,-10,5);
		ln36.setMaterial(new PhongMaterial(Color.MAGENTA));
		ln36.setLineRadius(1);
		System.out.println("\nln36= " + ln36);

		Point3D[] pi36 = ln36.findLineBoundaryIntersections(-minmax, minmax, -minmax, minmax, -minmax, minmax);
		Line3D ln36i = new Line3D(pi36[0], pi36[1]);
		ln36i.setMaterial(new PhongMaterial(Color.BLUE));
		ln36i.setLineRadius(1);
		System.out.println("intersections= " + pi36[0] + " " + pi36[1]);
		
		dt.drawLine3D(ln36i);
		dt.drawLine3D(ln36);
		
		// Test corner intersection
		dt.incrSequence();
		dt.setOriginView(0,  0,  0);
		Line3D ln37 = new Line3D(0,20,-10,-20,0,-10);
		ln37.setMaterial(new PhongMaterial(Color.MAGENTA));
		ln37.setLineRadius(1);
		System.out.println("\nln37= " + ln37);

		Point3D[] pi37 = ln37.findLineBoundaryIntersections(-minmax, minmax, -minmax, minmax, -minmax, minmax);
		Line3D ln37i = new Line3D(pi37[0], pi37[1]);
		ln37i.setMaterial(new PhongMaterial(Color.BLUE));
		ln37i.setLineRadius(1);
		System.out.println("intersections= " + pi37[0] + " " + pi37[1]);
		
		if (pi37[0].equals(pi37[1])) {
			dt.drawSphere3D(new Sphere3D(pi37[0],.15,new PhongMaterial(Color.BLUE)));
		}

//		dt.drawLine3D(ln37i);
		dt.drawLine3D(ln37);
		
		// Test outside boundary
		Line3D ln38 = new Line3D(0,30,-10,-30,0,-10);
		ln38.setMaterial(new PhongMaterial(Color.MAGENTA));
		ln38.setLineRadius(1);
		System.out.println("\nln38= " + ln38);

		Point3D[] pi38 = ln38.findLineBoundaryIntersections(-minmax, minmax, -minmax, minmax, -minmax, minmax);
		
		dt.drawLine3D(ln38);

		
		dt.incrSequence();
		dt.setOriginView(0,  0,  0);
		dt.setShowAxes(false);
		dt.setShowBoundaryCube(false);
	    for (int i = 0; i < 500; i++) {
	    	setColor();
	    	setLine();
	    	System.out.println("\ni= " + i + " generated line");
	    	dt.drawLineEquation(a1,  a2,  a3,  b1,  b2,  b3);
	    }
	}
	
	private void setLine() {
		a1 = mr.uniform(0.0, minmax);
		if (mr.uniform() < .5) a1 = -a1;
		a2 = mr.uniform(0.0, minmax);
		if (mr.uniform() < .5) a2 = -a2;
		a3 = mr.uniform(0.0, minmax);
		if (mr.uniform() < .5) a3 = -a3;
		b1 = mr.uniform(0.0, minmax);
		if (mr.uniform() < .5) b1 = -b1;
		b2 = mr.uniform(0.0, minmax);
		if (mr.uniform() < .5) b2 = -b2;
		b3 = mr.uniform(0.0, minmax);
		if (mr.uniform() < .5) b3 = -b3;
	}
	
	private void setColor() {
		int rRGB = mr.uniform(0,  255);
		int gRGB = mr.uniform(0,  255);
		int bRGB = mr.uniform(0,  255);
		dt.setDrawColor(Color.rgb(rRGB, gRGB, bRGB));
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}