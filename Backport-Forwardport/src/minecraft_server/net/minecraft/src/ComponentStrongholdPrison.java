package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class ComponentStrongholdPrison extends ComponentStronghold {
	protected final EnumDoor field_35333_a;

	public ComponentStrongholdPrison(int i1, Random random2, StructureBoundingBox structureBoundingBox3, int i4) {
		super(i1);
		this.field_35317_h = i4;
		this.field_35333_a = this.func_35322_a(random2);
		this.field_35316_g = structureBoundingBox3;
	}

	public void func_35308_a(StructureComponent structureComponent1, List list2, Random random3) {
		this.func_35324_a((ComponentStrongholdStairs2)structureComponent1, list2, random3, 1, 1);
	}

	public static ComponentStrongholdPrison func_35332_a(List list0, Random random1, int i2, int i3, int i4, int i5, int i6) {
		StructureBoundingBox structureBoundingBox7 = StructureBoundingBox.func_35663_a(i2, i3, i4, -1, -1, 0, 9, 5, 11, i5);
		return func_35319_a(structureBoundingBox7) && StructureComponent.func_35312_a(list0, structureBoundingBox7) == null ? new ComponentStrongholdPrison(i6, random1, structureBoundingBox7, i5) : null;
	}

	public boolean func_35310_a(World world1, Random random2, StructureBoundingBox structureBoundingBox3) {
		if(this.func_35295_a(world1, structureBoundingBox3)) {
			return false;
		} else {
			this.func_35307_a(world1, structureBoundingBox3, 0, 0, 0, 8, 4, 10, true, random2, StructureStrongholdPieces.func_35622_b());
			this.func_35323_a(world1, random2, structureBoundingBox3, this.field_35333_a, 1, 1, 0);
			this.func_35294_a(world1, structureBoundingBox3, 1, 1, 10, 3, 3, 10, 0, 0, false);
			this.func_35307_a(world1, structureBoundingBox3, 4, 1, 1, 4, 3, 1, false, random2, StructureStrongholdPieces.func_35622_b());
			this.func_35307_a(world1, structureBoundingBox3, 4, 1, 3, 4, 3, 3, false, random2, StructureStrongholdPieces.func_35622_b());
			this.func_35307_a(world1, structureBoundingBox3, 4, 1, 7, 4, 3, 7, false, random2, StructureStrongholdPieces.func_35622_b());
			this.func_35307_a(world1, structureBoundingBox3, 4, 1, 9, 4, 3, 9, false, random2, StructureStrongholdPieces.func_35622_b());
			this.func_35294_a(world1, structureBoundingBox3, 4, 1, 4, 4, 3, 6, Block.field_35055_bq.blockID, Block.field_35055_bq.blockID, false);
			this.func_35294_a(world1, structureBoundingBox3, 5, 1, 5, 7, 3, 5, Block.field_35055_bq.blockID, Block.field_35055_bq.blockID, false);
			this.func_35309_a(world1, Block.field_35055_bq.blockID, 0, 4, 3, 2, structureBoundingBox3);
			this.func_35309_a(world1, Block.field_35055_bq.blockID, 0, 4, 3, 8, structureBoundingBox3);
			this.func_35309_a(world1, Block.doorSteel.blockID, this.func_35301_c(Block.doorSteel.blockID, 3), 4, 1, 2, structureBoundingBox3);
			this.func_35309_a(world1, Block.doorSteel.blockID, this.func_35301_c(Block.doorSteel.blockID, 3) + 8, 4, 2, 2, structureBoundingBox3);
			this.func_35309_a(world1, Block.doorSteel.blockID, this.func_35301_c(Block.doorSteel.blockID, 3), 4, 1, 8, structureBoundingBox3);
			this.func_35309_a(world1, Block.doorSteel.blockID, this.func_35301_c(Block.doorSteel.blockID, 3) + 8, 4, 2, 8, structureBoundingBox3);
			return true;
		}
	}
}