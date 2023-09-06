package net.minecraft.src;

import java.util.Random;

public class BlockFire extends Block {
	private int[] chanceToEncourageFire = new int[256];
	private int[] abilityToCatchFire = new int[256];

	protected BlockFire(int i1, int i2) {
		super(i1, i2, Material.fire);
		this.setTickOnLoad(true);
	}

	public void initializeBlock() {
		this.setBurnRate(Block.planks.blockID, 5, 20);
		this.setBurnRate(Block.fence.blockID, 5, 20);
		this.setBurnRate(Block.stairCompactPlanks.blockID, 5, 20);
		this.setBurnRate(Block.wood.blockID, 5, 5);
		this.setBurnRate(Block.leaves.blockID, 30, 60);
		this.setBurnRate(Block.bookShelf.blockID, 30, 20);
		this.setBurnRate(Block.tnt.blockID, 15, 100);
		this.setBurnRate(Block.tallGrass.blockID, 60, 100);
		this.setBurnRate(Block.cloth.blockID, 30, 60);
		this.setBurnRate(Block.plantYellow.blockID, 60, 100);
		this.setBurnRate(Block.plantRed.blockID, 60, 100);
		this.setBurnRate(Block.deadBush.blockID, 60, 100);
		this.setBurnRate(Block.sapling.blockID, 60, 100);
	}

