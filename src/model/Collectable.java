package model;

import processing.core.PApplet;
import processing.core.PImage;

public abstract class Collectable {
	protected int posX;
	protected int posY;
	protected PApplet app;
	
	protected PImage coin, med;
	
	public Collectable(int posX, int posY, PApplet app) {
		this.posX = posX;
		this.posY = posY;
		this.app = app;
		
		coin = app.loadImage("img/Coin.png");
		med = app.loadImage("img/Medicine.png");
	}
	
	 public abstract void draw ();
}
