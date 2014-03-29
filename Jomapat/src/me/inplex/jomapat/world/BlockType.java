package me.inplex.jomapat.world;

import java.awt.image.BufferedImage;

import me.inplex.jomapat.extra.Util;
import me.inplex.jomapat.gfx.SpriteManager;

public enum BlockType {

// length of xTextures and yTexturs MUST be the same!
//	Name				xTs								yTs								hard	coll	dmg		place
	
	// First Row
	GRASS		(	 	new int[] { 0, 1, 2, 3, 4, 5 }, new int[] { 0, 0, 0, 0, 0, 0 }, 0.1f, 	true, 	false, 	true),
	DIRT		(		new int[] { 6 }, 				new int[] { 0 }, 				0.1f, 	true, 	false, 	true),
	STONE		(		new int[] { 7 }, 				new int[] { 0 }, 				0.4f, 	true, 	false, 	true),
	DIAMOND		(		new int[] { 8 }, 				new int[] { 0 }, 				0.8f, 	true, 	false, 	true),
	IRON		(		new int[] { 9 }, 				new int[] { 0 }, 				0.5f, 	true, 	false, 	true),
	GOLD		(		new int[] { 10 }, 				new int[] { 0 }, 				0.6f, 	true, 	false, 	true),
	// Second Row
	WOOD		(		new int[] { 0 }, 				new int[] { 1 }, 				0.3f, 	true, 	false, 	true),
	LEAF		(		new int[] { 1 }, 				new int[] { 1 }, 				0.1f, 	true, 	false, 	true),
	WOODPLANK	(		new int[] { 2 }, 				new int[] { 1 }, 				0.35f, 	true, 	false, 	true),
	WOODDOOR	(		new int[] { 3 }, 				new int[] { 1 }, 				0.5f, 	true, 	false, 	true),
	STONEDOOR	(		new int[] { 4 }, 				new int[] { 1 }, 				0.6f, 	true, 	false, 	true),
	IRONBLOCK	(		new int[] { 5 }, 				new int[] { 1 }, 				0.7f, 	true, 	false, 	true),
	GOLDBLOCK	(		new int[] { 6 }, 				new int[] { 1 }, 				0.8f, 	true, 	false, 	true),
	DIAMONDBLOCK(		new int[] { 7 }, 				new int[] { 1 }, 				0.9f, 	true, 	false, 	true);
	
	private BufferedImage[] sprites;
	private BufferedImage[] particles;
	private float hardness;
	private boolean collide;
	private boolean damage;
	private boolean placeable;
	
	private BlockType(int[] xTextures, int[] yTextures, float hardness, boolean collide, boolean damage, boolean placeable) {
		this.sprites = new BufferedImage[xTextures.length];
		this.particles = new BufferedImage[xTextures.length];
		for(int i = 0; i < xTextures.length; i++) {
			this.sprites[i] = SpriteManager.loadBlockImage(xTextures[i], yTextures[i]);
		}
		for(int i = 0; i < xTextures.length; i++) {
			this.particles[i] = Util.getScaledImage(sprites[i], 8, 8);
		}
		this.hardness = hardness;
		this.collide = collide;
		this.damage = damage;
		this.placeable = placeable;
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
	
	public BufferedImage[] getParticles() {
		return particles;
	}
	
	public BufferedImage getParticle(int i) {
		return particles[i];
	}

	public boolean isPlaceable() {
		return placeable;
	}

}