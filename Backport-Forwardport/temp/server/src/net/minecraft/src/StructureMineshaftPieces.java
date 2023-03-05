package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class StructureMineshaftPieces {
	private static final StructurePieceTreasure[] field_35436_a = new StructurePieceTreasure[]{new StructurePieceTreasure(Item.ingotIron.shiftedIndex, 0, 1, 5, 10), new StructurePieceTreasure(Item.ingotGold.shiftedIndex, 0, 1, 3, 5), new StructurePieceTreasure(Item.redstone.shiftedIndex, 0, 4, 9, 5), new StructurePieceTreasure(Item.dyePowder.shiftedIndex, 4, 4, 9, 5), new StructurePieceTreasure(Item.diamond.shiftedIndex, 0, 1, 2, 3), new StructurePieceTreasure(Item.coal.shiftedIndex, 0, 3, 8, 10), new StructurePieceTreasure(Item.bread.shiftedIndex, 0, 1, 3, 15), new StructurePieceTreasure(Item.pickaxeSteel.shiftedIndex, 0, 1, 1, 1), new StructurePieceTreasure(Block.rail.blockID, 0, 4, 8, 1), new StructurePieceTreasure(Item.field_35412_bf.shiftedIndex, 0, 2, 4, 10)};

	private static StructureComponent func_35434_a(List list0, Random random1, int i2, int i3, int i4, int i5, int i6) {
		int i7 = random1.nextInt(100);
		StructureBoundingBox structureBoundingBox8;
		if(i7 >= 80) {
			structureBoundingBox8 = ComponentMineshaftCross.func_35362_a(list0, random1, i2, i3, i4, i5);
			if(structureBoundingBox8 != null) {
				return new ComponentMineshaftCross(i6, random1, structureBoundingBox8, i5);
			}
		} else if(i7 >= 70) {
			structureBoundingBox8 = ComponentMineshaftStairs.func_35365_a(list0, random1, i2, i3, i4, i5);
			if(structureBoundingBox8 != null) {
				return new ComponentMineshaftStairs(i6, random1, structureBoundingBox8, i5);
			}
		} else {
			structureBoundingBox8 = ComponentMineshaftCorridor.func_35357_a(list0, random1, i2, i3, i4, i5);
			if(structureBoundingBox8 != null) {
				return new ComponentMineshaftCorridor(i6, random1, structureBoundingBox8, i5);
			}
		}

		return null;
	}

	private static StructureComponent func_35435_b(StructureComponent structureComponent0, List list1, Random random2, int i3, int i4, int i5, int i6, int i7) {
		if(i7 > 10) {
			return null;
		} else if(Math.abs(i3 - structureComponent0.func_35313_b().field_35678_a) <= 80 && Math.abs(i5 - structureComponent0.func_35313_b().field_35677_c) <= 80) {
			StructureComponent structureComponent8 = func_35434_a(list1, random2, i3, i4, i5, i6, i7 + 1);
			if(structureComponent8 != null) {
				list1.add(structureComponent8);
				structureComponent8.func_35308_a(structureComponent0, list1, random2);
			}

			return structureComponent8;
		} else {
			return null;
		}
	}

	static StructureComponent func_35433_a(StructureComponent structureComponent0, List list1, Random random2, int i3, int i4, int i5, int i6, int i7) {
		return func_35435_b(structureComponent0, list1, random2, i3, i4, i5, i6, i7);
	}

	static StructurePieceTreasure[] func_35432_a() {
		return field_35436_a;
	}
}
