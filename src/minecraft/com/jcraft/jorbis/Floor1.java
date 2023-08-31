package com.jcraft.jorbis;

import com.jcraft.jogg.Buffer;

class Floor1 extends FuncFloor {
	static final int floor1_rangedb = 140;
	static final int VIF_POSIT = 63;
	private static final float[] FLOOR_fromdB_LOOKUP = new float[]{1.0649863E-7F, 1.1341951E-7F, 1.2079015E-7F, 1.2863978E-7F, 1.369995E-7F, 1.459025E-7F, 1.5538409E-7F, 1.6548181E-7F, 1.7623574E-7F, 1.8768856E-7F, 1.998856E-7F, 2.128753E-7F, 2.2670913E-7F, 2.4144197E-7F, 2.5713223E-7F, 2.7384212E-7F, 2.9163792E-7F, 3.1059022E-7F, 3.307741E-7F, 3.5226967E-7F, 3.7516213E-7F, 3.995423E-7F, 4.255068E-7F, 4.5315863E-7F, 4.8260745E-7F, 5.1397E-7F, 5.4737063E-7F, 5.829419E-7F, 6.208247E-7F, 6.611694E-7F, 7.041359E-7F, 7.4989464E-7F, 7.98627E-7F, 8.505263E-7F, 9.057983E-7F, 9.646621E-7F, 1.0273513E-6F, 1.0941144E-6F, 1.1652161E-6F, 1.2409384E-6F, 1.3215816E-6F, 1.4074654E-6F, 1.4989305E-6F, 1.5963394E-6F, 1.7000785E-6F, 1.8105592E-6F, 1.9282195E-6F, 2.053526E-6F, 2.1869757E-6F, 2.3290977E-6F, 2.4804558E-6F, 2.6416496E-6F, 2.813319E-6F, 2.9961443E-6F, 3.1908505E-6F, 3.39821E-6F, 3.619045E-6F, 3.8542307E-6F, 4.1047006E-6F, 4.371447E-6F, 4.6555283E-6F, 4.958071E-6F, 5.280274E-6F, 5.623416E-6F, 5.988857E-6F, 6.3780467E-6F, 6.7925284E-6F, 7.2339453E-6F, 7.704048E-6F, 8.2047E-6F, 8.737888E-6F, 9.305725E-6F, 9.910464E-6F, 1.0554501E-5F, 1.1240392E-5F, 1.1970856E-5F, 1.2748789E-5F, 1.3577278E-5F, 1.4459606E-5F, 1.5399271E-5F, 1.6400005E-5F, 1.7465769E-5F, 1.8600793E-5F, 1.9809577E-5F, 2.1096914E-5F, 2.2467912E-5F, 2.3928002E-5F, 2.5482977E-5F, 2.7139005E-5F, 2.890265E-5F, 3.078091E-5F, 3.2781227E-5F, 3.4911533E-5F, 3.718028E-5F, 3.9596467E-5F, 4.2169668E-5F, 4.491009E-5F, 4.7828602E-5F, 5.0936775E-5F, 5.424693E-5F, 5.7772202E-5F, 6.152657E-5F, 6.552491E-5F, 6.9783084E-5F, 7.4317984E-5F, 7.914758E-5F, 8.429104E-5F, 8.976875E-5F, 9.560242E-5F, 1.0181521E-4F, 1.0843174E-4F, 1.1547824E-4F, 1.2298267E-4F, 1.3097477E-4F, 1.3948625E-4F, 1.4855085E-4F, 1.5820454E-4F, 1.6848555E-4F, 1.7943469E-4F, 1.9109536E-4F, 2.0351382E-4F, 2.167393E-4F, 2.3082423E-4F, 2.4582449E-4F, 2.6179955E-4F, 2.7881275E-4F, 2.9693157E-4F, 3.1622787E-4F, 3.3677815E-4F, 3.5866388E-4F, 3.8197188E-4F, 4.0679457E-4F, 4.3323037E-4F, 4.613841E-4F, 4.913675E-4F, 5.2329927E-4F, 5.573062E-4F, 5.935231E-4F, 6.320936E-4F, 6.731706E-4F, 7.16917E-4F, 7.635063E-4F, 8.1312325E-4F, 8.6596457E-4F, 9.2223985E-4F, 9.821722E-4F, 0.0010459992F, 0.0011139743F, 0.0011863665F, 0.0012634633F, 0.0013455702F, 0.0014330129F, 0.0015261382F, 0.0016253153F, 0.0017309374F, 0.0018434235F, 0.0019632196F, 0.0020908006F, 0.0022266726F, 0.0023713743F, 0.0025254795F, 0.0026895993F, 0.0028643848F, 0.0030505287F, 0.003248769F, 0.0034598925F, 0.0036847359F, 0.0039241905F, 0.0041792067F, 0.004450795F, 0.004740033F, 0.005048067F, 0.0053761187F, 0.005725489F, 0.0060975635F, 0.0064938175F, 0.0069158226F, 0.0073652514F, 0.007843887F, 0.008353627F, 0.008896492F, 0.009474637F, 0.010090352F, 0.01074608F, 0.011444421F, 0.012188144F, 0.012980198F, 0.013823725F, 0.014722068F, 0.015678791F, 0.016697686F, 0.017782796F, 0.018938422F, 0.020169148F, 0.021479854F, 0.022875736F, 0.02436233F, 0.025945531F, 0.027631618F, 0.029427277F, 0.031339627F, 0.03337625F, 0.035545226F, 0.037855156F, 0.0403152F, 0.042935107F, 0.045725275F, 0.048696756F, 0.05186135F, 0.05523159F, 0.05882085F, 0.062643364F, 0.06671428F, 0.07104975F, 0.075666964F, 0.08058423F, 0.08582105F, 0.09139818F, 0.097337745F, 0.1036633F, 0.11039993F, 0.11757434F, 0.12521498F, 0.13335215F, 0.14201812F, 0.15124726F, 0.16107617F, 0.1715438F, 0.18269168F, 0.19456401F, 0.20720787F, 0.22067343F, 0.23501402F, 0.25028655F, 0.26655158F, 0.28387362F, 0.3023213F, 0.32196787F, 0.34289113F, 0.36517414F, 0.3889052F, 0.41417846F, 0.44109413F, 0.4697589F, 0.50028646F, 0.53279793F, 0.5674221F, 0.6042964F, 0.64356697F, 0.6853896F, 0.72993004F, 0.777365F, 0.8278826F, 0.88168305F, 0.9389798F, 1.0F};

