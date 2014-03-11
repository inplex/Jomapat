package me.inplex.jomapat;

import java.awt.image.BufferedImage;

public class BlockWater extends Block {

	@Override
	public void load() {
		this.sprite = SpriteManager.loadBlockImage(0,0);
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