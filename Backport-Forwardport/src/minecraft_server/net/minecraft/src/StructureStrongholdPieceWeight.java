package net.minecraft.src;

class StructureStrongholdPieceWeight {
	public Class field_35580_a;
	public final int field_35578_b;
	public int field_35579_c;
	public int field_35577_d;

	public StructureStrongholdPieceWeight(Class class1, int i2, int i3) {
		this.field_35580_a = class1;
		this.field_35578_b = i2;
		this.field_35577_d = i3;
	}

	public boolean func_35575_a(int i1) {
		return this.field_35577_d == 0 || this.field_35579_c < this.field_35577_d;
	}

	public boolean func_35576_a() {
		return this.field_35577_d == 0 || this.field_35579_c < this.field_35577_d;
	}
}
