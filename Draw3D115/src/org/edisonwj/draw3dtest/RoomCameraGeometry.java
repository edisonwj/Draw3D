package org.edisonwj.draw3dtest;

import java.util.ArrayList;

import org.edisonwj.draw3dtest.Pointd;
import org.edisonwj.draw3dtest.RoomCamera;
import org.edisonwj.draw3dtest.Wall;

/**
* Class of methods for camera geometry.
*
* @author William Edison
* @version 1.08 January 2017
*
*/
public class RoomCameraGeometry {

	/**
	 * Class variable providing for inexact point comparison.
	 */
	private static final double EPSILON = .00000001;

	/**
	 * Class variable specifying processing option.
	 * For detail=false the value in each returned int[][] matrix cell is:
	 *  						-1 if the cell is in a wall;
	 *  						 0 if the cell is not covered by a camera;
	 *  						 1 if the cell is covered by a camera;
	 * For detail=true the value in each returned int[][] matrix cell is:
	 *  						-1 if the cell is in a wall;
	 *  						 0 if the cell is not covered by a camera;
	 *  						 a bit string with each bit indicating whether camera;
	 *  							0 through 32 (starting from LSB) covers the cell;
	 *  						(Maximum of 32 cameras)
	 */
	private static boolean detail = false;
	/**
	 * Class variable specifying processing option.
	 * For onBoundary = true, cells on the boundary lines of the field of view are considered covered.
	 * for onBoundary = false, cells on the boundary lines of the filed of view are considered not covered.
	 */
	private static boolean onBoundary = true;

    /**
	 * Determines the set of x,y coordinates of the tiles in a room that are visible to one or more
	 * of the specified cameras. Tiles are specified by the x,y coordinates of their lower left corner.
	 * The room is defined by a list of exterior walls (exWalls) whose maximum dimensions are xMax and
	 * yMax. The room may also have interior walls specified by the list (inWalls).
	 * Cameras each have a field of view specified by the range parameter.
	 *
	 * @param	xMax		int value of the maximum x dimension of the room
	 * @param	yMax		int value of the maximum y dimension of the room
	 * @param	range		int value in degrees defining the camera field of view,
	 * 						    i.e. number of degrees to the left and to the right
	 * 							of the orientation (0 through 90)
	 * @param	cameraList	ArrayList of Camera objects to be assessed for room coverage
	 * @param	exWalls		ArrayList of exterior Wall objects
	 * @param	inWalls		ArrayList of interior Wall objects
	 *
	 * @return	coverList	int[][] matrix containing in each cell:
	 *  						-1 if the cell is in a wall;
	 *  						 0 if the cell is not covered by a camera;
	 *  						 1 if the cell is covered by a camera;
	 */
	public static int[][] findCover(int xMax, int yMax, int range,
			 ArrayList<RoomCamera> cameraList,
			 ArrayList<Wall> exWalls,
			 ArrayList<Wall> inWalls) {

		if (range < 0 || range > 90) {
			range = 90;
			System.out.println("Range must be >= 0 and <= 90. Range set to 90.");
		}
		RoomCameraGeometry.detail = false;
		RoomCameraGeometry.onBoundary = true;
		return RoomCameraGeometry.findCover(xMax, yMax, range,
									   cameraList,
									   exWalls,
									   inWalls,
									   detail,
									   onBoundary);
}

    /**
	 * Determines the set of x,y coordinates of the tiles in a room that are visible to one or more
	 * of the specified cameras. Tiles are specified by the x,y coordinates of their lower left corner.
	 * The room is defined by a list of exterior walls (exWalls) whose maximum dimensions are xMax and
	 * yMax. The room may also have interior walls specified by the list (inWalls).
	 * Cameras each have a field of view specified by the range parameter.
	 *
	 * @param	xMax		int value of the maximum x dimension of the room
	 * @param	yMax		int value of the maximum y dimension of the room
	 * @param	range		int value in degrees defining the camera field of view,
	 * 						    i.e. number of degrees to the left and to the right
	 * 							of the orientation (0 through 90)
	 * @param	cameraList	ArrayList of Camera objects to be assessed for room coverage
	 * @param	exWalls		ArrayList of exterior Wall objects
	 * @param	inWalls		ArrayList of interior Wall objects
	 * @param	detail		boolean value indicating whether to return summary or detailed cover info
	 *
	 * @return	coverList	int[][] matrix containing
	 * 							when detail=false in each cell:
	 *  						-1 if the cell is in a wall;
	 *  						 0 if the cell is not covered by a camera;
	 *  						 1 if the cell is covered by a camera;
	 *
	 *  	 					when detail=true in each cell:
	 *  						-1 if the cell is in a wall;
	 *  						 0 if the cell is not covered by a camera;
	 *  						 a bit string with each bit indicating whether camera
	 *  							0 through 32 (starting from LSB) covers the cell;
	 *  						(Maximum of 32 cameras)
	 */
	public static int[][] findCover(int xMax, int yMax, int range,
									 ArrayList<RoomCamera> cameraList,
									 ArrayList<Wall> exWalls,
									 ArrayList<Wall> inWalls,
									 boolean detail) {
		if (range < 0 || range > 90) {
			range = 90;
			System.out.println("Range must be >= 0 and <= 90. Range set to 90.");
		}
		RoomCameraGeometry.detail = detail;
		RoomCameraGeometry.onBoundary = true;
		return RoomCameraGeometry.findCover(xMax, yMax, range,
									   cameraList,
									   exWalls,
									   inWalls,
									   detail,
									   onBoundary);
	}

