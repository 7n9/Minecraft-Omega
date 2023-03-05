package net.minecraft.src;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class StructureStrongholdPieces {
	private static final StructureStrongholdPieceWeight[] field_35628_b = new StructureStrongholdPieceWeight[]{new StructureStrongholdPieceWeight(ComponentStrongholdStraight.class, 40, 0), new StructureStrongholdPieceWeight(ComponentStrongholdPrison.class, 5, 5), new StructureStrongholdPieceWeight(ComponentStrongholdLeftTurn.class, 20, 0), new StructureStrongholdPieceWeight(ComponentStrongholdRightTurn.class, 20, 0), new StructureStrongholdPieceWeight(ComponentStrongholdRoomCrossing.class, 10, 6), new StructureStrongholdPieceWeight(ComponentStrongholdStairsStraight.class, 5, 10), new StructureStrongholdPieceWeight(ComponentStrongholdStairs.class, 5, 10), new StructureStrongholdPieceWeight(ComponentStrongholdCrossing.class, 5, 4), new StructureStrongholdPieceWeight2(ComponentStrongholdLibrary.class, 10, 1)};
	private static List field_35629_c;
	static int field_35630_a = 0;
	private static final StructureStrongholdStones field_35627_d = new StructureStrongholdStones((StructureStrongholdPieceWeight2)null);

	public static void func_35625_a() {
		field_35629_c = new ArrayList();
		StructureStrongholdPieceWeight[] structureStrongholdPieceWeight0 = field_35628_b;
		int i1 = structureStrongholdPieceWeight0.length;

		for(int i2 = 0; i2 < i1; ++i2) {
			StructureStrongholdPieceWeight structureStrongholdPieceWeight3 = structureStrongholdPieceWeight0[i2];
			structureStrongholdPieceWeight3.field_35579_c = 0;
			field_35629_c.add(structureStrongholdPieceWeight3);
		}

	}

	private static boolean func_35626_c() {
		boolean z0 = false;
		field_35630_a = 0;

		StructureStrongholdPieceWeight structureStrongholdPieceWeight2;
		for(Iterator iterator1 = field_35629_c.iterator(); iterator1.hasNext(); field_35630_a += structureStrongholdPieceWeight2.field_35578_b) {
			structureStrongholdPieceWeight2 = (StructureStrongholdPieceWeight)iterator1.next();
			if(structureStrongholdPieceWeight2.field_35577_d > 0 && structureStrongholdPieceWeight2.field_35579_c < structureStrongholdPieceWeight2.field_35577_d) {
				z0 = true;
			}
		}

		return z0;
	}

	private static ComponentStronghold func_35620_a(StructureStrongholdPieceWeight structureStrongholdPieceWeight0, List list1, Random random2, int i3, int i4, int i5, int i6, int i7) {
		Class class8 = structureStrongholdPieceWeight0.field_35580_a;
		Object object9 = null;
		if(class8 == ComponentStrongholdStraight.class) {
			object9 = ComponentStrongholdStraight.func_35338_a(list1, random2, i3, i4, i5, i6, i7);
		} else if(class8 == ComponentStrongholdPrison.class) {
			object9 = ComponentStrongholdPrison.func_35332_a(list1, random2, i3, i4, i5, i6, i7);
		} else if(class8 == ComponentStrongholdLeftTurn.class) {
			object9 = ComponentStrongholdLeftTurn.func_35330_a(list1, random2, i3, i4, i5, i6, i7);
		} else if(class8 == ComponentStrongholdRightTurn.class) {
			object9 = ComponentStrongholdRightTurn.func_35330_a(list1, random2, i3, i4, i5, i6, i7);
		} else if(class8 == ComponentStrongholdRoomCrossing.class) {
			object9 = ComponentStrongholdRoomCrossing.func_35346_a(list1, random2, i3, i4, i5, i6, i7);
		} else if(class8 == ComponentStrongholdStairsStraight.class) {
			object9 = ComponentStrongholdStairsStraight.func_35344_a(list1, random2, i3, i4, i5, i6, i7);
		} else if(class8 == ComponentStrongholdStairs.class) {
			object9 = ComponentStrongholdStairs.func_35325_a(list1, random2, i3, i4, i5, i6, i7);
		} else if(class8 == ComponentStrongholdCrossing.class) {
			object9 = ComponentStrongholdCrossing.func_35350_a(list1, random2, i3, i4, i5, i6, i7);
		} else if(class8 == ComponentStrongholdLibrary.class) {
			object9 = ComponentStrongholdLibrary.func_35334_a(list1, random2, i3, i4, i5, i6, i7);
		}

		return (ComponentStronghold)object9;
	}

	private static ComponentStronghold func_35623_b(ComponentStrongholdStairs2 componentStrongholdStairs20, List list1, Random random2, int i3, int i4, int i5, int i6, int i7) {
		if(!func_35626_c()) {
			return null;
		} else {
			int i8 = 0;

			while(i8 < 5) {
				++i8;
				int i9 = random2.nextInt(field_35630_a);
				Iterator iterator10 = field_35629_c.iterator();

				while(iterator10.hasNext()) {
					StructureStrongholdPieceWeight structureStrongholdPieceWeight11 = (StructureStrongholdPieceWeight)iterator10.next();
					i9 -= structureStrongholdPieceWeight11.field_35578_b;
					if(i9 < 0) {
						if(!structureStrongholdPieceWeight11.func_35575_a(i7) || structureStrongholdPieceWeight11 == componentStrongholdStairs20.field_35329_a) {
							break;
						}

						ComponentStronghold componentStronghold12 = func_35620_a(structureStrongholdPieceWeight11, list1, random2, i3, i4, i5, i6, i7);
						if(componentStronghold12 != null) {
							++structureStrongholdPieceWeight11.field_35579_c;
							componentStrongholdStairs20.field_35329_a = structureStrongholdPieceWeight11;
							if(!structureStrongholdPieceWeight11.func_35576_a()) {
								field_35629_c.remove(structureStrongholdPieceWeight11);
							}

							return componentStronghold12;
						}
					}
				}
			}

			StructureBoundingBox structureBoundingBox13 = ComponentStrongholdCorridor.func_35342_a(list1, random2, i3, i4, i5, i6);
			if(structureBoundingBox13 != null && structureBoundingBox13.field_35676_b > 1) {
				return new ComponentStrongholdCorridor(i7, random2, structureBoundingBox13, i6);
			} else {
				return null;
			}
		}
	}

	private static StructureComponent func_35621_c(ComponentStrongholdStairs2 componentStrongholdStairs20, List list1, Random random2, int i3, int i4, int i5, int i6, int i7) {
		if(i7 > 50) {
			return null;
		} else if(Math.abs(i3 - componentStrongholdStairs20.func_35313_b().field_35678_a) <= 112 && Math.abs(i5 - componentStrongholdStairs20.func_35313_b().field_35677_c) <= 112) {
			ComponentStronghold componentStronghold8 = func_35623_b(componentStrongholdStairs20, list1, random2, i3, i4, i5, i6, i7 + 1);
			if(componentStronghold8 != null) {
				list1.add(componentStronghold8);
				componentStrongholdStairs20.field_35328_b.add(componentStronghold8);
			}

			return componentStronghold8;
		} else {
			return null;
		}
	}

	static StructureComponent func_35624_a(ComponentStrongholdStairs2 componentStrongholdStairs20, List list1, Random random2, int i3, int i4, int i5, int i6, int i7) {
		return func_35621_c(componentStrongholdStairs20, list1, random2, i3, i4, i5, i6, i7);
	}

	static StructureStrongholdStones func_35622_b() {
		return field_35627_d;
	}
}
