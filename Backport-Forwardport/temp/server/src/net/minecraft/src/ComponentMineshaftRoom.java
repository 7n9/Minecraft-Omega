package net.minecraft.src;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ComponentMineshaftRoom extends StructureComponent {
	private LinkedList field_35356_a = new LinkedList();

	public ComponentMineshaftRoom(int i1, Random random2, int i3, int i4) {
		super(i1);
		this.field_35316_g = new StructureBoundingBox(i3, 50, i4, i3 + 7 + random2.nextInt(6), 54 + random2.nextInt(6), i4 + 7 + random2.nextInt(6));
	}

	public void func_35308_a(StructureComponent structureComponent1, List list2, Random random3) {
		int i4 = this.func_35305_c();
		int i6 = this.field_35316_g.func_35668_c() - 3 - 1;
		if(i6 <= 0) {
			i6 = 1;
		}

		int i5;
		StructureComponent structureComponent7;
		StructureBoundingBox structureBoundingBox8;
		for(i5 = 0; i5 < this.field_35316_g.func_35669_b(); i5 += 4) {
			i5 += random3.nextInt(this.field_35316_g.func_35669_b());
			if(i5 + 3 > this.field_35316_g.func_35669_b()) {
				break;
			}

			structureComponent7 = StructureMineshaftPieces.func_35433_a(structureComponent1, list2, random3, this.field_35316_g.field_35678_a + i5, this.field_35316_g.field_35676_b + random3.nextInt(i6) + 1, this.field_35316_g.field_35677_c - 1, 2, i4);
			if(structureComponent7 != null) {
				structureBoundingBox8 = structureComponent7.func_35313_b();
				this.field_35356_a.add(new StructureBoundingBox(structureBoundingBox8.field_35678_a, structureBoundingBox8.field_35676_b, this.field_35316_g.field_35677_c, structureBoundingBox8.field_35674_d, structureBoundingBox8.field_35675_e, this.field_35316_g.field_35677_c + 1));
			}
		}

		for(i5 = 0; i5 < this.field_35316_g.func_35669_b(); i5 += 4) {
			i5 += random3.nextInt(this.field_35316_g.func_35669_b());
			if(i5 + 3 > this.field_35316_g.func_35669_b()) {
				break;
			}

			structureComponent7 = StructureMineshaftPieces.func_35433_a(structureComponent1, list2, random3, this.field_35316_g.field_35678_a + i5, this.field_35316_g.field_35676_b + random3.nextInt(i6) + 1, this.field_35316_g.field_35673_f + 1, 0, i4);
			if(structureComponent7 != null) {
				structureBoundingBox8 = structureComponent7.func_35313_b();
				this.field_35356_a.add(new StructureBoundingBox(structureBoundingBox8.field_35678_a, structureBoundingBox8.field_35676_b, this.field_35316_g.field_35673_f - 1, structureBoundingBox8.field_35674_d, structureBoundingBox8.field_35675_e, this.field_35316_g.field_35673_f));
			}
		}

		for(i5 = 0; i5 < this.field_35316_g.func_35665_d(); i5 += 4) {
			i5 += random3.nextInt(this.field_35316_g.func_35665_d());
			if(i5 + 3 > this.field_35316_g.func_35665_d()) {
				break;
			}

			structureComponent7 = StructureMineshaftPieces.func_35433_a(structureComponent1, list2, random3, this.field_35316_g.field_35678_a - 1, this.field_35316_g.field_35676_b + random3.nextInt(i6) + 1, this.field_35316_g.field_35677_c + i5, 1, i4);
			if(structureComponent7 != null) {
				structureBoundingBox8 = structureComponent7.func_35313_b();
				this.field_35356_a.add(new StructureBoundingBox(this.field_35316_g.field_35678_a, structureBoundingBox8.field_35676_b, structureBoundingBox8.field_35677_c, this.field_35316_g.field_35678_a + 1, structureBoundingBox8.field_35675_e, structureBoundingBox8.field_35673_f));
			}
		}

		for(i5 = 0; i5 < this.field_35316_g.func_35665_d(); i5 += 4) {
			i5 += random3.nextInt(this.field_35316_g.func_35665_d());
			if(i5 + 3 > this.field_35316_g.func_35665_d()) {
				break;
			}

			structureComponent7 = StructureMineshaftPieces.func_35433_a(structureComponent1, list2, random3, this.field_35316_g.field_35674_d + 1, this.field_35316_g.field_35676_b + random3.nextInt(i6) + 1, this.field_35316_g.field_35677_c + i5, 3, i4);
			if(structureComponent7 != null) {
				structureBoundingBox8 = structureComponent7.func_35313_b();
				this.field_35356_a.add(new StructureBoundingBox(this.field_35316_g.field_35674_d - 1, structureBoundingBox8.field_35676_b, structureBoundingBox8.field_35677_c, this.field_35316_g.field_35674_d, structureBoundingBox8.field_35675_e, structureBoundingBox8.field_35673_f));
			}
		}

	}

	public boolean func_35310_a(World world1, Random random2, StructureBoundingBox structureBoundingBox3) {
		if(this.func_35295_a(world1, structureBoundingBox3)) {
			return false;
		} else {
			this.func_35294_a(world1, structureBoundingBox3, this.field_35316_g.field_35678_a, this.field_35316_g.field_35676_b, this.field_35316_g.field_35677_c, this.field_35316_g.field_35674_d, this.field_35316_g.field_35676_b, this.field_35316_g.field_35673_f, Block.dirt.blockID, 0, true);
			this.func_35294_a(world1, structureBoundingBox3, this.field_35316_g.field_35678_a, this.field_35316_g.field_35676_b + 1, this.field_35316_g.field_35677_c, this.field_35316_g.field_35674_d, Math.min(this.field_35316_g.field_35676_b + 3, this.field_35316_g.field_35675_e), this.field_35316_g.field_35673_f, 0, 0, false);
			Iterator iterator4 = this.field_35356_a.iterator();

			while(iterator4.hasNext()) {
				StructureBoundingBox structureBoundingBox5 = (StructureBoundingBox)iterator4.next();
				this.func_35294_a(world1, structureBoundingBox3, structureBoundingBox5.field_35678_a, structureBoundingBox5.field_35675_e - 2, structureBoundingBox5.field_35677_c, structureBoundingBox5.field_35674_d, structureBoundingBox5.field_35675_e, structureBoundingBox5.field_35673_f, 0, 0, false);
			}

			this.func_35304_a(world1, structureBoundingBox3, this.field_35316_g.field_35678_a, this.field_35316_g.field_35676_b + 4, this.field_35316_g.field_35677_c, this.field_35316_g.field_35674_d, this.field_35316_g.field_35675_e, this.field_35316_g.field_35673_f, 0, false);
			return true;
		}
	}
}
