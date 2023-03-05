package net.minecraft.src;

public class RecipesCrafting {
	public void addRecipes(CraftingManager craftingManager1) {
		craftingManager1.addRecipe(new ItemStack(Block.chest), new Object[]{"###", "# #", "###", '#', Block.planks});
		craftingManager1.addRecipe(new ItemStack(Block.stoneOvenIdle), new Object[]{"###", "# #", "###", '#', Block.cobblestone});
		craftingManager1.addRecipe(new ItemStack(Block.workbench), new Object[]{"##", "##", '#', Block.planks});
		craftingManager1.addRecipe(new ItemStack(Block.sandStone), new Object[]{"##", "##", '#', Block.sand});
		craftingManager1.addRecipe(new ItemStack(Block.field_35052_bn, 4), new Object[]{"##", "##", '#', Block.stone});
		craftingManager1.addRecipe(new ItemStack(Block.field_35055_bq, 16), new Object[]{"###", "###", '#', Item.ingotIron});
		craftingManager1.addRecipe(new ItemStack(Block.field_35049_br, 16), new Object[]{"###", "###", '#', Block.glass});
	}
}
