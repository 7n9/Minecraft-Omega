package net.minecraft.src;

import java.util.Random;

public abstract class StructurePieceBlockSelector {
	protected int field_35569_a;
	protected int field_35568_b;

	public abstract void func_35565_a(Random random1, int i2, int i3, int i4, boolean z5);

	public int func_35566_a() {
		return this.field_35569_a;
	}

	public int func_35567_b() {
		return this.field_35568_b;
	}
}
