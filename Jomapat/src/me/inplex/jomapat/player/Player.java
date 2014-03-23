package me.inplex.jomapat.player;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

import me.inplex.jomapat.Jomapat;
import me.inplex.jomapat.extra.Direction;
import me.inplex.jomapat.extra.Maths;
import me.inplex.jomapat.gfx.Particle;
import me.inplex.jomapat.gfx.ParticleBehaviour;
import me.inplex.jomapat.gfx.ParticleManager;
import me.inplex.jomapat.gfx.Renderer;
import me.inplex.jomapat.gfx.SpriteManager;
import me.inplex.jomapat.world.BlockType;

public class Player {

	public static final int SPEED_NORMAL = 3;
	public static final int SPEED_SNEAKING = 2;
	public static final int SPEED_SPRINTING = 4;

	private int x;
	private int y;
	private Direction direction;
	private MoveState state;

	private int mouseX = 0, mouseY = 0;
	private int oldBlockX = 0, oldBlockY = 0;
	public int actualBlockX = 0, actualBlockY = 0, actualBlockDigg = 0;
	public int actualBlockRawX = 0, actualBlockRawY = 0;
	public BufferedImage actDiggGraphics = null;

	private int playerHitboxW = 46, playerHitboxH = 64;

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
		final int oldx = x;
		x = dir == Direction.LEFT ? x - xVal : x + xVal;
		x = !collidesWithBlock() ? x : oldx;
		direction = dir;
		if (new Random().nextInt(10) == 1 || (isSprinting() && new Random().nextInt(5) == 1)) {
			int btx = Maths.positionToGrid(x) / 64;
			int bty = Maths.positionToGrid(y) / 64 + 2;
			BlockType bt = Jomapat.game.getWorld().getBlockAt(btx, bty);
			if (bt != null) {
				int lt = isSprinting() ? 10 : 15;
				ParticleManager.addParticle(new Particle(dir == Direction.RIGHT ? x : x + 64, y + 64, dir == Direction.RIGHT ? -1 : 1, -1, lt,
						ParticleBehaviour.ACCELERATE_FASTER, bt.getParticle((new Random().nextInt(bt.getSprites().length)))));
			}
		}
	}

	private void move(int xVal) {
		move(direction, xVal);
	}

	int motionY = 0;
	boolean wasFalling = true;
	boolean canJumpMore = true;

	public void update() {

		mouseX = Jomapat.game.getInput().getMousePosX();
		mouseY = Jomapat.game.getInput().getMousePosY();

		if (Jomapat.game.getInput().isMouseLeftDown()) {

			actualBlockX = Maths.positionToGrid(mouseX + Renderer.getXOffset()) / 64;
			actualBlockY = Maths.positionToGrid(mouseY + Renderer.getYOffset()) / 64;
			actualBlockRawX = Maths.positionToGrid(mouseX) / 64;
			actualBlockRawY = Maths.positionToGrid(mouseY) / 64;

			if (Maths.distance(mouseX, Jomapat.game.getWidth() / 2, mouseY, Jomapat.game.getHeight() / 2) < 200) {
				if (actualBlockX != oldBlockX || actualBlockY != oldBlockY) {
					actualBlockDigg = 0;
					actDiggGraphics = null;
				}

				oldBlockX = actualBlockX;
				oldBlockY = actualBlockY;
				int dmg = (Jomapat.game.getWorld().getBlockAt(actualBlockX, actualBlockY) != null) ? (int) ((1.0f - (Jomapat.game.getWorld()
						.getBlockAt(actualBlockX, actualBlockY).getHardness())) * 10) : 0;

				actualBlockDigg += dmg;
				if (actualBlockDigg > 20 && actualBlockDigg < 100) {
					actDiggGraphics = SpriteManager.digg1;
				}
				if (actualBlockDigg > 100 && actualBlockDigg < 200) {
					actDiggGraphics = SpriteManager.digg2;
				}
				if (actualBlockDigg > 200 && actualBlockDigg < 300) {
					actDiggGraphics = SpriteManager.digg3;
				}
				if (actualBlockDigg > 300 && Jomapat.game.getWorld().getBlockAt(actualBlockX, actualBlockY) != null) {
					Jomapat.game.getInventory().addBlock(Jomapat.game.getWorld().getBlockAt(actualBlockX, actualBlockY));
					Jomapat.game.getWorld().removeBlockAt(actualBlockX, actualBlockY);
				}

				if (new Random().nextInt(3) == 1) {
					BlockType bt = Jomapat.game.getWorld().getBlockAt(actualBlockX, actualBlockY);
					if (bt != null) {
						ParticleManager.addParticle(new Particle(actualBlockX * 64 + 32, actualBlockY * 64 + 32, 1, 1, 30, ParticleBehaviour.RANDOM,
								bt.getParticle(new Random().nextInt(bt.getParticles().length))));
					}
				}

			} else {
				actualBlockDigg = -1;
			}

		} else if (Jomapat.game.getInput().isMouseRightDown()) {
			int bx = Maths.positionToGrid(mouseX + Renderer.getXOffset()) / 64;
			int by = Maths.positionToGrid(mouseY + Renderer.getYOffset()) / 64;

			if (Jomapat.game.getWorld().getBlockAt(bx, by) == null) {
				// Build Block
				if (Jomapat.game.getInventory().getBlockAmount(BlockType.values()[Jomapat.game.getInventory().getSelected()]) > 0) {
					Jomapat.game.getWorld().setBlock(bx, by, BlockType.values()[Jomapat.game.getInventory().getSelected()]);
					Jomapat.game.getInventory().removeBlock(BlockType.values()[Jomapat.game.getInventory().getSelected()]);
				}
			}

		}

		if (Jomapat.game.getInput().isKeyDown(KeyEvent.VK_A)) {
			direction = Direction.LEFT;
			state = MoveState.WALK;
			move(isSprinting() ? SPEED_SPRINTING : SPEED_NORMAL);
		} else if (Jomapat.game.getInput().isKeyDown(KeyEvent.VK_D)) {
			direction = Direction.RIGHT;
			state = MoveState.WALK;
			move(isSprinting() ? SPEED_SPRINTING : SPEED_NORMAL);
		} else {
			if (motionY == 0) {
				state = MoveState.IDLE;
			}
		}

		// Jump
		/*
		 * Moved to InputHandler, because Double Jump would not work otherwise
		 * if (Jomapat.game.getInput().isKeyDown(KeyEvent.VK_SPACE)) { jump(); }
		 */

		if (!collidesWithBlock()) {
			final boolean dCollide = collidesWithBlock();
			final int dX = x;
			final int dY = y;
			y -= motionY / 7;
			motionY -= 2;
			if (!dCollide && collidesWithBlock()) {
				x = dX;
				y = dY;
				motionY = 0;
				if (wasFalling) {
					// landed after jump
					int btx = Maths.positionToGrid(x) / 64;
					int bty = Maths.positionToGrid(y) / 64 + 2;
					BlockType bt = Jomapat.game.getWorld().getBlockAt(btx, bty);
					if (bt != null) {
						for (int i = 0; i < new Random().nextInt(30); i++) {
							ParticleManager.addParticle(new Particle(x + 32 + Maths.randomize(-32, 32), y + 64, Maths.randomize(-2, 2), new Random()
									.nextInt(1) - 2, 30, ParticleBehaviour.ACCELERATE_SLOWER,
									bt.getParticle((new Random().nextInt(bt.getSprites().length)))));
						}
					}
					wasFalling = false;
				}
			}
		} else {
			motionY = 0;
		}

	}

	private boolean collidesWithBlock() {
		return Collision.checkCollisionAt(x, y) || Collision.checkCollisionAt(x + playerHitboxW, y)
				|| Collision.checkCollisionAt(x + playerHitboxW, y + playerHitboxH) || Collision.checkCollisionAt(x, y + playerHitboxH);
	}

	public void jump() {
		y += 2;
		if (collidesWithBlock()) {
			y -= 5;
			motionY = 50;
			wasFalling = true;
			canJumpMore = true;
		} else {
			if (wasFalling == true && canJumpMore == true) {
				// double jump
				y -= 5;
				motionY = 50;
				wasFalling = true;
				canJumpMore = false;
			}
		}
		y -= 2;
	}

	/*
	 * private void handleFalls() { if (Collision.checkCollisionAt(x, y +
	 * playerHitboxH) == false && Collision.checkCollisionAt(x+playerHitboxW, y)
	 * == false) { y = y + speed; } if (Collision.checkCollisionAt(x +
	 * playerHitboxW, y + playerHitboxH)) { y = y - speed; } if
	 * (Collision.checkCollisionAt(x, y + playerHitboxH) == true ||
	 * Collision.checkCollisionAt(x, y + playerHitboxH) == true) { y = y -
	 * speed; } }
	 */
	public boolean isOnGround() {
		return motionY == 0;
	}

	public boolean isSneaking() {
		return Jomapat.game.getInput().isKeyDown(KeyEvent.VK_CONTROL);
	}

	public boolean isSprinting() {
		return Jomapat.game.getInput().isKeyDown(KeyEvent.VK_SHIFT);
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