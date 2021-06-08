package model;

import java.util.Date;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Player {
	private String name;
	private int score;
	private int time;
	private Date date;
	
	PApplet app;
	public Player(String name, Date date, PApplet app) {
		this.name = name;
		this.date = date;
	
		this.app = app;
	
	}
	
}
