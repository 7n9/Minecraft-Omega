package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class ComponentVillageHouse4_Garden extends ComponentVillage {
	private int field_35084_a = -1;
	private final boolean field_35083_b;

	public ComponentVillageHouse4_Garden(int i1, Random random2, StructureBoundingBox structureBoundingBox3, int i4) {
		super(i1);
		this.field_35025_h = i4;
		this.field_35024_g = structureBoundingBox3;
		this.field_35083_b = random2.nextBoolean();
	}

	public void func_35004_a(StructureComponent structureComponent1, List list2, Random random3) {
	}

	public static ComponentVillageHouse4_Garden func_35082_a(List list0, Random random1, int i2, int i3, int i4, int i5, int i6) {
		StructureBoundingBox structureBoundingBox7 = StructureBoundingBox.func_35747_a(i2, i3, i4, 0, 0, 0, 5, 6, 5, i5);
		return StructureComponent.func_35020_a(list0, structureBoundingBox7) != null ? null : new ComponentVillageHouse4_Garden(i6, random1, structureBoundingBox7, i5);
	}

	public boolean func_35023_a(World world1, Random random2, StructureBoundingBox structureBoundingBox3) {
		if(this.field_35084_a < 0) {
			this.field_35084_a = this.func_35075_b(world1, structureBoundingBox3);
			if(this.field_35084_a < 0) {
				return true;
			}

			this.field_35024_g.func_35745_a(0, this.field_35084_a - this.field_35024_g.field_35750_e + 6 - 1, 0);
		}

		this.func_35011_a(world1, structureBoundingBox3, 0, 0, 0, 4, 0, 4, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
		this.func_35011_a(world1, structureBoundingBox3, 0, 4, 0, 4, 4, 4, Block.wood.blockID, Block.wood.blockID, false);
		this.func_35011_a(world1, structureBoundingBox3, 1, 4, 1, 3, 4, 3, Block.planks.blockID, Block.planks.blockID, false);
		this.func_35018_a(world1, Block.cobblestone.blockID, 0, 0, 1, 0, structureBoundingBox3);
		this.func_35018_a(world1, Block.cobblestone.blockID, 0, 0, 2, 0, structureBoundingBox3);
		this.func_35018_a(world1, Block.cobblestone.blockID, 0, 0, 3, 0, structureBoundingBox3);
		this.func_35018_a(world1, Block.cobblestone.blockID, 0, 4, 1, 0, structureBoundingBox3);
		this.func_35018_a(world1, Block.cobblestone.blockID, 0, 4, 2, 0, structureBoundingBox3);
		this.func_35018_a(world1, Block.cobblestone.blockID, 0, 4, 3, 0, structureBoundingBox3);
		this.func_35018_a(world1, Block.cobblestone.blockID, 0, 0, 1, 4, structureBoundingBox3);
		this.func_35018_a(world1, Block.cobblestone.blockID, 0, 0, 2, 4, structureBoundingBox3);
		this.func_35018_a(world1, Block.cobblestone.blockID, 0, 0, 3, 4, structureBoundingBox3);
		this.func_35018_a(world1, Block.cobblestone.blockID, 0, 4, 1, 4, structureBoundingBox3);
		this.func_35018_a(world1, Block.cobblestone.blockID, 0, 4, 2, 4, structureBoundingBox3);
		this.func_35018_a(world1, Block.cobblestone.blockID, 0, 4, 3, 4, structureBoundingBox3);
		this.func_35011_a(world1, structureBoundingBox3, 0, 1, 1, 0, 3, 3, Block.planks.blockID, Block.planks.blockID, false);
		this.func_35011_a(world1, structureBoundingBox3, 4, 1, 1, 4, 3, 3, Block.planks.blockID, Block.planks.blockID, false);
		this.func_35011_a(world1, structureBoundingBox3, 1, 1, 4, 3, 3, 4, Block.planks.blockID, Block.planks.blockID, false);
		this.func_35018_a(world1, Block.field_35282_br.blockID, 0, 0, 2, 2, structureBoundingBox3);
		this.func_35018_a(world1, Block.field_35282_br.blockID, 0, 2, 2, 4, structureBoundingBox3);
		this.func_35018_a(world1, Block.field_35282_br.blockID, 0, 4, 2, 2, structureBoundingBox3);
		this.func_35018_a(world1, Block.planks.blockID, 0, 1, 1, 0, structureBoundingBox3);
		this.func_35018_a(world1, Block.planks.blockID, 0, 1, 2, 0, structureBoundingBox3);
		this.func_35018_a(world1, Block.planks.blockID, 0, 1, 3, 0, structureBoundingBox3);
		this.func_35018_a(world1, Block.planks.blockID, 0, 2, 3, 0, structureBoundingBox3);
		this.func_35018_a(world1, Block.planks.blockID, 0, 3, 3, 0, structureBoundingBox3);
		this.func_35018_a(world1, Block.planks.blockID, 0, 3, 2, 0, structureBoundingBox3);
		this.func_35018_a(world1, Block.planks.blockID, 0, 3, 1, 0, structureBoundingBox3);
		if(this.func_35007_a(world1, 2, 0, -1, structureBoundingBox3) == 0 && this.func_35007_a(world1, 2, -1, -1, structureBoundingBox3) != 0) {
			this.func_35018_a(world1, Block.stairCompactCobblestone.blockID, this.func_35009_c(Block.stairCompactCobblestone.blockID, 3), 2, 0, -1, structureBoundingBox3);
		}

		this.func_35011_a(world1, structureBoundingBox3, 1, 1, 1, 3, 3, 3, 0, 0, false);
		if(this.field_35083_b) {
			this.func_35018_a(world1, Block.fence.blockID, 0, 0, 5, 0, structureBoundingBox3);
			this.func_35018_a(world1, Block.fence.blockID, 0, 1, 5, 0, structureBoundingBox3);
			this.func_35018_a(world1, Block.fence.blockID, 0, 2, 5, 0, structureBoundingBox3);
			this.func_35018_a(world1, Block.fence.blockID, 0, 3, 5, 0, structureBoundingBox3);
			this.func_35018_a(world1, Block.fence.blockID, 0, 4, 5, 0, structureBoundingBox3);
			this.func_35018_a(world1, Block.fence.blockID, 0, 0, 5, 4, structureBoundingBox3);
			this.func_35018_a(world1, Block.fence.blockID, 0, 1, 5, 4, structureBoundingBox3);
			this.func_35018_a(world1, Block.fence.blockID, 0, 2, 5, 4, structureBoundingBox3);
			this.func_35018_a(world1, Block.fence.blockID, 0, 3, 5, 4, structureBoundingBox3);
			this.func_35018_a(world1, Block.fence.blockID, 0, 4, 5, 4, structureBoundingBox3);
			this.func_35018_a(world1, Block.fence.blockID, 0, 4, 5, 1, structureBoundingBox3);
			this.func_35018_a(world1, Block.fence.blockID, 0, 4, 5, 2, structureBoundingBox3);
			this.func_35018_a(world1, Block.fence.blockID, 0, 4, 5, 3, structureBoundingBox3);
			this.func_35018_a(world1, Block.fence.blockID, 0, 0, 5, 1, structureBoundingBox3);
			this.func_35018_a(world1, Block.fence.blockID, 0, 0, 5, 2, structureBoundingBox3);
			this.func_35018_a(world1, Block.fence.blockID, 0, 0, 5, 3, structureBoundingBox3);
		}

		int i4;
		if(this.field_35083_b) {
			i4 = this.func_35009_c(Block.ladder.blockID, 3);
			this.func_35018_a(world1, Block.ladder.blockID, i4, 3, 1, 3, structureBoundingBox3);
			this.func_35018_a(world1, Block.ladder.blockID, i4, 3, 2, 3, structureBoundingBox3);
			this.func_35018_a(world1, Block.ladder.blockID, i4, 3, 3, 3, structureBoundingBox3);
			this.func_35018_a(world1, Block.ladder.blockID, i4, 3, 4, 3, structureBoundingBox3);
		}

		this.func_35018_a(world1, Block.torchWood.blockID, 0, 2, 3, 1, structureBoundingBox3);

		for(i4 = 0; i4 < 5; ++i4) {
			for(int i5 = 0; i5 < 5; ++i5) {
				this.func_35016_b(world1, i5, 6, i4, structureBoundingBox3);
				this.func_35005_b(world1, Block.cobblestone.blockID, 0, i5, -1, i4, structureBoundingBox3);
			}
		}

		return true;
	}
}
