package com.jcraft.jorbis;

import com.jcraft.jogg.Buffer;
import com.jcraft.jogg.Packet;

public class Comment {
	private static final byte[] _vorbis = "vorbis".getBytes();
	private static final byte[] _vendor = "Xiphophorus libVorbis I 20000508".getBytes();
	private static final int OV_EIMPL = -130;
	public byte[][] user_comments;
	public int[] comment_lengths;
	public int comments;
	public byte[] vendor;

	public void init() {
		this.user_comments = null;
		this.comments = 0;
		this.vendor = null;
	}

	public void add(String string1) {
		this.add(string1.getBytes());
	}

	private void add(byte[] b1) {
		byte[][] b2 = new byte[this.comments + 2][];
		if(this.user_comments != null) {
			System.arraycopy(this.user_comments, 0, b2, 0, this.comments);
		}

		this.user_comments = b2;
		int[] i3 = new int[this.comments + 2];
		if(this.comment_lengths != null) {
			System.arraycopy(this.comment_lengths, 0, i3, 0, this.comments);
		}

		this.comment_lengths = i3;
		byte[] b4 = new byte[b1.length + 1];
		System.arraycopy(b1, 0, b4, 0, b1.length);
		this.user_comments[this.comments] = b4;
		this.comment_lengths[this.comments] = b1.length;
		++this.comments;
		this.user_comments[this.comments] = null;
	}

	public void add_tag(String string1, String string2) {
		if(string2 == null) {
			string2 = "";
		}

		this.add(string1 + "=" + string2);
	}

	static boolean tagcompare(byte[] b0, byte[] b1, int i2) {
		for(int i3 = 0; i3 < i2; ++i3) {
			byte b4 = b0[i3];
			byte b5 = b1[i3];
			if(90 >= b4 && b4 >= 65) {
				b4 = (byte)(b4 - 65 + 97);
			}

			if(90 >= b5 && b5 >= 65) {
				b5 = (byte)(b5 - 65 + 97);
			}

			if(b4 != b5) {
				return false;
			}
		}

		return true;
	}

	public String query(String string1) {
		return this.query(string1, 0);
	}

	public String query(String string1, int i2) {
		int i3 = this.query(string1.getBytes(), i2);
		if(i3 == -1) {
			return null;
		} else {
			byte[] b4 = this.user_comments[i3];

			for(int i5 = 0; i5 < this.comment_lengths[i3]; ++i5) {
				if(b4[i5] == 61) {
					return new String(b4, i5 + 1, this.comment_lengths[i3] - (i5 + 1));
				}
			}

			return null;
		}
	}

	private int query(byte[] b1, int i2) {
		boolean z3 = false;
		int i4 = 0;
		int i5 = b1.length + 1;
		byte[] b6 = new byte[i5];
		System.arraycopy(b1, 0, b6, 0, b1.length);
		b6[b1.length] = 61;

		for(int i7 = 0; i7 < this.comments; ++i7) {
			if(tagcompare(this.user_comments[i7], b6, i5)) {
				if(i2 == i4) {
					return i7;
				}

				++i4;
			}
		}

		return -1;
	}

	int unpack(Buffer buffer1) {
		int i2 = buffer1.read(32);
		if(i2 < 0) {
			this.clear();
			return -1;
		} else {
			this.vendor = new byte[i2 + 1];
			buffer1.read(this.vendor, i2);
			this.comments = buffer1.read(32);
			if(this.comments < 0) {
				this.clear();
				return -1;
			} else {
				this.user_comments = new byte[this.comments + 1][];
				this.comment_lengths = new int[this.comments + 1];

				for(int i3 = 0; i3 < this.comments; ++i3) {
					int i4 = buffer1.read(32);
					if(i4 < 0) {
						this.clear();
						return -1;
					}

					this.comment_lengths[i3] = i4;
					this.user_comments[i3] = new byte[i4 + 1];
					buffer1.read(this.user_comments[i3], i4);
				}

				if(buffer1.read(1) != 1) {
					this.clear();
					return -1;
				} else {
					return 0;
				}
			}
		}
	}

	int pack(Buffer buffer1) {
		buffer1.write(3, 8);
		buffer1.write(_vorbis);
		buffer1.write(_vendor.length, 32);
		buffer1.write(_vendor);
		buffer1.write(this.comments, 32);
		if(this.comments != 0) {
			for(int i2 = 0; i2 < this.comments; ++i2) {
				if(this.user_comments[i2] != null) {
					buffer1.write(this.comment_lengths[i2], 32);
					buffer1.write(this.user_comments[i2]);
				} else {
					buffer1.write(0, 32);
				}
			}
		}

		buffer1.write(1, 1);
		return 0;
	}

	public int header_out(Packet packet1) {
		Buffer buffer2 = new Buffer();
		buffer2.writeinit();
		if(this.pack(buffer2) != 0) {
			return -130;
		} else {
			packet1.packet_base = new byte[buffer2.bytes()];
			packet1.packet = 0;
			packet1.bytes = buffer2.bytes();
			System.arraycopy(buffer2.buffer(), 0, packet1.packet_base, 0, packet1.bytes);
			packet1.b_o_s = 0;
			packet1.e_o_s = 0;
			packet1.granulepos = 0L;
			return 0;
		}
	}

	void clear() {
		for(int i1 = 0; i1 < this.comments; ++i1) {
			this.user_comments[i1] = null;
		}

		this.user_comments = null;
		this.vendor = null;
	}

	public String getVendor() {
		return new String(this.vendor, 0, this.vendor.length - 1);
	}

	public String getComment(int i1) {
		return this.comments <= i1 ? null : new String(this.user_comments[i1], 0, this.user_comments[i1].length - 1);
	}

	public String toString() {
		String string1 = "Vendor: " + new String(this.vendor, 0, this.vendor.length - 1);

		for(int i2 = 0; i2 < this.comments; ++i2) {
			string1 = string1 + "\nComment: " + new String(this.user_comments[i2], 0, this.user_comments[i2].length - 1);
		}

		string1 = string1 + "\n";
		return string1;
	}
}
