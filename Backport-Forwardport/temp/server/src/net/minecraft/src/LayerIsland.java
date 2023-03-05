package net.minecraft.src;

public class LayerIsland extends GenLayer {
	public LayerIsland(long j1) {
		super(j1);
	}

	public int[] func_35018_a(int i1, int i2, int i3, int i4) {
		int[] i5 = IntCache.func_35549_a(i3 * i4);

		for(int i6 = 0; i6 < i4; ++i6) {
			for(int i7 = 0; i7 < i3; ++i7) {
				this.func_35017_a((long)(i1 + i7), (long)(i2 + i6));
				i5[i7 + i6 * i3] = this.func_35016_a(10) == 0 ? 1 : 0;
			}
		}

		if(i1 > -i3 && i1 <= 0 && i2 > -i4 && i2 <= 0) {
			i5[-i1 + -i2 * i3] = 1;
		}

		return i5;
	}
}
