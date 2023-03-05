package net.minecraft.src;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public abstract class StructureComponent {
	protected StructureBoundingBox field_35024_g;
	protected int field_35025_h;
	protected int field_35026_i;

	protected StructureComponent(int i1) {
		this.field_35026_i = i1;
		this.field_35025_h = -1;
	}

	public void func_35004_a(StructureComponent structureComponent1, List list2, Random random3) {
	}

	public abstract boolean func_35023_a(World world1, Random random2, StructureBoundingBox structureBoundingBox3);

	public StructureBoundingBox func_35021_b() {
		return this.field_35024_g;
	}

	public int func_35012_c() {
		return this.field_35026_i;
	}

	public static StructureComponent func_35020_a(List list0, StructureBoundingBox structureBoundingBox1) {
		Iterator iterator2 = list0.iterator();

		StructureComponent structureComponent3;
		do {
			if(!iterator2.hasNext()) {
				return null;
			}

			structureComponent3 = (StructureComponent)iterator2.next();
		} while(structureComponent3.func_35021_b() == null || !structureComponent3.func_35021_b().func_35740_a(structureBoundingBox1));

		return structureComponent3;
	}

	protected boolean func_35013_a(World world1, StructureBoundingBox structureBoundingBox2) {
		int i3 = Math.max(this.field_35024_g.field_35753_a - 1, structureBoundingBox2.field_35753_a);
		int i4 = Math.max(this.field_35024_g.field_35751_b - 1, structureBoundingBox2.field_35751_b);
		int i5 = Math.max(this.field_35024_g.field_35752_c - 1, structureBoundingBox2.field_35752_c);
		int i6 = Math.min(this.field_35024_g.field_35749_d + 1, structureBoundingBox2.field_35749_d);
		int i7 = Math.min(this.field_35024_g.field_35750_e + 1, structureBoundingBox2.field_35750_e);
		int i8 = Math.min(this.field_35024_g.field_35748_f + 1, structureBoundingBox2.field_35748_f);

		int i9;
		int i10;
		int i11;
		for(i9 = i3; i9 <= i6; ++i9) {
			for(i10 = i5; i10 <= i8; ++i10) {
				i11 = world1.getBlockId(i9, i4, i10);
				if(i11 > 0 && Block.blocksList[i11].blockMaterial.getIsLiquid()) {
					return true;
				}

				i11 = world1.getBlockId(i9, i7, i10);
				if(i11 > 0 && Block.blocksList[i11].blockMaterial.getIsLiquid()) {
					return true;
				}
			}
		}

		for(i9 = i3; i9 <= i6; ++i9) {
			for(i10 = i4; i10 <= i7; ++i10) {
				i11 = world1.getBlockId(i9, i10, i5);
				if(i11 > 0 && Block.blocksList[i11].blockMaterial.getIsLiquid()) {
					return true;
				}

				i11 = world1.getBlockId(i9, i10, i8);
				if(i11 > 0 && Block.blocksList[i11].blockMaterial.getIsLiquid()) {
					return true;
				}
			}
		}

		for(i9 = i5; i9 <= i8; ++i9) {
			for(i10 = i4; i10 <= i7; ++i10) {
				i11 = world1.getBlockId(i3, i10, i9);
				if(i11 > 0 && Block.blocksList[i11].blockMaterial.getIsLiquid()) {
					return true;
				}

				i11 = world1.getBlockId(i6, i10, i9);
				if(i11 > 0 && Block.blocksList[i11].blockMaterial.getIsLiquid()) {
					return true;
				}
			}
		}

		return false;
	}

	protected int func_35017_a(int i1, int i2) {
		switch(this.field_35025_h) {
		case 0:
		case 2:
			return this.field_35024_g.field_35753_a + i1;
		case 1:
			return this.field_35024_g.field_35749_d - i2;
		case 3:
			return this.field_35024_g.field_35753_a + i2;
		default:
			return i1;
		}
	}

	protected int func_35008_a(int i1) {
		return this.field_35025_h == -1 ? i1 : i1 + this.field_35024_g.field_35751_b;
	}

	protected int func_35006_b(int i1, int i2) {
		switch(this.field_35025_h) {
		case 0:
			return this.field_35024_g.field_35752_c + i2;
		case 1:
		case 3:
			return this.field_35024_g.field_35752_c + i1;
		case 2:
			return this.field_35024_g.field_35748_f - i2;
		default:
			return i2;
		}
	}

	protected int func_35009_c(int i1, int i2) {
		if(i1 == Block.rail.blockID) {
			if(this.field_35025_h == 1 || this.field_35025_h == 3) {
				return i2 == 1 ? 0 : 1;
			}
		} else if(i1 != Block.doorWood.blockID && i1 != Block.doorSteel.blockID) {
			if(i1 != Block.stairCompactCobblestone.blockID && i1 != Block.stairCompactPlanks.blockID) {
				if(i1 == Block.ladder.blockID) {
					if(this.field_35025_h == 0) {
						if(i2 == 2) {
							return 3;
						}

						if(i2 == 3) {
							return 2;
						}
					} else if(this.field_35025_h == 1) {
						if(i2 == 2) {
							return 4;
						}

						if(i2 == 3) {
							return 5;
						}

						if(i2 == 4) {
							return 2;
						}

						if(i2 == 5) {
							return 3;
						}
					} else if(this.field_35025_h == 3) {
						if(i2 == 2) {
							return 5;
						}

						if(i2 == 3) {
							return 4;
						}

						if(i2 == 4) {
							return 2;
						}

						if(i2 == 5) {
							return 3;
						}
					}
				} else if(i1 == Block.button.blockID) {
					if(this.field_35025_h == 0) {
						if(i2 == 3) {
							return 4;
						}

						if(i2 == 4) {
							return 3;
						}
					} else if(this.field_35025_h == 1) {
						if(i2 == 3) {
							return 1;
						}

						if(i2 == 4) {
							return 2;
						}

						if(i2 == 2) {
							return 3;
						}

						if(i2 == 1) {
							return 4;
						}
					} else if(this.field_35025_h == 3) {
						if(i2 == 3) {
							return 2;
						}

						if(i2 == 4) {
							return 1;
						}

						if(i2 == 2) {
							return 3;
						}

						if(i2 == 1) {
							return 4;
						}
					}
				}
			} else if(this.field_35025_h == 0) {
				if(i2 == 2) {
					return 3;
				}

				if(i2 == 3) {
					return 2;
				}
			} else if(this.field_35025_h == 1) {
				if(i2 == 0) {
					return 2;
				}

				if(i2 == 1) {
					return 3;
				}

				if(i2 == 2) {
					return 0;
				}

				if(i2 == 3) {
					return 1;
				}
			} else if(this.field_35025_h == 3) {
				if(i2 == 0) {
					return 2;
				}

				if(i2 == 1) {
					return 3;
				}

				if(i2 == 2) {
					return 1;
				}

				if(i2 == 3) {
					return 0;
				}
			}
		} else if(this.field_35025_h == 0) {
			if(i2 == 0) {
				return 2;
			}

			if(i2 == 2) {
				return 0;
			}
		} else {
			if(this.field_35025_h == 1) {
				return i2 + 1 & 3;
			}

			if(this.field_35025_h == 3) {
				return i2 + 3 & 3;
			}
		}

		return i2;
	}

	protected void func_35018_a(World world1, int i2, int i3, int i4, int i5, int i6, StructureBoundingBox structureBoundingBox7) {
		int i8 = this.func_35017_a(i4, i6);
		int i9 = this.func_35008_a(i5);
		int i10 = this.func_35006_b(i4, i6);
		if(structureBoundingBox7.func_35742_b(i8, i9, i10)) {
			world1.setBlockAndMetadata(i8, i9, i10, i2, i3);
		}
	}

	protected int func_35007_a(World world1, int i2, int i3, int i4, StructureBoundingBox structureBoundingBox5) {
		int i6 = this.func_35017_a(i2, i4);
		int i7 = this.func_35008_a(i3);
		int i8 = this.func_35006_b(i2, i4);
		return !structureBoundingBox5.func_35742_b(i6, i7, i8) ? 0 : world1.getBlockId(i6, i7, i8);
	}

	protected void func_35011_a(World world1, StructureBoundingBox structureBoundingBox2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, boolean z11) {
		for(int i12 = i4; i12 <= i7; ++i12) {
			for(int i13 = i3; i13 <= i6; ++i13) {
				for(int i14 = i5; i14 <= i8; ++i14) {
					if(!z11 || this.func_35007_a(world1, i13, i12, i14, structureBoundingBox2) != 0) {
						if(i12 != i4 && i12 != i7 && i13 != i3 && i13 != i6 && i14 != i5 && i14 != i8) {
							this.func_35018_a(world1, i10, 0, i13, i12, i14, structureBoundingBox2);
						} else {
							this.func_35018_a(world1, i9, 0, i13, i12, i14, structureBoundingBox2);
						}
					}
				}
			}
		}

	}

	protected void func_35022_a(World world1, StructureBoundingBox structureBoundingBox2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z9, Random random10, StructurePieceBlockSelector structurePieceBlockSelector11) {
		for(int i12 = i4; i12 <= i7; ++i12) {
			for(int i13 = i3; i13 <= i6; ++i13) {
				for(int i14 = i5; i14 <= i8; ++i14) {
					if(!z9 || this.func_35007_a(world1, i13, i12, i14, structureBoundingBox2) != 0) {
						structurePieceBlockSelector11.func_35706_a(random10, i13, i12, i14, i12 == i4 || i12 == i7 || i13 == i3 || i13 == i6 || i14 == i5 || i14 == i8);
						this.func_35018_a(world1, structurePieceBlockSelector11.func_35707_a(), structurePieceBlockSelector11.func_35708_b(), i13, i12, i14, structureBoundingBox2);
					}
				}
			}
		}

	}

	protected void func_35010_a(World world1, StructureBoundingBox structureBoundingBox2, Random random3, float f4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, boolean z13) {
		for(int i14 = i6; i14 <= i9; ++i14) {
			for(int i15 = i5; i15 <= i8; ++i15) {
				for(int i16 = i7; i16 <= i10; ++i16) {
					if(random3.nextFloat() <= f4 && (!z13 || this.func_35007_a(world1, i15, i14, i16, structureBoundingBox2) != 0)) {
						if(i14 != i6 && i14 != i9 && i15 != i5 && i15 != i8 && i16 != i7 && i16 != i10) {
							this.func_35018_a(world1, i12, 0, i15, i14, i16, structureBoundingBox2);
						} else {
							this.func_35018_a(world1, i11, 0, i15, i14, i16, structureBoundingBox2);
						}
					}
				}
			}
		}

	}

	protected void func_35014_a(World world1, StructureBoundingBox structureBoundingBox2, Random random3, float f4, int i5, int i6, int i7, int i8, int i9) {
		if(random3.nextFloat() < f4) {
			this.func_35018_a(world1, i8, i9, i5, i6, i7, structureBoundingBox2);
		}

	}

	protected void func_35015_a(World world1, StructureBoundingBox structureBoundingBox2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z10) {
		float f11 = (float)(i6 - i3 + 1);
		float f12 = (float)(i7 - i4 + 1);
		float f13 = (float)(i8 - i5 + 1);
		float f14 = (float)i3 + f11 / 2.0F;
		float f15 = (float)i5 + f13 / 2.0F;

		for(int i16 = i4; i16 <= i7; ++i16) {
			float f17 = (float)(i16 - i4) / f12;

			for(int i18 = i3; i18 <= i6; ++i18) {
				float f19 = ((float)i18 - f14) / (f11 * 0.5F);

				for(int i20 = i5; i20 <= i8; ++i20) {
					float f21 = ((float)i20 - f15) / (f13 * 0.5F);
					if(!z10 || this.func_35007_a(world1, i18, i16, i20, structureBoundingBox2) != 0) {
						float f22 = f19 * f19 + f17 * f17 + f21 * f21;
						if(f22 <= 1.05F) {
							this.func_35018_a(world1, i9, 0, i18, i16, i20, structureBoundingBox2);
						}
					}
				}
			}
		}

	}

	protected void func_35016_b(World world1, int i2, int i3, int i4, StructureBoundingBox structureBoundingBox5) {
		int i6 = this.func_35017_a(i2, i4);
		int i7 = this.func_35008_a(i3);
		int i8 = this.func_35006_b(i2, i4);
		if(structureBoundingBox5.func_35742_b(i6, i7, i8)) {
			while(!world1.isAirBlock(i6, i7, i8)) {
				world1.getClass();
				if(i7 >= 127) {
					break;
				}

				world1.setBlockAndMetadata(i6, i7, i8, 0, 0);
				++i7;
			}

		}
	}

	protected void func_35005_b(World world1, int i2, int i3, int i4, int i5, int i6, StructureBoundingBox structureBoundingBox7) {
		int i8 = this.func_35017_a(i4, i6);
		int i9 = this.func_35008_a(i5);
		int i10 = this.func_35006_b(i4, i6);
		if(structureBoundingBox7.func_35742_b(i8, i9, i10)) {
			while((world1.isAirBlock(i8, i9, i10) || world1.getBlockMaterial(i8, i9, i10).getIsLiquid()) && i9 > 1) {
				world1.setBlockAndMetadata(i8, i9, i10, i2, i3);
				--i9;
			}

		}
	}

	protected void func_35003_a(World world1, StructureBoundingBox structureBoundingBox2, Random random3, int i4, int i5, int i6, StructurePieceTreasure[] structurePieceTreasure7, int i8) {
		int i9 = this.func_35017_a(i4, i6);
		int i10 = this.func_35008_a(i5);
		int i11 = this.func_35006_b(i4, i6);
		if(structureBoundingBox2.func_35742_b(i9, i10, i11) && world1.getBlockId(i9, i10, i11) != Block.chest.blockID) {
			world1.setBlockWithNotify(i9, i10, i11, Block.chest.blockID);
			TileEntityChest tileEntityChest12 = (TileEntityChest)world1.getBlockTileEntity(i9, i10, i11);
			if(tileEntityChest12 != null) {
				func_35019_a(random3, structurePieceTreasure7, tileEntityChest12, i8);
			}
		}

	}

	private static void func_35019_a(Random random0, StructurePieceTreasure[] structurePieceTreasure1, TileEntityChest tileEntityChest2, int i3) {
		for(int i4 = 0; i4 < i3; ++i4) {
			StructurePieceTreasure structurePieceTreasure5 = (StructurePieceTreasure)WeightedRandom.func_35735_a(random0, structurePieceTreasure1);
			int i6 = structurePieceTreasure5.field_35595_c + random0.nextInt(structurePieceTreasure5.field_35593_e - structurePieceTreasure5.field_35595_c + 1);
			if(Item.itemsList[structurePieceTreasure5.field_35596_a].getItemStackLimit() >= i6) {
				tileEntityChest2.setInventorySlotContents(random0.nextInt(tileEntityChest2.getSizeInventory()), new ItemStack(structurePieceTreasure5.field_35596_a, i6, structurePieceTreasure5.field_35594_b));
			} else {
				for(int i7 = 0; i7 < i6; ++i7) {
					tileEntityChest2.setInventorySlotContents(random0.nextInt(tileEntityChest2.getSizeInventory()), new ItemStack(structurePieceTreasure5.field_35596_a, 1, structurePieceTreasure5.field_35594_b));
				}
			}
		}

	}

	protected void func_35002_a(World world1, StructureBoundingBox structureBoundingBox2, Random random3, int i4, int i5, int i6, int i7) {
		int i8 = this.func_35017_a(i4, i6);
		int i9 = this.func_35008_a(i5);
		int i10 = this.func_35006_b(i4, i6);
		if(structureBoundingBox2.func_35742_b(i8, i9, i10)) {
			ItemDoor.func_35434_a(world1, i8, i9, i10, i7, Block.doorWood);
		}

	}
}
