package me.inplex.jomapat;

public class WorldGenerator {
	
	public static World generateWorld(int width, int height) {
		World world = new World(width, height);

		int startY = 7;
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
					if (y < 20-startY + Maths.randomize(0, 2)) { // Use dirt or stone
						world.setBlock(x, y, BlockType.DIRT);
					} else {
						if (Maths.randomize(1, 15) == 5) // ... or maybe
															// diamond...
						{
							world.setBlock(x, y, BlockType.DIAMOND);
						} else {
							world.setBlock(x, y, BlockType.STONE);
						}
					}
				}
			}

		}
		return world;
	}

}
