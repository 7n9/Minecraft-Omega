package net.minecraft.src;

public class ModelPig extends ModelQuadruped {
	public ModelRenderer field_35401_a;

	public ModelPig() {
		this(0.0F);
	}

	public ModelPig(float f1) {
		super(6, f1);
		this.field_35401_a = new ModelRenderer(this, 16, 16);
		this.field_35401_a.addBox(-2.0F, 0.0F, -9.0F, 4, 3, 1, f1);
		this.field_35401_a.setRotationPoint(0.0F, 12.0F, -6.0F);
	}

	public void setRotationAngles(float f1, float f2, float f3, float f4, float f5, float f6) {
		super.setRotationAngles(f1, f2, f3, f4, f5, f6);
		this.field_35401_a.func_35969_a(this.head);
	}

	public void render(Entity entity1, float f2, float f3, float f4, float f5, float f6, float f7) {
		super.render(entity1, f2, f3, f4, f5, f6, f7);
		this.field_35401_a.render(f7);
	}
}
