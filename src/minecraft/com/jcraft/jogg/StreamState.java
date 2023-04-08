package com.jcraft.jogg;

public class StreamState {
	byte[] body_data;
	int body_storage;
	int body_fill;
	private int body_returned;
	int[] lacing_vals;
	long[] granule_vals;
	int lacing_storage;
	int lacing_fill;
	int lacing_packet;
	int lacing_returned;
	byte[] header;
	int header_fill;
	public int e_o_s;
	int b_o_s;
	int serialno;
	int pageno;
	long packetno;
	long granulepos;

	public StreamState() {
		this.header = new byte[282];
		this.init();
	}

	StreamState(int i1) {
		this();
		this.init(i1);
	}

	void init() {
		this.body_storage = 16384;
		this.body_data = new byte[this.body_storage];
		this.lacing_storage = 1024;
		this.lacing_vals = new int[this.lacing_storage];
		this.granule_vals = new long[this.lacing_storage];
	}

	public void init(int i1) {
		if(this.body_data == null) {
			this.init();
		} else {
			int i2;
			for(i2 = 0; i2 < this.body_data.length; ++i2) {
				this.body_data[i2] = 0;
			}

			for(i2 = 0; i2 < this.lacing_vals.length; ++i2) {
				this.lacing_vals[i2] = 0;
			}

			for(i2 = 0; i2 < this.granule_vals.length; ++i2) {
				this.granule_vals[i2] = 0L;
			}
		}

		this.serialno = i1;
	}

	public void clear() {
		this.body_data = null;
		this.lacing_vals = null;
		this.granule_vals = null;
	}

	void destroy() {
		this.clear();
	}

	void body_expand(int i1) {
		if(this.body_storage <= this.body_fill + i1) {
			this.body_storage += i1 + 1024;
			byte[] b2 = new byte[this.body_storage];
			System.arraycopy(this.body_data, 0, b2, 0, this.body_data.length);
			this.body_data = b2;
		}

	}

	void lacing_expand(int i1) {
		if(this.lacing_storage <= this.lacing_fill + i1) {
			this.lacing_storage += i1 + 32;
			int[] i2 = new int[this.lacing_storage];
			System.arraycopy(this.lacing_vals, 0, i2, 0, this.lacing_vals.length);
			this.lacing_vals = i2;
			long[] j3 = new long[this.lacing_storage];
			System.arraycopy(this.granule_vals, 0, j3, 0, this.granule_vals.length);
			this.granule_vals = j3;
		}

	}

	public int packetin(Packet packet1) {
		int i2 = packet1.bytes / 255 + 1;
		if(this.body_returned != 0) {
			this.body_fill -= this.body_returned;
			if(this.body_fill != 0) {
				System.arraycopy(this.body_data, this.body_returned, this.body_data, 0, this.body_fill);
			}

			this.body_returned = 0;
		}

		this.body_expand(packet1.bytes);
		this.lacing_expand(i2);
		System.arraycopy(packet1.packet_base, packet1.packet, this.body_data, this.body_fill, packet1.bytes);
		this.body_fill += packet1.bytes;

		int i3;
		for(i3 = 0; i3 < i2 - 1; ++i3) {
			this.lacing_vals[this.lacing_fill + i3] = 255;
			this.granule_vals[this.lacing_fill + i3] = this.granulepos;
		}

		this.lacing_vals[this.lacing_fill + i3] = packet1.bytes % 255;
		this.granulepos = this.granule_vals[this.lacing_fill + i3] = packet1.granulepos;
		this.lacing_vals[this.lacing_fill] |= 256;
		this.lacing_fill += i2;
		++this.packetno;
		if(packet1.e_o_s != 0) {
			this.e_o_s = 1;
		}

		return 0;
	}

