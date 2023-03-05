package net.minecraft.src;

public class ItemSoup extends ItemFood {
	public ItemSoup(int i1, int i2) {
		super(i1, i2, false);
		this.setMaxStackSize(1);
	}

	public ItemStack func_35413_b(ItemStack itemStack1, World world2, EntityPlayer entityPlayer3) {
		super.func_35413_b(itemStack1, world2, entityPlayer3);
		return new ItemStack(Item.bowlEmpty);
	}
}
