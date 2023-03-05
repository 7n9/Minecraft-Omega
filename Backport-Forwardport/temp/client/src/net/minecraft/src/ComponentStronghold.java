package net.minecraft.src;

import java.util.List;
import java.util.Random;

abstract class ComponentStronghold extends StructureComponent {
	protected ComponentStronghold(int i1) {
		super(i1);
	}

	protected void func_35033_a(World world1, Random random2, StructureBoundingBox structureBoundingBox3, EnumDoor enumDoor4, int i5, int i6, int i7) {
		switch(ComponentStronghold.SyntheticClass_1.$SwitchMap$net$minecraft$src$EnumDoor[enumDoor4.ordinal()]) {
		case 1:
		default:
			this.func_35011_a(world1, structureBoundingBox3, i5, i6, i7, i5 + 3 - 1, i6 + 3 - 1, i7, 0, 0, false);
			break;
		case 2:
			this.func_35018_a(world1, Block.field_35285_bn.blockID, 0, i5, i6, i7, structureBoundingBox3);
			this.func_35018_a(world1, Block.field_35285_bn.blockID, 0, i5, i6 + 1, i7, structureBoundingBox3);
			this.func_35018_a(world1, Block.field_35285_bn.blockID, 0, i5, i6 + 2, i7, structureBoundingBox3);
			this.func_35018_a(world1, Block.field_35285_bn.blockID, 0, i5 + 1, i6 + 2, i7, structureBoundingBox3);
			this.func_35018_a(world1, Block.field_35285_bn.blockID, 0, i5 + 2, i6 + 2, i7, structureBoundingBox3);
			this.func_35018_a(world1, Block.field_35285_bn.blockID, 0, i5 + 2, i6 + 1, i7, structureBoundingBox3);
			this.func_35018_a(world1, Block.field_35285_bn.blockID, 0, i5 + 2, i6, i7, structureBoundingBox3);
			this.func_35018_a(world1, Block.doorWood.blockID, 0, i5 + 1, i6, i7, structureBoundingBox3);
			this.func_35018_a(world1, Block.doorWood.blockID, 8, i5 + 1, i6 + 1, i7, structureBoundingBox3);
			break;
		case 3:
			this.func_35018_a(world1, 0, 0, i5 + 1, i6, i7, structureBoundingBox3);
			this.func_35018_a(world1, 0, 0, i5 + 1, i6 + 1, i7, structureBoundingBox3);
			this.func_35018_a(world1, Block.field_35288_bq.blockID, 0, i5, i6, i7, structureBoundingBox3);
			this.func_35018_a(world1, Block.field_35288_bq.blockID, 0, i5, i6 + 1, i7, structureBoundingBox3);
			this.func_35018_a(world1, Block.field_35288_bq.blockID, 0, i5, i6 + 2, i7, structureBoundingBox3);
			this.func_35018_a(world1, Block.field_35288_bq.blockID, 0, i5 + 1, i6 + 2, i7, structureBoundingBox3);
			this.func_35018_a(world1, Block.field_35288_bq.blockID, 0, i5 + 2, i6 + 2, i7, structureBoundingBox3);
			this.func_35018_a(world1, Block.field_35288_bq.blockID, 0, i5 + 2, i6 + 1, i7, structureBoundingBox3);
			this.func_35018_a(world1, Block.field_35288_bq.blockID, 0, i5 + 2, i6, i7, structureBoundingBox3);
			break;
		case 4:
			this.func_35018_a(world1, Block.field_35285_bn.blockID, 0, i5, i6, i7, structureBoundingBox3);
			this.func_35018_a(world1, Block.field_35285_bn.blockID, 0, i5, i6 + 1, i7, structureBoundingBox3);
			this.func_35018_a(world1, Block.field_35285_bn.blockID, 0, i5, i6 + 2, i7, structureBoundingBox3);
			this.func_35018_a(world1, Block.field_35285_bn.blockID, 0, i5 + 1, i6 + 2, i7, structureBoundingBox3);
			this.func_35018_a(world1, Block.field_35285_bn.blockID, 0, i5 + 2, i6 + 2, i7, structureBoundingBox3);
			this.func_35018_a(world1, Block.field_35285_bn.blockID, 0, i5 + 2, i6 + 1, i7, structureBoundingBox3);
			this.func_35018_a(world1, Block.field_35285_bn.blockID, 0, i5 + 2, i6, i7, structureBoundingBox3);
			this.func_35018_a(world1, Block.doorSteel.blockID, 0, i5 + 1, i6, i7, structureBoundingBox3);
			this.func_35018_a(world1, Block.doorSteel.blockID, 8, i5 + 1, i6 + 1, i7, structureBoundingBox3);
			this.func_35018_a(world1, Block.button.blockID, this.func_35009_c(Block.button.blockID, 4), i5 + 2, i6 + 1, i7 + 1, structureBoundingBox3);
			this.func_35018_a(world1, Block.button.blockID, this.func_35009_c(Block.button.blockID, 3), i5 + 2, i6 + 1, i7 - 1, structureBoundingBox3);
		}

	}

	protected EnumDoor func_35031_a(Random random1) {
		int i2 = random1.nextInt(5);
		switch(i2) {
		case 0:
		case 1:
		default:
			return EnumDoor.OPENING;
		case 2:
			return EnumDoor.WOOD_DOOR;
		case 3:
			return EnumDoor.GRATES;
		case 4:
			return EnumDoor.IRON_DOOR;
		}
	}

