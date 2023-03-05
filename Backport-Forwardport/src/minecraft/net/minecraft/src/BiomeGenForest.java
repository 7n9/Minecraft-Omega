package net.minecraft.src;

import java.util.Random;

public class BiomeGenForest extends BiomeGenBase {
	public BiomeGenForest(int i1) {
		super(i1);
		this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 5, 4, 4));
		this.field_35488_u.field_35911_r = 10;
		this.field_35488_u.field_35909_t = 2;
	}

	public WorldGenerator getRandomWorldGenForTrees(Random random1) {
		return (WorldGenerator)(random1.nextInt(5) == 0 ? this.field_35481_B : (random1.nextInt(10) == 0 ? this.field_35480_A : this.field_35493_z));
	}
}
