package me.inplex.enemy;

public class Enemy {
	
	public int x;
	public int y;

	public int health;

	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void move(int x, int y) {
		this.x = x;
		this.y = y;
	}

}