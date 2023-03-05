package net.minecraft.src;

import java.util.Random;

public class BiomeDecorator {
	private World field_35889_B;
	private Random field_35890_C;
	private int field_35885_D;
	private int field_35886_E;
	private BiomeGenBase field_35887_F;
	protected WorldGenerator field_35897_a = new WorldGenClay(4);
	protected WorldGenerator field_35895_b = new WorldGenSand(7, Block.sand.blockID);
	protected WorldGenerator field_35896_c = new WorldGenSand(6, Block.gravel.blockID);
	protected WorldGenerator field_35893_d = new WorldGenMinable(Block.dirt.blockID, 32);
	protected WorldGenerator field_35894_e = new WorldGenMinable(Block.gravel.blockID, 32);
	protected WorldGenerator field_35891_f = new WorldGenMinable(Block.oreCoal.blockID, 16);
	protected WorldGenerator field_35892_g = new WorldGenMinable(Block.oreIron.blockID, 8);
	protected WorldGenerator field_35904_h = new WorldGenMinable(Block.oreGold.blockID, 8);
	protected WorldGenerator field_35905_i = new WorldGenMinable(Block.oreRedstone.blockID, 7);
	protected WorldGenerator field_35902_j = new WorldGenMinable(Block.oreDiamond.blockID, 7);
	protected WorldGenerator field_35903_k = new WorldGenMinable(Block.oreLapis.blockID, 6);
	protected WorldGenerator field_35900_l = new WorldGenFlowers(Block.plantYellow.blockID);
	protected WorldGenerator field_35901_m = new WorldGenFlowers(Block.plantYellow.blockID);
	protected WorldGenerator field_35898_n = new WorldGenFlowers(Block.mushroomBrown.blockID);
	protected WorldGenerator field_35899_o = new WorldGenFlowers(Block.mushroomRed.blockID);
	protected WorldGenerator field_35913_p = new WorldGenReed();
	protected WorldGenerator field_35912_q = new WorldGenCactus();
	protected int field_35911_r = 0;
	protected int field_35910_s = 2;
	protected int field_35909_t = 1;
	protected int field_35908_u = 0;
	protected int field_35907_v = 0;
	protected int field_35906_w = 0;
	protected int field_35916_x = 0;
	protected int field_35915_y = 1;
	protected int field_35914_z = 3;
	protected int field_35888_A = 1;

	public BiomeDecorator(BiomeGenBase biomeGenBase1) {
		this.field_35887_F = biomeGenBase1;
	}

	public void func_35881_a(World world1, Random random2, int i3, int i4) {
		if(this.field_35889_B != null) {
			throw new RuntimeException("Already decorating!!");
		} else {
			this.field_35889_B = world1;
			this.field_35890_C = random2;
			this.field_35885_D = i3;
			this.field_35886_E = i4;
			this.func_35882_b();
			this.field_35889_B = null;
			this.field_35890_C = null;
		}
	}

