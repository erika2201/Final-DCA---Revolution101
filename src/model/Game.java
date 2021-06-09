package model;

import java.util.Date;

import processing.core.PApplet;

public class Game implements Comparable<Game> {
	private String playName;
	private int score;
	private int time;
	private Date date;
	PApplet app;
	public Game(String playName, int score, int time, Date date, PApplet app) {
		this.playName = playName;
		this.score = score;
		this.time = time;
		this.date = date;
		this.app = app;
	}
	@Override
	public int compareTo(Game nextGame) {

		return this.score - nextGame.getScore();
	}
	public String getPlayName() {
		return playName;
	}
	public int getScore() {
		return score;
	}
	public int getTime() {
		return time;
	}
	public Date getDate() {
		return date;
	}
	public PApplet getApp() {
		return app;
	}

}
