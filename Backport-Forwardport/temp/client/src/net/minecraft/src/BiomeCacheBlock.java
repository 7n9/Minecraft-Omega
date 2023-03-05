package net.minecraft.src;

class BiomeCacheBlock {
	public float[] field_35659_a;
	public float[] field_35657_b;
	public BiomeGenBase[] field_35658_c;
	public int field_35655_d;
	public int field_35656_e;
	public long field_35653_f;
	final BiomeCache field_35654_g;

	public BiomeCacheBlock(BiomeCache biomeCache1, int i2, int i3) {
		this.field_35654_g = biomeCache1;
		this.field_35659_a = new float[256];
		this.field_35657_b = new float[256];
		this.field_35658_c = new BiomeGenBase[256];
		this.field_35655_d = i2;
		this.field_35656_e = i3;
		BiomeCache.func_35721_a(biomeCache1).getTemperatures(this.field_35659_a, i2 << 4, i3 << 4, 16, 16);
		BiomeCache.func_35721_a(biomeCache1).func_35560_b(this.field_35657_b, i2 << 4, i3 << 4, 16, 16);
		BiomeCache.func_35721_a(biomeCache1).func_35555_a(this.field_35658_c, i2 << 4, i3 << 4, 16, 16, false);
	}

	public BiomeGenBase func_35651_a(int i1, int i2) {
		return this.field_35658_c[i1 & 15 | (i2 & 15) << 4];
	}

	public float func_35650_b(int i1, int i2) {
		return this.field_35659_a[i1 & 15 | (i2 & 15) << 4];
	}

	public float func_35652_c(int i1, int i2) {
		return this.field_35657_b[i1 & 15 | (i2 & 15) << 4];
	}
}
