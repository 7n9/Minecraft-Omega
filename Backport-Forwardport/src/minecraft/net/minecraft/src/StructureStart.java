package net.minecraft.src;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public abstract class StructureStart {
	protected LinkedList field_35717_a = new LinkedList();
	protected StructureBoundingBox field_35716_b;

	public StructureBoundingBox func_35712_a() {
		return this.field_35716_b;
	}

	public void func_35711_a(World world1, Random random2, StructureBoundingBox structureBoundingBox3) {
		Iterator iterator4 = this.field_35717_a.iterator();

		while(iterator4.hasNext()) {
			StructureComponent structureComponent5 = (StructureComponent)iterator4.next();
			if(structureComponent5.func_35021_b().func_35740_a(structureBoundingBox3) && !structureComponent5.func_35023_a(world1, random2, structureBoundingBox3)) {
				iterator4.remove();
			}
		}

	}

	protected void func_35714_b() {
		this.field_35716_b = StructureBoundingBox.func_35741_a();
		Iterator iterator1 = this.field_35717_a.iterator();

		while(iterator1.hasNext()) {
			StructureComponent structureComponent2 = (StructureComponent)iterator1.next();
			this.field_35716_b.func_35738_b(structureComponent2.func_35021_b());
		}

	}

	protected void func_35713_a(World world1, Random random2, int i3) {
		world1.getClass();
		int i4 = 63 - i3;
		int i5 = this.field_35716_b.func_35743_c() + 1;
		if(i5 < i4) {
			i5 += random2.nextInt(i4 - i5);
		}

		int i6 = i5 - this.field_35716_b.field_35750_e;
		this.field_35716_b.func_35745_a(0, i6, 0);
		Iterator iterator7 = this.field_35717_a.iterator();

		while(iterator7.hasNext()) {
			StructureComponent structureComponent8 = (StructureComponent)iterator7.next();
			structureComponent8.func_35021_b().func_35745_a(0, i6, 0);
		}

	}

	public boolean func_35715_c() {
		return true;
	}
}
