package com.jcraft.jogg;

public class SyncState {
	public byte[] data;
	int storage;
	int fill;
	int returned;
	int unsynced;
	int headerbytes;
	int bodybytes;
	private final Page pageseek = new Page();
	private final byte[] chksum = new byte[4];

	public int clear() {
		this.data = null;
		return 0;
	}

	public int buffer(int i1) {
		if(this.returned != 0) {
			this.fill -= this.returned;
			if(this.fill > 0) {
				System.arraycopy(this.data, this.returned, this.data, 0, this.fill);
			}

			this.returned = 0;
		}

		if(i1 > this.storage - this.fill) {
			int i2 = i1 + this.fill + 4096;
			if(this.data != null) {
				byte[] b3 = new byte[i2];
				System.arraycopy(this.data, 0, b3, 0, this.data.length);
				this.data = b3;
			} else {
				this.data = new byte[i2];
			}

			this.storage = i2;
		}

		return this.fill;
	}

	public int wrote(int i1) {
		if(this.fill + i1 > this.storage) {
			return -1;
		} else {
			this.fill += i1;
			return 0;
		}
	}

	public int pageseek(Page page1) {
		int i2 = this.returned;
		int i4 = this.fill - this.returned;
		int i3;
		int i7;
		if(this.headerbytes == 0) {
			if(i4 < 27) {
				return 0;
			}

			if(this.data[i2] != 79 || this.data[i2 + 1] != 103 || this.data[i2 + 2] != 103 || this.data[i2 + 3] != 83) {
				this.headerbytes = 0;
				this.bodybytes = 0;
				i3 = 0;

				for(i7 = 0; i7 < i4 - 1; ++i7) {
					if(this.data[i2 + 1 + i7] == 79) {
						i3 = i2 + 1 + i7;
						break;
					}
				}

				if(i3 == 0) {
					i3 = this.fill;
				}

				this.returned = i3;
				return -(i3 - i2);
			}

			int i5 = (this.data[i2 + 26] & 255) + 27;
			if(i4 < i5) {
				return 0;
			}

			for(int i6 = 0; i6 < (this.data[i2 + 26] & 255); ++i6) {
				this.bodybytes += this.data[i2 + 27 + i6] & 255;
			}

			this.headerbytes = i5;
		}

		if(this.bodybytes + this.headerbytes > i4) {
			return 0;
		} else {
			byte[] b10 = this.chksum;
			synchronized(this.chksum) {
				System.arraycopy(this.data, i2 + 22, this.chksum, 0, 4);
				this.data[i2 + 22] = 0;
				this.data[i2 + 23] = 0;
				this.data[i2 + 24] = 0;
				this.data[i2 + 25] = 0;
				Page page11 = this.pageseek;
				page11.header_base = this.data;
				page11.header = i2;
				page11.header_len = this.headerbytes;
				page11.body_base = this.data;
				page11.body = i2 + this.headerbytes;
				page11.body_len = this.bodybytes;
				page11.checksum();
				if(this.chksum[0] != this.data[i2 + 22] || this.chksum[1] != this.data[i2 + 23] || this.chksum[2] != this.data[i2 + 24] || this.chksum[3] != this.data[i2 + 25]) {
					System.arraycopy(this.chksum, 0, this.data, i2 + 22, 4);
					this.headerbytes = 0;
					this.bodybytes = 0;
					i3 = 0;

					for(i7 = 0; i7 < i4 - 1; ++i7) {
						if(this.data[i2 + 1 + i7] == 79) {
							i3 = i2 + 1 + i7;
							break;
						}
					}

					if(i3 == 0) {
						i3 = this.fill;
					}

					this.returned = i3;
					return -(i3 - i2);
				}
			}

			i2 = this.returned;
			if(page1 != null) {
				page1.header_base = this.data;
				page1.header = i2;
				page1.header_len = this.headerbytes;
				page1.body_base = this.data;
				page1.body = i2 + this.headerbytes;
				page1.body_len = this.bodybytes;
			}

			this.unsynced = 0;
			this.returned += i4 = this.headerbytes + this.bodybytes;
			this.headerbytes = 0;
			this.bodybytes = 0;
			return i4;
		}
	}

	public int pageout(Page page1) {
		do {
			int i2 = this.pageseek(page1);
			if(i2 > 0) {
				return 1;
			}

			if(i2 == 0) {
				return 0;
			}
		} while(this.unsynced != 0);

		this.unsynced = 1;
		return -1;
	}

	public int reset() {
		this.fill = 0;
		this.returned = 0;
		this.unsynced = 0;
		this.headerbytes = 0;
		this.bodybytes = 0;
		return 0;
	}

	public void init() {
	}

	public int getDataOffset() {
		return this.returned;
	}

	public int getBufferOffset() {
		return this.fill;
	}
}
