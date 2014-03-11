package me.inplex.jomapat;

import java.awt.image.BufferedImage;

public class BlockGrass extends Block {

	@Override
	public void load() {
		this.sprite = SpriteManager.loadBlockImage(0,0);
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