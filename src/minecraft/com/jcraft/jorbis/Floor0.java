package com.jcraft.jorbis;

import com.jcraft.jogg.Buffer;

import net.minecraft.src.MathHelper;

class Floor0 extends FuncFloor {
	float[] lsp = null;

	void pack(Object object1, Buffer buffer2) {
		Floor0.InfoFloor0 floor0$InfoFloor03 = (Floor0.InfoFloor0)object1;
		buffer2.write(floor0$InfoFloor03.order, 8);
		buffer2.write(floor0$InfoFloor03.rate, 16);
		buffer2.write(floor0$InfoFloor03.barkmap, 16);
		buffer2.write(floor0$InfoFloor03.ampbits, 6);
		buffer2.write(floor0$InfoFloor03.ampdB, 8);
		buffer2.write(floor0$InfoFloor03.numbooks - 1, 4);

		for(int i4 = 0; i4 < floor0$InfoFloor03.numbooks; ++i4) {
			buffer2.write(floor0$InfoFloor03.books[i4], 8);
		}

	}

	Object unpack(Info info1, Buffer buffer2) {
		Floor0.InfoFloor0 floor0$InfoFloor03 = new Floor0.InfoFloor0();
		floor0$InfoFloor03.order = buffer2.read(8);
		floor0$InfoFloor03.rate = buffer2.read(16);
		floor0$InfoFloor03.barkmap = buffer2.read(16);
		floor0$InfoFloor03.ampbits = buffer2.read(6);
		floor0$InfoFloor03.ampdB = buffer2.read(8);
		floor0$InfoFloor03.numbooks = buffer2.read(4) + 1;
		if(floor0$InfoFloor03.order >= 1 && floor0$InfoFloor03.rate >= 1 && floor0$InfoFloor03.barkmap >= 1 && floor0$InfoFloor03.numbooks >= 1) {
			for(int i4 = 0; i4 < floor0$InfoFloor03.numbooks; ++i4) {
				floor0$InfoFloor03.books[i4] = buffer2.read(8);
				if(floor0$InfoFloor03.books[i4] < 0 || floor0$InfoFloor03.books[i4] >= info1.books) {
					return null;
				}
			}

			return floor0$InfoFloor03;
		} else {
			return null;
		}
	}

	Object look(DspState dspState1, InfoMode infoMode2, Object object3) {
		Info info5 = dspState1.vi;
		Floor0.InfoFloor0 floor0$InfoFloor06 = (Floor0.InfoFloor0)object3;
		Floor0.LookFloor0 floor0$LookFloor07 = new Floor0.LookFloor0();
		floor0$LookFloor07.m = floor0$InfoFloor06.order;
		floor0$LookFloor07.n = info5.blocksizes[infoMode2.blockflag] / 2;
		floor0$LookFloor07.ln = floor0$InfoFloor06.barkmap;
		floor0$LookFloor07.vi = floor0$InfoFloor06;
		floor0$LookFloor07.lpclook.init(floor0$LookFloor07.ln, floor0$LookFloor07.m);
		float f4 = (float)floor0$LookFloor07.ln / toBARK((float)((double)floor0$InfoFloor06.rate / 2.0D));
		floor0$LookFloor07.linearmap = new int[floor0$LookFloor07.n];

		for(int i8 = 0; i8 < floor0$LookFloor07.n; ++i8) {
			int i9 = MathHelper.floor_float(toBARK((float)((double)floor0$InfoFloor06.rate / 2.0D / (double)floor0$LookFloor07.n * (double)i8)) * f4);
			if(i9 >= floor0$LookFloor07.ln) {
				i9 = floor0$LookFloor07.ln;
			}

			floor0$LookFloor07.linearmap[i8] = i9;
		}

		return floor0$LookFloor07;
	}

	static float toBARK(float f0) {
		return (float)(13.1D * Math.atan(7.4E-4D * (double)f0) + 2.24D * Math.atan((double)(f0 * f0) * 1.85E-8D) + 1.0E-4D * (double)f0);
	}

	Object state(Object object1) {
		Floor0.EchstateFloor0 floor0$EchstateFloor02 = new Floor0.EchstateFloor0();
		Floor0.InfoFloor0 floor0$InfoFloor03 = (Floor0.InfoFloor0)object1;
		floor0$EchstateFloor02.codewords = new int[floor0$InfoFloor03.order];
		floor0$EchstateFloor02.curve = new float[floor0$InfoFloor03.barkmap];
		floor0$EchstateFloor02.frameno = -1L;
		return floor0$EchstateFloor02;
	}

