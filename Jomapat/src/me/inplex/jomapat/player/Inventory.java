package me.inplex.jomapat.player;

import me.inplex.jomapat.Jomapat;
import me.inplex.jomapat.world.BlockType;

public class Inventory {

	public Inventory() {
	}

	private int[] blockInventory = new int[100];

	private boolean show = false;
	private int selected = 0;

	public void addBlock(BlockType block) {
		// yes, this is needed
		if(block == null)
			return;
		if(block.ordinal() > BlockType.values().length-1)
			return;
		blockInventory[block.ordinal()]++;
	}
	
	public void addBlock(BlockType block, int amount) {
		if(block.ordinal() > BlockType.values().length-1)
			return;
		blockInventory[block.ordinal()]+=amount;
	}
	
	public int getBlockAmount(BlockType block) {
		return blockInventory[block.ordinal()];
	}
	
	public void setBlockAmount(BlockType block, int amount) {
		blockInventory[block.ordinal()] = amount;
	}

	public void removeBlock(BlockType block) {
		blockInventory[block.ordinal()] = blockInventory[block.ordinal()] >= 1 ? blockInventory[block.ordinal()] - 1 : 0;
	}
	
	public void removeBlock(BlockType block, int amount) {
		blockInventory[block.ordinal()] = blockInventory[block.ordinal()] >= amount ? blockInventory[block.ordinal()] - amount : blockInventory[block.ordinal()];
	}

	public void show(boolean show) {
		Jomapat.game.getInput().getMouseSingleClick();
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
