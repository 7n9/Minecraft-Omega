package com.jcraft.jorbis;

class Drft {
	int n;
	float[] trigcache;
	int[] splitcache;
	static int[] ntryh = new int[]{4, 2, 3, 5};
	static float tpi = 6.2831855F;
	static float hsqt2 = 0.70710677F;
	static float taui = 0.8660254F;
	static float taur = -0.5F;
	static float sqrt2 = 1.4142135F;

	void backward(float[] f1) {
		if(this.n != 1) {
			drftb1(this.n, f1, this.trigcache, this.trigcache, this.n, this.splitcache);
		}
	}

	void init(int i1) {
		this.n = i1;
		this.trigcache = new float[3 * i1];
		this.splitcache = new int[32];
		fdrffti(i1, this.trigcache, this.splitcache);
	}

	void clear() {
		if(this.trigcache != null) {
			this.trigcache = null;
		}

		if(this.splitcache != null) {
			this.splitcache = null;
		}

	}

	static void drfti1(int i0, float[] f1, int i2, int[] i3) {
		int i8 = 0;
		int i10 = -1;
		int i24 = i0;
		int i25 = 0;
		byte b26 = 101;

		while(true) {
			while(true) {
				int i9;
				switch(b26) {
				case 101:
					++i10;
					if(i10 < 4) {
						i8 = ntryh[i10];
					} else {
						i8 += 2;
					}
				case 104:
					int i19 = i24 / i8;
					int i20 = i24 - i8 * i19;
					if(i20 != 0) {
						b26 = 101;
						break;
					} else {
						++i25;
						i3[i25 + 1] = i8;
						i24 = i19;
						if(i8 != 2) {
							b26 = 107;
							break;
						} else if(i25 == 1) {
							b26 = 107;
							break;
						} else {
							for(i9 = 1; i9 < i25; ++i9) {
								int i14 = i25 - i9 + 1;
								i3[i14 + 1] = i3[i14];
							}

							i3[2] = 2;
						}
					}
				case 107:
					if(i24 == 1) {
						i3[0] = i0;
						i3[1] = i25;
						float f5 = tpi / (float)i0;
						int i18 = 0;
						int i23 = i25 - 1;
						int i12 = 1;
						if(i23 == 0) {
							return;
						}

						for(int i11 = 0; i11 < i23; ++i11) {
							int i17 = i3[i11 + 2];
							int i15 = 0;
							int i13 = i12 * i17;
							int i21 = i0 / i13;
							int i22 = i17 - 1;

							for(i10 = 0; i10 < i22; ++i10) {
								i15 += i12;
								i9 = i18;
								float f6 = (float)i15 * f5;
								float f7 = 0.0F;

								for(int i16 = 2; i16 < i21; i16 += 2) {
									++f7;
									float f4 = f7 * f6;
									f1[i2 + i9++] = (float)Math.cos((double)f4);
									f1[i2 + i9++] = (float)Math.sin((double)f4);
								}

								i18 += i21;
							}

							i12 = i13;
						}

						return;
					}

					b26 = 104;
				}
			}
		}
	}

	static void fdrffti(int i0, float[] f1, int[] i2) {
		if(i0 != 1) {
			drfti1(i0, f1, i0, i2);
		}
	}

	static void dradf2(int i0, int i1, float[] f2, float[] f3, float[] f4, int i5) {
		int i11 = 0;
		int i12;
		int i10 = i12 = i1 * i0;
		int i13 = i0 << 1;

		int i7;
		for(i7 = 0; i7 < i1; ++i7) {
			f3[i11 << 1] = f2[i11] + f2[i12];
			f3[(i11 << 1) + i13 - 1] = f2[i11] - f2[i12];
			i11 += i0;
			i12 += i0;
		}

		if(i0 >= 2) {
			if(i0 != 2) {
				i11 = 0;
				i12 = i10;

				for(i7 = 0; i7 < i1; ++i7) {
					i13 = i12;
					int i14 = (i11 << 1) + (i0 << 1);
					int i15 = i11;
					int i16 = i11 + i11;

					for(int i6 = 2; i6 < i0; i6 += 2) {
						i13 += 2;
						i14 -= 2;
						i15 += 2;
						i16 += 2;
						float f9 = f4[i5 + i6 - 2] * f2[i13 - 1] + f4[i5 + i6 - 1] * f2[i13];
						float f8 = f4[i5 + i6 - 2] * f2[i13] - f4[i5 + i6 - 1] * f2[i13 - 1];
						f3[i16] = f2[i15] + f8;
						f3[i14] = f8 - f2[i15];
						f3[i16 - 1] = f2[i15 - 1] + f9;
						f3[i14 - 1] = f2[i15 - 1] - f9;
					}

					i11 += i0;
					i12 += i0;
				}

				if(i0 % 2 == 1) {
					return;
				}
			}

			i11 = i0;
			i13 = i12 = i0 - 1;
			i12 += i10;

			for(i7 = 0; i7 < i1; ++i7) {
				f3[i11] = -f2[i12];
				f3[i11 - 1] = f2[i13];
				i11 += i0 << 1;
				i12 += i0;
				i13 += i0;
			}

		}
	}

