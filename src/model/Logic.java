package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

import exception.NameLength;
import exception.NoName;
import processing.core.PApplet;
import processing.core.PImage;
import view.DefeatScreen;
import view.Game1Screen;
import view.Game2Screen;
import view.Game3Screen;
import view.HomeScreen;
import view.NameScreen;
import view.ResultScreen;
import view.ScoreScreen;
import view.VictoryScreen;

public class Logic {
	private PApplet app;
	private ArrayList<Cloud> clouds;
	private LinkedList<Player> playerList;
	private LinkedList<Game> gamesList;
	private ByName sortByName;
	private ByDate sortByDate;
	private ByTime sortByTime;
	private MainCharacter[][] matrix;
	private int[][] barrier;
	private Coin[][] coins;
	private Medicine[][] medicines;
	private Enemy[][] enemies;
	private int cordX;
	private int cordY;
	private int posX;
	private int posY;
	private boolean coin1;
	private boolean coin2;
	private boolean medicine1;
	private boolean medicine2;
	private boolean enemy1;
	private boolean enemy2;
	private boolean firstPaint;
	private boolean secondPaint;
	private boolean thirdPaint;
	private Timer animation;
	private TimerTask gravity;
	NameScreen name;
	HomeScreen home;
	ScoreScreen score;
	Game1Screen game1;
	Game2Screen game2;
	Game3Screen game3;
	ResultScreen result;
	VictoryScreen victory;
	DefeatScreen defeat;
	Date date;
	SimpleDateFormat datePrintter;
	int timer;
	int countsec;
	int countmin;
	int sec;
	int scores; //la instancia de ScoreScreen se llama score
	PImage lucasimg;
	String time1;
	String time2;
	public int screen;
	
	
	public Logic(PApplet app) {
		this.app = app;
		clouds = new ArrayList<Cloud>();
		playerList = new LinkedList<Player>();
		gamesList = new LinkedList<Game>();
		lucasimg = app.loadImage("img/Lucas.png");
		
		coin1 = true;
		coin2 = true;
		medicine1 = true;
		medicine2 = true;
		enemy1=true;
		enemy2=true;
		firstPaint = true;
		secondPaint = true;
		thirdPaint = true;
		matrix = new MainCharacter[9][19];
		barrier = new int[9][19];
		coins = new Coin[9][19];
		medicines = new Medicine[9][19];
		enemies = new Enemy[9][19];
		name = new NameScreen(app);
		home = new HomeScreen(app);
		score = new ScoreScreen(app);
		game1 = new Game1Screen(app);
		game2 = new Game2Screen(app);
		game3 = new Game3Screen(app);
		result = new ResultScreen(app);
		victory = new VictoryScreen(app);
		defeat = new DefeatScreen(app);
		name.textFields();
		screen = 0;
		time1="";
		time2="";
		date = new Date();
		datePrintter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		sortByName= new ByName();
		sortByDate= new ByDate();
		sortByTime= new ByTime();
		
		animation = new Timer();
		gravity = new TimerTask() {
			@Override
			public void run() {
				try {
					gravity();
				}
				catch(Exception e) {
					e.getLocalizedMessage();
				}
			}
		};
		
		animation.schedule(gravity, 10, 500);
		
		//Nubes
		for (int i = 0; i <6; i++) {
			float posX = app.random (-50,1150);
			float posY = app.random (-50,130);
			
			clouds.add(new Cloud (posX, posY, app));
		}
	}
	
