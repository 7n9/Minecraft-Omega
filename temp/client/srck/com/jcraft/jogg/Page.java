package com.jcraft.jogg;

public class Page {
	private static int[] crc_lookup = new int[256];
	public byte[] header_base;
	public int header;
	public int header_len;
	public byte[] body_base;
	public int body;
	public int body_len;

	private static int crc_entry(int i0) {
		int i1 = i0 << 24;

		for(int i2 = 0; i2 < 8; ++i2) {
			if((i1 & Integer.MIN_VALUE) != 0) {
				i1 = i1 << 1 ^ 79764919;
			} else {
				i1 <<= 1;
			}
		}

		return i1 & -1;
	}

	int version() {
		return this.header_base[this.header + 4] & 255;
	}

	int continued() {
		return this.header_base[this.header + 5] & 1;
	}

	public int bos() {
		return this.header_base[this.header + 5] & 2;
	}

	public int eos() {
		return this.header_base[this.header + 5] & 4;
	}

	public long granulepos() {
		long j1 = (long)(this.header_base[this.header + 13] & 255);
		j1 = j1 << 8 | (long)(this.header_base[this.header + 12] & 255);
		j1 = j1 << 8 | (long)(this.header_base[this.header + 11] & 255);
		j1 = j1 << 8 | (long)(this.header_base[this.header + 10] & 255);
		j1 = j1 << 8 | (long)(this.header_base[this.header + 9] & 255);
		j1 = j1 << 8 | (long)(this.header_base[this.header + 8] & 255);
		j1 = j1 << 8 | (long)(this.header_base[this.header + 7] & 255);
		j1 = j1 << 8 | (long)(this.header_base[this.header + 6] & 255);
		return j1;
	}

	public int serialno() {
		return this.header_base[this.header + 14] & 255 | (this.header_base[this.header + 15] & 255) << 8 | (this.header_base[this.header + 16] & 255) << 16 | (this.header_base[this.header + 17] & 255) << 24;
	}

	int pageno() {
		return this.header_base[this.header + 18] & 255 | (this.header_base[this.header + 19] & 255) << 8 | (this.header_base[this.header + 20] & 255) << 16 | (this.header_base[this.header + 21] & 255) << 24;
	}

	void checksum() {
		int i1 = 0;

		int i2;
		for(i2 = 0; i2 < this.header_len; ++i2) {
			i1 = i1 << 8 ^ crc_lookup[i1 >>> 24 & 255 ^ this.header_base[this.header + i2] & 255];
		}

		for(i2 = 0; i2 < this.body_len; ++i2) {
			i1 = i1 << 8 ^ crc_lookup[i1 >>> 24 & 255 ^ this.body_base[this.body + i2] & 255];
		}

		this.header_base[this.header + 22] = (byte)i1;
		this.header_base[this.header + 23] = (byte)(i1 >>> 8);
		this.header_base[this.header + 24] = (byte)(i1 >>> 16);
		this.header_base[this.header + 25] = (byte)(i1 >>> 24);
	}

	public Page copy() {
		return this.copy(new Page());
	}

	public Page copy(Page page1) {
		byte[] b2 = new byte[this.header_len];
		System.arraycopy(this.header_base, this.header, b2, 0, this.header_len);
		page1.header_len = this.header_len;
		page1.header_base = b2;
		page1.header = 0;
		b2 = new byte[this.body_len];
		System.arraycopy(this.body_base, this.body, b2, 0, this.body_len);
		page1.body_len = this.body_len;
		page1.body_base = b2;
		page1.body = 0;
		return page1;
	}

	static {
		for(int i0 = 0; i0 < crc_lookup.length; ++i0) {
			crc_lookup[i0] = crc_entry(i0);
		}

	}
}
