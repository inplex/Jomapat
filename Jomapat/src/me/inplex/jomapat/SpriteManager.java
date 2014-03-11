package me.inplex.jomapat;

import java.awt.image.BufferedImage;

public class SpriteManager {

	public static BufferedImage sheet;

	/*
	 * Size of one Player Sprite
	 */

	public static final int SPRITE_PLAYER_SIZE_WIDTH = 48;
	public static final int SPRITE_PLAYER_SIZE_HEIGHT = 64;

	/*
	 * Size of one Block Sprite
	 */

	public static final int SPRITE_BLOCK_SIZE = 64;

	/*
	 * Idle Sprites framewise
	 */

	public static BufferedImage SPRITE_PLAYER_IDLE_1;
	public static BufferedImage SPRITE_PLAYER_IDLE_2;
	public static BufferedImage SPRITE_PLAYER_IDLE_3;

	/*
	 * Left Walk Sprites
	 */

	public static BufferedImage SPRITE_PLAYER_WALK_LEFT_1;
	public static BufferedImage SPRITE_PLAYER_WALK_LEFT_2;
	public static BufferedImage SPRITE_PLAYER_WALK_LEFT_3;

	/*
	 * Right Walk Sprites
	 */

	public static BufferedImage SPRITE_PLAYER_WALK_RIGHT_1;
	public static BufferedImage SPRITE_PLAYER_WALK_RIGHT_2;
	public static BufferedImage SPRITE_PLAYER_WALK_RIGHT_3;

	/*
	 * Jump Sprites 1 = Jump Start, 2 = Flying In Air Frame 1, 3 = Flying in Air
	 * Frame 2, 4 = Landing
	 */

	public static BufferedImage SPRITE_PLAYER_JUMP_1;
	public static BufferedImage SPRITE_PLAYER_JUMP_2;
	public static BufferedImage SPRITE_PLAYER_JUMP_3;
	public static BufferedImage SPRITE_PLAYER_JUMP_4;

	/*
	 * Sneak Sprites 1 = Sneak Start, 2 = While Sneaking Frame 1, 3 = While
	 * Sneaking Frame 2, 4 = Stopping Sneak
	 */

	public static BufferedImage SPRITE_PLAYER_SNEAK_1;
	public static BufferedImage SPRITE_PLAYER_SNEAK_2;
	public static BufferedImage SPRITE_PLAYER_SNEAK_3;
	public static BufferedImage SPRITE_PLAYER_SNEAK_4;

	/*
	 * Block Sprites
	 */

	public static BufferedImage SPRITE_BLOCK_DIRT;
	public static BufferedImage SPRITE_BLOCK_GRASS;
	public static BufferedImage SPRITE_BLOCK_STONE;

	/**
	 * Loads all sprites from the given spritesheet
	 * 
	 * @param spriteSheet The BufferedImage containing all sprites
	 */

