package com.owen2k6.omega.item;

import com.owen2k6.omega.Omega;
import net.minecraft.src.*;

public class ItemGrowthWand extends Item {
	private int weaponDamage;

	public ItemGrowthWand(int i1) {
		super(i1);
		this.maxStackSize = 1;
	}

	//public float getStrVsBlock(ItemStack itemStack1, Block block2) {
		//return block2.blockID == Block.web.blockID ? 15.0F : 1.5F;
	//}

	public boolean hitEntity(ItemStack itemStack1, EntityLiving entityLiving2, EntityLiving entityLiving3) {
		itemStack1.damageItem(1, entityLiving3);
		return true;
	}

	public boolean onBlockDestroyed(ItemStack itemStack1, int i2, int i3, int i4, int i5, EntityLiving entityLiving6) {
		itemStack1.damageItem(2, entityLiving6);
		return false;
	}

	public int getDamageVsEntity(Entity entity1) {
		return 0;
	}


	public boolean onItemUse(ItemStack itemStack1, EntityPlayer entityPlayer2, World world3, int i4, int i5, int i6, int i7) {
		int i8 = world3.getBlockId(i4, i5, i6);

		if(Omega.INSTANCE.manaSystem.get() < 9){
			Omega.INSTANCE.mc.thePlayer.addChatMessage("You don't have enough mana to do that!");
			return false;
		}

		if(i8 == Block.crops.blockID) {
			if(!world3.multiplayerWorld) {
				((BlockCrops)Block.crops).fertilize(world3, i4, i5, i6);
			}
			Omega.INSTANCE.manaSystem.remove(9);
		}

		if(i8 == Block.grass.blockID) {
			if(!world3.multiplayerWorld) {

				label53:
				for(int i9 = 0; i9 < 128; ++i9) {
					int i10 = i4;
					int i11 = i5 + 1;
					int i12 = i6;

					for(int i13 = 0; i13 < i9 / 16; ++i13) {
						i10 += itemRand.nextInt(3) - 1;
						i11 += (itemRand.nextInt(3) - 1) * itemRand.nextInt(3) / 2;
						i12 += itemRand.nextInt(3) - 1;
						if(world3.getBlockId(i10, i11 - 1, i12) != Block.grass.blockID || world3.isBlockNormalCube(i10, i11, i12)) {
							continue label53;
						}
					}

					if(world3.getBlockId(i10, i11, i12) == 0) {
						if(itemRand.nextInt(10) != 0) {
							world3.setBlockAndMetadataWithNotify(i10, i11, i12, Block.tallGrass.blockID, 1);
						} else if(itemRand.nextInt(3) != 0) {
							world3.setBlockWithNotify(i10, i11, i12, Block.plantYellow.blockID);
						} else {
							world3.setBlockWithNotify(i10, i11, i12, Block.plantRed.blockID);
						}
					}
				}
				Omega.INSTANCE.manaSystem.remove(9);
			}
		}

		if(i8 == Block.sapling.blockID) {

			if(!world3.multiplayerWorld) {
				((BlockSapling)Block.sapling).growTree(world3, i4, i5, i6, world3.rand);
			}
			Omega.INSTANCE.manaSystem.remove(9);

		}




		return true;
	}

	public boolean isFull3D() {
		return true;
	}

	public boolean canHarvestBlock(Block block1) {
		return false;
	}
}
