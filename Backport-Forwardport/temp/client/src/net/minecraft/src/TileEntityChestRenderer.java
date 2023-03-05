package net.minecraft.src;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class TileEntityChestRenderer extends TileEntitySpecialRenderer {
	private ModelChest field_35377_b = new ModelChest();
	private ModelChest field_35378_c = new ModelLargeChest();

	public void func_35376_a(TileEntityChest tileEntityChest1, double d2, double d4, double d6, float f8) {
		int i9;
		if(tileEntityChest1.worldObj == null) {
			i9 = 0;
		} else {
			Block block10 = tileEntityChest1.getBlockType();
			i9 = tileEntityChest1.getBlockMetadata();
			if(block10 != null && i9 == 0) {
				((BlockChest)block10).func_35306_h(tileEntityChest1.worldObj, tileEntityChest1.xCoord, tileEntityChest1.yCoord, tileEntityChest1.zCoord);
				i9 = tileEntityChest1.getBlockMetadata();
			}

			tileEntityChest1.func_35147_g();
		}

		if(tileEntityChest1.field_35152_b == null && tileEntityChest1.field_35150_d == null) {
			ModelChest modelChest14;
			if(tileEntityChest1.field_35153_c == null && tileEntityChest1.field_35151_e == null) {
				modelChest14 = this.field_35377_b;
				this.bindTextureByName("/item/chest.png");
			} else {
				modelChest14 = this.field_35378_c;
				this.bindTextureByName("/item/largechest.png");
			}

			GL11.glPushMatrix();
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glTranslatef((float)d2, (float)d4 + 1.0F, (float)d6 + 1.0F);
			GL11.glScalef(1.0F, -1.0F, -1.0F);
			GL11.glTranslatef(0.5F, 0.5F, 0.5F);
			short s11 = 0;
			if(i9 == 2) {
				s11 = 180;
			}

			if(i9 == 3) {
				s11 = 0;
			}

			if(i9 == 4) {
				s11 = 90;
			}

			if(i9 == 5) {
				s11 = -90;
			}

			if(i9 == 2 && tileEntityChest1.field_35153_c != null) {
				GL11.glTranslatef(1.0F, 0.0F, 0.0F);
			}

			if(i9 == 5 && tileEntityChest1.field_35151_e != null) {
				GL11.glTranslatef(0.0F, 0.0F, -1.0F);
			}

			GL11.glRotatef((float)s11, 0.0F, 1.0F, 0.0F);
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
			float f12 = tileEntityChest1.field_35149_g + (tileEntityChest1.field_35148_f - tileEntityChest1.field_35149_g) * f8;
			float f13;
			if(tileEntityChest1.field_35152_b != null) {
				f13 = tileEntityChest1.field_35152_b.field_35149_g + (tileEntityChest1.field_35152_b.field_35148_f - tileEntityChest1.field_35152_b.field_35149_g) * f8;
				if(f13 > f12) {
					f12 = f13;
				}
			}

			if(tileEntityChest1.field_35150_d != null) {
				f13 = tileEntityChest1.field_35150_d.field_35149_g + (tileEntityChest1.field_35150_d.field_35148_f - tileEntityChest1.field_35150_d.field_35149_g) * f8;
				if(f13 > f12) {
					f12 = f13;
				}
			}

			f12 = 1.0F - f12;
			f12 = 1.0F - f12 * f12 * f12;
			modelChest14.field_35405_a.rotateAngleX = -(f12 * (float)Math.PI / 2.0F);
			modelChest14.func_35402_a();
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glPopMatrix();
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		}
	}

	public void renderTileEntityAt(TileEntity tileEntity1, double d2, double d4, double d6, float f8) {
		this.func_35376_a((TileEntityChest)tileEntity1, d2, d4, d6, f8);
	}
}
