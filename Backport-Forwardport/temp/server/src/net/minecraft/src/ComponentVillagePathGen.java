package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class ComponentVillagePathGen extends ComponentVillageRoadPiece {
	private int field_35379_a;

	public ComponentVillagePathGen(int i1, Random random2, StructureBoundingBox structureBoundingBox3, int i4) {
		super(i1);
		this.field_35317_h = i4;
		this.field_35316_g = structureBoundingBox3;
		this.field_35379_a = Math.max(structureBoundingBox3.func_35669_b(), structureBoundingBox3.func_35665_d());
	}

	public void func_35308_a(StructureComponent structureComponent1, List list2, Random random3) {
		boolean z4 = false;

		int i5;
		StructureComponent structureComponent6;
		for(i5 = random3.nextInt(5); i5 < this.field_35379_a - 8; i5 += 2 + random3.nextInt(5)) {
			structureComponent6 = this.func_35368_a((ComponentVillageStartPiece)structureComponent1, list2, random3, 0, i5);
			if(structureComponent6 != null) {
				i5 += Math.max(structureComponent6.field_35316_g.func_35669_b(), structureComponent6.field_35316_g.func_35665_d());
				z4 = true;
			}
		}

		for(i5 = random3.nextInt(5); i5 < this.field_35379_a - 8; i5 += 2 + random3.nextInt(5)) {
			structureComponent6 = this.func_35369_b((ComponentVillageStartPiece)structureComponent1, list2, random3, 0, i5);
			if(structureComponent6 != null) {
				i5 += Math.max(structureComponent6.field_35316_g.func_35669_b(), structureComponent6.field_35316_g.func_35665_d());
				z4 = true;
			}
		}

		if(z4 && random3.nextInt(3) > 0) {
			switch(this.field_35317_h) {
			case 0:
				StructureVillagePieces.func_35642_b((ComponentVillageStartPiece)structureComponent1, list2, random3, this.field_35316_g.field_35678_a - 1, this.field_35316_g.field_35676_b, this.field_35316_g.field_35673_f - 2, 1, this.func_35305_c());
				break;
			case 1:
				StructureVillagePieces.func_35642_b((ComponentVillageStartPiece)structureComponent1, list2, random3, this.field_35316_g.field_35678_a, this.field_35316_g.field_35676_b, this.field_35316_g.field_35677_c - 1, 2, this.func_35305_c());
				break;
			case 2:
				StructureVillagePieces.func_35642_b((ComponentVillageStartPiece)structureComponent1, list2, random3, this.field_35316_g.field_35678_a - 1, this.field_35316_g.field_35676_b, this.field_35316_g.field_35677_c, 1, this.func_35305_c());
				break;
			case 3:
				StructureVillagePieces.func_35642_b((ComponentVillageStartPiece)structureComponent1, list2, random3, this.field_35316_g.field_35674_d - 2, this.field_35316_g.field_35676_b, this.field_35316_g.field_35677_c - 1, 2, this.func_35305_c());
			}
		}

		if(z4 && random3.nextInt(3) > 0) {
			switch(this.field_35317_h) {
			case 0:
				StructureVillagePieces.func_35642_b((ComponentVillageStartPiece)structureComponent1, list2, random3, this.field_35316_g.field_35674_d + 1, this.field_35316_g.field_35676_b, this.field_35316_g.field_35673_f - 2, 3, this.func_35305_c());
				break;
			case 1:
				StructureVillagePieces.func_35642_b((ComponentVillageStartPiece)structureComponent1, list2, random3, this.field_35316_g.field_35678_a, this.field_35316_g.field_35676_b, this.field_35316_g.field_35673_f + 1, 0, this.func_35305_c());
				break;
			case 2:
				StructureVillagePieces.func_35642_b((ComponentVillageStartPiece)structureComponent1, list2, random3, this.field_35316_g.field_35674_d + 1, this.field_35316_g.field_35676_b, this.field_35316_g.field_35677_c, 3, this.func_35305_c());
				break;
			case 3:
				StructureVillagePieces.func_35642_b((ComponentVillageStartPiece)structureComponent1, list2, random3, this.field_35316_g.field_35674_d - 2, this.field_35316_g.field_35676_b, this.field_35316_g.field_35673_f + 1, 0, this.func_35305_c());
			}
		}

	}

	public static StructureBoundingBox func_35378_a(ComponentVillageStartPiece componentVillageStartPiece0, List list1, Random random2, int i3, int i4, int i5, int i6) {
		for(int i7 = 7 * MathHelper.func_35476_a(random2, 3, 5); i7 >= 7; i7 -= 7) {
			StructureBoundingBox structureBoundingBox8 = StructureBoundingBox.func_35663_a(i3, i4, i5, 0, 0, 0, 3, 3, i7, i6);
			if(StructureComponent.func_35312_a(list1, structureBoundingBox8) == null) {
				return structureBoundingBox8;
			}
		}

		return null;
	}

	public boolean func_35310_a(World world1, Random random2, StructureBoundingBox structureBoundingBox3) {
		for(int i4 = this.field_35316_g.field_35678_a; i4 <= this.field_35316_g.field_35674_d; ++i4) {
			for(int i5 = this.field_35316_g.field_35677_c; i5 <= this.field_35316_g.field_35673_f; ++i5) {
				if(structureBoundingBox3.func_35667_b(i4, 64, i5)) {
					int i6 = world1.findTopSolidBlock(i4, i5) - 1;
					world1.setBlock(i4, i6, i5, Block.gravel.blockID);
				}
			}
		}

		return true;
	}
}
