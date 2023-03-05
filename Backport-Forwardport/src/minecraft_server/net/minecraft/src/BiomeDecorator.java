package net.minecraft.src;

import java.util.Random;

public class BiomeDecorator {
	private World field_35262_B;
	private Random field_35263_C;
	private int field_35258_D;
	private int field_35259_E;
	private BiomeGenBase field_35260_F;
	protected WorldGenerator field_35270_a = new WorldGenClay(4);
	protected WorldGenerator field_35268_b = new WorldGenSand(7, Block.sand.blockID);
	protected WorldGenerator field_35269_c = new WorldGenSand(6, Block.gravel.blockID);
	protected WorldGenerator field_35266_d = new WorldGenMinable(Block.dirt.blockID, 32);
	protected WorldGenerator field_35267_e = new WorldGenMinable(Block.gravel.blockID, 32);
	protected WorldGenerator field_35264_f = new WorldGenMinable(Block.oreCoal.blockID, 16);
	protected WorldGenerator field_35265_g = new WorldGenMinable(Block.oreIron.blockID, 8);
	protected WorldGenerator field_35277_h = new WorldGenMinable(Block.oreGold.blockID, 8);
	protected WorldGenerator field_35278_i = new WorldGenMinable(Block.oreRedstone.blockID, 7);
	protected WorldGenerator field_35275_j = new WorldGenMinable(Block.oreDiamond.blockID, 7);
	protected WorldGenerator field_35276_k = new WorldGenMinable(Block.oreLapis.blockID, 6);
	protected WorldGenerator field_35273_l = new WorldGenFlowers(Block.plantYellow.blockID);
	protected WorldGenerator field_35274_m = new WorldGenFlowers(Block.plantYellow.blockID);
	protected WorldGenerator field_35271_n = new WorldGenFlowers(Block.mushroomBrown.blockID);
	protected WorldGenerator field_35272_o = new WorldGenFlowers(Block.mushroomRed.blockID);
	protected WorldGenerator field_35286_p = new WorldGenReed();
	protected WorldGenerator field_35285_q = new WorldGenCactus();
	protected int field_35284_r = 0;
	protected int field_35283_s = 2;
	protected int field_35282_t = 1;
	protected int field_35281_u = 0;
	protected int field_35280_v = 0;
	protected int field_35279_w = 0;
	protected int field_35289_x = 0;
	protected int field_35288_y = 1;
	protected int field_35287_z = 3;
	protected int field_35261_A = 1;

	public BiomeDecorator(BiomeGenBase biomeGenBase1) {
		this.field_35260_F = biomeGenBase1;
	}

	public void func_35255_a(World world1, Random random2, int i3, int i4) {
		if(this.field_35262_B != null) {
			throw new RuntimeException("Already decorating!!");
		} else {
			this.field_35262_B = world1;
			this.field_35263_C = random2;
			this.field_35258_D = i3;
			this.field_35259_E = i4;
			this.func_35256_b();
			this.field_35262_B = null;
			this.field_35263_C = null;
		}
	}

