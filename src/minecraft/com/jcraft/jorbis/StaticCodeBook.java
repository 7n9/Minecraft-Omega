package com.jcraft.jorbis;

import com.jcraft.jogg.Buffer;

import net.minecraft.src.MathHelper;

class StaticCodeBook {
	int dim;
	int entries;
	int[] lengthlist;
	int maptype;
	int q_min;
	int q_delta;
	int q_quant;
	int q_sequencep;
	int[] quantlist;
	static final int VQ_FEXP = 10;
	static final int VQ_FMAN = 21;
	static final int VQ_FEXP_BIAS = 768;

	int pack(Buffer buffer1) {
		boolean z3 = false;
		buffer1.write(5653314, 24);
		buffer1.write(this.dim, 16);
		buffer1.write(this.entries, 24);

		int i2;
		for(i2 = 1; i2 < this.entries && this.lengthlist[i2] >= this.lengthlist[i2 - 1]; ++i2) {
		}

		if(i2 == this.entries) {
			z3 = true;
		}

		int i4;
		if(z3) {
			i4 = 0;
			buffer1.write(1, 1);
			buffer1.write(this.lengthlist[0] - 1, 5);

			for(i2 = 1; i2 < this.entries; ++i2) {
				int i5 = this.lengthlist[i2];
				int i6 = this.lengthlist[i2 - 1];
				if(i5 > i6) {
					for(int i7 = i6; i7 < i5; ++i7) {
						buffer1.write(i2 - i4, Util.ilog(this.entries - i4));
						i4 = i2;
					}
				}
			}

			buffer1.write(i2 - i4, Util.ilog(this.entries - i4));
		} else {
			buffer1.write(0, 1);

			for(i2 = 0; i2 < this.entries && this.lengthlist[i2] != 0; ++i2) {
			}

			if(i2 == this.entries) {
				buffer1.write(0, 1);

				for(i2 = 0; i2 < this.entries; ++i2) {
					buffer1.write(this.lengthlist[i2] - 1, 5);
				}
			} else {
				buffer1.write(1, 1);

				for(i2 = 0; i2 < this.entries; ++i2) {
					if(this.lengthlist[i2] == 0) {
						buffer1.write(0, 1);
					} else {
						buffer1.write(1, 1);
						buffer1.write(this.lengthlist[i2] - 1, 5);
					}
				}
			}
		}

		buffer1.write(this.maptype, 4);
		switch(this.maptype) {
		case 1:
		case 2:
			if(this.quantlist == null) {
				return -1;
			} else {
				buffer1.write(this.q_min, 32);
				buffer1.write(this.q_delta, 32);
				buffer1.write(this.q_quant - 1, 4);
				buffer1.write(this.q_sequencep, 1);
				i4 = 0;
				switch(this.maptype) {
				case 1:
					i4 = this.maptype1_quantvals();
					break;
				case 2:
					i4 = this.entries * this.dim;
				}

				for(i2 = 0; i2 < i4; ++i2) {
					buffer1.write(Math.abs(this.quantlist[i2]), this.q_quant);
				}
			}
		case 0:
			return 0;
		default:
			return -1;
		}
	}

