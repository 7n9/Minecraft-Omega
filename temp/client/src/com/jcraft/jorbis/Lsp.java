package com.jcraft.jorbis;

class Lsp {
	static final float M_PI = 3.1415927F;

	static void lsp_to_curve(float[] f0, int[] i1, int i2, int i3, float[] f4, int i5, float f6, float f7) {
		float f9 = 3.1415927F / (float)i3;

		int i8;
		for(i8 = 0; i8 < i5; ++i8) {
			f4[i8] = Lookup.coslook(f4[i8]);
		}

		int i10 = i5 / 2 * 2;
		i8 = 0;

		while(i8 < i2) {
			int i11 = i1[i8];
			float f12 = 0.70710677F;
			float f13 = 0.70710677F;
			float f14 = Lookup.coslook(f9 * (float)i11);

			int i15;
			for(i15 = 0; i15 < i10; i15 += 2) {
				f13 *= f4[i15] - f14;
				f12 *= f4[i15 + 1] - f14;
			}

			if((i5 & 1) != 0) {
				f13 *= f4[i5 - 1] - f14;
				f13 *= f13;
				f12 *= f12 * (1.0F - f14 * f14);
			} else {
				f13 *= f13 * (1.0F + f14);
				f12 *= f12 * (1.0F - f14);
			}

			f13 += f12;
			i15 = Float.floatToIntBits(f13);
			int i16 = Integer.MAX_VALUE & i15;
			int i17 = 0;
			if(i16 < 2139095040 && i16 != 0) {
				if(i16 < 8388608) {
					f13 = (float)((double)f13 * 3.3554432E7D);
					i15 = Float.floatToIntBits(f13);
					i16 = Integer.MAX_VALUE & i15;
					i17 = -25;
				}

				i17 += (i16 >>> 23) - 126;
				i15 = i15 & -2139095041 | 1056964608;
				f13 = Float.intBitsToFloat(i15);
			}

			f13 = Lookup.fromdBlook(f6 * Lookup.invsqlook(f13) * Lookup.invsq2explook(i17 + i5) - f7);

			while(true) {
				int i10001 = i8++;
				f0[i10001] *= f13;
				if(i8 >= i2 || i1[i8] != i11) {
					break;
				}
			}
		}

	}
}
