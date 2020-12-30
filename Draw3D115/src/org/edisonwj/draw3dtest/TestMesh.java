 package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Draw3D;
import org.edisonwj.draw3d.Mesh3D;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.TriangleMesh;
import javafx.stage.Stage;
/**
*
* @author William Edison
* @version 1.08 January 2017
*
*/
public class TestMesh extends Application {
	private Draw3D dt;

	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestMesh");
		primaryStage.show();
	}

	private void buildData() {
		dt.setYaxisUp(false);
		dt.setAmbientLight(false);
		dt.setPointLight(true);
		dt.setCumulate(false);
		Mesh3D mesh1 = new Mesh3D(buildTriangleMesh(50, 100),
							 new Point3D(0,0,0),
							 0, 0, 0,
							 new PhongMaterial(Color.GOLDENROD));
		dt.drawMesh3D(mesh1);

		Mesh3D mesh1rx = new Mesh3D(buildTriangleMesh(50, 100),
				 new Point3D(6,0,0),
				 45, 0, 0,
				 new PhongMaterial(Color.GOLDENROD));
		dt.drawMesh3D(mesh1rx);

		Mesh3D mesh1ry = new Mesh3D(buildTriangleMesh(50, 100),
				 new Point3D(0,6,0),
				 0, 45, 0,
				 new PhongMaterial(Color.GOLDENROD));
		dt.drawMesh3D(mesh1ry);

		Mesh3D mesh1rz = new Mesh3D(buildTriangleMesh(50, 100),
				 new Point3D(0,0,6),
				 0, 0, 20,
				 new PhongMaterial(Color.GOLDENROD));
		dt.drawMesh3D(mesh1rz);

		dt.incrSequence();
		dt.setCumulate(true);
		Mesh3D mesh2 = new Mesh3D(buildTriangleMesh(50, 100),
							 new Point3D(5,-5,5),
							 -90, 0, 0,
							 new PhongMaterial(Color.RED));
		dt.drawMesh3D(mesh2);


		dt.incrSequence();
		Mesh3D mesh3 = new Mesh3D(buildTriangleMesh(50, 100),
							 new Point3D(5,5,-5),
							 0, -20, 0,
							 new PhongMaterial(Color.BLUE));
		dt.drawMesh3D(mesh3);

		dt.incrSequence();
		Mesh3D mesh4 = new Mesh3D(buildTriangleMesh(50, 100),
							 new Point3D(-5,5,-5),
							 -45, 0, 0,
							 new PhongMaterial(Color.GREEN));
		mesh4.setDrawMode(DrawMode.LINE);
		mesh4.setCullFace(CullFace.NONE);
		dt.drawMesh3D(mesh4);

	}

//	Example from JavaFX 8 Introduction by Example

    private TriangleMesh buildTriangleMesh(float height, float hypotenuse) {
    	TriangleMesh mesh = new TriangleMesh();
        //End Step 2a
        //Step 2b: Add 5 points, later we will build our faces from these
        mesh.getPoints().addAll(
            0,0,0,                   //Point 0: Top of Pyramid
            0,height,-hypotenuse/2,  //Point 1: closest base point to camera
            -hypotenuse/2,height,0,  //Point 2: left most base point to camera
            hypotenuse/2,height,0,   //Point 3: farthest base point to camera
            0,height,hypotenuse/2    //Point 4: right most base point to camera
        );//End Step 2b
        //Step 2c:
        //for now we'll just make an empty texCoordinate group
        mesh.getTexCoords().addAll(0,0);
        //End Step 2c
        //Step 2d: Add the faces "winding" the points generally counter clock wise
        mesh.getFaces().addAll( //use dummy texCoords
            0,0,2,0,1,0,  // Vertical Faces "wind" counter clockwise
            0,0,1,0,3,0,  // Vertical Faces "wind" counter clockwise
            0,0,3,0,4,0,  // Vertical Faces "wind" counter clockwise
            0,0,4,0,2,0,  // Vertical Faces "wind" counter clockwise
            4,0,1,0,2,0,  // Base Triangle 1 "wind" clockwise because camera has rotated
            4,0,3,0,1,0   // Base Triangle 2 "wind" clockwise because camera has rotated
        ); //End Step 2d
        return mesh;
    }

	public static void main(String[] args) {
		launch(args);
	}
}