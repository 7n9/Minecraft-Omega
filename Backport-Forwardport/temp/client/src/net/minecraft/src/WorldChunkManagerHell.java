package net.minecraft.src;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class WorldChunkManagerHell extends WorldChunkManager {
	private BiomeGenBase field_4201_e;
	private float field_4200_f;
	private float field_4199_g;

	public WorldChunkManagerHell(BiomeGenBase biomeGenBase1, float f2, float f3) {
		this.field_4201_e = biomeGenBase1;
		this.field_4200_f = f2;
		this.field_4199_g = f3;
	}

	public BiomeGenBase getBiomeGenAtChunkCoord(ChunkCoordIntPair chunkCoordIntPair1) {
		return this.field_4201_e;
	}

	public BiomeGenBase getBiomeGenAt(int i1, int i2) {
		return this.field_4201_e;
	}

	public float func_35554_b(int i1, int i2) {
		return this.field_4200_f;
	}

	public BiomeGenBase[] func_4069_a(int i1, int i2, int i3, int i4) {
		this.field_4195_d = this.loadBlockGeneratorData(this.field_4195_d, i1, i2, i3, i4);
		return this.field_4195_d;
	}

	public float[] getTemperatures(float[] f1, int i2, int i3, int i4, int i5) {
		if(f1 == null || f1.length < i4 * i5) {
			f1 = new float[i4 * i5];
		}

		Arrays.fill(f1, 0, i4 * i5, this.field_4200_f);
		return f1;
	}

	public float[] func_35560_b(float[] f1, int i2, int i3, int i4, int i5) {
		if(f1 == null || f1.length < i4 * i5) {
			f1 = new float[i4 * i5];
		}

		Arrays.fill(f1, 0, i4 * i5, this.field_4199_g);
		return f1;
	}

	public float func_35558_c(int i1, int i2) {
		return this.field_4199_g;
	}

	public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] biomeGenBase1, int i2, int i3, int i4, int i5) {
		if(biomeGenBase1 == null || biomeGenBase1.length < i4 * i5) {
			biomeGenBase1 = new BiomeGenBase[i4 * i5];
		}

		Arrays.fill(biomeGenBase1, 0, i4 * i5, this.field_4201_e);
		return biomeGenBase1;
	}

	public ChunkPosition func_35556_a(int i1, int i2, int i3, List list4, Random random5) {
		return list4.contains(this.field_4201_e) ? new ChunkPosition(i1 - i3 + random5.nextInt(i3 * 2 + 1), 0, i2 - i3 + random5.nextInt(i3 * 2 + 1)) : null;
	}

	public boolean func_35562_a(int i1, int i2, int i3, List list4) {
		return list4.contains(this.field_4201_e);
	}
}