    /**
	 * Determines the set of x,y coordinates of the tiles in a room that are visible to one or more
	 * of the specified cameras. Tiles are specified by the x,y coordinates of their lower left corner.
	 * The room is defined by a list of exterior walls (exWalls) whose maximum dimensions are xMax and
	 * yMax. The room may also have interior walls specified by the list (inWalls).
	 * Cameras each have a field of view specified by the range parameter.
	 *
	 * @param	xMax		int value of the maximum x dimension of the room
	 * @param	yMax		int value of the maximum y dimension of the room
	 * @param	range		int value in degrees defining the camera field of view,
	 * 						    i.e. number of degrees to the left and to the right
	 * 							of the orientation (0 through 90)
	 * @param	cameraList	ArrayList of Camera objects to be assessed for room coverage
	 * @param	exWalls		ArrayList of exterior Wall objects
	 * @param	inWalls		ArrayList of interior Wall objects
	 * @param	detail		boolean value indicating whether to return summary or detailed cover info
	 * @param	onBoundary	boolean value indicating whether to consider covered (true value) tiles
	 * 						that are on the border line boundary lines or not
	 *
	 * @return	coverList	int[][] matrix containing
	 * 							when detail=false in each cell:
	 *  						-1 if the cell is in a wall;
	 *  						 0 if the cell is not covered by a camera;
	 *  						 1 if the cell is covered by a camera;
	 *
	 *  	 					when detail=true in each cell:
	 *  						-1 if the cell is in a wall;
	 *  						 0 if the cell is not covered by a camera;
	 *  						 a bit string with each bit indicating whether camera
	 *  							0 through 32 (starting from LSB) covers the cell;
	 *  						(Maximum of 32 cameras)
	 */
	public static int[][] findCover(int xMax, int yMax, int range,
									 ArrayList<RoomCamera> cameraList,
									 ArrayList<Wall> exWalls,
									 ArrayList<Wall> inWalls,
									 boolean detail,
									 boolean onBoundary) {

		if (range < 0 || range > 90) {
			range = 90;
			System.out.println("Range must be >= 0 and <= 90. Range set to 90.");
		}
		RoomCameraGeometry.detail = detail;
		RoomCameraGeometry.onBoundary = onBoundary;

		Lined[] boundaryLeft = new Lined[cameraList.size()];
		Lined[] boundaryRight = new Lined[cameraList.size()];
		for (int k = 0; k < cameraList.size(); k++) {
			RoomCamera camera = cameraList.get(k);
			int wallType = findWallType(camera.location, exWalls, inWalls, xMax, yMax);

			int boundaryAngleLeft  = findBoundaryAngle(range, camera, wallType, true);
			Pointd boundaryEndLeft = findBoundaryEnd(camera, boundaryAngleLeft, xMax, yMax);
			boundaryLeft[k] = new Lined(camera.location, boundaryEndLeft);

			int boundaryAngleRight  = findBoundaryAngle(range, camera, wallType, false);
			Pointd boundaryEndRight = findBoundaryEnd(camera, boundaryAngleRight, xMax, yMax);
			boundaryRight[k] = new Lined(camera.location, boundaryEndRight);
		}

		int[][] coverList = new int[xMax][yMax];
		for (int i = 0; i < xMax; i++) {
			for (int j = 0; j < yMax; j++) {
				Pointd p = new Pointd(i,j);
				if (inWall(p, exWalls, inWalls) >= 0) {
					coverList[i][j] = -1;
				}
				else {
					for (int k = 0; k < cameraList.size(); k++) {
						RoomCamera camera = cameraList.get(k);
						if(checkCell(range, camera, boundaryLeft[k], boundaryRight[k], exWalls, inWalls, p)) {
							if (detail)
								coverList[i][j] |= (1 << k);		// set the kth bit from the LSB indicating camera j cover
							else
								coverList[i][j] = 1;
						}
					}
				}
			}
		}

		return coverList;
	}

    /**
	 * Checks an individual cell (tile) determined by indices of its lower left corner, point p
	 * to see if it is visible to the specified camera. Cameras have a location on a wall, an orientation
	 * angle specifying the direction of view, boundaries of their range of view determined by their
	 * orientation and the range of view angle (generally left boundary = orientation - range and
	 * right boundary = orientation + range).
	 *
	 * Generally tiles are visible by a camera if all four corners of the the tile are within or on
	 * the boundary lines and if a ray from the camera location to all four corners of the cell does
	 * not properly intersect an interior wall.
	 *
	 * The room is defined by a list of exterior walls (exWalls) whose maximum dimensions are xMax and yMax.
	 * The room may also have interior walls specified by the list (inWalls).
	 *
	 * @param	range		int value in degrees defining the camera field of view,
	 * 						    i.e. number of degrees to the left and to the right
	 * 							of the orientation (0 through 90)
	 * @param	c				Camera object defining the camera to be assessed
	 * @param	leftBoundary	Lined object specifying a line segment defining the left boundary of view
	 * @param	rightBoundary	Lined object specifying a line segment defining the right boundary of view
	 * @param	exWalls			ArrayList of exterior Wall objects
	 * @param	inWalls			ArrayList of interior Wall objects
	 * @param	p				Pointd object specifying the cell to be evaluated
	 *
	 * @return	boolean			Value indicating whether the cell is visible or not by the specified camera
	 */
	public static boolean checkCell(int range,
									RoomCamera c,
									Lined leftBoundary,
									Lined rightBoundary,
									ArrayList<Wall> exWalls,
									ArrayList<Wall> inWalls,
									Pointd p) {

		if (range < 0 || range > 90) {
			range = 90;
			System.out.println("Range must be >= 0 and <= 90. Range set to 90.");
		}
		Pointd[] cellPoints = new Pointd[4];
		cellPoints[0] = new Pointd(p.x,   p.y);
		cellPoints[1] = new Pointd(p.x+1, p.y);
		cellPoints[2] = new Pointd(p.x+1, p.y+1);
		cellPoints[3] = new Pointd(p.x,   p.y+1);

		for (Pointd cp : cellPoints) {				// For each cell corner
													// Check if corner within camera field of view
			if ((onBoundary &&
				 righton(leftBoundary.p1, leftBoundary.p2,  cp) &&
			 	 lefton(rightBoundary.p1, rightBoundary.p2, cp) ) ||
				(!onBoundary &&
				 right(leftBoundary.p1, leftBoundary.p2,  cp) &&
				 left(rightBoundary.p1, rightBoundary.p2, cp) ) ) {

				// Check if ray from corner intersects an intervening interior wall
			 	Lined ray = new Lined(cp, c.location);
				for (Wall wall : inWalls) {
					Lined[] walls = new Lined[4];
					if (horizontal(wall)) {
						walls[0] = new Lined(wall.start, wall.end);
						walls[1] = new Lined(wall.end,
											  new Pointd(wall.end.x,   wall.end.y+1));
						walls[2] = new Lined(new Pointd(wall.end.x,   wall.end.y+1),
											  new Pointd(wall.start.x, wall.start.y+1));
						walls[3] = new Lined(new Pointd(wall.start.x, wall.start.y+1),
								  			  wall.start);
					}
					else {
						walls[0] = new Lined(wall.start, wall.end);
						walls[1] = new Lined(wall.end,
											  new Pointd(wall.end.x+1,   wall.end.y));
						walls[2] = new Lined(new Pointd(wall.end.x+1,   wall.end.y),
											  new Pointd(wall.start.x+1, wall.start.y));
						walls[3] = new Lined(new Pointd(wall.start.x+1, wall.start.y),
								  			  wall.start);
					}

					for (Lined w : walls) {
						// Ignore intersect, if camera is located on the wall
						if ( between(w.p1, w.p2, c.location) ) {
							continue;
						}

//						// Ignore intersect, if interior wall end point is cell point
//						if ( interiorEndPoint(exWalls, wall, cp) )
//							continue;

//						// Check for intersection with interior wall
						if ( properIntersect(ray, w) ) {
							return false;
						}
						else {
							continue;
						}
					}
					continue;
				}
			}
			else {
				return false;
			}
		}
		return true;
	}

