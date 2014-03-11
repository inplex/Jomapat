package me.inplex.jomapat;

import java.awt.image.BufferedImage;

public class BlockGrass extends Block {

	@Override
	public void load() {
		this.sprite = SpriteManager.loadBlockImage(SpriteManager.SPRITE_BLOCK_SIZE, SpriteManager.SPRITE_PLAYER_SIZE_HEIGHT);
	}

	@Override
	public byte getId() {
		return 0x02;
	}

	@Override
	public BufferedImage getImage() {
		return sprite;
	}

	@Override
	public int getProps() {
		return PROPS_COLLIDE;
	}

}