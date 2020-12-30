package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Draw3D;
import org.edisonwj.draw3d.Sphere3D;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
/**
*
* @author William Edison
* @version 1.04 February 2016
* 
*/
public class TestTexture extends Application {
	private Draw3D dt;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestTexture");
		primaryStage.show();
	}
	
	private void buildData() {

		Image i01 = new Image("/Resources/black_stripes.png");
		Image i02 = new Image("/Resources/blue_stripes.jpg");
		Image i03 = new Image("/Resources/chainlink-fence.jpg");
		Image i04 = new Image("/Resources/dots.png");
		Image i05 = new Image("/Resources/honeycomb1.gif");
		Image i06 = new Image("/Resources/images.png");
		Image i07 = new Image("/Resources/images6YXY362H.jpg");
		Image i08 = new Image("/Resources/imagesD9XROQTQ.jpg");
		Image i09 = new Image("/Resources/imagesGA3RP02Q.jpg");
		Image i10 = new Image("/Resources/imagesRWBFXO8J.jpg");
		Image i11 = new Image("/Resources/imagesSH1AUT0Y.jpg");
		Image i12 = new Image("/Resources/imagesTZL8AEY9.jpg");
		Image i13 = new Image("/Resources/imagesXRP1DUCN.jpg");
		Image i14 = new Image("/Resources/monostripe.png");
		Image i15 = new Image("/Resources/monostripe.png");
		Image i16 = new Image("/Resources/Polka_dots.png");
		Image i17 = new Image("/Resources/squares.png");
		Image i18 = new Image("/Resources/transparent_graphic.png");
		Image i19 = new Image("/Resources/transparent_graphic.png");
		Image i20 = new Image("/Resources/images72CF78U3.jpg");
		Image i21 = new Image("/Resources/imagesEO3JGV96.jpg");
		Image i22 = new Image("/Resources/imagesB12P99G9.jpg");
		Image i23 = new Image("/Resources/imagescrisscross.jpg");
		Image i24 = new Image("/Resources/mercator.jpg");
		
		dt.setSequencingOn();
		dt.setCumulate(false);
		
		// Boxes
		
		dt.setTexture(i01);
		dt.drawCuboid(-1, -8, 7, 2, 3, 2);
		
		dt.setTexture(i02);
		dt.drawCuboid(-1, -5, 7, 2, 3, 2);
		
		dt.setTexture(i03);
		dt.drawCuboid(-1, -2, 7, 2, 3, 2);
		
		dt.setTexture(i04);
		dt.drawCuboid(-1,  2, 7, 2, 3, 2);
		
		dt.setTexture(i05);
		dt.drawCuboid(-1,  5, 7, 2, 3, 2);
		
		dt.setTexture(i06);
		dt.drawCuboid(-1,  8, 7, 2, 3, 2);
		
		dt.setTexture(i07);
		dt.drawCuboid(-1, -8, 3, 2, 3, 2);
		
		dt.setTexture(i08);
		dt.drawCuboid(-1, -5, 3, 2, 3, 2);
		
		dt.setTexture(i09);
		dt.drawCuboid(-1, -2, 3, 2, 3, 2);
		
		dt.setTexture(i10);
		dt.drawCuboid(-1,  2, 3, 2, 3, 2);
		
		dt.setTexture(i11);
		dt.drawCuboid(-1,  5, 3, 2, 3, 2);
		
		dt.setTexture(i12);
		dt.drawCuboid(-1,  8, 3, 2, 3, 2);
		
		// Rectangles
		
		dt.setTexture(i13);
		dt.drawRectangle(-1, -8, -7, 2, 3);
		
		dt.setTexture(i14);
		dt.drawRectangle(-1, -5, -7, 2, 3);
		
		dt.setTexture(i15);
		dt.drawRectangle(-1, -2, -7, 2, 3);
		
		dt.setTexture(i16);
		dt.drawRectangle(-1,  2, -7, 2, 3);
		
		dt.setTexture(i17);
		dt.drawRectangle(-1,  5, -7, 2, 3);
		
		dt.setTexture(i18);
		dt.drawRectangle(-1,  8, -7, 2, 3);
		
		dt.setTexture(i19);
		dt.drawRectangle(-1, -8, -3, 2, 3);
		
		dt.setTexture(i20);
		dt.drawRectangle(-1, -5, -3, 2, 3);
		
		dt.setTexture(i21);
		dt.drawRectangle(-1, -2, -3, 2, 3);
		
		dt.setTexture(i22);
		dt.drawRectangle(-1,  2, -3, 2, 3);
		
		dt.setTexture(i23);
		dt.drawRectangle(-1,  5, -3, 2, 3);
		
		dt.setTexture(i24);
		dt.drawRectangle(-1,  8, -3, 2, 3);
		
		dt.setTexture(i24);
		dt.drawSphere3D(new Sphere3D(0, 0,  0,  2, 180, 0, -90));
		
		dt.incrSequence();
		
		// Cylinders	
		
		dt.setTexture(i01);
		dt.drawCylinder(-1, -8, 8, 1.5, 3);
		
		dt.setTexture(i02);
		dt.drawCylinder(-1, -5, 8, 1.5, 3);
		
		dt.setTexture(i03);
		dt.drawCylinder(-1, -2, 8, 1.5, 3);
		
		dt.setTexture(i04);
		dt.drawCylinder(-1,  2, 8, 1.5, 3);
		
		dt.setTexture(i05);
		dt.drawCylinder(-1,  5, 8, 1.5, 3);
		
		dt.setTexture(i06);
		dt.drawCylinder(-1,  8, 8, 1.5, 3);
		
		dt.setCylinderDivisions(6);
		
		dt.setTexture(i07);
		dt.drawCylinder(-1, -8, 3, 1.5, 3);
		
		dt.setTexture(i08);
		dt.drawCylinder(-1, -5, 3, 1.5, 3);
				
		dt.setTexture(i09);
		dt.drawCylinder(-1, -2, 3, 1.5, 3);
		
		dt.setTexture(i10);
		dt.drawCylinder(-1,  2, 3, 1.5, 3);
		
		dt.setTexture(i11);
		dt.drawCylinder(-1,  5, 3, 1.5, 3);
		
		dt.setTexture(i12);
		dt.drawCylinder(-1,  8, 3, 1.5, 3);
		
		// Spheres
		
		dt.setTexture(i13);
		dt.drawSphere(-1, -8, -8, 1.5);
		
		dt.setTexture(i14);
		dt.drawSphere(-1, -5, -8, 1.5);
		
		dt.setTexture(i15);
		dt.drawSphere(-1, -2, -8, 1.5);
		
		dt.setTexture(i16);
		dt.drawSphere(-1,  2, -8, 1.5);
		
		dt.setTexture(i17);
		dt.drawSphere(-1,  5, -8, 1.5);
		
		dt.setTexture(i18);
		dt.drawSphere(-1,  8, -8, 1.5);
		
		dt.setSphereDivisions(6);
		
		dt.setTexture(i19);
		dt.drawSphere(-1, -8, -3, 1.5);
		
		dt.setTexture(i20);
		dt.drawSphere(-1, -5, -3, 1.5);
				
		dt.setTexture(i21);
		dt.drawSphere(-1, -2, -3, 1.5);
		
		dt.setTexture(i22);
		dt.drawSphere(-1,  2, -3, 1.5);
		
		dt.setTexture(i23);
		dt.drawSphere(-1,  5, -3, 1.5);
		
		dt.setTexture(i24);
		dt.drawSphere(-1,  8, -3, 1.5);
		
		dt.setTexture(i24);
		dt.setSphereDivisions(64);
		dt.drawSphere3D(new Sphere3D(0, 0,  0,  2, 180, 0, -90));

//		Image diffuseMap = new Image(Test8.class.getResource("/Resource/Polka_dots.png").toExternalForm());
//		PhongMaterial m = new PhongMaterial(Color.TRANSPARENT, diffuseMap, null, null, null);

	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}