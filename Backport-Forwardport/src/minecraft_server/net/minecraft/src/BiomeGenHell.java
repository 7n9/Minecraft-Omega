package net.minecraft.src;

public class BiomeGenHell extends BiomeGenBase {
	public BiomeGenHell(int i1) {
		super(i1);
		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableMonsterList.add(new SpawnListEntry(EntityGhast.class, 10, 4, 4));
		this.spawnableMonsterList.add(new SpawnListEntry(EntityPigZombie.class, 10, 4, 4));
	}
}
