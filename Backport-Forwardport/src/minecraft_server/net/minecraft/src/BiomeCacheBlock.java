package net.minecraft.src;

class BiomeCacheBlock {
	public float[] field_35707_a;
	public float[] field_35705_b;
	public BiomeGenBase[] field_35706_c;
	public int field_35703_d;
	public int field_35704_e;
	public long field_35701_f;
	final BiomeCache field_35702_g;

	public BiomeCacheBlock(BiomeCache biomeCache1, int i2, int i3) {
		this.field_35702_g = biomeCache1;
		this.field_35707_a = new float[256];
		this.field_35705_b = new float[256];
		this.field_35706_c = new BiomeGenBase[256];
		this.field_35703_d = i2;
		this.field_35704_e = i3;
		BiomeCache.func_35679_a(biomeCache1).getTemperatures(this.field_35707_a, i2 << 4, i3 << 4, 16, 16);
		BiomeCache.func_35679_a(biomeCache1).func_4065_a(this.field_35705_b, i2 << 4, i3 << 4, 16, 16);
		BiomeCache.func_35679_a(biomeCache1).func_35140_a(this.field_35706_c, i2 << 4, i3 << 4, 16, 16, false);
	}

	public BiomeGenBase func_35700_a(int i1, int i2) {
		return this.field_35706_c[i1 & 15 | (i2 & 15) << 4];
	}
}
