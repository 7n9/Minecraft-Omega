package net.minecraft.src;

public abstract class WorldProvider {
	public World worldObj;
	public WorldChunkManager worldChunkMgr;
	public boolean canSleepInWorld = false;
	public boolean isHellWorld = false;
	public boolean hasNoSky = false;
	public float[] lightBrightnessTable = new float[16];
	public int worldType = 0;
	private float[] colorsSunriseSunset = new float[4];

	public final void registerWorld(World world1) {
		this.worldObj = world1;
		this.registerWorldChunkManager();
		this.generateLightBrightnessTable();
	}

	protected void generateLightBrightnessTable() {
		float f1 = 0.0F;

		for(int i2 = 0; i2 <= 15; ++i2) {
			float f3 = 1.0F - (float)i2 / 15.0F;
			this.lightBrightnessTable[i2] = (1.0F - f3) / (f3 * 3.0F + 1.0F) * (1.0F - f1) + f1;
		}

	}

	protected void registerWorldChunkManager() {
		this.worldChunkMgr = new WorldChunkManager(this.worldObj);
	}

	public IChunkProvider getChunkProvider() {
		return new ChunkProviderGenerate(this.worldObj, this.worldObj.getRandomSeed(), this.worldObj.getWorldInfo().func_35499_o());
	}

	public boolean canCoordinateBeSpawn(int i1, int i2) {
		int i3 = this.worldObj.getFirstUncoveredBlock(i1, i2);
		return i3 == Block.grass.blockID;
	}

	public float calculateCelestialAngle(long j1, float f3) {
		int i4 = (int)(j1 % 24000L);
		float f5 = ((float)i4 + f3) / 24000.0F - 0.25F;
		if(f5 < 0.0F) {
			++f5;
		}

		if(f5 > 1.0F) {
			--f5;
		}

		float f6 = f5;
		f5 = 1.0F - (float)((Math.cos((double)f5 * Math.PI) + 1.0D) / 2.0D);
		f5 = f6 + (f5 - f6) / 3.0F;
		return f5;
	}

	public boolean canRespawnHere() {
		return true;
	}

	public static WorldProvider getProviderForDimension(int i0) {
		return (WorldProvider)(i0 == -1 ? new WorldProviderHell() : (i0 == 0 ? new WorldProviderSurface() : (i0 == 1 ? new WorldProviderSky() : null)));
	}
}