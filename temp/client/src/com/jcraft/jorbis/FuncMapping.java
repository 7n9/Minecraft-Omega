package com.jcraft.jorbis;

import com.jcraft.jogg.Buffer;

abstract class FuncMapping {
	public static FuncMapping[] mapping_P = new FuncMapping[]{new Mapping0()};

	abstract void pack(Info info1, Object object2, Buffer buffer3);

	abstract Object unpack(Info info1, Buffer buffer2);

	abstract Object look(DspState dspState1, InfoMode infoMode2, Object object3);

	abstract void free_info(Object object1);

	abstract void free_look(Object object1);

	abstract int inverse(Block block1, Object object2);
}
