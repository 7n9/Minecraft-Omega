package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class ComponentStrongholdStairs extends ComponentStronghold {
	private final boolean field_35327_a;
	private final EnumDoor field_35326_b;

	public ComponentStrongholdStairs(int i1, Random random2, int i3, int i4) {
		super(i1);
		this.field_35327_a = true;
		this.field_35317_h = random2.nextInt(4);
		this.field_35326_b = EnumDoor.OPENING;
		switch(this.field_35317_h) {
		case 0:
		case 2:
			this.field_35316_g = new StructureBoundingBox(i3, 64, i4, i3 + 5 - 1, 74, i4 + 5 - 1);
			break;
		default:
			this.field_35316_g = new StructureBoundingBox(i3, 64, i4, i3 + 5 - 1, 74, i4 + 5 - 1);
		}

	}

	public ComponentStrongholdStairs(int i1, Random random2, StructureBoundingBox structureBoundingBox3, int i4) {
		super(i1);
		this.field_35327_a = false;
		this.field_35317_h = i4;
		this.field_35326_b = this.func_35322_a(random2);
		this.field_35316_g = structureBoundingBox3;
	}

	public void func_35308_a(StructureComponent structureComponent1, List list2, Random random3) {
		this.func_35324_a((ComponentStrongholdStairs2)structureComponent1, list2, random3, 1, 1);
	}

	public static ComponentStrongholdStairs func_35325_a(List list0, Random random1, int i2, int i3, int i4, int i5, int i6) {
		StructureBoundingBox structureBoundingBox7 = StructureBoundingBox.func_35663_a(i2, i3, i4, -1, -7, 0, 5, 11, 5, i5);
		return func_35319_a(structureBoundingBox7) && StructureComponent.func_35312_a(list0, structureBoundingBox7) == null ? new ComponentStrongholdStairs(i6, random1, structureBoundingBox7, i5) : null;
	}

	public boolean func_35310_a(World world1, Random random2, StructureBoundingBox structureBoundingBox3) {
		if(this.func_35295_a(world1, structureBoundingBox3)) {
			return false;
		} else {
			if(this.field_35327_a) {
				;
			}

			this.func_35307_a(world1, structureBoundingBox3, 0, 0, 0, 4, 10, 4, true, random2, StructureStrongholdPieces.func_35622_b());
			this.func_35323_a(world1, random2, structureBoundingBox3, this.field_35326_b, 1, 7, 0);
			this.func_35323_a(world1, random2, structureBoundingBox3, EnumDoor.OPENING, 1, 1, 4);
			this.func_35309_a(world1, Block.field_35052_bn.blockID, 0, 2, 6, 1, structureBoundingBox3);
			this.func_35309_a(world1, Block.field_35052_bn.blockID, 0, 1, 5, 1, structureBoundingBox3);
			this.func_35309_a(world1, Block.stairSingle.blockID, 0, 1, 6, 1, structureBoundingBox3);
			this.func_35309_a(world1, Block.field_35052_bn.blockID, 0, 1, 5, 2, structureBoundingBox3);
			this.func_35309_a(world1, Block.field_35052_bn.blockID, 0, 1, 4, 3, structureBoundingBox3);
			this.func_35309_a(world1, Block.stairSingle.blockID, 0, 1, 5, 3, structureBoundingBox3);
			this.func_35309_a(world1, Block.field_35052_bn.blockID, 0, 2, 4, 3, structureBoundingBox3);
			this.func_35309_a(world1, Block.field_35052_bn.blockID, 0, 3, 3, 3, structureBoundingBox3);
			this.func_35309_a(world1, Block.stairSingle.blockID, 0, 3, 4, 3, structureBoundingBox3);
			this.func_35309_a(world1, Block.field_35052_bn.blockID, 0, 3, 3, 2, structureBoundingBox3);
			this.func_35309_a(world1, Block.field_35052_bn.blockID, 0, 3, 2, 1, structureBoundingBox3);
			this.func_35309_a(world1, Block.stairSingle.blockID, 0, 3, 3, 1, structureBoundingBox3);
			this.func_35309_a(world1, Block.field_35052_bn.blockID, 0, 2, 2, 1, structureBoundingBox3);
			this.func_35309_a(world1, Block.field_35052_bn.blockID, 0, 1, 1, 1, structureBoundingBox3);
			this.func_35309_a(world1, Block.stairSingle.blockID, 0, 1, 2, 1, structureBoundingBox3);
			this.func_35309_a(world1, Block.field_35052_bn.blockID, 0, 1, 1, 2, structureBoundingBox3);
			this.func_35309_a(world1, Block.stairSingle.blockID, 0, 1, 1, 3, structureBoundingBox3);
			return true;
		}
	}
}
