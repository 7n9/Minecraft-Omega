package net.minecraft.src;

public class ColorizerFoliage {
	private static int[] foliageBuffer = new int[65536];

	public static void func_28152_a(int[] i0) {
		if(i0.length != 65536) {
			throw new IllegalArgumentException("Foliage color is an invalid size!");
		}

		for(int i1 = 0; i1 < foliageBuffer.length; ++i1) {
			foliageBuffer[i1] = i0[i1];
		}

	}

	public static int getFoliageColor(double d0, double d2) {
		d2 *= d0;
		int i4 = (int)((1.0D - d0) * 255.0D);
		int i5 = (int)((1.0D - d2) * 255.0D);
		int i6 = i5 << 8 | i4;
		return i6 >= foliageBuffer.length ? -65281 : foliageBuffer[i6];
	}

	public static int getFoliageColorPine() {
		return 6396257;
	}

	public static int getFoliageColorBirch() {
		return 8431445;
	}

	public static int func_31073_c() {
		return 4764952;
	}
}
