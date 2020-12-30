package org.edisonwj.draw3d;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
/**
* This interface provides default settings for all Draw3D classes.
*
* @author William Edison
* @version 1.12 June 2017
*
*/
public interface Draw3DDefaults {

    public static final double SCENE_WIDTH = 1000;
    public static final double SCENE_HEIGHT = 1000;

    public static final boolean SHOW_AXES = true;
    public static final boolean SHOW_TICK_MARKS = true;
    public static final boolean SHOW_Z_AXIS_UP = true;
    public static final boolean SHOW_X_Y_ONLY = false;
    public static final boolean SHOW_BOUNDARY_CUBE = false;
    public static final boolean SHOW_DEFAULT_GRID = false;
    public static final boolean AMBIENT_LIGHT_ON = false;
    public static final boolean POINT_LIGHT_ON = true;
    public static final boolean CUMULATE_DEFAULT = false;
    public static final boolean CUMULATE_MANUAL_DEFAULT = false;

    public static final double POINT_LIGHT_1_X = -100;
    public static final double POINT_LIGHT_1_Y =  100;
    public static final double POINT_LIGHT_1_Z =  600;
    public static final double POINT_LIGHT_2_X =  100;
    public static final double POINT_LIGHT_2_Y = -100;
    public static final double POINT_LIGHT_2_Z = -600;

    public static final double MIN_MAX_X = 10.0;
    public static final double MIN_MAX_Y = 10.0;
    public static final double MIN_MAX_Z = 10.0;

    public static final int[] DEFAULT_ORIGIN = {0, 0, 0};
    public static final double CAMERA_INITIAL_X_ANGLE = -10.0;
    public static final double CAMERA_INITIAL_Y_ANGLE = -200.0;
    public static final double CAMERA_INITIAL_DISTANCE = -1100;
    public static final double CAMERA_NEAR_CLIP = 0.1;
    public static final double CAMERA_FAR_CLIP = 10000.0;
    public static final double MOUSE_SPEED = 0.1;
    public static final double ROTATION_SPEED = 2.0;
    public static final double SCROLL_FACTOR = .01;
    public static final double SCROLL_DELTA = 10.0;

    public static final double AXIS_LENGTH = 400.0;
    public static final double AXIS_RADIUS = 0.5;
    public static final double TICK_MARK_LENGTH = 4.0;
    public static final double TICK_MARK_RADIUS = 0.5;
    public static final double BOUNDRY_RADIUS = 0.5;
    public static final double POINT_SIZE = 1.0;
    public static final double VERTEX_SIZE = 1.0;
    public static final double LINE_RADIUS = 0.5;
    public static final double EDGE_RADIUS = 0.5;
    public static final double ARROW_RADIUS = 0.5;
    public static final double ARROW_POINT_RADIUS = 2.0;
    public static final double ARROW_POINT_HEIGHT = 6.0;
    public static final double VECTOR_RADIUS = 0.5;
    public static final double VECTOR_POINT_RADIUS = 2.0;
    public static final double VECTOR_POINT_HEIGHT = 6.0;
    public static final int AV_POINT_DIVISIONS = 8;
    public static final int CONE_DIVISIONS = 64;
    public static final int CYLINDER_DIVISIONS = 64;
    public static final int OVAL_DIVISIONS = 64;
    public static final int SPHERE_DIVISIONS = 64;
    public static final Color BACKGROUND_COLOR = Color.WHITE;
    public static final Font LABEL_FONT = Font.font ("Regular", 8);
    public static final double LABEL_FONT_SIZE = 8;
    public static final double EPSILON = .00000001;
}