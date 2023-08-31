package net.minecraft.src;

public class RecipesWeapons {
	private final String[][] recipePatterns = new String[][]{{"X", "X", "#"}};
	private final Object[][] recipeItems = new Object[][]{{Block.planks, Block.cobblestone, Item.ingotIron, Item.diamond, Item.ingotGold}, {Item.swordWood, Item.swordStone, Item.swordSteel, Item.swordDiamond, Item.swordGold}};

	public void addRecipes(CraftingManager craftingManager1) {
		for(int i2 = 0; i2 < this.recipeItems[0].length; ++i2) {
			Object object3 = this.recipeItems[0][i2];

			for(int i4 = 0; i4 < this.recipeItems.length - 1; ++i4) {
				Item item5 = (Item)this.recipeItems[i4 + 1][i2];
				craftingManager1.addRecipe(new ItemStack(item5), this.recipePatterns[i4], '#', Item.stick, 'X', object3);
			}
		}

		craftingManager1.addRecipe(new ItemStack(Item.bow, 1), " #X", "# X", " #X", 'X', Item.silk, '#', Item.stick);
		craftingManager1.addRecipe(new ItemStack(Item.arrow, 4), "X", "#", "Y", 'Y', Item.feather, 'X', Item.flint, '#', Item.stick);
	}
}
