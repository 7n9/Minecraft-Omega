package net.minecraft.src;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public abstract class StructureStart {
	protected LinkedList field_35547_a = new LinkedList();
	protected StructureBoundingBox field_35546_b;

	public StructureBoundingBox func_35543_b() {
		return this.field_35546_b;
	}

	public void func_35541_a(World world1, Random random2, StructureBoundingBox structureBoundingBox3) {
		Iterator iterator4 = this.field_35547_a.iterator();

		while(iterator4.hasNext()) {
			StructureComponent structureComponent5 = (StructureComponent)iterator4.next();
			if(structureComponent5.func_35313_b().func_35664_a(structureBoundingBox3) && !structureComponent5.func_35310_a(world1, random2, structureBoundingBox3)) {
				iterator4.remove();
			}
		}

	}

	protected void func_35544_c() {
		this.field_35546_b = StructureBoundingBox.func_35672_a();
		Iterator iterator1 = this.field_35547_a.iterator();

		while(iterator1.hasNext()) {
			StructureComponent structureComponent2 = (StructureComponent)iterator1.next();
			this.field_35546_b.func_35666_b(structureComponent2.func_35313_b());
		}

	}

	protected void func_35545_a(World world1, Random random2, int i3) {
		world1.getClass();
		int i4 = 63 - i3;
		int i5 = this.field_35546_b.func_35668_c() + 1;
		if(i5 < i4) {
			i5 += random2.nextInt(i4 - i5);
		}

		int i6 = i5 - this.field_35546_b.field_35675_e;
		this.field_35546_b.func_35670_a(0, i6, 0);
		Iterator iterator7 = this.field_35547_a.iterator();

		while(iterator7.hasNext()) {
			StructureComponent structureComponent8 = (StructureComponent)iterator7.next();
			structureComponent8.func_35313_b().func_35670_a(0, i6, 0);
		}

	}

	public boolean func_35542_a() {
		return true;
	}
}
