package net.minecraft.src;

import java.util.Random;

public class ChunkProviderGenerate implements IChunkProvider {
	private Random rand;
	private NoiseGeneratorOctaves field_912_k;
	private NoiseGeneratorOctaves field_911_l;
	private NoiseGeneratorOctaves field_910_m;
	private NoiseGeneratorOctaves field_908_o;
	public NoiseGeneratorOctaves field_922_a;
	public NoiseGeneratorOctaves field_921_b;
	public NoiseGeneratorOctaves mobSpawnerNoise;
	private World worldObj;
	private final boolean field_35389_t;
	private double[] field_4180_q;
	private double[] stoneNoise = new double[256];
	private MapGenBase caveGenerator = new MapGenCaves();
	public MapGenStronghold field_35386_d = new MapGenStronghold();
	public MapGenVillage field_35387_e = new MapGenVillage();
	public MapGenMineshaft field_35385_f = new MapGenMineshaft();
	private MapGenBase field_35390_x = new MapGenRavine();
	private BiomeGenBase[] biomesForGeneration;
	double[] field_4185_d;
	double[] field_4184_e;
	double[] field_4183_f;
	double[] field_4182_g;
	double[] field_4181_h;
	float[] field_35388_l;
	int[][] unusedIntArray32x32 = new int[32][32];

	public ChunkProviderGenerate(World world1, long j2, boolean z4) {
		this.worldObj = world1;
		this.field_35389_t = z4;
		this.rand = new Random(j2);
		this.field_912_k = new NoiseGeneratorOctaves(this.rand, 16);
		this.field_911_l = new NoiseGeneratorOctaves(this.rand, 16);
		this.field_910_m = new NoiseGeneratorOctaves(this.rand, 8);
		this.field_908_o = new NoiseGeneratorOctaves(this.rand, 4);
		this.field_922_a = new NoiseGeneratorOctaves(this.rand, 10);
		this.field_921_b = new NoiseGeneratorOctaves(this.rand, 16);
		this.mobSpawnerNoise = new NoiseGeneratorOctaves(this.rand, 8);
	}

	public void generateTerrain(int i1, int i2, byte[] b3) {
		byte b4 = 4;
		this.worldObj.getClass();
		int i5 = 128 / 8;
		this.worldObj.getClass();
		byte b6 = 63;
		int i7 = b4 + 1;
		this.worldObj.getClass();
		int i8 = 128 / 8 + 1;
		int i9 = b4 + 1;
		this.biomesForGeneration = this.worldObj.getWorldChunkManager().func_35557_b(this.biomesForGeneration, i1 * 4 - 2, i2 * 4 - 2, i7 + 5, i9 + 5);
		this.field_4180_q = this.func_4061_a(this.field_4180_q, i1 * b4, 0, i2 * b4, i7, i8, i9);

		for(int i10 = 0; i10 < b4; ++i10) {
			for(int i11 = 0; i11 < b4; ++i11) {
				for(int i12 = 0; i12 < i5; ++i12) {
					double d13 = 0.125D;
					double d15 = this.field_4180_q[((i10 + 0) * i9 + i11 + 0) * i8 + i12 + 0];
					double d17 = this.field_4180_q[((i10 + 0) * i9 + i11 + 1) * i8 + i12 + 0];
					double d19 = this.field_4180_q[((i10 + 1) * i9 + i11 + 0) * i8 + i12 + 0];
					double d21 = this.field_4180_q[((i10 + 1) * i9 + i11 + 1) * i8 + i12 + 0];
					double d23 = (this.field_4180_q[((i10 + 0) * i9 + i11 + 0) * i8 + i12 + 1] - d15) * d13;
					double d25 = (this.field_4180_q[((i10 + 0) * i9 + i11 + 1) * i8 + i12 + 1] - d17) * d13;
					double d27 = (this.field_4180_q[((i10 + 1) * i9 + i11 + 0) * i8 + i12 + 1] - d19) * d13;
					double d29 = (this.field_4180_q[((i10 + 1) * i9 + i11 + 1) * i8 + i12 + 1] - d21) * d13;

					for(int i31 = 0; i31 < 8; ++i31) {
						double d32 = 0.25D;
						double d34 = d15;
						double d36 = d17;
						double d38 = (d19 - d15) * d32;
						double d40 = (d21 - d17) * d32;

						for(int i42 = 0; i42 < 4; ++i42) {
							int i10000 = i42 + i10 * 4;
							this.worldObj.getClass();
							i10000 <<= 11;
							int i10001 = 0 + i11 * 4;
							this.worldObj.getClass();
							int i43 = i10000 | i10001 << 7 | i12 * 8 + i31;
							this.worldObj.getClass();
							int i44 = 1 << 7;
							double d45 = 0.25D;
							double d47 = d34;
							double d49 = (d36 - d34) * d45;

							for(int i51 = 0; i51 < 4; ++i51) {
								int i52 = 0;
								if(i12 * 8 + i31 < b6) {
									i52 = Block.waterStill.blockID;
								}

								if(d47 > 0.0D) {
									i52 = Block.stone.blockID;
								}

								b3[i43] = (byte)i52;
								i43 += i44;
								d47 += d49;
							}

							d34 += d38;
							d36 += d40;
						}

						d15 += d23;
						d17 += d25;
						d19 += d27;
						d21 += d29;
					}
				}
			}
		}

	}

