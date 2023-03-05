package net.minecraft.src;

import java.util.Random;

public class BlockSilverfish extends Block {
	public BlockSilverfish(int i1) {
		super(i1, 1, Material.clay);
		this.setHardness(0.0F);
	}

	public void harvestBlock(World world1, EntityPlayer entityPlayer2, int i3, int i4, int i5, int i6) {
		super.harvestBlock(world1, entityPlayer2, i3, i4, i5, i6);
	}

	public int getBlockTextureFromSideAndMetadata(int i1, int i2) {
		return i2 == 1 ? Block.cobblestone.blockIndexInTexture : (i2 == 2 ? Block.field_35052_bn.blockIndexInTexture : Block.stone.blockIndexInTexture);
	}

	public void onBlockDestroyedByPlayer(World world1, int i2, int i3, int i4, int i5) {
		if(!world1.singleplayerWorld) {
			EntitySilverfish entitySilverfish6 = new EntitySilverfish(world1);
			entitySilverfish6.setLocationAndAngles((double)i2 + 0.5D, (double)i3, (double)i4 + 0.5D, 0.0F, 0.0F);
			world1.entityJoinedWorld(entitySilverfish6);
			entitySilverfish6.spawnExplosionParticle();
		}

		super.onBlockDestroyedByPlayer(world1, i2, i3, i4, i5);
	}

	public int quantityDropped(Random random1) {
		return 0;
	}

	public static boolean func_35060_c(int i0) {
		return i0 == Block.stone.blockID || i0 == Block.cobblestone.blockID || i0 == Block.field_35052_bn.blockID;
	}

	public static int func_35061_d(int i0) {
		return i0 == Block.cobblestone.blockID ? 1 : (i0 == Block.field_35052_bn.blockID ? 2 : 0);
	}
}
