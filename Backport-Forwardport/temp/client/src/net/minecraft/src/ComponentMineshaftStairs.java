package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class ComponentMineshaftStairs extends StructureComponent {
	public ComponentMineshaftStairs(int i1, Random random2, StructureBoundingBox structureBoundingBox3, int i4) {
		super(i1);
		this.field_35025_h = i4;
		this.field_35024_g = structureBoundingBox3;
	}

	public static StructureBoundingBox func_35027_a(List list0, Random random1, int i2, int i3, int i4, int i5) {
		StructureBoundingBox structureBoundingBox6 = new StructureBoundingBox(i2, i3 - 5, i4, i2, i3 + 2, i4);
		switch(i5) {
		case 0:
			structureBoundingBox6.field_35749_d = i2 + 2;
			structureBoundingBox6.field_35748_f = i4 + 8;
			break;
		case 1:
			structureBoundingBox6.field_35753_a = i2 - 8;
			structureBoundingBox6.field_35748_f = i4 + 2;
			break;
		case 2:
			structureBoundingBox6.field_35749_d = i2 + 2;
			structureBoundingBox6.field_35752_c = i4 - 8;
			break;
		case 3:
			structureBoundingBox6.field_35749_d = i2 + 8;
			structureBoundingBox6.field_35748_f = i4 + 2;
		}

		return StructureComponent.func_35020_a(list0, structureBoundingBox6) != null ? null : structureBoundingBox6;
	}

	public void func_35004_a(StructureComponent structureComponent1, List list2, Random random3) {
		int i4 = this.func_35012_c();
		switch(this.field_35025_h) {
		case 0:
			StructureMineshaftPieces.func_35585_a(structureComponent1, list2, random3, this.field_35024_g.field_35753_a, this.field_35024_g.field_35751_b, this.field_35024_g.field_35748_f + 1, 0, i4);
			break;
		case 1:
			StructureMineshaftPieces.func_35585_a(structureComponent1, list2, random3, this.field_35024_g.field_35753_a - 1, this.field_35024_g.field_35751_b, this.field_35024_g.field_35752_c, 1, i4);
			break;
		case 2:
			StructureMineshaftPieces.func_35585_a(structureComponent1, list2, random3, this.field_35024_g.field_35753_a, this.field_35024_g.field_35751_b, this.field_35024_g.field_35752_c - 1, 2, i4);
			break;
		case 3:
			StructureMineshaftPieces.func_35585_a(structureComponent1, list2, random3, this.field_35024_g.field_35749_d + 1, this.field_35024_g.field_35751_b, this.field_35024_g.field_35752_c, 3, i4);
		}

	}

	public boolean func_35023_a(World world1, Random random2, StructureBoundingBox structureBoundingBox3) {
		if(this.func_35013_a(world1, structureBoundingBox3)) {
			return false;
		} else {
			this.func_35011_a(world1, structureBoundingBox3, 0, 5, 0, 2, 7, 1, 0, 0, false);
			this.func_35011_a(world1, structureBoundingBox3, 0, 0, 7, 2, 2, 8, 0, 0, false);

			for(int i4 = 0; i4 < 5; ++i4) {
				this.func_35011_a(world1, structureBoundingBox3, 0, 5 - i4 - (i4 < 4 ? 1 : 0), 2 + i4, 2, 7 - i4, 2 + i4, 0, 0, false);
			}

			return true;
		}
	}
}
