package net.minecraft.src;

import java.util.Random;

public class ChunkProviderSky implements IChunkProvider {
	private Random random;
	private NoiseGeneratorOctaves field_28086_k;
	private NoiseGeneratorOctaves field_28085_l;
	private NoiseGeneratorOctaves field_28084_m;
	private NoiseGeneratorOctaves field_28083_n;
	private NoiseGeneratorOctaves field_28082_o;
	public NoiseGeneratorOctaves field_28096_a;
	public NoiseGeneratorOctaves field_28095_b;
	public NoiseGeneratorOctaves field_28094_c;
	private World worldObj;
	private double[] field_28080_q;
	private double[] unusedSandNoise = new double[256];
	private double[] unusedGravelNoise = new double[256];
	private double[] stoneNoise = new double[256];
	private MapGenBase caveGen = new MapGenCaves();
	private BiomeGenBase[] field_28075_v;
	double[] field_28093_d;
	double[] field_28092_e;
	double[] field_28091_f;
	double[] field_28090_g;
	double[] field_28089_h;
	int[][] field_28088_i = new int[32][32];

	public ChunkProviderSky(World world1, long j2) {
		this.worldObj = world1;
		this.random = new Random(j2);
		this.field_28086_k = new NoiseGeneratorOctaves(this.random, 16);
		this.field_28085_l = new NoiseGeneratorOctaves(this.random, 16);
		this.field_28084_m = new NoiseGeneratorOctaves(this.random, 8);
		this.field_28083_n = new NoiseGeneratorOctaves(this.random, 4);
		this.field_28082_o = new NoiseGeneratorOctaves(this.random, 4);
		this.field_28096_a = new NoiseGeneratorOctaves(this.random, 10);
		this.field_28095_b = new NoiseGeneratorOctaves(this.random, 16);
		this.field_28094_c = new NoiseGeneratorOctaves(this.random, 8);
	}

	public void generateTerrain(int i1, int i2, byte[] b3, BiomeGenBase[] biomeGenBase4) {
		byte b5 = 2;
		int i6 = b5 + 1;
		this.worldObj.getClass();
		int i7 = 128 / 4 + 1;
		int i8 = b5 + 1;
		this.field_28080_q = this.func_28073_a(this.field_28080_q, i1 * b5, 0, i2 * b5, i6, i7, i8);

		for(int i9 = 0; i9 < b5; ++i9) {
			for(int i10 = 0; i10 < b5; ++i10) {
				int i11 = 0;

				while(true) {
					this.worldObj.getClass();
					if(i11 >= 128 / 4) {
						break;
					}

					double d12 = 0.25D;
					double d14 = this.field_28080_q[((i9 + 0) * i8 + i10 + 0) * i7 + i11 + 0];
					double d16 = this.field_28080_q[((i9 + 0) * i8 + i10 + 1) * i7 + i11 + 0];
					double d18 = this.field_28080_q[((i9 + 1) * i8 + i10 + 0) * i7 + i11 + 0];
					double d20 = this.field_28080_q[((i9 + 1) * i8 + i10 + 1) * i7 + i11 + 0];
					double d22 = (this.field_28080_q[((i9 + 0) * i8 + i10 + 0) * i7 + i11 + 1] - d14) * d12;
					double d24 = (this.field_28080_q[((i9 + 0) * i8 + i10 + 1) * i7 + i11 + 1] - d16) * d12;
					double d26 = (this.field_28080_q[((i9 + 1) * i8 + i10 + 0) * i7 + i11 + 1] - d18) * d12;
					double d28 = (this.field_28080_q[((i9 + 1) * i8 + i10 + 1) * i7 + i11 + 1] - d20) * d12;

					for(int i30 = 0; i30 < 4; ++i30) {
						double d31 = 0.125D;
						double d33 = d14;
						double d35 = d16;
						double d37 = (d18 - d14) * d31;
						double d39 = (d20 - d16) * d31;

						for(int i41 = 0; i41 < 8; ++i41) {
							int i10000 = i41 + i9 * 8;
							this.worldObj.getClass();
							i10000 <<= 11;
							int i10001 = 0 + i10 * 8;
							this.worldObj.getClass();
							int i42 = i10000 | i10001 << 7 | i11 * 4 + i30;
							this.worldObj.getClass();
							int i43 = 1 << 7;
							double d44 = 0.125D;
							double d46 = d33;
							double d48 = (d35 - d33) * d44;

							for(int i50 = 0; i50 < 8; ++i50) {
								int i51 = 0;
								if(d46 > 0.0D) {
									i51 = Block.stone.blockID;
								}

								b3[i42] = (byte)i51;
								i42 += i43;
								d46 += d48;
							}

							d33 += d37;
							d35 += d39;
						}

						d14 += d22;
						d16 += d24;
						d18 += d26;
						d20 += d28;
					}

					++i11;
				}
			}
		}

	}