	/**
	 * Determines whether a wall is horizontal or vertical.
	 *
	 * @param	wall	Wall object to be assessed.
	 *
	 * @return	boolean	True if the wall is horizontal and false if the wall is vertical.
	 * 					(Diagonal or non-vertical, non-horizontal walls are not allowed.)
	 */
	public static boolean horizontal(Wall wall) {
		if (wall.start.y == wall.end.y)
			return true;
		else
			return false;
	}

	/**
	 * Determines the angle, with respect to the X axis of the left or right boundary of the camera
	 * range of view. Cameras have a location on a wall, an orientation angle specifying the direction
	 * of their view, boundaries of their range of view determined by their orientation and the range
	 * of view angle. Generally left boundary = orientation-range and right boundary = orientation+range.
	 *
	 * @param	range		int value in degrees defining the camera field of view,
	 * 						    i.e. number of degrees to the left and to the right
	 * 							of the orientation (0 through 90)
	 * @param	c			Camera object to be defined
	 * @param	wallType	int value indicating the type of the wall location of the camera
	 * @param	left		boolean value indicating whether to find the left boundary or the right boundary
	 *
	 * @return	angle		int value indicating the angle of the boundary line relative to the x axis
	 */
	public static int findBoundaryAngle(int range, RoomCamera c, int wallType, boolean left) {
		if (range < 0 || range > 90) {
			range = 90;
			System.out.println("Range must be >= 0 and <= 90. Range set to 90.");
		}
		int angle = 0;

		if (wallType == 0 || wallType == 4) {						// Horizontal upper side or vertical top
			if (c.orientation > 180 && c.orientation < 360) {		// Can't see anything looking into the wall
				angle = c.orientation;
			}
			else if (c.orientation == 0 || c.orientation == 360) {
				if (left) {
					angle = range;
				}
				else {
					angle = 0;
				}
			}
			else {
				if (left) {
					angle = c.orientation + range;
					if (angle > 360) {
						angle -= 360;
					}
					if (angle > 180) {
						angle = 180;
					}
				}
				else {
					angle = c.orientation - range;
					if (angle < 0) {
						angle += 360;
					}
					if (angle > 180 && angle < 360) {
						angle = 0;
					}
				}
			}
		}
		else if (wallType == 1 || wallType == 5) {					// Horizontal lower side or vertical bottom
			if (c.orientation > 0 && c.orientation < 180) {			// Can't see anything looking into the wall
				angle = c.orientation;
			}
			else if (c.orientation == 0 || c.orientation == 360) {
				if (left) {
					angle = 360;
				}
				else {
					angle = 360 - range;
				}
			}
			else {
				if (left) {
					angle = c.orientation + range;
					if (angle > 360) {
						angle -= 360;
					}
					if (angle > 0 && angle < 180) {
						angle = 360;
					}
				}
				else {
					angle = c.orientation - range;
					if (angle < 0) {
						angle += 360;
					}
					if (angle < 180) {
						angle = 180;
					}
				}
			}
		}
		else if (wallType == 2 || wallType == 6) {					// Vertical right side or horizontal right
			if (c.orientation > 90 && c.orientation < 270) {		// Can't see anything looking into the wall
				angle = c.orientation;
			}
			else if (c.orientation == 0 || c.orientation == 360) {
				if (left) {
					angle = range;
				}
				else {
					angle = 360 - range;
				}
			}
			else {
				if (left) {
					angle = c.orientation + range;
					if (angle > 360) {
						angle -= 360;
					}
					if ((angle > 90) && (angle < 270) ) {
						angle = 90;
					}
				}
				else {
					angle = c.orientation - range;
					if (angle < 0) {
						angle += 360;
					}
					if (angle > 90 && angle < 270) {
						angle = 270;
					}
				}
			}
		}
		else if (wallType == 3 || wallType == 7 ) {					// Vertical left side or horizontal left
			if (c.orientation >= 0 && c.orientation < 90 ||			// Can't see anything looking into the wall
				c.orientation > 270 && c.orientation <= 360 ) {
				angle = c.orientation;
			}
			else {
				if (left) {
					angle = c.orientation + range;
					if (angle > 360) {
						angle -= 360;
					}
					if (angle > 270) {
						angle = 270;
					}
				}
				else {
					angle = c.orientation - range;
					if (angle < 0) {
						angle += 360;
					}
					if (angle < 90) {
						angle = 90;
					}
				}
			}
		}
																// Internal end corners

		else if (wallType == 8) {								// Horizontal internal left lower corner
			if (c.orientation > 0 && c.orientation < 90 ) {		// Can't see anything looking into the wall
				angle = c.orientation;
			}
			else if (c.orientation == 0 || c.orientation == 360) {
				if (left) {
					angle = 0;
				}
				else {
					angle = 360 - range;
				}
			}
			else {
				if (left) {
					angle = c.orientation + range;
					if (angle > 360) {
						angle -= 360;
					}
					if (angle > 0 && angle < 90) {
						angle = 360;
					}
				}
				else {
					angle = c.orientation - range;
					if (angle < 0) {
						angle += 360;
					}
					if (angle < 90) {
						angle = 90;
					}
				}
			}
		}
		else if (wallType == 9) {								// Horizontal internal left upper corner
			if (c.orientation > 270 && c.orientation < 360 ) {	// Can't see anything looking into the wall
				angle = c.orientation;
			}
			else if (c.orientation == 0 || c.orientation == 360) {
				if (left) {
					angle = range;
				}
				else {
					angle = 0;
				}
			}
			else {
				if (left) {
					angle = c.orientation + range;
					if (angle > 360) {
						angle -= 360;
					}
					if (angle > 270) {
						angle = 270;
					}
				}
				else {
					angle = c.orientation - range;
					if (angle < 0) {
						angle += 360;
					}
					if (angle > 270 && angle < 360) {
						angle = 0;
					}
				}
			}
		}
		else if (wallType == 10) {								// Horizontal internal right lower corner
			if (c.orientation > 90 && c.orientation < 180 ) {	// Can't see anything looking into the wall
				angle = c.orientation;
			}
			else if (c.orientation == 0 || c.orientation == 360) {
				if (left) {
					angle = range;
				}
				else {
					angle = 360 - range;
				}
			}
			else {
				if (left) {
					angle = c.orientation + range;
					if (angle > 360) {
						angle -= 360;
					}
					if (angle > 90 && angle < 180) {
						angle = 90;
					}
				}
				else {
					angle = c.orientation - range;
					if (angle < 0) {
						angle += 360;
					}
					if (angle < 180 && angle > 90) {
						angle = 180;
					}
				}
			}
		}
		else if (wallType == 11) {								// Horizontal internal right upper corner
			if (c.orientation > 180 && c.orientation < 270 ) {	// Can't see anything looking into the wall
				angle = c.orientation;
			}
			else if (c.orientation == 0 || c.orientation == 360) {
				if (left) {
					angle = range;
				}
				else {
					angle = 360 - range;
				}
			}
			else {
				if (left) {
					angle = c.orientation + range;
					if (angle > 360) {
						angle -= 360;
					}
					if (angle > 180  && angle < 270) {
						angle = 180;
					}
				}
				else {
					angle = c.orientation - range;
					if (angle < 0) {
						angle += 360;
					}
					if (angle < 270 && angle > 180) {
						angle = 270;
					}
				}
			}
		}
		else if (wallType == 12) {								// Vertical internal bottom left corner
			if (c.orientation > 0 && c.orientation < 90 ) {		// Can't see anything looking into the wall
				angle = c.orientation;
			}
			else if (c.orientation == 0 || c.orientation == 360) {
				if (left) {
					angle = 0;
				}
				else {
					angle = 360 - range;
				}
			}
			else {
				if (left) {
					angle = c.orientation + range;
					if (angle > 360) {
						angle -= 360;
					}
					if (angle > 0 && angle < 90) {
						angle = 0;
					}
				}
				else {
					angle = c.orientation - range;
					if (angle < 0) {
						angle += 360;
					}
					if (angle < 90) {
						angle = 90;
					}
				}
			}
		}
		else if (wallType == 13) {								// Vertical internal bottom right corner
			if (c.orientation > 90 && c.orientation < 180 ) {	// Can't see anything looking into the wall
				angle = c.orientation;
			}
			else if (c.orientation == 0 || c.orientation == 360) {
				if (left) {
					angle = range;
				}
				else {
					angle = 360 - range;
				}
			}
			else {
				if (left) {
					angle = c.orientation + range;
					if (angle > 360) {
						angle -= 360;
					}
					if (angle > 90 && angle < 180) {
						angle = 90;
					}
				}
				else {
					angle = c.orientation - range;
					if (angle < 0) {
						angle += 360;
					}
					if (angle > 90 && angle < 180) {
						angle = 180;
					}
				}
			}
		}
		else if (wallType == 14) {								// Vertical internal top left corner
			if (c.orientation > 270 && c.orientation < 360 ) {	// Can't see anything looking into the wall
				angle = c.orientation;
			}
			else if (c.orientation == 0 || c.orientation == 360) {
				if (left) {
					angle = range;
				}
				else {
					angle = 0;
				}
			}
			else {
				 if (left) {
					angle = c.orientation + range;
					if (angle > 360) {
						angle -= 360;
					}
					if (angle > 270) {
						angle = 270;
					}
				}
				else {
					angle = c.orientation - range;
					if (angle < 0) {
						angle += 360;
					}
					if (angle > 270 && angle < 360) {
						angle = 0;
					}
				}
			}
		}
		else if (wallType == 15) {								// Vertical internal top right corner
			if (c.orientation > 180 && c.orientation < 270 ) {	// Can't see anything looking into the wall
				angle = c.orientation;
			}
			else if (c.orientation == 0 || c.orientation == 360) {
				if (left) {
					angle = range;
				}
				else {
					angle = 360 - range;
				}
			}
			else {
				if (left) {
					angle = c.orientation + range;
					if (angle > 360) {
						angle -= 360;
					}
					if (angle > 180 && angle < 270) {
						angle = 180;
					}
				}
				else {
					angle = c.orientation - range;
					if (angle < 0) {
						angle += 360;
					}
					if	(angle >  180 && angle < 270) {
						angle = 270;
					}
				}
			}
		}
																// 16 Horizontal internal right external end lower corner
																// 20 Vertical internal upper external end left corner
																// 27 Upper right interior grid corner
		else if (wallType == 16 || wallType == 20 || wallType == 27) {
			if ((c.orientation >= 0 && c.orientation < 180) ||
				(c.orientation > 270 && c.orientation <= 360)) {
				angle = c.orientation;							// Can't see anything looking into the wall
			}
			else {
				if (left) {
					angle = c.orientation + range;
					if (angle > 360) {
						angle -= 360;
					}
					if (angle > 270) {
						angle = 270;
					}
				}
				else {
					angle = c.orientation - range;
					if (angle < 0) {
						angle += 360;
					}
					if	(angle < 180) {
						angle = 180;
					}
				}
			}
		}
																// 17 Horizontal internal right external end upper corner
																// 22 Vertical internal lower external end left corner
																// 26 Lower right interior grid corner
		else if (wallType == 17 || wallType == 22 || wallType == 26) {
			if ((c.orientation >= 0 && c.orientation < 90) ||
				(c.orientation > 180 && c.orientation <= 360)) {
				angle = c.orientation;							// Can't see anything looking into the wall
			}
			else {
				if (left) {
					angle = c.orientation + range;
					if (angle > 360) {
						angle -= 360;
					}
					if (angle > 180) {
						angle = 180;
					}
				}
				else {
					angle = c.orientation - range;
					if (angle < 0) {
						angle += 360;
					}
					if	(angle < 90) {
						angle = 90;
					}
				}
			}
		}
																// 18 Horizontal internal left external end lower corner
																// 21 Vertical internal upper external end right corner
																// 25 Upper left interior grid corner
		else if (wallType == 18 || wallType == 21 || wallType == 25) {
			if (c.orientation > 0 && c.orientation < 270) {
				angle = c.orientation;							// Can't see anything looking into the wall
			}
			else {
				if (left) {
					angle = c.orientation + range;
					if (angle > 360) {
						angle -= 360;
					}
					if (angle > 0 && angle < 90) {
						angle = 360;
					}
				}
				else {
					angle = c.orientation - range;
					if (angle < 0) {
						angle += 360;
					}
					if	(angle < 270) {
						angle = 270;
					}
				}
			}
		}
																// 19 Horizontal internal left external end upper corner
																// 23 Vertical internal lower external end right corner
																// 24 Lower left interior grid corner
		else if (wallType == 19 || wallType == 23 || wallType == 24) {
			if (c.orientation > 90 && c.orientation < 360) {
				angle = c.orientation;							// Can't see anything looking into the wall
			}
			else {
				if (left) {
					angle = c.orientation + range;
					if (angle > 360) {
						angle -= 360;
					}
					if (angle > 90) {
						angle = 90;
					}
				}
				else {
					angle = c.orientation - range;
					if (angle < 0) {
						angle += 360;
					}
					if	(angle > 90 && angle < 360) {
						angle = 0;
					}
				}
			}
		}
		else {
			angle = 0;
			System.out.println("Invalid wallType= " + wallType);
		}

		return angle;
	}

