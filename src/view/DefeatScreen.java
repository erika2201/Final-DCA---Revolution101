package view;

import processing.core.PApplet;

public class DefeatScreen extends Screen {

	public DefeatScreen(PApplet app) {
		super(app);
	}

	@Override
	public void draw() {
		app.image(Defeat, 0, 0);

	}

}
