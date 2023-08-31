package com.jcraft.jorbis;

import com.jcraft.jogg.Buffer;

class CodeBook {
	int dim;
	int entries;
	StaticCodeBook c = new StaticCodeBook();
	float[] valuelist;
	int[] codelist;
	CodeBook.DecodeAux decode_tree;
	private int[] t = new int[15];

	int encode(int i1, Buffer buffer2) {
		buffer2.write(this.codelist[i1], this.c.lengthlist[i1]);
		return this.c.lengthlist[i1];
	}

	int errorv(float[] f1) {
		int i2 = this.best(f1, 1);

        if (this.dim >= 0) System.arraycopy(this.valuelist, i2 * this.dim + 0, f1, 0, this.dim);

		return i2;
	}

	int encodev(int i1, float[] f2, Buffer buffer3) {
        if (this.dim >= 0) System.arraycopy(this.valuelist, i1 * this.dim + 0, f2, 0, this.dim);

		return this.encode(i1, buffer3);
	}

	int encodevs(float[] f1, Buffer buffer2, int i3, int i4) {
		int i5 = this.besterror(f1, i3, i4);
		return this.encode(i5, buffer2);
	}

	synchronized int decodevs_add(float[] f1, int i2, Buffer buffer3, int i4) {
		int i5 = i4 / this.dim;
		if(this.t.length < i5) {
			this.t = new int[i5];
		}

		int i7;
		for(i7 = 0; i7 < i5; ++i7) {
			int i6 = this.decode(buffer3);
			if(i6 == -1) {
				return -1;
			}

			this.t[i7] = i6 * this.dim;
		}

		i7 = 0;

		for(int i9 = 0; i7 < this.dim; i9 += i5) {
			for(int i8 = 0; i8 < i5; ++i8) {
				f1[i2 + i9 + i8] += this.valuelist[this.t[i8] + i7];
			}

			++i7;
		}

		return 0;
	}

	int decodev_add(float[] f1, int i2, Buffer buffer3, int i4) {
		int i10002;
		int i5;
		int i6;
		int i7;
		int i8;
		if(this.dim > 8) {
			i5 = 0;

			while(i5 < i4) {
				i7 = this.decode(buffer3);
				if(i7 == -1) {
					return -1;
				}

				i8 = i7 * this.dim;

				for(i6 = 0; i6 < this.dim; f1[i2 + i10002] += this.valuelist[i8 + i6++]) {
					i10002 = i5++;
				}
			}
		} else {
			i5 = 0;

			while(i5 < i4) {
				i7 = this.decode(buffer3);
				if(i7 == -1) {
					return -1;
				}

				i8 = i7 * this.dim;
				i6 = 0;
				switch(this.dim) {
				case 0:
				default:
					break;
				case 8:
					i10002 = i5++;
					f1[i2 + i10002] += this.valuelist[i8 + i6++];
				case 7:
					i10002 = i5++;
					f1[i2 + i10002] += this.valuelist[i8 + i6++];
				case 6:
					i10002 = i5++;
					f1[i2 + i10002] += this.valuelist[i8 + i6++];
				case 5:
					i10002 = i5++;
					f1[i2 + i10002] += this.valuelist[i8 + i6++];
				case 4:
					i10002 = i5++;
					f1[i2 + i10002] += this.valuelist[i8 + i6++];
				case 3:
					i10002 = i5++;
					f1[i2 + i10002] += this.valuelist[i8 + i6++];
				case 2:
					i10002 = i5++;
					f1[i2 + i10002] += this.valuelist[i8 + i6++];
				case 1:
					i10002 = i5++;
					f1[i2 + i10002] += this.valuelist[i8 + i6++];
				}
			}
		}

		return 0;
	}

	int decodev_set(float[] f1, int i2, Buffer buffer3, int i4) {
		int i5 = 0;

		while(i5 < i4) {
			int i7 = this.decode(buffer3);
			if(i7 == -1) {
				return -1;
			}

			int i8 = i7 * this.dim;

			for(int i6 = 0; i6 < this.dim; f1[i2 + i5++] = this.valuelist[i8 + i6++]) {
			}
		}

		return 0;
	}

