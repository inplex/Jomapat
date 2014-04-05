package me.inplex.jomapat.world;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import me.inplex.jomapat.Jomapat;
import me.inplex.jomapat.extra.Maths;
import me.inplex.jomapat.gfx.Particle;
import me.inplex.jomapat.gfx.ParticleBehaviour;
import me.inplex.jomapat.gfx.ParticleManager;

public class World {

	private int width;
	private int height;

	// Block at [x][y]
	private byte[][] blocks;

	public World(int width, int height) {
		this.width = width;
		this.height = height;
		this.blocks = new byte[width][height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				blocks[x][y] = (byte) 0xFF;
			}
		}
	}

	// for example leaf blocks
	ArrayList<Point> blocksToRemove = new ArrayList<Point>();
	ArrayList<Point> blocksToExplode = new ArrayList<Point>();

	public void update() {
		if (Jomapat.game.getTicks() % 120 == 0) {
			if (blocksToRemove.size() > 0) {
				Jomapat.game.getInventory().addBlock(getBlockAt(blocksToRemove.get(0).x, blocksToRemove.get(0).y));
				for (int i = 0; i < new Random().nextInt(20) + 10; i++) {
					ParticleManager.addParticle(new Particle(blocksToRemove.get(0).x * 64 + 32, blocksToRemove.get(0).y * 64 + 32, 1, 1, 45,
							ParticleBehaviour.RANDOM, BlockType.LEAF.getParticle(0)));
				}
				removeBlockAt(blocksToRemove.get(0).x, blocksToRemove.get(0).y);
				blocksToRemove.remove(0);
			}
		}
		for (int i = 0; i < 5; i++) {
			if (blocksToExplode.size() > i) {
				createExplosion(blocksToExplode.get(i).x, blocksToExplode.get(i).y);
				if (new Random().nextInt(2) == 1) {
					int x = blocksToExplode.get(i).x + Maths.randomize(-2, 2);
					int y = blocksToExplode.get(i).y + Maths.randomize(-2, 2);
					if (getBlockAt(x, y) != BlockType.TNT) {
						removeBlockAt(x, y);
					}
				}
				blocksToExplode.remove(i);
			}
		}
	}

	public void detonateTnt(int x, int y) {
		for (int by = y - 4; by < y + 4; by++) {
			for (int bx = x - 4; bx < x + 4; bx++) {
				blocksToExplode.add(new Point(bx, by));
			}
		}
		Collections.shuffle(blocksToExplode);
	}

	public void createExplosion(int x, int y) {
		int r = Maths.randomize(2, 5);
		for (int by = y - r; by < y + r; by++) {
			for (int bx = x - r; bx < x + r; bx++) {
				if (getBlockAt(x, y) == BlockType.TNT) {
					blocksToExplode.add(new Point(bx, by));
				}
			}
		}

		if (getBlockAt(x, y) != null) {
			removeBlockAt(x, y);
		}
		for (int i = 0; i < new Random().nextInt(9) + 1; i++) {
			ParticleManager.addParticle(new Particle(x * 64 + 32, y * 64 + 32, 1, 1, 30, ParticleBehaviour.RANDOM, BlockType.TNT.getParticle(0)));
		}
	}

	// Called when Block is destroyed
	/**
	 * @param block the previous block. must be given since block at x/y got
	 *            destroyed
	 */
	public void updateNearBlocks(int x, int y, final BlockType block) {
		if (block.equals(BlockType.WOOD)) {
			if (getBlockAt(x, y - 1) == BlockType.LEAF) {
				for (int by = y - 3; by < y + 3; by++) {
					for (int bx = x - 3; bx < x + 3; bx++) {
						BlockType bt = getBlockAt(bx, by);
						if (bt == BlockType.LEAF) {
							blocksToRemove.add(new Point(bx, by));
						}
					}
				}
			}
			Collections.shuffle(blocksToRemove);
		}
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setBlock(int x, int y, BlockType block) {
		if (x < 0 || y < 0 || x >= width || y >= height)
			return;
		if (block == null || block.ordinal() == 0xFF) {
			blocks[x][y] = (byte) 0xFF;
			return;
		}
		blocks[x][y] = (byte) block.ordinal();
	}

	public BlockType getBlockAt(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height)
			return null;
		if (blocks[x][y] == (byte) 0xFF)
			return null;
		return BlockType.values()[(int) blocks[x][y]];
	}

	public void removeBlockAt(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height)
			return;
		blocks[x][y] = (byte)0xFF;
	}

}