	public void replaceBlocksForBiome(int i1, int i2, byte[] b3, BiomeGenBase[] biomeGenBase4) {
		this.worldObj.getClass();
		byte b5 = 63;
		double d6 = 8.0D / 256D;
		this.stoneNoise = this.field_908_o.generateNoiseOctaves(this.stoneNoise, i1 * 16, i2 * 16, 0, 16, 16, 1, d6 * 2.0D, d6 * 2.0D, d6 * 2.0D);

		for(int i8 = 0; i8 < 16; ++i8) {
			for(int i9 = 0; i9 < 16; ++i9) {
				BiomeGenBase biomeGenBase10 = biomeGenBase4[i9 + i8 * 16];
				int i11 = (int)(this.stoneNoise[i8 + i9 * 16] / 3.0D + 3.0D + this.rand.nextDouble() * 0.25D);
				int i12 = -1;
				byte b13 = biomeGenBase10.topBlock;
				byte b14 = biomeGenBase10.fillerBlock;
				this.worldObj.getClass();

				for(int i15 = 127; i15 >= 0; --i15) {
					int i10000 = i9 * 16 + i8;
					this.worldObj.getClass();
					int i16 = i10000 * 128 + i15;
					if(i15 <= 0 + this.rand.nextInt(5)) {
						b3[i16] = (byte)Block.bedrock.blockID;
					} else {
						byte b17 = b3[i16];
						if(b17 == 0) {
							i12 = -1;
						} else if(b17 == Block.stone.blockID) {
							if(i12 == -1) {
								if(i11 <= 0) {
									b13 = 0;
									b14 = (byte)Block.stone.blockID;
								} else if(i15 >= b5 - 4 && i15 <= b5 + 1) {
									b13 = biomeGenBase10.topBlock;
									b14 = biomeGenBase10.fillerBlock;
								}

								if(i15 < b5 && b13 == 0) {
									b13 = (byte)Block.waterStill.blockID;
								}

								i12 = i11;
								if(i15 >= b5 - 1) {
									b3[i16] = b13;
								} else {
									b3[i16] = b14;
								}
							} else if(i12 > 0) {
								--i12;
								b3[i16] = b14;
								if(i12 == 0 && b14 == Block.sand.blockID) {
									i12 = this.rand.nextInt(4);
									b14 = (byte)Block.sandStone.blockID;
								}
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
		this.rand.setSeed((long)i1 * 341873128712L + (long)i2 * 132897987541L);
		this.worldObj.getClass();
		byte[] b3 = new byte[16 * 128 * 16];
		Chunk chunk4 = new Chunk(this.worldObj, b3, i1, i2);
		this.generateTerrain(i1, i2, b3);
		this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, i1 * 16, i2 * 16, 16, 16);
		this.replaceBlocksForBiome(i1, i2, b3, this.biomesForGeneration);
		this.caveGenerator.generate(this, this.worldObj, i1, i2, b3);
		if(this.field_35389_t) {
			this.field_35386_d.generate(this, this.worldObj, i1, i2, b3);
			this.field_35385_f.generate(this, this.worldObj, i1, i2, b3);
			this.field_35387_e.generate(this, this.worldObj, i1, i2, b3);
		}

		this.field_35390_x.generate(this, this.worldObj, i1, i2, b3);
		chunk4.generateSkylightMap();
		return chunk4;
	}

	private double[] func_4061_a(double[] d1, int i2, int i3, int i4, int i5, int i6, int i7) {
		if(d1 == null) {
			d1 = new double[i5 * i6 * i7];
		}

		if(this.field_35388_l == null) {
			this.field_35388_l = new float[25];

			for(int i8 = -2; i8 <= 2; ++i8) {
				for(int i9 = -2; i9 <= 2; ++i9) {
					float f10 = 10.0F / MathHelper.sqrt_float((float)(i8 * i8 + i9 * i9) + 0.2F);
					this.field_35388_l[i8 + 2 + (i9 + 2) * 5] = f10;
				}
			}
		}

		double d44 = 684.412D;
		double d45 = 684.412D;
		this.field_4182_g = this.field_922_a.func_4109_a(this.field_4182_g, i2, i4, i5, i7, 1.121D, 1.121D, 0.5D);
		this.field_4181_h = this.field_921_b.func_4109_a(this.field_4181_h, i2, i4, i5, i7, 200.0D, 200.0D, 0.5D);
		this.field_4185_d = this.field_910_m.generateNoiseOctaves(this.field_4185_d, i2, i3, i4, i5, i6, i7, d44 / 80.0D, d45 / 160.0D, d44 / 80.0D);
		this.field_4184_e = this.field_912_k.generateNoiseOctaves(this.field_4184_e, i2, i3, i4, i5, i6, i7, d44, d45, d44);
		this.field_4183_f = this.field_911_l.generateNoiseOctaves(this.field_4183_f, i2, i3, i4, i5, i6, i7, d44, d45, d44);
		boolean z43 = false;
		boolean z42 = false;
		int i12 = 0;
		int i13 = 0;

		for(int i14 = 0; i14 < i5; ++i14) {
			for(int i15 = 0; i15 < i7; ++i15) {
				float f16 = 0.0F;
				float f17 = 0.0F;
				float f18 = 0.0F;
				byte b19 = 2;
				BiomeGenBase biomeGenBase20 = this.biomesForGeneration[i14 + 2 + (i15 + 2) * (i5 + 5)];

				for(int i21 = -b19; i21 <= b19; ++i21) {
					for(int i22 = -b19; i22 <= b19; ++i22) {
						BiomeGenBase biomeGenBase23 = this.biomesForGeneration[i14 + i21 + 2 + (i15 + i22 + 2) * (i5 + 5)];
						float f24 = this.field_35388_l[i21 + 2 + (i22 + 2) * 5] / (biomeGenBase23.field_35492_q + 2.0F);
						if(biomeGenBase23.field_35492_q > biomeGenBase20.field_35492_q) {
							f24 /= 2.0F;
						}

						f16 += biomeGenBase23.field_35491_r * f24;
						f17 += biomeGenBase23.field_35492_q * f24;
						f18 += f24;
					}
				}

				f16 /= f18;
				f17 /= f18;
				f16 = f16 * 0.9F + 0.1F;
				f17 = (f17 * 4.0F - 1.0F) / 8.0F;
				double d46 = this.field_4181_h[i13] / 8000.0D;
				if(d46 < 0.0D) {
					d46 = -d46 * 0.3D;
				}

				d46 = d46 * 3.0D - 2.0D;
				if(d46 < 0.0D) {
					d46 /= 2.0D;
					if(d46 < -1.0D) {
						d46 = -1.0D;
					}

					d46 /= 1.4D;
					d46 /= 2.0D;
				} else {
					if(d46 > 1.0D) {
						d46 = 1.0D;
					}

					d46 /= 8.0D;
				}

				++i13;

				for(int i47 = 0; i47 < i6; ++i47) {
					double d48 = (double)f17;
					double d26 = (double)f16;
					d48 += d46 * 0.2D;
					d48 = d48 * (double)i6 / 16.0D;
					double d28 = (double)i6 / 2.0D + d48 * 4.0D;
					double d30 = 0.0D;
					double d10000 = ((double)i47 - d28) * 12.0D * 128.0D;
					this.worldObj.getClass();
					double d32 = d10000 / 128.0D / d26;
					if(d32 < 0.0D) {
						d32 *= 4.0D;
					}

					double d34 = this.field_4184_e[i12] / 512.0D;
					double d36 = this.field_4183_f[i12] / 512.0D;
					double d38 = (this.field_4185_d[i12] / 10.0D + 1.0D) / 2.0D;
					if(d38 < 0.0D) {
						d30 = d34;
					} else if(d38 > 1.0D) {
						d30 = d36;
					} else {
						d30 = d34 + (d36 - d34) * d38;
					}

					d30 -= d32;
					if(i47 > i6 - 4) {
						double d40 = (double)((float)(i47 - (i6 - 4)) / 3.0F);
						d30 = d30 * (1.0D - d40) + -10.0D * d40;
					}

					d1[i12] = d30;
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
		this.rand.setSeed(this.worldObj.getRandomSeed());
		long j7 = this.rand.nextLong() / 2L * 2L + 1L;
		long j9 = this.rand.nextLong() / 2L * 2L + 1L;
		this.rand.setSeed((long)i2 * j7 + (long)i3 * j9 ^ this.worldObj.getRandomSeed());
		boolean z11 = false;
		if(this.field_35389_t) {
			this.field_35386_d.func_35629_a(this.worldObj, this.rand, i2, i3);
			this.field_35385_f.func_35629_a(this.worldObj, this.rand, i2, i3);
			z11 = this.field_35387_e.func_35629_a(this.worldObj, this.rand, i2, i3);
		}

		int i12;
		int i13;
		int i14;
		Random random10000;
		if(!z11 && this.rand.nextInt(4) == 0) {
			i12 = i4 + this.rand.nextInt(16) + 8;
			random10000 = this.rand;
			this.worldObj.getClass();
			i13 = random10000.nextInt(128);
			i14 = i5 + this.rand.nextInt(16) + 8;
			(new WorldGenLakes(Block.waterStill.blockID)).generate(this.worldObj, this.rand, i12, i13, i14);
		}

		if(!z11 && this.rand.nextInt(8) == 0) {
			i12 = i4 + this.rand.nextInt(16) + 8;
			random10000 = this.rand;
			Random random10001 = this.rand;
			this.worldObj.getClass();
			i13 = random10000.nextInt(random10001.nextInt(128 - 8) + 8);
			i14 = i5 + this.rand.nextInt(16) + 8;
			this.worldObj.getClass();
			if(i13 < 63 || this.rand.nextInt(10) == 0) {
				(new WorldGenLakes(Block.lavaStill.blockID)).generate(this.worldObj, this.rand, i12, i13, i14);
			}
		}

		for(i12 = 0; i12 < 8; ++i12) {
			i13 = i4 + this.rand.nextInt(16) + 8;
			random10000 = this.rand;
			this.worldObj.getClass();
			i14 = random10000.nextInt(128);
			int i15 = i5 + this.rand.nextInt(16) + 8;
			if((new WorldGenDungeons()).generate(this.worldObj, this.rand, i13, i14, i15)) {
				;
			}
		}

		biomeGenBase6.func_35477_a(this.worldObj, this.rand, i4, i5);
		SpawnerAnimals.func_35957_a(this.worldObj, biomeGenBase6, i4 + 8, i5 + 8, 16, 16, this.rand);
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
