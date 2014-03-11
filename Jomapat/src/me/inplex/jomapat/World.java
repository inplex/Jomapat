package me.inplex.jomapat;

public class World {

	private int width;
	private int height;

	// Block at [x][y]
	private Block[][] blocks;

	public World(int width, int height) {
		this.width = width;
		this.height = height;
		this.blocks = new Block[width][height];
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

	public void setBlock(int x, int y, Block block) {
		blocks[x][y] = block;
	}

	public Block getBlockAt(int x, int y) {
		return blocks[x][y];
	}
	
	public void removeBlockAt(int x, int y) {
		blocks[x][y] = null;
	}

	public Block[][] getBlocks() {
		return blocks;
	}

}