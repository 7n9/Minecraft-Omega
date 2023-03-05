package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class ComponentMineshaftCorridor extends StructureComponent {
	private final boolean field_35361_a;
	private final boolean field_35359_b;
	private boolean field_35360_c;
	private int field_35358_d;

	public ComponentMineshaftCorridor(int i1, Random random2, StructureBoundingBox structureBoundingBox3, int i4) {
		super(i1);
		this.field_35317_h = i4;
		this.field_35316_g = structureBoundingBox3;
		this.field_35361_a = random2.nextInt(3) == 0;
		this.field_35359_b = !this.field_35361_a && random2.nextInt(23) == 0;
		if(this.field_35317_h != 2 && this.field_35317_h != 0) {
			this.field_35358_d = structureBoundingBox3.func_35669_b() / 5;
		} else {
			this.field_35358_d = structureBoundingBox3.func_35665_d() / 5;
		}

	}

	public static StructureBoundingBox func_35357_a(List list0, Random random1, int i2, int i3, int i4, int i5) {
		StructureBoundingBox structureBoundingBox6 = new StructureBoundingBox(i2, i3, i4, i2, i3 + 2, i4);

		int i7;
		for(i7 = random1.nextInt(3) + 2; i7 > 0; --i7) {
			int i8 = i7 * 5;
			switch(i5) {
			case 0:
				structureBoundingBox6.field_35674_d = i2 + 2;
				structureBoundingBox6.field_35673_f = i4 + (i8 - 1);
				break;
			case 1:
				structureBoundingBox6.field_35678_a = i2 - (i8 - 1);
				structureBoundingBox6.field_35673_f = i4 + 2;
				break;
			case 2:
				structureBoundingBox6.field_35674_d = i2 + 2;
				structureBoundingBox6.field_35677_c = i4 - (i8 - 1);
				break;
			case 3:
				structureBoundingBox6.field_35674_d = i2 + (i8 - 1);
				structureBoundingBox6.field_35673_f = i4 + 2;
			}

			if(StructureComponent.func_35312_a(list0, structureBoundingBox6) == null) {
				break;
			}
		}

		return i7 > 0 ? structureBoundingBox6 : null;
	}

	public void func_35308_a(StructureComponent structureComponent1, List list2, Random random3) {
		int i4 = this.func_35305_c();
		int i5 = random3.nextInt(4);
		switch(this.field_35317_h) {
		case 0:
			if(i5 <= 1) {
				StructureMineshaftPieces.func_35433_a(structureComponent1, list2, random3, this.field_35316_g.field_35678_a, this.field_35316_g.field_35676_b - 1 + random3.nextInt(3), this.field_35316_g.field_35673_f + 1, this.field_35317_h, i4);
			} else if(i5 == 2) {
				StructureMineshaftPieces.func_35433_a(structureComponent1, list2, random3, this.field_35316_g.field_35678_a - 1, this.field_35316_g.field_35676_b - 1 + random3.nextInt(3), this.field_35316_g.field_35673_f - 3, 1, i4);
			} else {
				StructureMineshaftPieces.func_35433_a(structureComponent1, list2, random3, this.field_35316_g.field_35674_d + 1, this.field_35316_g.field_35676_b - 1 + random3.nextInt(3), this.field_35316_g.field_35673_f - 3, 3, i4);
			}
			break;
		case 1:
			if(i5 <= 1) {
				StructureMineshaftPieces.func_35433_a(structureComponent1, list2, random3, this.field_35316_g.field_35678_a - 1, this.field_35316_g.field_35676_b - 1 + random3.nextInt(3), this.field_35316_g.field_35677_c, this.field_35317_h, i4);
			} else if(i5 == 2) {
				StructureMineshaftPieces.func_35433_a(structureComponent1, list2, random3, this.field_35316_g.field_35678_a, this.field_35316_g.field_35676_b - 1 + random3.nextInt(3), this.field_35316_g.field_35677_c - 1, 2, i4);
			} else {
				StructureMineshaftPieces.func_35433_a(structureComponent1, list2, random3, this.field_35316_g.field_35678_a, this.field_35316_g.field_35676_b - 1 + random3.nextInt(3), this.field_35316_g.field_35673_f + 1, 0, i4);
			}
			break;
		case 2:
			if(i5 <= 1) {
				StructureMineshaftPieces.func_35433_a(structureComponent1, list2, random3, this.field_35316_g.field_35678_a, this.field_35316_g.field_35676_b - 1 + random3.nextInt(3), this.field_35316_g.field_35677_c - 1, this.field_35317_h, i4);
			} else if(i5 == 2) {
				StructureMineshaftPieces.func_35433_a(structureComponent1, list2, random3, this.field_35316_g.field_35678_a - 1, this.field_35316_g.field_35676_b - 1 + random3.nextInt(3), this.field_35316_g.field_35677_c, 1, i4);
			} else {
				StructureMineshaftPieces.func_35433_a(structureComponent1, list2, random3, this.field_35316_g.field_35674_d + 1, this.field_35316_g.field_35676_b - 1 + random3.nextInt(3), this.field_35316_g.field_35677_c, 3, i4);
			}
			break;
		case 3:
			if(i5 <= 1) {
				StructureMineshaftPieces.func_35433_a(structureComponent1, list2, random3, this.field_35316_g.field_35674_d + 1, this.field_35316_g.field_35676_b - 1 + random3.nextInt(3), this.field_35316_g.field_35677_c, this.field_35317_h, i4);
			} else if(i5 == 2) {
				StructureMineshaftPieces.func_35433_a(structureComponent1, list2, random3, this.field_35316_g.field_35674_d - 3, this.field_35316_g.field_35676_b - 1 + random3.nextInt(3), this.field_35316_g.field_35677_c - 1, 2, i4);
			} else {
				StructureMineshaftPieces.func_35433_a(structureComponent1, list2, random3, this.field_35316_g.field_35674_d - 3, this.field_35316_g.field_35676_b - 1 + random3.nextInt(3), this.field_35316_g.field_35673_f + 1, 0, i4);
			}
		}

		if(i4 < 10) {
			int i6;
			int i7;
			if(this.field_35317_h != 2 && this.field_35317_h != 0) {
				for(i6 = this.field_35316_g.field_35678_a + 3; i6 + 3 <= this.field_35316_g.field_35674_d; i6 += 5) {
					i7 = random3.nextInt(5);
					if(i7 == 0) {
						StructureMineshaftPieces.func_35433_a(structureComponent1, list2, random3, i6, this.field_35316_g.field_35676_b, this.field_35316_g.field_35677_c - 1, 2, i4 + 1);
					} else if(i7 == 1) {
						StructureMineshaftPieces.func_35433_a(structureComponent1, list2, random3, i6, this.field_35316_g.field_35676_b, this.field_35316_g.field_35673_f + 1, 0, i4 + 1);
					}
				}
			} else {
				for(i6 = this.field_35316_g.field_35677_c + 3; i6 + 3 <= this.field_35316_g.field_35673_f; i6 += 5) {
					i7 = random3.nextInt(5);
					if(i7 == 0) {
						StructureMineshaftPieces.func_35433_a(structureComponent1, list2, random3, this.field_35316_g.field_35678_a - 1, this.field_35316_g.field_35676_b, i6, 1, i4 + 1);
					} else if(i7 == 1) {
						StructureMineshaftPieces.func_35433_a(structureComponent1, list2, random3, this.field_35316_g.field_35674_d + 1, this.field_35316_g.field_35676_b, i6, 3, i4 + 1);
					}
				}
			}
		}

	}

	public boolean func_35310_a(World world1, Random random2, StructureBoundingBox structureBoundingBox3) {
		if(this.func_35295_a(world1, structureBoundingBox3)) {
			return false;
		} else {
			int i8 = this.field_35358_d * 5 - 1;
			this.func_35294_a(world1, structureBoundingBox3, 0, 0, 0, 2, 1, i8, 0, 0, false);
			this.func_35315_a(world1, structureBoundingBox3, random2, 0.8F, 0, 2, 0, 2, 2, i8, 0, 0, false);
			if(this.field_35359_b) {
				this.func_35315_a(world1, structureBoundingBox3, random2, 0.6F, 0, 0, 0, 2, 1, i8, Block.web.blockID, 0, false);
			}

			int i9;
			int i10;
			for(i9 = 0; i9 < this.field_35358_d; ++i9) {
				i10 = 2 + i9 * 5;
				this.func_35294_a(world1, structureBoundingBox3, 0, 0, i10, 0, 2, i10, Block.planks.blockID, 0, false);
				this.func_35294_a(world1, structureBoundingBox3, 2, 0, i10, 2, 2, i10, Block.planks.blockID, 0, false);
				if(random2.nextInt(4) != 0) {
					this.func_35294_a(world1, structureBoundingBox3, 1, 2, i10, 1, 2, i10, Block.planks.blockID, 0, false);
				}

				this.func_35302_a(world1, structureBoundingBox3, random2, 0.1F, 0, 2, i10 - 1, Block.web.blockID, 0);
				this.func_35302_a(world1, structureBoundingBox3, random2, 0.1F, 2, 2, i10 - 1, Block.web.blockID, 0);
				this.func_35302_a(world1, structureBoundingBox3, random2, 0.1F, 0, 2, i10 + 1, Block.web.blockID, 0);
				this.func_35302_a(world1, structureBoundingBox3, random2, 0.1F, 2, 2, i10 + 1, Block.web.blockID, 0);
				this.func_35302_a(world1, structureBoundingBox3, random2, 0.05F, 0, 2, i10 - 2, Block.web.blockID, 0);
				this.func_35302_a(world1, structureBoundingBox3, random2, 0.05F, 2, 2, i10 - 2, Block.web.blockID, 0);
				this.func_35302_a(world1, structureBoundingBox3, random2, 0.05F, 0, 2, i10 + 2, Block.web.blockID, 0);
				this.func_35302_a(world1, structureBoundingBox3, random2, 0.05F, 2, 2, i10 + 2, Block.web.blockID, 0);
				this.func_35302_a(world1, structureBoundingBox3, random2, 0.05F, 1, 2, i10 - 1, Block.torchWood.blockID, 0);
				this.func_35302_a(world1, structureBoundingBox3, random2, 0.05F, 1, 2, i10 + 1, Block.torchWood.blockID, 0);
				if(random2.nextInt(100) == 0) {
					this.func_35299_a(world1, structureBoundingBox3, random2, 2, 0, i10 - 1, StructureMineshaftPieces.func_35432_a(), 3 + random2.nextInt(4));
				}

				if(random2.nextInt(100) == 0) {
					this.func_35299_a(world1, structureBoundingBox3, random2, 0, 0, i10 + 1, StructureMineshaftPieces.func_35432_a(), 3 + random2.nextInt(4));
				}

				if(this.field_35359_b && !this.field_35360_c) {
					int i11 = this.func_35300_a(0);
					int i12 = i10 - 1 + random2.nextInt(3);
					int i13 = this.func_35306_a(1, i12);
					i12 = this.func_35296_b(1, i12);
					if(structureBoundingBox3.func_35667_b(i13, i11, i12)) {
						this.field_35360_c = true;
						world1.setBlockWithNotify(i13, i11, i12, Block.mobSpawner.blockID);
						TileEntityMobSpawner tileEntityMobSpawner14 = (TileEntityMobSpawner)world1.getBlockTileEntity(i13, i11, i12);
						if(tileEntityMobSpawner14 != null) {
							tileEntityMobSpawner14.setMobID("CaveSpider");
						}
					}
				}
			}

			if(this.field_35361_a) {
				for(i9 = 0; i9 <= i8; ++i9) {
					i10 = this.func_35297_a(world1, 1, -1, i9, structureBoundingBox3);
					if(i10 > 0 && Block.opaqueCubeLookup[i10]) {
						this.func_35302_a(world1, structureBoundingBox3, random2, 0.7F, 1, 0, i9, Block.rail.blockID, this.func_35301_c(Block.rail.blockID, 0));
					}
				}
			}

			return true;
		}
	}
}
