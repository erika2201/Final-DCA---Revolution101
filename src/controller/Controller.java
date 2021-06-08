package controller;

import model.Logic;
import processing.core.PApplet;

public class Controller {
	private PApplet app;
	private Logic l;
	
	public Controller(PApplet app) {
		this.app = app;
		l = new Logic(app);
	}
	public void drawChar() {
		l.drawChar();
	}
}

