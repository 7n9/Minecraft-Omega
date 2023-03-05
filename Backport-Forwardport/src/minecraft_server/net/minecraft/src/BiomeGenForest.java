package net.minecraft.src;

import java.util.Random;

public class BiomeGenForest extends BiomeGenBase {
	public BiomeGenForest(int i1) {
		super(i1);
		this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 5, 4, 4));
		this.field_35523_u.field_35284_r = 10;
		this.field_35523_u.field_35282_t = 2;
	}

	public WorldGenerator getRandomWorldGenForTrees(Random random1) {
		return (WorldGenerator)(random1.nextInt(5) == 0 ? this.field_35516_B : (random1.nextInt(10) == 0 ? this.field_35515_A : this.field_35528_z));
	}
}
