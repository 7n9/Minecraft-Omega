package com.jcraft.jorbis;

import com.jcraft.jogg.Buffer;

class Mapping0 extends FuncMapping {
	static int seq = 0;
	float[][] pcmbundle = (float[][])null;
	int[] zerobundle = null;
	int[] nonzero = null;
	Object[] floormemo = null;

	void free_info(Object object1) {
	}

	void free_look(Object object1) {
	}

	Object look(DspState dspState1, InfoMode infoMode2, Object object3) {
		Info info4 = dspState1.vi;
		Mapping0.LookMapping0 mapping0$LookMapping05 = new Mapping0.LookMapping0();
		Mapping0.InfoMapping0 mapping0$InfoMapping06 = mapping0$LookMapping05.map = (Mapping0.InfoMapping0)object3;
		mapping0$LookMapping05.mode = infoMode2;
		mapping0$LookMapping05.time_look = new Object[mapping0$InfoMapping06.submaps];
		mapping0$LookMapping05.floor_look = new Object[mapping0$InfoMapping06.submaps];
		mapping0$LookMapping05.residue_look = new Object[mapping0$InfoMapping06.submaps];
		mapping0$LookMapping05.time_func = new FuncTime[mapping0$InfoMapping06.submaps];
		mapping0$LookMapping05.floor_func = new FuncFloor[mapping0$InfoMapping06.submaps];
		mapping0$LookMapping05.residue_func = new FuncResidue[mapping0$InfoMapping06.submaps];

		for(int i7 = 0; i7 < mapping0$InfoMapping06.submaps; ++i7) {
			int i8 = mapping0$InfoMapping06.timesubmap[i7];
			int i9 = mapping0$InfoMapping06.floorsubmap[i7];
			int i10 = mapping0$InfoMapping06.residuesubmap[i7];
			mapping0$LookMapping05.time_func[i7] = FuncTime.time_P[info4.time_type[i8]];
			mapping0$LookMapping05.time_look[i7] = mapping0$LookMapping05.time_func[i7].look(dspState1, infoMode2, info4.time_param[i8]);
			mapping0$LookMapping05.floor_func[i7] = FuncFloor.floor_P[info4.floor_type[i9]];
			mapping0$LookMapping05.floor_look[i7] = mapping0$LookMapping05.floor_func[i7].look(dspState1, infoMode2, info4.floor_param[i9]);
			mapping0$LookMapping05.residue_func[i7] = FuncResidue.residue_P[info4.residue_type[i10]];
			mapping0$LookMapping05.residue_look[i7] = mapping0$LookMapping05.residue_func[i7].look(dspState1, infoMode2, info4.residue_param[i10]);
		}

		if(info4.psys != 0 && dspState1.analysisp != 0) {
			;
		}

		mapping0$LookMapping05.ch = info4.channels;
		return mapping0$LookMapping05;
	}

	void pack(Info info1, Object object2, Buffer buffer3) {
		Mapping0.InfoMapping0 mapping0$InfoMapping04 = (Mapping0.InfoMapping0)object2;
		if(mapping0$InfoMapping04.submaps > 1) {
			buffer3.write(1, 1);
			buffer3.write(mapping0$InfoMapping04.submaps - 1, 4);
		} else {
			buffer3.write(0, 1);
		}

		int i5;
		if(mapping0$InfoMapping04.coupling_steps > 0) {
			buffer3.write(1, 1);
			buffer3.write(mapping0$InfoMapping04.coupling_steps - 1, 8);

			for(i5 = 0; i5 < mapping0$InfoMapping04.coupling_steps; ++i5) {
				buffer3.write(mapping0$InfoMapping04.coupling_mag[i5], Util.ilog2(info1.channels));
				buffer3.write(mapping0$InfoMapping04.coupling_ang[i5], Util.ilog2(info1.channels));
			}
		} else {
			buffer3.write(0, 1);
		}

		buffer3.write(0, 2);
		if(mapping0$InfoMapping04.submaps > 1) {
			for(i5 = 0; i5 < info1.channels; ++i5) {
				buffer3.write(mapping0$InfoMapping04.chmuxlist[i5], 4);
			}
		}

		for(i5 = 0; i5 < mapping0$InfoMapping04.submaps; ++i5) {
			buffer3.write(mapping0$InfoMapping04.timesubmap[i5], 8);
			buffer3.write(mapping0$InfoMapping04.floorsubmap[i5], 8);
			buffer3.write(mapping0$InfoMapping04.residuesubmap[i5], 8);
		}

	}

