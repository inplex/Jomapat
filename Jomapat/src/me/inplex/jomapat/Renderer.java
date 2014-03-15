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
		// Graphics2D g2d = (Graphics2D) g;
		// g2d.translate(-Jomapat.game.getPlayer().getX() +
		// Jomapat.game.getWidth() / 2, -Jomapat.game.getHeight() / 2 +
		// Jomapat.game.getPlayer().getY() + 50);

		int playerX = Jomapat.game.getPlayer().getX()-Jomapat.game.getWidth()/2;
		int playerY = Jomapat.game.getPlayer().getY()-Jomapat.game.getHeight()/2;
		// Render Blocks
		for (int x = 0; x < Jomapat.game.getWorld().getWidth(); x++) {
			for (int y = 0; y < Jomapat.game.getWorld().getHeight(); y++) {
				if (Jomapat.game.getWorld().getBlockAt(x, y) == null)
					continue;
				// Render Block at
				if (Maths.isVisible(x*SpriteManager.SPRITE_BLOCK_SIZE-playerX , y*SpriteManager.SPRITE_BLOCK_SIZE)){
				g.drawImage(Jomapat.game.getWorld().getBlockAt(x, y).getSprite(), (x * SpriteManager.SPRITE_BLOCK_SIZE)-playerX,
						(y * SpriteManager.SPRITE_BLOCK_SIZE)-playerY, null);
				}
			}
		}

		// Render Player
		g.drawImage(Player.SPRITE_IDLE_1, Jomapat.game.getWidth()/2, Jomapat.game.getHeight()/2, null);
		
	}

}