package net.minecraft.src;

public class EntityTNTPrimed extends Entity {
	public int fuse;

	public EntityTNTPrimed(World world1) {
		super(world1);
		this.fuse = 0;
		this.preventEntitySpawning = true;
		this.setSize(0.98F, 0.98F);
		this.yOffset = this.height / 2.0F;
	}

	public EntityTNTPrimed(World world1, double d2, double d4, double d6) {
		this(world1);
		this.setPosition(d2, d4, d6);
		float f8 = (float)(Math.random() * 3.1415927410125732D * 2.0D);
		this.motionX = (double)(-MathHelper.sin(f8 * 3.1415927F / 180.0F) * 0.02F);
		this.motionY = 0.20000000298023224D;
		this.motionZ = (double)(-MathHelper.cos(f8 * 3.1415927F / 180.0F) * 0.02F);
		this.fuse = 80;
		this.prevPosX = d2;
		this.prevPosY = d4;
		this.prevPosZ = d6;
	}

	protected void entityInit() {
	}

	protected boolean canTriggerWalking() {
		return false;
	}

	public boolean canBeCollidedWith() {
		return !this.isDead;
	}

	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		this.motionY -= 0.03999999910593033D;
		this.moveEntity(this.motionX, this.motionY, this.motionZ);
		this.motionX *= 0.9800000190734863D;
		this.motionY *= 0.9800000190734863D;
		this.motionZ *= 0.9800000190734863D;
		if(this.onGround) {
			this.motionX *= 0.699999988079071D;
			this.motionZ *= 0.699999988079071D;
			this.motionY *= -0.5D;
		}

		if(this.fuse-- <= 0) {
			if(!this.worldObj.multiplayerWorld) {
				this.setEntityDead();
				this.explode();
			} else {
				this.setEntityDead();
			}
		} else {
			this.worldObj.spawnParticle("smoke", this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);
		}

	}

	private void explode() {
		float f1 = 4.0F;
		this.worldObj.createExplosion((Entity)null, this.posX, this.posY, this.posZ, f1);
	}

	protected void writeEntityToNBT(NBTTagCompound nBTTagCompound1) {
		nBTTagCompound1.setByte("Fuse", (byte)this.fuse);
	}

	protected void readEntityFromNBT(NBTTagCompound nBTTagCompound1) {
		this.fuse = nBTTagCompound1.getByte("Fuse");
	}

	public float getShadowSize() {
		return 0.0F;
	}
}
