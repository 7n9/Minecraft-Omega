package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class ComponentStrongholdCrossing extends ComponentStronghold {
	protected final EnumDoor field_35044_a;
	private boolean field_35042_b;
	private boolean field_35043_c;
	private boolean field_35040_d;
	private boolean field_35041_e;

	public ComponentStrongholdCrossing(int i1, Random random2, StructureBoundingBox structureBoundingBox3, int i4) {
		super(i1);
		this.field_35025_h = i4;
		this.field_35044_a = this.func_35031_a(random2);
		this.field_35024_g = structureBoundingBox3;
		this.field_35042_b = random2.nextBoolean();
		this.field_35043_c = random2.nextBoolean();
		this.field_35040_d = random2.nextBoolean();
		this.field_35041_e = random2.nextBoolean();
	}

	public void func_35004_a(StructureComponent structureComponent1, List list2, Random random3) {
		this.func_35028_a((ComponentStrongholdStairs2)structureComponent1, list2, random3, 5, 1);
		if(this.field_35042_b) {
			this.func_35032_b((ComponentStrongholdStairs2)structureComponent1, list2, random3, 3, 1);
		}

		if(this.field_35043_c) {
			this.func_35032_b((ComponentStrongholdStairs2)structureComponent1, list2, random3, 5, 7);
		}

		if(this.field_35040_d) {
			this.func_35029_c((ComponentStrongholdStairs2)structureComponent1, list2, random3, 3, 1);
		}

		if(this.field_35041_e) {
			this.func_35029_c((ComponentStrongholdStairs2)structureComponent1, list2, random3, 5, 7);
		}

	}

	public static ComponentStrongholdCrossing func_35039_a(List list0, Random random1, int i2, int i3, int i4, int i5, int i6) {
		StructureBoundingBox structureBoundingBox7 = StructureBoundingBox.func_35747_a(i2, i3, i4, -4, -3, 0, 10, 9, 11, i5);
		return func_35030_a(structureBoundingBox7) && StructureComponent.func_35020_a(list0, structureBoundingBox7) == null ? new ComponentStrongholdCrossing(i6, random1, structureBoundingBox7, i5) : null;
	}

	public boolean func_35023_a(World world1, Random random2, StructureBoundingBox structureBoundingBox3) {
		if(this.func_35013_a(world1, structureBoundingBox3)) {
			return false;
		} else {
			this.func_35022_a(world1, structureBoundingBox3, 0, 0, 0, 9, 8, 10, true, random2, StructureStrongholdPieces.func_35852_b());
			this.func_35033_a(world1, random2, structureBoundingBox3, this.field_35044_a, 4, 3, 0);
			if(this.field_35042_b) {
				this.func_35011_a(world1, structureBoundingBox3, 0, 3, 1, 0, 5, 3, 0, 0, false);
			}

			if(this.field_35040_d) {
				this.func_35011_a(world1, structureBoundingBox3, 9, 3, 1, 9, 5, 3, 0, 0, false);
			}

			if(this.field_35043_c) {
				this.func_35011_a(world1, structureBoundingBox3, 0, 5, 7, 0, 7, 9, 0, 0, false);
			}

			if(this.field_35041_e) {
				this.func_35011_a(world1, structureBoundingBox3, 9, 5, 7, 9, 7, 9, 0, 0, false);
			}

			this.func_35011_a(world1, structureBoundingBox3, 5, 1, 10, 7, 3, 10, 0, 0, false);
			this.func_35022_a(world1, structureBoundingBox3, 1, 2, 1, 8, 2, 6, false, random2, StructureStrongholdPieces.func_35852_b());
			this.func_35022_a(world1, structureBoundingBox3, 4, 1, 5, 4, 4, 9, false, random2, StructureStrongholdPieces.func_35852_b());
			this.func_35022_a(world1, structureBoundingBox3, 8, 1, 5, 8, 4, 9, false, random2, StructureStrongholdPieces.func_35852_b());
			this.func_35022_a(world1, structureBoundingBox3, 1, 4, 7, 3, 4, 9, false, random2, StructureStrongholdPieces.func_35852_b());
			this.func_35022_a(world1, structureBoundingBox3, 1, 3, 5, 3, 3, 6, false, random2, StructureStrongholdPieces.func_35852_b());
			this.func_35011_a(world1, structureBoundingBox3, 1, 3, 4, 3, 3, 4, Block.stairSingle.blockID, Block.stairSingle.blockID, false);
			this.func_35011_a(world1, structureBoundingBox3, 1, 4, 6, 3, 4, 6, Block.stairSingle.blockID, Block.stairSingle.blockID, false);
			this.func_35022_a(world1, structureBoundingBox3, 5, 1, 7, 7, 1, 8, false, random2, StructureStrongholdPieces.func_35852_b());
			this.func_35011_a(world1, structureBoundingBox3, 5, 1, 9, 7, 1, 9, Block.stairSingle.blockID, Block.stairSingle.blockID, false);
			this.func_35011_a(world1, structureBoundingBox3, 5, 2, 7, 7, 2, 7, Block.stairSingle.blockID, Block.stairSingle.blockID, false);
			this.func_35011_a(world1, structureBoundingBox3, 4, 5, 7, 4, 5, 9, Block.stairSingle.blockID, Block.stairSingle.blockID, false);
			this.func_35011_a(world1, structureBoundingBox3, 8, 5, 7, 8, 5, 9, Block.stairSingle.blockID, Block.stairSingle.blockID, false);
			this.func_35011_a(world1, structureBoundingBox3, 5, 5, 7, 7, 5, 9, Block.stairDouble.blockID, Block.stairDouble.blockID, false);
			this.func_35018_a(world1, Block.torchWood.blockID, 0, 6, 5, 6, structureBoundingBox3);
			return true;
		}
	}
}