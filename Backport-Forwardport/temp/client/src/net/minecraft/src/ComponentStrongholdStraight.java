package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class ComponentStrongholdStraight extends ComponentStronghold {
	private final EnumDoor field_35050_a;
	private final boolean field_35048_b;
	private final boolean field_35049_c;

	public ComponentStrongholdStraight(int i1, Random random2, StructureBoundingBox structureBoundingBox3, int i4) {
		super(i1);
		this.field_35025_h = i4;
		this.field_35050_a = this.func_35031_a(random2);
		this.field_35024_g = structureBoundingBox3;
		this.field_35048_b = random2.nextInt(2) == 0;
		this.field_35049_c = random2.nextInt(2) == 0;
	}

	public void func_35004_a(StructureComponent structureComponent1, List list2, Random random3) {
		this.func_35028_a((ComponentStrongholdStairs2)structureComponent1, list2, random3, 1, 1);
		if(this.field_35048_b) {
			this.func_35032_b((ComponentStrongholdStairs2)structureComponent1, list2, random3, 1, 2);
		}

		if(this.field_35049_c) {
			this.func_35029_c((ComponentStrongholdStairs2)structureComponent1, list2, random3, 1, 2);
		}

	}

	public static ComponentStrongholdStraight func_35047_a(List list0, Random random1, int i2, int i3, int i4, int i5, int i6) {
		StructureBoundingBox structureBoundingBox7 = StructureBoundingBox.func_35747_a(i2, i3, i4, -1, -1, 0, 5, 5, 7, i5);
		return func_35030_a(structureBoundingBox7) && StructureComponent.func_35020_a(list0, structureBoundingBox7) == null ? new ComponentStrongholdStraight(i6, random1, structureBoundingBox7, i5) : null;
	}

	public boolean func_35023_a(World world1, Random random2, StructureBoundingBox structureBoundingBox3) {
		if(this.func_35013_a(world1, structureBoundingBox3)) {
			return false;
		} else {
			this.func_35022_a(world1, structureBoundingBox3, 0, 0, 0, 4, 4, 6, true, random2, StructureStrongholdPieces.func_35852_b());
			this.func_35033_a(world1, random2, structureBoundingBox3, this.field_35050_a, 1, 1, 0);
			this.func_35033_a(world1, random2, structureBoundingBox3, EnumDoor.OPENING, 1, 1, 6);
			this.func_35014_a(world1, structureBoundingBox3, random2, 0.1F, 1, 2, 1, Block.torchWood.blockID, 0);
			this.func_35014_a(world1, structureBoundingBox3, random2, 0.1F, 3, 2, 1, Block.torchWood.blockID, 0);
			this.func_35014_a(world1, structureBoundingBox3, random2, 0.1F, 1, 2, 5, Block.torchWood.blockID, 0);
			this.func_35014_a(world1, structureBoundingBox3, random2, 0.1F, 3, 2, 5, Block.torchWood.blockID, 0);
			if(this.field_35048_b) {
				this.func_35011_a(world1, structureBoundingBox3, 0, 1, 2, 0, 3, 4, 0, 0, false);
			}

			if(this.field_35049_c) {
				this.func_35011_a(world1, structureBoundingBox3, 4, 1, 2, 4, 3, 4, 0, 0, false);
			}

			return true;
		}
	}
}
