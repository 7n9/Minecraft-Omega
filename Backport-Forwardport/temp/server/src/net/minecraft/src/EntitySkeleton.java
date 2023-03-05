package net.minecraft.src;

public class EntitySkeleton extends EntityMob {
	private static final ItemStack defaultHeldItem = new ItemStack(Item.bow, 1);

	public EntitySkeleton(World world1) {
		super(world1);
		this.texture = "/mob/skeleton.png";
	}

	protected String getLivingSound() {
		return "mob.skeleton";
	}

	protected String getHurtSound() {
		return "mob.skeletonhurt";
	}

	protected String getDeathSound() {
		return "mob.skeletonhurt";
	}

	public boolean attackEntityFrom(DamageSource damageSource1, int i2) {
		return super.attackEntityFrom(damageSource1, i2);
	}

	public void onDeath(DamageSource damageSource1) {
		super.onDeath(damageSource1);
		if(damageSource1.func_35079_e() instanceof EntityArrow && damageSource1.func_35080_a() instanceof EntityPlayer) {
			EntityPlayer entityPlayer2 = (EntityPlayer)damageSource1.func_35080_a();
			double d3 = entityPlayer2.posX - this.posX;
			double d5 = entityPlayer2.posZ - this.posZ;
			if(d3 * d3 + d5 * d5 >= 2500.0D) {
				entityPlayer2.triggerAchievement(AchievementList.field_35490_v);
			}
		}

	}

	public void onLivingUpdate() {
		if(this.worldObj.isDaytime() && !this.worldObj.singleplayerWorld) {
			float f1 = this.getEntityBrightness(1.0F);
			if(f1 > 0.5F && this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)) && this.rand.nextFloat() * 30.0F < (f1 - 0.4F) * 2.0F) {
				this.fire = 300;
			}
		}

		super.onLivingUpdate();
	}

	protected void attackEntity(Entity entity1, float f2) {
		if(f2 < 10.0F) {
			double d3 = entity1.posX - this.posX;
			double d5 = entity1.posZ - this.posZ;
			if(this.attackTime == 0) {
				EntityArrow entityArrow7 = new EntityArrow(this.worldObj, this, 1.0F);
				double d8 = entity1.posY + (double)entity1.getEyeHeight() - (double)0.7F - entityArrow7.posY;
				float f10 = MathHelper.sqrt_double(d3 * d3 + d5 * d5) * 0.2F;
				this.worldObj.playSoundAtEntity(this, "random.bow", 1.0F, 1.0F / (this.rand.nextFloat() * 0.4F + 0.8F));
				this.worldObj.entityJoinedWorld(entityArrow7);
				entityArrow7.setArrowHeading(d3, d8 + (double)f10, d5, 1.6F, 12.0F);
				this.attackTime = 60;
			}

			this.rotationYaw = (float)(Math.atan2(d5, d3) * 180.0D / (double)(float)Math.PI) - 90.0F;
			this.hasAttacked = true;
		}

	}

	public void writeEntityToNBT(NBTTagCompound nBTTagCompound1) {
		super.writeEntityToNBT(nBTTagCompound1);
	}

	public void readEntityFromNBT(NBTTagCompound nBTTagCompound1) {
		super.readEntityFromNBT(nBTTagCompound1);
	}

	protected int getDropItemId() {
		return Item.arrow.shiftedIndex;
	}

	protected void dropFewItems(boolean z1) {
		int i2 = this.rand.nextInt(3);

		int i3;
		for(i3 = 0; i3 < i2; ++i3) {
			this.dropItem(Item.arrow.shiftedIndex, 1);
		}

		i2 = this.rand.nextInt(3);

		for(i3 = 0; i3 < i2; ++i3) {
			this.dropItem(Item.bone.shiftedIndex, 1);
		}

	}
}
