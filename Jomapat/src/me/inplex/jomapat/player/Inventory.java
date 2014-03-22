package me.inplex.jomapat.player;

import me.inplex.jomapat.world.BlockType;

public class Inventory {

	public Inventory() {
	}

	private int[] blockInventory = new int[100];
	// Not needed yet: private int[] itemInventory = new int[100];

	private boolean show = false;
	private int selected = 0;

	public void addBlock(BlockType block) {
		blockInventory[block.ordinal()]++;
	}

	public int getBlockAmount(BlockType block) {
		return blockInventory[block.ordinal()];
	}

	public void removeBlock(BlockType block) {
		blockInventory[block.ordinal()]--;
	}

	public void show(boolean show) {
		this.show = show;
	}

	public boolean isVisible() {
		return this.show;
	}

	public int getSelected() {
		return selected;
	}

	public void setSelected(int selected) {
		this.selected = selected;
	}

}
