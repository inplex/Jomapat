package me.inplex.jomapat.extra;

import me.inplex.jomapat.Jomapat;
import me.inplex.jomapat.gfx.SpriteManager;
import me.inplex.jomapat.world.World;

public class Collision {
	
	private static World world = Jomapat.game.getWorld();

	public static boolean checkCollisionAt(int x,int y){

		int px = Maths.positionToGrid(x)/SpriteManager.SPRITE_BLOCK_SIZE;
		int py = Maths.positionToGrid(y)/SpriteManager.SPRITE_BLOCK_SIZE;
		if (world.getBlockAt(px, py)==null){return false;}
		return world.getBlockAt(px, py).isCollide();

	}
	

}