	public void changeScreen() {
		//
		switch (screen) {
		// Name
		case 0:
			name.draw();
			name.show();
			break;
		// Home
		case 1:
			name.hide();
			home.draw();
			break;
		// Nivel1
		case 2:
			if(firstPaint) {
				cordX = 6;
				cordY = 0;
				posX = 0;
				posY = 0;
				calcPos();
				createLucas();
				createCoins1();
				createMedical1();
				createEnemy1();
				drawFloor1();
				scores = 0;
				timer=0;
				countsec=0;
				countmin=0;
				firstPaint=false;
			}
			drawCloud();
			game1.draw();
			
			//Activar cronometro
			Chrono();
			
			//Dibujar personaje y fondo
			
			drawCoins1();
			deleteCoin1();
			
			drawMedical1();
			deleteMedical1();
			
			drawEnemy1();
			loseEnemy1();
			
			app.fill(238,19,19);
			app.textSize(25);
			app.createFont("arial",16);
			app.text(scores, 1100, 20);
			
			drawChar();
			drawFloor1();
			winScreen();
			lose();
			
			break;
		// Nivel2
		case 3:
			if(secondPaint) {
				cordX = 6;
				cordY = 0;
				posX = 0;
				posY = 0;
				calcPos();
				createLucas();
				createCoins2();
				createMedical2();
				createEnemy2();
				drawFloor2();
				secondPaint=false;
			}
			drawCloud();
			game2.draw();
			Chrono();
			drawCoins2();
			deleteCoin2();
			
			drawMedical2();
			deleteMedical2();
			
			drawEnemy2();
			loseEnemy2();
			
			app.createFont("arial",16);
			app.text(scores, 1100, 20);
			
			drawChar();
			drawFloor2();
			winScreen();
			lose();
			
			break;
		// Nivel3
		case 4:
			if(thirdPaint) {
				cordX = 3;
				cordY = 0;
				posX = 0;
				posY = 0;
				calcPos();
				createLucas();
				createCoins3();
				createMedical3();
				createEnemy3();
				drawFloor3();
				thirdPaint=false;
			}
			drawCloud();
			game3.draw();
			Chrono();
			drawCoins3();
			deleteCoin3();
			
			drawMedical3();
			deleteMedical3();
			
			drawEnemy3();
			loseEnemy3();
			
			app.createFont("arial",16);
			app.text(scores, 1100, 20);
			
			drawChar();
			drawFloor3();
			winScreen();
			lose();
			
			break;
		// Resumen del juego
		case 5:
			result.draw();
			app.fill(0);
			app.textSize(20);
			app.text(gamesList.get(gamesList.size()-1).getScore(),365,389);
			app.text(gamesList.get(gamesList.size()-1).getTime()+" segundos",365,447);
			break;
		// Pantalla de victoria
		case 6:
			victory.draw();
			break;
		// Pantalla de derrota
		case 7:
			defeat.draw();
			break;
		// Lista de puntajes	
		case 8:
		//	System.out.println(gamesList.get(0).getPlayName());
		//	System.out.println(gamesList.get(0).getScore());
		//	System.out.println(gamesList.get(0).getDate());
		//	System.out.println(gamesList.get(0).getTime());
			score.draw();
			for (int i = 0; i < gamesList.size(); i++) {
				app.fill(0);
				app.textSize(20);
				app.text(gamesList.get(i).getPlayName(), 137, 292+(30*(i)));
				app.text(gamesList.get(i).getScore(), 383, 292+(30*(i)));
				app.text(datePrintter.format(gamesList.get(i).getDate()), 597, 292+(30*(i)));
				/*
				String tiempo = String.valueOf(gamesList.get(i).getTime());
				if(tiempo.length()==2) {
				time1=tiempo.substring(0,1);
				time2=tiempo.substring(1,2);
				}else if(tiempo.length()==3){
				time1=tiempo.substring(0,1);
				time2=tiempo.substring(1,3);
				}
				*/
				app.text(gamesList.get(i).getTime()+" segundos", 897, 292+(30*(i)));
			}
			break;

		default:
			break;
		}
	}
	//=============================================================//
	public void Chrono() {
		//Para calcular el tiempo
		sec = PApplet.second();
		if (sec > timer) {
			timer = sec;
			countsec++;
		}
		if (sec > 60) {
			sec = 0;
			
		}
		if (countsec >= 60) {
			countmin++;
		}
		app.fill(238,19,19);
		app.textSize(25);
		app.text(countmin + ":" + countsec, 905, 20);
	}
	//=============================================================//
	
	public void calcPos() {
		posX = (cordY*60)+30;
		posY = (cordX*60)+160;
	}
	
