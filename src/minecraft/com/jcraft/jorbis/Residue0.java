package com.jcraft.jorbis;

import com.jcraft.jogg.Buffer;

class Residue0 extends FuncResidue {
	private static int[][][] _01inverse_partword = new int[2][][];
	static int[][] _2inverse_partword = null;

	void pack(Object object1, Buffer buffer2) {
		Residue0.InfoResidue0 residue0$InfoResidue03 = (Residue0.InfoResidue0)object1;
		int i4 = 0;
		buffer2.write(residue0$InfoResidue03.begin, 24);
		buffer2.write(residue0$InfoResidue03.end, 24);
		buffer2.write(residue0$InfoResidue03.grouping - 1, 24);
		buffer2.write(residue0$InfoResidue03.partitions - 1, 6);
		buffer2.write(residue0$InfoResidue03.groupbook, 8);

		int i5;
		for(i5 = 0; i5 < residue0$InfoResidue03.partitions; ++i5) {
			int i6 = residue0$InfoResidue03.secondstages[i5];
			if(Util.ilog(i6) > 3) {
				buffer2.write(i6, 3);
				buffer2.write(1, 1);
				buffer2.write(i6 >>> 3, 5);
			} else {
				buffer2.write(i6, 4);
			}

			i4 += Util.icount(i6);
		}

		for(i5 = 0; i5 < i4; ++i5) {
			buffer2.write(residue0$InfoResidue03.booklist[i5], 8);
		}

	}

	Object unpack(Info info1, Buffer buffer2) {
		int i3 = 0;
		Residue0.InfoResidue0 residue0$InfoResidue04 = new Residue0.InfoResidue0();
		residue0$InfoResidue04.begin = buffer2.read(24);
		residue0$InfoResidue04.end = buffer2.read(24);
		residue0$InfoResidue04.grouping = buffer2.read(24) + 1;
		residue0$InfoResidue04.partitions = buffer2.read(6) + 1;
		residue0$InfoResidue04.groupbook = buffer2.read(8);

		int i5;
		for(i5 = 0; i5 < residue0$InfoResidue04.partitions; ++i5) {
			int i6 = buffer2.read(3);
			if(buffer2.read(1) != 0) {
				i6 |= buffer2.read(5) << 3;
			}

			residue0$InfoResidue04.secondstages[i5] = i6;
			i3 += Util.icount(i6);
		}

		for(i5 = 0; i5 < i3; ++i5) {
			residue0$InfoResidue04.booklist[i5] = buffer2.read(8);
		}

		if(residue0$InfoResidue04.groupbook >= info1.books) {
			this.free_info(residue0$InfoResidue04);
			return null;
		} else {
			for(i5 = 0; i5 < i3; ++i5) {
				if(residue0$InfoResidue04.booklist[i5] >= info1.books) {
					this.free_info(residue0$InfoResidue04);
					return null;
				}
			}

			return residue0$InfoResidue04;
		}
	}

	Object look(DspState dspState1, InfoMode infoMode2, Object object3) {
		Residue0.InfoResidue0 residue0$InfoResidue04 = (Residue0.InfoResidue0)object3;
		Residue0.LookResidue0 residue0$LookResidue05 = new Residue0.LookResidue0();
		int i6 = 0;
		int i8 = 0;
		residue0$LookResidue05.info = residue0$InfoResidue04;
		residue0$LookResidue05.map = infoMode2.mapping;
		residue0$LookResidue05.parts = residue0$InfoResidue04.partitions;
		residue0$LookResidue05.fullbooks = dspState1.fullbooks;
		residue0$LookResidue05.phrasebook = dspState1.fullbooks[residue0$InfoResidue04.groupbook];
		int i7 = residue0$LookResidue05.phrasebook.dim;
		residue0$LookResidue05.partbooks = new int[residue0$LookResidue05.parts][];

		int i9;
		int i10;
		int i11;
		int i12;
		for(i9 = 0; i9 < residue0$LookResidue05.parts; ++i9) {
			i10 = residue0$InfoResidue04.secondstages[i9];
			i11 = Util.ilog(i10);
			if(i11 != 0) {
				if(i11 > i8) {
					i8 = i11;
				}

				residue0$LookResidue05.partbooks[i9] = new int[i11];

				for(i12 = 0; i12 < i11; ++i12) {
					if((i10 & 1 << i12) != 0) {
						residue0$LookResidue05.partbooks[i9][i12] = residue0$InfoResidue04.booklist[i6++];
					}
				}
			}
		}

		residue0$LookResidue05.partvals = (int)Math.rint(Math.pow(residue0$LookResidue05.parts, i7));
		residue0$LookResidue05.stages = i8;
		residue0$LookResidue05.decodemap = new int[residue0$LookResidue05.partvals][];

		for(i9 = 0; i9 < residue0$LookResidue05.partvals; ++i9) {
			i10 = i9;
			i11 = residue0$LookResidue05.partvals / residue0$LookResidue05.parts;
			residue0$LookResidue05.decodemap[i9] = new int[i7];

			for(i12 = 0; i12 < i7; ++i12) {
				int i13 = i10 / i11;
				i10 -= i13 * i11;
				i11 /= residue0$LookResidue05.parts;
				residue0$LookResidue05.decodemap[i9][i12] = i13;
			}
		}

		return residue0$LookResidue05;
	}

	void free_info(Object object1) {
	}

	void free_look(Object object1) {
	}

