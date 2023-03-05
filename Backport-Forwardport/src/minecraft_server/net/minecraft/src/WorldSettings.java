package net.minecraft.src;

public final class WorldSettings {
	private final long field_35043_a;
	private final int field_35041_b;
	private final boolean field_35042_c;

	public WorldSettings(long j1, int i3, boolean z4) {
		this.field_35043_a = j1;
		this.field_35041_b = i3;
		this.field_35042_c = z4;
	}

	public long func_35038_a() {
		return this.field_35043_a;
	}

	public int func_35039_b() {
		return this.field_35041_b;
	}

	public boolean func_35040_c() {
		return this.field_35042_c;
	}

	public static int func_35037_a(int i0) {
		switch(i0) {
		case 0:
		case 1:
			return i0;
		default:
			return 0;
		}
	}
}
