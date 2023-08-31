package com.jcraft.jorbis;

import com.jcraft.jogg.Buffer;
import com.jcraft.jogg.Packet;

public class Info {
	private static final int OV_EBADPACKET = -136;
	private static final int OV_ENOTAUDIO = -135;
	private static final byte[] _vorbis = "vorbis".getBytes();
	private static final int VI_TIMEB = 1;
	private static final int VI_FLOORB = 2;
	private static final int VI_RESB = 3;
	private static final int VI_MAPB = 1;
	private static final int VI_WINDOWB = 1;
	public int version;
	public int channels;
	public int rate;
	int bitrate_upper;
	int bitrate_nominal;
	int bitrate_lower;
	int[] blocksizes = new int[2];
	int modes;
	int maps;
	int times;
	int floors;
	int residues;
	int books;
	int psys;
	InfoMode[] mode_param = null;
	int[] map_type = null;
	Object[] map_param = null;
	int[] time_type = null;
	Object[] time_param = null;
	int[] floor_type = null;
	Object[] floor_param = null;
	int[] residue_type = null;
	Object[] residue_param = null;
	StaticCodeBook[] book_param = null;
	PsyInfo[] psy_param = new PsyInfo[64];
	int envelopesa;
	float preecho_thresh;
	float preecho_clamp;

	public void init() {
		this.rate = 0;
	}

	public void clear() {
		int i1;
		for(i1 = 0; i1 < this.modes; ++i1) {
			this.mode_param[i1] = null;
		}

		this.mode_param = null;

		for(i1 = 0; i1 < this.maps; ++i1) {
			FuncMapping.mapping_P[this.map_type[i1]].free_info(this.map_param[i1]);
		}

		this.map_param = null;

		for(i1 = 0; i1 < this.times; ++i1) {
			FuncTime.time_P[this.time_type[i1]].free_info(this.time_param[i1]);
		}

		this.time_param = null;

		for(i1 = 0; i1 < this.floors; ++i1) {
			FuncFloor.floor_P[this.floor_type[i1]].free_info(this.floor_param[i1]);
		}

		this.floor_param = null;

		for(i1 = 0; i1 < this.residues; ++i1) {
			FuncResidue.residue_P[this.residue_type[i1]].free_info(this.residue_param[i1]);
		}

		this.residue_param = null;

		for(i1 = 0; i1 < this.books; ++i1) {
			if(this.book_param[i1] != null) {
				this.book_param[i1].clear();
				this.book_param[i1] = null;
			}
		}

		this.book_param = null;

		for(i1 = 0; i1 < this.psys; ++i1) {
			this.psy_param[i1].free();
		}

	}

	int unpack_info(Buffer buffer1) {
		this.version = buffer1.read(32);
		if(this.version != 0) {
			return -1;
		} else {
			this.channels = buffer1.read(8);
			this.rate = buffer1.read(32);
			this.bitrate_upper = buffer1.read(32);
			this.bitrate_nominal = buffer1.read(32);
			this.bitrate_lower = buffer1.read(32);
			this.blocksizes[0] = 1 << buffer1.read(4);
			this.blocksizes[1] = 1 << buffer1.read(4);
			if(this.rate >= 1 && this.channels >= 1 && this.blocksizes[0] >= 8 && this.blocksizes[1] >= this.blocksizes[0] && buffer1.read(1) == 1) {
				return 0;
			} else {
				this.clear();
				return -1;
			}
		}
	}

