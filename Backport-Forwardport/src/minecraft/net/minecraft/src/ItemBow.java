package net.minecraft.src;

public class ItemBow extends Item {
	public ItemBow(int i1) {
		super(i1);
		this.maxStackSize = 1;
	}

	public void func_35414_a(ItemStack itemStack1, World world2, EntityPlayer entityPlayer3, int i4) {
		if(entityPlayer3.inventory.func_35157_d(Item.arrow.shiftedIndex)) {
			int i5 = this.func_35411_c(itemStack1) - i4;
			float f6 = (float)i5 / 20.0F;
			f6 = (f6 * f6 + f6 * 2.0F) / 3.0F;
			if((double)f6 < 0.1D) {
				return;
			}

			if(f6 > 1.0F) {
				f6 = 1.0F;
			}

			EntityArrow entityArrow7 = new EntityArrow(world2, entityPlayer3, f6 * 2.0F);
			if(f6 == 1.0F) {
				entityArrow7.field_35140_d = true;
			}

			world2.playSoundAtEntity(entityPlayer3, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f6 * 0.5F);
			entityPlayer3.inventory.consumeInventoryItem(Item.arrow.shiftedIndex);
			if(!world2.multiplayerWorld) {
				world2.entityJoinedWorld(entityArrow7);
			}
		}

	}

	public ItemStack func_35413_b(ItemStack itemStack1, World world2, EntityPlayer entityPlayer3) {
		return itemStack1;
	}

	public int func_35411_c(ItemStack itemStack1) {
		return 72000;
	}

	public EnumAction func_35412_b(ItemStack itemStack1) {
		return EnumAction.bow;
	}

	public ItemStack onItemRightClick(ItemStack itemStack1, World world2, EntityPlayer entityPlayer3) {
		if(entityPlayer3.inventory.func_35157_d(Item.arrow.shiftedIndex)) {
			entityPlayer3.func_35199_b(itemStack1, this.func_35411_c(itemStack1));
		}

		return itemStack1;
	}
}
