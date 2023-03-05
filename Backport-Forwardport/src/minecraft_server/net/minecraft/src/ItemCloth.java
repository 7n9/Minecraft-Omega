package net.minecraft.src;

public class ItemCloth extends ItemBlock {
	public ItemCloth(int i1) {
		super(i1);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	public int getMetadata(int i1) {
		return i1;
	}

	public String func_35407_a(ItemStack itemStack1) {
		return super.getItemName() + "." + ItemDye.dyeColorNames[BlockCloth.getBlockFromDye(itemStack1.getItemDamage())];
	}
}
