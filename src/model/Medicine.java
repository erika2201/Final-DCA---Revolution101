package model;

import processing.core.PApplet;

public class Medicine extends Collectable {

	public Medicine(int posX, int posY, PApplet app) {
		super(posX, posY, app);
	}

	@Override
	public void draw() {
		app.image(med, posX, posY);
	}

}