	public static void loadSprites(BufferedImage spriteSheet) {
		sheet = spriteSheet;
/*
		SPRITE_PLAYER_IDLE_1 = sheet.getSubimage(0, 0, SPRITE_PLAYER_SIZE_WIDTH, SPRITE_PLAYER_SIZE_HEIGHT);
		SPRITE_PLAYER_IDLE_2 = sheet.getSubimage(SPRITE_PLAYER_SIZE_WIDTH, 0, SPRITE_PLAYER_SIZE_WIDTH, SPRITE_PLAYER_SIZE_HEIGHT);
		SPRITE_PLAYER_IDLE_3 = sheet.getSubimage(SPRITE_PLAYER_SIZE_WIDTH * 2, 0, SPRITE_PLAYER_SIZE_WIDTH, SPRITE_PLAYER_SIZE_HEIGHT);

		SPRITE_PLAYER_WALK_LEFT_1 = sheet.getSubimage(SPRITE_PLAYER_SIZE_WIDTH * 3, 0, SPRITE_PLAYER_SIZE_WIDTH, SPRITE_PLAYER_SIZE_HEIGHT);
		SPRITE_PLAYER_WALK_LEFT_2 = sheet.getSubimage(SPRITE_PLAYER_SIZE_WIDTH * 4, 0, SPRITE_PLAYER_SIZE_WIDTH, SPRITE_PLAYER_SIZE_HEIGHT);
		SPRITE_PLAYER_WALK_LEFT_2 = sheet.getSubimage(SPRITE_PLAYER_SIZE_WIDTH * 5, 0, SPRITE_PLAYER_SIZE_WIDTH, SPRITE_PLAYER_SIZE_HEIGHT);

		SPRITE_PLAYER_WALK_RIGHT_1 = sheet.getSubimage(SPRITE_PLAYER_SIZE_WIDTH * 6, 0, SPRITE_PLAYER_SIZE_WIDTH, SPRITE_PLAYER_SIZE_HEIGHT);
		SPRITE_PLAYER_WALK_RIGHT_2 = sheet.getSubimage(SPRITE_PLAYER_SIZE_WIDTH * 7, 0, SPRITE_PLAYER_SIZE_WIDTH, SPRITE_PLAYER_SIZE_HEIGHT);
		SPRITE_PLAYER_WALK_RIGHT_3 = sheet.getSubimage(SPRITE_PLAYER_SIZE_WIDTH * 8, 0, SPRITE_PLAYER_SIZE_WIDTH, SPRITE_PLAYER_SIZE_HEIGHT);

		SPRITE_PLAYER_JUMP_1 = sheet.getSubimage(SPRITE_PLAYER_SIZE_WIDTH * 9, 0, SPRITE_PLAYER_SIZE_WIDTH, SPRITE_PLAYER_SIZE_HEIGHT);
		SPRITE_PLAYER_JUMP_2 = sheet.getSubimage(SPRITE_PLAYER_SIZE_WIDTH * 10, 0, SPRITE_PLAYER_SIZE_WIDTH, SPRITE_PLAYER_SIZE_HEIGHT);
		SPRITE_PLAYER_JUMP_3 = sheet.getSubimage(SPRITE_PLAYER_SIZE_WIDTH * 11, 0, SPRITE_PLAYER_SIZE_WIDTH, SPRITE_PLAYER_SIZE_HEIGHT);
		SPRITE_PLAYER_JUMP_4 = sheet.getSubimage(SPRITE_PLAYER_SIZE_WIDTH * 12, 0, SPRITE_PLAYER_SIZE_WIDTH, SPRITE_PLAYER_SIZE_HEIGHT);

		SPRITE_PLAYER_SNEAK_1 = sheet.getSubimage(SPRITE_PLAYER_SIZE_WIDTH * 13, 0, SPRITE_PLAYER_SIZE_WIDTH, SPRITE_PLAYER_SIZE_HEIGHT);
		SPRITE_PLAYER_SNEAK_2 = sheet.getSubimage(SPRITE_PLAYER_SIZE_WIDTH * 14, 0, SPRITE_PLAYER_SIZE_WIDTH, SPRITE_PLAYER_SIZE_HEIGHT);
		SPRITE_PLAYER_SNEAK_3 = sheet.getSubimage(SPRITE_PLAYER_SIZE_WIDTH * 15, 0, SPRITE_PLAYER_SIZE_WIDTH, SPRITE_PLAYER_SIZE_HEIGHT);
		SPRITE_PLAYER_SNEAK_4 = sheet.getSubimage(SPRITE_PLAYER_SIZE_WIDTH * 16, 0, SPRITE_PLAYER_SIZE_WIDTH, SPRITE_PLAYER_SIZE_HEIGHT);
*/
		
	}

	public static BufferedImage loadImage(int x, int y, int width, int height) {
		return sheet.getSubimage(x, y, width, height);
	}

	public static BufferedImage loadBlockImage(int x, int y) {
		return loadImage(x*SPRITE_BLOCK_SIZE, y*SPRITE_BLOCK_SIZE, SPRITE_BLOCK_SIZE, SPRITE_BLOCK_SIZE);
	}

}