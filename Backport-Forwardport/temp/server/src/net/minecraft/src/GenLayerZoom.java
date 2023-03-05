package net.minecraft.src;

public class GenLayerZoom extends GenLayer {
	public GenLayerZoom(long j1, GenLayer genLayer3) {
		super(j1);
		super.field_35023_a = genLayer3;
	}

	public int[] func_35018_a(int i1, int i2, int i3, int i4) {
		int i5 = i1 >> 1;
		int i6 = i2 >> 1;
		int i7 = (i3 >> 1) + 3;
		int i8 = (i4 >> 1) + 3;
		int[] i9 = this.field_35023_a.func_35018_a(i5, i6, i7, i8);
		int[] i10 = IntCache.func_35549_a(i7 * 2 * i8 * 2);
		int i11 = i7 << 1;

		int i13;
		for(int i12 = 0; i12 < i8 - 1; ++i12) {
			i13 = i12 << 1;
			int i14 = i13 * i11;
			int i15 = i9[0 + (i12 + 0) * i7];
			int i16 = i9[0 + (i12 + 1) * i7];

			for(int i17 = 0; i17 < i7 - 1; ++i17) {
				this.func_35017_a((long)(i17 + i5 << 1), (long)(i12 + i6 << 1));
				int i18 = i9[i17 + 1 + (i12 + 0) * i7];
				int i19 = i9[i17 + 1 + (i12 + 1) * i7];
				i10[i14] = i15;
				i10[i14++ + i11] = this.func_35026_a(i15, i16);
				i10[i14] = this.func_35026_a(i15, i18);
				i10[i14++ + i11] = this.func_35024_b(i15, i18, i16, i19);
				i15 = i18;
				i16 = i19;
			}
		}

		int[] i20 = IntCache.func_35549_a(i3 * i4);

		for(i13 = 0; i13 < i4; ++i13) {
			System.arraycopy(i10, (i13 + (i2 & 1)) * (i7 << 1) + (i1 & 1), i20, i13 * i3, i3);
		}

		return i20;
	}

	protected int func_35026_a(int i1, int i2) {
		return this.func_35016_a(2) == 0 ? i1 : i2;
	}

	protected int func_35024_b(int i1, int i2, int i3, int i4) {
		if(i2 == i3 && i3 == i4) {
			return i2;
		} else if(i1 == i2 && i1 == i3) {
			return i1;
		} else if(i1 == i2 && i1 == i4) {
			return i1;
		} else if(i1 == i3 && i1 == i4) {
			return i1;
		} else if(i1 == i2 && i3 != i4) {
			return i1;
		} else if(i1 == i3 && i2 != i4) {
			return i1;
		} else if(i1 == i4 && i2 != i3) {
			return i1;
		} else if(i2 == i1 && i3 != i4) {
			return i2;
		} else if(i2 == i3 && i1 != i4) {
			return i2;
		} else if(i2 == i4 && i1 != i3) {
			return i2;
		} else if(i3 == i1 && i2 != i4) {
			return i3;
		} else if(i3 == i2 && i1 != i4) {
			return i3;
		} else if(i3 == i4 && i1 != i2) {
			return i3;
		} else if(i4 == i1 && i2 != i3) {
			return i3;
		} else if(i4 == i2 && i1 != i3) {
			return i3;
		} else if(i4 == i3 && i1 != i2) {
			return i3;
		} else {
			int i5 = this.func_35016_a(4);
			return i5 == 0 ? i1 : (i5 == 1 ? i2 : (i5 == 2 ? i3 : i4));
		}
	}

	public static GenLayer func_35025_a(long j0, GenLayer genLayer2, int i3) {
		Object object4 = genLayer2;

		for(int i5 = 0; i5 < i3; ++i5) {
			object4 = new GenLayerZoom(j0 + (long)i5, (GenLayer)object4);
		}

		return (GenLayer)object4;
	}
}
