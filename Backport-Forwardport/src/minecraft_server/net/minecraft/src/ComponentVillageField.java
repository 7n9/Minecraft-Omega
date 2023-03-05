package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class ComponentVillageField extends ComponentVillage {
	private int field_35371_a = -1;

	public ComponentVillageField(int i1, Random random2, StructureBoundingBox structureBoundingBox3, int i4) {
		super(i1);
		this.field_35317_h = i4;
		this.field_35316_g = structureBoundingBox3;
	}

	public void func_35308_a(StructureComponent structureComponent1, List list2, Random random3) {
	}

	public static ComponentVillageField func_35370_a(List list0, Random random1, int i2, int i3, int i4, int i5, int i6) {
		StructureBoundingBox structureBoundingBox7 = StructureBoundingBox.func_35663_a(i2, i3, i4, 0, 0, 0, 13, 4, 9, i5);
		return func_35366_a(structureBoundingBox7) && StructureComponent.func_35312_a(list0, structureBoundingBox7) == null ? new ComponentVillageField(i6, random1, structureBoundingBox7, i5) : null;
	}

	public boolean func_35310_a(World world1, Random random2, StructureBoundingBox structureBoundingBox3) {
		if(this.field_35371_a < 0) {
			this.field_35371_a = this.func_35367_b(world1, structureBoundingBox3);
			if(this.field_35371_a < 0) {
				return true;
			}

			this.field_35316_g.func_35670_a(0, this.field_35371_a - this.field_35316_g.field_35675_e + 4 - 1, 0);
		}

		this.func_35294_a(world1, structureBoundingBox3, 0, 1, 0, 12, 4, 8, 0, 0, false);
		this.func_35294_a(world1, structureBoundingBox3, 1, 0, 1, 2, 0, 7, Block.tilledField.blockID, Block.tilledField.blockID, false);
		this.func_35294_a(world1, structureBoundingBox3, 4, 0, 1, 5, 0, 7, Block.tilledField.blockID, Block.tilledField.blockID, false);
		this.func_35294_a(world1, structureBoundingBox3, 7, 0, 1, 8, 0, 7, Block.tilledField.blockID, Block.tilledField.blockID, false);
		this.func_35294_a(world1, structureBoundingBox3, 10, 0, 1, 11, 0, 7, Block.tilledField.blockID, Block.tilledField.blockID, false);
		this.func_35294_a(world1, structureBoundingBox3, 0, 0, 0, 0, 0, 8, Block.wood.blockID, Block.wood.blockID, false);
		this.func_35294_a(world1, structureBoundingBox3, 6, 0, 0, 6, 0, 8, Block.wood.blockID, Block.wood.blockID, false);
		this.func_35294_a(world1, structureBoundingBox3, 12, 0, 0, 12, 0, 8, Block.wood.blockID, Block.wood.blockID, false);
		this.func_35294_a(world1, structureBoundingBox3, 1, 0, 0, 11, 0, 0, Block.wood.blockID, Block.wood.blockID, false);
		this.func_35294_a(world1, structureBoundingBox3, 1, 0, 8, 11, 0, 8, Block.wood.blockID, Block.wood.blockID, false);
		this.func_35294_a(world1, structureBoundingBox3, 3, 0, 1, 3, 0, 7, Block.waterMoving.blockID, Block.waterMoving.blockID, false);
		this.func_35294_a(world1, structureBoundingBox3, 9, 0, 1, 9, 0, 7, Block.waterMoving.blockID, Block.waterMoving.blockID, false);

		int i4;
		for(i4 = 1; i4 <= 7; ++i4) {
			this.func_35309_a(world1, Block.crops.blockID, MathHelper.func_35476_a(random2, 2, 7), 1, 1, i4, structureBoundingBox3);
			this.func_35309_a(world1, Block.crops.blockID, MathHelper.func_35476_a(random2, 2, 7), 2, 1, i4, structureBoundingBox3);
			this.func_35309_a(world1, Block.crops.blockID, MathHelper.func_35476_a(random2, 2, 7), 4, 1, i4, structureBoundingBox3);
			this.func_35309_a(world1, Block.crops.blockID, MathHelper.func_35476_a(random2, 2, 7), 5, 1, i4, structureBoundingBox3);
			this.func_35309_a(world1, Block.crops.blockID, MathHelper.func_35476_a(random2, 2, 7), 7, 1, i4, structureBoundingBox3);
			this.func_35309_a(world1, Block.crops.blockID, MathHelper.func_35476_a(random2, 2, 7), 8, 1, i4, structureBoundingBox3);
			this.func_35309_a(world1, Block.crops.blockID, MathHelper.func_35476_a(random2, 2, 7), 10, 1, i4, structureBoundingBox3);
			this.func_35309_a(world1, Block.crops.blockID, MathHelper.func_35476_a(random2, 2, 7), 11, 1, i4, structureBoundingBox3);
		}

		for(i4 = 0; i4 < 9; ++i4) {
			for(int i5 = 0; i5 < 13; ++i5) {
				this.func_35314_b(world1, i5, 4, i4, structureBoundingBox3);
				this.func_35303_b(world1, Block.dirt.blockID, 0, i5, -1, i4, structureBoundingBox3);
			}
		}

		return true;
	}
}
