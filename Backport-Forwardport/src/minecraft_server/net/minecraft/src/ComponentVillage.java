package net.minecraft.src;

import java.util.List;
import java.util.Random;

abstract class ComponentVillage extends StructureComponent {
	protected ComponentVillage(int i1) {
		super(i1);
	}

	protected StructureComponent func_35368_a(ComponentVillageStartPiece componentVillageStartPiece1, List list2, Random random3, int i4, int i5) {
		switch(this.field_35317_h) {
		case 0:
			return StructureVillagePieces.func_35640_a(componentVillageStartPiece1, list2, random3, this.field_35316_g.field_35678_a - 1, this.field_35316_g.field_35676_b + i4, this.field_35316_g.field_35677_c + i5, 1, this.func_35305_c());
		case 1:
			return StructureVillagePieces.func_35640_a(componentVillageStartPiece1, list2, random3, this.field_35316_g.field_35678_a + i5, this.field_35316_g.field_35676_b + i4, this.field_35316_g.field_35677_c - 1, 2, this.func_35305_c());
		case 2:
			return StructureVillagePieces.func_35640_a(componentVillageStartPiece1, list2, random3, this.field_35316_g.field_35678_a - 1, this.field_35316_g.field_35676_b + i4, this.field_35316_g.field_35677_c + i5, 1, this.func_35305_c());
		case 3:
			return StructureVillagePieces.func_35640_a(componentVillageStartPiece1, list2, random3, this.field_35316_g.field_35678_a + i5, this.field_35316_g.field_35676_b + i4, this.field_35316_g.field_35677_c - 1, 2, this.func_35305_c());
		default:
			return null;
		}
	}

	protected StructureComponent func_35369_b(ComponentVillageStartPiece componentVillageStartPiece1, List list2, Random random3, int i4, int i5) {
		switch(this.field_35317_h) {
		case 0:
			return StructureVillagePieces.func_35640_a(componentVillageStartPiece1, list2, random3, this.field_35316_g.field_35674_d + 1, this.field_35316_g.field_35676_b + i4, this.field_35316_g.field_35677_c + i5, 3, this.func_35305_c());
		case 1:
			return StructureVillagePieces.func_35640_a(componentVillageStartPiece1, list2, random3, this.field_35316_g.field_35678_a + i5, this.field_35316_g.field_35676_b + i4, this.field_35316_g.field_35673_f + 1, 0, this.func_35305_c());
		case 2:
			return StructureVillagePieces.func_35640_a(componentVillageStartPiece1, list2, random3, this.field_35316_g.field_35674_d + 1, this.field_35316_g.field_35676_b + i4, this.field_35316_g.field_35677_c + i5, 3, this.func_35305_c());
		case 3:
			return StructureVillagePieces.func_35640_a(componentVillageStartPiece1, list2, random3, this.field_35316_g.field_35678_a + i5, this.field_35316_g.field_35676_b + i4, this.field_35316_g.field_35673_f + 1, 0, this.func_35305_c());
		default:
			return null;
		}
	}

	protected int func_35367_b(World world1, StructureBoundingBox structureBoundingBox2) {
		int i3 = 0;
		int i4 = 0;

		for(int i5 = this.field_35316_g.field_35677_c; i5 <= this.field_35316_g.field_35673_f; ++i5) {
			for(int i6 = this.field_35316_g.field_35678_a; i6 <= this.field_35316_g.field_35674_d; ++i6) {
				if(structureBoundingBox2.func_35667_b(i6, 64, i5)) {
					int i10001 = world1.findTopSolidBlock(i6, i5);
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

	protected static boolean func_35366_a(StructureBoundingBox structureBoundingBox0) {
		return structureBoundingBox0 != null && structureBoundingBox0.field_35676_b > 10;
	}
}
