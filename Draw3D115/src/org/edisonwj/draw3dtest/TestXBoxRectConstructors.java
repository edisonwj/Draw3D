package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Box3D;
import org.edisonwj.draw3d.Draw3D;
import org.edisonwj.draw3d.Rectangle3D;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.stage.Stage;
/**
*
* @author William Edison
* @version 1.04 February 2016
* 
*/
public class TestXBoxRectConstructors extends Application {
	private Draw3D dt;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestXBoxRectConstructors");
		primaryStage.show();
	}
	
	private void buildData() {
		dt.setSequencingOn();
		dt.setAmbientLight(true);
		
	    PhongMaterial m1 = new PhongMaterial();
        m1.setDiffuseColor(Color.DARKORCHID);
        m1.setSpecularColor(Color.ORCHID);
        System.out.println("Orchid: " + m1);
        
		Box3D b1 = new Box3D(new Point3D(5, -8.0, 7.5), 1.5, 4, 2);
		dt.drawBox3D(b1);
		System.out.println(b1);
		Box3D b2 = new Box3D(new Point3D(5, -6.0, 7.5), 1.5, 4, 2, m1);
		dt.drawBox3D(b2);
		System.out.println(b2);
		
		Box3D b3 = new Box3D(new Point3D(5, -4.0, 7.5), 1.5, 4, 2, 0, 0, 0);
		dt.drawBox3D(b3);
		System.out.println(b3);
		Box3D b4 = new Box3D(new Point3D(5, -2.0, 7.5), 1.5, 4, 2, 0, 0, 0, m1);
		dt.drawBox3D(b4);
		System.out.println(b4);

		Box3D b5 = new Box3D(5, 2.0, 7.5, 1.5, 4, 2);
		dt.drawBox3D(b5);
		System.out.println(b5);
		Box3D b6 = new Box3D(5, 4.0, 7.5, 1.5, 4, 2, m1);
		dt.drawBox3D(b6);
		System.out.println(b6);
		
		Box3D b7 = new Box3D(5, 6.0, 7.5, 1.5, 4, 2, 0, 0, 0);
		dt.drawBox3D(b7);
		System.out.println(b7);
		Box3D b8 = new Box3D(5, 8.0, 7.5, 1.5, 4, 2, 0, 0, 0, m1);
		dt.drawBox3D(b8);
		System.out.println(b8);
		
		Box3D b9 = new Box3D(new Point3D(5, -8.0, 2.5), 1.5, 4, 2);
		dt.drawBox3D(b9);
		System.out.println(b9);
		Box3D b10 = new Box3D(new Point3D(5, -6.0, 2.5), 1.5, 4, 2, m1);
		dt.drawBox3D(b10);
		System.out.println(b10);
		
		Box3D b11 = new Box3D(new Point3D(5, -4.0, 2.5), 1.5, 4, 2, 30, -30, 0);
		dt.drawBox3D(b11);
		System.out.println(b11);
		Box3D b12 = new Box3D(new Point3D(5, -2.0, 2.5), 1.5, 4, 2, 30, -30, 0, m1);
		dt.drawBox3D(b12);
		System.out.println(b12);

		Box3D b13 = new Box3D(5, 2.0, 2.5, 1.5, 4, 2);
		dt.drawBox3D(b13);
		System.out.println(b13);
		Box3D b14 = new Box3D(5, 4.0, 2.5, 1.5, 4, 2, m1);
		dt.drawBox3D(b14);
		System.out.println(b14);
		
		Box3D b15 = new Box3D(5, 6.0, 2.5, 1.5, 4, 2, 30, -30 ,0);
		dt.drawBox3D(b15);
		System.out.println(b15);
		Box3D b16 = new Box3D(5, 8.0, 2.5, 1.5, 4, 2, 30, -30, 0, m1);
		dt.drawBox3D(b16);
		System.out.println(b16);
		
	    PhongMaterial m2 = new PhongMaterial();
        m2.setDiffuseColor(Color.DARKGOLDENROD);
        m2.setSpecularColor(Color.GOLDENROD);
        System.out.println("Goldenrod: " + m2);
        
		Rectangle3D r1 = new Rectangle3D(new Point3D(5, -8.0, -3), 1.5, 4);
		dt.drawRectangle3D(r1);
		System.out.println(r1);
		Rectangle3D r2 = new Rectangle3D(new Point3D(5, -6.0, -3), 1.5, 4, m2);
		dt.drawRectangle3D(r2);
		System.out.println(r2);
		
		Rectangle3D r3 = new Rectangle3D(new Point3D(5, -4.0, -3), 1.5, 4, 0, 0, 0);
		dt.drawRectangle3D(r3);
		System.out.println(r3);
		Rectangle3D r4 = new Rectangle3D(new Point3D(5, -2.0, -3), 1.5, 4, 0, 0, 0, m2);
		dt.drawRectangle3D(r4);
		System.out.println(r4);

		Rectangle3D r5 = new Rectangle3D(5, 2.0, -3, 1.5, 4);
		dt.drawRectangle3D(r5);
		System.out.println(r5);
		Rectangle3D r6 = new Rectangle3D(5, 4.0, -3, 1.5, 4, m2);
		dt.drawRectangle3D(r6);
		System.out.println(r6);
		
		Rectangle3D r7 = new Rectangle3D(5, 6.0, -3, 1.5, 4, 0, 0, 0);
		dt.drawRectangle3D(r7);
		System.out.println(r7);
		Rectangle3D r8 = new Rectangle3D(5, 8.0, -3, 1.5, 4, 0, 0, 0, m2);
		dt.drawRectangle3D(r8);
		System.out.println(r8);
		
		Rectangle3D r9 = new Rectangle3D(new Point3D(5, -8.0, -8), 1.5, 4);
		dt.drawRectangle3D(r9);
		System.out.println(r9);
		Rectangle3D r10 = new Rectangle3D(new Point3D(5, -6.0, -8), 1.5, 4, m2);
		dt.drawRectangle3D(r10);
		System.out.println(r10);
		
		Rectangle3D r11 = new Rectangle3D(new Point3D(5, -4.0, -8), 1.5, 4, 30, -30, 0);
		dt.drawRectangle3D(r11);
		System.out.println(r11);
		Rectangle3D r12 = new Rectangle3D(new Point3D(5, -2.0, -8), 1.5, 4, 30, -30, 0, m2);
		dt.drawRectangle3D(r12);
		System.out.println(r12);

		Rectangle3D r13 = new Rectangle3D(5, 2.0, -8, 1.5, 4);
		dt.drawRectangle3D(r13);
		System.out.println(r13);
		Rectangle3D r14 = new Rectangle3D(5, 4.0, -8, 1.5, 4, m2);
		dt.drawRectangle3D(r14);
		System.out.println(r14);
		
		Rectangle3D r15 = new Rectangle3D(5, 6.0, -8, 1.5, 4, 30, -30 ,0);
		dt.drawRectangle3D(r15);
		System.out.println(r15);
		Rectangle3D r16 = new Rectangle3D(5, 8.0, -8, 1.5, 4, 30, -30, 0, m2);
		dt.drawRectangle3D(r16);
		System.out.println(r16);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}