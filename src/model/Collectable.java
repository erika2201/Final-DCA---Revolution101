package model;

import processing.core.PApplet;
import processing.core.PImage;

public abstract class Collectable {
	protected int posX;
	protected int posY;
	protected int size;
	protected PApplet app;
	
	protected PImage coin, med;
	
	public Collectable(int posX, int posY, int size, PApplet app) {
		this.posX = posX;
		this.posY = posY;
		this.size = size;
		this.app = app;
		
		coin = app.loadImage("img/Coin.png");
		med = app.loadImage("img/Medicine.png");
	}
	
	 public abstract void draw ();
	 

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public void setApp(PApplet app) {
		this.app = app;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}
	 
}