	Object unpack(Info info1, Buffer buffer2) {
		Mapping0.InfoMapping0 mapping0$InfoMapping03 = new Mapping0.InfoMapping0();
		if(buffer2.read(1) != 0) {
			mapping0$InfoMapping03.submaps = buffer2.read(4) + 1;
		} else {
			mapping0$InfoMapping03.submaps = 1;
		}

		int i4;
		if(buffer2.read(1) != 0) {
			mapping0$InfoMapping03.coupling_steps = buffer2.read(8) + 1;

			for(i4 = 0; i4 < mapping0$InfoMapping03.coupling_steps; ++i4) {
				int i5 = mapping0$InfoMapping03.coupling_mag[i4] = buffer2.read(Util.ilog2(info1.channels));
				int i6 = mapping0$InfoMapping03.coupling_ang[i4] = buffer2.read(Util.ilog2(info1.channels));
				if(i5 < 0 || i6 < 0 || i5 == i6 || i5 >= info1.channels || i6 >= info1.channels) {
					mapping0$InfoMapping03.free();
					return null;
				}
			}
		}

		if(buffer2.read(2) > 0) {
			mapping0$InfoMapping03.free();
			return null;
		} else {
			if(mapping0$InfoMapping03.submaps > 1) {
				for(i4 = 0; i4 < info1.channels; ++i4) {
					mapping0$InfoMapping03.chmuxlist[i4] = buffer2.read(4);
					if(mapping0$InfoMapping03.chmuxlist[i4] >= mapping0$InfoMapping03.submaps) {
						mapping0$InfoMapping03.free();
						return null;
					}
				}
			}

			for(i4 = 0; i4 < mapping0$InfoMapping03.submaps; ++i4) {
				mapping0$InfoMapping03.timesubmap[i4] = buffer2.read(8);
				if(mapping0$InfoMapping03.timesubmap[i4] >= info1.times) {
					mapping0$InfoMapping03.free();
					return null;
				}

				mapping0$InfoMapping03.floorsubmap[i4] = buffer2.read(8);
				if(mapping0$InfoMapping03.floorsubmap[i4] >= info1.floors) {
					mapping0$InfoMapping03.free();
					return null;
				}

				mapping0$InfoMapping03.residuesubmap[i4] = buffer2.read(8);
				if(mapping0$InfoMapping03.residuesubmap[i4] >= info1.residues) {
					mapping0$InfoMapping03.free();
					return null;
				}
			}

			return mapping0$InfoMapping03;
		}
	}

