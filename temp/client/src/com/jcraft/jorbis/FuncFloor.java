package com.jcraft.jorbis;

import com.jcraft.jogg.Buffer;

abstract class FuncFloor {
	public static FuncFloor[] floor_P = new FuncFloor[]{new Floor0(), new Floor1()};

	abstract void pack(Object object1, Buffer buffer2);

	abstract Object unpack(Info info1, Buffer buffer2);

	abstract Object look(DspState dspState1, InfoMode infoMode2, Object object3);

	abstract void free_info(Object object1);

	abstract void free_look(Object object1);

	abstract void free_state(Object object1);

	abstract int forward(Block block1, Object object2, float[] f3, float[] f4, Object object5);

	abstract Object inverse1(Block block1, Object object2, Object object3);

	abstract int inverse2(Block block1, Object object2, Object object3, float[] f4);
}
