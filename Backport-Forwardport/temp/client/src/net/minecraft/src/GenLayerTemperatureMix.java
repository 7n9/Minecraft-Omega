package net.minecraft.src;

public class GenLayerTemperatureMix extends GenLayer {
	private GenLayer field_35505_b;
	private int field_35506_c;

	public GenLayerTemperatureMix(GenLayer genLayer1, GenLayer genLayer2, int i3) {
		super(0L);
		this.field_35504_a = genLayer2;
		this.field_35505_b = genLayer1;
		this.field_35506_c = i3;
	}

	public int[] func_35500_a(int i1, int i2, int i3, int i4) {
		int[] i5 = this.field_35504_a.func_35500_a(i1, i2, i3, i4);
		int[] i6 = this.field_35505_b.func_35500_a(i1, i2, i3, i4);
		int[] i7 = IntCache.func_35267_a(i3 * i4);

		for(int i8 = 0; i8 < i3 * i4; ++i8) {
			i7[i8] = i6[i8] + (BiomeGenBase.field_35486_a[i5[i8]].func_35474_f() - i6[i8]) / (this.field_35506_c * 2 + 1);
		}

		return i7;
	}
}
