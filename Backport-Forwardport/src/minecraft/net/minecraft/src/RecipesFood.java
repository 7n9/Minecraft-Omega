package net.minecraft.src;

public class RecipesFood {
	public void addRecipes(CraftingManager craftingManager1) {
		craftingManager1.addRecipe(new ItemStack(Item.bowlSoup), new Object[]{"Y", "X", "#", 'X', Block.mushroomBrown, 'Y', Block.mushroomRed, '#', Item.bowlEmpty});
		craftingManager1.addRecipe(new ItemStack(Item.bowlSoup), new Object[]{"Y", "X", "#", 'X', Block.mushroomRed, 'Y', Block.mushroomBrown, '#', Item.bowlEmpty});
		craftingManager1.addRecipe(new ItemStack(Item.cookie, 8), new Object[]{"#X#", 'X', new ItemStack(Item.dyePowder, 1, 3), '#', Item.wheat});
		craftingManager1.addRecipe(new ItemStack(Block.field_35281_bs), new Object[]{"MMM", "MMM", "MMM", 'M', Item.field_35421_bg});
		craftingManager1.addRecipe(new ItemStack(Item.field_35423_bi), new Object[]{"M", 'M', Item.field_35421_bg});
	}
}