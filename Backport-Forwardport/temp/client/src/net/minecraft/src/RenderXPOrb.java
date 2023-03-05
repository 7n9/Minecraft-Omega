package net.minecraft.src;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class RenderXPOrb extends Render {
	private RenderBlocks field_35439_b = new RenderBlocks();
	public boolean field_35440_a = true;

	public RenderXPOrb() {
		this.shadowSize = 0.15F;
		this.field_194_c = 0.75F;
	}

	public void func_35438_a(EntityXPOrb entityXPOrb1, double d2, double d4, double d6, float f8, float f9) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float)d2, (float)d4, (float)d6);
		int i10 = entityXPOrb1.func_35120_i();
		this.loadTexture("/item/xporb.png");
		Tessellator tessellator11 = Tessellator.instance;
		float f12 = (float)(i10 % 4 * 16 + 0) / 64.0F;
		float f13 = (float)(i10 % 4 * 16 + 16) / 64.0F;
		float f14 = (float)(i10 / 4 * 16 + 0) / 64.0F;
		float f15 = (float)(i10 / 4 * 16 + 16) / 64.0F;
		float f16 = 1.0F;
		float f17 = 0.5F;
		float f18 = 0.25F;
		float f19 = entityXPOrb1.getEntityBrightness(f9) * 255.0F;
		float f20 = ((float)entityXPOrb1.field_35127_a + f9) / 2.0F;
		int i21 = (int)((MathHelper.sin(f20 + 0.0F) + 1.0F) * 0.5F * f19);
		int i22 = (int)f19;
		int i23 = (int)((MathHelper.sin(f20 + 4.1887903F) + 1.0F) * 0.1F * f19);
		int i24 = i21 << 16 | i22 << 8 | i23;
		GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
		float f25 = 0.3F;
		GL11.glScalef(f25, f25, f25);
		tessellator11.startDrawingQuads();
		tessellator11.setColorRGBA_I(i24, 128);
		tessellator11.setNormal(0.0F, 1.0F, 0.0F);
		tessellator11.addVertexWithUV((double)(0.0F - f17), (double)(0.0F - f18), 0.0D, (double)f12, (double)f15);
		tessellator11.addVertexWithUV((double)(f16 - f17), (double)(0.0F - f18), 0.0D, (double)f13, (double)f15);
		tessellator11.addVertexWithUV((double)(f16 - f17), (double)(1.0F - f18), 0.0D, (double)f13, (double)f14);
		tessellator11.addVertexWithUV((double)(0.0F - f17), (double)(1.0F - f18), 0.0D, (double)f12, (double)f14);
		tessellator11.draw();
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
	}

	public void doRender(Entity entity1, double d2, double d4, double d6, float f8, float f9) {
		this.func_35438_a((EntityXPOrb)entity1, d2, d4, d6, f8, f9);
	}
}
