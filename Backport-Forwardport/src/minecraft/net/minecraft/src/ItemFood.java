package net.minecraft.src;

public class ItemFood extends Item {
	public final int field_35430_a;
	private final int healAmount;
	private final float field_35428_c;
	private final boolean isWolfsFavoriteMeat;
	private boolean field_35431_bw;
	private int field_35433_bx;
	private int field_35432_by;
	private int field_35427_bz;
	private float field_35429_bA;

	public ItemFood(int i1, int i2, float f3, boolean z4) {
		super(i1);
		this.field_35430_a = 32;
		this.healAmount = i2;
		this.isWolfsFavoriteMeat = z4;
		this.field_35428_c = f3;
	}

	public ItemFood(int i1, int i2, boolean z3) {
		this(i1, i2, 0.6F, z3);
	}

	public ItemStack func_35413_b(ItemStack itemStack1, World world2, EntityPlayer entityPlayer3) {
		--itemStack1.stackSize;
		entityPlayer3.func_35191_at().func_35761_a(this);
		if(!world2.multiplayerWorld && this.field_35433_bx > 0 && world2.rand.nextFloat() < this.field_35429_bA) {
			entityPlayer3.func_35165_a(new PotionEffect(this.field_35433_bx, this.field_35432_by * 20, this.field_35427_bz));
		}

		return itemStack1;
	}

	public int func_35411_c(ItemStack itemStack1) {
		return 32;
	}

	public EnumAction func_35412_b(ItemStack itemStack1) {
		return EnumAction.eat;
	}

	public ItemStack onItemRightClick(ItemStack itemStack1, World world2, EntityPlayer entityPlayer3) {
		if(entityPlayer3.func_35197_b(this.field_35431_bw)) {
			entityPlayer3.func_35199_b(itemStack1, this.func_35411_c(itemStack1));
		}

		return itemStack1;
	}

	public int getHealAmount() {
		return this.healAmount;
	}

	public float func_35426_m() {
		return this.field_35428_c;
	}

	public boolean getIsWolfsFavoriteMeat() {
		return this.isWolfsFavoriteMeat;
	}

	public ItemFood func_35425_a(int i1, int i2, int i3, float f4) {
		this.field_35433_bx = i1;
		this.field_35432_by = i2;
		this.field_35427_bz = i3;
		this.field_35429_bA = f4;
		return this;
	}

	public ItemFood func_35424_o() {
		this.field_35431_bw = true;
		return this;
	}

	public Item setItemName(String string1) {
		return super.setItemName(string1);
	}
}
