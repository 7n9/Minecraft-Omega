package com.jcraft.jorbis;

class Residue1 extends Residue0 {
	int inverse(Block block1, Object object2, float[][] f3, int[] i4, int i5) {
		int i6 = 0;

		for(int i7 = 0; i7 < i5; ++i7) {
			if(i4[i7] != 0) {
				f3[i6++] = f3[i7];
			}
		}

		if(i6 != 0) {
			return _01inverse(block1, object2, f3, i6, 1);
		} else {
			return 0;
		}
	}
}
