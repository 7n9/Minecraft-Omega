package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class ComponentVillageChurch extends ComponentVillage {
	private int field_35381_a = -1;

	public ComponentVillageChurch(int i1, Random random2, StructureBoundingBox structureBoundingBox3, int i4) {
		super(i1);
		this.field_35317_h = i4;
		this.field_35316_g = structureBoundingBox3;
	}

	public void func_35308_a(StructureComponent structureComponent1, List list2, Random random3) {
	}

	public static ComponentVillageChurch func_35380_a(List list0, Random random1, int i2, int i3, int i4, int i5, int i6) {
		StructureBoundingBox structureBoundingBox7 = StructureBoundingBox.func_35663_a(i2, i3, i4, 0, 0, 0, 5, 12, 9, i5);
		return func_35366_a(structureBoundingBox7) && StructureComponent.func_35312_a(list0, structureBoundingBox7) == null ? new ComponentVillageChurch(i6, random1, structureBoundingBox7, i5) : null;
	}

	public boolean func_35310_a(World world1, Random random2, StructureBoundingBox structureBoundingBox3) {
		if(this.field_35381_a < 0) {
			this.field_35381_a = this.func_35367_b(world1, structureBoundingBox3);
			if(this.field_35381_a < 0) {
				return true;
			}

			this.field_35316_g.func_35670_a(0, this.field_35381_a - this.field_35316_g.field_35675_e + 12 - 1, 0);
		}

		this.func_35294_a(world1, structureBoundingBox3, 1, 1, 1, 3, 3, 7, 0, 0, false);
		this.func_35294_a(world1, structureBoundingBox3, 1, 5, 1, 3, 9, 3, 0, 0, false);
		this.func_35294_a(world1, structureBoundingBox3, 1, 0, 0, 3, 0, 8, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
		this.func_35294_a(world1, structureBoundingBox3, 1, 1, 0, 3, 10, 0, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
		this.func_35294_a(world1, structureBoundingBox3, 0, 1, 1, 0, 10, 3, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
		this.func_35294_a(world1, structureBoundingBox3, 4, 1, 1, 4, 10, 3, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
		this.func_35294_a(world1, structureBoundingBox3, 0, 0, 4, 0, 4, 7, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
		this.func_35294_a(world1, structureBoundingBox3, 4, 0, 4, 4, 4, 7, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
		this.func_35294_a(world1, structureBoundingBox3, 1, 1, 8, 3, 4, 8, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
		this.func_35294_a(world1, structureBoundingBox3, 1, 5, 4, 3, 10, 4, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
		this.func_35294_a(world1, structureBoundingBox3, 1, 5, 5, 3, 5, 7, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
		this.func_35294_a(world1, structureBoundingBox3, 0, 9, 0, 4, 9, 4, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
		this.func_35294_a(world1, structureBoundingBox3, 0, 4, 0, 4, 4, 4, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
		this.func_35309_a(world1, Block.cobblestone.blockID, 0, 0, 11, 2, structureBoundingBox3);
		this.func_35309_a(world1, Block.cobblestone.blockID, 0, 4, 11, 2, structureBoundingBox3);
		this.func_35309_a(world1, Block.cobblestone.blockID, 0, 2, 11, 0, structureBoundingBox3);
		this.func_35309_a(world1, Block.cobblestone.blockID, 0, 2, 11, 4, structureBoundingBox3);
		this.func_35309_a(world1, Block.cobblestone.blockID, 0, 1, 1, 6, structureBoundingBox3);
		this.func_35309_a(world1, Block.cobblestone.blockID, 0, 1, 1, 7, structureBoundingBox3);
		this.func_35309_a(world1, Block.cobblestone.blockID, 0, 2, 1, 7, structureBoundingBox3);
		this.func_35309_a(world1, Block.cobblestone.blockID, 0, 3, 1, 6, structureBoundingBox3);
		this.func_35309_a(world1, Block.cobblestone.blockID, 0, 3, 1, 7, structureBoundingBox3);
		this.func_35309_a(world1, Block.stairCompactCobblestone.blockID, this.func_35301_c(Block.stairCompactCobblestone.blockID, 3), 1, 1, 5, structureBoundingBox3);
		this.func_35309_a(world1, Block.stairCompactCobblestone.blockID, this.func_35301_c(Block.stairCompactCobblestone.blockID, 3), 2, 1, 6, structureBoundingBox3);
		this.func_35309_a(world1, Block.stairCompactCobblestone.blockID, this.func_35301_c(Block.stairCompactCobblestone.blockID, 3), 3, 1, 5, structureBoundingBox3);
		this.func_35309_a(world1, Block.stairCompactCobblestone.blockID, this.func_35301_c(Block.stairCompactCobblestone.blockID, 1), 1, 2, 7, structureBoundingBox3);
		this.func_35309_a(world1, Block.stairCompactCobblestone.blockID, this.func_35301_c(Block.stairCompactCobblestone.blockID, 0), 3, 2, 7, structureBoundingBox3);
		this.func_35309_a(world1, Block.field_35049_br.blockID, 0, 0, 2, 2, structureBoundingBox3);
		this.func_35309_a(world1, Block.field_35049_br.blockID, 0, 0, 3, 2, structureBoundingBox3);
		this.func_35309_a(world1, Block.field_35049_br.blockID, 0, 4, 2, 2, structureBoundingBox3);
		this.func_35309_a(world1, Block.field_35049_br.blockID, 0, 4, 3, 2, structureBoundingBox3);
		this.func_35309_a(world1, Block.field_35049_br.blockID, 0, 0, 6, 2, structureBoundingBox3);
		this.func_35309_a(world1, Block.field_35049_br.blockID, 0, 0, 7, 2, structureBoundingBox3);
		this.func_35309_a(world1, Block.field_35049_br.blockID, 0, 4, 6, 2, structureBoundingBox3);
		this.func_35309_a(world1, Block.field_35049_br.blockID, 0, 4, 7, 2, structureBoundingBox3);
		this.func_35309_a(world1, Block.field_35049_br.blockID, 0, 2, 6, 0, structureBoundingBox3);
		this.func_35309_a(world1, Block.field_35049_br.blockID, 0, 2, 7, 0, structureBoundingBox3);
		this.func_35309_a(world1, Block.field_35049_br.blockID, 0, 2, 6, 4, structureBoundingBox3);
		this.func_35309_a(world1, Block.field_35049_br.blockID, 0, 2, 7, 4, structureBoundingBox3);
		this.func_35309_a(world1, Block.field_35049_br.blockID, 0, 0, 3, 6, structureBoundingBox3);
		this.func_35309_a(world1, Block.field_35049_br.blockID, 0, 4, 3, 6, structureBoundingBox3);
		this.func_35309_a(world1, Block.field_35049_br.blockID, 0, 2, 3, 8, structureBoundingBox3);
		this.func_35309_a(world1, Block.torchWood.blockID, 0, 2, 4, 7, structureBoundingBox3);
		this.func_35309_a(world1, Block.torchWood.blockID, 0, 1, 4, 6, structureBoundingBox3);
		this.func_35309_a(world1, Block.torchWood.blockID, 0, 3, 4, 6, structureBoundingBox3);
		this.func_35309_a(world1, Block.torchWood.blockID, 0, 2, 4, 5, structureBoundingBox3);
		int i4 = this.func_35301_c(Block.ladder.blockID, 4);

		int i5;
		for(i5 = 1; i5 <= 9; ++i5) {
			this.func_35309_a(world1, Block.ladder.blockID, i4, 3, i5, 3, structureBoundingBox3);
		}

		this.func_35309_a(world1, 0, 0, 2, 1, 0, structureBoundingBox3);
		this.func_35309_a(world1, 0, 0, 2, 2, 0, structureBoundingBox3);
		this.func_35298_a(world1, structureBoundingBox3, random2, 2, 1, 0, this.func_35301_c(Block.doorWood.blockID, 1));
		if(this.func_35297_a(world1, 2, 0, -1, structureBoundingBox3) == 0 && this.func_35297_a(world1, 2, -1, -1, structureBoundingBox3) != 0) {
			this.func_35309_a(world1, Block.stairCompactCobblestone.blockID, this.func_35301_c(Block.stairCompactCobblestone.blockID, 3), 2, 0, -1, structureBoundingBox3);
		}

		for(i5 = 0; i5 < 9; ++i5) {
			for(int i6 = 0; i6 < 5; ++i6) {
				this.func_35314_b(world1, i6, 12, i5, structureBoundingBox3);
				this.func_35303_b(world1, Block.cobblestone.blockID, 0, i6, -1, i5, structureBoundingBox3);
			}
		}

		return true;
	}
}