	//=============================================================//
	
	public void createLucas() {
		matrix[cordX][cordY] = new MainCharacter(posX,posY,lucasimg,app);
	}
	
	//=============================================================//
	
	public void findLucas() {
		for(int x = 0; x < 10; x++) {
			for(int y = 0; y < 20; y++) {
				if(matrix[x][y] != null) {
					cordX = x;
					cordY = y;
				}
			}
		}
	}
	
	//=============================================================//
	
	public void drawChar(){
		try {
			if(matrix[cordX][cordY] != null) {
				matrix[cordX][cordY].draw();
			}
		}
		catch(Exception e) {
			e.getLocalizedMessage();
		}
	}
	
	//=============================================================//
	// CREAR MONEDAS
	//=============================================================//
	
	public void createCoins1() { //Crear Monedas Nivel 1
		coins[1][17] = new Coin(1040, 223,50, app);
	}
	
	public void createCoins2() { //Crear Monedas Nivel 2
		coins[7][8] = new Coin(500, 595,42, app);
		coins[4][18] = new Coin(1100, 413,42, app);
	}
	
	public void createCoins3() { //Crear Monedas Nivel 3
		coins[7][4] = new Coin(263, 593,42, app);
		coins[0][10] = new Coin(616, 179,42, app);
	}
	
	//=============================================================//
	// CREAR SUMINISTROS MÉDICOS
	//=============================================================//
	
	public void createMedical1() { //Crear Suministros Nivel 1
		medicines[2][4] = new Medicine(270, 295,39, app);
		medicines[2][12] = new Medicine(750, 295,39, app);
	}
	
	public void createMedical2() { //Crear Suministros Nivel 2
		medicines[2][0] = new Medicine(30, 295,39, app);
		medicines[5][11] = new Medicine(690, 475,39, app);
	}
	
	public void createMedical3() { //Crear Suministros Nivel 3
		medicines[4][6] = new Medicine(390, 415,39, app);
		medicines[6][13] = new Medicine(810, 535,39, app);
	}
	//=============================================================//
	// CREAR ENEMIGOS
	//=============================================================//
	//posX = (cordY*60)+30;
	//posY = (cordX*60)+160;
	public void createEnemy1() { //Crear Enemigos Nivel 1
		enemies[6][10] = new Enemy((10*60)+30, (6*60)+160, app);
	}
	
	public void createEnemy2() { //Crear Enemigos Nivel 2
		enemies[7][16] = new Enemy((16*60)+30, (7*60)+160, app);
	}
	
	public void createEnemy3() { //Crear Enemigos Nivel 3
		enemies[1][3] = new Enemy((3*60)+30, (1*60)+160, app);
		enemies[7][8] = new Enemy((8*60)+30, (7*60)+160, app);
	}
	
	//=============================================================//
	// PINTAR MONEDAS
	//=============================================================//

	public void drawCoins1() { 	//Pintar Monedas Nivel 1
		if(coins[1][17] != null) {
			if(coin1) {
				coins[1][17].draw();
				new Thread (coins[1][17]).start();
			}
		}
	}
	
	public void drawCoins2() {	//Pintar Monedas Nivel 2
		if(coins[7][8] != null) {
			if(coin1) {
				coins[7][8].draw();
				new Thread (coins[7][8]).start();
			}
		}
		if(coins[4][18] != null) {
			if(coin2) {
				coins[4][18].draw();
				new Thread (coins[4][18]).start();
			}
		}
	}
	
	public void drawCoins3() {	//Pintar Monedas Nivel 3
		if(coins[7][4] != null) {
			if(coin1) {
				coins[7][4].draw();
				new Thread (coins[7][4]).start();
			}
		}
		if(coins[0][10] != null) {
			if(coin2) {
				coins[0][10].draw();
				new Thread (coins[0][10]).start();
			}
		}
	}
	
	//=============================================================//
	// PINTAR SUMINISTROS MÉDICOS
	//=============================================================//
	
