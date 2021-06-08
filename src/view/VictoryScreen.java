package view;

import processing.core.PApplet;

public class VictoryScreen extends Screen {

	public VictoryScreen(PApplet app) {
		super(app);
	}

	@Override
	public void draw() {
		app.image(Victory, 0, 0);

	}

}
