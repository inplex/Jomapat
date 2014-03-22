package me.inplex.jomapat.gfx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteManager{

	public static BufferedImage blockSheet;
	public static BufferedImage playerSheet;
	

	/*
	 * Size of one Player Sprite
	 */

	public static final int SPRITE_PLAYER_SIZE_WIDTH = 48;
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
	
	public static void setSheets(BufferedImage blockSpriteSheet, BufferedImage playerSpriteSheet ){
		blockSheet = blockSpriteSheet;
		playerSheet = playerSpriteSheet;

		BufferedImage digg = null;
		try {
			digg = ImageIO.read(new File("res//Assets//Sprites//digg.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		digg1 = digg.getSubimage(0,0,64,64);
		digg2 = digg.getSubimage(64,0,64,64);
		digg3 = digg.getSubimage(128,0,64,64);
		
		/*
		 * SPRITE_PLAYER_IDLE_1 = sheet.getSubimage(0, 0,
		 * SPRITE_PLAYER_SIZE_WIDTH, SPRITE_PLAYER_SIZE_HEIGHT);
		 * SPRITE_PLAYER_IDLE_2 = sheet.getSubimage(SPRITE_PLAYER_SIZE_WIDTH, 0,
		 * SPRITE_PLAYER_SIZE_WIDTH, SPRITE_PLAYER_SIZE_HEIGHT);
		 * SPRITE_PLAYER_IDLE_3 = sheet.getSubimage(SPRITE_PLAYER_SIZE_WIDTH *
		 * 2, 0, SPRITE_PLAYER_SIZE_WIDTH, SPRITE_PLAYER_SIZE_HEIGHT);
		 * 
		 * SPRITE_PLAYER_WALK_LEFT_1 =
		 * sheet.getSubimage(SPRITE_PLAYER_SIZE_WIDTH * 3, 0,
		 * SPRITE_PLAYER_SIZE_WIDTH, SPRITE_PLAYER_SIZE_HEIGHT);
		 * SPRITE_PLAYER_WALK_LEFT_2 =
		 * sheet.getSubimage(SPRITE_PLAYER_SIZE_WIDTH * 4, 0,
		 * SPRITE_PLAYER_SIZE_WIDTH, SPRITE_PLAYER_SIZE_HEIGHT);
		 * SPRITE_PLAYER_WALK_LEFT_2 =
		 * sheet.getSubimage(SPRITE_PLAYER_SIZE_WIDTH * 5, 0,
		 * SPRITE_PLAYER_SIZE_WIDTH, SPRITE_PLAYER_SIZE_HEIGHT);
		 * 
		 * SPRITE_PLAYER_WALK_RIGHT_1 =
		 * sheet.getSubimage(SPRITE_PLAYER_SIZE_WIDTH * 6, 0,
		 * SPRITE_PLAYER_SIZE_WIDTH, SPRITE_PLAYER_SIZE_HEIGHT);
		 * SPRITE_PLAYER_WALK_RIGHT_2 =
		 * sheet.getSubimage(SPRITE_PLAYER_SIZE_WIDTH * 7, 0,
		 * SPRITE_PLAYER_SIZE_WIDTH, SPRITE_PLAYER_SIZE_HEIGHT);
		 * SPRITE_PLAYER_WALK_RIGHT_3 =
		 * sheet.getSubimage(SPRITE_PLAYER_SIZE_WIDTH * 8, 0,
		 * SPRITE_PLAYER_SIZE_WIDTH, SPRITE_PLAYER_SIZE_HEIGHT);
		 * 
		 * SPRITE_PLAYER_JUMP_1 = sheet.getSubimage(SPRITE_PLAYER_SIZE_WIDTH *
		 * 9, 0, SPRITE_PLAYER_SIZE_WIDTH, SPRITE_PLAYER_SIZE_HEIGHT);
		 * SPRITE_PLAYER_JUMP_2 = sheet.getSubimage(SPRITE_PLAYER_SIZE_WIDTH *
		 * 10, 0, SPRITE_PLAYER_SIZE_WIDTH, SPRITE_PLAYER_SIZE_HEIGHT);
		 * SPRITE_PLAYER_JUMP_3 = sheet.getSubimage(SPRITE_PLAYER_SIZE_WIDTH *
		 * 11, 0, SPRITE_PLAYER_SIZE_WIDTH, SPRITE_PLAYER_SIZE_HEIGHT);
		 * SPRITE_PLAYER_JUMP_4 = sheet.getSubimage(SPRITE_PLAYER_SIZE_WIDTH *
		 * 12, 0, SPRITE_PLAYER_SIZE_WIDTH, SPRITE_PLAYER_SIZE_HEIGHT);
		 * 
		 * SPRITE_PLAYER_SNEAK_1 = sheet.getSubimage(SPRITE_PLAYER_SIZE_WIDTH *
		 * 13, 0, SPRITE_PLAYER_SIZE_WIDTH, SPRITE_PLAYER_SIZE_HEIGHT);
		 * SPRITE_PLAYER_SNEAK_2 = sheet.getSubimage(SPRITE_PLAYER_SIZE_WIDTH *
		 * 14, 0, SPRITE_PLAYER_SIZE_WIDTH, SPRITE_PLAYER_SIZE_HEIGHT);
		 * SPRITE_PLAYER_SNEAK_3 = sheet.getSubimage(SPRITE_PLAYER_SIZE_WIDTH *
		 * 15, 0, SPRITE_PLAYER_SIZE_WIDTH, SPRITE_PLAYER_SIZE_HEIGHT);
		 * SPRITE_PLAYER_SNEAK_4 = sheet.getSubimage(SPRITE_PLAYER_SIZE_WIDTH *
		 * 16, 0, SPRITE_PLAYER_SIZE_WIDTH, SPRITE_PLAYER_SIZE_HEIGHT);
		 */

	}
	public static BufferedImage loadBlockImage(int x, int y) {
		return blockSheet.getSubimage(x * SPRITE_BLOCK_SIZE, y * SPRITE_BLOCK_SIZE, SPRITE_BLOCK_SIZE, SPRITE_BLOCK_SIZE);
	}

	public static BufferedImage loadPlayerImage(int x, int y) {
		return playerSheet.getSubimage(x * SPRITE_PLAYER_SIZE_WIDTH, y * SPRITE_PLAYER_SIZE_HEIGHT, SPRITE_PLAYER_SIZE_WIDTH, SPRITE_PLAYER_SIZE_HEIGHT);
	}

}