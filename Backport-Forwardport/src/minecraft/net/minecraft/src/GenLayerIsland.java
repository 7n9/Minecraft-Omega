package net.minecraft.src;

public class GenLayerIsland extends GenLayer {
	public GenLayerIsland(long j1, GenLayer genLayer3) {
		super(j1);
		this.field_35504_a = genLayer3;
	}

	public int[] func_35500_a(int i1, int i2, int i3, int i4) {
		int i5 = i1 - 1;
		int i6 = i2 - 1;
		int i7 = i3 + 2;
		int i8 = i4 + 2;
		int[] i9 = this.field_35504_a.func_35500_a(i5, i6, i7, i8);
		int[] i10 = IntCache.func_35267_a(i3 * i4);

		for(int i11 = 0; i11 < i4; ++i11) {
			for(int i12 = 0; i12 < i3; ++i12) {
				int i13 = i9[i12 + 0 + (i11 + 0) * i7];
				int i14 = i9[i12 + 2 + (i11 + 0) * i7];
				int i15 = i9[i12 + 0 + (i11 + 2) * i7];
				int i16 = i9[i12 + 2 + (i11 + 2) * i7];
				int i17 = i9[i12 + 1 + (i11 + 1) * i7];
				this.func_35499_a((long)(i12 + i1), (long)(i11 + i2));
				if(i17 == 0 && (i13 != 0 || i14 != 0 || i15 != 0 || i16 != 0)) {
					i10[i12 + i11 * i3] = 0 + this.func_35498_a(3) / 2;
				} else if(i17 != 1 || i13 == 1 && i14 == 1 && i15 == 1 && i16 == 1) {
					i10[i12 + i11 * i3] = i17;
				} else {
					i10[i12 + i11 * i3] = 1 - this.func_35498_a(5) / 4;
				}
			}
		}

		return i10;
	}
}
