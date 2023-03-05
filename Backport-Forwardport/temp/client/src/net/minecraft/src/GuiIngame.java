package net.minecraft.src;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.client.Minecraft;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class GuiIngame extends Gui {
	private static RenderItem itemRenderer = new RenderItem();
	private List chatMessageList = new ArrayList();
	private Random rand = new Random();
	private Minecraft mc;
	public String field_933_a = null;
	private int updateCounter = 0;
	private String recordPlaying = "";
	private int recordPlayingUpFor = 0;
	private boolean recordIsPlaying = false;
	public float damageGuiPartialTime;
	float prevVignetteBrightness = 1.0F;

	public GuiIngame(Minecraft minecraft1) {
		this.mc = minecraft1;
	}

	public void renderGameOverlay(float f1, boolean z2, int i3, int i4) {
		ScaledResolution scaledResolution5 = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
		int i6 = scaledResolution5.getScaledWidth();
		int i7 = scaledResolution5.getScaledHeight();
		FontRenderer fontRenderer8 = this.mc.fontRenderer;
		this.mc.entityRenderer.func_905_b();
		GL11.glEnable(GL11.GL_BLEND);
		if(Minecraft.isFancyGraphicsEnabled()) {
			this.renderVignette(this.mc.thePlayer.getEntityBrightness(f1), i6, i7);
		}

		ItemStack itemStack9 = this.mc.thePlayer.inventory.armorItemInSlot(3);
		if(!this.mc.gameSettings.thirdPersonView && itemStack9 != null && itemStack9.itemID == Block.pumpkin.blockID) {
			this.renderPumpkinBlur(i6, i7);
		}

		float f10;
		if(!this.mc.thePlayer.func_35160_a(Potion.field_35684_k)) {
			f10 = this.mc.thePlayer.prevTimeInPortal + (this.mc.thePlayer.timeInPortal - this.mc.thePlayer.prevTimeInPortal) * f1;
			if(f10 > 0.0F) {
				this.renderPortalOverlay(f10, i6, i7);
			}
		}

		boolean z11;
		int i12;
		int i16;
		int i17;
		int i18;
		int i20;
		int i22;
		int i47;
		if(!this.mc.playerController.func_35643_e()) {
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/gui/gui.png"));
			InventoryPlayer inventoryPlayer30 = this.mc.thePlayer.inventory;
			this.zLevel = -90.0F;
			this.drawTexturedModalRect(i6 / 2 - 91, i7 - 22, 0, 0, 182, 22);
			this.drawTexturedModalRect(i6 / 2 - 91 - 1 + inventoryPlayer30.currentItem * 20, i7 - 22 - 1, 0, 22, 24, 22);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/gui/icons.png"));
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_ONE_MINUS_DST_COLOR, GL11.GL_ONE_MINUS_SRC_COLOR);
			this.drawTexturedModalRect(i6 / 2 - 7, i7 / 2 - 7, 0, 0, 16, 16);
			GL11.glDisable(GL11.GL_BLEND);
			z11 = this.mc.thePlayer.heartsLife / 3 % 2 == 1;
			if(this.mc.thePlayer.heartsLife < 10) {
				z11 = false;
			}

			i12 = this.mc.thePlayer.health;
			int i13 = this.mc.thePlayer.prevHealth;
			this.rand.setSeed((long)(this.updateCounter * 312871));
			boolean z14 = false;
			FoodStats foodStats15 = this.mc.thePlayer.func_35191_at();
			i16 = foodStats15.func_35765_a();
			i17 = foodStats15.func_35769_b();
			int i19;
			if(this.mc.playerController.shouldDrawHUD()) {
				i18 = i6 / 2 - 91;
				i19 = i6 / 2 + 91;
				i20 = this.mc.thePlayer.func_35193_as();
				int i23;
				if(i20 > 0) {
					short s21 = 182;
					i22 = this.mc.thePlayer.field_35211_aX * (s21 + 1) / this.mc.thePlayer.func_35193_as();
					i23 = i7 - 32 + 3;
					this.drawTexturedModalRect(i18, i23, 0, 64, s21, 5);
					if(i22 > 0) {
						this.drawTexturedModalRect(i18, i23, 0, 69, i22, 5);
					}
				}

				i47 = i7 - 39;
				i22 = i47 - 10;
				i23 = this.mc.thePlayer.getPlayerArmorValue();
				int i24 = -1;
				if(this.mc.thePlayer.func_35160_a(Potion.field_35681_l)) {
					i24 = this.updateCounter % 25;
				}

				int i25;
				int i26;
				int i29;
				for(i25 = 0; i25 < 10; ++i25) {
					if(i23 > 0) {
						i26 = i18 + i25 * 8;
						if(i25 * 2 + 1 < i23) {
							this.drawTexturedModalRect(i26, i22, 34, 9, 9, 9);
						}

						if(i25 * 2 + 1 == i23) {
							this.drawTexturedModalRect(i26, i22, 25, 9, 9, 9);
						}

						if(i25 * 2 + 1 > i23) {
							this.drawTexturedModalRect(i26, i22, 16, 9, 9, 9);
						}
					}

					i26 = 16;
					if(this.mc.thePlayer.func_35160_a(Potion.field_35689_u)) {
						i26 += 36;
					}

					byte b27 = 0;
					if(z11) {
						b27 = 1;
					}

					int i28 = i18 + i25 * 8;
					i29 = i47;
					if(i12 <= 4) {
						i29 = i47 + this.rand.nextInt(2);
					}

					if(i25 == i24) {
						i29 -= 2;
					}

					this.drawTexturedModalRect(i28, i29, 16 + b27 * 9, 0, 9, 9);
					if(z11) {
						if(i25 * 2 + 1 < i13) {
							this.drawTexturedModalRect(i28, i29, i26 + 54, 0, 9, 9);
						}

						if(i25 * 2 + 1 == i13) {
							this.drawTexturedModalRect(i28, i29, i26 + 63, 0, 9, 9);
						}
					}

					if(i25 * 2 + 1 < i12) {
						this.drawTexturedModalRect(i28, i29, i26 + 36, 0, 9, 9);
					}

					if(i25 * 2 + 1 == i12) {
						this.drawTexturedModalRect(i28, i29, i26 + 45, 0, 9, 9);
					}
				}

				int i53;
				for(i25 = 0; i25 < 10; ++i25) {
					i26 = i47;
					i53 = 16;
					byte b54 = 0;
					if(this.mc.thePlayer.func_35160_a(Potion.field_35691_s)) {
						i53 += 36;
						b54 = 13;
					}

					if(this.mc.thePlayer.func_35191_at().func_35760_d() <= 0.0F && this.updateCounter % (i16 * 3 + 1) == 0) {
						i26 = i47 + (this.rand.nextInt(3) - 1);
					}

					if(z14) {
						b54 = 1;
					}

					i29 = i19 - i25 * 8 - 9;
					this.drawTexturedModalRect(i29, i26, 16 + b54 * 9, 27, 9, 9);
					if(z14) {
						if(i25 * 2 + 1 < i17) {
							this.drawTexturedModalRect(i29, i26, i53 + 54, 27, 9, 9);
						}

						if(i25 * 2 + 1 == i17) {
							this.drawTexturedModalRect(i29, i26, i53 + 63, 27, 9, 9);
						}
					}

					if(i25 * 2 + 1 < i16) {
						this.drawTexturedModalRect(i29, i26, i53 + 36, 27, 9, 9);
					}

					if(i25 * 2 + 1 == i16) {
						this.drawTexturedModalRect(i29, i26, i53 + 45, 27, 9, 9);
					}
				}

				if(this.mc.thePlayer.isInsideOfMaterial(Material.water)) {
					i25 = (int)Math.ceil((double)(this.mc.thePlayer.air - 2) * 10.0D / 300.0D);
					i26 = (int)Math.ceil((double)this.mc.thePlayer.air * 10.0D / 300.0D) - i25;

					for(i53 = 0; i53 < i25 + i26; ++i53) {
						if(i53 < i25) {
							this.drawTexturedModalRect(i19 - i53 * 8 - 9, i22, 16, 18, 9, 9);
						} else {
							this.drawTexturedModalRect(i19 - i53 * 8 - 9, i22, 25, 18, 9, 9);
						}
					}
				}
			}

			GL11.glDisable(GL11.GL_BLEND);
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glPushMatrix();
			GL11.glRotatef(120.0F, 1.0F, 0.0F, 0.0F);
			RenderHelper.enableStandardItemLighting();
			GL11.glPopMatrix();

			for(i18 = 0; i18 < 9; ++i18) {
				i19 = i6 / 2 - 90 + i18 * 20 + 2;
				i20 = i7 - 16 - 3;
				this.renderInventorySlot(i18, i19, i20, f1);
			}

			RenderHelper.disableStandardItemLighting();
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		}

		if(this.mc.thePlayer.func_22060_M() > 0) {
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			GL11.glDisable(GL11.GL_ALPHA_TEST);
			int i31 = this.mc.thePlayer.func_22060_M();
			float f34 = (float)i31 / 100.0F;
			if(f34 > 1.0F) {
				f34 = 1.0F - (float)(i31 - 100) / 10.0F;
			}

			i12 = (int)(220.0F * f34) << 24 | 1052704;
			this.drawRect(0, 0, i6, i7, i12);
			GL11.glEnable(GL11.GL_ALPHA_TEST);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
		}

		if(this.mc.playerController.func_35642_f() && this.mc.thePlayer.field_35210_aY > 0) {
			;
		}

		String string45;
		if(this.mc.gameSettings.showDebugInfo) {
			GL11.glPushMatrix();
			if(Minecraft.hasPaidCheckTime > 0L) {
				GL11.glTranslatef(0.0F, 32.0F, 0.0F);
			}

			fontRenderer8.drawStringWithShadow("Minecraft Beta 1.8 (" + this.mc.debug + ")", 2, 2, 0xFFFFFF);
			fontRenderer8.drawStringWithShadow(this.mc.debugInfoRenders(), 2, 12, 0xFFFFFF);
			fontRenderer8.drawStringWithShadow(this.mc.func_6262_n(), 2, 22, 0xFFFFFF);
			fontRenderer8.drawStringWithShadow(this.mc.debugInfoEntities(), 2, 32, 0xFFFFFF);
			fontRenderer8.drawStringWithShadow(this.mc.func_21002_o(), 2, 42, 0xFFFFFF);
			long j32 = Runtime.getRuntime().maxMemory();
			long j35 = Runtime.getRuntime().totalMemory();
			long j38 = Runtime.getRuntime().freeMemory();
			long j43 = j35 - j38;
			string45 = "Used memory: " + j43 * 100L / j32 + "% (" + j43 / 1024L / 1024L + "MB) of " + j32 / 1024L / 1024L + "MB";
			this.drawString(fontRenderer8, string45, i6 - fontRenderer8.getStringWidth(string45) - 2, 2, 14737632);
			string45 = "Allocated memory: " + j35 * 100L / j32 + "% (" + j35 / 1024L / 1024L + "MB)";
			this.drawString(fontRenderer8, string45, i6 - fontRenderer8.getStringWidth(string45) - 2, 12, 14737632);
			this.drawString(fontRenderer8, "x: " + this.mc.thePlayer.posX, 2, 64, 14737632);
			this.drawString(fontRenderer8, "y: " + this.mc.thePlayer.posY, 2, 72, 14737632);
			this.drawString(fontRenderer8, "z: " + this.mc.thePlayer.posZ, 2, 80, 14737632);
			this.drawString(fontRenderer8, "f: " + (MathHelper.floor_double((double)(this.mc.thePlayer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3), 2, 88, 14737632);
			this.drawString(fontRenderer8, "Seed: " + this.mc.theWorld.getRandomSeed(), 2, 104, 14737632);
			GL11.glPopMatrix();
		} else {
			fontRenderer8.drawStringWithShadow("Minecraft Beta 1.8", 2, 2, 0xFFFFFF);
		}

		if(this.recordPlayingUpFor > 0) {
			f10 = (float)this.recordPlayingUpFor - f1;
			int i36 = (int)(f10 * 256.0F / 20.0F);
			if(i36 > 255) {
				i36 = 255;
			}

			if(i36 > 0) {
				GL11.glPushMatrix();
				GL11.glTranslatef((float)(i6 / 2), (float)(i7 - 48), 0.0F);
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
				i12 = 0xFFFFFF;
				if(this.recordIsPlaying) {
					i12 = Color.HSBtoRGB(f10 / 50.0F, 0.7F, 0.6F) & 0xFFFFFF;
				}

				fontRenderer8.drawString(this.recordPlaying, -fontRenderer8.getStringWidth(this.recordPlaying) / 2, -4, i12 + (i36 << 24));
				GL11.glDisable(GL11.GL_BLEND);
				GL11.glPopMatrix();
			}
		}

		byte b33 = 10;
		z11 = false;
		if(this.mc.currentScreen instanceof GuiChat) {
			b33 = 20;
			z11 = true;
		}

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		GL11.glPushMatrix();
		GL11.glTranslatef(0.0F, (float)(i7 - 48), 0.0F);

		int i41;
		for(i12 = 0; i12 < this.chatMessageList.size() && i12 < b33; ++i12) {
			if(((ChatLine)this.chatMessageList.get(i12)).updateCounter < 200 || z11) {
				double d37 = (double)((ChatLine)this.chatMessageList.get(i12)).updateCounter / 200.0D;
				d37 = 1.0D - d37;
				d37 *= 10.0D;
				if(d37 < 0.0D) {
					d37 = 0.0D;
				}

				if(d37 > 1.0D) {
					d37 = 1.0D;
				}

				d37 *= d37;
				i41 = (int)(255.0D * d37);
				if(z11) {
					i41 = 255;
				}

				if(i41 > 0) {
					byte b44 = 2;
					i17 = -i12 * 9;
					string45 = ((ChatLine)this.chatMessageList.get(i12)).message;
					this.drawRect(b44, i17 - 1, b44 + 320, i17 + 8, i41 / 2 << 24);
					GL11.glEnable(GL11.GL_BLEND);
					fontRenderer8.drawStringWithShadow(string45, b44, i17, 0xFFFFFF + (i41 << 24));
				}
			}
		}

		GL11.glPopMatrix();
		if(this.mc.thePlayer instanceof EntityClientPlayerMP && this.mc.gameSettings.field_35384_x.field_35965_e) {
			NetClientHandler netClientHandler39 = ((EntityClientPlayerMP)this.mc.thePlayer).sendQueue;
			List list42 = netClientHandler39.field_35786_c;
			int i40 = netClientHandler39.field_35785_d;
			i41 = i40;

			for(i16 = 1; i41 > 20; i41 = (i40 + i16 - 1) / i16) {
				++i16;
			}

			i17 = 300 / i16;
			if(i17 > 150) {
				i17 = 150;
			}

			i18 = (i6 - i16 * i17) / 2;
			byte b46 = 10;
			this.drawRect(i18 - 1, b46 - 1, i18 + i17 * i16, b46 + 9 * i41, Integer.MIN_VALUE);

			for(i20 = 0; i20 < i40; ++i20) {
				i47 = i18 + i20 % i16 * i17;
				i22 = b46 + i20 / i16 * 9;
				this.drawRect(i47, i22, i47 + i17 - 1, i22 + 8, 553648127);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				GL11.glEnable(GL11.GL_ALPHA_TEST);
				if(i20 < list42.size()) {
					GuiSavingLevelString guiSavingLevelString48 = (GuiSavingLevelString)list42.get(i20);
					fontRenderer8.drawStringWithShadow(guiSavingLevelString48.field_35624_a, i47, i22, 0xFFFFFF);
					this.mc.renderEngine.bindTexture(this.mc.renderEngine.getTexture("/gui/icons.png"));
					boolean z49 = false;
					boolean z51 = false;
					byte b50 = 0;
					z51 = false;
					byte b52;
					if(guiSavingLevelString48.field_35623_b < 0) {
						b52 = 5;
					} else if(guiSavingLevelString48.field_35623_b < 150) {
						b52 = 0;
					} else if(guiSavingLevelString48.field_35623_b < 300) {
						b52 = 1;
					} else if(guiSavingLevelString48.field_35623_b < 600) {
						b52 = 2;
					} else if(guiSavingLevelString48.field_35623_b < 1000) {
						b52 = 3;
					} else {
						b52 = 4;
					}

					this.zLevel += 100.0F;
					this.drawTexturedModalRect(i47 + i17 - 12, i22, 0 + b50 * 10, 176 + b52 * 8, 10, 8);
					this.zLevel -= 100.0F;
				}
			}
		}

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
	}

	private void renderPumpkinBlur(int i1, int i2) {
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("%blur%/misc/pumpkinblur.png"));
		Tessellator tessellator3 = Tessellator.instance;
		tessellator3.startDrawingQuads();
		tessellator3.addVertexWithUV(0.0D, (double)i2, -90.0D, 0.0D, 1.0D);
		tessellator3.addVertexWithUV((double)i1, (double)i2, -90.0D, 1.0D, 1.0D);
		tessellator3.addVertexWithUV((double)i1, 0.0D, -90.0D, 1.0D, 0.0D);
		tessellator3.addVertexWithUV(0.0D, 0.0D, -90.0D, 0.0D, 0.0D);
		tessellator3.draw();
		GL11.glDepthMask(true);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

	private void renderVignette(float f1, int i2, int i3) {
		f1 = 1.0F - f1;
		if(f1 < 0.0F) {
			f1 = 0.0F;
		}

		if(f1 > 1.0F) {
			f1 = 1.0F;
		}

		this.prevVignetteBrightness = (float)((double)this.prevVignetteBrightness + (double)(f1 - this.prevVignetteBrightness) * 0.01D);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		GL11.glBlendFunc(GL11.GL_ZERO, GL11.GL_ONE_MINUS_SRC_COLOR);
		GL11.glColor4f(this.prevVignetteBrightness, this.prevVignetteBrightness, this.prevVignetteBrightness, 1.0F);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("%blur%/misc/vignette.png"));
		Tessellator tessellator4 = Tessellator.instance;
		tessellator4.startDrawingQuads();
		tessellator4.addVertexWithUV(0.0D, (double)i3, -90.0D, 0.0D, 1.0D);
		tessellator4.addVertexWithUV((double)i2, (double)i3, -90.0D, 1.0D, 1.0D);
		tessellator4.addVertexWithUV((double)i2, 0.0D, -90.0D, 1.0D, 0.0D);
		tessellator4.addVertexWithUV(0.0D, 0.0D, -90.0D, 0.0D, 0.0D);
		tessellator4.draw();
		GL11.glDepthMask(true);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	}

	private void renderPortalOverlay(float f1, int i2, int i3) {
		if(f1 < 1.0F) {
			f1 *= f1;
			f1 *= f1;
			f1 = f1 * 0.8F + 0.2F;
		}

		GL11.glDisable(GL11.GL_ALPHA_TEST);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, f1);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/terrain.png"));
		float f4 = (float)(Block.portal.blockIndexInTexture % 16) / 16.0F;
		float f5 = (float)(Block.portal.blockIndexInTexture / 16) / 16.0F;
		float f6 = (float)(Block.portal.blockIndexInTexture % 16 + 1) / 16.0F;
		float f7 = (float)(Block.portal.blockIndexInTexture / 16 + 1) / 16.0F;
		Tessellator tessellator8 = Tessellator.instance;
		tessellator8.startDrawingQuads();
		tessellator8.addVertexWithUV(0.0D, (double)i3, -90.0D, (double)f4, (double)f7);
		tessellator8.addVertexWithUV((double)i2, (double)i3, -90.0D, (double)f6, (double)f7);
		tessellator8.addVertexWithUV((double)i2, 0.0D, -90.0D, (double)f6, (double)f5);
		tessellator8.addVertexWithUV(0.0D, 0.0D, -90.0D, (double)f4, (double)f5);
		tessellator8.draw();
		GL11.glDepthMask(true);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

	private void renderInventorySlot(int i1, int i2, int i3, float f4) {
		ItemStack itemStack5 = this.mc.thePlayer.inventory.mainInventory[i1];
		if(itemStack5 != null) {
			float f6 = (float)itemStack5.animationsToGo - f4;
			if(f6 > 0.0F) {
				GL11.glPushMatrix();
				float f7 = 1.0F + f6 / 5.0F;
				GL11.glTranslatef((float)(i2 + 8), (float)(i3 + 12), 0.0F);
				GL11.glScalef(1.0F / f7, (f7 + 1.0F) / 2.0F, 1.0F);
				GL11.glTranslatef((float)(-(i2 + 8)), (float)(-(i3 + 12)), 0.0F);
			}

			itemRenderer.renderItemIntoGUI(this.mc.fontRenderer, this.mc.renderEngine, itemStack5, i2, i3);
			if(f6 > 0.0F) {
				GL11.glPopMatrix();
			}

			itemRenderer.renderItemOverlayIntoGUI(this.mc.fontRenderer, this.mc.renderEngine, itemStack5, i2, i3);
		}
	}

	public void updateTick() {
		if(this.recordPlayingUpFor > 0) {
			--this.recordPlayingUpFor;
		}

		++this.updateCounter;

		for(int i1 = 0; i1 < this.chatMessageList.size(); ++i1) {
			++((ChatLine)this.chatMessageList.get(i1)).updateCounter;
		}

	}

	public void clearChatMessages() {
		this.chatMessageList.clear();
	}

	public void addChatMessage(String string1) {
		while(this.mc.fontRenderer.getStringWidth(string1) > 320) {
			int i2;
			for(i2 = 1; i2 < string1.length() && this.mc.fontRenderer.getStringWidth(string1.substring(0, i2 + 1)) <= 320; ++i2) {
			}

			this.addChatMessage(string1.substring(0, i2));
			string1 = string1.substring(i2);
		}

		this.chatMessageList.add(0, new ChatLine(string1));

		while(this.chatMessageList.size() > 50) {
			this.chatMessageList.remove(this.chatMessageList.size() - 1);
		}

	}

	public void setRecordPlayingMessage(String string1) {
		this.recordPlaying = "Now playing: " + string1;
		this.recordPlayingUpFor = 60;
		this.recordIsPlaying = true;
	}

	public void addChatMessageTranslate(String string1) {
		StringTranslate stringTranslate2 = StringTranslate.getInstance();
		String string3 = stringTranslate2.translateKey(string1);
		this.addChatMessage(string3);
	}
}
