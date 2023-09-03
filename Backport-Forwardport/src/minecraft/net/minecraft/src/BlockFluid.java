package net.minecraft.src;

import java.util.Random;

public abstract class BlockFluid extends Block {
	protected BlockFluid(int i1, Material material2) {
		super(i1, (material2 == Material.lava ? 14 : 12) * 16 + 13, material2);
		float f3 = 0.0F;
		float f4 = 0.0F;
		this.setBlockBounds(0.0F + f4, 0.0F + f3, 0.0F + f4, 1.0F + f4, 1.0F + f3, 1.0F + f4);
		this.setTickOnLoad(true);
	}

	public int func_35274_i() {
		return 0xFFFFFF;
	}

	public int colorMultiplier(IBlockAccess iBlockAccess1, int i2, int i3, int i4) {
		return 0xFFFFFF;
	}

	public static float getFluidHeightPercent(int i0) {
		if(i0 >= 8) {
			i0 = 0;
		}

		float f1 = (float)(i0 + 1) / 9.0F;
		return f1;
	}

	public int getBlockTextureFromSide(int i1) {
		return i1 != 0 && i1 != 1 ? this.blockIndexInTexture + 1 : this.blockIndexInTexture;
	}

	protected int getFlowDecay(World world1, int i2, int i3, int i4) {
		return world1.getBlockMaterial(i2, i3, i4) != this.blockMaterial ? -1 : world1.getBlockMetadata(i2, i3, i4);
	}

