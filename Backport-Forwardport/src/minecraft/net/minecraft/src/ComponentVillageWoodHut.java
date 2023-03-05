package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class ComponentVillageWoodHut extends ComponentVillage {
	private int field_35094_a = -1;
	private final boolean field_35092_b;
	private final int field_35093_c;

	public ComponentVillageWoodHut(int i1, Random random2, StructureBoundingBox structureBoundingBox3, int i4) {
		super(i1);
		this.field_35025_h = i4;
		this.field_35024_g = structureBoundingBox3;
		this.field_35092_b = random2.nextBoolean();
		this.field_35093_c = random2.nextInt(3);
	}

	public void func_35004_a(StructureComponent structureComponent1, List list2, Random random3) {
	}

	public static ComponentVillageWoodHut func_35091_a(List list0, Random random1, int i2, int i3, int i4, int i5, int i6) {
		StructureBoundingBox structureBoundingBox7 = StructureBoundingBox.func_35747_a(i2, i3, i4, 0, 0, 0, 4, 6, 5, i5);
		return func_35074_a(structureBoundingBox7) && StructureComponent.func_35020_a(list0, structureBoundingBox7) == null ? new ComponentVillageWoodHut(i6, random1, structureBoundingBox7, i5) : null;
	}

	public boolean func_35023_a(World world1, Random random2, StructureBoundingBox structureBoundingBox3) {
		if(this.field_35094_a < 0) {
			this.field_35094_a = this.func_35075_b(world1, structureBoundingBox3);
			if(this.field_35094_a < 0) {
				return true;
			}

			this.field_35024_g.func_35745_a(0, this.field_35094_a - this.field_35024_g.field_35750_e + 6 - 1, 0);
		}

		this.func_35011_a(world1, structureBoundingBox3, 1, 1, 1, 3, 5, 4, 0, 0, false);
		this.func_35011_a(world1, structureBoundingBox3, 0, 0, 0, 3, 0, 4, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
		this.func_35011_a(world1, structureBoundingBox3, 1, 0, 1, 2, 0, 3, Block.dirt.blockID, Block.dirt.blockID, false);
		if(this.field_35092_b) {
			this.func_35011_a(world1, structureBoundingBox3, 1, 4, 1, 2, 4, 3, Block.wood.blockID, Block.wood.blockID, false);
		} else {
			this.func_35011_a(world1, structureBoundingBox3, 1, 5, 1, 2, 5, 3, Block.wood.blockID, Block.wood.blockID, false);
		}

		this.func_35018_a(world1, Block.wood.blockID, 0, 1, 4, 0, structureBoundingBox3);
		this.func_35018_a(world1, Block.wood.blockID, 0, 2, 4, 0, structureBoundingBox3);
		this.func_35018_a(world1, Block.wood.blockID, 0, 1, 4, 4, structureBoundingBox3);
		this.func_35018_a(world1, Block.wood.blockID, 0, 2, 4, 4, structureBoundingBox3);
		this.func_35018_a(world1, Block.wood.blockID, 0, 0, 4, 1, structureBoundingBox3);
		this.func_35018_a(world1, Block.wood.blockID, 0, 0, 4, 2, structureBoundingBox3);
		this.func_35018_a(world1, Block.wood.blockID, 0, 0, 4, 3, structureBoundingBox3);
		this.func_35018_a(world1, Block.wood.blockID, 0, 3, 4, 1, structureBoundingBox3);
		this.func_35018_a(world1, Block.wood.blockID, 0, 3, 4, 2, structureBoundingBox3);
		this.func_35018_a(world1, Block.wood.blockID, 0, 3, 4, 3, structureBoundingBox3);
		this.func_35011_a(world1, structureBoundingBox3, 0, 1, 0, 0, 3, 0, Block.wood.blockID, Block.wood.blockID, false);
		this.func_35011_a(world1, structureBoundingBox3, 3, 1, 0, 3, 3, 0, Block.wood.blockID, Block.wood.blockID, false);
		this.func_35011_a(world1, structureBoundingBox3, 0, 1, 4, 0, 3, 4, Block.wood.blockID, Block.wood.blockID, false);
		this.func_35011_a(world1, structureBoundingBox3, 3, 1, 4, 3, 3, 4, Block.wood.blockID, Block.wood.blockID, false);
		this.func_35011_a(world1, structureBoundingBox3, 0, 1, 1, 0, 3, 3, Block.planks.blockID, Block.planks.blockID, false);
		this.func_35011_a(world1, structureBoundingBox3, 3, 1, 1, 3, 3, 3, Block.planks.blockID, Block.planks.blockID, false);
		this.func_35011_a(world1, structureBoundingBox3, 1, 1, 0, 2, 3, 0, Block.planks.blockID, Block.planks.blockID, false);
		this.func_35011_a(world1, structureBoundingBox3, 1, 1, 4, 2, 3, 4, Block.planks.blockID, Block.planks.blockID, false);
		this.func_35018_a(world1, Block.field_35282_br.blockID, 0, 0, 2, 2, structureBoundingBox3);
		this.func_35018_a(world1, Block.field_35282_br.blockID, 0, 3, 2, 2, structureBoundingBox3);
		if(this.field_35093_c > 0) {
			this.func_35018_a(world1, Block.fence.blockID, 0, this.field_35093_c, 1, 3, structureBoundingBox3);
			this.func_35018_a(world1, Block.pressurePlatePlanks.blockID, 0, this.field_35093_c, 2, 3, structureBoundingBox3);
		}

		this.func_35018_a(world1, 0, 0, 1, 1, 0, structureBoundingBox3);
		this.func_35018_a(world1, 0, 0, 1, 2, 0, structureBoundingBox3);
		this.func_35002_a(world1, structureBoundingBox3, random2, 1, 1, 0, this.func_35009_c(Block.doorWood.blockID, 1));
		if(this.func_35007_a(world1, 1, 0, -1, structureBoundingBox3) == 0 && this.func_35007_a(world1, 1, -1, -1, structureBoundingBox3) != 0) {
			this.func_35018_a(world1, Block.stairCompactCobblestone.blockID, this.func_35009_c(Block.stairCompactCobblestone.blockID, 3), 1, 0, -1, structureBoundingBox3);
		}

		for(int i4 = 0; i4 < 5; ++i4) {
			for(int i5 = 0; i5 < 4; ++i5) {
				this.func_35016_b(world1, i5, 6, i4, structureBoundingBox3);
				this.func_35005_b(world1, Block.cobblestone.blockID, 0, i5, -1, i4, structureBoundingBox3);
			}
		}

		return true;
	}
}