	private void func_35256_b() {
		this.func_35253_a();

		int i1;
		int i2;
		int i3;
		for(i1 = 0; i1 < this.field_35287_z; ++i1) {
			i2 = this.field_35258_D + this.field_35263_C.nextInt(16) + 8;
			i3 = this.field_35259_E + this.field_35263_C.nextInt(16) + 8;
			this.field_35268_b.generate(this.field_35262_B, this.field_35263_C, i2, this.field_35262_B.findTopSolidBlock(i2, i3), i3);
		}

		for(i1 = 0; i1 < this.field_35261_A; ++i1) {
			i2 = this.field_35258_D + this.field_35263_C.nextInt(16) + 8;
			i3 = this.field_35259_E + this.field_35263_C.nextInt(16) + 8;
			this.field_35270_a.generate(this.field_35262_B, this.field_35263_C, i2, this.field_35262_B.findTopSolidBlock(i2, i3), i3);
		}

		for(i1 = 0; i1 < this.field_35288_y; ++i1) {
			i2 = this.field_35258_D + this.field_35263_C.nextInt(16) + 8;
			i3 = this.field_35259_E + this.field_35263_C.nextInt(16) + 8;
			this.field_35268_b.generate(this.field_35262_B, this.field_35263_C, i2, this.field_35262_B.findTopSolidBlock(i2, i3), i3);
		}

		i1 = this.field_35284_r;
		if(this.field_35263_C.nextInt(10) == 0) {
			++i1;
		}

		int i4;
		for(i2 = 0; i2 < i1; ++i2) {
			i3 = this.field_35258_D + this.field_35263_C.nextInt(16) + 8;
			i4 = this.field_35259_E + this.field_35263_C.nextInt(16) + 8;
			WorldGenerator worldGenerator5 = this.field_35260_F.getRandomWorldGenForTrees(this.field_35263_C);
			worldGenerator5.func_420_a(1.0D, 1.0D, 1.0D);
			worldGenerator5.generate(this.field_35262_B, this.field_35263_C, i3, this.field_35262_B.getHeightValue(i3, i4), i4);
		}

		Random random10000;
		int i8;
		for(i2 = 0; i2 < this.field_35283_s; ++i2) {
			i3 = this.field_35258_D + this.field_35263_C.nextInt(16) + 8;
			random10000 = this.field_35263_C;
			this.field_35262_B.getClass();
			i4 = random10000.nextInt(128);
			i8 = this.field_35259_E + this.field_35263_C.nextInt(16) + 8;
			this.field_35273_l.generate(this.field_35262_B, this.field_35263_C, i3, i4, i8);
			if(this.field_35263_C.nextInt(4) == 0) {
				i3 = this.field_35258_D + this.field_35263_C.nextInt(16) + 8;
				random10000 = this.field_35263_C;
				this.field_35262_B.getClass();
				i4 = random10000.nextInt(128);
				i8 = this.field_35259_E + this.field_35263_C.nextInt(16) + 8;
				this.field_35274_m.generate(this.field_35262_B, this.field_35263_C, i3, i4, i8);
			}
		}

		for(i2 = 0; i2 < this.field_35282_t; ++i2) {
			byte b7 = 1;
			i4 = this.field_35258_D + this.field_35263_C.nextInt(16) + 8;
			random10000 = this.field_35263_C;
			this.field_35262_B.getClass();
			i8 = random10000.nextInt(128);
			int i6 = this.field_35259_E + this.field_35263_C.nextInt(16) + 8;
			(new WorldGenTallGrass(Block.tallGrass.blockID, b7)).generate(this.field_35262_B, this.field_35263_C, i4, i8, i6);
		}

		for(i2 = 0; i2 < this.field_35281_u; ++i2) {
			i3 = this.field_35258_D + this.field_35263_C.nextInt(16) + 8;
			random10000 = this.field_35263_C;
			this.field_35262_B.getClass();
			i4 = random10000.nextInt(128);
			i8 = this.field_35259_E + this.field_35263_C.nextInt(16) + 8;
			(new WorldGenDeadBush(Block.deadBush.blockID)).generate(this.field_35262_B, this.field_35263_C, i3, i4, i8);
		}

		for(i2 = 0; i2 < this.field_35280_v; ++i2) {
			if(this.field_35263_C.nextInt(4) == 0) {
				i3 = this.field_35258_D + this.field_35263_C.nextInt(16) + 8;
				i4 = this.field_35259_E + this.field_35263_C.nextInt(16) + 8;
				i8 = this.field_35262_B.getHeightValue(i3, i4);
				this.field_35271_n.generate(this.field_35262_B, this.field_35263_C, i3, i8, i4);
			}

			if(this.field_35263_C.nextInt(8) == 0) {
				i3 = this.field_35258_D + this.field_35263_C.nextInt(16) + 8;
				i4 = this.field_35259_E + this.field_35263_C.nextInt(16) + 8;
				random10000 = this.field_35263_C;
				this.field_35262_B.getClass();
				i8 = random10000.nextInt(128);
				this.field_35272_o.generate(this.field_35262_B, this.field_35263_C, i3, i8, i4);
			}
		}

		if(this.field_35263_C.nextInt(4) == 0) {
			i2 = this.field_35258_D + this.field_35263_C.nextInt(16) + 8;
			random10000 = this.field_35263_C;
			this.field_35262_B.getClass();
			i3 = random10000.nextInt(128);
			i4 = this.field_35259_E + this.field_35263_C.nextInt(16) + 8;
			this.field_35271_n.generate(this.field_35262_B, this.field_35263_C, i2, i3, i4);
		}

		if(this.field_35263_C.nextInt(8) == 0) {
			i2 = this.field_35258_D + this.field_35263_C.nextInt(16) + 8;
			random10000 = this.field_35263_C;
			this.field_35262_B.getClass();
			i3 = random10000.nextInt(128);
			i4 = this.field_35259_E + this.field_35263_C.nextInt(16) + 8;
			this.field_35272_o.generate(this.field_35262_B, this.field_35263_C, i2, i3, i4);
		}

		for(i2 = 0; i2 < this.field_35279_w; ++i2) {
			i3 = this.field_35258_D + this.field_35263_C.nextInt(16) + 8;
			i4 = this.field_35259_E + this.field_35263_C.nextInt(16) + 8;
			random10000 = this.field_35263_C;
			this.field_35262_B.getClass();
			i8 = random10000.nextInt(128);
			this.field_35286_p.generate(this.field_35262_B, this.field_35263_C, i3, i8, i4);
		}

		for(i2 = 0; i2 < 10; ++i2) {
			i3 = this.field_35258_D + this.field_35263_C.nextInt(16) + 8;
			random10000 = this.field_35263_C;
			this.field_35262_B.getClass();
			i4 = random10000.nextInt(128);
			i8 = this.field_35259_E + this.field_35263_C.nextInt(16) + 8;
			this.field_35286_p.generate(this.field_35262_B, this.field_35263_C, i3, i4, i8);
		}

		if(this.field_35263_C.nextInt(32) == 0) {
			i2 = this.field_35258_D + this.field_35263_C.nextInt(16) + 8;
			random10000 = this.field_35263_C;
			this.field_35262_B.getClass();
			i3 = random10000.nextInt(128);
			i4 = this.field_35259_E + this.field_35263_C.nextInt(16) + 8;
			(new WorldGenPumpkin()).generate(this.field_35262_B, this.field_35263_C, i2, i3, i4);
		}

		for(i2 = 0; i2 < this.field_35289_x; ++i2) {
			i3 = this.field_35258_D + this.field_35263_C.nextInt(16) + 8;
			random10000 = this.field_35263_C;
			this.field_35262_B.getClass();
			i4 = random10000.nextInt(128);
			i8 = this.field_35259_E + this.field_35263_C.nextInt(16) + 8;
			this.field_35285_q.generate(this.field_35262_B, this.field_35263_C, i3, i4, i8);
		}

		Random random10001;
		for(i2 = 0; i2 < 50; ++i2) {
			i3 = this.field_35258_D + this.field_35263_C.nextInt(16) + 8;
			random10000 = this.field_35263_C;
			random10001 = this.field_35263_C;
			this.field_35262_B.getClass();
			i4 = random10000.nextInt(random10001.nextInt(128 - 8) + 8);
			i8 = this.field_35259_E + this.field_35263_C.nextInt(16) + 8;
			(new WorldGenLiquids(Block.waterMoving.blockID)).generate(this.field_35262_B, this.field_35263_C, i3, i4, i8);
		}

		for(i2 = 0; i2 < 20; ++i2) {
			i3 = this.field_35258_D + this.field_35263_C.nextInt(16) + 8;
			random10000 = this.field_35263_C;
			random10001 = this.field_35263_C;
			Random random10002 = this.field_35263_C;
			this.field_35262_B.getClass();
			i4 = random10000.nextInt(random10001.nextInt(random10002.nextInt(128 - 16) + 8) + 8);
			i8 = this.field_35259_E + this.field_35263_C.nextInt(16) + 8;
			(new WorldGenLiquids(Block.lavaMoving.blockID)).generate(this.field_35262_B, this.field_35263_C, i3, i4, i8);
		}

	}

