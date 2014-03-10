package me.inplex.jomapat;

import java.awt.image.BufferedImage;

public class BlockDirt extends Block {

	@Override
	public byte getId() {
		return 0x01;
	}

	@Override
	public BufferedImage getImage() {
		return SpriteManager.SPRITE_BLOCK_DIRT;
	}
		
}