	private void func_35882_b() {
		this.func_35880_a();

		int i1;
		int i2;
		int i3;
		for(i1 = 0; i1 < this.field_35914_z; ++i1) {
			i2 = this.field_35885_D + this.field_35890_C.nextInt(16) + 8;
			i3 = this.field_35886_E + this.field_35890_C.nextInt(16) + 8;
			this.field_35895_b.generate(this.field_35889_B, this.field_35890_C, i2, this.field_35889_B.getTopSolidOrLiquidBlock(i2, i3), i3);
		}

		for(i1 = 0; i1 < this.field_35888_A; ++i1) {
			i2 = this.field_35885_D + this.field_35890_C.nextInt(16) + 8;
			i3 = this.field_35886_E + this.field_35890_C.nextInt(16) + 8;
			this.field_35897_a.generate(this.field_35889_B, this.field_35890_C, i2, this.field_35889_B.getTopSolidOrLiquidBlock(i2, i3), i3);
		}

		for(i1 = 0; i1 < this.field_35915_y; ++i1) {
			i2 = this.field_35885_D + this.field_35890_C.nextInt(16) + 8;
			i3 = this.field_35886_E + this.field_35890_C.nextInt(16) + 8;
			this.field_35895_b.generate(this.field_35889_B, this.field_35890_C, i2, this.field_35889_B.getTopSolidOrLiquidBlock(i2, i3), i3);
		}

		i1 = this.field_35911_r;
		if(this.field_35890_C.nextInt(10) == 0) {
			++i1;
		}

		int i4;
		for(i2 = 0; i2 < i1; ++i2) {
			i3 = this.field_35885_D + this.field_35890_C.nextInt(16) + 8;
			i4 = this.field_35886_E + this.field_35890_C.nextInt(16) + 8;
			WorldGenerator worldGenerator5 = this.field_35887_F.getRandomWorldGenForTrees(this.field_35890_C);
			worldGenerator5.func_517_a(1.0D, 1.0D, 1.0D);
			worldGenerator5.generate(this.field_35889_B, this.field_35890_C, i3, this.field_35889_B.getHeightValue(i3, i4), i4);
		}

		Random random10000;
		int i8;
		for(i2 = 0; i2 < this.field_35910_s; ++i2) {
			i3 = this.field_35885_D + this.field_35890_C.nextInt(16) + 8;
			random10000 = this.field_35890_C;
			this.field_35889_B.getClass();
			i4 = random10000.nextInt(128);
			i8 = this.field_35886_E + this.field_35890_C.nextInt(16) + 8;
			this.field_35900_l.generate(this.field_35889_B, this.field_35890_C, i3, i4, i8);
			if(this.field_35890_C.nextInt(4) == 0) {
				i3 = this.field_35885_D + this.field_35890_C.nextInt(16) + 8;
				random10000 = this.field_35890_C;
				this.field_35889_B.getClass();
				i4 = random10000.nextInt(128);
				i8 = this.field_35886_E + this.field_35890_C.nextInt(16) + 8;
				this.field_35901_m.generate(this.field_35889_B, this.field_35890_C, i3, i4, i8);
			}
		}

		for(i2 = 0; i2 < this.field_35909_t; ++i2) {
			byte b7 = 1;
			i4 = this.field_35885_D + this.field_35890_C.nextInt(16) + 8;
			random10000 = this.field_35890_C;
			this.field_35889_B.getClass();
			i8 = random10000.nextInt(128);
			int i6 = this.field_35886_E + this.field_35890_C.nextInt(16) + 8;
			(new WorldGenTallGrass(Block.tallGrass.blockID, b7)).generate(this.field_35889_B, this.field_35890_C, i4, i8, i6);
		}

		for(i2 = 0; i2 < this.field_35908_u; ++i2) {
			i3 = this.field_35885_D + this.field_35890_C.nextInt(16) + 8;
			random10000 = this.field_35890_C;
			this.field_35889_B.getClass();
			i4 = random10000.nextInt(128);
			i8 = this.field_35886_E + this.field_35890_C.nextInt(16) + 8;
			(new WorldGenDeadBush(Block.deadBush.blockID)).generate(this.field_35889_B, this.field_35890_C, i3, i4, i8);
		}

		for(i2 = 0; i2 < this.field_35907_v; ++i2) {
			if(this.field_35890_C.nextInt(4) == 0) {
				i3 = this.field_35885_D + this.field_35890_C.nextInt(16) + 8;
				i4 = this.field_35886_E + this.field_35890_C.nextInt(16) + 8;
				i8 = this.field_35889_B.getHeightValue(i3, i4);
				this.field_35898_n.generate(this.field_35889_B, this.field_35890_C, i3, i8, i4);
			}

			if(this.field_35890_C.nextInt(8) == 0) {
				i3 = this.field_35885_D + this.field_35890_C.nextInt(16) + 8;
				i4 = this.field_35886_E + this.field_35890_C.nextInt(16) + 8;
				random10000 = this.field_35890_C;
				this.field_35889_B.getClass();
				i8 = random10000.nextInt(128);
				this.field_35899_o.generate(this.field_35889_B, this.field_35890_C, i3, i8, i4);
			}
		}

		if(this.field_35890_C.nextInt(4) == 0) {
			i2 = this.field_35885_D + this.field_35890_C.nextInt(16) + 8;
			random10000 = this.field_35890_C;
			this.field_35889_B.getClass();
			i3 = random10000.nextInt(128);
			i4 = this.field_35886_E + this.field_35890_C.nextInt(16) + 8;
			this.field_35898_n.generate(this.field_35889_B, this.field_35890_C, i2, i3, i4);
		}

		if(this.field_35890_C.nextInt(8) == 0) {
			i2 = this.field_35885_D + this.field_35890_C.nextInt(16) + 8;
			random10000 = this.field_35890_C;
			this.field_35889_B.getClass();
			i3 = random10000.nextInt(128);
			i4 = this.field_35886_E + this.field_35890_C.nextInt(16) + 8;
			this.field_35899_o.generate(this.field_35889_B, this.field_35890_C, i2, i3, i4);
		}

		for(i2 = 0; i2 < this.field_35906_w; ++i2) {
			i3 = this.field_35885_D + this.field_35890_C.nextInt(16) + 8;
			i4 = this.field_35886_E + this.field_35890_C.nextInt(16) + 8;
			random10000 = this.field_35890_C;
			this.field_35889_B.getClass();
			i8 = random10000.nextInt(128);
			this.field_35913_p.generate(this.field_35889_B, this.field_35890_C, i3, i8, i4);
		}

		for(i2 = 0; i2 < 10; ++i2) {
			i3 = this.field_35885_D + this.field_35890_C.nextInt(16) + 8;
			random10000 = this.field_35890_C;
			this.field_35889_B.getClass();
			i4 = random10000.nextInt(128);
			i8 = this.field_35886_E + this.field_35890_C.nextInt(16) + 8;
			this.field_35913_p.generate(this.field_35889_B, this.field_35890_C, i3, i4, i8);
		}

		if(this.field_35890_C.nextInt(32) == 0) {
			i2 = this.field_35885_D + this.field_35890_C.nextInt(16) + 8;
			random10000 = this.field_35890_C;
			this.field_35889_B.getClass();
			i3 = random10000.nextInt(128);
			i4 = this.field_35886_E + this.field_35890_C.nextInt(16) + 8;
			(new WorldGenPumpkin()).generate(this.field_35889_B, this.field_35890_C, i2, i3, i4);
		}

		for(i2 = 0; i2 < this.field_35916_x; ++i2) {
			i3 = this.field_35885_D + this.field_35890_C.nextInt(16) + 8;
			random10000 = this.field_35890_C;
			this.field_35889_B.getClass();
			i4 = random10000.nextInt(128);
			i8 = this.field_35886_E + this.field_35890_C.nextInt(16) + 8;
			this.field_35912_q.generate(this.field_35889_B, this.field_35890_C, i3, i4, i8);
		}

		Random random10001;
		for(i2 = 0; i2 < 50; ++i2) {
			i3 = this.field_35885_D + this.field_35890_C.nextInt(16) + 8;
			random10000 = this.field_35890_C;
			random10001 = this.field_35890_C;
			this.field_35889_B.getClass();
			i4 = random10000.nextInt(random10001.nextInt(128 - 8) + 8);
			i8 = this.field_35886_E + this.field_35890_C.nextInt(16) + 8;
			(new WorldGenLiquids(Block.waterMoving.blockID)).generate(this.field_35889_B, this.field_35890_C, i3, i4, i8);
		}

		for(i2 = 0; i2 < 20; ++i2) {
			i3 = this.field_35885_D + this.field_35890_C.nextInt(16) + 8;
			random10000 = this.field_35890_C;
			random10001 = this.field_35890_C;
			Random random10002 = this.field_35890_C;
			this.field_35889_B.getClass();
			i4 = random10000.nextInt(random10001.nextInt(random10002.nextInt(128 - 16) + 8) + 8);
			i8 = this.field_35886_E + this.field_35890_C.nextInt(16) + 8;
			(new WorldGenLiquids(Block.lavaMoving.blockID)).generate(this.field_35889_B, this.field_35890_C, i3, i4, i8);
		}

	}

