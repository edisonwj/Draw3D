package org.edisonwj.draw3dtest;

import java.util.ArrayList;

import org.edisonwj.draw3d.Draw3D;
import org.edisonwj.draw3d.Draw3DDefaults;
import org.edisonwj.draw3d.Line3D;
import org.edisonwj.draw3d.Text3D;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
* Test of Preparata-Shamos algorithm for finding antipodal pairs.
* 
* @author William Edison
* @version 1.01 August 2015
* 
*/
public class TestAntipodal7 extends Application implements Draw3DDefaults{
	private Draw3D dt;
	private int endCompare = 0;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestAntipodal7");
		primaryStage.show();
	}
	
	private void buildData() {
	
		// Hull from 500 point sample
		
		dt.setCumulate(true);
		dt.setYaxisUp(true);
		dt.setShowAxes(false);
		dt.setXYZRange(1, 1, 1);
		dt.setCamera(0.0,  -180.0,  -800.0);
		dt.setOriginView(-100, -100, -100);
		
		Point3D[] p = {
						new Point3D(0.31151209320477774, 2.5108782586226604E-4, 0),
						new Point3D(0.5366162129382679, 0.001214743126749407, 0),
						new Point3D(0.990802229843476, 0.014436774428205925, 0),
						new Point3D(0.9945162548658049, 0.2941386272637819, 0),
						new Point3D(0.9942971267710892, 0.7166063672474615, 0),
						new Point3D(0.9888923126221133, 0.8208225820310519, 0),
						new Point3D(0.9444381221870138, 0.9725960893428867, 0),
						new Point3D(0.9384096935104624, 0.9743154435298943, 0),
						new Point3D(0.752770616557808, 0.9904318619474917, 0),
						new Point3D(0.37113373557624113, 0.996550000736746, 0),
						new Point3D(0.0489320662100483, 0.9997680252416842, 0),
						new Point3D(0.034989845955274, 0.9948541070310651, 0),
						new Point3D(0.01437643031327819, 0.9646676522515097, 0),
						new Point3D(0.005964929706400693, 0.9331218576678643, 0),
						new Point3D(0.002965765540937784, 0.1604684266077673, 0),
						new Point3D(0.008846934888906281, 0.05039402239508649, 0),
						new Point3D(0.12270311737558949, 0.0021788370805693965, 0),
						new Point3D(0.26494169480397445, 5.498826506314253E-4, 0)
					};
		
 //  		System.out.println("\nHull Points");
		dt.setPointSize(2);
		for (int i = 0; i < p.length; i++) {
			dt.setDrawColor(Color.RED);
			dt.drawPoint3D(p[i]);
			dt.setDrawColor(Color.BLACK);
			dt.drawLabel3D(new Text3D(p[i],180,0,0,(""+i)));
//			System.out.println("i= " + i + ", " + p[i].toString());
		}
		
		// Compute all distances
		double maxdist = 0;
		double dist = 0;
		int maxi = 0;
		int maxj = 0;
//		System.out.println("\nHull Distances");
		for (int i=0; i<p.length-1; i++) {
			for (int j=i+1; j<p.length; j++) {
				dist = p[i].distance(p[j]);
				if (dist > maxdist) {
					maxdist = dist;
					maxi = i;
					maxj= j;
				}
//				System.out.println( "Distance: p[" + i + "]= " + p[i].toString() +
//											", p[" + j + "]= " + p[j].toString() +
//											", d= " + dist);	
			}
		}
	    System.out.println( "Hull Largest i= " + maxi + ", j= " + maxj + 
				"\n  (Xi= " + p[maxi].getX() + ", Yi= " + p[maxi].getY() +
				"),\n  (Xj= " + p[maxj].getX() + ", Yj= " + p[maxj].getY() +
				"),\n  Hull largestDistance= " + maxdist);
//	    dt.setDrawColor(Color.RED);
//	    dt.drawLine3D(new Line3D(p[maxi], p[maxj]));
	    
//	    System.out.println( "Antipodal Largest i= " + 2 + ", j= " + 10 + 
//				"\n  (Xi= " + p[2].getX() + ", Yi= " + p[2].getY() +
//				"),\n  (Xj= " + p[10].getX() + ", Yj= " + p[10].getY() +
//				"),\n  Antipodal largestDistance= " + p[2].distance(p[10]));
//	    dt.setDrawColor(Color.GREEN);
//	    dt.drawLine3D(new Line3D(p[2], p[10]));
	    
//	    System.out.println("\ncompute area : " +
//	    		"\n  p[17]= " + p[17] +
//	    		"\n  p[0] = " + p[0]  +
//	    		"\n  p[2] = " + p[2]);
//	    System.out.println(" area2 = " + area2(p, 17, 0, 2));
	    
//	    System.out.println("\ncompute area : " +
//	    		"\n  p[17]= " + p[17] +
//	    		"\n  p[0] = " + p[0]  +
//	    		"\n  p[3] = " + p[3]);
//	    System.out.println(" area2 = " + area2(p, 17, 0, 3));
	    
//	    System.out.println("\ncompute area : " +
//	    		"\n  p[17]= " + p[17] +
//	    		"\n  p[0] = " + p[0]  +
//	    		"\n  p[4] = " + p[4]);
//	    System.out.println(" area2 = " + area2(p, 17, 0, 4));
		
		// Find antipodal pairs
	    System.out.println("\nFind antipodal pairs\n");
		System.out.println("\nMax AntiPodal Distance= " + antipodalPairs(p));
	}
	
	/**
	 * Method for finding antipodal pairs of the hull.
	 * Adopted from algorithm in Preparata and Shamos, p. 180.
	 * See also Pirzadeh, p. 12.
	 */
	private double antipodalPairs(Point3D[] h)
	{
		// Assumes input hull points are in
		// array h ordered counterclockwise.

		// Returns ArrayList of antipodal pairs w/ distances

		ArrayList<Pair> al = new ArrayList<>();
		Pair pr;
		int p, p0, pn, q, q0;
		int n = h.length-1;
		double maxDist = 0.0;
		int maxp = 0;
		int maxq = 0;

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

		scan: while (q != p0)
		{
//			System.out.println("\nOuter while p= " + p + ", q= " + q + ", p0= " + p0);
			p = next(h.length, p);
			pr = new Pair(h[p], h[q]);
			al.add(pr);
			if (pr.getDist() > maxDist) {
				maxp = p;
				maxq = q;
				maxDist = pr.getDist();
			}
//			System.out.println("NEW PAIR Outer while: p= " + p + ", q= " + q + ", d= " + pr.getDist() +
//								", pr.p1()= " + pr.getp1() + ", pr.getp2()= " + pr.getp2());
			System.out.println("NEW PAIR Outer while: p= " + p + ", q= " + q + ", d= " + pr.getDist());
			dt.incrSequence();
			dt.setDrawColor(Color.BLACK);
			dt.drawLine(pr.getp1().getX(), pr.getp1().getY(), pr.getp1().getZ(),
						pr.getp2().getX(), pr.getp2().getY(), pr.getp2().getZ());
			
			System.out.println("Check for inner while");
			while ( area2(h, p, next(h.length, p), next(h.length, q)) -
					area2(h, p, next(h.length, p), q) > EPSILON )
			{
				System.out.println("Inner while");
				q = next(h.length, q);
				if ( !(p == q0 && q == p0) ) {
					pr = new Pair(h[p], h[q]);
					al.add(pr);
					if (pr.getDist() > maxDist) {
						maxp = p;
						maxq = q;
						maxDist = pr.getDist();
					}
					System.out.println("NEW PAIR Inner while: p= " + p + ", q= " + q + ", d= " + pr.getDist());
					dt.incrSequence();
					dt.setDrawColor(Color.BLUE);
					dt.drawLine(pr.getp1().getX(), pr.getp1().getY(), pr.getp1().getZ(),
								pr.getp2().getX(), pr.getp2().getY(), pr.getp2().getZ());
				}
				else {
					System.out.println("Break from inner while");
					break scan;
				}
			}

			// Handle parallel lines
			System.out.println("Check for parallel lines");
			if ( Math.abs( area2(h, p, next(h.length, p), next(h.length, q)) -
			   	   		   area2(h, p, next(h.length, p), q) ) < EPSILON ) {
				System.out.println("Parallel lines");

				if ( !(p == q0 && q == pn) ) {
					pr = new Pair(h[p], h[next(h.length, q)]);
					al.add(pr);
					if (pr.getDist() > maxDist) {
						maxp = p;
						maxq = next(h.length, q);
						maxDist = pr.getDist();
					}
					System.out.println("NEW PAIR Parallel lines: p= " + p + ", next(q)= " + next(h.length, q) + ", d= " + pr.getDist());
					dt.incrSequence();
					dt.setDrawColor(Color.RED);
					dt.drawLine(pr.getp1().getX(), pr.getp1().getY(), pr.getp1().getZ(),
								pr.getp2().getX(), pr.getp2().getY(), pr.getp2().getZ());
				}
			}
		}
//		System.out.println("end of search");
		System.out.println("maxPair: p= " + maxp + ", q= " + maxq + ", dist= " + maxDist);
		return maxDist;
	}
	
	private double area2(Point3D[] h, int ai, int bi, int ci) {
		Point3D a = h[ai];
		Point3D b = h[bi];
		Point3D c = h[ci];
		double a2 = (a.getX() - c.getX()) * (b.getY() - c.getY()) - (a.getY() - c.getY()) * (b.getX() - c.getX());
		System.out.println("area2: ai= " + ai + ", bi= " + bi + ", ci= " + ci + ", area2= " + a2);
		if (endCompare==1) System.out.println(" ");
		endCompare = (endCompare+1)%2;
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