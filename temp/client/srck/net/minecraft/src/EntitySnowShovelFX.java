package net.minecraft.src;

public class EntitySnowShovelFX extends EntityFX {
	float field_27017_a;

	public EntitySnowShovelFX(World world1, double d2, double d4, double d6, double d8, double d10, double d12) {
		this(world1, d2, d4, d6, d8, d10, d12, 1.0F);
	}

	public EntitySnowShovelFX(World world1, double d2, double d4, double d6, double d8, double d10, double d12, float f14) {
		super(world1, d2, d4, d6, d8, d10, d12);
		this.motionX *= 0.10000000149011612D;
		this.motionY *= 0.10000000149011612D;
		this.motionZ *= 0.10000000149011612D;
		this.motionX += d8;
		this.motionY += d10;
		this.motionZ += d12;
		this.particleRed = this.particleGreen = this.particleBlue = 1.0F - (float)(Math.random() * 0.30000001192092896D);
		this.particleScale *= 0.75F;
		this.particleScale *= f14;
		this.field_27017_a = this.particleScale;
		this.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
		this.particleMaxAge = (int)((float)this.particleMaxAge * f14);
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

		this.particleScale = this.field_27017_a * f8;
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
		this.motionY -= 0.03D;
		this.moveEntity(this.motionX, this.motionY, this.motionZ);
		this.motionX *= 0.9900000095367432D;
		this.motionY *= 0.9900000095367432D;
		this.motionZ *= 0.9900000095367432D;
		if(this.onGround) {
			this.motionX *= 0.699999988079071D;
			this.motionZ *= 0.699999988079071D;
		}

	}
}
