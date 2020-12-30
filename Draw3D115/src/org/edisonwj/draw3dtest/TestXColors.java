package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Box3D;
import org.edisonwj.draw3d.Draw3D;

import javafx.application.Application;
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
public class TestXColors extends Application {
	private Draw3D dt;
	
	private PhongMaterial redMaterial = new PhongMaterial();
    private PhongMaterial greenMaterial = new PhongMaterial();
    private PhongMaterial blueMaterial = new PhongMaterial();
    private PhongMaterial salmonMaterial = new PhongMaterial();
    private PhongMaterial whiteMaterial = new PhongMaterial();
	private PhongMaterial blackMaterial = new PhongMaterial();
    private PhongMaterial greyMaterial = new PhongMaterial();
    private PhongMaterial coralMaterial = new PhongMaterial();
    private PhongMaterial magentaMaterial = new PhongMaterial();
    private PhongMaterial orchidMaterial = new PhongMaterial();
    private PhongMaterial turquoiseMaterial = new PhongMaterial();
    private PhongMaterial yellowMaterial = new PhongMaterial();
    private PhongMaterial cyanMaterial = new PhongMaterial();
    private PhongMaterial steelblueMaterial = new PhongMaterial();
    private PhongMaterial goldMaterial = new PhongMaterial();
    private PhongMaterial crimsonMaterial = new PhongMaterial();
    private PhongMaterial cornflowerMaterial = new PhongMaterial();
    
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		setColors();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestXColors");
		primaryStage.show();
	}
	
	private void buildData() {
		dt.setSequencingOn();
        
		Box3D b1 = new Box3D(5, -8.0, 7.5, 1.5, 4, 2, redMaterial );
		dt.drawBox3D(b1);
		System.out.println(b1);
		Box3D b2 = new Box3D(5, -6.0, 7.5, 1.5, 4, 2, greenMaterial);
		dt.drawBox3D(b2);
		System.out.println(b2);
		
		Box3D b3 = new Box3D(5, -4.0, 7.5, 1.5, 4, 2, blueMaterial);
		dt.drawBox3D(b3);
		System.out.println(b3);
		Box3D b4 = new Box3D(5, -2.0, 7.5, 1.5, 4, 2, goldMaterial);
		dt.drawBox3D(b4);
		System.out.println(b4);

		Box3D b5 = new Box3D(5, 2.0, 7.5, 1.5, 4, 2, cyanMaterial);
		dt.drawBox3D(b5);
		System.out.println(b5);
		Box3D b6 = new Box3D(5, 4.0, 7.5, 1.5, 4, 2, steelblueMaterial);
		dt.drawBox3D(b6);
		System.out.println(b6);
		
		Box3D b7 = new Box3D(5, 6.0, 7.5, 1.5, 4, 2, cornflowerMaterial);
		dt.drawBox3D(b7);
		System.out.println(b7);
		Box3D b8 = new Box3D(5, 8.0, 7.5, 1.5, 4, 2, crimsonMaterial);
		dt.drawBox3D(b8);
		System.out.println(b8);
		
		Box3D b9 = new Box3D(5, -8.0, 2.5, 1.5, 4, 2, salmonMaterial);
		dt.drawBox3D(b9);
		System.out.println(b9);
		Box3D b10 = new Box3D(5, -6.0, 2.5, 1.5, 4, 2, coralMaterial);
		dt.drawBox3D(b10);
		System.out.println(b10);
		
		Box3D b11 = new Box3D(5, -4.0, 2.5, 1.5, 4, 2, magentaMaterial);
		dt.drawBox3D(b11);
		System.out.println(b11);
		Box3D b12 = new Box3D(5, -2.0, 2.5, 1.5, 4, 2, orchidMaterial);
		dt.drawBox3D(b12);
		System.out.println(b12);

		Box3D b13 = new Box3D(5, 2.0, 2.5, 1.5, 4, 2, turquoiseMaterial);
		dt.drawBox3D(b13);
		System.out.println(b13);
		Box3D b14 = new Box3D(5, 4.0, 2.5, 1.5, 4, 2, yellowMaterial);
		dt.drawBox3D(b14);
		System.out.println(b14);
		
		Box3D b15 = new Box3D(5, 6.0, 2.5, 1.5, 4, 2, greyMaterial);
		dt.drawBox3D(b15);
		System.out.println(b15);
		Box3D b16 = new Box3D(5, 8.0, 2.5, 1.5, 4, 2, blackMaterial);
		dt.drawBox3D(b16);
		System.out.println(b16);
	}
	