	static void dradf4(int i0, int i1, float[] f2, float[] f3, float[] f4, int i5, float[] f6, int i7, float[] f8, int i9) {
		int i12 = i1 * i0;
		int i13 = i12;
		int i16 = i12 << 1;
		int i14 = i12 + (i12 << 1);
		int i15 = 0;

		int i11;
		int i17;
		float f29;
		float f30;
		for(i11 = 0; i11 < i1; ++i11) {
			f29 = f2[i13] + f2[i14];
			f30 = f2[i15] + f2[i16];
			f3[i17 = i15 << 2] = f29 + f30;
			f3[(i0 << 2) + i17 - 1] = f30 - f29;
			f3[(i17 += i0 << 1) - 1] = f2[i15] - f2[i16];
			f3[i17] = f2[i14] - f2[i13];
			i13 += i0;
			i14 += i0;
			i15 += i0;
			i16 += i0;
		}

		if(i0 >= 2) {
			int i18;
			float f25;
			if(i0 != 2) {
				i13 = 0;

				for(i11 = 0; i11 < i1; ++i11) {
					i14 = i13;
					i16 = i13 << 2;
					i17 = (i18 = i0 << 1) + i16;

					for(int i10 = 2; i10 < i0; i10 += 2) {
						i14 += 2;
						i16 += 2;
						i17 -= 2;
						i15 = i14 + i12;
						float f22 = f4[i5 + i10 - 2] * f2[i15 - 1] + f4[i5 + i10 - 1] * f2[i15];
						float f19 = f4[i5 + i10 - 2] * f2[i15] - f4[i5 + i10 - 1] * f2[i15 - 1];
						i15 += i12;
						float f23 = f6[i7 + i10 - 2] * f2[i15 - 1] + f6[i7 + i10 - 1] * f2[i15];
						float f20 = f6[i7 + i10 - 2] * f2[i15] - f6[i7 + i10 - 1] * f2[i15 - 1];
						i15 += i12;
						float f24 = f8[i9 + i10 - 2] * f2[i15 - 1] + f8[i9 + i10 - 1] * f2[i15];
						float f21 = f8[i9 + i10 - 2] * f2[i15] - f8[i9 + i10 - 1] * f2[i15 - 1];
						f29 = f22 + f24;
						float f32 = f24 - f22;
						f25 = f19 + f21;
						float f28 = f19 - f21;
						float f26 = f2[i14] + f20;
						float f27 = f2[i14] - f20;
						f30 = f2[i14 - 1] + f23;
						float f31 = f2[i14 - 1] - f23;
						f3[i16 - 1] = f29 + f30;
						f3[i16] = f25 + f26;
						f3[i17 - 1] = f31 - f28;
						f3[i17] = f32 - f27;
						f3[i16 + i18 - 1] = f28 + f31;
						f3[i16 + i18] = f32 + f27;
						f3[i17 + i18 - 1] = f30 - f29;
						f3[i17 + i18] = f25 - f26;
					}

					i13 += i0;
				}

				if((i0 & 1) != 0) {
					return;
				}
			}

			i14 = (i13 = i12 + i0 - 1) + (i12 << 1);
			i15 = i0 << 2;
			i16 = i0;
			i17 = i0 << 1;
			i18 = i0;

			for(i11 = 0; i11 < i1; ++i11) {
				f25 = -hsqt2 * (f2[i13] + f2[i14]);
				f29 = hsqt2 * (f2[i13] - f2[i14]);
				f3[i16 - 1] = f29 + f2[i18 - 1];
				f3[i16 + i17 - 1] = f2[i18 - 1] - f29;
				f3[i16] = f25 - f2[i13 + i12];
				f3[i16 + i17] = f25 + f2[i13 + i12];
				i13 += i0;
				i14 += i0;
				i16 += i15;
				i18 += i0;
			}

		}
	}

