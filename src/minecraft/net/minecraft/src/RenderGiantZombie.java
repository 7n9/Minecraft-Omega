package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class RenderGiantZombie extends RenderLiving {
	private final float scale;

	public RenderGiantZombie(ModelBase modelBase1, float f2, float f3) {
		super(modelBase1, f2 * f3);
		this.scale = f3;
	}

	protected void preRenderScale() {
		GL11.glScalef(this.scale, this.scale, this.scale);
	}

	protected void preRenderCallback(EntityLiving entityLiving1, float f2) {
		this.preRenderScale();
	}
}