	public int packetout(Packet packet1) {
		int i2 = this.lacing_returned;
		if(this.lacing_packet <= i2) {
			return 0;
		} else if((this.lacing_vals[i2] & 1024) != 0) {
			++this.lacing_returned;
			++this.packetno;
			return -1;
		} else {
			int i3 = this.lacing_vals[i2] & 255;
			byte b4 = 0;
			packet1.packet_base = this.body_data;
			packet1.packet = this.body_returned;
			packet1.e_o_s = this.lacing_vals[i2] & 512;
			packet1.b_o_s = this.lacing_vals[i2] & 256;

			int i6;
			for(i6 = b4 + i3; i3 == 255; i6 += i3) {
				++i2;
				int i5 = this.lacing_vals[i2];
				i3 = i5 & 255;
				if((i5 & 512) != 0) {
					packet1.e_o_s = 512;
				}
			}

			packet1.packetno = this.packetno;
			packet1.granulepos = this.granule_vals[i2];
			packet1.bytes = i6;
			this.body_returned += i6;
			this.lacing_returned = i2 + 1;
			++this.packetno;
			return 1;
		}
	}

	public int pagein(Page page1) {
		byte[] b2 = page1.header_base;
		int i3 = page1.header;
		byte[] b4 = page1.body_base;
		int i5 = page1.body;
		int i6 = page1.body_len;
		int i7 = 0;
		int i8 = page1.version();
		int i9 = page1.continued();
		int i10 = page1.bos();
		int i11 = page1.eos();
		long j12 = page1.granulepos();
		int i14 = page1.serialno();
		int i15 = page1.pageno();
		int i16 = b2[i3 + 26] & 255;
		int i17 = this.lacing_returned;
		int i18 = this.body_returned;
		if(i18 != 0) {
			this.body_fill -= i18;
			if(this.body_fill != 0) {
				System.arraycopy(this.body_data, i18, this.body_data, 0, this.body_fill);
			}

			this.body_returned = 0;
		}

		if(i17 != 0) {
			if(this.lacing_fill - i17 != 0) {
				System.arraycopy(this.lacing_vals, i17, this.lacing_vals, 0, this.lacing_fill - i17);
				System.arraycopy(this.granule_vals, i17, this.granule_vals, 0, this.lacing_fill - i17);
			}

			this.lacing_fill -= i17;
			this.lacing_packet -= i17;
			this.lacing_returned = 0;
		}

		if(i14 != this.serialno) {
			return -1;
		} else if(i8 > 0) {
			return -1;
		} else {
			this.lacing_expand(i16 + 1);
			if(i15 != this.pageno) {
				for(i17 = this.lacing_packet; i17 < this.lacing_fill; ++i17) {
					this.body_fill -= this.lacing_vals[i17] & 255;
				}

				this.lacing_fill = this.lacing_packet;
				if(this.pageno != -1) {
					this.lacing_vals[this.lacing_fill++] = 1024;
					++this.lacing_packet;
				}

				if(i9 != 0) {
					for(i10 = 0; i7 < i16; ++i7) {
						i18 = b2[i3 + 27 + i7] & 255;
						i5 += i18;
						i6 -= i18;
						if(i18 < 255) {
							++i7;
							break;
						}
					}
				}
			}

			if(i6 != 0) {
				this.body_expand(i6);
				System.arraycopy(b4, i5, this.body_data, this.body_fill, i6);
				this.body_fill += i6;
			}

			i17 = -1;

			while(i7 < i16) {
				i18 = b2[i3 + 27 + i7] & 255;
				this.lacing_vals[this.lacing_fill] = i18;
				this.granule_vals[this.lacing_fill] = -1L;
				if(i10 != 0) {
					this.lacing_vals[this.lacing_fill] |= 256;
					i10 = 0;
				}

				if(i18 < 255) {
					i17 = this.lacing_fill;
				}

				++this.lacing_fill;
				++i7;
				if(i18 < 255) {
					this.lacing_packet = this.lacing_fill;
				}
			}

			if(i17 != -1) {
				this.granule_vals[i17] = j12;
			}

			if(i11 != 0) {
				this.e_o_s = 1;
				if(this.lacing_fill > 0) {
					this.lacing_vals[this.lacing_fill - 1] |= 512;
				}
			}

			this.pageno = i15 + 1;
			return 0;
		}
	}

