package com.jcraft.jogg;

public class Buffer {
	private static final int BUFFER_INCREMENT = 256;
	private static final int[] mask = new int[]{0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, 65535, 131071, 262143, 524287, 1048575, 2097151, 4194303, 8388607, 16777215, 33554431, 67108863, 134217727, 268435455, 536870911, 1073741823, Integer.MAX_VALUE, -1};
	int ptr = 0;
	byte[] buffer = null;
	int endbit = 0;
	int endbyte = 0;
	int storage = 0;

	public void writeinit() {
		this.buffer = new byte[256];
		this.ptr = 0;
		this.buffer[0] = 0;
		this.storage = 256;
	}

	public void write(byte[] b1) {
		for(int i2 = 0; i2 < b1.length && b1[i2] != 0; ++i2) {
			this.write(b1[i2], 8);
		}

	}

	public void read(byte[] b1, int i2) {
		for(int i3 = 0; i2-- != 0; b1[i3++] = (byte)this.read(8)) {
		}

	}

	void reset() {
		this.ptr = 0;
		this.buffer[0] = 0;
		this.endbit = this.endbyte = 0;
	}

	public void writeclear() {
		this.buffer = null;
	}

	public void readinit(byte[] b1, int i2) {
		this.readinit(b1, 0, i2);
	}

	public void readinit(byte[] b1, int i2, int i3) {
		this.ptr = i2;
		this.buffer = b1;
		this.endbit = this.endbyte = 0;
		this.storage = i3;
	}

	public void write(int i1, int i2) {
		if(this.endbyte + 4 >= this.storage) {
			byte[] b3 = new byte[this.storage + 256];
			System.arraycopy(this.buffer, 0, b3, 0, this.storage);
			this.buffer = b3;
			this.storage += 256;
		}

		i1 &= mask[i2];
		i2 += this.endbit;
		this.buffer[this.ptr] |= (byte)(i1 << this.endbit);
		if(i2 >= 8) {
			this.buffer[this.ptr + 1] = (byte)(i1 >>> 8 - this.endbit);
			if(i2 >= 16) {
				this.buffer[this.ptr + 2] = (byte)(i1 >>> 16 - this.endbit);
				if(i2 >= 24) {
					this.buffer[this.ptr + 3] = (byte)(i1 >>> 24 - this.endbit);
					if(i2 >= 32) {
						if(this.endbit > 0) {
							this.buffer[this.ptr + 4] = (byte)(i1 >>> 32 - this.endbit);
						} else {
							this.buffer[this.ptr + 4] = 0;
						}
					}
				}
			}
		}

		this.endbyte += i2 / 8;
		this.ptr += i2 / 8;
		this.endbit = i2 & 7;
	}

	public int look(int i1) {
		int i3 = mask[i1];
		i1 += this.endbit;
		if(this.endbyte + 4 >= this.storage && this.endbyte + (i1 - 1) / 8 >= this.storage) {
			return -1;
		} else {
			int i2 = (this.buffer[this.ptr] & 255) >>> this.endbit;
			if(i1 > 8) {
				i2 |= (this.buffer[this.ptr + 1] & 255) << 8 - this.endbit;
				if(i1 > 16) {
					i2 |= (this.buffer[this.ptr + 2] & 255) << 16 - this.endbit;
					if(i1 > 24) {
						i2 |= (this.buffer[this.ptr + 3] & 255) << 24 - this.endbit;
						if(i1 > 32 && this.endbit != 0) {
							i2 |= (this.buffer[this.ptr + 4] & 255) << 32 - this.endbit;
						}
					}
				}
			}

			return i3 & i2;
		}
	}

	public int look1() {
		return this.endbyte >= this.storage ? -1 : this.buffer[this.ptr] >> this.endbit & 1;
	}

	public void adv(int i1) {
		i1 += this.endbit;
		this.ptr += i1 / 8;
		this.endbyte += i1 / 8;
		this.endbit = i1 & 7;
	}

	public void adv1() {
		++this.endbit;
		if(this.endbit > 7) {
			this.endbit = 0;
			++this.ptr;
			++this.endbyte;
		}

	}

	public int read(int i1) {
		int i3 = mask[i1];
		i1 += this.endbit;
		if(this.endbyte + 4 >= this.storage) {
			byte b2 = -1;
			if(this.endbyte + (i1 - 1) / 8 >= this.storage) {
				this.ptr += i1 / 8;
				this.endbyte += i1 / 8;
				this.endbit = i1 & 7;
				return b2;
			}
		}

		int i4 = (this.buffer[this.ptr] & 255) >>> this.endbit;
		if(i1 > 8) {
			i4 |= (this.buffer[this.ptr + 1] & 255) << 8 - this.endbit;
			if(i1 > 16) {
				i4 |= (this.buffer[this.ptr + 2] & 255) << 16 - this.endbit;
				if(i1 > 24) {
					i4 |= (this.buffer[this.ptr + 3] & 255) << 24 - this.endbit;
					if(i1 > 32 && this.endbit != 0) {
						i4 |= (this.buffer[this.ptr + 4] & 255) << 32 - this.endbit;
					}
				}
			}
		}

		i4 &= i3;
		this.ptr += i1 / 8;
		this.endbyte += i1 / 8;
		this.endbit = i1 & 7;
		return i4;
	}

	public int readB(int i1) {
		int i3 = 32 - i1;
		i1 += this.endbit;
		if(this.endbyte + 4 >= this.storage) {
			byte b2 = -1;
			if(this.endbyte * 8 + i1 > this.storage * 8) {
				this.ptr += i1 / 8;
				this.endbyte += i1 / 8;
				this.endbit = i1 & 7;
				return b2;
			}
		}

		int i4 = (this.buffer[this.ptr] & 255) << 24 + this.endbit;
		if(i1 > 8) {
			i4 |= (this.buffer[this.ptr + 1] & 255) << 16 + this.endbit;
			if(i1 > 16) {
				i4 |= (this.buffer[this.ptr + 2] & 255) << 8 + this.endbit;
				if(i1 > 24) {
					i4 |= (this.buffer[this.ptr + 3] & 255) << this.endbit;
					if(i1 > 32 && this.endbit != 0) {
						i4 |= (this.buffer[this.ptr + 4] & 255) >> 8 - this.endbit;
					}
				}
			}
		}

		i4 = i4 >>> (i3 >> 1) >>> (i3 + 1 >> 1);
		this.ptr += i1 / 8;
		this.endbyte += i1 / 8;
		this.endbit = i1 & 7;
		return i4;
	}

	public int read1() {
		if(this.endbyte >= this.storage) {
			byte b2 = -1;
			++this.endbit;
			if(this.endbit > 7) {
				this.endbit = 0;
				++this.ptr;
				++this.endbyte;
			}

			return b2;
		} else {
			int i1 = this.buffer[this.ptr] >> this.endbit & 1;
			++this.endbit;
			if(this.endbit > 7) {
				this.endbit = 0;
				++this.ptr;
				++this.endbyte;
			}

			return i1;
		}
	}

	public int bytes() {
		return this.endbyte + (this.endbit + 7) / 8;
	}

	public int bits() {
		return this.endbyte * 8 + this.endbit;
	}

	public byte[] buffer() {
		return this.buffer;
	}

	public static int ilog(int i0) {
		int i1;
		for(i1 = 0; i0 > 0; i0 >>>= 1) {
			++i1;
		}

		return i1;
	}

	public static void report(String string0) {
		System.err.println(string0);
		System.exit(1);
	}
}