	/**
	 * Computes a representative end point of the line segment defining camera range of view boundaries.
	 *
	 * @param	c		Camera object being processed
	 * @param	angle	Angle of the boundary line, determined by findBoundaryAngle()
	 * @param	xMax	int value specifying the maximum x dimension of the room
	 * @param	yMax	int value specifying the maximum y dimension of the room
	 *
	 * @return	Pointd value of the boundary end point
	 */
	public static Pointd findBoundaryEnd(RoomCamera c, int angle, double xMax, double yMax) {
		double radians = angle*Math.PI/180.0;
		double h = Math.sqrt(xMax*xMax + yMax*yMax);
		double x2 = h * Math.cos(radians) + c.location.x;
		double y2 = h * Math.sin(radians) + c.location.y;
		return new Pointd(x2, y2);
	}

	/**
	 * Returns a string descriptive of a wall type.
	 *
	 * @param 	wallType	int value associated with a location (wall or corner) type
	 * @return	label		String describing a wall type
	 */
	public static String getWallTypeLabel(int wallType) {
		String label = "Invalid wall type: " + wallType;
		switch (wallType) {
			case 0: label = "0 Horizontal external upper side";
					break;
			case 1: label = "1 Horizontal external lower side";
					break;
			case 2: label = "2 Vertical external right side";
					break;
			case 3: label = "3 Vertical external left side";
					break;
			case 4: label = "4 Horizontal internal upper side";
					break;
			case 5: label = "5 Horizontal internal lower side";
					break;
			case 6: label = "6 Vertical internal right side";
					break;
			case 7: label = "7 Vertical internal left side";
					break;
			case 8: label = "8 Horizontal internal left end lower corner";
					break;
			case 9: label = "9 Horizontal internal left end upper corner";
					break;
			case 10: label = "10 Horizontal internal right end lower corner";
					break;
			case 11: label = "11 Horizontal internal right end upper corner";
					break;
			case 12: label = "12 Vertical internal lower end left corner";
					break;
			case 13: label = "13 Vertical internal lower end right corner";
					break;
			case 14: label = "14 Vertical internal upper end left corner";
					break;
			case 15: label = "15 Vertical internal upper end right corner";
					break;
			case 16: label = "16 Horizontal internal right end lower corner on external";
					break;
			case 17: label = "17 Horizontal internal right end upper corner on external";
					break;
			case 18: label = "18 Horizontal internal left end lower corner on external";
					break;
			case 19: label = "19 Horizontal internal left end upper corner on external";
					break;
			case 20: label = "20 Vertical internal upper end left corner on external";
					break;
			case 21: label = "21 Vertical internal upper end right corner on external";
					break;
			case 22: label = "22 Vertical internal lower end left corner on external";
					break;
			case 23: label = "23 Vertical internal lower end right corner on external";
					break;
			case 24: label = "24 Lower left interior grid corner";
					break;
			case 25: label = "25 Upper left interior grid corner";
					break;
			case 26: label = "26 Lower right interior grid corner";
					break;
			case 27: label = "27 Upper right interior grid corner";
					break;
		}
		return label;
	}

