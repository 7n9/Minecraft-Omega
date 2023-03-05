package net.minecraft.src;

import java.util.List;
import java.util.Random;

abstract class ComponentVillage extends StructureComponent {
	protected ComponentVillage(int i1) {
		super(i1);
	}

	protected StructureComponent func_35077_a(ComponentVillageStartPiece componentVillageStartPiece1, List list2, Random random3, int i4, int i5) {
		switch(this.field_35025_h) {
		case 0:
			return StructureVillagePieces.func_35704_a(componentVillageStartPiece1, list2, random3, this.field_35024_g.field_35753_a - 1, this.field_35024_g.field_35751_b + i4, this.field_35024_g.field_35752_c + i5, 1, this.func_35012_c());
		case 1:
			return StructureVillagePieces.func_35704_a(componentVillageStartPiece1, list2, random3, this.field_35024_g.field_35753_a + i5, this.field_35024_g.field_35751_b + i4, this.field_35024_g.field_35752_c - 1, 2, this.func_35012_c());
		case 2:
			return StructureVillagePieces.func_35704_a(componentVillageStartPiece1, list2, random3, this.field_35024_g.field_35753_a - 1, this.field_35024_g.field_35751_b + i4, this.field_35024_g.field_35752_c + i5, 1, this.func_35012_c());
		case 3:
			return StructureVillagePieces.func_35704_a(componentVillageStartPiece1, list2, random3, this.field_35024_g.field_35753_a + i5, this.field_35024_g.field_35751_b + i4, this.field_35024_g.field_35752_c - 1, 2, this.func_35012_c());
		default:
			return null;
		}
	}

	protected StructureComponent func_35076_b(ComponentVillageStartPiece componentVillageStartPiece1, List list2, Random random3, int i4, int i5) {
		switch(this.field_35025_h) {
		case 0:
			return StructureVillagePieces.func_35704_a(componentVillageStartPiece1, list2, random3, this.field_35024_g.field_35749_d + 1, this.field_35024_g.field_35751_b + i4, this.field_35024_g.field_35752_c + i5, 3, this.func_35012_c());
		case 1:
			return StructureVillagePieces.func_35704_a(componentVillageStartPiece1, list2, random3, this.field_35024_g.field_35753_a + i5, this.field_35024_g.field_35751_b + i4, this.field_35024_g.field_35748_f + 1, 0, this.func_35012_c());
		case 2:
			return StructureVillagePieces.func_35704_a(componentVillageStartPiece1, list2, random3, this.field_35024_g.field_35749_d + 1, this.field_35024_g.field_35751_b + i4, this.field_35024_g.field_35752_c + i5, 3, this.func_35012_c());
		case 3:
			return StructureVillagePieces.func_35704_a(componentVillageStartPiece1, list2, random3, this.field_35024_g.field_35753_a + i5, this.field_35024_g.field_35751_b + i4, this.field_35024_g.field_35748_f + 1, 0, this.func_35012_c());
		default:
			return null;
		}
	}

	protected int func_35075_b(World world1, StructureBoundingBox structureBoundingBox2) {
		int i3 = 0;
		int i4 = 0;

		for(int i5 = this.field_35024_g.field_35752_c; i5 <= this.field_35024_g.field_35748_f; ++i5) {
			for(int i6 = this.field_35024_g.field_35753_a; i6 <= this.field_35024_g.field_35749_d; ++i6) {
				if(structureBoundingBox2.func_35742_b(i6, 64, i5)) {
					int i10001 = world1.getTopSolidOrLiquidBlock(i6, i5);
					world1.getClass();
					i3 += Math.max(i10001, 63);
					++i4;
				}
			}
		}

		if(i4 == 0) {
			return -1;
		} else {
			return i3 / i4;
		}
	}

	protected static boolean func_35074_a(StructureBoundingBox structureBoundingBox0) {
		return structureBoundingBox0 != null && structureBoundingBox0.field_35751_b > 10;
	}
}
