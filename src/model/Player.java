package model;

import java.util.Date;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Player {
	private String name;
	private int score;
	private int time;
	private Date date;
	private int posX;
	private int posY;
	private boolean fail;
	PImage img;
	PApplet app;
	public Player(String name, Date date, int posX, int posY, PApplet app) {
		this.name = name;
		this.date = date;
		this.posX = posX;
		this.posY = posY;
		this.app = app;
		img = app.loadImage("img/Lucas.png");
	}
	public void draw() {
		app.imageMode(PConstants.CORNER);
		app.image(img, 140*(posX), 150*(posY-1),140,140);
	}
}
