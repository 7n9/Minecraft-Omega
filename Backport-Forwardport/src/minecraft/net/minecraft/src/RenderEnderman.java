package net.minecraft.src;

import java.util.Random;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.ARBMultitexture;
import org.lwjgl.opengl.GL12;

public class RenderEnderman extends RenderLiving {
	private ModelEnderman field_35444_c = (ModelEnderman)this.mainModel;
	private Random field_35445_h = new Random();

	public RenderEnderman() {
		super(new ModelEnderman(), 0.5F);
		this.setRenderPassModel(this.field_35444_c);
	}

	public void func_35442_a(EntityEnderman entityEnderman1, double d2, double d4, double d6, float f8, float f9) {
		this.field_35444_c.field_35407_a = entityEnderman1.func_35176_r() > 0;
		this.field_35444_c.field_35406_b = entityEnderman1.field_35187_a;
		if(entityEnderman1.field_35187_a) {
			double d10 = 0.02D;
			d2 += this.field_35445_h.nextGaussian() * d10;
			d6 += this.field_35445_h.nextGaussian() * d10;
		}

		super.doRenderLiving(entityEnderman1, d2, d4, d6, f8, f9);
	}

	protected void func_35443_a(EntityEnderman entityEnderman1, float f2) {
		super.renderEquippedItems(entityEnderman1, f2);
		if(entityEnderman1.func_35176_r() > 0) {
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glPushMatrix();
			float f3 = 0.5F;
			GL11.glTranslatef(0.0F, 0.6875F, -0.75F);
			f3 *= 1.0F;
			GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(f3, -f3, f3);
			int i4 = entityEnderman1.func_35115_a(f2);
			int i5 = i4 % 65536;
			int i6 = i4 / 65536;
			GL13.glMultiTexCoord2f(ARBMultitexture.GL_TEXTURE1_ARB, (float)i5 / 1.0F, (float)i6 / 1.0F);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.loadTexture("/terrain.png");
			this.renderBlocks.renderBlockOnInventory(Block.blocksList[entityEnderman1.func_35176_r()], entityEnderman1.func_35180_s(), 1.0F);
			GL11.glPopMatrix();
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		}

	}

	protected boolean func_35441_a(EntityEnderman entityEnderman1, int i2, float f3) {
		if(i2 != 0) {
			return false;
		} else {
			this.loadTexture("/mob/enderman_eyes.png");
			float f4 = 1.0F;
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_ALPHA_TEST);
			GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
			GL11.glDisable(GL11.GL_LIGHTING);
			int i5 = 61680;
			int i6 = i5 % 65536;
			int i7 = i5 / 65536;
			GL13.glMultiTexCoord2f(ARBMultitexture.GL_TEXTURE1_ARB, (float)i6 / 1.0F, (float)i7 / 1.0F);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, f4);
			return true;
		}
	}

	protected boolean shouldRenderPass(EntityLiving entityLiving1, int i2, float f3) {
		return this.func_35441_a((EntityEnderman)entityLiving1, i2, f3);
	}

	protected void renderEquippedItems(EntityLiving entityLiving1, float f2) {
		this.func_35443_a((EntityEnderman)entityLiving1, f2);
	}

	public void doRenderLiving(EntityLiving entityLiving1, double d2, double d4, double d6, float f8, float f9) {
		this.func_35442_a((EntityEnderman)entityLiving1, d2, d4, d6, f8, f9);
	}

	public void doRender(Entity entity1, double d2, double d4, double d6, float f8, float f9) {
		this.func_35442_a((EntityEnderman)entity1, d2, d4, d6, f8, f9);
	}
}
