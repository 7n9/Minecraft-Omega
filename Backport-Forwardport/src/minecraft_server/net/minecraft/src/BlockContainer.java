package net.minecraft.src;

public abstract class BlockContainer extends Block {
	protected BlockContainer(int i1, Material material2) {
		super(i1, material2);
		isBlockContainer[this.blockID] = true;
	}

	protected BlockContainer(int i1, int i2, Material material3) {
		super(i1, i2, material3);
		isBlockContainer[this.blockID] = true;
	}

	public void onBlockAdded(World world1, int i2, int i3, int i4) {
		super.onBlockAdded(world1, i2, i3, i4);
		world1.setBlockTileEntity(i2, i3, i4, this.getBlockEntity());
	}

	public void onBlockRemoval(World world1, int i2, int i3, int i4) {
		super.onBlockRemoval(world1, i2, i3, i4);
		world1.removeBlockTileEntity(i2, i3, i4);
	}

	public abstract TileEntity getBlockEntity();

	public void playBlock(World world1, int i2, int i3, int i4, int i5, int i6) {
		super.playBlock(world1, i2, i3, i4, i5, i6);
		TileEntity tileEntity7 = world1.getBlockTileEntity(i2, i3, i4);
		if(tileEntity7 != null) {
			tileEntity7.func_35163_b(i5, i6);
		}

	}
}
