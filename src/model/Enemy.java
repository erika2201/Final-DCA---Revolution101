package model;

import processing.core.PApplet;
import processing.core.PImage;

public class Enemy {
	private PApplet app;
	private int posX;
	private int posY;
	private PImage enemy;
	
	public Enemy(PApplet app, int posX, int posY) {
		this.app = app;
		this.posX = posX;
		this.posY = posY;
	}
}
