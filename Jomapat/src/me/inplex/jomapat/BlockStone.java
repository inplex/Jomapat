package me.inplex.jomapat;

import java.awt.image.BufferedImage;

public class BlockStone extends Block {

	private BufferedImage sprite = null;

	public void load() {
		this.sprite = SpriteManager.loadBlockImage(SpriteManager.SPRITE_BLOCK_SIZE*2, SpriteManager.SPRITE_PLAYER_SIZE_HEIGHT);
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