package me.inplex.jomapat.world;

import java.awt.image.BufferedImage;

import me.inplex.jomapat.gfx.SpriteManager;

public enum BlockType {

// length of xTextures and yTexturs MUST be the same!
//	Name			xTs					yTs					Hard	coll	dmg
	GRASS	(	 	new int[] { 0, 1, 2, 3, 4, 5 }, 	new int[] { 0, 0, 0, 0, 0, 0 }, 	0.1f, 	true, 	false),
	DIRT	(		new int[] { 6 }, 	new int[] { 0 }, 	0.1f, 	true, 	false),
	STONE	(		new int[] { 7 }, 	new int[] { 0 }, 	0.4f, 	true, 	false),
	DIAMOND	(	new int[] { 8 }, 	new int[] { 0 }, 	0.8f, 	true, 	false),
	WOOD	(		new int[] { 0 }, 	new int[] { 1 }, 	0.35f, 	true, 	false),
	LEAF	(		new int[] { 1 }, 	new int[] { 1 }, 	0.2f, 	true, 	false);
	
	private BufferedImage[] sprites;
	private float hardness;
	private boolean collide;
	private boolean damage;
	
	private BlockType(int[] xTextures, int[] yTextures, float hardness, boolean collide, boolean damage) {
		this.sprites = new BufferedImage[xTextures.length];
		for(int i = 0; i < xTextures.length; i++) {
			this.sprites[i] = SpriteManager.loadBlockImage(xTextures[i], yTextures[i]);
		}
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

	public BufferedImage[] getSprites() {
		return sprites;
	}
	
	public BufferedImage getSprite(int i) {
		return sprites[i];
	}

}