	public void replaceBlocksForBiome(int i1, int i2, byte[] b3, BiomeGenBase[] biomeGenBase4) {
		double d5 = 8.0D / 256D;
		this.unusedSandNoise = this.field_28083_n.generateNoiseOctaves(this.unusedSandNoise, i1 * 16, i2 * 16, 0, 16, 16, 1, d5, d5, 1.0D);
		this.unusedGravelNoise = this.field_28083_n.generateNoiseOctaves(this.unusedGravelNoise, i1 * 16, 109, i2 * 16, 16, 1, 16, d5, 1.0D, d5);
		this.stoneNoise = this.field_28082_o.generateNoiseOctaves(this.stoneNoise, i1 * 16, i2 * 16, 0, 16, 16, 1, d5 * 2.0D, d5 * 2.0D, d5 * 2.0D);

		for(int i7 = 0; i7 < 16; ++i7) {
			for(int i8 = 0; i8 < 16; ++i8) {
				BiomeGenBase biomeGenBase9 = biomeGenBase4[i7 + i8 * 16];
				int i10 = (int)(this.stoneNoise[i7 + i8 * 16] / 3.0D + 3.0D + this.random.nextDouble() * 0.25D);
				int i11 = -1;
				byte b12 = biomeGenBase9.topBlock;
				byte b13 = biomeGenBase9.fillerBlock;
				this.worldObj.getClass();

				for(int i14 = 127; i14 >= 0; --i14) {
					int i10000 = i8 * 16 + i7;
					this.worldObj.getClass();
					int i15 = i10000 * 128 + i14;
					byte b16 = b3[i15];
					if(b16 == 0) {
						i11 = -1;
					} else if(b16 == Block.stone.blockID) {
						if(i11 == -1) {
							if(i10 <= 0) {
								b12 = 0;
								b13 = (byte)Block.stone.blockID;
							}

							i11 = i10;
							if(i14 >= 0) {
								b3[i15] = b12;
							} else {
								b3[i15] = b13;
							}
						} else if(i11 > 0) {
							--i11;
							b3[i15] = b13;
							if(i11 == 0 && b13 == Block.sand.blockID) {
								i11 = this.random.nextInt(4);
								b13 = (byte)Block.sandStone.blockID;
							}
						}
					}
				}
			}
		}

	}

	public Chunk loadChunk(int i1, int i2) {
		return this.provideChunk(i1, i2);
	}

