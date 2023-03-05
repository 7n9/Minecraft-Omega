package net.minecraft.src;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.ARBMultitexture;

public class EntityPickupFX extends EntityFX {
	private Entity field_675_a;
	private Entity field_679_o;
	private int age = 0;
	private int maxAge = 0;
	private float field_676_r;

	public EntityPickupFX(World world1, Entity entity2, Entity entity3, float f4) {
		super(world1, entity2.posX, entity2.posY, entity2.posZ, entity2.motionX, entity2.motionY, entity2.motionZ);
		this.field_675_a = entity2;
		this.field_679_o = entity3;
		this.maxAge = 3;
		this.field_676_r = f4;
	}

	public void renderParticle(Tessellator tessellator1, float f2, float f3, float f4, float f5, float f6, float f7) {
		float f8 = ((float)this.age + f2) / (float)this.maxAge;
		f8 *= f8;
		double d9 = this.field_675_a.posX;
		double d11 = this.field_675_a.posY;
		double d13 = this.field_675_a.posZ;
		double d15 = this.field_679_o.lastTickPosX + (this.field_679_o.posX - this.field_679_o.lastTickPosX) * (double)f2;
		double d17 = this.field_679_o.lastTickPosY + (this.field_679_o.posY - this.field_679_o.lastTickPosY) * (double)f2 + (double)this.field_676_r;
		double d19 = this.field_679_o.lastTickPosZ + (this.field_679_o.posZ - this.field_679_o.lastTickPosZ) * (double)f2;
		double d21 = d9 + (d15 - d9) * (double)f8;
		double d23 = d11 + (d17 - d11) * (double)f8;
		double d25 = d13 + (d19 - d13) * (double)f8;
		int i27 = MathHelper.floor_double(d21);
		int i28 = MathHelper.floor_double(d23 + (double)(this.yOffset / 2.0F));
		int i29 = MathHelper.floor_double(d25);
		int i30 = this.func_35115_a(f2);
		int i31 = i30 % 65536;
		int i32 = i30 / 65536;
		GL13.glMultiTexCoord2f(ARBMultitexture.GL_TEXTURE1_ARB, (float)i31 / 1.0F, (float)i32 / 1.0F);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		d21 -= interpPosX;
		d23 -= interpPosY;
		d25 -= interpPosZ;
		RenderManager.instance.renderEntityWithPosYaw(this.field_675_a, (double)((float)d21), (double)((float)d23), (double)((float)d25), this.field_675_a.rotationYaw, f2);
	}

	public void onUpdate() {
		++this.age;
		if(this.age == this.maxAge) {
			this.setEntityDead();
		}

	}

	public int getFXLayer() {
		return 3;
	}
}
