package net.minecraft.src;

public class ItemReed extends Item {
	private int spawnID;

	public ItemReed(int i1, Block block2) {
		super(i1);
		this.spawnID = block2.blockID;
	}

	public boolean onItemUse(ItemStack itemStack1, EntityPlayer entityPlayer2, World world3, int i4, int i5, int i6, int i7) {
		int i8 = world3.getBlockId(i4, i5, i6);
		if(i8 == Block.snow.blockID) {
			i7 = 0;
		} else if(i8 != Block.field_35278_bv.blockID) {
			if(i7 == 0) {
				--i5;
			}

			if(i7 == 1) {
				++i5;
			}

			if(i7 == 2) {
				--i6;
			}

			if(i7 == 3) {
				++i6;
			}

			if(i7 == 4) {
				--i4;
			}

			if(i7 == 5) {
				++i4;
			}
		}

		if(!entityPlayer2.func_35190_e(i4, i5, i6)) {
			return false;
		} else if(itemStack1.stackSize == 0) {
			return false;
		} else {
			if(world3.canBlockBePlacedAt(this.spawnID, i4, i5, i6, false, i7)) {
				Block block9 = Block.blocksList[this.spawnID];
				if(world3.setBlockWithNotify(i4, i5, i6, this.spawnID)) {
					if(world3.getBlockId(i4, i5, i6) == this.spawnID) {
						Block.blocksList[this.spawnID].onBlockPlaced(world3, i4, i5, i6, i7);
						Block.blocksList[this.spawnID].onBlockPlacedBy(world3, i4, i5, i6, entityPlayer2);
					}

					world3.playSoundEffect((double)((float)i4 + 0.5F), (double)((float)i5 + 0.5F), (double)((float)i6 + 0.5F), block9.stepSound.stepSoundDir2(), (block9.stepSound.getVolume() + 1.0F) / 2.0F, block9.stepSound.getPitch() * 0.8F);
					--itemStack1.stackSize;
				}
			}

			return true;
		}
	}
}
