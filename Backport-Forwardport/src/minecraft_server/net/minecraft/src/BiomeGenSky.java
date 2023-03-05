package net.minecraft.src;

public class BiomeGenSky extends BiomeGenBase {
	public BiomeGenSky(int i1) {
		super(i1);
		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 10, 4, 4));
	}
}