private void setColors() {
		
        redMaterial.setDiffuseColor(Color.DARKRED);
        redMaterial.setSpecularColor(Color.RED);
        System.out.println("redMaterial= " + redMaterial);

        greenMaterial.setDiffuseColor(Color.DARKGREEN);
        greenMaterial.setSpecularColor(Color.GREEN);
        System.out.println("greenMaterial= " + greenMaterial);

        blueMaterial.setDiffuseColor(Color.DARKBLUE);
        blueMaterial.setSpecularColor(Color.BLUE);
        System.out.println("blueMaterial= " + blueMaterial);
        
        goldMaterial.setDiffuseColor(Color.DARKGOLDENROD);
        goldMaterial.setSpecularColor(Color.GOLDENROD);
        System.out.println("goldMaterial= " + goldMaterial);
        
        cyanMaterial.setDiffuseColor(Color.DARKCYAN);
        cyanMaterial.setSpecularColor(Color.CYAN);
        System.out.println("cyanMaterial= " + cyanMaterial);
        
        steelblueMaterial.setDiffuseColor(Color.STEELBLUE);
        steelblueMaterial.setSpecularColor(Color.LIGHTSTEELBLUE);
        System.out.println("steelblueMaterial= " + steelblueMaterial);
        
        cornflowerMaterial.setDiffuseColor(Color.CORNFLOWERBLUE);
        cornflowerMaterial.setSpecularColor(Color.LIGHTBLUE);
        System.out.println("cornflowerMaterial= " + cornflowerMaterial);
        
        crimsonMaterial.setDiffuseColor(Color.CRIMSON);
        crimsonMaterial.setSpecularColor(Color.PINK);
        System.out.println("crimsonMaterial= " + crimsonMaterial);
        
	    salmonMaterial.setDiffuseColor(Color.SALMON);
	    salmonMaterial.setSpecularColor(Color.LIGHTSALMON);
	    System.out.println("salmonMaterial= " + salmonMaterial);
	    
	    coralMaterial.setDiffuseColor(Color.CORAL);
	    coralMaterial.setSpecularColor(Color.LIGHTCORAL);
	    System.out.println("coralMaterial= " + coralMaterial);
	    
	    magentaMaterial.setDiffuseColor(Color.DARKMAGENTA);
	    magentaMaterial.setSpecularColor(Color.MAGENTA);
	    System.out.println("magentaMaterial= " + magentaMaterial);
	    
	    orchidMaterial.setDiffuseColor(Color.DARKORCHID);
	    orchidMaterial.setSpecularColor(Color.ORCHID);
	    System.out.println("orchidMaterial= " + orchidMaterial);
	    
	    turquoiseMaterial.setDiffuseColor(Color.DARKTURQUOISE);
	    turquoiseMaterial.setSpecularColor(Color.TURQUOISE);
	    System.out.println("turquoiseMaterial= " + turquoiseMaterial);

	    yellowMaterial.setDiffuseColor(Color.LIGHTYELLOW);
	    yellowMaterial.setSpecularColor(Color.YELLOW);
	    System.out.println("yellowMaterial= " + yellowMaterial);
	    
	    whiteMaterial.setDiffuseColor(Color.WHITE);
	    whiteMaterial.setSpecularColor(Color.WHITE);
	    System.out.println("whiteMaterial= " + whiteMaterial);
	    
	    blackMaterial.setDiffuseColor(Color.BLACK);
	    blackMaterial.setSpecularColor(Color.BLACK);
	    System.out.println("blackMaterial= " + blackMaterial);

	    greyMaterial.setDiffuseColor(Color.DARKGREY);
	    greyMaterial.setSpecularColor(Color.GREY);
	    System.out.println("greyMaterial= " + greyMaterial);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}