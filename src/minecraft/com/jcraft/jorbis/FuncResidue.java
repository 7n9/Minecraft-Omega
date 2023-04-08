package com.jcraft.jorbis;

import com.jcraft.jogg.Buffer;

abstract class FuncResidue {
	public static FuncResidue[] residue_P = new FuncResidue[]{new Residue0(), new Residue1(), new Residue2()};

	abstract void pack(Object object1, Buffer buffer2);

	abstract Object unpack(Info info1, Buffer buffer2);

	abstract Object look(DspState dspState1, InfoMode infoMode2, Object object3);

	abstract void free_info(Object object1);

	abstract void free_look(Object object1);

	abstract int inverse(Block block1, Object object2, float[][] f3, int[] i4, int i5);
}
