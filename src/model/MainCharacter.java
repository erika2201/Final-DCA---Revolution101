package model;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class MainCharacter {
	private int posX;
	private int posY;
	private boolean fail;
	PImage img;
	PApplet app;
public MainCharacter(int posX,int posY,PApplet app) {
	this.posX= posX;
	this.posY=posY;
	this.app=app;
	img = app.loadImage("img/Lucas.png");
}
public void draw() {
	//app.imageMode(PConstants.CORNER);
	app.image(img, posX, posY,100,100);
	
}
}
