package net.minecraft.src;

public class EntityNoteFX extends EntityFX {
	float field_21065_a;

	public EntityNoteFX(World world1, double d2, double d4, double d6, double d8, double d10, double d12) {
		this(world1, d2, d4, d6, d8, d10, d12, 2.0F);
	}

	public EntityNoteFX(World world1, double d2, double d4, double d6, double d8, double d10, double d12, float f14) {
		super(world1, d2, d4, d6, 0.0D, 0.0D, 0.0D);
		this.motionX *= 0.009999999776482582D;
		this.motionY *= 0.009999999776482582D;
		this.motionZ *= 0.009999999776482582D;
		this.motionY += 0.2D;
		this.particleRed = MathHelper.sin(((float)d8 + 0.0F) * 3.1415927F * 2.0F) * 0.65F + 0.35F;
		this.particleGreen = MathHelper.sin(((float)d8 + 0.33333334F) * 3.1415927F * 2.0F) * 0.65F + 0.35F;
		this.particleBlue = MathHelper.sin(((float)d8 + 0.6666667F) * 3.1415927F * 2.0F) * 0.65F + 0.35F;
		this.particleScale *= 0.75F;
		this.particleScale *= f14;
		this.field_21065_a = this.particleScale;
		this.particleMaxAge = 6;
		this.noClip = false;
		this.particleTextureIndex = 64;
	}

	public void renderParticle(Tessellator tessellator1, float f2, float f3, float f4, float f5, float f6, float f7) {
		float f8 = ((float)this.particleAge + f2) / (float)this.particleMaxAge * 32.0F;
		if(f8 < 0.0F) {
			f8 = 0.0F;
		}

		if(f8 > 1.0F) {
			f8 = 1.0F;
		}

		this.particleScale = this.field_21065_a * f8;
		super.renderParticle(tessellator1, f2, f3, f4, f5, f6, f7);
	}

	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		if(this.particleAge++ >= this.particleMaxAge) {
			this.setEntityDead();
		}

		this.moveEntity(this.motionX, this.motionY, this.motionZ);
		if(this.posY == this.prevPosY) {
			this.motionX *= 1.1D;
			this.motionZ *= 1.1D;
		}

		this.motionX *= 0.6600000262260437D;
		this.motionY *= 0.6600000262260437D;
		this.motionZ *= 0.6600000262260437D;
		if(this.onGround) {
			this.motionX *= 0.699999988079071D;
			this.motionZ *= 0.699999988079071D;
		}

	}
}
