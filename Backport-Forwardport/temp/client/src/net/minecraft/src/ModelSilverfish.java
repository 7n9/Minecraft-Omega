package net.minecraft.src;

public class ModelSilverfish extends ModelBase {
	private ModelRenderer[] field_35400_a = new ModelRenderer[7];
	private ModelRenderer[] field_35398_b;
	private float[] field_35399_c = new float[7];
	private static final int[][] field_35396_d = new int[][]{{3, 2, 2}, {4, 3, 2}, {6, 4, 3}, {3, 3, 3}, {2, 2, 3}, {2, 1, 2}, {1, 1, 2}};
	private static final int[][] field_35397_e = new int[][]{{0, 0}, {0, 4}, {0, 9}, {0, 16}, {0, 22}, {11, 0}, {13, 4}};

	public ModelSilverfish() {
		float f1 = -3.5F;

		for(int i2 = 0; i2 < this.field_35400_a.length; ++i2) {
			this.field_35400_a[i2] = new ModelRenderer(this, field_35397_e[i2][0], field_35397_e[i2][1]);
			this.field_35400_a[i2].addBox((float)field_35396_d[i2][0] * -0.5F, 0.0F, (float)field_35396_d[i2][2] * -0.5F, field_35396_d[i2][0], field_35396_d[i2][1], field_35396_d[i2][2]);
			this.field_35400_a[i2].setRotationPoint(0.0F, (float)(24 - field_35396_d[i2][1]), f1);
			this.field_35399_c[i2] = f1;
			if(i2 < this.field_35400_a.length - 1) {
				f1 += (float)(field_35396_d[i2][2] + field_35396_d[i2 + 1][2]) * 0.5F;
			}
		}

		this.field_35398_b = new ModelRenderer[3];
		this.field_35398_b[0] = new ModelRenderer(this, 20, 0);
		this.field_35398_b[0].addBox(-5.0F, 0.0F, (float)field_35396_d[2][2] * -0.5F, 10, 8, field_35396_d[2][2]);
		this.field_35398_b[0].setRotationPoint(0.0F, 16.0F, this.field_35399_c[2]);
		this.field_35398_b[1] = new ModelRenderer(this, 20, 11);
		this.field_35398_b[1].addBox(-3.0F, 0.0F, (float)field_35396_d[4][2] * -0.5F, 6, 4, field_35396_d[4][2]);
		this.field_35398_b[1].setRotationPoint(0.0F, 20.0F, this.field_35399_c[4]);
		this.field_35398_b[2] = new ModelRenderer(this, 20, 18);
		this.field_35398_b[2].addBox(-3.0F, 0.0F, (float)field_35396_d[4][2] * -0.5F, 6, 5, field_35396_d[1][2]);
		this.field_35398_b[2].setRotationPoint(0.0F, 19.0F, this.field_35399_c[1]);
	}

	public int func_35395_a() {
		return 38;
	}

	public void render(Entity entity1, float f2, float f3, float f4, float f5, float f6, float f7) {
		this.setRotationAngles(f2, f3, f4, f5, f6, f7);

		int i8;
		for(i8 = 0; i8 < this.field_35400_a.length; ++i8) {
			this.field_35400_a[i8].render(f7);
		}

		for(i8 = 0; i8 < this.field_35398_b.length; ++i8) {
			this.field_35398_b[i8].render(f7);
		}

	}

	public void setRotationAngles(float f1, float f2, float f3, float f4, float f5, float f6) {
		for(int i7 = 0; i7 < this.field_35400_a.length; ++i7) {
			this.field_35400_a[i7].rotateAngleY = MathHelper.cos(f3 * 0.9F + (float)i7 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (float)(1 + Math.abs(i7 - 2));
			this.field_35400_a[i7].rotationPointX = MathHelper.sin(f3 * 0.9F + (float)i7 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.2F * (float)Math.abs(i7 - 2);
		}

		this.field_35398_b[0].rotateAngleY = this.field_35400_a[2].rotateAngleY;
		this.field_35398_b[1].rotateAngleY = this.field_35400_a[4].rotateAngleY;
		this.field_35398_b[1].rotationPointX = this.field_35400_a[4].rotationPointX;
		this.field_35398_b[2].rotateAngleY = this.field_35400_a[1].rotateAngleY;
		this.field_35398_b[2].rotationPointX = this.field_35400_a[1].rotationPointX;
	}
}
