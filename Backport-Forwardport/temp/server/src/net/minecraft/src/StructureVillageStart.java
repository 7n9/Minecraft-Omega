package net.minecraft.src;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

class StructureVillageStart extends StructureStart {
	private boolean field_35548_c = false;

	public StructureVillageStart(World world1, Random random2, int i3, int i4) {
		byte b5 = 0;
		ArrayList arrayList6 = StructureVillagePieces.func_35646_a(random2, b5);
		ComponentVillageStartPiece componentVillageStartPiece7 = new ComponentVillageStartPiece(world1.getWorldChunkManager(), 0, random2, (i3 << 4) + 2, (i4 << 4) + 2, arrayList6, b5);
		this.field_35547_a.add(componentVillageStartPiece7);
		componentVillageStartPiece7.func_35308_a(componentVillageStartPiece7, this.field_35547_a, random2);
		ArrayList arrayList8 = componentVillageStartPiece7.field_35387_f;
		ArrayList arrayList9 = componentVillageStartPiece7.field_35389_e;

		int i10;
		while(!arrayList8.isEmpty() || !arrayList9.isEmpty()) {
			StructureComponent structureComponent11;
			if(!arrayList8.isEmpty()) {
				i10 = random2.nextInt(arrayList8.size());
				structureComponent11 = (StructureComponent)arrayList8.remove(i10);
				structureComponent11.func_35308_a(componentVillageStartPiece7, this.field_35547_a, random2);
			} else {
				i10 = random2.nextInt(arrayList9.size());
				structureComponent11 = (StructureComponent)arrayList9.remove(i10);
				structureComponent11.func_35308_a(componentVillageStartPiece7, this.field_35547_a, random2);
			}
		}

		this.func_35544_c();
		i10 = 0;
		Iterator iterator13 = this.field_35547_a.iterator();

		while(iterator13.hasNext()) {
			StructureComponent structureComponent12 = (StructureComponent)iterator13.next();
			if(!(structureComponent12 instanceof ComponentVillageRoadPiece)) {
				++i10;
			}
		}

		this.field_35548_c = i10 > 2;
	}

	public boolean func_35542_a() {
		return this.field_35548_c;
	}
}
