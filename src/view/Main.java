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
		background(255);
		
	//System.out.println(mouseX+" "+mouseY);
		c.changeScreen();
	}
	
	@Override
	public void mouseClicked() {
		c.mousePressed();
	}
	
	public void keyPressed() {
		if(key == CODED) {
			if(keyCode == UP) {
				c.moveU();
			}
			if(keyCode == RIGHT) {
				c.moveR();
			}
			if(keyCode == LEFT) {
				c.moveL();
			}
		}
	}
}
