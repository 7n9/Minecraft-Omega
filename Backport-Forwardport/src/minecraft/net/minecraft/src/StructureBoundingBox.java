package net.minecraft.src;

public class StructureBoundingBox {
	public int field_35753_a;
	public int field_35751_b;
	public int field_35752_c;
	public int field_35749_d;
	public int field_35750_e;
	public int field_35748_f;

	public StructureBoundingBox() {
	}

	public static StructureBoundingBox func_35741_a() {
		return new StructureBoundingBox(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
	}

	public static StructureBoundingBox func_35747_a(int i0, int i1, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
		switch(i9) {
		case 0:
			return new StructureBoundingBox(i0 + i3, i1 + i4, i2 + i5, i0 + i6 - 1 + i3, i1 + i7 - 1 + i4, i2 + i8 - 1 + i5);
		case 1:
			return new StructureBoundingBox(i0 - i8 + 1 + i5, i1 + i4, i2 + i3, i0 + i5, i1 + i7 - 1 + i4, i2 + i6 - 1 + i3);
		case 2:
			return new StructureBoundingBox(i0 + i3, i1 + i4, i2 - i8 + 1 + i5, i0 + i6 - 1 + i3, i1 + i7 - 1 + i4, i2 + i5);
		case 3:
			return new StructureBoundingBox(i0 + i5, i1 + i4, i2 + i3, i0 + i8 - 1 + i5, i1 + i7 - 1 + i4, i2 + i6 - 1 + i3);
		default:
			return new StructureBoundingBox(i0 + i3, i1 + i4, i2 + i5, i0 + i6 - 1 + i3, i1 + i7 - 1 + i4, i2 + i8 - 1 + i5);
		}
	}

	public StructureBoundingBox(StructureBoundingBox structureBoundingBox1) {
		this.field_35753_a = structureBoundingBox1.field_35753_a;
		this.field_35751_b = structureBoundingBox1.field_35751_b;
		this.field_35752_c = structureBoundingBox1.field_35752_c;
		this.field_35749_d = structureBoundingBox1.field_35749_d;
		this.field_35750_e = structureBoundingBox1.field_35750_e;
		this.field_35748_f = structureBoundingBox1.field_35748_f;
	}

	public StructureBoundingBox(int i1, int i2, int i3, int i4, int i5, int i6) {
		this.field_35753_a = i1;
		this.field_35751_b = i2;
		this.field_35752_c = i3;
		this.field_35749_d = i4;
		this.field_35750_e = i5;
		this.field_35748_f = i6;
	}

	public StructureBoundingBox(int i1, int i2, int i3, int i4) {
		this.field_35753_a = i1;
		this.field_35751_b = 0;
		this.field_35752_c = i2;
		this.field_35749_d = i3;
		this.field_35750_e = 65536;
		this.field_35748_f = i4;
	}

	public boolean func_35740_a(StructureBoundingBox structureBoundingBox1) {
		return this.field_35749_d >= structureBoundingBox1.field_35753_a && this.field_35753_a <= structureBoundingBox1.field_35749_d && this.field_35748_f >= structureBoundingBox1.field_35752_c && this.field_35752_c <= structureBoundingBox1.field_35748_f && this.field_35750_e >= structureBoundingBox1.field_35751_b && this.field_35751_b <= structureBoundingBox1.field_35750_e;
	}

	public boolean func_35746_a(int i1, int i2, int i3, int i4) {
		return this.field_35749_d >= i1 && this.field_35753_a <= i3 && this.field_35748_f >= i2 && this.field_35752_c <= i4;
	}

	public void func_35738_b(StructureBoundingBox structureBoundingBox1) {
		this.field_35753_a = Math.min(this.field_35753_a, structureBoundingBox1.field_35753_a);
		this.field_35751_b = Math.min(this.field_35751_b, structureBoundingBox1.field_35751_b);
		this.field_35752_c = Math.min(this.field_35752_c, structureBoundingBox1.field_35752_c);
		this.field_35749_d = Math.max(this.field_35749_d, structureBoundingBox1.field_35749_d);
		this.field_35750_e = Math.max(this.field_35750_e, structureBoundingBox1.field_35750_e);
		this.field_35748_f = Math.max(this.field_35748_f, structureBoundingBox1.field_35748_f);
	}

	public void func_35745_a(int i1, int i2, int i3) {
		this.field_35753_a += i1;
		this.field_35751_b += i2;
		this.field_35752_c += i3;
		this.field_35749_d += i1;
		this.field_35750_e += i2;
		this.field_35748_f += i3;
	}

	public boolean func_35742_b(int i1, int i2, int i3) {
		return i1 >= this.field_35753_a && i1 <= this.field_35749_d && i3 >= this.field_35752_c && i3 <= this.field_35748_f && i2 >= this.field_35751_b && i2 <= this.field_35750_e;
	}

	public int func_35744_b() {
		return this.field_35749_d - this.field_35753_a + 1;
	}

	public int func_35743_c() {
		return this.field_35750_e - this.field_35751_b + 1;
	}

	public int func_35739_d() {
		return this.field_35748_f - this.field_35752_c + 1;
	}

	public String toString() {
		return "(" + this.field_35753_a + ", " + this.field_35751_b + ", " + this.field_35752_c + "; " + this.field_35749_d + ", " + this.field_35750_e + ", " + this.field_35748_f + ")";
	}
}
