package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class ModelWolf extends ModelBase {
	public ModelRenderer wolfHeadMain;
	public ModelRenderer wolfBody;
	public ModelRenderer wolfLeg1;
	public ModelRenderer wolfLeg2;
	public ModelRenderer wolfLeg3;
	public ModelRenderer wolfLeg4;
	ModelRenderer wolfRightEar;
	ModelRenderer wolfLeftEar;
	ModelRenderer wolfSnout;
	ModelRenderer wolfTail;
	ModelRenderer wolfMane;

	public ModelWolf() {
		float f1 = 0.0F;
		float f2 = 13.5F;
		this.wolfHeadMain = new ModelRenderer(0, 0);
		this.wolfHeadMain.addBox(-3.0F, -3.0F, -2.0F, 6, 6, 4, f1);
		this.wolfHeadMain.setRotationPoint(-1.0F, f2, -7.0F);
		this.wolfBody = new ModelRenderer(18, 14);
		this.wolfBody.addBox(-4.0F, -2.0F, -3.0F, 6, 9, 6, f1);
		this.wolfBody.setRotationPoint(0.0F, 14.0F, 2.0F);
		this.wolfMane = new ModelRenderer(21, 0);
		this.wolfMane.addBox(-4.0F, -3.0F, -3.0F, 8, 6, 7, f1);
		this.wolfMane.setRotationPoint(-1.0F, 14.0F, 2.0F);
		this.wolfLeg1 = new ModelRenderer(0, 18);
		this.wolfLeg1.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, f1);
		this.wolfLeg1.setRotationPoint(-2.5F, 16.0F, 7.0F);
		this.wolfLeg2 = new ModelRenderer(0, 18);
		this.wolfLeg2.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, f1);
		this.wolfLeg2.setRotationPoint(0.5F, 16.0F, 7.0F);
		this.wolfLeg3 = new ModelRenderer(0, 18);
		this.wolfLeg3.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, f1);
		this.wolfLeg3.setRotationPoint(-2.5F, 16.0F, -4.0F);
		this.wolfLeg4 = new ModelRenderer(0, 18);
		this.wolfLeg4.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, f1);
		this.wolfLeg4.setRotationPoint(0.5F, 16.0F, -4.0F);
		this.wolfTail = new ModelRenderer(9, 18);
		this.wolfTail.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, f1);
		this.wolfTail.setRotationPoint(-1.0F, 12.0F, 8.0F);
		this.wolfRightEar = new ModelRenderer(16, 14);
		this.wolfRightEar.addBox(-3.0F, -5.0F, 0.0F, 2, 2, 1, f1);
		this.wolfRightEar.setRotationPoint(-1.0F, f2, -7.0F);
		this.wolfLeftEar = new ModelRenderer(16, 14);
		this.wolfLeftEar.addBox(1.0F, -5.0F, 0.0F, 2, 2, 1, f1);
		this.wolfLeftEar.setRotationPoint(-1.0F, f2, -7.0F);
		this.wolfSnout = new ModelRenderer(0, 10);
		this.wolfSnout.addBox(-2.0F, 0.0F, -5.0F, 3, 3, 4, f1);
		this.wolfSnout.setRotationPoint(-0.5F, f2, -7.0F);
	}

	public void render(float f1, float f2, float f3, float f4, float f5, float f6) {
		super.render(f1, f2, f3, f4, f5, f6);
		this.setRotationAngles(f1, f2, f3, f4, f5, f6);
		this.wolfHeadMain.renderWithRotation(f6);
		this.wolfBody.render(f6);
		this.wolfLeg1.render(f6);
		this.wolfLeg2.render(f6);
		this.wolfLeg3.render(f6);
		this.wolfLeg4.render(f6);
		this.wolfRightEar.renderWithRotation(f6);
		this.wolfLeftEar.renderWithRotation(f6);
		this.wolfSnout.renderWithRotation(f6);
		this.wolfTail.renderWithRotation(f6);
		this.wolfMane.render(f6);
	}

	public void setLivingAnimations(EntityLiving entityLiving1, float f2, float f3, float f4) {
		EntityWolf entityWolf5 = (EntityWolf)entityLiving1;
		if(entityWolf5.isWolfAngry()) {
			this.wolfTail.rotateAngleY = 0.0F;
		} else {
			this.wolfTail.rotateAngleY = MathHelper.cos(f2 * 0.6662F) * 1.4F * f3;
		}

		if(entityWolf5.isWolfSitting()) {
			this.wolfMane.setRotationPoint(-1.0F, 16.0F, -3.0F);
			this.wolfMane.rotateAngleX = 1.2566371F;
			this.wolfMane.rotateAngleY = 0.0F;
			this.wolfBody.setRotationPoint(0.0F, 18.0F, 0.0F);
			this.wolfBody.rotateAngleX = 0.7853982F;
			this.wolfTail.setRotationPoint(-1.0F, 21.0F, 6.0F);
			this.wolfLeg1.setRotationPoint(-2.5F, 22.0F, 2.0F);
			this.wolfLeg1.rotateAngleX = 4.712389F;
			this.wolfLeg2.setRotationPoint(0.5F, 22.0F, 2.0F);
			this.wolfLeg2.rotateAngleX = 4.712389F;
			this.wolfLeg3.rotateAngleX = 5.811947F;
			this.wolfLeg3.setRotationPoint(-2.49F, 17.0F, -4.0F);
			this.wolfLeg4.rotateAngleX = 5.811947F;
			this.wolfLeg4.setRotationPoint(0.51F, 17.0F, -4.0F);
		} else {
			this.wolfBody.setRotationPoint(0.0F, 14.0F, 2.0F);
			this.wolfBody.rotateAngleX = 1.5707964F;
			this.wolfMane.setRotationPoint(-1.0F, 14.0F, -3.0F);
			this.wolfMane.rotateAngleX = this.wolfBody.rotateAngleX;
			this.wolfTail.setRotationPoint(-1.0F, 12.0F, 8.0F);
			this.wolfLeg1.setRotationPoint(-2.5F, 16.0F, 7.0F);
			this.wolfLeg2.setRotationPoint(0.5F, 16.0F, 7.0F);
			this.wolfLeg3.setRotationPoint(-2.5F, 16.0F, -4.0F);
			this.wolfLeg4.setRotationPoint(0.5F, 16.0F, -4.0F);
			this.wolfLeg1.rotateAngleX = MathHelper.cos(f2 * 0.6662F) * 1.4F * f3;
			this.wolfLeg2.rotateAngleX = MathHelper.cos(f2 * 0.6662F + 3.1415927F) * 1.4F * f3;
			this.wolfLeg3.rotateAngleX = MathHelper.cos(f2 * 0.6662F + 3.1415927F) * 1.4F * f3;
			this.wolfLeg4.rotateAngleX = MathHelper.cos(f2 * 0.6662F) * 1.4F * f3;
		}

		float f6 = entityWolf5.getInterestedAngle(f4) + entityWolf5.getShakeAngle(f4, 0.0F);
		this.wolfHeadMain.rotateAngleZ = f6;
		this.wolfRightEar.rotateAngleZ = f6;
		this.wolfLeftEar.rotateAngleZ = f6;
		this.wolfSnout.rotateAngleZ = f6;
		this.wolfMane.rotateAngleZ = entityWolf5.getShakeAngle(f4, -0.08F);
		this.wolfBody.rotateAngleZ = entityWolf5.getShakeAngle(f4, -0.16F);
		this.wolfTail.rotateAngleZ = entityWolf5.getShakeAngle(f4, -0.2F);
		if(entityWolf5.getWolfShaking()) {
			float f7 = entityWolf5.getEntityBrightness(f4) * entityWolf5.getShadingWhileShaking(f4);
			GL11.glColor3f(f7, f7, f7);
		}

	}

	public void setRotationAngles(float f1, float f2, float f3, float f4, float f5, float f6) {
		super.setRotationAngles(f1, f2, f3, f4, f5, f6);
		this.wolfHeadMain.rotateAngleX = f5 / 57.295776F;
		this.wolfHeadMain.rotateAngleY = f4 / 57.295776F;
		this.wolfRightEar.rotateAngleY = this.wolfHeadMain.rotateAngleY;
		this.wolfRightEar.rotateAngleX = this.wolfHeadMain.rotateAngleX;
		this.wolfLeftEar.rotateAngleY = this.wolfHeadMain.rotateAngleY;
		this.wolfLeftEar.rotateAngleX = this.wolfHeadMain.rotateAngleX;
		this.wolfSnout.rotateAngleY = this.wolfHeadMain.rotateAngleY;
		this.wolfSnout.rotateAngleX = this.wolfHeadMain.rotateAngleX;
		this.wolfTail.rotateAngleX = f3;
	}
}