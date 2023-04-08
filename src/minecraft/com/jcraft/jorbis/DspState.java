package com.jcraft.jorbis;

public class DspState {
	static final float M_PI = 3.1415927F;
	static final int VI_TRANSFORMB = 1;
	static final int VI_WINDOWB = 1;
	int analysisp;
	Info vi;
	int modebits;
	float[][] pcm;
	int pcm_storage;
	int pcm_current;
	int pcm_returned;
	float[] multipliers;
	int envelope_storage;
	int envelope_current;
	int eofflag;
	int lW;
	int W;
	int nW;
	int centerW;
	long granulepos;
	long sequence;
	long glue_bits;
	long time_bits;
	long floor_bits;
	long res_bits;
	float[][][][][] window;
	Object[][] transform;
	CodeBook[] fullbooks;
	Object[] mode;
	byte[] header;
	byte[] header1;
	byte[] header2;

	public DspState() {
		this.transform = new Object[2][];
		this.window = new float[2][][][][];
		this.window[0] = new float[2][][][];
		this.window[0][0] = new float[2][][];
		this.window[0][1] = new float[2][][];
		this.window[0][0][0] = new float[2][];
		this.window[0][0][1] = new float[2][];
		this.window[0][1][0] = new float[2][];
		this.window[0][1][1] = new float[2][];
		this.window[1] = new float[2][][][];
		this.window[1][0] = new float[2][][];
		this.window[1][1] = new float[2][][];
		this.window[1][0][0] = new float[2][];
		this.window[1][0][1] = new float[2][];
		this.window[1][1][0] = new float[2][];
		this.window[1][1][1] = new float[2][];
	}

	static float[] window(int i0, int i1, int i2, int i3) {
		float[] f4 = new float[i1];
		switch(i0) {
		case 0:
			int i5 = i1 / 4 - i2 / 2;
			int i6 = i1 - i1 / 4 - i3 / 2;

			int i7;
			float f8;
			for(i7 = 0; i7 < i2; ++i7) {
				f8 = (float)(((double)i7 + 0.5D) / (double)i2 * 3.1415927410125732D / 2.0D);
				f8 = (float)Math.sin((double)f8);
				f8 *= f8;
				f8 = (float)((double)f8 * 1.5707963705062866D);
				f8 = (float)Math.sin((double)f8);
				f4[i7 + i5] = f8;
			}

			for(i7 = i5 + i2; i7 < i6; ++i7) {
				f4[i7] = 1.0F;
			}

			for(i7 = 0; i7 < i3; ++i7) {
				f8 = (float)(((double)(i3 - i7) - 0.5D) / (double)i3 * 3.1415927410125732D / 2.0D);
				f8 = (float)Math.sin((double)f8);
				f8 *= f8;
				f8 = (float)((double)f8 * 1.5707963705062866D);
				f8 = (float)Math.sin((double)f8);
				f4[i7 + i6] = f8;
			}

			return f4;
		default:
			return null;
		}
	}

	int init(Info info1, boolean z2) {
		this.vi = info1;
		this.modebits = Util.ilog2(info1.modes);
		this.transform[0] = new Object[1];
		this.transform[1] = new Object[1];
		this.transform[0][0] = new Mdct();
		this.transform[1][0] = new Mdct();
		((Mdct)this.transform[0][0]).init(info1.blocksizes[0]);
		((Mdct)this.transform[1][0]).init(info1.blocksizes[1]);
		this.window[0][0][0] = new float[1][];
		this.window[0][0][1] = this.window[0][0][0];
		this.window[0][1][0] = this.window[0][0][0];
		this.window[0][1][1] = this.window[0][0][0];
		this.window[1][0][0] = new float[1][];
		this.window[1][0][1] = new float[1][];
		this.window[1][1][0] = new float[1][];
		this.window[1][1][1] = new float[1][];

		int i3;
		for(i3 = 0; i3 < 1; ++i3) {
			this.window[0][0][0][i3] = window(i3, info1.blocksizes[0], info1.blocksizes[0] / 2, info1.blocksizes[0] / 2);
			this.window[1][0][0][i3] = window(i3, info1.blocksizes[1], info1.blocksizes[0] / 2, info1.blocksizes[0] / 2);
			this.window[1][0][1][i3] = window(i3, info1.blocksizes[1], info1.blocksizes[0] / 2, info1.blocksizes[1] / 2);
			this.window[1][1][0][i3] = window(i3, info1.blocksizes[1], info1.blocksizes[1] / 2, info1.blocksizes[0] / 2);
			this.window[1][1][1][i3] = window(i3, info1.blocksizes[1], info1.blocksizes[1] / 2, info1.blocksizes[1] / 2);
		}

		this.fullbooks = new CodeBook[info1.books];

		for(i3 = 0; i3 < info1.books; ++i3) {
			this.fullbooks[i3] = new CodeBook();
			this.fullbooks[i3].init_decode(info1.book_param[i3]);
		}

		this.pcm_storage = 8192;
		this.pcm = new float[info1.channels][];

		for(i3 = 0; i3 < info1.channels; ++i3) {
			this.pcm[i3] = new float[this.pcm_storage];
		}

		this.lW = 0;
		this.W = 0;
		this.centerW = info1.blocksizes[1] / 2;
		this.pcm_current = this.centerW;
		this.mode = new Object[info1.modes];

		for(i3 = 0; i3 < info1.modes; ++i3) {
			int i4 = info1.mode_param[i3].mapping;
			int i5 = info1.map_type[i4];
			this.mode[i3] = FuncMapping.mapping_P[i5].look(this, info1.mode_param[i3], info1.map_param[i4]);
		}

		return 0;
	}

