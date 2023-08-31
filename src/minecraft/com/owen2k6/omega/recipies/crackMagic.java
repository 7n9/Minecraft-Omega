package com.owen2k6.omega.recipies;

import net.minecraft.src.Block;
import net.minecraft.src.CraftingManager;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class crackMagic {
    public void addRecipes(CraftingManager craftingManager1) {
        craftingManager1.addRecipe(new ItemStack(Item.magicDust, 1), "#",
                '#', Item.magicreed);
    }
}

//        this.addRecipe(new ItemStack(Item.magicDust, 1), new Object[]{
//                "#",
//
//                '#', Item.magicreed});
