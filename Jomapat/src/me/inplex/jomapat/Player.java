package me.inplex.jomapat;

import java.awt.event.KeyEvent;

public class Player {
	
	private int x;
	private int y;
	private int speed = 1;
	private Direction direction;
	private MoveState state;
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
	}

	private void move(Direction dir, int xVal) {
		x = dir == Direction.LEFT ? x - xVal : x + xVal;
		direction = dir;
	}
	
	private void move(int xVal) {
		move(direction, xVal);
	}
	
	public void update() {
		if(Jomapat.game.getInput().isKeyDown(KeyEvent.VK_A)) {
			direction = Direction.LEFT;
			move(speed);
		}
		if(Jomapat.game.getInput().isKeyDown(KeyEvent.VK_D)) {
			direction = Direction.RIGHT;
			move(speed);
		}
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

	public MoveState getState() {
		return state;
	}

	public void setState(MoveState state) {
		this.state = state;
	}

}