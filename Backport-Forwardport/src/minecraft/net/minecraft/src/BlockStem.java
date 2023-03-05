package net.minecraft.src;

import java.util.Random;

public class BlockStem extends BlockFlower {
	private Block field_35297_a;

	protected BlockStem(int i1, Block block2) {
		super(i1, 111);
		this.field_35297_a = block2;
		this.setTickOnLoad(true);
		float f3 = 0.125F;
		this.setBlockBounds(0.5F - f3, 0.0F, 0.5F - f3, 0.5F + f3, 0.25F, 0.5F + f3);
	}

	protected boolean canThisPlantGrowOnThisBlockID(int i1) {
		return i1 == Block.tilledField.blockID;
	}

	public void updateTick(World world1, int i2, int i3, int i4, Random random5) {
		super.updateTick(world1, i2, i3, i4, random5);
		if(world1.getBlockLightValue(i2, i3 + 1, i4) >= 9) {
			float f6 = this.func_35295_j(world1, i2, i3, i4);
			if(random5.nextInt((int)(100.0F / f6)) == 0) {
				int i7 = world1.getBlockMetadata(i2, i3, i4);
				if(i7 < 7) {
					++i7;
					world1.setBlockMetadataWithNotify(i2, i3, i4, i7);
				} else {
					if(world1.getBlockId(i2 - 1, i3, i4) == this.field_35297_a.blockID) {
						return;
					}

					if(world1.getBlockId(i2 + 1, i3, i4) == this.field_35297_a.blockID) {
						return;
					}

					if(world1.getBlockId(i2, i3, i4 - 1) == this.field_35297_a.blockID) {
						return;
					}

					if(world1.getBlockId(i2, i3, i4 + 1) == this.field_35297_a.blockID) {
						return;
					}

					int i8 = random5.nextInt(4);
					int i9 = i2;
					int i10 = i4;
					if(i8 == 0) {
						i9 = i2 - 1;
					}

					if(i8 == 1) {
						++i9;
					}

					if(i8 == 2) {
						i10 = i4 - 1;
					}

					if(i8 == 3) {
						++i10;
					}

					if(world1.getBlockId(i9, i3, i10) == 0 && world1.getBlockId(i9, i3 - 1, i10) == Block.tilledField.blockID) {
						world1.setBlockWithNotify(i9, i3, i10, this.field_35297_a.blockID);
					}
				}
			}
		}

	}

	public void func_35294_i(World world1, int i2, int i3, int i4) {
		world1.setBlockMetadataWithNotify(i2, i3, i4, 7);
	}

	private float func_35295_j(World world1, int i2, int i3, int i4) {
		float f5 = 1.0F;
		int i6 = world1.getBlockId(i2, i3, i4 - 1);
		int i7 = world1.getBlockId(i2, i3, i4 + 1);
		int i8 = world1.getBlockId(i2 - 1, i3, i4);
		int i9 = world1.getBlockId(i2 + 1, i3, i4);
		int i10 = world1.getBlockId(i2 - 1, i3, i4 - 1);
		int i11 = world1.getBlockId(i2 + 1, i3, i4 - 1);
		int i12 = world1.getBlockId(i2 + 1, i3, i4 + 1);
		int i13 = world1.getBlockId(i2 - 1, i3, i4 + 1);
		boolean z14 = i8 == this.blockID || i9 == this.blockID;
		boolean z15 = i6 == this.blockID || i7 == this.blockID;
		boolean z16 = i10 == this.blockID || i11 == this.blockID || i12 == this.blockID || i13 == this.blockID;

		for(int i17 = i2 - 1; i17 <= i2 + 1; ++i17) {
			for(int i18 = i4 - 1; i18 <= i4 + 1; ++i18) {
				int i19 = world1.getBlockId(i17, i3 - 1, i18);
				float f20 = 0.0F;
				if(i19 == Block.tilledField.blockID) {
					f20 = 1.0F;
					if(world1.getBlockMetadata(i17, i3 - 1, i18) > 0) {
						f20 = 3.0F;
					}
				}

				if(i17 != i2 || i18 != i4) {
					f20 /= 4.0F;
				}

				f5 += f20;
			}
		}

		if(z16 || z14 && z15) {
			f5 /= 2.0F;
		}

		return f5;
	}

