package net.minecraft.src;

public abstract class EntityMob extends EntityCreature implements IMob {
	protected int attackStrength = 2;

	public EntityMob(World world1) {
		super(world1);
		this.health = 20;
		this.field_35171_bJ = 5;
	}

	public void onLivingUpdate() {
		float f1 = this.getEntityBrightness(1.0F);
		if(f1 > 0.5F) {
			this.entityAge += 2;
		}

		super.onLivingUpdate();
	}

	public void onUpdate() {
		super.onUpdate();
		if(!this.worldObj.multiplayerWorld && this.worldObj.difficultySetting == 0) {
			this.setEntityDead();
		}

	}

	protected Entity findPlayerToAttack() {
		EntityPlayer entityPlayer1 = this.worldObj.getClosestPlayerToEntity(this, 16.0D);
		return entityPlayer1 != null && this.canEntityBeSeen(entityPlayer1) ? entityPlayer1 : null;
	}

	public boolean attackEntityFrom(DamageSource damageSource1, int i2) {
		if(super.attackEntityFrom(damageSource1, i2)) {
			Entity entity3 = damageSource1.func_35532_a();
			if(this.riddenByEntity != entity3 && this.ridingEntity != entity3) {
				if(entity3 != this) {
					this.entityToAttack = entity3;
				}

				return true;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	protected boolean func_35175_b(Entity entity1) {
		return entity1.attackEntityFrom(DamageSource.func_35525_a(this), this.attackStrength);
	}

	protected void attackEntity(Entity entity1, float f2) {
		if(this.attackTime <= 0 && f2 < 2.0F && entity1.boundingBox.maxY > this.boundingBox.minY && entity1.boundingBox.minY < this.boundingBox.maxY) {
			this.attackTime = 20;
			this.func_35175_b(entity1);
		}

	}

	protected float getBlockPathWeight(int i1, int i2, int i3) {
		return 0.5F - this.worldObj.getLightBrightness(i1, i2, i3);
	}

	public void writeEntityToNBT(NBTTagCompound nBTTagCompound1) {
		super.writeEntityToNBT(nBTTagCompound1);
	}

	public void readEntityFromNBT(NBTTagCompound nBTTagCompound1) {
		super.readEntityFromNBT(nBTTagCompound1);
	}

	public boolean getCanSpawnHere() {
		int i1 = MathHelper.floor_double(this.posX);
		int i2 = MathHelper.floor_double(this.boundingBox.minY);
		int i3 = MathHelper.floor_double(this.posZ);
		if(this.worldObj.getSavedLightValue(EnumSkyBlock.Sky, i1, i2, i3) > this.rand.nextInt(32)) {
			return false;
		} else {
			int i4 = this.worldObj.getBlockLightValue(i1, i2, i3);
			if(this.worldObj.getIsThundering()) {
				int i5 = this.worldObj.skylightSubtracted;
				this.worldObj.skylightSubtracted = 10;
				i4 = this.worldObj.getBlockLightValue(i1, i2, i3);
				this.worldObj.skylightSubtracted = i5;
			}

			return i4 <= this.rand.nextInt(8) && super.getCanSpawnHere();
		}
	}
}
