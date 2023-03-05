package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;

public class BiomeCache {
	private final WorldChunkManager field_35731_a;
	private long field_35729_b = 0L;
	private PlayerList field_35730_c = new PlayerList();
	private List field_35728_d = new ArrayList();

	public BiomeCache(WorldChunkManager worldChunkManager1) {
		this.field_35731_a = worldChunkManager1;
	}

	private BiomeCacheBlock func_35726_e(int i1, int i2) {
		i1 >>= 4;
		i2 >>= 4;
		long j3 = (long)i1 & 4294967295L | ((long)i2 & 4294967295L) << 32;
		BiomeCacheBlock biomeCacheBlock5 = (BiomeCacheBlock)this.field_35730_c.func_35578_a(j3);
		if(biomeCacheBlock5 == null) {
			biomeCacheBlock5 = new BiomeCacheBlock(this, i1, i2);
			this.field_35730_c.func_35577_a(j3, biomeCacheBlock5);
			this.field_35728_d.add(biomeCacheBlock5);
		}

		biomeCacheBlock5.field_35653_f = System.currentTimeMillis();
		return biomeCacheBlock5;
	}

	public BiomeGenBase func_35725_a(int i1, int i2) {
		return this.func_35726_e(i1, i2).func_35651_a(i1, i2);
	}

	public float func_35722_b(int i1, int i2) {
		return this.func_35726_e(i1, i2).func_35650_b(i1, i2);
	}

	public float func_35727_c(int i1, int i2) {
		return this.func_35726_e(i1, i2).func_35652_c(i1, i2);
	}

	public void func_35724_a() {
		long j1 = System.currentTimeMillis();
		long j3 = j1 - this.field_35729_b;
		if(j3 > 7500L || j3 < 0L) {
			this.field_35729_b = j1;

			for(int i5 = 0; i5 < this.field_35728_d.size(); ++i5) {
				BiomeCacheBlock biomeCacheBlock6 = (BiomeCacheBlock)this.field_35728_d.get(i5);
				long j7 = j1 - biomeCacheBlock6.field_35653_f;
				if(j7 > 30000L || j7 < 0L) {
					this.field_35728_d.remove(i5--);
					long j9 = (long)biomeCacheBlock6.field_35655_d & 4294967295L | ((long)biomeCacheBlock6.field_35656_e & 4294967295L) << 32;
					this.field_35730_c.func_35574_d(j9);
				}
			}
		}

	}

	public BiomeGenBase[] func_35723_d(int i1, int i2) {
		return this.func_35726_e(i1, i2).field_35658_c;
	}

	static WorldChunkManager func_35721_a(BiomeCache biomeCache0) {
		return biomeCache0.field_35731_a;
	}
}