	public int getRenderColor(int i1) {
		int i2 = i1 * 32;
		int i3 = 255 - i1 * 8;
		int i4 = i1 * 4;
		return i2 << 16 | i3 << 8 | i4;
	}

	public int colorMultiplier(IBlockAccess iBlockAccess1, int i2, int i3, int i4) {
		return this.getRenderColor(iBlockAccess1.getBlockMetadata(i2, i3, i4));
	}

	public int getBlockTextureFromSideAndMetadata(int i1, int i2) {
		return this.blockIndexInTexture;
	}

	public void setBlockBoundsForItemRender() {
		float f1 = 0.125F;
		this.setBlockBounds(0.5F - f1, 0.0F, 0.5F - f1, 0.5F + f1, 0.25F, 0.5F + f1);
	}

	public void setBlockBoundsBasedOnState(IBlockAccess iBlockAccess1, int i2, int i3, int i4) {
		this.maxY = (double)((float)(iBlockAccess1.getBlockMetadata(i2, i3, i4) * 2 + 2) / 16.0F);
		float f5 = 0.125F;
		this.setBlockBounds(0.5F - f5, 0.0F, 0.5F - f5, 0.5F + f5, (float)this.maxY, 0.5F + f5);
	}

	public int getRenderType() {
		return 19;
	}

	public int func_35296_f(IBlockAccess iBlockAccess1, int i2, int i3, int i4) {
		int i5 = iBlockAccess1.getBlockMetadata(i2, i3, i4);
		return i5 < 7 ? -1 : (iBlockAccess1.getBlockId(i2 - 1, i3, i4) == this.field_35297_a.blockID ? 0 : (iBlockAccess1.getBlockId(i2 + 1, i3, i4) == this.field_35297_a.blockID ? 1 : (iBlockAccess1.getBlockId(i2, i3, i4 - 1) == this.field_35297_a.blockID ? 2 : (iBlockAccess1.getBlockId(i2, i3, i4 + 1) == this.field_35297_a.blockID ? 3 : -1))));
	}

	public void dropBlockAsItemWithChance(World world1, int i2, int i3, int i4, int i5, float f6) {
		super.dropBlockAsItemWithChance(world1, i2, i3, i4, i5, f6);
		if(!world1.multiplayerWorld) {
			Item item7 = null;
			if(this.field_35297_a == Block.pumpkin) {
				item7 = Item.field_35422_bh;
			}

			if(this.field_35297_a == Block.field_35281_bs) {
				item7 = Item.field_35423_bi;
			}

			for(int i8 = 0; i8 < 3; ++i8) {
				if(world1.rand.nextInt(15) <= i5) {
					float f9 = 0.7F;
					float f10 = world1.rand.nextFloat() * f9 + (1.0F - f9) * 0.5F;
					float f11 = world1.rand.nextFloat() * f9 + (1.0F - f9) * 0.5F;
					float f12 = world1.rand.nextFloat() * f9 + (1.0F - f9) * 0.5F;
					EntityItem entityItem13 = new EntityItem(world1, (double)((float)i2 + f10), (double)((float)i3 + f11), (double)((float)i4 + f12), new ItemStack(item7));
					entityItem13.delayBeforeCanPickup = 10;
					world1.entityJoinedWorld(entityItem13);
				}
			}

		}
	}

	public int idDropped(int i1, Random random2) {
		if(i1 == 7) {
			;
		}

		return -1;
	}

	public int quantityDropped(Random random1) {
		return 1;
	}
}
