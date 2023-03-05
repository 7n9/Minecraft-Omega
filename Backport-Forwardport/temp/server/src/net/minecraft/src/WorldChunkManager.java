package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorldChunkManager {
	private GenLayer field_34907_a;
	private GenLayer field_34906_b;
	private GenLayer field_34905_c;
	private GenLayer field_35144_d;
	private BiomeCache field_35145_e;
	private List field_35143_f;

	protected WorldChunkManager() {
		this.field_35145_e = new BiomeCache(this);
		this.field_35143_f = new ArrayList();
		this.field_35143_f.add(BiomeGenBase.forest);
		this.field_35143_f.add(BiomeGenBase.swampland);
		this.field_35143_f.add(BiomeGenBase.taiga);
	}

	public WorldChunkManager(World world1) {
		this();
		GenLayer[] genLayer2 = GenLayer.func_35019_a(world1.getRandomSeed());
		this.field_34907_a = genLayer2[0];
		this.field_34906_b = genLayer2[1];
		this.field_34905_c = genLayer2[2];
		this.field_35144_d = genLayer2[3];
	}

	public List func_35137_a() {
		return this.field_35143_f;
	}

	public BiomeGenBase getBiomeGenAtChunkCoord(ChunkCoordIntPair chunkCoordIntPair1) {
		return this.getBiomeGenAt(chunkCoordIntPair1.chunkXPos << 4, chunkCoordIntPair1.chunkZPos << 4);
	}

	public BiomeGenBase getBiomeGenAt(int i1, int i2) {
		return this.field_35145_e.func_35683_a(i1, i2);
	}

	public float[] func_4065_a(float[] f1, int i2, int i3, int i4, int i5) {
		IntCache.func_35550_a();
		if(f1 == null || f1.length < i4 * i5) {
			f1 = new float[i4 * i5];
		}

		int[] i6 = this.field_35144_d.func_35018_a(i2, i3, i4, i5);

		for(int i7 = 0; i7 < i4 * i5; ++i7) {
			float f8 = (float)i6[i7] / 65536.0F;
			if(f8 > 1.0F) {
				f8 = 1.0F;
			}

			f1[i7] = f8;
		}

		return f1;
	}

	public float[] getTemperatures(float[] f1, int i2, int i3, int i4, int i5) {
		IntCache.func_35550_a();
		if(f1 == null || f1.length < i4 * i5) {
			f1 = new float[i4 * i5];
		}

		int[] i6 = this.field_34905_c.func_35018_a(i2, i3, i4, i5);

		for(int i7 = 0; i7 < i4 * i5; ++i7) {
			float f8 = (float)i6[i7] / 65536.0F;
			if(f8 > 1.0F) {
				f8 = 1.0F;
			}

			f1[i7] = f8;
		}

		return f1;
	}

	public BiomeGenBase[] func_35142_b(BiomeGenBase[] biomeGenBase1, int i2, int i3, int i4, int i5) {
		IntCache.func_35550_a();
		if(biomeGenBase1 == null || biomeGenBase1.length < i4 * i5) {
			biomeGenBase1 = new BiomeGenBase[i4 * i5];
		}

		int[] i6 = this.field_34907_a.func_35018_a(i2, i3, i4, i5);

		for(int i7 = 0; i7 < i4 * i5; ++i7) {
			biomeGenBase1[i7] = BiomeGenBase.field_35521_a[i6[i7]];
		}

		return biomeGenBase1;
	}

	public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] biomeGenBase1, int i2, int i3, int i4, int i5) {
		return this.func_35140_a(biomeGenBase1, i2, i3, i4, i5, true);
	}

	public BiomeGenBase[] func_35140_a(BiomeGenBase[] biomeGenBase1, int i2, int i3, int i4, int i5, boolean z6) {
		IntCache.func_35550_a();
		if(biomeGenBase1 == null || biomeGenBase1.length < i4 * i5) {
			biomeGenBase1 = new BiomeGenBase[i4 * i5];
		}

		if(z6 && i4 == 16 && i5 == 16 && (i2 & 15) == 0 && (i3 & 15) == 0) {
			BiomeGenBase[] biomeGenBase9 = this.field_35145_e.func_35682_b(i2, i3);
			System.arraycopy(biomeGenBase9, 0, biomeGenBase1, 0, i4 * i5);
			return biomeGenBase1;
		} else {
			int[] i7 = this.field_34906_b.func_35018_a(i2, i3, i4, i5);

			for(int i8 = 0; i8 < i4 * i5; ++i8) {
				biomeGenBase1[i8] = BiomeGenBase.field_35521_a[i7[i8]];
			}

			return biomeGenBase1;
		}
	}

	public boolean func_35141_a(int i1, int i2, int i3, List list4) {
		int i5 = i1 - i3 >> 2;
		int i6 = i2 - i3 >> 2;
		int i7 = i1 + i3 >> 2;
		int i8 = i2 + i3 >> 2;
		int i9 = i7 - i5 + 1;
		int i10 = i8 - i6 + 1;
		int[] i11 = this.field_34907_a.func_35018_a(i5, i6, i9, i10);

		for(int i12 = 0; i12 < i9 * i10; ++i12) {
			BiomeGenBase biomeGenBase13 = BiomeGenBase.field_35521_a[i11[i12]];
			if(!list4.contains(biomeGenBase13)) {
				return false;
			}
		}

		return true;
	}

	public ChunkPosition func_35139_a(int i1, int i2, int i3, List list4, Random random5) {
		int i6 = i1 - i3 >> 2;
		int i7 = i2 - i3 >> 2;
		int i8 = i1 + i3 >> 2;
		int i9 = i2 + i3 >> 2;
		int i10 = i8 - i6 + 1;
		int i11 = i9 - i7 + 1;
		int[] i12 = this.field_34907_a.func_35018_a(i6, i7, i10, i11);
		ChunkPosition chunkPosition13 = null;
		int i14 = 0;

		for(int i15 = 0; i15 < i12.length; ++i15) {
			int i16 = i6 + i15 % i10 << 2;
			int i17 = i7 + i15 / i10 << 2;
			BiomeGenBase biomeGenBase18 = BiomeGenBase.field_35521_a[i12[i15]];
			if(list4.contains(biomeGenBase18) && (chunkPosition13 == null || random5.nextInt(i14 + 1) == 0)) {
				chunkPosition13 = new ChunkPosition(i16, 0, i17);
				++i14;
			}
		}

		return chunkPosition13;
	}

	public void func_35138_b() {
		this.field_35145_e.func_35681_a();
	}
}
