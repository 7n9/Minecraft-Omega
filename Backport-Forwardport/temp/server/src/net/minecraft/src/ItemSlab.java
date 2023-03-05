package net.minecraft.src;

public class ItemSlab extends ItemBlock {
	public ItemSlab(int i1) {
		super(i1);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	public int getMetadata(int i1) {
		return i1;
	}

	public String func_35407_a(ItemStack itemStack1) {
		int i2 = itemStack1.getItemDamage();
		if(i2 < 0 || i2 >= BlockStep.field_35062_a.length) {
			i2 = 0;
		}

		return super.getItemName() + "." + BlockStep.field_35062_a[i2];
	}
}
