package model;

import processing.core.PApplet;

public class Coin extends Collectable {

	public Coin(int posX, int posY, PApplet app) {
		super(posX, posY, app);
	}

	@Override
	public void draw() {
		app.image(coin, posX, posY);
	}
}
