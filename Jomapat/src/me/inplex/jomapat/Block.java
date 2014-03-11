package me.inplex.jomapat;

import java.awt.image.BufferedImage;

public abstract class Block {

	public static final int PROPS_LIQUID = 1;
	public static final int PROPS_COLLIDE = 2;
	public static final int PROPS_LIGHT = 4;
	public static final int PROPS_DAMAGE = 8;

	protected BufferedImage sprite;

	public abstract void load();

	public void update() {

	}

	public abstract byte getId();
	public abstract BufferedImage getImage();

	// For more Props use PROP1 | PROP2 | PROP3 | ...
	public abstract int getProps();

	public boolean isLiquid() {
		return (getProps() & PROPS_LIQUID) == PROPS_LIQUID;
	}

	public boolean isCollide() {
		return (getProps() & PROPS_COLLIDE) == PROPS_COLLIDE;
	}

	public boolean isLight() {
		return (getProps() & PROPS_LIGHT) == PROPS_LIGHT;
	}

	public boolean isDamage() {
		return (getProps() & PROPS_DAMAGE) == PROPS_DAMAGE;
	}


}