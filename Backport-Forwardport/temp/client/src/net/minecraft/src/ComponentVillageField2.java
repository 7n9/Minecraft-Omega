package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class ComponentVillageField2 extends ComponentVillage {
	private int field_35090_a = -1;

	public ComponentVillageField2(int i1, Random random2, StructureBoundingBox structureBoundingBox3, int i4) {
		super(i1);
		this.field_35025_h = i4;
		this.field_35024_g = structureBoundingBox3;
	}

	public void func_35004_a(StructureComponent structureComponent1, List list2, Random random3) {
	}

	public static ComponentVillageField2 func_35089_a(List list0, Random random1, int i2, int i3, int i4, int i5, int i6) {
		StructureBoundingBox structureBoundingBox7 = StructureBoundingBox.func_35747_a(i2, i3, i4, 0, 0, 0, 7, 4, 9, i5);
		return func_35074_a(structureBoundingBox7) && StructureComponent.func_35020_a(list0, structureBoundingBox7) == null ? new ComponentVillageField2(i6, random1, structureBoundingBox7, i5) : null;
	}

	public boolean func_35023_a(World world1, Random random2, StructureBoundingBox structureBoundingBox3) {
		if(this.field_35090_a < 0) {
			this.field_35090_a = this.func_35075_b(world1, structureBoundingBox3);
			if(this.field_35090_a < 0) {
				return true;
			}

			this.field_35024_g.func_35745_a(0, this.field_35090_a - this.field_35024_g.field_35750_e + 4 - 1, 0);
		}

		this.func_35011_a(world1, structureBoundingBox3, 0, 1, 0, 6, 4, 8, 0, 0, false);
		this.func_35011_a(world1, structureBoundingBox3, 1, 0, 1, 2, 0, 7, Block.tilledField.blockID, Block.tilledField.blockID, false);
		this.func_35011_a(world1, structureBoundingBox3, 4, 0, 1, 5, 0, 7, Block.tilledField.blockID, Block.tilledField.blockID, false);
		this.func_35011_a(world1, structureBoundingBox3, 0, 0, 0, 0, 0, 8, Block.wood.blockID, Block.wood.blockID, false);
		this.func_35011_a(world1, structureBoundingBox3, 6, 0, 0, 6, 0, 8, Block.wood.blockID, Block.wood.blockID, false);
		this.func_35011_a(world1, structureBoundingBox3, 1, 0, 0, 5, 0, 0, Block.wood.blockID, Block.wood.blockID, false);
		this.func_35011_a(world1, structureBoundingBox3, 1, 0, 8, 5, 0, 8, Block.wood.blockID, Block.wood.blockID, false);
		this.func_35011_a(world1, structureBoundingBox3, 3, 0, 1, 3, 0, 7, Block.waterMoving.blockID, Block.waterMoving.blockID, false);

		int i4;
		for(i4 = 1; i4 <= 7; ++i4) {
			this.func_35018_a(world1, Block.crops.blockID, MathHelper.func_35598_a(random2, 2, 7), 1, 1, i4, structureBoundingBox3);
			this.func_35018_a(world1, Block.crops.blockID, MathHelper.func_35598_a(random2, 2, 7), 2, 1, i4, structureBoundingBox3);
			this.func_35018_a(world1, Block.crops.blockID, MathHelper.func_35598_a(random2, 2, 7), 4, 1, i4, structureBoundingBox3);
			this.func_35018_a(world1, Block.crops.blockID, MathHelper.func_35598_a(random2, 2, 7), 5, 1, i4, structureBoundingBox3);
		}

		for(i4 = 0; i4 < 9; ++i4) {
			for(int i5 = 0; i5 < 7; ++i5) {
				this.func_35016_b(world1, i5, 4, i4, structureBoundingBox3);
				this.func_35005_b(world1, Block.dirt.blockID, 0, i5, -1, i4, structureBoundingBox3);
			}
		}

		return true;
	}
}
