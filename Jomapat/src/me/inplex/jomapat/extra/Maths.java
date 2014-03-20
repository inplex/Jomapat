package me.inplex.jomapat.extra;

import java.util.Random;

import me.inplex.jomapat.Jomapat;
import me.inplex.jomapat.gfx.SpriteManager;
import me.inplex.jomapat.world.World;

public class Maths {

	public static int randomize(int min, int max) {
		Random random = new Random();
		return random.nextInt(max - min + 1) + min;
	}

	public static int positionToGrid(int pos) {
		return (int) (Math.floor(pos / (double) SpriteManager.SPRITE_BLOCK_SIZE) * SpriteManager.SPRITE_BLOCK_SIZE);
	}

	public static boolean isVisible(int x, int y) {
		return (x >= -64 && y >= -100 && x < Jomapat.game.getWidth() && y < Jomapat.game.getHeight()) ? true : false;
	}

	public static int distance(int x1, int x2, int y1, int y2) {
		return (int) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	}
	
	public static int getHighestBlock(World w, int x) {
		for(int i = 0; i < w.getWidth(); i++) {
			if(w.getBlockAt(x, i) != null) {
				return i;
			}
		}
		return 0;
	}
	
}