	int decodevv_add(float[][] f1, int i2, int i3, Buffer buffer4, int i5) {
		int i9 = 0;
		int i6 = i2 / i3;

		while(i6 < (i2 + i5) / i3) {
			int i8 = this.decode(buffer4);
			if(i8 == -1) {
				return -1;
			}

			int i10 = i8 * this.dim;

			for(int i7 = 0; i7 < this.dim; ++i7) {
				int i10001 = i9++;
				f1[i10001][i6] += this.valuelist[i10 + i7];
				if(i9 == i3) {
					i9 = 0;
					++i6;
				}
			}
		}

		return 0;
	}

	int decode(Buffer buffer1) {
		int i2 = 0;
		CodeBook.DecodeAux codeBook$DecodeAux3 = this.decode_tree;
		int i4 = buffer1.look(codeBook$DecodeAux3.tabn);
		if(i4 >= 0) {
			i2 = codeBook$DecodeAux3.tab[i4];
			buffer1.adv(codeBook$DecodeAux3.tabl[i4]);
			if(i2 <= 0) {
				return -i2;
			}
		}

		do {
			switch(buffer1.read1()) {
			case -1:
			default:
				return -1;
			case 0:
				i2 = codeBook$DecodeAux3.ptr0[i2];
				break;
			case 1:
				i2 = codeBook$DecodeAux3.ptr1[i2];
			}
		} while(i2 > 0);

		return -i2;
	}

	int decodevs(float[] f1, int i2, Buffer buffer3, int i4, int i5) {
		int i6 = this.decode(buffer3);
		if(i6 == -1) {
			return -1;
		} else {
			int i7;
			int i8;
			switch(i5) {
			case -1:
				i7 = 0;

				for(i8 = 0; i7 < this.dim; i8 += i4) {
					f1[i2 + i8] = this.valuelist[i6 * this.dim + i7];
					++i7;
				}

				return i6;
			case 0:
				i7 = 0;

				for(i8 = 0; i7 < this.dim; i8 += i4) {
					f1[i2 + i8] += this.valuelist[i6 * this.dim + i7];
					++i7;
				}

				return i6;
			case 1:
				i7 = 0;

				for(i8 = 0; i7 < this.dim; i8 += i4) {
					f1[i2 + i8] *= this.valuelist[i6 * this.dim + i7];
					++i7;
				}
			}

			return i6;
		}
	}

	int best(float[] f1, int i2) {
		int i3 = -1;
		float f4 = 0.0F;
		int i5 = 0;

		for(int i6 = 0; i6 < this.entries; ++i6) {
			if(this.c.lengthlist[i6] > 0) {
				float f7 = dist(this.dim, this.valuelist, i5, f1, i2);
				if(i3 == -1 || f7 < f4) {
					f4 = f7;
					i3 = i6;
				}
			}

			i5 += this.dim;
		}

		return i3;
	}

	int besterror(float[] f1, int i2, int i3) {
		int i4 = this.best(f1, i2);
		int i5;
		int i6;
		switch(i3) {
		case 0:
			i5 = 0;

			for(i6 = 0; i5 < this.dim; i6 += i2) {
				f1[i6] -= this.valuelist[i4 * this.dim + i5];
				++i5;
			}

			return i4;
		case 1:
			i5 = 0;

			for(i6 = 0; i5 < this.dim; i6 += i2) {
				float f7 = this.valuelist[i4 * this.dim + i5];
				if(f7 == 0.0F) {
					f1[i6] = 0.0F;
				} else {
					f1[i6] /= f7;
				}

				++i5;
			}
		}

		return i4;
	}

	void clear() {
	}

	private static float dist(int i0, float[] f1, int i2, float[] f3, int i4) {
		float f5 = 0.0F;

		for(int i6 = 0; i6 < i0; ++i6) {
			float f7 = f1[i2 + i6] - f3[i6 * i4];
			f5 += f7 * f7;
		}

		return f5;
	}

