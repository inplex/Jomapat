package me.inplex.jomapat;

import java.awt.image.BufferedImage;

public class BlockGrass extends Block {
	
	@Override
	public byte getId() {
		return 0x02;
	}
	
	@Override
	public BufferedImage getImage() {
		return SpriteManager.SPRITE_BLOCK_GRASS;
	}
	
}