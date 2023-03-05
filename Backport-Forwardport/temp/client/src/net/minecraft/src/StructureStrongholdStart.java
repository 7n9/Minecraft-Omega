package net.minecraft.src;

import java.util.ArrayList;
import java.util.Random;

class StructureStrongholdStart extends StructureStart {
	public StructureStrongholdStart(World world1, Random random2, int i3, int i4) {
		StructureStrongholdPieces.func_35849_a();
		ComponentStrongholdStairs2 componentStrongholdStairs25 = new ComponentStrongholdStairs2(0, random2, (i3 << 4) + 2, (i4 << 4) + 2);
		this.field_35717_a.add(componentStrongholdStairs25);
		componentStrongholdStairs25.func_35004_a(componentStrongholdStairs25, this.field_35717_a, random2);
		ArrayList arrayList6 = componentStrongholdStairs25.field_35037_b;

		while(!arrayList6.isEmpty()) {
			int i7 = random2.nextInt(arrayList6.size());
			StructureComponent structureComponent8 = (StructureComponent)arrayList6.remove(i7);
			structureComponent8.func_35004_a(componentStrongholdStairs25, this.field_35717_a, random2);
		}

		this.func_35714_b();
		this.func_35713_a(world1, random2, 10);
	}
}
