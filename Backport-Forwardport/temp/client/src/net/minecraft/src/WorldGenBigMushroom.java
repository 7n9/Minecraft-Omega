package net.minecraft.src;

import java.util.Random;

public class WorldGenBigMushroom extends WorldGenerator {
	private int field_35266_a = -1;

	public WorldGenBigMushroom(int i1) {
		this.field_35266_a = i1;
	}

	public WorldGenBigMushroom() {
	}

	public boolean generate(World world1, Random random2, int i3, int i4, int i5) {
		int i6 = random2.nextInt(2);
		if(this.field_35266_a >= 0) {
			i6 = this.field_35266_a;
		}

		int i7 = random2.nextInt(3) + 4;
		boolean z8 = true;
		if(i4 >= 1) {
			int i10000 = i4 + i7 + 1;
			world1.getClass();
			if(i10000 <= 128) {
				int i9;
				int i11;
				int i12;
				int i13;
				for(i9 = i4; i9 <= i4 + 1 + i7; ++i9) {
					byte b10 = 3;
					if(i9 == i4) {
						b10 = 0;
					}

					for(i11 = i3 - b10; i11 <= i3 + b10 && z8; ++i11) {
						for(i12 = i5 - b10; i12 <= i5 + b10 && z8; ++i12) {
							if(i9 >= 0) {
								world1.getClass();
								if(i9 < 128) {
									i13 = world1.getBlockId(i11, i9, i12);
									if(i13 != 0 && i13 != Block.leaves.blockID) {
										z8 = false;
									}
									continue;
								}
							}

							z8 = false;
						}
					}
				}

				if(!z8) {
					return false;
				}

				if(!Block.mushroomBrown.canPlaceBlockAt(world1, i3, i4, i5)) {
					return false;
				}

				world1.setBlock(i3, i4 - 1, i5, Block.dirt.blockID);
				i9 = i4 + i7;
				if(i6 == 1) {
					i9 = i4 + i7 - 3;
				}

				int i15;
				for(i15 = i9; i15 <= i4 + i7; ++i15) {
					i11 = 1;
					if(i15 < i4 + i7) {
						++i11;
					}

					if(i6 == 0) {
						i11 = 3;
					}

					for(i12 = i3 - i11; i12 <= i3 + i11; ++i12) {
						for(i13 = i5 - i11; i13 <= i5 + i11; ++i13) {
							int i14 = 5;
							if(i12 == i3 - i11) {
								--i14;
							}

							if(i12 == i3 + i11) {
								++i14;
							}

							if(i13 == i5 - i11) {
								i14 -= 3;
							}

							if(i13 == i5 + i11) {
								i14 += 3;
							}

							if(i6 == 0 || i15 < i4 + i7) {
								if((i12 == i3 - i11 || i12 == i3 + i11) && (i13 == i5 - i11 || i13 == i5 + i11)) {
									continue;
								}

								if(i12 == i3 - (i11 - 1) && i13 == i5 - i11) {
									i14 = 1;
								}

								if(i12 == i3 - i11 && i13 == i5 - (i11 - 1)) {
									i14 = 1;
								}

								if(i12 == i3 + (i11 - 1) && i13 == i5 - i11) {
									i14 = 3;
								}

								if(i12 == i3 + i11 && i13 == i5 - (i11 - 1)) {
									i14 = 3;
								}

								if(i12 == i3 - (i11 - 1) && i13 == i5 + i11) {
									i14 = 7;
								}

								if(i12 == i3 - i11 && i13 == i5 + (i11 - 1)) {
									i14 = 7;
								}

								if(i12 == i3 + (i11 - 1) && i13 == i5 + i11) {
									i14 = 9;
								}

								if(i12 == i3 + i11 && i13 == i5 + (i11 - 1)) {
									i14 = 9;
								}
							}

							if(i14 == 5 && i15 < i4 + i7) {
								i14 = 0;
							}

							if((i14 != 0 || i4 >= i4 + i7 - 1) && !Block.opaqueCubeLookup[world1.getBlockId(i12, i15, i13)]) {
								world1.setBlockAndMetadata(i12, i15, i13, Block.field_35286_bo.blockID + i6, i14);
							}
						}
					}
				}

				for(i15 = 0; i15 < i7; ++i15) {
					i11 = world1.getBlockId(i3, i4 + i15, i5);
					if(!Block.opaqueCubeLookup[i11]) {
						world1.setBlockAndMetadata(i3, i4 + i15, i5, Block.field_35286_bo.blockID + i6, 10);
					}
				}

				return true;
			}
		}

		return false;
	}
}
