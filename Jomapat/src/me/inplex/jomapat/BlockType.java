package me.inplex.jomapat;

import java.awt.image.BufferedImage;

public enum BlockType {
	
//	Name		xT	yT	Hard	coll	dmg
	GRASS(	 	0, 	0, 	0.1f, 	true, 	false),
	DIRT(		1, 	0, 	0.1f, 	true, 	false),
	STONE(		2, 	0, 	0.4f, 	true, 	false),
	DIAMOND(	3, 	0, 	0.8f, 	true, 	false);
	
	private BufferedImage sprite;
	private float hardness;
	private boolean collide;
	private boolean damage;
	
	private BlockType(int xTexture, int yTexture, float hardness, boolean collide, boolean damage) {
		this.sprite = SpriteManager.loadBlockImage(xTexture, yTexture);
		this.hardness = hardness;
		this.collide = collide;
		this.damage = damage;
	}
	
	public float getHardness() {
		return hardness;
	}

	public boolean isCollide() {
		return collide;
	}

	public boolean isDamage() {
		return damage;
	}

	public BufferedImage getSprite() {
		return sprite;
	}

}