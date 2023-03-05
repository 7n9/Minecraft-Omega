package net.minecraft.src;

public class RenderSilverfish extends RenderLiving {
	private int field_35450_c;

	public RenderSilverfish() {
		super(new ModelSilverfish(), 0.3F);
	}

	protected float func_35447_a(EntitySilverfish entitySilverfish1) {
		return 180.0F;
	}

	public void func_35448_a(EntitySilverfish entitySilverfish1, double d2, double d4, double d6, float f8, float f9) {
		int i10 = ((ModelSilverfish)this.mainModel).func_35395_a();
		if(i10 != this.field_35450_c) {
			this.field_35450_c = i10;
			this.mainModel = new ModelSilverfish();
			System.out.println("new silverfish model");
		}

		super.doRenderLiving(entitySilverfish1, d2, d4, d6, f8, f9);
	}

	protected boolean func_35449_a(EntitySilverfish entitySilverfish1, int i2, float f3) {
		return false;
	}

	protected float getDeathMaxRotation(EntityLiving entityLiving1) {
		return this.func_35447_a((EntitySilverfish)entityLiving1);
	}

	protected boolean shouldRenderPass(EntityLiving entityLiving1, int i2, float f3) {
		return this.func_35449_a((EntitySilverfish)entityLiving1, i2, f3);
	}

	public void doRenderLiving(EntityLiving entityLiving1, double d2, double d4, double d6, float f8, float f9) {
		this.func_35448_a((EntitySilverfish)entityLiving1, d2, d4, d6, f8, f9);
	}

	public void doRender(Entity entity1, double d2, double d4, double d6, float f8, float f9) {
		this.func_35448_a((EntitySilverfish)entity1, d2, d4, d6, f8, f9);
	}
}