	public int flush(Page page1) {
		boolean z3 = false;
		int i4 = this.lacing_fill > 255 ? 255 : this.lacing_fill;
		int i5 = 0;
		int i6 = 0;
		long j7 = this.granule_vals[0];
		if(i4 == 0) {
			return 0;
		} else {
			int i10;
			if(this.b_o_s == 0) {
				j7 = 0L;

				for(i10 = 0; i10 < i4; ++i10) {
					if((this.lacing_vals[i10] & 255) < 255) {
						++i10;
						break;
					}
				}
			} else {
				for(i10 = 0; i10 < i4 && i6 <= 4096; ++i10) {
					i6 += this.lacing_vals[i10] & 255;
					j7 = this.granule_vals[i10];
				}
			}

			System.arraycopy("OggS".getBytes(), 0, this.header, 0, 4);
			this.header[4] = 0;
			this.header[5] = 0;
			if((this.lacing_vals[0] & 256) == 0) {
				this.header[5] = (byte)(this.header[5] | 1);
			}

			if(this.b_o_s == 0) {
				this.header[5] = (byte)(this.header[5] | 2);
			}

			if(this.e_o_s != 0 && this.lacing_fill == i10) {
				this.header[5] = (byte)(this.header[5] | 4);
			}

			this.b_o_s = 1;

			int i2;
			for(i2 = 6; i2 < 14; ++i2) {
				this.header[i2] = (byte)((int)j7);
				j7 >>>= 8;
			}

			int i9 = this.serialno;

			for(i2 = 14; i2 < 18; ++i2) {
				this.header[i2] = (byte)i9;
				i9 >>>= 8;
			}

			if(this.pageno == -1) {
				this.pageno = 0;
			}

			i9 = this.pageno++;

			for(i2 = 18; i2 < 22; ++i2) {
				this.header[i2] = (byte)i9;
				i9 >>>= 8;
			}

			this.header[22] = 0;
			this.header[23] = 0;
			this.header[24] = 0;
			this.header[25] = 0;
			this.header[26] = (byte)i10;

			for(i2 = 0; i2 < i10; ++i2) {
				this.header[i2 + 27] = (byte)this.lacing_vals[i2];
				i5 += this.header[i2 + 27] & 255;
			}

			page1.header_base = this.header;
			page1.header = 0;
			page1.header_len = this.header_fill = i10 + 27;
			page1.body_base = this.body_data;
			page1.body = this.body_returned;
			page1.body_len = i5;
			this.lacing_fill -= i10;
			System.arraycopy(this.lacing_vals, i10, this.lacing_vals, 0, this.lacing_fill * 4);
			System.arraycopy(this.granule_vals, i10, this.granule_vals, 0, this.lacing_fill * 8);
			this.body_returned += i5;
			page1.checksum();
			return 1;
		}
	}

	public int pageout(Page page1) {
		return (this.e_o_s == 0 || this.lacing_fill == 0) && this.body_fill - this.body_returned <= 4096 && this.lacing_fill < 255 && (this.lacing_fill == 0 || this.b_o_s != 0) ? 0 : this.flush(page1);
	}

	public int eof() {
		return this.e_o_s;
	}

	public int reset() {
		this.body_fill = 0;
		this.body_returned = 0;
		this.lacing_fill = 0;
		this.lacing_packet = 0;
		this.lacing_returned = 0;
		this.header_fill = 0;
		this.e_o_s = 0;
		this.b_o_s = 0;
		this.pageno = -1;
		this.packetno = 0L;
		this.granulepos = 0L;
		return 0;
	}
}
