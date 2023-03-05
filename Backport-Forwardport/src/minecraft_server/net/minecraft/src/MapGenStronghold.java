package net.minecraft.src;

import java.util.ArrayList;

public class MapGenStronghold extends MapGenStructure {
	private BiomeGenBase[] field_35537_a = new BiomeGenBase[]{BiomeGenBase.desert, BiomeGenBase.forest, BiomeGenBase.field_35518_e, BiomeGenBase.swampland};
	private boolean field_35535_f;
	private ChunkCoordIntPair[] field_35536_g = new ChunkCoordIntPair[3];

	protected boolean func_35531_a(int i1, int i2) {
		int i5;
		if(!this.field_35535_f) {
			this.rand.setSeed(this.field_35530_d.getRandomSeed());
			double d3 = this.rand.nextDouble() * Math.PI * 2.0D;

			for(i5 = 0; i5 < this.field_35536_g.length; ++i5) {
				double d6 = (1.25D + this.rand.nextDouble()) * 32.0D;
				int i8 = (int)Math.round(Math.cos(d3) * d6);
				int i9 = (int)Math.round(Math.sin(d3) * d6);
				ArrayList arrayList10 = new ArrayList();
				BiomeGenBase[] biomeGenBase11 = this.field_35537_a;
				int i12 = biomeGenBase11.length;

				for(int i13 = 0; i13 < i12; ++i13) {
					BiomeGenBase biomeGenBase14 = biomeGenBase11[i13];
					arrayList10.add(biomeGenBase14);
				}

				ChunkPosition chunkPosition17 = this.field_35530_d.getWorldChunkManager().func_35139_a((i8 << 4) + 8, (i9 << 4) + 8, 112, arrayList10, this.rand);
				if(chunkPosition17 != null) {
					i8 = chunkPosition17.x >> 4;
					i9 = chunkPosition17.z >> 4;
				} else {
					System.out.println("Placed stronghold in INVALID biome at (" + i8 + ", " + i9 + ")");
				}

				this.field_35536_g[i5] = new ChunkCoordIntPair(i8, i9);
				d3 += Math.PI * 2D / (double)this.field_35536_g.length;
			}

			this.field_35535_f = true;
		}

		ChunkCoordIntPair[] chunkCoordIntPair15 = this.field_35536_g;
		int i4 = chunkCoordIntPair15.length;

		for(i5 = 0; i5 < i4; ++i5) {
			ChunkCoordIntPair chunkCoordIntPair16 = chunkCoordIntPair15[i5];
			if(i1 == chunkCoordIntPair16.chunkXPos && i2 == chunkCoordIntPair16.chunkZPos) {
				return true;
			}
		}

		return false;
	}

	protected StructureStart func_35533_b(int i1, int i2) {
		return new StructureStrongholdStart(this.field_35530_d, this.rand, i1, i2);
	}
}
