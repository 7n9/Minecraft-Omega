package net.minecraft.src;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class WorldChunkManagerHell extends WorldChunkManager {
	private BiomeGenBase field_4262_e;
	private float field_4261_f;
	private float field_4260_g;

	public WorldChunkManagerHell(BiomeGenBase biomeGenBase1, float f2, float f3) {
		this.field_4262_e = biomeGenBase1;
		this.field_4261_f = f2;
		this.field_4260_g = f3;
	}

	public BiomeGenBase getBiomeGenAtChunkCoord(ChunkCoordIntPair chunkCoordIntPair1) {
		return this.field_4262_e;
	}

	public BiomeGenBase getBiomeGenAt(int i1, int i2) {
		return this.field_4262_e;
	}

	public float[] getTemperatures(float[] f1, int i2, int i3, int i4, int i5) {
		if(f1 == null || f1.length < i4 * i5) {
			f1 = new float[i4 * i5];
		}

		Arrays.fill(f1, 0, i4 * i5, this.field_4261_f);
		return f1;
	}

	public float[] func_4065_a(float[] f1, int i2, int i3, int i4, int i5) {
		if(f1 == null || f1.length < i4 * i5) {
			f1 = new float[i4 * i5];
		}

		Arrays.fill(f1, 0, i4 * i5, this.field_4260_g);
		return f1;
	}

	public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] biomeGenBase1, int i2, int i3, int i4, int i5) {
		if(biomeGenBase1 == null || biomeGenBase1.length < i4 * i5) {
			biomeGenBase1 = new BiomeGenBase[i4 * i5];
		}

		Arrays.fill(biomeGenBase1, 0, i4 * i5, this.field_4262_e);
		return biomeGenBase1;
	}

	public ChunkPosition func_35139_a(int i1, int i2, int i3, List list4, Random random5) {
		return list4.contains(this.field_4262_e) ? new ChunkPosition(i1 - i3 + random5.nextInt(i3 * 2 + 1), 0, i2 - i3 + random5.nextInt(i3 * 2 + 1)) : null;
	}

	public boolean func_35141_a(int i1, int i2, int i3, List list4) {
		return list4.contains(this.field_4262_e);
	}
}
