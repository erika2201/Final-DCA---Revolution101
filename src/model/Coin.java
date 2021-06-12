package model;

import processing.core.PApplet;

public class Coin extends Collectable {

	private boolean dir;
	
	public Coin(float posX, float posY, int size, PApplet app) {
		super(posX, posY,size, app);
		dir= true;
	}
	
	@Override
	public void draw() {
		app.image(coin, posX, posY,size,size);
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
	
	private void move() {
		if(PApplet.second()%2==0) {
			dir=true;
			}else {
				dir=false;
			}
		
	    if(dir) {
		   posY += 0.2;
	    }else {
		   posY -= 0.2;
		}
	}

}