	/**
	 * Determines the wall type of the camera location, e.g. top of horizontal interior wall,
	 * left side of vertical interior wall, end side of interior wall, etc.
	 *
	 * @param	location	Pointd value specifying the camera locatio
	 * @param	exWalls		ArrayList of exterior Wall objects
	 * @param	inWalls		ArrayList of interior Wall objects
	 * @param	xMax		int value specifying the maximum x dimension of the room
	 * @param	yMax		int value specifying the maximum y dimension of the room
	 *
	 * @return	wallType	int value indicating camera location wall type:
	 * 							 0 Horizontal external upper side
	 * 							 1 Horizontal external lower side
	 * 							 2 Vertical external right side
	 * 							 3 Vertical external left side
	 * 							 4 Horizontal internal upper side
	 * 							 5 Horizontal internal lower side
	 * 							 6 Vertical internal right side
	 * 							 7 Vertical internal left side
	 * 							 8 Horizontal internal left lower corner
	 * 							 9 Horizontal internal left upper corner
	 * 							10 Horizontal internal right lower corner
	 * 							11 Horizontal internal right upper corner
	 * 							12 Vertical internal lower left corner
	 * 							13 Vertical internal lower right corner
	 * 							14 Vertical internal upper left corner
	 * 							15 Vertical internal upper right corner
	 *							16 Horizontal internal right end lower corner on external
	 *							17 Horizontal internal right end upper corner on external
	 *							18 Horizontal internal left end lower corner on external
	 *							19 Horizontal internal left end upper corner on external
	 *							20 Vertical internal upper end left corner on external
	 *							21 Vertical internal upper end right corner on external
	 *							22 Vertical internal lower end left corner on external
	 *							23 Vertical internal lower end right corner on external
	 *							24 Lower left interior grid corner
	 *							25 Upper left interior grid corner
	 *							26 Lower right interior grid corner
	 *							27 Upper right interior grid corner
	 * 							-1 Invalid position;
	 *
	 */
	public static int findWallType( Pointd location,
									ArrayList<Wall> exWalls,
									ArrayList<Wall> inWalls,
									double xMax, double yMax) {
// Interior Grid Corner Location

		if (	 location.x == 1 && location.y == 1) {
			return 24;				// Lower left corner
		}
		else if (location.x == 1 && location.y == yMax-1) {
			return 25;				// Upper left corner
		}
		else if (location.x == xMax-1 && location.y == 1) {
			return 26;
		}
		else if (location.x == xMax-1 && location.y == yMax-1) {
			return 27;
		}

// Interior Wall Location

		for (Wall iwall : inWalls) {
			Pointd w1 = iwall.start;
			Pointd w2 = iwall.end;

			if ( w1.y == w2.y ) {	// Horizontal
				if 		(location.y == w1.y &&
						 location.x == w1.x &&
						 w1.x != 1) {
					return 8;		// Horizontal internal left end lower corner
				}
				else if (location.y == w1.y+1 &&
						 location.x == w1.x &&
						 w1.x != 1) {
					return 9;		// Horizontal internal left end upper corner
				}
				else if (location.y == w2.y &&
						 location.x == w2.x &&
						 w2.x == xMax-1) {
					return 16;		// Horizontal internal right end lower corner on external
				}
				else if (location.y == w2.y+1 &&
						 location.x == w2.x &&
						 w2.x == xMax-1) {
					return 17;		// Horizontal internal right end upper corner on external
				}
				else if (location.y == w2.y &&
						 location.x == w2.x &&
						 w2.x != xMax-1 ) {
					return 10;		// Horizontal internal right end lower corner
				}
				else if (location.y == w2.y+1 &&
						 location.x == w2.x &&
						 w2.x != xMax-1 ) {
					return 11;		// Horizontal internal right end upper corner
				}
				else if (location.y == w1.y &&
						 location.x == w1.x &&
						 w1.x == 1 ) {
					return 18;		// Horizontal internal left end lower corner on external
				}
				else if (location.y == w1.y+1 &&
						 location.x == w1.x &&
						 w1.x == 1 ) {
					return 19;		// Horizontal internal left end upper corner on external
				}
				else if (between(w1, w2, location)) {
					return 5;		// Horizontal internal lower side
				}
				else {
					Pointd nw1 = new Pointd(w1.x, w1.y+1);
					Pointd nw2 = new Pointd(w2.x, w2.y+1);
					if (between(nw1, nw2, location)) {
						return 4;	// Horizontal internal upper side
					}
				}
			}

			if ( w1.x == w2.x ) {		// Vertical

				if 		(location.x == w1.x &&
						 location.y == w1.y &&
						 w1.y != 1) {
					return 12;		// Vertical internal lower end left corner
				}
				else if (location.x == w1.x+1 &&
						 location.y == w1.y &&
						 w1.y != 1) {
					return 13;		// Vertical internal lower end right corner
				}
				if 		(location.x == w2.x &&
						 location.y == w2.y &&
						 w2.y == yMax-1) {
					return 20;		// Vertical internal upper end left corner on external
				}
				else if (location.x == w2.x+1 &&
						 location.y == w2.y &&
						 w2.y == yMax-1) {
					return 21;		// Vertical internal upper end right corner on external
				}

				else if (location.x == w2.x &&
						 location.y == w2.y &&
						 w2.y != yMax-1) {
					return 14;		// Vertical internal upper end left corner
				}
				else if (location.x == w2.x+1 &&
						 location.y == w2.y &&
						 w2.y != yMax-1) {
					return 15;		// Vertical internal upper end right corner
				}
				else if (location.x == w1.x &&
						 location.y == w1.y &&
						 w1.y == 1) {
					return 22;		// Vertical internal lower end left corner on external
				}
				else if (location.x == w1.x+1 &&
						 location.y == w1.y &&
						 w1.y == 1) {
					return 23;		// Vertical internal lower end right corner on external
				}
				else if (between(w1, w2, location)) {
					return 7;		// Vertical internal left side
				}
				else {
					Pointd nw1 = new Pointd(w1.x+1, w1.y);
					Pointd nw2 = new Pointd(w2.x+1, w2.y);
					if (between(nw1, nw2, location)) {
						return 6;	// Vertical internal right side
					}
				}
			}
		}

		// Exterior Wall Location

		for (Wall ewall : exWalls) {
			Pointd w1 = ewall.start;
			Pointd w2 = ewall.end;

			if      (w1.y == w2.y && w1.y == 0) {
				Pointd nw1 = new Pointd(w1.x+1, w1.y+1);
				Pointd nw2 = new Pointd(w2.x-1, w2.y+1);
				if (between(nw1, nw2, location)) {
					return 0;		// Lower horizontal external
				}
			}
			else if (w1.y == w2.y && w1.y != 0) {
				Pointd nw1 = new Pointd(w1.x+1, w1.y);
				Pointd nw2 = new Pointd(w2.x-1, w2.y);
				if (between(nw1, nw2, location)) {
					return 1;		// Upper horizontal external
				}
			}
			else if (w1.x == w2.x && w1.x == 0) {
				Pointd nw1 = new Pointd(w1.x+1, w1.y+1);
				Pointd nw2 = new Pointd(w2.x+1, w2.y-1);
				if (between(nw1, nw2, location)) {
					return 2;		// Right vertical external
				}
			}
			else if (w1.x == w2.x && w1.x != 0) {
				Pointd nw1 = new Pointd(w1.x, w1.y+1);
				Pointd nw2 = new Pointd(w2.x, w2.y-1);
				if (between(nw1, nw2, location)) {
					return 3;		// Left vertical external
				}
			}
		}
		return -1;
	}

