package model;

import processing.core.PApplet;

public class Coin extends Collectable {

	public Coin(int posX, int posY, int size, PApplet app) {
		super(posX, posY,size, app);
	}

	@Override
	public void draw() {
		app.image(coin, posX, posY,size,size);
	}
	
}
