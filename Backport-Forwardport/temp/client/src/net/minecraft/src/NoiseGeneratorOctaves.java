package net.minecraft.src;

import java.util.Random;

public class NoiseGeneratorOctaves extends NoiseGenerator {
	private NoiseGeneratorPerlin[] generatorCollection;
	private int field_1191_b;

	public NoiseGeneratorOctaves(Random random1, int i2) {
		this.field_1191_b = i2;
		this.generatorCollection = new NoiseGeneratorPerlin[i2];

		for(int i3 = 0; i3 < i2; ++i3) {
			this.generatorCollection[i3] = new NoiseGeneratorPerlin(random1);
		}

	}

	public double func_806_a(double d1, double d3) {
		double d5 = 0.0D;
		double d7 = 1.0D;

		for(int i9 = 0; i9 < this.field_1191_b; ++i9) {
			d5 += this.generatorCollection[i9].func_801_a(d1 * d7, d3 * d7) / d7;
			d7 /= 2.0D;
		}

		return d5;
	}

	public double[] generateNoiseOctaves(double[] d1, int i2, int i3, int i4, int i5, int i6, int i7, double d8, double d10, double d12) {
		if(d1 == null) {
			d1 = new double[i5 * i6 * i7];
		} else {
			for(int i14 = 0; i14 < d1.length; ++i14) {
				d1[i14] = 0.0D;
			}
		}

		double d27 = 1.0D;

		for(int i16 = 0; i16 < this.field_1191_b; ++i16) {
			double d17 = (double)i2 * d27 * d8;
			double d19 = (double)i3 * d27 * d10;
			double d21 = (double)i4 * d27 * d12;
			long j23 = MathHelper.func_35599_c(d17);
			long j25 = MathHelper.func_35599_c(d21);
			d17 -= (double)j23;
			d21 -= (double)j25;
			j23 %= 16777216L;
			j25 %= 16777216L;
			d17 += (double)j23;
			d21 += (double)j25;
			this.generatorCollection[i16].func_805_a(d1, d17, d19, d21, i5, i6, i7, d8 * d27, d10 * d27, d12 * d27, d27);
			d27 /= 2.0D;
		}

		return d1;
	}

	public double[] func_4109_a(double[] d1, int i2, int i3, int i4, int i5, double d6, double d8, double d10) {
		return this.generateNoiseOctaves(d1, i2, 10, i3, i4, 1, i5, d6, 1.0D, d8);
	}
}
