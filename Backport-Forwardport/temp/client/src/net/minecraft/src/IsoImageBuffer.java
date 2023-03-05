package net.minecraft.src;

import java.awt.image.BufferedImage;

public class IsoImageBuffer {
	public BufferedImage field_1348_a;
	public World worldObj;
	public int chunkX;
	public int chunkZ;
	public boolean field_1352_e = false;
	public boolean field_1351_f = false;
	public int field_1350_g = 0;
	public boolean field_1349_h = false;

	public IsoImageBuffer(World world1, int i2, int i3) {
		this.worldObj = world1;
		this.setChunkPosition(i2, i3);
	}

	public void setChunkPosition(int i1, int i2) {
		this.field_1352_e = false;
		this.chunkX = i1;
		this.chunkZ = i2;
		this.field_1350_g = 0;
		this.field_1349_h = false;
	}

	public void setWorldAndChunkPosition(World world1, int i2, int i3) {
		this.worldObj = world1;
		this.setChunkPosition(i2, i3);
	}
}
