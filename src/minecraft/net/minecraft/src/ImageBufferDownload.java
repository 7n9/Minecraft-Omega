package net.minecraft.src;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class ImageBufferDownload implements ImageBuffer {
	private int[] imageData;
	private int imageWidth;

	public BufferedImage parseUserSkin(BufferedImage bufferedImage1) {
		if(bufferedImage1 == null) {
			return null;
		} else {
			this.imageWidth = 64;
			int imageHeight = 32;
			BufferedImage bufferedImage2 = new BufferedImage(this.imageWidth, imageHeight, 2);
			Graphics graphics3 = bufferedImage2.getGraphics();
			graphics3.drawImage(bufferedImage1, 0, 0, null);
			graphics3.dispose();
			this.imageData = ((DataBufferInt)bufferedImage2.getRaster().getDataBuffer()).getData();
			this.func_884_b(0, 32, 16);
			this.func_885_a();
			this.func_884_b(16, 64, 32);
			boolean z4 = false;

			int i5;
			int i6;
			int i7;
			for(i5 = 32; i5 < 64; ++i5) {
				for(i6 = 0; i6 < 16; ++i6) {
					i7 = this.imageData[i5 + i6 * 64];
					if((i7 >> 24 & 255) < 128) {
						z4 = true;
					}
				}
			}

			if(!z4) {
				for(i5 = 32; i5 < 64; ++i5) {
					for(i6 = 0; i6 < 16; ++i6) {
						i7 = this.imageData[i5 + i6 * 64];
						if((i7 >> 24 & 255) < 128) {
							z4 = true;
						}
					}
				}
			}

			return bufferedImage2;
		}
	}

	private void func_885_a() {
		if(!this.func_886_c()) {
			for(int i5 = 32; i5 < 64; ++i5) {
				for(int i6 = 0; i6 < 32; ++i6) {
					this.imageData[i5 + i6 * this.imageWidth] &= 0xFFFFFF;
				}
			}

		}
	}

	private void func_884_b(int i2, int i3, int i4) {
		for(int i5 = 0; i5 < i3; ++i5) {
			for(int i6 = i2; i6 < i4; ++i6) {
				this.imageData[i5 + i6 * this.imageWidth] |= 0xFF000000;
			}
		}

	}

	private boolean func_886_c() {
		for(int i5 = 32; i5 < 64; ++i5) {
			for(int i6 = 0; i6 < 32; ++i6) {
				int i7 = this.imageData[i5 + i6 * this.imageWidth];
				if((i7 >> 24 & 255) < 128) {
					return true;
				}
			}
		}

		return false;
	}
}
