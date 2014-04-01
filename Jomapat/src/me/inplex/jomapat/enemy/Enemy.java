package me.inplex.jomapat.enemy;

import java.awt.image.BufferedImage;

import me.inplex.jomapat.Jomapat;
import me.inplex.jomapat.extra.Direction4;
import me.inplex.jomapat.extra.Maths;
import me.inplex.jomapat.extra.Util;
import me.inplex.jomapat.gfx.SpriteManager;
import me.inplex.jomapat.player.Collision;
import me.inplex.jomapat.player.MoveState;

public class Enemy {

	public static BufferedImage SPRITE_IDLE[] = new BufferedImage[3];

	public static BufferedImage SPRITE_WALK_LEFT[] = new BufferedImage[3];

	public static BufferedImage SPRITE_WALK_RIGHT[] = new BufferedImage[3];

	public static BufferedImage SPRITE_JUMP[] = new BufferedImage[3];

	public static BufferedImage SPRITE_SNEAK[] = new BufferedImage[3];

	public static BufferedImage SPRITE_SPRINT_LEFT[] = new BufferedImage[3];
	public static BufferedImage SPRITE_SPRINT_RIGHT[] = new BufferedImage[3];

	public static int frame = 0;

	/**
	 * only call this once!
	 */
	public static void loadSprites() {
		SPRITE_IDLE[0] = SpriteManager.loadEnemyImage(0, 0);
		SPRITE_IDLE[1] = SpriteManager.loadEnemyImage(1, 0);
		SPRITE_IDLE[2] = SpriteManager.loadEnemyImage(2, 0);

		SPRITE_WALK_LEFT[0] = SpriteManager.loadEnemyImage(3, 0);
		SPRITE_WALK_LEFT[1] = SpriteManager.loadEnemyImage(4, 0);
		SPRITE_WALK_LEFT[2] = SpriteManager.loadEnemyImage(5, 0);

		SPRITE_WALK_RIGHT[0] = Util.horizontalFlip(SPRITE_WALK_LEFT[0]);
		SPRITE_WALK_RIGHT[1] = Util.horizontalFlip(SPRITE_WALK_LEFT[1]);
		SPRITE_WALK_RIGHT[2] = Util.horizontalFlip(SPRITE_WALK_LEFT[2]);

		SPRITE_JUMP[0] = SpriteManager.loadEnemyImage(6, 0);
		SPRITE_JUMP[1] = SpriteManager.loadEnemyImage(7, 0);
		SPRITE_JUMP[2] = SpriteManager.loadEnemyImage(8, 0);

		SPRITE_SNEAK[0] = SpriteManager.loadEnemyImage(9, 0);
		SPRITE_SNEAK[1] = SpriteManager.loadEnemyImage(10, 0);
		SPRITE_SNEAK[2] = SpriteManager.loadEnemyImage(11, 0);

		SPRITE_SPRINT_LEFT[0] = SpriteManager.loadEnemyImage(12, 0);
		SPRITE_SPRINT_LEFT[1] = SpriteManager.loadEnemyImage(13, 0);
		SPRITE_SPRINT_LEFT[2] = SpriteManager.loadEnemyImage(14, 0);

		SPRITE_SPRINT_RIGHT[0] = Util.horizontalFlip(SPRITE_SPRINT_LEFT[0]);
		SPRITE_SPRINT_RIGHT[1] = Util.horizontalFlip(SPRITE_SPRINT_LEFT[1]);
		SPRITE_SPRINT_RIGHT[2] = Util.horizontalFlip(SPRITE_SPRINT_LEFT[2]);
	}

	private int x;
	private int y;
	private int health;
	private Direction4 direction;
	private MoveState state;

	private int enemyHitboxW = 30, enemyHitboxH = 64;

	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void move(Direction4 dir, int xVal, int yVal) {
		final int oldx = x;
		x = dir == Direction4.LEFT ? x - xVal : x + xVal;
		x = !collidesWithBlock() ? x : oldx;
		if (x != oldx) {
			state = MoveState.WALK;
		}
		final int oldy = y;
		y = dir == Direction4.UP ? y - yVal : y + yVal;
		y = !collidesWithBlock() ? y : oldy;
		if (y != oldy) {
			state = MoveState.JUMP;
		}
		direction = dir;
	}

	public void update() {
		if (Maths.distance(x, Jomapat.game.getPlayer().getX(), y, Jomapat.game.getPlayer().getY()) < 800) {
			if (x > Jomapat.game.getPlayer().getX()) {
				move(Direction4.LEFT, 1, 0);
			}
			if (x < Jomapat.game.getPlayer().getX()) {
				move(Direction4.RIGHT, 1, 0);
			}
			if (y > Jomapat.game.getPlayer().getY()) {
				jump();
			}
			if (y < Jomapat.game.getPlayer().getY()) {
				move(Direction4.DOWN, 0, 1);
			}
		}
		fall();
	}
	
	private void fall() {
		
	}
	
	private void jump() {
		
	}

	private boolean collidesWithBlock() {
		return Collision.checkCollisionAt(x, y) || Collision.checkCollisionAt(x + enemyHitboxW, y)
				|| Collision.checkCollisionAt(x + enemyHitboxW, y + enemyHitboxH) || Collision.checkCollisionAt(x, y + enemyHitboxH);
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

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public MoveState getState() {
		return state;
	}

	public void setState(MoveState state) {
		this.state = state;
	}

	public Direction4 getDirection() {
		return direction;
	}

	public void setDirection(Direction4 direction) {
		this.direction = direction;
	}

}