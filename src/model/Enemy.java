package model;

import processing.core.PApplet;
import processing.core.PImage;

public class Enemy implements Runnable {
	private PApplet app;
	private float posX;
	private float posY;
	private boolean dir;
	private PImage enemy;
	
	public Enemy(float posX, float posY, PApplet app) {
		this.app = app;
		this.posX = posX;
		this.posY = posY;
		enemy = app.loadImage("img/Police.png");
		dir= true;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(2000);
			move();
			
			}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		
	
	}
	public void draw() {
		app.image(enemy, posX, posY);
	}
	private void move() {
		if(PApplet.second()%2==0) {
			dir=true;
			}else {
				dir=false;
			}
	if(dir) {
		posX += 0.5;
	}else {
		posX -= 0.5;
	}
	}

	public float getPosX() {
		return posX;
	}



	public float getPosY() {
		return posY;
	}


	
	}
	

