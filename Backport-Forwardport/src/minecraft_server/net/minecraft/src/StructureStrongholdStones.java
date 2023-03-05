package net.minecraft.src;

import java.util.Random;

class StructureStrongholdStones extends StructurePieceBlockSelector {
	private StructureStrongholdStones() {
	}

	public void func_35565_a(Random random1, int i2, int i3, int i4, boolean z5) {
		if(!z5) {
			this.field_35569_a = 0;
			this.field_35568_b = 0;
		} else {
			this.field_35569_a = Block.field_35052_bn.blockID;
			float f6 = random1.nextFloat();
			if(f6 < 0.2F) {
				this.field_35568_b = 2;
			} else if(f6 < 0.5F) {
				this.field_35568_b = 1;
			} else if(f6 < 0.55F) {
				this.field_35569_a = Block.field_35056_bm.blockID;
				this.field_35568_b = 2;
			} else {
				this.field_35568_b = 0;
			}
		}

	}

	StructureStrongholdStones(StructureStrongholdPieceWeight2 structureStrongholdPieceWeight21) {
		this();
	}
}
