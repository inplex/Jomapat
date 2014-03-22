package me.inplex.jomapat.gfx;

import java.util.ArrayList;

public class ParticleManager {

	private static ArrayList<Particle> particles;

	public static void load() {
		particles = new ArrayList<Particle>();
	}

	public static void update() {
		for (int i = 0; i < particles.size(); i++) {
			Particle p = particles.get(i);
			p.update();
			if (p.getLifetime() <= 0) {
				particles.remove(i);
				p = null;
			}
		}
	}

	public static ArrayList<Particle> getParticles() {
		return particles;
	}

	public static void addParticle(Particle p) {
		particles.add(p);
	}

}