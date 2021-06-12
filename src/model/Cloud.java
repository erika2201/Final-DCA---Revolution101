package model;

import processing.core.PApplet;
import processing.core.PImage;

public class Cloud implements Runnable{

	private PApplet app;
	private float posX;
	private float posY;
	private boolean dir;
	private PImage cloud;
	
	public Cloud (float posX, float posY, PApplet app) {
		this.app = app;
		this.posX = posX;
		this.posY = posY;
		cloud = app.loadImage("img/Cloud.png");
		dir= true;
	}

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
		app.image(cloud, posX, posY);
	}
	
	
	private void move() {
		if(PApplet.second()%3==0) {
			dir=true;
			}else {
				dir=false;
			}
		
	    if(dir) {
		   posX += 1;
	    }else {
		   posX -= 1;
		}
	}

	
	public float getPosX() {
		return posX;
	}


	public float getPosY() {
		return posY;
	}
	
}
	

