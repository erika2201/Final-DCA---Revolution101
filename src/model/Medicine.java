package model;

import processing.core.PApplet;

public class Medicine extends Collectable {

	public Medicine(int posX, int posY,int size, PApplet app) {
		super(posX, posY,size, app);
	}

	@Override
	public void draw() {
		app.image(med, posX, posY,size,size);
	}

}
