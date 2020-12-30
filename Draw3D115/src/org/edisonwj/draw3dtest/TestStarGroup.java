package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Arrow3D;
import org.edisonwj.draw3d.Draw3D;
import org.edisonwj.draw3d.Polygon3D;

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
public class TestStarGroup extends Application {
	private Draw3D dt;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestStarGroup");
		primaryStage.show();
	}
	
	private void buildData() {
		
		double a = 2;
		double b = 3;
		double c = 4;
		double d = 5;
	    
		dt.setCumulate(false);
		dt.setShowBoundaryCube(true);
		
		Point3D[] v01 = {
				new Point3D( 0.5711756267848119, 0.5683135093878718, 0.0),
				new Point3D( 0.4753697622585127, 0.9824508894381954, 0.0),
				new Point3D( 0.2901604317558946, 0.5506091455731394, 0.0),
				new Point3D( 0.3376049809907715, 0.5246477404042422, 0.0),
				new Point3D( 0.2236729240700464, 0.2435183840521773, 0.0),
				new Point3D( 0.3687424231064096, 0.1891591747985080, 0.0),
				new Point3D( 0.4743799316694006, 0.0204843828304860, 0.0),
				new Point3D( 0.5454013797965978, 0.2920592666048716, 0.0),
				new Point3D( 0.6425921408834337, 0.1209307527042393, 0.0),
				new Point3D( 0.7910144845048505, 0.4349620424752564, 0.0)
				};

				Point3D[] v02 = {
				new Point3D( 0.5298589273733031, 0.5078697255217236, 0.0),
				new Point3D( 0.8702265286407163, 0.7694445330503243, 0.0),
				new Point3D( 0.5215840505378707, 0.5565210039488699, 0.0),
				new Point3D( 0.2830819602106010, 0.9430334783520955, 0.0),
				new Point3D( 0.1820350884683719, 0.8302893844065489, 0.0),
				new Point3D( 0.2989771976100100, 0.4911997850801412, 0.0),
				new Point3D( 0.2532869421478251, 0.2304437563552181, 0.0),
				new Point3D( 0.5274140525450131, 0.0679891557413269, 0.0),
				new Point3D( 0.5362472611232982, 0.1469787311105175, 0.0),
				new Point3D( 0.5511151041091965, 0.4537640263081345, 0.0)
				};

				Point3D[] v03 = {
				new Point3D( 0.6112407076499076, 0.5135997352006992, 0.0),
				new Point3D( 0.5033681131028138, 0.5061427773972192, 0.0),
				new Point3D( 0.5142819375222916, 0.5280294512765521, 0.0),
				new Point3D( 0.3426170325221912, 0.6616671689316448, 0.0),
				new Point3D( 0.4642683927447597, 0.5196015641667991, 0.0),
				new Point3D( 0.2897219027820875, 0.3090467350119001, 0.0),
				new Point3D( 0.5002238949046766, 0.4941928546860511, 0.0),
				new Point3D( 0.5110444571031169, 0.4775957776278701, 0.0),
				new Point3D( 0.7338972239457258, 0.4427679290058698, 0.0),
				new Point3D( 0.7064251331389490, 0.4556294634794037, 0.0)
				};

				Point3D[] v04 = {
				new Point3D( 0.6108537091122277, 0.5588081323119324, 0.0),
				new Point3D( 0.6283391607159644, 0.7107529800631309, 0.0),
				new Point3D( 0.6110147883543944, 0.6942527061219208, 0.0),
				new Point3D( 0.5332179857392513, 0.8316943679025195, 0.0),
				new Point3D( 0.2479733020342640, 0.6790219142698590, 0.0),
				new Point3D( 0.1738338345595367, 0.6731840061311622, 0.0),
				new Point3D( 0.1860632742828540, 0.6572646091743485, 0.0),
				new Point3D( 0.3360745766157910, 0.4821911002462840, 0.0),
				new Point3D( 0.2706110029509458, 0.1679066000807661, 0.0),
				new Point3D( 0.7633765144136033, 0.1782363938002762, 0.0)
				};

				Point3D[] v05 = {
				new Point3D( 0.5901148728497516, 0.5991736293361123, 0.0),
				new Point3D( 0.4858272939301129, 0.7098076756482100, 0.0),
				new Point3D( 0.3676858218294706, 0.6589934301170337, 0.0),
				new Point3D( 0.2689530205503081, 0.5758418074978411, 0.0),
				new Point3D( 0.1396440584729648, 0.5120286463020008, 0.0),
				new Point3D( 0.0934543205193049, 0.4578194597377335, 0.0),
				new Point3D( 0.4028555602681904, 0.4577745743032052, 0.0),
				new Point3D( 0.4394182525525001, 0.4359559412538361, 0.0),
				new Point3D( 0.4532418590333263, 0.0394256586406395, 0.0),
				new Point3D( 0.5877475491455937, 0.3657676220220820, 0.0)
				};

				Point3D[] v06 = {
				new Point3D( 0.8290874791854355, 0.8630433557798263, 0.0),
				new Point3D( 0.4306719705612429, 0.7641493430592731, 0.0),
				new Point3D( 0.4553322062645371, 0.5912860119239174, 0.0),
				new Point3D( 0.3566597730247031, 0.6511593842790091, 0.0),
				new Point3D( 0.3760693304682716, 0.4886666850699842, 0.0),
				new Point3D( 0.2926825701714057, 0.4271493743999352, 0.0),
				new Point3D( 0.4067095153481196, 0.2189757344999730, 0.0),
				new Point3D( 0.4889165769434366, 0.2499186204923028, 0.0),
				new Point3D( 0.5177033668546878, 0.4782641179182657, 0.0),
				new Point3D( 0.6044355894202648, 0.4032324700122863, 0.0)
				};

				Point3D[] v07 = {
				new Point3D( 0.5372195748012613, 0.5217139356185251, 0.0),
				new Point3D( 0.7719146539059274, 0.6664095353415436, 0.0),
				new Point3D( 0.4933909960065397, 0.6103432792390339, 0.0),
				new Point3D( 0.1487473936076639, 0.7407118408310522, 0.0),
				new Point3D( 0.3731845476169005, 0.4434848367541501, 0.0),
				new Point3D( 0.1969864306626740, 0.2840109316064202, 0.0),
				new Point3D( 0.3056645522474105, 0.2799058951082335, 0.0),
				new Point3D( 0.5576315765913932, 0.0858778575200759, 0.0),
				new Point3D( 0.5984753767598621, 0.2850037545274021, 0.0),
				new Point3D( 0.8285049574608834, 0.2359926455299881, 0.0)
				};

				Point3D[] v08 = {
				new Point3D( 0.6956468924981190, 0.5141880485428278, 0.0),
				new Point3D( 0.8972468568112143, 0.7526904030308472, 0.0),
				new Point3D( 0.5795216330285289, 0.7224152197515162, 0.0),
				new Point3D( 0.5749760932057697, 0.7339392212900642, 0.0),
				new Point3D( 0.4254706692543792, 0.6090218137888143, 0.0),
				new Point3D( 0.2476681581352813, 0.5833722367422282, 0.0),
				new Point3D( 0.0962243799416855, 0.3060650116501156, 0.0),
				new Point3D( 0.3133513264677371, 0.3221397210545046, 0.0),
				new Point3D( 0.7068203575126575, 0.2702286379490174, 0.0),
				new Point3D( 0.6497113248851336, 0.4682245721419804, 0.0)
				};

				Point3D[] v09 = {
				new Point3D( 0.8316450881012138, 0.7953715848460029, 0.0),
				new Point3D( 0.5750416351171430, 0.9352984259126711, 0.0),
				new Point3D( 0.4677738428671820, 0.7315525304071405, 0.0),
				new Point3D( 0.4989416428832281, 0.5017073744285250, 0.0),
				new Point3D( 0.0343258684116244, 0.5263984717176323, 0.0),
				new Point3D( 0.4055245505697747, 0.3890748402761927, 0.0),
				new Point3D( 0.4016014928754668, 0.3826463334691038, 0.0),
				new Point3D( 0.3668926002840969, 0.1058700250819404, 0.0),
				new Point3D( 0.5912856847466065, 0.2813013390291673, 0.0),
				new Point3D( 0.5249586365534520, 0.4791793270464104, 0.0)
				};

				Point3D[] v10 = {
				new Point3D( 0.5221265330331841, 0.5039445483452990, 0.0),
				new Point3D( 0.6741862012153330, 0.9274434355139430, 0.0),
				new Point3D( 0.4790850069603272, 0.5371620392723435, 0.0),
				new Point3D( 0.3403402587076026, 0.7203500488584333, 0.0),
				new Point3D( 0.3392854176104208, 0.6941783634950675, 0.0),
				new Point3D( 0.0979511061466199, 0.4605979312924602, 0.0),
				new Point3D( 0.2860716395387870, 0.3203701445970443, 0.0),
				new Point3D( 0.4525112955503422, 0.3667315640853641, 0.0),
				new Point3D( 0.5064517678915382, 0.2789194051180433, 0.0),
				new Point3D( 0.6827408987102017, 0.2316193574925055, 0.0)
				};

				Point3D[] v11 = {
				new Point3D( 0.7089679605837387, 0.8152638091210505, 0.0),
				new Point3D( 0.5760581730266641, 0.6161581150538165, 0.0),
				new Point3D( 0.5686355525321045, 0.6962182940107093, 0.0),
				new Point3D( 0.3870069016950651, 0.8685522618993453, 0.0),
				new Point3D( 0.3321622605357872, 0.6370455494569319, 0.0),
				new Point3D( 0.0550279995668093, 0.5257366674902813, 0.0),
				new Point3D( 0.3733639984970428, 0.4379103173572396, 0.0),
				new Point3D( 0.4933447404472618, 0.4399895904655619, 0.0),
				new Point3D( 0.5070223808851883, 0.4791855490782288, 0.0),
				new Point3D( 0.7804560384969190, 0.2499023792132553, 0.0)
				};

				Point3D[] v12 = {
				new Point3D( 0.9381017413264696, 0.5890268003064125, 0.0),
				new Point3D( 0.8389638294915440, 0.6281201914315496, 0.0),
				new Point3D( 0.5783147078228955, 0.7966741191702917, 0.0),
				new Point3D( 0.3123825922778773, 0.7488728974121839, 0.0),
				new Point3D( 0.4175517390931845, 0.5709510559071429, 0.0),
				new Point3D( 0.3735821787090366, 0.4989301956045856, 0.0),
				new Point3D( 0.4760152431091921, 0.4771160827612194, 0.0),
				new Point3D( 0.5060424376958734, 0.3020765210582101, 0.0),
				new Point3D( 0.8242361874050484, 0.2401644377994483, 0.0),
				new Point3D( 0.7964277763171760, 0.4924978245540527, 0.0)
				};

				Point3D[] v13 = {
				new Point3D( 0.6682483617427625, 0.6653307337658924, 0.0),
				new Point3D( 0.6363487181706728, 0.7454586071131998, 0.0),
				new Point3D( 0.4668387789601520, 0.8299125638116136, 0.0),
				new Point3D( 0.4585842622203568, 0.8419498229987168, 0.0),
				new Point3D( 0.3342710611066002, 0.6329356368876068, 0.0),
				new Point3D( 0.4896340268641795, 0.5014832957506975, 0.0),
				new Point3D( 0.1175583796657083, 0.2222405257315477, 0.0),
				new Point3D( 0.2814606890567772, 0.0528160391458149, 0.0),
				new Point3D( 0.3967169703343270, 0.1627501382336154, 0.0),
				new Point3D( 0.6591072250723209, 0.3570824731173112, 0.0)
				};

				Point3D[] v14 = {
				new Point3D( 0.6125486015059030, 0.6089332008299139, 0.0),
				new Point3D( 0.6264928831497087, 0.7199417031481419, 0.0),
				new Point3D( 0.6325846286148983, 0.7548434901638419, 0.0),
				new Point3D( 0.4529095804473636, 0.9053854588778378, 0.0),
				new Point3D( 0.1860904080048711, 0.8128768886714122, 0.0),
				new Point3D( 0.0384186081256485, 0.6170270440989818, 0.0),
				new Point3D( 0.1372655697921835, 0.2264426879030321, 0.0),
				new Point3D( 0.4429775763108015, 0.3600576971841337, 0.0),
				new Point3D( 0.6850524127909660, 0.1250465162242552, 0.0),
				new Point3D( 0.6298563963324171, 0.4101441048410557, 0.0)
				};

				Point3D[] v15 = {
				new Point3D( 0.5194820126975132, 0.5226130989692729, 0.0),
				new Point3D( 0.3579104567034994, 0.9532039396697489, 0.0),
				new Point3D( 0.4294428601954853, 0.6378765631655279, 0.0),
				new Point3D( 0.4284097236817113, 0.5541274970732146, 0.0),
				new Point3D( 0.2546126826246557, 0.3399570312921352, 0.0),
				new Point3D( 0.4978642039723619, 0.2703365172243009, 0.0),
				new Point3D( 0.5036734099507748, 0.2929908535678231, 0.0),
				new Point3D( 0.5161275480067742, 0.3747384144822483, 0.0),
				new Point3D( 0.8535262536955535, 0.4353093837118066, 0.0),
				new Point3D( 0.8838591351217665, 0.4640696287468453, 0.0)
				};
				 
				Point3D[] v16 = {
				new Point3D( 0.7685345747775925, 0.6876458258332810, 0.0),
				new Point3D( 0.4828275297733826, 0.7066132458223149, 0.0),
				new Point3D( 0.2398387803491439, 0.6805549446498038, 0.0),
				new Point3D( 0.2283926586108802, 0.5925928444665647, 0.0),
				new Point3D( 0.3679236542613865, 0.3882744770873661, 0.0),
				new Point3D( 0.3719732992401676, 0.3899498099340622, 0.0),
				new Point3D( 0.4965229816675194, 0.2395943807553007, 0.0),
				new Point3D( 0.5109340943693109, 0.2710310886494885, 0.0),
				new Point3D( 0.5146391930771403, 0.4779286415644897, 0.0),
				new Point3D( 0.9440715245627069, 0.4083210292470099, 0.0)
				};
				 
				Point3D[] v17 = {
				new Point3D( 0.5802975141211402, 0.5152476698003216, 0.0),
				new Point3D( 0.6091010569427239, 0.8419171284721023, 0.0),
				new Point3D( 0.4800430499809027, 0.5340438504177702, 0.0),
				new Point3D( 0.4341334162377652, 0.6011463130781366, 0.0),
				new Point3D( 0.4178617944459601, 0.5696740647071644, 0.0),
				new Point3D( 0.2947522382153464, 0.4853167873160161, 0.0),
				new Point3D( 0.2306531038623482, 0.3011262251449519, 0.0),
				new Point3D( 0.3175754584334500, 0.3331663855056892, 0.0),
				new Point3D( 0.5012160376811888, 0.4458090692005452, 0.0),
				new Point3D( 0.5069669803475462, 0.4943964985403648, 0.0)
				};
				 
				Point3D[] v18 = {
				new Point3D( 0.6454109621899056, 0.6062336087016236, 0.0),
				new Point3D( 0.5052675768520563, 0.8239065658347959, 0.0),
				new Point3D( 0.3812216307714268, 0.8411789902901412, 0.0),
				new Point3D( 0.4517081300533136, 0.5289144073750507, 0.0),
				new Point3D( 0.0147014613347169, 0.3974006122016991, 0.0),
				new Point3D( 0.3714633338756297, 0.4279236397358313, 0.0),
				new Point3D( 0.4671240420876298, 0.0070156087420814, 0.0),
				new Point3D( 0.5248288265126939, 0.2502223712557909, 0.0),
				new Point3D( 0.8094453463251556, 0.2662123620363595, 0.0),
				new Point3D( 0.7062080780670676, 0.3664939294663014, 0.0)
				};
				  
				 Point3D[] v19 = {
				new Point3D( 0.7629273880946272, 0.7457947904702357, 0.0),
				new Point3D( 0.5053878336104205, 0.8768677500440360, 0.0),
				new Point3D( 0.4870525541743183, 0.8095635099144678, 0.0),
				new Point3D( 0.4711608924472910, 0.5960581934871344, 0.0),
				new Point3D( 0.4232473585513845, 0.6839390776701127, 0.0),
				new Point3D( 0.3579824405835170, 0.5053171591306063, 0.0),
				new Point3D( 0.3803276152373691, 0.4426404658363810, 0.0),
				new Point3D( 0.3890516642303775, 0.0318968450309720, 0.0),
				new Point3D( 0.6655319873122195, 0.2286338486638443, 0.0),
				new Point3D( 0.8260967775221736, 0.4292867242637549, 0.0)
				};
				  
				Point3D[] v20 = {
				new Point3D( 0.7347171333447267, 0.8242157959380257, 0.0),
				new Point3D( 0.5405444381565829, 0.6782397702975632, 0.0),
				new Point3D( 0.4809443650571255, 0.5972489729699405, 0.0),
				new Point3D( 0.4359116377465380, 0.5403275826157845, 0.0),
				new Point3D( 0.3935737753407177, 0.4529562321933397, 0.0),
				new Point3D( 0.3605511424201139, 0.4318495724552558, 0.0),
				new Point3D( 0.3842751461195094, 0.2252221943425564, 0.0),
				new Point3D( 0.5304628238665814, 0.3703584399298269, 0.0),
				new Point3D( 0.8348988296100434, 0.3420267568112365, 0.0),
				new Point3D( 0.6398395248407192, 0.4659078562969594, 0.0)
				};
				
		
		dt.incrSequence();
		dt.setArrowRadius(.5);
		System.out.println("Polygon01");
		Polygon3D p01 = new Polygon3D(v01, new PhongMaterial(Color.ANTIQUEWHITE));
		p01.scale(10.0);
		p01.setZ(a, b, c, d);
		dt.setPointSize(1);
		dt.setDrawColor(Color.RED);
		Point3D[] v01s = p01.getV();
		for (int i= 0; i < v01s.length; i++) {
			dt.drawPoint3D(v01s[i]);
			dt.drawArrow3D(new Arrow3D(v01s[i], v01s[(i+1)%v01s.length]));
			dt.drawLabel(v01s[i].getX(), v01s[i].getY(), v01s[i].getZ(), "V" + i);
			dt.setDrawColor(Color.BLACK);
		}
		dt.drawPolygon3D(p01);
		
		dt.incrSequence();
		System.out.println("Polygon02");
		Polygon3D p02 = new Polygon3D(v02, new PhongMaterial(Color.CORNFLOWERBLUE));
		p02.scale(10.0);
		p02.setZ(a, b, c, d);
		dt.setPointSize(1);
		dt.setDrawColor(Color.RED);
		Point3D[] v02s = p02.getV();
		for (int i= 0; i < v02s.length; i++) {
			dt.drawPoint3D(v02s[i]);
			dt.drawArrow3D(new Arrow3D(v02s[i], v02s[(i+1)%v02s.length]));
			dt.drawLabel(v02s[i].getX(), v02s[i].getY(), v02s[i].getZ(), "V" + i);
			dt.setDrawColor(Color.BLACK);
		}
		dt.drawPolygon3D(p02);
	
		dt.incrSequence();
		System.out.println("Polygon03");
		Polygon3D p03 = new Polygon3D(v03, new PhongMaterial(Color.CORNFLOWERBLUE));
		p03.scale(10.0);
		p03.setZ(a, b, c, d);
		dt.setPointSize(1);
		dt.setDrawColor(Color.RED);
		Point3D[] v03s = p03.getV();
		for (int i= 0; i < v03s.length; i++) {
			dt.drawPoint3D(v03s[i]);
			dt.drawArrow3D(new Arrow3D(v03s[i], v03s[(i+1)%v03s.length]));
			dt.drawLabel(v03s[i].getX(), v03s[i].getY(), v03s[i].getZ(), "V" + i);
			dt.setDrawColor(Color.BLACK);
		}
 		dt.drawPolygon3D(p03);

		dt.incrSequence();
		System.out.println("Polygon04");
		Polygon3D p04 = new Polygon3D(v04, new PhongMaterial(Color.CORNFLOWERBLUE));
		p04.scale(10.0);
		p04.setZ(a, b, c, d);
		dt.drawPolygon3D(p04);
		
		dt.incrSequence();
		System.out.println("Polygon05");
		Polygon3D p05 = new Polygon3D(v05, new PhongMaterial(Color.CORNFLOWERBLUE));
		p05.scale(10.0);
		p05.setZ(a, b, c, d);
		dt.drawPolygon3D(p05);
		
		
		dt.incrSequence();
		Polygon3D p06 = new Polygon3D(v06, new PhongMaterial(Color.CORNFLOWERBLUE));
		p06.scale(10.0);
		p06.setZ(a, b, c, d);
		dt.drawPolygon3D(p06);
		
		dt.incrSequence();
		Polygon3D p07 = new Polygon3D(v07, new PhongMaterial(Color.CORNFLOWERBLUE));
		p07.scale(10.0);
		p07.setZ(a, b, c, d);
		dt.drawPolygon3D(p07);
		
		dt.incrSequence();
		Polygon3D p08 = new Polygon3D(v08, new PhongMaterial(Color.CORNFLOWERBLUE));
		p08.scale(10.0);
		p08.setZ(a, b, c, d);
		dt.drawPolygon3D(p08);
		
		dt.incrSequence();
		Polygon3D p09 = new Polygon3D(v09, new PhongMaterial(Color.CORNFLOWERBLUE));
		p09.scale(10.0);
		p09.setZ(a, b, c, d);
		dt.drawPolygon3D(p09);
		
		dt.incrSequence();
		Polygon3D p10 = new Polygon3D(v10, new PhongMaterial(Color.CORNFLOWERBLUE));
		p10.scale(10.0);
		p10.setZ(a, b, c, d);
		dt.drawPolygon3D(p10);
		
		dt.incrSequence();
		Polygon3D p11 = new Polygon3D(v11, new PhongMaterial(Color.CORNFLOWERBLUE));
		p11.scale(10.0);
		p11.setZ(a, b, c, d);
		dt.drawPolygon3D(p11);
		
		dt.incrSequence();
		Polygon3D p12 = new Polygon3D(v12, new PhongMaterial(Color.CORNFLOWERBLUE));
		p12.scale(10.0);
		p12.setZ(a, b, c, d);
		dt.drawPolygon3D(p12);
		
		dt.incrSequence();
		Polygon3D p13 = new Polygon3D(v13, new PhongMaterial(Color.CORNFLOWERBLUE));
		p13.scale(10.0);
		p13.setZ(a, b, c, d);
		dt.drawPolygon3D(p13);
		
		dt.incrSequence();
		Polygon3D p14 = new Polygon3D(v14, new PhongMaterial(Color.CORNFLOWERBLUE));
		p14.scale(10.0);
		p14.setZ(a, b, c, d);
		dt.drawPolygon3D(p14);
		
		dt.incrSequence();
		Polygon3D p15 = new Polygon3D(v15, new PhongMaterial(Color.CORNFLOWERBLUE));
		p15.scale(10.0);
		p15.setZ(a, b, c, d);
		dt.drawPolygon3D(p15);
		
		dt.incrSequence();
		Polygon3D p16 = new Polygon3D(v16, new PhongMaterial(Color.CORNFLOWERBLUE));
		p16.scale(10.0);
		p16.setZ(a, b, c, d);
		dt.drawPolygon3D(p16);
		
		dt.incrSequence();
		Polygon3D p17 = new Polygon3D(v17, new PhongMaterial(Color.CORNFLOWERBLUE));
		p17.scale(10.0);
		p17.setZ(a, b, c, d);
		dt.drawPolygon3D(p17);
		
		dt.incrSequence();
		Polygon3D p18 = new Polygon3D(v18, new PhongMaterial(Color.CORNFLOWERBLUE));
		p18.scale(10.0);
		p18.setZ(a, b, c, d);
		dt.drawPolygon3D(p18);
		
		dt.incrSequence();
		Polygon3D p19 = new Polygon3D(v19, new PhongMaterial(Color.CORNFLOWERBLUE));
		p19.scale(10.0);
		p19.setZ(a, b, c, d);
		dt.drawPolygon3D(p19);
		
		dt.incrSequence();
		Polygon3D p20 = new Polygon3D(v20, new PhongMaterial(Color.CORNFLOWERBLUE));
		p20.scale(10.0);
		p20.setZ(a, b, c, d);
		dt.drawPolygon3D(p20);

	}
	
	public static void main(String[] args) {
		launch(args);
	}
}