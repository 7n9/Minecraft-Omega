package net.minecraft.src;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

class CanvasMojangLogo extends Canvas {
	private BufferedImage logo;

	public CanvasMojangLogo() {
		try {
			this.logo = ImageIO.read(Objects.requireNonNull(PanelCrashReport.class.getResource("/gui/logo.png")));
		} catch (IOException ignored) {
		}

		byte b1 = 100;
		this.setPreferredSize(new Dimension(b1, b1));
		this.setMinimumSize(new Dimension(b1, b1));
	}

	public void paint(Graphics graphics1) {
		super.paint(graphics1);
		graphics1.drawImage(this.logo, this.getWidth() / 2 - this.logo.getWidth() / 2, 32, null);
	}
}
