package net.minecraft.src;

import org.lwjgl.opengl.GL11;

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
			float f4 = (1.0F - entitySpider1.getEntityBrightness(1.0F)) * 0.5F;
			GL11.glEnable(3042);
			GL11.glDisable(3008);
			GL11.glBlendFunc(770, 771);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, f4);
			return true;
		}
	}

	protected float getDeathMaxRotation(EntityLiving entityLiving1) {
		return this.setSpiderDeathMaxRotation((EntitySpider)entityLiving1);
	}

	protected boolean shouldRenderPass(EntityLiving entityLiving1, int i2, float f3) {
		return this.setSpiderEyeBrightness((EntitySpider)entityLiving1, i2, f3);
	}
}
