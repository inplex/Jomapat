package me.inplex.jomapat.player;

import me.inplex.jomapat.world.BlockType;

public class ItemStack {
	
	private BlockType block;
	private int amount;
	
	public ItemStack(BlockType block, int amount) {
		this.block = block;
		this.amount = amount;
	}

	public BlockType getBlock() {
		return block;
	}

	public void setBlock(BlockType block) {
		this.block = block;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
}