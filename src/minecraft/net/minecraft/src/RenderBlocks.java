package net.minecraft.src;

import net.minecraft.client.Minecraft;

import org.lwjgl.opengl.GL11;

public class RenderBlocks {
	private IBlockAccess blockAccess;
	private int overrideBlockTexture = -1;
	private boolean flipTexture = false;
	private boolean renderAllFaces = false;
	public static boolean fancyGrass = true;
	public boolean field_31088_b = true;
	private int field_31087_g = 0;
	private int field_31086_h = 0;
	private int field_31085_i = 0;
	private int field_31084_j = 0;
	private int field_31083_k = 0;
	private int field_31082_l = 0;
	private boolean enableAO;
	private float colorRedTopLeft;
	private float colorRedBottomLeft;
	private float colorRedBottomRight;
	private float colorRedTopRight;
	private float colorGreenTopLeft;
	private float colorGreenBottomLeft;
	private float colorGreenBottomRight;
	private float colorGreenTopRight;
	private float colorBlueTopLeft;
	private float colorBlueBottomLeft;
	private float colorBlueBottomRight;
	private float colorBlueTopRight;

	public RenderBlocks(IBlockAccess iBlockAccess1) {
		this.blockAccess = iBlockAccess1;
	}

	public RenderBlocks() {
	}

	public void renderBlockUsingTexture(Block block1, int i2, int i3, int i4, int i5) {
		this.overrideBlockTexture = i5;
		this.renderBlockByRenderType(block1, i2, i3, i4);
		this.overrideBlockTexture = -1;
	}

	public void func_31075_a(Block block1, int i2, int i3, int i4) {
		this.renderAllFaces = true;
		this.renderBlockByRenderType(block1, i2, i3, i4);
		this.renderAllFaces = false;
	}

	public boolean renderBlockByRenderType(Block block1, int i2, int i3, int i4) {
		int i5 = block1.getRenderType();
		block1.setBlockBoundsBasedOnState(this.blockAccess, i2, i3, i4);
		return i5 == 0 ? this.renderStandardBlock(block1, i2, i3, i4) : (i5 == 4 ? this.renderBlockFluids(block1, i2, i3, i4) : (i5 == 13 ? this.renderBlockCactus(block1, i2, i3, i4) : (i5 == 1 ? this.renderBlockReed(block1, i2, i3, i4) : (i5 == 6 ? this.renderBlockCrops(block1, i2, i3, i4) : (i5 == 2 ? this.renderBlockTorch(block1, i2, i3, i4) : (i5 == 3 ? this.renderBlockFire(block1, i2, i3, i4) : (i5 == 5 ? this.renderBlockRedstoneWire(block1, i2, i3, i4) : (i5 == 8 ? this.renderBlockLadder(block1, i2, i3, i4) : (i5 == 7 ? this.renderBlockDoor(block1, i2, i3, i4) : (i5 == 9 ? this.renderBlockMinecartTrack((BlockRail)block1, i2, i3, i4) : (i5 == 10 ? this.renderBlockStairs(block1, i2, i3, i4) : (i5 == 11 ? this.renderBlockFence(block1, i2, i3, i4) : (i5 == 12 ? this.renderBlockLever(block1, i2, i3, i4) : (i5 == 14 ? this.renderBlockBed(block1, i2, i3, i4) : (i5 == 15 ? this.renderBlockRepeater(block1, i2, i3, i4) : (i5 == 16 ? this.func_31074_b(block1, i2, i3, i4, false) : (i5 == 17 && this.func_31080_c(block1, i2, i3, i4, true))))))))))))))))));
	}

	private boolean renderBlockBed(Block block1, int i2, int i3, int i4) {
		Tessellator tessellator5 = Tessellator.instance;
		int i6 = this.blockAccess.getBlockMetadata(i2, i3, i4);
		int i7 = BlockBed.getDirectionFromMetadata(i6);
		boolean z8 = BlockBed.isBlockFootOfBed(i6);
		float f9 = 0.5F;
		float f10 = 1.0F;
		float f11 = 0.8F;
		float f12 = 0.6F;
		float f25 = block1.getBlockBrightness(this.blockAccess, i2, i3, i4);
		tessellator5.setColorOpaque_F(f9 * f25, f9 * f25, f9 * f25);
		int i26 = block1.getBlockTexture(this.blockAccess, i2, i3, i4, 0);
		int i27 = (i26 & 15) << 4;
		int i28 = i26 & 240;
		double d29 = (float)i27 / 256.0F;
		double d31 = ((double)(i27 + 16) - 0.01D) / 256.0D;
		double d33 = (float)i28 / 256.0F;
		double d35 = ((double)(i28 + 16) - 0.01D) / 256.0D;
		double d37 = (double)i2 + block1.minX;
		double d39 = (double)i2 + block1.maxX;
		double d41 = (double)i3 + block1.minY + 0.1875D;
		double d43 = (double)i4 + block1.minZ;
		double d45 = (double)i4 + block1.maxZ;
		tessellator5.addVertexWithUV(d37, d41, d45, d29, d35);
		tessellator5.addVertexWithUV(d37, d41, d43, d29, d33);
		tessellator5.addVertexWithUV(d39, d41, d43, d31, d33);
		tessellator5.addVertexWithUV(d39, d41, d45, d31, d35);
		float f64 = block1.getBlockBrightness(this.blockAccess, i2, i3 + 1, i4);
		tessellator5.setColorOpaque_F(f10 * f64, f10 * f64, f10 * f64);
		i27 = block1.getBlockTexture(this.blockAccess, i2, i3, i4, 1);
		i28 = (i27 & 15) << 4;
		int i67 = i27 & 240;
		double d30 = (float)i28 / 256.0F;
		double d32 = ((double)(i28 + 16) - 0.01D) / 256.0D;
		double d34 = (float)i67 / 256.0F;
		double d36 = ((double)(i67 + 16) - 0.01D) / 256.0D;
		double d38 = d30;
		double d40 = d32;
		double d42 = d34;
		double d44 = d34;
		double d46 = d30;
		double d48 = d32;
		double d50 = d36;
		double d52 = d36;
		if(i7 == 0) {
			d40 = d30;
			d42 = d36;
			d46 = d32;
			d52 = d34;
		} else if(i7 == 2) {
			d38 = d32;
			d44 = d36;
			d48 = d30;
			d50 = d34;
		} else if(i7 == 3) {
			d38 = d32;
			d44 = d36;
			d48 = d30;
			d50 = d34;
			d40 = d30;
			d42 = d36;
			d46 = d32;
			d52 = d34;
		}

		double d54 = (double)i2 + block1.minX;
		double d56 = (double)i2 + block1.maxX;
		double d58 = (double)i3 + block1.maxY;
		double d60 = (double)i4 + block1.minZ;
		double d62 = (double)i4 + block1.maxZ;
		tessellator5.addVertexWithUV(d56, d58, d62, d46, d50);
		tessellator5.addVertexWithUV(d56, d58, d60, d38, d42);
		tessellator5.addVertexWithUV(d54, d58, d60, d40, d44);
		tessellator5.addVertexWithUV(d54, d58, d62, d48, d52);
		i26 = ModelBed.field_22280_a[i7];
		if(z8) {
			i26 = ModelBed.field_22280_a[ModelBed.field_22279_b[i7]];
		}

		byte b65 = 4;
		switch(i7) {
		case 0:
			b65 = 5;
			break;
		case 1:
			b65 = 3;
		case 2:
		default:
			break;
		case 3:
			b65 = 2;
		}

		float f66;
		if(i26 != 2 && (this.renderAllFaces || block1.shouldSideBeRendered(this.blockAccess, i2, i3, i4 - 1, 2))) {
			f66 = block1.getBlockBrightness(this.blockAccess, i2, i3, i4 - 1);
			if(block1.minZ > 0.0D) {
				f66 = f25;
			}

			tessellator5.setColorOpaque_F(f11 * f66, f11 * f66, f11 * f66);
			this.flipTexture = b65 == 2;
			this.renderEastFace(block1, i2, i3, i4, block1.getBlockTexture(this.blockAccess, i2, i3, i4, 2));
		}

		if(i26 != 3 && (this.renderAllFaces || block1.shouldSideBeRendered(this.blockAccess, i2, i3, i4 + 1, 3))) {
			f66 = block1.getBlockBrightness(this.blockAccess, i2, i3, i4 + 1);
			if(block1.maxZ < 1.0D) {
				f66 = f25;
			}

			tessellator5.setColorOpaque_F(f11 * f66, f11 * f66, f11 * f66);
			this.flipTexture = b65 == 3;
			this.renderWestFace(block1, i2, i3, i4, block1.getBlockTexture(this.blockAccess, i2, i3, i4, 3));
		}

		if(i26 != 4 && (this.renderAllFaces || block1.shouldSideBeRendered(this.blockAccess, i2 - 1, i3, i4, 4))) {
			f66 = block1.getBlockBrightness(this.blockAccess, i2 - 1, i3, i4);
			if(block1.minX > 0.0D) {
				f66 = f25;
			}

			tessellator5.setColorOpaque_F(f12 * f66, f12 * f66, f12 * f66);
			this.flipTexture = b65 == 4;
			this.renderNorthFace(block1, i2, i3, i4, block1.getBlockTexture(this.blockAccess, i2, i3, i4, 4));
		}

		if(i26 != 5 && (this.renderAllFaces || block1.shouldSideBeRendered(this.blockAccess, i2 + 1, i3, i4, 5))) {
			f66 = block1.getBlockBrightness(this.blockAccess, i2 + 1, i3, i4);
			if(block1.maxX < 1.0D) {
				f66 = f25;
			}

			tessellator5.setColorOpaque_F(f12 * f66, f12 * f66, f12 * f66);
			this.flipTexture = b65 == 5;
			this.renderSouthFace(block1, i2, i3, i4, block1.getBlockTexture(this.blockAccess, i2, i3, i4, 5));
		}

		this.flipTexture = false;
		return true;
	}

	public boolean renderBlockTorch(Block block1, int i2, int i3, int i4) {
		int i5 = this.blockAccess.getBlockMetadata(i2, i3, i4);
		Tessellator tessellator6 = Tessellator.instance;
		float f7 = block1.getBlockBrightness(this.blockAccess, i2, i3, i4);
		if(Block.lightValue[block1.blockID] > 0) {
			f7 = 1.0F;
		}

		tessellator6.setColorOpaque_F(f7, f7, f7);
		double d8 = 0.4F;
		double d10 = 0.5D - d8;
		double d12 = 0.2F;
		if(i5 == 1) {
			this.renderTorchAtAngle(block1, (double)i2 - d10, (double)i3 + d12, i4, -d8, 0.0D);
		} else if(i5 == 2) {
			this.renderTorchAtAngle(block1, (double)i2 + d10, (double)i3 + d12, i4, d8, 0.0D);
		} else if(i5 == 3) {
			this.renderTorchAtAngle(block1, i2, (double)i3 + d12, (double)i4 - d10, 0.0D, -d8);
		} else if(i5 == 4) {
			this.renderTorchAtAngle(block1, i2, (double)i3 + d12, (double)i4 + d10, 0.0D, d8);
		} else {
			this.renderTorchAtAngle(block1, i2, i3, i4, 0.0D, 0.0D);
		}

		return true;
	}

	private boolean renderBlockRepeater(Block block1, int i2, int i3, int i4) {
		int i5 = this.blockAccess.getBlockMetadata(i2, i3, i4);
		int i6 = i5 & 3;
		int i7 = (i5 & 12) >> 2;
		this.renderStandardBlock(block1, i2, i3, i4);
		Tessellator tessellator8 = Tessellator.instance;
		float f9 = block1.getBlockBrightness(this.blockAccess, i2, i3, i4);
		if(Block.lightValue[block1.blockID] > 0) {
			f9 = (f9 + 1.0F) * 0.5F;
		}

		tessellator8.setColorOpaque_F(f9, f9, f9);
		double d10 = -0.1875D;
		double d12 = 0.0D;
		double d14 = 0.0D;
		double d16 = 0.0D;
		double d18 = 0.0D;
		switch(i6) {
		case 0:
			d18 = -0.3125D;
			d14 = BlockRedstoneRepeater.field_22024_a[i7];
			break;
		case 1:
			d16 = 0.3125D;
			d12 = -BlockRedstoneRepeater.field_22024_a[i7];
			break;
		case 2:
			d18 = 0.3125D;
			d14 = -BlockRedstoneRepeater.field_22024_a[i7];
			break;
		case 3:
			d16 = -0.3125D;
			d12 = BlockRedstoneRepeater.field_22024_a[i7];
		}

		this.renderTorchAtAngle(block1, (double)i2 + d12, (double)i3 + d10, (double)i4 + d14, 0.0D, 0.0D);
		this.renderTorchAtAngle(block1, (double)i2 + d16, (double)i3 + d10, (double)i4 + d18, 0.0D, 0.0D);
		int i20 = block1.getBlockTextureFromSide(1);
		int i21 = (i20 & 15) << 4;
		int i22 = i20 & 240;
		double d23 = (float)i21 / 256.0F;
		double d25 = ((float)i21 + 15.99F) / 256.0F;
		double d27 = (float)i22 / 256.0F;
		double d29 = ((float)i22 + 15.99F) / 256.0F;
		float f31 = 0.125F;
		float f32 = (float)(i2 + 1);
		float f33 = (float)(i2 + 1);
		float f34 = (float)(i2);
		float f35 = (float)(i2);
		float f36 = (float)(i4);
		float f37 = (float)(i4 + 1);
		float f38 = (float)(i4 + 1);
		float f39 = (float)(i4);
		float f40 = (float)i3 + f31;
		if(i6 == 2) {
			f32 = f33 = (float)(i2);
			f34 = f35 = (float)(i2 + 1);
			f36 = f39 = (float)(i4 + 1);
			f37 = f38 = (float)(i4);
		} else if(i6 == 3) {
			f32 = f35 = (float)(i2);
			f33 = f34 = (float)(i2 + 1);
			f36 = f37 = (float)(i4);
			f38 = f39 = (float)(i4 + 1);
		} else if(i6 == 1) {
			f32 = f35 = (float)(i2 + 1);
			f33 = f34 = (float)(i2);
			f36 = f37 = (float)(i4 + 1);
			f38 = f39 = (float)(i4);
		}

		tessellator8.addVertexWithUV(f35, f40, f39, d23, d27);
		tessellator8.addVertexWithUV(f34, f40, f38, d23, d29);
		tessellator8.addVertexWithUV(f33, f40, f37, d25, d29);
		tessellator8.addVertexWithUV(f32, f40, f36, d25, d27);
		return true;
	}

	public void func_31078_d(Block block1, int i2, int i3, int i4) {
		this.renderAllFaces = true;
		this.func_31074_b(block1, i2, i3, i4, true);
		this.renderAllFaces = false;
	}

	private boolean func_31074_b(Block block1, int i2, int i3, int i4, boolean z5) {
		int i6 = this.blockAccess.getBlockMetadata(i2, i3, i4);
		boolean z7 = z5 || (i6 & 8) != 0;
		int i8 = BlockPistonBase.func_31044_d(i6);
		if(z7) {
			switch(i8) {
			case 0:
				this.field_31087_g = 3;
				this.field_31086_h = 3;
				this.field_31085_i = 3;
				this.field_31084_j = 3;
				block1.setBlockBounds(0.0F, 0.25F, 0.0F, 1.0F, 1.0F, 1.0F);
				break;
			case 1:
				block1.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
				break;
			case 2:
				this.field_31085_i = 1;
				this.field_31084_j = 2;
				block1.setBlockBounds(0.0F, 0.0F, 0.25F, 1.0F, 1.0F, 1.0F);
				break;
			case 3:
				this.field_31085_i = 2;
				this.field_31084_j = 1;
				this.field_31083_k = 3;
				this.field_31082_l = 3;
				block1.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.75F);
				break;
			case 4:
				this.field_31087_g = 1;
				this.field_31086_h = 2;
				this.field_31083_k = 2;
				this.field_31082_l = 1;
				block1.setBlockBounds(0.25F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
				break;
			case 5:
				this.field_31087_g = 2;
				this.field_31086_h = 1;
				this.field_31083_k = 1;
				this.field_31082_l = 2;
				block1.setBlockBounds(0.0F, 0.0F, 0.0F, 0.75F, 1.0F, 1.0F);
			}

			this.renderStandardBlock(block1, i2, i3, i4);
			this.field_31087_g = 0;
			this.field_31086_h = 0;
			this.field_31085_i = 0;
			this.field_31084_j = 0;
			this.field_31083_k = 0;
			this.field_31082_l = 0;
			block1.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		} else {
			switch(i8) {
			case 0:
				this.field_31087_g = 3;
				this.field_31086_h = 3;
				this.field_31085_i = 3;
				this.field_31084_j = 3;
			case 1:
			default:
				break;
			case 2:
				this.field_31085_i = 1;
				this.field_31084_j = 2;
				break;
			case 3:
				this.field_31085_i = 2;
				this.field_31084_j = 1;
				this.field_31083_k = 3;
				this.field_31082_l = 3;
				break;
			case 4:
				this.field_31087_g = 1;
				this.field_31086_h = 2;
				this.field_31083_k = 2;
				this.field_31082_l = 1;
				break;
			case 5:
				this.field_31087_g = 2;
				this.field_31086_h = 1;
				this.field_31083_k = 1;
				this.field_31082_l = 2;
			}

			this.renderStandardBlock(block1, i2, i3, i4);
			this.field_31087_g = 0;
			this.field_31086_h = 0;
			this.field_31085_i = 0;
			this.field_31084_j = 0;
			this.field_31083_k = 0;
			this.field_31082_l = 0;
		}

		return true;
	}

	private void func_31076_a(double d1, double d3, double d5, double d7, double d9, double d11, float f13, double d14) {
		int i16 = 108;
		if(this.overrideBlockTexture >= 0) {
			i16 = this.overrideBlockTexture;
		}

		int i17 = (i16 & 15) << 4;
		int i18 = i16 & 240;
		Tessellator tessellator19 = Tessellator.instance;
		double d20 = (float)(i17) / 256.0F;
		double d22 = (float)(i18) / 256.0F;
		double d24 = ((double)i17 + d14 - 0.01D) / 256.0D;
		double d26 = ((double)((float)i18 + 4.0F) - 0.01D) / 256.0D;
		tessellator19.setColorOpaque_F(f13, f13, f13);
		tessellator19.addVertexWithUV(d1, d7, d9, d24, d22);
		tessellator19.addVertexWithUV(d1, d5, d9, d20, d22);
		tessellator19.addVertexWithUV(d3, d5, d11, d20, d26);
		tessellator19.addVertexWithUV(d3, d7, d11, d24, d26);
	}

	private void func_31081_b(double d1, double d3, double d5, double d7, double d9, double d11, float f13, double d14) {
		int i16 = 108;
		if(this.overrideBlockTexture >= 0) {
			i16 = this.overrideBlockTexture;
		}

		int i17 = (i16 & 15) << 4;
		int i18 = i16 & 240;
		Tessellator tessellator19 = Tessellator.instance;
		double d20 = (float)(i17) / 256.0F;
		double d22 = (float)(i18) / 256.0F;
		double d24 = ((double)i17 + d14 - 0.01D) / 256.0D;
		double d26 = ((double)((float)i18 + 4.0F) - 0.01D) / 256.0D;
		tessellator19.setColorOpaque_F(f13, f13, f13);
		tessellator19.addVertexWithUV(d1, d5, d11, d24, d22);
		tessellator19.addVertexWithUV(d1, d5, d9, d20, d22);
		tessellator19.addVertexWithUV(d3, d7, d9, d20, d26);
		tessellator19.addVertexWithUV(d3, d7, d11, d24, d26);
	}

	private void func_31077_c(double d1, double d3, double d5, double d7, double d9, double d11, float f13, double d14) {
		int i16 = 108;
		if(this.overrideBlockTexture >= 0) {
			i16 = this.overrideBlockTexture;
		}

		int i17 = (i16 & 15) << 4;
		int i18 = i16 & 240;
		Tessellator tessellator19 = Tessellator.instance;
		double d20 = (float)(i17) / 256.0F;
		double d22 = (float)(i18) / 256.0F;
		double d24 = ((double)i17 + d14 - 0.01D) / 256.0D;
		double d26 = ((double)((float)i18 + 4.0F) - 0.01D) / 256.0D;
		tessellator19.setColorOpaque_F(f13, f13, f13);
		tessellator19.addVertexWithUV(d3, d5, d9, d24, d22);
		tessellator19.addVertexWithUV(d1, d5, d9, d20, d22);
		tessellator19.addVertexWithUV(d1, d7, d11, d20, d26);
		tessellator19.addVertexWithUV(d3, d7, d11, d24, d26);
	}

	public void func_31079_a(Block block1, int i2, int i3, int i4, boolean z5) {
		this.renderAllFaces = true;
		this.func_31080_c(block1, i2, i3, i4, z5);
		this.renderAllFaces = false;
	}

