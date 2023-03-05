package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;

public class BiomeCache {
	private final WorldChunkManager field_35687_a;
	private long field_35685_b = 0L;
	private PlayerHash field_35686_c = new PlayerHash();
	private List field_35684_d = new ArrayList();

	public BiomeCache(WorldChunkManager worldChunkManager1) {
		this.field_35687_a = worldChunkManager1;
	}

	private BiomeCacheBlock func_35680_c(int i1, int i2) {
		i1 >>= 4;
		i2 >>= 4;
		long j3 = (long)i1 & 4294967295L | ((long)i2 & 4294967295L) << 32;
		BiomeCacheBlock biomeCacheBlock5 = (BiomeCacheBlock)this.field_35686_c.getValueByKey(j3);
		if(biomeCacheBlock5 == null) {
			biomeCacheBlock5 = new BiomeCacheBlock(this, i1, i2);
			this.field_35686_c.add(j3, biomeCacheBlock5);
			this.field_35684_d.add(biomeCacheBlock5);
		}

		biomeCacheBlock5.field_35701_f = System.currentTimeMillis();
		return biomeCacheBlock5;
	}

	public BiomeGenBase func_35683_a(int i1, int i2) {
		return this.func_35680_c(i1, i2).func_35700_a(i1, i2);
	}

	public void func_35681_a() {
		long j1 = System.currentTimeMillis();
		long j3 = j1 - this.field_35685_b;
		if(j3 > 7500L || j3 < 0L) {
			this.field_35685_b = j1;

			for(int i5 = 0; i5 < this.field_35684_d.size(); ++i5) {
				BiomeCacheBlock biomeCacheBlock6 = (BiomeCacheBlock)this.field_35684_d.get(i5);
				long j7 = j1 - biomeCacheBlock6.field_35701_f;
				if(j7 > 30000L || j7 < 0L) {
					this.field_35684_d.remove(i5--);
					long j9 = (long)biomeCacheBlock6.field_35703_d & 4294967295L | ((long)biomeCacheBlock6.field_35704_e & 4294967295L) << 32;
					this.field_35686_c.remove(j9);
				}
			}
		}

	}

	public BiomeGenBase[] func_35682_b(int i1, int i2) {
		return this.func_35680_c(i1, i2).field_35706_c;
	}

	static WorldChunkManager func_35679_a(BiomeCache biomeCache0) {
		return biomeCache0.field_35687_a;
	}
}