	void pack(Object object1, Buffer buffer2) {
		Floor1.InfoFloor1 floor1$InfoFloor13 = (Floor1.InfoFloor1)object1;
		int i4 = 0;
		int i6 = floor1$InfoFloor13.postlist[1];
		int i7 = -1;
		buffer2.write(floor1$InfoFloor13.partitions, 5);

		int i8;
		for(i8 = 0; i8 < floor1$InfoFloor13.partitions; ++i8) {
			buffer2.write(floor1$InfoFloor13.partitionclass[i8], 4);
			if(i7 < floor1$InfoFloor13.partitionclass[i8]) {
				i7 = floor1$InfoFloor13.partitionclass[i8];
			}
		}

		int i9;
		for(i8 = 0; i8 < i7 + 1; ++i8) {
			buffer2.write(floor1$InfoFloor13.class_dim[i8] - 1, 3);
			buffer2.write(floor1$InfoFloor13.class_subs[i8], 2);
			if(floor1$InfoFloor13.class_subs[i8] != 0) {
				buffer2.write(floor1$InfoFloor13.class_book[i8], 8);
			}

			for(i9 = 0; i9 < 1 << floor1$InfoFloor13.class_subs[i8]; ++i9) {
				buffer2.write(floor1$InfoFloor13.class_subbook[i8][i9] + 1, 8);
			}
		}

		buffer2.write(floor1$InfoFloor13.mult - 1, 2);
		buffer2.write(Util.ilog2(i6), 4);
		int i5 = Util.ilog2(i6);
		i8 = 0;

		for(i9 = 0; i8 < floor1$InfoFloor13.partitions; ++i8) {
			for(i4 += floor1$InfoFloor13.class_dim[floor1$InfoFloor13.partitionclass[i8]]; i9 < i4; ++i9) {
				buffer2.write(floor1$InfoFloor13.postlist[i9 + 2], i5);
			}
		}

	}

