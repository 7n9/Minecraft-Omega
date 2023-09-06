package net.minecraft.src;

public class ColorizerGrass {
	private static int[] grassBuffer = new int[65536];

	public static void func_28181_a(int[] i0) {
		if(i0.length != 65536) {
			throw new IllegalArgumentException("Grass color is an invalid size!");
		}

		for(int i1 = 0; i1 < grassBuffer.length; ++i1) {
			grassBuffer[i1] = i0[i1];
		}

	}

	public static int getGrassColor(double d0, double d2) {
		d2 *= d0;
		int i4 = (int)((1.0D - d0) * 255.0D);
		int i5 = (int)((1.0D - d2) * 255.0D);
		int i6 = i5 << 8 | i4;
		return i6 >= grassBuffer.length ? -65281 : grassBuffer[i6];
	}
}
