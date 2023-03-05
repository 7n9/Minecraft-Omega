package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class ComponentStrongholdStairsStraight extends ComponentStronghold {
	private final EnumDoor field_35054_a;

	public ComponentStrongholdStairsStraight(int i1, Random random2, StructureBoundingBox structureBoundingBox3, int i4) {
		super(i1);
		this.field_35025_h = i4;
		this.field_35054_a = this.func_35031_a(random2);
		this.field_35024_g = structureBoundingBox3;
	}

	public void func_35004_a(StructureComponent structureComponent1, List list2, Random random3) {
		this.func_35028_a((ComponentStrongholdStairs2)structureComponent1, list2, random3, 1, 1);
	}

	public static ComponentStrongholdStairsStraight func_35053_a(List list0, Random random1, int i2, int i3, int i4, int i5, int i6) {
		StructureBoundingBox structureBoundingBox7 = StructureBoundingBox.func_35747_a(i2, i3, i4, -1, -7, 0, 5, 11, 8, i5);
		return func_35030_a(structureBoundingBox7) && StructureComponent.func_35020_a(list0, structureBoundingBox7) == null ? new ComponentStrongholdStairsStraight(i6, random1, structureBoundingBox7, i5) : null;
	}

	public boolean func_35023_a(World world1, Random random2, StructureBoundingBox structureBoundingBox3) {
		if(this.func_35013_a(world1, structureBoundingBox3)) {
			return false;
		} else {
			this.func_35022_a(world1, structureBoundingBox3, 0, 0, 0, 4, 10, 7, true, random2, StructureStrongholdPieces.func_35852_b());
			this.func_35033_a(world1, random2, structureBoundingBox3, this.field_35054_a, 1, 7, 0);
			this.func_35033_a(world1, random2, structureBoundingBox3, EnumDoor.OPENING, 1, 1, 7);
			int i4 = this.func_35009_c(Block.stairCompactCobblestone.blockID, 2);

			for(int i5 = 0; i5 < 6; ++i5) {
				this.func_35018_a(world1, Block.stairCompactCobblestone.blockID, i4, 1, 6 - i5, 1 + i5, structureBoundingBox3);
				this.func_35018_a(world1, Block.stairCompactCobblestone.blockID, i4, 2, 6 - i5, 1 + i5, structureBoundingBox3);
				this.func_35018_a(world1, Block.stairCompactCobblestone.blockID, i4, 3, 6 - i5, 1 + i5, structureBoundingBox3);
				if(i5 < 5) {
					this.func_35018_a(world1, Block.field_35285_bn.blockID, 0, 1, 5 - i5, 1 + i5, structureBoundingBox3);
					this.func_35018_a(world1, Block.field_35285_bn.blockID, 0, 2, 5 - i5, 1 + i5, structureBoundingBox3);
					this.func_35018_a(world1, Block.field_35285_bn.blockID, 0, 3, 5 - i5, 1 + i5, structureBoundingBox3);
				}
			}

			return true;
		}
	}
}
