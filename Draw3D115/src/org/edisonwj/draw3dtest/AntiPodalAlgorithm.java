package org.edisonwj.draw3dtest;

public class AntiPodalAlgorithm {
	// Author: William Edison
	// Version: 1.01 August 2015

	// Value used for comparing computed quantities to account
	// for possible round-off errors
	static final double EPSILON = .00000001;

	public double findLargestDistance(Pointd[] points) {

	    // 1. Compute the convex hull:
	    Hull hull = new Hull (points);

	    // 2. Extract the hull points:
	    Pointd[] hullPoints = hull.getPoints();

	    // 3. Process the hull points to find antipodal pairs and
	    //    maximum pair distance:
	    return findAntiPodalPairs(hullPoints);
	}

	// Method for finding antipodal pairs of a convex hull
	// and the distance between the pair of points farthest apart.
	// Adapted from algorithm in Preparata and Shamos, p. 180.

	private double findAntiPodalPairs(Pointd[] hullPoints) {
		double dist = 0.0;			// Distance between antipodal points
		double maxDist = 0.0;		// Max. distance between antipodal points

		int p0 = 0;						// Array index of first hull vertex.
		int pn = hullPoints.length-1;	// Array index of last hull vertex.
		int p = pn;						// Successive index of first vertex
										// of curent edge (p, next(p)).
		int q = next(hullPoints, p);	// Successive index of vertex
										// farthest from the current edge
										// (p,next(p)).
		int q0;

		// 1. Traverse hull counterclockwise moving q to find point q0,
		//    the first counterclockwise point farthest from edge (n-1,0).
		//    At each iteration, the areas of the triangles:
		//		hullPoints[p], hullPoints[next(p)], hullPoints[next(q)]
		//		hullPoints[p], hullPoints[next(p)], hullPoints[q]
		//    are compared. If the area for next(q) is larger, q is advanced,
		//    since next(q) must be farther to left, and thus farther away.

		while ( Geometry.twice_area(hullPoints[p], hullPoints[next(hullPoints, p)], hullPoints[next(hullPoints, q)]) -
				Geometry.twice_area(hullPoints[p], hullPoints[next(hullPoints, p)], hullPoints[q]) > EPSILON ) {
			q = next(hullPoints, q);
		}
		q0 = q;						// Mark initial antipodal point as q0

		// 2. Move p counterclockwise from pn to q0 and
		//    q from q0 to pn, each change possibly
		//    creating an anitpodal pair. The loop terminates
		//    when q = p0 = 0, or for final parallel edge, q = pn;

		scan: while (q != p0) {
			p = next(hullPoints, p);			// Move p to next
												// counterclockwise vertex.
			dist = distance(hullPoints[p], hullPoints[q]);
												// Compute distances for
												// antipodal pair p & q.
			if (dist > maxDist) {				// Check/set max. distance.
				maxDist = dist;
			}

			// 3. For each p, traverse hull counterclockwise to find farthest
			//    point q, the vertex farthest from current edge, p-next(p).
			//    At each iteration, the areas of the triangles:
			//		hullPoints[p], hullPoints[next(p)], hullPoints[next(q)]
			//		hullPoints[p], hullPoints[next(p)], hullPoints[q]
			//    are compared.

			while ( Geometry.twice_area(hullPoints[p], hullPoints[next(hullPoints, p)], hullPoints[next(hullPoints, q)]) -
					Geometry.twice_area(hullPoints[p], hullPoints[next(hullPoints, p)], hullPoints[q]) > EPSILON ) {
				q = next(hullPoints, q);
				if ( !(p == q0 && q == p0) ) {	// If we have not moved
												// p to q0 and q to p0, we
												// have a new antipodal pair.
												// Compute distances for
												// antipodal pair p and q.
					dist = distance(hullPoints[p], hullPoints[q]);
					if (dist > maxDist) {		// Check/set max. distance.
						maxDist = dist;
					}
				}
				else {
					break scan;
				}
			}

			// 4. If edges are parallel, (areas equal)
			//    create antipodal pair for current p
			//    and point after current q
			if ( Math.abs( Geometry.twice_area(hullPoints[p], hullPoints[next(hullPoints, p)], hullPoints[next(hullPoints, q)]) -
			   	   		   Geometry.twice_area(hullPoints[p], hullPoints[next(hullPoints, p)], hullPoints[q]) ) < EPSILON ) {
				if ( !(p == q0 && q == pn) ) {	// If we have not moved
												// p to q0 and q to pn, we
												// have a new antipodal pair.
												// Compute distances for
												// antipodal pair p and next(q).
					dist = distance(hullPoints[p], hullPoints[next(hullPoints, q)]);
					if (dist > maxDist) {		// Check/set max. distance.
						maxDist = dist;
					}
				}
				else {
					break scan;
				}
			}
		}
		return maxDist;
	}

	// Find the next point in the list going counterclockwise,
	// that is, in increasing order. Go back to zero if needed.
	// Return the array index.

	private int next(Pointd[] points, int i) {
		if (i >= points.length-1)
			return 0;
		else
			return (i+1);
	}

	// Find the distance between two points.

	private double distance (Pointd p, Pointd q) {
		// Return the Euclidean distance between the two points.
		return Math.sqrt ( (p.x - q.x)*(p.x - q.x) + (p.y - q.y)*(p.y  -q.y) );
	}
}