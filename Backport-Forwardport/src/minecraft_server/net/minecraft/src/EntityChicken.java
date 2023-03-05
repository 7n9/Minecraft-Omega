package net.minecraft.src;

public class EntityChicken extends EntityAnimal {
	public boolean field_392_a = false;
	public float field_391_b = 0.0F;
	public float destPos = 0.0F;
	public float field_394_ae;
	public float field_393_af;
	public float field_390_ai = 1.0F;
	public int timeUntilNextEgg;

	public EntityChicken(World world1) {
		super(world1);
		this.texture = "/mob/chicken.png";
		this.setSize(0.3F, 0.7F);
		this.health = 4;
		this.timeUntilNextEgg = this.rand.nextInt(6000) + 6000;
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		this.field_393_af = this.field_391_b;
		this.field_394_ae = this.destPos;
		this.destPos = (float)((double)this.destPos + (double)(this.onGround ? -1 : 4) * 0.3D);
		if(this.destPos < 0.0F) {
			this.destPos = 0.0F;
		}

		if(this.destPos > 1.0F) {
			this.destPos = 1.0F;
		}

		if(!this.onGround && this.field_390_ai < 1.0F) {
			this.field_390_ai = 1.0F;
		}

		this.field_390_ai = (float)((double)this.field_390_ai * 0.9D);
		if(!this.onGround && this.motionY < 0.0D) {
			this.motionY *= 0.6D;
		}

		this.field_391_b += this.field_390_ai * 2.0F;
		if(!this.worldObj.singleplayerWorld && --this.timeUntilNextEgg <= 0) {
			this.worldObj.playSoundAtEntity(this, "mob.chickenplop", 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
			this.dropItem(Item.egg.shiftedIndex, 1);
			this.timeUntilNextEgg = this.rand.nextInt(6000) + 6000;
		}

	}

	protected void fall(float f1) {
	}

	public void writeEntityToNBT(NBTTagCompound nBTTagCompound1) {
		super.writeEntityToNBT(nBTTagCompound1);
	}

	public void readEntityFromNBT(NBTTagCompound nBTTagCompound1) {
		super.readEntityFromNBT(nBTTagCompound1);
	}

	protected String getLivingSound() {
		return "mob.chicken";
	}

	protected String getHurtSound() {
		return "mob.chickenhurt";
	}

	protected String getDeathSound() {
		return "mob.chickenhurt";
	}

	protected int getDropItemId() {
		return Item.feather.shiftedIndex;
	}

	protected void dropFewItems(boolean z1) {
		int i2 = this.rand.nextInt(3);

		for(int i3 = 0; i3 < i2; ++i3) {
			this.dropItem(Item.feather.shiftedIndex, 1);
		}

		if(this.fire > 0) {
			this.dropItem(Item.field_35409_bj.shiftedIndex, 1);
		} else {
			this.dropItem(Item.field_35415_bi.shiftedIndex, 1);
		}

	}
}
