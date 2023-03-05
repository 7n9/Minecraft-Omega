package net.minecraft.src;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public abstract class MapGenStructure extends MapGenBase {
	protected HashMap field_35534_e = new HashMap();

	public void generate(IChunkProvider iChunkProvider1, World world2, int i3, int i4, byte[] b5) {
		super.generate(iChunkProvider1, world2, i3, i4, b5);
	}

	protected void recursiveGenerate(World world1, int i2, int i3, int i4, int i5, byte[] b6) {
		if(!this.field_35534_e.containsKey(ChunkCoordIntPair.chunkXZ2Int(i2, i3))) {
			this.rand.nextInt();
			if(this.func_35531_a(i2, i3)) {
				StructureStart structureStart7 = this.func_35533_b(i2, i3);
				this.field_35534_e.put(ChunkCoordIntPair.chunkXZ2Int(i2, i3), structureStart7);
			}

		}
	}

	public boolean func_35532_a(World world1, Random random2, int i3, int i4) {
		int i5 = (i3 << 4) + 8;
		int i6 = (i4 << 4) + 8;
		boolean z7 = false;
		Iterator iterator8 = this.field_35534_e.values().iterator();

		while(iterator8.hasNext()) {
			StructureStart structureStart9 = (StructureStart)iterator8.next();
			if(structureStart9.func_35542_a() && structureStart9.func_35543_b().func_35671_a(i5, i6, i5 + 15, i6 + 15)) {
				structureStart9.func_35541_a(world1, random2, new StructureBoundingBox(i5, i6, i5 + 15, i6 + 15));
				z7 = true;
			}
		}

		return z7;
	}

	protected abstract boolean func_35531_a(int i1, int i2);

	protected abstract StructureStart func_35533_b(int i1, int i2);
}
