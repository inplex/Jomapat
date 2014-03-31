package me.inplex.jomapat.player;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

import me.inplex.jomapat.Jomapat;
import me.inplex.jomapat.extra.Direction2;
import me.inplex.jomapat.extra.Maths;
import me.inplex.jomapat.extra.Util;
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
	private Direction2 direction;
	private MoveState state;

	private int mouseX = 0, mouseY = 0;
	private int oldBlockX = 0, oldBlockY = 0;
	public int actualBlockX = 0, actualBlockY = 0, actualBlockDigg = 0;
	public int actualBlockRawX = 0, actualBlockRawY = 0;
	public BufferedImage actDiggGraphics = null;

	private int playerHitboxW = 30, playerHitboxH = 64;

	public static boolean noclip = false;

	// <Player attributes>

	public int health = 100;
	public int stamina = 100;

	// TODO add some more

	// </Player attributes>

	public static BufferedImage SPRITE_IDLE[] = new BufferedImage[3];

	public static BufferedImage SPRITE_WALK_LEFT[] = new BufferedImage[3];

	public static BufferedImage SPRITE_WALK_RIGHT[] = new BufferedImage[3];

	public static BufferedImage SPRITE_JUMP[] = new BufferedImage[3];

	public static BufferedImage SPRITE_SNEAK[] = new BufferedImage[3];

	public static BufferedImage SPRITE_SPRINT_LEFT[] = new BufferedImage[3];
	public static BufferedImage SPRITE_SPRINT_RIGHT[] = new BufferedImage[3];

	public static int frame = 0;

	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		this.state = MoveState.IDLE;
		loadSprites();
	}

	private static void loadSprites() {
		SPRITE_IDLE[0] = SpriteManager.loadPlayerImage(0, 0);
		SPRITE_IDLE[1] = SpriteManager.loadPlayerImage(1, 0);
		SPRITE_IDLE[2] = SpriteManager.loadPlayerImage(2, 0);

		SPRITE_WALK_LEFT[0] = SpriteManager.loadPlayerImage(3, 0);
		SPRITE_WALK_LEFT[1] = SpriteManager.loadPlayerImage(4, 0);
		SPRITE_WALK_LEFT[2] = SpriteManager.loadPlayerImage(5, 0);

		SPRITE_WALK_RIGHT[0] = Util.horizontalFlip(SPRITE_WALK_LEFT[0]);
		SPRITE_WALK_RIGHT[1] = Util.horizontalFlip(SPRITE_WALK_LEFT[1]);
		SPRITE_WALK_RIGHT[2] = Util.horizontalFlip(SPRITE_WALK_LEFT[2]);

		SPRITE_JUMP[0] = SpriteManager.loadPlayerImage(6, 0);
		SPRITE_JUMP[1] = SpriteManager.loadPlayerImage(7, 0);
		SPRITE_JUMP[2] = SpriteManager.loadPlayerImage(8, 0);

		SPRITE_SNEAK[0] = SpriteManager.loadPlayerImage(9, 0);
		SPRITE_SNEAK[1] = SpriteManager.loadPlayerImage(10, 0);
		SPRITE_SNEAK[2] = SpriteManager.loadPlayerImage(11, 0);

		SPRITE_SPRINT_LEFT[0] = SpriteManager.loadPlayerImage(12, 0);
		SPRITE_SPRINT_LEFT[1] = SpriteManager.loadPlayerImage(13, 0);
		SPRITE_SPRINT_LEFT[2] = SpriteManager.loadPlayerImage(14, 0);

		SPRITE_SPRINT_RIGHT[0] = Util.horizontalFlip(SPRITE_SPRINT_LEFT[0]);
		SPRITE_SPRINT_RIGHT[1] = Util.horizontalFlip(SPRITE_SPRINT_LEFT[1]);
		SPRITE_SPRINT_RIGHT[2] = Util.horizontalFlip(SPRITE_SPRINT_LEFT[2]);

	}

	private void move(Direction2 dir, int xVal) {
		if (!noclip) {
			final int oldx = x;
			x = dir == Direction2.LEFT ? x - xVal : x + xVal;
			x = !collidesWithBlock() ? x : oldx;
			if (x == oldx) {
				sprintTime = 0;
			} else {
				// he moved
				state = MoveState.WALK;
			}
			direction = dir;
			if (new Random().nextInt(10) == 1 || (isSprinting() && new Random().nextInt(5) == 1)) {
				int btx = Maths.positionToGrid(x) / 64;
				int bty = Maths.positionToGrid(y) / 64 + 2;
				BlockType bt = Jomapat.game.getWorld().getBlockAt(btx, bty);
				if (bt != null) {
					int lt = isSprinting() ? 10 : 15;
					ParticleManager.addParticle(new Particle(dir == Direction2.RIGHT ? x : x + 64, y + 64, dir == Direction2.RIGHT ? -1 : 1, -1, lt,
							ParticleBehaviour.ACCELERATE_FASTER, bt.getParticle((new Random().nextInt(bt.getSprites().length)))));
				}
			}
		} else {
			x = dir == Direction2.LEFT ? x - xVal : x + xVal;
		}
	}

	private void move(int xVal) {
		move(direction, xVal);
	}

	int motionY = 0;
	boolean wasFalling = true;
	boolean canJumpMore = true;
	int sprintTime = 0;

	public void update() {

		if (Jomapat.game.getTicks() % 20 == 0)
			frame++;
		if (frame > 2) {
			frame = 0;
		}

		if (isSprinting()) {
			sprintTime++;
		} else {
			sprintTime = 0;
		}

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

				if (noclip) {
					final BlockType bt = Jomapat.game.getWorld().getBlockAt(actualBlockX, actualBlockY);
					if (bt != null) {
						Jomapat.game.getInventory().addBlock(bt);
						Jomapat.game.getWorld().removeBlockAt(actualBlockX, actualBlockY);
						Jomapat.game.getWorld().updateNearBlocks(actualBlockX, actualBlockY, bt);
					}
				} else {
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
						final BlockType bt = Jomapat.game.getWorld().getBlockAt(actualBlockX, actualBlockY);
						Jomapat.game.getInventory().addBlock(bt);
						Jomapat.game.getWorld().removeBlockAt(actualBlockX, actualBlockY);
						Jomapat.game.getWorld().updateNearBlocks(actualBlockX, actualBlockY, bt);
						if (bt == BlockType.TNT) {
							Jomapat.game.getWorld().detonateTnt(actualBlockX, actualBlockY);
						}
					}
					if (new Random().nextInt(3) == 1) {
						BlockType bt = Jomapat.game.getWorld().getBlockAt(actualBlockX, actualBlockY);
						if (bt != null) {
							ParticleManager.addParticle(new Particle(actualBlockX * 64 + 32, actualBlockY * 64 + 32, 1, 1, 30,
									ParticleBehaviour.RANDOM, bt.getParticle(new Random().nextInt(bt.getParticles().length))));
						}
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

		if (!noclip) {
			if (Jomapat.game.getInput().isKeyDown(KeyEvent.VK_A)) {
				direction = Direction2.LEFT;
				state = MoveState.WALK;
				move(isSprinting() ? (SPEED_SPRINTING + sprintTime / 100) : SPEED_NORMAL);
			} else if (Jomapat.game.getInput().isKeyDown(KeyEvent.VK_D)) {
				direction = Direction2.RIGHT;
				state = MoveState.WALK;
				move(isSprinting() ? (SPEED_SPRINTING + sprintTime / 100) : SPEED_NORMAL);
			} else {
				if (!jumping) {
					state = MoveState.IDLE;
				} else {
					state = MoveState.JUMP;
				}
			}

			if (jumping) {
				state = MoveState.JUMP;
			} else if (Jomapat.game.getInput().isKeyDown(KeyEvent.VK_SHIFT)) {
				state = MoveState.SPRINT;
			} else if (Jomapat.game.getInput().isKeyDown(KeyEvent.VK_CONTROL)) {
				state = MoveState.SNEAK;
			}

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
						jumping = false;
						int btx = Maths.positionToGrid(x) / 64;
						int bty = Maths.positionToGrid(y) / 64 + 2;
						BlockType bt = Jomapat.game.getWorld().getBlockAt(btx, bty);
						if (bt != null) {
							for (int i = 0; i < new Random().nextInt(30); i++) {
								ParticleManager.addParticle(new Particle(x + 32 + Maths.randomize(-32, 32), y + 64, Maths.randomize(-2, 2),
										new Random().nextInt(1) - 2, 30, ParticleBehaviour.ACCELERATE_SLOWER, bt.getParticle((new Random().nextInt(bt
												.getSprites().length)))));
							}
						}
						wasFalling = false;
					}
				}
			} else {
				motionY = 0;
			}

			if (jumping) {
				state = MoveState.JUMP;
			}
		} else {
			if (Jomapat.game.getInput().isKeyDown(KeyEvent.VK_A)) {
				direction = Direction2.LEFT;
				state = MoveState.WALK;
				move(isSprinting() ? (SPEED_SPRINTING + sprintTime / 100) : SPEED_NORMAL);
			}
			if (Jomapat.game.getInput().isKeyDown(KeyEvent.VK_D)) {
				direction = Direction2.RIGHT;
				state = MoveState.WALK;
				move(isSprinting() ? (SPEED_SPRINTING + sprintTime / 100) : SPEED_NORMAL);
			}
			if (Jomapat.game.getInput().isKeyDown(KeyEvent.VK_SPACE)) {
				y -= SPEED_NORMAL;
			}
			if (Jomapat.game.getInput().isKeyDown(KeyEvent.VK_CONTROL)) {
				y += SPEED_NORMAL;
			}
		}

	}

	private boolean collidesWithBlock() {
		return Collision.checkCollisionAt(x, y) || Collision.checkCollisionAt(x + playerHitboxW, y)
				|| Collision.checkCollisionAt(x + playerHitboxW, y + playerHitboxH) || Collision.checkCollisionAt(x, y + playerHitboxH);
	}

	boolean jumping = false;

	public void jump() {
		y += 2;
		if (collidesWithBlock()) {
			y -= 5;
			motionY = 50;
			if (isSprinting())
				motionY += 5;
			if (isSneaking())
				motionY -= 20;
			wasFalling = true;
			canJumpMore = true;
			jumping = true;
		} else {
			if (wasFalling == true && canJumpMore == true) {
				// double jump
				y -= 5;
				motionY = 50;
				if (isSprinting())
					motionY += 5;
				if (isSneaking())
					motionY -= 20;
				wasFalling = true;
				canJumpMore = false;
				jumping = true;
			} else {
				jumping = false;
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
		return motionY < -8 || motionY > 0;
	}

	public boolean isInAir() {
		return !isOnGround();
	}

	public boolean isSneaking() {
		return Jomapat.game.getInput().isKeyDown(KeyEvent.VK_CONTROL);
	}

	public boolean isSprinting() {
		return Jomapat.game.getInput().isKeyDown(KeyEvent.VK_SHIFT);
	}

	public boolean isMoving() {
		return Jomapat.game.getInput().isKeyDown(KeyEvent.VK_A) || Jomapat.game.getInput().isKeyDown(KeyEvent.VK_D);
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

	public Direction2 getDirection() {
		return direction;
	}

	public void setDirection(Direction2 direction) {
		this.direction = direction;
	}

	public MoveState getState() {
		return state;
	}

	public void setState(MoveState state) {
		this.state = state;
	}

}