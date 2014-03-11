package me.inplex.jomapat;

import java.awt.image.BufferedImage;

public class BlockDirt extends Block {

	@Override
	public void load() {
		this.sprite = SpriteManager.loadBlockImage(0, SpriteManager.SPRITE_PLAYER_SIZE_HEIGHT);
	}

	@Override
	public byte getId() {
		return 0x01;
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