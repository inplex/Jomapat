package me.inplex.jomapat;

public class Collision {
	
	private static World world = Jomapat.game.getWorld();

	public static boolean checkCollisionAt(int x,int y){

		int px = Maths.positionToGrid(x)/SpriteManager.SPRITE_BLOCK_SIZE;
		int py = Maths.positionToGrid(y)/SpriteManager.SPRITE_BLOCK_SIZE;
		if (world.getBlockAt(px, py)==null){return false;}
		return world.getBlockAt(px, py).isCollide();

	}
	

}