	private boolean func_31080_c(Block block1, int i2, int i3, int i4, boolean z5) {
		int i6 = this.blockAccess.getBlockMetadata(i2, i3, i4);
		int i7 = BlockPistonExtension.func_31050_c(i6);
		float f11 = block1.getBlockBrightness(this.blockAccess, i2, i3, i4);
		float f12 = z5 ? 1.0F : 0.5F;
		double d13 = z5 ? 16.0D : 8.0D;
		switch(i7) {
		case 0:
			this.field_31087_g = 3;
			this.field_31086_h = 3;
			this.field_31085_i = 3;
			this.field_31084_j = 3;
			block1.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);
			this.renderStandardBlock(block1, i2, i3, i4);
			this.func_31076_a((float)i2 + 0.375F, (float)i2 + 0.625F, (float)i3 + 0.25F, (float)i3 + 0.25F + f12, (float)i4 + 0.625F, (float)i4 + 0.625F, f11 * 0.8F, d13);
			this.func_31076_a((float)i2 + 0.625F, (float)i2 + 0.375F, (float)i3 + 0.25F, (float)i3 + 0.25F + f12, (float)i4 + 0.375F, (float)i4 + 0.375F, f11 * 0.8F, d13);
			this.func_31076_a((float)i2 + 0.375F, (float)i2 + 0.375F, (float)i3 + 0.25F, (float)i3 + 0.25F + f12, (float)i4 + 0.375F, (float)i4 + 0.625F, f11 * 0.6F, d13);
			this.func_31076_a((float)i2 + 0.625F, (float)i2 + 0.625F, (float)i3 + 0.25F, (float)i3 + 0.25F + f12, (float)i4 + 0.625F, (float)i4 + 0.375F, f11 * 0.6F, d13);
			break;
		case 1:
			block1.setBlockBounds(0.0F, 0.75F, 0.0F, 1.0F, 1.0F, 1.0F);
			this.renderStandardBlock(block1, i2, i3, i4);
			this.func_31076_a((float)i2 + 0.375F, (float)i2 + 0.625F, (float)i3 - 0.25F + 1.0F - f12, (float)i3 - 0.25F + 1.0F, (float)i4 + 0.625F, (float)i4 + 0.625F, f11 * 0.8F, d13);
			this.func_31076_a((float)i2 + 0.625F, (float)i2 + 0.375F, (float)i3 - 0.25F + 1.0F - f12, (float)i3 - 0.25F + 1.0F, (float)i4 + 0.375F, (float)i4 + 0.375F, f11 * 0.8F, d13);
			this.func_31076_a((float)i2 + 0.375F, (float)i2 + 0.375F, (float)i3 - 0.25F + 1.0F - f12, (float)i3 - 0.25F + 1.0F, (float)i4 + 0.375F, (float)i4 + 0.625F, f11 * 0.6F, d13);
			this.func_31076_a((float)i2 + 0.625F, (float)i2 + 0.625F, (float)i3 - 0.25F + 1.0F - f12, (float)i3 - 0.25F + 1.0F, (float)i4 + 0.625F, (float)i4 + 0.375F, f11 * 0.6F, d13);
			break;
		case 2:
			this.field_31085_i = 1;
			this.field_31084_j = 2;
			block1.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.25F);
			this.renderStandardBlock(block1, i2, i3, i4);
			this.func_31081_b((float)i2 + 0.375F, (float)i2 + 0.375F, (float)i3 + 0.625F, (float)i3 + 0.375F, (float)i4 + 0.25F, (float)i4 + 0.25F + f12, f11 * 0.6F, d13);
			this.func_31081_b((float)i2 + 0.625F, (float)i2 + 0.625F, (float)i3 + 0.375F, (float)i3 + 0.625F, (float)i4 + 0.25F, (float)i4 + 0.25F + f12, f11 * 0.6F, d13);
			this.func_31081_b((float)i2 + 0.375F, (float)i2 + 0.625F, (float)i3 + 0.375F, (float)i3 + 0.375F, (float)i4 + 0.25F, (float)i4 + 0.25F + f12, f11 * 0.5F, d13);
			this.func_31081_b((float)i2 + 0.625F, (float)i2 + 0.375F, (float)i3 + 0.625F, (float)i3 + 0.625F, (float)i4 + 0.25F, (float)i4 + 0.25F + f12, f11, d13);
			break;
		case 3:
			this.field_31085_i = 2;
			this.field_31084_j = 1;
			this.field_31083_k = 3;
			this.field_31082_l = 3;
			block1.setBlockBounds(0.0F, 0.0F, 0.75F, 1.0F, 1.0F, 1.0F);
			this.renderStandardBlock(block1, i2, i3, i4);
			this.func_31081_b((float)i2 + 0.375F, (float)i2 + 0.375F, (float)i3 + 0.625F, (float)i3 + 0.375F, (float)i4 - 0.25F + 1.0F - f12, (float)i4 - 0.25F + 1.0F, f11 * 0.6F, d13);
			this.func_31081_b((float)i2 + 0.625F, (float)i2 + 0.625F, (float)i3 + 0.375F, (float)i3 + 0.625F, (float)i4 - 0.25F + 1.0F - f12, (float)i4 - 0.25F + 1.0F, f11 * 0.6F, d13);
			this.func_31081_b((float)i2 + 0.375F, (float)i2 + 0.625F, (float)i3 + 0.375F, (float)i3 + 0.375F, (float)i4 - 0.25F + 1.0F - f12, (float)i4 - 0.25F + 1.0F, f11 * 0.5F, d13);
			this.func_31081_b((float)i2 + 0.625F, (float)i2 + 0.375F, (float)i3 + 0.625F, (float)i3 + 0.625F, (float)i4 - 0.25F + 1.0F - f12, (float)i4 - 0.25F + 1.0F, f11, d13);
			break;
		case 4:
			this.field_31087_g = 1;
			this.field_31086_h = 2;
			this.field_31083_k = 2;
			this.field_31082_l = 1;
			block1.setBlockBounds(0.0F, 0.0F, 0.0F, 0.25F, 1.0F, 1.0F);
			this.renderStandardBlock(block1, i2, i3, i4);
			this.func_31077_c((float)i2 + 0.25F, (float)i2 + 0.25F + f12, (float)i3 + 0.375F, (float)i3 + 0.375F, (float)i4 + 0.625F, (float)i4 + 0.375F, f11 * 0.5F, d13);
			this.func_31077_c((float)i2 + 0.25F, (float)i2 + 0.25F + f12, (float)i3 + 0.625F, (float)i3 + 0.625F, (float)i4 + 0.375F, (float)i4 + 0.625F, f11, d13);
			this.func_31077_c((float)i2 + 0.25F, (float)i2 + 0.25F + f12, (float)i3 + 0.375F, (float)i3 + 0.625F, (float)i4 + 0.375F, (float)i4 + 0.375F, f11 * 0.6F, d13);
			this.func_31077_c((float)i2 + 0.25F, (float)i2 + 0.25F + f12, (float)i3 + 0.625F, (float)i3 + 0.375F, (float)i4 + 0.625F, (float)i4 + 0.625F, f11 * 0.6F, d13);
			break;
		case 5:
			this.field_31087_g = 2;
			this.field_31086_h = 1;
			this.field_31083_k = 1;
			this.field_31082_l = 2;
			block1.setBlockBounds(0.75F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
			this.renderStandardBlock(block1, i2, i3, i4);
			this.func_31077_c((float)i2 - 0.25F + 1.0F - f12, (float)i2 - 0.25F + 1.0F, (float)i3 + 0.375F, (float)i3 + 0.375F, (float)i4 + 0.625F, (float)i4 + 0.375F, f11 * 0.5F, d13);
			this.func_31077_c((float)i2 - 0.25F + 1.0F - f12, (float)i2 - 0.25F + 1.0F, (float)i3 + 0.625F, (float)i3 + 0.625F, (float)i4 + 0.375F, (float)i4 + 0.625F, f11, d13);
			this.func_31077_c((float)i2 - 0.25F + 1.0F - f12, (float)i2 - 0.25F + 1.0F, (float)i3 + 0.375F, (float)i3 + 0.625F, (float)i4 + 0.375F, (float)i4 + 0.375F, f11 * 0.6F, d13);
			this.func_31077_c((float)i2 - 0.25F + 1.0F - f12, (float)i2 - 0.25F + 1.0F, (float)i3 + 0.625F, (float)i3 + 0.375F, (float)i4 + 0.625F, (float)i4 + 0.625F, f11 * 0.6F, d13);
		}

