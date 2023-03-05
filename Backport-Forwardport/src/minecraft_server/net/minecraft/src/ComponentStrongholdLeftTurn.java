package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class ComponentStrongholdLeftTurn extends ComponentStronghold {
	protected final EnumDoor field_35331_a;

	public ComponentStrongholdLeftTurn(int i1, Random random2, StructureBoundingBox structureBoundingBox3, int i4) {
		super(i1);
		this.field_35317_h = i4;
		this.field_35331_a = this.func_35322_a(random2);
		this.field_35316_g = structureBoundingBox3;
	}

	public void func_35308_a(StructureComponent structureComponent1, List list2, Random random3) {
		if(this.field_35317_h != 2 && this.field_35317_h != 3) {
			this.func_35320_c((ComponentStrongholdStairs2)structureComponent1, list2, random3, 1, 1);
		} else {
			this.func_35321_b((ComponentStrongholdStairs2)structureComponent1, list2, random3, 1, 1);
		}

	}

	public static ComponentStrongholdLeftTurn func_35330_a(List list0, Random random1, int i2, int i3, int i4, int i5, int i6) {
		StructureBoundingBox structureBoundingBox7 = StructureBoundingBox.func_35663_a(i2, i3, i4, -1, -1, 0, 5, 5, 5, i5);
		return func_35319_a(structureBoundingBox7) && StructureComponent.func_35312_a(list0, structureBoundingBox7) == null ? new ComponentStrongholdLeftTurn(i6, random1, structureBoundingBox7, i5) : null;
	}

	public boolean func_35310_a(World world1, Random random2, StructureBoundingBox structureBoundingBox3) {
		if(this.func_35295_a(world1, structureBoundingBox3)) {
			return false;
		} else {
			this.func_35307_a(world1, structureBoundingBox3, 0, 0, 0, 4, 4, 4, true, random2, StructureStrongholdPieces.func_35622_b());
			this.func_35323_a(world1, random2, structureBoundingBox3, this.field_35331_a, 1, 1, 0);
			if(this.field_35317_h != 2 && this.field_35317_h != 3) {
				this.func_35294_a(world1, structureBoundingBox3, 4, 1, 1, 4, 3, 3, 0, 0, false);
			} else {
				this.func_35294_a(world1, structureBoundingBox3, 0, 1, 1, 0, 3, 3, 0, 0, false);
			}

			return true;
		}
	}
}
