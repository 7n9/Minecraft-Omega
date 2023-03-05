package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class ComponentVillageWell extends ComponentVillage {
	private final boolean field_35104_a = true;
	private int field_35103_b = -1;

	public ComponentVillageWell(int i1, Random random2, int i3, int i4) {
		super(i1);
		this.field_35025_h = random2.nextInt(4);
		switch(this.field_35025_h) {
		case 0:
		case 2:
			this.field_35024_g = new StructureBoundingBox(i3, 64, i4, i3 + 6 - 1, 78, i4 + 6 - 1);
			break;
		default:
			this.field_35024_g = new StructureBoundingBox(i3, 64, i4, i3 + 6 - 1, 78, i4 + 6 - 1);
		}

	}

	public void func_35004_a(StructureComponent structureComponent1, List list2, Random random3) {
		StructureVillagePieces.func_35701_b((ComponentVillageStartPiece)structureComponent1, list2, random3, this.field_35024_g.field_35753_a - 1, this.field_35024_g.field_35750_e - 4, this.field_35024_g.field_35752_c + 1, 1, this.func_35012_c());
		StructureVillagePieces.func_35701_b((ComponentVillageStartPiece)structureComponent1, list2, random3, this.field_35024_g.field_35749_d + 1, this.field_35024_g.field_35750_e - 4, this.field_35024_g.field_35752_c + 1, 3, this.func_35012_c());
		StructureVillagePieces.func_35701_b((ComponentVillageStartPiece)structureComponent1, list2, random3, this.field_35024_g.field_35753_a + 1, this.field_35024_g.field_35750_e - 4, this.field_35024_g.field_35752_c - 1, 2, this.func_35012_c());
		StructureVillagePieces.func_35701_b((ComponentVillageStartPiece)structureComponent1, list2, random3, this.field_35024_g.field_35753_a + 1, this.field_35024_g.field_35750_e - 4, this.field_35024_g.field_35748_f + 1, 0, this.func_35012_c());
	}

	public boolean func_35023_a(World world1, Random random2, StructureBoundingBox structureBoundingBox3) {
		if(this.field_35103_b < 0) {
			this.field_35103_b = this.func_35075_b(world1, structureBoundingBox3);
			if(this.field_35103_b < 0) {
				return true;
			}

			this.field_35024_g.func_35745_a(0, this.field_35103_b - this.field_35024_g.field_35750_e + 3, 0);
		}

		if(this.field_35104_a) {
			;
		}

		this.func_35011_a(world1, structureBoundingBox3, 1, 0, 1, 4, 12, 4, Block.cobblestone.blockID, Block.waterMoving.blockID, false);
		this.func_35018_a(world1, 0, 0, 2, 12, 2, structureBoundingBox3);
		this.func_35018_a(world1, 0, 0, 3, 12, 2, structureBoundingBox3);
		this.func_35018_a(world1, 0, 0, 2, 12, 3, structureBoundingBox3);
		this.func_35018_a(world1, 0, 0, 3, 12, 3, structureBoundingBox3);
		this.func_35018_a(world1, Block.fence.blockID, 0, 1, 13, 1, structureBoundingBox3);
		this.func_35018_a(world1, Block.fence.blockID, 0, 1, 14, 1, structureBoundingBox3);
		this.func_35018_a(world1, Block.fence.blockID, 0, 4, 13, 1, structureBoundingBox3);
		this.func_35018_a(world1, Block.fence.blockID, 0, 4, 14, 1, structureBoundingBox3);
		this.func_35018_a(world1, Block.fence.blockID, 0, 1, 13, 4, structureBoundingBox3);
		this.func_35018_a(world1, Block.fence.blockID, 0, 1, 14, 4, structureBoundingBox3);
		this.func_35018_a(world1, Block.fence.blockID, 0, 4, 13, 4, structureBoundingBox3);
		this.func_35018_a(world1, Block.fence.blockID, 0, 4, 14, 4, structureBoundingBox3);
		this.func_35011_a(world1, structureBoundingBox3, 1, 15, 1, 4, 15, 4, Block.cobblestone.blockID, Block.cobblestone.blockID, false);

		for(int i4 = 0; i4 <= 5; ++i4) {
			for(int i5 = 0; i5 <= 5; ++i5) {
				if(i5 == 0 || i5 == 5 || i4 == 0 || i4 == 5) {
					this.func_35018_a(world1, Block.gravel.blockID, 0, i5, 11, i4, structureBoundingBox3);
					this.func_35016_b(world1, i5, 12, i4, structureBoundingBox3);
				}
			}
		}

		return true;
	}
}
