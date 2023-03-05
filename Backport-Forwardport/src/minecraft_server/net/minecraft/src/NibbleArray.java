package net.minecraft.src;

public class NibbleArray {
	public final byte[] data;
	private final int field_35661_b;
	private final int field_35662_c;

	public NibbleArray(int i1, int i2) {
		this.data = new byte[i1 >> 1];
		this.field_35661_b = i2;
		this.field_35662_c = i2 + 4;
	}

	public NibbleArray(byte[] b1, int i2) {
		this.data = b1;
		this.field_35661_b = i2;
		this.field_35662_c = i2 + 4;
	}

	public int getNibble(int i1, int i2, int i3) {
		int i4 = i1 << this.field_35662_c | i3 << this.field_35661_b | i2;
		int i5 = i4 >> 1;
		int i6 = i4 & 1;
		return i6 == 0 ? this.data[i5] & 15 : this.data[i5] >> 4 & 15;
	}

	public void setNibble(int i1, int i2, int i3, int i4) {
		int i5 = i1 << this.field_35662_c | i3 << this.field_35661_b | i2;
		int i6 = i5 >> 1;
		int i7 = i5 & 1;
		if(i7 == 0) {
			this.data[i6] = (byte)(this.data[i6] & 240 | i4 & 15);
		} else {
			this.data[i6] = (byte)(this.data[i6] & 15 | (i4 & 15) << 4);
		}

	}

	public boolean isValid() {
		return this.data != null;
	}
}
