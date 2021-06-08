package view;

import processing.core.PApplet;

public class Game3Screen extends Screen {

	public Game3Screen(PApplet app) {
		super(app);
	}

	@Override
	public void draw() {
		app.image(Game3, 0, 0);
		
	}

}
