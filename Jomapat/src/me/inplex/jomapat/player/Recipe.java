package me.inplex.jomapat.player;

import me.inplex.jomapat.Jomapat;
import me.inplex.jomapat.world.BlockType;

public enum Recipe {

	WOODPLANK		(new ItemStack[] { new ItemStack(BlockType.WOOD, 1) }, 			new ItemStack(BlockType.WOODPLANK, 4)), //
	WOODDOOR		(new ItemStack[] { new ItemStack(BlockType.WOODPLANK, 12) }, 	new ItemStack(BlockType.WOODDOOR, 1)), //
	STONEDOOR		(new ItemStack[] { new ItemStack(BlockType.STONE, 24) },		new ItemStack(BlockType.STONEDOOR, 1)), //
	IRONBLOCK		(new ItemStack[] { new ItemStack(BlockType.IRON, 16) }, 		new ItemStack(BlockType.IRONBLOCK, 1)), //
	GOLDBLOCK		(new ItemStack[] { new ItemStack(BlockType.GOLD, 16) }, 		new ItemStack(BlockType.GOLDBLOCK, 1)), //
	DIAMONDBLOCK	(new ItemStack[] { new ItemStack(BlockType.DIAMOND, 16) }, 		new ItemStack(BlockType.DIAMONDBLOCK, 1)); //

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

	public static boolean shown = false;
	public static int selected = 0;

	/**
	 * @param r the recipe to be checked
	 * @return true, if the player has all ingredients needed for the Recipe
	 */
	public static boolean canCraft(Recipe r) {
		for (int i = 0; i < r.getIngredients().length; i++) {
			ItemStack is = r.getIngredients()[i];
			if (!(Jomapat.game.getInventory().getBlockAmount(is.getBlock()) >= is.getAmount())) {
				return false;
			}
		}
		return true;
	}

	public static void craft(Recipe r) {
		if (!canCraft(r))
			return;
		for (int i = 0; i < r.getIngredients().length; i++) {
			Jomapat.game.getInventory().removeBlock(r.getIngredients()[i].getBlock(), r.getIngredients()[i].getAmount());
		}
		Jomapat.game.getInventory().addBlock(r.getOutput().getBlock(), r.getOutput().getAmount());
	}

	public static int getSelected() {
		return selected;
	}

	public static void setSelected(int selected) {
		Recipe.selected = selected;
	}

}