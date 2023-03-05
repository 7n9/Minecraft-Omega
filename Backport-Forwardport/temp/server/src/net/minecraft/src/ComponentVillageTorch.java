package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class ComponentVillageTorch extends ComponentVillage {
	private int field_35383_a = -1;

	public ComponentVillageTorch(int i1, Random random2, StructureBoundingBox structureBoundingBox3, int i4) {
		super(i1);
		this.field_35317_h = i4;
		this.field_35316_g = structureBoundingBox3;
	}

	public void func_35308_a(StructureComponent structureComponent1, List list2, Random random3) {
	}

	public static StructureBoundingBox func_35382_a(List list0, Random random1, int i2, int i3, int i4, int i5) {
		StructureBoundingBox structureBoundingBox6 = StructureBoundingBox.func_35663_a(i2, i3, i4, 0, 0, 0, 3, 4, 2, i5);
		return StructureComponent.func_35312_a(list0, structureBoundingBox6) != null ? null : structureBoundingBox6;
	}

	public boolean func_35310_a(World world1, Random random2, StructureBoundingBox structureBoundingBox3) {
		if(this.field_35383_a < 0) {
			this.field_35383_a = this.func_35367_b(world1, structureBoundingBox3);
			if(this.field_35383_a < 0) {
				return true;
			}

			this.field_35316_g.func_35670_a(0, this.field_35383_a - this.field_35316_g.field_35675_e + 4 - 1, 0);
		}

		this.func_35294_a(world1, structureBoundingBox3, 0, 0, 0, 2, 3, 1, 0, 0, false);
		this.func_35309_a(world1, Block.fence.blockID, 0, 1, 0, 0, structureBoundingBox3);
		this.func_35309_a(world1, Block.fence.blockID, 0, 1, 1, 0, structureBoundingBox3);
		this.func_35309_a(world1, Block.fence.blockID, 0, 1, 2, 0, structureBoundingBox3);
		this.func_35309_a(world1, Block.cloth.blockID, 15, 1, 3, 0, structureBoundingBox3);
		this.func_35309_a(world1, Block.torchWood.blockID, 15, 0, 3, 0, structureBoundingBox3);
		this.func_35309_a(world1, Block.torchWood.blockID, 15, 1, 3, 1, structureBoundingBox3);
		this.func_35309_a(world1, Block.torchWood.blockID, 15, 2, 3, 0, structureBoundingBox3);
		this.func_35309_a(world1, Block.torchWood.blockID, 15, 1, 3, -1, structureBoundingBox3);
		return true;
	}
}
