package com.jcraft.jorbis;

import com.jcraft.jogg.Buffer;
import com.jcraft.jogg.Packet;

public class Block {
	float[][] pcm = new float[0][];
	Buffer opb = new Buffer();
	int lW;
	int W;
	int nW;
	int pcmend;
	int mode;
	int eofflag;
	long granulepos;
	long sequence;
	DspState vd;
	int glue_bits;
	int time_bits;
	int floor_bits;
	int res_bits;

	public Block(DspState dspState1) {
		this.vd = dspState1;
		if(dspState1.analysisp != 0) {
			this.opb.writeinit();
		}

	}

	public void init(DspState dspState1) {
		this.vd = dspState1;
	}

	public int clear() {
		if(this.vd != null && this.vd.analysisp != 0) {
			this.opb.writeclear();
		}

		return 0;
	}

	public int synthesis(Packet packet1) {
		Info info2 = this.vd.vi;
		this.opb.readinit(packet1.packet_base, packet1.packet, packet1.bytes);
		if(this.opb.read(1) != 0) {
			return -1;
		} else {
			int i3 = this.opb.read(this.vd.modebits);
			if(i3 == -1) {
				return -1;
			} else {
				this.mode = i3;
				this.W = info2.mode_param[this.mode].blockflag;
				if(this.W != 0) {
					this.lW = this.opb.read(1);
					this.nW = this.opb.read(1);
					if(this.nW == -1) {
						return -1;
					}
				} else {
					this.lW = 0;
					this.nW = 0;
				}

				this.granulepos = packet1.granulepos;
				this.sequence = packet1.packetno - 3L;
				this.eofflag = packet1.e_o_s;
				this.pcmend = info2.blocksizes[this.W];
				if(this.pcm.length < info2.channels) {
					this.pcm = new float[info2.channels][];
				}

				int i4;
				for(i4 = 0; i4 < info2.channels; ++i4) {
					if(this.pcm[i4] != null && this.pcm[i4].length >= this.pcmend) {
						for(int i5 = 0; i5 < this.pcmend; ++i5) {
							this.pcm[i4][i5] = 0.0F;
						}
					} else {
						this.pcm[i4] = new float[this.pcmend];
					}
				}

				i4 = info2.map_type[info2.mode_param[this.mode].mapping];
				return FuncMapping.mapping_P[i4].inverse(this, this.vd.mode[this.mode]);
			}
		}
	}
}
