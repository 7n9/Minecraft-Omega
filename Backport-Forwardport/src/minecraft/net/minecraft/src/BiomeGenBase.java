package net.minecraft.src;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class BiomeGenBase {
	public static final BiomeGenBase[] field_35486_a = new BiomeGenBase[256];
	public static final BiomeGenBase field_35484_b = (new BiomeGenOcean(0)).setColor(112).setBiomeName("Ocean").func_35479_b(-1.0F, 0.5F);
	public static final BiomeGenBase field_35485_c = (new BiomeGenPlains(1)).setColor(9286496).setBiomeName("Plains").func_35478_a(0.8F, 0.4F);
	public static final BiomeGenBase desert = (new BiomeGenDesert(2)).setColor(16421912).setBiomeName("Desert").setDisableRain().func_35478_a(2.0F, 0.0F).func_35479_b(0.1F, 0.2F);
	public static final BiomeGenBase field_35483_e = (new BiomeGenHills(3)).setColor(6316128).setBiomeName("Extreme Hills").func_35479_b(0.2F, 1.8F).func_35478_a(0.2F, 0.3F);
	public static final BiomeGenBase forest = (new BiomeGenForest(4)).setColor(353825).setBiomeName("Forest").func_4124_a(5159473).func_35478_a(0.7F, 0.8F);
	public static final BiomeGenBase taiga = (new BiomeGenTaiga(5)).setColor(747097).setBiomeName("Taiga").func_4124_a(5159473).func_35478_a(0.3F, 0.8F).func_35479_b(0.1F, 0.4F);
	public static final BiomeGenBase swampland = (new BiomeGenSwamp(6)).setColor(522674).setBiomeName("Swampland").func_4124_a(9154376).func_35479_b(-0.2F, 0.1F).func_35478_a(0.8F, 0.9F);
	public static final BiomeGenBase field_35487_i = (new BiomeGenRiver(7)).setColor(255).setBiomeName("River").func_35479_b(-0.5F, 0.0F);
	public static final BiomeGenBase hell = (new BiomeGenHell(8)).setColor(16711680).setBiomeName("Hell").setDisableRain();
	public static final BiomeGenBase sky = (new BiomeGenSky(9)).setColor(8421631).setBiomeName("Sky").setDisableRain();
	public String biomeName;
	public int color;
	public byte topBlock = (byte)Block.grass.blockID;
	public byte fillerBlock = (byte)Block.dirt.blockID;
	public int field_6502_q = 5169201;
	public float field_35492_q = 0.1F;
	public float field_35491_r = 0.3F;
	public float field_35490_s = 0.5F;
	public float field_35489_t = 0.5F;
	public BiomeDecorator field_35488_u;
	protected List spawnableMonsterList = new ArrayList();
	protected List spawnableCreatureList = new ArrayList();
	protected List spawnableWaterCreatureList = new ArrayList();
	private boolean enableSnow;
	private boolean enableRain = true;
	public final int field_35494_y;
	protected WorldGenTrees field_35493_z = new WorldGenTrees();
	protected WorldGenBigTree field_35480_A = new WorldGenBigTree();
	protected WorldGenForest field_35481_B = new WorldGenForest();
	protected WorldGenSwamp field_35482_C = new WorldGenSwamp();

	protected BiomeGenBase(int i1) {
		this.field_35494_y = i1;
		field_35486_a[i1] = this;
		this.field_35488_u = this.func_35475_a();
		this.spawnableCreatureList.add(new SpawnListEntry(EntitySheep.class, 12, 4, 4));
		this.spawnableCreatureList.add(new SpawnListEntry(EntityPig.class, 10, 4, 4));
		this.spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 10, 4, 4));
		this.spawnableCreatureList.add(new SpawnListEntry(EntityCow.class, 8, 4, 4));
		this.spawnableMonsterList.add(new SpawnListEntry(EntitySpider.class, 10, 4, 4));
		this.spawnableMonsterList.add(new SpawnListEntry(EntityZombie.class, 10, 4, 4));
		this.spawnableMonsterList.add(new SpawnListEntry(EntitySkeleton.class, 10, 4, 4));
		this.spawnableMonsterList.add(new SpawnListEntry(EntityCreeper.class, 10, 4, 4));
		this.spawnableMonsterList.add(new SpawnListEntry(EntitySlime.class, 10, 4, 4));
		this.spawnableMonsterList.add(new SpawnListEntry(EntityEnderman.class, 2, 4, 4));
		this.spawnableWaterCreatureList.add(new SpawnListEntry(EntitySquid.class, 10, 4, 4));
	}

	protected BiomeDecorator func_35475_a() {
		return new BiomeDecorator(this);
	}

	private BiomeGenBase func_35478_a(float f1, float f2) {
		this.field_35490_s = f1;
		this.field_35489_t = f2;
		return this;
	}

	private BiomeGenBase func_35479_b(float f1, float f2) {
		this.field_35492_q = f1;
		this.field_35491_r = f2;
		return this;
	}

	private BiomeGenBase setDisableRain() {
		this.enableRain = false;
		return this;
	}

	public WorldGenerator getRandomWorldGenForTrees(Random random1) {
		return (WorldGenerator)(random1.nextInt(10) == 0 ? this.field_35480_A : this.field_35493_z);
	}

	protected BiomeGenBase setBiomeName(String string1) {
		this.biomeName = string1;
		return this;
	}

	protected BiomeGenBase func_4124_a(int i1) {
		this.field_6502_q = i1;
		return this;
	}

	protected BiomeGenBase setColor(int i1) {
		this.color = i1;
		return this;
	}

	public int getSkyColorByTemp(float f1) {
		f1 /= 3.0F;
		if(f1 < -1.0F) {
			f1 = -1.0F;
		}

		if(f1 > 1.0F) {
			f1 = 1.0F;
		}

		return Color.getHSBColor(0.62222224F - f1 * 0.05F, 0.5F + f1 * 0.1F, 1.0F).getRGB();
	}

	public List getSpawnableList(EnumCreatureType enumCreatureType1) {
		return enumCreatureType1 == EnumCreatureType.monster ? this.spawnableMonsterList : (enumCreatureType1 == EnumCreatureType.creature ? this.spawnableCreatureList : (enumCreatureType1 == EnumCreatureType.waterCreature ? this.spawnableWaterCreatureList : null));
	}

	public boolean getEnableSnow() {
		return this.enableSnow;
	}

	public boolean canSpawnLightningBolt() {
		return this.enableSnow ? false : this.enableRain;
	}

	public float getBiome() {
		return 0.1F;
	}

	public final int func_35476_e() {
		return (int)(this.field_35489_t * 65536.0F);
	}

	public final int func_35474_f() {
		return (int)(this.field_35490_s * 65536.0F);
	}

	public void func_35477_a(World world1, Random random2, int i3, int i4) {
		this.field_35488_u.func_35881_a(world1, random2, i3, i4);
	}
}
