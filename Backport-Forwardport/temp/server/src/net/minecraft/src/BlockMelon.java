package net.minecraft.src;

import java.util.Random;

public class BlockMelon extends Block {
	protected BlockMelon(int i1) {
		super(i1, Material.pumpkin);
		this.blockIndexInTexture = 136;
	}

	public int getBlockTextureFromSideAndMetadata(int i1, int i2) {
		return i1 != 1 && i1 != 0 ? 136 : 137;
	}

	public int getBlockTextureFromSide(int i1) {
		return i1 != 1 && i1 != 0 ? 136 : 137;
	}

	public int idDropped(int i1, Random random2) {
		return Item.field_35416_bd.shiftedIndex;
	}

	public int quantityDropped(Random random1) {
		return 3 + random1.nextInt(5);
	}
}