	public void drawMedical1() {	//Pintar Suministros Nivel 1
		if(medicines[2][4] != null) {
			if(medicine1) {
				medicines[2][4].draw();
				new Thread (medicines[2][4]).start();
			}
		}
		if(medicines[2][12] != null) {
			if(medicine2) {
				medicines[2][12].draw();
				new Thread (medicines[2][12]).start();
			}
		}
	}
	
	public void drawMedical2() {	//Pintar Suministros Nivel 2
		if(medicines[2][0] != null) {
			if(medicine1) {
				medicines[2][0].draw();
				new Thread (medicines[2][0]).start();
			}
		}
		if(medicines[5][11] != null) {
			if(medicine2) {
				medicines[5][11].draw();
				new Thread (medicines[5][11]).start();
			}
		}
	}
	
	public void drawMedical3() {	//Pintar Suministros Nivel 3
		if(medicines[4][6] != null) {
			if(medicine1) {
				medicines[4][6].draw();
				new Thread (medicines[4][6]).start();
			}
		}
		if(medicines[6][13] != null) {
			if(medicine2) {
				medicines[6][13].draw();
				new Thread (medicines[6][13]).start();
			}
		}
	}
	//=============================================================//
	// PINTAR ENEMIGOS
	//=============================================================//
	public void drawEnemy1() { 	//Pintar Enemigos Nivel 1
		if(enemies[6][10] != null) {
				enemies[6][10].draw();
				new Thread(enemies[6][10]).start();
		}
	}
	public void drawEnemy2() { 	//Pintar Enemigos Nivel 2
		if(enemies[7][16] != null) {
				enemies[7][16].draw();
				new Thread(enemies[7][16]).start();
		}
	}
	public void drawEnemy3() { 	//Pintar Enemigos Nivel 3
		if(enemies[1][3] != null) {
				enemies[1][3].draw();
				new Thread(enemies[1][3]).start();
		}
		if(enemies[7][8] != null) {
			enemies[7][8].draw();
			new Thread(enemies[7][8]).start();
	}
	}

	//=============================================================//
	// PINTAR NUBES
	//=============================================================//
	public void drawCloud() {
		for (Cloud littleClouds : clouds) {
			littleClouds.draw();
			new Thread(littleClouds).start(); 
		}	
	}
	//=============================================================//
	// BORRAR MONEDAS
	//=============================================================//
	
	@SuppressWarnings("static-access")
	public void deleteCoin1() {	//Borrar Monedas Nivel 1
		
            	float dist = app.dist(posX, posY, coins[1][17].getPosX(),coins[1][17].getPosY());
            	
        		if(dist <= 20) {
        			coins[1][17].setPosX(765);
        			coins[1][17].setPosY(10);
        			coins[1][17].setSize(21);
        			scoreCoin();
        		} 
	}
	
	@SuppressWarnings("static-access")
	public void deleteCoin2() {	//Borrar Monedas Nivel 2
	
		float dist1 =  (float) app.dist(posX, posY, coins[7][8].getPosX(),coins[7][8].getPosY());
    	float dist2 =  (float) app.dist(posX, posY, coins[4][18].getPosX(),coins[4][18].getPosY());
    	
		if(dist1 <= 20) {
			coins[7][8].setPosX(745);
			coins[7][8].setPosY(10);
			coins[7][8].setSize(21);
			scoreCoin();
		}
		
		if(dist2 <= 20) {
			coins[4][18].setPosX(788);
			coins[4][18].setPosY(10);
			coins[4][18].setSize(21);
			scoreCoin();
		}
	}
	
	@SuppressWarnings("static-access")
	public void deleteCoin3() {	//Borrar Monedas Nivel 3
		float dist1 =  (float) app.dist(posX, posY, coins[7][4].getPosX(),coins[7][4].getPosY());
    	float dist2 =  (float) app.dist(posX, posY, coins[0][10].getPosX(),coins[0][10].getPosY());
    	
		if(dist1 <= 20) {
			coins[7][4].setPosX(725);
			coins[7][4].setPosY(10);
			coins[7][4].setSize(21);
			scoreCoin();
		}
		
		if(dist2 <= 20) {
			coins[0][10].setPosX(768);
			coins[0][10].setPosY(10);
			coins[0][10].setSize(21);
			scoreCoin();
		}
	}
	
