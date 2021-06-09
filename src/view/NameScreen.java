package view;

import controlP5.ControlP5;
import controlP5.Textfield;
import processing.core.PApplet;

public class NameScreen extends Screen {

	ControlP5 cp5;
	private String name;
	
	public NameScreen(PApplet app) {
		super(app);
		 cp5= new ControlP5 (app);
		
	}

	public void draw() {
		 app.image(Name,0,0);
		
	}
	
	public void textFields() {
		 cp5.addTextfield("name")
		 .setPosition(375, 370)
		 .setSize(427,69)
		 .setFont(app.createFont("arial",16))
		 .setAutoClear(false)
		 .setColor(app.color(62,36,32))
		 .setColorBackground(app.color(0,0,0,1))
		 .setColorForeground(app.color(0,0,0,1))
		 .getCaptionLabel().setColor(app.color(255,235,207));
		 

	}
	public void show() {
		cp5.get("name").show();
	}
	public void hide() {
		cp5.get("name").hide();
	}
	public void clear() {
		cp5.get(Textfield.class,"name").clear();
	}
	public void textfieldValues() {
		name = cp5.get(Textfield.class,"name").getText();
		
	}

	public String getName() {
		return name;
	}

}
