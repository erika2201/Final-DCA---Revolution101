package view;

import controlP5.ControlP5;
import processing.core.PApplet;
import processing.core.PImage;

public abstract class Screen {

	protected PApplet app;
	protected ControlP5 cp5;
	protected PImage Name, Home, Score, Game1, Game2, Game3, Result;

	public Screen(PApplet app) {
		this.app = app;
		this.cp5 = cp5;
		Name = app.loadImage("img/Name.png");
		Home = app.loadImage("img/Home.png");
		Score = app.loadImage("img/Score.png");
		Game1 = app.loadImage("img/Level1.png");
		Game2 = app.loadImage("img/Level2.png");
		Game3 = app.loadImage("img/Level3.png");
		Result = app.loadImage("img/Summary.png");
	}
	
	 public abstract void draw ();

}