	int init_decode(StaticCodeBook staticCodeBook1) {
		this.c = staticCodeBook1;
		this.entries = staticCodeBook1.entries;
		this.dim = staticCodeBook1.dim;
		this.valuelist = staticCodeBook1.unquantize();
		this.decode_tree = this.make_decode_tree();
		if(this.decode_tree == null) {
			this.clear();
			return -1;
		} else {
			return 0;
		}
	}

	static int[] make_words(int[] i0, int i1) {
		int[] i2 = new int[33];
		int[] i3 = new int[i1];

		int i4;
		int i5;
		int i6;
		for(i4 = 0; i4 < i1; ++i4) {
			i5 = i0[i4];
			if(i5 > 0) {
				i6 = i2[i5];
				if(i5 < 32 && i6 >>> i5 != 0) {
					return null;
				}

				i3[i4] = i6;

				int i7;
				for(i7 = i5; i7 > 0; --i7) {
					if((i2[i7] & 1) != 0) {
						if(i7 == 1) {
							++i2[1];
						} else {
							i2[i7] = i2[i7 - 1] << 1;
						}
						break;
					}

					++i2[i7];
				}

				for(i7 = i5 + 1; i7 < 33 && i2[i7] >>> 1 == i6; ++i7) {
					i6 = i2[i7];
					i2[i7] = i2[i7 - 1] << 1;
				}
			}
		}

		for(i4 = 0; i4 < i1; ++i4) {
			i5 = 0;

			for(i6 = 0; i6 < i0[i4]; ++i6) {
				i5 <<= 1;
				i5 |= i3[i4] >>> i6 & 1;
			}

			i3[i4] = i5;
		}

		return i3;
	}

	CodeBook.DecodeAux make_decode_tree() {
		int i1 = 0;
		CodeBook.DecodeAux codeBook$DecodeAux2 = new CodeBook.DecodeAux();
		int[] i3 = codeBook$DecodeAux2.ptr0 = new int[this.entries * 2];
		int[] i4 = codeBook$DecodeAux2.ptr1 = new int[this.entries * 2];
		int[] i5 = make_words(this.c.lengthlist, this.c.entries);
		if(i5 == null) {
			return null;
		} else {
			codeBook$DecodeAux2.aux = this.entries * 2;

			int i6;
			int i7;
			int i8;
			int i9;
			for(i6 = 0; i6 < this.entries; ++i6) {
				if(this.c.lengthlist[i6] > 0) {
					i7 = 0;

					for(i8 = 0; i8 < this.c.lengthlist[i6] - 1; ++i8) {
						i9 = i5[i6] >>> i8 & 1;
						if(i9 == 0) {
							if(i3[i7] == 0) {
								++i1;
								i3[i7] = i1;
							}

							i7 = i3[i7];
						} else {
							if(i4[i7] == 0) {
								++i1;
								i4[i7] = i1;
							}

							i7 = i4[i7];
						}
					}

					if((i5[i6] >>> i8 & 1) == 0) {
						i3[i7] = -i6;
					} else {
						i4[i7] = -i6;
					}
				}
			}

			codeBook$DecodeAux2.tabn = Util.ilog(this.entries) - 4;
			if(codeBook$DecodeAux2.tabn < 5) {
				codeBook$DecodeAux2.tabn = 5;
			}

			i6 = 1 << codeBook$DecodeAux2.tabn;
			codeBook$DecodeAux2.tab = new int[i6];
			codeBook$DecodeAux2.tabl = new int[i6];

			for(i7 = 0; i7 < i6; ++i7) {
				i8 = 0;
				boolean z10 = false;

				for(i9 = 0; i9 < codeBook$DecodeAux2.tabn && (i8 > 0 || i9 == 0); ++i9) {
					if((i7 & 1 << i9) != 0) {
						i8 = i4[i8];
					} else {
						i8 = i3[i8];
					}
				}

				codeBook$DecodeAux2.tab[i7] = i8;
				codeBook$DecodeAux2.tabl[i7] = i9;
			}

			return codeBook$DecodeAux2;
		}
	}

	class DecodeAux {
		int[] tab;
		int[] tabl;
		int tabn;
		int[] ptr0;
		int[] ptr1;
		int aux;
	}
}