	protected void func_35257_a(int i1, WorldGenerator worldGenerator2, int i3, int i4) {
		for(int i5 = 0; i5 < i1; ++i5) {
			int i6 = this.field_35258_D + this.field_35263_C.nextInt(16);
			int i7 = this.field_35263_C.nextInt(i4 - i3) + i3;
			int i8 = this.field_35259_E + this.field_35263_C.nextInt(16);
			worldGenerator2.generate(this.field_35262_B, this.field_35263_C, i6, i7, i8);
		}

	}

	protected void func_35254_b(int i1, WorldGenerator worldGenerator2, int i3, int i4) {
		for(int i5 = 0; i5 < i1; ++i5) {
			int i6 = this.field_35258_D + this.field_35263_C.nextInt(16);
			int i7 = this.field_35263_C.nextInt(i4) + this.field_35263_C.nextInt(i4) + (i3 - i4);
			int i8 = this.field_35259_E + this.field_35263_C.nextInt(16);
			worldGenerator2.generate(this.field_35262_B, this.field_35263_C, i6, i7, i8);
		}

	}

	protected void func_35253_a() {
		WorldGenerator worldGenerator10002 = this.field_35266_d;
		this.field_35262_B.getClass();
		this.func_35257_a(20, worldGenerator10002, 0, 128);
		worldGenerator10002 = this.field_35267_e;
		this.field_35262_B.getClass();
		this.func_35257_a(10, worldGenerator10002, 0, 128);
		worldGenerator10002 = this.field_35264_f;
		this.field_35262_B.getClass();
		this.func_35257_a(20, worldGenerator10002, 0, 128);
		worldGenerator10002 = this.field_35265_g;
		this.field_35262_B.getClass();
		this.func_35257_a(20, worldGenerator10002, 0, 128 / 2);
		worldGenerator10002 = this.field_35277_h;
		this.field_35262_B.getClass();
		this.func_35257_a(2, worldGenerator10002, 0, 128 / 4);
		worldGenerator10002 = this.field_35278_i;
		this.field_35262_B.getClass();
		this.func_35257_a(8, worldGenerator10002, 0, 128 / 8);
		worldGenerator10002 = this.field_35275_j;
		this.field_35262_B.getClass();
		this.func_35257_a(1, worldGenerator10002, 0, 128 / 8);
		worldGenerator10002 = this.field_35276_k;
		this.field_35262_B.getClass();
		int i10003 = 128 / 8;
		this.field_35262_B.getClass();
		this.func_35254_b(1, worldGenerator10002, i10003, 128 / 8);
	}
}
