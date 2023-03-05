package net.minecraft.src;

public abstract class EntityAnimal extends EntityCreature {
	public EntityAnimal(World world1) {
		super(world1);
	}

	public boolean attackEntityFrom(DamageSource damageSource1, int i2) {
		this.field_35174_at = 60;
		return super.attackEntityFrom(damageSource1, i2);
	}

	protected float getBlockPathWeight(int i1, int i2, int i3) {
		return this.worldObj.getBlockId(i1, i2 - 1, i3) == Block.grass.blockID ? 10.0F : this.worldObj.getLightBrightness(i1, i2, i3) - 0.5F;
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
		return this.worldObj.getBlockId(i1, i2 - 1, i3) == Block.grass.blockID && this.worldObj.getFullBlockLightValue(i1, i2, i3) > 8 && super.getCanSpawnHere();
	}

	public int getTalkInterval() {
		return 120;
	}

	protected boolean canDespawn() {
		return false;
	}

	protected int a(EntityPlayer entityPlayer1) {
		return 1 + this.worldObj.rand.nextInt(3);
	}
}
