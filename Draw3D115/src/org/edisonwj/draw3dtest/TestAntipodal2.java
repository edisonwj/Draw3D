package org.edisonwj.draw3dtest;

import java.util.ArrayList;

import org.edisonwj.draw3d.Draw3D;
import org.edisonwj.draw3d.Draw3DDefaults;
import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
*
* @author William Edison
* @version 1.01 August 2015
* 
*/
public class TestAntipodal2 extends Application implements Draw3DDefaults{
	private Draw3D dt;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestAntipodal");
		primaryStage.show();
	}
	
	private void buildData() {
	    
		dt.setCumulate(true);
		dt.setYaxisUp(true);
		dt.setShowBoundaryCube(false);
		dt.setShowAxes(false);
		dt.setCamera(0.0, -180.0,  -600.0);
		dt.setOriginView(-100, -100, -100);
		
		Point3D[] p = { new Point3D( 6.0, 0.0, 0),	// p0
				new Point3D( 6.0, 2.0, 0),	// p1
				new Point3D( 4.8, 6.0, 0),	// p2
				new Point3D( 4.0, 6.0, 0),	// p3
				new Point3D( 3.0, 5.5, 0),	// p4
				new Point3D( 0.6, 3.0, 0),	// p5
				new Point3D( 0.0, 2.2, 0),	// p6
				new Point3D( 0.0, 1.4, 0),	// p7
				new Point3D( 4.2, 0.0, 0)	// p8
				};
		
		dt.setDrawColor(Color.ANTIQUEWHITE);
		dt.drawPolygon(p);
		
		dt.setDrawColor(Color.BLACK);
		dt.setPointSize(2);
		dt.setLineRadius(.5);
		dt.setLabelFontSize(10);
		
		for (int i = 0; i < p.length; i++) {
			dt.drawPoint(p[i].getX(), p[i].getY(), p[i].getZ());
			dt.drawLabel(p[i].getX(), p[i].getY(), p[i].getZ(), ("P" + i));
			dt.drawLine(p[i].getX(), p[i].getY(), p[i].getZ(),
					p[next(p.length, i)].getX(), p[next(p.length, i)].getY(), p[next(p.length, i)].getZ());
		}
		
		// Find antipodal pairs
		ArrayList<Pair> ap = antipodalPairs(p);
	}
	
	/**
	 * Method for finding antipodal pairs of the hull.
	 * Adopted from algorithm in Preparata and Shamos, p. 180.
	 * See also Pirzadeh, p. 12.
	 */
	private ArrayList<Pair> antipodalPairs(Point3D[] h)
	{
		// Assumes input hull points are in
		// array h ordered counterclockwise.

		// Returns ArrayList of antipodal pairs w/ distances

		ArrayList<Pair> al = new ArrayList<>();
		Pair pr;
		int p, p0, pn, q, q0;
		int n = h.length-1;

		p0 = 0;
		pn = n;

		p = pn;
		q = next(h.length, p);

		// Traverse hull counterclockwise to find point, q0,
		// farthest from pn-p0.

		while ( area2(h, p, next(h.length, p), next(h.length, q)) -
				area2(h, p, next(h.length, p), q) > EPSILON )
			q = next(h.length, q);
		
		q0 = q;

		// Move p counterclockwise from pn to q0
		// and q from q0 to pn, each change creating
		// an anitpodal pair.

		int count = 0;
		scan: while (q != p0)
		{
			System.out.println("\nscan count = " + count++ + ", p= " + p + ", q= " + q + ", p0= " + p0);
			p = next(h.length, p);
			pr = new Pair(h[p], h[q]);
			al.add(pr);
			System.out.println("Stage 0 new pair: p= " + p + ", q= " + q + ", d= " + pr.getDist());
			dt.incrSequence();
			dt.setDrawColor(Color.BLACK);
			dt.drawLine(pr.getp1().getX(), pr.getp1().getY(), pr.getp1().getZ(),
						pr.getp2().getX(), pr.getp2().getY(), pr.getp2().getZ());

			while ( area2(h, p, next(h.length, p), next(h.length, q)) -
					area2(h, p, next(h.length, p), q) > EPSILON )
			{
				q = next(h.length, q);
				if ( !(p == q0 && q == p0) ) {
					pr = new Pair(h[p], h[q]);
					al.add(pr);
					System.out.println("Stage 1 new pair: p= " + p + ", q= " + q + ", d= " + pr.getDist());
					dt.incrSequence();
					dt.setDrawColor(Color.BLUE);
					dt.drawLine(pr.getp1().getX(), pr.getp1().getY(), pr.getp1().getZ(),
								pr.getp2().getX(), pr.getp2().getY(), pr.getp2().getZ());
				}
				else {
					break scan;
				}
			}

			// Handle parallel lines
			if ( Math.abs( area2(h, p, next(h.length, p), next(h.length, q)) -
			   	   		   area2(h, p, next(h.length, p), q) ) < EPSILON ) {
				System.out.println("Parallel lines");

				if ( !(p == q0 && q == pn) ) {
					pr = new Pair(h[p], h[next(h.length, q)]);
					al.add(pr);
					System.out.println("Stage 2 new pair: p= " + p + ", next(q)= " + next(h.length, q) + ", d= " + pr.getDist());
					dt.incrSequence();
					dt.setDrawColor(Color.RED);
					dt.drawLine(pr.getp1().getX(), pr.getp1().getY(), pr.getp1().getZ(),
								pr.getp2().getX(), pr.getp2().getY(), pr.getp2().getZ());
				}
			}
		}
		return al;
	}
	
	private double area2(Point3D[] h, int ai, int bi, int ci) {
		Point3D a = h[ai];
		Point3D b = h[bi];
		Point3D c = h[ci];
		double a2 = (a.getX() - c.getX()) * (b.getY() - c.getY()) - (a.getY() - c.getY()) * (b.getX() - c.getX());
		System.out.println("area2: ai= " + ai + ", bi= " + bi + ", ci= " + ci + ", area2= " + a2);
		return a2;
	}
	
	/**
	 * Return next index cycling back to zero after n-1.
	 */
	private int next(int length, int i)
	{
		return i == (length-1) ? 0 : i + 1;
	}
	
	/**
	 * Inner class defining antipodal pairs.
	 */
	private class Pair
	{
		Point3D p1;
		Point3D p2;
		double d;

		Pair(Point3D a, Point3D b)
		{
			p1 = a;
			p2 = b;
			d = p1.distance(p2);
		}

		public Point3D getp1()
		{
			return p1;
		}

		public Point3D getp2()
		{
			return p2;
		}

		public double getDist()
		{
			return d;
		}

		public String toString()
		{
			return ("" + p1 + " : " + p2 + " Distance= " + d);
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}