	static void dradfg(int i0, int i1, int i2, int i3, float[] f4, float[] f5, float[] f6, float[] f7, float[] f8, float[] f9, int i10) {
		int i22 = 0;
		float f38 = 0.0F;
		float f40 = 0.0F;
		float f39 = tpi / (float)i1;
		f38 = (float)Math.cos((double)f39);
		f40 = (float)Math.sin((double)f39);
		int i12 = i1 + 1 >> 1;
		int i44 = i1;
		int i43 = i0;
		int i37 = i0 - 1 >> 1;
		int i20 = i2 * i0;
		int i30 = i1 * i0;
		short s45 = 100;

		while(true) {
			int i13;
			int i14;
			int i15;
			int i18;
			int i21;
			int i23;
			int i24;
			int i25;
			int i26;
			int i27;
			int i28;
			int i29;
			switch(s45) {
			case 101:
				if(i0 == 1) {
					s45 = 119;
					break;
				} else {
					for(i18 = 0; i18 < i3; ++i18) {
						f8[i18] = f6[i18];
					}

					i21 = 0;

					for(i14 = 1; i14 < i1; ++i14) {
						i21 += i20;
						i22 = i21;

						for(i15 = 0; i15 < i2; ++i15) {
							f7[i22] = f5[i22];
							i22 += i0;
						}
					}

					int i19 = -i0;
					i21 = 0;
					int i11;
					if(i37 > i2) {
						for(i14 = 1; i14 < i1; ++i14) {
							i21 += i20;
							i19 += i0;
							i22 = -i0 + i21;

							for(i15 = 0; i15 < i2; ++i15) {
								i11 = i19 - 1;
								i22 += i0;
								i23 = i22;

								for(i13 = 2; i13 < i0; i13 += 2) {
									i11 += 2;
									i23 += 2;
									f7[i23 - 1] = f9[i10 + i11 - 1] * f5[i23 - 1] + f9[i10 + i11] * f5[i23];
									f7[i23] = f9[i10 + i11 - 1] * f5[i23] - f9[i10 + i11] * f5[i23 - 1];
								}
							}
						}
					} else {
						for(i14 = 1; i14 < i1; ++i14) {
							i19 += i0;
							i11 = i19 - 1;
							i21 += i20;
							i22 = i21;

							for(i13 = 2; i13 < i0; i13 += 2) {
								i11 += 2;
								i22 += 2;
								i23 = i22;

								for(i15 = 0; i15 < i2; ++i15) {
									f7[i23 - 1] = f9[i10 + i11 - 1] * f5[i23 - 1] + f9[i10 + i11] * f5[i23];
									f7[i23] = f9[i10 + i11 - 1] * f5[i23] - f9[i10 + i11] * f5[i23 - 1];
									i23 += i0;
								}
							}
						}
					}

					i21 = 0;
					i22 = i44 * i20;
					if(i37 < i2) {
						for(i14 = 1; i14 < i12; ++i14) {
							i21 += i20;
							i22 -= i20;
							i23 = i21;
							i24 = i22;

							for(i13 = 2; i13 < i0; i13 += 2) {
								i23 += 2;
								i24 += 2;
								i25 = i23 - i0;
								i26 = i24 - i0;

								for(i15 = 0; i15 < i2; ++i15) {
									i25 += i0;
									i26 += i0;
									f5[i25 - 1] = f7[i25 - 1] + f7[i26 - 1];
									f5[i26 - 1] = f7[i25] - f7[i26];
									f5[i25] = f7[i25] + f7[i26];
									f5[i26] = f7[i26 - 1] - f7[i25 - 1];
								}
							}
						}
					} else {
						for(i14 = 1; i14 < i12; ++i14) {
							i21 += i20;
							i22 -= i20;
							i23 = i21;
							i24 = i22;

							for(i15 = 0; i15 < i2; ++i15) {
								i25 = i23;
								i26 = i24;

								for(i13 = 2; i13 < i0; i13 += 2) {
									i25 += 2;
									i26 += 2;
									f5[i25 - 1] = f7[i25 - 1] + f7[i26 - 1];
									f5[i26 - 1] = f7[i25] - f7[i26];
									f5[i25] = f7[i25] + f7[i26];
									f5[i26] = f7[i26 - 1] - f7[i25 - 1];
								}

								i23 += i0;
								i24 += i0;
							}
						}
					}
				}
			case 119:
				for(i18 = 0; i18 < i3; ++i18) {
					f6[i18] = f8[i18];
				}

				i21 = 0;
				i22 = i44 * i3;

				for(i14 = 1; i14 < i12; ++i14) {
					i21 += i20;
					i22 -= i20;
					i23 = i21 - i0;
					i24 = i22 - i0;

					for(i15 = 0; i15 < i2; ++i15) {
						i23 += i0;
						i24 += i0;
						f5[i23] = f7[i23] + f7[i24];
						f5[i24] = f7[i24] - f7[i23];
					}
				}

				float f34 = 1.0F;
				float f32 = 0.0F;
				i21 = 0;
				i22 = i44 * i3;
				i23 = (i1 - 1) * i3;

				for(int i16 = 1; i16 < i12; ++i16) {
					i21 += i3;
					i22 -= i3;
					float f41 = f38 * f34 - f40 * f32;
					f32 = f38 * f32 + f40 * f34;
					f34 = f41;
					i24 = i21;
					i25 = i22;
					i26 = i23;
					i27 = i3;

					for(i18 = 0; i18 < i3; ++i18) {
						f8[i24++] = f6[i18] + f34 * f6[i27++];
						f8[i25++] = f32 * f6[i26++];
					}

					float f31 = f34;
					float f36 = f32;
					float f35 = f34;
					float f33 = f32;
					i24 = i3;
					i25 = (i44 - 1) * i3;

					for(i14 = 2; i14 < i12; ++i14) {
						i24 += i3;
						i25 -= i3;
						float f42 = f31 * f35 - f36 * f33;
						f33 = f31 * f33 + f36 * f35;
						f35 = f42;
						i26 = i21;
						i27 = i22;
						i28 = i24;
						i29 = i25;

						for(i18 = 0; i18 < i3; ++i18) {
							int i10001 = i26++;
							f8[i10001] += f35 * f6[i28++];
							i10001 = i27++;
							f8[i10001] += f33 * f6[i29++];
						}
					}
				}

				i21 = 0;

				for(i14 = 1; i14 < i12; ++i14) {
					i21 += i3;
					i22 = i21;

					for(i18 = 0; i18 < i3; ++i18) {
						f8[i18] += f6[i22++];
					}
				}

				if(i0 < i2) {
					s45 = 132;
					break;
				}

				i21 = 0;
				i22 = 0;

				for(i15 = 0; i15 < i2; ++i15) {
					i23 = i21;
					i24 = i22;

					for(i13 = 0; i13 < i0; ++i13) {
						f4[i24++] = f7[i23++];
					}

					i21 += i0;
					i22 += i30;
				}

				s45 = 135;
				break;
			case 132:
				for(i13 = 0; i13 < i0; ++i13) {
					i21 = i13;
					i22 = i13;

					for(i15 = 0; i15 < i2; ++i15) {
						f4[i22] = f7[i21];
						i21 += i0;
						i22 += i30;
					}
				}
			case 135:
				i21 = 0;
				i22 = i0 << 1;
				i23 = 0;
				i24 = i44 * i20;

				for(i14 = 1; i14 < i12; ++i14) {
					i21 += i22;
					i23 += i20;
					i24 -= i20;
					i25 = i21;
					i26 = i23;
					i27 = i24;

					for(i15 = 0; i15 < i2; ++i15) {
						f4[i25 - 1] = f7[i26];
						f4[i25] = f7[i27];
						i25 += i30;
						i26 += i0;
						i27 += i0;
					}
				}

				if(i0 == 1) {
					return;
				}

				if(i37 >= i2) {
					i21 = -i0;
					i23 = 0;
					i24 = 0;
					i25 = i44 * i20;

					for(i14 = 1; i14 < i12; ++i14) {
						i21 += i22;
						i23 += i22;
						i24 += i20;
						i25 -= i20;
						i26 = i21;
						i27 = i23;
						i28 = i24;
						i29 = i25;

						for(i15 = 0; i15 < i2; ++i15) {
							for(i13 = 2; i13 < i0; i13 += 2) {
								int i17 = i43 - i13;
								f4[i13 + i27 - 1] = f7[i13 + i28 - 1] + f7[i13 + i29 - 1];
								f4[i17 + i26 - 1] = f7[i13 + i28 - 1] - f7[i13 + i29 - 1];
								f4[i13 + i27] = f7[i13 + i28] + f7[i13 + i29];
								f4[i17 + i26] = f7[i13 + i29] - f7[i13 + i28];
							}

							i26 += i30;
							i27 += i30;
							i28 += i0;
							i29 += i0;
						}
					}

					return;
				}

				s45 = 141;
				break;
			case 141:
				i21 = -i0;
				i23 = 0;
				i24 = 0;
				i25 = i44 * i20;

				for(i14 = 1; i14 < i12; ++i14) {
					i21 += i22;
					i23 += i22;
					i24 += i20;
					i25 -= i20;

					for(i13 = 2; i13 < i0; i13 += 2) {
						i26 = i43 + i21 - i13;
						i27 = i13 + i23;
						i28 = i13 + i24;
						i29 = i13 + i25;

						for(i15 = 0; i15 < i2; ++i15) {
							f4[i27 - 1] = f7[i28 - 1] + f7[i29 - 1];
							f4[i26 - 1] = f7[i28 - 1] - f7[i29 - 1];
							f4[i27] = f7[i28] + f7[i29];
							f4[i26] = f7[i29] - f7[i28];
							i26 += i30;
							i27 += i30;
							i28 += i0;
							i29 += i0;
						}
					}
				}

				return;
			}
		}
	}