	protected int getEffectiveFlowDecay(IBlockAccess iBlockAccess1, int i2, int i3, int i4) {
		if(iBlockAccess1.getBlockMaterial(i2, i3, i4) != this.blockMaterial) {
			return -1;
		} else {
			int i5 = iBlockAccess1.getBlockMetadata(i2, i3, i4);
			if(i5 >= 8) {
				i5 = 0;
			}

			return i5;
		}
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean canCollideCheck(int i1, boolean z2) {
		return z2 && i1 == 0;
	}

	public boolean getIsBlockSolid(IBlockAccess iBlockAccess1, int i2, int i3, int i4, int i5) {
		Material material6 = iBlockAccess1.getBlockMaterial(i2, i3, i4);
		return material6 == this.blockMaterial ? false : (i5 == 1 ? true : (material6 == Material.ice ? false : super.getIsBlockSolid(iBlockAccess1, i2, i3, i4, i5)));
	}

	public boolean shouldSideBeRendered(IBlockAccess iBlockAccess1, int i2, int i3, int i4, int i5) {
		Material material6 = iBlockAccess1.getBlockMaterial(i2, i3, i4);
		return material6 == this.blockMaterial ? false : (i5 == 1 ? true : (material6 == Material.ice ? false : super.shouldSideBeRendered(iBlockAccess1, i2, i3, i4, i5)));
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world1, int i2, int i3, int i4) {
		return null;
	}

	public int getRenderType() {
		return 4;
	}

	public int idDropped(int i1, Random random2) {
		return 0;
	}

	public int quantityDropped(Random random1) {
		return 0;
	}

	private Vec3D getFlowVector(IBlockAccess iBlockAccess1, int i2, int i3, int i4) {
		Vec3D vec3D5 = Vec3D.createVector(0.0D, 0.0D, 0.0D);
		int i6 = this.getEffectiveFlowDecay(iBlockAccess1, i2, i3, i4);

		for(int i7 = 0; i7 < 4; ++i7) {
			int i8 = i2;
			int i10 = i4;
			if(i7 == 0) {
				i8 = i2 - 1;
			}

			if(i7 == 1) {
				i10 = i4 - 1;
			}

			if(i7 == 2) {
				++i8;
			}

			if(i7 == 3) {
				++i10;
			}

			int i11 = this.getEffectiveFlowDecay(iBlockAccess1, i8, i3, i10);
			int i12;
			if(i11 < 0) {
				if(!iBlockAccess1.getBlockMaterial(i8, i3, i10).getIsSolid()) {
					i11 = this.getEffectiveFlowDecay(iBlockAccess1, i8, i3 - 1, i10);
					if(i11 >= 0) {
						i12 = i11 - (i6 - 8);
						vec3D5 = vec3D5.addVector((double)((i8 - i2) * i12), (double)((i3 - i3) * i12), (double)((i10 - i4) * i12));
					}
				}
			} else if(i11 >= 0) {
				i12 = i11 - i6;
				vec3D5 = vec3D5.addVector((double)((i8 - i2) * i12), (double)((i3 - i3) * i12), (double)((i10 - i4) * i12));
			}
		}

		if(iBlockAccess1.getBlockMetadata(i2, i3, i4) >= 8) {
			boolean z13 = false;
			if(z13 || this.getIsBlockSolid(iBlockAccess1, i2, i3, i4 - 1, 2)) {
				z13 = true;
			}

			if(z13 || this.getIsBlockSolid(iBlockAccess1, i2, i3, i4 + 1, 3)) {
				z13 = true;
			}

			if(z13 || this.getIsBlockSolid(iBlockAccess1, i2 - 1, i3, i4, 4)) {
				z13 = true;
			}

			if(z13 || this.getIsBlockSolid(iBlockAccess1, i2 + 1, i3, i4, 5)) {
				z13 = true;
			}

			if(z13 || this.getIsBlockSolid(iBlockAccess1, i2, i3 + 1, i4 - 1, 2)) {
				z13 = true;
			}

			if(z13 || this.getIsBlockSolid(iBlockAccess1, i2, i3 + 1, i4 + 1, 3)) {
				z13 = true;
			}

			if(z13 || this.getIsBlockSolid(iBlockAccess1, i2 - 1, i3 + 1, i4, 4)) {
				z13 = true;
			}

			if(z13 || this.getIsBlockSolid(iBlockAccess1, i2 + 1, i3 + 1, i4, 5)) {
				z13 = true;
			}

			if(z13) {
				vec3D5 = vec3D5.normalize().addVector(0.0D, -6.0D, 0.0D);
			}
		}

		vec3D5 = vec3D5.normalize();
		return vec3D5;
	}

	public void velocityToAddToEntity(World world1, int i2, int i3, int i4, Entity entity5, Vec3D vec3D6) {
		Vec3D vec3D7 = this.getFlowVector(world1, i2, i3, i4);
		vec3D6.xCoord += vec3D7.xCoord;
		vec3D6.yCoord += vec3D7.yCoord;
		vec3D6.zCoord += vec3D7.zCoord;
	}

	public int tickRate() {
		return this.blockMaterial == Material.water ? 5 : (this.blockMaterial == Material.lava ? 30 : 0);
	}

	public int func_35275_c(IBlockAccess iBlockAccess1, int i2, int i3, int i4) {
		int i5 = iBlockAccess1.func_35451_b(i2, i3, i4, 0);
		int i6 = iBlockAccess1.func_35451_b(i2, i3 + 1, i4, 0);
		int i7 = i5 & 255;
		int i8 = i6 & 255;
		int i9 = i5 >> 16 & 255;
		int i10 = i6 >> 16 & 255;
		return (i7 > i8 ? i7 : i8) | (i9 > i10 ? i9 : i10) << 16;
	}

	public float getBlockBrightness(IBlockAccess iBlockAccess1, int i2, int i3, int i4) {
		float f5 = iBlockAccess1.getLightBrightness(i2, i3, i4);
		float f6 = iBlockAccess1.getLightBrightness(i2, i3 + 1, i4);
		return f5 > f6 ? f5 : f6;
	}

	public void updateTick(World world1, int i2, int i3, int i4, Random random5) {
		super.updateTick(world1, i2, i3, i4, random5);
	}

	public int getRenderBlockPass() {
		return this.blockMaterial == Material.water ? 1 : 0;
	}

	public void randomDisplayTick(World world1, int i2, int i3, int i4, Random random5) {
		int i6;
		if(this.blockMaterial == Material.water) {
			if(random5.nextInt(10) == 0) {
				i6 = world1.getBlockMetadata(i2, i3, i4);
				if(i6 <= 0 || i6 >= 8) {
					world1.spawnParticle("suspended", (double)((float)i2 + random5.nextFloat()), (double)((float)i3 + random5.nextFloat()), (double)((float)i4 + random5.nextFloat()), 0.0D, 0.0D, 0.0D);
				}
			}

			for(i6 = 0; i6 < 0; ++i6) {
				int i7 = random5.nextInt(4);
				int i8 = i2;
				int i9 = i4;
				if(i7 == 0) {
					i8 = i2 - 1;
				}

				if(i7 == 1) {
					++i8;
				}

				if(i7 == 2) {
					i9 = i4 - 1;
				}

				if(i7 == 3) {
					++i9;
				}

				if(world1.getBlockMaterial(i8, i3, i9) == Material.air && (world1.getBlockMaterial(i8, i3 - 1, i9).getIsSolid() || world1.getBlockMaterial(i8, i3 - 1, i9).getIsLiquid())) {
					float f10 = 0.0625F;
					double d11 = (double)((float)i2 + random5.nextFloat());
					double d13 = (double)((float)i3 + random5.nextFloat());
					double d15 = (double)((float)i4 + random5.nextFloat());
					if(i7 == 0) {
						d11 = (double)((float)i2 - f10);
					}

					if(i7 == 1) {
						d11 = (double)((float)(i2 + 1) + f10);
					}

					if(i7 == 2) {
						d15 = (double)((float)i4 - f10);
					}

					if(i7 == 3) {
						d15 = (double)((float)(i4 + 1) + f10);
					}

					double d17 = 0.0D;
					double d19 = 0.0D;
					if(i7 == 0) {
						d17 = (double)(-f10);
					}

					if(i7 == 1) {
						d17 = (double)f10;
					}

					if(i7 == 2) {
						d19 = (double)(-f10);
					}

					if(i7 == 3) {
						d19 = (double)f10;
					}

					world1.spawnParticle("splash", d11, d13, d15, d17, 0.0D, d19);
				}
			}
		}

		if(this.blockMaterial == Material.water && random5.nextInt(64) == 0) {
			i6 = world1.getBlockMetadata(i2, i3, i4);
			if(i6 > 0 && i6 < 8) {
				world1.playSoundEffect((double)((float)i2 + 0.5F), (double)((float)i3 + 0.5F), (double)((float)i4 + 0.5F), "liquid.water", random5.nextFloat() * 0.25F + 0.75F, random5.nextFloat() * 1.0F + 0.5F);
			}
		}

		if(this.blockMaterial == Material.lava && world1.getBlockMaterial(i2, i3 + 1, i4) == Material.air && !world1.isBlockOpaqueCube(i2, i3 + 1, i4) && random5.nextInt(100) == 0) {
			double d21 = (double)((float)i2 + random5.nextFloat());
			double d22 = (double)i3 + this.maxY;
			double d23 = (double)((float)i4 + random5.nextFloat());
			world1.spawnParticle("lava", d21, d22, d23, 0.0D, 0.0D, 0.0D);
		}

	}

	public static double func_293_a(IBlockAccess iBlockAccess0, int i1, int i2, int i3, Material material4) {
		Vec3D vec3D5 = null;
		if(material4 == Material.water) {
			vec3D5 = ((BlockFluid)Block.waterMoving).getFlowVector(iBlockAccess0, i1, i2, i3);
		}

		if(material4 == Material.lava) {
			vec3D5 = ((BlockFluid)Block.lavaMoving).getFlowVector(iBlockAccess0, i1, i2, i3);
		}

		return vec3D5.xCoord == 0.0D && vec3D5.zCoord == 0.0D ? -1000.0D : Math.atan2(vec3D5.zCoord, vec3D5.xCoord) - Math.PI / 2D;
	}

	public void onBlockAdded(World world1, int i2, int i3, int i4) {
		this.checkForHarden(world1, i2, i3, i4);
	}

	public void onNeighborBlockChange(World world1, int i2, int i3, int i4, int i5) {
		this.checkForHarden(world1, i2, i3, i4);
	}

	private void checkForHarden(World world1, int i2, int i3, int i4) {
		if(world1.getBlockId(i2, i3, i4) == this.blockID) {
			if(this.blockMaterial == Material.lava) {
				boolean z5 = false;
				if(z5 || world1.getBlockMaterial(i2, i3, i4 - 1) == Material.water) {
					z5 = true;
				}

				if(z5 || world1.getBlockMaterial(i2, i3, i4 + 1) == Material.water) {
					z5 = true;
				}

				if(z5 || world1.getBlockMaterial(i2 - 1, i3, i4) == Material.water) {
					z5 = true;
				}

				if(z5 || world1.getBlockMaterial(i2 + 1, i3, i4) == Material.water) {
					z5 = true;
				}

				if(z5 || world1.getBlockMaterial(i2, i3 + 1, i4) == Material.water) {
					z5 = true;
				}

				if(z5) {
					int i6 = world1.getBlockMetadata(i2, i3, i4);
					if(i6 == 0) {
						world1.setBlockWithNotify(i2, i3, i4, Block.obsidian.blockID);
					} else if(i6 <= 4) {
						world1.setBlockWithNotify(i2, i3, i4, Block.cobblestone.blockID);
					}

					this.triggerLavaMixEffects(world1, i2, i3, i4);
				}
			}

		}
	}

	protected void triggerLavaMixEffects(World world1, int i2, int i3, int i4) {
		world1.playSoundEffect((double)((float)i2 + 0.5F), (double)((float)i3 + 0.5F), (double)((float)i4 + 0.5F), "random.fizz", 0.5F, 2.6F + (world1.rand.nextFloat() - world1.rand.nextFloat()) * 0.8F);

		for(int i5 = 0; i5 < 8; ++i5) {
			world1.spawnParticle("largesmoke", (double)i2 + Math.random(), (double)i3 + 1.2D, (double)i4 + Math.random(), 0.0D, 0.0D, 0.0D);
		}

	}
}