package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Arrow3D;
import org.edisonwj.draw3d.Draw3D;
import org.edisonwj.draw3d.Line3D;
import org.edisonwj.draw3d.Polygon3D;
import org.edisonwj.draw3d.Triangle3D;
import org.edisonwj.draw3d.Vector3D;

import javafx.application.Application;
import javafx.geometry.Point3D;
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
public class TestPolygon2 extends Application {
	private Draw3D dt;

	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestPolygon2");
		primaryStage.show();
	}

	private void buildData() {

		dt.setCumulate(false);
		dt.setPointLight(false);
		dt.setAmbientLight(true);
		dt.setShowBoundaryCube(true);

		int[][] t;
		Triangle3D[] t3D;
		Color[] colors = {	Color.RED, Color.BLUE, Color.GREEN, Color.MAGENTA,
							Color.RED, Color.BLUE, Color.GREEN, Color.MAGENTA };


		dt.setSequencingOn();
		System.out.println("Polygon 1");
		Point3D[] v1 = {
					new Point3D( -5.00,  -2.6, 0.0),
					new Point3D( -0.75,  -5.0, 0.0),
					new Point3D(  6.00,  -4.0, 0.0),
					new Point3D(  5.00,   5.4, 0.0),
//					new Point3D(  2.00,   2.4, 0.0),
					new Point3D(  3.00,   7.0, 0.0),
					new Point3D( -5.00,   5.0, 0.0) };

		Polygon3D p1 = new Polygon3D(v1, new PhongMaterial(Color.CORNFLOWERBLUE));
		dt.drawPolygon3D(p1);
		dt.drawLabel(12, -6, -8, "Polygon 1");

		dt.incrSequence();
		Polygon3D p1p = new Polygon3D(v1, new PhongMaterial(Color.CORNFLOWERBLUE));
		p1p.setZ(4,  5,  6,  7);
		Point3D[] v1s = p1p.getV();
//		dt.drawPlane3D(new Plane3D(v1s[0], v1s[1], v1s[2]), new PhongMaterial(Color.ANTIQUEWHITE));
		dt.drawPolygon3D(p1p);
		dt.drawLabel(12, -6, -8, "Polygon 1Z");
		dt.setDrawColor(Color.BLACK);
		dt.setPointSize(2);
		dt.drawPoint3D(v1s[0]);
		dt.drawLabel(v1s[0].getX(), v1s[0].getY(), v1s[0].getZ(), "V0");
		dt.drawPoint3D(v1s[1]);
		dt.drawLabel(v1s[1].getX(), v1s[1].getY(), v1s[1].getZ(), "V1");
		dt.drawPoint3D(v1s[2]);
		dt.drawLabel(v1s[2].getX(), v1s[2].getY(), v1s[2].getZ(), "V2");
		dt.drawPoint3D(v1s[3]);
		dt.drawLabel(v1s[3].getX(), v1s[3].getY(), v1s[3].getZ(), "V3");
		dt.drawPoint3D(v1s[4]);
		dt.drawLabel(v1s[4].getX(), v1s[4].getY(), v1s[4].getZ(), "V4");
		dt.drawPoint3D(v1s[5]);
		dt.drawLabel(v1s[5].getX(), v1s[5].getY(), v1s[5].getZ(), "V5");

		System.out.println("isConvexPoly()= " + p1.isConvexPoly());
//		System.out.println("isPlanar= " + p1.isPlanar());
		System.out.println("isInAxisPlane= " + p1.isInAxisPlane());

		dt.incrSequence();
		dt.drawLabel(12, -6, -8, "Polygon 1T");
		if (p1p.isConvexPoly()) {
			t = p1p.triangulateConvexPolygon();
		}
		else {
			t = p1p.triangulatePolygon();
		}

		t3D = new Triangle3D[t.length];
		System.out.println("t.length= " + t.length);
		for (int i = 0; i < t.length; i++) {
			t3D[i] = new Triangle3D(v1s[t[i][0]], v1s[t[i][1]], v1s[t[i][2]], new PhongMaterial(colors[i]));
			System.out.println("t3D[" + i + "]= " + t3D[i]);
			dt.drawTriangle3D(t3D[i]);
		}

		dt.incrSequence();
		System.out.println("\nPolygon 2");
		Point3D[] v2 = {
				new Point3D( -5.00, 0.0, -2.6 ),
				new Point3D( -0.75, 0.0, -5.0 ),
				new Point3D(  6.00, 0.0, -4.0 ),
				new Point3D(  5.00, 0.0,  5.4 ),
//				new Point3D(  2.00, 0.0,  2.4 ),
				new Point3D(  3.00, 0.0,  7.0 ),
				new Point3D( -5.00, 0.0,  5.0 ) };
		Polygon3D p2 = new Polygon3D(v2, new PhongMaterial(Color.CORNFLOWERBLUE));
		dt.drawPolygon3D(p2);
		dt.drawLabel(12, -6, -8, "Polygon 2");
		Point3D[] v2s = p2.getV();
		dt.setDrawColor(Color.BLACK);
		dt.setPointSize(2);
		dt.drawPoint3D(v2s[0]);
		dt.drawLabel(v2s[0].getX(), v2s[0].getY(), v2s[0].getZ(), "V0");
		dt.drawPoint3D(v2s[1]);
		dt.drawLabel(v2s[1].getX(), v2s[1].getY(), v2s[1].getZ(), "V1");
		dt.drawPoint3D(v2s[2]);
		dt.drawLabel(v2s[2].getX(), v2s[2].getY(), v2s[2].getZ(), "V2");
		dt.drawPoint3D(v2s[3]);
		dt.drawLabel(v2s[3].getX(), v2s[3].getY(), v2s[3].getZ(), "V3");
		dt.drawPoint3D(v2s[4]);
		dt.drawLabel(v2s[4].getX(), v2s[4].getY(), v2s[4].getZ(), "V4");
		dt.drawPoint3D(v2s[5]);
		dt.drawLabel(v2s[5].getX(), v2s[5].getY(), v2s[5].getZ(), "V5");

		System.out.println("isConvexPoly()= " + p2.isConvexPoly());
//		System.out.println("isPlanar= " + p2.isPlanar());
		System.out.println("isInAxisPlane= " + p2.isInAxisPlane());

		dt.incrSequence();
		dt.drawLabel(12, -6, -8, "Polygon 2T");
		if (p2.isConvexPoly()) {
			t = p2.triangulateConvexPolygon();
		}
		else {
			t = p2.triangulatePolygon();
		}

		t3D = new Triangle3D[t.length];
		System.out.println("t.length= " + t.length);
		for (int i = 0; i < t.length; i++) {
			t3D[i] = new Triangle3D(v2s[t[i][0]], v2s[t[i][1]], v2s[t[i][2]], new PhongMaterial(colors[i]));
			System.out.println("t3D[" + i + "]= " + t3D[i]);
			dt.drawTriangle3D(t3D[i]);
		}

		dt.incrSequence();
		System.out.println("\nPolygon 3");
		Point3D[] v3 = {
				new Point3D ( 0.0, -0.75, -5.0),
				new Point3D ( 0.0, -5.0,  -2.6),
				new Point3D ( 0.0, -5.0,   5.0),
				new Point3D ( 0.0,  3.0,   7.0),
				new Point3D ( 0.0,  5.0,   5.4),
				new Point3D ( 0.0,  6.0,  -4.0) };

/*
				new Point3D( 0.0, -5.00,  -2.6 ),
				new Point3D( 0.0, -0.75,  -5.0 ),
				new Point3D( 0.0,  6.00,  -4.0 ),
				new Point3D( 0.0,  5.00,   5.4 ),
//				new Point3D( 0.0,  2.00,   2.4 ),
				new Point3D( 0.0,  3.00,   7.0 ),
				new Point3D( 0.0, -5.00,   5.0 ) };
*/

		Polygon3D p3 = new Polygon3D(v3, new PhongMaterial(Color.CORNFLOWERBLUE));
		dt.drawPolygon3D(p3);
		dt.drawLabel(12, -6, -8, "Polygon 3");
		Point3D[] v3s = p3.getV();
		dt.setDrawColor(Color.BLACK);
		dt.setPointSize(2);
		dt.drawPoint3D(v3s[0]);
		dt.drawLabel(v3s[0].getX(), v3s[0].getY(), v3s[0].getZ(), "V0");
		dt.drawPoint3D(v3s[1]);
		dt.drawLabel(v3s[1].getX(), v3s[1].getY(), v3s[1].getZ(), "V1");
		dt.drawPoint3D(v3s[2]);
		dt.drawLabel(v3s[2].getX(), v3s[2].getY(), v3s[2].getZ(), "V2");
		dt.drawPoint3D(v3s[3]);
		dt.drawLabel(v3s[3].getX(), v3s[3].getY(), v3s[3].getZ(), "v4");
		dt.drawPoint3D(v3s[4]);
		dt.drawLabel(v3s[4].getX(), v3s[4].getY(), v3s[4].getZ(), "V4");
		dt.drawPoint3D(v3s[5]);
		dt.drawLabel(v3s[5].getX(), v3s[5].getY(), v3s[5].getZ(), "V5");

		System.out.println("isConvexPoly()= " + p3.isConvexPoly());
//		System.out.println("isPlanar= " + p3.isPlanar());
		System.out.println("isInAxisPlane= " + p3.isInAxisPlane());

		dt.incrSequence();
		dt.drawLabel(12, -6, -8, "Polygon 3T");
		if (p3.isConvexPoly()) {
			t = p3.triangulateConvexPolygon();
		}
		else {
			t = p3.triangulatePolygon();
		}

		t3D = new Triangle3D[t.length];
		System.out.println("t.length= " + t.length);
		for (int i = 0; i < t.length; i++) {
			t3D[i] = new Triangle3D(v3s[t[i][0]], v3s[t[i][1]], v3s[t[i][2]], new PhongMaterial(colors[i]));
			System.out.println("t3D[" + i + "]= " + t3D[i]);
			dt.drawTriangle3D(t3D[i]);
		}

		dt.incrSequence();
		System.out.println("\nPolygon 4");
		Point3D[] v4 = {
				new Point3D( 10.0, -0.75,  -5.0 ),
				new Point3D( 10.0, -5.00,  -2.6 ),
				new Point3D( 10.0, -5.00,   5.0 ),
				new Point3D( 10.0,  3.00,   7.0 ),
				new Point3D( 10.0,  2.00,   2.4 ),
				new Point3D( 10.0,  6.00,  -4.0 ) };

//				new Point3D( 10.0, -5.00,  -2.6 ),
//				new Point3D( 10.0, -0.75,  -5.0 ),
//				new Point3D( 10.0,  6.00,  -4.0 ),
//-				new Point3D( 10.0,  5.00,   5.4 ),
//				new Point3D( 10.0,  2.00,   2.4 ),
//				new Point3D( 10.0,  3.00,   7.0 ),
//				new Point3D( 10.0, -5.00,   5.0 ) };

		Polygon3D p4 = new Polygon3D(v4, new PhongMaterial(Color.CORNFLOWERBLUE));
		Point3D[] vs = p4.getV();
		dt.setPointSize(1);
		for (int j = 0; j < vs.length; j++) {
			dt.drawPoint3D(vs[j]);
			dt.drawLabel(vs[j].getX(), vs[j].getY(), vs[j].getZ(), ("V" + j));
			dt.drawArrow3D(new Arrow3D(vs[j], vs[(j+1)%vs.length]));
			dt.setDrawColor(Color.BLACK);
			System.out.println("vs[" + j + "]= " + vs[j]);
		}
		String label4 = String.format("Polygon 4, d=%6.2f, z=%6.2f, vs=%4.1f, set to -1", p4.getD(), p4.getUnitNormal().getZ(), p4.getVolumeSign());
		dt.drawLabel(12, -6, -8, label4);
		Vector3D un4 = Vector3D.multiply(10.0,  p4.getUnitNormal());
		un4.setMaterial(new PhongMaterial(Color.MAGENTA));
		un4.setVectorRadius(1);
		dt.drawVector3D(un4);
		dt.drawPolygon3D(p4);

		dt.incrSequence();
		System.out.println("ChangeXYZ");
		Polygon3D p4c = new Polygon3D(v4, new PhongMaterial(Color.CORNFLOWERBLUE));
		p4c.changeXYZ(-8.0,  0.0, 0.0);
		dt.drawPolygon3D(p4c);
		dt.drawLabel(12, -6, -8, "Polygon 4C");
		Vector3D un4c = Vector3D.multiply(10.0, p4c.getUnitNormal());
		un4c.setMaterial(new PhongMaterial(Color.MAGENTA));
		un4c.setVectorRadius(1);
		dt.drawVector3D(un4c);
		Point3D[] v4s = p4c.getV();
		dt.setDrawColor(Color.BLACK);
		dt.setPointSize(2);
		dt.drawPoint3D(v4s[0]);
		dt.drawLabel(v4s[0].getX(), v4s[0].getY(), v4s[0].getZ(), "V0");
		dt.drawPoint3D(v4s[1]);
		dt.drawLabel(v4s[1].getX(), v4s[1].getY(), v4s[1].getZ(), "V1");
		dt.drawPoint3D(v4s[2]);
		dt.drawLabel(v4s[2].getX(), v4s[2].getY(), v4s[2].getZ(), "V2");
		dt.drawPoint3D(v4s[3]);
		dt.drawLabel(v4s[3].getX(), v4s[3].getY(), v4s[3].getZ(), "v3");
		dt.drawPoint3D(v4s[4]);
		dt.drawLabel(v4s[4].getX(), v4s[4].getY(), v4s[4].getZ(), "V4");
		dt.drawPoint3D(v4s[5]);
		dt.drawLabel(v4s[5].getX(), v4s[5].getY(), v4s[5].getZ(), "V5");

       	// Draw projections
    	int lc = p4.findLongestComponent();
       	for (int i = 0; i < v4s.length; i++) {
    		if (lc == 1) {									// X is longest
    			dt.drawLine3D(new Line3D(v4s[i].getX(),v4s[i].getY(),v4s[i].getZ(), 0.0,v4s[i].getY(),v4s[i].getZ(), new PhongMaterial(Color.TURQUOISE )));
    			dt.drawLine3D(new Line3D(0.0, 0.0, 0.0, 					   0.0,v4s[i].getY(),v4s[i].getZ(), new PhongMaterial(Color.TURQUOISE )));
    		}
    		else if (lc == 2) {								// Y is longest
       			dt.drawLine3D(new Line3D(v4s[i].getX(),v4s[i].getY(),v4s[i].getZ(),v4s[i].getX(), 0.0, v4s[i].getZ(), new PhongMaterial(Color.TURQUOISE ) ));
    			dt.drawLine3D(new Line3D(0.0, 0.0, 0.0, 					  v4s[i].getX(), 0.0, v4s[i].getZ(), new PhongMaterial(Color.TURQUOISE ) ));
        		}
    		else if (lc == 3) {								// Z is longest
    			dt.drawLine3D(new Line3D(v4s[i].getX(),v4s[i].getY(),v4s[i].getZ(),v4s[i].getX(),v4s[i].getY(), 0.0, new PhongMaterial(Color.TURQUOISE )));
    			dt.drawLine3D(new Line3D(0.0, 0.0, 0.0, 					  v4s[i].getX(),v4s[i].getY(), 0.0, new PhongMaterial(Color.TURQUOISE ) ));
            }
    	}

		System.out.println("isConvexPoly()= " + p4.isConvexPoly());
//		System.out.println("isPlanar= " + p4.isPlanar());
		System.out.println("isInAxisPlane= " + p4.isInAxisPlane());

		dt.incrSequence();
		dt.drawLabel(12, -6, -8, "Polygon 4T");
		if (p4.isConvexPoly()) {
			t = p4.triangulateConvexPolygon();
		}
		else {
			t = p4.triangulatePolygon();
		}

		t3D = new Triangle3D[t.length];
		System.out.println("t.length= " + t.length);
		for (int i = 0; i < t.length; i++) {
			t3D[i] = new Triangle3D(v4s[t[i][0]], v4s[t[i][1]], v4s[t[i][2]], new PhongMaterial(colors[i]));
			System.out.println("t3D[" + i + "]= " + t3D[i]);
			dt.drawTriangle3D(t3D[i]);
		}

		dt.incrSequence();
		System.out.println("\nPolygon 5");
		Point3D[] v5 = {
				new Point3D (-10.0, -0.75, -5.0),
				new Point3D (-10.0, -5.0,  -2.6),
				new Point3D (-10.0, -5.0,   5.0),
				new Point3D (-10.0,  3.0,   7.0),
				new Point3D (-10.0,  5.0,   5.4),
				new Point3D (-10.0,  6.0,  -4.0) };
		Polygon3D p5 = new Polygon3D(v5, new PhongMaterial(Color.CORNFLOWERBLUE));
		String label5 = String.format("Polygon 5, d=%6.2f, z=%6.2f, vs=%4.1f, set to +1", p5.getD(), p5.getUnitNormal().getZ(), p5.getVolumeSign());
		dt.drawLabel(12, -6, -8, label5);
		Vector3D un5 = Vector3D.multiply(10.0,  p5.getUnitNormal());
		un5.setMaterial(new PhongMaterial(Color.MAGENTA));
		un5.setVectorRadius(1);
		dt.drawVector3D(un5);
		dt.drawPolygon3D(p5);
		Point3D[] v5s = p5.getV();
		dt.setDrawColor(Color.BLACK);
		dt.setPointSize(2);
		dt.drawPoint3D(v5s[0]);
		dt.drawLabel(v5s[0].getX(), v5s[0].getY(), v5s[0].getZ(), "V0");
		dt.drawPoint3D(v5s[1]);
		dt.drawLabel(v5s[1].getX(), v5s[1].getY(), v5s[1].getZ(), "V1");
		dt.drawPoint3D(v5s[2]);
		dt.drawLabel(v5s[2].getX(), v5s[2].getY(), v5s[2].getZ(), "V2");
		dt.drawPoint3D(v5s[3]);
		dt.drawLabel(v5s[3].getX(), v5s[3].getY(), v5s[3].getZ(), "V3");
		dt.drawPoint3D(v5s[4]);
		dt.drawLabel(v5s[4].getX(), v5s[4].getY(), v5s[4].getZ(), "V4");
		dt.drawPoint3D(v5s[5]);
		dt.drawLabel(v5s[5].getX(), v5s[5].getY(), v5s[5].getZ(), "V5");

		System.out.println("isConvexPoly()= " + p5.isConvexPoly());
//		System.out.println("isPlanar= " + p5.isPlanar());
		System.out.println("isInAxisPlane= " + p5.isInAxisPlane());

		dt.incrSequence();
		dt.drawLabel(12, -6, -8, "Polygon 5T");
		if (p5.isConvexPoly()) {
			t = p5.triangulateConvexPolygon();
		}
		else {
			t = p5.triangulatePolygon();
		}

		t3D = new Triangle3D[t.length];
		System.out.println("t.length= " + t.length);
		for (int i = 0; i < t.length; i++) {
			t3D[i] = new Triangle3D(v5s[t[i][0]], v5s[t[i][1]], v5s[t[i][2]], new PhongMaterial(colors[i]));
			System.out.println("t3D[" + i + "]= " + t3D[i]);
			dt.drawTriangle3D(t3D[i]);
		}

		dt.incrSequence();
		System.out.println("\nPolygon 6");
		Point3D[] v6 = {
				new Point3D( -5.00, 10.0, -2.6 ),
				new Point3D( -0.75, 10.0, -5.0 ),
				new Point3D(  6.00, 10.0, -4.0 ),
//				new Point3D(  5.00, 10.0,  5.4 ),
				new Point3D(  2.00, 10.0,  2.4 ),
				new Point3D(  3.00, 10.0,  7.0 ),
				new Point3D( -5.00, 10.0,  5.0 ) };
		Polygon3D p6 = new Polygon3D(v6, new PhongMaterial(Color.CORNFLOWERBLUE));
		String label6 = String.format("Polygon 6, d=%6.2f, z=%6.2f, vs=%4.1f, set to -1", p6.getD(), p6.getUnitNormal().getZ(), p6.getVolumeSign());
		dt.drawLabel(12, -6, -8,label6);
		Vector3D un6 = Vector3D.multiply(10.0,  p6.getUnitNormal());
		un6.setMaterial(new PhongMaterial(Color.MAGENTA));
		un6.setVectorRadius(1);
		dt.drawVector3D(un6);
		dt.drawPolygon3D(p6);
		Point3D[] v6s = p6.getV();
		dt.setDrawColor(Color.BLACK);
		dt.setPointSize(2);
		dt.drawPoint3D(v6s[0]);
		dt.drawLabel(v6s[0].getX(), v6s[0].getY(), v6s[0].getZ(), "V0");
		dt.drawPoint3D(v6s[1]);
		dt.drawLabel(v6s[1].getX(), v6s[1].getY(), v6s[1].getZ(), "V1");
		dt.drawPoint3D(v6s[2]);
		dt.drawLabel(v6s[2].getX(), v6s[2].getY(), v6s[2].getZ(), "V2");
		dt.drawPoint3D(v6s[3]);
		dt.drawLabel(v6s[3].getX(), v6s[3].getY(), v6s[3].getZ(), "v3");
		dt.drawPoint3D(v6s[4]);
		dt.drawLabel(v6s[4].getX(), v6s[4].getY(), v6s[4].getZ(), "v4");
		dt.drawPoint3D(v6s[5]);
		dt.drawLabel(v6s[5].getX(), v6s[5].getY(), v6s[5].getZ(), "V5");

		System.out.println("isConvexPoly()= " + p6.isConvexPoly());
//		System.out.println("isPlanar= " + p6.isPlanar());
		System.out.println("isInAxisPlane= " + p6.isInAxisPlane());

		dt.incrSequence();
		dt.drawLabel(12, -6, -8, "Polygon 6T");
		if (p6.isConvexPoly()) {
			t = p6.triangulateConvexPolygon();
		}
		else {
			t = p6.triangulatePolygon();
		}

		t3D = new Triangle3D[t.length];
		System.out.println("t.length= " + t.length);
		for (int i = 0; i < t.length; i++) {
			t3D[i] = new Triangle3D(v6s[t[i][0]], v6s[t[i][1]], v6s[t[i][2]], new PhongMaterial(colors[i]));
			System.out.println("t3D[" + i + "]= " + t3D[i]);
			dt.drawTriangle3D(t3D[i]);
		}


		dt.incrSequence();
		System.out.println("\nPolygon 7");
		Point3D[] v7 = {
				new Point3D( -5.00, -10.0, -2.6 ),
				new Point3D( -0.75, -10.0, -5.0 ),
				new Point3D(  6.00, -10.0, -4.0 ),
//				new Point3D(  5.00, -10.0,  5.4 ),
				new Point3D(  2.00, -10.0,  2.4 ),
				new Point3D(  3.00, -10.0,  7.0 ),
				new Point3D( -5.00, -10.0,  5.0 ) };
		Polygon3D p7 = new Polygon3D(v7, new PhongMaterial(Color.CORNFLOWERBLUE));
		String label7 = String.format("Polygon 7, d=%6.2f, z=%6.2f, vs=%4.1f, set to +1", p7.getD(), p7.getUnitNormal().getZ(), p7.getVolumeSign());
		dt.drawLabel(12, -6, -8, label7);
		Vector3D un7 = Vector3D.multiply(10.0,  p7.getUnitNormal());
		un7.setMaterial(new PhongMaterial(Color.MAGENTA));
		un7.setVectorRadius(1);
		dt.drawVector3D(un7);
		dt.drawPolygon3D(p7);
		Point3D[] v7s = p7.getV();
		dt.setDrawColor(Color.BLACK);
		dt.setPointSize(2);
		dt.drawPoint3D(v7s[0]);
		dt.drawLabel(v7s[0].getX(), v7s[0].getY(), v7s[0].getZ(), "V0");
		dt.drawPoint3D(v7s[1]);
		dt.drawLabel(v7s[1].getX(), v7s[1].getY(), v7s[1].getZ(), "V1");
		dt.drawPoint3D(v7s[2]);
		dt.drawLabel(v7s[2].getX(), v7s[2].getY(), v7s[2].getZ(), "V2");
		dt.drawPoint3D(v7s[3]);
		dt.drawLabel(v7s[3].getX(), v7s[3].getY(), v7s[3].getZ(), "V3");
		dt.drawPoint3D(v7s[4]);
		dt.drawLabel(v7s[4].getX(), v7s[4].getY(), v7s[4].getZ(), "V4");
		dt.drawPoint3D(v7s[5]);
		dt.drawLabel(v7s[5].getX(), v7s[5].getY(), v7s[5].getZ(), "V5");

		System.out.println("isConvexPoly()= " + p7.isConvexPoly());
//		System.out.println("isPlanar= " + p7.isPlanar());
		System.out.println("isInAxisPlane= " + p7.isInAxisPlane());

		dt.incrSequence();
		dt.drawLabel(12, -6, -8, "Polygon 7T");
		if (p7.isConvexPoly()) {
			t = p7.triangulateConvexPolygon();
		}
		else {
			t = p7.triangulatePolygon();
		}

		t3D = new Triangle3D[t.length];
		System.out.println("t.length= " + t.length);
		for (int i = 0; i < t.length; i++) {
			t3D[i] = new Triangle3D(v7s[t[i][0]], v7s[t[i][1]], v7s[t[i][2]], new PhongMaterial(colors[i]));
			System.out.println("t3D[" + i + "]= " + t3D[i]);
			dt.drawTriangle3D(t3D[i]);
		}

		dt.incrSequence();
		System.out.println("\nPolygon 8");
		Point3D[] v8 = {
				new Point3D( -5.00,  -2.6, 10.0),
				new Point3D( -0.75,  -5.0, 10.0),
				new Point3D(  6.00,  -4.0, 10.0),
//				new Point3D(  5.00,   5.4, 10.0),
				new Point3D(  2.00,   2.4, 10.0),
				new Point3D(  3.00,   7.0, 10.0),
				new Point3D( -5.00,   5.0, 10.0) };
		Polygon3D p8 = new Polygon3D(v8, new PhongMaterial(Color.CORNFLOWERBLUE));
		String label8 = String.format("Polygon 8, d=%6.2f, z=%6.2f, vs=%4.1f, not reset", p8.getD(), p8.getUnitNormal().getZ(), p8.getVolumeSign());
		dt.drawLabel(12, -6, -8, label8);
		Vector3D un8 = Vector3D.multiply(10.0,  p8.getUnitNormal());
		un8.setMaterial(new PhongMaterial(Color.MAGENTA));
		un8.setVectorRadius(1);
		dt.drawVector3D(un8);
		dt.drawPolygon3D(p8);
		Point3D[] v8s = p8.getV();
		dt.setDrawColor(Color.BLACK);
		dt.setPointSize(2);
		dt.drawPoint3D(v8s[0]);
		dt.drawLabel(v8s[0].getX(), v8s[0].getY(), v8s[0].getZ(), "V0");
		dt.drawPoint3D(v8s[1]);
		dt.drawLabel(v8s[1].getX(), v8s[1].getY(), v8s[1].getZ(), "V1");
		dt.drawPoint3D(v8s[2]);
		dt.drawLabel(v8s[2].getX(), v8s[2].getY(), v8s[2].getZ(), "V2");
		dt.drawPoint3D(v8s[3]);
		dt.drawLabel(v8s[3].getX(), v8s[3].getY(), v8s[3].getZ(), "v3");
		dt.drawPoint3D(v8s[4]);
		dt.drawLabel(v8s[4].getX(), v8s[4].getY(), v8s[4].getZ(), "v4");
		dt.drawPoint3D(v8s[5]);
		dt.drawLabel(v8s[5].getX(), v8s[5].getY(), v8s[5].getZ(), "V5");

		System.out.println("isConvexPoly()= " + p8.isConvexPoly());
//		System.out.println("isPlanar= " + p8.isPlanar());
		System.out.println("isInAxisPlane= " + p8.isInAxisPlane());

		dt.incrSequence();
		dt.drawLabel(12, -6, -8, "Polygon 8T");
		if (p8.isConvexPoly()) {
			t = p8.triangulateConvexPolygon();
		}
		else {
			t = p8.triangulatePolygon();
		}

		t3D = new Triangle3D[t.length];
		System.out.println("t.length= " + t.length);
		for (int i = 0; i < t.length; i++) {
			t3D[i] = new Triangle3D(v8s[t[i][0]], v8s[t[i][1]], v8s[t[i][2]], new PhongMaterial(colors[i]));
			System.out.println("t3D[" + i + "]= " + t3D[i]);
			dt.drawTriangle3D(t3D[i]);
		}

		dt.incrSequence();
		System.out.println("\nPolygon 9");
		Point3D[] v9 = {
				new Point3D( -5.00,  -2.6, -10.0),
				new Point3D( -0.75,  -5.0, -10.0),
				new Point3D(  6.00,  -4.0, -10.0),
//				new Point3D(  5.00,   5.4, -10.0),
				new Point3D(  2.00,   2.4, -10.0),
				new Point3D(  3.00,   7.0, -10.0),
				new Point3D( -5.00,   5.0, -10.0) };
		Polygon3D p9 = new Polygon3D(v9, new PhongMaterial(Color.CORNFLOWERBLUE));
		String label9 = String.format("Polygon 9, d=%6.2f, z=%6.2f, vs=%4.1f, not reset", p9.getD(), p9.getUnitNormal().getZ(), p9.getVolumeSign());
		dt.drawLabel(12, -6, -8, label9);
		Vector3D un9 = Vector3D.multiply(10.0,  p9.getUnitNormal());
		un9.setMaterial(new PhongMaterial(Color.MAGENTA));
		un9.setVectorRadius(1);
		dt.drawVector3D(un9);
		dt.drawPolygon3D(p9);
		Point3D[] v9s = p9.getV();
		dt.setDrawColor(Color.BLACK);
		dt.setPointSize(2);
		dt.drawPoint3D(v9s[0]);
		dt.drawLabel(v9s[0].getX(), v9s[0].getY(), v9s[0].getZ(), "V0");
		dt.drawPoint3D(v9s[1]);
		dt.drawLabel(v9s[1].getX(), v9s[1].getY(), v9s[1].getZ(), "V1");
		dt.drawPoint3D(v9s[2]);
		dt.drawLabel(v9s[2].getX(), v9s[2].getY(), v9s[2].getZ(), "V2");
		dt.drawPoint3D(v9s[3]);
		dt.drawLabel(v9s[3].getX(), v9s[3].getY(), v9s[3].getZ(), "V3");
		dt.drawPoint3D(v9s[4]);
		dt.drawLabel(v9s[4].getX(), v9s[4].getY(), v9s[4].getZ(), "V4");
		dt.drawPoint3D(v9s[5]);
		dt.drawLabel(v9s[5].getX(), v9s[5].getY(), v9s[5].getZ(), "V5");

		System.out.println("isConvexPoly()= " + p9.isConvexPoly());
//		System.out.println("isPlanar= " + p9.isPlanar());
		System.out.println("isInAxisPlane= " + p9.isInAxisPlane());

		dt.incrSequence();
		dt.drawLabel(12, -6, -8, "Polygon 9T");
		if (p9.isConvexPoly()) {
			t = p9.triangulateConvexPolygon();
		}
		else {
			t = p9.triangulatePolygon();
		}

		t3D = new Triangle3D[t.length];
		System.out.println("t.length= " + t.length);
		for (int i = 0; i < t.length; i++) {
			t3D[i] = new Triangle3D(v9s[t[i][0]], v9s[t[i][1]], v9s[t[i][2]], new PhongMaterial(colors[i]));
			System.out.println("t3D[" + i + "]= " + t3D[i]);
			dt.drawTriangle3D(t3D[i]);
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}