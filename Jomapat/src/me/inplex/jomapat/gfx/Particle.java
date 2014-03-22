package me.inplex.jomapat.gfx;

import java.awt.image.BufferedImage;

import me.inplex.jomapat.Jomapat;
import me.inplex.jomapat.extra.Maths;

public class Particle {

	private int x;
	private int y;
	private int xDirection;
	private int yDirection;
	private long lifetime;
	private BufferedImage image;
	private ParticleBehaviour behaviour;

	public Particle(int x, int y, int xDirection, int yDirection, long lifetime, ParticleBehaviour behaviour, BufferedImage image) {
		this.x = x;
		this.y = y;
		this.xDirection = xDirection;
		this.yDirection = yDirection;
		this.lifetime = lifetime;
		this.behaviour = behaviour;
		this.image = image;
	}

	public void update() {
		lifetime--;
		switch(behaviour) {
			case RANDOM:
				x += xDirection;
				y += yDirection;
				xDirection += Maths.randomize(-2, 2);
				yDirection += Maths.randomize(-2, 2);
				break;
			case ACCELERATE_SLOWER:
				x += xDirection;
				y += yDirection;
				if(xDirection > 0) {
					xDirection--;
				}
				if(xDirection < 0) {
					xDirection++;
				}
				break;
			case ACCELERATE_FASTER:
				if(Jomapat.game.getTicks() % 30 == 0) {
					// else its too fast
					return;
				}
				x += xDirection;
				y += yDirection;
				if(xDirection > 0) {
					xDirection++;
				}
				if(xDirection < 0) {
					xDirection--;
				}
				break;
			case STAY:
				break;
			case FALL_DOWN:
				x += xDirection;
				y += Math.abs(yDirection);
				break;
			default:
				break;
		}
		
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getxDirection() {
		return xDirection;
	}

	public void setxDirection(int xDirection) {
		this.xDirection = xDirection;
	}

	public int getyDirection() {
		return yDirection;
	}

	public void setyDirection(int yDirection) {
		this.yDirection = yDirection;
	}

	public long getLifetime() {
		return lifetime;
	}

	public void setLifetime(long lifetime) {
		this.lifetime = lifetime;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

}