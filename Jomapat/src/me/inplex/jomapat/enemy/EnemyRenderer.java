package me.inplex.jomapat.enemy;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import me.inplex.jomapat.gfx.Renderer;

public class EnemyRenderer {

	public static List<Enemy> enemies = new ArrayList<Enemy>();

	public static void update() {
		for (Enemy e : enemies) {
			e.update();
		}
	}

	public static void renderEnemies(Graphics g) {
		for (Enemy e : enemies) {
			g.drawImage(Enemy.SPRITE_IDLE[0], e.getX() - Renderer.getXOffset(), e.getY() - Renderer.getYOffset(), null);
		}
	}

}
