package net.minecraft.src;

public class ItemFood extends Item {
	public final int field_35427_a;
	private final int healAmount;
	private final float field_35431_bu;
	private final boolean isWolfsFavoriteMeat;
	private boolean field_35428_bw;
	private int field_35430_bx;
	private int field_35429_by;
	private int field_35425_bz;
	private float field_35426_bA;

	public ItemFood(int i1, int i2, float f3, boolean z4) {
		super(i1);
		this.field_35427_a = 32;
		this.healAmount = i2;
		this.isWolfsFavoriteMeat = z4;
		this.field_35431_bu = f3;
	}

	public ItemFood(int i1, int i2, boolean z3) {
		this(i1, i2, 0.6F, z3);
	}

	public ItemStack func_35405_b(ItemStack itemStack1, World world2, EntityPlayer entityPlayer3) {
		--itemStack1.stackSize;
		entityPlayer3.func_35207_V().func_35582_a(this);
		if(!world2.singleplayerWorld && this.field_35430_bx > 0 && world2.rand.nextFloat() < this.field_35426_bA) {
			entityPlayer3.func_35182_d(new PotionEffect(this.field_35430_bx, this.field_35429_by * 20, this.field_35425_bz));
		}

		return itemStack1;
	}

	public int func_35404_c(ItemStack itemStack1) {
		return 32;
	}

	public EnumAction func_35406_b(ItemStack itemStack1) {
		return EnumAction.eat;
	}

	public ItemStack onItemRightClick(ItemStack itemStack1, World world2, EntityPlayer entityPlayer3) {
		if(entityPlayer3.func_35197_c(this.field_35428_bw)) {
			entityPlayer3.func_35201_a(itemStack1, this.func_35404_c(itemStack1));
		}

		return itemStack1;
	}

	public int getHealAmount() {
		return this.healAmount;
	}

	public float func_35424_l() {
		return this.field_35431_bu;
	}

	public boolean getIsWolfsFavoriteMeat() {
		return this.isWolfsFavoriteMeat;
	}

	public ItemFood func_35422_a(int i1, int i2, int i3, float f4) {
		this.field_35430_bx = i1;
		this.field_35429_by = i2;
		this.field_35425_bz = i3;
		this.field_35426_bA = f4;
		return this;
	}

	public ItemFood func_35423_n() {
		this.field_35428_bw = true;
		return this;
	}

	public Item setItemName(String string1) {
		return super.setItemName(string1);
	}
}
