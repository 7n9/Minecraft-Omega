package net.minecraft.src;

public class PotionEffect {
	private int field_35805_a;
	private int field_35803_b;
	private int field_35804_c;

	public PotionEffect(int i1, int i2, int i3) {
		this.field_35805_a = i1;
		this.field_35803_b = i2;
		this.field_35804_c = i3;
	}

	public void func_35796_a(PotionEffect potionEffect1) {
		if(this.field_35805_a != potionEffect1.field_35805_a) {
			System.err.println("This method should only be called for matching effects!");
		}

		if(potionEffect1.field_35804_c >= this.field_35804_c) {
			this.field_35804_c = potionEffect1.field_35804_c;
			this.field_35803_b = potionEffect1.field_35803_b;
		}

	}

	public int func_35799_a() {
		return this.field_35805_a;
	}

	public int func_35802_b() {
		return this.field_35803_b;
	}

	public int func_35801_c() {
		return this.field_35804_c;
	}

	public boolean func_35798_a(EntityLiving entityLiving1) {
		if(this.field_35803_b > 0) {
			if(Potion.field_35678_a[this.field_35805_a].func_35660_a(this.field_35803_b, this.field_35804_c)) {
				this.func_35800_b(entityLiving1);
			}

			this.func_35797_d();
		}

		return this.field_35803_b > 0;
	}

	private int func_35797_d() {
		return --this.field_35803_b;
	}

	public void func_35800_b(EntityLiving entityLiving1) {
		if(this.field_35803_b > 0) {
			Potion.field_35678_a[this.field_35805_a].func_35662_a(entityLiving1, this.field_35804_c);
		}

	}

	public int hashCode() {
		return this.field_35805_a;
	}
}