	static void drftf1(int i0, float[] f1, float[] f2, float[] f3, int[] i4) {
		int i11 = i4[1];
		int i9 = 1;
		int i8 = i0;
		int i13 = i0;

		for(int i6 = 0; i6 < i11; ++i6) {
			int i10 = i11 - i6;
			int i12 = i4[i10 + 1];
			int i7 = i8 / i12;
			int i14 = i0 / i8;
			int i15 = i14 * i7;
			i13 -= (i12 - 1) * i14;
			i9 = 1 - i9;
			byte b18 = 100;

			label62:
			while(true) {
				switch(b18) {
				case 100:
					if(i12 != 4) {
						b18 = 102;
					} else {
						int i16 = i13 + i14;
						int i17 = i16 + i14;
						if(i9 != 0) {
							dradf4(i14, i7, f2, f1, f3, i13 - 1, f3, i16 - 1, f3, i17 - 1);
						} else {
							dradf4(i14, i7, f1, f2, f3, i13 - 1, f3, i16 - 1, f3, i17 - 1);
						}

						b18 = 110;
					}
				case 101:
				case 105:
				case 106:
				case 107:
				case 108:
				default:
					break;
				case 102:
					if(i12 != 2) {
						b18 = 104;
					} else if(i9 != 0) {
						b18 = 103;
					} else {
						dradf2(i14, i7, f1, f2, f3, i13 - 1);
						b18 = 110;
					}
					break;
				case 103:
					dradf2(i14, i7, f2, f1, f3, i13 - 1);
				case 104:
					if(i14 == 1) {
						i9 = 1 - i9;
					}

					if(i9 != 0) {
						b18 = 109;
					} else {
						dradfg(i14, i12, i7, i15, f1, f1, f1, f2, f2, f3, i13 - 1);
						i9 = 1;
						b18 = 110;
					}
					break;
				case 109:
					dradfg(i14, i12, i7, i15, f2, f2, f2, f1, f1, f3, i13 - 1);
					i9 = 0;
				case 110:
					break label62;
				}
			}

			i8 = i7;
		}

		if(i9 != 1) {
			for(int i5 = 0; i5 < i0; ++i5) {
				f1[i5] = f2[i5];
			}

		}
	}

