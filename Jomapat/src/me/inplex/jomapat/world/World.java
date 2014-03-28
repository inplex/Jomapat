package me.inplex.jomapat.world;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;

import me.inplex.jomapat.Jomapat;

public class World {

	private int width;
	private int height;

	// Block at [x][y]
	private BlockType[][] blocks;

	public World(int width, int height) {
		this.width = width;
		this.height = height;
		this.blocks = new BlockType[width][height];
	}

	// for example leaf blocks
	ArrayList<Point> blocksToRemove = new ArrayList<Point>();

	public void update() {
		if (Jomapat.game.getTicks() % 120 == 0) {
			if (blocksToRemove.size() > 0) {
				Jomapat.game.getInventory().addBlock(getBlockAt(blocksToRemove.get(0).x, blocksToRemove.get(0).y));
				removeBlockAt(blocksToRemove.get(0).x, blocksToRemove.get(0).y);
				blocksToRemove.remove(0);
			}
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
		blocks[x][y] = block;
	}

	public BlockType getBlockAt(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height)
			return null;
		return blocks[x][y];
	}

	public void removeBlockAt(int x, int y) {
		blocks[x][y] = null;
	}

}