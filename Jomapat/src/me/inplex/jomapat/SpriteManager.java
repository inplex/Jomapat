package me.inplex.jomapat;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class SpriteManager {

	// Hashmap for Sprites
	// Integer : color in the map
	private HashMap<Integer, BufferedImage> spriteMap = new HashMap<Integer, BufferedImage>();

	
	public void loadSprite(int color, File filename) {
		BufferedImage temp = null;
		try {
			temp = ImageIO.read(filename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		spriteMap.put(color, temp);
	}
	
	

	public void addSpriteToImage(Graphics g, int color, int x, int y) {
		g.drawImage(spriteMap.get(color), x, y, null);
	}

}
