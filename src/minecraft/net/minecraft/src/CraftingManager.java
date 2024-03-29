package net.minecraft.src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CraftingManager {
    private static final CraftingManager instance = new CraftingManager();
    private final List<IRecipe> recipes = new ArrayList<IRecipe>();

    public static CraftingManager getInstance() {
        return instance;
    }

    private CraftingManager() {
        (new RecipesTools()).addRecipes(this);

        (new RecipesWeapons()).addRecipes(this);

        (new RecipesIngots()).addRecipes(this);

        (new RecipesFood()).addRecipes(this);

        (new RecipesCrafting()).addRecipes(this);

        (new RecipesArmor()).addRecipes(this);

        (new RecipesDyes()).addRecipes(this);

        this.addRecipe(new ItemStack(Item.paper, 3), "###",

                '#', Item.reed);

        this.addRecipe(new ItemStack(Item.book, 1), "#",
                "#",
                "#",

                '#', Item.paper);

        this.addRecipe(new ItemStack(Block.fence, 2), "###",
                "###",

                '#', Item.stick);

        this.addRecipe(new ItemStack(Block.jukebox, 1), "###",
                "#X#",
                "###",

                '#', Block.planks, 'X', Item.diamond);

        this.addRecipe(new ItemStack(Block.musicBlock, 1), "###",
                "#X#",
                "###",

                '#', Block.planks, 'X', Item.redstone);

        this.addRecipe(new ItemStack(Block.bookShelf, 1), "###",
                "XXX",
                "###",

                '#', Block.planks, 'X', Item.book);

        this.addRecipe(new ItemStack(Block.blockSnow, 1), "##",
                "##",

                '#', Item.snowball);

        this.addRecipe(new ItemStack(Block.blockClay, 1), "##",
                "##",

                '#', Item.clay);

        this.addRecipe(new ItemStack(Block.brick, 1), "##",
                "##",

                '#', Item.brick);

        this.addRecipe(new ItemStack(Block.glowStone, 1), "##",
                "##",

                '#', Item.lightStoneDust);

        this.addRecipe(new ItemStack(Block.cloth, 1), "##",
                "##",

                '#', Item.silk);

        this.addRecipe(new ItemStack(Block.tnt, 1), "X#X",
                "#X#",
                "X#X",

                'X', Item.gunpowder, '#', Block.sand);

        this.addRecipe(new ItemStack(Block.stairSingle, 3, 3), "###",

                '#', Block.cobblestone);

        this.addRecipe(new ItemStack(Block.stairSingle, 3, 0), "###",

                '#', Block.stone);

        this.addRecipe(new ItemStack(Block.stairSingle, 3, 1), "###",

                '#', Block.sandStone);

        this.addRecipe(new ItemStack(Block.stairSingle, 3, 2), "###",

                '#', Block.planks);

        this.addRecipe(new ItemStack(Block.ladder, 2), "# #",
                "###",
                "# #",

                '#', Item.stick);

        this.addRecipe(new ItemStack(Item.doorWood, 1), "##",
                "##",
                "##",

                '#', Block.planks);

        this.addRecipe(new ItemStack(Block.trapdoor, 2), "###",
                "###",

                '#', Block.planks);

        this.addRecipe(new ItemStack(Item.doorSteel, 1), "##",
                "##",
                "##",

                '#', Item.ingotIron);

        this.addRecipe(new ItemStack(Item.sign, 1), "###",
                "###",
                " X ",

                '#', Block.planks, 'X', Item.stick);

        this.addRecipe(new ItemStack(Item.cake, 1), "AAA",
                "BEB",
                "CCC",

                'A', Item.bucketMilk, 'B', Item.sugar, 'C', Item.wheat, 'E', Item.egg);

        this.addRecipe(new ItemStack(Item.sugar, 1), "#",

                '#', Item.reed);

        this.addRecipe(new ItemStack(Block.planks, 4), "#",

                '#', Block.wood);

        this.addRecipe(new ItemStack(Item.stick, 4), "#",
                "#",

                '#', Block.planks);

        this.addRecipe(new ItemStack(Block.torchWood, 4), "X",
                "#",

                'X', Item.coal, '#', Item.stick);

        this.addRecipe(new ItemStack(Block.torchWood, 4), "X",
                "#",

                'X', new ItemStack(Item.coal, 1, 1), '#', Item.stick);

        this.addRecipe(new ItemStack(Item.bowlEmpty, 4), "# #",
                " # ",

                '#', Block.planks);

        this.addRecipe(new ItemStack(Block.rail, 16), "X X",
                "X#X",
                "X X",

                'X', Item.ingotIron, '#', Item.stick);

        this.addRecipe(new ItemStack(Block.railPowered, 6), "X X",
                "X#X",
                "XRX",

                'X', Item.ingotGold, 'R', Item.redstone, '#', Item.stick);

        this.addRecipe(new ItemStack(Block.railDetector, 6), "X X",
                "X#X",
                "XRX",

                'X', Item.ingotIron, 'R', Item.redstone, '#', Block.pressurePlateStone);

        this.addRecipe(new ItemStack(Item.minecartEmpty, 1), "# #",
                "###",

                '#', Item.ingotIron);

        this.addRecipe(new ItemStack(Block.pumpkinLantern, 1), "A",
                "B",

                'A', Block.pumpkin, 'B', Block.torchWood);

        this.addRecipe(new ItemStack(Item.minecartCrate, 1), "A",
                "B",

                'A', Block.chest, 'B', Item.minecartEmpty);

        this.addRecipe(new ItemStack(Item.minecartPowered, 1), "A",
                "B",

                'A', Block.stoneOvenIdle, 'B', Item.minecartEmpty);

        this.addRecipe(new ItemStack(Item.boat, 1), "# #",
                "###",

                '#', Block.planks);

        this.addRecipe(new ItemStack(Item.bucketEmpty, 1), "# #",
                " # ",

                '#', Item.ingotIron);

        this.addRecipe(new ItemStack(Item.flintAndSteel, 1), "A ",
                " B",

                'A', Item.ingotIron, 'B', Item.flint);

        this.addRecipe(new ItemStack(Item.bread, 1), "###",

                '#', Item.wheat);

        this.addRecipe(new ItemStack(Block.stairCompactPlanks, 4), "#  ",
                "## ",
                "###",

                '#', Block.planks);

        this.addRecipe(new ItemStack(Item.fishingRod, 1), "  #",
                " #X",
                "# X",

                '#', Item.stick, 'X', Item.silk);

        this.addRecipe(new ItemStack(Block.stairCompactCobblestone, 4), "#  ",
                "## ",
                "###",

                '#', Block.cobblestone);

        this.addRecipe(new ItemStack(Item.painting, 1), "###",
                "#X#",
                "###",

                '#', Item.stick, 'X', Block.cloth);

        this.addRecipe(new ItemStack(Item.appleGold, 1), "###",
                "#X#",
                "###",

                '#', Block.blockGold, 'X', Item.appleRed);

        this.addRecipe(new ItemStack(Block.lever, 1), "X",
                "#",

                '#', Block.cobblestone, 'X', Item.stick);

        this.addRecipe(new ItemStack(Block.torchRedstoneActive, 1), "X",
                "#",

                '#', Item.stick, 'X', Item.redstone);

        this.addRecipe(new ItemStack(Item.redstoneRepeater, 1), "#X#",
                "III",

                '#', Block.torchRedstoneActive, 'X', Item.redstone, 'I', Block.stone);

        this.addRecipe(new ItemStack(Item.pocketSundial, 1), " # ",
                "#X#",
                " # ",

                '#', Item.ingotGold, 'X', Item.redstone);

        this.addRecipe(new ItemStack(Item.compass, 1), " # ",
                "#X#",
                " # ",

                '#', Item.ingotIron, 'X', Item.redstone);

        this.addRecipe(new ItemStack(Item.mapItem, 1), "###",
                "#X#",
                "###",

                '#', Item.paper, 'X', Item.compass);

        this.addRecipe(new ItemStack(Block.button, 1), "#",
                "#",

                '#', Block.stone);

        this.addRecipe(new ItemStack(Block.pressurePlateStone, 1), "##",

                '#', Block.stone);

        this.addRecipe(new ItemStack(Block.pressurePlatePlanks, 1), "##",

                '#', Block.planks);

        this.addRecipe(new ItemStack(Block.dispenser, 1), "###",
                "#X#",
                "#R#",

                '#', Block.cobblestone, 'X', Item.bow, 'R', Item.redstone);

        this.addRecipe(new ItemStack(Block.pistonBase, 1), "TTT",
                "#X#",
                "#R#",

                '#', Block.cobblestone, 'X', Item.ingotIron, 'R', Item.redstone, 'T', Block.planks);

        this.addRecipe(new ItemStack(Block.pistonStickyBase, 1), "S",
                "P",

                'S', Item.slimeBall, 'P', Block.pistonBase);

        this.addRecipe(new ItemStack(Item.bed, 1), "###",
                "XXX",

                '#', Block.cloth, 'X', Block.planks);

        this.recipes.sort(new RecipeSorter(this));
        System.out.println(this.recipes.size() + " recipes");
    }

    public void addRecipe(ItemStack itemStack1, Object... object2) {
        StringBuilder string3 = new StringBuilder();
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        if (object2[i4] instanceof String[]) {
            String[] string11 = (String[]) object2[i4++];

            for (String string9 : string11) {
                ++i6;
                i5 = string9.length();
                string3.append(string9);
            }
        } else {
            while (object2[i4] instanceof String) {
                String string7 = (String) object2[i4++];
                ++i6;
                i5 = string7.length();
                string3.append(string7);
            }
        }

        HashMap<Character, ItemStack> hashMap12;
        for (hashMap12 = new HashMap<Character, ItemStack>(); i4 < object2.length; i4 += 2) {
            Character character13 = (Character) object2[i4];
            ItemStack itemStack15 = null;
            if (object2[i4 + 1] instanceof Item) {
                itemStack15 = new ItemStack((Item) object2[i4 + 1]);
            } else if (object2[i4 + 1] instanceof Block) {
                itemStack15 = new ItemStack((Block) object2[i4 + 1], 1, -1);
            } else if (object2[i4 + 1] instanceof ItemStack) {
                itemStack15 = (ItemStack) object2[i4 + 1];
            }

            hashMap12.put(character13, itemStack15);
        }

        ItemStack[] itemStack14 = new ItemStack[i5 * i6];

        for (int i16 = 0; i16 < i5 * i6; ++i16) {
            char c10 = string3.charAt(i16);
            if (hashMap12.containsKey(c10)) {
                itemStack14[i16] = (hashMap12.get(c10)).copy();
            } else {
                itemStack14[i16] = null;
            }
        }

        this.recipes.add(new ShapedRecipes(i5, i6, itemStack14, itemStack1));
    }

    void addShapelessRecipe(ItemStack itemStack1, Object... object2) {
        ArrayList<ItemStack> arrayList3 = new ArrayList<ItemStack>();

        for (Object object7 : object2) {
            if (object7 instanceof ItemStack) {
                arrayList3.add(((ItemStack) object7).copy());
            } else if (object7 instanceof Item) {
                arrayList3.add(new ItemStack((Item) object7));
            } else {
                if (!(object7 instanceof Block)) {
                    throw new RuntimeException("Invalid shapeless recipe!");
                }

                arrayList3.add(new ItemStack((Block) object7));
            }
        }

        this.recipes.add(new ShapelessRecipes(itemStack1, arrayList3));
    }

    public ItemStack findMatchingRecipe(InventoryCrafting inventoryCrafting1) {
        for (IRecipe iRecipe3 : this.recipes) {
            if (iRecipe3.matches(inventoryCrafting1)) {
                return iRecipe3.getCraftingResult(inventoryCrafting1);
            }
        }

        return null;
    }

    public List<IRecipe> getRecipeList() {
        return this.recipes;
    }
}
