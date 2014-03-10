package me.inplex.jomapat;

import java.awt.Color;
import java.awt.Graphics;

public class Renderer {

	/**
	 * Renders the game with given Graphics object
	 * 
	 * @param g the Graphics object to draw on
	 */

	public static void renderGame(Graphics g) {

		g.setColor(Color.RED);
		g.drawString("Player (" + Jomapat.game.getPlayer().getX() + "|" + Jomapat.game.getPlayer().getY() + ")", 10, 20);

		// TODO Translate according to the player position

		// Render Blocks
		// TODO Improve this iteration - do not render EVERY Block, only the
		// visible ones
		for (int x = 0; x < Jomapat.game.getWorld().getWidth(); x++) {
			for (int y = 0; y < Jomapat.game.getWorld().getHeight(); y++) {
				if (Jomapat.game.getWorld().getBlockAt(x, y) == null)
					continue;
				// Render Block at x|y
				g.drawImage(Jomapat.game.getWorld().getBlockAt(x, y).getImage(), x*64, y*64, null);
			}
		}

		// Render Player
		g.drawImage(SpriteManager.SPRITE_PLAYER_IDLE_1, Jomapat.game.getPlayer().getX(), Jomapat.game.getWidth() - Jomapat.game.getPlayer().getY(),
				null);

	}

}
