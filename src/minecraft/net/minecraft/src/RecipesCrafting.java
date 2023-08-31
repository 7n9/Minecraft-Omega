package net.minecraft.src;

public class RecipesCrafting {
	public void addRecipes(CraftingManager craftingManager1) {
		craftingManager1.addRecipe(new ItemStack(Block.chest), "###", "# #", "###", '#', Block.planks);
		craftingManager1.addRecipe(new ItemStack(Block.stoneOvenIdle), "###", "# #", "###", '#', Block.cobblestone);
		craftingManager1.addRecipe(new ItemStack(Block.workbench), "##", "##", '#', Block.planks);
		craftingManager1.addRecipe(new ItemStack(Block.sandStone), "##", "##", '#', Block.sand);
	}
}
