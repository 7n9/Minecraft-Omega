package net.minecraft.src;

public class ItemApple extends Item {
	private final int healAmount;
	private final boolean isWolfsFavoriteMeat;

	public ItemApple(int i1, int i2, boolean z3) {
		super(i1);
		this.healAmount = i2;
		this.isWolfsFavoriteMeat = z3;
		this.maxStackSize = 6;
	}

	public ItemStack onItemRightClick(ItemStack itemStack1, World world2, EntityPlayer entityPlayer3) {
		--itemStack1.stackSize;
		entityPlayer3.heal(this.healAmount);
		return itemStack1;
	}

	public int getHealAmount() {
		return this.healAmount;
	}

	public boolean getIsWolfsFavoriteMeat() {
		return this.isWolfsFavoriteMeat;
	}
}
