package net.minecraft.src;

import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

public class WeightedRandom {
	public static int func_35736_a(Collection collection0) {
		int i1 = 0;

		WeightedRandomChoice weightedRandomChoice3;
		for(Iterator iterator2 = collection0.iterator(); iterator2.hasNext(); i1 += weightedRandomChoice3.field_35590_d) {
			weightedRandomChoice3 = (WeightedRandomChoice)iterator2.next();
		}

		return i1;
	}

	public static WeightedRandomChoice func_35734_a(Random random0, Collection collection1, int i2) {
		if(i2 <= 0) {
			throw new IllegalArgumentException();
		} else {
			int i3 = random0.nextInt(i2);
			Iterator iterator4 = collection1.iterator();

			WeightedRandomChoice weightedRandomChoice5;
			do {
				if(!iterator4.hasNext()) {
					return null;
				}

				weightedRandomChoice5 = (WeightedRandomChoice)iterator4.next();
				i3 -= weightedRandomChoice5.field_35590_d;
			} while(i3 >= 0);

			return weightedRandomChoice5;
		}
	}

	public static WeightedRandomChoice func_35733_a(Random random0, Collection collection1) {
		return func_35734_a(random0, collection1, func_35736_a(collection1));
	}

	public static int func_35737_a(WeightedRandomChoice[] weightedRandomChoice0) {
		int i1 = 0;
		WeightedRandomChoice[] weightedRandomChoice2 = weightedRandomChoice0;
		int i3 = weightedRandomChoice0.length;

		for(int i4 = 0; i4 < i3; ++i4) {
			WeightedRandomChoice weightedRandomChoice5 = weightedRandomChoice2[i4];
			i1 += weightedRandomChoice5.field_35590_d;
		}

		return i1;
	}

	public static WeightedRandomChoice func_35732_a(Random random0, WeightedRandomChoice[] weightedRandomChoice1, int i2) {
		if(i2 <= 0) {
			throw new IllegalArgumentException();
		} else {
			int i3 = random0.nextInt(i2);
			WeightedRandomChoice[] weightedRandomChoice4 = weightedRandomChoice1;
			int i5 = weightedRandomChoice1.length;

			for(int i6 = 0; i6 < i5; ++i6) {
				WeightedRandomChoice weightedRandomChoice7 = weightedRandomChoice4[i6];
				i3 -= weightedRandomChoice7.field_35590_d;
				if(i3 < 0) {
					return weightedRandomChoice7;
				}
			}

			return null;
		}
	}

	public static WeightedRandomChoice func_35735_a(Random random0, WeightedRandomChoice[] weightedRandomChoice1) {
		return func_35732_a(random0, weightedRandomChoice1, func_35737_a(weightedRandomChoice1));
	}
}