	static synchronized int _01inverse(Block block0, Object object1, float[][] f2, int i3, int i4) {
		Residue0.LookResidue0 residue0$LookResidue010 = (Residue0.LookResidue0)object1;
		Residue0.InfoResidue0 residue0$InfoResidue011 = residue0$LookResidue010.info;
		int i12 = residue0$InfoResidue011.grouping;
		int i13 = residue0$LookResidue010.phrasebook.dim;
		int i14 = residue0$InfoResidue011.end - residue0$InfoResidue011.begin;
		int i15 = i14 / i12;
		int i16 = (i15 + i13 - 1) / i13;
		if(_01inverse_partword.length < i3) {
			_01inverse_partword = new int[i3][][];
		}

		int i6;
		for(i6 = 0; i6 < i3; ++i6) {
			if(_01inverse_partword[i6] == null || _01inverse_partword[i6].length < i16) {
				_01inverse_partword[i6] = new int[i16][];
			}
		}

		for(int i9 = 0; i9 < residue0$LookResidue010.stages; ++i9) {
			int i5 = 0;

			for(int i8 = 0; i5 < i15; ++i8) {
				int i17;
				if(i9 == 0) {
					for(i6 = 0; i6 < i3; ++i6) {
						i17 = residue0$LookResidue010.phrasebook.decode(block0.opb);
						if(i17 == -1) {
							return 0;
						}

						_01inverse_partword[i6][i8] = residue0$LookResidue010.decodemap[i17];
						if(_01inverse_partword[i6][i8] == null) {
							return 0;
						}
					}
				}

				for(int i7 = 0; i7 < i13 && i5 < i15; ++i5) {
					for(i6 = 0; i6 < i3; ++i6) {
						i17 = residue0$InfoResidue011.begin + i5 * i12;
						int i18 = _01inverse_partword[i6][i8][i7];
						if((residue0$InfoResidue011.secondstages[i18] & 1 << i9) != 0) {
							CodeBook codeBook19 = residue0$LookResidue010.fullbooks[residue0$LookResidue010.partbooks[i18][i9]];
							if(codeBook19 != null) {
								if(i4 == 0) {
									if(codeBook19.decodevs_add(f2[i6], i17, block0.opb, i12) == -1) {
										return 0;
									}
								} else if(i4 == 1 && codeBook19.decodev_add(f2[i6], i17, block0.opb, i12) == -1) {
									return 0;
								}
							}
						}
					}

					++i7;
				}
			}
		}

		return 0;
	}

	static synchronized int _2inverse(Block block0, Object object1, float[][] f2, int i3) {
		Residue0.LookResidue0 residue0$LookResidue08 = (Residue0.LookResidue0)object1;
		Residue0.InfoResidue0 residue0$InfoResidue09 = residue0$LookResidue08.info;
		int i10 = residue0$InfoResidue09.grouping;
		int i11 = residue0$LookResidue08.phrasebook.dim;
		int i12 = residue0$InfoResidue09.end - residue0$InfoResidue09.begin;
		int i13 = i12 / i10;
		int i14 = (i13 + i11 - 1) / i11;
		if(_2inverse_partword == null || _2inverse_partword.length < i14) {
			_2inverse_partword = new int[i14][];
		}

		for(int i7 = 0; i7 < residue0$LookResidue08.stages; ++i7) {
			int i4 = 0;

			for(int i6 = 0; i4 < i13; ++i6) {
				int i15;
				if(i7 == 0) {
					i15 = residue0$LookResidue08.phrasebook.decode(block0.opb);
					if(i15 == -1) {
						return 0;
					}

					_2inverse_partword[i6] = residue0$LookResidue08.decodemap[i15];
					if(_2inverse_partword[i6] == null) {
						return 0;
					}
				}

				for(int i5 = 0; i5 < i11 && i4 < i13; ++i4) {
					i15 = residue0$InfoResidue09.begin + i4 * i10;
					int i16 = _2inverse_partword[i6][i5];
					if((residue0$InfoResidue09.secondstages[i16] & 1 << i7) != 0) {
						CodeBook codeBook17 = residue0$LookResidue08.fullbooks[residue0$LookResidue08.partbooks[i16][i7]];
						if(codeBook17 != null && codeBook17.decodevv_add(f2, i15, i3, block0.opb, i10) == -1) {
							return 0;
						}
					}

					++i5;
				}
			}
		}

		return 0;
	}

	int inverse(Block block1, Object object2, float[][] f3, int[] i4, int i5) {
		int i6 = 0;

		for(int i7 = 0; i7 < i5; ++i7) {
			if(i4[i7] != 0) {
				f3[i6++] = f3[i7];
			}
		}

		if(i6 != 0) {
			return _01inverse(block1, object2, f3, i6, 0);
		} else {
			return 0;
		}
	}

	class InfoResidue0 {
		int begin;
		int end;
		int grouping;
		int partitions;
		int groupbook;
		int[] secondstages = new int[64];
		int[] booklist = new int[256];
		float[] entmax = new float[64];
		float[] ampmax = new float[64];
		int[] subgrp = new int[64];
		int[] blimit = new int[64];
	}

	class LookResidue0 {
		Residue0.InfoResidue0 info;
		int map;
		int parts;
		int stages;
		CodeBook[] fullbooks;
		CodeBook phrasebook;
		int[][] partbooks;
		int partvals;
		int[][] decodemap;
		int postbits;
		int phrasebits;
		int frames;
	}
}