	//=============================================================//
	// BORRAR SUMINISTROS MÉDICOS
	//=============================================================//
	
	@SuppressWarnings("static-access")
	public void deleteMedical1() {
		//Borrar Suministros Nivel 1
		
            	float dist1 =  (float) app.dist(posX, posY, medicines[2][4].getPosX(),medicines[2][4].getPosY());
            	float dist2 =  (float) app.dist(posX, posY, medicines[2][12].getPosX(),medicines[2][12].getPosY());
            	
        		if(dist1 <= 20) {
  
        			medicines[2][4].setPosX(725);
        			medicines[2][4].setPosY(10);
        			medicines[2][4].setSize(19);
        			scoreMedicine();
        		} 
        		if(dist2 <= 20) {
        			  
        			medicines[2][12].setPosX(745);
        			medicines[2][12].setPosY(10);
        			medicines[2][12].setSize(19);
        			scoreMedicine();
        		} 
          
		
	}
	
	@SuppressWarnings("static-access")
	public void deleteMedical2() {	//Borrar Suministros Nivel 2
		
            	float dist1 =  (float) app.dist(posX, posY, medicines[2][0].getPosX(),medicines[2][0].getPosY());
            	float dist2 =  (float) app.dist(posX, posY, medicines[5][11].getPosX(),medicines[5][11].getPosY());
            	
        		if(dist1 <= 20) {
  
        			medicines[2][0].setPosX(725);
        			medicines[2][0].setPosY(10);
        			medicines[2][0].setSize(19);
        			scoreMedicine();
        		} 
        		if(dist2 <= 20) {
        			  
        			medicines[5][11].setPosX(768);
        			medicines[5][11].setPosY(10);
        			medicines[5][11].setSize(19);
        			scoreMedicine();
        		} 
         
	}
	
	@SuppressWarnings("static-access")
	public void deleteMedical3() {	//Borrar Suministros Nivel 3s
		
            	float dist1 =  (float) app.dist(posX, posY, medicines[4][6].getPosX(),medicines[4][6].getPosY());
            	float dist2 =  (float) app.dist(posX, posY, medicines[6][13].getPosX(),medicines[6][13].getPosY());
            	
        		if(dist1 <= 20) {
  
        			medicines[4][6].setPosX(748);
        			medicines[4][6].setPosY(10);
        			medicines[4][6].setSize(19);
        			scoreMedicine();
        		} 
        		if(dist2 <= 20) {
        			  
        			medicines[6][13].setPosX(790);
        			medicines[6][13].setPosY(10);
        			medicines[6][13].setSize(19);
        			scoreMedicine();
        		} 
           
	}
	//=============================================================//
	// COMPROBAR PERDER POR ENEMIGOS
	//=============================================================//
	public void loseEnemy1() {	//Perder por enemigos Nivel 1
		
    	float dist =  (float) app.dist(posX, posY, enemies[6][10].getPosX(),enemies[6][10].getPosY());
		if(dist <= 11) {

			screen=7;
			registerGame();
		} 
		}
	
	public void loseEnemy2() {	//Perder por enemigos Nivel 1

    	float dist =  (float) app.dist(posX, posY, enemies[7][16].getPosX(),enemies[7][16].getPosY());
		if(dist <= 11) {

			screen=7;
			registerGame();
		} 
		}
	public void loseEnemy3() {	//Perder por enemigos Nivel 1
	
	float dist1 =  (float) app.dist(posX, posY, enemies[1][3].getPosX(),enemies[1][3].getPosY());
	float dist2 =  (float) app.dist(posX, posY, enemies[7][8].getPosX(),enemies[7][8].getPosY());
	if(dist1 <= 11||dist2<=11) {

		screen=7;
		registerGame();
	} 
	}
	
	//=============================================================//
	
