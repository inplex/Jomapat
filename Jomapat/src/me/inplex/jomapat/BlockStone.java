package me.inplex.jomapat;

import java.awt.image.BufferedImage;

public class BlockStone extends Block {

	private BufferedImage sprite = null;

	public void load() {
		this.sprite = SpriteManager.loadBlockImage(0,0);
	}

	@Override
	public byte getId() {
		return 0x03;
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