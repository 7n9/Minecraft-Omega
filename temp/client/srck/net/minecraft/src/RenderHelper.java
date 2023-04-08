package net.minecraft.src;

import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL11;

public class RenderHelper {
	private static FloatBuffer field_1695_a = GLAllocation.createDirectFloatBuffer(16);

	public static void disableStandardItemLighting() {
		GL11.glDisable(2896);
		GL11.glDisable(16384);
		GL11.glDisable(16385);
		GL11.glDisable(2903);
	}

	public static void enableStandardItemLighting() {
		GL11.glEnable(2896);
		GL11.glEnable(16384);
		GL11.glEnable(16385);
		GL11.glEnable(2903);
		GL11.glColorMaterial(1032, 5634);
		float f0 = 0.4F;
		float f1 = 0.6F;
		float f2 = 0.0F;
		Vec3D vec3D3 = Vec3D.createVector(0.20000000298023224D, 1.0D, -0.699999988079071D).normalize();
		GL11.glLight(16384, 4611, func_1157_a(vec3D3.xCoord, vec3D3.yCoord, vec3D3.zCoord, 0.0D));
		GL11.glLight(16384, 4609, func_1156_a(f1, f1, f1, 1.0F));
		GL11.glLight(16384, 4608, func_1156_a(0.0F, 0.0F, 0.0F, 1.0F));
		GL11.glLight(16384, 4610, func_1156_a(f2, f2, f2, 1.0F));
		vec3D3 = Vec3D.createVector(-0.20000000298023224D, 1.0D, 0.699999988079071D).normalize();
		GL11.glLight(16385, 4611, func_1157_a(vec3D3.xCoord, vec3D3.yCoord, vec3D3.zCoord, 0.0D));
		GL11.glLight(16385, 4609, func_1156_a(f1, f1, f1, 1.0F));
		GL11.glLight(16385, 4608, func_1156_a(0.0F, 0.0F, 0.0F, 1.0F));
		GL11.glLight(16385, 4610, func_1156_a(f2, f2, f2, 1.0F));
		GL11.glShadeModel(7424);
		GL11.glLightModel(2899, func_1156_a(f0, f0, f0, 1.0F));
	}

	private static FloatBuffer func_1157_a(double d0, double d2, double d4, double d6) {
		return func_1156_a((float)d0, (float)d2, (float)d4, (float)d6);
	}

	private static FloatBuffer func_1156_a(float f0, float f1, float f2, float f3) {
		field_1695_a.clear();
		field_1695_a.put(f0).put(f1).put(f2).put(f3);
		field_1695_a.flip();
		return field_1695_a;
	}
}