	static void dradb2(int i0, int i1, float[] f2, float[] f3, float[] f4, int i5) {
		int i8 = i1 * i0;
		int i9 = 0;
		int i10 = 0;
		int i11 = (i0 << 1) - 1;

		int i7;
		for(i7 = 0; i7 < i1; ++i7) {
			f3[i9] = f2[i10] + f2[i11 + i10];
			f3[i9 + i8] = f2[i10] - f2[i11 + i10];
			i10 = (i9 += i0) << 1;
		}

		if(i0 >= 2) {
			if(i0 != 2) {
				i9 = 0;
				i10 = 0;

				for(i7 = 0; i7 < i1; ++i7) {
					i11 = i9;
					int i12 = i10;
					int i13 = i10 + (i0 << 1);
					int i14 = i8 + i9;

					for(int i6 = 2; i6 < i0; i6 += 2) {
						i11 += 2;
						i12 += 2;
						i13 -= 2;
						i14 += 2;
						f3[i11 - 1] = f2[i12 - 1] + f2[i13 - 1];
						float f16 = f2[i12 - 1] - f2[i13 - 1];
						f3[i11] = f2[i12] - f2[i13];
						float f15 = f2[i12] + f2[i13];
						f3[i14 - 1] = f4[i5 + i6 - 2] * f16 - f4[i5 + i6 - 1] * f15;
						f3[i14] = f4[i5 + i6 - 2] * f15 + f4[i5 + i6 - 1] * f16;
					}

					i10 = (i9 += i0) << 1;
				}

				if(i0 % 2 == 1) {
					return;
				}
			}

			i9 = i0 - 1;
			i10 = i0 - 1;

			for(i7 = 0; i7 < i1; ++i7) {
				f3[i9] = f2[i10] + f2[i10];
				f3[i9 + i8] = -(f2[i10 + 1] + f2[i10 + 1]);
				i9 += i0;
				i10 += i0 << 1;
			}

		}
	}

	static void dradb3(int i0, int i1, float[] f2, float[] f3, float[] f4, int i5, float[] f6, int i7) {
		int i10 = i1 * i0;
		int i11 = 0;
		int i12 = i10 << 1;
		int i13 = i0 << 1;
		int i14 = i0 + (i0 << 1);
		int i15 = 0;

		int i9;
		float f22;
		float f25;
		float f30;
		for(i9 = 0; i9 < i1; ++i9) {
			f30 = f2[i13 - 1] + f2[i13 - 1];
			f25 = f2[i15] + taur * f30;
			f3[i11] = f2[i15] + f30;
			f22 = taui * (f2[i13] + f2[i13]);
			f3[i11 + i10] = f25 - f22;
			f3[i11 + i12] = f25 + f22;
			i11 += i0;
			i13 += i14;
			i15 += i14;
		}

		if(i0 != 1) {
			i11 = 0;
			i13 = i0 << 1;

			for(i9 = 0; i9 < i1; ++i9) {
				int i17 = i11 + (i11 << 1);
				int i16 = i15 = i17 + i13;
				int i18 = i11;
				int i19;
				int i20 = (i19 = i11 + i10) + i10;

				for(int i8 = 2; i8 < i0; i8 += 2) {
					i15 += 2;
					i16 -= 2;
					i17 += 2;
					i18 += 2;
					i19 += 2;
					i20 += 2;
					f30 = f2[i15 - 1] + f2[i16 - 1];
					f25 = f2[i17 - 1] + taur * f30;
					f3[i18 - 1] = f2[i17 - 1] + f30;
					float f29 = f2[i15] - f2[i16];
					float f21 = f2[i17] + taur * f29;
					f3[i18] = f2[i17] + f29;
					float f26 = taui * (f2[i15 - 1] - f2[i16 - 1]);
					f22 = taui * (f2[i15] + f2[i16]);
					float f27 = f25 - f22;
					float f28 = f25 + f22;
					float f23 = f21 + f26;
					float f24 = f21 - f26;
					f3[i19 - 1] = f4[i5 + i8 - 2] * f27 - f4[i5 + i8 - 1] * f23;
					f3[i19] = f4[i5 + i8 - 2] * f23 + f4[i5 + i8 - 1] * f27;
					f3[i20 - 1] = f6[i7 + i8 - 2] * f28 - f6[i7 + i8 - 1] * f24;
					f3[i20] = f6[i7 + i8 - 2] * f24 + f6[i7 + i8 - 1] * f28;
				}

				i11 += i0;
			}

		}
	}

