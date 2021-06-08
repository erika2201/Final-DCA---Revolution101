package view;

import controlP5.ControlP5;
import controller.Controller;
import processing.core.PApplet;

public class Main extends PApplet {

	public static void main(String[] args) {
		PApplet.main(Main.class.getName());
	}

	@Override
	public void settings() {
		size(1152, 700);
	}

	NameScreen name;
	HomeScreen home;
	ScoreScreen score;
	Game1Screen game1;
	Game2Screen game2;
	Game3Screen game3;
	ResultScreen result;
	VictoryScreen victory;
	DefeatScreen defeat;
	Controller c;
	public int screen;

	@Override
	public void setup() {
		c = new Controller(this);
		name = new NameScreen(this);
		home = new HomeScreen(this);
		score = new ScoreScreen(this);
		game1 = new Game1Screen(this);
		game2 = new Game2Screen(this);
		game3 = new Game3Screen(this);
		result = new ResultScreen(this);
		victory = new VictoryScreen(this);
		defeat = new DefeatScreen(this);
		name.textFields();
		screen = 0;

	}

	@Override
	public void draw() {
		changeScreen();
	}

	private void changeScreen() {
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
			c.drawChar();
			game1.draw();
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

	
	@Override
	public void mouseClicked() {
		screen +=1;
	}
}
