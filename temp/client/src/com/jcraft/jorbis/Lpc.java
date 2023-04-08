package com.jcraft.jorbis;

class Lpc {
	Drft fft = new Drft();
	int ln;
	int m;

	static float lpc_from_data(float[] f0, float[] f1, int i2, int i3) {
		float[] f4 = new float[i3 + 1];

		int i6;
		int i7;
		float f8;
		for(i7 = i3 + 1; i7-- != 0; f4[i7] = f8) {
			f8 = 0.0F;

			for(i6 = i7; i6 < i2; ++i6) {
				f8 += f0[i6] * f0[i6 - i7];
			}
		}

		float f5 = f4[0];

		for(i6 = 0; i6 < i3; ++i6) {
			f8 = -f4[i6 + 1];
			if(f5 == 0.0F) {
				for(int i10 = 0; i10 < i3; ++i10) {
					f1[i10] = 0.0F;
				}

				return 0.0F;
			}

			for(i7 = 0; i7 < i6; ++i7) {
				f8 -= f1[i7] * f4[i6 - i7];
			}

			f8 /= f5;
			f1[i6] = f8;

			for(i7 = 0; i7 < i6 / 2; ++i7) {
				float f9 = f1[i7];
				f1[i7] += f8 * f1[i6 - 1 - i7];
				f1[i6 - 1 - i7] += f8 * f9;
			}

			if(i6 % 2 != 0) {
				f1[i7] += f1[i7] * f8;
			}

			f5 = (float)((double)f5 * (1.0D - (double)(f8 * f8)));
		}

		return f5;
	}

	float lpc_from_curve(float[] f1, float[] f2) {
		int i3 = this.ln;
		float[] f4 = new float[i3 + i3];
		float f5 = (float)(0.5D / (double)i3);

		int i6;
		for(i6 = 0; i6 < i3; ++i6) {
			f4[i6 * 2] = f1[i6] * f5;
			f4[i6 * 2 + 1] = 0.0F;
		}

		f4[i3 * 2 - 1] = f1[i3 - 1] * f5;
		i3 *= 2;
		this.fft.backward(f4);
		i6 = 0;

		float f8;
		for(int i7 = i3 / 2; i6 < i3 / 2; f4[i7++] = f8) {
			f8 = f4[i6];
			f4[i6++] = f4[i7];
		}

		return lpc_from_data(f4, f2, i3, this.m);
	}

	void init(int i1, int i2) {
		this.ln = i1;
		this.m = i2;
		this.fft.init(i1 * 2);
	}

	void clear() {
		this.fft.clear();
	}

	static float FAST_HYPOT(float f0, float f1) {
		return (float)Math.sqrt((double)(f0 * f0 + f1 * f1));
	}

	void lpc_to_curve(float[] f1, float[] f2, float f3) {
		int i4;
		for(i4 = 0; i4 < this.ln * 2; ++i4) {
			f1[i4] = 0.0F;
		}

		if(f3 != 0.0F) {
			for(i4 = 0; i4 < this.m; ++i4) {
				f1[i4 * 2 + 1] = f2[i4] / (4.0F * f3);
				f1[i4 * 2 + 2] = -f2[i4] / (4.0F * f3);
			}

			this.fft.backward(f1);
			i4 = this.ln * 2;
			float f5 = (float)(1.0D / (double)f3);
			f1[0] = (float)(1.0D / (double)(f1[0] * 2.0F + f5));

			for(int i6 = 1; i6 < this.ln; ++i6) {
				float f7 = f1[i6] + f1[i4 - i6];
				float f8 = f1[i6] - f1[i4 - i6];
				float f9 = f7 + f5;
				f1[i6] = (float)(1.0D / (double)FAST_HYPOT(f9, f8));
			}

		}
	}
}
