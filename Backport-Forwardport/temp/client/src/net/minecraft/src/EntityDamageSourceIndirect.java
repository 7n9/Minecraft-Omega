package net.minecraft.src;

public class EntityDamageSourceIndirect extends EntityDamageSource {
	private Entity field_35553_n;

	public EntityDamageSourceIndirect(String string1, Entity entity2, Entity entity3) {
		super(string1, entity2);
		this.field_35553_n = entity3;
	}

	public Entity func_35532_a() {
		return this.field_35553_n;
	}
}
