package me.inplex.jomapat.world;

import java.util.Random;

import me.inplex.jomapat.enemy.Enemy;
import me.inplex.jomapat.enemy.EnemyRenderer;
import me.inplex.jomapat.extra.Maths;

public class WorldGenerator {

	private static void tree(World world, int x, int y) {
		world.setBlock(x, y, BlockType.WOOD);
		world.setBlock(x, y - 1, BlockType.WOOD);
		world.setBlock(x, y - 2, BlockType.WOOD);
		world.setBlock(x, y - 3, BlockType.WOOD);
		world.setBlock(x - 1, y - 4, BlockType.LEAF);
		world.setBlock(x, y - 4, BlockType.LEAF);
		world.setBlock(x + 1, y - 4, BlockType.LEAF);
		world.setBlock(x - 1, y - 5, BlockType.LEAF);
		world.setBlock(x, y - 5, BlockType.LEAF);
		world.setBlock(x + 1, y - 5, BlockType.LEAF);
		world.setBlock(x - 1, y - 6, BlockType.LEAF);
		world.setBlock(x, y - 6, BlockType.LEAF);
		world.setBlock(x + 1, y - 6, BlockType.LEAF);
	}

	public static World generateWorld(int width, int height) {
		World world = new World(width, height);

		int startY = 20;
		int rand;

		for (int x = 0; x < width; x++) {

			// World height

			rand = Maths.randomize(1, 4);
			if (rand == 2 || rand == 2) {
				startY = startY + 1;
			}
			if (rand == 3) {
				startY = startY - 1;
			}
			if (startY < 0) {
				startY = 0;
			}

			for (int y = startY; y < height; y++) {
				if (y == startY) { // Use Grass or deeper grounds
					world.setBlock(x, y, BlockType.GRASS);
				} else {
					if (y < 50 - startY + Maths.randomize(0, 2)) {
						world.setBlock(x, y, BlockType.DIRT);
					} else {
						if (Maths.randomize(1, 15) == 5) {
							BlockType bt = BlockType.IRON;
							if (y > width / 10 && new Random().nextInt(3) == 1) {
								bt = BlockType.GOLD;
							}
							if (y > width / 4 && new Random().nextInt(5) == 1) {
								bt = BlockType.DIAMOND;
							}
							world.setBlock(x, y, bt);
						} else {
							world.setBlock(x, y, BlockType.STONE);
						}
					}
				}

			}
			if (x > 4 && startY > 8 && x < world.getWidth() - 2 && Maths.randomize(1, 6) == 1) {
				tree(world, x, startY - 1);
			}
			if (Maths.randomize(0, 50) == 1) {
				EnemyRenderer.enemies.add(new Enemy(x*64, (startY-5)*64));
			}

		}
		CaveBuilder.buildCave(world);
		world.finish();
		return world;
	}

}