		this.field_31087_g = 0;
		this.field_31086_h = 0;
		this.field_31085_i = 0;
		this.field_31084_j = 0;
		this.field_31083_k = 0;
		this.field_31082_l = 0;
		block1.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		return true;
	}

	public boolean renderBlockLever(Block block1, int i2, int i3, int i4) {
		int i5 = this.blockAccess.getBlockMetadata(i2, i3, i4);
		int i6 = i5 & 7;
		boolean z7 = (i5 & 8) > 0;
		Tessellator tessellator8 = Tessellator.instance;
		boolean z9 = this.overrideBlockTexture >= 0;
		if(!z9) {
			this.overrideBlockTexture = Block.cobblestone.blockIndexInTexture;
		}

		float f10 = 0.25F;
		float f11 = 0.1875F;
		float f12 = 0.1875F;
		if(i6 == 5) {
			block1.setBlockBounds(0.5F - f11, 0.0F, 0.5F - f10, 0.5F + f11, f12, 0.5F + f10);
		} else if(i6 == 6) {
			block1.setBlockBounds(0.5F - f10, 0.0F, 0.5F - f11, 0.5F + f10, f12, 0.5F + f11);
		} else if(i6 == 4) {
			block1.setBlockBounds(0.5F - f11, 0.5F - f10, 1.0F - f12, 0.5F + f11, 0.5F + f10, 1.0F);
		} else if(i6 == 3) {
			block1.setBlockBounds(0.5F - f11, 0.5F - f10, 0.0F, 0.5F + f11, 0.5F + f10, f12);
		} else if(i6 == 2) {
			block1.setBlockBounds(1.0F - f12, 0.5F - f10, 0.5F - f11, 1.0F, 0.5F + f10, 0.5F + f11);
		} else if(i6 == 1) {
			block1.setBlockBounds(0.0F, 0.5F - f10, 0.5F - f11, f12, 0.5F + f10, 0.5F + f11);
		}

		this.renderStandardBlock(block1, i2, i3, i4);
		if(!z9) {
			this.overrideBlockTexture = -1;
		}

		float f13 = block1.getBlockBrightness(this.blockAccess, i2, i3, i4);
		if(Block.lightValue[block1.blockID] > 0) {
			f13 = 1.0F;
		}

		tessellator8.setColorOpaque_F(f13, f13, f13);
		int i14 = block1.getBlockTextureFromSide(0);
		if(this.overrideBlockTexture >= 0) {
			i14 = this.overrideBlockTexture;
		}

		int i15 = (i14 & 15) << 4;
		int i16 = i14 & 240;
		float f17 = (float)i15 / 256.0F;
		float f18 = ((float)i15 + 15.99F) / 256.0F;
		float f19 = (float)i16 / 256.0F;
		float f20 = ((float)i16 + 15.99F) / 256.0F;
		Vec3D[] vec3D21 = new Vec3D[8];
		float f22 = 0.0625F;
		float f23 = 0.0625F;
		float f24 = 0.625F;
		vec3D21[0] = Vec3D.createVector(-f22, 0.0D, -f23);
		vec3D21[1] = Vec3D.createVector(f22, 0.0D, -f23);
		vec3D21[2] = Vec3D.createVector(f22, 0.0D, f23);
		vec3D21[3] = Vec3D.createVector(-f22, 0.0D, f23);
		vec3D21[4] = Vec3D.createVector(-f22, f24, -f23);
		vec3D21[5] = Vec3D.createVector(f22, f24, -f23);
		vec3D21[6] = Vec3D.createVector(f22, f24, f23);
		vec3D21[7] = Vec3D.createVector(-f22, f24, f23);

		for(int i25 = 0; i25 < 8; ++i25) {
			if(z7) {
				vec3D21[i25].zCoord -= 0.0625D;
				vec3D21[i25].rotateAroundX((float)Math.PI / 4.5F);
			} else {
				vec3D21[i25].zCoord += 0.0625D;
				vec3D21[i25].rotateAroundX(-0.69813174F);
			}

			if(i6 == 6) {
				vec3D21[i25].rotateAroundY((float)Math.PI / 2F);
			}

			if(i6 < 5) {
				vec3D21[i25].yCoord -= 0.375D;
				vec3D21[i25].rotateAroundX((float)Math.PI / 2F);
				if(i6 == 4) {
					vec3D21[i25].rotateAroundY(0.0F);
				}

				if(i6 == 3) {
					vec3D21[i25].rotateAroundY((float)Math.PI);
				}

				if(i6 == 2) {
					vec3D21[i25].rotateAroundY((float)Math.PI / 2F);
				}

				if(i6 == 1) {
					vec3D21[i25].rotateAroundY(-1.5707964F);
				}

				vec3D21[i25].xCoord += (double)i2 + 0.5D;
				vec3D21[i25].yCoord += (float)i3 + 0.5F;
			} else {
				vec3D21[i25].xCoord += (double)i2 + 0.5D;
				vec3D21[i25].yCoord += (float)i3 + 0.125F;
			}
			vec3D21[i25].zCoord += (double)i4 + 0.5D;
		}

		Vec3D vec3D30 = null;
		Vec3D vec3D26 = null;
		Vec3D vec3D27 = null;
		Vec3D vec3D28 = null;

		for(int i29 = 0; i29 < 6; ++i29) {
			if(i29 == 0) {
				f17 = (float)(i15 + 7) / 256.0F;
				f18 = ((float)(i15 + 9) - 0.01F) / 256.0F;
				f19 = (float)(i16 + 6) / 256.0F;
				f20 = ((float)(i16 + 8) - 0.01F) / 256.0F;
			} else if(i29 == 2) {
				f17 = (float)(i15 + 7) / 256.0F;
				f18 = ((float)(i15 + 9) - 0.01F) / 256.0F;
				f19 = (float)(i16 + 6) / 256.0F;
				f20 = ((float)(i16 + 16) - 0.01F) / 256.0F;
			}

			if(i29 == 0) {
				vec3D30 = vec3D21[0];
				vec3D26 = vec3D21[1];
				vec3D27 = vec3D21[2];
				vec3D28 = vec3D21[3];
			} else if(i29 == 1) {
				vec3D30 = vec3D21[7];
				vec3D26 = vec3D21[6];
				vec3D27 = vec3D21[5];
				vec3D28 = vec3D21[4];
			} else if(i29 == 2) {
				vec3D30 = vec3D21[1];
				vec3D26 = vec3D21[0];
				vec3D27 = vec3D21[4];
				vec3D28 = vec3D21[5];
			} else if(i29 == 3) {
				vec3D30 = vec3D21[2];
				vec3D26 = vec3D21[1];
				vec3D27 = vec3D21[5];
				vec3D28 = vec3D21[6];
			} else if(i29 == 4) {
				vec3D30 = vec3D21[3];
				vec3D26 = vec3D21[2];
				vec3D27 = vec3D21[6];
				vec3D28 = vec3D21[7];
			} else {
				vec3D30 = vec3D21[0];
				vec3D26 = vec3D21[3];
				vec3D27 = vec3D21[7];
				vec3D28 = vec3D21[4];
			}

			tessellator8.addVertexWithUV(vec3D30.xCoord, vec3D30.yCoord, vec3D30.zCoord, f17, f20);
			tessellator8.addVertexWithUV(vec3D26.xCoord, vec3D26.yCoord, vec3D26.zCoord, f18, f20);
			tessellator8.addVertexWithUV(vec3D27.xCoord, vec3D27.yCoord, vec3D27.zCoord, f18, f19);
			tessellator8.addVertexWithUV(vec3D28.xCoord, vec3D28.yCoord, vec3D28.zCoord, f17, f19);
		}

		return true;
	}

	public boolean renderBlockFire(Block block1, int i2, int i3, int i4) {
		Tessellator tessellator5 = Tessellator.instance;
		int i6 = block1.getBlockTextureFromSide(0);
		if(this.overrideBlockTexture >= 0) {
			i6 = this.overrideBlockTexture;
		}

		float f7 = block1.getBlockBrightness(this.blockAccess, i2, i3, i4);
		tessellator5.setColorOpaque_F(f7, f7, f7);
		int i8 = (i6 & 15) << 4;
		int i9 = i6 & 240;
		double d10 = (float)i8 / 256.0F;
		double d12 = ((float)i8 + 15.99F) / 256.0F;
		double d14 = (float)i9 / 256.0F;
		double d16 = ((float)i9 + 15.99F) / 256.0F;
		float f18 = 1.4F;
		double d21;
		double d23;
		double d25;
		double d27;
		double d29;
		double d31;
		double d33;
		if(!this.blockAccess.isBlockNormalCube(i2, i3 - 1, i4) && !Block.fire.canBlockCatchFire(this.blockAccess, i2, i3 - 1, i4)) {
			float f37 = 0.2F;
			float f20 = 0.0625F;
			if((i2 + i3 + i4 & 1) == 1) {
				d10 = (float)i8 / 256.0F;
				d12 = ((float)i8 + 15.99F) / 256.0F;
				d14 = (float)(i9 + 16) / 256.0F;
				d16 = ((float)i9 + 15.99F + 16.0F) / 256.0F;
			}

			if((i2 / 2 + i3 / 2 + i4 / 2 & 1) == 1) {
				d21 = d12;
				d12 = d10;
				d10 = d21;
			}

			if(Block.fire.canBlockCatchFire(this.blockAccess, i2 - 1, i3, i4)) {
				tessellator5.addVertexWithUV((float)i2 + f37, (float)i3 + f18 + f20, i4 + 1, d12, d14);
				tessellator5.addVertexWithUV(i2, (float)(i3) + f20, i4 + 1, d12, d16);
				tessellator5.addVertexWithUV(i2, (float)(i3) + f20, i4, d10, d16);
				tessellator5.addVertexWithUV((float)i2 + f37, (float)i3 + f18 + f20, i4, d10, d14);
				tessellator5.addVertexWithUV((float)i2 + f37, (float)i3 + f18 + f20, i4, d10, d14);
				tessellator5.addVertexWithUV(i2, (float)(i3) + f20, i4, d10, d16);
				tessellator5.addVertexWithUV(i2, (float)(i3) + f20, i4 + 1, d12, d16);
				tessellator5.addVertexWithUV((float)i2 + f37, (float)i3 + f18 + f20, i4 + 1, d12, d14);
			}

			if(Block.fire.canBlockCatchFire(this.blockAccess, i2 + 1, i3, i4)) {
				tessellator5.addVertexWithUV((float)(i2 + 1) - f37, (float)i3 + f18 + f20, i4, d10, d14);
				tessellator5.addVertexWithUV(i2 + 1, (float)(i3) + f20, i4, d10, d16);
				tessellator5.addVertexWithUV(i2 + 1, (float)(i3) + f20, i4 + 1, d12, d16);
				tessellator5.addVertexWithUV((float)(i2 + 1) - f37, (float)i3 + f18 + f20, i4 + 1, d12, d14);
				tessellator5.addVertexWithUV((float)(i2 + 1) - f37, (float)i3 + f18 + f20, i4 + 1, d12, d14);
				tessellator5.addVertexWithUV(i2 + 1, (float)(i3) + f20, i4 + 1, d12, d16);
				tessellator5.addVertexWithUV(i2 + 1, (float)(i3) + f20, i4, d10, d16);
				tessellator5.addVertexWithUV((float)(i2 + 1) - f37, (float)i3 + f18 + f20, i4, d10, d14);
			}

			if(Block.fire.canBlockCatchFire(this.blockAccess, i2, i3, i4 - 1)) {
				tessellator5.addVertexWithUV(i2, (float)i3 + f18 + f20, (float)i4 + f37, d12, d14);
				tessellator5.addVertexWithUV(i2, (float)(i3) + f20, i4, d12, d16);
				tessellator5.addVertexWithUV(i2 + 1, (float)(i3) + f20, i4, d10, d16);
				tessellator5.addVertexWithUV(i2 + 1, (float)i3 + f18 + f20, (float)i4 + f37, d10, d14);
				tessellator5.addVertexWithUV(i2 + 1, (float)i3 + f18 + f20, (float)i4 + f37, d10, d14);
				tessellator5.addVertexWithUV(i2 + 1, (float)(i3) + f20, i4, d10, d16);
				tessellator5.addVertexWithUV(i2, (float)(i3) + f20, i4, d12, d16);
				tessellator5.addVertexWithUV(i2, (float)i3 + f18 + f20, (float)i4 + f37, d12, d14);
			}

			if(Block.fire.canBlockCatchFire(this.blockAccess, i2, i3, i4 + 1)) {
				tessellator5.addVertexWithUV(i2 + 1, (float)i3 + f18 + f20, (float)(i4 + 1) - f37, d10, d14);
				tessellator5.addVertexWithUV(i2 + 1, (float)(i3) + f20, i4 + 1, d10, d16);
				tessellator5.addVertexWithUV(i2, (float)(i3) + f20, i4 + 1, d12, d16);
				tessellator5.addVertexWithUV(i2, (float)i3 + f18 + f20, (float)(i4 + 1) - f37, d12, d14);
				tessellator5.addVertexWithUV(i2, (float)i3 + f18 + f20, (float)(i4 + 1) - f37, d12, d14);
				tessellator5.addVertexWithUV(i2, (float)(i3) + f20, i4 + 1, d12, d16);
				tessellator5.addVertexWithUV(i2 + 1, (float)(i3) + f20, i4 + 1, d10, d16);
				tessellator5.addVertexWithUV(i2 + 1, (float)i3 + f18 + f20, (float)(i4 + 1) - f37, d10, d14);
			}

			if(Block.fire.canBlockCatchFire(this.blockAccess, i2, i3 + 1, i4)) {
				d21 = (double)i2 + 0.5D + 0.5D;
				d23 = (double)i2 + 0.5D - 0.5D;
				d25 = (double)i4 + 0.5D + 0.5D;
				d27 = (double)i4 + 0.5D - 0.5D;
				d29 = (double)i2 + 0.5D - 0.5D;
				d31 = (double)i2 + 0.5D + 0.5D;
				d33 = (double)i4 + 0.5D - 0.5D;
				double d35 = (double)i4 + 0.5D + 0.5D;
				d10 = (float)i8 / 256.0F;
				d12 = ((float)i8 + 15.99F) / 256.0F;
				d14 = (float)i9 / 256.0F;
				d16 = ((float)i9 + 15.99F) / 256.0F;
				++i3;
				f18 = -0.2F;
				if((i2 + i3 + i4 & 1) == 0) {
					tessellator5.addVertexWithUV(d29, (float)i3 + f18, i4, d12, d14);
					tessellator5.addVertexWithUV(d21, i3, i4, d12, d16);
					tessellator5.addVertexWithUV(d21, i3, i4 + 1, d10, d16);
					tessellator5.addVertexWithUV(d29, (float)i3 + f18, i4 + 1, d10, d14);
					d10 = (float)i8 / 256.0F;
					d12 = ((float)i8 + 15.99F) / 256.0F;
					d14 = (float)(i9 + 16) / 256.0F;
					d16 = ((float)i9 + 15.99F + 16.0F) / 256.0F;
					tessellator5.addVertexWithUV(d31, (float)i3 + f18, i4 + 1, d12, d14);
					tessellator5.addVertexWithUV(d23, i3, i4 + 1, d12, d16);
					tessellator5.addVertexWithUV(d23, i3, i4, d10, d16);
					tessellator5.addVertexWithUV(d31, (float)i3 + f18, i4, d10, d14);
				} else {
					tessellator5.addVertexWithUV(i2, (float)i3 + f18, d35, d12, d14);
					tessellator5.addVertexWithUV(i2, i3, d27, d12, d16);
					tessellator5.addVertexWithUV(i2 + 1, i3, d27, d10, d16);
					tessellator5.addVertexWithUV(i2 + 1, (float)i3 + f18, d35, d10, d14);
					d10 = (float)i8 / 256.0F;
					d12 = ((float)i8 + 15.99F) / 256.0F;
					d14 = (float)(i9 + 16) / 256.0F;
					d16 = ((float)i9 + 15.99F + 16.0F) / 256.0F;
					tessellator5.addVertexWithUV(i2 + 1, (float)i3 + f18, d33, d12, d14);
					tessellator5.addVertexWithUV(i2 + 1, i3, d25, d12, d16);
					tessellator5.addVertexWithUV(i2, i3, d25, d10, d16);
					tessellator5.addVertexWithUV(i2, (float)i3 + f18, d33, d10, d14);
				}
			}
		} else {
			double d19 = (double)i2 + 0.5D + 0.2D;
			d21 = (double)i2 + 0.5D - 0.2D;
			d23 = (double)i4 + 0.5D + 0.2D;
			d25 = (double)i4 + 0.5D - 0.2D;
			d27 = (double)i2 + 0.5D - 0.3D;
			d29 = (double)i2 + 0.5D + 0.3D;
			d31 = (double)i4 + 0.5D - 0.3D;
			d33 = (double)i4 + 0.5D + 0.3D;
			tessellator5.addVertexWithUV(d27, (float)i3 + f18, i4 + 1, d12, d14);
			tessellator5.addVertexWithUV(d19, i3, i4 + 1, d12, d16);
			tessellator5.addVertexWithUV(d19, i3, i4, d10, d16);
			tessellator5.addVertexWithUV(d27, (float)i3 + f18, i4, d10, d14);
			tessellator5.addVertexWithUV(d29, (float)i3 + f18, i4, d12, d14);
			tessellator5.addVertexWithUV(d21, i3, i4, d12, d16);
			tessellator5.addVertexWithUV(d21, i3, i4 + 1, d10, d16);
			tessellator5.addVertexWithUV(d29, (float)i3 + f18, i4 + 1, d10, d14);
			d10 = (float)i8 / 256.0F;
			d12 = ((float)i8 + 15.99F) / 256.0F;
			d14 = (float)(i9 + 16) / 256.0F;
			d16 = ((float)i9 + 15.99F + 16.0F) / 256.0F;
			tessellator5.addVertexWithUV(i2 + 1, (float)i3 + f18, d33, d12, d14);
			tessellator5.addVertexWithUV(i2 + 1, i3, d25, d12, d16);
			tessellator5.addVertexWithUV(i2, i3, d25, d10, d16);
			tessellator5.addVertexWithUV(i2, (float)i3 + f18, d33, d10, d14);
			tessellator5.addVertexWithUV(i2, (float)i3 + f18, d31, d12, d14);
			tessellator5.addVertexWithUV(i2, i3, d23, d12, d16);
			tessellator5.addVertexWithUV(i2 + 1, i3, d23, d10, d16);
			tessellator5.addVertexWithUV(i2 + 1, (float)i3 + f18, d31, d10, d14);
			d19 = (double)i2 + 0.5D - 0.5D;
			d21 = (double)i2 + 0.5D + 0.5D;
			d23 = (double)i4 + 0.5D - 0.5D;
			d25 = (double)i4 + 0.5D + 0.5D;
			d27 = (double)i2 + 0.5D - 0.4D;
			d29 = (double)i2 + 0.5D + 0.4D;
			d31 = (double)i4 + 0.5D - 0.4D;
			d33 = (double)i4 + 0.5D + 0.4D;
			tessellator5.addVertexWithUV(d27, (float)i3 + f18, i4, d10, d14);
			tessellator5.addVertexWithUV(d19, i3, i4, d10, d16);
			tessellator5.addVertexWithUV(d19, i3, i4 + 1, d12, d16);
			tessellator5.addVertexWithUV(d27, (float)i3 + f18, i4 + 1, d12, d14);
			tessellator5.addVertexWithUV(d29, (float)i3 + f18, i4 + 1, d10, d14);
			tessellator5.addVertexWithUV(d21, i3, i4 + 1, d10, d16);
			tessellator5.addVertexWithUV(d21, i3, i4, d12, d16);
			tessellator5.addVertexWithUV(d29, (float)i3 + f18, i4, d12, d14);
			d10 = (float)i8 / 256.0F;
			d12 = ((float)i8 + 15.99F) / 256.0F;
			d14 = (float)i9 / 256.0F;
			d16 = ((float)i9 + 15.99F) / 256.0F;
			tessellator5.addVertexWithUV(i2, (float)i3 + f18, d33, d10, d14);
			tessellator5.addVertexWithUV(i2, i3, d25, d10, d16);
			tessellator5.addVertexWithUV(i2 + 1, i3, d25, d12, d16);
			tessellator5.addVertexWithUV(i2 + 1, (float)i3 + f18, d33, d12, d14);
			tessellator5.addVertexWithUV(i2 + 1, (float)i3 + f18, d31, d10, d14);
			tessellator5.addVertexWithUV(i2 + 1, i3, d23, d10, d16);
			tessellator5.addVertexWithUV(i2, i3, d23, d12, d16);
			tessellator5.addVertexWithUV(i2, (float)i3 + f18, d31, d12, d14);
		}

		return true;
	}

	public boolean renderBlockRedstoneWire(Block block1, int i2, int i3, int i4) {
		Tessellator tessellator5 = Tessellator.instance;
		int i6 = this.blockAccess.getBlockMetadata(i2, i3, i4);
		int i7 = block1.getBlockTextureFromSideAndMetadata(1, i6);
		if(this.overrideBlockTexture >= 0) {
			i7 = this.overrideBlockTexture;
		}

		float f8 = block1.getBlockBrightness(this.blockAccess, i2, i3, i4);
		float f9 = (float)i6 / 15.0F;
		float f10 = f9 * 0.6F + 0.4F;
		if(i6 == 0) {
			f10 = 0.3F;
		}

		float f11 = f9 * f9 * 0.7F - 0.5F;
		float f12 = f9 * f9 * 0.6F - 0.7F;
		if(f11 < 0.0F) {
			f11 = 0.0F;
		}

		if(f12 < 0.0F) {
			f12 = 0.0F;
		}

		tessellator5.setColorOpaque_F(f8 * f10, f8 * f11, f8 * f12);
		int i13 = (i7 & 15) << 4;
		int i14 = i7 & 240;
		double d15 = (float)i13 / 256.0F;
		double d17 = ((float)i13 + 15.99F) / 256.0F;
		double d19 = (float)i14 / 256.0F;
		double d21 = ((float)i14 + 15.99F) / 256.0F;
		boolean z26 = BlockRedstoneWire.isPowerProviderOrWire(this.blockAccess, i2 - 1, i3, i4, 1) || !this.blockAccess.isBlockNormalCube(i2 - 1, i3, i4) && BlockRedstoneWire.isPowerProviderOrWire(this.blockAccess, i2 - 1, i3 - 1, i4, -1);
		boolean z27 = BlockRedstoneWire.isPowerProviderOrWire(this.blockAccess, i2 + 1, i3, i4, 3) || !this.blockAccess.isBlockNormalCube(i2 + 1, i3, i4) && BlockRedstoneWire.isPowerProviderOrWire(this.blockAccess, i2 + 1, i3 - 1, i4, -1);
		boolean z28 = BlockRedstoneWire.isPowerProviderOrWire(this.blockAccess, i2, i3, i4 - 1, 2) || !this.blockAccess.isBlockNormalCube(i2, i3, i4 - 1) && BlockRedstoneWire.isPowerProviderOrWire(this.blockAccess, i2, i3 - 1, i4 - 1, -1);
		boolean z29 = BlockRedstoneWire.isPowerProviderOrWire(this.blockAccess, i2, i3, i4 + 1, 0) || !this.blockAccess.isBlockNormalCube(i2, i3, i4 + 1) && BlockRedstoneWire.isPowerProviderOrWire(this.blockAccess, i2, i3 - 1, i4 + 1, -1);
		if(!this.blockAccess.isBlockNormalCube(i2, i3 + 1, i4)) {
			if(this.blockAccess.isBlockNormalCube(i2 - 1, i3, i4) && BlockRedstoneWire.isPowerProviderOrWire(this.blockAccess, i2 - 1, i3 + 1, i4, -1)) {
				z26 = true;
			}

			if(this.blockAccess.isBlockNormalCube(i2 + 1, i3, i4) && BlockRedstoneWire.isPowerProviderOrWire(this.blockAccess, i2 + 1, i3 + 1, i4, -1)) {
				z27 = true;
			}

			if(this.blockAccess.isBlockNormalCube(i2, i3, i4 - 1) && BlockRedstoneWire.isPowerProviderOrWire(this.blockAccess, i2, i3 + 1, i4 - 1, -1)) {
				z28 = true;
			}

			if(this.blockAccess.isBlockNormalCube(i2, i3, i4 + 1) && BlockRedstoneWire.isPowerProviderOrWire(this.blockAccess, i2, i3 + 1, i4 + 1, -1)) {
				z29 = true;
			}
		}

		float f31 = (float)(i2);
		float f32 = (float)(i2 + 1);
		float f33 = (float)(i4);
		float f34 = (float)(i4 + 1);
		byte b35 = 0;
		if((z26 || z27) && !z28 && !z29) {
			b35 = 1;
		}

		if((z28 || z29) && !z27 && !z26) {
			b35 = 2;
		}

		if(b35 != 0) {
			d15 = (float)(i13 + 16) / 256.0F;
			d17 = ((float)(i13 + 16) + 15.99F) / 256.0F;
			d19 = (float)i14 / 256.0F;
			d21 = ((float)i14 + 15.99F) / 256.0F;
		}

		if(b35 == 0) {
			if(z27 || z28 || z29) {
				if(!z26) {
					f31 += 0.3125F;
				}

				if(!z26) {
					d15 += 0.01953125D;
				}

				if(!z27) {
					f32 -= 0.3125F;
				}

				if(!z27) {
					d17 -= 0.01953125D;
				}

				if(!z28) {
					f33 += 0.3125F;
				}

				if(!z28) {
					d19 += 0.01953125D;
				}

				if(!z29) {
					f34 -= 0.3125F;
				}

				if(!z29) {
					d21 -= 0.01953125D;
				}
			}

			tessellator5.addVertexWithUV(f32, (float)i3 + 0.015625F, f34, d17, d21);
			tessellator5.addVertexWithUV(f32, (float)i3 + 0.015625F, f33, d17, d19);
			tessellator5.addVertexWithUV(f31, (float)i3 + 0.015625F, f33, d15, d19);
			tessellator5.addVertexWithUV(f31, (float)i3 + 0.015625F, f34, d15, d21);
			tessellator5.setColorOpaque_F(f8, f8, f8);
			tessellator5.addVertexWithUV(f32, (float)i3 + 0.015625F, f34, d17, d21 + 0.0625D);
			tessellator5.addVertexWithUV(f32, (float)i3 + 0.015625F, f33, d17, d19 + 0.0625D);
			tessellator5.addVertexWithUV(f31, (float)i3 + 0.015625F, f33, d15, d19 + 0.0625D);
			tessellator5.addVertexWithUV(f31, (float)i3 + 0.015625F, f34, d15, d21 + 0.0625D);
		} else if(b35 == 1) {
			tessellator5.addVertexWithUV(f32, (float)i3 + 0.015625F, f34, d17, d21);
			tessellator5.addVertexWithUV(f32, (float)i3 + 0.015625F, f33, d17, d19);
			tessellator5.addVertexWithUV(f31, (float)i3 + 0.015625F, f33, d15, d19);
			tessellator5.addVertexWithUV(f31, (float)i3 + 0.015625F, f34, d15, d21);
			tessellator5.setColorOpaque_F(f8, f8, f8);
			tessellator5.addVertexWithUV(f32, (float)i3 + 0.015625F, f34, d17, d21 + 0.0625D);
			tessellator5.addVertexWithUV(f32, (float)i3 + 0.015625F, f33, d17, d19 + 0.0625D);
			tessellator5.addVertexWithUV(f31, (float)i3 + 0.015625F, f33, d15, d19 + 0.0625D);
			tessellator5.addVertexWithUV(f31, (float)i3 + 0.015625F, f34, d15, d21 + 0.0625D);
		} else {
			tessellator5.addVertexWithUV(f32, (float)i3 + 0.015625F, f34, d17, d21);
			tessellator5.addVertexWithUV(f32, (float)i3 + 0.015625F, f33, d15, d21);
			tessellator5.addVertexWithUV(f31, (float)i3 + 0.015625F, f33, d15, d19);
			tessellator5.addVertexWithUV(f31, (float)i3 + 0.015625F, f34, d17, d19);
			tessellator5.setColorOpaque_F(f8, f8, f8);
			tessellator5.addVertexWithUV(f32, (float)i3 + 0.015625F, f34, d17, d21 + 0.0625D);
			tessellator5.addVertexWithUV(f32, (float)i3 + 0.015625F, f33, d15, d21 + 0.0625D);
			tessellator5.addVertexWithUV(f31, (float)i3 + 0.015625F, f33, d15, d19 + 0.0625D);
			tessellator5.addVertexWithUV(f31, (float)i3 + 0.015625F, f34, d17, d19 + 0.0625D);
		}

		if(!this.blockAccess.isBlockNormalCube(i2, i3 + 1, i4)) {
			d15 = (float)(i13 + 16) / 256.0F;
			d17 = ((float)(i13 + 16) + 15.99F) / 256.0F;
			d19 = (float)i14 / 256.0F;
			d21 = ((float)i14 + 15.99F) / 256.0F;
			if(this.blockAccess.isBlockNormalCube(i2 - 1, i3, i4) && this.blockAccess.getBlockId(i2 - 1, i3 + 1, i4) == Block.redstoneWire.blockID) {
				tessellator5.setColorOpaque_F(f8 * f10, f8 * f11, f8 * f12);
				tessellator5.addVertexWithUV((float)i2 + 0.015625F, (float)(i3 + 1) + 0.021875F, i4 + 1, d17, d19);
				tessellator5.addVertexWithUV((float)i2 + 0.015625F, i3, i4 + 1, d15, d19);
				tessellator5.addVertexWithUV((float)i2 + 0.015625F, i3, i4, d15, d21);
				tessellator5.addVertexWithUV((float)i2 + 0.015625F, (float)(i3 + 1) + 0.021875F, i4, d17, d21);
				tessellator5.setColorOpaque_F(f8, f8, f8);
				tessellator5.addVertexWithUV((float)i2 + 0.015625F, (float)(i3 + 1) + 0.021875F, i4 + 1, d17, d19 + 0.0625D);
				tessellator5.addVertexWithUV((float)i2 + 0.015625F, i3, i4 + 1, d15, d19 + 0.0625D);
				tessellator5.addVertexWithUV((float)i2 + 0.015625F, i3, i4, d15, d21 + 0.0625D);
				tessellator5.addVertexWithUV((float)i2 + 0.015625F, (float)(i3 + 1) + 0.021875F, i4, d17, d21 + 0.0625D);
			}

			if(this.blockAccess.isBlockNormalCube(i2 + 1, i3, i4) && this.blockAccess.getBlockId(i2 + 1, i3 + 1, i4) == Block.redstoneWire.blockID) {
				tessellator5.setColorOpaque_F(f8 * f10, f8 * f11, f8 * f12);
				tessellator5.addVertexWithUV((float)(i2 + 1) - 0.015625F, i3, i4 + 1, d15, d21);
				tessellator5.addVertexWithUV((float)(i2 + 1) - 0.015625F, (float)(i3 + 1) + 0.021875F, i4 + 1, d17, d21);
				tessellator5.addVertexWithUV((float)(i2 + 1) - 0.015625F, (float)(i3 + 1) + 0.021875F, i4, d17, d19);
				tessellator5.addVertexWithUV((float)(i2 + 1) - 0.015625F, i3, i4, d15, d19);
				tessellator5.setColorOpaque_F(f8, f8, f8);
				tessellator5.addVertexWithUV((float)(i2 + 1) - 0.015625F, i3, i4 + 1, d15, d21 + 0.0625D);
				tessellator5.addVertexWithUV((float)(i2 + 1) - 0.015625F, (float)(i3 + 1) + 0.021875F, i4 + 1, d17, d21 + 0.0625D);
				tessellator5.addVertexWithUV((float)(i2 + 1) - 0.015625F, (float)(i3 + 1) + 0.021875F, i4, d17, d19 + 0.0625D);
				tessellator5.addVertexWithUV((float)(i2 + 1) - 0.015625F, i3, i4, d15, d19 + 0.0625D);
			}

			if(this.blockAccess.isBlockNormalCube(i2, i3, i4 - 1) && this.blockAccess.getBlockId(i2, i3 + 1, i4 - 1) == Block.redstoneWire.blockID) {
				tessellator5.setColorOpaque_F(f8 * f10, f8 * f11, f8 * f12);
				tessellator5.addVertexWithUV(i2 + 1, i3, (float)i4 + 0.015625F, d15, d21);
				tessellator5.addVertexWithUV(i2 + 1, (float)(i3 + 1) + 0.021875F, (float)i4 + 0.015625F, d17, d21);
				tessellator5.addVertexWithUV(i2, (float)(i3 + 1) + 0.021875F, (float)i4 + 0.015625F, d17, d19);
				tessellator5.addVertexWithUV(i2, i3, (float)i4 + 0.015625F, d15, d19);
				tessellator5.setColorOpaque_F(f8, f8, f8);
				tessellator5.addVertexWithUV(i2 + 1, i3, (float)i4 + 0.015625F, d15, d21 + 0.0625D);
				tessellator5.addVertexWithUV(i2 + 1, (float)(i3 + 1) + 0.021875F, (float)i4 + 0.015625F, d17, d21 + 0.0625D);
				tessellator5.addVertexWithUV(i2, (float)(i3 + 1) + 0.021875F, (float)i4 + 0.015625F, d17, d19 + 0.0625D);
				tessellator5.addVertexWithUV(i2, i3, (float)i4 + 0.015625F, d15, d19 + 0.0625D);
			}

			if(this.blockAccess.isBlockNormalCube(i2, i3, i4 + 1) && this.blockAccess.getBlockId(i2, i3 + 1, i4 + 1) == Block.redstoneWire.blockID) {
				tessellator5.setColorOpaque_F(f8 * f10, f8 * f11, f8 * f12);
				tessellator5.addVertexWithUV(i2 + 1, (float)(i3 + 1) + 0.021875F, (float)(i4 + 1) - 0.015625F, d17, d19);
				tessellator5.addVertexWithUV(i2 + 1, i3, (float)(i4 + 1) - 0.015625F, d15, d19);
				tessellator5.addVertexWithUV(i2, i3, (float)(i4 + 1) - 0.015625F, d15, d21);
				tessellator5.addVertexWithUV(i2, (float)(i3 + 1) + 0.021875F, (float)(i4 + 1) - 0.015625F, d17, d21);
				tessellator5.setColorOpaque_F(f8, f8, f8);
				tessellator5.addVertexWithUV(i2 + 1, (float)(i3 + 1) + 0.021875F, (float)(i4 + 1) - 0.015625F, d17, d19 + 0.0625D);
				tessellator5.addVertexWithUV(i2 + 1, i3, (float)(i4 + 1) - 0.015625F, d15, d19 + 0.0625D);
				tessellator5.addVertexWithUV(i2, i3, (float)(i4 + 1) - 0.015625F, d15, d21 + 0.0625D);
				tessellator5.addVertexWithUV(i2, (float)(i3 + 1) + 0.021875F, (float)(i4 + 1) - 0.015625F, d17, d21 + 0.0625D);
			}
		}

		return true;
	}

	public boolean renderBlockMinecartTrack(BlockRail blockRail1, int i2, int i3, int i4) {
		Tessellator tessellator5 = Tessellator.instance;
		int i6 = this.blockAccess.getBlockMetadata(i2, i3, i4);
		int i7 = blockRail1.getBlockTextureFromSideAndMetadata(0, i6);
		if(this.overrideBlockTexture >= 0) {
			i7 = this.overrideBlockTexture;
		}

		if(blockRail1.getIsPowered()) {
			i6 &= 7;
		}

		float f8 = blockRail1.getBlockBrightness(this.blockAccess, i2, i3, i4);
		tessellator5.setColorOpaque_F(f8, f8, f8);
		int i9 = (i7 & 15) << 4;
		int i10 = i7 & 240;
		double d11 = (float)i9 / 256.0F;
		double d13 = ((float)i9 + 15.99F) / 256.0F;
		double d15 = (float)i10 / 256.0F;
		double d17 = ((float)i10 + 15.99F) / 256.0F;
		float f19 = 0.0625F;
		float f20 = (float)(i2 + 1);
		float f21 = (float)(i2 + 1);
		float f22 = (float)(i2);
		float f23 = (float)(i2);
		float f24 = (float)(i4);
		float f25 = (float)(i4 + 1);
		float f26 = (float)(i4 + 1);
		float f27 = (float)(i4);
		float f28 = (float)i3 + f19;
		float f29 = (float)i3 + f19;
		float f30 = (float)i3 + f19;
		float f31 = (float)i3 + f19;
		if(i6 != 1 && i6 != 2 && i6 != 3 && i6 != 7) {
			if(i6 == 8) {
				f20 = f21 = (float)(i2);
				f22 = f23 = (float)(i2 + 1);
				f24 = f27 = (float)(i4 + 1);
				f25 = f26 = (float)(i4);
			} else if(i6 == 9) {
				f20 = f23 = (float)(i2);
				f21 = f22 = (float)(i2 + 1);
				f24 = f25 = (float)(i4);
				f26 = f27 = (float)(i4 + 1);
			}
		} else {
			f20 = f23 = (float)(i2 + 1);
			f21 = f22 = (float)(i2);
			f24 = f25 = (float)(i4 + 1);
			f26 = f27 = (float)(i4);
		}

		if(i6 != 2 && i6 != 4) {
			if(i6 == 3 || i6 == 5) {
				++f29;
				++f30;
			}
		} else {
			++f28;
			++f31;
		}

		tessellator5.addVertexWithUV(f20, f28, f24, d13, d15);
		tessellator5.addVertexWithUV(f21, f29, f25, d13, d17);
		tessellator5.addVertexWithUV(f22, f30, f26, d11, d17);
		tessellator5.addVertexWithUV(f23, f31, f27, d11, d15);
		tessellator5.addVertexWithUV(f23, f31, f27, d11, d15);
		tessellator5.addVertexWithUV(f22, f30, f26, d11, d17);
		tessellator5.addVertexWithUV(f21, f29, f25, d13, d17);
		tessellator5.addVertexWithUV(f20, f28, f24, d13, d15);
		return true;
	}

	public boolean renderBlockLadder(Block block1, int i2, int i3, int i4) {
		Tessellator tessellator5 = Tessellator.instance;
		int i6 = block1.getBlockTextureFromSide(0);
		if(this.overrideBlockTexture >= 0) {
			i6 = this.overrideBlockTexture;
		}

		float f7 = block1.getBlockBrightness(this.blockAccess, i2, i3, i4);
		tessellator5.setColorOpaque_F(f7, f7, f7);
		int i8 = (i6 & 15) << 4;
		int i9 = i6 & 240;
		double d10 = (float)i8 / 256.0F;
		double d12 = ((float)i8 + 15.99F) / 256.0F;
		double d14 = (float)i9 / 256.0F;
		double d16 = ((float)i9 + 15.99F) / 256.0F;
		int i18 = this.blockAccess.getBlockMetadata(i2, i3, i4);
		float f19 = 0.0F;
		float f20 = 0.05F;
		if(i18 == 5) {
			tessellator5.addVertexWithUV((float)i2 + f20, (float)(i3 + 1) + f19, (float)(i4 + 1) + f19, d10, d14);
			tessellator5.addVertexWithUV((float)i2 + f20, (float)(i3) - f19, (float)(i4 + 1) + f19, d10, d16);
			tessellator5.addVertexWithUV((float)i2 + f20, (float)(i3) - f19, (float)(i4) - f19, d12, d16);
			tessellator5.addVertexWithUV((float)i2 + f20, (float)(i3 + 1) + f19, (float)(i4) - f19, d12, d14);
		}

		if(i18 == 4) {
			tessellator5.addVertexWithUV((float)(i2 + 1) - f20, (float)(i3) - f19, (float)(i4 + 1) + f19, d12, d16);
			tessellator5.addVertexWithUV((float)(i2 + 1) - f20, (float)(i3 + 1) + f19, (float)(i4 + 1) + f19, d12, d14);
			tessellator5.addVertexWithUV((float)(i2 + 1) - f20, (float)(i3 + 1) + f19, (float)(i4) - f19, d10, d14);
			tessellator5.addVertexWithUV((float)(i2 + 1) - f20, (float)(i3) - f19, (float)(i4) - f19, d10, d16);
		}

		if(i18 == 3) {
			tessellator5.addVertexWithUV((float)(i2 + 1) + f19, (float)(i3) - f19, (float)i4 + f20, d12, d16);
			tessellator5.addVertexWithUV((float)(i2 + 1) + f19, (float)(i3 + 1) + f19, (float)i4 + f20, d12, d14);
			tessellator5.addVertexWithUV((float)(i2) - f19, (float)(i3 + 1) + f19, (float)i4 + f20, d10, d14);
			tessellator5.addVertexWithUV((float)(i2) - f19, (float)(i3) - f19, (float)i4 + f20, d10, d16);
		}

		if(i18 == 2) {
			tessellator5.addVertexWithUV((float)(i2 + 1) + f19, (float)(i3 + 1) + f19, (float)(i4 + 1) - f20, d10, d14);
			tessellator5.addVertexWithUV((float)(i2 + 1) + f19, (float)(i3) - f19, (float)(i4 + 1) - f20, d10, d16);
			tessellator5.addVertexWithUV((float)(i2) - f19, (float)(i3) - f19, (float)(i4 + 1) - f20, d12, d16);
			tessellator5.addVertexWithUV((float)(i2) - f19, (float)(i3 + 1) + f19, (float)(i4 + 1) - f20, d12, d14);
		}

		return true;
	}

	public boolean renderBlockReed(Block block1, int i2, int i3, int i4) {
		Tessellator tessellator5 = Tessellator.instance;
		float f6 = block1.getBlockBrightness(this.blockAccess, i2, i3, i4);
		int i7 = block1.colorMultiplier(this.blockAccess, i2, i3, i4);
		float f8 = (float)(i7 >> 16 & 255) / 255.0F;
		float f9 = (float)(i7 >> 8 & 255) / 255.0F;
		float f10 = (float)(i7 & 255) / 255.0F;
		if(EntityRenderer.field_28135_a) {
			float f11 = (f8 * 30.0F + f9 * 59.0F + f10 * 11.0F) / 100.0F;
			float f12 = (f8 * 30.0F + f9 * 70.0F) / 100.0F;
			float f13 = (f8 * 30.0F + f10 * 70.0F) / 100.0F;
			f8 = f11;
			f9 = f12;
			f10 = f13;
		}

		tessellator5.setColorOpaque_F(f6 * f8, f6 * f9, f6 * f10);
		double d19 = i2;
		double d20 = i3;
		double d15 = i4;
		if(block1 == Block.tallGrass) {
			long j17 = (i2 * 3129871L) ^ (long)i4 * 116129781L ^ (long)i3;
			j17 = j17 * j17 * 42317861L + j17 * 11L;
			d19 += ((double)((float)(j17 >> 16 & 15L) / 15.0F) - 0.5D) * 0.5D;
			d20 += ((double)((float)(j17 >> 20 & 15L) / 15.0F) - 1.0D) * 0.2D;
			d15 += ((double)((float)(j17 >> 24 & 15L) / 15.0F) - 0.5D) * 0.5D;
		}

		this.renderCrossedSquares(block1, this.blockAccess.getBlockMetadata(i2, i3, i4), d19, d20, d15);
		return true;
	}

	public boolean renderBlockCrops(Block block1, int i2, int i3, int i4) {
		Tessellator tessellator5 = Tessellator.instance;
		float f6 = block1.getBlockBrightness(this.blockAccess, i2, i3, i4);
		tessellator5.setColorOpaque_F(f6, f6, f6);
		this.func_1245_b(block1, this.blockAccess.getBlockMetadata(i2, i3, i4), i2, (float)i3 - 0.0625F, i4);
		return true;
	}

	public void renderTorchAtAngle(Block block1, double d2, double d4, double d6, double d8, double d10) {
		Tessellator tessellator12 = Tessellator.instance;
		int i13 = block1.getBlockTextureFromSide(0);
		if(this.overrideBlockTexture >= 0) {
			i13 = this.overrideBlockTexture;
		}

		int i14 = (i13 & 15) << 4;
		int i15 = i13 & 240;
		float f16 = (float)i14 / 256.0F;
		float f17 = ((float)i14 + 15.99F) / 256.0F;
		float f18 = (float)i15 / 256.0F;
		float f19 = ((float)i15 + 15.99F) / 256.0F;
		double d20 = (double)f16 + 7.0D / 256D;
		double d22 = (double)f18 + 6.0D / 256D;
		double d24 = (double)f16 + 9.0D / 256D;
		double d26 = (double)f18 + 8.0D / 256D;
		d2 += 0.5D;
		d6 += 0.5D;
		double d28 = d2 - 0.5D;
		double d30 = d2 + 0.5D;
		double d32 = d6 - 0.5D;
		double d34 = d6 + 0.5D;
		double d36 = 0.0625D;
		double d38 = 0.625D;
		tessellator12.addVertexWithUV(d2 + d8 * (1.0D - d38) - d36, d4 + d38, d6 + d10 * (1.0D - d38) - d36, d20, d22);
		tessellator12.addVertexWithUV(d2 + d8 * (1.0D - d38) - d36, d4 + d38, d6 + d10 * (1.0D - d38) + d36, d20, d26);
		tessellator12.addVertexWithUV(d2 + d8 * (1.0D - d38) + d36, d4 + d38, d6 + d10 * (1.0D - d38) + d36, d24, d26);
		tessellator12.addVertexWithUV(d2 + d8 * (1.0D - d38) + d36, d4 + d38, d6 + d10 * (1.0D - d38) - d36, d24, d22);
		tessellator12.addVertexWithUV(d2 - d36, d4 + 1.0D, d32, f16, f18);
		tessellator12.addVertexWithUV(d2 - d36 + d8, d4 + 0.0D, d32 + d10, f16, f19);
		tessellator12.addVertexWithUV(d2 - d36 + d8, d4 + 0.0D, d34 + d10, f17, f19);
		tessellator12.addVertexWithUV(d2 - d36, d4 + 1.0D, d34, f17, f18);
		tessellator12.addVertexWithUV(d2 + d36, d4 + 1.0D, d34, f16, f18);
		tessellator12.addVertexWithUV(d2 + d8 + d36, d4 + 0.0D, d34 + d10, f16, f19);
		tessellator12.addVertexWithUV(d2 + d8 + d36, d4 + 0.0D, d32 + d10, f17, f19);
		tessellator12.addVertexWithUV(d2 + d36, d4 + 1.0D, d32, f17, f18);
		tessellator12.addVertexWithUV(d28, d4 + 1.0D, d6 + d36, f16, f18);
		tessellator12.addVertexWithUV(d28 + d8, d4 + 0.0D, d6 + d36 + d10, f16, f19);
		tessellator12.addVertexWithUV(d30 + d8, d4 + 0.0D, d6 + d36 + d10, f17, f19);
		tessellator12.addVertexWithUV(d30, d4 + 1.0D, d6 + d36, f17, f18);
		tessellator12.addVertexWithUV(d30, d4 + 1.0D, d6 - d36, f16, f18);
		tessellator12.addVertexWithUV(d30 + d8, d4 + 0.0D, d6 - d36 + d10, f16, f19);
		tessellator12.addVertexWithUV(d28 + d8, d4 + 0.0D, d6 - d36 + d10, f17, f19);
		tessellator12.addVertexWithUV(d28, d4 + 1.0D, d6 - d36, f17, f18);
	}

	public void renderCrossedSquares(Block block1, int i2, double d3, double d5, double d7) {
		Tessellator tessellator9 = Tessellator.instance;
		int i10 = block1.getBlockTextureFromSideAndMetadata(0, i2);
		if(this.overrideBlockTexture >= 0) {
			i10 = this.overrideBlockTexture;
		}

		int i11 = (i10 & 15) << 4;
		int i12 = i10 & 240;
		double d13 = (float)i11 / 256.0F;
		double d15 = ((float)i11 + 15.99F) / 256.0F;
		double d17 = (float)i12 / 256.0F;
		double d19 = ((float)i12 + 15.99F) / 256.0F;
		double d21 = d3 + 0.5D - (double)0.45F;
		double d23 = d3 + 0.5D + (double)0.45F;
		double d25 = d7 + 0.5D - (double)0.45F;
		double d27 = d7 + 0.5D + (double)0.45F;
		tessellator9.addVertexWithUV(d21, d5 + 1.0D, d25, d13, d17);
		tessellator9.addVertexWithUV(d21, d5 + 0.0D, d25, d13, d19);
		tessellator9.addVertexWithUV(d23, d5 + 0.0D, d27, d15, d19);
		tessellator9.addVertexWithUV(d23, d5 + 1.0D, d27, d15, d17);
		tessellator9.addVertexWithUV(d23, d5 + 1.0D, d27, d13, d17);
		tessellator9.addVertexWithUV(d23, d5 + 0.0D, d27, d13, d19);
		tessellator9.addVertexWithUV(d21, d5 + 0.0D, d25, d15, d19);
		tessellator9.addVertexWithUV(d21, d5 + 1.0D, d25, d15, d17);
		tessellator9.addVertexWithUV(d21, d5 + 1.0D, d27, d13, d17);
		tessellator9.addVertexWithUV(d21, d5 + 0.0D, d27, d13, d19);
		tessellator9.addVertexWithUV(d23, d5 + 0.0D, d25, d15, d19);
		tessellator9.addVertexWithUV(d23, d5 + 1.0D, d25, d15, d17);
		tessellator9.addVertexWithUV(d23, d5 + 1.0D, d25, d13, d17);
		tessellator9.addVertexWithUV(d23, d5 + 0.0D, d25, d13, d19);
		tessellator9.addVertexWithUV(d21, d5 + 0.0D, d27, d15, d19);
		tessellator9.addVertexWithUV(d21, d5 + 1.0D, d27, d15, d17);
	}

	public void func_1245_b(Block block1, int i2, double d3, double d5, double d7) {
		Tessellator tessellator9 = Tessellator.instance;
		int i10 = block1.getBlockTextureFromSideAndMetadata(0, i2);
		if(this.overrideBlockTexture >= 0) {
			i10 = this.overrideBlockTexture;
		}

		int i11 = (i10 & 15) << 4;
		int i12 = i10 & 240;
		double d13 = (float)i11 / 256.0F;
		double d15 = ((float)i11 + 15.99F) / 256.0F;
		double d17 = (float)i12 / 256.0F;
		double d19 = ((float)i12 + 15.99F) / 256.0F;
		double d21 = d3 + 0.5D - 0.25D;
		double d23 = d3 + 0.5D + 0.25D;
		double d25 = d7 + 0.5D - 0.5D;
		double d27 = d7 + 0.5D + 0.5D;
		tessellator9.addVertexWithUV(d21, d5 + 1.0D, d25, d13, d17);
		tessellator9.addVertexWithUV(d21, d5 + 0.0D, d25, d13, d19);
		tessellator9.addVertexWithUV(d21, d5 + 0.0D, d27, d15, d19);
		tessellator9.addVertexWithUV(d21, d5 + 1.0D, d27, d15, d17);
		tessellator9.addVertexWithUV(d21, d5 + 1.0D, d27, d13, d17);
		tessellator9.addVertexWithUV(d21, d5 + 0.0D, d27, d13, d19);
		tessellator9.addVertexWithUV(d21, d5 + 0.0D, d25, d15, d19);
		tessellator9.addVertexWithUV(d21, d5 + 1.0D, d25, d15, d17);
		tessellator9.addVertexWithUV(d23, d5 + 1.0D, d27, d13, d17);
		tessellator9.addVertexWithUV(d23, d5 + 0.0D, d27, d13, d19);
		tessellator9.addVertexWithUV(d23, d5 + 0.0D, d25, d15, d19);
		tessellator9.addVertexWithUV(d23, d5 + 1.0D, d25, d15, d17);
		tessellator9.addVertexWithUV(d23, d5 + 1.0D, d25, d13, d17);
		tessellator9.addVertexWithUV(d23, d5 + 0.0D, d25, d13, d19);
		tessellator9.addVertexWithUV(d23, d5 + 0.0D, d27, d15, d19);
		tessellator9.addVertexWithUV(d23, d5 + 1.0D, d27, d15, d17);
		d21 = d3 + 0.5D - 0.5D;
		d23 = d3 + 0.5D + 0.5D;
		d25 = d7 + 0.5D - 0.25D;
		d27 = d7 + 0.5D + 0.25D;
		tessellator9.addVertexWithUV(d21, d5 + 1.0D, d25, d13, d17);
		tessellator9.addVertexWithUV(d21, d5 + 0.0D, d25, d13, d19);
		tessellator9.addVertexWithUV(d23, d5 + 0.0D, d25, d15, d19);
		tessellator9.addVertexWithUV(d23, d5 + 1.0D, d25, d15, d17);
		tessellator9.addVertexWithUV(d23, d5 + 1.0D, d25, d13, d17);
		tessellator9.addVertexWithUV(d23, d5 + 0.0D, d25, d13, d19);
		tessellator9.addVertexWithUV(d21, d5 + 0.0D, d25, d15, d19);
		tessellator9.addVertexWithUV(d21, d5 + 1.0D, d25, d15, d17);
		tessellator9.addVertexWithUV(d23, d5 + 1.0D, d27, d13, d17);
		tessellator9.addVertexWithUV(d23, d5 + 0.0D, d27, d13, d19);
		tessellator9.addVertexWithUV(d21, d5 + 0.0D, d27, d15, d19);
		tessellator9.addVertexWithUV(d21, d5 + 1.0D, d27, d15, d17);
		tessellator9.addVertexWithUV(d21, d5 + 1.0D, d27, d13, d17);
		tessellator9.addVertexWithUV(d21, d5 + 0.0D, d27, d13, d19);
		tessellator9.addVertexWithUV(d23, d5 + 0.0D, d27, d15, d19);
		tessellator9.addVertexWithUV(d23, d5 + 1.0D, d27, d15, d17);
	}

	public boolean renderBlockFluids(Block block1, int i2, int i3, int i4) {
		Tessellator tessellator5 = Tessellator.instance;
		int i6 = block1.colorMultiplier(this.blockAccess, i2, i3, i4);
		float f7 = (float)(i6 >> 16 & 255) / 255.0F;
		float f8 = (float)(i6 >> 8 & 255) / 255.0F;
		float f9 = (float)(i6 & 255) / 255.0F;
		boolean z10 = block1.shouldSideBeRendered(this.blockAccess, i2, i3 + 1, i4, 1);
		boolean z11 = block1.shouldSideBeRendered(this.blockAccess, i2, i3 - 1, i4, 0);
		boolean[] z12 = new boolean[]{block1.shouldSideBeRendered(this.blockAccess, i2, i3, i4 - 1, 2), block1.shouldSideBeRendered(this.blockAccess, i2, i3, i4 + 1, 3), block1.shouldSideBeRendered(this.blockAccess, i2 - 1, i3, i4, 4), block1.shouldSideBeRendered(this.blockAccess, i2 + 1, i3, i4, 5)};
		if(!z10 && !z11 && !z12[0] && !z12[1] && !z12[2] && !z12[3]) {
			return false;
		} else {
			boolean z13 = false;
			float f14 = 0.5F;
			float f15 = 1.0F;
			float f16 = 0.8F;
			float f17 = 0.6F;
			double d18 = 0.0D;
			double d20 = 1.0D;
			Material material22 = block1.blockMaterial;
			int i23 = this.blockAccess.getBlockMetadata(i2, i3, i4);
			float f24 = this.func_1224_a(i2, i3, i4, material22);
			float f25 = this.func_1224_a(i2, i3, i4 + 1, material22);
			float f26 = this.func_1224_a(i2 + 1, i3, i4 + 1, material22);
			float f27 = this.func_1224_a(i2 + 1, i3, i4, material22);
			int i28;
			int i31;
			float f36;
			float f37;
			float f38;
			if(this.renderAllFaces || z10) {
				z13 = true;
				i28 = block1.getBlockTextureFromSideAndMetadata(1, i23);
				float f29 = (float)BlockFluid.func_293_a(this.blockAccess, i2, i3, i4, material22);
				if(f29 > -999.0F) {
					i28 = block1.getBlockTextureFromSideAndMetadata(2, i23);
				}

				int i30 = (i28 & 15) << 4;
				i31 = i28 & 240;
				double d32 = ((double)i30 + 8.0D) / 256.0D;
				double d34 = ((double)i31 + 8.0D) / 256.0D;
				if(f29 < -999.0F) {
					f29 = 0.0F;
				} else {
					d32 = (float)(i30 + 16) / 256.0F;
					d34 = (float)(i31 + 16) / 256.0F;
				}

				f36 = MathHelper.sin(f29) * 8.0F / 256.0F;
				f37 = MathHelper.cos(f29) * 8.0F / 256.0F;
				f38 = block1.getBlockBrightness(this.blockAccess, i2, i3, i4);
				tessellator5.setColorOpaque_F(f15 * f38 * f7, f15 * f38 * f8, f15 * f38 * f9);
				tessellator5.addVertexWithUV(i2, (float)i3 + f24, i4, d32 - (double)f37 - (double)f36, d34 - (double)f37 + (double)f36);
				tessellator5.addVertexWithUV(i2, (float)i3 + f25, i4 + 1, d32 - (double)f37 + (double)f36, d34 + (double)f37 + (double)f36);
				tessellator5.addVertexWithUV(i2 + 1, (float)i3 + f26, i4 + 1, d32 + (double)f37 + (double)f36, d34 + (double)f37 - (double)f36);
				tessellator5.addVertexWithUV(i2 + 1, (float)i3 + f27, i4, d32 + (double)f37 - (double)f36, d34 - (double)f37 - (double)f36);
			}

			if(this.renderAllFaces || z11) {
				float f52 = block1.getBlockBrightness(this.blockAccess, i2, i3 - 1, i4);
				tessellator5.setColorOpaque_F(f14 * f52, f14 * f52, f14 * f52);
				this.renderBottomFace(block1, i2, i3, i4, block1.getBlockTextureFromSide(0));
				z13 = true;
			}

			for(i28 = 0; i28 < 4; ++i28) {
				int i53 = i2;
				i31 = i4;
				if(i28 == 0) {
					i31 = i4 - 1;
				}

				if(i28 == 1) {
					++i31;
				}

				if(i28 == 2) {
					i53 = i2 - 1;
				}

				if(i28 == 3) {
					++i53;
				}

				int i54 = block1.getBlockTextureFromSideAndMetadata(i28 + 2, i23);
				int i33 = (i54 & 15) << 4;
				int i55 = i54 & 240;
				if(this.renderAllFaces || z12[i28]) {
					float f35;
					float f39;
					float f40;
					if(i28 == 0) {
						f35 = f24;
						f36 = f27;
						f37 = (float)i2;
						f39 = (float)(i2 + 1);
						f38 = (float)i4;
						f40 = (float)i4;
					} else if(i28 == 1) {
						f35 = f26;
						f36 = f25;
						f37 = (float)(i2 + 1);
						f39 = (float)i2;
						f38 = (float)(i4 + 1);
						f40 = (float)(i4 + 1);
					} else if(i28 == 2) {
						f35 = f25;
						f36 = f24;
						f37 = (float)i2;
						f39 = (float)i2;
						f38 = (float)(i4 + 1);
						f40 = (float)i4;
					} else {
						f35 = f27;
						f36 = f26;
						f37 = (float)(i2 + 1);
						f39 = (float)(i2 + 1);
						f38 = (float)i4;
						f40 = (float)(i4 + 1);
					}

					z13 = true;
					double d41 = (float)(i33) / 256.0F;
					double d43 = ((double)(i33 + 16) - 0.01D) / 256.0D;
					double d45 = ((float)i55 + (1.0F - f35) * 16.0F) / 256.0F;
					double d47 = ((float)i55 + (1.0F - f36) * 16.0F) / 256.0F;
					double d49 = ((double)(i55 + 16) - 0.01D) / 256.0D;
					float f51 = block1.getBlockBrightness(this.blockAccess, i53, i3, i31);
					if(i28 < 2) {
						f51 *= f16;
					} else {
						f51 *= f17;
					}

					tessellator5.setColorOpaque_F(f15 * f51 * f7, f15 * f51 * f8, f15 * f51 * f9);
					tessellator5.addVertexWithUV(f37, (float)i3 + f35, f38, d41, d45);
					tessellator5.addVertexWithUV(f39, (float)i3 + f36, f40, d43, d47);
					tessellator5.addVertexWithUV(f39, i3, f40, d43, d49);
					tessellator5.addVertexWithUV(f37, i3, f38, d41, d49);
				}
			}

			block1.minY = d18;
			block1.maxY = d20;
			return z13;
		}
	}

	private float func_1224_a(int i1, int i2, int i3, Material material4) {
		int i5 = 0;
		float f6 = 0.0F;

		for(int i7 = 0; i7 < 4; ++i7) {
			int i8 = i1 - (i7 & 1);
			int i10 = i3 - (i7 >> 1 & 1);
			if(this.blockAccess.getBlockMaterial(i8, i2 + 1, i10) == material4) {
				return 1.0F;
			}

			Material material11 = this.blockAccess.getBlockMaterial(i8, i2, i10);
			if(material11 != material4) {
				if(!material11.isSolid()) {
					++f6;
					++i5;
				}
			} else {
				int i12 = this.blockAccess.getBlockMetadata(i8, i2, i10);
				if(i12 >= 8 || i12 == 0) {
					f6 += BlockFluid.getPercentAir(i12) * 10.0F;
					i5 += 10;
				}

				f6 += BlockFluid.getPercentAir(i12);
				++i5;
			}
		}

		return 1.0F - f6 / (float)i5;
	}

	public void renderBlockFallingSand(Block block1, World world2, int i3, int i4, int i5) {
		float f6 = 0.5F;
		float f7 = 1.0F;
		float f8 = 0.8F;
		float f9 = 0.6F;
		Tessellator tessellator10 = Tessellator.instance;
		tessellator10.startDrawingQuads();
		float f11 = block1.getBlockBrightness(world2, i3, i4, i5);
		float f12 = block1.getBlockBrightness(world2, i3, i4 - 1, i5);
		if(f12 < f11) {
			f12 = f11;
		}

		tessellator10.setColorOpaque_F(f6 * f12, f6 * f12, f6 * f12);
		this.renderBottomFace(block1, -0.5D, -0.5D, -0.5D, block1.getBlockTextureFromSide(0));
		f12 = block1.getBlockBrightness(world2, i3, i4 + 1, i5);
		if(f12 < f11) {
			f12 = f11;
		}

		tessellator10.setColorOpaque_F(f7 * f12, f7 * f12, f7 * f12);
		this.renderTopFace(block1, -0.5D, -0.5D, -0.5D, block1.getBlockTextureFromSide(1));
		f12 = block1.getBlockBrightness(world2, i3, i4, i5 - 1);
		if(f12 < f11) {
			f12 = f11;
		}

		tessellator10.setColorOpaque_F(f8 * f12, f8 * f12, f8 * f12);
		this.renderEastFace(block1, -0.5D, -0.5D, -0.5D, block1.getBlockTextureFromSide(2));
		f12 = block1.getBlockBrightness(world2, i3, i4, i5 + 1);
		if(f12 < f11) {
			f12 = f11;
		}

		tessellator10.setColorOpaque_F(f8 * f12, f8 * f12, f8 * f12);
		this.renderWestFace(block1, -0.5D, -0.5D, -0.5D, block1.getBlockTextureFromSide(3));
		f12 = block1.getBlockBrightness(world2, i3 - 1, i4, i5);
		if(f12 < f11) {
			f12 = f11;
		}

		tessellator10.setColorOpaque_F(f9 * f12, f9 * f12, f9 * f12);
		this.renderNorthFace(block1, -0.5D, -0.5D, -0.5D, block1.getBlockTextureFromSide(4));
		f12 = block1.getBlockBrightness(world2, i3 + 1, i4, i5);
		if(f12 < f11) {
			f12 = f11;
		}

		tessellator10.setColorOpaque_F(f9 * f12, f9 * f12, f9 * f12);
		this.renderSouthFace(block1, -0.5D, -0.5D, -0.5D, block1.getBlockTextureFromSide(5));
		tessellator10.draw();
	}

	public boolean renderStandardBlock(Block block1, int i2, int i3, int i4) {
		int i5 = block1.colorMultiplier(this.blockAccess, i2, i3, i4);
		float f6 = (float)(i5 >> 16 & 255) / 255.0F;
		float f7 = (float)(i5 >> 8 & 255) / 255.0F;
		float f8 = (float)(i5 & 255) / 255.0F;
		if(EntityRenderer.field_28135_a) {
			float f9 = (f6 * 30.0F + f7 * 59.0F + f8 * 11.0F) / 100.0F;
			float f10 = (f6 * 30.0F + f7 * 70.0F) / 100.0F;
			float f11 = (f6 * 30.0F + f8 * 70.0F) / 100.0F;
			f6 = f9;
			f7 = f10;
			f8 = f11;
		}

		return Minecraft.isAmbientOcclusionEnabled() ? this.renderStandardBlockWithAmbientOcclusion(block1, i2, i3, i4, f6, f7, f8) : this.renderStandardBlockWithColorMultiplier(block1, i2, i3, i4, f6, f7, f8);
	}

	public boolean renderStandardBlockWithAmbientOcclusion(Block block1, int i2, int i3, int i4, float f5, float f6, float f7) {
		this.enableAO = true;
		boolean z8 = false;
		float f9;
		float f10;
		float f11;
		float f12;
		boolean z13 = true;
		boolean z15 = true;
		boolean z16 = true;
		boolean z17 = true;
		boolean z18 = true;
		float lightValueOwn = block1.getBlockBrightness(this.blockAccess, i2, i3, i4);
		float aoLightValueXNeg = block1.getBlockBrightness(this.blockAccess, i2 - 1, i3, i4);
		float aoLightValueYNeg = block1.getBlockBrightness(this.blockAccess, i2, i3 - 1, i4);
		float aoLightValueZNeg = block1.getBlockBrightness(this.blockAccess, i2, i3, i4 - 1);
		float aoLightValueXPos = block1.getBlockBrightness(this.blockAccess, i2 + 1, i3, i4);
		float aoLightValueYPos = block1.getBlockBrightness(this.blockAccess, i2, i3 + 1, i4);
		float aoLightValueZPos = block1.getBlockBrightness(this.blockAccess, i2, i3, i4 + 1);
		boolean field_22338_U = Block.canBlockGrass[this.blockAccess.getBlockId(i2 + 1, i3 + 1, i4)];
		boolean field_22359_ac = Block.canBlockGrass[this.blockAccess.getBlockId(i2 + 1, i3 - 1, i4)];
		boolean field_22334_Y = Block.canBlockGrass[this.blockAccess.getBlockId(i2 + 1, i3, i4 + 1)];
		boolean field_22363_aa = Block.canBlockGrass[this.blockAccess.getBlockId(i2 + 1, i3, i4 - 1)];
		boolean field_22337_V = Block.canBlockGrass[this.blockAccess.getBlockId(i2 - 1, i3 + 1, i4)];
		boolean field_22357_ad = Block.canBlockGrass[this.blockAccess.getBlockId(i2 - 1, i3 - 1, i4)];
		boolean field_22335_X = Block.canBlockGrass[this.blockAccess.getBlockId(i2 - 1, i3, i4 - 1)];
		boolean field_22333_Z = Block.canBlockGrass[this.blockAccess.getBlockId(i2 - 1, i3, i4 + 1)];
		boolean field_22336_W = Block.canBlockGrass[this.blockAccess.getBlockId(i2, i3 + 1, i4 + 1)];
		boolean field_22339_T = Block.canBlockGrass[this.blockAccess.getBlockId(i2, i3 + 1, i4 - 1)];
		boolean field_22355_ae = Block.canBlockGrass[this.blockAccess.getBlockId(i2, i3 - 1, i4 + 1)];
		boolean field_22361_ab = Block.canBlockGrass[this.blockAccess.getBlockId(i2, i3 - 1, i4 - 1)];
		if(block1.blockIndexInTexture == 3) {
			z18 = false;
			z17 = false;
			z16 = false;
			z15 = false;
			z13 = false;
		}

		if(this.overrideBlockTexture >= 0) {
			z18 = false;
			z17 = false;
			z16 = false;
			z15 = false;
			z13 = false;
		}

		float field_22377_m;
		float field_22376_n;
		float field_22375_o;
		float field_22374_p;
		float field_22373_q;
		float field_22372_r;
		float field_22371_s;
		float field_22370_t;
		if(this.renderAllFaces || block1.shouldSideBeRendered(this.blockAccess, i2, i3 - 1, i4, 0)) {
			--i3;
			field_22376_n = block1.getBlockBrightness(this.blockAccess, i2 - 1, i3, i4);
			field_22374_p = block1.getBlockBrightness(this.blockAccess, i2, i3, i4 - 1);
			field_22373_q = block1.getBlockBrightness(this.blockAccess, i2, i3, i4 + 1);
			field_22371_s = block1.getBlockBrightness(this.blockAccess, i2 + 1, i3, i4);
			if(!field_22361_ab && !field_22357_ad) {
				field_22377_m = field_22376_n;
			} else {
				field_22377_m = block1.getBlockBrightness(this.blockAccess, i2 - 1, i3, i4 - 1);
			}

			if(!field_22355_ae && !field_22357_ad) {
				field_22375_o = field_22376_n;
			} else {
				field_22375_o = block1.getBlockBrightness(this.blockAccess, i2 - 1, i3, i4 + 1);
			}

			if(!field_22361_ab && !field_22359_ac) {
				field_22372_r = field_22371_s;
			} else {
				field_22372_r = block1.getBlockBrightness(this.blockAccess, i2 + 1, i3, i4 - 1);
			}

			if(!field_22355_ae && !field_22359_ac) {
				field_22370_t = field_22371_s;
			} else {
				field_22370_t = block1.getBlockBrightness(this.blockAccess, i2 + 1, i3, i4 + 1);
			}

			++i3;
			f9 = (field_22375_o + field_22376_n + field_22373_q + aoLightValueYNeg) / 4.0F;
			f12 = (field_22373_q + aoLightValueYNeg + field_22370_t + field_22371_s) / 4.0F;
			f11 = (aoLightValueYNeg + field_22374_p + field_22371_s + field_22372_r) / 4.0F;
			f10 = (field_22376_n + field_22377_m + aoLightValueYNeg + field_22374_p) / 4.0F;

			this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = (z13 ? f5 : 1.0F) * 0.5F;
			this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = (z13 ? f6 : 1.0F) * 0.5F;
			this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = (z13 ? f7 : 1.0F) * 0.5F;
			this.colorRedTopLeft *= f9;
			this.colorGreenTopLeft *= f9;
			this.colorBlueTopLeft *= f9;
			this.colorRedBottomLeft *= f10;
			this.colorGreenBottomLeft *= f10;
			this.colorBlueBottomLeft *= f10;
			this.colorRedBottomRight *= f11;
			this.colorGreenBottomRight *= f11;
			this.colorBlueBottomRight *= f11;
			this.colorRedTopRight *= f12;
			this.colorGreenTopRight *= f12;
			this.colorBlueTopRight *= f12;
			this.renderBottomFace(block1, i2, i3, i4, block1.getBlockTexture(this.blockAccess, i2, i3, i4, 0));
			z8 = true;
		}

		float field_22369_u;
		float field_22368_v;
		float field_22367_w;
		float field_22366_x;
		float field_22365_y;
		float field_22364_z;
		float field_22362_A;
		float field_22360_B;
		if(this.renderAllFaces || block1.shouldSideBeRendered(this.blockAccess, i2, i3 + 1, i4, 1)) {
			++i3;
			field_22368_v = block1.getBlockBrightness(this.blockAccess, i2 - 1, i3, i4);
			field_22364_z = block1.getBlockBrightness(this.blockAccess, i2 + 1, i3, i4);
			field_22366_x = block1.getBlockBrightness(this.blockAccess, i2, i3, i4 - 1);
			field_22362_A = block1.getBlockBrightness(this.blockAccess, i2, i3, i4 + 1);
			if(!field_22339_T && !field_22337_V) {
				field_22369_u = field_22368_v;
			} else {
				field_22369_u = block1.getBlockBrightness(this.blockAccess, i2 - 1, i3, i4 - 1);
			}

			if(!field_22339_T && !field_22338_U) {
				field_22365_y = field_22364_z;
			} else {
				field_22365_y = block1.getBlockBrightness(this.blockAccess, i2 + 1, i3, i4 - 1);
			}

			if(!field_22336_W && !field_22337_V) {
				field_22367_w = field_22368_v;
			} else {
				field_22367_w = block1.getBlockBrightness(this.blockAccess, i2 - 1, i3, i4 + 1);
			}

			if(!field_22336_W && !field_22338_U) {
				field_22360_B = field_22364_z;
			} else {
				field_22360_B = block1.getBlockBrightness(this.blockAccess, i2 + 1, i3, i4 + 1);
			}

			--i3;
			f12 = (field_22367_w + field_22368_v + field_22362_A + aoLightValueYPos) / 4.0F;
			f9 = (field_22362_A + aoLightValueYPos + field_22360_B + field_22364_z) / 4.0F;
			f10 = (aoLightValueYPos + field_22366_x + field_22364_z + field_22365_y) / 4.0F;
			f11 = (field_22368_v + field_22369_u + aoLightValueYPos + field_22366_x) / 4.0F;

			this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = f5;
			this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = f6;
			this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = f7;
			this.colorRedTopLeft *= f9;
			this.colorGreenTopLeft *= f9;
			this.colorBlueTopLeft *= f9;
			this.colorRedBottomLeft *= f10;
			this.colorGreenBottomLeft *= f10;
			this.colorBlueBottomLeft *= f10;
			this.colorRedBottomRight *= f11;
			this.colorGreenBottomRight *= f11;
			this.colorBlueBottomRight *= f11;
			this.colorRedTopRight *= f12;
			this.colorGreenTopRight *= f12;
			this.colorBlueTopRight *= f12;
			this.renderTopFace(block1, i2, i3, i4, block1.getBlockTexture(this.blockAccess, i2, i3, i4, 1));
			z8 = true;
		}

		int i19;
		float field_22358_C;
		float field_22356_D;
		if(this.renderAllFaces || block1.shouldSideBeRendered(this.blockAccess, i2, i3, i4 - 1, 2)) {
			--i4;
			field_22358_C = block1.getBlockBrightness(this.blockAccess, i2 - 1, i3, i4);
			field_22374_p = block1.getBlockBrightness(this.blockAccess, i2, i3 - 1, i4);
			field_22366_x = block1.getBlockBrightness(this.blockAccess, i2, i3 + 1, i4);
			field_22356_D = block1.getBlockBrightness(this.blockAccess, i2 + 1, i3, i4);
			if(!field_22335_X && !field_22361_ab) {
				field_22377_m = field_22358_C;
			} else {
				field_22377_m = block1.getBlockBrightness(this.blockAccess, i2 - 1, i3 - 1, i4);
			}

			if(!field_22335_X && !field_22339_T) {
				field_22369_u = field_22358_C;
			} else {
				field_22369_u = block1.getBlockBrightness(this.blockAccess, i2 - 1, i3 + 1, i4);
			}

			if(!field_22363_aa && !field_22361_ab) {
				field_22372_r = field_22356_D;
			} else {
				field_22372_r = block1.getBlockBrightness(this.blockAccess, i2 + 1, i3 - 1, i4);
			}

			if(!field_22363_aa && !field_22339_T) {
				field_22365_y = field_22356_D;
			} else {
				field_22365_y = block1.getBlockBrightness(this.blockAccess, i2 + 1, i3 + 1, i4);
			}

			++i4;
			f9 = (field_22358_C + field_22369_u + aoLightValueZNeg + field_22366_x) / 4.0F;
			f10 = (aoLightValueZNeg + field_22366_x + field_22356_D + field_22365_y) / 4.0F;
			f11 = (field_22374_p + aoLightValueZNeg + field_22372_r + field_22356_D) / 4.0F;
			f12 = (field_22377_m + field_22358_C + field_22374_p + aoLightValueZNeg) / 4.0F;

			this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = (z15 ? f5 : 1.0F) * 0.8F;
			this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = (z15 ? f6 : 1.0F) * 0.8F;
			this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = (z15 ? f7 : 1.0F) * 0.8F;
			this.colorRedTopLeft *= f9;
			this.colorGreenTopLeft *= f9;
			this.colorBlueTopLeft *= f9;
			this.colorRedBottomLeft *= f10;
			this.colorGreenBottomLeft *= f10;
			this.colorBlueBottomLeft *= f10;
			this.colorRedBottomRight *= f11;
			this.colorGreenBottomRight *= f11;
			this.colorBlueBottomRight *= f11;
			this.colorRedTopRight *= f12;
			this.colorGreenTopRight *= f12;
			this.colorBlueTopRight *= f12;
			i19 = block1.getBlockTexture(this.blockAccess, i2, i3, i4, 2);
			this.renderEastFace(block1, i2, i3, i4, i19);
			if(fancyGrass && i19 == 3 && this.overrideBlockTexture < 0) {
				this.colorRedTopLeft *= f5;
				this.colorRedBottomLeft *= f5;
				this.colorRedBottomRight *= f5;
				this.colorRedTopRight *= f5;
				this.colorGreenTopLeft *= f6;
				this.colorGreenBottomLeft *= f6;
				this.colorGreenBottomRight *= f6;
				this.colorGreenTopRight *= f6;
				this.colorBlueTopLeft *= f7;
				this.colorBlueBottomLeft *= f7;
				this.colorBlueBottomRight *= f7;
				this.colorBlueTopRight *= f7;
				this.renderEastFace(block1, i2, i3, i4, 38);
			}

			z8 = true;
		}

		float field_22354_E;
		float field_22353_F;
		if(this.renderAllFaces || block1.shouldSideBeRendered(this.blockAccess, i2, i3, i4 + 1, 3)) {
			++i4;
			field_22354_E = block1.getBlockBrightness(this.blockAccess, i2 - 1, i3, i4);
			field_22353_F = block1.getBlockBrightness(this.blockAccess, i2 + 1, i3, i4);
			field_22373_q = block1.getBlockBrightness(this.blockAccess, i2, i3 - 1, i4);
			field_22362_A = block1.getBlockBrightness(this.blockAccess, i2, i3 + 1, i4);
			if(!field_22333_Z && !field_22355_ae) {
				field_22375_o = field_22354_E;
			} else {
				field_22375_o = block1.getBlockBrightness(this.blockAccess, i2 - 1, i3 - 1, i4);
			}

			if(!field_22333_Z && !field_22336_W) {
				field_22367_w = field_22354_E;
			} else {
				field_22367_w = block1.getBlockBrightness(this.blockAccess, i2 - 1, i3 + 1, i4);
			}

			if(!field_22334_Y && !field_22355_ae) {
				field_22370_t = field_22353_F;
			} else {
				field_22370_t = block1.getBlockBrightness(this.blockAccess, i2 + 1, i3 - 1, i4);
			}

			if(!field_22334_Y && !field_22336_W) {
				field_22360_B = field_22353_F;
			} else {
				field_22360_B = block1.getBlockBrightness(this.blockAccess, i2 + 1, i3 + 1, i4);
			}

			--i4;
			f9 = (field_22354_E + field_22367_w + aoLightValueZPos + field_22362_A) / 4.0F;
			f12 = (aoLightValueZPos + field_22362_A + field_22353_F + field_22360_B) / 4.0F;
			f11 = (field_22373_q + aoLightValueZPos + field_22370_t + field_22353_F) / 4.0F;
			f10 = (field_22375_o + field_22354_E + field_22373_q + aoLightValueZPos) / 4.0F;

			this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = (z16 ? f5 : 1.0F) * 0.8F;
			this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = (z16 ? f6 : 1.0F) * 0.8F;
			this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = (z16 ? f7 : 1.0F) * 0.8F;
			this.colorRedTopLeft *= f9;
			this.colorGreenTopLeft *= f9;
			this.colorBlueTopLeft *= f9;
			this.colorRedBottomLeft *= f10;
			this.colorGreenBottomLeft *= f10;
			this.colorBlueBottomLeft *= f10;
			this.colorRedBottomRight *= f11;
			this.colorGreenBottomRight *= f11;
			this.colorBlueBottomRight *= f11;
			this.colorRedTopRight *= f12;
			this.colorGreenTopRight *= f12;
			this.colorBlueTopRight *= f12;
			i19 = block1.getBlockTexture(this.blockAccess, i2, i3, i4, 3);
			this.renderWestFace(block1, i2, i3, i4, block1.getBlockTexture(this.blockAccess, i2, i3, i4, 3));
			if(fancyGrass && i19 == 3 && this.overrideBlockTexture < 0) {
				this.colorRedTopLeft *= f5;
				this.colorRedBottomLeft *= f5;
				this.colorRedBottomRight *= f5;
				this.colorRedTopRight *= f5;
				this.colorGreenTopLeft *= f6;
				this.colorGreenBottomLeft *= f6;
				this.colorGreenBottomRight *= f6;
				this.colorGreenTopRight *= f6;
				this.colorBlueTopLeft *= f7;
				this.colorBlueBottomLeft *= f7;
				this.colorBlueBottomRight *= f7;
				this.colorBlueTopRight *= f7;
				this.renderWestFace(block1, i2, i3, i4, 38);
			}

			z8 = true;
		}

		if(this.renderAllFaces || block1.shouldSideBeRendered(this.blockAccess, i2 - 1, i3, i4, 4)) {
			--i2;
			field_22376_n = block1.getBlockBrightness(this.blockAccess, i2, i3 - 1, i4);
			field_22358_C = block1.getBlockBrightness(this.blockAccess, i2, i3, i4 - 1);
			field_22354_E = block1.getBlockBrightness(this.blockAccess, i2, i3, i4 + 1);
			field_22368_v = block1.getBlockBrightness(this.blockAccess, i2, i3 + 1, i4);
			if(!field_22335_X && !field_22357_ad) {
				field_22377_m = field_22358_C;
			} else {
				field_22377_m = block1.getBlockBrightness(this.blockAccess, i2, i3 - 1, i4 - 1);
			}

			if(!field_22333_Z && !field_22357_ad) {
				field_22375_o = field_22354_E;
			} else {
				field_22375_o = block1.getBlockBrightness(this.blockAccess, i2, i3 - 1, i4 + 1);
			}

			if(!field_22335_X && !field_22337_V) {
				field_22369_u = field_22358_C;
			} else {
				field_22369_u = block1.getBlockBrightness(this.blockAccess, i2, i3 + 1, i4 - 1);
			}

			if(!field_22333_Z && !field_22337_V) {
				field_22367_w = field_22354_E;
			} else {
				field_22367_w = block1.getBlockBrightness(this.blockAccess, i2, i3 + 1, i4 + 1);
			}

			++i2;
			f12 = (field_22376_n + field_22375_o + aoLightValueXNeg + field_22354_E) / 4.0F;
			f9 = (aoLightValueXNeg + field_22354_E + field_22368_v + field_22367_w) / 4.0F;
			f10 = (field_22358_C + aoLightValueXNeg + field_22369_u + field_22368_v) / 4.0F;
			f11 = (field_22377_m + field_22376_n + field_22358_C + aoLightValueXNeg) / 4.0F;

			this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = (z17 ? f5 : 1.0F) * 0.6F;
			this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = (z17 ? f6 : 1.0F) * 0.6F;
			this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = (z17 ? f7 : 1.0F) * 0.6F;
			this.colorRedTopLeft *= f9;
			this.colorGreenTopLeft *= f9;
			this.colorBlueTopLeft *= f9;
			this.colorRedBottomLeft *= f10;
			this.colorGreenBottomLeft *= f10;
			this.colorBlueBottomLeft *= f10;
			this.colorRedBottomRight *= f11;
			this.colorGreenBottomRight *= f11;
			this.colorBlueBottomRight *= f11;
			this.colorRedTopRight *= f12;
			this.colorGreenTopRight *= f12;
			this.colorBlueTopRight *= f12;
			i19 = block1.getBlockTexture(this.blockAccess, i2, i3, i4, 4);
			this.renderNorthFace(block1, i2, i3, i4, i19);
			if(fancyGrass && i19 == 3 && this.overrideBlockTexture < 0) {
				this.colorRedTopLeft *= f5;
				this.colorRedBottomLeft *= f5;
				this.colorRedBottomRight *= f5;
				this.colorRedTopRight *= f5;
				this.colorGreenTopLeft *= f6;
				this.colorGreenBottomLeft *= f6;
				this.colorGreenBottomRight *= f6;
				this.colorGreenTopRight *= f6;
				this.colorBlueTopLeft *= f7;
				this.colorBlueBottomLeft *= f7;
				this.colorBlueBottomRight *= f7;
				this.colorBlueTopRight *= f7;
				this.renderNorthFace(block1, i2, i3, i4, 38);
			}

			z8 = true;
		}

		if(this.renderAllFaces || block1.shouldSideBeRendered(this.blockAccess, i2 + 1, i3, i4, 5)) {
			++i2;
			field_22371_s = block1.getBlockBrightness(this.blockAccess, i2, i3 - 1, i4);
			field_22356_D = block1.getBlockBrightness(this.blockAccess, i2, i3, i4 - 1);
			field_22353_F = block1.getBlockBrightness(this.blockAccess, i2, i3, i4 + 1);
			field_22364_z = block1.getBlockBrightness(this.blockAccess, i2, i3 + 1, i4);
			if(!field_22359_ac && !field_22363_aa) {
				field_22372_r = field_22356_D;
			} else {
				field_22372_r = block1.getBlockBrightness(this.blockAccess, i2, i3 - 1, i4 - 1);
			}

			if(!field_22359_ac && !field_22334_Y) {
				field_22370_t = field_22353_F;
			} else {
				field_22370_t = block1.getBlockBrightness(this.blockAccess, i2, i3 - 1, i4 + 1);
			}

			if(!field_22338_U && !field_22363_aa) {
				field_22365_y = field_22356_D;
			} else {
				field_22365_y = block1.getBlockBrightness(this.blockAccess, i2, i3 + 1, i4 - 1);
			}

			if(!field_22338_U && !field_22334_Y) {
				field_22360_B = field_22353_F;
			} else {
				field_22360_B = block1.getBlockBrightness(this.blockAccess, i2, i3 + 1, i4 + 1);
			}

			--i2;
			f9 = (field_22371_s + field_22370_t + aoLightValueXPos + field_22353_F) / 4.0F;
			f12 = (aoLightValueXPos + field_22353_F + field_22364_z + field_22360_B) / 4.0F;
			f11 = (field_22356_D + aoLightValueXPos + field_22365_y + field_22364_z) / 4.0F;
			f10 = (field_22372_r + field_22371_s + field_22356_D + aoLightValueXPos) / 4.0F;

			this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = (z18 ? f5 : 1.0F) * 0.6F;
			this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = (z18 ? f6 : 1.0F) * 0.6F;
			this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = (z18 ? f7 : 1.0F) * 0.6F;
			this.colorRedTopLeft *= f9;
			this.colorGreenTopLeft *= f9;
			this.colorBlueTopLeft *= f9;
			this.colorRedBottomLeft *= f10;
			this.colorGreenBottomLeft *= f10;
			this.colorBlueBottomLeft *= f10;
			this.colorRedBottomRight *= f11;
			this.colorGreenBottomRight *= f11;
			this.colorBlueBottomRight *= f11;
			this.colorRedTopRight *= f12;
			this.colorGreenTopRight *= f12;
			this.colorBlueTopRight *= f12;
			i19 = block1.getBlockTexture(this.blockAccess, i2, i3, i4, 5);
			this.renderSouthFace(block1, i2, i3, i4, i19);
			if(fancyGrass && i19 == 3 && this.overrideBlockTexture < 0) {
				this.colorRedTopLeft *= f5;
				this.colorRedBottomLeft *= f5;
				this.colorRedBottomRight *= f5;
				this.colorRedTopRight *= f5;
				this.colorGreenTopLeft *= f6;
				this.colorGreenBottomLeft *= f6;
				this.colorGreenBottomRight *= f6;
				this.colorGreenTopRight *= f6;
				this.colorBlueTopLeft *= f7;
				this.colorBlueBottomLeft *= f7;
				this.colorBlueBottomRight *= f7;
				this.colorBlueTopRight *= f7;
				this.renderSouthFace(block1, i2, i3, i4, 38);
			}

			z8 = true;
		}

		this.enableAO = false;
		return z8;
	}

	public boolean renderStandardBlockWithColorMultiplier(Block block1, int i2, int i3, int i4, float f5, float f6, float f7) {
		this.enableAO = false;
		Tessellator tessellator8 = Tessellator.instance;
		boolean z9 = false;
		float f10 = 0.5F;
		float f11 = 1.0F;
		float f12 = 0.8F;
		float f13 = 0.6F;
		float f14 = f11 * f5;
		float f15 = f11 * f6;
		float f16 = f11 * f7;
		float f17 = f10;
		float f18 = f12;
		float f19 = f13;
		float f20 = f10;
		float f21 = f12;
		float f22 = f13;
		float f23 = f10;
		float f24 = f12;
		float f25 = f13;
		if(block1 != Block.grass) {
			f17 = f10 * f5;
			f18 = f12 * f5;
			f19 = f13 * f5;
			f20 = f10 * f6;
			f21 = f12 * f6;
			f22 = f13 * f6;
			f23 = f10 * f7;
			f24 = f12 * f7;
			f25 = f13 * f7;
		}

		float f26 = block1.getBlockBrightness(this.blockAccess, i2, i3, i4);
		float f27;
		if(this.renderAllFaces || block1.shouldSideBeRendered(this.blockAccess, i2, i3 - 1, i4, 0)) {
			f27 = block1.getBlockBrightness(this.blockAccess, i2, i3 - 1, i4);
			tessellator8.setColorOpaque_F(f17 * f27, f20 * f27, f23 * f27);
			this.renderBottomFace(block1, i2, i3, i4, block1.getBlockTexture(this.blockAccess, i2, i3, i4, 0));
			z9 = true;
		}

		if(this.renderAllFaces || block1.shouldSideBeRendered(this.blockAccess, i2, i3 + 1, i4, 1)) {
			f27 = block1.getBlockBrightness(this.blockAccess, i2, i3 + 1, i4);
			if(block1.maxY != 1.0D && !block1.blockMaterial.getIsLiquid()) {
				f27 = f26;
			}

			tessellator8.setColorOpaque_F(f14 * f27, f15 * f27, f16 * f27);
			this.renderTopFace(block1, i2, i3, i4, block1.getBlockTexture(this.blockAccess, i2, i3, i4, 1));
			z9 = true;
		}

		int i28;
		if(this.renderAllFaces || block1.shouldSideBeRendered(this.blockAccess, i2, i3, i4 - 1, 2)) {
			f27 = block1.getBlockBrightness(this.blockAccess, i2, i3, i4 - 1);
			if(block1.minZ > 0.0D) {
				f27 = f26;
			}

			tessellator8.setColorOpaque_F(f18 * f27, f21 * f27, f24 * f27);
			i28 = block1.getBlockTexture(this.blockAccess, i2, i3, i4, 2);
			this.renderEastFace(block1, i2, i3, i4, i28);
			if(fancyGrass && i28 == 3 && this.overrideBlockTexture < 0) {
				tessellator8.setColorOpaque_F(f18 * f27 * f5, f21 * f27 * f6, f24 * f27 * f7);
				this.renderEastFace(block1, i2, i3, i4, 38);
			}

			z9 = true;
		}

		if(this.renderAllFaces || block1.shouldSideBeRendered(this.blockAccess, i2, i3, i4 + 1, 3)) {
			f27 = block1.getBlockBrightness(this.blockAccess, i2, i3, i4 + 1);
			if(block1.maxZ < 1.0D) {
				f27 = f26;
			}

			tessellator8.setColorOpaque_F(f18 * f27, f21 * f27, f24 * f27);
			i28 = block1.getBlockTexture(this.blockAccess, i2, i3, i4, 3);
			this.renderWestFace(block1, i2, i3, i4, i28);
			if(fancyGrass && i28 == 3 && this.overrideBlockTexture < 0) {
				tessellator8.setColorOpaque_F(f18 * f27 * f5, f21 * f27 * f6, f24 * f27 * f7);
				this.renderWestFace(block1, i2, i3, i4, 38);
			}

			z9 = true;
		}

		if(this.renderAllFaces || block1.shouldSideBeRendered(this.blockAccess, i2 - 1, i3, i4, 4)) {
			f27 = block1.getBlockBrightness(this.blockAccess, i2 - 1, i3, i4);
			if(block1.minX > 0.0D) {
				f27 = f26;
			}

			tessellator8.setColorOpaque_F(f19 * f27, f22 * f27, f25 * f27);
			i28 = block1.getBlockTexture(this.blockAccess, i2, i3, i4, 4);
			this.renderNorthFace(block1, i2, i3, i4, i28);
			if(fancyGrass && i28 == 3 && this.overrideBlockTexture < 0) {
				tessellator8.setColorOpaque_F(f19 * f27 * f5, f22 * f27 * f6, f25 * f27 * f7);
				this.renderNorthFace(block1, i2, i3, i4, 38);
			}

			z9 = true;
		}

		if(this.renderAllFaces || block1.shouldSideBeRendered(this.blockAccess, i2 + 1, i3, i4, 5)) {
			f27 = block1.getBlockBrightness(this.blockAccess, i2 + 1, i3, i4);
			if(block1.maxX < 1.0D) {
				f27 = f26;
			}

			tessellator8.setColorOpaque_F(f19 * f27, f22 * f27, f25 * f27);
			i28 = block1.getBlockTexture(this.blockAccess, i2, i3, i4, 5);
			this.renderSouthFace(block1, i2, i3, i4, i28);
			if(fancyGrass && i28 == 3 && this.overrideBlockTexture < 0) {
				tessellator8.setColorOpaque_F(f19 * f27 * f5, f22 * f27 * f6, f25 * f27 * f7);
				this.renderSouthFace(block1, i2, i3, i4, 38);
			}

			z9 = true;
		}

		return z9;
	}

	public boolean renderBlockCactus(Block block1, int i2, int i3, int i4) {
		int i5 = block1.colorMultiplier(this.blockAccess, i2, i3, i4);
		float f6 = (float)(i5 >> 16 & 255) / 255.0F;
		float f7 = (float)(i5 >> 8 & 255) / 255.0F;
		float f8 = (float)(i5 & 255) / 255.0F;
		if(EntityRenderer.field_28135_a) {
			float f9 = (f6 * 30.0F + f7 * 59.0F + f8 * 11.0F) / 100.0F;
			float f10 = (f6 * 30.0F + f7 * 70.0F) / 100.0F;
			float f11 = (f6 * 30.0F + f8 * 70.0F) / 100.0F;
			f6 = f9;
			f7 = f10;
			f8 = f11;
		}

		return this.func_1230_b(block1, i2, i3, i4, f6, f7, f8);
	}

	public boolean func_1230_b(Block block1, int i2, int i3, int i4, float f5, float f6, float f7) {
		Tessellator tessellator8 = Tessellator.instance;
		boolean z9 = false;
		float f10 = 0.5F;
		float f11 = 1.0F;
		float f12 = 0.8F;
		float f13 = 0.6F;
		float f14 = f10 * f5;
		float f15 = f11 * f5;
		float f16 = f12 * f5;
		float f17 = f13 * f5;
		float f18 = f10 * f6;
		float f19 = f11 * f6;
		float f20 = f12 * f6;
		float f21 = f13 * f6;
		float f22 = f10 * f7;
		float f23 = f11 * f7;
		float f24 = f12 * f7;
		float f25 = f13 * f7;
		float f26 = 0.0625F;
		float f27 = block1.getBlockBrightness(this.blockAccess, i2, i3, i4);
		float f28;
		if(this.renderAllFaces || block1.shouldSideBeRendered(this.blockAccess, i2, i3 - 1, i4, 0)) {
			f28 = block1.getBlockBrightness(this.blockAccess, i2, i3 - 1, i4);
			tessellator8.setColorOpaque_F(f14 * f28, f18 * f28, f22 * f28);
			this.renderBottomFace(block1, i2, i3, i4, block1.getBlockTexture(this.blockAccess, i2, i3, i4, 0));
			z9 = true;
		}

		if(this.renderAllFaces || block1.shouldSideBeRendered(this.blockAccess, i2, i3 + 1, i4, 1)) {
			f28 = block1.getBlockBrightness(this.blockAccess, i2, i3 + 1, i4);
			if(block1.maxY != 1.0D && !block1.blockMaterial.getIsLiquid()) {
				f28 = f27;
			}

			tessellator8.setColorOpaque_F(f15 * f28, f19 * f28, f23 * f28);
			this.renderTopFace(block1, i2, i3, i4, block1.getBlockTexture(this.blockAccess, i2, i3, i4, 1));
			z9 = true;
		}

		if(this.renderAllFaces || block1.shouldSideBeRendered(this.blockAccess, i2, i3, i4 - 1, 2)) {
			f28 = block1.getBlockBrightness(this.blockAccess, i2, i3, i4 - 1);
			if(block1.minZ > 0.0D) {
				f28 = f27;
			}

			tessellator8.setColorOpaque_F(f16 * f28, f20 * f28, f24 * f28);
			tessellator8.setTranslationF(0.0F, 0.0F, f26);
			this.renderEastFace(block1, i2, i3, i4, block1.getBlockTexture(this.blockAccess, i2, i3, i4, 2));
			tessellator8.setTranslationF(0.0F, 0.0F, -f26);
			z9 = true;
		}

		if(this.renderAllFaces || block1.shouldSideBeRendered(this.blockAccess, i2, i3, i4 + 1, 3)) {
			f28 = block1.getBlockBrightness(this.blockAccess, i2, i3, i4 + 1);
			if(block1.maxZ < 1.0D) {
				f28 = f27;
			}

			tessellator8.setColorOpaque_F(f16 * f28, f20 * f28, f24 * f28);
			tessellator8.setTranslationF(0.0F, 0.0F, -f26);
			this.renderWestFace(block1, i2, i3, i4, block1.getBlockTexture(this.blockAccess, i2, i3, i4, 3));
			tessellator8.setTranslationF(0.0F, 0.0F, f26);
			z9 = true;
		}

		if(this.renderAllFaces || block1.shouldSideBeRendered(this.blockAccess, i2 - 1, i3, i4, 4)) {
			f28 = block1.getBlockBrightness(this.blockAccess, i2 - 1, i3, i4);
			if(block1.minX > 0.0D) {
				f28 = f27;
			}

			tessellator8.setColorOpaque_F(f17 * f28, f21 * f28, f25 * f28);
			tessellator8.setTranslationF(f26, 0.0F, 0.0F);
			this.renderNorthFace(block1, i2, i3, i4, block1.getBlockTexture(this.blockAccess, i2, i3, i4, 4));
			tessellator8.setTranslationF(-f26, 0.0F, 0.0F);
			z9 = true;
		}

		if(this.renderAllFaces || block1.shouldSideBeRendered(this.blockAccess, i2 + 1, i3, i4, 5)) {
			f28 = block1.getBlockBrightness(this.blockAccess, i2 + 1, i3, i4);
			if(block1.maxX < 1.0D) {
				f28 = f27;
			}

			tessellator8.setColorOpaque_F(f17 * f28, f21 * f28, f25 * f28);
			tessellator8.setTranslationF(-f26, 0.0F, 0.0F);
			this.renderSouthFace(block1, i2, i3, i4, block1.getBlockTexture(this.blockAccess, i2, i3, i4, 5));
			tessellator8.setTranslationF(f26, 0.0F, 0.0F);
			z9 = true;
		}

		return z9;
	}

	public boolean renderBlockFence(Block block1, int i2, int i3, int i4) {
		boolean z5 = false;
		float f6 = 0.375F;
		float f7 = 0.625F;
		block1.setBlockBounds(f6, 0.0F, f6, f7, 1.0F, f7);
		this.renderStandardBlock(block1, i2, i3, i4);
		z5 = true;
		boolean z8 = false;
		boolean z9 = false;
		if(this.blockAccess.getBlockId(i2 - 1, i3, i4) == block1.blockID || this.blockAccess.getBlockId(i2 + 1, i3, i4) == block1.blockID) {
			z8 = true;
		}

		if(this.blockAccess.getBlockId(i2, i3, i4 - 1) == block1.blockID || this.blockAccess.getBlockId(i2, i3, i4 + 1) == block1.blockID) {
			z9 = true;
		}

		boolean z10 = this.blockAccess.getBlockId(i2 - 1, i3, i4) == block1.blockID;
		boolean z11 = this.blockAccess.getBlockId(i2 + 1, i3, i4) == block1.blockID;
		boolean z12 = this.blockAccess.getBlockId(i2, i3, i4 - 1) == block1.blockID;
		boolean z13 = this.blockAccess.getBlockId(i2, i3, i4 + 1) == block1.blockID;
		if(!z8 && !z9) {
			z8 = true;
		}

		f6 = 0.4375F;
		f7 = 0.5625F;
		float f14 = 0.75F;
		float f15 = 0.9375F;
		float f16 = z10 ? 0.0F : f6;
		float f17 = z11 ? 1.0F : f7;
		float f18 = z12 ? 0.0F : f6;
		float f19 = z13 ? 1.0F : f7;
		if(z8) {
			block1.setBlockBounds(f16, f14, f6, f17, f15, f7);
			this.renderStandardBlock(block1, i2, i3, i4);
		}

		if(z9) {
			block1.setBlockBounds(f6, f14, f18, f7, f15, f19);
			this.renderStandardBlock(block1, i2, i3, i4);
		}

		f14 = 0.375F;
		f15 = 0.5625F;
		if(z8) {
			block1.setBlockBounds(f16, f14, f6, f17, f15, f7);
			this.renderStandardBlock(block1, i2, i3, i4);
		}

		if(z9) {
			block1.setBlockBounds(f6, f14, f18, f7, f15, f19);
			this.renderStandardBlock(block1, i2, i3, i4);
		}

		block1.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		return z5;
	}

	public boolean renderBlockStairs(Block block1, int i2, int i3, int i4) {
		boolean z5 = false;
		int i6 = this.blockAccess.getBlockMetadata(i2, i3, i4);
		if(i6 == 0) {
			block1.setBlockBounds(0.0F, 0.0F, 0.0F, 0.5F, 0.5F, 1.0F);
			this.renderStandardBlock(block1, i2, i3, i4);
			block1.setBlockBounds(0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
			this.renderStandardBlock(block1, i2, i3, i4);
			z5 = true;
		} else if(i6 == 1) {
			block1.setBlockBounds(0.0F, 0.0F, 0.0F, 0.5F, 1.0F, 1.0F);
			this.renderStandardBlock(block1, i2, i3, i4);
			block1.setBlockBounds(0.5F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
			this.renderStandardBlock(block1, i2, i3, i4);
			z5 = true;
		} else if(i6 == 2) {
			block1.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 0.5F);
			this.renderStandardBlock(block1, i2, i3, i4);
			block1.setBlockBounds(0.0F, 0.0F, 0.5F, 1.0F, 1.0F, 1.0F);
			this.renderStandardBlock(block1, i2, i3, i4);
			z5 = true;
		} else if(i6 == 3) {
			block1.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.5F);
			this.renderStandardBlock(block1, i2, i3, i4);
			block1.setBlockBounds(0.0F, 0.0F, 0.5F, 1.0F, 0.5F, 1.0F);
			this.renderStandardBlock(block1, i2, i3, i4);
			z5 = true;
		}

		block1.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		return z5;
	}

	public boolean renderBlockDoor(Block block1, int i2, int i3, int i4) {
		Tessellator tessellator5 = Tessellator.instance;
		BlockDoor blockDoor6 = (BlockDoor)block1;
		boolean z7 = false;
		float f8 = 0.5F;
		float f9 = 1.0F;
		float f10 = 0.8F;
		float f11 = 0.6F;
		float f12 = block1.getBlockBrightness(this.blockAccess, i2, i3, i4);
		float f13 = block1.getBlockBrightness(this.blockAccess, i2, i3 - 1, i4);
		if(blockDoor6.minY > 0.0D) {
			f13 = f12;
		}

		if(Block.lightValue[block1.blockID] > 0) {
			f13 = 1.0F;
		}

		tessellator5.setColorOpaque_F(f8 * f13, f8 * f13, f8 * f13);
		this.renderBottomFace(block1, i2, i3, i4, block1.getBlockTexture(this.blockAccess, i2, i3, i4, 0));
		z7 = true;
		f13 = block1.getBlockBrightness(this.blockAccess, i2, i3 + 1, i4);
		if(blockDoor6.maxY < 1.0D) {
			f13 = f12;
		}

		if(Block.lightValue[block1.blockID] > 0) {
			f13 = 1.0F;
		}

		tessellator5.setColorOpaque_F(f9 * f13, f9 * f13, f9 * f13);
		this.renderTopFace(block1, i2, i3, i4, block1.getBlockTexture(this.blockAccess, i2, i3, i4, 1));
		f13 = block1.getBlockBrightness(this.blockAccess, i2, i3, i4 - 1);
		if(blockDoor6.minZ > 0.0D) {
			f13 = f12;
		}

		if(Block.lightValue[block1.blockID] > 0) {
			f13 = 1.0F;
		}

		tessellator5.setColorOpaque_F(f10 * f13, f10 * f13, f10 * f13);
		int i14 = block1.getBlockTexture(this.blockAccess, i2, i3, i4, 2);
		if(i14 < 0) {
			this.flipTexture = true;
			i14 = -i14;
		}

		this.renderEastFace(block1, i2, i3, i4, i14);
		this.flipTexture = false;
		f13 = block1.getBlockBrightness(this.blockAccess, i2, i3, i4 + 1);
		if(blockDoor6.maxZ < 1.0D) {
			f13 = f12;
		}

		if(Block.lightValue[block1.blockID] > 0) {
			f13 = 1.0F;
		}

		tessellator5.setColorOpaque_F(f10 * f13, f10 * f13, f10 * f13);
		i14 = block1.getBlockTexture(this.blockAccess, i2, i3, i4, 3);
		if(i14 < 0) {
			this.flipTexture = true;
			i14 = -i14;
		}

		this.renderWestFace(block1, i2, i3, i4, i14);
		this.flipTexture = false;
		f13 = block1.getBlockBrightness(this.blockAccess, i2 - 1, i3, i4);
		if(blockDoor6.minX > 0.0D) {
			f13 = f12;
		}

		if(Block.lightValue[block1.blockID] > 0) {
			f13 = 1.0F;
		}

		tessellator5.setColorOpaque_F(f11 * f13, f11 * f13, f11 * f13);
		i14 = block1.getBlockTexture(this.blockAccess, i2, i3, i4, 4);
		if(i14 < 0) {
			this.flipTexture = true;
			i14 = -i14;
		}

		this.renderNorthFace(block1, i2, i3, i4, i14);
		this.flipTexture = false;
		f13 = block1.getBlockBrightness(this.blockAccess, i2 + 1, i3, i4);
		if(blockDoor6.maxX < 1.0D) {
			f13 = f12;
		}

		if(Block.lightValue[block1.blockID] > 0) {
			f13 = 1.0F;
		}

		tessellator5.setColorOpaque_F(f11 * f13, f11 * f13, f11 * f13);
		i14 = block1.getBlockTexture(this.blockAccess, i2, i3, i4, 5);
		if(i14 < 0) {
			this.flipTexture = true;
			i14 = -i14;
		}

		this.renderSouthFace(block1, i2, i3, i4, i14);
		this.flipTexture = false;
		return z7;
	}

	public void renderBottomFace(Block block1, double d2, double d4, double d6, int i8) {
		Tessellator tessellator9 = Tessellator.instance;
		if(this.overrideBlockTexture >= 0) {
			i8 = this.overrideBlockTexture;
		}

		int i10 = (i8 & 15) << 4;
		int i11 = i8 & 240;
		double d12 = ((double)i10 + block1.minX * 16.0D) / 256.0D;
		double d14 = ((double)i10 + block1.maxX * 16.0D - 0.01D) / 256.0D;
		double d16 = ((double)i11 + block1.minZ * 16.0D) / 256.0D;
		double d18 = ((double)i11 + block1.maxZ * 16.0D - 0.01D) / 256.0D;
		if(block1.minX < 0.0D || block1.maxX > 1.0D) {
			d12 = ((float)i10 + 0.0F) / 256.0F;
			d14 = ((float)i10 + 15.99F) / 256.0F;
		}

		if(block1.minZ < 0.0D || block1.maxZ > 1.0D) {
			d16 = ((float)i11 + 0.0F) / 256.0F;
			d18 = ((float)i11 + 15.99F) / 256.0F;
		}

		double d20 = d14;
		double d22 = d12;
		double d24 = d16;
		double d26 = d18;
		if(this.field_31082_l == 2) {
			d12 = ((double)i10 + block1.minZ * 16.0D) / 256.0D;
			d16 = ((double)(i11 + 16) - block1.maxX * 16.0D) / 256.0D;
			d14 = ((double)i10 + block1.maxZ * 16.0D) / 256.0D;
			d18 = ((double)(i11 + 16) - block1.minX * 16.0D) / 256.0D;
			d24 = d16;
			d26 = d18;
			d20 = d12;
			d22 = d14;
			d16 = d18;
			d18 = d24;
		} else if(this.field_31082_l == 1) {
			d12 = ((double)(i10 + 16) - block1.maxZ * 16.0D) / 256.0D;
			d16 = ((double)i11 + block1.minX * 16.0D) / 256.0D;
			d14 = ((double)(i10 + 16) - block1.minZ * 16.0D) / 256.0D;
			d18 = ((double)i11 + block1.maxX * 16.0D) / 256.0D;
			d20 = d14;
			d22 = d12;
			d12 = d14;
			d14 = d22;
			d24 = d18;
			d26 = d16;
		} else if(this.field_31082_l == 3) {
			d12 = ((double)(i10 + 16) - block1.minX * 16.0D) / 256.0D;
			d14 = ((double)(i10 + 16) - block1.maxX * 16.0D - 0.01D) / 256.0D;
			d16 = ((double)(i11 + 16) - block1.minZ * 16.0D) / 256.0D;
			d18 = ((double)(i11 + 16) - block1.maxZ * 16.0D - 0.01D) / 256.0D;
			d20 = d14;
			d22 = d12;
			d24 = d16;
			d26 = d18;
		}

		double d28 = d2 + block1.minX;
		double d30 = d2 + block1.maxX;
		double d32 = d4 + block1.minY;
		double d34 = d6 + block1.minZ;
		double d36 = d6 + block1.maxZ;
		if(this.enableAO) {
			tessellator9.setColorOpaque_F(this.colorRedTopLeft, this.colorGreenTopLeft, this.colorBlueTopLeft);
			tessellator9.addVertexWithUV(d28, d32, d36, d22, d26);
			tessellator9.setColorOpaque_F(this.colorRedBottomLeft, this.colorGreenBottomLeft, this.colorBlueBottomLeft);
			tessellator9.addVertexWithUV(d28, d32, d34, d12, d16);
			tessellator9.setColorOpaque_F(this.colorRedBottomRight, this.colorGreenBottomRight, this.colorBlueBottomRight);
			tessellator9.addVertexWithUV(d30, d32, d34, d20, d24);
			tessellator9.setColorOpaque_F(this.colorRedTopRight, this.colorGreenTopRight, this.colorBlueTopRight);
			tessellator9.addVertexWithUV(d30, d32, d36, d14, d18);
		} else {
			tessellator9.addVertexWithUV(d28, d32, d36, d22, d26);
			tessellator9.addVertexWithUV(d28, d32, d34, d12, d16);
			tessellator9.addVertexWithUV(d30, d32, d34, d20, d24);
			tessellator9.addVertexWithUV(d30, d32, d36, d14, d18);
		}

	}

	public void renderTopFace(Block block1, double d2, double d4, double d6, int i8) {
		Tessellator tessellator9 = Tessellator.instance;
		if(this.overrideBlockTexture >= 0) {
			i8 = this.overrideBlockTexture;
		}

		int i10 = (i8 & 15) << 4;
		int i11 = i8 & 240;
		double d12 = ((double)i10 + block1.minX * 16.0D) / 256.0D;
		double d14 = ((double)i10 + block1.maxX * 16.0D - 0.01D) / 256.0D;
		double d16 = ((double)i11 + block1.minZ * 16.0D) / 256.0D;
		double d18 = ((double)i11 + block1.maxZ * 16.0D - 0.01D) / 256.0D;
		if(block1.minX < 0.0D || block1.maxX > 1.0D) {
			d12 = ((float)i10 + 0.0F) / 256.0F;
			d14 = ((float)i10 + 15.99F) / 256.0F;
		}

		if(block1.minZ < 0.0D || block1.maxZ > 1.0D) {
			d16 = ((float)i11 + 0.0F) / 256.0F;
			d18 = ((float)i11 + 15.99F) / 256.0F;
		}

		double d20 = d14;
		double d22 = d12;
		double d24 = d16;
		double d26 = d18;
		if(this.field_31083_k == 1) {
			d12 = ((double)i10 + block1.minZ * 16.0D) / 256.0D;
			d16 = ((double)(i11 + 16) - block1.maxX * 16.0D) / 256.0D;
			d14 = ((double)i10 + block1.maxZ * 16.0D) / 256.0D;
			d18 = ((double)(i11 + 16) - block1.minX * 16.0D) / 256.0D;
			d24 = d16;
			d26 = d18;
			d20 = d12;
			d22 = d14;
			d16 = d18;
			d18 = d24;
		} else if(this.field_31083_k == 2) {
			d12 = ((double)(i10 + 16) - block1.maxZ * 16.0D) / 256.0D;
			d16 = ((double)i11 + block1.minX * 16.0D) / 256.0D;
			d14 = ((double)(i10 + 16) - block1.minZ * 16.0D) / 256.0D;
			d18 = ((double)i11 + block1.maxX * 16.0D) / 256.0D;
			d20 = d14;
			d22 = d12;
			d12 = d14;
			d14 = d22;
			d24 = d18;
			d26 = d16;
		} else if(this.field_31083_k == 3) {
			d12 = ((double)(i10 + 16) - block1.minX * 16.0D) / 256.0D;
			d14 = ((double)(i10 + 16) - block1.maxX * 16.0D - 0.01D) / 256.0D;
			d16 = ((double)(i11 + 16) - block1.minZ * 16.0D) / 256.0D;
			d18 = ((double)(i11 + 16) - block1.maxZ * 16.0D - 0.01D) / 256.0D;
			d20 = d14;
			d22 = d12;
			d24 = d16;
			d26 = d18;
		}

		double d28 = d2 + block1.minX;
		double d30 = d2 + block1.maxX;
		double d32 = d4 + block1.maxY;
		double d34 = d6 + block1.minZ;
		double d36 = d6 + block1.maxZ;
		if(this.enableAO) {
			tessellator9.setColorOpaque_F(this.colorRedTopLeft, this.colorGreenTopLeft, this.colorBlueTopLeft);
			tessellator9.addVertexWithUV(d30, d32, d36, d14, d18);
			tessellator9.setColorOpaque_F(this.colorRedBottomLeft, this.colorGreenBottomLeft, this.colorBlueBottomLeft);
			tessellator9.addVertexWithUV(d30, d32, d34, d20, d24);
			tessellator9.setColorOpaque_F(this.colorRedBottomRight, this.colorGreenBottomRight, this.colorBlueBottomRight);
			tessellator9.addVertexWithUV(d28, d32, d34, d12, d16);
			tessellator9.setColorOpaque_F(this.colorRedTopRight, this.colorGreenTopRight, this.colorBlueTopRight);
			tessellator9.addVertexWithUV(d28, d32, d36, d22, d26);
		} else {
			tessellator9.addVertexWithUV(d30, d32, d36, d14, d18);
			tessellator9.addVertexWithUV(d30, d32, d34, d20, d24);
			tessellator9.addVertexWithUV(d28, d32, d34, d12, d16);
			tessellator9.addVertexWithUV(d28, d32, d36, d22, d26);
		}

	}

	public void renderEastFace(Block block1, double d2, double d4, double d6, int i8) {
		Tessellator tessellator9 = Tessellator.instance;
		if(this.overrideBlockTexture >= 0) {
			i8 = this.overrideBlockTexture;
		}

		int i10 = (i8 & 15) << 4;
		int i11 = i8 & 240;
		double d12 = ((double)i10 + block1.minX * 16.0D) / 256.0D;
		double d14 = ((double)i10 + block1.maxX * 16.0D - 0.01D) / 256.0D;
		double d16 = ((double)(i11 + 16) - block1.maxY * 16.0D) / 256.0D;
		double d18 = ((double)(i11 + 16) - block1.minY * 16.0D - 0.01D) / 256.0D;
		double d20;
		if(this.flipTexture) {
			d20 = d12;
			d12 = d14;
			d14 = d20;
		}

		if(block1.minX < 0.0D || block1.maxX > 1.0D) {
			d12 = ((float)i10 + 0.0F) / 256.0F;
			d14 = ((float)i10 + 15.99F) / 256.0F;
		}

		if(block1.minY < 0.0D || block1.maxY > 1.0D) {
			d16 = ((float)i11 + 0.0F) / 256.0F;
			d18 = ((float)i11 + 15.99F) / 256.0F;
		}

		d20 = d14;
		double d22 = d12;
		double d24 = d16;
		double d26 = d18;
		if(this.field_31087_g == 2) {
			d12 = ((double)i10 + block1.minY * 16.0D) / 256.0D;
			d16 = ((double)(i11 + 16) - block1.minX * 16.0D) / 256.0D;
			d14 = ((double)i10 + block1.maxY * 16.0D) / 256.0D;
			d18 = ((double)(i11 + 16) - block1.maxX * 16.0D) / 256.0D;
			d24 = d16;
			d26 = d18;
			d20 = d12;
			d22 = d14;
			d16 = d18;
			d18 = d24;
		} else if(this.field_31087_g == 1) {
			d12 = ((double)(i10 + 16) - block1.maxY * 16.0D) / 256.0D;
			d16 = ((double)i11 + block1.maxX * 16.0D) / 256.0D;
			d14 = ((double)(i10 + 16) - block1.minY * 16.0D) / 256.0D;
			d18 = ((double)i11 + block1.minX * 16.0D) / 256.0D;
			d20 = d14;
			d22 = d12;
			d12 = d14;
			d14 = d22;
			d24 = d18;
			d26 = d16;
		} else if(this.field_31087_g == 3) {
			d12 = ((double)(i10 + 16) - block1.minX * 16.0D) / 256.0D;
			d14 = ((double)(i10 + 16) - block1.maxX * 16.0D - 0.01D) / 256.0D;
			d16 = ((double)i11 + block1.maxY * 16.0D) / 256.0D;
			d18 = ((double)i11 + block1.minY * 16.0D - 0.01D) / 256.0D;
			d20 = d14;
			d22 = d12;
			d24 = d16;
			d26 = d18;
		}

		double d28 = d2 + block1.minX;
		double d30 = d2 + block1.maxX;
		double d32 = d4 + block1.minY;
		double d34 = d4 + block1.maxY;
		double d36 = d6 + block1.minZ;
		if(this.enableAO) {
			tessellator9.setColorOpaque_F(this.colorRedTopLeft, this.colorGreenTopLeft, this.colorBlueTopLeft);
			tessellator9.addVertexWithUV(d28, d34, d36, d20, d24);
			tessellator9.setColorOpaque_F(this.colorRedBottomLeft, this.colorGreenBottomLeft, this.colorBlueBottomLeft);
			tessellator9.addVertexWithUV(d30, d34, d36, d12, d16);
			tessellator9.setColorOpaque_F(this.colorRedBottomRight, this.colorGreenBottomRight, this.colorBlueBottomRight);
			tessellator9.addVertexWithUV(d30, d32, d36, d22, d26);
			tessellator9.setColorOpaque_F(this.colorRedTopRight, this.colorGreenTopRight, this.colorBlueTopRight);
			tessellator9.addVertexWithUV(d28, d32, d36, d14, d18);
		} else {
			tessellator9.addVertexWithUV(d28, d34, d36, d20, d24);
			tessellator9.addVertexWithUV(d30, d34, d36, d12, d16);
			tessellator9.addVertexWithUV(d30, d32, d36, d22, d26);
			tessellator9.addVertexWithUV(d28, d32, d36, d14, d18);
		}

	}

	public void renderWestFace(Block block1, double d2, double d4, double d6, int i8) {
		Tessellator tessellator9 = Tessellator.instance;
		if(this.overrideBlockTexture >= 0) {
			i8 = this.overrideBlockTexture;
		}

		int i10 = (i8 & 15) << 4;
		int i11 = i8 & 240;
		double d12 = ((double)i10 + block1.minX * 16.0D) / 256.0D;
		double d14 = ((double)i10 + block1.maxX * 16.0D - 0.01D) / 256.0D;
		double d16 = ((double)(i11 + 16) - block1.maxY * 16.0D) / 256.0D;
		double d18 = ((double)(i11 + 16) - block1.minY * 16.0D - 0.01D) / 256.0D;
		double d20;
		if(this.flipTexture) {
			d20 = d12;
			d12 = d14;
			d14 = d20;
		}

		if(block1.minX < 0.0D || block1.maxX > 1.0D) {
			d12 = ((float)i10 + 0.0F) / 256.0F;
			d14 = ((float)i10 + 15.99F) / 256.0F;
		}

		if(block1.minY < 0.0D || block1.maxY > 1.0D) {
			d16 = ((float)i11 + 0.0F) / 256.0F;
			d18 = ((float)i11 + 15.99F) / 256.0F;
		}

		d20 = d14;
		double d22 = d12;
		double d24 = d16;
		double d26 = d18;
		if(this.field_31086_h == 1) {
			d12 = ((double)i10 + block1.minY * 16.0D) / 256.0D;
			d18 = ((double)(i11 + 16) - block1.minX * 16.0D) / 256.0D;
			d14 = ((double)i10 + block1.maxY * 16.0D) / 256.0D;
			d16 = ((double)(i11 + 16) - block1.maxX * 16.0D) / 256.0D;
			d24 = d16;
			d26 = d18;
			d20 = d12;
			d22 = d14;
			d16 = d18;
			d18 = d24;
		} else if(this.field_31086_h == 2) {
			d12 = ((double)(i10 + 16) - block1.maxY * 16.0D) / 256.0D;
			d16 = ((double)i11 + block1.minX * 16.0D) / 256.0D;
			d14 = ((double)(i10 + 16) - block1.minY * 16.0D) / 256.0D;
			d18 = ((double)i11 + block1.maxX * 16.0D) / 256.0D;
			d20 = d14;
			d22 = d12;
			d12 = d14;
			d14 = d22;
			d24 = d18;
			d26 = d16;
		} else if(this.field_31086_h == 3) {
			d12 = ((double)(i10 + 16) - block1.minX * 16.0D) / 256.0D;
			d14 = ((double)(i10 + 16) - block1.maxX * 16.0D - 0.01D) / 256.0D;
			d16 = ((double)i11 + block1.maxY * 16.0D) / 256.0D;
			d18 = ((double)i11 + block1.minY * 16.0D - 0.01D) / 256.0D;
			d20 = d14;
			d22 = d12;
			d24 = d16;
			d26 = d18;
		}

		double d28 = d2 + block1.minX;
		double d30 = d2 + block1.maxX;
		double d32 = d4 + block1.minY;
		double d34 = d4 + block1.maxY;
		double d36 = d6 + block1.maxZ;
		if(this.enableAO) {
			tessellator9.setColorOpaque_F(this.colorRedTopLeft, this.colorGreenTopLeft, this.colorBlueTopLeft);
			tessellator9.addVertexWithUV(d28, d34, d36, d12, d16);
			tessellator9.setColorOpaque_F(this.colorRedBottomLeft, this.colorGreenBottomLeft, this.colorBlueBottomLeft);
			tessellator9.addVertexWithUV(d28, d32, d36, d22, d26);
			tessellator9.setColorOpaque_F(this.colorRedBottomRight, this.colorGreenBottomRight, this.colorBlueBottomRight);
			tessellator9.addVertexWithUV(d30, d32, d36, d14, d18);
			tessellator9.setColorOpaque_F(this.colorRedTopRight, this.colorGreenTopRight, this.colorBlueTopRight);
			tessellator9.addVertexWithUV(d30, d34, d36, d20, d24);
		} else {
			tessellator9.addVertexWithUV(d28, d34, d36, d12, d16);
			tessellator9.addVertexWithUV(d28, d32, d36, d22, d26);
			tessellator9.addVertexWithUV(d30, d32, d36, d14, d18);
			tessellator9.addVertexWithUV(d30, d34, d36, d20, d24);
		}

	}

	public void renderNorthFace(Block block1, double d2, double d4, double d6, int i8) {
		Tessellator tessellator9 = Tessellator.instance;
		if(this.overrideBlockTexture >= 0) {
			i8 = this.overrideBlockTexture;
		}

		int i10 = (i8 & 15) << 4;
		int i11 = i8 & 240;
		double d12 = ((double)i10 + block1.minZ * 16.0D) / 256.0D;
		double d14 = ((double)i10 + block1.maxZ * 16.0D - 0.01D) / 256.0D;
		double d16 = ((double)(i11 + 16) - block1.maxY * 16.0D) / 256.0D;
		double d18 = ((double)(i11 + 16) - block1.minY * 16.0D - 0.01D) / 256.0D;
		double d20;
		if(this.flipTexture) {
			d20 = d12;
			d12 = d14;
			d14 = d20;
		}

		if(block1.minZ < 0.0D || block1.maxZ > 1.0D) {
			d12 = ((float)i10 + 0.0F) / 256.0F;
			d14 = ((float)i10 + 15.99F) / 256.0F;
		}

		if(block1.minY < 0.0D || block1.maxY > 1.0D) {
			d16 = ((float)i11 + 0.0F) / 256.0F;
			d18 = ((float)i11 + 15.99F) / 256.0F;
		}

		d20 = d14;
		double d22 = d12;
		double d24 = d16;
		double d26 = d18;
		if(this.field_31084_j == 1) {
			d12 = ((double)i10 + block1.minY * 16.0D) / 256.0D;
			d16 = ((double)(i11 + 16) - block1.maxZ * 16.0D) / 256.0D;
			d14 = ((double)i10 + block1.maxY * 16.0D) / 256.0D;
			d18 = ((double)(i11 + 16) - block1.minZ * 16.0D) / 256.0D;
			d24 = d16;
			d26 = d18;
			d20 = d12;
			d22 = d14;
			d16 = d18;
			d18 = d24;
		} else if(this.field_31084_j == 2) {
			d12 = ((double)(i10 + 16) - block1.maxY * 16.0D) / 256.0D;
			d16 = ((double)i11 + block1.minZ * 16.0D) / 256.0D;
			d14 = ((double)(i10 + 16) - block1.minY * 16.0D) / 256.0D;
			d18 = ((double)i11 + block1.maxZ * 16.0D) / 256.0D;
			d20 = d14;
			d22 = d12;
			d12 = d14;
			d14 = d22;
			d24 = d18;
			d26 = d16;
		} else if(this.field_31084_j == 3) {
			d12 = ((double)(i10 + 16) - block1.minZ * 16.0D) / 256.0D;
			d14 = ((double)(i10 + 16) - block1.maxZ * 16.0D - 0.01D) / 256.0D;
			d16 = ((double)i11 + block1.maxY * 16.0D) / 256.0D;
			d18 = ((double)i11 + block1.minY * 16.0D - 0.01D) / 256.0D;
			d20 = d14;
			d22 = d12;
			d24 = d16;
			d26 = d18;
		}

		double d28 = d2 + block1.minX;
		double d30 = d4 + block1.minY;
		double d32 = d4 + block1.maxY;
		double d34 = d6 + block1.minZ;
		double d36 = d6 + block1.maxZ;
		if(this.enableAO) {
			tessellator9.setColorOpaque_F(this.colorRedTopLeft, this.colorGreenTopLeft, this.colorBlueTopLeft);
			tessellator9.addVertexWithUV(d28, d32, d36, d20, d24);
			tessellator9.setColorOpaque_F(this.colorRedBottomLeft, this.colorGreenBottomLeft, this.colorBlueBottomLeft);
			tessellator9.addVertexWithUV(d28, d32, d34, d12, d16);
			tessellator9.setColorOpaque_F(this.colorRedBottomRight, this.colorGreenBottomRight, this.colorBlueBottomRight);
			tessellator9.addVertexWithUV(d28, d30, d34, d22, d26);
			tessellator9.setColorOpaque_F(this.colorRedTopRight, this.colorGreenTopRight, this.colorBlueTopRight);
			tessellator9.addVertexWithUV(d28, d30, d36, d14, d18);
		} else {
			tessellator9.addVertexWithUV(d28, d32, d36, d20, d24);
			tessellator9.addVertexWithUV(d28, d32, d34, d12, d16);
			tessellator9.addVertexWithUV(d28, d30, d34, d22, d26);
			tessellator9.addVertexWithUV(d28, d30, d36, d14, d18);
		}

	}

	public void renderSouthFace(Block block1, double d2, double d4, double d6, int i8) {
		Tessellator tessellator9 = Tessellator.instance;
		if(this.overrideBlockTexture >= 0) {
			i8 = this.overrideBlockTexture;
		}

		int i10 = (i8 & 15) << 4;
		int i11 = i8 & 240;
		double d12 = ((double)i10 + block1.minZ * 16.0D) / 256.0D;
		double d14 = ((double)i10 + block1.maxZ * 16.0D - 0.01D) / 256.0D;
		double d16 = ((double)(i11 + 16) - block1.maxY * 16.0D) / 256.0D;
		double d18 = ((double)(i11 + 16) - block1.minY * 16.0D - 0.01D) / 256.0D;
		double d20;
		if(this.flipTexture) {
			d20 = d12;
			d12 = d14;
			d14 = d20;
		}

		if(block1.minZ < 0.0D || block1.maxZ > 1.0D) {
			d12 = ((float)i10 + 0.0F) / 256.0F;
			d14 = ((float)i10 + 15.99F) / 256.0F;
		}

		if(block1.minY < 0.0D || block1.maxY > 1.0D) {
			d16 = ((float)i11 + 0.0F) / 256.0F;
			d18 = ((float)i11 + 15.99F) / 256.0F;
		}

		d20 = d14;
		double d22 = d12;
		double d24 = d16;
		double d26 = d18;
		if(this.field_31085_i == 2) {
			d12 = ((double)i10 + block1.minY * 16.0D) / 256.0D;
			d16 = ((double)(i11 + 16) - block1.minZ * 16.0D) / 256.0D;
			d14 = ((double)i10 + block1.maxY * 16.0D) / 256.0D;
			d18 = ((double)(i11 + 16) - block1.maxZ * 16.0D) / 256.0D;
			d24 = d16;
			d26 = d18;
			d20 = d12;
			d22 = d14;
			d16 = d18;
			d18 = d24;
		} else if(this.field_31085_i == 1) {
			d12 = ((double)(i10 + 16) - block1.maxY * 16.0D) / 256.0D;
			d16 = ((double)i11 + block1.maxZ * 16.0D) / 256.0D;
			d14 = ((double)(i10 + 16) - block1.minY * 16.0D) / 256.0D;
			d18 = ((double)i11 + block1.minZ * 16.0D) / 256.0D;
			d20 = d14;
			d22 = d12;
			d12 = d14;
			d14 = d22;
			d24 = d18;
			d26 = d16;
		} else if(this.field_31085_i == 3) {
			d12 = ((double)(i10 + 16) - block1.minZ * 16.0D) / 256.0D;
			d14 = ((double)(i10 + 16) - block1.maxZ * 16.0D - 0.01D) / 256.0D;
			d16 = ((double)i11 + block1.maxY * 16.0D) / 256.0D;
			d18 = ((double)i11 + block1.minY * 16.0D - 0.01D) / 256.0D;
			d20 = d14;
			d22 = d12;
			d24 = d16;
			d26 = d18;
		}

		double d28 = d2 + block1.maxX;
		double d30 = d4 + block1.minY;
		double d32 = d4 + block1.maxY;
		double d34 = d6 + block1.minZ;
		double d36 = d6 + block1.maxZ;
		if(this.enableAO) {
			tessellator9.setColorOpaque_F(this.colorRedTopLeft, this.colorGreenTopLeft, this.colorBlueTopLeft);
			tessellator9.addVertexWithUV(d28, d30, d36, d22, d26);
			tessellator9.setColorOpaque_F(this.colorRedBottomLeft, this.colorGreenBottomLeft, this.colorBlueBottomLeft);
			tessellator9.addVertexWithUV(d28, d30, d34, d14, d18);
			tessellator9.setColorOpaque_F(this.colorRedBottomRight, this.colorGreenBottomRight, this.colorBlueBottomRight);
			tessellator9.addVertexWithUV(d28, d32, d34, d20, d24);
			tessellator9.setColorOpaque_F(this.colorRedTopRight, this.colorGreenTopRight, this.colorBlueTopRight);
			tessellator9.addVertexWithUV(d28, d32, d36, d12, d16);
		} else {
			tessellator9.addVertexWithUV(d28, d30, d36, d22, d26);
			tessellator9.addVertexWithUV(d28, d30, d34, d14, d18);
			tessellator9.addVertexWithUV(d28, d32, d34, d20, d24);
			tessellator9.addVertexWithUV(d28, d32, d36, d12, d16);
		}

	}

	public void renderBlockOnInventory(Block block1, int i2, float f3) {
		Tessellator tessellator4 = Tessellator.instance;
		int i5;
		float f6;
		float f7;
		if(this.field_31088_b) {
			i5 = block1.getRenderColor(i2);
			f6 = (float)(i5 >> 16 & 255) / 255.0F;
			f7 = (float)(i5 >> 8 & 255) / 255.0F;
			float f8 = (float)(i5 & 255) / 255.0F;
			GL11.glColor4f(f6 * f3, f7 * f3, f8 * f3, 1.0F);
		}

		i5 = block1.getRenderType();
		if(i5 != 0 && i5 != 16) {
			if(i5 == 1) {
				tessellator4.startDrawingQuads();
				tessellator4.setNormal(0.0F, -1.0F, 0.0F);
				this.renderCrossedSquares(block1, i2, -0.5D, -0.5D, -0.5D);
				tessellator4.draw();
			} else if(i5 == 13) {
				block1.setBlockBoundsForItemRender();
				GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
				f6 = 0.0625F;
				tessellator4.startDrawingQuads();
				tessellator4.setNormal(0.0F, -1.0F, 0.0F);
				this.renderBottomFace(block1, 0.0D, 0.0D, 0.0D, block1.getBlockTextureFromSide(0));
				tessellator4.draw();
				tessellator4.startDrawingQuads();
				tessellator4.setNormal(0.0F, 1.0F, 0.0F);
				this.renderTopFace(block1, 0.0D, 0.0D, 0.0D, block1.getBlockTextureFromSide(1));
				tessellator4.draw();
				tessellator4.startDrawingQuads();
				tessellator4.setNormal(0.0F, 0.0F, -1.0F);
				tessellator4.setTranslationF(0.0F, 0.0F, f6);
				this.renderEastFace(block1, 0.0D, 0.0D, 0.0D, block1.getBlockTextureFromSide(2));
				tessellator4.setTranslationF(0.0F, 0.0F, -f6);
				tessellator4.draw();
				tessellator4.startDrawingQuads();
				tessellator4.setNormal(0.0F, 0.0F, 1.0F);
				tessellator4.setTranslationF(0.0F, 0.0F, -f6);
				this.renderWestFace(block1, 0.0D, 0.0D, 0.0D, block1.getBlockTextureFromSide(3));
				tessellator4.setTranslationF(0.0F, 0.0F, f6);
				tessellator4.draw();
				tessellator4.startDrawingQuads();
				tessellator4.setNormal(-1.0F, 0.0F, 0.0F);
				tessellator4.setTranslationF(f6, 0.0F, 0.0F);
				this.renderNorthFace(block1, 0.0D, 0.0D, 0.0D, block1.getBlockTextureFromSide(4));
				tessellator4.setTranslationF(-f6, 0.0F, 0.0F);
				tessellator4.draw();
				tessellator4.startDrawingQuads();
				tessellator4.setNormal(1.0F, 0.0F, 0.0F);
				tessellator4.setTranslationF(-f6, 0.0F, 0.0F);
				this.renderSouthFace(block1, 0.0D, 0.0D, 0.0D, block1.getBlockTextureFromSide(5));
				tessellator4.setTranslationF(f6, 0.0F, 0.0F);
				tessellator4.draw();
				GL11.glTranslatef(0.5F, 0.5F, 0.5F);
			} else if(i5 == 6) {
				tessellator4.startDrawingQuads();
				tessellator4.setNormal(0.0F, -1.0F, 0.0F);
				this.func_1245_b(block1, i2, -0.5D, -0.5D, -0.5D);
				tessellator4.draw();
			} else if(i5 == 2) {
				tessellator4.startDrawingQuads();
				tessellator4.setNormal(0.0F, -1.0F, 0.0F);
				this.renderTorchAtAngle(block1, -0.5D, -0.5D, -0.5D, 0.0D, 0.0D);
				tessellator4.draw();
			} else {
				int i9;
				if(i5 == 10) {
					for(i9 = 0; i9 < 2; ++i9) {
						if(i9 == 0) {
							block1.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.5F);
						}

						if(i9 == 1) {
							block1.setBlockBounds(0.0F, 0.0F, 0.5F, 1.0F, 0.5F, 1.0F);
						}

						GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
						tessellator4.startDrawingQuads();
						tessellator4.setNormal(0.0F, -1.0F, 0.0F);
						this.renderBottomFace(block1, 0.0D, 0.0D, 0.0D, block1.getBlockTextureFromSide(0));
						tessellator4.draw();
						tessellator4.startDrawingQuads();
						tessellator4.setNormal(0.0F, 1.0F, 0.0F);
						this.renderTopFace(block1, 0.0D, 0.0D, 0.0D, block1.getBlockTextureFromSide(1));
						tessellator4.draw();
						tessellator4.startDrawingQuads();
						tessellator4.setNormal(0.0F, 0.0F, -1.0F);
						this.renderEastFace(block1, 0.0D, 0.0D, 0.0D, block1.getBlockTextureFromSide(2));
						tessellator4.draw();
						tessellator4.startDrawingQuads();
						tessellator4.setNormal(0.0F, 0.0F, 1.0F);
						this.renderWestFace(block1, 0.0D, 0.0D, 0.0D, block1.getBlockTextureFromSide(3));
						tessellator4.draw();
						tessellator4.startDrawingQuads();
						tessellator4.setNormal(-1.0F, 0.0F, 0.0F);
						this.renderNorthFace(block1, 0.0D, 0.0D, 0.0D, block1.getBlockTextureFromSide(4));
						tessellator4.draw();
						tessellator4.startDrawingQuads();
						tessellator4.setNormal(1.0F, 0.0F, 0.0F);
						this.renderSouthFace(block1, 0.0D, 0.0D, 0.0D, block1.getBlockTextureFromSide(5));
						tessellator4.draw();
						GL11.glTranslatef(0.5F, 0.5F, 0.5F);
					}
				} else if(i5 == 11) {
					for(i9 = 0; i9 < 4; ++i9) {
						f7 = 0.125F;
						if(i9 == 0) {
							block1.setBlockBounds(0.5F - f7, 0.0F, 0.0F, 0.5F + f7, 1.0F, f7 * 2.0F);
						}

						if(i9 == 1) {
							block1.setBlockBounds(0.5F - f7, 0.0F, 1.0F - f7 * 2.0F, 0.5F + f7, 1.0F, 1.0F);
						}

						f7 = 0.0625F;
						if(i9 == 2) {
							block1.setBlockBounds(0.5F - f7, 1.0F - f7 * 3.0F, -f7 * 2.0F, 0.5F + f7, 1.0F - f7, 1.0F + f7 * 2.0F);
						}

						if(i9 == 3) {
							block1.setBlockBounds(0.5F - f7, 0.5F - f7 * 3.0F, -f7 * 2.0F, 0.5F + f7, 0.5F - f7, 1.0F + f7 * 2.0F);
						}

						GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
						tessellator4.startDrawingQuads();
						tessellator4.setNormal(0.0F, -1.0F, 0.0F);
						this.renderBottomFace(block1, 0.0D, 0.0D, 0.0D, block1.getBlockTextureFromSide(0));
						tessellator4.draw();
						tessellator4.startDrawingQuads();
						tessellator4.setNormal(0.0F, 1.0F, 0.0F);
						this.renderTopFace(block1, 0.0D, 0.0D, 0.0D, block1.getBlockTextureFromSide(1));
						tessellator4.draw();
						tessellator4.startDrawingQuads();
						tessellator4.setNormal(0.0F, 0.0F, -1.0F);
						this.renderEastFace(block1, 0.0D, 0.0D, 0.0D, block1.getBlockTextureFromSide(2));
						tessellator4.draw();
						tessellator4.startDrawingQuads();
						tessellator4.setNormal(0.0F, 0.0F, 1.0F);
						this.renderWestFace(block1, 0.0D, 0.0D, 0.0D, block1.getBlockTextureFromSide(3));
						tessellator4.draw();
						tessellator4.startDrawingQuads();
						tessellator4.setNormal(-1.0F, 0.0F, 0.0F);
						this.renderNorthFace(block1, 0.0D, 0.0D, 0.0D, block1.getBlockTextureFromSide(4));
						tessellator4.draw();
						tessellator4.startDrawingQuads();
						tessellator4.setNormal(1.0F, 0.0F, 0.0F);
						this.renderSouthFace(block1, 0.0D, 0.0D, 0.0D, block1.getBlockTextureFromSide(5));
						tessellator4.draw();
						GL11.glTranslatef(0.5F, 0.5F, 0.5F);
					}

					block1.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
				}
			}
		} else {
			if(i5 == 16) {
				i2 = 1;
			}

			block1.setBlockBoundsForItemRender();
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
			tessellator4.startDrawingQuads();
			tessellator4.setNormal(0.0F, -1.0F, 0.0F);
			this.renderBottomFace(block1, 0.0D, 0.0D, 0.0D, block1.getBlockTextureFromSideAndMetadata(0, i2));
			tessellator4.draw();
			tessellator4.startDrawingQuads();
			tessellator4.setNormal(0.0F, 1.0F, 0.0F);
			this.renderTopFace(block1, 0.0D, 0.0D, 0.0D, block1.getBlockTextureFromSideAndMetadata(1, i2));
			tessellator4.draw();
			tessellator4.startDrawingQuads();
			tessellator4.setNormal(0.0F, 0.0F, -1.0F);
			this.renderEastFace(block1, 0.0D, 0.0D, 0.0D, block1.getBlockTextureFromSideAndMetadata(2, i2));
			tessellator4.draw();
			tessellator4.startDrawingQuads();
			tessellator4.setNormal(0.0F, 0.0F, 1.0F);
			this.renderWestFace(block1, 0.0D, 0.0D, 0.0D, block1.getBlockTextureFromSideAndMetadata(3, i2));
			tessellator4.draw();
			tessellator4.startDrawingQuads();
			tessellator4.setNormal(-1.0F, 0.0F, 0.0F);
			this.renderNorthFace(block1, 0.0D, 0.0D, 0.0D, block1.getBlockTextureFromSideAndMetadata(4, i2));
			tessellator4.draw();
			tessellator4.startDrawingQuads();
			tessellator4.setNormal(1.0F, 0.0F, 0.0F);
			this.renderSouthFace(block1, 0.0D, 0.0D, 0.0D, block1.getBlockTextureFromSideAndMetadata(5, i2));
			tessellator4.draw();
			GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		}

	}

	public static boolean renderItemIn3d(int i0) {
		return i0 == 0 || (i0 == 13 || (i0 == 10 || (i0 == 11 || i0 == 16)));
	}
}
