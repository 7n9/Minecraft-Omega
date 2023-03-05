package net.minecraft.src;

public class ItemSword extends Item {
	private int weaponDamage;

	public ItemSword(int i1, EnumToolMaterial enumToolMaterial2) {
		super(i1);
		this.maxStackSize = 1;
		this.setMaxDamage(enumToolMaterial2.getMaxUses());
		this.weaponDamage = 4 + enumToolMaterial2.getDamageVsEntity() * 2;
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
		return true;
	}

	public int getDamageVsEntity(Entity entity1) {
		return this.weaponDamage;
	}

	public EnumAction func_35406_b(ItemStack itemStack1) {
		return EnumAction.block;
	}

	public int func_35404_c(ItemStack itemStack1) {
		return 72000;
	}

	public ItemStack onItemRightClick(ItemStack itemStack1, World world2, EntityPlayer entityPlayer3) {
		entityPlayer3.func_35201_a(itemStack1, this.func_35404_c(itemStack1));
		return itemStack1;
	}

	public boolean canHarvestBlock(Block block1) {
		return block1.blockID == Block.web.blockID;
	}
}
