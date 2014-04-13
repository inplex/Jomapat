package me.inplex.jomapat.world;

import me.inplex.jomapat.extra.Maths;

public class CaveBuilder {

	public static void buildCave(World world) {
		int caveRandom[][] = new int[world.getWidth()][world.getHeight()];
		for (int i = 0; i < 10; i++) {
			for (int x = 2; x < world.getWidth() - 4; x++) {
				for (int y = 2; y < world.getHeight() - 4; y++) {
					int random = caveRandom[x][y] == 0 ? 300 : caveRandom[x][y];
					if (Maths.randomize(0, random) == 1 && world.getBlockAt(x, y) == BlockType.STONE) {
						if (world.getBlockAt(x, y) != BlockType.DIRT) {
							world.removeBlockAt(x, y);
							caveRandom[x - 1][y - 1] = 3;
							caveRandom[x - 1][y] = 3;
							caveRandom[x][y - 1] = 3;
							caveRandom[x + 1][y + 1] = 3;
							caveRandom[x][y + 1] = 3;
							caveRandom[x + 1][y] = 3;
						}
					}
				}
			}
		}
	}

}
