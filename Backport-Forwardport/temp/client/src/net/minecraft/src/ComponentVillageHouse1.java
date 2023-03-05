package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class ComponentVillageHouse1 extends ComponentVillage {
	private int field_35096_a = -1;

	public ComponentVillageHouse1(int i1, Random random2, StructureBoundingBox structureBoundingBox3, int i4) {
		super(i1);
		this.field_35025_h = i4;
		this.field_35024_g = structureBoundingBox3;
	}

	public void func_35004_a(StructureComponent structureComponent1, List list2, Random random3) {
	}

	public static ComponentVillageHouse1 func_35095_a(List list0, Random random1, int i2, int i3, int i4, int i5, int i6) {
		StructureBoundingBox structureBoundingBox7 = StructureBoundingBox.func_35747_a(i2, i3, i4, 0, 0, 0, 9, 9, 6, i5);
		return func_35074_a(structureBoundingBox7) && StructureComponent.func_35020_a(list0, structureBoundingBox7) == null ? new ComponentVillageHouse1(i6, random1, structureBoundingBox7, i5) : null;
	}

	public boolean func_35023_a(World world1, Random random2, StructureBoundingBox structureBoundingBox3) {
		if(this.field_35096_a < 0) {
			this.field_35096_a = this.func_35075_b(world1, structureBoundingBox3);
			if(this.field_35096_a < 0) {
				return true;
			}

			this.field_35024_g.func_35745_a(0, this.field_35096_a - this.field_35024_g.field_35750_e + 9 - 1, 0);
		}

		this.func_35011_a(world1, structureBoundingBox3, 1, 1, 1, 7, 5, 4, 0, 0, false);
		this.func_35011_a(world1, structureBoundingBox3, 0, 0, 0, 8, 0, 5, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
		this.func_35011_a(world1, structureBoundingBox3, 0, 5, 0, 8, 5, 5, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
		this.func_35011_a(world1, structureBoundingBox3, 0, 6, 1, 8, 6, 4, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
		this.func_35011_a(world1, structureBoundingBox3, 0, 7, 2, 8, 7, 3, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
		int i4 = this.func_35009_c(Block.stairCompactPlanks.blockID, 3);
		int i5 = this.func_35009_c(Block.stairCompactPlanks.blockID, 2);

		int i6;
		int i7;
		for(i6 = -1; i6 <= 2; ++i6) {
			for(i7 = 0; i7 <= 8; ++i7) {
				this.func_35018_a(world1, Block.stairCompactPlanks.blockID, i4, i7, 6 + i6, i6, structureBoundingBox3);
				this.func_35018_a(world1, Block.stairCompactPlanks.blockID, i5, i7, 6 + i6, 5 - i6, structureBoundingBox3);
			}
		}

		this.func_35011_a(world1, structureBoundingBox3, 0, 1, 0, 0, 1, 5, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
		this.func_35011_a(world1, structureBoundingBox3, 1, 1, 5, 8, 1, 5, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
		this.func_35011_a(world1, structureBoundingBox3, 8, 1, 0, 8, 1, 4, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
		this.func_35011_a(world1, structureBoundingBox3, 2, 1, 0, 7, 1, 0, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
		this.func_35011_a(world1, structureBoundingBox3, 0, 2, 0, 0, 4, 0, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
		this.func_35011_a(world1, structureBoundingBox3, 0, 2, 5, 0, 4, 5, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
		this.func_35011_a(world1, structureBoundingBox3, 8, 2, 5, 8, 4, 5, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
		this.func_35011_a(world1, structureBoundingBox3, 8, 2, 0, 8, 4, 0, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
		this.func_35011_a(world1, structureBoundingBox3, 0, 2, 1, 0, 4, 4, Block.planks.blockID, Block.planks.blockID, false);
		this.func_35011_a(world1, structureBoundingBox3, 1, 2, 5, 7, 4, 5, Block.planks.blockID, Block.planks.blockID, false);
		this.func_35011_a(world1, structureBoundingBox3, 8, 2, 1, 8, 4, 4, Block.planks.blockID, Block.planks.blockID, false);
		this.func_35011_a(world1, structureBoundingBox3, 1, 2, 0, 7, 4, 0, Block.planks.blockID, Block.planks.blockID, false);
		this.func_35018_a(world1, Block.field_35282_br.blockID, 0, 4, 2, 0, structureBoundingBox3);
		this.func_35018_a(world1, Block.field_35282_br.blockID, 0, 5, 2, 0, structureBoundingBox3);
		this.func_35018_a(world1, Block.field_35282_br.blockID, 0, 6, 2, 0, structureBoundingBox3);
		this.func_35018_a(world1, Block.field_35282_br.blockID, 0, 4, 3, 0, structureBoundingBox3);
		this.func_35018_a(world1, Block.field_35282_br.blockID, 0, 5, 3, 0, structureBoundingBox3);
		this.func_35018_a(world1, Block.field_35282_br.blockID, 0, 6, 3, 0, structureBoundingBox3);
		this.func_35018_a(world1, Block.field_35282_br.blockID, 0, 0, 2, 2, structureBoundingBox3);
		this.func_35018_a(world1, Block.field_35282_br.blockID, 0, 0, 2, 3, structureBoundingBox3);
		this.func_35018_a(world1, Block.field_35282_br.blockID, 0, 0, 3, 2, structureBoundingBox3);
		this.func_35018_a(world1, Block.field_35282_br.blockID, 0, 0, 3, 3, structureBoundingBox3);
		this.func_35018_a(world1, Block.field_35282_br.blockID, 0, 8, 2, 2, structureBoundingBox3);
		this.func_35018_a(world1, Block.field_35282_br.blockID, 0, 8, 2, 3, structureBoundingBox3);
		this.func_35018_a(world1, Block.field_35282_br.blockID, 0, 8, 3, 2, structureBoundingBox3);
		this.func_35018_a(world1, Block.field_35282_br.blockID, 0, 8, 3, 3, structureBoundingBox3);
		this.func_35018_a(world1, Block.field_35282_br.blockID, 0, 2, 2, 5, structureBoundingBox3);
		this.func_35018_a(world1, Block.field_35282_br.blockID, 0, 3, 2, 5, structureBoundingBox3);
		this.func_35018_a(world1, Block.field_35282_br.blockID, 0, 5, 2, 5, structureBoundingBox3);
		this.func_35018_a(world1, Block.field_35282_br.blockID, 0, 6, 2, 5, structureBoundingBox3);
		this.func_35011_a(world1, structureBoundingBox3, 1, 4, 1, 7, 4, 1, Block.planks.blockID, Block.planks.blockID, false);
		this.func_35011_a(world1, structureBoundingBox3, 1, 4, 4, 7, 4, 4, Block.planks.blockID, Block.planks.blockID, false);
		this.func_35011_a(world1, structureBoundingBox3, 1, 3, 4, 7, 3, 4, Block.bookShelf.blockID, Block.bookShelf.blockID, false);
		this.func_35018_a(world1, Block.planks.blockID, 0, 7, 1, 4, structureBoundingBox3);
		this.func_35018_a(world1, Block.stairCompactPlanks.blockID, this.func_35009_c(Block.stairCompactPlanks.blockID, 0), 7, 1, 3, structureBoundingBox3);
		i6 = this.func_35009_c(Block.stairCompactPlanks.blockID, 3);
		this.func_35018_a(world1, Block.stairCompactPlanks.blockID, i6, 6, 1, 4, structureBoundingBox3);
		this.func_35018_a(world1, Block.stairCompactPlanks.blockID, i6, 5, 1, 4, structureBoundingBox3);
		this.func_35018_a(world1, Block.stairCompactPlanks.blockID, i6, 4, 1, 4, structureBoundingBox3);
		this.func_35018_a(world1, Block.stairCompactPlanks.blockID, i6, 3, 1, 4, structureBoundingBox3);
		this.func_35018_a(world1, Block.fence.blockID, 0, 6, 1, 3, structureBoundingBox3);
		this.func_35018_a(world1, Block.pressurePlatePlanks.blockID, 0, 6, 2, 3, structureBoundingBox3);
		this.func_35018_a(world1, Block.fence.blockID, 0, 4, 1, 3, structureBoundingBox3);
		this.func_35018_a(world1, Block.pressurePlatePlanks.blockID, 0, 4, 2, 3, structureBoundingBox3);
		this.func_35018_a(world1, Block.workbench.blockID, 0, 7, 1, 1, structureBoundingBox3);
		this.func_35018_a(world1, 0, 0, 1, 1, 0, structureBoundingBox3);
		this.func_35018_a(world1, 0, 0, 1, 2, 0, structureBoundingBox3);
		this.func_35002_a(world1, structureBoundingBox3, random2, 1, 1, 0, this.func_35009_c(Block.doorWood.blockID, 1));
		if(this.func_35007_a(world1, 1, 0, -1, structureBoundingBox3) == 0 && this.func_35007_a(world1, 1, -1, -1, structureBoundingBox3) != 0) {
			this.func_35018_a(world1, Block.stairCompactCobblestone.blockID, this.func_35009_c(Block.stairCompactCobblestone.blockID, 3), 1, 0, -1, structureBoundingBox3);
		}

		for(i7 = 0; i7 < 6; ++i7) {
			for(int i8 = 0; i8 < 9; ++i8) {
				this.func_35016_b(world1, i8, 9, i7, structureBoundingBox3);
				this.func_35005_b(world1, Block.cobblestone.blockID, 0, i8, -1, i7, structureBoundingBox3);
			}
		}

		return true;
	}
}