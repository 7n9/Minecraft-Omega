package com.jcraft.jorbis;

class Residue2 extends Residue0 {
	int inverse(Block block1, Object object2, float[][] f3, int[] i4, int i5) {
		boolean z6 = false;

		int i7;
		for(i7 = 0; i7 < i5 && i4[i7] == 0; ++i7) {
		}

		return i7 == i5 ? 0 : _2inverse(block1, object2, f3, i5);
	}
}
