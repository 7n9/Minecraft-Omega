package net.minecraft.src;

import java.util.Random;

public class BlockTallGrass extends BlockFlower {
	protected BlockTallGrass(int i1, int i2) {
		super(i1, i2);
		float f3 = 0.4F;
		this.setBlockBounds(0.5F - f3, 0.0F, 0.5F - f3, 0.5F + f3, 0.8F, 0.5F + f3);
	}

	public int getBlockTextureFromSideAndMetadata(int i1, int i2) {
		return i2 == 1 ? this.blockIndexInTexture : (i2 == 2 ? this.blockIndexInTexture + 16 + 1 : (i2 == 0 ? this.blockIndexInTexture + 16 : this.blockIndexInTexture));
	}

	public int func_35274_i() {
		double d1 = 0.5D;
		double d3 = 1.0D;
		return ColorizerGrass.getGrassColor(d1, d3);
	}

	public int getRenderColor(int i1) {
		return i1 == 0 ? 0xFFFFFF : ColorizerFoliage.func_31073_c();
	}

	public int colorMultiplier(IBlockAccess iBlockAccess1, int i2, int i3, int i4) {
		int i5 = iBlockAccess1.getBlockMetadata(i2, i3, i4);
		if(i5 == 0) {
			return 0xFFFFFF;
		} else {
			long j6 = (long)(i2 * 3129871 + i4 * 6129781 + i3);
			j6 = j6 * j6 * 42317861L + j6 * 11L;
			i2 = (int)((long)i2 + ((j6 >> 14 & 31L) - 16L));
			i3 = (int)((long)i3 + ((j6 >> 19 & 31L) - 16L));
			i4 = (int)((long)i4 + ((j6 >> 24 & 31L) - 16L));
			double d8 = (double)iBlockAccess1.getWorldChunkManager().func_35554_b(i2, i4);
			double d10 = (double)iBlockAccess1.getWorldChunkManager().func_35558_c(i2, i4);
			return ColorizerGrass.getGrassColor(d8, d10);
		}
	}

	public int idDropped(int i1, Random random2) {
		return random2.nextInt(8) == 0 ? Item.seeds.shiftedIndex : -1;
	}

	public void harvestBlock(World world1, EntityPlayer entityPlayer2, int i3, int i4, int i5, int i6) {
		if(!world1.multiplayerWorld && entityPlayer2.getCurrentEquippedItem() != null && entityPlayer2.getCurrentEquippedItem().itemID == Item.shears.shiftedIndex) {
			entityPlayer2.addStat(StatList.mineBlockStatArray[this.blockID], 1);
			this.dropBlockAsItem_do(world1, i3, i4, i5, new ItemStack(Block.tallGrass, 1, i6));
		} else {
			super.harvestBlock(world1, entityPlayer2, i3, i4, i5, i6);
		}

	}
}
