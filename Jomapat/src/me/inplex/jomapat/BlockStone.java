package me.inplex.jomapat;

import java.awt.image.BufferedImage;

public class BlockStone extends Block {
	
	@Override
	public byte getId() {
		return 0x03;
	}

	@Override
	public BufferedImage getImage() {
		return SpriteManager.SPRITE_BLOCK_STONE;
	}
	
}
