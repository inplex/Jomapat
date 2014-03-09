package me.inplex.jomapat;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;



public class SpriteManager {
	
	
	//Hashmap for Sprites
	//Integer : color in the map
	private HashMap<Integer,BufferedImage> spriteMap = new HashMap<Integer, BufferedImage>(); 
	
	
	public void loadSprite(int color,File filename){
		BufferedImage temp = null;
		try {
			temp = ImageIO.read(filename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		spriteMap.put(color, temp);	//sm
	}

}
