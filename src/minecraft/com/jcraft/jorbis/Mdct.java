package com.jcraft.jorbis;

class Mdct {
	int n;
	int log2n;
	float[] trig;
	int[] bitrev;
	float scale;
	float[] _x = new float[1024];
	float[] _w = new float[1024];

	void init(int i1) {
		this.bitrev = new int[i1 / 4];
		this.trig = new float[i1 + i1 / 4];
		this.log2n = (int)Math.rint(Math.log((double)i1) / Math.log(2.0D));
		this.n = i1;
		byte b2 = 0;
		byte b3 = 1;
		int i4 = b2 + i1 / 2;
		int i5 = i4 + 1;
		int i6 = i4 + i1 / 2;
		int i7 = i6 + 1;

		int i8;
		for(i8 = 0; i8 < i1 / 4; ++i8) {
			this.trig[b2 + i8 * 2] = (float)Math.cos(3.141592653589793D / (double)i1 * (double)(4 * i8));
			this.trig[b3 + i8 * 2] = (float)(-Math.sin(3.141592653589793D / (double)i1 * (double)(4 * i8)));
			this.trig[i4 + i8 * 2] = (float)Math.cos(3.141592653589793D / (double)(2 * i1) * (double)(2 * i8 + 1));
			this.trig[i5 + i8 * 2] = (float)Math.sin(3.141592653589793D / (double)(2 * i1) * (double)(2 * i8 + 1));
		}

		for(i8 = 0; i8 < i1 / 8; ++i8) {
			this.trig[i6 + i8 * 2] = (float)Math.cos(3.141592653589793D / (double)i1 * (double)(4 * i8 + 2));
			this.trig[i7 + i8 * 2] = (float)(-Math.sin(3.141592653589793D / (double)i1 * (double)(4 * i8 + 2)));
		}

		i8 = (1 << this.log2n - 1) - 1;
		int i9 = 1 << this.log2n - 2;

		for(int i10 = 0; i10 < i1 / 8; ++i10) {
			int i11 = 0;

			for(int i12 = 0; i9 >>> i12 != 0; ++i12) {
				if((i9 >>> i12 & i10) != 0) {
					i11 |= 1 << i12;
				}
			}

			this.bitrev[i10 * 2] = ~i11 & i8;
			this.bitrev[i10 * 2 + 1] = i11;
		}

		this.scale = 4.0F / (float)i1;
	}

	void clear() {
	}

	void forward(float[] f1, float[] f2) {
	}

	synchronized void backward(float[] f1, float[] f2) {
		if(this._x.length < this.n / 2) {
			this._x = new float[this.n / 2];
		}

		if(this._w.length < this.n / 2) {
			this._w = new float[this.n / 2];
		}

		float[] f3 = this._x;
		float[] f4 = this._w;
		int i5 = this.n >>> 1;
		int i6 = this.n >>> 2;
		int i7 = this.n >>> 3;
		int i8 = 1;
		int i9 = 0;
		int i10 = i5;

		int i11;
		for(i11 = 0; i11 < i7; ++i11) {
			i10 -= 2;
			f3[i9++] = -f1[i8 + 2] * this.trig[i10 + 1] - f1[i8] * this.trig[i10];
			f3[i9++] = f1[i8] * this.trig[i10 + 1] - f1[i8 + 2] * this.trig[i10];
			i8 += 4;
		}

		i8 = i5 - 4;

		for(i11 = 0; i11 < i7; ++i11) {
			i10 -= 2;
			f3[i9++] = f1[i8] * this.trig[i10 + 1] + f1[i8 + 2] * this.trig[i10];
			f3[i9++] = f1[i8] * this.trig[i10] - f1[i8 + 2] * this.trig[i10 + 1];
			i8 -= 4;
		}

		float[] f18 = this.mdct_kernel(f3, f4, this.n, i5, i6, i7);
		i9 = 0;
		i10 = i5;
		i11 = i6;
		int i12 = i6 - 1;
		int i13 = i6 + i5;
		int i14 = i13 - 1;

		for(int i15 = 0; i15 < i6; ++i15) {
			float f16 = f18[i9] * this.trig[i10 + 1] - f18[i9 + 1] * this.trig[i10];
			float f17 = -(f18[i9] * this.trig[i10] + f18[i9 + 1] * this.trig[i10 + 1]);
			f2[i11] = -f16;
			f2[i12] = f16;
			f2[i13] = f17;
			f2[i14] = f17;
			++i11;
			--i12;
			++i13;
			--i14;
			i9 += 2;
			i10 += 2;
		}

	}

	private float[] mdct_kernel(float[] f1, float[] f2, int i3, int i4, int i5, int i6) {
		int i7 = i5;
		int i8 = 0;
		int i9 = i5;
		int i10 = i4;

		int i11;
		for(i11 = 0; i11 < i5; ++i11) {
			float f12 = f1[i7] - f1[i8];
			f2[i9 + i11] = f1[i7++] + f1[i8++];
			float f13 = f1[i7] - f1[i8];
			i10 -= 4;
			f2[i11++] = f12 * this.trig[i10] + f13 * this.trig[i10 + 1];
			f2[i11] = f13 * this.trig[i10] - f12 * this.trig[i10 + 1];
			f2[i9 + i11] = f1[i7++] + f1[i8++];
		}

		int i14;
		int i16;
		int i17;
		float f18;
		float f19;
		float f20;
		float f21;
		int i26;
		int i27;
		for(i11 = 0; i11 < this.log2n - 3; ++i11) {
			i26 = i3 >>> i11 + 2;
			i27 = 1 << i11 + 3;
			i14 = i4 - 2;
			i10 = 0;

			for(i16 = 0; i16 < i26 >>> 2; ++i16) {
				i17 = i14;
				i9 = i14 - (i26 >> 1);
				f18 = this.trig[i10];
				f20 = this.trig[i10 + 1];
				i14 -= 2;
				++i26;

				for(int i22 = 0; i22 < 2 << i11; ++i22) {
					f21 = f2[i17] - f2[i9];
					f1[i17] = f2[i17] + f2[i9];
					++i17;
					float f10000 = f2[i17];
					++i9;
					f19 = f10000 - f2[i9];
					f1[i17] = f2[i17] + f2[i9];
					f1[i9] = f19 * f18 - f21 * f20;
					f1[i9 - 1] = f21 * f18 + f19 * f20;
					i17 -= i26;
					i9 -= i26;
				}

				--i26;
				i10 += i27;
			}

			float[] f15 = f2;
			f2 = f1;
			f1 = f15;
		}

		i11 = i3;
		i26 = 0;
		i27 = 0;
		i14 = i4 - 1;

		for(int i28 = 0; i28 < i6; ++i28) {
			i16 = this.bitrev[i26++];
			i17 = this.bitrev[i26++];
			f18 = f2[i16] - f2[i17 + 1];
			f19 = f2[i16 - 1] + f2[i17];
			f20 = f2[i16] + f2[i17 + 1];
			f21 = f2[i16 - 1] - f2[i17];
			float f30 = f18 * this.trig[i11];
			float f23 = f19 * this.trig[i11++];
			float f24 = f18 * this.trig[i11];
			float f25 = f19 * this.trig[i11++];
			f1[i27++] = (f20 + f24 + f23) * 0.5F;
			f1[i14--] = (-f21 + f25 - f30) * 0.5F;
			f1[i27++] = (f21 + f25 - f30) * 0.5F;
			f1[i14--] = (f20 - f24 - f23) * 0.5F;
		}

		return f1;
	}
}
