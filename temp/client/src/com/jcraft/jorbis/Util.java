package com.jcraft.jorbis;

class Util {
	static int ilog(int i0) {
		int i1;
		for(i1 = 0; i0 != 0; i0 >>>= 1) {
			++i1;
		}

		return i1;
	}

	static int ilog2(int i0) {
		int i1;
		for(i1 = 0; i0 > 1; i0 >>>= 1) {
			++i1;
		}

		return i1;
	}

	static int icount(int i0) {
		int i1;
		for(i1 = 0; i0 != 0; i0 >>>= 1) {
			i1 += i0 & 1;
		}

		return i1;
	}
}
