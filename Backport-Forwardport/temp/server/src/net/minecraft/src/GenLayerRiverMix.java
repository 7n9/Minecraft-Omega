package net.minecraft.src;

public class GenLayerRiverMix extends GenLayer {
	private GenLayer field_35033_b;
	private GenLayer field_35034_c;

	public GenLayerRiverMix(long j1, GenLayer genLayer3, GenLayer genLayer4) {
		super(j1);
		this.field_35033_b = genLayer3;
		this.field_35034_c = genLayer4;
	}

	public void func_35015_b(long j1) {
		this.field_35033_b.func_35015_b(j1);
		this.field_35034_c.func_35015_b(j1);
		super.func_35015_b(j1);
	}

	public int[] func_35018_a(int i1, int i2, int i3, int i4) {
		int[] i5 = this.field_35033_b.func_35018_a(i1, i2, i3, i4);
		int[] i6 = this.field_35034_c.func_35018_a(i1, i2, i3, i4);
		int[] i7 = IntCache.func_35549_a(i3 * i4);

		for(int i8 = 0; i8 < i3 * i4; ++i8) {
			if(i5[i8] == BiomeGenBase.field_35519_b.field_35529_y) {
				i7[i8] = i5[i8];
			} else {
				i7[i8] = i6[i8] >= 0 ? i6[i8] : i5[i8];
			}
		}

		return i7;
	}
}
