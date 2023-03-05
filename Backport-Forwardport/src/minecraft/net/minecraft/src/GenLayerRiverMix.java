package net.minecraft.src;

public class GenLayerRiverMix extends GenLayer {
	private GenLayer field_35512_b;
	private GenLayer field_35513_c;

	public GenLayerRiverMix(long j1, GenLayer genLayer3, GenLayer genLayer4) {
		super(j1);
		this.field_35512_b = genLayer3;
		this.field_35513_c = genLayer4;
	}

	public void func_35496_b(long j1) {
		this.field_35512_b.func_35496_b(j1);
		this.field_35513_c.func_35496_b(j1);
		super.func_35496_b(j1);
	}

	public int[] func_35500_a(int i1, int i2, int i3, int i4) {
		int[] i5 = this.field_35512_b.func_35500_a(i1, i2, i3, i4);
		int[] i6 = this.field_35513_c.func_35500_a(i1, i2, i3, i4);
		int[] i7 = IntCache.func_35267_a(i3 * i4);

		for(int i8 = 0; i8 < i3 * i4; ++i8) {
			if(i5[i8] == BiomeGenBase.field_35484_b.field_35494_y) {
				i7[i8] = i5[i8];
			} else {
				i7[i8] = i6[i8] >= 0 ? i6[i8] : i5[i8];
			}
		}

		return i7;
	}
}
