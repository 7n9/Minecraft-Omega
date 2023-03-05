package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class ModelBase {
	public float onGround;
	public boolean isRiding = false;
	public List field_35394_j = new ArrayList();

	public void render(Entity entity1, float f2, float f3, float f4, float f5, float f6, float f7) {
	}

	public void setRotationAngles(float f1, float f2, float f3, float f4, float f5, float f6) {
	}

	public void setLivingAnimations(EntityLiving entityLiving1, float f2, float f3, float f4) {
	}

	public ModelRenderer func_35393_a(Random random1) {
		return (ModelRenderer)this.field_35394_j.get(random1.nextInt(this.field_35394_j.size()));
	}
}
