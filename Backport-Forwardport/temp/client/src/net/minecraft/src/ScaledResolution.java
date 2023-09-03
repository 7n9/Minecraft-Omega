package net.minecraft.src;

public class ScaledResolution {
	private int scaledWidth;
	private int scaledHeight;
	public double scaledWidthD;
	public double scaledHeightD;
	public int scaleFactor;

	public ScaledResolution(GameSettings gameSettings1, int i2, int i3) {
		this.scaledWidth = i2;
		this.scaledHeight = i3;
		this.scaleFactor = 1;
		int i4 = gameSettings1.guiScale;
		if(i4 == 0) {
			i4 = 1000;
		}

		while(this.scaleFactor < i4 && this.scaledWidth / (this.scaleFactor + 1) >= 320 && this.scaledHeight / (this.scaleFactor + 1) >= 240) {
			++this.scaleFactor;
		}

		this.scaledWidthD = (double)this.scaledWidth / (double)this.scaleFactor;
		this.scaledHeightD = (double)this.scaledHeight / (double)this.scaleFactor;
		this.scaledWidth = (int)Math.ceil(this.scaledWidthD);
		this.scaledHeight = (int)Math.ceil(this.scaledHeightD);
	}

	public int getScaledWidth() {
		return this.scaledWidth;
	}

	public int getScaledHeight() {
		return this.scaledHeight;
	}
}