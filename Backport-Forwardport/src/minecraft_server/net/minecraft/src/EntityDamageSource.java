package net.minecraft.src;

public class EntityDamageSource extends DamageSource {
	private Entity field_35101_n;

	public EntityDamageSource(String string1, Entity entity2) {
		super(string1);
		this.field_35101_n = entity2;
	}

	public Entity func_35080_a() {
		return this.field_35101_n;
	}

	public String func_35075_a(EntityPlayer entityPlayer1) {
		return StatCollector.translateToLocalFormatted("death." + this.field_35095_m, new Object[]{entityPlayer1.username, this.field_35101_n.func_35150_Y()});
	}
}