	void free_info(Object object1) {
	}

	void free_look(Object object1) {
	}

	void free_state(Object object1) {
	}

	int forward(Block block1, Object object2, float[] f3, float[] f4, Object object5) {
		return 0;
	}

	int inverse(Block block1, Object object2, float[] f3) {
		Floor0.LookFloor0 floor0$LookFloor04 = (Floor0.LookFloor0)object2;
		Floor0.InfoFloor0 floor0$InfoFloor05 = floor0$LookFloor04.vi;
		int i6 = block1.opb.read(floor0$InfoFloor05.ampbits);
		if(i6 > 0) {
			int i7 = (1 << floor0$InfoFloor05.ampbits) - 1;
			float f8 = (float)i6 / (float)i7 * (float)floor0$InfoFloor05.ampdB;
			int i9 = block1.opb.read(Util.ilog(floor0$InfoFloor05.numbooks));
			if(i9 != -1 && i9 < floor0$InfoFloor05.numbooks) {
				synchronized(this) {
					if(this.lsp != null && this.lsp.length >= floor0$LookFloor04.m) {
						for(int i11 = 0; i11 < floor0$LookFloor04.m; ++i11) {
							this.lsp[i11] = 0.0F;
						}
					} else {
						this.lsp = new float[floor0$LookFloor04.m];
					}

					CodeBook codeBook17 = block1.vd.fullbooks[floor0$InfoFloor05.books[i9]];
					float f12 = 0.0F;

					int i13;
					for(i13 = 0; i13 < floor0$LookFloor04.m; ++i13) {
						f3[i13] = 0.0F;
					}

					int i14;
					for(i13 = 0; i13 < floor0$LookFloor04.m; i13 += codeBook17.dim) {
						if(codeBook17.decodevs(this.lsp, i13, block1.opb, 1, -1) == -1) {
							for(i14 = 0; i14 < floor0$LookFloor04.n; ++i14) {
								f3[i14] = 0.0F;
							}

							return 0;
						}
					}

					for(i13 = 0; i13 < floor0$LookFloor04.m; f12 = this.lsp[i13 - 1]) {
						for(i14 = 0; i14 < codeBook17.dim; ++i13) {
							this.lsp[i13] += f12;
							++i14;
						}
					}

					Lsp.lsp_to_curve(f3, floor0$LookFloor04.linearmap, floor0$LookFloor04.n, floor0$LookFloor04.ln, this.lsp, floor0$LookFloor04.m, f8, (float)floor0$InfoFloor05.ampdB);
					return 1;
				}
			}
		}

		return 0;
	}

	Object inverse1(Block block1, Object object2, Object object3) {
		Floor0.LookFloor0 floor0$LookFloor04 = (Floor0.LookFloor0)object2;
		Floor0.InfoFloor0 floor0$InfoFloor05 = floor0$LookFloor04.vi;
		float[] f6 = null;
		if(object3 instanceof float[]) {
			f6 = (float[])((float[])object3);
		}

		int i7 = block1.opb.read(floor0$InfoFloor05.ampbits);
		if(i7 > 0) {
			int i8 = (1 << floor0$InfoFloor05.ampbits) - 1;
			float f9 = (float)i7 / (float)i8 * (float)floor0$InfoFloor05.ampdB;
			int i10 = block1.opb.read(Util.ilog(floor0$InfoFloor05.numbooks));
			if(i10 != -1 && i10 < floor0$InfoFloor05.numbooks) {
				CodeBook codeBook11 = block1.vd.fullbooks[floor0$InfoFloor05.books[i10]];
				float f12 = 0.0F;
				int i13;
				if(f6 != null && f6.length >= floor0$LookFloor04.m + 1) {
					for(i13 = 0; i13 < f6.length; ++i13) {
						f6[i13] = 0.0F;
					}
				} else {
					f6 = new float[floor0$LookFloor04.m + 1];
				}

				for(i13 = 0; i13 < floor0$LookFloor04.m; i13 += codeBook11.dim) {
					if(codeBook11.decodev_set(f6, i13, block1.opb, codeBook11.dim) == -1) {
						return null;
					}
				}

				for(i13 = 0; i13 < floor0$LookFloor04.m; f12 = f6[i13 - 1]) {
					for(int i14 = 0; i14 < codeBook11.dim; ++i13) {
						f6[i13] += f12;
						++i14;
					}
				}

				f6[floor0$LookFloor04.m] = f9;
				return f6;
			}
		}

		return null;
	}

