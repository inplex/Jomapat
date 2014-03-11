package me.inplex.jomapat;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

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
		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(-Jomapat.game.getPlayer().getX() + Jomapat.game.getWidth() / 2, -Jomapat.game.getHeight() / 2 + Jomapat.game.getPlayer().getY() + 50);

		// Render Blocks
		// TODO Improve this iteration - do not render EVERY Block, only the
		// visible ones
		for (int x = 0; x < Jomapat.game.getWorld().getWidth(); x++) {
			for (int y = 0; y < Jomapat.game.getWorld().getHeight(); y++) {
				if (Jomapat.game.getWorld().getBlockAt(x, y) == null)
					continue;
				// Render Block at
				g.drawImage(Jomapat.game.getWorld().getBlockAt(x, y).getImage(), x * SpriteManager.SPRITE_BLOCK_SIZE, y
						* SpriteManager.SPRITE_BLOCK_SIZE, null);
			}
		}

		// Render Player
		g.drawImage(SpriteManager.SPRITE_PLAYER_IDLE_1, Jomapat.game.getPlayer().getX(), Jomapat.game.getWidth() - Jomapat.game.getPlayer().getY(),
				null);

	}

}