	protected void func_35884_a(int i1, WorldGenerator worldGenerator2, int i3, int i4) {
		for(int i5 = 0; i5 < i1; ++i5) {
			int i6 = this.field_35885_D + this.field_35890_C.nextInt(16);
			int i7 = this.field_35890_C.nextInt(i4 - i3) + i3;
			int i8 = this.field_35886_E + this.field_35890_C.nextInt(16);
			worldGenerator2.generate(this.field_35889_B, this.field_35890_C, i6, i7, i8);
		}

	}

	protected void func_35883_b(int i1, WorldGenerator worldGenerator2, int i3, int i4) {
		for(int i5 = 0; i5 < i1; ++i5) {
			int i6 = this.field_35885_D + this.field_35890_C.nextInt(16);
			int i7 = this.field_35890_C.nextInt(i4) + this.field_35890_C.nextInt(i4) + (i3 - i4);
			int i8 = this.field_35886_E + this.field_35890_C.nextInt(16);
			worldGenerator2.generate(this.field_35889_B, this.field_35890_C, i6, i7, i8);
		}

	}

	protected void func_35880_a() {
		WorldGenerator worldGenerator10002 = this.field_35893_d;
		this.field_35889_B.getClass();
		this.func_35884_a(20, worldGenerator10002, 0, 128);
		worldGenerator10002 = this.field_35894_e;
		this.field_35889_B.getClass();
		this.func_35884_a(10, worldGenerator10002, 0, 128);
		worldGenerator10002 = this.field_35891_f;
		this.field_35889_B.getClass();
		this.func_35884_a(20, worldGenerator10002, 0, 128);
		worldGenerator10002 = this.field_35892_g;
		this.field_35889_B.getClass();
		this.func_35884_a(20, worldGenerator10002, 0, 128 / 2);
		worldGenerator10002 = this.field_35904_h;
		this.field_35889_B.getClass();
		this.func_35884_a(2, worldGenerator10002, 0, 128 / 4);
		worldGenerator10002 = this.field_35905_i;
		this.field_35889_B.getClass();
		this.func_35884_a(8, worldGenerator10002, 0, 128 / 8);
		worldGenerator10002 = this.field_35902_j;
		this.field_35889_B.getClass();
		this.func_35884_a(1, worldGenerator10002, 0, 128 / 8);
		worldGenerator10002 = this.field_35903_k;
		this.field_35889_B.getClass();
		int i10003 = 128 / 8;
		this.field_35889_B.getClass();
		this.func_35883_b(1, worldGenerator10002, i10003, 128 / 8);
	}
}
