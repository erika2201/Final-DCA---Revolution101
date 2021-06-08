package view;

import processing.core.PApplet;

public class ResultScreen extends Screen {

	public ResultScreen (PApplet app) {
		super(app);
	}

	@Override
	public void draw() {
		app.image(Result, 0, 0);
		
	}

}