	protected StructureComponent func_35028_a(ComponentStrongholdStairs2 componentStrongholdStairs21, List list2, Random random3, int i4, int i5) {
		switch(this.field_35025_h) {
		case 0:
			return StructureStrongholdPieces.func_35850_a(componentStrongholdStairs21, list2, random3, this.field_35024_g.field_35753_a + i4, this.field_35024_g.field_35751_b + i5, this.field_35024_g.field_35748_f + 1, this.field_35025_h, this.func_35012_c());
		case 1:
			return StructureStrongholdPieces.func_35850_a(componentStrongholdStairs21, list2, random3, this.field_35024_g.field_35753_a - 1, this.field_35024_g.field_35751_b + i5, this.field_35024_g.field_35752_c + i4, this.field_35025_h, this.func_35012_c());
		case 2:
			return StructureStrongholdPieces.func_35850_a(componentStrongholdStairs21, list2, random3, this.field_35024_g.field_35753_a + i4, this.field_35024_g.field_35751_b + i5, this.field_35024_g.field_35752_c - 1, this.field_35025_h, this.func_35012_c());
		case 3:
			return StructureStrongholdPieces.func_35850_a(componentStrongholdStairs21, list2, random3, this.field_35024_g.field_35749_d + 1, this.field_35024_g.field_35751_b + i5, this.field_35024_g.field_35752_c + i4, this.field_35025_h, this.func_35012_c());
		default:
			return null;
		}
	}

	protected StructureComponent func_35032_b(ComponentStrongholdStairs2 componentStrongholdStairs21, List list2, Random random3, int i4, int i5) {
		switch(this.field_35025_h) {
		case 0:
			return StructureStrongholdPieces.func_35850_a(componentStrongholdStairs21, list2, random3, this.field_35024_g.field_35753_a - 1, this.field_35024_g.field_35751_b + i4, this.field_35024_g.field_35752_c + i5, 1, this.func_35012_c());
		case 1:
			return StructureStrongholdPieces.func_35850_a(componentStrongholdStairs21, list2, random3, this.field_35024_g.field_35753_a + i5, this.field_35024_g.field_35751_b + i4, this.field_35024_g.field_35752_c - 1, 2, this.func_35012_c());
		case 2:
			return StructureStrongholdPieces.func_35850_a(componentStrongholdStairs21, list2, random3, this.field_35024_g.field_35753_a - 1, this.field_35024_g.field_35751_b + i4, this.field_35024_g.field_35752_c + i5, 1, this.func_35012_c());
		case 3:
			return StructureStrongholdPieces.func_35850_a(componentStrongholdStairs21, list2, random3, this.field_35024_g.field_35753_a + i5, this.field_35024_g.field_35751_b + i4, this.field_35024_g.field_35752_c - 1, 2, this.func_35012_c());
		default:
			return null;
		}
	}

	protected StructureComponent func_35029_c(ComponentStrongholdStairs2 componentStrongholdStairs21, List list2, Random random3, int i4, int i5) {
		switch(this.field_35025_h) {
		case 0:
			return StructureStrongholdPieces.func_35850_a(componentStrongholdStairs21, list2, random3, this.field_35024_g.field_35749_d + 1, this.field_35024_g.field_35751_b + i4, this.field_35024_g.field_35752_c + i5, 3, this.func_35012_c());
		case 1:
			return StructureStrongholdPieces.func_35850_a(componentStrongholdStairs21, list2, random3, this.field_35024_g.field_35753_a + i5, this.field_35024_g.field_35751_b + i4, this.field_35024_g.field_35748_f + 1, 0, this.func_35012_c());
		case 2:
			return StructureStrongholdPieces.func_35850_a(componentStrongholdStairs21, list2, random3, this.field_35024_g.field_35749_d + 1, this.field_35024_g.field_35751_b + i4, this.field_35024_g.field_35752_c + i5, 3, this.func_35012_c());
		case 3:
			return StructureStrongholdPieces.func_35850_a(componentStrongholdStairs21, list2, random3, this.field_35024_g.field_35753_a + i5, this.field_35024_g.field_35751_b + i4, this.field_35024_g.field_35748_f + 1, 0, this.func_35012_c());
		default:
			return null;
		}
	}

	protected static boolean func_35030_a(StructureBoundingBox structureBoundingBox0) {
		return structureBoundingBox0 != null && structureBoundingBox0.field_35751_b > 10;
	}

	static final class SyntheticClass_1 {
		static final int[] $SwitchMap$net$minecraft$src$EnumDoor = new int[EnumDoor.values().length];

		static {
			try {
				$SwitchMap$net$minecraft$src$EnumDoor[EnumDoor.OPENING.ordinal()] = 1;
			} catch (NoSuchFieldError noSuchFieldError4) {
			}

			try {
				$SwitchMap$net$minecraft$src$EnumDoor[EnumDoor.WOOD_DOOR.ordinal()] = 2;
			} catch (NoSuchFieldError noSuchFieldError3) {
			}

			try {
				$SwitchMap$net$minecraft$src$EnumDoor[EnumDoor.GRATES.ordinal()] = 3;
			} catch (NoSuchFieldError noSuchFieldError2) {
			}

			try {
				$SwitchMap$net$minecraft$src$EnumDoor[EnumDoor.IRON_DOOR.ordinal()] = 4;
			} catch (NoSuchFieldError noSuchFieldError1) {
			}

		}
	}
}
