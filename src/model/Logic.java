package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;

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
	private LinkedList<Player> playerList;
	private ArrayList<Enemy> enemyList;
	private LinkedList<Game> gamesList;
	private ByName sortByName;
	private ByDate sortByDate;
	private ByTime sortByTime;
	private MainCharacter[][] matrix;
	private int[][] barrier;
	private int cordX;
	private int cordY;
	private int posX;
	private int posY;
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
	int timer = 0;
	int countsec = 0;
	int countmin = 0;
	int sec;
	PImage lucasimg;

	public int screen;
	//Recolectables
	ArrayList<Coin> coinCol;
	ArrayList<Medicine> medCol;
	
	
	public Logic(PApplet app) {
		this.app = app;
		playerList = new LinkedList<Player>();
		gamesList = new LinkedList<Game>();
		lucasimg = app.loadImage("img/Lucas.png");
		cordX = 6;
		cordY = 0;
		posX = 0;
		posY = 0;
		matrix = new MainCharacter[9][19];
		barrier = new int[9][19];
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
		date = new Date();
		calcPos();
		createLucas();
		//Recolectables
		coinCol = new ArrayList<Coin>();
		medCol = new ArrayList<Medicine>();
	}
	
	public void changeScreen() {
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
			app.fill(238, 19, 19);
			app.textSize(25);
			app.text(countmin + ":" + countsec, 65, 38);
			//Dibujar personaje y fondo
			game1.draw();
			collectable(); //recolectables
			drawChar();
			drawFloor1();
			
				
			break;
		// Nivel2
		case 3:
			game2.draw();
			collectable();
			break;
		// Nivel3
		case 4:
			game3.draw();
			collectable();
			break;
		// Resumen del juego
		case 5:
			result.draw();
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
			score.draw();
			break;

		default:
			break;
		}
	}
	
	public void calcPos() {
		posX = (cordY*60)+30;
		posY = (cordX*60)+160;
	}
	
	public void createLucas() {
		matrix[cordX][cordY] = new MainCharacter(posX,posY,lucasimg,app);
	}
	
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
	
	public void drawFloor1() {
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
		barrier[3][0] = 1;
		barrier[3][1] = 1;
		barrier[3][5] = 1;
		barrier[3][6] = 1;
		
		barrier[4][5] = 1;
		barrier[4][6] = 1;
		
		barrier[5][5] = 1;
		barrier[5][6] = 1;
		barrier[5][17] = 1;
		barrier[5][18] = 1;
		
		barrier[6][5] = 1;
		barrier[6][6] = 1;
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
		barrier[1][10] = 1;

		barrier[2][2] = 1;
		barrier[2][3] = 1;
		barrier[2][4] = 1;
		
		barrier[3][12] = 1;
		barrier[3][13] = 1;
		barrier[3][14] = 1;
		
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
	
	public void collectable() {
		switch (screen) {
		case 2:
			coinCol.add(new Coin(1040, 233, app));
			medCol.add(new Medicine(740, 290, app));
			medCol.add(new Medicine(265, 290, app));
			
			//Monedas
			for (int i = 0; i < coinCol.size(); i++) {
				coinCol.get(i).draw(); 
				//Insertar condicional o método para recolectar
			}
			
			//Insumos medicos
			for (int i = 0; i < medCol.size(); i++) {
				medCol.get(i).draw(); 
				//Insertar condicional o método para recolectar
			}
			break;
		case 3:
			coinCol.add(new Coin(500, 595, app));
			coinCol.add(new Coin(1100, 413, app));
			medCol.add(new Medicine(685, 473, app));
			
			//Monedas
			for (int i = 0; i < coinCol.size(); i++) {
				coinCol.get(i).draw(); 
				//Insertar condicional o método para recolectar
			}
			
			//Insumos medicos
			for (int i = 0; i < medCol.size(); i++) {
				medCol.get(i).draw(); 
				//Insertar condicional o método para recolectar
			}
			break;
		case 4:
			coinCol.add(new Coin(263, 593, app));
			coinCol.add(new Coin(616, 179, app));
			medCol.add(new Medicine(375, 412, app));
			medCol.add(new Medicine(797, 534, app));
			
			//Monedas
			for (int i = 0; i < coinCol.size(); i++) {
				coinCol.get(i).draw(); 
				//Insertar condicional o método para recolectar
			}
			
			//Insumos medicos
			for (int i = 0; i < medCol.size(); i++) {
				medCol.get(i).draw(); 
				//Insertar condicional o método para recolectar
			}
			
			break;
		default:
			break;
		}
	}
	
	public void moveL() {
		System.out.println("move L");
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
	
	public void moveU() {
		try {
			if(cordX - 1 > -1) {
				cordX -= 1;
				calcPos();
				matrix[cordX][cordY] = new MainCharacter(posX, posY, lucasimg, app);
				matrix[cordX + 1][cordY] = null;
			}
		}
		catch(Exception e) {
			e.getLocalizedMessage();
		}
	}
	
	public void moveD() {
		try {
			if(cordX + 1 > -1) {
				cordX += 1;
				calcPos();
				matrix[cordX][cordY] = new MainCharacter(posX, posY, lucasimg, app);
				matrix[cordX - 1][cordY] = null;
			}
		}
		catch(Exception e) {
			e.getLocalizedMessage();
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
			screen=2;
			}
			break;
		case 5:
			//De Resumen a Home
			if((641<app.mouseX&&app.mouseX<1068)&&(301<app.mouseY&&app.mouseY<372)) {
				screen=5;
				}
			//De Resumen a Game1
			if((641<app.mouseX&&app.mouseX<1068)&&(428<app.mouseY&&app.mouseY<495)) {
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
			System.out.println("ordenar por nombre");
			}
			//Ordenar por puntaje (ORDENAMIENTO NATURAL)
			if((349<app.mouseX&&app.mouseX<547)&&(199<app.mouseY&&app.mouseY<243)) {
				
				Collections.sort(gamesList);
				System.out.println("ordenar por puntaje");
				}
			//Ordenar por fecha
			if((597<app.mouseX&&app.mouseX<796)&&(199<app.mouseY&&app.mouseY<243)) {
				Collections.sort(gamesList,sortByDate);
				System.out.println("ordenar por fecha");
				}
			//Ordenar por tiempo
			if((847<app.mouseX&&app.mouseX<1045)&&(199<app.mouseY&&app.mouseY<243)) {
				Collections.sort(gamesList,sortByTime);
				System.out.println("ordenar por tiempo");
				}
			break;
			}
	}
	public void registerPlayer() {
		playerList.add(new Player(name.getName(),app));
	}
}
