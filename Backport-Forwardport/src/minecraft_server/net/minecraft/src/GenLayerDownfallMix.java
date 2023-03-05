package net.minecraft.src;

public class GenLayerDownfallMix extends GenLayer {
	private GenLayer field_35035_b;
	private int field_35036_c;

	public GenLayerDownfallMix(GenLayer genLayer1, GenLayer genLayer2, int i3) {
		super(0L);
		this.field_35023_a = genLayer2;
		this.field_35035_b = genLayer1;
		this.field_35036_c = i3;
	}

	public int[] func_35018_a(int i1, int i2, int i3, int i4) {
		int[] i5 = this.field_35023_a.func_35018_a(i1, i2, i3, i4);
		int[] i6 = this.field_35035_b.func_35018_a(i1, i2, i3, i4);
		int[] i7 = IntCache.func_35549_a(i3 * i4);

		for(int i8 = 0; i8 < i3 * i4; ++i8) {
			i7[i8] = i6[i8] + (BiomeGenBase.field_35521_a[i5[i8]].func_35510_e() - i6[i8]) / (this.field_35036_c + 1);
		}

		return i7;
	}
}
