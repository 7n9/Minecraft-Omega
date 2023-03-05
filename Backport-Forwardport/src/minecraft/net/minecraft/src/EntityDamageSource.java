package net.minecraft.src;

public class EntityDamageSource extends DamageSource {
	private Entity field_35552_n;

	public EntityDamageSource(String string1, Entity entity2) {
		super(string1);
		this.field_35552_n = entity2;
	}

	public Entity func_35532_a() {
		return this.field_35552_n;
	}
}