	static void dradb4(int i0, int i1, float[] f2, float[] f3, float[] f4, int i5, float[] f6, int i7, float[] f8, int i9) {
		int i12 = i1 * i0;
		int i13 = 0;
		int i14 = i0 << 2;
		int i15 = 0;
		int i18 = i0 << 1;

		int i11;
		int i16;
		int i17;
		float f31;
		float f32;
		float f33;
		float f34;
		for(i11 = 0; i11 < i1; ++i11) {
			i16 = i15 + i18;
			f33 = f2[i16 - 1] + f2[i16 - 1];
			f34 = f2[i16] + f2[i16];
			f31 = f2[i15] - f2[(i16 += i18) - 1];
			f32 = f2[i15] + f2[i16 - 1];
			f3[i13] = f32 + f33;
			f3[i17 = i13 + i12] = f31 - f34;
			f3[i17 += i12] = f32 - f33;
			f3[i17 + i12] = f31 + f34;
			i13 += i0;
			i15 += i14;
		}

		if(i0 >= 2) {
			float f27;
			float f28;
			if(i0 != 2) {
				i13 = 0;

				for(i11 = 0; i11 < i1; ++i11) {
					i17 = (i16 = i15 = (i14 = i13 << 2) + i18) + i18;
					int i19 = i13;

					for(int i10 = 2; i10 < i0; i10 += 2) {
						i14 += 2;
						i15 += 2;
						i16 -= 2;
						i17 -= 2;
						i19 += 2;
						f27 = f2[i14] + f2[i17];
						f28 = f2[i14] - f2[i17];
						float f29 = f2[i15] - f2[i16];
						f34 = f2[i15] + f2[i16];
						f31 = f2[i14 - 1] - f2[i17 - 1];
						f32 = f2[i14 - 1] + f2[i17 - 1];
						float f30 = f2[i15 - 1] - f2[i16 - 1];
						f33 = f2[i15 - 1] + f2[i16 - 1];
						f3[i19 - 1] = f32 + f33;
						float f25 = f32 - f33;
						f3[i19] = f28 + f29;
						float f22 = f28 - f29;
						float f24 = f31 - f34;
						float f26 = f31 + f34;
						float f21 = f27 + f30;
						float f23 = f27 - f30;
						int i20;
						f3[(i20 = i19 + i12) - 1] = f4[i5 + i10 - 2] * f24 - f4[i5 + i10 - 1] * f21;
						f3[i20] = f4[i5 + i10 - 2] * f21 + f4[i5 + i10 - 1] * f24;
						f3[(i20 += i12) - 1] = f6[i7 + i10 - 2] * f25 - f6[i7 + i10 - 1] * f22;
						f3[i20] = f6[i7 + i10 - 2] * f22 + f6[i7 + i10 - 1] * f25;
						f3[(i20 += i12) - 1] = f8[i9 + i10 - 2] * f26 - f8[i9 + i10 - 1] * f23;
						f3[i20] = f8[i9 + i10 - 2] * f23 + f8[i9 + i10 - 1] * f26;
					}

					i13 += i0;
				}

				if(i0 % 2 == 1) {
					return;
				}
			}

			i13 = i0;
			i14 = i0 << 2;
			i15 = i0 - 1;
			i16 = i0 + (i0 << 1);

			for(i11 = 0; i11 < i1; ++i11) {
				f27 = f2[i13] + f2[i16];
				f28 = f2[i16] - f2[i13];
				f31 = f2[i13 - 1] - f2[i16 - 1];
				f32 = f2[i13 - 1] + f2[i16 - 1];
				f3[i15] = f32 + f32;
				f3[i17 = i15 + i12] = sqrt2 * (f31 - f27);
				f3[i17 += i12] = f28 + f28;
				f3[i17 + i12] = -sqrt2 * (f31 + f27);
				i15 += i0;
				i13 += i14;
				i16 += i14;
			}

		}
	}

