package net.minecraft.src;

public class ItemWand extends Item {
	private int weaponDamage;

	public ItemWand(int i1) {
		super(i1);
		this.maxStackSize = 1;
	}

	public float getStrVsBlock(ItemStack itemStack1, Block block2) {
		return block2.blockID == Block.web.blockID ? 15.0F : 1.5F;
	}

	public boolean hitEntity(ItemStack itemStack1, EntityLiving entityLiving2, EntityLiving entityLiving3) {
		itemStack1.damageItem(1, entityLiving3);
		return true;
	}

	public boolean onBlockDestroyed(ItemStack itemStack1, int i2, int i3, int i4, int i5, EntityLiving entityLiving6) {
		itemStack1.damageItem(2, entityLiving6);
		return false;
	}

	public int getDamageVsEntity(Entity entity1) {
		return 10000;
	}


	public ItemStack onItemRightClick(ItemStack itemStack1, World world2, EntityPlayer entityPlayer3) {
//		EntityFireball entityFireball17 = new EntityFireball(world2, entityPlayer3, 7, 7,7 );
//		double d18 = 4.0D;
//		entityFireball17.posX = entityPlayer3.posX;
//		entityFireball17.posY = entityPlayer3.posY;
//		entityFireball17.posZ = entityPlayer3.posZ;
//		entityFireball17.rotationPitch = entityPlayer3.rotationPitch;
//		entityFireball17.rotationYaw = entityPlayer3.rotationYaw;
//		entityFireball17.setPositionAndRotation(entityPlayer3.posX, entityPlayer3.posY, entityPlayer3.posZ, entityPlayer3.rotationYaw, entityPlayer3.rotationPitch);
//		entityPlayer3.worldObj.entityJoinedWorld(entityFireball17);
		world2.entityJoinedWorld(new EntityArrow(world2, entityPlayer3));

		return itemStack1;
	}

	public boolean isFull3D() {
		return true;
	}

	public boolean canHarvestBlock(Block block1) {
		return false;
	}
}
