package model;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class MainCharacter {
	private int posX;
	private int posY;
	private boolean fail;
	PImage Lucas;
	PApplet app;
public MainCharacter(int posX,int posY, PImage Lucas, PApplet app) {
	this.posX= posX;
	this.posY=posY;
	this.Lucas = Lucas;
	this.app=app;
	
}
public void draw() {
	//app.imageMode(PConstants.CORNER);
	//System.out.println("entra a draw");
	app.image(Lucas, posX, posY);
	}
}
