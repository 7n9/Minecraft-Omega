package net.minecraft.src;

public class EntityReddustFX extends EntityFX {
	float field_673_a;

	public EntityReddustFX(World world1, double d2, double d4, double d6, float f8, float f9, float f10) {
		this(world1, d2, d4, d6, 1.0F, f8, f9, f10);
	}

	public EntityReddustFX(World world1, double d2, double d4, double d6, float f8, float f9, float f10, float f11) {
		super(world1, d2, d4, d6, 0.0D, 0.0D, 0.0D);
		this.motionX *= 0.10000000149011612D;
		this.motionY *= 0.10000000149011612D;
		this.motionZ *= 0.10000000149011612D;
		if(f9 == 0.0F) {
			f9 = 1.0F;
		}

		float f12 = (float)Math.random() * 0.4F + 0.6F;
		this.particleRed = ((float)(Math.random() * 0.20000000298023224D) + 0.8F) * f9 * f12;
		this.particleGreen = ((float)(Math.random() * 0.20000000298023224D) + 0.8F) * f10 * f12;
		this.particleBlue = ((float)(Math.random() * 0.20000000298023224D) + 0.8F) * f11 * f12;
		this.particleScale *= 0.75F;
		this.particleScale *= f8;
		this.field_673_a = this.particleScale;
		this.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
		this.particleMaxAge = (int)((float)this.particleMaxAge * f8);
		this.noClip = false;
	}

	public void renderParticle(Tessellator tessellator1, float f2, float f3, float f4, float f5, float f6, float f7) {
		float f8 = ((float)this.particleAge + f2) / (float)this.particleMaxAge * 32.0F;
		if(f8 < 0.0F) {
			f8 = 0.0F;
		}

		if(f8 > 1.0F) {
			f8 = 1.0F;
		}

		this.particleScale = this.field_673_a * f8;
		super.renderParticle(tessellator1, f2, f3, f4, f5, f6, f7);
	}

	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		if(this.particleAge++ >= this.particleMaxAge) {
			this.setEntityDead();
		}

		this.particleTextureIndex = 7 - this.particleAge * 8 / this.particleMaxAge;
		this.moveEntity(this.motionX, this.motionY, this.motionZ);
		if(this.posY == this.prevPosY) {
			this.motionX *= 1.1D;
			this.motionZ *= 1.1D;
		}

		this.motionX *= 0.9599999785423279D;
		this.motionY *= 0.9599999785423279D;
		this.motionZ *= 0.9599999785423279D;
		if(this.onGround) {
			this.motionX *= 0.699999988079071D;
			this.motionZ *= 0.699999988079071D;
		}

	}
}