	public void scoreCoin() {
				scores =scores+100;		
	}
	public void scoreMedicine() {
		scores =scores+50;		
	}
	
	public void drawFloor1() {
		//Vaciar el array antes de dibujar las paredes para eliminar paredes de otros niveles
		for (int i = 0; i < barrier.length; i++) { 
            for (int j = 0; j < barrier[i].length; j++) {
            	barrier[i][j]= 0;
            	}
            }
		barrier[7][0] = 1;
		barrier[7][1] = 1;
		barrier[7][2] = 1;
		barrier[7][3] = 1;
		barrier[8][3] = 1;
		
		barrier[3][4] = 1;
		barrier[3][5] = 1;
		
		barrier[8][5] = 1;
		barrier[7][5] = 1;
		barrier[6][5] = 1;
		barrier[6][6] = 1;
		barrier[6][7] = 1;
		barrier[6][8] = 1;
		barrier[6][9] = 1;
		barrier[7][10] = 1;
		barrier[7][11] = 1;
		barrier[7][12] = 1;
		barrier[7][13] = 1;
		barrier[8][13] = 1;
		
		barrier[4][7] = 1;
		barrier[4][8] = 1;
		
		barrier[3][11] = 1;
		barrier[3][12] = 1;
		
		barrier[2][16] = 1;
		barrier[2][17] = 1;
		
		barrier[8][16] = 1;
		barrier[7][16] = 1;
		barrier[7][17] = 1;
		barrier[7][18] = 1;
	}
	
	public void drawFloor2() {
		//Vaciar el array antes de dibujar las paredes para eliminar paredes de otros niveles
				for (int i = 0; i < barrier.length; i++) { 
		            for (int j = 0; j < barrier[i].length; j++) {
		            	barrier[i][j]= 0;
		            	}
		            }
		barrier[3][0] = 1;
		barrier[3][1] = 1;
		barrier[3][5] = 1;
		barrier[3][6] = 1;
		
		barrier[4][5] = 1;
		barrier[4][6] = 1;
		
		barrier[5][4] = 1;
		barrier[5][5] = 1;
		barrier[5][6] = 1;
		barrier[5][17] = 1;
		barrier[5][18] = 1;
		
		barrier[6][5] = 1;
		barrier[6][6] = 1;
		barrier[6][10] = 1;
		barrier[6][11] = 1;
		barrier[6][12] = 1;
		barrier[6][13] = 1;
		barrier[6][14] = 1;
		
		barrier[7][0] = 1;
		barrier[7][1] = 1;
		barrier[7][2] = 1;
		barrier[7][3] = 1;
		barrier[7][5] = 1;
		barrier[7][6] = 1;
		barrier[7][11] = 1;
		barrier[7][14] = 1;
		
		barrier[8][3] = 1;
		barrier[8][5] = 1;
		barrier[8][6] = 1;
		barrier[8][8] = 1;
		barrier[8][9] = 1;
		barrier[8][11] = 1;
		barrier[8][14] = 1;
		barrier[8][16] = 1;
		barrier[8][17] = 1;
		barrier[8][18] = 1;
		}
	
	public void drawFloor3() {
		//Vaciar el array antes de dibujar las paredes para eliminar paredes de otros niveles
				for (int i = 0; i < barrier.length; i++) { 
		            for (int j = 0; j < barrier[i].length; j++) {
		            	barrier[i][j]= 0;
		            	}
		            }
		barrier[1][10] = 1;

		barrier[2][2] = 1;
		barrier[2][3] = 1;
		barrier[2][4] = 1;
		
		barrier[3][12] = 1;
		barrier[3][13] = 1;
		barrier[3][14] = 1;
		
		barrier[4][0] = 1;
		barrier[4][1] = 1;
		barrier[4][18] = 1;
		
		barrier[5][1] = 1;
		barrier[5][4] = 1;
		barrier[5][5] = 1;
		barrier[5][6] = 1;
		barrier[5][15] = 1;
		barrier[5][16] = 1;
		
		barrier[6][1] = 1;
		
		barrier[7][1] = 1;
		barrier[7][11] = 1;
		barrier[7][12] = 1;
		barrier[7][13] = 1;
		
		barrier[8][1] = 1;
		barrier[8][3] = 1;
		barrier[8][4] = 1;
		barrier[8][7] = 1;
		barrier[8][8] = 1;
		barrier[8][9] = 1;
		barrier[8][11] = 1;
		barrier[8][13] = 1;
	}
	
