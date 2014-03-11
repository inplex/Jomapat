package me.inplex.jomapat;

import java.awt.image.BufferedImage;

public class BlockWater extends Block {

	@Override
	public void load() {
		this.sprite = SpriteManager.loadBlockImage(SpriteManager.SPRITE_BLOCK_SIZE, SpriteManager.SPRITE_PLAYER_SIZE_HEIGHT);
	}

	@Override
	public byte getId() {
		return 0x04;
	}

	@Override
	public BufferedImage getImage() {
		return null;
	}

	@Override
	public int getProps() {
		return PROPS_LIQUID;
	}

}