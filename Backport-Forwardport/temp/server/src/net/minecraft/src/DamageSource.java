package net.minecraft.src;

public class DamageSource {
	public static DamageSource field_35091_a = new DamageSource("inFire");
	public static DamageSource field_35089_b = (new DamageSource("onFire")).func_35078_f();
	public static DamageSource field_35090_c = new DamageSource("lava");
	public static DamageSource field_35087_d = (new DamageSource("inWall")).func_35078_f();
	public static DamageSource field_35088_e = (new DamageSource("drown")).func_35078_f();
	public static DamageSource field_35085_f = (new DamageSource("starve")).func_35078_f();
	public static DamageSource field_35086_g = new DamageSource("cactus");
	public static DamageSource field_35098_h = new DamageSource("fall");
	public static DamageSource field_35099_i = (new DamageSource("outOfWorld")).func_35078_f().func_35084_g();
	public static DamageSource field_35096_j = (new DamageSource("generic")).func_35078_f();
	public static DamageSource field_35097_k = new DamageSource("explosion");
	public static DamageSource field_35094_l = (new DamageSource("magic")).func_35078_f();
	private boolean field_35092_n = false;
	private boolean field_35093_o = false;
	private float field_35100_p = 0.3F;
	public String field_35095_m;

	public static DamageSource func_35072_a(EntityLiving entityLiving0) {
		return new EntityDamageSource("mob", entityLiving0);
	}

	public static DamageSource func_35076_b(EntityPlayer entityPlayer0) {
		return new EntityDamageSource("player", entityPlayer0);
	}

	public static DamageSource func_35073_a(EntityArrow entityArrow0, Entity entity1) {
		return new EntityDamageSourceIndirect("arrow", entityArrow0, entity1);
	}

	public static DamageSource func_35082_a(EntityFireball entityFireball0, Entity entity1) {
		return new EntityDamageSourceIndirect("fireball", entityFireball0, entity1);
	}

	public static DamageSource func_35081_a(Entity entity0, Entity entity1) {
		return new EntityDamageSourceIndirect("thrown", entity0, entity1);
	}

	public boolean func_35083_b() {
		return this.field_35092_n;
	}

	public float func_35074_c() {
		return this.field_35100_p;
	}

	public boolean func_35077_d() {
		return this.field_35093_o;
	}

	protected DamageSource(String string1) {
		this.field_35095_m = string1;
	}

	public Entity func_35079_e() {
		return this.func_35080_a();
	}

	public Entity func_35080_a() {
		return null;
	}

	private DamageSource func_35078_f() {
		this.field_35092_n = true;
		this.field_35100_p = 0.0F;
		return this;
	}

	private DamageSource func_35084_g() {
		this.field_35093_o = true;
		return this;
	}

	public String func_35075_a(EntityPlayer entityPlayer1) {
		return StatCollector.translateToLocalFormatted("death." + this.field_35095_m, new Object[]{entityPlayer1.username});
	}
}
