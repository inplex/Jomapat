package me.inplex.jomapat.player;

import me.inplex.jomapat.world.BlockType;

public class Inventory {
	
	public Inventory(){}
	
	int[] blockInventory = new int[100];
	int[] itemInventory  = new int[100];
	
	public void addBlock(BlockType block){
		blockInventory[block.ordinal()]++;
	}
	
	public int getBlockAmount(BlockType block){
		return blockInventory[block.ordinal()];
	}
	
	public void removeBlock(BlockType block){
		blockInventory[block.ordinal()]--;
	}
	
	public boolean show = false;
	
	public void show(boolean show){
		this.show = show;
	}
	
	public boolean isVisible(){
		return this.show;
	}

	

}
