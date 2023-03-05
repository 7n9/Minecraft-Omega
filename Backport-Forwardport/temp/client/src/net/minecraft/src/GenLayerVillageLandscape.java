package net.minecraft.src;

public class GenLayerVillageLandscape extends GenLayer {
	private BiomeGenBase[] field_35509_b = new BiomeGenBase[]{BiomeGenBase.desert, BiomeGenBase.forest, BiomeGenBase.field_35483_e, BiomeGenBase.swampland, BiomeGenBase.field_35485_c, BiomeGenBase.taiga};

	public GenLayerVillageLandscape(long j1, GenLayer genLayer3) {
		super(j1);
		this.field_35504_a = genLayer3;
	}

	public int[] func_35500_a(int i1, int i2, int i3, int i4) {
		int[] i5 = this.field_35504_a.func_35500_a(i1, i2, i3, i4);
		int[] i6 = IntCache.func_35267_a(i3 * i4);

		for(int i7 = 0; i7 < i4; ++i7) {
			for(int i8 = 0; i8 < i3; ++i8) {
				this.func_35499_a((long)(i8 + i1), (long)(i7 + i2));
				i6[i8 + i7 * i3] = i5[i8 + i7 * i3] > 0 ? this.field_35509_b[this.func_35498_a(this.field_35509_b.length)].field_35494_y : 0;
			}
		}

		return i6;
	}
}
