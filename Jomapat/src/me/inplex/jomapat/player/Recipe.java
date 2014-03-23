package me.inplex.jomapat.player;

import me.inplex.jomapat.world.BlockType;

public enum Recipe {
	
	WOODPLANKS(new ItemStack[] { new ItemStack(BlockType.WOOD, 1) }, new ItemStack(BlockType.WOODPLANK, 4)),
	WOODDOOR(new ItemStack[] { new ItemStack(BlockType.WOODPLANK, 12) }, new ItemStack(BlockType.WOODDOOR, 1)),
	STONEDOOR(new ItemStack[] { new ItemStack(BlockType.STONE, 24) }, new ItemStack(BlockType.STONEDOOR, 1));

	private ItemStack[] ingredients;
	private ItemStack output;
	
	private Recipe(ItemStack[] ingredients, ItemStack output) {
		this.ingredients = ingredients;
		this.output = output;
	}

	public ItemStack[] getIngredients() {
		return ingredients;
	}

	public ItemStack getOutput() {
		return output;
	}
	
}