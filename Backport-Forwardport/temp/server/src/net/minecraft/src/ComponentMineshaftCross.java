package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class ComponentMineshaftCross extends StructureComponent {
	private final int field_35364_a;
	private final boolean field_35363_b;

	public ComponentMineshaftCross(int i1, Random random2, StructureBoundingBox structureBoundingBox3, int i4) {
		super(i1);
		this.field_35364_a = i4;
		this.field_35316_g = structureBoundingBox3;
		this.field_35363_b = structureBoundingBox3.func_35668_c() > 3;
	}

	public static StructureBoundingBox func_35362_a(List list0, Random random1, int i2, int i3, int i4, int i5) {
		StructureBoundingBox structureBoundingBox6 = new StructureBoundingBox(i2, i3, i4, i2, i3 + 2, i4);
		if(random1.nextInt(4) == 0) {
			structureBoundingBox6.field_35675_e += 4;
		}

		switch(i5) {
		case 0:
			structureBoundingBox6.field_35678_a = i2 - 1;
			structureBoundingBox6.field_35674_d = i2 + 3;
			structureBoundingBox6.field_35673_f = i4 + 4;
			break;
		case 1:
			structureBoundingBox6.field_35678_a = i2 - 4;
			structureBoundingBox6.field_35677_c = i4 - 1;
			structureBoundingBox6.field_35673_f = i4 + 3;
			break;
		case 2:
			structureBoundingBox6.field_35678_a = i2 - 1;
			structureBoundingBox6.field_35674_d = i2 + 3;
			structureBoundingBox6.field_35677_c = i4 - 4;
			break;
		case 3:
			structureBoundingBox6.field_35674_d = i2 + 4;
			structureBoundingBox6.field_35677_c = i4 - 1;
			structureBoundingBox6.field_35673_f = i4 + 3;
		}

		return StructureComponent.func_35312_a(list0, structureBoundingBox6) != null ? null : structureBoundingBox6;
	}

	public void func_35308_a(StructureComponent structureComponent1, List list2, Random random3) {
		int i4 = this.func_35305_c();
		switch(this.field_35364_a) {
		case 0:
			StructureMineshaftPieces.func_35433_a(structureComponent1, list2, random3, this.field_35316_g.field_35678_a + 1, this.field_35316_g.field_35676_b, this.field_35316_g.field_35673_f + 1, 0, i4);
			StructureMineshaftPieces.func_35433_a(structureComponent1, list2, random3, this.field_35316_g.field_35678_a - 1, this.field_35316_g.field_35676_b, this.field_35316_g.field_35677_c + 1, 1, i4);
			StructureMineshaftPieces.func_35433_a(structureComponent1, list2, random3, this.field_35316_g.field_35674_d + 1, this.field_35316_g.field_35676_b, this.field_35316_g.field_35677_c + 1, 3, i4);
			break;
		case 1:
			StructureMineshaftPieces.func_35433_a(structureComponent1, list2, random3, this.field_35316_g.field_35678_a + 1, this.field_35316_g.field_35676_b, this.field_35316_g.field_35677_c - 1, 2, i4);
			StructureMineshaftPieces.func_35433_a(structureComponent1, list2, random3, this.field_35316_g.field_35678_a + 1, this.field_35316_g.field_35676_b, this.field_35316_g.field_35673_f + 1, 0, i4);
			StructureMineshaftPieces.func_35433_a(structureComponent1, list2, random3, this.field_35316_g.field_35678_a - 1, this.field_35316_g.field_35676_b, this.field_35316_g.field_35677_c + 1, 1, i4);
			break;
		case 2:
			StructureMineshaftPieces.func_35433_a(structureComponent1, list2, random3, this.field_35316_g.field_35678_a + 1, this.field_35316_g.field_35676_b, this.field_35316_g.field_35677_c - 1, 2, i4);
			StructureMineshaftPieces.func_35433_a(structureComponent1, list2, random3, this.field_35316_g.field_35678_a - 1, this.field_35316_g.field_35676_b, this.field_35316_g.field_35677_c + 1, 1, i4);
			StructureMineshaftPieces.func_35433_a(structureComponent1, list2, random3, this.field_35316_g.field_35674_d + 1, this.field_35316_g.field_35676_b, this.field_35316_g.field_35677_c + 1, 3, i4);
			break;
		case 3:
			StructureMineshaftPieces.func_35433_a(structureComponent1, list2, random3, this.field_35316_g.field_35678_a + 1, this.field_35316_g.field_35676_b, this.field_35316_g.field_35677_c - 1, 2, i4);
			StructureMineshaftPieces.func_35433_a(structureComponent1, list2, random3, this.field_35316_g.field_35678_a + 1, this.field_35316_g.field_35676_b, this.field_35316_g.field_35673_f + 1, 0, i4);
			StructureMineshaftPieces.func_35433_a(structureComponent1, list2, random3, this.field_35316_g.field_35674_d + 1, this.field_35316_g.field_35676_b, this.field_35316_g.field_35677_c + 1, 3, i4);
		}

		if(this.field_35363_b) {
			if(random3.nextBoolean()) {
				StructureMineshaftPieces.func_35433_a(structureComponent1, list2, random3, this.field_35316_g.field_35678_a + 1, this.field_35316_g.field_35676_b + 3 + 1, this.field_35316_g.field_35677_c - 1, 2, i4);
			}

			if(random3.nextBoolean()) {
				StructureMineshaftPieces.func_35433_a(structureComponent1, list2, random3, this.field_35316_g.field_35678_a - 1, this.field_35316_g.field_35676_b + 3 + 1, this.field_35316_g.field_35677_c + 1, 1, i4);
			}

			if(random3.nextBoolean()) {
				StructureMineshaftPieces.func_35433_a(structureComponent1, list2, random3, this.field_35316_g.field_35674_d + 1, this.field_35316_g.field_35676_b + 3 + 1, this.field_35316_g.field_35677_c + 1, 3, i4);
			}

			if(random3.nextBoolean()) {
				StructureMineshaftPieces.func_35433_a(structureComponent1, list2, random3, this.field_35316_g.field_35678_a + 1, this.field_35316_g.field_35676_b + 3 + 1, this.field_35316_g.field_35673_f + 1, 0, i4);
			}
		}

	}

	public boolean func_35310_a(World world1, Random random2, StructureBoundingBox structureBoundingBox3) {
		if(this.func_35295_a(world1, structureBoundingBox3)) {
			return false;
		} else {
			if(this.field_35363_b) {
				this.func_35294_a(world1, structureBoundingBox3, this.field_35316_g.field_35678_a + 1, this.field_35316_g.field_35676_b, this.field_35316_g.field_35677_c, this.field_35316_g.field_35674_d - 1, this.field_35316_g.field_35676_b + 3 - 1, this.field_35316_g.field_35673_f, 0, 0, false);
				this.func_35294_a(world1, structureBoundingBox3, this.field_35316_g.field_35678_a, this.field_35316_g.field_35676_b, this.field_35316_g.field_35677_c + 1, this.field_35316_g.field_35674_d, this.field_35316_g.field_35676_b + 3 - 1, this.field_35316_g.field_35673_f - 1, 0, 0, false);
				this.func_35294_a(world1, structureBoundingBox3, this.field_35316_g.field_35678_a + 1, this.field_35316_g.field_35675_e - 2, this.field_35316_g.field_35677_c, this.field_35316_g.field_35674_d - 1, this.field_35316_g.field_35675_e, this.field_35316_g.field_35673_f, 0, 0, false);
				this.func_35294_a(world1, structureBoundingBox3, this.field_35316_g.field_35678_a, this.field_35316_g.field_35675_e - 2, this.field_35316_g.field_35677_c + 1, this.field_35316_g.field_35674_d, this.field_35316_g.field_35675_e, this.field_35316_g.field_35673_f - 1, 0, 0, false);
				this.func_35294_a(world1, structureBoundingBox3, this.field_35316_g.field_35678_a + 1, this.field_35316_g.field_35676_b + 3, this.field_35316_g.field_35677_c + 1, this.field_35316_g.field_35674_d - 1, this.field_35316_g.field_35676_b + 3, this.field_35316_g.field_35673_f - 1, 0, 0, false);
			} else {
				this.func_35294_a(world1, structureBoundingBox3, this.field_35316_g.field_35678_a + 1, this.field_35316_g.field_35676_b, this.field_35316_g.field_35677_c, this.field_35316_g.field_35674_d - 1, this.field_35316_g.field_35675_e, this.field_35316_g.field_35673_f, 0, 0, false);
				this.func_35294_a(world1, structureBoundingBox3, this.field_35316_g.field_35678_a, this.field_35316_g.field_35676_b, this.field_35316_g.field_35677_c + 1, this.field_35316_g.field_35674_d, this.field_35316_g.field_35675_e, this.field_35316_g.field_35673_f - 1, 0, 0, false);
			}

			this.func_35294_a(world1, structureBoundingBox3, this.field_35316_g.field_35678_a + 1, this.field_35316_g.field_35676_b, this.field_35316_g.field_35677_c + 1, this.field_35316_g.field_35678_a + 1, this.field_35316_g.field_35675_e, this.field_35316_g.field_35677_c + 1, Block.planks.blockID, 0, false);
			this.func_35294_a(world1, structureBoundingBox3, this.field_35316_g.field_35678_a + 1, this.field_35316_g.field_35676_b, this.field_35316_g.field_35673_f - 1, this.field_35316_g.field_35678_a + 1, this.field_35316_g.field_35675_e, this.field_35316_g.field_35673_f - 1, Block.planks.blockID, 0, false);
			this.func_35294_a(world1, structureBoundingBox3, this.field_35316_g.field_35674_d - 1, this.field_35316_g.field_35676_b, this.field_35316_g.field_35677_c + 1, this.field_35316_g.field_35674_d - 1, this.field_35316_g.field_35675_e, this.field_35316_g.field_35677_c + 1, Block.planks.blockID, 0, false);
			this.func_35294_a(world1, structureBoundingBox3, this.field_35316_g.field_35674_d - 1, this.field_35316_g.field_35676_b, this.field_35316_g.field_35673_f - 1, this.field_35316_g.field_35674_d - 1, this.field_35316_g.field_35675_e, this.field_35316_g.field_35673_f - 1, Block.planks.blockID, 0, false);
			return true;
		}
	}
}
