 package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Draw3D;
import org.edisonwj.draw3d.Polygon3D;
import org.edisonwj.draw3d.Rand;
import org.edisonwj.draw3d.Triangle3D;

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
public class TestPolygon3 extends Application {
	private Draw3D dt;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestPolygon3");
		primaryStage.show();
	}
	
	private void buildData() {
	    
		dt.setCumulate(false);
		dt.setShowBoundaryCube(true);
		
		dt.setSequencingOn();
		System.out.println("Polygon1");
		Point3D[] v1 = {
				new Point3D( -5.0,  -2.6, 0),
				new Point3D(  -0.75,  -5.0, 0),
				new Point3D(  6.0,  -4.0, 0),
				new Point3D(  5.0,  5.4, 0),
//				new Point3D(  2.0, 2.4, 0),
				new Point3D(  3, 7.0, 0),
				new Point3D(  -5.0,  5.0, 0) };
		
		Polygon3D p1 = new Polygon3D(v1, new PhongMaterial(Color.CORNFLOWERBLUE));
		p1.setZ(4, 5, 6, 7);
		System.out.println("p1 isConvexPoly()= " + p1.isConvexPoly());
		dt.drawPolygon3D(p1);
		
		Point3D[] v2 = {
				new Point3D( -5.0,  -2.6, 0),
				new Point3D(  -0.75,  -5.0, 0),
				new Point3D(  6.0,  -4.0, 0),
				new Point3D(  5.0,  5.4, 0),
				new Point3D(  2.0, 2.4, 0),
//				new Point3D(  3, 7.0, 0),
				new Point3D(  -5.0,  5.0, 0) };
		 
		dt.incrSequence();
		Polygon3D p2 = new Polygon3D(v2, new PhongMaterial(Color.CORNFLOWERBLUE));
		p2.setZ(4, 5, 6, 7);
		dt.drawPolygon3D(p2);
		System.out.println("p2 isConvexPoly()= " + p2.isConvexPoly());

		Point3D[] v3 = { 
				new Point3D( 0.6402151359821230, 0.5074588632881751 , 0.0 ),
				new Point3D( 0.7082179660119566, 0.5222480464832023 , 0.0 ),
				new Point3D( 0.7424729745394019, 0.5375013435257088 , 0.0 ),
				new Point3D( 0.7420590518230206, 0.5569618520125615 , 0.0 ),
				new Point3D( 0.7063461093447063, 0.5673621815220089 , 0.0 ),
				new Point3D( 0.7091632502312475, 0.5708224246553932 , 0.0 ),
				new Point3D( 0.7071525699400316, 0.5718890910359129 , 0.0 ),
				new Point3D( 0.6788050710283865, 0.6249503108002923 , 0.0 ),
				new Point3D( 0.6662998500788007, 0.6322863440825490 , 0.0 ),
				new Point3D( 0.6634696668726516, 0.6931342688757285 , 0.0 ),
				new Point3D( 0.6936785451825833, 0.8675401954301549 , 0.0 ),
				new Point3D( 0.5257909970518804, 0.5947100193300610 , 0.0 ),
				new Point3D( 0.5277665212859461, 0.6491671574564812 , 0.0 ),
				new Point3D( 0.5303323367544164, 0.7440344083255197 , 0.0 ),
				new Point3D( 0.4909250971096550, 0.6533912671854594 , 0.0 ),
				new Point3D( 0.4866409123060239, 0.5841956737257807 , 0.0 ),
				new Point3D( 0.4421108573015147, 0.6727947790654440 , 0.0 ),
				new Point3D( 0.4549732805708249, 0.5941754871685234 , 0.0 ),
				new Point3D( 0.3285853140121448, 0.7374974596112968 , 0.0 ),
				new Point3D( 0.3065754361129810, 0.6709973663522576 , 0.0 ),
				new Point3D( 0.3013362478046763, 0.6643390460637538 , 0.0 ),
				new Point3D( 0.2572828606180418, 0.6545578504467537 , 0.0 ),
				new Point3D( 0.1287269714912100, 0.6744725895726364 , 0.0 ),
				new Point3D( 0.4519697519707827, 0.5084345112365946 , 0.0 ),
				new Point3D( 0.3948800877763691, 0.5153916606172074 , 0.0 ),
				new Point3D( 0.3046114058605581, 0.5163058172922240 , 0.0 ),
				new Point3D( 0.2876305058260997, 0.5109200041518589 , 0.0 ),
				new Point3D( 0.1475385371133400, 0.4750528389828104 , 0.0 ),
				new Point3D( 0.1796762838611786, 0.4490732261068599 , 0.0 ),
				new Point3D( 0.1294592500222945, 0.3936256098799296 , 0.0 ),
				new Point3D( 0.3225439192718041, 0.4214352276549366 , 0.0 ),
				new Point3D( 0.2666986785600286, 0.3698271515060545 , 0.0 ),
				new Point3D( 0.3343778653042966, 0.3935791618109563 , 0.0 ),
				new Point3D( 0.4184775697230658, 0.4353349247920723 , 0.0 ),
				new Point3D( 0.4976742521454954, 0.4970660441000407 , 0.0 ),
				new Point3D( 0.4564535747090654, 0.4370999822201377 , 0.0 ),
				new Point3D( 0.3538448112158794, 0.1987961451113828 , 0.0 ),
				new Point3D( 0.4869244996576755, 0.4431421273920585 , 0.0 ),
				new Point3D( 0.4838497995101707, 0.3310531743014332 , 0.0 ),
				new Point3D( 0.5155597999629054, 0.3038322154112599 , 0.0 ),
				new Point3D( 0.5697506783715511, 0.1682190849544248 , 0.0 ),
				new Point3D( 0.5459218400482159, 0.3980534140611096 , 0.0 ),
				new Point3D( 0.5532281428629542, 0.3866888784940473 , 0.0 ),
				new Point3D( 0.5542044223027428, 0.3853268614177374 , 0.0 ),
				new Point3D( 0.7942425727455709, 0.1594318281774929 , 0.0 ),
				new Point3D( 0.8494014666760727, 0.2087151481102407 , 0.0 ),
				new Point3D( 0.7311645321024652, 0.3904200674629252 , 0.0 ),
				new Point3D( 0.7619623182104607, 0.4261132650565905 , 0.0 ),
				new Point3D( 0.7420236265703650, 0.4451141932813297 , 0.0 ),
				new Point3D( 0.7552861562139712, 0.4474084891139871 , 0.0 ) };
		
		dt.incrSequence();
		Polygon3D p3 = new Polygon3D(v3, new PhongMaterial(Color.CORNFLOWERBLUE));
		p3.scale(10.0);
		dt.drawPolygon3D(p3);
		
		dt.incrSequence();
		Polygon3D p3z = new Polygon3D(v3, new PhongMaterial(Color.CORNFLOWERBLUE));
		p3z.setZ(4,5,6,7);
		p3z.scale(10.0);
		dt.drawPolygon3D(p3z);
		System.out.println("p3z isConvexPoly()= " + p3z.isConvexPoly());

		
		Point3D[] v4 = {
				new Point3D( 0.7848258039378482, 0.5581667762656738, 0.0 ),
				new Point3D( 0.6925820762946271, 0.5579843980105631, 0.0 ),
				new Point3D( 0.9036763132346342, 0.7101425321440947, 0.0 ),
				new Point3D( 0.8210725543265334, 0.6989959306876723, 0.0 ),
				new Point3D( 0.6695235679813135, 0.6429331306855246, 0.0 ),
				new Point3D( 0.7327386877283959, 0.7766693792018565, 0.0 ),
				new Point3D( 0.7339774199699337, 0.8274157503177460, 0.0 ),
				new Point3D( 0.6957837189406586, 0.7899739458730180, 0.0 ),
				new Point3D( 0.6491414243383893, 0.7898834929492609, 0.0 ),
				new Point3D( 0.6193236276791467, 0.7734530446325852, 0.0 ),
				new Point3D( 0.6198022406461943, 0.7772730675981754, 0.0 ),
				new Point3D( 0.5674515410126130, 0.7559383653851718, 0.0 ),
				new Point3D( 0.5443707600504344, 0.8155470231852502, 0.0 ),
				new Point3D( 0.5174064835249420, 0.7200355732383503, 0.0 ),
				new Point3D( 0.5009278038225249, 0.7395902793208078, 0.0 ),
				new Point3D( 0.4323070711155080, 0.8112827255732061, 0.0 ),
				new Point3D( 0.4496715048427805, 0.6071793913125997, 0.0 ),
				new Point3D( 0.4124875671313381, 0.6248763513872724, 0.0 ),
				new Point3D( 0.2473637618184144, 0.7655367349455682, 0.0 ),
				new Point3D( 0.4662668569704823, 0.5239440545795254, 0.0 ),
				new Point3D( 0.2435962292246230, 0.6265086353082223, 0.0 ),
				new Point3D( 0.0551489896373989, 0.6190050765575744, 0.0 ),
				new Point3D( 0.3286962389575612, 0.5238867372642039, 0.0 ),
				new Point3D( 0.2988159104684523, 0.5182477498669028, 0.0 ),
				new Point3D( 0.1921217956220425, 0.4554567292553475, 0.0 ),
				new Point3D( 0.1921217237803929, 0.4244540556753926, 0.0 ),
				new Point3D( 0.1840558289637617, 0.4092114435478456, 0.0 ),
				new Point3D( 0.2740566731964162, 0.4115283415626270, 0.0 ),
				new Point3D( 0.2557404771985918, 0.3969124812372754, 0.0 ),
				new Point3D( 0.3044561924420106, 0.3948597368705811, 0.0 ),
				new Point3D( 0.2521257476492340, 0.3125319447281395, 0.0 ),
				new Point3D( 0.2412996586586690, 0.2956297869397313, 0.0 ),
				new Point3D( 0.2704606198652386, 0.2657386346533200, 0.0 ),
				new Point3D( 0.2571914662874542, 0.1920298216663382, 0.0 ),
				new Point3D( 0.2664018406099264, 0.1819422653866031, 0.0 ),
				new Point3D( 0.3510264380631352, 0.2200588607540121, 0.0 ),
				new Point3D( 0.3868298744368723, 0.1491798020023399, 0.0 ),
				new Point3D( 0.4836605175315121, 0.3555130083299076, 0.0 ),
				new Point3D( 0.4833615755162603, 0.3186087496743693, 0.0 ),
				new Point3D( 0.4988013136193688, 0.3484636301903227, 0.0 ),
				new Point3D( 0.5033997792047885, 0.3699694334986929, 0.0 ),
				new Point3D( 0.5103773264983578, 0.3295925147427883, 0.0 ),
				new Point3D( 0.5584116930897767, 0.2352100518446950, 0.0 ),
				new Point3D( 0.6398069987515533, 0.2116265913175590, 0.0 ),
				new Point3D( 0.6403634017979033, 0.2896208515300871, 0.0 ),
				new Point3D( 0.6007651097959354, 0.3750620635462061, 0.0 ),
				new Point3D( 0.5278065941847558, 0.4765046290509977, 0.0 ),
				new Point3D( 0.7169806650580048, 0.3620376736222380, 0.0 ),
				new Point3D( 0.8885581481448122, 0.3650686068035837, 0.0 ),
				new Point3D( 0.8395647014028211, 0.4299856415569005, 0.0 ) };
		
		dt.incrSequence();
		Polygon3D p4 = new Polygon3D(v4, new PhongMaterial(Color.CORNFLOWERBLUE));
		p4.scale(10.0);
		dt.drawPolygon3D(p4);
	
		dt.incrSequence();
		Polygon3D p4z = new Polygon3D(v4, new PhongMaterial(Color.CORNFLOWERBLUE));
		p4z.setZ(4,5,6,7);
		p4z.scale(10.0);
		dt.drawPolygon3D(p4z);
		System.out.println("p4z isConvexPoly()= " + p4z.isConvexPoly());

		Rand mr = new Rand();
		
		dt.incrSequence();
		Point3D[] v4z = p4z.getV();
		int[][] t = p4z.triangulatePolygon();
		Triangle3D[] t4D = new Triangle3D[t.length];
		for (int i = 0; i < t.length; i++) {
//			dt.setDrawColor(Color.BLACK);
//			dt.drawArrow3D(new Arrow3D(v4z[t[i][0]], v4z[t[i][1]]));
//			dt.drawArrow3D(new Arrow3D(v4z[t[i][1]], v4z[t[i][2]]));
//			dt.drawArrow3D(new Arrow3D(v4z[t[i][2]], v4z[t[i][0]]));
			int r = mr.uniform(0,  255);
			int g = mr.uniform(0,  255);
			int b = mr.uniform(0,  255);
			t4D[i] = new Triangle3D(v4z[t[i][0]], v4z[t[i][1]], v4z[t[i][2]], new PhongMaterial(Color.rgb(r, g, b))); 
			dt.drawTriangle3D(t4D[i]);
		}
	
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}