	/**
	 * Determines the type of wall that a tile (cell) is located within.
	 *
	 * @param	location	Pointd coordinates of the lower left corner of the cell
	 * @param	exWalls		ArrayList of exterior Wall objects
	 * @param	inWalls		ArrayList of interior Wall objects
	 *
	 * @return	int value indicating the wall type:
	 * 				 0 Lower horizontal exterior;
	 *				 1 Upper horizontal exterior;
	 *				 2 Left vertical exterior;
	 *				 3 Right vertical exterior;
	 *				 4 Horizontal interior;
	 *				 5 Vertical interior;
	 *				-1 Invalid cell location;
	 */
	public static int inWall( Pointd location,
							  ArrayList<Wall> exWalls,
							  ArrayList<Wall> inWalls) {

		for (Wall ewall : exWalls) {
			Pointd w1 = ewall.start;
			Pointd w2 = ewall.end;

			if      (w1.y == w2.y && w1.y == 0) {
				if (between(w1, w2, location)) {
					return 0;											// Lower horizontal external
				}
			}
			else if (w1.y == w2.y && w1.y != 0) {
				if (between(w1, w2, location)) {
					return 1;											// Upper horizontal external
				}
			}
			else if (w1.x == w2.x && w1.x == 0) {
				if (between(w1, w2, location)) {
					return 2;											// Left vertical external
				}
			}
			else if (w1.x == w2.x && w1.x != 0) {
				if (between(w1, w2, location)) {
					return 3;											// Right vertical external
				}
			}
		}

		for (Wall iwall : inWalls) {
			Pointd w1 = iwall.start;
			Pointd w2 = iwall.end;

			if ( w1.y == w2.y ) {
				Pointd nw1 = new Pointd(w1.x, w1.y);
				Pointd nw2 = new Pointd(w2.x-1, w2.y);
				if (between(nw1, nw2, location)) {
					return 5;											// Horizontal internal
				}
			}

			if ( w1.x == w2.x ) {
				Pointd nw1 = new Pointd(w1.x, w1.y);
				Pointd nw2 = new Pointd(w2.x, w2.y-1);
				if (between(nw1, nw2, location)) {
					return 7;											// Vertical internal
				}
			}
		}
		return -1;
	}