	static void dradbg(int i0, int i1, int i2, int i3, float[] f4, float[] f5, float[] f6, float[] f7, float[] f8, float[] f9, int i10) {
		int i12 = 0;
		int i19 = 0;
		int i29 = 0;
		int i38 = 0;
		float f39 = 0.0F;
		float f41 = 0.0F;
		int i44 = 0;
		short s45 = 100;

		while(true) {
			int i11;
			int i13;
			int i14;
			int i15;
			int i17;
			int i18;
			int i20;
			int i21;
			int i22;
			int i23;
			int i24;
			int i25;
			int i26;
			int i27;
			int i28;
			int i30;
			int i31;
			switch(s45) {
			case 100:
				i29 = i1 * i0;
				i19 = i2 * i0;
				float f40 = tpi / (float)i1;
				f39 = (float)Math.cos((double)f40);
				f41 = (float)Math.sin((double)f40);
				i38 = i0 - 1 >>> 1;
				i44 = i1;
				i12 = i1 + 1 >>> 1;
				if(i0 < i2) {
					s45 = 103;
					break;
				}

				i20 = 0;
				i21 = 0;

				for(i15 = 0; i15 < i2; ++i15) {
					i22 = i20;
					i23 = i21;

					for(i13 = 0; i13 < i0; ++i13) {
						f7[i22] = f4[i23];
						++i22;
						++i23;
					}

					i20 += i0;
					i21 += i29;
				}

				s45 = 106;
				break;
			case 103:
				i20 = 0;

				for(i13 = 0; i13 < i0; ++i13) {
					i21 = i20;
					i22 = i20;

					for(i15 = 0; i15 < i2; ++i15) {
						f7[i21] = f4[i22];
						i21 += i0;
						i22 += i29;
					}

					++i20;
				}
			case 106:
				i20 = 0;
				i21 = i44 * i19;
				i26 = i24 = i0 << 1;

				for(i14 = 1; i14 < i12; ++i14) {
					i20 += i19;
					i21 -= i19;
					i22 = i20;
					i23 = i21;
					i25 = i24;

					for(i15 = 0; i15 < i2; ++i15) {
						f7[i22] = f4[i25 - 1] + f4[i25 - 1];
						f7[i23] = f4[i25] + f4[i25];
						i22 += i0;
						i23 += i0;
						i25 += i29;
					}

					i24 += i26;
				}

				if(i0 == 1) {
					s45 = 116;
				} else {
					if(i38 < i2) {
						s45 = 112;
						break;
					}

					i20 = 0;
					i21 = i44 * i19;
					i26 = 0;

					for(i14 = 1; i14 < i12; ++i14) {
						i20 += i19;
						i21 -= i19;
						i22 = i20;
						i23 = i21;
						i26 += i0 << 1;
						i27 = i26;

						for(i15 = 0; i15 < i2; ++i15) {
							i24 = i22;
							i25 = i23;
							i28 = i27;
							i30 = i27;

							for(i13 = 2; i13 < i0; i13 += 2) {
								i24 += 2;
								i25 += 2;
								i28 += 2;
								i30 -= 2;
								f7[i24 - 1] = f4[i28 - 1] + f4[i30 - 1];
								f7[i25 - 1] = f4[i28 - 1] - f4[i30 - 1];
								f7[i24] = f4[i28] - f4[i30];
								f7[i25] = f4[i28] + f4[i30];
							}

							i22 += i0;
							i23 += i0;
							i27 += i29;
						}
					}

					s45 = 116;
				}
				break;
			case 112:
				i20 = 0;
				i21 = i44 * i19;
				i26 = 0;

				for(i14 = 1; i14 < i12; ++i14) {
					i20 += i19;
					i21 -= i19;
					i22 = i20;
					i23 = i21;
					i26 += i0 << 1;
					i27 = i26;
					i28 = i26;

					for(i13 = 2; i13 < i0; i13 += 2) {
						i22 += 2;
						i23 += 2;
						i27 += 2;
						i28 -= 2;
						i24 = i22;
						i25 = i23;
						i30 = i27;
						i31 = i28;

						for(i15 = 0; i15 < i2; ++i15) {
							f7[i24 - 1] = f4[i30 - 1] + f4[i31 - 1];
							f7[i25 - 1] = f4[i30 - 1] - f4[i31 - 1];
							f7[i24] = f4[i30] - f4[i31];
							f7[i25] = f4[i30] + f4[i31];
							i24 += i0;
							i25 += i0;
							i30 += i29;
							i31 += i29;
						}
					}
				}
			case 116:
				float f35 = 1.0F;
				float f33 = 0.0F;
				i20 = 0;
				i28 = i21 = i44 * i3;
				i22 = (i1 - 1) * i3;

				for(int i16 = 1; i16 < i12; ++i16) {
					i20 += i3;
					i21 -= i3;
					float f42 = f39 * f35 - f41 * f33;
					f33 = f39 * f33 + f41 * f35;
					f35 = f42;
					i23 = i20;
					i24 = i21;
					i25 = 0;
					i26 = i3;
					i27 = i22;

					for(i17 = 0; i17 < i3; ++i17) {
						f6[i23++] = f8[i25++] + f35 * f8[i26++];
						f6[i24++] = f33 * f8[i27++];
					}

					float f32 = f35;
					float f37 = f33;
					float f36 = f35;
					float f34 = f33;
					i25 = i3;
					i26 = i28 - i3;

					for(i14 = 2; i14 < i12; ++i14) {
						i25 += i3;
						i26 -= i3;
						float f43 = f32 * f36 - f37 * f34;
						f34 = f32 * f34 + f37 * f36;
						f36 = f43;
						i23 = i20;
						i24 = i21;
						i30 = i25;
						i31 = i26;

						for(i17 = 0; i17 < i3; ++i17) {
							int i10001 = i23++;
							f6[i10001] += f36 * f8[i30++];
							i10001 = i24++;
							f6[i10001] += f34 * f8[i31++];
						}
					}
				}

				i20 = 0;

				for(i14 = 1; i14 < i12; ++i14) {
					i20 += i3;
					i21 = i20;

					for(i17 = 0; i17 < i3; ++i17) {
						f8[i17] += f8[i21++];
					}
				}

				i20 = 0;
				i21 = i44 * i19;

				for(i14 = 1; i14 < i12; ++i14) {
					i20 += i19;
					i21 -= i19;
					i22 = i20;
					i23 = i21;

					for(i15 = 0; i15 < i2; ++i15) {
						f7[i22] = f5[i22] - f5[i23];
						f7[i23] = f5[i22] + f5[i23];
						i22 += i0;
						i23 += i0;
					}
				}

				if(i0 == 1) {
					s45 = 132;
				} else {
					if(i38 < i2) {
						s45 = 128;
						break;
					}

					i20 = 0;
					i21 = i44 * i19;

					for(i14 = 1; i14 < i12; ++i14) {
						i20 += i19;
						i21 -= i19;
						i22 = i20;
						i23 = i21;

						for(i15 = 0; i15 < i2; ++i15) {
							i24 = i22;
							i25 = i23;

							for(i13 = 2; i13 < i0; i13 += 2) {
								i24 += 2;
								i25 += 2;
								f7[i24 - 1] = f5[i24 - 1] - f5[i25];
								f7[i25 - 1] = f5[i24 - 1] + f5[i25];
								f7[i24] = f5[i24] + f5[i25 - 1];
								f7[i25] = f5[i24] - f5[i25 - 1];
							}

							i22 += i0;
							i23 += i0;
						}
					}

					s45 = 132;
				}
				break;
			case 128:
				i20 = 0;
				i21 = i44 * i19;

				for(i14 = 1; i14 < i12; ++i14) {
					i20 += i19;
					i21 -= i19;
					i22 = i20;
					i23 = i21;

					for(i13 = 2; i13 < i0; i13 += 2) {
						i22 += 2;
						i23 += 2;
						i24 = i22;
						i25 = i23;

						for(i15 = 0; i15 < i2; ++i15) {
							f7[i24 - 1] = f5[i24 - 1] - f5[i25];
							f7[i25 - 1] = f5[i24 - 1] + f5[i25];
							f7[i24] = f5[i24] + f5[i25 - 1];
							f7[i25] = f5[i24] - f5[i25 - 1];
							i24 += i0;
							i25 += i0;
						}
					}
				}
			case 132:
				if(i0 == 1) {
					return;
				}

				for(i17 = 0; i17 < i3; ++i17) {
					f6[i17] = f8[i17];
				}

				i20 = 0;

				for(i14 = 1; i14 < i1; ++i14) {
					i21 = i20 += i19;

					for(i15 = 0; i15 < i2; ++i15) {
						f5[i21] = f7[i21];
						i21 += i0;
					}
				}

				if(i38 <= i2) {
					i18 = -i0 - 1;
					i20 = 0;

					for(i14 = 1; i14 < i1; ++i14) {
						i18 += i0;
						i20 += i19;
						i11 = i18;
						i21 = i20;

						for(i13 = 2; i13 < i0; i13 += 2) {
							i21 += 2;
							i11 += 2;
							i22 = i21;

							for(i15 = 0; i15 < i2; ++i15) {
								f5[i22 - 1] = f9[i10 + i11 - 1] * f7[i22 - 1] - f9[i10 + i11] * f7[i22];
								f5[i22] = f9[i10 + i11 - 1] * f7[i22] + f9[i10 + i11] * f7[i22 - 1];
								i22 += i0;
							}
						}
					}

					return;
				}

				s45 = 139;
				break;
			case 139:
				i18 = -i0 - 1;
				i20 = 0;

				for(i14 = 1; i14 < i1; ++i14) {
					i18 += i0;
					i20 += i19;
					i21 = i20;

					for(i15 = 0; i15 < i2; ++i15) {
						i11 = i18;
						i22 = i21;

						for(i13 = 2; i13 < i0; i13 += 2) {
							i11 += 2;
							i22 += 2;
							f5[i22 - 1] = f9[i10 + i11 - 1] * f7[i22 - 1] - f9[i10 + i11] * f7[i22];
							f5[i22] = f9[i10 + i11 - 1] * f7[i22] + f9[i10 + i11] * f7[i22 - 1];
						}

						i21 += i0;
					}
				}

				return;
			}
		}
	}

