package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import exception.NameLength;
import exception.NoName;
import processing.core.PApplet;
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
	private ByName sortByName;
	private ByScore sortByScore;
	private ByDate sortByDate;
	private ByTime sortByTime;
	private MainCharacter Lucas;
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

	public int screen;
	
	public Logic(PApplet app) {
		this.app = app;
		playerList = new LinkedList<Player>();
		Lucas = new MainCharacter(app.width,app.height,app);
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
			game1.draw();
			drawChar();
			break;
		// Nivel2
		case 3:
			game2.draw();
			break;
		// Nivel3
		case 4:
			game3.draw();
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
	public void drawChar(){
	Lucas.draw();
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
			
			if((372<app.mouseX&&app.mouseX<801)&&(458<app.mouseY&&app.mouseY<522)) {
			screen=2;
			}
			break;
			}
	}
	public void registerPlayer() {
		playerList.add(new Player(name.getName(),date,app));
	}
}