	int inverse2(Block block1, Object object2, Object object3, float[] f4) {
		Floor0.LookFloor0 floor0$LookFloor05 = (Floor0.LookFloor0)object2;
		Floor0.InfoFloor0 floor0$InfoFloor06 = floor0$LookFloor05.vi;
		if(object3 != null) {
			float[] f9 = (float[])((float[])object3);
			float f8 = f9[floor0$LookFloor05.m];
			Lsp.lsp_to_curve(f4, floor0$LookFloor05.linearmap, floor0$LookFloor05.n, floor0$LookFloor05.ln, f9, floor0$LookFloor05.m, f8, (float)floor0$InfoFloor06.ampdB);
			return 1;
		} else {
			for(int i7 = 0; i7 < floor0$LookFloor05.n; ++i7) {
				f4[i7] = 0.0F;
			}

			return 0;
		}
	}

	static float fromdB(float f0) {
		return (float)Math.exp((double)f0 * 0.11512925D);
	}

	static void lsp_to_lpc(float[] f0, float[] f1, int i2) {
		int i5 = i2 / 2;
		float[] f6 = new float[i5];
		float[] f7 = new float[i5];
		float[] f9 = new float[i5 + 1];
		float[] f10 = new float[i5 + 1];
		float[] f12 = new float[i5];
		float[] f13 = new float[i5];

		int i3;
		for(i3 = 0; i3 < i5; ++i3) {
			f6[i3] = (float)(-2.0D * Math.cos((double)f0[i3 * 2]));
			f7[i3] = (float)(-2.0D * Math.cos((double)f0[i3 * 2 + 1]));
		}

		int i4;
		for(i4 = 0; i4 < i5; ++i4) {
			f9[i4] = 0.0F;
			f10[i4] = 1.0F;
			f12[i4] = 0.0F;
			f13[i4] = 1.0F;
		}

		f10[i4] = 1.0F;
		f9[i4] = 1.0F;

		for(i3 = 1; i3 < i2 + 1; ++i3) {
			float f11 = 0.0F;
			float f8 = 0.0F;

			for(i4 = 0; i4 < i5; ++i4) {
				float f14 = f6[i4] * f10[i4] + f9[i4];
				f9[i4] = f10[i4];
				f10[i4] = f8;
				f8 += f14;
				f14 = f7[i4] * f13[i4] + f12[i4];
				f12[i4] = f13[i4];
				f13[i4] = f11;
				f11 += f14;
			}

			f1[i3 - 1] = (f8 + f10[i4] + f11 - f9[i4]) / 2.0F;
			f10[i4] = f8;
			f9[i4] = f11;
		}

	}

	static void lpc_to_curve(float[] f0, float[] f1, float f2, Floor0.LookFloor0 floor0$LookFloor03, String string4, int i5) {
		float[] f6 = new float[Math.max(floor0$LookFloor03.ln * 2, floor0$LookFloor03.m * 2 + 2)];
		int i7;
		if(f2 == 0.0F) {
			for(i7 = 0; i7 < floor0$LookFloor03.n; ++i7) {
				f0[i7] = 0.0F;
			}

		} else {
			floor0$LookFloor03.lpclook.lpc_to_curve(f6, f1, f2);

			for(i7 = 0; i7 < floor0$LookFloor03.n; ++i7) {
				f0[i7] = f6[floor0$LookFloor03.linearmap[i7]];
			}

		}
	}

	class EchstateFloor0 {
		int[] codewords;
		float[] curve;
		long frameno;
		long codes;
	}

	class InfoFloor0 {
		int order;
		int rate;
		int barkmap;
		int ampbits;
		int ampdB;
		int numbooks;
		int[] books = new int[16];
	}

	class LookFloor0 {
		int n;
		int ln;
		int m;
		int[] linearmap;
		Floor0.InfoFloor0 vi;
		Lpc lpclook = new Lpc();
	}
}