	int unpack_books(Buffer buffer1) {
		this.books = buffer1.read(8) + 1;
		if(this.book_param == null || this.book_param.length != this.books) {
			this.book_param = new StaticCodeBook[this.books];
		}

		int i2;
		for(i2 = 0; i2 < this.books; ++i2) {
			this.book_param[i2] = new StaticCodeBook();
			if(this.book_param[i2].unpack(buffer1) != 0) {
				this.clear();
				return -1;
			}
		}

		this.times = buffer1.read(6) + 1;
		if(this.time_type == null || this.time_type.length != this.times) {
			this.time_type = new int[this.times];
		}

		if(this.time_param == null || this.time_param.length != this.times) {
			this.time_param = new Object[this.times];
		}

		for(i2 = 0; i2 < this.times; ++i2) {
			this.time_type[i2] = buffer1.read(16);
			if(this.time_type[i2] < 0 || this.time_type[i2] >= 1) {
				this.clear();
				return -1;
			}

			this.time_param[i2] = FuncTime.time_P[this.time_type[i2]].unpack(this, buffer1);
			if(this.time_param[i2] == null) {
				this.clear();
				return -1;
			}
		}

		this.floors = buffer1.read(6) + 1;
		if(this.floor_type == null || this.floor_type.length != this.floors) {
			this.floor_type = new int[this.floors];
		}

		if(this.floor_param == null || this.floor_param.length != this.floors) {
			this.floor_param = new Object[this.floors];
		}

		for(i2 = 0; i2 < this.floors; ++i2) {
			this.floor_type[i2] = buffer1.read(16);
			if(this.floor_type[i2] < 0 || this.floor_type[i2] >= 2) {
				this.clear();
				return -1;
			}

			this.floor_param[i2] = FuncFloor.floor_P[this.floor_type[i2]].unpack(this, buffer1);
			if(this.floor_param[i2] == null) {
				this.clear();
				return -1;
			}
		}

		this.residues = buffer1.read(6) + 1;
		if(this.residue_type == null || this.residue_type.length != this.residues) {
			this.residue_type = new int[this.residues];
		}

		if(this.residue_param == null || this.residue_param.length != this.residues) {
			this.residue_param = new Object[this.residues];
		}

		for(i2 = 0; i2 < this.residues; ++i2) {
			this.residue_type[i2] = buffer1.read(16);
			if(this.residue_type[i2] < 0 || this.residue_type[i2] >= 3) {
				this.clear();
				return -1;
			}

			this.residue_param[i2] = FuncResidue.residue_P[this.residue_type[i2]].unpack(this, buffer1);
			if(this.residue_param[i2] == null) {
				this.clear();
				return -1;
			}
		}

		this.maps = buffer1.read(6) + 1;
		if(this.map_type == null || this.map_type.length != this.maps) {
			this.map_type = new int[this.maps];
		}

		if(this.map_param == null || this.map_param.length != this.maps) {
			this.map_param = new Object[this.maps];
		}

		for(i2 = 0; i2 < this.maps; ++i2) {
			this.map_type[i2] = buffer1.read(16);
			if(this.map_type[i2] < 0 || this.map_type[i2] >= 1) {
				this.clear();
				return -1;
			}

			this.map_param[i2] = FuncMapping.mapping_P[this.map_type[i2]].unpack(this, buffer1);
			if(this.map_param[i2] == null) {
				this.clear();
				return -1;
			}
		}

		this.modes = buffer1.read(6) + 1;
		if(this.mode_param == null || this.mode_param.length != this.modes) {
			this.mode_param = new InfoMode[this.modes];
		}

		for(i2 = 0; i2 < this.modes; ++i2) {
			this.mode_param[i2] = new InfoMode();
			this.mode_param[i2].blockflag = buffer1.read(1);
			this.mode_param[i2].windowtype = buffer1.read(16);
			this.mode_param[i2].transformtype = buffer1.read(16);
			this.mode_param[i2].mapping = buffer1.read(8);
			if(this.mode_param[i2].windowtype >= 1 || this.mode_param[i2].transformtype >= 1 || this.mode_param[i2].mapping >= this.maps) {
				this.clear();
				return -1;
			}
		}

		if(buffer1.read(1) != 1) {
			this.clear();
			return -1;
		} else {
			return 0;
		}
	}

