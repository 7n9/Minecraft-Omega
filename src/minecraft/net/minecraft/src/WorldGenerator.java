package net.minecraft.src;

import java.util.Random;

public abstract class WorldGenerator {
	private final boolean doBlockNotify;

	public WorldGenerator() {
		this.doBlockNotify = false;
	}

	public WorldGenerator(boolean z1) {
		this.doBlockNotify = z1;
	}

	public abstract boolean generate(World world1, Random random2, int i3, int i4, int i5);

	public void func_517_a(double d1, double d3, double d5) {
	}

	protected void setBlock(World world1, int i2, int i3, int i4, int i5) {
		this.setBlockAndMetadata(world1, i2, i3, i4, i5, 0);
	}

	protected void setBlockAndMetadata(World world1, int i2, int i3, int i4, int i5, int i6) {
		if(this.doBlockNotify) {
			world1.setBlockAndMetadataWithNotify(i2, i3, i4, i5, i6);
		} else {
			world1.setBlockAndMetadata(i2, i3, i4, i5, i6);
		}

	}
}
