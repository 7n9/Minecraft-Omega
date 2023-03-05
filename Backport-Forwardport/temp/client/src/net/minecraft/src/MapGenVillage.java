package net.minecraft.src;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MapGenVillage extends MapGenStructure {
	public static List field_35635_a = Arrays.asList(new BiomeGenBase[]{BiomeGenBase.field_35485_c, BiomeGenBase.desert});

	protected boolean func_35628_a(int i1, int i2) {
		byte b3 = 32;
		byte b4 = 8;
		int i5 = i1;
		int i6 = i2;
		if(i1 < 0) {
			i1 -= b3 - 1;
		}

		if(i2 < 0) {
			i2 -= b3 - 1;
		}

		int i7 = i1 / b3;
		int i8 = i2 / b3;
		Random random9 = this.field_35625_d.func_35462_u(i7, i8, 10387312);
		i7 *= b3;
		i8 *= b3;
		i7 += random9.nextInt(b3 - b4);
		i8 += random9.nextInt(b3 - b4);
		if(i5 == i7 && i6 == i8) {
			boolean z10 = this.field_35625_d.getWorldChunkManager().func_35562_a(i5 * 16 + 8, i6 * 16 + 8, 0, field_35635_a);
			if(z10) {
				return true;
			}
		}

		return false;
	}

	protected StructureStart func_35630_b(int i1, int i2) {
		return new StructureVillageStart(this.field_35625_d, this.rand, i1, i2);
	}
}