	public int synthesis_headerin(Comment comment1, Packet packet2) {
		Buffer buffer3 = new Buffer();
		if(packet2 != null) {
			buffer3.readinit(packet2.packet_base, packet2.packet, packet2.bytes);
			byte[] b4 = new byte[6];
			int i5 = buffer3.read(8);
			buffer3.read(b4, 6);
			if(b4[0] != 118 || b4[1] != 111 || b4[2] != 114 || b4[3] != 98 || b4[4] != 105 || b4[5] != 115) {
				return -1;
			}

			switch(i5) {
			case 1:
				if(packet2.b_o_s == 0) {
					return -1;
				}

				if(this.rate != 0) {
					return -1;
				}

				return this.unpack_info(buffer3);
			case 2:
			case 4:
			default:
				break;
			case 3:
				if(this.rate == 0) {
					return -1;
				}

				return comment1.unpack(buffer3);
			case 5:
				if(this.rate != 0 && comment1.vendor != null) {
					return this.unpack_books(buffer3);
				}

				return -1;
			}
		}

		return -1;
	}

	int pack_info(Buffer buffer1) {
		buffer1.write(1, 8);
		buffer1.write(_vorbis);
		buffer1.write(0, 32);
		buffer1.write(this.channels, 8);
		buffer1.write(this.rate, 32);
		buffer1.write(this.bitrate_upper, 32);
		buffer1.write(this.bitrate_nominal, 32);
		buffer1.write(this.bitrate_lower, 32);
		buffer1.write(Util.ilog2(this.blocksizes[0]), 4);
		buffer1.write(Util.ilog2(this.blocksizes[1]), 4);
		buffer1.write(1, 1);
		return 0;
	}

	int pack_books(Buffer buffer1) {
		buffer1.write(5, 8);
		buffer1.write(_vorbis);
		buffer1.write(this.books - 1, 8);

		int i2;
		for(i2 = 0; i2 < this.books; ++i2) {
			if(this.book_param[i2].pack(buffer1) != 0) {
				return -1;
			}
		}

		buffer1.write(this.times - 1, 6);

		for(i2 = 0; i2 < this.times; ++i2) {
			buffer1.write(this.time_type[i2], 16);
			FuncTime.time_P[this.time_type[i2]].pack(this.time_param[i2], buffer1);
		}

		buffer1.write(this.floors - 1, 6);

		for(i2 = 0; i2 < this.floors; ++i2) {
			buffer1.write(this.floor_type[i2], 16);
			FuncFloor.floor_P[this.floor_type[i2]].pack(this.floor_param[i2], buffer1);
		}

		buffer1.write(this.residues - 1, 6);

		for(i2 = 0; i2 < this.residues; ++i2) {
			buffer1.write(this.residue_type[i2], 16);
			FuncResidue.residue_P[this.residue_type[i2]].pack(this.residue_param[i2], buffer1);
		}

		buffer1.write(this.maps - 1, 6);

		for(i2 = 0; i2 < this.maps; ++i2) {
			buffer1.write(this.map_type[i2], 16);
			FuncMapping.mapping_P[this.map_type[i2]].pack(this, this.map_param[i2], buffer1);
		}

		buffer1.write(this.modes - 1, 6);

		for(i2 = 0; i2 < this.modes; ++i2) {
			buffer1.write(this.mode_param[i2].blockflag, 1);
			buffer1.write(this.mode_param[i2].windowtype, 16);
			buffer1.write(this.mode_param[i2].transformtype, 16);
			buffer1.write(this.mode_param[i2].mapping, 8);
		}

		buffer1.write(1, 1);
		return 0;
	}

	public int blocksize(Packet packet1) {
		Buffer buffer2 = new Buffer();
		buffer2.readinit(packet1.packet_base, packet1.packet, packet1.bytes);
		if(buffer2.read(1) != 0) {
			return -135;
		} else {
			int i4 = 0;

			for(int i5 = this.modes; i5 > 1; i5 >>>= 1) {
				++i4;
			}

			int i3 = buffer2.read(i4);
			return i3 == -1 ? -136 : this.blocksizes[this.mode_param[i3].blockflag];
		}
	}

	public String toString() {
		return "version:" + new Integer(this.version) + ", channels:" + new Integer(this.channels) + ", rate:" + new Integer(this.rate) + ", bitrate:" + new Integer(this.bitrate_upper) + "," + new Integer(this.bitrate_nominal) + "," + new Integer(this.bitrate_lower);
	}
}
