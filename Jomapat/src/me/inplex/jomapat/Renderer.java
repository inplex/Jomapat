package me.inplex.jomapat;

import java.awt.Graphics;

public class Renderer {

	/**
	 * Renders the game with given Graphics object
	 * 
	 * @param g the Graphics object to draw on
	 */

	public static void renderGame(Graphics g) {
		
		// Render Blocks
		for (int x = 0; x < Jomapat.game.getWorld().getWidth(); x++) {
			for (int y = 0; y < Jomapat.game.getWorld().getHeight(); y++) {
				if(Jomapat.game.getWorld().getBlockAt(x, y) == null)
					continue;
				// Render Block at x|y
				// TODO Check if Block MUST be rendered, e.g is in range of the
				// screen
				// System.out.println("Render: " + x + "|" + y + " = " +
				// Jomapat.game.getWorld().getBlockAt(x, y));
				g.drawImage(Jomapat.game.getWorld().getBlockAt(x, y).getImage(), x, y, null);
				System.out.println("Drawn Block at " + x + "|" + y);
			}
		}

		// Render Player
		g.drawImage(SpriteManager.SPRITE_PLAYER_IDLE_1, Jomapat.game.getPlayer().getX(), Jomapat.game.getPlayer().getY(), null);

	}

}
