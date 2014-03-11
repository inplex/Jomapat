package me.inplex.jomapat;

import java.awt.image.BufferedImage;

public class BlockDiamond extends Block {

	private BufferedImage sprite = null;

	public void load() {
		this.sprite = SpriteManager.loadBlockImage(3,0);
	}

	@Override
	public byte getId() {
		return 0x05;
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