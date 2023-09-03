package net.minecraft.src;

import java.util.Random;

public class MapGenRavine extends MapGenBase {
	private float[] field_35540_a = new float[1024];

	protected void func_35539_a(long j1, int i3, int i4, byte[] b5, double d6, double d8, double d10, float f12, float f13, float f14, int i15, int i16, double d17) {
		Random random19 = new Random(j1);
		double d20 = (double)(i3 * 16 + 8);
		double d22 = (double)(i4 * 16 + 8);
		float f24 = 0.0F;
		float f25 = 0.0F;
		if(i16 <= 0) {
			int i26 = this.field_947_a * 16 - 16;
			i16 = i26 - random19.nextInt(i26 / 4);
		}

		boolean z53 = false;
		if(i15 == -1) {
			i15 = i16 / 2;
			z53 = true;
		}

		float f27 = 1.0F;
		int i28 = 0;

		while(true) {
			this.field_35530_d.getClass();
			if(i28 >= 128) {
				for(; i15 < i16; ++i15) {
					double d54 = 1.5D + (double)(MathHelper.sin((float)i15 * (float)Math.PI / (float)i16) * f12 * 1.0F);
					double d30 = d54 * d17;
					d54 *= (double)random19.nextFloat() * 0.25D + 0.75D;
					d30 *= (double)random19.nextFloat() * 0.25D + 0.75D;
					float f32 = MathHelper.cos(f14);
					float f33 = MathHelper.sin(f14);
					d6 += (double)(MathHelper.cos(f13) * f32);
					d8 += (double)f33;
					d10 += (double)(MathHelper.sin(f13) * f32);
					f14 *= 0.7F;
					f14 += f25 * 0.05F;
					f13 += f24 * 0.05F;
					f25 *= 0.8F;
					f24 *= 0.5F;
					f25 += (random19.nextFloat() - random19.nextFloat()) * random19.nextFloat() * 2.0F;
					f24 += (random19.nextFloat() - random19.nextFloat()) * random19.nextFloat() * 4.0F;
					if(z53 || random19.nextInt(4) != 0) {
						double d34 = d6 - d20;
						double d36 = d10 - d22;
						double d38 = (double)(i16 - i15);
						double d40 = (double)(f12 + 2.0F + 16.0F);
						if(d34 * d34 + d36 * d36 - d38 * d38 > d40 * d40) {
							return;
						}

						if(d6 >= d20 - 16.0D - d54 * 2.0D && d10 >= d22 - 16.0D - d54 * 2.0D && d6 <= d20 + 16.0D + d54 * 2.0D && d10 <= d22 + 16.0D + d54 * 2.0D) {
							int i55 = MathHelper.floor_double(d6 - d54) - i3 * 16 - 1;
							int i35 = MathHelper.floor_double(d6 + d54) - i3 * 16 + 1;
							int i56 = MathHelper.floor_double(d8 - d30) - 1;
							int i37 = MathHelper.floor_double(d8 + d30) + 1;
							int i57 = MathHelper.floor_double(d10 - d54) - i4 * 16 - 1;
							int i39 = MathHelper.floor_double(d10 + d54) - i4 * 16 + 1;
							if(i55 < 0) {
								i55 = 0;
							}

							if(i35 > 16) {
								i35 = 16;
							}

							if(i56 < 1) {
								i56 = 1;
							}

							this.field_35530_d.getClass();
							if(i37 > 128 - 8) {
								this.field_35530_d.getClass();
								i37 = 128 - 8;
							}

							if(i57 < 0) {
								i57 = 0;
							}

							if(i39 > 16) {
								i39 = 16;
							}

							boolean z58 = false;

							int i10000;
							int i41;
							int i44;
							for(i41 = i55; !z58 && i41 < i35; ++i41) {
								for(int i42 = i57; !z58 && i42 < i39; ++i42) {
									for(int i43 = i37 + 1; !z58 && i43 >= i56 - 1; --i43) {
										i10000 = i41 * 16 + i42;
										this.field_35530_d.getClass();
										i44 = i10000 * 128 + i43;
										if(i43 >= 0) {
											this.field_35530_d.getClass();
											if(i43 < 128) {
												if(b5[i44] == Block.waterMoving.blockID || b5[i44] == Block.waterStill.blockID) {
													z58 = true;
												}

												if(i43 != i56 - 1 && i41 != i55 && i41 != i35 - 1 && i42 != i57 && i42 != i39 - 1) {
													i43 = i56;
												}
											}
										}
									}
								}
							}

							if(!z58) {
								for(i41 = i55; i41 < i35; ++i41) {
									double d59 = ((double)(i41 + i3 * 16) + 0.5D - d6) / d54;

									for(i44 = i57; i44 < i39; ++i44) {
										double d45 = ((double)(i44 + i4 * 16) + 0.5D - d10) / d54;
										i10000 = i41 * 16 + i44;
										this.field_35530_d.getClass();
										int i47 = i10000 * 128 + i37;
										boolean z48 = false;
										if(d59 * d59 + d45 * d45 < 1.0D) {
											for(int i49 = i37 - 1; i49 >= i56; --i49) {
												double d50 = ((double)i49 + 0.5D - d8) / d30;
												if((d59 * d59 + d45 * d45) * (double)this.field_35540_a[i49] + d50 * d50 / 6.0D < 1.0D) {
													byte b52 = b5[i47];
													if(b52 == Block.grass.blockID) {
														z48 = true;
													}

													if(b52 == Block.stone.blockID || b52 == Block.dirt.blockID || b52 == Block.grass.blockID) {
														if(i49 < 10) {
															b5[i47] = (byte)Block.lavaMoving.blockID;
														} else {
															b5[i47] = 0;
															if(z48 && b5[i47 - 1] == Block.dirt.blockID) {
																b5[i47 - 1] = (byte)Block.grass.blockID;
															}
														}
													}
												}

												--i47;
											}
										}
									}
								}

								if(z53) {
									break;
								}
							}
						}
					}
				}

				return;
			}

			if(i28 == 0 || random19.nextInt(3) == 0) {
				f27 = 1.0F + random19.nextFloat() * random19.nextFloat() * 1.0F;
			}

			this.field_35540_a[i28] = f27 * f27;
			++i28;
		}
	}

	protected void recursiveGenerate(World world1, int i2, int i3, int i4, int i5, byte[] b6) {
		if(this.rand.nextInt(50) == 0) {
			double d7 = (double)(i2 * 16 + this.rand.nextInt(16));
			double d9 = (double)(this.rand.nextInt(this.rand.nextInt(40) + 8) + 20);
			double d11 = (double)(i3 * 16 + this.rand.nextInt(16));
			byte b13 = 1;

			for(int i14 = 0; i14 < b13; ++i14) {
				float f15 = this.rand.nextFloat() * (float)Math.PI * 2.0F;
				float f16 = (this.rand.nextFloat() - 0.5F) * 2.0F / 8.0F;
				float f17 = (this.rand.nextFloat() * 2.0F + this.rand.nextFloat()) * 2.0F;
				this.func_35539_a(this.rand.nextLong(), i4, i5, b6, d7, d9, d11, f17, f15, f16, 0, 0, 3.0D);
			}

		}
	}
}