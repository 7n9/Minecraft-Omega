package net.minecraft.src;

public final class WorldSettings {
	private final long field_35523_a;
	private final int field_35521_b;
	private final boolean field_35522_c;

	public WorldSettings(long j1, int i3, boolean z4) {
		this.field_35523_a = j1;
		this.field_35521_b = i3;
		this.field_35522_c = z4;
	}

	public long func_35518_a() {
		return this.field_35523_a;
	}

	public int func_35519_b() {
		return this.field_35521_b;
	}

	public boolean func_35520_c() {
		return this.field_35522_c;
	}
}
