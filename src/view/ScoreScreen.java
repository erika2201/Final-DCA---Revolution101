package view;

import processing.core.PApplet;

public class ScoreScreen extends Screen {

	public ScoreScreen (PApplet app) {
		super(app);
	}

	@Override
	public void draw() {
		app.image(Score, 0, 0);
		
	}

}