	static void drftb1(int i0, float[] f1, float[] f2, float[] f3, int i4, int[] i5) {
		int i9 = 0;
		int i12 = 0;
		int i16 = 0;
		int i17 = 0;
		int i11 = i5[1];
		int i10 = 0;
		int i8 = 1;
		int i13 = 1;

		for(int i7 = 0; i7 < i11; ++i7) {
			byte b18 = 100;

			label71:
			while(true) {
				int i14;
				switch(b18) {
				case 100:
					i12 = i5[i7 + 2];
					i9 = i12 * i8;
					i16 = i0 / i9;
					i17 = i16 * i8;
					if(i12 != 4) {
						b18 = 103;
					} else {
						i14 = i13 + i16;
						int i15 = i14 + i16;
						if(i10 != 0) {
							dradb4(i16, i8, f2, f1, f3, i4 + i13 - 1, f3, i4 + i14 - 1, f3, i4 + i15 - 1);
						} else {
							dradb4(i16, i8, f1, f2, f3, i4 + i13 - 1, f3, i4 + i14 - 1, f3, i4 + i15 - 1);
						}

						i10 = 1 - i10;
						b18 = 115;
					}
					break;
				case 103:
					if(i12 != 2) {
						b18 = 106;
					} else {
						if(i10 != 0) {
							dradb2(i16, i8, f2, f1, f3, i4 + i13 - 1);
						} else {
							dradb2(i16, i8, f1, f2, f3, i4 + i13 - 1);
						}

						i10 = 1 - i10;
						b18 = 115;
					}
					break;
				case 106:
					if(i12 != 3) {
						b18 = 109;
					} else {
						i14 = i13 + i16;
						if(i10 != 0) {
							dradb3(i16, i8, f2, f1, f3, i4 + i13 - 1, f3, i4 + i14 - 1);
						} else {
							dradb3(i16, i8, f1, f2, f3, i4 + i13 - 1, f3, i4 + i14 - 1);
						}

						i10 = 1 - i10;
						b18 = 115;
					}
					break;
				case 109:
					if(i10 != 0) {
						dradbg(i16, i12, i8, i17, f2, f2, f2, f1, f1, f3, i4 + i13 - 1);
					} else {
						dradbg(i16, i12, i8, i17, f1, f1, f1, f2, f2, f3, i4 + i13 - 1);
					}

					if(i16 == 1) {
						i10 = 1 - i10;
					}
				case 115:
					break label71;
				}
			}

			i8 = i9;
			i13 += (i12 - 1) * i16;
		}

		if(i10 != 0) {
			for(int i6 = 0; i6 < i0; ++i6) {
				f1[i6] = f2[i6];
			}

		}
	}
}
