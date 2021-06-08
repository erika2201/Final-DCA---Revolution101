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
	
	public Logic(PApplet app) {
		this.app = app;
		
	}
}
