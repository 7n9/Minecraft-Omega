package net.minecraft.src;

public class GenLayerDownfall extends GenLayer {
	public GenLayerDownfall(GenLayer genLayer1) {
		super(0L);
		this.field_35504_a = genLayer1;
	}

	public int[] func_35500_a(int i1, int i2, int i3, int i4) {
		int[] i5 = this.field_35504_a.func_35500_a(i1, i2, i3, i4);
		int[] i6 = IntCache.func_35267_a(i3 * i4);

		for(int i7 = 0; i7 < i3 * i4; ++i7) {
			i6[i7] = BiomeGenBase.field_35486_a[i5[i7]].func_35476_e();
		}

		return i6;
	}
}