	public Chunk provideChunk(int i1, int i2) {
		this.random.setSeed((long)i1 * 341873128712L + (long)i2 * 132897987541L);
		this.worldObj.getClass();
		byte[] b3 = new byte[16 * 128 * 16];
		Chunk chunk4 = new Chunk(this.worldObj, b3, i1, i2);
		this.field_28075_v = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.field_28075_v, i1 * 16, i2 * 16, 16, 16);
		this.generateTerrain(i1, i2, b3, this.field_28075_v);
		this.replaceBlocksForBiome(i1, i2, b3, this.field_28075_v);
		this.caveGen.generate(this, this.worldObj, i1, i2, b3);
		chunk4.generateSkylightMap();
		return chunk4;
	}

	private double[] func_28073_a(double[] d1, int i2, int i3, int i4, int i5, int i6, int i7) {
		if(d1 == null) {
			d1 = new double[i5 * i6 * i7];
		}

		double d8 = 684.412D;
		double d10 = 684.412D;
		this.field_28090_g = this.field_28096_a.func_4109_a(this.field_28090_g, i2, i4, i5, i7, 1.121D, 1.121D, 0.5D);
		this.field_28089_h = this.field_28095_b.func_4109_a(this.field_28089_h, i2, i4, i5, i7, 200.0D, 200.0D, 0.5D);
		d8 *= 2.0D;
		this.field_28093_d = this.field_28084_m.generateNoiseOctaves(this.field_28093_d, i2, i3, i4, i5, i6, i7, d8 / 80.0D, d10 / 160.0D, d8 / 80.0D);
		this.field_28092_e = this.field_28086_k.generateNoiseOctaves(this.field_28092_e, i2, i3, i4, i5, i6, i7, d8, d10, d8);
		this.field_28091_f = this.field_28085_l.generateNoiseOctaves(this.field_28091_f, i2, i3, i4, i5, i6, i7, d8, d10, d8);
		int i12 = 0;
		int i13 = 0;

		for(int i14 = 0; i14 < i5; ++i14) {
			for(int i15 = 0; i15 < i7; ++i15) {
				double d16 = (this.field_28090_g[i13] + 256.0D) / 512.0D;
				if(d16 > 1.0D) {
					d16 = 1.0D;
				}

				double d18 = this.field_28089_h[i13] / 8000.0D;
				if(d18 < 0.0D) {
					d18 = -d18 * 0.3D;
				}

				d18 = d18 * 3.0D - 2.0D;
				if(d18 > 1.0D) {
					d18 = 1.0D;
				}

				d18 /= 8.0D;
				d18 = 0.0D;
				if(d16 < 0.0D) {
					d16 = 0.0D;
				}

				d16 += 0.5D;
				d18 = d18 * (double)i6 / 16.0D;
				++i13;
				double d20 = (double)i6 / 2.0D;

				for(int i22 = 0; i22 < i6; ++i22) {
					double d23 = 0.0D;
					double d25 = ((double)i22 - d20) * 8.0D / d16;
					if(d25 < 0.0D) {
						d25 *= -1.0D;
					}

					double d27 = this.field_28092_e[i12] / 512.0D;
					double d29 = this.field_28091_f[i12] / 512.0D;
					double d31 = (this.field_28093_d[i12] / 10.0D + 1.0D) / 2.0D;
					if(d31 < 0.0D) {
						d23 = d27;
					} else if(d31 > 1.0D) {
						d23 = d29;
					} else {
						d23 = d27 + (d29 - d27) * d31;
					}

					d23 -= 8.0D;
					byte b33 = 32;
					double d34;
					if(i22 > i6 - b33) {
						d34 = (double)((float)(i22 - (i6 - b33)) / ((float)b33 - 1.0F));
						d23 = d23 * (1.0D - d34) + -30.0D * d34;
					}

					b33 = 8;
					if(i22 < b33) {
						d34 = (double)((float)(b33 - i22) / ((float)b33 - 1.0F));
						d23 = d23 * (1.0D - d34) + -30.0D * d34;
					}

					d1[i12] = d23;
					++i12;
				}
			}
		}

		return d1;
	}

	public boolean chunkExists(int i1, int i2) {
		return true;
	}

	public void populate(IChunkProvider iChunkProvider1, int i2, int i3) {
		BlockSand.fallInstantly = true;
		int i4 = i2 * 16;
		int i5 = i3 * 16;
		BiomeGenBase biomeGenBase6 = this.worldObj.getWorldChunkManager().getBiomeGenAt(i4 + 16, i5 + 16);
		this.random.setSeed(this.worldObj.getRandomSeed());
		long j7 = this.random.nextLong() / 2L * 2L + 1L;
		long j9 = this.random.nextLong() / 2L * 2L + 1L;
		this.random.setSeed((long)i2 * j7 + (long)i3 * j9 ^ this.worldObj.getRandomSeed());
		double d11 = 0.25D;
		int i13;
		int i14;
		int i15;
		Random random10000;
		if(this.random.nextInt(4) == 0) {
			i13 = i4 + this.random.nextInt(16) + 8;
			random10000 = this.random;
			this.worldObj.getClass();
			i14 = random10000.nextInt(128);
			i15 = i5 + this.random.nextInt(16) + 8;
			(new WorldGenLakes(Block.waterStill.blockID)).generate(this.worldObj, this.random, i13, i14, i15);
		}

		Random random10001;
		if(this.random.nextInt(8) == 0) {
			i13 = i4 + this.random.nextInt(16) + 8;
			random10000 = this.random;
			random10001 = this.random;
			this.worldObj.getClass();
			i14 = random10000.nextInt(random10001.nextInt(128 - 8) + 8);
			i15 = i5 + this.random.nextInt(16) + 8;
			if(i14 < 64 || this.random.nextInt(10) == 0) {
				(new WorldGenLakes(Block.lavaStill.blockID)).generate(this.worldObj, this.random, i13, i14, i15);
			}
		}

		int i16;
		for(i13 = 0; i13 < 8; ++i13) {
			i14 = i4 + this.random.nextInt(16) + 8;
			random10000 = this.random;
			this.worldObj.getClass();
			i15 = random10000.nextInt(128);
			i16 = i5 + this.random.nextInt(16) + 8;
			(new WorldGenDungeons()).generate(this.worldObj, this.random, i14, i15, i16);
		}

		for(i13 = 0; i13 < 10; ++i13) {
			i14 = i4 + this.random.nextInt(16);
			random10000 = this.random;
			this.worldObj.getClass();
			i15 = random10000.nextInt(128);
			i16 = i5 + this.random.nextInt(16);
			(new WorldGenClay(32)).generate(this.worldObj, this.random, i14, i15, i16);
		}

		for(i13 = 0; i13 < 20; ++i13) {
			i14 = i4 + this.random.nextInt(16);
			random10000 = this.random;
			this.worldObj.getClass();
			i15 = random10000.nextInt(128);
			i16 = i5 + this.random.nextInt(16);
			(new WorldGenMinable(Block.dirt.blockID, 32)).generate(this.worldObj, this.random, i14, i15, i16);
		}

		for(i13 = 0; i13 < 10; ++i13) {
			i14 = i4 + this.random.nextInt(16);
			random10000 = this.random;
			this.worldObj.getClass();
			i15 = random10000.nextInt(128);
			i16 = i5 + this.random.nextInt(16);
			(new WorldGenMinable(Block.gravel.blockID, 32)).generate(this.worldObj, this.random, i14, i15, i16);
		}

		for(i13 = 0; i13 < 20; ++i13) {
			i14 = i4 + this.random.nextInt(16);
			random10000 = this.random;
			this.worldObj.getClass();
			i15 = random10000.nextInt(128);
			i16 = i5 + this.random.nextInt(16);
			(new WorldGenMinable(Block.oreCoal.blockID, 16)).generate(this.worldObj, this.random, i14, i15, i16);
		}

		for(i13 = 0; i13 < 20; ++i13) {
			i14 = i4 + this.random.nextInt(16);
			random10000 = this.random;
			this.worldObj.getClass();
			i15 = random10000.nextInt(128 / 2);
			i16 = i5 + this.random.nextInt(16);
			(new WorldGenMinable(Block.oreIron.blockID, 8)).generate(this.worldObj, this.random, i14, i15, i16);
		}

		for(i13 = 0; i13 < 2; ++i13) {
			i14 = i4 + this.random.nextInt(16);
			random10000 = this.random;
			this.worldObj.getClass();
			i15 = random10000.nextInt(128 / 4);
			i16 = i5 + this.random.nextInt(16);
			(new WorldGenMinable(Block.oreGold.blockID, 8)).generate(this.worldObj, this.random, i14, i15, i16);
		}

		for(i13 = 0; i13 < 8; ++i13) {
			i14 = i4 + this.random.nextInt(16);
			random10000 = this.random;
			this.worldObj.getClass();
			i15 = random10000.nextInt(128 / 8);
			i16 = i5 + this.random.nextInt(16);
			(new WorldGenMinable(Block.oreRedstone.blockID, 7)).generate(this.worldObj, this.random, i14, i15, i16);
		}

		for(i13 = 0; i13 < 1; ++i13) {
			i14 = i4 + this.random.nextInt(16);
			random10000 = this.random;
			this.worldObj.getClass();
			i15 = random10000.nextInt(128 / 8);
			i16 = i5 + this.random.nextInt(16);
			(new WorldGenMinable(Block.oreDiamond.blockID, 7)).generate(this.worldObj, this.random, i14, i15, i16);
		}

		for(i13 = 0; i13 < 1; ++i13) {
			i14 = i4 + this.random.nextInt(16);
			random10000 = this.random;
			this.worldObj.getClass();
			int i21 = random10000.nextInt(128 / 8);
			random10001 = this.random;
			this.worldObj.getClass();
			i15 = i21 + random10001.nextInt(128 / 8);
			i16 = i5 + this.random.nextInt(16);
			(new WorldGenMinable(Block.oreLapis.blockID, 6)).generate(this.worldObj, this.random, i14, i15, i16);
		}

		d11 = 0.5D;
		i13 = (int)((this.field_28094_c.func_806_a((double)i4 * d11, (double)i5 * d11) / 8.0D + this.random.nextDouble() * 4.0D + 4.0D) / 3.0D);
		i14 = 0;
		if(this.random.nextInt(10) == 0) {
			++i14;
		}

		if(biomeGenBase6 == BiomeGenBase.forest) {
			i14 += i13 + 5;
		}

		if(biomeGenBase6 == BiomeGenBase.desert) {
			i14 -= 20;
		}

		if(biomeGenBase6 == BiomeGenBase.field_35485_c) {
			i14 -= 20;
		}

		int i17;
		for(i15 = 0; i15 < i14; ++i15) {
			i16 = i4 + this.random.nextInt(16) + 8;
			i17 = i5 + this.random.nextInt(16) + 8;
			WorldGenerator worldGenerator18 = biomeGenBase6.getRandomWorldGenForTrees(this.random);
			worldGenerator18.func_517_a(1.0D, 1.0D, 1.0D);
			worldGenerator18.generate(this.worldObj, this.random, i16, this.worldObj.getHeightValue(i16, i17), i17);
		}

		int i20;
		for(i15 = 0; i15 < 2; ++i15) {
			i16 = i4 + this.random.nextInt(16) + 8;
			random10000 = this.random;
			this.worldObj.getClass();
			i17 = random10000.nextInt(128);
			i20 = i5 + this.random.nextInt(16) + 8;
			(new WorldGenFlowers(Block.plantYellow.blockID)).generate(this.worldObj, this.random, i16, i17, i20);
		}

		if(this.random.nextInt(2) == 0) {
			i15 = i4 + this.random.nextInt(16) + 8;
			random10000 = this.random;
			this.worldObj.getClass();
			i16 = random10000.nextInt(128);
			i17 = i5 + this.random.nextInt(16) + 8;
			(new WorldGenFlowers(Block.plantRed.blockID)).generate(this.worldObj, this.random, i15, i16, i17);
		}

		if(this.random.nextInt(4) == 0) {
			i15 = i4 + this.random.nextInt(16) + 8;
			random10000 = this.random;
			this.worldObj.getClass();
			i16 = random10000.nextInt(128);
			i17 = i5 + this.random.nextInt(16) + 8;
			(new WorldGenFlowers(Block.mushroomBrown.blockID)).generate(this.worldObj, this.random, i15, i16, i17);
		}

		if(this.random.nextInt(8) == 0) {
			i15 = i4 + this.random.nextInt(16) + 8;
			random10000 = this.random;
			this.worldObj.getClass();
			i16 = random10000.nextInt(128);
			i17 = i5 + this.random.nextInt(16) + 8;
			(new WorldGenFlowers(Block.mushroomRed.blockID)).generate(this.worldObj, this.random, i15, i16, i17);
		}

		for(i15 = 0; i15 < 10; ++i15) {
			i16 = i4 + this.random.nextInt(16) + 8;
			random10000 = this.random;
			this.worldObj.getClass();
			i17 = random10000.nextInt(128);
			i20 = i5 + this.random.nextInt(16) + 8;
			(new WorldGenReed()).generate(this.worldObj, this.random, i16, i17, i20);
		}

		if(this.random.nextInt(32) == 0) {
			i15 = i4 + this.random.nextInt(16) + 8;
			random10000 = this.random;
			this.worldObj.getClass();
			i16 = random10000.nextInt(128);
			i17 = i5 + this.random.nextInt(16) + 8;
			(new WorldGenPumpkin()).generate(this.worldObj, this.random, i15, i16, i17);
		}

		i15 = 0;
		if(biomeGenBase6 == BiomeGenBase.desert) {
			i15 += 10;
		}

		int i19;
		for(i16 = 0; i16 < i15; ++i16) {
			i17 = i4 + this.random.nextInt(16) + 8;
			random10000 = this.random;
			this.worldObj.getClass();
			i20 = random10000.nextInt(128);
			i19 = i5 + this.random.nextInt(16) + 8;
			(new WorldGenCactus()).generate(this.worldObj, this.random, i17, i20, i19);
		}

		for(i16 = 0; i16 < 50; ++i16) {
			i17 = i4 + this.random.nextInt(16) + 8;
			random10000 = this.random;
			random10001 = this.random;
			this.worldObj.getClass();
			i20 = random10000.nextInt(random10001.nextInt(128 - 8) + 8);
			i19 = i5 + this.random.nextInt(16) + 8;
			(new WorldGenLiquids(Block.waterMoving.blockID)).generate(this.worldObj, this.random, i17, i20, i19);
		}

		for(i16 = 0; i16 < 20; ++i16) {
			i17 = i4 + this.random.nextInt(16) + 8;
			random10000 = this.random;
			random10001 = this.random;
			Random random10002 = this.random;
			this.worldObj.getClass();
			i20 = random10000.nextInt(random10001.nextInt(random10002.nextInt(128 - 16) + 8) + 8);
			i19 = i5 + this.random.nextInt(16) + 8;
			(new WorldGenLiquids(Block.lavaMoving.blockID)).generate(this.worldObj, this.random, i17, i20, i19);
		}

		BlockSand.fallInstantly = false;
	}

	public boolean saveChunks(boolean z1, IProgressUpdate iProgressUpdate2) {
		return true;
	}

	public boolean unload100OldestChunks() {
		return false;
	}

	public boolean canSave() {
		return true;
	}

	public String makeString() {
		return "RandomLevelSource";
	}
}
