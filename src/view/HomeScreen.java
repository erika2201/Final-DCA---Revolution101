package view;

import processing.core.PApplet;

public class HomeScreen extends Screen {

	public HomeScreen(PApplet app) {
		super(app);
	}

	@Override
	public void draw() {
		app.image(Home, 0, 0);
		
	}

}