	/**
	 * Computes twice the area of the triangle formed by points a, b, c.
	 * When the area is positive, it indicates point c is to the left of line a,b.
	 * Based on "Computational Geometry in C" (Second Edition),
	 * Joseph O'Rourke, 1997.
	 *
	 * @param	a	Pointd object for point a
	 * @param	b	Pointd object for point b
	 * @param	c	Pointd object for point c
	 *
	 * @return	area2	double value for twice the area of triangle a, b, c
	 */
	public static double area2(Pointd a, Pointd b, Pointd c) {
		// Based on "Computational Geometry in C" (Second Edition),
		// Joseph O'Rourke, 1997.
		return (a.x - c.x) * (b.y - c.y) - (a.y - c.y) * (b.x - c.x);
	}

	/**
	 * Returns the sign of twice the area of the triangle formed by points a, b, c.
	 * When the area is positive, it indicates point c is to the left of line a,b.
	 * Based on "Computational Geometry in C" (Second Edition),
	 * Joseph O'Rourke, 1997.
	 *
	 * @param	a	Pointd object for point a
	 * @param	b	Pointd object for point b
	 * @param	c	Pointd object for point c
	 *
	 * @return	int value corresponding to the sign of twice the area of triangle a, b, c:
	 * 				-1 negative area, i.e. c is to the right of line a,b;
	 * 				 1 positive area, i.e. c is to the left of line a, b;
	 * 				 0 c is collinear with line a, b;
	 */
	public static int area_sign (Pointd a, Pointd b, Pointd c) {
		double area2 = area2(a, b, c);
		if (area2 < -EPSILON)
			return -1;
		else if (area2 > EPSILON)
			return 1;
		else
			return 0;
	}

