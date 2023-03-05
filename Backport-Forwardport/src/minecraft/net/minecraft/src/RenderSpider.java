package net.minecraft.src;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.ARBMultitexture;

public class RenderSpider extends RenderLiving {
	public RenderSpider() {
		super(new ModelSpider(), 1.0F);
		this.setRenderPassModel(new ModelSpider());
	}

	protected float setSpiderDeathMaxRotation(EntitySpider entitySpider1) {
		return 180.0F;
	}

	protected boolean setSpiderEyeBrightness(EntitySpider entitySpider1, int i2, float f3) {
		if(i2 != 0) {
			return false;
		} else if(i2 != 0) {
			return false;
		} else {
			this.loadTexture("/mob/spider_eyes.png");
			float f4 = 1.0F;
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_ALPHA_TEST);
			GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
			int i5 = 61680;
			int i6 = i5 % 65536;
			int i7 = i5 / 65536;
			GL13.glMultiTexCoord2f(ARBMultitexture.GL_TEXTURE1_ARB, (float)i6 / 1.0F, (float)i7 / 1.0F);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, f4);
			return true;
		}
	}

	protected void func_35446_a(EntitySpider entitySpider1, float f2) {
		float f3 = entitySpider1.func_35188_k_();
		GL11.glScalef(f3, f3, f3);
	}

	protected void preRenderCallback(EntityLiving entityLiving1, float f2) {
		this.func_35446_a((EntitySpider)entityLiving1, f2);
	}

	protected float getDeathMaxRotation(EntityLiving entityLiving1) {
		return this.setSpiderDeathMaxRotation((EntitySpider)entityLiving1);
	}

	protected boolean shouldRenderPass(EntityLiving entityLiving1, int i2, float f3) {
		return this.setSpiderEyeBrightness((EntitySpider)entityLiving1, i2, f3);
	}
}
