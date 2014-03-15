package me.inplex.jomapat;

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
		if(x < 0 || y < 0 || x >= width || y >= height)
			return null;
		return blocks[x][y];
	}
	
	public void removeBlockAt(int x, int y) {
		blocks[x][y] = null;
	}

}