package net.minecraft.src;

public class RecipesFood {
	public void addRecipes(CraftingManager craftingManager1) {
		craftingManager1.addRecipe(new ItemStack(Item.bowlSoup), new Object[]{"Y", "X", "#", 'X', Block.mushroomBrown, 'Y', Block.mushroomRed, '#', Item.bowlEmpty});
		craftingManager1.addRecipe(new ItemStack(Item.bowlSoup), new Object[]{"Y", "X", "#", 'X', Block.mushroomRed, 'Y', Block.mushroomBrown, '#', Item.bowlEmpty});
		craftingManager1.addRecipe(new ItemStack(Item.cookie, 8), new Object[]{"#X#", 'X', new ItemStack(Item.dyePowder, 1, 3), '#', Item.wheat});
		craftingManager1.addRecipe(new ItemStack(Block.field_35048_bs), new Object[]{"MMM", "MMM", "MMM", 'M', Item.field_35416_bd});
		craftingManager1.addRecipe(new ItemStack(Item.field_35412_bf), new Object[]{"M", 'M', Item.field_35416_bd});
	}
}
