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
* @version 1.00 July 2015
* 
*/
public class TestPolygon1 extends Application {
	private Draw3D dt;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestPolygon");
		primaryStage.show();
	}
	
	private void buildData() {
	    
		dt.setCumulate(false);
//		dt.setAmbientLight(true);
//		dt.setPointLight(false);
		
		dt.setShowBoundaryCube(true);
		
		Point3D[] p = { new Point3D( -5.0,  -2.6, 0),
						new Point3D(  -0.75,  -5.0, 0),
						new Point3D(  6.0,  -4.0, 0),
						new Point3D(  5.0,  5.4, 0),
						new Point3D(  2.0, 2.4, 0),
//						new Point3D(  3, 7.0, 0),
						new Point3D(  -5.0,  5.0, 0) };

		Polygon3D p3D = new Polygon3D(p, new PhongMaterial(Color.ANTIQUEWHITE));
		Vector3D un = Vector3D.multiply(5.0, p3D.getUnitNormal());
		un.setMaterial(new PhongMaterial(Color.MAGENTA));
		un.setVectorRadius(1);
		dt.drawVector3D(un);
		System.out.println("isConvexPoly()= " + p3D.isConvexPoly());
		p = p3D.getV();
		dt.setDrawColor(Color.BLACK);
		dt.setPointSize(2);
		dt.drawPoint3D(p[0]);
		dt.drawLabel(p[0].getX(), p[0].getY(), p[0].getZ(), "V0");
		dt.drawArrow3D(new Arrow3D(p[0], p[1], new PhongMaterial(Color.RED)));
		dt.drawPoint3D(p[1]);
		dt.drawLabel(p[1].getX(), p[1].getY(), p[1].getZ(), "V1");
		dt.drawArrow3D(new Arrow3D(p[1], p[2], new PhongMaterial(Color.BLACK)));
		dt.drawPoint3D(p[2]);
		dt.drawLabel(p[2].getX(), p[2].getY(), p[2].getZ(), "V2");
		dt.drawArrow3D(new Arrow3D(p[2], p[3], new PhongMaterial(Color.BLACK)));
		dt.drawPoint3D(p[3]);
		dt.drawLabel(p[3].getX(), p[3].getY(), p[3].getZ(), "V3");
		dt.drawArrow3D(new Arrow3D(p[3], p[4], new PhongMaterial(Color.BLACK)));
		dt.drawPoint3D(p[4]);
		dt.drawLabel(p[4].getX(), p[4].getY(), p[4].getZ(), "V4");
		dt.drawArrow3D(new Arrow3D(p[4], p[5], new PhongMaterial(Color.BLACK)));
		dt.drawPoint3D(p[5]);
		dt.drawLabel(p[5].getX(), p[5].getY(), p[5].getZ(), "V5");
		dt.drawArrow3D(new Arrow3D(p[5], p[0], new PhongMaterial(Color.BLACK)));
		
		dt.incrSequence();
		dt.drawPolygon3D(p3D);
		
		dt.incrSequence();
		Polygon3D pz3D = new Polygon3D(p, new PhongMaterial(Color.ANTIQUEWHITE));
		pz3D.setZ(4,  5,  6,  7);
		Vector3D unz = Vector3D.multiply(10.0, pz3D.getUnitNormal());
		unz.setMaterial(new PhongMaterial(Color.MAGENTA));
		unz.setVectorRadius(1);
		dt.drawVector3D(unz);
		Point3D[] pz = pz3D.getV();
		dt.setDrawColor(Color.BLACK);
		dt.setPointSize(4);
		dt.drawPoint3D(pz[0]);
		dt.drawLabel(pz[0].getX(), pz[0].getY(), pz[0].getZ(), "V0");
		dt.drawArrow3D(new Arrow3D(pz[0], pz[1], new PhongMaterial(Color.RED)));
		dt.drawPoint3D(pz[1]);
		dt.drawLabel(pz[1].getX(), pz[1].getY(), pz[1].getZ(), "V1");
		dt.drawArrow3D(new Arrow3D(pz[1], pz[2], new PhongMaterial(Color.BLACK)));
		dt.drawPoint3D(pz[2]);
		dt.drawLabel(pz[2].getX(), pz[2].getY(), pz[2].getZ(), "V2");
		dt.drawArrow3D(new Arrow3D(pz[2], pz[3], new PhongMaterial(Color.BLACK)));
		dt.drawPoint3D(pz[3]);
		dt.drawLabel(pz[3].getX(), pz[3].getY(), pz[3].getZ(), "V3");
		dt.drawArrow3D(new Arrow3D(pz[3], pz[4], new PhongMaterial(Color.BLACK)));
		dt.drawPoint3D(pz[4]);
		dt.drawLabel(pz[4].getX(), pz[4].getY(), pz[4].getZ(), "V4");
		dt.drawArrow3D(new Arrow3D(pz[4], pz[5], new PhongMaterial(Color.BLACK)));
		dt.drawPoint3D(pz[5]);
		dt.drawLabel(pz[5].getX(), pz[5].getY(), pz[5].getZ(), "V5");
		dt.drawArrow3D(new Arrow3D(pz[5], pz[0], new PhongMaterial(Color.BLACK)));
		
		dt.incrSequence();
		dt.drawPolygon3D(pz3D);
		
       	// Draw projections
    	int lc = pz3D.findLongestComponent();
       	for (int i = 0; i < pz.length; i++) {
    		if (lc == 1) {									// X is longest
    			dt.drawLine3D(new Line3D(pz[i].getX(),pz[i].getY(),pz[i].getZ(), 0.0,pz[i].getY(),pz[i].getZ(), new PhongMaterial(Color.TURQUOISE )));
    			dt.drawLine3D(new Line3D(0.0, 0.0, 0.0, 					   0.0,pz[i].getY(),pz[i].getZ(), new PhongMaterial(Color.TURQUOISE )));
    		}
    		else if (lc == 2) {								// Y is longest
       			dt.drawLine3D(new Line3D(pz[i].getX(),pz[i].getY(),pz[i].getZ(),pz[i].getX(), 0.0, pz[i].getZ(), new PhongMaterial(Color.TURQUOISE ) ));
    			dt.drawLine3D(new Line3D(0.0, 0.0, 0.0, 					  pz[i].getX(), 0.0, pz[i].getZ(), new PhongMaterial(Color.TURQUOISE ) ));
        		}
    		else if (lc == 3) {								// Z is longest
    			dt.drawLine3D(new Line3D(pz[i].getX(),pz[i].getY(),pz[i].getZ(),pz[i].getX(),pz[i].getY(), 0.0, new PhongMaterial(Color.TURQUOISE )));
    			dt.drawLine3D(new Line3D(0.0, 0.0, 0.0, 					  pz[i].getX(),pz[i].getY(), 0.0, new PhongMaterial(Color.TURQUOISE ) ));
            }
    	}
	
		dt.incrSequence();		
		Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.MAGENTA, Color.SALMON };
		
		int[][] ti;
		
		if (pz3D.isConvexPoly()) {
			ti = pz3D.triangulateConvexPolygon();
		}
		else {
			ti = pz3D.triangulatePolygon();
		}	
		Triangle3D[] tr = pz3D.findPolygonTriangles(ti);
		for (int i = 0; i < tr.length; i++) {
			Point3D[] v = tr[i].getV();
			dt.setDrawColor(colors[i]);
			dt.drawArrow3D(new Arrow3D(v[0], v[1]));
			dt.drawArrow3D(new Arrow3D(v[1], v[2]));
			dt.drawArrow3D(new Arrow3D(v[2], v[0]));
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}