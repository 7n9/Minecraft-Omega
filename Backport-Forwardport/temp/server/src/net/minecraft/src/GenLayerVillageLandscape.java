package net.minecraft.src;

public class GenLayerVillageLandscape extends GenLayer {
	private BiomeGenBase[] field_35029_b = new BiomeGenBase[]{BiomeGenBase.desert, BiomeGenBase.forest, BiomeGenBase.field_35518_e, BiomeGenBase.swampland, BiomeGenBase.field_35520_c, BiomeGenBase.taiga};

	public GenLayerVillageLandscape(long j1, GenLayer genLayer3) {
		super(j1);
		this.field_35023_a = genLayer3;
	}

	public int[] func_35018_a(int i1, int i2, int i3, int i4) {
		int[] i5 = this.field_35023_a.func_35018_a(i1, i2, i3, i4);
		int[] i6 = IntCache.func_35549_a(i3 * i4);

		for(int i7 = 0; i7 < i4; ++i7) {
			for(int i8 = 0; i8 < i3; ++i8) {
				this.func_35017_a((long)(i8 + i1), (long)(i7 + i2));
				i6[i8 + i7 * i3] = i5[i8 + i7 * i3] > 0 ? this.field_35029_b[this.func_35016_a(this.field_35029_b.length)].field_35529_y : 0;
			}
		}

		return i6;
	}
}