	//=============================================================//
	// MOVIMIENTO A LA IZQUIERDA
	//=============================================================//
	
	public void moveL() {
		try {
			
			if(cordY - 1 > -1) {
				if(barrier[cordX][cordY - 1] != 1) {
					cordY -= 1;
					calcPos();
					matrix[cordX][cordY] = new MainCharacter(posX,posY,lucasimg,app);
					matrix[cordX][cordY + 1] = null;
				}
			}
		}
		catch(Exception e) {
			e.getLocalizedMessage();
		}
	}
	
	//=============================================================//
	// MOVIMIENTO A LA DERECHA
	//=============================================================//
	
	public void moveR() {
		
		try {
			if(cordY + 1 < 20) {
				if(barrier[cordX][cordY + 1] != 1) {
					cordY += 1;
					calcPos();
					matrix[cordX][cordY] = new MainCharacter(posX,posY,lucasimg,app);
					matrix[cordX][cordY - 1] = null;
				}
			}
		}
		catch(Exception e) {
			e.getLocalizedMessage();
		}
	}
	
	//=============================================================//
	// MOVIMIENTO HACIA ARRIBA
	//=============================================================//
	
	public void moveU() {
		try {
			if(cordX - 1 > -1) {
				if(barrier[cordX - 1][cordY] != 1) {
					cordX -= 1;
					calcPos();
					matrix[cordX][cordY] = new MainCharacter(posX, posY, lucasimg, app);
					matrix[cordX + 1][cordY] = null;
				}
			}
		}
		catch(Exception e) {
			e.getLocalizedMessage();
		}
	}
	
	//=============================================================//
	// MOVIMIENTO HACIA ABAJO
	//=============================================================// 
	
	public void moveD() {
		try {
			if(cordX + 1 < 9) {
				if(barrier[cordX + 1][cordY] != 1) {
					cordX += 1;
					calcPos();
					matrix[cordX][cordY] = new MainCharacter(posX, posY, lucasimg, app);
					matrix[cordX - 1][cordY] = null;
				}
			}
		}
		catch(Exception e) {
			e.getLocalizedMessage();
		}
	}
	
	//=============================================================//
	// SALTO
	//=============================================================//
	
	public void jump() {
		try {
			if(barrier[cordX + 1][cordY] == 1) {
				moveU();
				moveU();
			}
		}
		catch(Exception e) {
			e.getLocalizedMessage();
		}
	}
	
	//=============================================================//
	// GRAVEDAD
	//=============================================================//
	
	public void gravity() {
		moveD();
	}
	
	public void winScreen() {
		//Condicion de Cambio de pantalla
		if(screen==2&&cordX==6&&cordY==18) {
			screen=3;
		}
		if(screen==3&&cordX==7&&cordY==18) {
			screen=4;
		}
		if(screen==4&&cordX==3&&cordY==18) {
			screen=6;
			registerGame();
		}

	}
	public void lose() {
		if(cordX>=8) {
			screen=7;
			registerGame();
		}
	}
	
