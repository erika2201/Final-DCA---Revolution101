package view;

import processing.core.PApplet;

public class Game1Screen extends Screen {

	public Game1Screen(PApplet app) {
		super(app);
	}

	@Override
	public void draw() {
		app.image(Game1, 0, 0);
		
	}

}
