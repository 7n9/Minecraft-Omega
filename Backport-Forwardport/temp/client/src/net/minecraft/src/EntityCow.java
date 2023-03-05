package net.minecraft.src;

public class EntityCow extends EntityAnimal {
	public EntityCow(World world1) {
		super(world1);
		this.texture = "/mob/cow.png";
		this.setSize(0.9F, 1.3F);
	}

	public void writeEntityToNBT(NBTTagCompound nBTTagCompound1) {
		super.writeEntityToNBT(nBTTagCompound1);
	}

	public void readEntityFromNBT(NBTTagCompound nBTTagCompound1) {
		super.readEntityFromNBT(nBTTagCompound1);
	}

	protected String getLivingSound() {
		return "mob.cow";
	}

	protected String getHurtSound() {
		return "mob.cowhurt";
	}

	protected String getDeathSound() {
		return "mob.cowhurt";
	}

	protected float getSoundVolume() {
		return 0.4F;
	}

	protected int getDropItemId() {
		return Item.leather.shiftedIndex;
	}

	protected void a(boolean z1) {
		int i2 = this.rand.nextInt(3);

		int i3;
		for(i3 = 0; i3 < i2; ++i3) {
			this.dropItem(Item.leather.shiftedIndex, 1);
		}

		i2 = this.rand.nextInt(3) + 1;

		for(i3 = 0; i3 < i2; ++i3) {
			if(this.fire > 0) {
				this.dropItem(Item.field_35418_bk.shiftedIndex, 1);
			} else {
				this.dropItem(Item.field_35417_bj.shiftedIndex, 1);
			}
		}

	}

	public boolean interact(EntityPlayer entityPlayer1) {
		ItemStack itemStack2 = entityPlayer1.inventory.getCurrentItem();
		if(itemStack2 != null && itemStack2.itemID == Item.bucketEmpty.shiftedIndex) {
			entityPlayer1.inventory.setInventorySlotContents(entityPlayer1.inventory.currentItem, new ItemStack(Item.bucketMilk));
			return true;
		} else {
			return false;
		}
	}
}
