package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class ComponentVillageHouse2 extends ComponentVillage {
	private int field_35086_a = -1;

	public ComponentVillageHouse2(int i1, Random random2, StructureBoundingBox structureBoundingBox3, int i4) {
		super(i1);
		this.field_35025_h = i4;
		this.field_35024_g = structureBoundingBox3;
	}

	public void func_35004_a(StructureComponent structureComponent1, List list2, Random random3) {
	}

	public static ComponentVillageHouse2 func_35085_a(List list0, Random random1, int i2, int i3, int i4, int i5, int i6) {
		StructureBoundingBox structureBoundingBox7 = StructureBoundingBox.func_35747_a(i2, i3, i4, 0, 0, 0, 10, 6, 7, i5);
		return func_35074_a(structureBoundingBox7) && StructureComponent.func_35020_a(list0, structureBoundingBox7) == null ? new ComponentVillageHouse2(i6, random1, structureBoundingBox7, i5) : null;
	}

	public boolean func_35023_a(World world1, Random random2, StructureBoundingBox structureBoundingBox3) {
		if(this.field_35086_a < 0) {
			this.field_35086_a = this.func_35075_b(world1, structureBoundingBox3);
			if(this.field_35086_a < 0) {
				return true;
			}

			this.field_35024_g.func_35745_a(0, this.field_35086_a - this.field_35024_g.field_35750_e + 6 - 1, 0);
		}

		this.func_35011_a(world1, structureBoundingBox3, 0, 1, 0, 9, 4, 6, 0, 0, false);
		this.func_35011_a(world1, structureBoundingBox3, 0, 0, 0, 9, 0, 6, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
		this.func_35011_a(world1, structureBoundingBox3, 0, 4, 0, 9, 4, 6, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
		this.func_35011_a(world1, structureBoundingBox3, 0, 5, 0, 9, 5, 6, Block.stairSingle.blockID, Block.stairSingle.blockID, false);
		this.func_35011_a(world1, structureBoundingBox3, 1, 5, 1, 8, 5, 5, 0, 0, false);
		this.func_35011_a(world1, structureBoundingBox3, 1, 1, 0, 2, 3, 0, Block.planks.blockID, Block.planks.blockID, false);
		this.func_35011_a(world1, structureBoundingBox3, 0, 1, 0, 0, 4, 0, Block.wood.blockID, Block.wood.blockID, false);
		this.func_35011_a(world1, structureBoundingBox3, 3, 1, 0, 3, 4, 0, Block.wood.blockID, Block.wood.blockID, false);
		this.func_35011_a(world1, structureBoundingBox3, 0, 1, 6, 0, 4, 6, Block.wood.blockID, Block.wood.blockID, false);
		this.func_35018_a(world1, Block.planks.blockID, 0, 3, 3, 1, structureBoundingBox3);
		this.func_35011_a(world1, structureBoundingBox3, 3, 1, 2, 3, 3, 2, Block.planks.blockID, Block.planks.blockID, false);
		this.func_35011_a(world1, structureBoundingBox3, 4, 1, 3, 5, 3, 3, Block.planks.blockID, Block.planks.blockID, false);
		this.func_35011_a(world1, structureBoundingBox3, 0, 1, 1, 0, 3, 5, Block.planks.blockID, Block.planks.blockID, false);
		this.func_35011_a(world1, structureBoundingBox3, 1, 1, 6, 5, 3, 6, Block.planks.blockID, Block.planks.blockID, false);
		this.func_35011_a(world1, structureBoundingBox3, 5, 1, 0, 5, 3, 0, Block.fence.blockID, Block.fence.blockID, false);
		this.func_35011_a(world1, structureBoundingBox3, 9, 1, 0, 9, 3, 0, Block.fence.blockID, Block.fence.blockID, false);
		this.func_35011_a(world1, structureBoundingBox3, 6, 1, 4, 9, 4, 6, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
		this.func_35018_a(world1, Block.lavaMoving.blockID, 0, 7, 1, 5, structureBoundingBox3);
		this.func_35018_a(world1, Block.lavaMoving.blockID, 0, 8, 1, 5, structureBoundingBox3);
		this.func_35018_a(world1, Block.field_35288_bq.blockID, 0, 9, 2, 5, structureBoundingBox3);
		this.func_35018_a(world1, Block.field_35288_bq.blockID, 0, 9, 2, 4, structureBoundingBox3);
		this.func_35011_a(world1, structureBoundingBox3, 7, 2, 4, 8, 2, 5, 0, 0, false);
		this.func_35018_a(world1, Block.cobblestone.blockID, 0, 6, 1, 3, structureBoundingBox3);
		this.func_35018_a(world1, Block.stoneOvenIdle.blockID, 0, 6, 2, 3, structureBoundingBox3);
		this.func_35018_a(world1, Block.stoneOvenIdle.blockID, 0, 6, 3, 3, structureBoundingBox3);
		this.func_35018_a(world1, Block.stairDouble.blockID, 0, 8, 1, 1, structureBoundingBox3);
		this.func_35018_a(world1, Block.field_35282_br.blockID, 0, 0, 2, 2, structureBoundingBox3);
		this.func_35018_a(world1, Block.field_35282_br.blockID, 0, 0, 2, 4, structureBoundingBox3);
		this.func_35018_a(world1, Block.field_35282_br.blockID, 0, 2, 2, 6, structureBoundingBox3);
		this.func_35018_a(world1, Block.field_35282_br.blockID, 0, 4, 2, 6, structureBoundingBox3);
		this.func_35018_a(world1, Block.fence.blockID, 0, 2, 1, 4, structureBoundingBox3);
		this.func_35018_a(world1, Block.pressurePlatePlanks.blockID, 0, 2, 2, 4, structureBoundingBox3);
		this.func_35018_a(world1, Block.planks.blockID, 0, 1, 1, 5, structureBoundingBox3);
		this.func_35018_a(world1, Block.stairCompactPlanks.blockID, this.func_35009_c(Block.stairCompactPlanks.blockID, 3), 2, 1, 5, structureBoundingBox3);
		this.func_35018_a(world1, Block.stairCompactPlanks.blockID, this.func_35009_c(Block.stairCompactPlanks.blockID, 1), 1, 1, 4, structureBoundingBox3);

		int i4;
		for(i4 = 6; i4 <= 8; ++i4) {
			if(this.func_35007_a(world1, i4, 0, -1, structureBoundingBox3) == 0 && this.func_35007_a(world1, i4, -1, -1, structureBoundingBox3) != 0) {
				this.func_35018_a(world1, Block.stairCompactCobblestone.blockID, this.func_35009_c(Block.stairCompactCobblestone.blockID, 3), i4, 0, -1, structureBoundingBox3);
			}
		}

		for(i4 = 0; i4 < 7; ++i4) {
			for(int i5 = 0; i5 < 10; ++i5) {
				this.func_35016_b(world1, i5, 6, i4, structureBoundingBox3);
				this.func_35005_b(world1, Block.cobblestone.blockID, 0, i5, -1, i4, structureBoundingBox3);
			}
		}

		return true;
	}
}
