package me.inplex.jomapat;

public class Player {

	private int x;
	private int y;
	private Direction direction;

	public Player(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void move(Direction dir, int xVal) {
		x = dir == Direction.LEFT ? x - xVal : x + xVal;
	}
	
	public boolean isOnGround() {
		/*
		 * TODO Implement Jumping and return if player is on the ground
		 */
		return false;
	}
	
	public boolean isSneaking() {
		/*
		 * TODO Implement Sneaking and return if player is sneaking
		 */
		return false;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

}