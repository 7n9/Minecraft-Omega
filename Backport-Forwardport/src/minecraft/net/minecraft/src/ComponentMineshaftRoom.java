package net.minecraft.src;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ComponentMineshaftRoom extends StructureComponent {
	private LinkedList field_35065_a = new LinkedList();

	public ComponentMineshaftRoom(int i1, Random random2, int i3, int i4) {
		super(i1);
		this.field_35024_g = new StructureBoundingBox(i3, 50, i4, i3 + 7 + random2.nextInt(6), 54 + random2.nextInt(6), i4 + 7 + random2.nextInt(6));
	}

	public void func_35004_a(StructureComponent structureComponent1, List list2, Random random3) {
		int i4 = this.func_35012_c();
		int i6 = this.field_35024_g.func_35743_c() - 3 - 1;
		if(i6 <= 0) {
			i6 = 1;
		}

		int i5;
		StructureComponent structureComponent7;
		StructureBoundingBox structureBoundingBox8;
		for(i5 = 0; i5 < this.field_35024_g.func_35744_b(); i5 += 4) {
			i5 += random3.nextInt(this.field_35024_g.func_35744_b());
			if(i5 + 3 > this.field_35024_g.func_35744_b()) {
				break;
			}

			structureComponent7 = StructureMineshaftPieces.func_35585_a(structureComponent1, list2, random3, this.field_35024_g.field_35753_a + i5, this.field_35024_g.field_35751_b + random3.nextInt(i6) + 1, this.field_35024_g.field_35752_c - 1, 2, i4);
			if(structureComponent7 != null) {
				structureBoundingBox8 = structureComponent7.func_35021_b();
				this.field_35065_a.add(new StructureBoundingBox(structureBoundingBox8.field_35753_a, structureBoundingBox8.field_35751_b, this.field_35024_g.field_35752_c, structureBoundingBox8.field_35749_d, structureBoundingBox8.field_35750_e, this.field_35024_g.field_35752_c + 1));
			}
		}

		for(i5 = 0; i5 < this.field_35024_g.func_35744_b(); i5 += 4) {
			i5 += random3.nextInt(this.field_35024_g.func_35744_b());
			if(i5 + 3 > this.field_35024_g.func_35744_b()) {
				break;
			}

			structureComponent7 = StructureMineshaftPieces.func_35585_a(structureComponent1, list2, random3, this.field_35024_g.field_35753_a + i5, this.field_35024_g.field_35751_b + random3.nextInt(i6) + 1, this.field_35024_g.field_35748_f + 1, 0, i4);
			if(structureComponent7 != null) {
				structureBoundingBox8 = structureComponent7.func_35021_b();
				this.field_35065_a.add(new StructureBoundingBox(structureBoundingBox8.field_35753_a, structureBoundingBox8.field_35751_b, this.field_35024_g.field_35748_f - 1, structureBoundingBox8.field_35749_d, structureBoundingBox8.field_35750_e, this.field_35024_g.field_35748_f));
			}
		}

		for(i5 = 0; i5 < this.field_35024_g.func_35739_d(); i5 += 4) {
			i5 += random3.nextInt(this.field_35024_g.func_35739_d());
			if(i5 + 3 > this.field_35024_g.func_35739_d()) {
				break;
			}

			structureComponent7 = StructureMineshaftPieces.func_35585_a(structureComponent1, list2, random3, this.field_35024_g.field_35753_a - 1, this.field_35024_g.field_35751_b + random3.nextInt(i6) + 1, this.field_35024_g.field_35752_c + i5, 1, i4);
			if(structureComponent7 != null) {
				structureBoundingBox8 = structureComponent7.func_35021_b();
				this.field_35065_a.add(new StructureBoundingBox(this.field_35024_g.field_35753_a, structureBoundingBox8.field_35751_b, structureBoundingBox8.field_35752_c, this.field_35024_g.field_35753_a + 1, structureBoundingBox8.field_35750_e, structureBoundingBox8.field_35748_f));
			}
		}

		for(i5 = 0; i5 < this.field_35024_g.func_35739_d(); i5 += 4) {
			i5 += random3.nextInt(this.field_35024_g.func_35739_d());
			if(i5 + 3 > this.field_35024_g.func_35739_d()) {
				break;
			}

			structureComponent7 = StructureMineshaftPieces.func_35585_a(structureComponent1, list2, random3, this.field_35024_g.field_35749_d + 1, this.field_35024_g.field_35751_b + random3.nextInt(i6) + 1, this.field_35024_g.field_35752_c + i5, 3, i4);
			if(structureComponent7 != null) {
				structureBoundingBox8 = structureComponent7.func_35021_b();
				this.field_35065_a.add(new StructureBoundingBox(this.field_35024_g.field_35749_d - 1, structureBoundingBox8.field_35751_b, structureBoundingBox8.field_35752_c, this.field_35024_g.field_35749_d, structureBoundingBox8.field_35750_e, structureBoundingBox8.field_35748_f));
			}
		}

	}

	public boolean func_35023_a(World world1, Random random2, StructureBoundingBox structureBoundingBox3) {
		if(this.func_35013_a(world1, structureBoundingBox3)) {
			return false;
		} else {
			this.func_35011_a(world1, structureBoundingBox3, this.field_35024_g.field_35753_a, this.field_35024_g.field_35751_b, this.field_35024_g.field_35752_c, this.field_35024_g.field_35749_d, this.field_35024_g.field_35751_b, this.field_35024_g.field_35748_f, Block.dirt.blockID, 0, true);
			this.func_35011_a(world1, structureBoundingBox3, this.field_35024_g.field_35753_a, this.field_35024_g.field_35751_b + 1, this.field_35024_g.field_35752_c, this.field_35024_g.field_35749_d, Math.min(this.field_35024_g.field_35751_b + 3, this.field_35024_g.field_35750_e), this.field_35024_g.field_35748_f, 0, 0, false);
			Iterator iterator4 = this.field_35065_a.iterator();

			while(iterator4.hasNext()) {
				StructureBoundingBox structureBoundingBox5 = (StructureBoundingBox)iterator4.next();
				this.func_35011_a(world1, structureBoundingBox3, structureBoundingBox5.field_35753_a, structureBoundingBox5.field_35750_e - 2, structureBoundingBox5.field_35752_c, structureBoundingBox5.field_35749_d, structureBoundingBox5.field_35750_e, structureBoundingBox5.field_35748_f, 0, 0, false);
			}

			this.func_35015_a(world1, structureBoundingBox3, this.field_35024_g.field_35753_a, this.field_35024_g.field_35751_b + 4, this.field_35024_g.field_35752_c, this.field_35024_g.field_35749_d, this.field_35024_g.field_35750_e, this.field_35024_g.field_35748_f, 0, false);
			return true;
		}
	}
}
