package me.inplex.jomapat.player;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import me.inplex.jomapat.Jomapat;
import me.inplex.jomapat.extra.Direction;
import me.inplex.jomapat.extra.InputHandler;
import me.inplex.jomapat.extra.Maths;
import me.inplex.jomapat.gfx.Renderer;
import me.inplex.jomapat.gfx.SpriteManager;

public class Player {

	private int x;
	private int y;
	private int speed = 3;
	private Direction direction;
	private MoveState state;
	
	private int mouseX=0,mouseY=0;

	/*
	 * Idle Sprites framewise
	 */

	public static BufferedImage SPRITE_IDLE_1;
	public static BufferedImage SPRITE_IDLE_2;
	public static BufferedImage SPRITE_IDLE_3;

	/*
	 * Left Walk Sprites
	 */

	public static BufferedImage SPRITE_WALK_LEFT_1;
	public static BufferedImage SPRITE_WALK_LEFT_2;
	public static BufferedImage SPRITE_WALK_LEFT_3;

	/*
	 * Right Walk Sprites
	 */

	public static BufferedImage SPRITE_WALK_RIGHT_1;
	public static BufferedImage SPRITE_WALK_RIGHT_2;
	public static BufferedImage SPRITE_WALK_RIGHT_3;

	/*
	 * Jump Sprites 1 = Jump Start, 2 = Flying In Air Frame 1, 3 = Flying in Air
	 * Frame 2, 4 = Landing
	 */

	public static BufferedImage SPRITE_JUMP_1;
	public static BufferedImage SPRITE_JUMP_2;
	public static BufferedImage SPRITE_JUMP_3;
	public static BufferedImage SPRITE_JUMP_4;

	/*
	 * Sneak Sprites 1 = Sneak Start, 2 = While Sneaking Frame 1, 3 = While
	 * Sneaking Frame 2, 4 = Stopping Sneak
	 */

	public static BufferedImage SPRITE_SNEAK_1;
	public static BufferedImage SPRITE_SNEAK_2;
	public static BufferedImage SPRITE_SNEAK_3;
	public static BufferedImage SPRITE_SNEAK_4;
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		this.loadSprites();
	}

	private void loadSprites() {
		SPRITE_IDLE_1 = SpriteManager.loadPlayerImage(0, 0);
		// TODO load others
	}

	private void move(Direction dir, int xVal) {
		int oldx = x;
		x = dir == Direction.LEFT ? x - xVal : x + xVal;
		x = Collision.checkCollisionAt(dir == Direction.LEFT ? x : x+64, y+10)==false ? x : oldx;
		direction = dir;
	}

	private void move(int xVal) {
		move(direction, xVal);
	}

	private void handleFalls(){
		if (Collision.checkCollisionAt(x, y+65)==false&&Collision.checkCollisionAt(x, y+64)==false){
			y = y + speed;
		}
		if (Collision.checkCollisionAt(x, y)){
			y = y - speed;
		}
		if (Collision.checkCollisionAt(x+50, y+64)){
			y = y - speed;
		}
		if (Collision.checkCollisionAt(x, y+65)==true||Collision.checkCollisionAt(x, y+64)==true){
			y = y - speed;
		}
	}



	public void update() {
		

		mouseX = Jomapat.game.getInput().getMousePosX();
		mouseY = Jomapat.game.getInput().getMousePosY();
		
		if (Jomapat.game.getInput().isMouseDown()){
			if (Maths.distance(Maths.positionToGrid(mouseX+Renderer.getXOffset()), x, Maths.positionToGrid(mouseY+Renderer.getYOffset()), y)<100){
			//Jomapat.game.getWorld().removeBlockAt(Maths.positionToGrid(mouseX+Renderer.getXOffset())/64, Maths.positionToGrid(mouseY+Renderer.getYOffset())/64);
				
			}
		}
		
		
		handleFalls();
		if(Jomapat.game.getInput().isKeyDown(KeyEvent.VK_A)) {
			direction = Direction.LEFT;
			move(speed);
		}
		if(Jomapat.game.getInput().isKeyDown(KeyEvent.VK_D)) {
			direction = Direction.RIGHT;
			move(speed);
		}

		//Jump

		if(Jomapat.game.getInput().isKeyDown(KeyEvent.VK_SPACE)) {
			y=y-speed*3;
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