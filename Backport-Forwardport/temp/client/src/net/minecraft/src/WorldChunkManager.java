package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorldChunkManager {
	private GenLayer field_34903_b;
	private GenLayer field_34902_c;
	private GenLayer field_34901_d;
	private GenLayer field_35565_e;
	private BiomeCache field_35563_f;
	private List field_35564_g;
	public BiomeGenBase[] field_4195_d;

	protected WorldChunkManager() {
		this.field_35563_f = new BiomeCache(this);
		this.field_35564_g = new ArrayList();
		this.field_35564_g.add(BiomeGenBase.forest);
		this.field_35564_g.add(BiomeGenBase.swampland);
		this.field_35564_g.add(BiomeGenBase.taiga);
	}

	public WorldChunkManager(World world1) {
		this();
		GenLayer[] genLayer2 = GenLayer.func_35497_a(world1.getRandomSeed());
		this.field_34903_b = genLayer2[0];
		this.field_34902_c = genLayer2[1];
		this.field_34901_d = genLayer2[2];
		this.field_35565_e = genLayer2[3];
	}

	public List func_35559_a() {
		return this.field_35564_g;
	}

	public BiomeGenBase getBiomeGenAtChunkCoord(ChunkCoordIntPair chunkCoordIntPair1) {
		return this.getBiomeGenAt(chunkCoordIntPair1.chunkXPos << 4, chunkCoordIntPair1.chunkZPos << 4);
	}

	public BiomeGenBase getBiomeGenAt(int i1, int i2) {
		return this.field_35563_f.func_35725_a(i1, i2);
	}

	public float func_35558_c(int i1, int i2) {
		return this.field_35563_f.func_35727_c(i1, i2);
	}

	public float[] func_35560_b(float[] f1, int i2, int i3, int i4, int i5) {
		IntCache.func_35268_a();
		if(f1 == null || f1.length < i4 * i5) {
			f1 = new float[i4 * i5];
		}

		int[] i6 = this.field_35565_e.func_35500_a(i2, i3, i4, i5);

		for(int i7 = 0; i7 < i4 * i5; ++i7) {
			float f8 = (float)i6[i7] / 65536.0F;
			if(f8 > 1.0F) {
				f8 = 1.0F;
			}

			f1[i7] = f8;
		}

		return f1;
	}

	public float func_35554_b(int i1, int i2) {
		return this.field_35563_f.func_35722_b(i1, i2);
	}

	public float[] getTemperatures(float[] f1, int i2, int i3, int i4, int i5) {
		IntCache.func_35268_a();
		if(f1 == null || f1.length < i4 * i5) {
			f1 = new float[i4 * i5];
		}

		int[] i6 = this.field_34901_d.func_35500_a(i2, i3, i4, i5);

		for(int i7 = 0; i7 < i4 * i5; ++i7) {
			float f8 = (float)i6[i7] / 65536.0F;
			if(f8 > 1.0F) {
				f8 = 1.0F;
			}

			f1[i7] = f8;
		}

		return f1;
	}

	public BiomeGenBase[] func_35557_b(BiomeGenBase[] biomeGenBase1, int i2, int i3, int i4, int i5) {
		IntCache.func_35268_a();
		if(biomeGenBase1 == null || biomeGenBase1.length < i4 * i5) {
			biomeGenBase1 = new BiomeGenBase[i4 * i5];
		}

		int[] i6 = this.field_34903_b.func_35500_a(i2, i3, i4, i5);

		for(int i7 = 0; i7 < i4 * i5; ++i7) {
			biomeGenBase1[i7] = BiomeGenBase.field_35486_a[i6[i7]];
		}

		return biomeGenBase1;
	}

	public BiomeGenBase[] func_4069_a(int i1, int i2, int i3, int i4) {
		if(i3 == 16 && i4 == 16 && (i1 & 15) == 0 && (i2 & 15) == 0) {
			return this.field_35563_f.func_35723_d(i1, i2);
		} else {
			this.field_4195_d = this.loadBlockGeneratorData(this.field_4195_d, i1, i2, i3, i4);
			return this.field_4195_d;
		}
	}

	public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] biomeGenBase1, int i2, int i3, int i4, int i5) {
		return this.func_35555_a(biomeGenBase1, i2, i3, i4, i5, true);
	}

	public BiomeGenBase[] func_35555_a(BiomeGenBase[] biomeGenBase1, int i2, int i3, int i4, int i5, boolean z6) {
		IntCache.func_35268_a();
		if(biomeGenBase1 == null || biomeGenBase1.length < i4 * i5) {
			biomeGenBase1 = new BiomeGenBase[i4 * i5];
		}

		if(z6 && i4 == 16 && i5 == 16 && (i2 & 15) == 0 && (i3 & 15) == 0) {
			BiomeGenBase[] biomeGenBase9 = this.field_35563_f.func_35723_d(i2, i3);
			System.arraycopy(biomeGenBase9, 0, biomeGenBase1, 0, i4 * i5);
			return biomeGenBase1;
		} else {
			int[] i7 = this.field_34902_c.func_35500_a(i2, i3, i4, i5);

			for(int i8 = 0; i8 < i4 * i5; ++i8) {
				biomeGenBase1[i8] = BiomeGenBase.field_35486_a[i7[i8]];
			}

			return biomeGenBase1;
		}
	}

	public boolean func_35562_a(int i1, int i2, int i3, List list4) {
		int i5 = i1 - i3 >> 2;
		int i6 = i2 - i3 >> 2;
		int i7 = i1 + i3 >> 2;
		int i8 = i2 + i3 >> 2;
		int i9 = i7 - i5 + 1;
		int i10 = i8 - i6 + 1;
		int[] i11 = this.field_34903_b.func_35500_a(i5, i6, i9, i10);

		for(int i12 = 0; i12 < i9 * i10; ++i12) {
			BiomeGenBase biomeGenBase13 = BiomeGenBase.field_35486_a[i11[i12]];
			if(!list4.contains(biomeGenBase13)) {
				return false;
			}
		}

		return true;
	}

	public ChunkPosition func_35556_a(int i1, int i2, int i3, List list4, Random random5) {
		int i6 = i1 - i3 >> 2;
		int i7 = i2 - i3 >> 2;
		int i8 = i1 + i3 >> 2;
		int i9 = i2 + i3 >> 2;
		int i10 = i8 - i6 + 1;
		int i11 = i9 - i7 + 1;
		int[] i12 = this.field_34903_b.func_35500_a(i6, i7, i10, i11);
		ChunkPosition chunkPosition13 = null;
		int i14 = 0;

		for(int i15 = 0; i15 < i12.length; ++i15) {
			int i16 = i6 + i15 % i10 << 2;
			int i17 = i7 + i15 / i10 << 2;
			BiomeGenBase biomeGenBase18 = BiomeGenBase.field_35486_a[i12[i15]];
			if(list4.contains(biomeGenBase18) && (chunkPosition13 == null || random5.nextInt(i14 + 1) == 0)) {
				chunkPosition13 = new ChunkPosition(i16, 0, i17);
				++i14;
			}
		}

		return chunkPosition13;
	}

	public void func_35561_b() {
		this.field_35563_f.func_35724_a();
	}
}
