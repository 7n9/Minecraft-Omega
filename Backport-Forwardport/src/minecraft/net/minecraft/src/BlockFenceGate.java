package net.minecraft.src;

public class BlockFenceGate extends Block {
	public BlockFenceGate(int i1, int i2) {
		super(i1, i2, Material.wood);
	}

	public boolean canPlaceBlockAt(World world1, int i2, int i3, int i4) {
		return !world1.getBlockMaterial(i2, i3 - 1, i4).isSolid() ? false : super.canPlaceBlockAt(world1, i2, i3, i4);
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world1, int i2, int i3, int i4) {
		int i5 = world1.getBlockMetadata(i2, i3, i4);
		return func_35291_d(i5) ? null : AxisAlignedBB.getBoundingBoxFromPool((double)i2, (double)i3, (double)i4, (double)(i2 + 1), (double)((float)i3 + 1.5F), (double)(i4 + 1));
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public int getRenderType() {
		return 21;
	}

	public void onBlockPlacedBy(World world1, int i2, int i3, int i4, EntityLiving entityLiving5) {
		int i6 = (MathHelper.floor_double((double)(entityLiving5.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3) % 4;
		world1.setBlockMetadataWithNotify(i2, i3, i4, i6);
	}

	public boolean blockActivated(World world1, int i2, int i3, int i4, EntityPlayer entityPlayer5) {
		int i6 = world1.getBlockMetadata(i2, i3, i4);
		if(func_35291_d(i6)) {
			world1.setBlockMetadataWithNotify(i2, i3, i4, i6 & -5);
		} else {
			int i7 = (MathHelper.floor_double((double)(entityPlayer5.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3) % 4;
			int i8 = func_35290_f(i6);
			if(i8 == (i7 + 2) % 4) {
				i6 = i7;
			}

			world1.setBlockMetadataWithNotify(i2, i3, i4, i6 | 4);
		}

		world1.playAuxSFXAtEntity(entityPlayer5, 1003, i2, i3, i4, 0);
		return true;
	}

	public static boolean func_35291_d(int i0) {
		return (i0 & 4) != 0;
	}

	public static int func_35290_f(int i0) {
		return i0 & 3;
	}
}
