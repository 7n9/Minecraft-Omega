package net.minecraft.src;

public class FoodStats {
	private int field_35776_a = 20;
	private float field_35774_b = 5.0F;
	private float field_35775_c;
	private int field_35772_d = 0;
	private int field_35773_e = 20;

	public void func_35771_a(int i1, float f2) {
		this.field_35776_a = Math.min(i1 + this.field_35776_a, 20);
		this.field_35774_b = Math.min(this.field_35774_b + (float)i1 * f2 * 2.0F, (float)this.field_35776_a);
	}

	public void func_35761_a(ItemFood itemFood1) {
		this.func_35771_a(itemFood1.getHealAmount(), itemFood1.func_35426_m());
	}

	public void func_35768_a(EntityPlayer entityPlayer1) {
		int i2 = entityPlayer1.worldObj.difficultySetting;
		this.field_35773_e = this.field_35776_a;
		if(this.field_35775_c > 4.0F) {
			this.field_35775_c -= 4.0F;
			if(this.field_35774_b > 0.0F) {
				this.field_35774_b = Math.max(this.field_35774_b - 1.0F, 0.0F);
			} else if(i2 > 0) {
				this.field_35776_a = Math.max(this.field_35776_a - 1, 0);
			}
		}

		if(this.field_35776_a >= 18 && entityPlayer1.func_35194_au()) {
			++this.field_35772_d;
			if(this.field_35772_d >= 80) {
				entityPlayer1.heal(1);
				this.field_35772_d = 0;
			}
		} else if(this.field_35776_a <= 0) {
			++this.field_35772_d;
			if(this.field_35772_d >= 80) {
				if(entityPlayer1.health > 10 || i2 >= 3 || entityPlayer1.health > 1 && i2 >= 2) {
					entityPlayer1.attackEntityFrom(DamageSource.field_35536_f, 1);
				}

				this.field_35772_d = 0;
			}
		} else {
			this.field_35772_d = 0;
		}

	}

	public void func_35766_a(NBTTagCompound nBTTagCompound1) {
		if(nBTTagCompound1.hasKey("foodLevel")) {
			this.field_35776_a = nBTTagCompound1.getInteger("foodLevel");
			this.field_35772_d = nBTTagCompound1.getInteger("foodTickTimer");
			this.field_35774_b = nBTTagCompound1.getFloat("foodSaturationLevel");
			this.field_35775_c = nBTTagCompound1.getFloat("foodExhaustionLevel");
		}

	}

	public void func_35763_b(NBTTagCompound nBTTagCompound1) {
		nBTTagCompound1.setInteger("foodLevel", this.field_35776_a);
		nBTTagCompound1.setInteger("foodTickTimer", this.field_35772_d);
		nBTTagCompound1.setFloat("foodSaturationLevel", this.field_35774_b);
		nBTTagCompound1.setFloat("foodExhaustionLevel", this.field_35775_c);
	}

	public int func_35765_a() {
		return this.field_35776_a;
	}

	public int func_35769_b() {
		return this.field_35773_e;
	}

	public boolean func_35770_c() {
		return this.field_35776_a < 20;
	}

	public void func_35762_a(float f1) {
		this.field_35775_c = Math.min(this.field_35775_c + f1, 40.0F);
	}

	public float func_35760_d() {
		return this.field_35774_b;
	}

	public void func_35764_a(int i1) {
		this.field_35776_a = i1;
	}

	public void func_35767_b(float f1) {
		this.field_35774_b = f1;
	}
}
