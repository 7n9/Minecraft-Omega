package net.minecraft.src;

import java.util.Random;

public abstract class StructurePieceBlockSelector {
	protected int field_35710_a;
	protected int field_35709_b;

	public abstract void func_35706_a(Random random1, int i2, int i3, int i4, boolean z5);

	public int func_35707_a() {
		return this.field_35710_a;
	}

	public int func_35708_b() {
		return this.field_35709_b;
	}
}
