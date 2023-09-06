package net.minecraft.src;

public class ColorizerWater {
	private static int[] waterBuffer = new int[65536];

	public static void func_28182_a(int[] i0) {
		if(i0.length != 65536) {
			throw new IllegalArgumentException("Water color is an invalid size!");
		}

		for(int i1 = 0; i1 < waterBuffer.length; ++i1) {
			waterBuffer[i1] = i0[i1];
		}

	}

	public static int getWaterColor(double d0, double d2) {
		d2 *= d0;
		int i4 = (int)((1.0D - d0) * 255.0D);
		int i5 = (int)((1.0D - d2) * 255.0D);
		int i6 = i5 << 8 | i4;
		return i6 >= waterBuffer.length ? -65281 : waterBuffer[i6];
	}
}