	Object unpack(Info info1, Buffer buffer2) {
		int i3 = 0;
		int i4 = -1;
		Floor1.InfoFloor1 floor1$InfoFloor16 = new Floor1.InfoFloor1();
		floor1$InfoFloor16.partitions = buffer2.read(5);

		int i7;
		for(i7 = 0; i7 < floor1$InfoFloor16.partitions; ++i7) {
			floor1$InfoFloor16.partitionclass[i7] = buffer2.read(4);
			if(i4 < floor1$InfoFloor16.partitionclass[i7]) {
				i4 = floor1$InfoFloor16.partitionclass[i7];
			}
		}

		int i8;
		for(i7 = 0; i7 < i4 + 1; ++i7) {
			floor1$InfoFloor16.class_dim[i7] = buffer2.read(3) + 1;
			floor1$InfoFloor16.class_subs[i7] = buffer2.read(2);
			if(floor1$InfoFloor16.class_subs[i7] < 0) {
				floor1$InfoFloor16.free();
				return null;
			}

			if(floor1$InfoFloor16.class_subs[i7] != 0) {
				floor1$InfoFloor16.class_book[i7] = buffer2.read(8);
			}

			if(floor1$InfoFloor16.class_book[i7] < 0 || floor1$InfoFloor16.class_book[i7] >= info1.books) {
				floor1$InfoFloor16.free();
				return null;
			}

			for(i8 = 0; i8 < 1 << floor1$InfoFloor16.class_subs[i7]; ++i8) {
				floor1$InfoFloor16.class_subbook[i7][i8] = buffer2.read(8) - 1;
				if(floor1$InfoFloor16.class_subbook[i7][i8] < -1 || floor1$InfoFloor16.class_subbook[i7][i8] >= info1.books) {
					floor1$InfoFloor16.free();
					return null;
				}
			}
		}

		floor1$InfoFloor16.mult = buffer2.read(2) + 1;
		int i5 = buffer2.read(4);
		i7 = 0;

		for(i8 = 0; i7 < floor1$InfoFloor16.partitions; ++i7) {
			for(i3 += floor1$InfoFloor16.class_dim[floor1$InfoFloor16.partitionclass[i7]]; i8 < i3; ++i8) {
				int i9 = floor1$InfoFloor16.postlist[i8 + 2] = buffer2.read(i5);
				if(i9 < 0 || i9 >= 1 << i5) {
					floor1$InfoFloor16.free();
					return null;
				}
			}
		}

		floor1$InfoFloor16.postlist[0] = 0;
		floor1$InfoFloor16.postlist[1] = 1 << i5;
		return floor1$InfoFloor16;
	}

