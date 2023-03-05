package net.minecraft.src;

public class StructureBoundingBox {
	public int field_35678_a;
	public int field_35676_b;
	public int field_35677_c;
	public int field_35674_d;
	public int field_35675_e;
	public int field_35673_f;

	public StructureBoundingBox() {
	}

	public static StructureBoundingBox func_35672_a() {
		return new StructureBoundingBox(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
	}

	public static StructureBoundingBox func_35663_a(int i0, int i1, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
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
		this.field_35678_a = structureBoundingBox1.field_35678_a;
		this.field_35676_b = structureBoundingBox1.field_35676_b;
		this.field_35677_c = structureBoundingBox1.field_35677_c;
		this.field_35674_d = structureBoundingBox1.field_35674_d;
		this.field_35675_e = structureBoundingBox1.field_35675_e;
		this.field_35673_f = structureBoundingBox1.field_35673_f;
	}

	public StructureBoundingBox(int i1, int i2, int i3, int i4, int i5, int i6) {
		this.field_35678_a = i1;
		this.field_35676_b = i2;
		this.field_35677_c = i3;
		this.field_35674_d = i4;
		this.field_35675_e = i5;
		this.field_35673_f = i6;
	}

	public StructureBoundingBox(int i1, int i2, int i3, int i4) {
		this.field_35678_a = i1;
		this.field_35676_b = 0;
		this.field_35677_c = i2;
		this.field_35674_d = i3;
		this.field_35675_e = 65536;
		this.field_35673_f = i4;
	}

	public boolean func_35664_a(StructureBoundingBox structureBoundingBox1) {
		return this.field_35674_d >= structureBoundingBox1.field_35678_a && this.field_35678_a <= structureBoundingBox1.field_35674_d && this.field_35673_f >= structureBoundingBox1.field_35677_c && this.field_35677_c <= structureBoundingBox1.field_35673_f && this.field_35675_e >= structureBoundingBox1.field_35676_b && this.field_35676_b <= structureBoundingBox1.field_35675_e;
	}

	public boolean func_35671_a(int i1, int i2, int i3, int i4) {
		return this.field_35674_d >= i1 && this.field_35678_a <= i3 && this.field_35673_f >= i2 && this.field_35677_c <= i4;
	}

	public void func_35666_b(StructureBoundingBox structureBoundingBox1) {
		this.field_35678_a = Math.min(this.field_35678_a, structureBoundingBox1.field_35678_a);
		this.field_35676_b = Math.min(this.field_35676_b, structureBoundingBox1.field_35676_b);
		this.field_35677_c = Math.min(this.field_35677_c, structureBoundingBox1.field_35677_c);
		this.field_35674_d = Math.max(this.field_35674_d, structureBoundingBox1.field_35674_d);
		this.field_35675_e = Math.max(this.field_35675_e, structureBoundingBox1.field_35675_e);
		this.field_35673_f = Math.max(this.field_35673_f, structureBoundingBox1.field_35673_f);
	}

	public void func_35670_a(int i1, int i2, int i3) {
		this.field_35678_a += i1;
		this.field_35676_b += i2;
		this.field_35677_c += i3;
		this.field_35674_d += i1;
		this.field_35675_e += i2;
		this.field_35673_f += i3;
	}

	public boolean func_35667_b(int i1, int i2, int i3) {
		return i1 >= this.field_35678_a && i1 <= this.field_35674_d && i3 >= this.field_35677_c && i3 <= this.field_35673_f && i2 >= this.field_35676_b && i2 <= this.field_35675_e;
	}

	public int func_35669_b() {
		return this.field_35674_d - this.field_35678_a + 1;
	}

	public int func_35668_c() {
		return this.field_35675_e - this.field_35676_b + 1;
	}

	public int func_35665_d() {
		return this.field_35673_f - this.field_35677_c + 1;
	}

	public String toString() {
		return "(" + this.field_35678_a + ", " + this.field_35676_b + ", " + this.field_35677_c + "; " + this.field_35674_d + ", " + this.field_35675_e + ", " + this.field_35673_f + ")";
	}
}
