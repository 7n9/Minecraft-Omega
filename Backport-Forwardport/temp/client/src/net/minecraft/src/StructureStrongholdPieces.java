package net.minecraft.src;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class StructureStrongholdPieces {
	private static final StructureStrongholdPieceWeight[] field_35855_b = new StructureStrongholdPieceWeight[]{new StructureStrongholdPieceWeight(ComponentStrongholdStraight.class, 40, 0), new StructureStrongholdPieceWeight(ComponentStrongholdPrison.class, 5, 5), new StructureStrongholdPieceWeight(ComponentStrongholdLeftTurn.class, 20, 0), new StructureStrongholdPieceWeight(ComponentStrongholdRightTurn.class, 20, 0), new StructureStrongholdPieceWeight(ComponentStrongholdRoomCrossing.class, 10, 6), new StructureStrongholdPieceWeight(ComponentStrongholdStairsStraight.class, 5, 10), new StructureStrongholdPieceWeight(ComponentStrongholdStairs.class, 5, 10), new StructureStrongholdPieceWeight(ComponentStrongholdCrossing.class, 5, 4), new StructureStrongholdPieceWeight2(ComponentStrongholdLibrary.class, 10, 1)};
	private static List field_35856_c;
	static int field_35857_a = 0;
	private static final StructureStrongholdStones field_35854_d = new StructureStrongholdStones((StructureStrongholdPieceWeight2)null);

	public static void func_35849_a() {
		field_35856_c = new ArrayList();
		StructureStrongholdPieceWeight[] structureStrongholdPieceWeight0 = field_35855_b;
		int i1 = structureStrongholdPieceWeight0.length;

		for(int i2 = 0; i2 < i1; ++i2) {
			StructureStrongholdPieceWeight structureStrongholdPieceWeight3 = structureStrongholdPieceWeight0[i2];
			structureStrongholdPieceWeight3.field_35617_c = 0;
			field_35856_c.add(structureStrongholdPieceWeight3);
		}

	}

	private static boolean func_35853_c() {
		boolean z0 = false;
		field_35857_a = 0;

		StructureStrongholdPieceWeight structureStrongholdPieceWeight2;
		for(Iterator iterator1 = field_35856_c.iterator(); iterator1.hasNext(); field_35857_a += structureStrongholdPieceWeight2.field_35616_b) {
			structureStrongholdPieceWeight2 = (StructureStrongholdPieceWeight)iterator1.next();
			if(structureStrongholdPieceWeight2.field_35615_d > 0 && structureStrongholdPieceWeight2.field_35617_c < structureStrongholdPieceWeight2.field_35615_d) {
				z0 = true;
			}
		}

		return z0;
	}

	private static ComponentStronghold func_35851_a(StructureStrongholdPieceWeight structureStrongholdPieceWeight0, List list1, Random random2, int i3, int i4, int i5, int i6, int i7) {
		Class class8 = structureStrongholdPieceWeight0.field_35618_a;
		Object object9 = null;
		if(class8 == ComponentStrongholdStraight.class) {
			object9 = ComponentStrongholdStraight.func_35047_a(list1, random2, i3, i4, i5, i6, i7);
		} else if(class8 == ComponentStrongholdPrison.class) {
			object9 = ComponentStrongholdPrison.func_35063_a(list1, random2, i3, i4, i5, i6, i7);
		} else if(class8 == ComponentStrongholdLeftTurn.class) {
			object9 = ComponentStrongholdLeftTurn.func_35045_a(list1, random2, i3, i4, i5, i6, i7);
		} else if(class8 == ComponentStrongholdRightTurn.class) {
			object9 = ComponentStrongholdRightTurn.func_35045_a(list1, random2, i3, i4, i5, i6, i7);
		} else if(class8 == ComponentStrongholdRoomCrossing.class) {
			object9 = ComponentStrongholdRoomCrossing.func_35059_a(list1, random2, i3, i4, i5, i6, i7);
		} else if(class8 == ComponentStrongholdStairsStraight.class) {
			object9 = ComponentStrongholdStairsStraight.func_35053_a(list1, random2, i3, i4, i5, i6, i7);
		} else if(class8 == ComponentStrongholdStairs.class) {
			object9 = ComponentStrongholdStairs.func_35034_a(list1, random2, i3, i4, i5, i6, i7);
		} else if(class8 == ComponentStrongholdCrossing.class) {
			object9 = ComponentStrongholdCrossing.func_35039_a(list1, random2, i3, i4, i5, i6, i7);
		} else if(class8 == ComponentStrongholdLibrary.class) {
			object9 = ComponentStrongholdLibrary.func_35055_a(list1, random2, i3, i4, i5, i6, i7);
		}

		return (ComponentStronghold)object9;
	}

	private static ComponentStronghold func_35847_b(ComponentStrongholdStairs2 componentStrongholdStairs20, List list1, Random random2, int i3, int i4, int i5, int i6, int i7) {
		if(!func_35853_c()) {
			return null;
		} else {
			int i8 = 0;

			while(i8 < 5) {
				++i8;
				int i9 = random2.nextInt(field_35857_a);
				Iterator iterator10 = field_35856_c.iterator();

				while(iterator10.hasNext()) {
					StructureStrongholdPieceWeight structureStrongholdPieceWeight11 = (StructureStrongholdPieceWeight)iterator10.next();
					i9 -= structureStrongholdPieceWeight11.field_35616_b;
					if(i9 < 0) {
						if(!structureStrongholdPieceWeight11.func_35613_a(i7) || structureStrongholdPieceWeight11 == componentStrongholdStairs20.field_35038_a) {
							break;
						}

						ComponentStronghold componentStronghold12 = func_35851_a(structureStrongholdPieceWeight11, list1, random2, i3, i4, i5, i6, i7);
						if(componentStronghold12 != null) {
							++structureStrongholdPieceWeight11.field_35617_c;
							componentStrongholdStairs20.field_35038_a = structureStrongholdPieceWeight11;
							if(!structureStrongholdPieceWeight11.func_35614_a()) {
								field_35856_c.remove(structureStrongholdPieceWeight11);
							}

							return componentStronghold12;
						}
					}
				}
			}

			StructureBoundingBox structureBoundingBox13 = ComponentStrongholdCorridor.func_35051_a(list1, random2, i3, i4, i5, i6);
			if(structureBoundingBox13 != null && structureBoundingBox13.field_35751_b > 1) {
				return new ComponentStrongholdCorridor(i7, random2, structureBoundingBox13, i6);
			} else {
				return null;
			}
		}
	}

	private static StructureComponent func_35848_c(ComponentStrongholdStairs2 componentStrongholdStairs20, List list1, Random random2, int i3, int i4, int i5, int i6, int i7) {
		if(i7 > 50) {
			return null;
		} else if(Math.abs(i3 - componentStrongholdStairs20.func_35021_b().field_35753_a) <= 112 && Math.abs(i5 - componentStrongholdStairs20.func_35021_b().field_35752_c) <= 112) {
			ComponentStronghold componentStronghold8 = func_35847_b(componentStrongholdStairs20, list1, random2, i3, i4, i5, i6, i7 + 1);
			if(componentStronghold8 != null) {
				list1.add(componentStronghold8);
				componentStrongholdStairs20.field_35037_b.add(componentStronghold8);
			}

			return componentStronghold8;
		} else {
			return null;
		}
	}

	static StructureComponent func_35850_a(ComponentStrongholdStairs2 componentStrongholdStairs20, List list1, Random random2, int i3, int i4, int i5, int i6, int i7) {
		return func_35848_c(componentStrongholdStairs20, list1, random2, i3, i4, i5, i6, i7);
	}

	static StructureStrongholdStones func_35852_b() {
		return field_35854_d;
	}
}
