package me.inplex.jomapat;

import java.awt.image.BufferedImage;

public class BlockDirt extends Block {

	@Override
	public void load() {
		this.sprite = SpriteManager.loadBlockImage(1,0);
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