	int unpack(Buffer buffer1) {
		if(buffer1.read(24) != 5653314) {
			this.clear();
			return -1;
		} else {
			this.dim = buffer1.read(16);
			this.entries = buffer1.read(24);
			if(this.entries == -1) {
				this.clear();
				return -1;
			} else {
				int i2;
				int i3;
				label89:
				switch(buffer1.read(1)) {
				case 0:
					this.lengthlist = new int[this.entries];
					if(buffer1.read(1) != 0) {
						i2 = 0;

						while(true) {
							if(i2 >= this.entries) {
								break label89;
							}

							if(buffer1.read(1) != 0) {
								i3 = buffer1.read(5);
								if(i3 == -1) {
									this.clear();
									return -1;
								}

								this.lengthlist[i2] = i3 + 1;
							} else {
								this.lengthlist[i2] = 0;
							}

							++i2;
						}
					} else {
						i2 = 0;

						while(true) {
							if(i2 >= this.entries) {
								break label89;
							}

							i3 = buffer1.read(5);
							if(i3 == -1) {
								this.clear();
								return -1;
							}

							this.lengthlist[i2] = i3 + 1;
							++i2;
						}
					}
				case 1:
					i3 = buffer1.read(5) + 1;
					this.lengthlist = new int[this.entries];
					i2 = 0;

					while(true) {
						if(i2 >= this.entries) {
							break label89;
						}

						int i4 = buffer1.read(Util.ilog(this.entries - i2));
						if(i4 == -1) {
							this.clear();
							return -1;
						}

						for(int i5 = 0; i5 < i4; ++i2) {
							this.lengthlist[i2] = i3;
							++i5;
						}

						++i3;
					}
				default:
					return -1;
				}

				switch(this.maptype = buffer1.read(4)) {
				case 1:
				case 2:
					this.q_min = buffer1.read(32);
					this.q_delta = buffer1.read(32);
					this.q_quant = buffer1.read(4) + 1;
					this.q_sequencep = buffer1.read(1);
					i3 = 0;
					switch(this.maptype) {
					case 1:
						i3 = this.maptype1_quantvals();
						break;
					case 2:
						i3 = this.entries * this.dim;
					}

					this.quantlist = new int[i3];

					for(i2 = 0; i2 < i3; ++i2) {
						this.quantlist[i2] = buffer1.read(this.q_quant);
					}

					if(this.quantlist[i3 - 1] == -1) {
						this.clear();
						return -1;
					}
				case 0:
					return 0;
				default:
					this.clear();
					return -1;
				}
			}
		}
	}

	private int maptype1_quantvals() {
		int i1 = MathHelper.floor_double(Math.pow((double)this.entries, 1.0D / (double)this.dim));

		while(true) {
			int i2 = 1;
			int i3 = 1;

			for(int i4 = 0; i4 < this.dim; ++i4) {
				i2 *= i1;
				i3 *= i1 + 1;
			}

			if(i2 <= this.entries && i3 > this.entries) {
				return i1;
			}

			if(i2 > this.entries) {
				--i1;
			} else {
				++i1;
			}
		}
	}

	void clear() {
	}

	float[] unquantize() {
		if(this.maptype != 1 && this.maptype != 2) {
			return null;
		} else {
			float f2 = float32_unpack(this.q_min);
			float f3 = float32_unpack(this.q_delta);
			float[] f4 = new float[this.entries * this.dim];
			int i5;
			float f6;
			int i7;
			switch(this.maptype) {
			case 1:
				int i1 = this.maptype1_quantvals();

				for(i5 = 0; i5 < this.entries; ++i5) {
					f6 = 0.0F;
					i7 = 1;

					for(int i11 = 0; i11 < this.dim; ++i11) {
						int i9 = i5 / i7 % i1;
						float f10 = (float)this.quantlist[i9];
						f10 = Math.abs(f10) * f3 + f2 + f6;
						if(this.q_sequencep != 0) {
							f6 = f10;
						}

						f4[i5 * this.dim + i11] = f10;
						i7 *= i1;
					}
				}

				return f4;
			case 2:
				for(i5 = 0; i5 < this.entries; ++i5) {
					f6 = 0.0F;

					for(i7 = 0; i7 < this.dim; ++i7) {
						float f8 = (float)this.quantlist[i5 * this.dim + i7];
						f8 = Math.abs(f8) * f3 + f2 + f6;
						if(this.q_sequencep != 0) {
							f6 = f8;
						}

						f4[i5 * this.dim + i7] = f8;
					}
				}
			}

			return f4;
		}
	}

	static long float32_pack(float f0) {
		int i1 = 0;
		if(f0 < 0.0F) {
			i1 = Integer.MIN_VALUE;
			f0 = -f0;
		}

		int i2 = MathHelper.floor_double(Math.log((double)f0) / Math.log(2.0D));
		int i3 = (int)Math.rint(Math.pow((double)f0, (double)(20 - i2)));
		i2 = i2 + 768 << 21;
		return (long)(i1 | i2 | i3);
	}

	static float float32_unpack(int i0) {
		float f1 = (float)(i0 & 2097151);
		float f2 = (float)((i0 & 2145386496) >>> 21);
		if((i0 & Integer.MIN_VALUE) != 0) {
			f1 = -f1;
		}

		return ldexp(f1, (int)f2 - 20 - 768);
	}

	static float ldexp(float f0, int i1) {
		return (float)((double)f0 * Math.pow(2.0D, (double)i1));
	}
}
