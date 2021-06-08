package model;

import java.util.ArrayList;
import java.util.LinkedList;

import processing.core.PApplet;

public class Logic {
	private PApplet app;
	private LinkedList<Player> playerList;
	private ArrayList<Enemy> enemyList;
	private ByName sortByName;
	private ByScore sortByScore;
	private ByDate sortByDate;
	private ByTime sortByTime;
	private Character Lucas;
	
	public Logic(PApplet app) {
		this.app = app;
		playerList = new LinkedList<Player>();
		Lucas = new Character(0,0,app);
	}
	
	public void drawChar(){
	Lucas.draw();
	}
}
