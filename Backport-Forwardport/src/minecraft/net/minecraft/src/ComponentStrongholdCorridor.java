package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class ComponentStrongholdCorridor extends ComponentStronghold {
	private final int field_35052_a;

	public ComponentStrongholdCorridor(int i1, Random random2, StructureBoundingBox structureBoundingBox3, int i4) {
		super(i1);
		this.field_35025_h = i4;
		this.field_35024_g = structureBoundingBox3;
		this.field_35052_a = i4 != 2 && i4 != 0 ? structureBoundingBox3.func_35744_b() : structureBoundingBox3.func_35739_d();
	}

	public void func_35004_a(StructureComponent structureComponent1, List list2, Random random3) {
	}

	public static StructureBoundingBox func_35051_a(List list0, Random random1, int i2, int i3, int i4, int i5) {
		StructureBoundingBox structureBoundingBox7 = StructureBoundingBox.func_35747_a(i2, i3, i4, -1, -1, 0, 5, 5, 4, i5);
		StructureComponent structureComponent8 = StructureComponent.func_35020_a(list0, structureBoundingBox7);
		if(structureComponent8 == null) {
			return null;
		} else {
			if(structureComponent8.func_35021_b().field_35751_b == structureBoundingBox7.field_35751_b) {
				for(int i9 = 3; i9 >= 1; --i9) {
					structureBoundingBox7 = StructureBoundingBox.func_35747_a(i2, i3, i4, -1, -1, 0, 5, 5, i9 - 1, i5);
					if(!structureComponent8.func_35021_b().func_35740_a(structureBoundingBox7)) {
						return StructureBoundingBox.func_35747_a(i2, i3, i4, -1, -1, 0, 5, 5, i9, i5);
					}
				}
			}

			return null;
		}
	}

	public boolean func_35023_a(World world1, Random random2, StructureBoundingBox structureBoundingBox3) {
		if(this.func_35013_a(world1, structureBoundingBox3)) {
			return false;
		} else {
			for(int i4 = 0; i4 < this.field_35052_a; ++i4) {
				this.func_35018_a(world1, Block.field_35285_bn.blockID, 0, 0, 0, i4, structureBoundingBox3);
				this.func_35018_a(world1, Block.field_35285_bn.blockID, 0, 1, 0, i4, structureBoundingBox3);
				this.func_35018_a(world1, Block.field_35285_bn.blockID, 0, 2, 0, i4, structureBoundingBox3);
				this.func_35018_a(world1, Block.field_35285_bn.blockID, 0, 3, 0, i4, structureBoundingBox3);
				this.func_35018_a(world1, Block.field_35285_bn.blockID, 0, 4, 0, i4, structureBoundingBox3);

				for(int i5 = 1; i5 <= 3; ++i5) {
					this.func_35018_a(world1, Block.field_35285_bn.blockID, 0, 0, i5, i4, structureBoundingBox3);
					this.func_35018_a(world1, 0, 0, 1, i5, i4, structureBoundingBox3);
					this.func_35018_a(world1, 0, 0, 2, i5, i4, structureBoundingBox3);
					this.func_35018_a(world1, 0, 0, 3, i5, i4, structureBoundingBox3);
					this.func_35018_a(world1, Block.field_35285_bn.blockID, 0, 4, i5, i4, structureBoundingBox3);
				}

				this.func_35018_a(world1, Block.field_35285_bn.blockID, 0, 0, 4, i4, structureBoundingBox3);
				this.func_35018_a(world1, Block.field_35285_bn.blockID, 0, 1, 4, i4, structureBoundingBox3);
				this.func_35018_a(world1, Block.field_35285_bn.blockID, 0, 2, 4, i4, structureBoundingBox3);
				this.func_35018_a(world1, Block.field_35285_bn.blockID, 0, 3, 4, i4, structureBoundingBox3);
				this.func_35018_a(world1, Block.field_35285_bn.blockID, 0, 4, 4, i4, structureBoundingBox3);
			}

			return true;
		}
	}
}
