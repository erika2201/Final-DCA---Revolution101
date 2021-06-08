package model;

import java.util.Date;

public class Player {
	private String name;
	private int score;
	private int time;
	private Date date;
	private int posX;
	private int posY;
	private boolean fail;
	
	public Player(String name, Date date, int posX, int posY) {
		this.name = name;
		this.date = date;
		this.posX = posX;
		this.posY = posY;
	}
}
