package view;

import processing.core.PApplet;

public class Game2Screen extends Screen {

	public Game2Screen(PApplet app) {
		super(app);
	}

	@Override
	public void draw() {
		app.image(Game2, 0, 0);
		
	}
}
