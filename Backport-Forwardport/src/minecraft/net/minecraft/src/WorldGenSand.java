package net.minecraft.src;

import java.util.Random;

public class WorldGenSand extends WorldGenerator {
	private int field_35264_a;
	private int field_35263_b;

	public WorldGenSand(int i1, int i2) {
		this.field_35264_a = i2;
		this.field_35263_b = i1;
	}

	public boolean generate(World world1, Random random2, int i3, int i4, int i5) {
		if(world1.getBlockMaterial(i3, i4, i5) != Material.water) {
			return false;
		} else {
			int i6 = random2.nextInt(this.field_35263_b - 2) + 2;
			byte b7 = 2;

			for(int i8 = i3 - i6; i8 <= i3 + i6; ++i8) {
				for(int i9 = i5 - i6; i9 <= i5 + i6; ++i9) {
					int i10 = i8 - i3;
					int i11 = i9 - i5;
					if(i10 * i10 + i11 * i11 <= i6 * i6) {
						for(int i12 = i4 - b7; i12 <= i4 + b7; ++i12) {
							int i13 = world1.getBlockId(i8, i12, i9);
							if(i13 == Block.dirt.blockID || i13 == Block.grass.blockID) {
								world1.setBlock(i8, i12, i9, this.field_35264_a);
							}
						}
					}
				}
			}

			return true;
		}
	}
}