	public void mousePressed() {
		switch(screen) {
		case 0:
			//de name a home
			if((457<app.mouseX&&app.mouseX<730)&&(490<app.mouseY&&app.mouseY<546)) {
				name.textfieldValues();
				try {
					if(name.getName().length()==0) {
						JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío", "Woops", JOptionPane.ERROR_MESSAGE);
						throw new NoName("El nombre no puede estar vacío");
					}else {
						try {
							if(name.getName().length()>8) {
								JOptionPane.showMessageDialog(null, "El nombre no debe tener más de 8 caracteres", "Woops", JOptionPane.ERROR_MESSAGE);
								throw new NameLength("El nombre no debe tener más de 8 caracteres");
							}else {
								try {
									if(name.getName().matches(".*\\d.*")) {
									char[] chars = name.getName().toCharArray();
									for(char c : chars){
								         if(Character.isDigit(c)){
								        	 JOptionPane.showMessageDialog(null, "El nombre no debe tener números", "Woops", JOptionPane.ERROR_MESSAGE);
												throw new NameLength("El nombre no debe tener números");
								         }
								     
								         }}else{
								        	 registerPlayer();
												screen=1;
												name.clear();
												name.hide();
												System.out.println(name.getName());
												}
								        	
								         
								         
								} catch (Exception e) {
									System.out.println(e.getMessage());
								}
							}
						} catch (Exception e) {
							System.out.println(e.getMessage());
							
						}
						
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}	
				 
			}
			
			break;
		case 1:
			//De home a puntajes
			if((372<app.mouseX&&app.mouseX<801)&&(332<app.mouseY&&app.mouseY<400)) {
				screen=8;
				}
			//De home a Game1
			if((372<app.mouseX&&app.mouseX<801)&&(458<app.mouseY&&app.mouseY<522)) {
			firstPaint=true;
			secondPaint=true;
			thirdPaint=true;
			screen=2;
			}
			break;
		case 5:
			//De Resumen a Home
			if((641<app.mouseX&&app.mouseX<1068)&&(301<app.mouseY&&app.mouseY<372)) {
				screen=1;
				}
			//De Resumen a Game1
			if((641<app.mouseX&&app.mouseX<1068)&&(428<app.mouseY&&app.mouseY<495)) {
				firstPaint=true;
				secondPaint=true;
				thirdPaint=true;
				screen=2;
				}
			
			break;
		case 6:
			//De Victoria a Resumen
			if((641<app.mouseX&&app.mouseX<1068)&&(301<app.mouseY&&app.mouseY<372)) {
				screen=5;
				}
			//De Victoria a Home
			if((641<app.mouseX&&app.mouseX<1068)&&(428<app.mouseY&&app.mouseY<495)) {
				screen=1;
				}
			break;
		case 7:
			//De Derrota a Resumen
			if((641<app.mouseX&&app.mouseX<1068)&&(301<app.mouseY&&app.mouseY<372)) {
				screen=5;
				}
			//De Derrota a Home
			if((641<app.mouseX&&app.mouseX<1068)&&(428<app.mouseY&&app.mouseY<495)) {
				screen=1;
				}
			break;
		case 8:
			//De puntajes a home
			if((22<app.mouseX&&app.mouseX<96)&&(29<app.mouseY&&app.mouseY<62)) {
				screen=1;
				}
			//Ordenar por nombre
			if((101<app.mouseX&&app.mouseX<299)&&(199<app.mouseY&&app.mouseY<243)) {
			Collections.sort(gamesList,sortByName);
			
			}
			//Ordenar por puntaje (ORDENAMIENTO NATURAL)
			if((349<app.mouseX&&app.mouseX<547)&&(199<app.mouseY&&app.mouseY<243)) {
				Collections.sort(gamesList);
				
				}
			//Ordenar por fecha
			if((597<app.mouseX&&app.mouseX<796)&&(199<app.mouseY&&app.mouseY<243)) {
				Collections.sort(gamesList,sortByDate);
			
				}
			//Ordenar por tiempo
			if((847<app.mouseX&&app.mouseX<1045)&&(199<app.mouseY&&app.mouseY<243)) {
				Collections.sort(gamesList,sortByTime);
				
				}
			break;
			}
	}
	public void registerPlayer() {
		playerList.add(new Player(name.getName(),app));
	}
	public void  registerGame() {
		date = new Date();
		 String s1= String.valueOf(countmin);
		 String s2= String.valueOf(countsec);
		 String s3 = s1+s2;
		
		gamesList.add(new Game(playerList.get(playerList.size()-1).getName(),scores,Integer.parseInt(s3),date,app));
	}
}
