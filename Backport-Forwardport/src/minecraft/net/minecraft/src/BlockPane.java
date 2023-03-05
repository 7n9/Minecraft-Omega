package net.minecraft.src;

import java.util.ArrayList;

public class BlockPane extends Block {
	private int field_35300_a;

	protected BlockPane(int i1, int i2, int i3, Material material4) {
		super(i1, i2, material4);
		this.field_35300_a = i3;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public int getRenderType() {
		return 18;
	}

	public boolean shouldSideBeRendered(IBlockAccess iBlockAccess1, int i2, int i3, int i4, int i5) {
		int i6 = iBlockAccess1.getBlockId(i2, i3, i4);
		return i6 == this.blockID ? false : super.shouldSideBeRendered(iBlockAccess1, i2, i3, i4, i5);
	}

	public void getCollidingBoundingBoxes(World world1, int i2, int i3, int i4, AxisAlignedBB axisAlignedBB5, ArrayList arrayList6) {
		boolean z7 = this.func_35298_d(world1.getBlockId(i2, i3, i4 - 1));
		boolean z8 = this.func_35298_d(world1.getBlockId(i2, i3, i4 + 1));
		boolean z9 = this.func_35298_d(world1.getBlockId(i2 - 1, i3, i4));
		boolean z10 = this.func_35298_d(world1.getBlockId(i2 + 1, i3, i4));
		if(z9 && z10 || !z9 && !z10 && !z7 && !z8) {
			this.setBlockBounds(0.0F, 0.0F, 0.4375F, 1.0F, 1.0F, 0.5625F);
			super.getCollidingBoundingBoxes(world1, i2, i3, i4, axisAlignedBB5, arrayList6);
		} else if(z9 && !z10) {
			this.setBlockBounds(0.0F, 0.0F, 0.4375F, 0.5F, 1.0F, 0.5625F);
			super.getCollidingBoundingBoxes(world1, i2, i3, i4, axisAlignedBB5, arrayList6);
		} else if(!z9 && z10) {
			this.setBlockBounds(0.5F, 0.0F, 0.4375F, 1.0F, 1.0F, 0.5625F);
			super.getCollidingBoundingBoxes(world1, i2, i3, i4, axisAlignedBB5, arrayList6);
		}

		if((!z7 || !z8) && (z9 || z10 || z7 || z8)) {
			if(z7 && !z8) {
				this.setBlockBounds(0.4375F, 0.0F, 0.0F, 0.5625F, 1.0F, 0.5F);
				super.getCollidingBoundingBoxes(world1, i2, i3, i4, axisAlignedBB5, arrayList6);
			} else if(!z7 && z8) {
				this.setBlockBounds(0.4375F, 0.0F, 0.5F, 0.5625F, 1.0F, 1.0F);
				super.getCollidingBoundingBoxes(world1, i2, i3, i4, axisAlignedBB5, arrayList6);
			}
		} else {
			this.setBlockBounds(0.4375F, 0.0F, 0.0F, 0.5625F, 1.0F, 1.0F);
			super.getCollidingBoundingBoxes(world1, i2, i3, i4, axisAlignedBB5, arrayList6);
		}

	}

	public void setBlockBoundsForItemRender() {
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	public void setBlockBoundsBasedOnState(IBlockAccess iBlockAccess1, int i2, int i3, int i4) {
		float f5 = 0.4375F;
		float f6 = 0.5625F;
		float f7 = 0.4375F;
		float f8 = 0.5625F;
		boolean z9 = this.func_35298_d(iBlockAccess1.getBlockId(i2, i3, i4 - 1));
		boolean z10 = this.func_35298_d(iBlockAccess1.getBlockId(i2, i3, i4 + 1));
		boolean z11 = this.func_35298_d(iBlockAccess1.getBlockId(i2 - 1, i3, i4));
		boolean z12 = this.func_35298_d(iBlockAccess1.getBlockId(i2 + 1, i3, i4));
		if(z11 && z12 || !z11 && !z12 && !z9 && !z10) {
			f5 = 0.0F;
			f6 = 1.0F;
		} else if(z11 && !z12) {
			f5 = 0.0F;
		} else if(!z11 && z12) {
			f6 = 1.0F;
		}

		if((!z9 || !z10) && (z11 || z12 || z9 || z10)) {
			if(z9 && !z10) {
				f7 = 0.0F;
			} else if(!z9 && z10) {
				f8 = 1.0F;
			}
		} else {
			f7 = 0.0F;
			f8 = 1.0F;
		}

		this.setBlockBounds(f5, 0.0F, f7, f6, 1.0F, f8);
	}

	public int func_35299_s() {
		return this.field_35300_a;
	}

	public final boolean func_35298_d(int i1) {
		return Block.opaqueCubeLookup[i1] || i1 == this.blockID || i1 == Block.glass.blockID;
	}
}
