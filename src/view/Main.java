package view;

import controlP5.ControlP5;
import controller.Controller;
import processing.core.PApplet;

public class Main extends PApplet {

	public static void main(String[] args) {
		PApplet.main(Main.class.getName());
	}

	@Override
	public void settings() {
		size(1152, 700);
	}

	
	Controller c;
	@Override
	public void setup() {
		c = new Controller(this);
		

	}

	@Override
	public void draw() {
		 System.out.println(mouseX+" "+mouseY);
		c.changeScreen();
	}

	

	
	@Override
	public void mouseClicked() {
		c.mousePressed();
	}
}
