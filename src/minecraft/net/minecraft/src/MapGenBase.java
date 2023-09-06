package net.minecraft.src;

import java.util.Random;

public class MapGenBase {
	protected int field_1306_a = 8;
	protected Random rand = new Random();
	protected World worldObj;

	public void func_867_a(IChunkProvider iChunkProvider1, World world2, int i3, int i4, byte[] b5) {
		int i6 = this.field_1306_a;
		this.worldObj = world2;
		this.rand.setSeed(world2.getRandomSeed());
		long j7 = this.rand.nextLong();
		long j9 = this.rand.nextLong();

		for(int i11 = i3 - i6; i11 <= i3 + i6; ++i11) {
			for(int i12 = i4 - i6; i12 <= i4 + i6; ++i12) {
				long j13 = (long)i11 * j7;
				long j15 = (long)i12 * j9;
				this.rand.setSeed(j13 ^ j15 ^ world2.getRandomSeed());
				this.func_868_a(world2, i11, i12, i3, i4, b5);
			}
		}

	}

	protected void func_868_a(World world1, int i2, int i3, int i4, int i5, byte[] b6) {
	}
}