	/**
	 * Returns a boolean value indicating whether or not point c is to the left of line a,b.
	 * Based on "Computational Geometry in C" (Second Edition),
	 * Joseph O'Rourke, 1997.
	 *
	 * @param	a	Pointd object for point a
	 * @param	b	Pointd object for point b
	 * @param	c	Pointd object for point c
	 *
	 * @return	boolean value:
	 * 				true if c is to the left of line a,b;
	 * 				false if c is not to the left of line a,b;
	 */
	public static boolean left (Pointd a, Pointd b, Pointd c) {
		double a2 = area2(a, b, c);
	    if ( (a2 > EPSILON) )
	    	return true;
	    else
	    	return false;
	}

	/**
	 * Returns a boolean value indicating whether or not point c is to the right of line a,b.
	 * Based on "Computational Geometry in C" (Second Edition),
	 * Joseph O'Rourke, 1997.
	 *
	 * @param	a	Pointd object for point a
	 * @param	b	Pointd object for point b
	 * @param	c	Pointd object for point c
	 *
	 * @return	boolean value:
	 * 				true if c is to the right of line a,b;
	 * 				false if c is not to the right of line a,b;
	 */
	public static boolean right (Pointd a, Pointd b, Pointd c) {
		double a2 = area2(a, b, c);
	    if ( (a2 < -EPSILON) )
	    	return true;
	    else
	    	return false;
	}

	/**
	 * Returns a boolean value indicating whether or not point c is to the left of or on line a,b.
	 * Based on "Computational Geometry in C" (Second Edition),
	 * Joseph O'Rourke, 1997.
	 *
	 * @param	a	Pointd object for point a
	 * @param	b	Pointd object for point b
	 * @param	c	Pointd object for point c
	 *
	 * @return	boolean value:
	 * 				true if c is to the left of or on line a,b;
	 * 				false if c is not to the right of line a,b;
	 */
	public static boolean lefton (Pointd a, Pointd b, Pointd c) {
		double a2 = area2(a, b, c);
	    if ( (a2 > EPSILON) || (Math.abs(a2) <= EPSILON) )
	    	return true;
	    else
	    	return false;
	}

	/**
	 * Returns a boolean value indicating whether or not point c is to the right of or on line a,b.
	 * Based on "Computational Geometry in C" (Second Edition),
	 * Joseph O'Rourke, 1997.
	 *
	 * @param	a	Pointd object for point a
	 * @param	b	Pointd object for point b
	 * @param	c	Pointd object for point c
	 *
	 * @return	boolean value:
	 * 				true if c is to the right of or on line a,b;
	 * 				false if c is to the left of line a,b;
	 */
	public static boolean righton (Pointd a, Pointd b, Pointd c) {
		double a2 = area2(a, b, c);
	    if ( (a2 < -EPSILON) || (Math.abs(a2) <= EPSILON) )
	    	return true;
	    else
	    	return false;
	}

	/**
	 * Returns a boolean value indicating whether point c is collinear with points a, b
	 * and if c is between a or b or equal to a or b.
	 * Based on "Computational Geometry in C" (Second Edition),
	 * Joseph O'Rourke, 1997.
	 *
	 * @param	a	Pointd object for point a
	 * @param	b	Pointd object for point b
	 * @param	c	Pointd object for point c
	 *
	 * @return	boolean value:
	 * 				true if c is collinear with a,b, and strictly between or equal to a or b;
	 * 				false if c is not between and not equal to a or b;
	 */

	public static boolean between (Pointd a, Pointd b, Pointd c) {
		boolean col;
		double area2 = area2(a, b, c);

		if (a.equals(b)) {
			if (a.equals(c))
				return true;
			else
				return false;
		}

		if (Math.abs(area2) <= EPSILON)
			col = true;
		else
			col = false;

		if (!col )
			return false;

	  /* Now that the points are collinear, check using coordinates.
	     To be between, the x-value of c has to be between the x values
	     of a and b. However, the three points can be on a vertical line
	     with c being above a and b. In this case, we have to make sure
	     c's y-value is between that of a and b */

	    if ( Math.abs(a.x-b.x) > EPSILON)  /* Not on a vertical line */
	      return  (   ( (a.x <= c.x) && (c.x <= b.x) )
		       || ( (b.x <= c.x) && (c.x <= a.x) )  );
	    else
	      return  (   ( (a.y <= c.y) && (c.y <= b.y) )
		       || ( (b.y <= c.y) && (c.y <= a.y) )  );
	  }

	/**
	 * Returns a boolean value indicating whether two line segments properly intersect one another,
	 * i.e. the intersection is within the segments and not at a line start or end point.
	 * Based on "Computational Geometry in C" (Second Edition),
	 * Joseph O'Rourke, 1997.
	 *
	 * @param	l1	Lined object specifying the start/end points of a line segment
	 * @param	l2	Lined object specifying the start/end points of a line segment
	 *
	 * @return	boolean value:
	 * 				true - the line segments properly intersect;
	 * 				false - the line segments do not properly intersect;
	 */
	public static boolean properIntersect (Lined l1, Lined l2) {
	/* If the intersection is proper then points c and d should
	   lie on opposite sides of segment ab. Also, a and b should
	   lie on opposite sides of segment cd. In other words, their
	   areas with cd should have opposite sign */

		Pointd a = l1.p1;
		Pointd b = l1.p2;
		Pointd c = l2.p1;
		Pointd d = l2.p2;

		if ( (area_sign(a,b,c) * area_sign(a,b,d) < 0) &&
			 (area_sign(c,d,a) * area_sign(c,d,b) < 0) )
			return true;
		else
			return false;
	}
}