	private void setBurnRate(int i1, int i2, int i3) {
		this.chanceToEncourageFire[i1] = i2;
		this.abilityToCatchFire[i1] = i3;
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world1, int i2, int i3, int i4) {
		return null;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public int getRenderType() {
		return 3;
	}

	public int quantityDropped(Random random1) {
		return 0;
	}

	public int tickRate() {
		return 10;
	}

	public void updateTick(World world1, int i2, int i3, int i4, Random random5) {
		boolean z6 = world1.getBlockId(i2, i3 - 1, i4) == Block.netherrack.blockID;
		if(world1.worldProvider instanceof WorldProviderSky && world1.getBlockId(i2, i3 - 1, i4) == Block.bedrock.blockID) {
			z6 = true;
		}

		if(!this.canPlaceBlockAt(world1, i2, i3, i4)) {
			world1.setBlockWithNotify(i2, i3, i4, 0);
		}

		if(!z6 && world1.func_27161_C() && (world1.canBlockBeRainedOn(i2, i3, i4) || world1.canBlockBeRainedOn(i2 - 1, i3, i4) || world1.canBlockBeRainedOn(i2 + 1, i3, i4) || world1.canBlockBeRainedOn(i2, i3, i4 - 1) || world1.canBlockBeRainedOn(i2, i3, i4 + 1))) {
			world1.setBlockWithNotify(i2, i3, i4, 0);
		} else {
			int i7 = world1.getBlockMetadata(i2, i3, i4);
			if(i7 < 15) {
				world1.setBlockMetadataWithNotify(i2, i3, i4, i7 + 1);
				world1.scheduleBlockUpdate(i2, i3, i4, this.blockID, this.tickRate());
			}

			if(!z6 && !this.func_263_h(world1, i2, i3, i4)) {
				if(!world1.isBlockNormalCube(i2, i3 - 1, i4) || i7 > 3) {
					world1.setBlockWithNotify(i2, i3, i4, 0);
				}

			} else if(!z6 && !this.canBlockCatchFire(world1, i2, i3 - 1, i4) && i7 == 15 && random5.nextInt(4) == 0) {
				world1.setBlockWithNotify(i2, i3, i4, 0);
			} else {
				if(i7 % 2 == 0 && i7 > 2) {
					world1.getWorldChunkManager().func_4069_a(i2, i4, 1, 1);
					double d4 = world1.getWorldChunkManager().temperature[0];
					double d6 = world1.getWorldChunkManager().humidity[0] * d4;
					boolean z8 = d6 > 0.85D;
					byte b9 = 0;
					if(z8) {
						b9 = -50;
					}

					this.tryToCatchBlockOnFire(world1, i2 + 1, i3, i4, 300 + b9, random5, 4);
					this.tryToCatchBlockOnFire(world1, i2 - 1, i3, i4, 300 + b9, random5, 5);
					this.tryToCatchBlockOnFire(world1, i2, i3 - 1, i4, 200 + b9, random5, 1);
					this.tryToCatchBlockOnFire(world1, i2, i3 + 1, i4, 250 + b9, random5, 0);
					this.tryToCatchBlockOnFire(world1, i2, i3, i4 - 1, 300 + b9, random5, 3);
					this.tryToCatchBlockOnFire(world1, i2, i3, i4 + 1, 300 + b9, random5, 2);

					for(int i8 = i2 - 1; i8 <= i2 + 1; ++i8) {
						for(int i9 = i4 - 1; i9 <= i4 + 1; ++i9) {
							for(int i10 = i3 - 1; i10 <= i3 + 4; ++i10) {
								if(i8 != i2 || i10 != i3 || i9 != i4) {
									int i11 = 100;
									if(i10 > i3 + 1) {
										i11 += (i10 - (i3 + 1)) * 100;
									}

									int i12 = this.getChanceOfNeighborsEncouragingFire(world1, i8, i10, i9);
									if(i12 > 0 && random5.nextInt(i11) <= i12 && (!world1.func_27161_C() || !world1.canBlockBeRainedOn(i8, i10, i9)) && !world1.canBlockBeRainedOn(i8 - 1, i10, i4) && !world1.canBlockBeRainedOn(i8 + 1, i10, i9) && !world1.canBlockBeRainedOn(i8, i10, i9 - 1) && !world1.canBlockBeRainedOn(i8, i10, i9 + 1)) {
										world1.setBlockWithNotify(i8, i10, i9, this.blockID);
									}
								}
							}
						}
					}
				}

				z6 = world1.getBlockId(i2, i3 - 1, i4) == Block.wood.blockID;
				if(z6) {
					for(int face = 1; z6 && face <= 5; ++face) {
						int x = i2;
						int y = i3 - 1;
						int z = i4;
						if(face == 0) {
							++y;
						} else if(face == 1) {
							--y;
						} else if(face == 2) {
							++z;
						} else if(face == 3) {
							--z;
						} else if(face == 4) {
							++x;
						} else if(face == 5) {
							--x;
						}

						int id = world1.getBlockId(x, y, z);
						if(Block.blocksList[id] == null || !Block.blocksList[id].isOpaqueCube() || !Block.blocksList[id].renderAsNormalBlock() || this.chanceToEncourageFire[id] > 0) {
							z6 = false;
						}
					}
				}

				if(!z6 && i7 == 15) {
					this.tryToCatchBlockOnFire(world1, i2 + 1, i3, i4, 1, random5, 4);
					this.tryToCatchBlockOnFire(world1, i2 - 1, i3, i4, 1, random5, 5);
					this.tryToCatchBlockOnFire(world1, i2, i3 - 1, i4, 1, random5, 1);
					this.tryToCatchBlockOnFire(world1, i2, i3 + 1, i4, 1, random5, 0);
					this.tryToCatchBlockOnFire(world1, i2, i3, i4 - 1, 1, random5, 3);
					this.tryToCatchBlockOnFire(world1, i2, i3, i4 + 1, 1, random5, 2);
				}
			}
		}

	}

	private void tryToCatchBlockOnFire(World world1, int i2, int i3, int i4, int i5, Random random6, int i7) {
		int i8 = this.abilityToCatchFire[world1.getBlockId(i2, i3, i4)];
		if(random6.nextInt(i5) < i8) {
			boolean z9 = world1.getBlockId(i2, i3, i4) == Block.tnt.blockID;
			if(random6.nextInt(2) == 0 && !world1.canBlockBeRainedOn(i2, i3, i4)) {
				world1.setBlockWithNotify(i2, i3, i4, this.blockID);
			} else {
				world1.setBlockWithNotify(i2, i3, i4, 0);
			}

			if(z9) {
				Block.tnt.onBlockDestroyedByPlayer(world1, i2, i3, i4, 1);
			}
		}

	}

	private boolean func_263_h(World world1, int i2, int i3, int i4) {
		return this.canBlockCatchFire(world1, i2 + 1, i3, i4) ? true : (this.canBlockCatchFire(world1, i2 - 1, i3, i4) ? true : (this.canBlockCatchFire(world1, i2, i3 - 1, i4) ? true : (this.canBlockCatchFire(world1, i2, i3 + 1, i4) ? true : (this.canBlockCatchFire(world1, i2, i3, i4 - 1) ? true : this.canBlockCatchFire(world1, i2, i3, i4 + 1)))));
	}

	private int getChanceOfNeighborsEncouragingFire(World world1, int i2, int i3, int i4) {
		byte b5 = 0;
		if(!world1.isAirBlock(i2, i3, i4)) {
			return 0;
		} else {
			int i6 = this.getChanceToEncourageFire(world1, i2 + 1, i3, i4, b5);
			i6 = this.getChanceToEncourageFire(world1, i2 - 1, i3, i4, i6);
			i6 = this.getChanceToEncourageFire(world1, i2, i3 - 1, i4, i6);
			i6 = this.getChanceToEncourageFire(world1, i2, i3 + 1, i4, i6);
			i6 = this.getChanceToEncourageFire(world1, i2, i3, i4 - 1, i6);
			i6 = this.getChanceToEncourageFire(world1, i2, i3, i4 + 1, i6);
			return i6;
		}
	}

	public boolean isCollidable() {
		return false;
	}

	public boolean canBlockCatchFire(IBlockAccess iBlockAccess1, int i2, int i3, int i4) {
		return this.chanceToEncourageFire[iBlockAccess1.getBlockId(i2, i3, i4)] > 0;
	}

	public int getChanceToEncourageFire(World world1, int i2, int i3, int i4, int i5) {
		int i6 = this.chanceToEncourageFire[world1.getBlockId(i2, i3, i4)];
		return i6 > i5 ? i6 : i5;
	}

	public boolean canPlaceBlockAt(World world1, int i2, int i3, int i4) {
		return world1.isBlockNormalCube(i2, i3 - 1, i4) || this.func_263_h(world1, i2, i3, i4);
	}

	public void onNeighborBlockChange(World world1, int i2, int i3, int i4, int i5) {
		if(!world1.isBlockNormalCube(i2, i3 - 1, i4) && !this.func_263_h(world1, i2, i3, i4)) {
			world1.setBlockWithNotify(i2, i3, i4, 0);
		}
	}

	public void onBlockAdded(World world1, int i2, int i3, int i4) {
		if(world1.worldProvider.worldType > 0 || world1.getBlockId(i2, i3 - 1, i4) != Block.obsidian.blockID || !Block.portal.tryToCreatePortal(world1, i2, i3, i4)) {
			if(!world1.isBlockNormalCube(i2, i3 - 1, i4) && !this.func_263_h(world1, i2, i3, i4)) {
				world1.setBlockWithNotify(i2, i3, i4, 0);
			} else {
				world1.scheduleBlockUpdate(i2, i3, i4, this.blockID, this.tickRate());
			}
		}
	}

	public void randomDisplayTick(World world1, int i2, int i3, int i4, Random random5) {
		if(random5.nextInt(24) == 0) {
			world1.playSoundEffect((double)((float)i2 + 0.5F), (double)((float)i3 + 0.5F), (double)((float)i4 + 0.5F), "fire.fire", 1.0F + random5.nextFloat(), random5.nextFloat() * 0.7F + 0.3F);
		}

		int i6;
		float f7;
		float f8;
		float f9;
		if(!world1.isBlockNormalCube(i2, i3 - 1, i4) && !Block.fire.canBlockCatchFire(world1, i2, i3 - 1, i4)) {
			if(Block.fire.canBlockCatchFire(world1, i2 - 1, i3, i4)) {
				for(i6 = 0; i6 < 2; ++i6) {
					f7 = (float)i2 + random5.nextFloat() * 0.1F;
					f8 = (float)i3 + random5.nextFloat();
					f9 = (float)i4 + random5.nextFloat();
					world1.spawnParticle("largesmoke", (double)f7, (double)f8, (double)f9, 0.0D, 0.0D, 0.0D);
				}
			}

			if(Block.fire.canBlockCatchFire(world1, i2 + 1, i3, i4)) {
				for(i6 = 0; i6 < 2; ++i6) {
					f7 = (float)(i2 + 1) - random5.nextFloat() * 0.1F;
					f8 = (float)i3 + random5.nextFloat();
					f9 = (float)i4 + random5.nextFloat();
					world1.spawnParticle("largesmoke", (double)f7, (double)f8, (double)f9, 0.0D, 0.0D, 0.0D);
				}
			}

			if(Block.fire.canBlockCatchFire(world1, i2, i3, i4 - 1)) {
				for(i6 = 0; i6 < 2; ++i6) {
					f7 = (float)i2 + random5.nextFloat();
					f8 = (float)i3 + random5.nextFloat();
					f9 = (float)i4 + random5.nextFloat() * 0.1F;
					world1.spawnParticle("largesmoke", (double)f7, (double)f8, (double)f9, 0.0D, 0.0D, 0.0D);
				}
			}

			if(Block.fire.canBlockCatchFire(world1, i2, i3, i4 + 1)) {
				for(i6 = 0; i6 < 2; ++i6) {
					f7 = (float)i2 + random5.nextFloat();
					f8 = (float)i3 + random5.nextFloat();
					f9 = (float)(i4 + 1) - random5.nextFloat() * 0.1F;
					world1.spawnParticle("largesmoke", (double)f7, (double)f8, (double)f9, 0.0D, 0.0D, 0.0D);
				}
			}

			if(Block.fire.canBlockCatchFire(world1, i2, i3 + 1, i4)) {
				for(i6 = 0; i6 < 2; ++i6) {
					f7 = (float)i2 + random5.nextFloat();
					f8 = (float)(i3 + 1) - random5.nextFloat() * 0.1F;
					f9 = (float)i4 + random5.nextFloat();
					world1.spawnParticle("largesmoke", (double)f7, (double)f8, (double)f9, 0.0D, 0.0D, 0.0D);
				}
			}
		} else {
			for(i6 = 0; i6 < 3; ++i6) {
				f7 = (float)i2 + random5.nextFloat();
				f8 = (float)i3 + random5.nextFloat() * 0.5F + 0.5F;
				f9 = (float)i4 + random5.nextFloat();
				world1.spawnParticle("largesmoke", (double)f7, (double)f8, (double)f9, 0.0D, 0.0D, 0.0D);
			}
		}

	}
}
