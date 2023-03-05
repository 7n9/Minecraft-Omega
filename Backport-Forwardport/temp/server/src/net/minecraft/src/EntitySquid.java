package net.minecraft.src;

public class EntitySquid extends EntityWaterMob {
	public float field_21063_a = 0.0F;
	public float field_21062_b = 0.0F;
	public float field_21061_c = 0.0F;
	public float field_21059_f = 0.0F;
	public float field_21060_ak = 0.0F;
	public float field_21058_al = 0.0F;
	public float field_21057_am = 0.0F;
	public float field_21056_an = 0.0F;
	private float randomMotionSpeed = 0.0F;
	private float field_21054_ap = 0.0F;
	private float field_21053_aq = 0.0F;
	private float randomMotionVecX = 0.0F;
	private float randomMotionVecY = 0.0F;
	private float randomMotionVecZ = 0.0F;

	public EntitySquid(World world1) {
		super(world1);
		this.texture = "/mob/squid.png";
		this.setSize(0.95F, 0.95F);
		this.field_21054_ap = 1.0F / (this.rand.nextFloat() + 1.0F) * 0.2F;
	}

	public void writeEntityToNBT(NBTTagCompound nBTTagCompound1) {
		super.writeEntityToNBT(nBTTagCompound1);
	}

	public void readEntityFromNBT(NBTTagCompound nBTTagCompound1) {
		super.readEntityFromNBT(nBTTagCompound1);
	}

	protected String getLivingSound() {
		return null;
	}

	protected String getHurtSound() {
		return null;
	}

	protected String getDeathSound() {
		return null;
	}

	protected float getSoundVolume() {
		return 0.4F;
	}

	protected int getDropItemId() {
		return 0;
	}

	protected void dropFewItems(boolean z1) {
		int i2 = this.rand.nextInt(3) + 1;

		for(int i3 = 0; i3 < i2; ++i3) {
			this.entityDropItem(new ItemStack(Item.dyePowder, 1, 0), 0.0F);
		}

	}

	public boolean interact(EntityPlayer entityPlayer1) {
		return false;
	}

	public boolean isInWater() {
		return this.worldObj.handleMaterialAcceleration(this.boundingBox.expand(0.0D, -0.6000000238418579D, 0.0D), Material.water, this);
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		this.field_21062_b = this.field_21063_a;
		this.field_21059_f = this.field_21061_c;
		this.field_21058_al = this.field_21060_ak;
		this.field_21056_an = this.field_21057_am;
		this.field_21060_ak += this.field_21054_ap;
		if(this.field_21060_ak > 6.2831855F) {
			this.field_21060_ak -= 6.2831855F;
			if(this.rand.nextInt(10) == 0) {
				this.field_21054_ap = 1.0F / (this.rand.nextFloat() + 1.0F) * 0.2F;
			}
		}

		if(this.isInWater()) {
			float f1;
			if(this.field_21060_ak < (float)Math.PI) {
				f1 = this.field_21060_ak / (float)Math.PI;
				this.field_21057_am = MathHelper.sin(f1 * f1 * (float)Math.PI) * (float)Math.PI * 0.25F;
				if((double)f1 > 0.75D) {
					this.randomMotionSpeed = 1.0F;
					this.field_21053_aq = 1.0F;
				} else {
					this.field_21053_aq *= 0.8F;
				}
			} else {
				this.field_21057_am = 0.0F;
				this.randomMotionSpeed *= 0.9F;
				this.field_21053_aq *= 0.99F;
			}

			if(!this.isMultiplayerEntity) {
				this.motionX = (double)(this.randomMotionVecX * this.randomMotionSpeed);
				this.motionY = (double)(this.randomMotionVecY * this.randomMotionSpeed);
				this.motionZ = (double)(this.randomMotionVecZ * this.randomMotionSpeed);
			}

			f1 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
			this.renderYawOffset += (-((float)Math.atan2(this.motionX, this.motionZ)) * 180.0F / (float)Math.PI - this.renderYawOffset) * 0.1F;
			this.rotationYaw = this.renderYawOffset;
			this.field_21061_c += (float)Math.PI * this.field_21053_aq * 1.5F;
			this.field_21063_a += (-((float)Math.atan2((double)f1, this.motionY)) * 180.0F / (float)Math.PI - this.field_21063_a) * 0.1F;
		} else {
			this.field_21057_am = MathHelper.abs(MathHelper.sin(this.field_21060_ak)) * (float)Math.PI * 0.25F;
			if(!this.isMultiplayerEntity) {
				this.motionX = 0.0D;
				this.motionY -= 0.08D;
				this.motionY *= (double)0.98F;
				this.motionZ = 0.0D;
			}

			this.field_21063_a = (float)((double)this.field_21063_a + (double)(-90.0F - this.field_21063_a) * 0.02D);
		}

	}

	public void moveEntityWithHeading(float f1, float f2) {
		this.moveEntity(this.motionX, this.motionY, this.motionZ);
	}

	protected void updateEntityActionState() {
		if(this.rand.nextInt(50) == 0 || !this.inWater || this.randomMotionVecX == 0.0F && this.randomMotionVecY == 0.0F && this.randomMotionVecZ == 0.0F) {
			float f1 = this.rand.nextFloat() * (float)Math.PI * 2.0F;
			this.randomMotionVecX = MathHelper.cos(f1) * 0.2F;
			this.randomMotionVecY = -0.1F + this.rand.nextFloat() * 0.2F;
			this.randomMotionVecZ = MathHelper.sin(f1) * 0.2F;
		}

		this.despawnEntity();
	}
}