	Object look(DspState dspState1, InfoMode infoMode2, Object object3) {
		int i4 = 0;
		int[] i5 = new int[65];
		Floor1.InfoFloor1 floor1$InfoFloor16 = (Floor1.InfoFloor1)object3;
		Floor1.LookFloor1 floor1$LookFloor17 = new Floor1.LookFloor1();
		floor1$LookFloor17.vi = floor1$InfoFloor16;
		floor1$LookFloor17.n = floor1$InfoFloor16.postlist[1];

		int i8;
		for(i8 = 0; i8 < floor1$InfoFloor16.partitions; ++i8) {
			i4 += floor1$InfoFloor16.class_dim[floor1$InfoFloor16.partitionclass[i8]];
		}

		i4 += 2;
		floor1$LookFloor17.posts = i4;

		for(i8 = 0; i8 < i4; i5[i8] = i8++) {
		}

		int i9;
		int i10;
		for(i9 = 0; i9 < i4 - 1; ++i9) {
			for(i10 = i9; i10 < i4; ++i10) {
				if(floor1$InfoFloor16.postlist[i5[i9]] > floor1$InfoFloor16.postlist[i5[i10]]) {
					i8 = i5[i10];
					i5[i10] = i5[i9];
					i5[i9] = i8;
				}
			}
		}

		for(i9 = 0; i9 < i4; ++i9) {
			floor1$LookFloor17.forward_index[i9] = i5[i9];
		}

		for(i9 = 0; i9 < i4; floor1$LookFloor17.reverse_index[floor1$LookFloor17.forward_index[i9]] = i9++) {
		}

		for(i9 = 0; i9 < i4; ++i9) {
			floor1$LookFloor17.sorted_index[i9] = floor1$InfoFloor16.postlist[floor1$LookFloor17.forward_index[i9]];
		}

		switch(floor1$InfoFloor16.mult) {
		case 1:
			floor1$LookFloor17.quant_q = 256;
			break;
		case 2:
			floor1$LookFloor17.quant_q = 128;
			break;
		case 3:
			floor1$LookFloor17.quant_q = 86;
			break;
		case 4:
			floor1$LookFloor17.quant_q = 64;
			break;
		default:
			floor1$LookFloor17.quant_q = -1;
		}

		for(i9 = 0; i9 < i4 - 2; ++i9) {
			i10 = 0;
			int i11 = 1;
			int i12 = 0;
			int i13 = floor1$LookFloor17.n;
			int i14 = floor1$InfoFloor16.postlist[i9 + 2];

			for(int i15 = 0; i15 < i9 + 2; ++i15) {
				int i16 = floor1$InfoFloor16.postlist[i15];
				if(i16 > i12 && i16 < i14) {
					i10 = i15;
					i12 = i16;
				}

				if(i16 < i13 && i16 > i14) {
					i11 = i15;
					i13 = i16;
				}
			}

			floor1$LookFloor17.loneighbor[i9] = i10;
			floor1$LookFloor17.hineighbor[i9] = i11;
		}

		return floor1$LookFloor17;
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

	Object inverse1(Block block1, Object object2, Object object3) {
		Floor1.LookFloor1 floor1$LookFloor14 = (Floor1.LookFloor1)object2;
		Floor1.InfoFloor1 floor1$InfoFloor15 = floor1$LookFloor14.vi;
		CodeBook[] codeBook6 = block1.vd.fullbooks;
		if(block1.opb.read(1) == 1) {
			int[] i7 = null;
			if(object3 instanceof int[]) {
				i7 = (int[]) object3;
			}

			int i8;
			if(i7 != null && i7.length >= floor1$LookFloor14.posts) {
				for(i8 = 0; i8 < i7.length; ++i8) {
					i7[i8] = 0;
				}
			} else {
				i7 = new int[floor1$LookFloor14.posts];
			}

			i7[0] = block1.opb.read(Util.ilog(floor1$LookFloor14.quant_q - 1));
			i7[1] = block1.opb.read(Util.ilog(floor1$LookFloor14.quant_q - 1));
			i8 = 0;

			int i9;
			int i10;
			int i12;
			int i13;
			for(i9 = 2; i8 < floor1$InfoFloor15.partitions; ++i8) {
				i10 = floor1$InfoFloor15.partitionclass[i8];
				int i11 = floor1$InfoFloor15.class_dim[i10];
				i12 = floor1$InfoFloor15.class_subs[i10];
				i13 = 1 << i12;
				int i14 = 0;
				if(i12 != 0) {
					i14 = codeBook6[floor1$InfoFloor15.class_book[i10]].decode(block1.opb);
					if(i14 == -1) {
						return null;
					}
				}

				for(int i15 = 0; i15 < i11; ++i15) {
					int i16 = floor1$InfoFloor15.class_subbook[i10][i14 & i13 - 1];
					i14 >>>= i12;
					if(i16 >= 0) {
						if((i7[i9 + i15] = codeBook6[i16].decode(block1.opb)) == -1) {
							return null;
						}
					} else {
						i7[i9 + i15] = 0;
					}
				}

				i9 += i11;
			}

			for(i8 = 2; i8 < floor1$LookFloor14.posts; ++i8) {
				i9 = render_point(floor1$InfoFloor15.postlist[floor1$LookFloor14.loneighbor[i8 - 2]], floor1$InfoFloor15.postlist[floor1$LookFloor14.hineighbor[i8 - 2]], i7[floor1$LookFloor14.loneighbor[i8 - 2]], i7[floor1$LookFloor14.hineighbor[i8 - 2]], floor1$InfoFloor15.postlist[i8]);
				i10 = floor1$LookFloor14.quant_q - i9;
				i12 = (i10 < i9 ? i10 : i9) << 1;
				i13 = i7[i8];
				if(i13 != 0) {
					if(i13 >= i12) {
						if(i10 > i9) {
							i13 -= i9;
						} else {
							i13 = -1 - (i13 - i10);
						}
					} else if((i13 & 1) != 0) {
						i13 = -(i13 + 1 >>> 1);
					} else {
						i13 >>= 1;
					}

					i7[i8] = i13 + i9;
					i7[floor1$LookFloor14.loneighbor[i8 - 2]] &= 32767;
					i7[floor1$LookFloor14.hineighbor[i8 - 2]] &= 32767;
				} else {
					i7[i8] = i9 | 32768;
				}
			}

			return i7;
		} else {
			return null;
		}
	}

	private static int render_point(int i0, int i1, int i2, int i3, int i4) {
		i2 &= 32767;
		i3 &= 32767;
		int i5 = i3 - i2;
		int i6 = i1 - i0;
		int i7 = Math.abs(i5);
		int i8 = i7 * (i4 - i0);
		int i9 = i8 / i6;
		return i5 < 0 ? i2 - i9 : i2 + i9;
	}

	int inverse2(Block block1, Object object2, Object object3, float[] f4) {
		Floor1.LookFloor1 floor1$LookFloor15 = (Floor1.LookFloor1)object2;
		Floor1.InfoFloor1 floor1$InfoFloor16 = floor1$LookFloor15.vi;
		int i7 = block1.vd.vi.blocksizes[block1.mode] / 2;
		if(object3 != null) {
			int[] i15 = (int[]) object3;
			int i9 = 0;
			int i10 = 0;
			int i11 = i15[0] * floor1$InfoFloor16.mult;

			int i12;
			for(i12 = 1; i12 < floor1$LookFloor15.posts; ++i12) {
				int i13 = floor1$LookFloor15.forward_index[i12];
				int i14 = i15[i13] & 32767;
				if(i14 == i15[i13]) {
					i14 *= floor1$InfoFloor16.mult;
					i9 = floor1$InfoFloor16.postlist[i13];
					render_line(i10, i9, i11, i14, f4);
					i10 = i9;
					i11 = i14;
				}
			}

			for(i12 = i9; i12 < i7; ++i12) {
				f4[i12] *= f4[i12 - 1];
			}

			return 1;
		} else {
			for(int i8 = 0; i8 < i7; ++i8) {
				f4[i8] = 0.0F;
			}

			return 0;
		}
	}

	private static void render_line(int i0, int i1, int i2, int i3, float[] f4) {
		int i5 = i3 - i2;
		int i6 = i1 - i0;
		int i7 = Math.abs(i5);
		int i8 = i5 / i6;
		int i9 = i5 < 0 ? i8 - 1 : i8 + 1;
		int i10 = i0;
		int i11 = i2;
		int i12 = 0;
		i7 -= Math.abs(i8 * i6);
		f4[i0] *= FLOOR_fromdB_LOOKUP[i2];

		while(true) {
			++i10;
			if(i10 >= i1) {
				return;
			}

			i12 += i7;
			if(i12 >= i6) {
				i12 -= i6;
				i11 += i9;
			} else {
				i11 += i8;
			}

			f4[i10] *= FLOOR_fromdB_LOOKUP[i11];
		}
	}

	class LookFloor1 {
		static final int VIF_POSIT = 63;
		int[] sorted_index = new int[65];
		int[] forward_index = new int[65];
		int[] reverse_index = new int[65];
		int[] hineighbor = new int[63];
		int[] loneighbor = new int[63];
		int posts;
		int n;
		int quant_q;
		Floor1.InfoFloor1 vi;
		int phrasebits;
		int postbits;
		int frames;

		void free() {
			this.sorted_index = null;
			this.forward_index = null;
			this.reverse_index = null;
			this.hineighbor = null;
			this.loneighbor = null;
		}
	}

	class EchstateFloor1 {
		int[] codewords;
		float[] curve;
		long frameno;
		long codes;
	}

	class Lsfit_acc {
		long x0;
		long x1;
		long xa;
		long ya;
		long x2a;
		long y2a;
		long xya;
		long n;
		long an;
		long un;
		long edgey0;
		long edgey1;
	}

	class InfoFloor1 {
		static final int VIF_POSIT = 63;
		static final int VIF_CLASS = 16;
		static final int VIF_PARTS = 31;
		int partitions;
		int[] partitionclass = new int[31];
		int[] class_dim = new int[16];
		int[] class_subs = new int[16];
		int[] class_book = new int[16];
		int[][] class_subbook = new int[16][];
		int mult;
		int[] postlist = new int[65];
		float maxover;
		float maxunder;
		float maxerr;
		int twofitminsize;
		int twofitminused;
		int twofitweight;
		float twofitatten;
		int unusedminsize;
		int unusedmin_n;
		int n;

		InfoFloor1() {
			for(int i2 = 0; i2 < this.class_subbook.length; ++i2) {
				this.class_subbook[i2] = new int[8];
			}

		}

		void free() {
			this.partitionclass = null;
			this.class_dim = null;
			this.class_subs = null;
			this.class_book = null;
			this.class_subbook = null;
			this.postlist = null;
		}

		Object copy_info() {
			Floor1.InfoFloor1 floor1$InfoFloor11 = this;
			Floor1.InfoFloor1 floor1$InfoFloor12 = Floor1.this.new InfoFloor1();
			floor1$InfoFloor12.partitions = this.partitions;
			System.arraycopy(this.partitionclass, 0, floor1$InfoFloor12.partitionclass, 0, 31);
			System.arraycopy(this.class_dim, 0, floor1$InfoFloor12.class_dim, 0, 16);
			System.arraycopy(this.class_subs, 0, floor1$InfoFloor12.class_subs, 0, 16);
			System.arraycopy(this.class_book, 0, floor1$InfoFloor12.class_book, 0, 16);

			for(int i3 = 0; i3 < 16; ++i3) {
				System.arraycopy(floor1$InfoFloor11.class_subbook[i3], 0, floor1$InfoFloor12.class_subbook[i3], 0, 8);
			}

			floor1$InfoFloor12.mult = floor1$InfoFloor11.mult;
			System.arraycopy(floor1$InfoFloor11.postlist, 0, floor1$InfoFloor12.postlist, 0, 65);
			floor1$InfoFloor12.maxover = floor1$InfoFloor11.maxover;
			floor1$InfoFloor12.maxunder = floor1$InfoFloor11.maxunder;
			floor1$InfoFloor12.maxerr = floor1$InfoFloor11.maxerr;
			floor1$InfoFloor12.twofitminsize = floor1$InfoFloor11.twofitminsize;
			floor1$InfoFloor12.twofitminused = floor1$InfoFloor11.twofitminused;
			floor1$InfoFloor12.twofitweight = floor1$InfoFloor11.twofitweight;
			floor1$InfoFloor12.twofitatten = floor1$InfoFloor11.twofitatten;
			floor1$InfoFloor12.unusedminsize = floor1$InfoFloor11.unusedminsize;
			floor1$InfoFloor12.unusedmin_n = floor1$InfoFloor11.unusedmin_n;
			floor1$InfoFloor12.n = floor1$InfoFloor11.n;
			return floor1$InfoFloor12;
		}
	}
}