	public int synthesis_init(Info info1) {
		this.init(info1, false);
		this.pcm_returned = this.centerW;
		this.centerW -= info1.blocksizes[this.W] / 4 + info1.blocksizes[this.lW] / 4;
		this.granulepos = -1L;
		this.sequence = -1L;
		return 0;
	}

	DspState(Info info1) {
		this();
		this.init(info1, false);
		this.pcm_returned = this.centerW;
		this.centerW -= info1.blocksizes[this.W] / 4 + info1.blocksizes[this.lW] / 4;
		this.granulepos = -1L;
		this.sequence = -1L;
	}

	public int synthesis_blockin(Block block1) {
		int i2;
		int i3;
		if(this.centerW > this.vi.blocksizes[1] / 2 && this.pcm_returned > 8192) {
			i2 = this.centerW - this.vi.blocksizes[1] / 2;
			i2 = this.pcm_returned < i2 ? this.pcm_returned : i2;
			this.pcm_current -= i2;
			this.centerW -= i2;
			this.pcm_returned -= i2;
			if(i2 != 0) {
				for(i3 = 0; i3 < this.vi.channels; ++i3) {
					System.arraycopy(this.pcm[i3], i2, this.pcm[i3], 0, this.pcm_current);
				}
			}
		}

		this.lW = this.W;
		this.W = block1.W;
		this.nW = -1;
		this.glue_bits += (long)block1.glue_bits;
		this.time_bits += (long)block1.time_bits;
		this.floor_bits += (long)block1.floor_bits;
		this.res_bits += (long)block1.res_bits;
		if(this.sequence + 1L != block1.sequence) {
			this.granulepos = -1L;
		}

		this.sequence = block1.sequence;
		i2 = this.vi.blocksizes[this.W];
		i3 = this.centerW + this.vi.blocksizes[this.lW] / 4 + i2 / 4;
		int i4 = i3 - i2 / 2;
		int i5 = i4 + i2;
		int i6 = 0;
		int i7 = 0;
		int i8;
		if(i5 > this.pcm_storage) {
			this.pcm_storage = i5 + this.vi.blocksizes[1];

			for(i8 = 0; i8 < this.vi.channels; ++i8) {
				float[] f9 = new float[this.pcm_storage];
				System.arraycopy(this.pcm[i8], 0, f9, 0, this.pcm[i8].length);
				this.pcm[i8] = f9;
			}
		}

		switch(this.W) {
		case 0:
			i6 = 0;
			i7 = this.vi.blocksizes[0] / 2;
			break;
		case 1:
			i6 = this.vi.blocksizes[1] / 4 - this.vi.blocksizes[this.lW] / 4;
			i7 = i6 + this.vi.blocksizes[this.lW] / 2;
		}

		for(i8 = 0; i8 < this.vi.channels; ++i8) {
			int i11 = i4;
			boolean z10 = false;

			int i12;
			for(i12 = i6; i12 < i7; ++i12) {
				this.pcm[i8][i11 + i12] += block1.pcm[i8][i12];
			}

			while(i12 < i2) {
				this.pcm[i8][i11 + i12] = block1.pcm[i8][i12];
				++i12;
			}
		}

		if(this.granulepos == -1L) {
			this.granulepos = block1.granulepos;
		} else {
			this.granulepos += (long)(i3 - this.centerW);
			if(block1.granulepos != -1L && this.granulepos != block1.granulepos) {
				if(this.granulepos > block1.granulepos && block1.eofflag != 0) {
					i3 = (int)((long)i3 - (this.granulepos - block1.granulepos));
				}

				this.granulepos = block1.granulepos;
			}
		}

		this.centerW = i3;
		this.pcm_current = i5;
		if(block1.eofflag != 0) {
			this.eofflag = 1;
		}

		return 0;
	}

	public int synthesis_pcmout(float[][][] f1, int[] i2) {
		if(this.pcm_returned >= this.centerW) {
			return 0;
		} else {
			if(f1 != null) {
				for(int i3 = 0; i3 < this.vi.channels; ++i3) {
					i2[i3] = this.pcm_returned;
				}

				f1[0] = this.pcm;
			}

			return this.centerW - this.pcm_returned;
		}
	}

	public int synthesis_read(int i1) {
		if(i1 != 0 && this.pcm_returned + i1 > this.centerW) {
			return -1;
		} else {
			this.pcm_returned += i1;
			return 0;
		}
	}

	public void clear() {
	}
}
