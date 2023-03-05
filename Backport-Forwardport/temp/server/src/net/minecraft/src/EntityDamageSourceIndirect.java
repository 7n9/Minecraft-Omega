package net.minecraft.src;

public class EntityDamageSourceIndirect extends EntityDamageSource {
	private Entity field_35102_n;

	public EntityDamageSourceIndirect(String string1, Entity entity2, Entity entity3) {
		super(string1, entity2);
		this.field_35102_n = entity3;
	}

	public Entity func_35080_a() {
		return this.field_35102_n;
	}

	public String func_35075_a(EntityPlayer entityPlayer1) {
		return StatCollector.translateToLocalFormatted("death." + this.field_35095_m, new Object[]{entityPlayer1.username, this.field_35102_n.func_35150_Y()});
	}
}
