package net.minecraft.src;

public class BlockFence extends Block {
	public BlockFence(int i1, int i2) {
		super(i1, i2, Material.wood);
	}

	public boolean canPlaceBlockAt(World world1, int i2, int i3, int i4) {
		return world1.getBlockId(i2, i3 - 1, i4) == this.blockID || (world1.getBlockMaterial(i2, i3 - 1, i4).isSolid() && super.canPlaceBlockAt(world1, i2, i3, i4));
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world1, int i2, int i3, int i4) {
		return AxisAlignedBB.getBoundingBoxFromPool(i2, i3, i4, i2 + 1, (float)i3 + 1.5F, i4 + 1);
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public int getRenderType() {
		return 11;
	}
}
