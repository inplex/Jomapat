package me.inplex.jomapat;

import java.awt.image.BufferedImage;

public abstract class Block {
	
	public void update() {
		
	}
	
	public abstract byte getId();
	public abstract BufferedImage getImage();

	public void load() {

	}
	
}