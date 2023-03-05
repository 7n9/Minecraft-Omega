package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;

class ContainerCreative extends Container {
	public List field_35375_a = new ArrayList();

	public ContainerCreative(EntityPlayer entityPlayer1) {
		Block[] block2 = new Block[]{Block.cobblestone, Block.stone, Block.oreDiamond, Block.oreGold, Block.oreIron, Block.oreCoal, Block.oreLapis, Block.oreRedstone, Block.field_35285_bn, Block.field_35285_bn, Block.field_35285_bn, Block.blockClay, Block.blockDiamond, Block.blockGold, Block.blockSteel, Block.bedrock, Block.blockLapis, Block.brick, Block.cobblestoneMossy, Block.stairSingle, Block.stairSingle, Block.stairSingle, Block.stairSingle, Block.stairSingle, Block.stairSingle, Block.obsidian, Block.netherrack, Block.slowSand, Block.glowStone, Block.wood, Block.wood, Block.wood, Block.leaves, Block.dirt, Block.grass, Block.sand, Block.sandStone, Block.gravel, Block.web, Block.planks, Block.sapling, Block.sapling, Block.sapling, Block.deadBush, Block.sponge, Block.ice, Block.blockSnow, Block.plantYellow, Block.plantRed, Block.mushroomBrown, Block.mushroomRed, Block.reed, Block.cactus, Block.field_35281_bs, Block.pumpkin, Block.pumpkinLantern, Block.field_35278_bv, Block.field_35288_bq, Block.field_35282_br, Block.chest, Block.workbench, Block.glass, Block.tnt, Block.bookShelf, Block.cloth, Block.cloth, Block.cloth, Block.cloth, Block.cloth, Block.cloth, Block.cloth, Block.cloth, Block.cloth, Block.cloth, Block.cloth, Block.cloth, Block.cloth, Block.cloth, Block.cloth, Block.cloth, Block.dispenser, Block.stoneOvenIdle, Block.music, Block.jukebox, Block.pistonStickyBase, Block.pistonBase, Block.fence, Block.field_35277_bw, Block.ladder, Block.rail, Block.railPowered, Block.railDetector, Block.torchWood, Block.stairCompactPlanks, Block.stairCompactCobblestone, Block.field_35280_bx, Block.field_35279_by, Block.lever, Block.pressurePlateStone, Block.pressurePlatePlanks, Block.torchRedstoneActive, Block.button, Block.cake, Block.trapdoor};
		int i3 = 0;
		int i4 = 0;
		int i5 = 0;
		int i6 = 0;
		int i7 = 0;

		int i8;
		int i9;
		for(i8 = 0; i8 < block2.length; ++i8) {
			i9 = 0;
			if(block2[i8] == Block.cloth) {
				i9 = i3++;
			} else if(block2[i8] == Block.stairSingle) {
				i9 = i4++;
			} else if(block2[i8] == Block.wood) {
				i9 = i5++;
			} else if(block2[i8] == Block.sapling) {
				i9 = i6++;
			} else if(block2[i8] == Block.field_35285_bn) {
				i9 = i7++;
			}

			this.field_35375_a.add(new ItemStack(block2[i8], 1, i9));
		}

		for(i8 = 256; i8 < Item.itemsList.length; ++i8) {
			if(Item.itemsList[i8] != null) {
				this.field_35375_a.add(new ItemStack(Item.itemsList[i8]));
			}
		}

		for(i8 = 1; i8 < 16; ++i8) {
			this.field_35375_a.add(new ItemStack(Item.dyePowder.shiftedIndex, 1, i8));
		}

		InventoryPlayer inventoryPlayer11 = entityPlayer1.inventory;

		for(i9 = 0; i9 < 9; ++i9) {
			for(int i10 = 0; i10 < 8; ++i10) {
				this.addSlot(new Slot(GuiContainerCreative.func_35310_g(), i10 + i9 * 8, 8 + i10 * 18, 18 + i9 * 18));
			}
		}

		for(i9 = 0; i9 < 9; ++i9) {
			this.addSlot(new Slot(inventoryPlayer11, i9, 8 + i9 * 18, 184));
		}

		this.func_35374_a(0.0F);
	}

	public boolean canInteractWith(EntityPlayer entityPlayer1) {
		return true;
	}

	public void func_35374_a(float f1) {
		int i2 = this.field_35375_a.size() / 8 - 8 + 1;
		int i3 = (int)((double)(f1 * (float)i2) + 0.5D);
		if(i3 < 0) {
			i3 = 0;
		}

		for(int i4 = 0; i4 < 9; ++i4) {
			for(int i5 = 0; i5 < 8; ++i5) {
				int i6 = i5 + (i4 + i3) * 8;
				if(i6 >= 0 && i6 < this.field_35375_a.size()) {
					GuiContainerCreative.func_35310_g().setInventorySlotContents(i5 + i4 * 8, (ItemStack)this.field_35375_a.get(i6));
				} else {
					GuiContainerCreative.func_35310_g().setInventorySlotContents(i5 + i4 * 8, (ItemStack)null);
				}
			}
		}

	}

	protected void func_35373_b(int i1, int i2, boolean z3, EntityPlayer entityPlayer4) {
	}
}
