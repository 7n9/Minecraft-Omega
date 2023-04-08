package net.minecraft.src;

import net.minecraft.client.Minecraft;

import org.lwjgl.opengl.GL11;

public class GuiAchievement extends Gui {
	private Minecraft theGame;
	private int achievementWindowWidth;
	private int achievementWindowHeight;
	private String field_25085_d;
	private String field_25084_e;
	private Achievement theAchievement;
	private long field_25083_f;
	private RenderItem itemRender;
	private boolean field_27103_i;

	public GuiAchievement(Minecraft minecraft1) {
		this.theGame = minecraft1;
		this.itemRender = new RenderItem();
	}

	public void queueTakenAchievement(Achievement achievement1) {
		this.field_25085_d = StatCollector.translateToLocal("achievement.get");
		this.field_25084_e = achievement1.statName;
		this.field_25083_f = System.currentTimeMillis();
		this.theAchievement = achievement1;
		this.field_27103_i = false;
	}

	public void queueAchievementInformation(Achievement achievement1) {
		this.field_25085_d = achievement1.statName;
		this.field_25084_e = achievement1.getDescription();
		this.field_25083_f = System.currentTimeMillis() - 2500L;
		this.theAchievement = achievement1;
		this.field_27103_i = true;
	}

	private void updateAchievementWindowScale() {
		GL11.glViewport(0, 0, this.theGame.displayWidth, this.theGame.displayHeight);
		GL11.glMatrixMode(5889);
		GL11.glLoadIdentity();
		GL11.glMatrixMode(5888);
		GL11.glLoadIdentity();
		this.achievementWindowWidth = this.theGame.displayWidth;
		this.achievementWindowHeight = this.theGame.displayHeight;
		ScaledResolution scaledResolution1 = new ScaledResolution(this.theGame.gameSettings, this.theGame.displayWidth, this.theGame.displayHeight);
		this.achievementWindowWidth = scaledResolution1.getScaledWidth();
		this.achievementWindowHeight = scaledResolution1.getScaledHeight();
		GL11.glClear(256);
		GL11.glMatrixMode(5889);
		GL11.glLoadIdentity();
		GL11.glOrtho(0.0D, (double)this.achievementWindowWidth, (double)this.achievementWindowHeight, 0.0D, 1000.0D, 3000.0D);
		GL11.glMatrixMode(5888);
		GL11.glLoadIdentity();
		GL11.glTranslatef(0.0F, 0.0F, -2000.0F);
	}

	public void updateAchievementWindow() {
		if(Minecraft.hasPaidCheckTime > 0L) {
			GL11.glDisable(2929);
			GL11.glDepthMask(false);
			RenderHelper.disableStandardItemLighting();
			this.updateAchievementWindowScale();
			String string1 = "Minecraft Beta 1.7.3   Unlicensed Copy :(";
			String string2 = "(Or logged in from another location)";
			String string3 = "Purchase at minecraft.net";
			this.theGame.fontRenderer.drawStringWithShadow(string1, 2, 2, 16777215);
			this.theGame.fontRenderer.drawStringWithShadow(string2, 2, 11, 16777215);
			this.theGame.fontRenderer.drawStringWithShadow(string3, 2, 20, 16777215);
			GL11.glDepthMask(true);
			GL11.glEnable(2929);
		}

		if(this.theAchievement != null && this.field_25083_f != 0L) {
			double d8 = (double)(System.currentTimeMillis() - this.field_25083_f) / 3000.0D;
			if(this.field_27103_i || this.field_27103_i || d8 >= 0.0D && d8 <= 1.0D) {
				this.updateAchievementWindowScale();
				GL11.glDisable(2929);
				GL11.glDepthMask(false);
				double d9 = d8 * 2.0D;
				if(d9 > 1.0D) {
					d9 = 2.0D - d9;
				}

				d9 *= 4.0D;
				d9 = 1.0D - d9;
				if(d9 < 0.0D) {
					d9 = 0.0D;
				}

				d9 *= d9;
				d9 *= d9;
				int i5 = this.achievementWindowWidth - 160;
				int i6 = 0 - (int)(d9 * 36.0D);
				int i7 = this.theGame.renderEngine.getTexture("/achievement/bg.png");
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				GL11.glEnable(3553);
				GL11.glBindTexture(3553, i7);
				GL11.glDisable(2896);
				this.drawTexturedModalRect(i5, i6, 96, 202, 160, 32);
				if(this.field_27103_i) {
					this.theGame.fontRenderer.func_27278_a(this.field_25084_e, i5 + 30, i6 + 7, 120, -1);
				} else {
					this.theGame.fontRenderer.drawString(this.field_25085_d, i5 + 30, i6 + 7, -256);
					this.theGame.fontRenderer.drawString(this.field_25084_e, i5 + 30, i6 + 18, -1);
				}

				GL11.glPushMatrix();
				GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
				RenderHelper.enableStandardItemLighting();
				GL11.glPopMatrix();
				GL11.glDisable(2896);
				GL11.glEnable(32826);
				GL11.glEnable(2903);
				GL11.glEnable(2896);
				this.itemRender.renderItemIntoGUI(this.theGame.fontRenderer, this.theGame.renderEngine, this.theAchievement.theItemStack, i5 + 8, i6 + 8);
				GL11.glDisable(2896);
				GL11.glDepthMask(true);
				GL11.glEnable(2929);
			} else {
				this.field_25083_f = 0L;
			}
		}
	}
}