	synchronized int inverse(Block block1, Object object2) {
		DspState dspState3 = block1.vd;
		Info info4 = dspState3.vi;
		Mapping0.LookMapping0 mapping0$LookMapping05 = (Mapping0.LookMapping0)object2;
		Mapping0.InfoMapping0 mapping0$InfoMapping06 = mapping0$LookMapping05.map;
		InfoMode infoMode7 = mapping0$LookMapping05.mode;
		int i8 = block1.pcmend = info4.blocksizes[block1.W];
		float[] f9 = dspState3.window[block1.W][block1.lW][block1.nW][infoMode7.windowtype];
		if(this.pcmbundle == null || this.pcmbundle.length < info4.channels) {
			this.pcmbundle = new float[info4.channels][];
			this.nonzero = new int[info4.channels];
			this.zerobundle = new int[info4.channels];
			this.floormemo = new Object[info4.channels];
		}

		int i10;
		float[] f11;
		int i12;
		int i13;
		for(i10 = 0; i10 < info4.channels; ++i10) {
			f11 = block1.pcm[i10];
			i12 = mapping0$InfoMapping06.chmuxlist[i10];
			this.floormemo[i10] = mapping0$LookMapping05.floor_func[i12].inverse1(block1, mapping0$LookMapping05.floor_look[i12], this.floormemo[i10]);
			if(this.floormemo[i10] != null) {
				this.nonzero[i10] = 1;
			} else {
				this.nonzero[i10] = 0;
			}

			for(i13 = 0; i13 < i8 / 2; ++i13) {
				f11[i13] = 0.0F;
			}
		}

		for(i10 = 0; i10 < mapping0$InfoMapping06.coupling_steps; ++i10) {
			if(this.nonzero[mapping0$InfoMapping06.coupling_mag[i10]] != 0 || this.nonzero[mapping0$InfoMapping06.coupling_ang[i10]] != 0) {
				this.nonzero[mapping0$InfoMapping06.coupling_mag[i10]] = 1;
				this.nonzero[mapping0$InfoMapping06.coupling_ang[i10]] = 1;
			}
		}

		for(i10 = 0; i10 < mapping0$InfoMapping06.submaps; ++i10) {
			int i16 = 0;

			for(i12 = 0; i12 < info4.channels; ++i12) {
				if(mapping0$InfoMapping06.chmuxlist[i12] == i10) {
					if(this.nonzero[i12] != 0) {
						this.zerobundle[i16] = 1;
					} else {
						this.zerobundle[i16] = 0;
					}

					this.pcmbundle[i16++] = block1.pcm[i12];
				}
			}

			mapping0$LookMapping05.residue_func[i10].inverse(block1, mapping0$LookMapping05.residue_look[i10], this.pcmbundle, this.zerobundle, i16);
		}

		for(i10 = mapping0$InfoMapping06.coupling_steps - 1; i10 >= 0; --i10) {
			f11 = block1.pcm[mapping0$InfoMapping06.coupling_mag[i10]];
			float[] f17 = block1.pcm[mapping0$InfoMapping06.coupling_ang[i10]];

			for(i13 = 0; i13 < i8 / 2; ++i13) {
				float f14 = f11[i13];
				float f15 = f17[i13];
				if(f14 > 0.0F) {
					if(f15 > 0.0F) {
						f11[i13] = f14;
						f17[i13] = f14 - f15;
					} else {
						f17[i13] = f14;
						f11[i13] = f14 + f15;
					}
				} else if(f15 > 0.0F) {
					f11[i13] = f14;
					f17[i13] = f14 + f15;
				} else {
					f17[i13] = f14;
					f11[i13] = f14 - f15;
				}
			}
		}

		for(i10 = 0; i10 < info4.channels; ++i10) {
			f11 = block1.pcm[i10];
			i12 = mapping0$InfoMapping06.chmuxlist[i10];
			mapping0$LookMapping05.floor_func[i12].inverse2(block1, mapping0$LookMapping05.floor_look[i12], this.floormemo[i10], f11);
		}

		for(i10 = 0; i10 < info4.channels; ++i10) {
			f11 = block1.pcm[i10];
			((Mdct)dspState3.transform[block1.W][0]).backward(f11, f11);
		}

		for(i10 = 0; i10 < info4.channels; ++i10) {
			f11 = block1.pcm[i10];
			if(this.nonzero[i10] != 0) {
				for(i12 = 0; i12 < i8; ++i12) {
					f11[i12] *= f9[i12];
				}
			} else {
				for(i12 = 0; i12 < i8; ++i12) {
					f11[i12] = 0.0F;
				}
			}
		}

		return 0;
	}

	class InfoMapping0 {
		int submaps;
		int[] chmuxlist = new int[256];
		int[] timesubmap = new int[16];
		int[] floorsubmap = new int[16];
		int[] residuesubmap = new int[16];
		int[] psysubmap = new int[16];
		int coupling_steps;
		int[] coupling_mag = new int[256];
		int[] coupling_ang = new int[256];

		void free() {
			this.chmuxlist = null;
			this.timesubmap = null;
			this.floorsubmap = null;
			this.residuesubmap = null;
			this.psysubmap = null;
			this.coupling_mag = null;
			this.coupling_ang = null;
		}
	}

	class LookMapping0 {
		InfoMode mode;
		Mapping0.InfoMapping0 map;
		Object[] time_look;
		Object[] floor_look;
		Object[] floor_state;
		Object[] residue_look;
		PsyLook[] psy_look;
		FuncTime[] time_func;
		FuncFloor[] floor_func;
		FuncResidue[] residue_func;
		int ch;
		float[][] decay;
		int lastframe;
	}
}
