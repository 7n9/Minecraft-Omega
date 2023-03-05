package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class ComponentVillageHall extends ComponentVillage {
	private int field_35375_a = -1;

	public ComponentVillageHall(int i1, Random random2, StructureBoundingBox structureBoundingBox3, int i4) {
		super(i1);
		this.field_35317_h = i4;
		this.field_35316_g = structureBoundingBox3;
	}

	public void func_35308_a(StructureComponent structureComponent1, List list2, Random random3) {
	}

	public static ComponentVillageHall func_35374_a(List list0, Random random1, int i2, int i3, int i4, int i5, int i6) {
		StructureBoundingBox structureBoundingBox7 = StructureBoundingBox.func_35663_a(i2, i3, i4, 0, 0, 0, 9, 7, 11, i5);
		return func_35366_a(structureBoundingBox7) && StructureComponent.func_35312_a(list0, structureBoundingBox7) == null ? new ComponentVillageHall(i6, random1, structureBoundingBox7, i5) : null;
	}

	public boolean func_35310_a(World world1, Random random2, StructureBoundingBox structureBoundingBox3) {
		if(this.field_35375_a < 0) {
			this.field_35375_a = this.func_35367_b(world1, structureBoundingBox3);
			if(this.field_35375_a < 0) {
				return true;
			}

			this.field_35316_g.func_35670_a(0, this.field_35375_a - this.field_35316_g.field_35675_e + 7 - 1, 0);
		}

		this.func_35294_a(world1, structureBoundingBox3, 1, 1, 1, 7, 4, 4, 0, 0, false);
		this.func_35294_a(world1, structureBoundingBox3, 2, 1, 6, 8, 4, 10, 0, 0, false);
		this.func_35294_a(world1, structureBoundingBox3, 2, 0, 6, 8, 0, 10, Block.dirt.blockID, Block.dirt.blockID, false);
		this.func_35309_a(world1, Block.cobblestone.blockID, 0, 6, 0, 6, structureBoundingBox3);
		this.func_35294_a(world1, structureBoundingBox3, 2, 1, 6, 2, 1, 10, Block.fence.blockID, Block.fence.blockID, false);
		this.func_35294_a(world1, structureBoundingBox3, 8, 1, 6, 8, 1, 10, Block.fence.blockID, Block.fence.blockID, false);
		this.func_35294_a(world1, structureBoundingBox3, 3, 1, 10, 7, 1, 10, Block.fence.blockID, Block.fence.blockID, false);
		this.func_35294_a(world1, structureBoundingBox3, 1, 0, 1, 7, 0, 4, Block.planks.blockID, Block.planks.blockID, false);
		this.func_35294_a(world1, structureBoundingBox3, 0, 0, 0, 0, 3, 5, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
		this.func_35294_a(world1, structureBoundingBox3, 8, 0, 0, 8, 3, 5, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
		this.func_35294_a(world1, structureBoundingBox3, 1, 0, 0, 7, 1, 0, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
		this.func_35294_a(world1, structureBoundingBox3, 1, 0, 5, 7, 1, 5, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
		this.func_35294_a(world1, structureBoundingBox3, 1, 2, 0, 7, 3, 0, Block.planks.blockID, Block.planks.blockID, false);
		this.func_35294_a(world1, structureBoundingBox3, 1, 2, 5, 7, 3, 5, Block.planks.blockID, Block.planks.blockID, false);
		this.func_35294_a(world1, structureBoundingBox3, 0, 4, 1, 8, 4, 1, Block.planks.blockID, Block.planks.blockID, false);
		this.func_35294_a(world1, structureBoundingBox3, 0, 4, 4, 8, 4, 4, Block.planks.blockID, Block.planks.blockID, false);
		this.func_35294_a(world1, structureBoundingBox3, 0, 5, 2, 8, 5, 3, Block.planks.blockID, Block.planks.blockID, false);
		this.func_35309_a(world1, Block.planks.blockID, 0, 0, 4, 2, structureBoundingBox3);
		this.func_35309_a(world1, Block.planks.blockID, 0, 0, 4, 3, structureBoundingBox3);
		this.func_35309_a(world1, Block.planks.blockID, 0, 8, 4, 2, structureBoundingBox3);
		this.func_35309_a(world1, Block.planks.blockID, 0, 8, 4, 3, structureBoundingBox3);
		int i4 = this.func_35301_c(Block.stairCompactPlanks.blockID, 3);
		int i5 = this.func_35301_c(Block.stairCompactPlanks.blockID, 2);

		int i6;
		int i7;
		for(i6 = -1; i6 <= 2; ++i6) {
			for(i7 = 0; i7 <= 8; ++i7) {
				this.func_35309_a(world1, Block.stairCompactPlanks.blockID, i4, i7, 4 + i6, i6, structureBoundingBox3);
				this.func_35309_a(world1, Block.stairCompactPlanks.blockID, i5, i7, 4 + i6, 5 - i6, structureBoundingBox3);
			}
		}

		this.func_35309_a(world1, Block.wood.blockID, 0, 0, 2, 1, structureBoundingBox3);
		this.func_35309_a(world1, Block.wood.blockID, 0, 0, 2, 4, structureBoundingBox3);
		this.func_35309_a(world1, Block.wood.blockID, 0, 8, 2, 1, structureBoundingBox3);
		this.func_35309_a(world1, Block.wood.blockID, 0, 8, 2, 4, structureBoundingBox3);
		this.func_35309_a(world1, Block.field_35049_br.blockID, 0, 0, 2, 2, structureBoundingBox3);
		this.func_35309_a(world1, Block.field_35049_br.blockID, 0, 0, 2, 3, structureBoundingBox3);
		this.func_35309_a(world1, Block.field_35049_br.blockID, 0, 8, 2, 2, structureBoundingBox3);
		this.func_35309_a(world1, Block.field_35049_br.blockID, 0, 8, 2, 3, structureBoundingBox3);
		this.func_35309_a(world1, Block.field_35049_br.blockID, 0, 2, 2, 5, structureBoundingBox3);
		this.func_35309_a(world1, Block.field_35049_br.blockID, 0, 3, 2, 5, structureBoundingBox3);
		this.func_35309_a(world1, Block.field_35049_br.blockID, 0, 5, 2, 0, structureBoundingBox3);
		this.func_35309_a(world1, Block.field_35049_br.blockID, 0, 6, 2, 5, structureBoundingBox3);
		this.func_35309_a(world1, Block.fence.blockID, 0, 2, 1, 3, structureBoundingBox3);
		this.func_35309_a(world1, Block.pressurePlatePlanks.blockID, 0, 2, 2, 3, structureBoundingBox3);
		this.func_35309_a(world1, Block.planks.blockID, 0, 1, 1, 4, structureBoundingBox3);
		this.func_35309_a(world1, Block.stairCompactPlanks.blockID, this.func_35301_c(Block.stairCompactPlanks.blockID, 3), 2, 1, 4, structureBoundingBox3);
		this.func_35309_a(world1, Block.stairCompactPlanks.blockID, this.func_35301_c(Block.stairCompactPlanks.blockID, 1), 1, 1, 3, structureBoundingBox3);
		this.func_35294_a(world1, structureBoundingBox3, 5, 0, 1, 7, 0, 3, Block.stairDouble.blockID, Block.stairDouble.blockID, false);
		this.func_35309_a(world1, Block.stairDouble.blockID, 0, 6, 1, 1, structureBoundingBox3);
		this.func_35309_a(world1, Block.stairDouble.blockID, 0, 6, 1, 2, structureBoundingBox3);
		this.func_35309_a(world1, 0, 0, 2, 1, 0, structureBoundingBox3);
		this.func_35309_a(world1, 0, 0, 2, 2, 0, structureBoundingBox3);
		this.func_35309_a(world1, Block.torchWood.blockID, 0, 2, 3, 1, structureBoundingBox3);
		this.func_35298_a(world1, structureBoundingBox3, random2, 2, 1, 0, this.func_35301_c(Block.doorWood.blockID, 1));
		if(this.func_35297_a(world1, 2, 0, -1, structureBoundingBox3) == 0 && this.func_35297_a(world1, 2, -1, -1, structureBoundingBox3) != 0) {
			this.func_35309_a(world1, Block.stairCompactCobblestone.blockID, this.func_35301_c(Block.stairCompactCobblestone.blockID, 3), 2, 0, -1, structureBoundingBox3);
		}

		this.func_35309_a(world1, 0, 0, 6, 1, 5, structureBoundingBox3);
		this.func_35309_a(world1, 0, 0, 6, 2, 5, structureBoundingBox3);
		this.func_35309_a(world1, Block.torchWood.blockID, 0, 6, 3, 4, structureBoundingBox3);
		this.func_35298_a(world1, structureBoundingBox3, random2, 6, 1, 5, this.func_35301_c(Block.doorWood.blockID, 1));

		for(i6 = 0; i6 < 5; ++i6) {
			for(i7 = 0; i7 < 9; ++i7) {
				this.func_35314_b(world1, i7, 7, i6, structureBoundingBox3);
				this.func_35303_b(world1, Block.cobblestone.blockID, 0, i7, -1, i6, structureBoundingBox3);
			}
		}

		return true;
	}
}
