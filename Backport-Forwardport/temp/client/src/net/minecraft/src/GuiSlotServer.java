package net.minecraft.src;

import org.lwjgl.opengl.GL11;

class GuiSlotServer extends GuiSlot {
	final GuiMultiplayer field_35410_a;

	public GuiSlotServer(GuiMultiplayer guiMultiplayer1) {
		super(guiMultiplayer1.mc, guiMultiplayer1.width, guiMultiplayer1.height, 32, guiMultiplayer1.height - 64, 36);
		this.field_35410_a = guiMultiplayer1;
	}

	protected int getSize() {
		return GuiMultiplayer.func_35320_a(this.field_35410_a).size();
	}

	protected void elementClicked(int i1, boolean z2) {
		GuiMultiplayer.func_35326_a(this.field_35410_a, i1);
		boolean z3 = GuiMultiplayer.func_35333_b(this.field_35410_a) >= 0 && GuiMultiplayer.func_35333_b(this.field_35410_a) < this.getSize();
		GuiMultiplayer.func_35329_c(this.field_35410_a).enabled = z3;
		GuiMultiplayer.func_35334_d(this.field_35410_a).enabled = z3;
		GuiMultiplayer.func_35339_e(this.field_35410_a).enabled = z3;
		if(z2 && z3) {
			GuiMultiplayer.func_35332_b(this.field_35410_a, i1);
		}

	}

	protected boolean isSelected(int i1) {
		return i1 == GuiMultiplayer.func_35333_b(this.field_35410_a);
	}

	protected int getContentHeight() {
		return GuiMultiplayer.func_35320_a(this.field_35410_a).size() * 36;
	}

	protected void drawBackground() {
		this.field_35410_a.drawDefaultBackground();
	}

	protected void drawSlot(int i1, int i2, int i3, int i4, Tessellator tessellator5) {
		ServerNBTStorage serverNBTStorage6 = (ServerNBTStorage)GuiMultiplayer.func_35320_a(this.field_35410_a).get(i1);
		synchronized(GuiMultiplayer.func_35321_g()) {
			if(GuiMultiplayer.func_35338_m() < 5 && !serverNBTStorage6.field_35790_f) {
				serverNBTStorage6.field_35790_f = true;
				serverNBTStorage6.field_35792_e = -2L;
				serverNBTStorage6.field_35791_d = "";
				serverNBTStorage6.field_35794_c = "";
				GuiMultiplayer.func_35331_n();
				(new ThreadPollServers(this, serverNBTStorage6)).start();
			}
		}

		this.field_35410_a.drawString(this.field_35410_a.fontRenderer, serverNBTStorage6.field_35795_a, i2 + 2, i3 + 1, 0xFFFFFF);
		this.field_35410_a.drawString(this.field_35410_a.fontRenderer, serverNBTStorage6.field_35791_d, i2 + 2, i3 + 12, 8421504);
		this.field_35410_a.drawString(this.field_35410_a.fontRenderer, serverNBTStorage6.field_35794_c, i2 + 215 - this.field_35410_a.fontRenderer.getStringWidth(serverNBTStorage6.field_35794_c), i3 + 12, 8421504);
		this.field_35410_a.drawString(this.field_35410_a.fontRenderer, serverNBTStorage6.field_35793_b, i2 + 2, i3 + 12 + 11, 3158064);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.field_35410_a.mc.renderEngine.bindTexture(this.field_35410_a.mc.renderEngine.getTexture("/gui/icons.png"));
		boolean z7 = false;
		boolean z8 = false;
		String string9 = "";
		byte b12;
		int i13;
		if(serverNBTStorage6.field_35790_f && serverNBTStorage6.field_35792_e != -2L) {
			b12 = 0;
			z8 = false;
			if(serverNBTStorage6.field_35792_e < 0L) {
				i13 = 5;
			} else if(serverNBTStorage6.field_35792_e < 150L) {
				i13 = 0;
			} else if(serverNBTStorage6.field_35792_e < 300L) {
				i13 = 1;
			} else if(serverNBTStorage6.field_35792_e < 600L) {
				i13 = 2;
			} else if(serverNBTStorage6.field_35792_e < 1000L) {
				i13 = 3;
			} else {
				i13 = 4;
			}

			if(serverNBTStorage6.field_35792_e < 0L) {
				string9 = "(no connection)";
			} else {
				string9 = serverNBTStorage6.field_35792_e + "ms";
			}
		} else {
			b12 = 1;
			i13 = (int)(System.currentTimeMillis() / 100L + (long)(i1 * 2) & 7L);
			if(i13 > 4) {
				i13 = 8 - i13;
			}

			string9 = "Polling..";
		}

		this.field_35410_a.drawTexturedModalRect(i2 + 205, i3, 0 + b12 * 10, 176 + i13 * 8, 10, 8);
		byte b10 = 4;
		if(this.field_35409_k >= i2 + 205 - b10 && this.field_35408_l >= i3 - b10 && this.field_35409_k <= i2 + 205 + 10 + b10 && this.field_35408_l <= i3 + 8 + b10) {
			GuiMultiplayer.func_35327_a(this.field_35410_a, string9);
		}

	}
}
