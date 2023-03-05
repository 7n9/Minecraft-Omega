package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class ComponentStrongholdRoomCrossing extends ComponentStronghold {
	private static final StructurePieceTreasure[] field_35348_c = new StructurePieceTreasure[]{new StructurePieceTreasure(Item.ingotIron.shiftedIndex, 0, 1, 5, 10), new StructurePieceTreasure(Item.ingotGold.shiftedIndex, 0, 1, 3, 5), new StructurePieceTreasure(Item.redstone.shiftedIndex, 0, 4, 9, 5), new StructurePieceTreasure(Item.coal.shiftedIndex, 0, 3, 8, 10), new StructurePieceTreasure(Item.bread.shiftedIndex, 0, 1, 3, 15), new StructurePieceTreasure(Item.appleRed.shiftedIndex, 0, 1, 3, 15), new StructurePieceTreasure(Item.pickaxeSteel.shiftedIndex, 0, 1, 1, 1)};
	protected final EnumDoor field_35349_a;
	protected final int field_35347_b;

	public ComponentStrongholdRoomCrossing(int i1, Random random2, StructureBoundingBox structureBoundingBox3, int i4) {
		super(i1);
		this.field_35317_h = i4;
		this.field_35349_a = this.func_35322_a(random2);
		this.field_35316_g = structureBoundingBox3;
		this.field_35347_b = random2.nextInt(5);
	}

	public void func_35308_a(StructureComponent structureComponent1, List list2, Random random3) {
		this.func_35324_a((ComponentStrongholdStairs2)structureComponent1, list2, random3, 4, 1);
		this.func_35321_b((ComponentStrongholdStairs2)structureComponent1, list2, random3, 1, 4);
		this.func_35320_c((ComponentStrongholdStairs2)structureComponent1, list2, random3, 1, 4);
	}

	public static ComponentStrongholdRoomCrossing func_35346_a(List list0, Random random1, int i2, int i3, int i4, int i5, int i6) {
		StructureBoundingBox structureBoundingBox7 = StructureBoundingBox.func_35663_a(i2, i3, i4, -4, -1, 0, 11, 7, 11, i5);
		return func_35319_a(structureBoundingBox7) && StructureComponent.func_35312_a(list0, structureBoundingBox7) == null ? new ComponentStrongholdRoomCrossing(i6, random1, structureBoundingBox7, i5) : null;
	}

	public boolean func_35310_a(World world1, Random random2, StructureBoundingBox structureBoundingBox3) {
		if(this.func_35295_a(world1, structureBoundingBox3)) {
			return false;
		} else {
			this.func_35307_a(world1, structureBoundingBox3, 0, 0, 0, 10, 6, 10, true, random2, StructureStrongholdPieces.func_35622_b());
			this.func_35323_a(world1, random2, structureBoundingBox3, this.field_35349_a, 4, 1, 0);
			this.func_35294_a(world1, structureBoundingBox3, 4, 1, 10, 6, 3, 10, 0, 0, false);
			this.func_35294_a(world1, structureBoundingBox3, 0, 1, 4, 0, 3, 6, 0, 0, false);
			this.func_35294_a(world1, structureBoundingBox3, 10, 1, 4, 10, 3, 6, 0, 0, false);
			int i4;
			switch(this.field_35347_b) {
			case 0:
				this.func_35309_a(world1, Block.field_35052_bn.blockID, 0, 5, 1, 5, structureBoundingBox3);
				this.func_35309_a(world1, Block.field_35052_bn.blockID, 0, 5, 2, 5, structureBoundingBox3);
				this.func_35309_a(world1, Block.field_35052_bn.blockID, 0, 5, 3, 5, structureBoundingBox3);
				this.func_35309_a(world1, Block.torchWood.blockID, 0, 4, 3, 5, structureBoundingBox3);
				this.func_35309_a(world1, Block.torchWood.blockID, 0, 6, 3, 5, structureBoundingBox3);
				this.func_35309_a(world1, Block.torchWood.blockID, 0, 5, 3, 4, structureBoundingBox3);
				this.func_35309_a(world1, Block.torchWood.blockID, 0, 5, 3, 6, structureBoundingBox3);
				this.func_35309_a(world1, Block.stairSingle.blockID, 0, 4, 1, 4, structureBoundingBox3);
				this.func_35309_a(world1, Block.stairSingle.blockID, 0, 4, 1, 5, structureBoundingBox3);
				this.func_35309_a(world1, Block.stairSingle.blockID, 0, 4, 1, 6, structureBoundingBox3);
				this.func_35309_a(world1, Block.stairSingle.blockID, 0, 6, 1, 4, structureBoundingBox3);
				this.func_35309_a(world1, Block.stairSingle.blockID, 0, 6, 1, 5, structureBoundingBox3);
				this.func_35309_a(world1, Block.stairSingle.blockID, 0, 6, 1, 6, structureBoundingBox3);
				this.func_35309_a(world1, Block.stairSingle.blockID, 0, 5, 1, 4, structureBoundingBox3);
				this.func_35309_a(world1, Block.stairSingle.blockID, 0, 5, 1, 6, structureBoundingBox3);
				break;
			case 1:
				for(i4 = 0; i4 < 5; ++i4) {
					this.func_35309_a(world1, Block.field_35052_bn.blockID, 0, 3, 1, 3 + i4, structureBoundingBox3);
					this.func_35309_a(world1, Block.field_35052_bn.blockID, 0, 7, 1, 3 + i4, structureBoundingBox3);
					this.func_35309_a(world1, Block.field_35052_bn.blockID, 0, 3 + i4, 1, 3, structureBoundingBox3);
					this.func_35309_a(world1, Block.field_35052_bn.blockID, 0, 3 + i4, 1, 7, structureBoundingBox3);
				}

				this.func_35309_a(world1, Block.field_35052_bn.blockID, 0, 5, 1, 5, structureBoundingBox3);
				this.func_35309_a(world1, Block.field_35052_bn.blockID, 0, 5, 2, 5, structureBoundingBox3);
				this.func_35309_a(world1, Block.field_35052_bn.blockID, 0, 5, 3, 5, structureBoundingBox3);
				this.func_35309_a(world1, Block.waterMoving.blockID, 0, 5, 4, 5, structureBoundingBox3);
				break;
			case 2:
				for(i4 = 1; i4 <= 9; ++i4) {
					this.func_35309_a(world1, Block.cobblestone.blockID, 0, 1, 3, i4, structureBoundingBox3);
					this.func_35309_a(world1, Block.cobblestone.blockID, 0, 9, 3, i4, structureBoundingBox3);
				}

				for(i4 = 1; i4 <= 9; ++i4) {
					this.func_35309_a(world1, Block.cobblestone.blockID, 0, i4, 3, 1, structureBoundingBox3);
					this.func_35309_a(world1, Block.cobblestone.blockID, 0, i4, 3, 9, structureBoundingBox3);
				}

				this.func_35309_a(world1, Block.cobblestone.blockID, 0, 5, 1, 4, structureBoundingBox3);
				this.func_35309_a(world1, Block.cobblestone.blockID, 0, 5, 1, 6, structureBoundingBox3);
				this.func_35309_a(world1, Block.cobblestone.blockID, 0, 5, 3, 4, structureBoundingBox3);
				this.func_35309_a(world1, Block.cobblestone.blockID, 0, 5, 3, 6, structureBoundingBox3);
				this.func_35309_a(world1, Block.cobblestone.blockID, 0, 4, 1, 5, structureBoundingBox3);
				this.func_35309_a(world1, Block.cobblestone.blockID, 0, 6, 1, 5, structureBoundingBox3);
				this.func_35309_a(world1, Block.cobblestone.blockID, 0, 4, 3, 5, structureBoundingBox3);
				this.func_35309_a(world1, Block.cobblestone.blockID, 0, 6, 3, 5, structureBoundingBox3);

				for(i4 = 1; i4 <= 3; ++i4) {
					this.func_35309_a(world1, Block.cobblestone.blockID, 0, 4, i4, 4, structureBoundingBox3);
					this.func_35309_a(world1, Block.cobblestone.blockID, 0, 6, i4, 4, structureBoundingBox3);
					this.func_35309_a(world1, Block.cobblestone.blockID, 0, 4, i4, 6, structureBoundingBox3);
					this.func_35309_a(world1, Block.cobblestone.blockID, 0, 6, i4, 6, structureBoundingBox3);
				}

				this.func_35309_a(world1, Block.torchWood.blockID, 0, 5, 3, 5, structureBoundingBox3);

				for(i4 = 2; i4 <= 8; ++i4) {
					this.func_35309_a(world1, Block.planks.blockID, 0, 2, 3, i4, structureBoundingBox3);
					this.func_35309_a(world1, Block.planks.blockID, 0, 3, 3, i4, structureBoundingBox3);
					if(i4 <= 3 || i4 >= 7) {
						this.func_35309_a(world1, Block.planks.blockID, 0, 4, 3, i4, structureBoundingBox3);
						this.func_35309_a(world1, Block.planks.blockID, 0, 5, 3, i4, structureBoundingBox3);
						this.func_35309_a(world1, Block.planks.blockID, 0, 6, 3, i4, structureBoundingBox3);
					}

					this.func_35309_a(world1, Block.planks.blockID, 0, 7, 3, i4, structureBoundingBox3);
					this.func_35309_a(world1, Block.planks.blockID, 0, 8, 3, i4, structureBoundingBox3);
				}

				this.func_35309_a(world1, Block.ladder.blockID, this.func_35301_c(Block.ladder.blockID, 4), 9, 1, 3, structureBoundingBox3);
				this.func_35309_a(world1, Block.ladder.blockID, this.func_35301_c(Block.ladder.blockID, 4), 9, 2, 3, structureBoundingBox3);
				this.func_35309_a(world1, Block.ladder.blockID, this.func_35301_c(Block.ladder.blockID, 4), 9, 3, 3, structureBoundingBox3);
				this.func_35299_a(world1, structureBoundingBox3, random2, 3, 4, 8, field_35348_c, 1 + random2.nextInt(4));
			}

			return true;
		}
	}
}
