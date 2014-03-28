package me.inplex.jomapat.gfx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteManager {

	public static BufferedImage blockSheet;
	public static BufferedImage playerSheet;

	/*
	 * Size of one Player Sprite
	 */

	public static final int SPRITE_PLAYER_SIZE_WIDTH = 32;
	public static final int SPRITE_PLAYER_SIZE_HEIGHT = 64;

	/*
	 * Size of one Block Sprite
	 */

	public static final int SPRITE_BLOCK_SIZE = 64;

	/**
	 * Loads all sprites from the given spritesheet
	 * 
	 * @param spriteSheet The BufferedImage containing all sprites
	 * 
	 * 
	 */

	public static BufferedImage digg1;
	public static BufferedImage digg2;
	public static BufferedImage digg3;

	public static void setSheets(BufferedImage blockSpriteSheet, BufferedImage playerSpriteSheet) {
		blockSheet = blockSpriteSheet;
		playerSheet = playerSpriteSheet;

		BufferedImage digg = null;
		try {
			digg = ImageIO.read(new File("res//Assets//Sprites//digg.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		digg1 = digg.getSubimage(0, 0, 64, 64);
		digg2 = digg.getSubimage(64, 0, 64, 64);
		digg3 = digg.getSubimage(128, 0, 64, 64);

	}

	public static BufferedImage loadBlockImage(int x, int y) {
		return blockSheet.getSubimage(x * SPRITE_BLOCK_SIZE, y * SPRITE_BLOCK_SIZE, SPRITE_BLOCK_SIZE, SPRITE_BLOCK_SIZE);
	}

	public static BufferedImage loadPlayerImage(int x, int y) {
		return playerSheet.getSubimage(x * SPRITE_PLAYER_SIZE_WIDTH, y * SPRITE_PLAYER_SIZE_HEIGHT, SPRITE_PLAYER_SIZE_WIDTH,
				SPRITE_PLAYER_SIZE_HEIGHT);
	}

}