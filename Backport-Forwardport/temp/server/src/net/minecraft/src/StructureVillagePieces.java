package net.minecraft.src;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class StructureVillagePieces {
	public static ArrayList func_35646_a(Random random0, int i1) {
		ArrayList arrayList2 = new ArrayList();
		arrayList2.add(new StructureVillagePieceWeight(ComponentVillageHouse4_Garden.class, 4, MathHelper.func_35476_a(random0, 2 + i1, 4 + i1 * 2)));
		arrayList2.add(new StructureVillagePieceWeight(ComponentVillageChurch.class, 20, MathHelper.func_35476_a(random0, 0 + i1, 1 + i1)));
		arrayList2.add(new StructureVillagePieceWeight(ComponentVillageHouse1.class, 20, MathHelper.func_35476_a(random0, 0 + i1, 2 + i1)));
		arrayList2.add(new StructureVillagePieceWeight(ComponentVillageWoodHut.class, 3, MathHelper.func_35476_a(random0, 2 + i1, 5 + i1 * 3)));
		arrayList2.add(new StructureVillagePieceWeight(ComponentVillageHall.class, 15, MathHelper.func_35476_a(random0, 0 + i1, 2 + i1)));
		arrayList2.add(new StructureVillagePieceWeight(ComponentVillageField.class, 3, MathHelper.func_35476_a(random0, 1 + i1, 4 + i1)));
		arrayList2.add(new StructureVillagePieceWeight(ComponentVillageField2.class, 3, MathHelper.func_35476_a(random0, 2 + i1, 4 + i1 * 2)));
		arrayList2.add(new StructureVillagePieceWeight(ComponentVillageHouse2.class, 15, MathHelper.func_35476_a(random0, 0, 1 + i1)));
		arrayList2.add(new StructureVillagePieceWeight(ComponentVillageHouse3.class, 8, MathHelper.func_35476_a(random0, 0 + i1, 3 + i1 * 2)));
		Iterator iterator3 = arrayList2.iterator();

		while(iterator3.hasNext()) {
			if(((StructureVillagePieceWeight)iterator3.next()).field_35493_d == 0) {
				iterator3.remove();
			}
		}

		return arrayList2;
	}

	private static int func_35645_a(ArrayList arrayList0) {
		boolean z1 = false;
		int i2 = 0;

		StructureVillagePieceWeight structureVillagePieceWeight4;
		for(Iterator iterator3 = arrayList0.iterator(); iterator3.hasNext(); i2 += structureVillagePieceWeight4.field_35494_b) {
			structureVillagePieceWeight4 = (StructureVillagePieceWeight)iterator3.next();
			if(structureVillagePieceWeight4.field_35493_d > 0 && structureVillagePieceWeight4.field_35495_c < structureVillagePieceWeight4.field_35493_d) {
				z1 = true;
			}
		}

		return z1 ? i2 : -1;
	}

	private static ComponentVillage func_35639_a(StructureVillagePieceWeight structureVillagePieceWeight0, List list1, Random random2, int i3, int i4, int i5, int i6, int i7) {
		Class class8 = structureVillagePieceWeight0.field_35496_a;
		Object object9 = null;
		if(class8 == ComponentVillageHouse4_Garden.class) {
			object9 = ComponentVillageHouse4_Garden.func_35401_a(list1, random2, i3, i4, i5, i6, i7);
		} else if(class8 == ComponentVillageChurch.class) {
			object9 = ComponentVillageChurch.func_35380_a(list1, random2, i3, i4, i5, i6, i7);
		} else if(class8 == ComponentVillageHouse1.class) {
			object9 = ComponentVillageHouse1.func_35397_a(list1, random2, i3, i4, i5, i6, i7);
		} else if(class8 == ComponentVillageWoodHut.class) {
			object9 = ComponentVillageWoodHut.func_35393_a(list1, random2, i3, i4, i5, i6, i7);
		} else if(class8 == ComponentVillageHall.class) {
			object9 = ComponentVillageHall.func_35374_a(list1, random2, i3, i4, i5, i6, i7);
		} else if(class8 == ComponentVillageField.class) {
			object9 = ComponentVillageField.func_35370_a(list1, random2, i3, i4, i5, i6, i7);
		} else if(class8 == ComponentVillageField2.class) {
			object9 = ComponentVillageField2.func_35399_a(list1, random2, i3, i4, i5, i6, i7);
		} else if(class8 == ComponentVillageHouse2.class) {
			object9 = ComponentVillageHouse2.func_35376_a(list1, random2, i3, i4, i5, i6, i7);
		} else if(class8 == ComponentVillageHouse3.class) {
			object9 = ComponentVillageHouse3.func_35372_a(list1, random2, i3, i4, i5, i6, i7);
		}

		return (ComponentVillage)object9;
	}

	private static ComponentVillage func_35643_c(ComponentVillageStartPiece componentVillageStartPiece0, List list1, Random random2, int i3, int i4, int i5, int i6, int i7) {
		int i8 = func_35645_a(componentVillageStartPiece0.field_35388_d);
		if(i8 <= 0) {
			return null;
		} else {
			int i9 = 0;

			while(i9 < 5) {
				++i9;
				int i10 = random2.nextInt(i8);
				Iterator iterator11 = componentVillageStartPiece0.field_35388_d.iterator();

				while(iterator11.hasNext()) {
					StructureVillagePieceWeight structureVillagePieceWeight12 = (StructureVillagePieceWeight)iterator11.next();
					i10 -= structureVillagePieceWeight12.field_35494_b;
					if(i10 < 0) {
						if(!structureVillagePieceWeight12.func_35491_a(i7) || structureVillagePieceWeight12 == componentVillageStartPiece0.field_35391_c && componentVillageStartPiece0.field_35388_d.size() > 1) {
							break;
						}

						ComponentVillage componentVillage13 = func_35639_a(structureVillagePieceWeight12, list1, random2, i3, i4, i5, i6, i7);
						if(componentVillage13 != null) {
							++structureVillagePieceWeight12.field_35495_c;
							componentVillageStartPiece0.field_35391_c = structureVillagePieceWeight12;
							if(!structureVillagePieceWeight12.func_35492_a()) {
								componentVillageStartPiece0.field_35388_d.remove(structureVillagePieceWeight12);
							}

							return componentVillage13;
						}
					}
				}
			}

			StructureBoundingBox structureBoundingBox14 = ComponentVillageTorch.func_35382_a(list1, random2, i3, i4, i5, i6);
			if(structureBoundingBox14 != null) {
				return new ComponentVillageTorch(i7, random2, structureBoundingBox14, i6);
			} else {
				return null;
			}
		}
	}

	private static StructureComponent func_35641_d(ComponentVillageStartPiece componentVillageStartPiece0, List list1, Random random2, int i3, int i4, int i5, int i6, int i7) {
		if(i7 > 50) {
			return null;
		} else if(Math.abs(i3 - componentVillageStartPiece0.func_35313_b().field_35678_a) <= 112 && Math.abs(i5 - componentVillageStartPiece0.func_35313_b().field_35677_c) <= 112) {
			ComponentVillage componentVillage8 = func_35643_c(componentVillageStartPiece0, list1, random2, i3, i4, i5, i6, i7 + 1);
			if(componentVillage8 != null) {
				int i9 = (componentVillage8.field_35316_g.field_35678_a + componentVillage8.field_35316_g.field_35674_d) / 2;
				int i10 = (componentVillage8.field_35316_g.field_35677_c + componentVillage8.field_35316_g.field_35673_f) / 2;
				int i11 = componentVillage8.field_35316_g.field_35674_d - componentVillage8.field_35316_g.field_35678_a;
				int i12 = componentVillage8.field_35316_g.field_35673_f - componentVillage8.field_35316_g.field_35677_c;
				int i13 = i11 > i12 ? i11 : i12;
				if(componentVillageStartPiece0.func_35386_a().func_35141_a(i9, i10, i13 / 2 + 4, MapGenVillage.field_35538_a)) {
					list1.add(componentVillage8);
					componentVillageStartPiece0.field_35389_e.add(componentVillage8);
					return componentVillage8;
				}
			}

			return null;
		} else {
			return null;
		}
	}

	private static StructureComponent func_35644_e(ComponentVillageStartPiece componentVillageStartPiece0, List list1, Random random2, int i3, int i4, int i5, int i6, int i7) {
		if(i7 > 3 + componentVillageStartPiece0.field_35390_b) {
			return null;
		} else if(Math.abs(i3 - componentVillageStartPiece0.func_35313_b().field_35678_a) <= 112 && Math.abs(i5 - componentVillageStartPiece0.func_35313_b().field_35677_c) <= 112) {
			StructureBoundingBox structureBoundingBox8 = ComponentVillagePathGen.func_35378_a(componentVillageStartPiece0, list1, random2, i3, i4, i5, i6);
			if(structureBoundingBox8 != null && structureBoundingBox8.field_35676_b > 10) {
				ComponentVillagePathGen componentVillagePathGen9 = new ComponentVillagePathGen(i7, random2, structureBoundingBox8, i6);
				int i10 = (componentVillagePathGen9.field_35316_g.field_35678_a + componentVillagePathGen9.field_35316_g.field_35674_d) / 2;
				int i11 = (componentVillagePathGen9.field_35316_g.field_35677_c + componentVillagePathGen9.field_35316_g.field_35673_f) / 2;
				int i12 = componentVillagePathGen9.field_35316_g.field_35674_d - componentVillagePathGen9.field_35316_g.field_35678_a;
				int i13 = componentVillagePathGen9.field_35316_g.field_35673_f - componentVillagePathGen9.field_35316_g.field_35677_c;
				int i14 = i12 > i13 ? i12 : i13;
				if(componentVillageStartPiece0.func_35386_a().func_35141_a(i10, i11, i14 / 2 + 4, MapGenVillage.field_35538_a)) {
					list1.add(componentVillagePathGen9);
					componentVillageStartPiece0.field_35387_f.add(componentVillagePathGen9);
					return componentVillagePathGen9;
				}
			}

			return null;
		} else {
			return null;
		}
	}

	static StructureComponent func_35640_a(ComponentVillageStartPiece componentVillageStartPiece0, List list1, Random random2, int i3, int i4, int i5, int i6, int i7) {
		return func_35641_d(componentVillageStartPiece0, list1, random2, i3, i4, i5, i6, i7);
	}

	static StructureComponent func_35642_b(ComponentVillageStartPiece componentVillageStartPiece0, List list1, Random random2, int i3, int i4, int i5, int i6, int i7) {
		return func_35644_e(componentVillageStartPiece0, list1, random2, i3, i4, i5, i6, i7);
	}
}
