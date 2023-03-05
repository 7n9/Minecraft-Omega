package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class ComponentStrongholdPrison extends ComponentStronghold {
	protected final EnumDoor field_35064_a;

	public ComponentStrongholdPrison(int i1, Random random2, StructureBoundingBox structureBoundingBox3, int i4) {
		super(i1);
		this.field_35025_h = i4;
		this.field_35064_a = this.func_35031_a(random2);
		this.field_35024_g = structureBoundingBox3;
	}

	public void func_35004_a(StructureComponent structureComponent1, List list2, Random random3) {
		this.func_35028_a((ComponentStrongholdStairs2)structureComponent1, list2, random3, 1, 1);
	}

	public static ComponentStrongholdPrison func_35063_a(List list0, Random random1, int i2, int i3, int i4, int i5, int i6) {
		StructureBoundingBox structureBoundingBox7 = StructureBoundingBox.func_35747_a(i2, i3, i4, -1, -1, 0, 9, 5, 11, i5);
		return func_35030_a(structureBoundingBox7) && StructureComponent.func_35020_a(list0, structureBoundingBox7) == null ? new ComponentStrongholdPrison(i6, random1, structureBoundingBox7, i5) : null;
	}

	public boolean func_35023_a(World world1, Random random2, StructureBoundingBox structureBoundingBox3) {
		if(this.func_35013_a(world1, structureBoundingBox3)) {
			return false;
		} else {
			this.func_35022_a(world1, structureBoundingBox3, 0, 0, 0, 8, 4, 10, true, random2, StructureStrongholdPieces.func_35852_b());
			this.func_35033_a(world1, random2, structureBoundingBox3, this.field_35064_a, 1, 1, 0);
			this.func_35011_a(world1, structureBoundingBox3, 1, 1, 10, 3, 3, 10, 0, 0, false);
			this.func_35022_a(world1, structureBoundingBox3, 4, 1, 1, 4, 3, 1, false, random2, StructureStrongholdPieces.func_35852_b());
			this.func_35022_a(world1, structureBoundingBox3, 4, 1, 3, 4, 3, 3, false, random2, StructureStrongholdPieces.func_35852_b());
			this.func_35022_a(world1, structureBoundingBox3, 4, 1, 7, 4, 3, 7, false, random2, StructureStrongholdPieces.func_35852_b());
			this.func_35022_a(world1, structureBoundingBox3, 4, 1, 9, 4, 3, 9, false, random2, StructureStrongholdPieces.func_35852_b());
			this.func_35011_a(world1, structureBoundingBox3, 4, 1, 4, 4, 3, 6, Block.field_35288_bq.blockID, Block.field_35288_bq.blockID, false);
			this.func_35011_a(world1, structureBoundingBox3, 5, 1, 5, 7, 3, 5, Block.field_35288_bq.blockID, Block.field_35288_bq.blockID, false);
			this.func_35018_a(world1, Block.field_35288_bq.blockID, 0, 4, 3, 2, structureBoundingBox3);
			this.func_35018_a(world1, Block.field_35288_bq.blockID, 0, 4, 3, 8, structureBoundingBox3);
			this.func_35018_a(world1, Block.doorSteel.blockID, this.func_35009_c(Block.doorSteel.blockID, 3), 4, 1, 2, structureBoundingBox3);
			this.func_35018_a(world1, Block.doorSteel.blockID, this.func_35009_c(Block.doorSteel.blockID, 3) + 8, 4, 2, 2, structureBoundingBox3);
			this.func_35018_a(world1, Block.doorSteel.blockID, this.func_35009_c(Block.doorSteel.blockID, 3), 4, 1, 8, structureBoundingBox3);
			this.func_35018_a(world1, Block.doorSteel.blockID, this.func_35009_c(Block.doorSteel.blockID, 3) + 8, 4, 2, 8, structureBoundingBox3);
			return true;
		}
	}
}
