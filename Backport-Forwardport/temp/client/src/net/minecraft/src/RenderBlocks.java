package net.minecraft.src;

import net.minecraft.client.Minecraft;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class RenderBlocks {
	private IBlockAccess blockAccess;
	private int overrideBlockTexture = -1;
	private boolean flipTexture = false;
	private boolean renderAllFaces = false;
	public static boolean fancyGrass = true;
	public boolean useInventoryTint = true;
	public boolean[] field_35942_c = new boolean[256];
	private int uvRotateEast = 0;
	private int uvRotateWest = 0;
	private int uvRotateSouth = 0;
	private int uvRotateNorth = 0;
	private int uvRotateTop = 0;
	private int uvRotateBottom = 0;
	private boolean enableAO;
	private float lightValueOwn;
	private float aoLightValueXNeg;
	private float aoLightValueYNeg;
	private float aoLightValueZNeg;
	private float aoLightValueXPos;
	private float aoLightValueYPos;
	private float aoLightValueZPos;
	private float aoLightValueScratchXYZNNN;
	private float aoLightValueScratchXYNN;
	private float aoLightValueScratchXYZNNP;
	private float aoLightValueScratchYZNN;
	private float aoLightValueScratchYZNP;
	private float aoLightValueScratchXYZPNN;
	private float aoLightValueScratchXYPN;
	private float aoLightValueScratchXYZPNP;
	private float aoLightValueScratchXYZNPN;
	private float aoLightValueScratchXYNP;
	private float aoLightValueScratchXYZNPP;
	private float aoLightValueScratchYZPN;
	private float aoLightValueScratchXYZPPN;
	private float aoLightValueScratchXYPP;
	private float aoLightValueScratchYZPP;
	private float aoLightValueScratchXYZPPP;
	private float aoLightValueScratchXZNN;
	private float aoLightValueScratchXZPN;
	private float aoLightValueScratchXZNP;
	private float aoLightValueScratchXZPP;
	private int field_35936_P;
	private int field_35935_Q;
	private int field_35938_R;
	private int field_35937_S;
	private int field_35932_T;
	private int field_35931_U;
	private int field_35934_V;
	private int field_35933_W;
	private int field_35940_X;
	private int field_35939_Y;
	private int field_35941_Z;
	private int field_35951_aa;
	private int field_35952_ab;
	private int field_35949_ac;
	private int field_35950_ad;
	private int field_35955_ae;
	private int field_35956_af;
	private int field_35953_ag;
	private int field_35954_ah;
	private int field_35945_ai;
	private int field_35946_aj = 1;
	private int field_35943_ak;
	private int field_35944_al;
	private int field_35947_am;
	private int field_35948_an;
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
	private boolean aoGrassXYZCPN;
	private boolean aoGrassXYZPPC;
	private boolean aoGrassXYZNPC;
	private boolean aoGrassXYZCPP;
	private boolean aoGrassXYZNCN;
	private boolean aoGrassXYZPCP;
	private boolean aoGrassXYZNCP;
	private boolean aoGrassXYZPCN;
	private boolean aoGrassXYZCNN;
	private boolean aoGrassXYZPNC;
	private boolean aoGrassXYZNNC;
	private boolean aoGrassXYZCNP;

	public RenderBlocks(IBlockAccess iBlockAccess1) {
		this.blockAccess = iBlockAccess1;

		for(int i2 = 0; i2 < 256; ++i2) {
			Block block3 = Block.blocksList[i2];
			if(block3 != null) {
				if(block3 == Block.ice) {
					this.field_35942_c[i2] = true;
				}

				if(block3.renderAsNormalBlock() && block3.isOpaqueCube()) {
					this.field_35942_c[i2] = true;
				}
			}
		}

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

	public boolean func_35927_a(int i1, int i2, int i3, int i4) {
		if(this != null) {
			return false;
		} else {
			byte b5 = 13;
			byte b6 = 52;
			byte b7 = 32;
			int i8 = MathHelper.func_35597_a(i1 - b5);
			int i9 = MathHelper.func_35597_a(i3 - b6);
			if(i9 <= b7 && i8 <= b7 && (i8 == b7 || i9 == b7) && this.field_35942_c[this.blockAccess.getBlockId(i1, i2, i3)]) {
				int i10 = 5;
				if(i1 == b5 - b7) {
					--i10;
				}

				if(i1 == b5 + b7) {
					++i10;
				}

				if(i3 == b6 - b7) {
					i10 -= 3;
				}

				if(i3 == b6 + b7) {
					i10 += 3;
				}

				this.func_35921_b(i10, i1, i2, i3);
				return true;
			} else {
				return false;
			}
		}
	}

	public boolean renderBlockByRenderType(Block block1, int i2, int i3, int i4) {
		int i5 = block1.getRenderType();
		block1.setBlockBoundsBasedOnState(this.blockAccess, i2, i3, i4);
		return i5 == 0 ? this.renderStandardBlock(block1, i2, i3, i4) : (i5 == 4 ? this.renderBlockFluids(block1, i2, i3, i4) : (i5 == 13 ? this.renderBlockCactus(block1, i2, i3, i4) : (i5 == 1 ? this.renderBlockReed(block1, i2, i3, i4) : (i5 == 19 ? this.renderBlockCrops(block1, i2, i3, i4) : (i5 == 6 ? this.func_35930_l(block1, i2, i3, i4) : (i5 == 2 ? this.renderBlockTorch(block1, i2, i3, i4) : (i5 == 3 ? this.renderBlockFire(block1, i2, i3, i4) : (i5 == 5 ? this.renderBlockRedstoneWire(block1, i2, i3, i4) : (i5 == 8 ? this.renderBlockLadder(block1, i2, i3, i4) : (i5 == 7 ? this.renderBlockDoor(block1, i2, i3, i4) : (i5 == 9 ? this.renderBlockMinecartTrack((BlockRail)block1, i2, i3, i4) : (i5 == 10 ? this.renderBlockStairs(block1, i2, i3, i4) : (i5 == 11 ? this.func_35925_a((BlockFence)block1, i2, i3, i4) : (i5 == 12 ? this.renderBlockLever(block1, i2, i3, i4) : (i5 == 14 ? this.renderBlockBed(block1, i2, i3, i4) : (i5 == 15 ? this.renderBlockRepeater(block1, i2, i3, i4) : (i5 == 16 ? this.renderPistonBase(block1, i2, i3, i4, false) : (i5 == 17 ? this.renderPistonExtension(block1, i2, i3, i4, true) : (i5 == 18 ? this.func_35922_a((BlockPane)block1, i2, i3, i4) : (i5 == 20 ? this.func_35929_i(block1, i2, i3, i4) : (i5 == 21 ? this.func_35923_a((BlockFenceGate)block1, i2, i3, i4) : false)))))))))))))))))))));
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
		int i25 = block1.func_35275_c(this.blockAccess, i2, i3, i4);
		tessellator5.func_35835_b(i25);
		tessellator5.setColorOpaque_F(f9, f9, f9);
		int i27 = block1.getBlockTexture(this.blockAccess, i2, i3, i4, 0);
		int i28 = (i27 & 15) << 4;
		int i29 = i27 & 240;
		double d30 = (double)((float)i28 / 256.0F);
		double d32 = ((double)(i28 + 16) - 0.01D) / 256.0D;
		double d34 = (double)((float)i29 / 256.0F);
		double d36 = ((double)(i29 + 16) - 0.01D) / 256.0D;
		double d38 = (double)i2 + block1.minX;
		double d40 = (double)i2 + block1.maxX;
		double d42 = (double)i3 + block1.minY + 0.1875D;
		double d44 = (double)i4 + block1.minZ;
		double d46 = (double)i4 + block1.maxZ;
		tessellator5.addVertexWithUV(d38, d42, d46, d30, d36);
		tessellator5.addVertexWithUV(d38, d42, d44, d30, d34);
		tessellator5.addVertexWithUV(d40, d42, d44, d32, d34);
		tessellator5.addVertexWithUV(d40, d42, d46, d32, d36);
		tessellator5.func_35835_b(block1.func_35275_c(this.blockAccess, i2, i3 + 1, i4));
		tessellator5.setColorOpaque_F(f10, f10, f10);
		i27 = block1.getBlockTexture(this.blockAccess, i2, i3, i4, 1);
		i28 = (i27 & 15) << 4;
		i29 = i27 & 240;
		d30 = (double)((float)i28 / 256.0F);
		d32 = ((double)(i28 + 16) - 0.01D) / 256.0D;
		d34 = (double)((float)i29 / 256.0F);
		d36 = ((double)(i29 + 16) - 0.01D) / 256.0D;
		d38 = d30;
		d40 = d32;
		d42 = d34;
		d44 = d34;
		d46 = d30;
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
		i27 = ModelBed.headInvisibleFace[i7];
		if(z8) {
			i27 = ModelBed.headInvisibleFace[ModelBed.footInvisibleFaceRemap[i7]];
		}

		byte b64 = 4;
		switch(i7) {
		case 0:
			b64 = 5;
			break;
		case 1:
			b64 = 3;
		case 2:
		default:
			break;
		case 3:
			b64 = 2;
		}

		if(i27 != 2 && (this.renderAllFaces || block1.shouldSideBeRendered(this.blockAccess, i2, i3, i4 - 1, 2))) {
			tessellator5.func_35835_b(block1.minZ > 0.0D ? i25 : block1.func_35275_c(this.blockAccess, i2, i3, i4 - 1));
			tessellator5.setColorOpaque_F(f11, f11, f11);
			this.flipTexture = b64 == 2;
			this.renderEastFace(block1, (double)i2, (double)i3, (double)i4, block1.getBlockTexture(this.blockAccess, i2, i3, i4, 2));
		}

		if(i27 != 3 && (this.renderAllFaces || block1.shouldSideBeRendered(this.blockAccess, i2, i3, i4 + 1, 3))) {
			tessellator5.func_35835_b(block1.maxZ < 1.0D ? i25 : block1.func_35275_c(this.blockAccess, i2, i3, i4 + 1));
			tessellator5.setColorOpaque_F(f11, f11, f11);
			this.flipTexture = b64 == 3;
			this.renderWestFace(block1, (double)i2, (double)i3, (double)i4, block1.getBlockTexture(this.blockAccess, i2, i3, i4, 3));
		}

		if(i27 != 4 && (this.renderAllFaces || block1.shouldSideBeRendered(this.blockAccess, i2 - 1, i3, i4, 4))) {
			tessellator5.func_35835_b(block1.minZ > 0.0D ? i25 : block1.func_35275_c(this.blockAccess, i2 - 1, i3, i4));
			tessellator5.setColorOpaque_F(f12, f12, f12);
			this.flipTexture = b64 == 4;
			this.renderNorthFace(block1, (double)i2, (double)i3, (double)i4, block1.getBlockTexture(this.blockAccess, i2, i3, i4, 4));
		}

		if(i27 != 5 && (this.renderAllFaces || block1.shouldSideBeRendered(this.blockAccess, i2 + 1, i3, i4, 5))) {
			tessellator5.func_35835_b(block1.maxZ < 1.0D ? i25 : block1.func_35275_c(this.blockAccess, i2 + 1, i3, i4));
			tessellator5.setColorOpaque_F(f12, f12, f12);
			this.flipTexture = b64 == 5;
			this.renderSouthFace(block1, (double)i2, (double)i3, (double)i4, block1.getBlockTexture(this.blockAccess, i2, i3, i4, 5));
		}

		this.flipTexture = false;
		return true;
	}

	public boolean renderBlockTorch(Block block1, int i2, int i3, int i4) {
		int i5 = this.blockAccess.getBlockMetadata(i2, i3, i4);
		Tessellator tessellator6 = Tessellator.instance;
		tessellator6.func_35835_b(block1.func_35275_c(this.blockAccess, i2, i3, i4));
		tessellator6.setColorOpaque_F(1.0F, 1.0F, 1.0F);
		double d7 = (double)0.4F;
		double d9 = 0.5D - d7;
		double d11 = (double)0.2F;
		if(i5 == 1) {
			this.renderTorchAtAngle(block1, (double)i2 - d9, (double)i3 + d11, (double)i4, -d7, 0.0D);
		} else if(i5 == 2) {
			this.renderTorchAtAngle(block1, (double)i2 + d9, (double)i3 + d11, (double)i4, d7, 0.0D);
		} else if(i5 == 3) {
			this.renderTorchAtAngle(block1, (double)i2, (double)i3 + d11, (double)i4 - d9, 0.0D, -d7);
		} else if(i5 == 4) {
			this.renderTorchAtAngle(block1, (double)i2, (double)i3 + d11, (double)i4 + d9, 0.0D, d7);
		} else {
			this.renderTorchAtAngle(block1, (double)i2, (double)i3, (double)i4, 0.0D, 0.0D);
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
			d14 = BlockRedstoneRepeater.repeaterTorchOffset[i7];
			break;
		case 1:
			d16 = 0.3125D;
			d12 = -BlockRedstoneRepeater.repeaterTorchOffset[i7];
			break;
		case 2:
			d18 = 0.3125D;
			d14 = -BlockRedstoneRepeater.repeaterTorchOffset[i7];
			break;
		case 3:
			d16 = -0.3125D;
			d12 = BlockRedstoneRepeater.repeaterTorchOffset[i7];
		}

		this.renderTorchAtAngle(block1, (double)i2 + d12, (double)i3 + d10, (double)i4 + d14, 0.0D, 0.0D);
		this.renderTorchAtAngle(block1, (double)i2 + d16, (double)i3 + d10, (double)i4 + d18, 0.0D, 0.0D);
		int i20 = block1.getBlockTextureFromSide(1);
		int i21 = (i20 & 15) << 4;
		int i22 = i20 & 240;
		double d23 = (double)((float)i21 / 256.0F);
		double d25 = (double)(((float)i21 + 15.99F) / 256.0F);
		double d27 = (double)((float)i22 / 256.0F);
		double d29 = (double)(((float)i22 + 15.99F) / 256.0F);
		double d31 = 0.125D;
		double d33 = (double)(i2 + 1);
		double d35 = (double)(i2 + 1);
		double d37 = (double)(i2 + 0);
		double d39 = (double)(i2 + 0);
		double d41 = (double)(i4 + 0);
		double d43 = (double)(i4 + 1);
		double d45 = (double)(i4 + 1);
		double d47 = (double)(i4 + 0);
		double d49 = (double)i3 + d31;
		if(i6 == 2) {
			d33 = d35 = (double)(i2 + 0);
			d37 = d39 = (double)(i2 + 1);
			d41 = d47 = (double)(i4 + 1);
			d43 = d45 = (double)(i4 + 0);
		} else if(i6 == 3) {
			d33 = d39 = (double)(i2 + 0);
			d35 = d37 = (double)(i2 + 1);
			d41 = d43 = (double)(i4 + 0);
			d45 = d47 = (double)(i4 + 1);
		} else if(i6 == 1) {
			d33 = d39 = (double)(i2 + 1);
			d35 = d37 = (double)(i2 + 0);
			d41 = d43 = (double)(i4 + 1);
			d45 = d47 = (double)(i4 + 0);
		}

		tessellator8.addVertexWithUV(d39, d49, d47, d23, d27);
		tessellator8.addVertexWithUV(d37, d49, d45, d23, d29);
		tessellator8.addVertexWithUV(d35, d49, d43, d25, d29);
		tessellator8.addVertexWithUV(d33, d49, d41, d25, d27);
		return true;
	}

	public void func_31078_d(Block block1, int i2, int i3, int i4) {
		this.renderAllFaces = true;
		this.renderPistonBase(block1, i2, i3, i4, true);
		this.renderAllFaces = false;
	}

	private boolean renderPistonBase(Block block1, int i2, int i3, int i4, boolean z5) {
		int i6 = this.blockAccess.getBlockMetadata(i2, i3, i4);
		boolean z7 = z5 || (i6 & 8) != 0;
		int i8 = BlockPistonBase.getOrientation(i6);
		if(z7) {
			switch(i8) {
			case 0:
				this.uvRotateEast = 3;
				this.uvRotateWest = 3;
				this.uvRotateSouth = 3;
				this.uvRotateNorth = 3;
				block1.setBlockBounds(0.0F, 0.25F, 0.0F, 1.0F, 1.0F, 1.0F);
				break;
			case 1:
				block1.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
				break;
			case 2:
				this.uvRotateSouth = 1;
				this.uvRotateNorth = 2;
				block1.setBlockBounds(0.0F, 0.0F, 0.25F, 1.0F, 1.0F, 1.0F);
				break;
			case 3:
				this.uvRotateSouth = 2;
				this.uvRotateNorth = 1;
				this.uvRotateTop = 3;
				this.uvRotateBottom = 3;
				block1.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.75F);
				break;
			case 4:
				this.uvRotateEast = 1;
				this.uvRotateWest = 2;
				this.uvRotateTop = 2;
				this.uvRotateBottom = 1;
				block1.setBlockBounds(0.25F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
				break;
			case 5:
				this.uvRotateEast = 2;
				this.uvRotateWest = 1;
				this.uvRotateTop = 1;
				this.uvRotateBottom = 2;
				block1.setBlockBounds(0.0F, 0.0F, 0.0F, 0.75F, 1.0F, 1.0F);
			}

			this.renderStandardBlock(block1, i2, i3, i4);
			this.uvRotateEast = 0;
			this.uvRotateWest = 0;
			this.uvRotateSouth = 0;
			this.uvRotateNorth = 0;
			this.uvRotateTop = 0;
			this.uvRotateBottom = 0;
			block1.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		} else {
			switch(i8) {
			case 0:
				this.uvRotateEast = 3;
				this.uvRotateWest = 3;
				this.uvRotateSouth = 3;
				this.uvRotateNorth = 3;
			case 1:
			default:
				break;
			case 2:
				this.uvRotateSouth = 1;
				this.uvRotateNorth = 2;
				break;
			case 3:
				this.uvRotateSouth = 2;
				this.uvRotateNorth = 1;
				this.uvRotateTop = 3;
				this.uvRotateBottom = 3;
				break;
			case 4:
				this.uvRotateEast = 1;
				this.uvRotateWest = 2;
				this.uvRotateTop = 2;
				this.uvRotateBottom = 1;
				break;
			case 5:
				this.uvRotateEast = 2;
				this.uvRotateWest = 1;
				this.uvRotateTop = 1;
				this.uvRotateBottom = 2;
			}

			this.renderStandardBlock(block1, i2, i3, i4);
			this.uvRotateEast = 0;
			this.uvRotateWest = 0;
			this.uvRotateSouth = 0;
			this.uvRotateNorth = 0;
			this.uvRotateTop = 0;
			this.uvRotateBottom = 0;
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
		double d20 = (double)((float)(i17 + 0) / 256.0F);
		double d22 = (double)((float)(i18 + 0) / 256.0F);
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
		double d20 = (double)((float)(i17 + 0) / 256.0F);
		double d22 = (double)((float)(i18 + 0) / 256.0F);
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
		double d20 = (double)((float)(i17 + 0) / 256.0F);
		double d22 = (double)((float)(i18 + 0) / 256.0F);
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
		this.renderPistonExtension(block1, i2, i3, i4, z5);
		this.renderAllFaces = false;
	}

	private boolean renderPistonExtension(Block block1, int i2, int i3, int i4, boolean z5) {
		int i6 = this.blockAccess.getBlockMetadata(i2, i3, i4);
		int i7 = BlockPistonExtension.func_31050_c(i6);
		float f11 = block1.getBlockBrightness(this.blockAccess, i2, i3, i4);
		float f12 = z5 ? 1.0F : 0.5F;
		double d13 = z5 ? 16.0D : 8.0D;
		switch(i7) {
		case 0:
			this.uvRotateEast = 3;
			this.uvRotateWest = 3;
			this.uvRotateSouth = 3;
			this.uvRotateNorth = 3;
			block1.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);
			this.renderStandardBlock(block1, i2, i3, i4);
			this.func_31076_a((double)((float)i2 + 0.375F), (double)((float)i2 + 0.625F), (double)((float)i3 + 0.25F), (double)((float)i3 + 0.25F + f12), (double)((float)i4 + 0.625F), (double)((float)i4 + 0.625F), f11 * 0.8F, d13);
			this.func_31076_a((double)((float)i2 + 0.625F), (double)((float)i2 + 0.375F), (double)((float)i3 + 0.25F), (double)((float)i3 + 0.25F + f12), (double)((float)i4 + 0.375F), (double)((float)i4 + 0.375F), f11 * 0.8F, d13);
			this.func_31076_a((double)((float)i2 + 0.375F), (double)((float)i2 + 0.375F), (double)((float)i3 + 0.25F), (double)((float)i3 + 0.25F + f12), (double)((float)i4 + 0.375F), (double)((float)i4 + 0.625F), f11 * 0.6F, d13);
			this.func_31076_a((double)((float)i2 + 0.625F), (double)((float)i2 + 0.625F), (double)((float)i3 + 0.25F), (double)((float)i3 + 0.25F + f12), (double)((float)i4 + 0.625F), (double)((float)i4 + 0.375F), f11 * 0.6F, d13);
			break;
		case 1:
			block1.setBlockBounds(0.0F, 0.75F, 0.0F, 1.0F, 1.0F, 1.0F);
			this.renderStandardBlock(block1, i2, i3, i4);
			this.func_31076_a((double)((float)i2 + 0.375F), (double)((float)i2 + 0.625F), (double)((float)i3 - 0.25F + 1.0F - f12), (double)((float)i3 - 0.25F + 1.0F), (double)((float)i4 + 0.625F), (double)((float)i4 + 0.625F), f11 * 0.8F, d13);
			this.func_31076_a((double)((float)i2 + 0.625F), (double)((float)i2 + 0.375F), (double)((float)i3 - 0.25F + 1.0F - f12), (double)((float)i3 - 0.25F + 1.0F), (double)((float)i4 + 0.375F), (double)((float)i4 + 0.375F), f11 * 0.8F, d13);
			this.func_31076_a((double)((float)i2 + 0.375F), (double)((float)i2 + 0.375F), (double)((float)i3 - 0.25F + 1.0F - f12), (double)((float)i3 - 0.25F + 1.0F), (double)((float)i4 + 0.375F), (double)((float)i4 + 0.625F), f11 * 0.6F, d13);
			this.func_31076_a((double)((float)i2 + 0.625F), (double)((float)i2 + 0.625F), (double)((float)i3 - 0.25F + 1.0F - f12), (double)((float)i3 - 0.25F + 1.0F), (double)((float)i4 + 0.625F), (double)((float)i4 + 0.375F), f11 * 0.6F, d13);
			break;
		case 2:
			this.uvRotateSouth = 1;
			this.uvRotateNorth = 2;
			block1.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.25F);
			this.renderStandardBlock(block1, i2, i3, i4);
			this.func_31081_b((double)((float)i2 + 0.375F), (double)((float)i2 + 0.375F), (double)((float)i3 + 0.625F), (double)((float)i3 + 0.375F), (double)((float)i4 + 0.25F), (double)((float)i4 + 0.25F + f12), f11 * 0.6F, d13);
			this.func_31081_b((double)((float)i2 + 0.625F), (double)((float)i2 + 0.625F), (double)((float)i3 + 0.375F), (double)((float)i3 + 0.625F), (double)((float)i4 + 0.25F), (double)((float)i4 + 0.25F + f12), f11 * 0.6F, d13);
			this.func_31081_b((double)((float)i2 + 0.375F), (double)((float)i2 + 0.625F), (double)((float)i3 + 0.375F), (double)((float)i3 + 0.375F), (double)((float)i4 + 0.25F), (double)((float)i4 + 0.25F + f12), f11 * 0.5F, d13);
			this.func_31081_b((double)((float)i2 + 0.625F), (double)((float)i2 + 0.375F), (double)((float)i3 + 0.625F), (double)((float)i3 + 0.625F), (double)((float)i4 + 0.25F), (double)((float)i4 + 0.25F + f12), f11, d13);
			break;
		case 3:
			this.uvRotateSouth = 2;
			this.uvRotateNorth = 1;
			this.uvRotateTop = 3;
			this.uvRotateBottom = 3;
			block1.setBlockBounds(0.0F, 0.0F, 0.75F, 1.0F, 1.0F, 1.0F);
			this.renderStandardBlock(block1, i2, i3, i4);
			this.func_31081_b((double)((float)i2 + 0.375F), (double)((float)i2 + 0.375F), (double)((float)i3 + 0.625F), (double)((float)i3 + 0.375F), (double)((float)i4 - 0.25F + 1.0F - f12), (double)((float)i4 - 0.25F + 1.0F), f11 * 0.6F, d13);
			this.func_31081_b((double)((float)i2 + 0.625F), (double)((float)i2 + 0.625F), (double)((float)i3 + 0.375F), (double)((float)i3 + 0.625F), (double)((float)i4 - 0.25F + 1.0F - f12), (double)((float)i4 - 0.25F + 1.0F), f11 * 0.6F, d13);
			this.func_31081_b((double)((float)i2 + 0.375F), (double)((float)i2 + 0.625F), (double)((float)i3 + 0.375F), (double)((float)i3 + 0.375F), (double)((float)i4 - 0.25F + 1.0F - f12), (double)((float)i4 - 0.25F + 1.0F), f11 * 0.5F, d13);
			this.func_31081_b((double)((float)i2 + 0.625F), (double)((float)i2 + 0.375F), (double)((float)i3 + 0.625F), (double)((float)i3 + 0.625F), (double)((float)i4 - 0.25F + 1.0F - f12), (double)((float)i4 - 0.25F + 1.0F), f11, d13);
			break;
		case 4:
			this.uvRotateEast = 1;
			this.uvRotateWest = 2;
			this.uvRotateTop = 2;
			this.uvRotateBottom = 1;
			block1.setBlockBounds(0.0F, 0.0F, 0.0F, 0.25F, 1.0F, 1.0F);
			this.renderStandardBlock(block1, i2, i3, i4);
			this.func_31077_c((double)((float)i2 + 0.25F), (double)((float)i2 + 0.25F + f12), (double)((float)i3 + 0.375F), (double)((float)i3 + 0.375F), (double)((float)i4 + 0.625F), (double)((float)i4 + 0.375F), f11 * 0.5F, d13);
			this.func_31077_c((double)((float)i2 + 0.25F), (double)((float)i2 + 0.25F + f12), (double)((float)i3 + 0.625F), (double)((float)i3 + 0.625F), (double)((float)i4 + 0.375F), (double)((float)i4 + 0.625F), f11, d13);
			this.func_31077_c((double)((float)i2 + 0.25F), (double)((float)i2 + 0.25F + f12), (double)((float)i3 + 0.375F), (double)((float)i3 + 0.625F), (double)((float)i4 + 0.375F), (double)((float)i4 + 0.375F), f11 * 0.6F, d13);
			this.func_31077_c((double)((float)i2 + 0.25F), (double)((float)i2 + 0.25F + f12), (double)((float)i3 + 0.625F), (double)((float)i3 + 0.375F), (double)((float)i4 + 0.625F), (double)((float)i4 + 0.625F), f11 * 0.6F, d13);
			break;
		case 5:
			this.uvRotateEast = 2;
			this.uvRotateWest = 1;
			this.uvRotateTop = 1;
			this.uvRotateBottom = 2;
			block1.setBlockBounds(0.75F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
			this.renderStandardBlock(block1, i2, i3, i4);
			this.func_31077_c((double)((float)i2 - 0.25F + 1.0F - f12), (double)((float)i2 - 0.25F + 1.0F), (double)((float)i3 + 0.375F), (double)((float)i3 + 0.375F), (double)((float)i4 + 0.625F), (double)((float)i4 + 0.375F), f11 * 0.5F, d13);
			this.func_31077_c((double)((float)i2 - 0.25F + 1.0F - f12), (double)((float)i2 - 0.25F + 1.0F), (double)((float)i3 + 0.625F), (double)((float)i3 + 0.625F), (double)((float)i4 + 0.375F), (double)((float)i4 + 0.625F), f11, d13);
			this.func_31077_c((double)((float)i2 - 0.25F + 1.0F - f12), (double)((float)i2 - 0.25F + 1.0F), (double)((float)i3 + 0.375F), (double)((float)i3 + 0.625F), (double)((float)i4 + 0.375F), (double)((float)i4 + 0.375F), f11 * 0.6F, d13);
			this.func_31077_c((double)((float)i2 - 0.25F + 1.0F - f12), (double)((float)i2 - 0.25F + 1.0F), (double)((float)i3 + 0.625F), (double)((float)i3 + 0.375F), (double)((float)i4 + 0.625F), (double)((float)i4 + 0.625F), f11 * 0.6F, d13);
		}

		this.uvRotateEast = 0;
		this.uvRotateWest = 0;
		this.uvRotateSouth = 0;
		this.uvRotateNorth = 0;
		this.uvRotateTop = 0;
		this.uvRotateBottom = 0;
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

		tessellator8.func_35835_b(block1.func_35275_c(this.blockAccess, i2, i3, i4));
		float f13 = 1.0F;
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
		vec3D21[0] = Vec3D.createVector((double)(-f22), 0.0D, (double)(-f23));
		vec3D21[1] = Vec3D.createVector((double)f22, 0.0D, (double)(-f23));
		vec3D21[2] = Vec3D.createVector((double)f22, 0.0D, (double)f23);
		vec3D21[3] = Vec3D.createVector((double)(-f22), 0.0D, (double)f23);
		vec3D21[4] = Vec3D.createVector((double)(-f22), (double)f24, (double)(-f23));
		vec3D21[5] = Vec3D.createVector((double)f22, (double)f24, (double)(-f23));
		vec3D21[6] = Vec3D.createVector((double)f22, (double)f24, (double)f23);
		vec3D21[7] = Vec3D.createVector((double)(-f22), (double)f24, (double)f23);

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
				vec3D21[i25].yCoord += (double)((float)i3 + 0.5F);
				vec3D21[i25].zCoord += (double)i4 + 0.5D;
			} else {
				vec3D21[i25].xCoord += (double)i2 + 0.5D;
				vec3D21[i25].yCoord += (double)((float)i3 + 0.125F);
				vec3D21[i25].zCoord += (double)i4 + 0.5D;
			}
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
			} else if(i29 == 5) {
				vec3D30 = vec3D21[0];
				vec3D26 = vec3D21[3];
				vec3D27 = vec3D21[7];
				vec3D28 = vec3D21[4];
			}

			tessellator8.addVertexWithUV(vec3D30.xCoord, vec3D30.yCoord, vec3D30.zCoord, (double)f17, (double)f20);
			tessellator8.addVertexWithUV(vec3D26.xCoord, vec3D26.yCoord, vec3D26.zCoord, (double)f18, (double)f20);
			tessellator8.addVertexWithUV(vec3D27.xCoord, vec3D27.yCoord, vec3D27.zCoord, (double)f18, (double)f19);
			tessellator8.addVertexWithUV(vec3D28.xCoord, vec3D28.yCoord, vec3D28.zCoord, (double)f17, (double)f19);
		}

		return true;
	}

	public boolean func_35921_b(int i1, int i2, int i3, int i4) {
		Tessellator tessellator5 = Tessellator.instance;

		for(int i6 = 0; i6 < 6; ++i6) {
			int i7 = i2;
			int i8 = i3;
			int i9 = i4;
			if((i6 != 2 && i6 != 3 || (i1 - 1) / 3 != 1) && (i6 != 4 && i6 != 5 || (i1 - 1) % 3 != 1)) {
				int i10 = 252;
				int i11 = 0;
				if(i1 == 2) {
					i11 = 0;
				}

				if(i1 == 4) {
					i11 = 1;
				}

				if(i1 == 8) {
					i11 = 2;
				}

				if(i1 == 6) {
					i11 = 3;
				}

				if(i1 == 1) {
					i11 = 0;
				}

				if(i1 == 3) {
					i11 = 3;
				}

				if(i1 == 7) {
					i11 = 1;
				}

				if(i1 == 9) {
					i11 = 2;
				}

				if(i1 != 2 && i1 != 4 && i1 != 6 && i1 != 8) {
					if(i6 >= 2) {
						if(i1 == 1) {
							--i10;
							if(i6 == 5 || i6 == 3) {
								continue;
							}

							if(i6 == 4) {
								++i11;
							}
						} else if(i1 == 3) {
							--i10;
							if(i6 == 5 || i6 == 2) {
								continue;
							}

							if(i6 == 3) {
								++i11;
							}
						} else if(i1 == 7) {
							--i10;
							if(i6 == 4 || i6 == 3) {
								continue;
							}

							if(i6 == 2) {
								++i11;
							}
						} else if(i1 == 9) {
							--i10;
							if(i6 == 4 || i6 == 2) {
								continue;
							}

							if(i6 == 5) {
								++i11;
							}
						}
					}
				} else {
					--i10;
				}

				if(i6 == 0) {
					i8 = i3 + 1;
				}

				if(i6 == 1) {
					--i8;
				}

				if(i6 == 2) {
					i7 = i2 + 1;
				}

				if(i6 == 3) {
					--i7;
				}

				if(i6 == 4) {
					i9 = i4 + 1;
				}

				if(i6 == 5) {
					--i9;
				}

				if(!this.field_35942_c[this.blockAccess.getBlockId(i7, i8, i9)]) {
					short s12 = 16384;
					tessellator5.setColorOpaque_I(s12);
					tessellator5.func_35835_b(this.blockAccess.func_35451_b(i7, i8, i9, 2));
					int i13 = (i10 & 15) << 4;
					int i14 = i10 & 240;
					double d15 = ((double)i13 + 0.0D) / 256.0D;
					double d17 = ((double)i13 + 15.99D) / 256.0D;
					double d19 = (double)((float)i14 / 256.0F);
					double d21 = (double)(((float)i14 + 15.99F) / 256.0F);
					double d23 = d15;
					double d25 = d17;
					double d27 = d17;
					double d29 = d15;
					double d31 = d21;
					double d33 = d21;
					double d35 = d19;
					double d37 = d19;
					if(i11 != 0) {
						if(i11 == 1) {
							d23 = d15;
							d25 = d15;
							d27 = d17;
							d29 = d17;
							d31 = d19;
							d33 = d21;
							d35 = d21;
							d37 = d19;
						} else if(i11 == 2) {
							d23 = d17;
							d25 = d15;
							d27 = d15;
							d29 = d17;
							d31 = d19;
							d33 = d19;
							d35 = d21;
							d37 = d21;
						} else if(i11 == 3) {
							d23 = d17;
							d25 = d17;
							d27 = d15;
							d29 = d15;
							d31 = d21;
							d33 = d19;
							d35 = d19;
							d37 = d21;
						}
					}

					double d39 = 0.0031250000465661287D;
					double d41 = 0.0D - d39;
					double d43 = 1.0D + d39;
					if(i6 == 0) {
						tessellator5.addVertexWithUV((double)i2 + d41, (double)(i3 + 1) + d39, (double)i4 + d41, d25, d33);
						tessellator5.addVertexWithUV((double)i2 + d41, (double)(i3 + 1) + d39, (double)i4 + d43, d27, d35);
						tessellator5.addVertexWithUV((double)i2 + d43, (double)(i3 + 1) + d39, (double)i4 + d43, d29, d37);
						tessellator5.addVertexWithUV((double)i2 + d43, (double)(i3 + 1) + d39, (double)i4 + d41, d23, d31);
					} else if(i6 == 1) {
						tessellator5.addVertexWithUV((double)i2 + d41, (double)(i3 + 0) - d39, (double)i4 + d43, d27, d35);
						tessellator5.addVertexWithUV((double)i2 + d41, (double)(i3 + 0) - d39, (double)i4 + d41, d25, d33);
						tessellator5.addVertexWithUV((double)i2 + d43, (double)(i3 + 0) - d39, (double)i4 + d41, d23, d31);
						tessellator5.addVertexWithUV((double)i2 + d43, (double)(i3 + 0) - d39, (double)i4 + d43, d29, d37);
					} else if(i6 == 2) {
						tessellator5.addVertexWithUV((double)(i2 + 1) + d39, (double)i3 + d41, (double)i4 + d43, d27, d35);
						tessellator5.addVertexWithUV((double)(i2 + 1) + d39, (double)i3 + d41, (double)i4 + d41, d25, d33);
						tessellator5.addVertexWithUV((double)(i2 + 1) + d39, (double)i3 + d43, (double)i4 + d41, d23, d31);
						tessellator5.addVertexWithUV((double)(i2 + 1) + d39, (double)i3 + d43, (double)i4 + d43, d29, d37);
					} else if(i6 == 3) {
						tessellator5.addVertexWithUV((double)(i2 + 0) - d39, (double)i3 + d41, (double)i4 + d41, d25, d33);
						tessellator5.addVertexWithUV((double)(i2 + 0) - d39, (double)i3 + d41, (double)i4 + d43, d27, d35);
						tessellator5.addVertexWithUV((double)(i2 + 0) - d39, (double)i3 + d43, (double)i4 + d43, d29, d37);
						tessellator5.addVertexWithUV((double)(i2 + 0) - d39, (double)i3 + d43, (double)i4 + d41, d23, d31);
					} else if(i6 == 4) {
						tessellator5.addVertexWithUV((double)i2 + d41, (double)i3 + d43, (double)(i4 + 1) + d39, d27, d35);
						tessellator5.addVertexWithUV((double)i2 + d41, (double)i3 + d41, (double)(i4 + 1) + d39, d25, d33);
						tessellator5.addVertexWithUV((double)i2 + d43, (double)i3 + d41, (double)(i4 + 1) + d39, d23, d31);
						tessellator5.addVertexWithUV((double)i2 + d43, (double)i3 + d43, (double)(i4 + 1) + d39, d29, d37);
					} else if(i6 == 5) {
						tessellator5.addVertexWithUV((double)i2 + d43, (double)i3 + d41, (double)(i4 + 0) - d39, d23, d31);
						tessellator5.addVertexWithUV((double)i2 + d41, (double)i3 + d41, (double)(i4 + 0) - d39, d25, d33);
						tessellator5.addVertexWithUV((double)i2 + d41, (double)i3 + d43, (double)(i4 + 0) - d39, d27, d35);
						tessellator5.addVertexWithUV((double)i2 + d43, (double)i3 + d43, (double)(i4 + 0) - d39, d29, d37);
					}
				}
			}
		}

		return true;
	}

	public boolean renderBlockFire(Block block1, int i2, int i3, int i4) {
		Tessellator tessellator5 = Tessellator.instance;
		int i6 = block1.getBlockTextureFromSide(0);
		if(this.overrideBlockTexture >= 0) {
			i6 = this.overrideBlockTexture;
		}

		tessellator5.setColorOpaque_F(1.0F, 1.0F, 1.0F);
		tessellator5.func_35835_b(block1.func_35275_c(this.blockAccess, i2, i3, i4));
		int i7 = (i6 & 15) << 4;
		int i8 = i6 & 240;
		double d9 = (double)((float)i7 / 256.0F);
		double d11 = (double)(((float)i7 + 15.99F) / 256.0F);
		double d13 = (double)((float)i8 / 256.0F);
		double d15 = (double)(((float)i8 + 15.99F) / 256.0F);
		float f17 = 1.4F;
		double d20;
		double d22;
		double d24;
		double d26;
		double d28;
		double d30;
		double d32;
		if(!this.blockAccess.isBlockNormalCube(i2, i3 - 1, i4) && !Block.fire.canBlockCatchFire(this.blockAccess, i2, i3 - 1, i4)) {
			float f36 = 0.2F;
			float f19 = 0.0625F;
			if((i2 + i3 + i4 & 1) == 1) {
				d9 = (double)((float)i7 / 256.0F);
				d11 = (double)(((float)i7 + 15.99F) / 256.0F);
				d13 = (double)((float)(i8 + 16) / 256.0F);
				d15 = (double)(((float)i8 + 15.99F + 16.0F) / 256.0F);
			}

			if((i2 / 2 + i3 / 2 + i4 / 2 & 1) == 1) {
				d20 = d11;
				d11 = d9;
				d9 = d20;
			}

			if(Block.fire.canBlockCatchFire(this.blockAccess, i2 - 1, i3, i4)) {
				tessellator5.addVertexWithUV((double)((float)i2 + f36), (double)((float)i3 + f17 + f19), (double)(i4 + 1), d11, d13);
				tessellator5.addVertexWithUV((double)(i2 + 0), (double)((float)(i3 + 0) + f19), (double)(i4 + 1), d11, d15);
				tessellator5.addVertexWithUV((double)(i2 + 0), (double)((float)(i3 + 0) + f19), (double)(i4 + 0), d9, d15);
				tessellator5.addVertexWithUV((double)((float)i2 + f36), (double)((float)i3 + f17 + f19), (double)(i4 + 0), d9, d13);
				tessellator5.addVertexWithUV((double)((float)i2 + f36), (double)((float)i3 + f17 + f19), (double)(i4 + 0), d9, d13);
				tessellator5.addVertexWithUV((double)(i2 + 0), (double)((float)(i3 + 0) + f19), (double)(i4 + 0), d9, d15);
				tessellator5.addVertexWithUV((double)(i2 + 0), (double)((float)(i3 + 0) + f19), (double)(i4 + 1), d11, d15);
				tessellator5.addVertexWithUV((double)((float)i2 + f36), (double)((float)i3 + f17 + f19), (double)(i4 + 1), d11, d13);
			}

			if(Block.fire.canBlockCatchFire(this.blockAccess, i2 + 1, i3, i4)) {
				tessellator5.addVertexWithUV((double)((float)(i2 + 1) - f36), (double)((float)i3 + f17 + f19), (double)(i4 + 0), d9, d13);
				tessellator5.addVertexWithUV((double)(i2 + 1 - 0), (double)((float)(i3 + 0) + f19), (double)(i4 + 0), d9, d15);
				tessellator5.addVertexWithUV((double)(i2 + 1 - 0), (double)((float)(i3 + 0) + f19), (double)(i4 + 1), d11, d15);
				tessellator5.addVertexWithUV((double)((float)(i2 + 1) - f36), (double)((float)i3 + f17 + f19), (double)(i4 + 1), d11, d13);
				tessellator5.addVertexWithUV((double)((float)(i2 + 1) - f36), (double)((float)i3 + f17 + f19), (double)(i4 + 1), d11, d13);
				tessellator5.addVertexWithUV((double)(i2 + 1 - 0), (double)((float)(i3 + 0) + f19), (double)(i4 + 1), d11, d15);
				tessellator5.addVertexWithUV((double)(i2 + 1 - 0), (double)((float)(i3 + 0) + f19), (double)(i4 + 0), d9, d15);
				tessellator5.addVertexWithUV((double)((float)(i2 + 1) - f36), (double)((float)i3 + f17 + f19), (double)(i4 + 0), d9, d13);
			}

			if(Block.fire.canBlockCatchFire(this.blockAccess, i2, i3, i4 - 1)) {
				tessellator5.addVertexWithUV((double)(i2 + 0), (double)((float)i3 + f17 + f19), (double)((float)i4 + f36), d11, d13);
				tessellator5.addVertexWithUV((double)(i2 + 0), (double)((float)(i3 + 0) + f19), (double)(i4 + 0), d11, d15);
				tessellator5.addVertexWithUV((double)(i2 + 1), (double)((float)(i3 + 0) + f19), (double)(i4 + 0), d9, d15);
				tessellator5.addVertexWithUV((double)(i2 + 1), (double)((float)i3 + f17 + f19), (double)((float)i4 + f36), d9, d13);
				tessellator5.addVertexWithUV((double)(i2 + 1), (double)((float)i3 + f17 + f19), (double)((float)i4 + f36), d9, d13);
				tessellator5.addVertexWithUV((double)(i2 + 1), (double)((float)(i3 + 0) + f19), (double)(i4 + 0), d9, d15);
				tessellator5.addVertexWithUV((double)(i2 + 0), (double)((float)(i3 + 0) + f19), (double)(i4 + 0), d11, d15);
				tessellator5.addVertexWithUV((double)(i2 + 0), (double)((float)i3 + f17 + f19), (double)((float)i4 + f36), d11, d13);
			}

			if(Block.fire.canBlockCatchFire(this.blockAccess, i2, i3, i4 + 1)) {
				tessellator5.addVertexWithUV((double)(i2 + 1), (double)((float)i3 + f17 + f19), (double)((float)(i4 + 1) - f36), d9, d13);
				tessellator5.addVertexWithUV((double)(i2 + 1), (double)((float)(i3 + 0) + f19), (double)(i4 + 1 - 0), d9, d15);
				tessellator5.addVertexWithUV((double)(i2 + 0), (double)((float)(i3 + 0) + f19), (double)(i4 + 1 - 0), d11, d15);
				tessellator5.addVertexWithUV((double)(i2 + 0), (double)((float)i3 + f17 + f19), (double)((float)(i4 + 1) - f36), d11, d13);
				tessellator5.addVertexWithUV((double)(i2 + 0), (double)((float)i3 + f17 + f19), (double)((float)(i4 + 1) - f36), d11, d13);
				tessellator5.addVertexWithUV((double)(i2 + 0), (double)((float)(i3 + 0) + f19), (double)(i4 + 1 - 0), d11, d15);
				tessellator5.addVertexWithUV((double)(i2 + 1), (double)((float)(i3 + 0) + f19), (double)(i4 + 1 - 0), d9, d15);
				tessellator5.addVertexWithUV((double)(i2 + 1), (double)((float)i3 + f17 + f19), (double)((float)(i4 + 1) - f36), d9, d13);
			}

			if(Block.fire.canBlockCatchFire(this.blockAccess, i2, i3 + 1, i4)) {
				d20 = (double)i2 + 0.5D + 0.5D;
				d22 = (double)i2 + 0.5D - 0.5D;
				d24 = (double)i4 + 0.5D + 0.5D;
				d26 = (double)i4 + 0.5D - 0.5D;
				d28 = (double)i2 + 0.5D - 0.5D;
				d30 = (double)i2 + 0.5D + 0.5D;
				d32 = (double)i4 + 0.5D - 0.5D;
				double d34 = (double)i4 + 0.5D + 0.5D;
				d9 = (double)((float)i7 / 256.0F);
				d11 = (double)(((float)i7 + 15.99F) / 256.0F);
				d13 = (double)((float)i8 / 256.0F);
				d15 = (double)(((float)i8 + 15.99F) / 256.0F);
				++i3;
				f17 = -0.2F;
				if((i2 + i3 + i4 & 1) == 0) {
					tessellator5.addVertexWithUV(d28, (double)((float)i3 + f17), (double)(i4 + 0), d11, d13);
					tessellator5.addVertexWithUV(d20, (double)(i3 + 0), (double)(i4 + 0), d11, d15);
					tessellator5.addVertexWithUV(d20, (double)(i3 + 0), (double)(i4 + 1), d9, d15);
					tessellator5.addVertexWithUV(d28, (double)((float)i3 + f17), (double)(i4 + 1), d9, d13);
					d9 = (double)((float)i7 / 256.0F);
					d11 = (double)(((float)i7 + 15.99F) / 256.0F);
					d13 = (double)((float)(i8 + 16) / 256.0F);
					d15 = (double)(((float)i8 + 15.99F + 16.0F) / 256.0F);
					tessellator5.addVertexWithUV(d30, (double)((float)i3 + f17), (double)(i4 + 1), d11, d13);
					tessellator5.addVertexWithUV(d22, (double)(i3 + 0), (double)(i4 + 1), d11, d15);
					tessellator5.addVertexWithUV(d22, (double)(i3 + 0), (double)(i4 + 0), d9, d15);
					tessellator5.addVertexWithUV(d30, (double)((float)i3 + f17), (double)(i4 + 0), d9, d13);
				} else {
					tessellator5.addVertexWithUV((double)(i2 + 0), (double)((float)i3 + f17), d34, d11, d13);
					tessellator5.addVertexWithUV((double)(i2 + 0), (double)(i3 + 0), d26, d11, d15);
					tessellator5.addVertexWithUV((double)(i2 + 1), (double)(i3 + 0), d26, d9, d15);
					tessellator5.addVertexWithUV((double)(i2 + 1), (double)((float)i3 + f17), d34, d9, d13);
					d9 = (double)((float)i7 / 256.0F);
					d11 = (double)(((float)i7 + 15.99F) / 256.0F);
					d13 = (double)((float)(i8 + 16) / 256.0F);
					d15 = (double)(((float)i8 + 15.99F + 16.0F) / 256.0F);
					tessellator5.addVertexWithUV((double)(i2 + 1), (double)((float)i3 + f17), d32, d11, d13);
					tessellator5.addVertexWithUV((double)(i2 + 1), (double)(i3 + 0), d24, d11, d15);
					tessellator5.addVertexWithUV((double)(i2 + 0), (double)(i3 + 0), d24, d9, d15);
					tessellator5.addVertexWithUV((double)(i2 + 0), (double)((float)i3 + f17), d32, d9, d13);
				}
			}
		} else {
			double d18 = (double)i2 + 0.5D + 0.2D;
			d20 = (double)i2 + 0.5D - 0.2D;
			d22 = (double)i4 + 0.5D + 0.2D;
			d24 = (double)i4 + 0.5D - 0.2D;
			d26 = (double)i2 + 0.5D - 0.3D;
			d28 = (double)i2 + 0.5D + 0.3D;
			d30 = (double)i4 + 0.5D - 0.3D;
			d32 = (double)i4 + 0.5D + 0.3D;
			tessellator5.addVertexWithUV(d26, (double)((float)i3 + f17), (double)(i4 + 1), d11, d13);
			tessellator5.addVertexWithUV(d18, (double)(i3 + 0), (double)(i4 + 1), d11, d15);
			tessellator5.addVertexWithUV(d18, (double)(i3 + 0), (double)(i4 + 0), d9, d15);
			tessellator5.addVertexWithUV(d26, (double)((float)i3 + f17), (double)(i4 + 0), d9, d13);
			tessellator5.addVertexWithUV(d28, (double)((float)i3 + f17), (double)(i4 + 0), d11, d13);
			tessellator5.addVertexWithUV(d20, (double)(i3 + 0), (double)(i4 + 0), d11, d15);
			tessellator5.addVertexWithUV(d20, (double)(i3 + 0), (double)(i4 + 1), d9, d15);
			tessellator5.addVertexWithUV(d28, (double)((float)i3 + f17), (double)(i4 + 1), d9, d13);
			d9 = (double)((float)i7 / 256.0F);
			d11 = (double)(((float)i7 + 15.99F) / 256.0F);
			d13 = (double)((float)(i8 + 16) / 256.0F);
			d15 = (double)(((float)i8 + 15.99F + 16.0F) / 256.0F);
			tessellator5.addVertexWithUV((double)(i2 + 1), (double)((float)i3 + f17), d32, d11, d13);
			tessellator5.addVertexWithUV((double)(i2 + 1), (double)(i3 + 0), d24, d11, d15);
			tessellator5.addVertexWithUV((double)(i2 + 0), (double)(i3 + 0), d24, d9, d15);
			tessellator5.addVertexWithUV((double)(i2 + 0), (double)((float)i3 + f17), d32, d9, d13);
			tessellator5.addVertexWithUV((double)(i2 + 0), (double)((float)i3 + f17), d30, d11, d13);
			tessellator5.addVertexWithUV((double)(i2 + 0), (double)(i3 + 0), d22, d11, d15);
			tessellator5.addVertexWithUV((double)(i2 + 1), (double)(i3 + 0), d22, d9, d15);
			tessellator5.addVertexWithUV((double)(i2 + 1), (double)((float)i3 + f17), d30, d9, d13);
			d18 = (double)i2 + 0.5D - 0.5D;
			d20 = (double)i2 + 0.5D + 0.5D;
			d22 = (double)i4 + 0.5D - 0.5D;
			d24 = (double)i4 + 0.5D + 0.5D;
			d26 = (double)i2 + 0.5D - 0.4D;
			d28 = (double)i2 + 0.5D + 0.4D;
			d30 = (double)i4 + 0.5D - 0.4D;
			d32 = (double)i4 + 0.5D + 0.4D;
			tessellator5.addVertexWithUV(d26, (double)((float)i3 + f17), (double)(i4 + 0), d9, d13);
			tessellator5.addVertexWithUV(d18, (double)(i3 + 0), (double)(i4 + 0), d9, d15);
			tessellator5.addVertexWithUV(d18, (double)(i3 + 0), (double)(i4 + 1), d11, d15);
			tessellator5.addVertexWithUV(d26, (double)((float)i3 + f17), (double)(i4 + 1), d11, d13);
			tessellator5.addVertexWithUV(d28, (double)((float)i3 + f17), (double)(i4 + 1), d9, d13);
			tessellator5.addVertexWithUV(d20, (double)(i3 + 0), (double)(i4 + 1), d9, d15);
			tessellator5.addVertexWithUV(d20, (double)(i3 + 0), (double)(i4 + 0), d11, d15);
			tessellator5.addVertexWithUV(d28, (double)((float)i3 + f17), (double)(i4 + 0), d11, d13);
			d9 = (double)((float)i7 / 256.0F);
			d11 = (double)(((float)i7 + 15.99F) / 256.0F);
			d13 = (double)((float)i8 / 256.0F);
			d15 = (double)(((float)i8 + 15.99F) / 256.0F);
			tessellator5.addVertexWithUV((double)(i2 + 0), (double)((float)i3 + f17), d32, d9, d13);
			tessellator5.addVertexWithUV((double)(i2 + 0), (double)(i3 + 0), d24, d9, d15);
			tessellator5.addVertexWithUV((double)(i2 + 1), (double)(i3 + 0), d24, d11, d15);
			tessellator5.addVertexWithUV((double)(i2 + 1), (double)((float)i3 + f17), d32, d11, d13);
			tessellator5.addVertexWithUV((double)(i2 + 1), (double)((float)i3 + f17), d30, d9, d13);
			tessellator5.addVertexWithUV((double)(i2 + 1), (double)(i3 + 0), d22, d9, d15);
			tessellator5.addVertexWithUV((double)(i2 + 0), (double)(i3 + 0), d22, d11, d15);
			tessellator5.addVertexWithUV((double)(i2 + 0), (double)((float)i3 + f17), d30, d11, d13);
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

		tessellator5.func_35835_b(block1.func_35275_c(this.blockAccess, i2, i3, i4));
		float f8 = 1.0F;
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

		tessellator5.setColorOpaque_F(f10, f11, f12);
		int i13 = (i7 & 15) << 4;
		int i14 = i7 & 240;
		double d15 = (double)((float)i13 / 256.0F);
		double d17 = (double)(((float)i13 + 15.99F) / 256.0F);
		double d19 = (double)((float)i14 / 256.0F);
		double d21 = (double)(((float)i14 + 15.99F) / 256.0F);
		boolean z29 = BlockRedstoneWire.isPowerProviderOrWire(this.blockAccess, i2 - 1, i3, i4, 1) || !this.blockAccess.isBlockNormalCube(i2 - 1, i3, i4) && BlockRedstoneWire.isPowerProviderOrWire(this.blockAccess, i2 - 1, i3 - 1, i4, -1);
		boolean z30 = BlockRedstoneWire.isPowerProviderOrWire(this.blockAccess, i2 + 1, i3, i4, 3) || !this.blockAccess.isBlockNormalCube(i2 + 1, i3, i4) && BlockRedstoneWire.isPowerProviderOrWire(this.blockAccess, i2 + 1, i3 - 1, i4, -1);
		boolean z31 = BlockRedstoneWire.isPowerProviderOrWire(this.blockAccess, i2, i3, i4 - 1, 2) || !this.blockAccess.isBlockNormalCube(i2, i3, i4 - 1) && BlockRedstoneWire.isPowerProviderOrWire(this.blockAccess, i2, i3 - 1, i4 - 1, -1);
		boolean z32 = BlockRedstoneWire.isPowerProviderOrWire(this.blockAccess, i2, i3, i4 + 1, 0) || !this.blockAccess.isBlockNormalCube(i2, i3, i4 + 1) && BlockRedstoneWire.isPowerProviderOrWire(this.blockAccess, i2, i3 - 1, i4 + 1, -1);
		if(!this.blockAccess.isBlockNormalCube(i2, i3 + 1, i4)) {
			if(this.blockAccess.isBlockNormalCube(i2 - 1, i3, i4) && BlockRedstoneWire.isPowerProviderOrWire(this.blockAccess, i2 - 1, i3 + 1, i4, -1)) {
				z29 = true;
			}

			if(this.blockAccess.isBlockNormalCube(i2 + 1, i3, i4) && BlockRedstoneWire.isPowerProviderOrWire(this.blockAccess, i2 + 1, i3 + 1, i4, -1)) {
				z30 = true;
			}

			if(this.blockAccess.isBlockNormalCube(i2, i3, i4 - 1) && BlockRedstoneWire.isPowerProviderOrWire(this.blockAccess, i2, i3 + 1, i4 - 1, -1)) {
				z31 = true;
			}

			if(this.blockAccess.isBlockNormalCube(i2, i3, i4 + 1) && BlockRedstoneWire.isPowerProviderOrWire(this.blockAccess, i2, i3 + 1, i4 + 1, -1)) {
				z32 = true;
			}
		}

		float f34 = (float)(i2 + 0);
		float f35 = (float)(i2 + 1);
		float f36 = (float)(i4 + 0);
		float f37 = (float)(i4 + 1);
		byte b38 = 0;
		if((z29 || z30) && !z31 && !z32) {
			b38 = 1;
		}

		if((z31 || z32) && !z30 && !z29) {
			b38 = 2;
		}

		if(b38 != 0) {
			d15 = (double)((float)(i13 + 16) / 256.0F);
			d17 = (double)(((float)(i13 + 16) + 15.99F) / 256.0F);
			d19 = (double)((float)i14 / 256.0F);
			d21 = (double)(((float)i14 + 15.99F) / 256.0F);
		}

		if(b38 == 0) {
			if(z30 || z31 || z32 || z29) {
				if(!z29) {
					f34 += 0.3125F;
				}

				if(!z29) {
					d15 += 0.01953125D;
				}

				if(!z30) {
					f35 -= 0.3125F;
				}

				if(!z30) {
					d17 -= 0.01953125D;
				}

				if(!z31) {
					f36 += 0.3125F;
				}

				if(!z31) {
					d19 += 0.01953125D;
				}

				if(!z32) {
					f37 -= 0.3125F;
				}

				if(!z32) {
					d21 -= 0.01953125D;
				}
			}

			tessellator5.addVertexWithUV((double)f35, (double)i3 + 0.015625D, (double)f37, d17, d21);
			tessellator5.addVertexWithUV((double)f35, (double)i3 + 0.015625D, (double)f36, d17, d19);
			tessellator5.addVertexWithUV((double)f34, (double)i3 + 0.015625D, (double)f36, d15, d19);
			tessellator5.addVertexWithUV((double)f34, (double)i3 + 0.015625D, (double)f37, d15, d21);
			tessellator5.setColorOpaque_F(f8, f8, f8);
			tessellator5.addVertexWithUV((double)f35, (double)i3 + 0.015625D, (double)f37, d17, d21 + 0.0625D);
			tessellator5.addVertexWithUV((double)f35, (double)i3 + 0.015625D, (double)f36, d17, d19 + 0.0625D);
			tessellator5.addVertexWithUV((double)f34, (double)i3 + 0.015625D, (double)f36, d15, d19 + 0.0625D);
			tessellator5.addVertexWithUV((double)f34, (double)i3 + 0.015625D, (double)f37, d15, d21 + 0.0625D);
		} else if(b38 == 1) {
			tessellator5.addVertexWithUV((double)f35, (double)i3 + 0.015625D, (double)f37, d17, d21);
			tessellator5.addVertexWithUV((double)f35, (double)i3 + 0.015625D, (double)f36, d17, d19);
			tessellator5.addVertexWithUV((double)f34, (double)i3 + 0.015625D, (double)f36, d15, d19);
			tessellator5.addVertexWithUV((double)f34, (double)i3 + 0.015625D, (double)f37, d15, d21);
			tessellator5.setColorOpaque_F(f8, f8, f8);
			tessellator5.addVertexWithUV((double)f35, (double)i3 + 0.015625D, (double)f37, d17, d21 + 0.0625D);
			tessellator5.addVertexWithUV((double)f35, (double)i3 + 0.015625D, (double)f36, d17, d19 + 0.0625D);
			tessellator5.addVertexWithUV((double)f34, (double)i3 + 0.015625D, (double)f36, d15, d19 + 0.0625D);
			tessellator5.addVertexWithUV((double)f34, (double)i3 + 0.015625D, (double)f37, d15, d21 + 0.0625D);
		} else if(b38 == 2) {
			tessellator5.addVertexWithUV((double)f35, (double)i3 + 0.015625D, (double)f37, d17, d21);
			tessellator5.addVertexWithUV((double)f35, (double)i3 + 0.015625D, (double)f36, d15, d21);
			tessellator5.addVertexWithUV((double)f34, (double)i3 + 0.015625D, (double)f36, d15, d19);
			tessellator5.addVertexWithUV((double)f34, (double)i3 + 0.015625D, (double)f37, d17, d19);
			tessellator5.setColorOpaque_F(f8, f8, f8);
			tessellator5.addVertexWithUV((double)f35, (double)i3 + 0.015625D, (double)f37, d17, d21 + 0.0625D);
			tessellator5.addVertexWithUV((double)f35, (double)i3 + 0.015625D, (double)f36, d15, d21 + 0.0625D);
			tessellator5.addVertexWithUV((double)f34, (double)i3 + 0.015625D, (double)f36, d15, d19 + 0.0625D);
			tessellator5.addVertexWithUV((double)f34, (double)i3 + 0.015625D, (double)f37, d17, d19 + 0.0625D);
		}

		if(!this.blockAccess.isBlockNormalCube(i2, i3 + 1, i4)) {
			d15 = (double)((float)(i13 + 16) / 256.0F);
			d17 = (double)(((float)(i13 + 16) + 15.99F) / 256.0F);
			d19 = (double)((float)i14 / 256.0F);
			d21 = (double)(((float)i14 + 15.99F) / 256.0F);
			if(this.blockAccess.isBlockNormalCube(i2 - 1, i3, i4) && this.blockAccess.getBlockId(i2 - 1, i3 + 1, i4) == Block.redstoneWire.blockID) {
				tessellator5.setColorOpaque_F(f8 * f10, f8 * f11, f8 * f12);
				tessellator5.addVertexWithUV((double)i2 + 0.015625D, (double)((float)(i3 + 1) + 0.021875F), (double)(i4 + 1), d17, d19);
				tessellator5.addVertexWithUV((double)i2 + 0.015625D, (double)(i3 + 0), (double)(i4 + 1), d15, d19);
				tessellator5.addVertexWithUV((double)i2 + 0.015625D, (double)(i3 + 0), (double)(i4 + 0), d15, d21);
				tessellator5.addVertexWithUV((double)i2 + 0.015625D, (double)((float)(i3 + 1) + 0.021875F), (double)(i4 + 0), d17, d21);
				tessellator5.setColorOpaque_F(f8, f8, f8);
				tessellator5.addVertexWithUV((double)i2 + 0.015625D, (double)((float)(i3 + 1) + 0.021875F), (double)(i4 + 1), d17, d19 + 0.0625D);
				tessellator5.addVertexWithUV((double)i2 + 0.015625D, (double)(i3 + 0), (double)(i4 + 1), d15, d19 + 0.0625D);
				tessellator5.addVertexWithUV((double)i2 + 0.015625D, (double)(i3 + 0), (double)(i4 + 0), d15, d21 + 0.0625D);
				tessellator5.addVertexWithUV((double)i2 + 0.015625D, (double)((float)(i3 + 1) + 0.021875F), (double)(i4 + 0), d17, d21 + 0.0625D);
			}

			if(this.blockAccess.isBlockNormalCube(i2 + 1, i3, i4) && this.blockAccess.getBlockId(i2 + 1, i3 + 1, i4) == Block.redstoneWire.blockID) {
				tessellator5.setColorOpaque_F(f8 * f10, f8 * f11, f8 * f12);
				tessellator5.addVertexWithUV((double)(i2 + 1) - 0.015625D, (double)(i3 + 0), (double)(i4 + 1), d15, d21);
				tessellator5.addVertexWithUV((double)(i2 + 1) - 0.015625D, (double)((float)(i3 + 1) + 0.021875F), (double)(i4 + 1), d17, d21);
				tessellator5.addVertexWithUV((double)(i2 + 1) - 0.015625D, (double)((float)(i3 + 1) + 0.021875F), (double)(i4 + 0), d17, d19);
				tessellator5.addVertexWithUV((double)(i2 + 1) - 0.015625D, (double)(i3 + 0), (double)(i4 + 0), d15, d19);
				tessellator5.setColorOpaque_F(f8, f8, f8);
				tessellator5.addVertexWithUV((double)(i2 + 1) - 0.015625D, (double)(i3 + 0), (double)(i4 + 1), d15, d21 + 0.0625D);
				tessellator5.addVertexWithUV((double)(i2 + 1) - 0.015625D, (double)((float)(i3 + 1) + 0.021875F), (double)(i4 + 1), d17, d21 + 0.0625D);
				tessellator5.addVertexWithUV((double)(i2 + 1) - 0.015625D, (double)((float)(i3 + 1) + 0.021875F), (double)(i4 + 0), d17, d19 + 0.0625D);
				tessellator5.addVertexWithUV((double)(i2 + 1) - 0.015625D, (double)(i3 + 0), (double)(i4 + 0), d15, d19 + 0.0625D);
			}

			if(this.blockAccess.isBlockNormalCube(i2, i3, i4 - 1) && this.blockAccess.getBlockId(i2, i3 + 1, i4 - 1) == Block.redstoneWire.blockID) {
				tessellator5.setColorOpaque_F(f8 * f10, f8 * f11, f8 * f12);
				tessellator5.addVertexWithUV((double)(i2 + 1), (double)(i3 + 0), (double)i4 + 0.015625D, d15, d21);
				tessellator5.addVertexWithUV((double)(i2 + 1), (double)((float)(i3 + 1) + 0.021875F), (double)i4 + 0.015625D, d17, d21);
				tessellator5.addVertexWithUV((double)(i2 + 0), (double)((float)(i3 + 1) + 0.021875F), (double)i4 + 0.015625D, d17, d19);
				tessellator5.addVertexWithUV((double)(i2 + 0), (double)(i3 + 0), (double)i4 + 0.015625D, d15, d19);
				tessellator5.setColorOpaque_F(f8, f8, f8);
				tessellator5.addVertexWithUV((double)(i2 + 1), (double)(i3 + 0), (double)i4 + 0.015625D, d15, d21 + 0.0625D);
				tessellator5.addVertexWithUV((double)(i2 + 1), (double)((float)(i3 + 1) + 0.021875F), (double)i4 + 0.015625D, d17, d21 + 0.0625D);
				tessellator5.addVertexWithUV((double)(i2 + 0), (double)((float)(i3 + 1) + 0.021875F), (double)i4 + 0.015625D, d17, d19 + 0.0625D);
				tessellator5.addVertexWithUV((double)(i2 + 0), (double)(i3 + 0), (double)i4 + 0.015625D, d15, d19 + 0.0625D);
			}

			if(this.blockAccess.isBlockNormalCube(i2, i3, i4 + 1) && this.blockAccess.getBlockId(i2, i3 + 1, i4 + 1) == Block.redstoneWire.blockID) {
				tessellator5.setColorOpaque_F(f8 * f10, f8 * f11, f8 * f12);
				tessellator5.addVertexWithUV((double)(i2 + 1), (double)((float)(i3 + 1) + 0.021875F), (double)(i4 + 1) - 0.015625D, d17, d19);
				tessellator5.addVertexWithUV((double)(i2 + 1), (double)(i3 + 0), (double)(i4 + 1) - 0.015625D, d15, d19);
				tessellator5.addVertexWithUV((double)(i2 + 0), (double)(i3 + 0), (double)(i4 + 1) - 0.015625D, d15, d21);
				tessellator5.addVertexWithUV((double)(i2 + 0), (double)((float)(i3 + 1) + 0.021875F), (double)(i4 + 1) - 0.015625D, d17, d21);
				tessellator5.setColorOpaque_F(f8, f8, f8);
				tessellator5.addVertexWithUV((double)(i2 + 1), (double)((float)(i3 + 1) + 0.021875F), (double)(i4 + 1) - 0.015625D, d17, d19 + 0.0625D);
				tessellator5.addVertexWithUV((double)(i2 + 1), (double)(i3 + 0), (double)(i4 + 1) - 0.015625D, d15, d19 + 0.0625D);
				tessellator5.addVertexWithUV((double)(i2 + 0), (double)(i3 + 0), (double)(i4 + 1) - 0.015625D, d15, d21 + 0.0625D);
				tessellator5.addVertexWithUV((double)(i2 + 0), (double)((float)(i3 + 1) + 0.021875F), (double)(i4 + 1) - 0.015625D, d17, d21 + 0.0625D);
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

		tessellator5.func_35835_b(blockRail1.func_35275_c(this.blockAccess, i2, i3, i4));
		tessellator5.setColorOpaque_F(1.0F, 1.0F, 1.0F);
		int i8 = (i7 & 15) << 4;
		int i9 = i7 & 240;
		double d10 = (double)((float)i8 / 256.0F);
		double d12 = (double)(((float)i8 + 15.99F) / 256.0F);
		double d14 = (double)((float)i9 / 256.0F);
		double d16 = (double)(((float)i9 + 15.99F) / 256.0F);
		double d18 = 0.0625D;
		double d20 = (double)(i2 + 1);
		double d22 = (double)(i2 + 1);
		double d24 = (double)(i2 + 0);
		double d26 = (double)(i2 + 0);
		double d28 = (double)(i4 + 0);
		double d30 = (double)(i4 + 1);
		double d32 = (double)(i4 + 1);
		double d34 = (double)(i4 + 0);
		double d36 = (double)i3 + d18;
		double d38 = (double)i3 + d18;
		double d40 = (double)i3 + d18;
		double d42 = (double)i3 + d18;
		if(i6 != 1 && i6 != 2 && i6 != 3 && i6 != 7) {
			if(i6 == 8) {
				d20 = d22 = (double)(i2 + 0);
				d24 = d26 = (double)(i2 + 1);
				d28 = d34 = (double)(i4 + 1);
				d30 = d32 = (double)(i4 + 0);
			} else if(i6 == 9) {
				d20 = d26 = (double)(i2 + 0);
				d22 = d24 = (double)(i2 + 1);
				d28 = d30 = (double)(i4 + 0);
				d32 = d34 = (double)(i4 + 1);
			}
		} else {
			d20 = d26 = (double)(i2 + 1);
			d22 = d24 = (double)(i2 + 0);
			d28 = d30 = (double)(i4 + 1);
			d32 = d34 = (double)(i4 + 0);
		}

		if(i6 != 2 && i6 != 4) {
			if(i6 == 3 || i6 == 5) {
				++d38;
				++d40;
			}
		} else {
			++d36;
			++d42;
		}

		tessellator5.addVertexWithUV(d20, d36, d28, d12, d14);
		tessellator5.addVertexWithUV(d22, d38, d30, d12, d16);
		tessellator5.addVertexWithUV(d24, d40, d32, d10, d16);
		tessellator5.addVertexWithUV(d26, d42, d34, d10, d14);
		tessellator5.addVertexWithUV(d26, d42, d34, d10, d14);
		tessellator5.addVertexWithUV(d24, d40, d32, d10, d16);
		tessellator5.addVertexWithUV(d22, d38, d30, d12, d16);
		tessellator5.addVertexWithUV(d20, d36, d28, d12, d14);
		return true;
	}

	public boolean renderBlockLadder(Block block1, int i2, int i3, int i4) {
		Tessellator tessellator5 = Tessellator.instance;
		int i6 = block1.getBlockTextureFromSide(0);
		if(this.overrideBlockTexture >= 0) {
			i6 = this.overrideBlockTexture;
		}

		tessellator5.func_35835_b(block1.func_35275_c(this.blockAccess, i2, i3, i4));
		float f7 = 1.0F;
		tessellator5.setColorOpaque_F(f7, f7, f7);
		int i22 = (i6 & 15) << 4;
		int i8 = i6 & 240;
		double d9 = (double)((float)i22 / 256.0F);
		double d11 = (double)(((float)i22 + 15.99F) / 256.0F);
		double d13 = (double)((float)i8 / 256.0F);
		double d15 = (double)(((float)i8 + 15.99F) / 256.0F);
		int i17 = this.blockAccess.getBlockMetadata(i2, i3, i4);
		double d18 = 0.0D;
		double d20 = (double)0.05F;
		if(i17 == 5) {
			tessellator5.addVertexWithUV((double)i2 + d20, (double)(i3 + 1) + d18, (double)(i4 + 1) + d18, d9, d13);
			tessellator5.addVertexWithUV((double)i2 + d20, (double)(i3 + 0) - d18, (double)(i4 + 1) + d18, d9, d15);
			tessellator5.addVertexWithUV((double)i2 + d20, (double)(i3 + 0) - d18, (double)(i4 + 0) - d18, d11, d15);
			tessellator5.addVertexWithUV((double)i2 + d20, (double)(i3 + 1) + d18, (double)(i4 + 0) - d18, d11, d13);
		}

		if(i17 == 4) {
			tessellator5.addVertexWithUV((double)(i2 + 1) - d20, (double)(i3 + 0) - d18, (double)(i4 + 1) + d18, d11, d15);
			tessellator5.addVertexWithUV((double)(i2 + 1) - d20, (double)(i3 + 1) + d18, (double)(i4 + 1) + d18, d11, d13);
			tessellator5.addVertexWithUV((double)(i2 + 1) - d20, (double)(i3 + 1) + d18, (double)(i4 + 0) - d18, d9, d13);
			tessellator5.addVertexWithUV((double)(i2 + 1) - d20, (double)(i3 + 0) - d18, (double)(i4 + 0) - d18, d9, d15);
		}

		if(i17 == 3) {
			tessellator5.addVertexWithUV((double)(i2 + 1) + d18, (double)(i3 + 0) - d18, (double)i4 + d20, d11, d15);
			tessellator5.addVertexWithUV((double)(i2 + 1) + d18, (double)(i3 + 1) + d18, (double)i4 + d20, d11, d13);
			tessellator5.addVertexWithUV((double)(i2 + 0) - d18, (double)(i3 + 1) + d18, (double)i4 + d20, d9, d13);
			tessellator5.addVertexWithUV((double)(i2 + 0) - d18, (double)(i3 + 0) - d18, (double)i4 + d20, d9, d15);
		}

		if(i17 == 2) {
			tessellator5.addVertexWithUV((double)(i2 + 1) + d18, (double)(i3 + 1) + d18, (double)(i4 + 1) - d20, d9, d13);
			tessellator5.addVertexWithUV((double)(i2 + 1) + d18, (double)(i3 + 0) - d18, (double)(i4 + 1) - d20, d9, d15);
			tessellator5.addVertexWithUV((double)(i2 + 0) - d18, (double)(i3 + 0) - d18, (double)(i4 + 1) - d20, d11, d15);
			tessellator5.addVertexWithUV((double)(i2 + 0) - d18, (double)(i3 + 1) + d18, (double)(i4 + 1) - d20, d11, d13);
		}

		return true;
	}

	public boolean func_35929_i(Block block1, int i2, int i3, int i4) {
		Tessellator tessellator5 = Tessellator.instance;
		int i6 = block1.getBlockTextureFromSide(0);
		if(this.overrideBlockTexture >= 0) {
			i6 = this.overrideBlockTexture;
		}

		float f7 = 1.0F;
		tessellator5.func_35835_b(block1.func_35275_c(this.blockAccess, i2, i3, i4));
		int i8 = block1.colorMultiplier(this.blockAccess, i2, i3, i4);
		float f9 = (float)(i8 >> 16 & 255) / 255.0F;
		float f10 = (float)(i8 >> 8 & 255) / 255.0F;
		float f11 = (float)(i8 & 255) / 255.0F;
		tessellator5.setColorOpaque_F(f7 * f9, f7 * f10, f7 * f11);
		i8 = (i6 & 15) << 4;
		int i21 = i6 & 240;
		double d22 = (double)((float)i8 / 256.0F);
		double d12 = (double)(((float)i8 + 15.99F) / 256.0F);
		double d14 = (double)((float)i21 / 256.0F);
		double d16 = (double)(((float)i21 + 15.99F) / 256.0F);
		double d18 = (double)0.05F;
		int i20 = this.blockAccess.getBlockMetadata(i2, i3, i4);
		if((i20 & 2) != 0) {
			tessellator5.addVertexWithUV((double)i2 + d18, (double)(i3 + 1), (double)(i4 + 1), d22, d14);
			tessellator5.addVertexWithUV((double)i2 + d18, (double)(i3 + 0), (double)(i4 + 1), d22, d16);
			tessellator5.addVertexWithUV((double)i2 + d18, (double)(i3 + 0), (double)(i4 + 0), d12, d16);
			tessellator5.addVertexWithUV((double)i2 + d18, (double)(i3 + 1), (double)(i4 + 0), d12, d14);
			tessellator5.addVertexWithUV((double)i2 + d18, (double)(i3 + 1), (double)(i4 + 0), d12, d14);
			tessellator5.addVertexWithUV((double)i2 + d18, (double)(i3 + 0), (double)(i4 + 0), d12, d16);
			tessellator5.addVertexWithUV((double)i2 + d18, (double)(i3 + 0), (double)(i4 + 1), d22, d16);
			tessellator5.addVertexWithUV((double)i2 + d18, (double)(i3 + 1), (double)(i4 + 1), d22, d14);
		}

		if((i20 & 8) != 0) {
			tessellator5.addVertexWithUV((double)(i2 + 1) - d18, (double)(i3 + 0), (double)(i4 + 1), d12, d16);
			tessellator5.addVertexWithUV((double)(i2 + 1) - d18, (double)(i3 + 1), (double)(i4 + 1), d12, d14);
			tessellator5.addVertexWithUV((double)(i2 + 1) - d18, (double)(i3 + 1), (double)(i4 + 0), d22, d14);
			tessellator5.addVertexWithUV((double)(i2 + 1) - d18, (double)(i3 + 0), (double)(i4 + 0), d22, d16);
			tessellator5.addVertexWithUV((double)(i2 + 1) - d18, (double)(i3 + 0), (double)(i4 + 0), d22, d16);
			tessellator5.addVertexWithUV((double)(i2 + 1) - d18, (double)(i3 + 1), (double)(i4 + 0), d22, d14);
			tessellator5.addVertexWithUV((double)(i2 + 1) - d18, (double)(i3 + 1), (double)(i4 + 1), d12, d14);
			tessellator5.addVertexWithUV((double)(i2 + 1) - d18, (double)(i3 + 0), (double)(i4 + 1), d12, d16);
		}

		if((i20 & 4) != 0) {
			tessellator5.addVertexWithUV((double)(i2 + 1), (double)(i3 + 0), (double)i4 + d18, d12, d16);
			tessellator5.addVertexWithUV((double)(i2 + 1), (double)(i3 + 1), (double)i4 + d18, d12, d14);
			tessellator5.addVertexWithUV((double)(i2 + 0), (double)(i3 + 1), (double)i4 + d18, d22, d14);
			tessellator5.addVertexWithUV((double)(i2 + 0), (double)(i3 + 0), (double)i4 + d18, d22, d16);
			tessellator5.addVertexWithUV((double)(i2 + 0), (double)(i3 + 0), (double)i4 + d18, d22, d16);
			tessellator5.addVertexWithUV((double)(i2 + 0), (double)(i3 + 1), (double)i4 + d18, d22, d14);
			tessellator5.addVertexWithUV((double)(i2 + 1), (double)(i3 + 1), (double)i4 + d18, d12, d14);
			tessellator5.addVertexWithUV((double)(i2 + 1), (double)(i3 + 0), (double)i4 + d18, d12, d16);
		}

		if((i20 & 1) != 0) {
			tessellator5.addVertexWithUV((double)(i2 + 1), (double)(i3 + 1), (double)(i4 + 1) - d18, d22, d14);
			tessellator5.addVertexWithUV((double)(i2 + 1), (double)(i3 + 0), (double)(i4 + 1) - d18, d22, d16);
			tessellator5.addVertexWithUV((double)(i2 + 0), (double)(i3 + 0), (double)(i4 + 1) - d18, d12, d16);
			tessellator5.addVertexWithUV((double)(i2 + 0), (double)(i3 + 1), (double)(i4 + 1) - d18, d12, d14);
			tessellator5.addVertexWithUV((double)(i2 + 0), (double)(i3 + 1), (double)(i4 + 1) - d18, d12, d14);
			tessellator5.addVertexWithUV((double)(i2 + 0), (double)(i3 + 0), (double)(i4 + 1) - d18, d12, d16);
			tessellator5.addVertexWithUV((double)(i2 + 1), (double)(i3 + 0), (double)(i4 + 1) - d18, d22, d16);
			tessellator5.addVertexWithUV((double)(i2 + 1), (double)(i3 + 1), (double)(i4 + 1) - d18, d22, d14);
		}

		if(this.blockAccess.isBlockNormalCube(i2, i3 + 1, i4)) {
			tessellator5.addVertexWithUV((double)(i2 + 1), (double)(i3 + 1) - d18, (double)(i4 + 0), d22, d14);
			tessellator5.addVertexWithUV((double)(i2 + 1), (double)(i3 + 1) - d18, (double)(i4 + 1), d22, d16);
			tessellator5.addVertexWithUV((double)(i2 + 0), (double)(i3 + 1) - d18, (double)(i4 + 1), d12, d16);
			tessellator5.addVertexWithUV((double)(i2 + 0), (double)(i3 + 1) - d18, (double)(i4 + 0), d12, d14);
		}

		return true;
	}

	public boolean func_35922_a(BlockPane blockPane1, int i2, int i3, int i4) {
		int i5 = this.blockAccess.func_35452_b();
		Tessellator tessellator6 = Tessellator.instance;
		tessellator6.func_35835_b(blockPane1.func_35275_c(this.blockAccess, i2, i3, i4));
		float f7 = 1.0F;
		int i8 = blockPane1.colorMultiplier(this.blockAccess, i2, i3, i4);
		float f9 = (float)(i8 >> 16 & 255) / 255.0F;
		float f10 = (float)(i8 >> 8 & 255) / 255.0F;
		float f11 = (float)(i8 & 255) / 255.0F;
		if(EntityRenderer.anaglyphEnable) {
			float f12 = (f9 * 30.0F + f10 * 59.0F + f11 * 11.0F) / 100.0F;
			float f13 = (f9 * 30.0F + f10 * 70.0F) / 100.0F;
			float f14 = (f9 * 30.0F + f11 * 70.0F) / 100.0F;
			f9 = f12;
			f10 = f13;
			f11 = f14;
		}

		tessellator6.setColorOpaque_F(f7 * f9, f7 * f10, f7 * f11);
		boolean z64 = false;
		boolean z66 = false;
		int i65;
		int i67;
		int i68;
		if(this.overrideBlockTexture >= 0) {
			i65 = this.overrideBlockTexture;
			i67 = this.overrideBlockTexture;
		} else {
			i68 = this.blockAccess.getBlockMetadata(i2, i3, i4);
			i65 = blockPane1.getBlockTextureFromSideAndMetadata(0, i68);
			i67 = blockPane1.func_35299_s();
		}

		i68 = (i65 & 15) << 4;
		int i15 = i65 & 240;
		double d16 = (double)((float)i68 / 256.0F);
		double d18 = (double)(((float)i68 + 7.99F) / 256.0F);
		double d20 = (double)(((float)i68 + 15.99F) / 256.0F);
		double d22 = (double)((float)i15 / 256.0F);
		double d24 = (double)(((float)i15 + 15.99F) / 256.0F);
		int i26 = (i67 & 15) << 4;
		int i27 = i67 & 240;
		double d28 = (double)((float)(i26 + 7) / 256.0F);
		double d30 = (double)(((float)i26 + 8.99F) / 256.0F);
		double d32 = (double)((float)i27 / 256.0F);
		double d34 = (double)((float)(i27 + 8) / 256.0F);
		double d36 = (double)(((float)i27 + 15.99F) / 256.0F);
		double d38 = (double)i2;
		double d40 = (double)i2 + 0.5D;
		double d42 = (double)(i2 + 1);
		double d44 = (double)i4;
		double d46 = (double)i4 + 0.5D;
		double d48 = (double)(i4 + 1);
		double d50 = (double)i2 + 0.5D - 0.0625D;
		double d52 = (double)i2 + 0.5D + 0.0625D;
		double d54 = (double)i4 + 0.5D - 0.0625D;
		double d56 = (double)i4 + 0.5D + 0.0625D;
		boolean z58 = blockPane1.func_35298_d(this.blockAccess.getBlockId(i2, i3, i4 - 1));
		boolean z59 = blockPane1.func_35298_d(this.blockAccess.getBlockId(i2, i3, i4 + 1));
		boolean z60 = blockPane1.func_35298_d(this.blockAccess.getBlockId(i2 - 1, i3, i4));
		boolean z61 = blockPane1.func_35298_d(this.blockAccess.getBlockId(i2 + 1, i3, i4));
		boolean z62 = blockPane1.shouldSideBeRendered(this.blockAccess, i2, i3 + 1, i4, 1);
		boolean z63 = blockPane1.shouldSideBeRendered(this.blockAccess, i2, i3 - 1, i4, 0);
		if((!z60 || !z61) && (z60 || z61 || z58 || z59)) {
			if(z60 && !z61) {
				tessellator6.addVertexWithUV(d38, (double)(i3 + 1), d46, d16, d22);
				tessellator6.addVertexWithUV(d38, (double)(i3 + 0), d46, d16, d24);
				tessellator6.addVertexWithUV(d40, (double)(i3 + 0), d46, d18, d24);
				tessellator6.addVertexWithUV(d40, (double)(i3 + 1), d46, d18, d22);
				tessellator6.addVertexWithUV(d40, (double)(i3 + 1), d46, d16, d22);
				tessellator6.addVertexWithUV(d40, (double)(i3 + 0), d46, d16, d24);
				tessellator6.addVertexWithUV(d38, (double)(i3 + 0), d46, d18, d24);
				tessellator6.addVertexWithUV(d38, (double)(i3 + 1), d46, d18, d22);
				if(!z59 && !z58) {
					tessellator6.addVertexWithUV(d40, (double)(i3 + 1), d56, d28, d32);
					tessellator6.addVertexWithUV(d40, (double)(i3 + 0), d56, d28, d36);
					tessellator6.addVertexWithUV(d40, (double)(i3 + 0), d54, d30, d36);
					tessellator6.addVertexWithUV(d40, (double)(i3 + 1), d54, d30, d32);
					tessellator6.addVertexWithUV(d40, (double)(i3 + 1), d54, d28, d32);
					tessellator6.addVertexWithUV(d40, (double)(i3 + 0), d54, d28, d36);
					tessellator6.addVertexWithUV(d40, (double)(i3 + 0), d56, d30, d36);
					tessellator6.addVertexWithUV(d40, (double)(i3 + 1), d56, d30, d32);
				}

				if(z62 || i3 < i5 - 1 && this.blockAccess.isAirBlock(i2 - 1, i3 + 1, i4)) {
					tessellator6.addVertexWithUV(d38, (double)(i3 + 1) + 0.01D, d56, d30, d34);
					tessellator6.addVertexWithUV(d40, (double)(i3 + 1) + 0.01D, d56, d30, d36);
					tessellator6.addVertexWithUV(d40, (double)(i3 + 1) + 0.01D, d54, d28, d36);
					tessellator6.addVertexWithUV(d38, (double)(i3 + 1) + 0.01D, d54, d28, d34);
					tessellator6.addVertexWithUV(d40, (double)(i3 + 1) + 0.01D, d56, d30, d34);
					tessellator6.addVertexWithUV(d38, (double)(i3 + 1) + 0.01D, d56, d30, d36);
					tessellator6.addVertexWithUV(d38, (double)(i3 + 1) + 0.01D, d54, d28, d36);
					tessellator6.addVertexWithUV(d40, (double)(i3 + 1) + 0.01D, d54, d28, d34);
				}

				if(z63 || i3 > 1 && this.blockAccess.isAirBlock(i2 - 1, i3 - 1, i4)) {
					tessellator6.addVertexWithUV(d38, (double)i3 - 0.01D, d56, d30, d34);
					tessellator6.addVertexWithUV(d40, (double)i3 - 0.01D, d56, d30, d36);
					tessellator6.addVertexWithUV(d40, (double)i3 - 0.01D, d54, d28, d36);
					tessellator6.addVertexWithUV(d38, (double)i3 - 0.01D, d54, d28, d34);
					tessellator6.addVertexWithUV(d40, (double)i3 - 0.01D, d56, d30, d34);
					tessellator6.addVertexWithUV(d38, (double)i3 - 0.01D, d56, d30, d36);
					tessellator6.addVertexWithUV(d38, (double)i3 - 0.01D, d54, d28, d36);
					tessellator6.addVertexWithUV(d40, (double)i3 - 0.01D, d54, d28, d34);
				}
			} else if(!z60 && z61) {
				tessellator6.addVertexWithUV(d40, (double)(i3 + 1), d46, d18, d22);
				tessellator6.addVertexWithUV(d40, (double)(i3 + 0), d46, d18, d24);
				tessellator6.addVertexWithUV(d42, (double)(i3 + 0), d46, d20, d24);
				tessellator6.addVertexWithUV(d42, (double)(i3 + 1), d46, d20, d22);
				tessellator6.addVertexWithUV(d42, (double)(i3 + 1), d46, d18, d22);
				tessellator6.addVertexWithUV(d42, (double)(i3 + 0), d46, d18, d24);
				tessellator6.addVertexWithUV(d40, (double)(i3 + 0), d46, d20, d24);
				tessellator6.addVertexWithUV(d40, (double)(i3 + 1), d46, d20, d22);
				if(!z59 && !z58) {
					tessellator6.addVertexWithUV(d40, (double)(i3 + 1), d54, d28, d32);
					tessellator6.addVertexWithUV(d40, (double)(i3 + 0), d54, d28, d36);
					tessellator6.addVertexWithUV(d40, (double)(i3 + 0), d56, d30, d36);
					tessellator6.addVertexWithUV(d40, (double)(i3 + 1), d56, d30, d32);
					tessellator6.addVertexWithUV(d40, (double)(i3 + 1), d56, d28, d32);
					tessellator6.addVertexWithUV(d40, (double)(i3 + 0), d56, d28, d36);
					tessellator6.addVertexWithUV(d40, (double)(i3 + 0), d54, d30, d36);
					tessellator6.addVertexWithUV(d40, (double)(i3 + 1), d54, d30, d32);
				}

				if(z62 || i3 < i5 - 1 && this.blockAccess.isAirBlock(i2 + 1, i3 + 1, i4)) {
					tessellator6.addVertexWithUV(d40, (double)(i3 + 1) + 0.01D, d56, d30, d32);
					tessellator6.addVertexWithUV(d42, (double)(i3 + 1) + 0.01D, d56, d30, d34);
					tessellator6.addVertexWithUV(d42, (double)(i3 + 1) + 0.01D, d54, d28, d34);
					tessellator6.addVertexWithUV(d40, (double)(i3 + 1) + 0.01D, d54, d28, d32);
					tessellator6.addVertexWithUV(d42, (double)(i3 + 1) + 0.01D, d56, d30, d32);
					tessellator6.addVertexWithUV(d40, (double)(i3 + 1) + 0.01D, d56, d30, d34);
					tessellator6.addVertexWithUV(d40, (double)(i3 + 1) + 0.01D, d54, d28, d34);
					tessellator6.addVertexWithUV(d42, (double)(i3 + 1) + 0.01D, d54, d28, d32);
				}

				if(z63 || i3 > 1 && this.blockAccess.isAirBlock(i2 + 1, i3 - 1, i4)) {
					tessellator6.addVertexWithUV(d40, (double)i3 - 0.01D, d56, d30, d32);
					tessellator6.addVertexWithUV(d42, (double)i3 - 0.01D, d56, d30, d34);
					tessellator6.addVertexWithUV(d42, (double)i3 - 0.01D, d54, d28, d34);
					tessellator6.addVertexWithUV(d40, (double)i3 - 0.01D, d54, d28, d32);
					tessellator6.addVertexWithUV(d42, (double)i3 - 0.01D, d56, d30, d32);
					tessellator6.addVertexWithUV(d40, (double)i3 - 0.01D, d56, d30, d34);
					tessellator6.addVertexWithUV(d40, (double)i3 - 0.01D, d54, d28, d34);
					tessellator6.addVertexWithUV(d42, (double)i3 - 0.01D, d54, d28, d32);
				}
			}
		} else {
			tessellator6.addVertexWithUV(d38, (double)(i3 + 1), d46, d16, d22);
			tessellator6.addVertexWithUV(d38, (double)(i3 + 0), d46, d16, d24);
			tessellator6.addVertexWithUV(d42, (double)(i3 + 0), d46, d20, d24);
			tessellator6.addVertexWithUV(d42, (double)(i3 + 1), d46, d20, d22);
			tessellator6.addVertexWithUV(d42, (double)(i3 + 1), d46, d16, d22);
			tessellator6.addVertexWithUV(d42, (double)(i3 + 0), d46, d16, d24);
			tessellator6.addVertexWithUV(d38, (double)(i3 + 0), d46, d20, d24);
			tessellator6.addVertexWithUV(d38, (double)(i3 + 1), d46, d20, d22);
			if(z62) {
				tessellator6.addVertexWithUV(d38, (double)(i3 + 1) + 0.01D, d56, d30, d36);
				tessellator6.addVertexWithUV(d42, (double)(i3 + 1) + 0.01D, d56, d30, d32);
				tessellator6.addVertexWithUV(d42, (double)(i3 + 1) + 0.01D, d54, d28, d32);
				tessellator6.addVertexWithUV(d38, (double)(i3 + 1) + 0.01D, d54, d28, d36);
				tessellator6.addVertexWithUV(d42, (double)(i3 + 1) + 0.01D, d56, d30, d36);
				tessellator6.addVertexWithUV(d38, (double)(i3 + 1) + 0.01D, d56, d30, d32);
				tessellator6.addVertexWithUV(d38, (double)(i3 + 1) + 0.01D, d54, d28, d32);
				tessellator6.addVertexWithUV(d42, (double)(i3 + 1) + 0.01D, d54, d28, d36);
			} else {
				if(i3 < i5 - 1 && this.blockAccess.isAirBlock(i2 - 1, i3 + 1, i4)) {
					tessellator6.addVertexWithUV(d38, (double)(i3 + 1) + 0.01D, d56, d30, d34);
					tessellator6.addVertexWithUV(d40, (double)(i3 + 1) + 0.01D, d56, d30, d36);
					tessellator6.addVertexWithUV(d40, (double)(i3 + 1) + 0.01D, d54, d28, d36);
					tessellator6.addVertexWithUV(d38, (double)(i3 + 1) + 0.01D, d54, d28, d34);
					tessellator6.addVertexWithUV(d40, (double)(i3 + 1) + 0.01D, d56, d30, d34);
					tessellator6.addVertexWithUV(d38, (double)(i3 + 1) + 0.01D, d56, d30, d36);
					tessellator6.addVertexWithUV(d38, (double)(i3 + 1) + 0.01D, d54, d28, d36);
					tessellator6.addVertexWithUV(d40, (double)(i3 + 1) + 0.01D, d54, d28, d34);
				}

				if(i3 < i5 - 1 && this.blockAccess.isAirBlock(i2 + 1, i3 + 1, i4)) {
					tessellator6.addVertexWithUV(d40, (double)(i3 + 1) + 0.01D, d56, d30, d32);
					tessellator6.addVertexWithUV(d42, (double)(i3 + 1) + 0.01D, d56, d30, d34);
					tessellator6.addVertexWithUV(d42, (double)(i3 + 1) + 0.01D, d54, d28, d34);
					tessellator6.addVertexWithUV(d40, (double)(i3 + 1) + 0.01D, d54, d28, d32);
					tessellator6.addVertexWithUV(d42, (double)(i3 + 1) + 0.01D, d56, d30, d32);
					tessellator6.addVertexWithUV(d40, (double)(i3 + 1) + 0.01D, d56, d30, d34);
					tessellator6.addVertexWithUV(d40, (double)(i3 + 1) + 0.01D, d54, d28, d34);
					tessellator6.addVertexWithUV(d42, (double)(i3 + 1) + 0.01D, d54, d28, d32);
				}
			}

			if(z63) {
				tessellator6.addVertexWithUV(d38, (double)i3 - 0.01D, d56, d30, d36);
				tessellator6.addVertexWithUV(d42, (double)i3 - 0.01D, d56, d30, d32);
				tessellator6.addVertexWithUV(d42, (double)i3 - 0.01D, d54, d28, d32);
				tessellator6.addVertexWithUV(d38, (double)i3 - 0.01D, d54, d28, d36);
				tessellator6.addVertexWithUV(d42, (double)i3 - 0.01D, d56, d30, d36);
				tessellator6.addVertexWithUV(d38, (double)i3 - 0.01D, d56, d30, d32);
				tessellator6.addVertexWithUV(d38, (double)i3 - 0.01D, d54, d28, d32);
				tessellator6.addVertexWithUV(d42, (double)i3 - 0.01D, d54, d28, d36);
			} else {
				if(i3 > 1 && this.blockAccess.isAirBlock(i2 - 1, i3 - 1, i4)) {
					tessellator6.addVertexWithUV(d38, (double)i3 - 0.01D, d56, d30, d34);
					tessellator6.addVertexWithUV(d40, (double)i3 - 0.01D, d56, d30, d36);
					tessellator6.addVertexWithUV(d40, (double)i3 - 0.01D, d54, d28, d36);
					tessellator6.addVertexWithUV(d38, (double)i3 - 0.01D, d54, d28, d34);
					tessellator6.addVertexWithUV(d40, (double)i3 - 0.01D, d56, d30, d34);
					tessellator6.addVertexWithUV(d38, (double)i3 - 0.01D, d56, d30, d36);
					tessellator6.addVertexWithUV(d38, (double)i3 - 0.01D, d54, d28, d36);
					tessellator6.addVertexWithUV(d40, (double)i3 - 0.01D, d54, d28, d34);
				}

				if(i3 > 1 && this.blockAccess.isAirBlock(i2 + 1, i3 - 1, i4)) {
					tessellator6.addVertexWithUV(d40, (double)i3 - 0.01D, d56, d30, d32);
					tessellator6.addVertexWithUV(d42, (double)i3 - 0.01D, d56, d30, d34);
					tessellator6.addVertexWithUV(d42, (double)i3 - 0.01D, d54, d28, d34);
					tessellator6.addVertexWithUV(d40, (double)i3 - 0.01D, d54, d28, d32);
					tessellator6.addVertexWithUV(d42, (double)i3 - 0.01D, d56, d30, d32);
					tessellator6.addVertexWithUV(d40, (double)i3 - 0.01D, d56, d30, d34);
					tessellator6.addVertexWithUV(d40, (double)i3 - 0.01D, d54, d28, d34);
					tessellator6.addVertexWithUV(d42, (double)i3 - 0.01D, d54, d28, d32);
				}
			}
		}

		if((!z58 || !z59) && (z60 || z61 || z58 || z59)) {
			if(z58 && !z59) {
				tessellator6.addVertexWithUV(d40, (double)(i3 + 1), d44, d16, d22);
				tessellator6.addVertexWithUV(d40, (double)(i3 + 0), d44, d16, d24);
				tessellator6.addVertexWithUV(d40, (double)(i3 + 0), d46, d18, d24);
				tessellator6.addVertexWithUV(d40, (double)(i3 + 1), d46, d18, d22);
				tessellator6.addVertexWithUV(d40, (double)(i3 + 1), d46, d16, d22);
				tessellator6.addVertexWithUV(d40, (double)(i3 + 0), d46, d16, d24);
				tessellator6.addVertexWithUV(d40, (double)(i3 + 0), d44, d18, d24);
				tessellator6.addVertexWithUV(d40, (double)(i3 + 1), d44, d18, d22);
				if(!z61 && !z60) {
					tessellator6.addVertexWithUV(d50, (double)(i3 + 1), d46, d28, d32);
					tessellator6.addVertexWithUV(d50, (double)(i3 + 0), d46, d28, d36);
					tessellator6.addVertexWithUV(d52, (double)(i3 + 0), d46, d30, d36);
					tessellator6.addVertexWithUV(d52, (double)(i3 + 1), d46, d30, d32);
					tessellator6.addVertexWithUV(d52, (double)(i3 + 1), d46, d28, d32);
					tessellator6.addVertexWithUV(d52, (double)(i3 + 0), d46, d28, d36);
					tessellator6.addVertexWithUV(d50, (double)(i3 + 0), d46, d30, d36);
					tessellator6.addVertexWithUV(d50, (double)(i3 + 1), d46, d30, d32);
				}

				if(z62 || i3 < i5 - 1 && this.blockAccess.isAirBlock(i2, i3 + 1, i4 - 1)) {
					tessellator6.addVertexWithUV(d50, (double)(i3 + 1), d44, d30, d32);
					tessellator6.addVertexWithUV(d50, (double)(i3 + 1), d46, d30, d34);
					tessellator6.addVertexWithUV(d52, (double)(i3 + 1), d46, d28, d34);
					tessellator6.addVertexWithUV(d52, (double)(i3 + 1), d44, d28, d32);
					tessellator6.addVertexWithUV(d50, (double)(i3 + 1), d46, d30, d32);
					tessellator6.addVertexWithUV(d50, (double)(i3 + 1), d44, d30, d34);
					tessellator6.addVertexWithUV(d52, (double)(i3 + 1), d44, d28, d34);
					tessellator6.addVertexWithUV(d52, (double)(i3 + 1), d46, d28, d32);
				}

				if(z63 || i3 > 1 && this.blockAccess.isAirBlock(i2, i3 - 1, i4 - 1)) {
					tessellator6.addVertexWithUV(d50, (double)i3, d44, d30, d32);
					tessellator6.addVertexWithUV(d50, (double)i3, d46, d30, d34);
					tessellator6.addVertexWithUV(d52, (double)i3, d46, d28, d34);
					tessellator6.addVertexWithUV(d52, (double)i3, d44, d28, d32);
					tessellator6.addVertexWithUV(d50, (double)i3, d46, d30, d32);
					tessellator6.addVertexWithUV(d50, (double)i3, d44, d30, d34);
					tessellator6.addVertexWithUV(d52, (double)i3, d44, d28, d34);
					tessellator6.addVertexWithUV(d52, (double)i3, d46, d28, d32);
				}
			} else if(!z58 && z59) {
				tessellator6.addVertexWithUV(d40, (double)(i3 + 1), d46, d18, d22);
				tessellator6.addVertexWithUV(d40, (double)(i3 + 0), d46, d18, d24);
				tessellator6.addVertexWithUV(d40, (double)(i3 + 0), d48, d20, d24);
				tessellator6.addVertexWithUV(d40, (double)(i3 + 1), d48, d20, d22);
				tessellator6.addVertexWithUV(d40, (double)(i3 + 1), d48, d18, d22);
				tessellator6.addVertexWithUV(d40, (double)(i3 + 0), d48, d18, d24);
				tessellator6.addVertexWithUV(d40, (double)(i3 + 0), d46, d20, d24);
				tessellator6.addVertexWithUV(d40, (double)(i3 + 1), d46, d20, d22);
				if(!z61 && !z60) {
					tessellator6.addVertexWithUV(d52, (double)(i3 + 1), d46, d28, d32);
					tessellator6.addVertexWithUV(d52, (double)(i3 + 0), d46, d28, d36);
					tessellator6.addVertexWithUV(d50, (double)(i3 + 0), d46, d30, d36);
					tessellator6.addVertexWithUV(d50, (double)(i3 + 1), d46, d30, d32);
					tessellator6.addVertexWithUV(d50, (double)(i3 + 1), d46, d28, d32);
					tessellator6.addVertexWithUV(d50, (double)(i3 + 0), d46, d28, d36);
					tessellator6.addVertexWithUV(d52, (double)(i3 + 0), d46, d30, d36);
					tessellator6.addVertexWithUV(d52, (double)(i3 + 1), d46, d30, d32);
				}

				if(z62 || i3 < i5 - 1 && this.blockAccess.isAirBlock(i2, i3 + 1, i4 + 1)) {
					tessellator6.addVertexWithUV(d50, (double)(i3 + 1), d46, d28, d34);
					tessellator6.addVertexWithUV(d50, (double)(i3 + 1), d48, d28, d36);
					tessellator6.addVertexWithUV(d52, (double)(i3 + 1), d48, d30, d36);
					tessellator6.addVertexWithUV(d52, (double)(i3 + 1), d46, d30, d34);
					tessellator6.addVertexWithUV(d50, (double)(i3 + 1), d48, d28, d34);
					tessellator6.addVertexWithUV(d50, (double)(i3 + 1), d46, d28, d36);
					tessellator6.addVertexWithUV(d52, (double)(i3 + 1), d46, d30, d36);
					tessellator6.addVertexWithUV(d52, (double)(i3 + 1), d48, d30, d34);
				}

				if(z63 || i3 > 1 && this.blockAccess.isAirBlock(i2, i3 - 1, i4 + 1)) {
					tessellator6.addVertexWithUV(d50, (double)i3, d46, d28, d34);
					tessellator6.addVertexWithUV(d50, (double)i3, d48, d28, d36);
					tessellator6.addVertexWithUV(d52, (double)i3, d48, d30, d36);
					tessellator6.addVertexWithUV(d52, (double)i3, d46, d30, d34);
					tessellator6.addVertexWithUV(d50, (double)i3, d48, d28, d34);
					tessellator6.addVertexWithUV(d50, (double)i3, d46, d28, d36);
					tessellator6.addVertexWithUV(d52, (double)i3, d46, d30, d36);
					tessellator6.addVertexWithUV(d52, (double)i3, d48, d30, d34);
				}
			}
		} else {
			tessellator6.addVertexWithUV(d40, (double)(i3 + 1), d48, d16, d22);
			tessellator6.addVertexWithUV(d40, (double)(i3 + 0), d48, d16, d24);
			tessellator6.addVertexWithUV(d40, (double)(i3 + 0), d44, d20, d24);
			tessellator6.addVertexWithUV(d40, (double)(i3 + 1), d44, d20, d22);
			tessellator6.addVertexWithUV(d40, (double)(i3 + 1), d44, d16, d22);
			tessellator6.addVertexWithUV(d40, (double)(i3 + 0), d44, d16, d24);
			tessellator6.addVertexWithUV(d40, (double)(i3 + 0), d48, d20, d24);
			tessellator6.addVertexWithUV(d40, (double)(i3 + 1), d48, d20, d22);
			if(z62) {
				tessellator6.addVertexWithUV(d52, (double)(i3 + 1), d48, d30, d36);
				tessellator6.addVertexWithUV(d52, (double)(i3 + 1), d44, d30, d32);
				tessellator6.addVertexWithUV(d50, (double)(i3 + 1), d44, d28, d32);
				tessellator6.addVertexWithUV(d50, (double)(i3 + 1), d48, d28, d36);
				tessellator6.addVertexWithUV(d52, (double)(i3 + 1), d44, d30, d36);
				tessellator6.addVertexWithUV(d52, (double)(i3 + 1), d48, d30, d32);
				tessellator6.addVertexWithUV(d50, (double)(i3 + 1), d48, d28, d32);
				tessellator6.addVertexWithUV(d50, (double)(i3 + 1), d44, d28, d36);
			} else {
				if(i3 < i5 - 1 && this.blockAccess.isAirBlock(i2, i3 + 1, i4 - 1)) {
					tessellator6.addVertexWithUV(d50, (double)(i3 + 1), d44, d30, d32);
					tessellator6.addVertexWithUV(d50, (double)(i3 + 1), d46, d30, d34);
					tessellator6.addVertexWithUV(d52, (double)(i3 + 1), d46, d28, d34);
					tessellator6.addVertexWithUV(d52, (double)(i3 + 1), d44, d28, d32);
					tessellator6.addVertexWithUV(d50, (double)(i3 + 1), d46, d30, d32);
					tessellator6.addVertexWithUV(d50, (double)(i3 + 1), d44, d30, d34);
					tessellator6.addVertexWithUV(d52, (double)(i3 + 1), d44, d28, d34);
					tessellator6.addVertexWithUV(d52, (double)(i3 + 1), d46, d28, d32);
				}

				if(i3 < i5 - 1 && this.blockAccess.isAirBlock(i2, i3 + 1, i4 + 1)) {
					tessellator6.addVertexWithUV(d50, (double)(i3 + 1), d46, d28, d34);
					tessellator6.addVertexWithUV(d50, (double)(i3 + 1), d48, d28, d36);
					tessellator6.addVertexWithUV(d52, (double)(i3 + 1), d48, d30, d36);
					tessellator6.addVertexWithUV(d52, (double)(i3 + 1), d46, d30, d34);
					tessellator6.addVertexWithUV(d50, (double)(i3 + 1), d48, d28, d34);
					tessellator6.addVertexWithUV(d50, (double)(i3 + 1), d46, d28, d36);
					tessellator6.addVertexWithUV(d52, (double)(i3 + 1), d46, d30, d36);
					tessellator6.addVertexWithUV(d52, (double)(i3 + 1), d48, d30, d34);
				}
			}

			if(z63) {
				tessellator6.addVertexWithUV(d52, (double)i3, d48, d30, d36);
				tessellator6.addVertexWithUV(d52, (double)i3, d44, d30, d32);
				tessellator6.addVertexWithUV(d50, (double)i3, d44, d28, d32);
				tessellator6.addVertexWithUV(d50, (double)i3, d48, d28, d36);
				tessellator6.addVertexWithUV(d52, (double)i3, d44, d30, d36);
				tessellator6.addVertexWithUV(d52, (double)i3, d48, d30, d32);
				tessellator6.addVertexWithUV(d50, (double)i3, d48, d28, d32);
				tessellator6.addVertexWithUV(d50, (double)i3, d44, d28, d36);
			} else {
				if(i3 > 1 && this.blockAccess.isAirBlock(i2, i3 - 1, i4 - 1)) {
					tessellator6.addVertexWithUV(d50, (double)i3, d44, d30, d32);
					tessellator6.addVertexWithUV(d50, (double)i3, d46, d30, d34);
					tessellator6.addVertexWithUV(d52, (double)i3, d46, d28, d34);
					tessellator6.addVertexWithUV(d52, (double)i3, d44, d28, d32);
					tessellator6.addVertexWithUV(d50, (double)i3, d46, d30, d32);
					tessellator6.addVertexWithUV(d50, (double)i3, d44, d30, d34);
					tessellator6.addVertexWithUV(d52, (double)i3, d44, d28, d34);
					tessellator6.addVertexWithUV(d52, (double)i3, d46, d28, d32);
				}

				if(i3 > 1 && this.blockAccess.isAirBlock(i2, i3 - 1, i4 + 1)) {
					tessellator6.addVertexWithUV(d50, (double)i3, d46, d28, d34);
					tessellator6.addVertexWithUV(d50, (double)i3, d48, d28, d36);
					tessellator6.addVertexWithUV(d52, (double)i3, d48, d30, d36);
					tessellator6.addVertexWithUV(d52, (double)i3, d46, d30, d34);
					tessellator6.addVertexWithUV(d50, (double)i3, d48, d28, d34);
					tessellator6.addVertexWithUV(d50, (double)i3, d46, d28, d36);
					tessellator6.addVertexWithUV(d52, (double)i3, d46, d30, d36);
					tessellator6.addVertexWithUV(d52, (double)i3, d48, d30, d34);
				}
			}
		}

		return true;
	}

	public boolean renderBlockReed(Block block1, int i2, int i3, int i4) {
		Tessellator tessellator5 = Tessellator.instance;
		tessellator5.func_35835_b(block1.func_35275_c(this.blockAccess, i2, i3, i4));
		float f6 = 1.0F;
		int i7 = block1.colorMultiplier(this.blockAccess, i2, i3, i4);
		float f8 = (float)(i7 >> 16 & 255) / 255.0F;
		float f9 = (float)(i7 >> 8 & 255) / 255.0F;
		float f10 = (float)(i7 & 255) / 255.0F;
		if(EntityRenderer.anaglyphEnable) {
			float f11 = (f8 * 30.0F + f9 * 59.0F + f10 * 11.0F) / 100.0F;
			float f12 = (f8 * 30.0F + f9 * 70.0F) / 100.0F;
			float f13 = (f8 * 30.0F + f10 * 70.0F) / 100.0F;
			f8 = f11;
			f9 = f12;
			f10 = f13;
		}

		tessellator5.setColorOpaque_F(f6 * f8, f6 * f9, f6 * f10);
		double d19 = (double)i2;
		double d20 = (double)i3;
		double d15 = (double)i4;
		if(block1 == Block.tallGrass) {
			long j17 = (long)(i2 * 3129871) ^ (long)i4 * 116129781L ^ (long)i3;
			j17 = j17 * j17 * 42317861L + j17 * 11L;
			d19 += ((double)((float)(j17 >> 16 & 15L) / 15.0F) - 0.5D) * 0.5D;
			d20 += ((double)((float)(j17 >> 20 & 15L) / 15.0F) - 1.0D) * 0.2D;
			d15 += ((double)((float)(j17 >> 24 & 15L) / 15.0F) - 0.5D) * 0.5D;
		}

		this.renderCrossedSquares(block1, this.blockAccess.getBlockMetadata(i2, i3, i4), d19, d20, d15);
		return true;
	}

	public boolean renderBlockCrops(Block block1, int i2, int i3, int i4) {
		BlockStem blockStem5 = (BlockStem)block1;
		Tessellator tessellator6 = Tessellator.instance;
		tessellator6.func_35835_b(blockStem5.func_35275_c(this.blockAccess, i2, i3, i4));
		float f7 = 1.0F;
		int i8 = blockStem5.colorMultiplier(this.blockAccess, i2, i3, i4);
		float f9 = (float)(i8 >> 16 & 255) / 255.0F;
		float f10 = (float)(i8 >> 8 & 255) / 255.0F;
		float f11 = (float)(i8 & 255) / 255.0F;
		if(EntityRenderer.anaglyphEnable) {
			float f12 = (f9 * 30.0F + f10 * 59.0F + f11 * 11.0F) / 100.0F;
			float f13 = (f9 * 30.0F + f10 * 70.0F) / 100.0F;
			float f14 = (f9 * 30.0F + f11 * 70.0F) / 100.0F;
			f9 = f12;
			f10 = f13;
			f11 = f14;
		}

		tessellator6.setColorOpaque_F(f7 * f9, f7 * f10, f7 * f11);
		blockStem5.setBlockBoundsBasedOnState(this.blockAccess, i2, i3, i4);
		int i15 = blockStem5.func_35296_f(this.blockAccess, i2, i3, i4);
		if(i15 < 0) {
			this.func_35928_a(blockStem5, this.blockAccess.getBlockMetadata(i2, i3, i4), blockStem5.maxY, (double)i2, (double)i3, (double)i4);
		} else {
			this.func_35928_a(blockStem5, this.blockAccess.getBlockMetadata(i2, i3, i4), 0.5D, (double)i2, (double)i3, (double)i4);
			this.func_35926_a(blockStem5, this.blockAccess.getBlockMetadata(i2, i3, i4), i15, blockStem5.maxY, (double)i2, (double)i3, (double)i4);
		}

		return true;
	}

	public boolean func_35930_l(Block block1, int i2, int i3, int i4) {
		Tessellator tessellator5 = Tessellator.instance;
		tessellator5.func_35835_b(block1.func_35275_c(this.blockAccess, i2, i3, i4));
		tessellator5.setColorOpaque_F(1.0F, 1.0F, 1.0F);
		this.func_1245_b(block1, this.blockAccess.getBlockMetadata(i2, i3, i4), (double)i2, (double)((float)i3 - 0.0625F), (double)i4);
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
		tessellator12.addVertexWithUV(d2 - d36, d4 + 1.0D, d32, (double)f16, (double)f18);
		tessellator12.addVertexWithUV(d2 - d36 + d8, d4 + 0.0D, d32 + d10, (double)f16, (double)f19);
		tessellator12.addVertexWithUV(d2 - d36 + d8, d4 + 0.0D, d34 + d10, (double)f17, (double)f19);
		tessellator12.addVertexWithUV(d2 - d36, d4 + 1.0D, d34, (double)f17, (double)f18);
		tessellator12.addVertexWithUV(d2 + d36, d4 + 1.0D, d34, (double)f16, (double)f18);
		tessellator12.addVertexWithUV(d2 + d8 + d36, d4 + 0.0D, d34 + d10, (double)f16, (double)f19);
		tessellator12.addVertexWithUV(d2 + d8 + d36, d4 + 0.0D, d32 + d10, (double)f17, (double)f19);
		tessellator12.addVertexWithUV(d2 + d36, d4 + 1.0D, d32, (double)f17, (double)f18);
		tessellator12.addVertexWithUV(d28, d4 + 1.0D, d6 + d36, (double)f16, (double)f18);
		tessellator12.addVertexWithUV(d28 + d8, d4 + 0.0D, d6 + d36 + d10, (double)f16, (double)f19);
		tessellator12.addVertexWithUV(d30 + d8, d4 + 0.0D, d6 + d36 + d10, (double)f17, (double)f19);
		tessellator12.addVertexWithUV(d30, d4 + 1.0D, d6 + d36, (double)f17, (double)f18);
		tessellator12.addVertexWithUV(d30, d4 + 1.0D, d6 - d36, (double)f16, (double)f18);
		tessellator12.addVertexWithUV(d30 + d8, d4 + 0.0D, d6 - d36 + d10, (double)f16, (double)f19);
		tessellator12.addVertexWithUV(d28 + d8, d4 + 0.0D, d6 - d36 + d10, (double)f17, (double)f19);
		tessellator12.addVertexWithUV(d28, d4 + 1.0D, d6 - d36, (double)f17, (double)f18);
	}

	public void renderCrossedSquares(Block block1, int i2, double d3, double d5, double d7) {
		Tessellator tessellator9 = Tessellator.instance;
		int i10 = block1.getBlockTextureFromSideAndMetadata(0, i2);
		if(this.overrideBlockTexture >= 0) {
			i10 = this.overrideBlockTexture;
		}

		int i11 = (i10 & 15) << 4;
		int i12 = i10 & 240;
		double d13 = (double)((float)i11 / 256.0F);
		double d15 = (double)(((float)i11 + 15.99F) / 256.0F);
		double d17 = (double)((float)i12 / 256.0F);
		double d19 = (double)(((float)i12 + 15.99F) / 256.0F);
		double d21 = d3 + 0.5D - 0.45D;
		double d23 = d3 + 0.5D + 0.45D;
		double d25 = d7 + 0.5D - 0.45D;
		double d27 = d7 + 0.5D + 0.45D;
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

	public void func_35928_a(Block block1, int i2, double d3, double d5, double d7, double d9) {
		Tessellator tessellator11 = Tessellator.instance;
		int i12 = block1.getBlockTextureFromSideAndMetadata(0, i2);
		if(this.overrideBlockTexture >= 0) {
			i12 = this.overrideBlockTexture;
		}

		int i13 = (i12 & 15) << 4;
		int i14 = i12 & 240;
		double d15 = (double)((float)i13 / 256.0F);
		double d17 = (double)(((float)i13 + 15.99F) / 256.0F);
		double d19 = (double)((float)i14 / 256.0F);
		double d21 = ((double)i14 + 15.989999771118164D * d3) / 256.0D;
		double d23 = d5 + 0.5D - (double)0.45F;
		double d25 = d5 + 0.5D + (double)0.45F;
		double d27 = d9 + 0.5D - (double)0.45F;
		double d29 = d9 + 0.5D + (double)0.45F;
		tessellator11.addVertexWithUV(d23, d7 + d3, d27, d15, d19);
		tessellator11.addVertexWithUV(d23, d7 + 0.0D, d27, d15, d21);
		tessellator11.addVertexWithUV(d25, d7 + 0.0D, d29, d17, d21);
		tessellator11.addVertexWithUV(d25, d7 + d3, d29, d17, d19);
		tessellator11.addVertexWithUV(d25, d7 + d3, d29, d15, d19);
		tessellator11.addVertexWithUV(d25, d7 + 0.0D, d29, d15, d21);
		tessellator11.addVertexWithUV(d23, d7 + 0.0D, d27, d17, d21);
		tessellator11.addVertexWithUV(d23, d7 + d3, d27, d17, d19);
		tessellator11.addVertexWithUV(d23, d7 + d3, d29, d15, d19);
		tessellator11.addVertexWithUV(d23, d7 + 0.0D, d29, d15, d21);
		tessellator11.addVertexWithUV(d25, d7 + 0.0D, d27, d17, d21);
		tessellator11.addVertexWithUV(d25, d7 + d3, d27, d17, d19);
		tessellator11.addVertexWithUV(d25, d7 + d3, d27, d15, d19);
		tessellator11.addVertexWithUV(d25, d7 + 0.0D, d27, d15, d21);
		tessellator11.addVertexWithUV(d23, d7 + 0.0D, d29, d17, d21);
		tessellator11.addVertexWithUV(d23, d7 + d3, d29, d17, d19);
	}

	public void func_35926_a(Block block1, int i2, int i3, double d4, double d6, double d8, double d10) {
		Tessellator tessellator12 = Tessellator.instance;
		int i13 = block1.getBlockTextureFromSideAndMetadata(0, i2) + 16;
		if(this.overrideBlockTexture >= 0) {
			i13 = this.overrideBlockTexture;
		}

		int i14 = (i13 & 15) << 4;
		int i15 = i13 & 240;
		double d16 = (double)((float)i14 / 256.0F);
		double d18 = (double)(((float)i14 + 15.99F) / 256.0F);
		double d20 = (double)((float)i15 / 256.0F);
		double d22 = ((double)i15 + 15.989999771118164D * d4) / 256.0D;
		double d24 = d6 + 0.5D - 0.5D;
		double d26 = d6 + 0.5D + 0.5D;
		double d28 = d10 + 0.5D - 0.5D;
		double d30 = d10 + 0.5D + 0.5D;
		double d32 = d6 + 0.5D;
		double d34 = d10 + 0.5D;
		if((i3 + 1) / 2 % 2 == 1) {
			double d36 = d18;
			d18 = d16;
			d16 = d36;
		}

		if(i3 < 2) {
			tessellator12.addVertexWithUV(d24, d8 + d4, d34, d16, d20);
			tessellator12.addVertexWithUV(d24, d8 + 0.0D, d34, d16, d22);
			tessellator12.addVertexWithUV(d26, d8 + 0.0D, d34, d18, d22);
			tessellator12.addVertexWithUV(d26, d8 + d4, d34, d18, d20);
			tessellator12.addVertexWithUV(d26, d8 + d4, d34, d18, d20);
			tessellator12.addVertexWithUV(d26, d8 + 0.0D, d34, d18, d22);
			tessellator12.addVertexWithUV(d24, d8 + 0.0D, d34, d16, d22);
			tessellator12.addVertexWithUV(d24, d8 + d4, d34, d16, d20);
		} else {
			tessellator12.addVertexWithUV(d32, d8 + d4, d30, d16, d20);
			tessellator12.addVertexWithUV(d32, d8 + 0.0D, d30, d16, d22);
			tessellator12.addVertexWithUV(d32, d8 + 0.0D, d28, d18, d22);
			tessellator12.addVertexWithUV(d32, d8 + d4, d28, d18, d20);
			tessellator12.addVertexWithUV(d32, d8 + d4, d28, d18, d20);
			tessellator12.addVertexWithUV(d32, d8 + 0.0D, d28, d18, d22);
			tessellator12.addVertexWithUV(d32, d8 + 0.0D, d30, d16, d22);
			tessellator12.addVertexWithUV(d32, d8 + d4, d30, d16, d20);
		}

	}

	public void func_1245_b(Block block1, int i2, double d3, double d5, double d7) {
		Tessellator tessellator9 = Tessellator.instance;
		int i10 = block1.getBlockTextureFromSideAndMetadata(0, i2);
		if(this.overrideBlockTexture >= 0) {
			i10 = this.overrideBlockTexture;
		}

		int i11 = (i10 & 15) << 4;
		int i12 = i10 & 240;
		double d13 = (double)((float)i11 / 256.0F);
		double d15 = (double)(((float)i11 + 15.99F) / 256.0F);
		double d17 = (double)((float)i12 / 256.0F);
		double d19 = (double)(((float)i12 + 15.99F) / 256.0F);
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
			double d24 = (double)this.func_1224_a(i2, i3, i4, material22);
			double d26 = (double)this.func_1224_a(i2, i3, i4 + 1, material22);
			double d28 = (double)this.func_1224_a(i2 + 1, i3, i4 + 1, material22);
			double d30 = (double)this.func_1224_a(i2 + 1, i3, i4, material22);
			int i32;
			int i35;
			if(this.renderAllFaces || z10) {
				z13 = true;
				i32 = block1.getBlockTextureFromSideAndMetadata(1, i23);
				float f33 = (float)BlockFluid.func_293_a(this.blockAccess, i2, i3, i4, material22);
				if(f33 > -999.0F) {
					i32 = block1.getBlockTextureFromSideAndMetadata(2, i23);
				}

				int i34 = (i32 & 15) << 4;
				i35 = i32 & 240;
				double d36 = ((double)i34 + 8.0D) / 256.0D;
				double d38 = ((double)i35 + 8.0D) / 256.0D;
				if(f33 < -999.0F) {
					f33 = 0.0F;
				} else {
					d36 = (double)((float)(i34 + 16) / 256.0F);
					d38 = (double)((float)(i35 + 16) / 256.0F);
				}

				double d40 = (double)(MathHelper.sin(f33) * 8.0F) / 256.0D;
				double d42 = (double)(MathHelper.cos(f33) * 8.0F) / 256.0D;
				tessellator5.func_35835_b(block1.func_35275_c(this.blockAccess, i2, i3, i4));
				float f44 = 1.0F;
				tessellator5.setColorOpaque_F(f15 * f44 * f7, f15 * f44 * f8, f15 * f44 * f9);
				tessellator5.addVertexWithUV((double)(i2 + 0), (double)i3 + d24, (double)(i4 + 0), d36 - d42 - d40, d38 - d42 + d40);
				tessellator5.addVertexWithUV((double)(i2 + 0), (double)i3 + d26, (double)(i4 + 1), d36 - d42 + d40, d38 + d42 + d40);
				tessellator5.addVertexWithUV((double)(i2 + 1), (double)i3 + d28, (double)(i4 + 1), d36 + d42 + d40, d38 + d42 - d40);
				tessellator5.addVertexWithUV((double)(i2 + 1), (double)i3 + d30, (double)(i4 + 0), d36 + d42 - d40, d38 - d42 - d40);
			}

			if(this.renderAllFaces || z11) {
				tessellator5.func_35835_b(block1.func_35275_c(this.blockAccess, i2, i3 - 1, i4));
				float f62 = 1.0F;
				tessellator5.setColorOpaque_F(f14 * f62, f14 * f62, f14 * f62);
				this.renderBottomFace(block1, (double)i2, (double)i3, (double)i4, block1.getBlockTextureFromSide(0));
				z13 = true;
			}

			for(i32 = 0; i32 < 4; ++i32) {
				int i63 = i2;
				i35 = i4;
				if(i32 == 0) {
					i35 = i4 - 1;
				}

				if(i32 == 1) {
					++i35;
				}

				if(i32 == 2) {
					i63 = i2 - 1;
				}

				if(i32 == 3) {
					++i63;
				}

				int i64 = block1.getBlockTextureFromSideAndMetadata(i32 + 2, i23);
				int i37 = (i64 & 15) << 4;
				int i65 = i64 & 240;
				if(this.renderAllFaces || z12[i32]) {
					double d39;
					double d41;
					double d43;
					double d45;
					double d47;
					double d49;
					if(i32 == 0) {
						d39 = d24;
						d41 = d30;
						d43 = (double)i2;
						d47 = (double)(i2 + 1);
						d45 = (double)i4;
						d49 = (double)i4;
					} else if(i32 == 1) {
						d39 = d28;
						d41 = d26;
						d43 = (double)(i2 + 1);
						d47 = (double)i2;
						d45 = (double)(i4 + 1);
						d49 = (double)(i4 + 1);
					} else if(i32 == 2) {
						d39 = d26;
						d41 = d24;
						d43 = (double)i2;
						d47 = (double)i2;
						d45 = (double)(i4 + 1);
						d49 = (double)i4;
					} else {
						d39 = d30;
						d41 = d28;
						d43 = (double)(i2 + 1);
						d47 = (double)(i2 + 1);
						d45 = (double)i4;
						d49 = (double)(i4 + 1);
					}

					z13 = true;
					double d51 = (double)((float)(i37 + 0) / 256.0F);
					double d53 = ((double)(i37 + 16) - 0.01D) / 256.0D;
					double d55 = ((double)i65 + (1.0D - d39) * 16.0D) / 256.0D;
					double d57 = ((double)i65 + (1.0D - d41) * 16.0D) / 256.0D;
					double d59 = ((double)(i65 + 16) - 0.01D) / 256.0D;
					tessellator5.func_35835_b(block1.func_35275_c(this.blockAccess, i63, i3, i35));
					float f61 = 1.0F;
					if(i32 < 2) {
						f61 *= f16;
					} else {
						f61 *= f17;
					}

					tessellator5.setColorOpaque_F(f15 * f61 * f7, f15 * f61 * f8, f15 * f61 * f9);
					tessellator5.addVertexWithUV(d43, (double)i3 + d39, d45, d51, d55);
					tessellator5.addVertexWithUV(d47, (double)i3 + d41, d49, d53, d57);
					tessellator5.addVertexWithUV(d47, (double)(i3 + 0), d49, d53, d59);
					tessellator5.addVertexWithUV(d43, (double)(i3 + 0), d45, d51, d59);
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
					f6 += BlockFluid.getFluidHeightPercent(i12) * 10.0F;
					i5 += 10;
				}

				f6 += BlockFluid.getFluidHeightPercent(i12);
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
		tessellator10.func_35835_b(block1.func_35275_c(world2, i3, i4, i5));
		float f11 = 1.0F;
		float f12 = 1.0F;
		if(f12 < f11) {
			f12 = f11;
		}

		tessellator10.setColorOpaque_F(f6 * f12, f6 * f12, f6 * f12);
		this.renderBottomFace(block1, -0.5D, -0.5D, -0.5D, block1.getBlockTextureFromSide(0));
		f12 = 1.0F;
		if(f12 < f11) {
			f12 = f11;
		}

		tessellator10.setColorOpaque_F(f7 * f12, f7 * f12, f7 * f12);
		this.renderTopFace(block1, -0.5D, -0.5D, -0.5D, block1.getBlockTextureFromSide(1));
		f12 = 1.0F;
		if(f12 < f11) {
			f12 = f11;
		}

		tessellator10.setColorOpaque_F(f8 * f12, f8 * f12, f8 * f12);
		this.renderEastFace(block1, -0.5D, -0.5D, -0.5D, block1.getBlockTextureFromSide(2));
		f12 = 1.0F;
		if(f12 < f11) {
			f12 = f11;
		}

		tessellator10.setColorOpaque_F(f8 * f12, f8 * f12, f8 * f12);
		this.renderWestFace(block1, -0.5D, -0.5D, -0.5D, block1.getBlockTextureFromSide(3));
		f12 = 1.0F;
		if(f12 < f11) {
			f12 = f11;
		}

		tessellator10.setColorOpaque_F(f9 * f12, f9 * f12, f9 * f12);
		this.renderNorthFace(block1, -0.5D, -0.5D, -0.5D, block1.getBlockTextureFromSide(4));
		f12 = 1.0F;
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
		if(EntityRenderer.anaglyphEnable) {
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
		float f9 = this.lightValueOwn;
		float f10 = this.lightValueOwn;
		float f11 = this.lightValueOwn;
		float f12 = this.lightValueOwn;
		boolean z13 = true;
		boolean z14 = true;
		boolean z15 = true;
		boolean z16 = true;
		boolean z17 = true;
		boolean z18 = true;
		this.lightValueOwn = block1.func_35276_e(this.blockAccess, i2, i3, i4);
		this.aoLightValueXNeg = block1.func_35276_e(this.blockAccess, i2 - 1, i3, i4);
		this.aoLightValueYNeg = block1.func_35276_e(this.blockAccess, i2, i3 - 1, i4);
		this.aoLightValueZNeg = block1.func_35276_e(this.blockAccess, i2, i3, i4 - 1);
		this.aoLightValueXPos = block1.func_35276_e(this.blockAccess, i2 + 1, i3, i4);
		this.aoLightValueYPos = block1.func_35276_e(this.blockAccess, i2, i3 + 1, i4);
		this.aoLightValueZPos = block1.func_35276_e(this.blockAccess, i2, i3, i4 + 1);
		int i19 = block1.func_35275_c(this.blockAccess, i2 - 1, i3, i4);
		int i20 = block1.func_35275_c(this.blockAccess, i2, i3 - 1, i4);
		int i21 = block1.func_35275_c(this.blockAccess, i2, i3, i4 - 1);
		int i22 = block1.func_35275_c(this.blockAccess, i2 + 1, i3, i4);
		int i23 = block1.func_35275_c(this.blockAccess, i2, i3 + 1, i4);
		int i24 = block1.func_35275_c(this.blockAccess, i2, i3, i4 + 1);
		Tessellator tessellator25 = Tessellator.instance;
		tessellator25.func_35835_b(983055);
		this.aoGrassXYZPPC = Block.canBlockGrass[this.blockAccess.getBlockId(i2 + 1, i3 + 1, i4)];
		this.aoGrassXYZPNC = Block.canBlockGrass[this.blockAccess.getBlockId(i2 + 1, i3 - 1, i4)];
		this.aoGrassXYZPCP = Block.canBlockGrass[this.blockAccess.getBlockId(i2 + 1, i3, i4 + 1)];
		this.aoGrassXYZPCN = Block.canBlockGrass[this.blockAccess.getBlockId(i2 + 1, i3, i4 - 1)];
		this.aoGrassXYZNPC = Block.canBlockGrass[this.blockAccess.getBlockId(i2 - 1, i3 + 1, i4)];
		this.aoGrassXYZNNC = Block.canBlockGrass[this.blockAccess.getBlockId(i2 - 1, i3 - 1, i4)];
		this.aoGrassXYZNCN = Block.canBlockGrass[this.blockAccess.getBlockId(i2 - 1, i3, i4 - 1)];
		this.aoGrassXYZNCP = Block.canBlockGrass[this.blockAccess.getBlockId(i2 - 1, i3, i4 + 1)];
		this.aoGrassXYZCPP = Block.canBlockGrass[this.blockAccess.getBlockId(i2, i3 + 1, i4 + 1)];
		this.aoGrassXYZCPN = Block.canBlockGrass[this.blockAccess.getBlockId(i2, i3 + 1, i4 - 1)];
		this.aoGrassXYZCNP = Block.canBlockGrass[this.blockAccess.getBlockId(i2, i3 - 1, i4 + 1)];
		this.aoGrassXYZCNN = Block.canBlockGrass[this.blockAccess.getBlockId(i2, i3 - 1, i4 - 1)];
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

		if(this.renderAllFaces || block1.shouldSideBeRendered(this.blockAccess, i2, i3 - 1, i4, 0)) {
			if(this.field_35946_aj <= 0) {
				f12 = this.aoLightValueYNeg;
				f11 = this.aoLightValueYNeg;
				f10 = this.aoLightValueYNeg;
				f9 = this.aoLightValueYNeg;
				this.field_35943_ak = this.field_35944_al = this.field_35947_am = this.field_35948_an = this.field_35935_Q;
			} else {
				--i3;
				this.field_35935_Q = block1.func_35275_c(this.blockAccess, i2 - 1, i3, i4);
				this.field_35937_S = block1.func_35275_c(this.blockAccess, i2, i3, i4 - 1);
				this.field_35932_T = block1.func_35275_c(this.blockAccess, i2, i3, i4 + 1);
				this.field_35934_V = block1.func_35275_c(this.blockAccess, i2 + 1, i3, i4);
				this.aoLightValueScratchXYNN = block1.func_35276_e(this.blockAccess, i2 - 1, i3, i4);
				this.aoLightValueScratchYZNN = block1.func_35276_e(this.blockAccess, i2, i3, i4 - 1);
				this.aoLightValueScratchYZNP = block1.func_35276_e(this.blockAccess, i2, i3, i4 + 1);
				this.aoLightValueScratchXYPN = block1.func_35276_e(this.blockAccess, i2 + 1, i3, i4);
				if(!this.aoGrassXYZCNN && !this.aoGrassXYZNNC) {
					this.aoLightValueScratchXYZNNN = this.aoLightValueScratchXYNN;
					this.field_35936_P = this.field_35935_Q;
				} else {
					this.aoLightValueScratchXYZNNN = block1.func_35276_e(this.blockAccess, i2 - 1, i3, i4 - 1);
					this.field_35936_P = block1.func_35275_c(this.blockAccess, i2 - 1, i3, i4 - 1);
				}

				if(!this.aoGrassXYZCNP && !this.aoGrassXYZNNC) {
					this.aoLightValueScratchXYZNNP = this.aoLightValueScratchXYNN;
					this.field_35938_R = this.field_35935_Q;
				} else {
					this.aoLightValueScratchXYZNNP = block1.func_35276_e(this.blockAccess, i2 - 1, i3, i4 + 1);
					this.field_35938_R = block1.func_35275_c(this.blockAccess, i2 - 1, i3, i4 + 1);
				}

				if(!this.aoGrassXYZCNN && !this.aoGrassXYZPNC) {
					this.aoLightValueScratchXYZPNN = this.aoLightValueScratchXYPN;
					this.field_35931_U = this.field_35934_V;
				} else {
					this.aoLightValueScratchXYZPNN = block1.func_35276_e(this.blockAccess, i2 + 1, i3, i4 - 1);
					this.field_35931_U = block1.func_35275_c(this.blockAccess, i2 + 1, i3, i4 - 1);
				}

				if(!this.aoGrassXYZCNP && !this.aoGrassXYZPNC) {
					this.aoLightValueScratchXYZPNP = this.aoLightValueScratchXYPN;
					this.field_35933_W = this.field_35934_V;
				} else {
					this.aoLightValueScratchXYZPNP = block1.func_35276_e(this.blockAccess, i2 + 1, i3, i4 + 1);
					this.field_35933_W = block1.func_35275_c(this.blockAccess, i2 + 1, i3, i4 + 1);
				}

				++i3;
				f9 = (this.aoLightValueScratchXYZNNP + this.aoLightValueScratchXYNN + this.aoLightValueScratchYZNP + this.aoLightValueYNeg) / 4.0F;
				f12 = (this.aoLightValueScratchYZNP + this.aoLightValueYNeg + this.aoLightValueScratchXYZPNP + this.aoLightValueScratchXYPN) / 4.0F;
				f11 = (this.aoLightValueYNeg + this.aoLightValueScratchYZNN + this.aoLightValueScratchXYPN + this.aoLightValueScratchXYZPNN) / 4.0F;
				f10 = (this.aoLightValueScratchXYNN + this.aoLightValueScratchXYZNNN + this.aoLightValueYNeg + this.aoLightValueScratchYZNN) / 4.0F;
				this.field_35943_ak = this.func_35924_c(this.field_35938_R, this.field_35935_Q, this.field_35932_T, i20);
				this.field_35948_an = this.func_35924_c(this.field_35932_T, this.field_35933_W, this.field_35934_V, i20);
				this.field_35947_am = this.func_35924_c(this.field_35937_S, this.field_35934_V, this.field_35931_U, i20);
				this.field_35944_al = this.func_35924_c(this.field_35935_Q, this.field_35936_P, this.field_35937_S, i20);
			}

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
			this.renderBottomFace(block1, (double)i2, (double)i3, (double)i4, block1.getBlockTexture(this.blockAccess, i2, i3, i4, 0));
			z8 = true;
		}

		if(this.renderAllFaces || block1.shouldSideBeRendered(this.blockAccess, i2, i3 + 1, i4, 1)) {
			if(this.field_35946_aj <= 0) {
				f12 = this.aoLightValueYPos;
				f11 = this.aoLightValueYPos;
				f10 = this.aoLightValueYPos;
				f9 = this.aoLightValueYPos;
				this.field_35943_ak = this.field_35944_al = this.field_35947_am = this.field_35948_an = i23;
			} else {
				++i3;
				this.field_35939_Y = block1.func_35275_c(this.blockAccess, i2 - 1, i3, i4);
				this.field_35949_ac = block1.func_35275_c(this.blockAccess, i2 + 1, i3, i4);
				this.field_35951_aa = block1.func_35275_c(this.blockAccess, i2, i3, i4 - 1);
				this.field_35950_ad = block1.func_35275_c(this.blockAccess, i2, i3, i4 + 1);
				this.aoLightValueScratchXYNP = block1.func_35276_e(this.blockAccess, i2 - 1, i3, i4);
				this.aoLightValueScratchXYPP = block1.func_35276_e(this.blockAccess, i2 + 1, i3, i4);
				this.aoLightValueScratchYZPN = block1.func_35276_e(this.blockAccess, i2, i3, i4 - 1);
				this.aoLightValueScratchYZPP = block1.func_35276_e(this.blockAccess, i2, i3, i4 + 1);
				if(!this.aoGrassXYZCPN && !this.aoGrassXYZNPC) {
					this.aoLightValueScratchXYZNPN = this.aoLightValueScratchXYNP;
					this.field_35940_X = this.field_35939_Y;
				} else {
					this.aoLightValueScratchXYZNPN = block1.func_35276_e(this.blockAccess, i2 - 1, i3, i4 - 1);
					this.field_35940_X = block1.func_35275_c(this.blockAccess, i2 - 1, i3, i4 - 1);
				}

				if(!this.aoGrassXYZCPN && !this.aoGrassXYZPPC) {
					this.aoLightValueScratchXYZPPN = this.aoLightValueScratchXYPP;
					this.field_35952_ab = this.field_35949_ac;
				} else {
					this.aoLightValueScratchXYZPPN = block1.func_35276_e(this.blockAccess, i2 + 1, i3, i4 - 1);
					this.field_35952_ab = block1.func_35275_c(this.blockAccess, i2 + 1, i3, i4 - 1);
				}

				if(!this.aoGrassXYZCPP && !this.aoGrassXYZNPC) {
					this.aoLightValueScratchXYZNPP = this.aoLightValueScratchXYNP;
					this.field_35941_Z = this.field_35939_Y;
				} else {
					this.aoLightValueScratchXYZNPP = block1.func_35276_e(this.blockAccess, i2 - 1, i3, i4 + 1);
					this.field_35941_Z = block1.func_35275_c(this.blockAccess, i2 - 1, i3, i4 + 1);
				}

				if(!this.aoGrassXYZCPP && !this.aoGrassXYZPPC) {
					this.aoLightValueScratchXYZPPP = this.aoLightValueScratchXYPP;
					this.field_35955_ae = this.field_35949_ac;
				} else {
					this.aoLightValueScratchXYZPPP = block1.func_35276_e(this.blockAccess, i2 + 1, i3, i4 + 1);
					this.field_35955_ae = block1.func_35275_c(this.blockAccess, i2 + 1, i3, i4 + 1);
				}

				--i3;
				f12 = (this.aoLightValueScratchXYZNPP + this.aoLightValueScratchXYNP + this.aoLightValueScratchYZPP + this.aoLightValueYPos) / 4.0F;
				f9 = (this.aoLightValueScratchYZPP + this.aoLightValueYPos + this.aoLightValueScratchXYZPPP + this.aoLightValueScratchXYPP) / 4.0F;
				f10 = (this.aoLightValueYPos + this.aoLightValueScratchYZPN + this.aoLightValueScratchXYPP + this.aoLightValueScratchXYZPPN) / 4.0F;
				f11 = (this.aoLightValueScratchXYNP + this.aoLightValueScratchXYZNPN + this.aoLightValueYPos + this.aoLightValueScratchYZPN) / 4.0F;
				this.field_35948_an = this.func_35924_c(this.field_35941_Z, this.field_35939_Y, this.field_35950_ad, i23);
				this.field_35943_ak = this.func_35924_c(this.field_35950_ad, this.field_35955_ae, this.field_35949_ac, i23);
				this.field_35944_al = this.func_35924_c(this.field_35951_aa, this.field_35949_ac, this.field_35952_ab, i23);
				this.field_35947_am = this.func_35924_c(this.field_35939_Y, this.field_35940_X, this.field_35951_aa, i23);
			}

			this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = z14 ? f5 : 1.0F;
			this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = z14 ? f6 : 1.0F;
			this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = z14 ? f7 : 1.0F;
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
			this.renderTopFace(block1, (double)i2, (double)i3, (double)i4, block1.getBlockTexture(this.blockAccess, i2, i3, i4, 1));
			z8 = true;
		}

		int i26;
		if(this.renderAllFaces || block1.shouldSideBeRendered(this.blockAccess, i2, i3, i4 - 1, 2)) {
			if(this.field_35946_aj <= 0) {
				f12 = this.aoLightValueZNeg;
				f11 = this.aoLightValueZNeg;
				f10 = this.aoLightValueZNeg;
				f9 = this.aoLightValueZNeg;
				this.field_35943_ak = this.field_35944_al = this.field_35947_am = this.field_35948_an = i21;
			} else {
				--i4;
				this.aoLightValueScratchXZNN = block1.func_35276_e(this.blockAccess, i2 - 1, i3, i4);
				this.aoLightValueScratchYZNN = block1.func_35276_e(this.blockAccess, i2, i3 - 1, i4);
				this.aoLightValueScratchYZPN = block1.func_35276_e(this.blockAccess, i2, i3 + 1, i4);
				this.aoLightValueScratchXZPN = block1.func_35276_e(this.blockAccess, i2 + 1, i3, i4);
				this.field_35956_af = block1.func_35275_c(this.blockAccess, i2 - 1, i3, i4);
				this.field_35937_S = block1.func_35275_c(this.blockAccess, i2, i3 - 1, i4);
				this.field_35951_aa = block1.func_35275_c(this.blockAccess, i2, i3 + 1, i4);
				this.field_35953_ag = block1.func_35275_c(this.blockAccess, i2 + 1, i3, i4);
				if(!this.aoGrassXYZNCN && !this.aoGrassXYZCNN) {
					this.aoLightValueScratchXYZNNN = this.aoLightValueScratchXZNN;
					this.field_35936_P = this.field_35956_af;
				} else {
					this.aoLightValueScratchXYZNNN = block1.func_35276_e(this.blockAccess, i2 - 1, i3 - 1, i4);
					this.field_35936_P = block1.func_35275_c(this.blockAccess, i2 - 1, i3 - 1, i4);
				}

				if(!this.aoGrassXYZNCN && !this.aoGrassXYZCPN) {
					this.aoLightValueScratchXYZNPN = this.aoLightValueScratchXZNN;
					this.field_35940_X = this.field_35956_af;
				} else {
					this.aoLightValueScratchXYZNPN = block1.func_35276_e(this.blockAccess, i2 - 1, i3 + 1, i4);
					this.field_35940_X = block1.func_35275_c(this.blockAccess, i2 - 1, i3 + 1, i4);
				}

				if(!this.aoGrassXYZPCN && !this.aoGrassXYZCNN) {
					this.aoLightValueScratchXYZPNN = this.aoLightValueScratchXZPN;
					this.field_35931_U = this.field_35953_ag;
				} else {
					this.aoLightValueScratchXYZPNN = block1.func_35276_e(this.blockAccess, i2 + 1, i3 - 1, i4);
					this.field_35931_U = block1.func_35275_c(this.blockAccess, i2 + 1, i3 - 1, i4);
				}

				if(!this.aoGrassXYZPCN && !this.aoGrassXYZCPN) {
					this.aoLightValueScratchXYZPPN = this.aoLightValueScratchXZPN;
					this.field_35952_ab = this.field_35953_ag;
				} else {
					this.aoLightValueScratchXYZPPN = block1.func_35276_e(this.blockAccess, i2 + 1, i3 + 1, i4);
					this.field_35952_ab = block1.func_35275_c(this.blockAccess, i2 + 1, i3 + 1, i4);
				}

				++i4;
				f9 = (this.aoLightValueScratchXZNN + this.aoLightValueScratchXYZNPN + this.aoLightValueZNeg + this.aoLightValueScratchYZPN) / 4.0F;
				f10 = (this.aoLightValueZNeg + this.aoLightValueScratchYZPN + this.aoLightValueScratchXZPN + this.aoLightValueScratchXYZPPN) / 4.0F;
				f11 = (this.aoLightValueScratchYZNN + this.aoLightValueZNeg + this.aoLightValueScratchXYZPNN + this.aoLightValueScratchXZPN) / 4.0F;
				f12 = (this.aoLightValueScratchXYZNNN + this.aoLightValueScratchXZNN + this.aoLightValueScratchYZNN + this.aoLightValueZNeg) / 4.0F;
				this.field_35943_ak = this.func_35924_c(this.field_35956_af, this.field_35940_X, this.field_35951_aa, i21);
				this.field_35944_al = this.func_35924_c(this.field_35951_aa, this.field_35953_ag, this.field_35952_ab, i21);
				this.field_35947_am = this.func_35924_c(this.field_35937_S, this.field_35931_U, this.field_35953_ag, i21);
				this.field_35948_an = this.func_35924_c(this.field_35936_P, this.field_35956_af, this.field_35937_S, i21);
			}

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
			i26 = block1.getBlockTexture(this.blockAccess, i2, i3, i4, 2);
			this.renderEastFace(block1, (double)i2, (double)i3, (double)i4, i26);
			if(fancyGrass && i26 == 3 && this.overrideBlockTexture < 0) {
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
				this.renderEastFace(block1, (double)i2, (double)i3, (double)i4, 38);
			}

			z8 = true;
		}

		if(this.renderAllFaces || block1.shouldSideBeRendered(this.blockAccess, i2, i3, i4 + 1, 3)) {
			if(this.field_35946_aj <= 0) {
				f12 = this.aoLightValueZPos;
				f11 = this.aoLightValueZPos;
				f10 = this.aoLightValueZPos;
				f9 = this.aoLightValueZPos;
				this.field_35943_ak = this.field_35944_al = this.field_35947_am = this.field_35948_an = i24;
			} else {
				++i4;
				this.aoLightValueScratchXZNP = block1.func_35276_e(this.blockAccess, i2 - 1, i3, i4);
				this.aoLightValueScratchXZPP = block1.func_35276_e(this.blockAccess, i2 + 1, i3, i4);
				this.aoLightValueScratchYZNP = block1.func_35276_e(this.blockAccess, i2, i3 - 1, i4);
				this.aoLightValueScratchYZPP = block1.func_35276_e(this.blockAccess, i2, i3 + 1, i4);
				this.field_35954_ah = block1.func_35275_c(this.blockAccess, i2 - 1, i3, i4);
				this.field_35945_ai = block1.func_35275_c(this.blockAccess, i2 + 1, i3, i4);
				this.field_35932_T = block1.func_35275_c(this.blockAccess, i2, i3 - 1, i4);
				this.field_35950_ad = block1.func_35275_c(this.blockAccess, i2, i3 + 1, i4);
				if(!this.aoGrassXYZNCP && !this.aoGrassXYZCNP) {
					this.aoLightValueScratchXYZNNP = this.aoLightValueScratchXZNP;
					this.field_35938_R = this.field_35954_ah;
				} else {
					this.aoLightValueScratchXYZNNP = block1.func_35276_e(this.blockAccess, i2 - 1, i3 - 1, i4);
					this.field_35938_R = block1.func_35275_c(this.blockAccess, i2 - 1, i3 - 1, i4);
				}

				if(!this.aoGrassXYZNCP && !this.aoGrassXYZCPP) {
					this.aoLightValueScratchXYZNPP = this.aoLightValueScratchXZNP;
					this.field_35941_Z = this.field_35954_ah;
				} else {
					this.aoLightValueScratchXYZNPP = block1.func_35276_e(this.blockAccess, i2 - 1, i3 + 1, i4);
					this.field_35941_Z = block1.func_35275_c(this.blockAccess, i2 - 1, i3 + 1, i4);
				}

				if(!this.aoGrassXYZPCP && !this.aoGrassXYZCNP) {
					this.aoLightValueScratchXYZPNP = this.aoLightValueScratchXZPP;
					this.field_35933_W = this.field_35945_ai;
				} else {
					this.aoLightValueScratchXYZPNP = block1.func_35276_e(this.blockAccess, i2 + 1, i3 - 1, i4);
					this.field_35933_W = block1.func_35275_c(this.blockAccess, i2 + 1, i3 - 1, i4);
				}

				if(!this.aoGrassXYZPCP && !this.aoGrassXYZCPP) {
					this.aoLightValueScratchXYZPPP = this.aoLightValueScratchXZPP;
					this.field_35955_ae = this.field_35945_ai;
				} else {
					this.aoLightValueScratchXYZPPP = block1.func_35276_e(this.blockAccess, i2 + 1, i3 + 1, i4);
					this.field_35955_ae = block1.func_35275_c(this.blockAccess, i2 + 1, i3 + 1, i4);
				}

				--i4;
				f9 = (this.aoLightValueScratchXZNP + this.aoLightValueScratchXYZNPP + this.aoLightValueZPos + this.aoLightValueScratchYZPP) / 4.0F;
				f12 = (this.aoLightValueZPos + this.aoLightValueScratchYZPP + this.aoLightValueScratchXZPP + this.aoLightValueScratchXYZPPP) / 4.0F;
				f11 = (this.aoLightValueScratchYZNP + this.aoLightValueZPos + this.aoLightValueScratchXYZPNP + this.aoLightValueScratchXZPP) / 4.0F;
				f10 = (this.aoLightValueScratchXYZNNP + this.aoLightValueScratchXZNP + this.aoLightValueScratchYZNP + this.aoLightValueZPos) / 4.0F;
				this.field_35943_ak = this.func_35924_c(this.field_35954_ah, this.field_35941_Z, this.field_35950_ad, i24);
				this.field_35948_an = this.func_35924_c(this.field_35950_ad, this.field_35945_ai, this.field_35955_ae, i24);
				this.field_35947_am = this.func_35924_c(this.field_35932_T, this.field_35933_W, this.field_35945_ai, i24);
				this.field_35944_al = this.func_35924_c(this.field_35938_R, this.field_35954_ah, this.field_35932_T, i24);
			}

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
			i26 = block1.getBlockTexture(this.blockAccess, i2, i3, i4, 3);
			this.renderWestFace(block1, (double)i2, (double)i3, (double)i4, block1.getBlockTexture(this.blockAccess, i2, i3, i4, 3));
			if(fancyGrass && i26 == 3 && this.overrideBlockTexture < 0) {
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
				this.renderWestFace(block1, (double)i2, (double)i3, (double)i4, 38);
			}

			z8 = true;
		}

		if(this.renderAllFaces || block1.shouldSideBeRendered(this.blockAccess, i2 - 1, i3, i4, 4)) {
			if(this.field_35946_aj <= 0) {
				f12 = this.aoLightValueXNeg;
				f11 = this.aoLightValueXNeg;
				f10 = this.aoLightValueXNeg;
				f9 = this.aoLightValueXNeg;
				this.field_35943_ak = this.field_35944_al = this.field_35947_am = this.field_35948_an = i19;
			} else {
				--i2;
				this.aoLightValueScratchXYNN = block1.func_35276_e(this.blockAccess, i2, i3 - 1, i4);
				this.aoLightValueScratchXZNN = block1.func_35276_e(this.blockAccess, i2, i3, i4 - 1);
				this.aoLightValueScratchXZNP = block1.func_35276_e(this.blockAccess, i2, i3, i4 + 1);
				this.aoLightValueScratchXYNP = block1.func_35276_e(this.blockAccess, i2, i3 + 1, i4);
				this.field_35935_Q = block1.func_35275_c(this.blockAccess, i2, i3 - 1, i4);
				this.field_35956_af = block1.func_35275_c(this.blockAccess, i2, i3, i4 - 1);
				this.field_35954_ah = block1.func_35275_c(this.blockAccess, i2, i3, i4 + 1);
				this.field_35939_Y = block1.func_35275_c(this.blockAccess, i2, i3 + 1, i4);
				if(!this.aoGrassXYZNCN && !this.aoGrassXYZNNC) {
					this.aoLightValueScratchXYZNNN = this.aoLightValueScratchXZNN;
					this.field_35936_P = this.field_35956_af;
				} else {
					this.aoLightValueScratchXYZNNN = block1.func_35276_e(this.blockAccess, i2, i3 - 1, i4 - 1);
					this.field_35936_P = block1.func_35275_c(this.blockAccess, i2, i3 - 1, i4 - 1);
				}

				if(!this.aoGrassXYZNCP && !this.aoGrassXYZNNC) {
					this.aoLightValueScratchXYZNNP = this.aoLightValueScratchXZNP;
					this.field_35938_R = this.field_35954_ah;
				} else {
					this.aoLightValueScratchXYZNNP = block1.func_35276_e(this.blockAccess, i2, i3 - 1, i4 + 1);
					this.field_35938_R = block1.func_35275_c(this.blockAccess, i2, i3 - 1, i4 + 1);
				}

				if(!this.aoGrassXYZNCN && !this.aoGrassXYZNPC) {
					this.aoLightValueScratchXYZNPN = this.aoLightValueScratchXZNN;
					this.field_35940_X = this.field_35956_af;
				} else {
					this.aoLightValueScratchXYZNPN = block1.func_35276_e(this.blockAccess, i2, i3 + 1, i4 - 1);
					this.field_35940_X = block1.func_35275_c(this.blockAccess, i2, i3 + 1, i4 - 1);
				}

				if(!this.aoGrassXYZNCP && !this.aoGrassXYZNPC) {
					this.aoLightValueScratchXYZNPP = this.aoLightValueScratchXZNP;
					this.field_35941_Z = this.field_35954_ah;
				} else {
					this.aoLightValueScratchXYZNPP = block1.func_35276_e(this.blockAccess, i2, i3 + 1, i4 + 1);
					this.field_35941_Z = block1.func_35275_c(this.blockAccess, i2, i3 + 1, i4 + 1);
				}

				++i2;
				f12 = (this.aoLightValueScratchXYNN + this.aoLightValueScratchXYZNNP + this.aoLightValueXNeg + this.aoLightValueScratchXZNP) / 4.0F;
				f9 = (this.aoLightValueXNeg + this.aoLightValueScratchXZNP + this.aoLightValueScratchXYNP + this.aoLightValueScratchXYZNPP) / 4.0F;
				f10 = (this.aoLightValueScratchXZNN + this.aoLightValueXNeg + this.aoLightValueScratchXYZNPN + this.aoLightValueScratchXYNP) / 4.0F;
				f11 = (this.aoLightValueScratchXYZNNN + this.aoLightValueScratchXYNN + this.aoLightValueScratchXZNN + this.aoLightValueXNeg) / 4.0F;
				this.field_35948_an = this.func_35924_c(this.field_35935_Q, this.field_35938_R, this.field_35954_ah, i19);
				this.field_35943_ak = this.func_35924_c(this.field_35954_ah, this.field_35939_Y, this.field_35941_Z, i19);
				this.field_35944_al = this.func_35924_c(this.field_35956_af, this.field_35940_X, this.field_35939_Y, i19);
				this.field_35947_am = this.func_35924_c(this.field_35936_P, this.field_35935_Q, this.field_35956_af, i19);
			}

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
			i26 = block1.getBlockTexture(this.blockAccess, i2, i3, i4, 4);
			this.renderNorthFace(block1, (double)i2, (double)i3, (double)i4, i26);
			if(fancyGrass && i26 == 3 && this.overrideBlockTexture < 0) {
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
				this.renderNorthFace(block1, (double)i2, (double)i3, (double)i4, 38);
			}

			z8 = true;
		}

		if(this.renderAllFaces || block1.shouldSideBeRendered(this.blockAccess, i2 + 1, i3, i4, 5)) {
			if(this.field_35946_aj <= 0) {
				f12 = this.aoLightValueXPos;
				f11 = this.aoLightValueXPos;
				f10 = this.aoLightValueXPos;
				f9 = this.aoLightValueXPos;
				this.field_35943_ak = this.field_35944_al = this.field_35947_am = this.field_35948_an = i22;
			} else {
				++i2;
				this.aoLightValueScratchXYPN = block1.func_35276_e(this.blockAccess, i2, i3 - 1, i4);
				this.aoLightValueScratchXZPN = block1.func_35276_e(this.blockAccess, i2, i3, i4 - 1);
				this.aoLightValueScratchXZPP = block1.func_35276_e(this.blockAccess, i2, i3, i4 + 1);
				this.aoLightValueScratchXYPP = block1.func_35276_e(this.blockAccess, i2, i3 + 1, i4);
				this.field_35934_V = block1.func_35275_c(this.blockAccess, i2, i3 - 1, i4);
				this.field_35953_ag = block1.func_35275_c(this.blockAccess, i2, i3, i4 - 1);
				this.field_35945_ai = block1.func_35275_c(this.blockAccess, i2, i3, i4 + 1);
				this.field_35949_ac = block1.func_35275_c(this.blockAccess, i2, i3 + 1, i4);
				if(!this.aoGrassXYZPNC && !this.aoGrassXYZPCN) {
					this.aoLightValueScratchXYZPNN = this.aoLightValueScratchXZPN;
					this.field_35931_U = this.field_35953_ag;
				} else {
					this.aoLightValueScratchXYZPNN = block1.func_35276_e(this.blockAccess, i2, i3 - 1, i4 - 1);
					this.field_35931_U = block1.func_35275_c(this.blockAccess, i2, i3 - 1, i4 - 1);
				}

				if(!this.aoGrassXYZPNC && !this.aoGrassXYZPCP) {
					this.aoLightValueScratchXYZPNP = this.aoLightValueScratchXZPP;
					this.field_35933_W = this.field_35945_ai;
				} else {
					this.aoLightValueScratchXYZPNP = block1.func_35276_e(this.blockAccess, i2, i3 - 1, i4 + 1);
					this.field_35933_W = block1.func_35275_c(this.blockAccess, i2, i3 - 1, i4 + 1);
				}

				if(!this.aoGrassXYZPPC && !this.aoGrassXYZPCN) {
					this.aoLightValueScratchXYZPPN = this.aoLightValueScratchXZPN;
					this.field_35952_ab = this.field_35953_ag;
				} else {
					this.aoLightValueScratchXYZPPN = block1.func_35276_e(this.blockAccess, i2, i3 + 1, i4 - 1);
					this.field_35952_ab = block1.func_35275_c(this.blockAccess, i2, i3 + 1, i4 - 1);
				}

				if(!this.aoGrassXYZPPC && !this.aoGrassXYZPCP) {
					this.aoLightValueScratchXYZPPP = this.aoLightValueScratchXZPP;
					this.field_35955_ae = this.field_35945_ai;
				} else {
					this.aoLightValueScratchXYZPPP = block1.func_35276_e(this.blockAccess, i2, i3 + 1, i4 + 1);
					this.field_35955_ae = block1.func_35275_c(this.blockAccess, i2, i3 + 1, i4 + 1);
				}

				--i2;
				f9 = (this.aoLightValueScratchXYPN + this.aoLightValueScratchXYZPNP + this.aoLightValueXPos + this.aoLightValueScratchXZPP) / 4.0F;
				f12 = (this.aoLightValueXPos + this.aoLightValueScratchXZPP + this.aoLightValueScratchXYPP + this.aoLightValueScratchXYZPPP) / 4.0F;
				f11 = (this.aoLightValueScratchXZPN + this.aoLightValueXPos + this.aoLightValueScratchXYZPPN + this.aoLightValueScratchXYPP) / 4.0F;
				f10 = (this.aoLightValueScratchXYZPNN + this.aoLightValueScratchXYPN + this.aoLightValueScratchXZPN + this.aoLightValueXPos) / 4.0F;
				this.field_35943_ak = this.func_35924_c(this.field_35934_V, this.field_35933_W, this.field_35945_ai, i22);
				this.field_35948_an = this.func_35924_c(this.field_35945_ai, this.field_35949_ac, this.field_35955_ae, i22);
				this.field_35947_am = this.func_35924_c(this.field_35953_ag, this.field_35952_ab, this.field_35949_ac, i22);
				this.field_35944_al = this.func_35924_c(this.field_35931_U, this.field_35934_V, this.field_35953_ag, i22);
			}

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
			i26 = block1.getBlockTexture(this.blockAccess, i2, i3, i4, 5);
			this.renderSouthFace(block1, (double)i2, (double)i3, (double)i4, i26);
			if(fancyGrass && i26 == 3 && this.overrideBlockTexture < 0) {
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
				this.renderSouthFace(block1, (double)i2, (double)i3, (double)i4, 38);
			}

			z8 = true;
		}

		this.enableAO = false;
		return z8;
	}

	private int func_35924_c(int i1, int i2, int i3, int i4) {
		if(i1 == 0) {
			i1 = i4;
		}

		if(i2 == 0) {
			i2 = i4;
		}

		if(i3 == 0) {
			i3 = i4;
		}

		return i1 + i2 + i3 + i4 >> 2 & 16711935;
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

		int i26 = block1.func_35275_c(this.blockAccess, i2, i3, i4);
		if(this.renderAllFaces || block1.shouldSideBeRendered(this.blockAccess, i2, i3 - 1, i4, 0)) {
			tessellator8.func_35835_b(block1.minY > 0.0D ? i26 : block1.func_35275_c(this.blockAccess, i2, i3 - 1, i4));
			tessellator8.setColorOpaque_F(f17, f20, f23);
			this.renderBottomFace(block1, (double)i2, (double)i3, (double)i4, block1.getBlockTexture(this.blockAccess, i2, i3, i4, 0));
			z9 = true;
		}

		if(this.renderAllFaces || block1.shouldSideBeRendered(this.blockAccess, i2, i3 + 1, i4, 1)) {
			tessellator8.func_35835_b(block1.maxY < 1.0D ? i26 : block1.func_35275_c(this.blockAccess, i2, i3 + 1, i4));
			tessellator8.setColorOpaque_F(f14, f15, f16);
			this.renderTopFace(block1, (double)i2, (double)i3, (double)i4, block1.getBlockTexture(this.blockAccess, i2, i3, i4, 1));
			z9 = true;
		}

		int i28;
		if(this.renderAllFaces || block1.shouldSideBeRendered(this.blockAccess, i2, i3, i4 - 1, 2)) {
			tessellator8.func_35835_b(block1.minZ > 0.0D ? i26 : block1.func_35275_c(this.blockAccess, i2, i3, i4 - 1));
			tessellator8.setColorOpaque_F(f18, f21, f24);
			i28 = block1.getBlockTexture(this.blockAccess, i2, i3, i4, 2);
			this.renderEastFace(block1, (double)i2, (double)i3, (double)i4, i28);
			if(fancyGrass && i28 == 3 && this.overrideBlockTexture < 0) {
				tessellator8.setColorOpaque_F(f18 * f5, f21 * f6, f24 * f7);
				this.renderEastFace(block1, (double)i2, (double)i3, (double)i4, 38);
			}

			z9 = true;
		}

		if(this.renderAllFaces || block1.shouldSideBeRendered(this.blockAccess, i2, i3, i4 + 1, 3)) {
			tessellator8.func_35835_b(block1.maxZ < 1.0D ? i26 : block1.func_35275_c(this.blockAccess, i2, i3, i4 + 1));
			tessellator8.setColorOpaque_F(f18, f21, f24);
			i28 = block1.getBlockTexture(this.blockAccess, i2, i3, i4, 3);
			this.renderWestFace(block1, (double)i2, (double)i3, (double)i4, i28);
			if(fancyGrass && i28 == 3 && this.overrideBlockTexture < 0) {
				tessellator8.setColorOpaque_F(f18 * f5, f21 * f6, f24 * f7);
				this.renderWestFace(block1, (double)i2, (double)i3, (double)i4, 38);
			}

			z9 = true;
		}

		if(this.renderAllFaces || block1.shouldSideBeRendered(this.blockAccess, i2 - 1, i3, i4, 4)) {
			tessellator8.func_35835_b(block1.minX > 0.0D ? i26 : block1.func_35275_c(this.blockAccess, i2 - 1, i3, i4));
			tessellator8.setColorOpaque_F(f19, f22, f25);
			i28 = block1.getBlockTexture(this.blockAccess, i2, i3, i4, 4);
			this.renderNorthFace(block1, (double)i2, (double)i3, (double)i4, i28);
			if(fancyGrass && i28 == 3 && this.overrideBlockTexture < 0) {
				tessellator8.setColorOpaque_F(f19 * f5, f22 * f6, f25 * f7);
				this.renderNorthFace(block1, (double)i2, (double)i3, (double)i4, 38);
			}

			z9 = true;
		}

		if(this.renderAllFaces || block1.shouldSideBeRendered(this.blockAccess, i2 + 1, i3, i4, 5)) {
			tessellator8.func_35835_b(block1.maxX < 1.0D ? i26 : block1.func_35275_c(this.blockAccess, i2 + 1, i3, i4));
			tessellator8.setColorOpaque_F(f19, f22, f25);
			i28 = block1.getBlockTexture(this.blockAccess, i2, i3, i4, 5);
			this.renderSouthFace(block1, (double)i2, (double)i3, (double)i4, i28);
			if(fancyGrass && i28 == 3 && this.overrideBlockTexture < 0) {
				tessellator8.setColorOpaque_F(f19 * f5, f22 * f6, f25 * f7);
				this.renderSouthFace(block1, (double)i2, (double)i3, (double)i4, 38);
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
		if(EntityRenderer.anaglyphEnable) {
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
		int i28 = block1.func_35275_c(this.blockAccess, i2, i3, i4);
		if(this.renderAllFaces || block1.shouldSideBeRendered(this.blockAccess, i2, i3 - 1, i4, 0)) {
			tessellator8.func_35835_b(block1.minY > 0.0D ? i28 : block1.func_35275_c(this.blockAccess, i2, i3 - 1, i4));
			tessellator8.setColorOpaque_F(f14, f18, f22);
			this.renderBottomFace(block1, (double)i2, (double)i3, (double)i4, block1.getBlockTexture(this.blockAccess, i2, i3, i4, 0));
			z9 = true;
		}

		if(this.renderAllFaces || block1.shouldSideBeRendered(this.blockAccess, i2, i3 + 1, i4, 1)) {
			tessellator8.func_35835_b(block1.maxY < 1.0D ? i28 : block1.func_35275_c(this.blockAccess, i2, i3 + 1, i4));
			tessellator8.setColorOpaque_F(f15, f19, f23);
			this.renderTopFace(block1, (double)i2, (double)i3, (double)i4, block1.getBlockTexture(this.blockAccess, i2, i3, i4, 1));
			z9 = true;
		}

		if(this.renderAllFaces || block1.shouldSideBeRendered(this.blockAccess, i2, i3, i4 - 1, 2)) {
			tessellator8.func_35835_b(block1.minZ > 0.0D ? i28 : block1.func_35275_c(this.blockAccess, i2, i3, i4 - 1));
			tessellator8.setColorOpaque_F(f16, f20, f24);
			tessellator8.setTranslationF(0.0F, 0.0F, f26);
			this.renderEastFace(block1, (double)i2, (double)i3, (double)i4, block1.getBlockTexture(this.blockAccess, i2, i3, i4, 2));
			tessellator8.setTranslationF(0.0F, 0.0F, -f26);
			z9 = true;
		}

		if(this.renderAllFaces || block1.shouldSideBeRendered(this.blockAccess, i2, i3, i4 + 1, 3)) {
			tessellator8.func_35835_b(block1.maxZ < 1.0D ? i28 : block1.func_35275_c(this.blockAccess, i2, i3, i4 + 1));
			tessellator8.setColorOpaque_F(f16, f20, f24);
			tessellator8.setTranslationF(0.0F, 0.0F, -f26);
			this.renderWestFace(block1, (double)i2, (double)i3, (double)i4, block1.getBlockTexture(this.blockAccess, i2, i3, i4, 3));
			tessellator8.setTranslationF(0.0F, 0.0F, f26);
			z9 = true;
		}

		if(this.renderAllFaces || block1.shouldSideBeRendered(this.blockAccess, i2 - 1, i3, i4, 4)) {
			tessellator8.func_35835_b(block1.minX > 0.0D ? i28 : block1.func_35275_c(this.blockAccess, i2 - 1, i3, i4));
			tessellator8.setColorOpaque_F(f17, f21, f25);
			tessellator8.setTranslationF(f26, 0.0F, 0.0F);
			this.renderNorthFace(block1, (double)i2, (double)i3, (double)i4, block1.getBlockTexture(this.blockAccess, i2, i3, i4, 4));
			tessellator8.setTranslationF(-f26, 0.0F, 0.0F);
			z9 = true;
		}

		if(this.renderAllFaces || block1.shouldSideBeRendered(this.blockAccess, i2 + 1, i3, i4, 5)) {
			tessellator8.func_35835_b(block1.maxX < 1.0D ? i28 : block1.func_35275_c(this.blockAccess, i2 + 1, i3, i4));
			tessellator8.setColorOpaque_F(f17, f21, f25);
			tessellator8.setTranslationF(-f26, 0.0F, 0.0F);
			this.renderSouthFace(block1, (double)i2, (double)i3, (double)i4, block1.getBlockTexture(this.blockAccess, i2, i3, i4, 5));
			tessellator8.setTranslationF(f26, 0.0F, 0.0F);
			z9 = true;
		}

		return z9;
	}

	public boolean func_35925_a(BlockFence blockFence1, int i2, int i3, int i4) {
		boolean z5 = false;
		float f6 = 0.375F;
		float f7 = 0.625F;
		blockFence1.setBlockBounds(f6, 0.0F, f6, f7, 1.0F, f7);
		this.renderStandardBlock(blockFence1, i2, i3, i4);
		z5 = true;
		boolean z8 = false;
		boolean z9 = false;
		if(blockFence1.func_35303_f(this.blockAccess, i2 - 1, i3, i4) || blockFence1.func_35303_f(this.blockAccess, i2 + 1, i3, i4)) {
			z8 = true;
		}

		if(blockFence1.func_35303_f(this.blockAccess, i2, i3, i4 - 1) || blockFence1.func_35303_f(this.blockAccess, i2, i3, i4 + 1)) {
			z9 = true;
		}

		boolean z10 = blockFence1.func_35303_f(this.blockAccess, i2 - 1, i3, i4);
		boolean z11 = blockFence1.func_35303_f(this.blockAccess, i2 + 1, i3, i4);
		boolean z12 = blockFence1.func_35303_f(this.blockAccess, i2, i3, i4 - 1);
		boolean z13 = blockFence1.func_35303_f(this.blockAccess, i2, i3, i4 + 1);
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
			blockFence1.setBlockBounds(f16, f14, f6, f17, f15, f7);
			this.renderStandardBlock(blockFence1, i2, i3, i4);
			z5 = true;
		}

		if(z9) {
			blockFence1.setBlockBounds(f6, f14, f18, f7, f15, f19);
			this.renderStandardBlock(blockFence1, i2, i3, i4);
			z5 = true;
		}

		f14 = 0.375F;
		f15 = 0.5625F;
		if(z8) {
			blockFence1.setBlockBounds(f16, f14, f6, f17, f15, f7);
			this.renderStandardBlock(blockFence1, i2, i3, i4);
			z5 = true;
		}

		if(z9) {
			blockFence1.setBlockBounds(f6, f14, f18, f7, f15, f19);
			this.renderStandardBlock(blockFence1, i2, i3, i4);
			z5 = true;
		}

		blockFence1.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		return z5;
	}

	public boolean func_35923_a(BlockFenceGate blockFenceGate1, int i2, int i3, int i4) {
		boolean z5 = true;
		int i6 = this.blockAccess.getBlockMetadata(i2, i3, i4);
		boolean z7 = BlockFenceGate.func_35291_d(i6);
		int i8 = BlockFenceGate.func_35290_f(i6);
		float f15;
		float f16;
		float f17;
		float f18;
		if(i8 != 3 && i8 != 1) {
			f15 = 0.0F;
			f16 = 0.125F;
			f17 = 0.4375F;
			f18 = 0.5625F;
			blockFenceGate1.setBlockBounds(f15, 0.3125F, f17, f16, 1.0F, f18);
			this.renderStandardBlock(blockFenceGate1, i2, i3, i4);
			f15 = 0.875F;
			f16 = 1.0F;
			blockFenceGate1.setBlockBounds(f15, 0.3125F, f17, f16, 1.0F, f18);
			this.renderStandardBlock(blockFenceGate1, i2, i3, i4);
		} else {
			f15 = 0.4375F;
			f16 = 0.5625F;
			f17 = 0.0F;
			f18 = 0.125F;
			blockFenceGate1.setBlockBounds(f15, 0.3125F, f17, f16, 1.0F, f18);
			this.renderStandardBlock(blockFenceGate1, i2, i3, i4);
			f17 = 0.875F;
			f18 = 1.0F;
			blockFenceGate1.setBlockBounds(f15, 0.3125F, f17, f16, 1.0F, f18);
			this.renderStandardBlock(blockFenceGate1, i2, i3, i4);
		}

		if(!z7) {
			if(i8 != 3 && i8 != 1) {
				f15 = 0.375F;
				f16 = 0.5F;
				f17 = 0.4375F;
				f18 = 0.5625F;
				blockFenceGate1.setBlockBounds(f15, 0.375F, f17, f16, 0.9375F, f18);
				this.renderStandardBlock(blockFenceGate1, i2, i3, i4);
				f15 = 0.5F;
				f16 = 0.625F;
				blockFenceGate1.setBlockBounds(f15, 0.375F, f17, f16, 0.9375F, f18);
				this.renderStandardBlock(blockFenceGate1, i2, i3, i4);
				f15 = 0.625F;
				f16 = 0.875F;
				blockFenceGate1.setBlockBounds(f15, 0.375F, f17, f16, 0.5625F, f18);
				this.renderStandardBlock(blockFenceGate1, i2, i3, i4);
				blockFenceGate1.setBlockBounds(f15, 0.75F, f17, f16, 0.9375F, f18);
				this.renderStandardBlock(blockFenceGate1, i2, i3, i4);
				f15 = 0.125F;
				f16 = 0.375F;
				blockFenceGate1.setBlockBounds(f15, 0.375F, f17, f16, 0.5625F, f18);
				this.renderStandardBlock(blockFenceGate1, i2, i3, i4);
				blockFenceGate1.setBlockBounds(f15, 0.75F, f17, f16, 0.9375F, f18);
				this.renderStandardBlock(blockFenceGate1, i2, i3, i4);
			} else {
				f15 = 0.4375F;
				f16 = 0.5625F;
				f17 = 0.375F;
				f18 = 0.5F;
				blockFenceGate1.setBlockBounds(f15, 0.375F, f17, f16, 0.9375F, f18);
				this.renderStandardBlock(blockFenceGate1, i2, i3, i4);
				f17 = 0.5F;
				f18 = 0.625F;
				blockFenceGate1.setBlockBounds(f15, 0.375F, f17, f16, 0.9375F, f18);
				this.renderStandardBlock(blockFenceGate1, i2, i3, i4);
				f17 = 0.625F;
				f18 = 0.875F;
				blockFenceGate1.setBlockBounds(f15, 0.375F, f17, f16, 0.5625F, f18);
				this.renderStandardBlock(blockFenceGate1, i2, i3, i4);
				blockFenceGate1.setBlockBounds(f15, 0.75F, f17, f16, 0.9375F, f18);
				this.renderStandardBlock(blockFenceGate1, i2, i3, i4);
				f17 = 0.125F;
				f18 = 0.375F;
				blockFenceGate1.setBlockBounds(f15, 0.375F, f17, f16, 0.5625F, f18);
				this.renderStandardBlock(blockFenceGate1, i2, i3, i4);
				blockFenceGate1.setBlockBounds(f15, 0.75F, f17, f16, 0.9375F, f18);
				this.renderStandardBlock(blockFenceGate1, i2, i3, i4);
			}
		} else if(i8 == 3) {
			blockFenceGate1.setBlockBounds(0.8125F, 0.375F, 0.0F, 0.9375F, 0.9375F, 0.125F);
			this.renderStandardBlock(blockFenceGate1, i2, i3, i4);
			blockFenceGate1.setBlockBounds(0.8125F, 0.375F, 0.875F, 0.9375F, 0.9375F, 1.0F);
			this.renderStandardBlock(blockFenceGate1, i2, i3, i4);
			blockFenceGate1.setBlockBounds(0.5625F, 0.375F, 0.0F, 0.8125F, 0.5625F, 0.125F);
			this.renderStandardBlock(blockFenceGate1, i2, i3, i4);
			blockFenceGate1.setBlockBounds(0.5625F, 0.375F, 0.875F, 0.8125F, 0.5625F, 1.0F);
			this.renderStandardBlock(blockFenceGate1, i2, i3, i4);
			blockFenceGate1.setBlockBounds(0.5625F, 0.75F, 0.0F, 0.8125F, 0.9375F, 0.125F);
			this.renderStandardBlock(blockFenceGate1, i2, i3, i4);
			blockFenceGate1.setBlockBounds(0.5625F, 0.75F, 0.875F, 0.8125F, 0.9375F, 1.0F);
			this.renderStandardBlock(blockFenceGate1, i2, i3, i4);
		} else if(i8 == 1) {
			blockFenceGate1.setBlockBounds(0.0625F, 0.375F, 0.0F, 0.1875F, 0.9375F, 0.125F);
			this.renderStandardBlock(blockFenceGate1, i2, i3, i4);
			blockFenceGate1.setBlockBounds(0.0625F, 0.375F, 0.875F, 0.1875F, 0.9375F, 1.0F);
			this.renderStandardBlock(blockFenceGate1, i2, i3, i4);
			blockFenceGate1.setBlockBounds(0.1875F, 0.375F, 0.0F, 0.4375F, 0.5625F, 0.125F);
			this.renderStandardBlock(blockFenceGate1, i2, i3, i4);
			blockFenceGate1.setBlockBounds(0.1875F, 0.375F, 0.875F, 0.4375F, 0.5625F, 1.0F);
			this.renderStandardBlock(blockFenceGate1, i2, i3, i4);
			blockFenceGate1.setBlockBounds(0.1875F, 0.75F, 0.0F, 0.4375F, 0.9375F, 0.125F);
			this.renderStandardBlock(blockFenceGate1, i2, i3, i4);
			blockFenceGate1.setBlockBounds(0.1875F, 0.75F, 0.875F, 0.4375F, 0.9375F, 1.0F);
			this.renderStandardBlock(blockFenceGate1, i2, i3, i4);
		} else if(i8 == 0) {
			blockFenceGate1.setBlockBounds(0.0F, 0.375F, 0.8125F, 0.125F, 0.9375F, 0.9375F);
			this.renderStandardBlock(blockFenceGate1, i2, i3, i4);
			blockFenceGate1.setBlockBounds(0.875F, 0.375F, 0.8125F, 1.0F, 0.9375F, 0.9375F);
			this.renderStandardBlock(blockFenceGate1, i2, i3, i4);
			blockFenceGate1.setBlockBounds(0.0F, 0.375F, 0.5625F, 0.125F, 0.5625F, 0.8125F);
			this.renderStandardBlock(blockFenceGate1, i2, i3, i4);
			blockFenceGate1.setBlockBounds(0.875F, 0.375F, 0.5625F, 1.0F, 0.5625F, 0.8125F);
			this.renderStandardBlock(blockFenceGate1, i2, i3, i4);
			blockFenceGate1.setBlockBounds(0.0F, 0.75F, 0.5625F, 0.125F, 0.9375F, 0.8125F);
			this.renderStandardBlock(blockFenceGate1, i2, i3, i4);
			blockFenceGate1.setBlockBounds(0.875F, 0.75F, 0.5625F, 1.0F, 0.9375F, 0.8125F);
			this.renderStandardBlock(blockFenceGate1, i2, i3, i4);
		} else if(i8 == 2) {
			blockFenceGate1.setBlockBounds(0.0F, 0.375F, 0.0625F, 0.125F, 0.9375F, 0.1875F);
			this.renderStandardBlock(blockFenceGate1, i2, i3, i4);
			blockFenceGate1.setBlockBounds(0.875F, 0.375F, 0.0625F, 1.0F, 0.9375F, 0.1875F);
			this.renderStandardBlock(blockFenceGate1, i2, i3, i4);
			blockFenceGate1.setBlockBounds(0.0F, 0.375F, 0.1875F, 0.125F, 0.5625F, 0.4375F);
			this.renderStandardBlock(blockFenceGate1, i2, i3, i4);
			blockFenceGate1.setBlockBounds(0.875F, 0.375F, 0.1875F, 1.0F, 0.5625F, 0.4375F);
			this.renderStandardBlock(blockFenceGate1, i2, i3, i4);
			blockFenceGate1.setBlockBounds(0.0F, 0.75F, 0.1875F, 0.125F, 0.9375F, 0.4375F);
			this.renderStandardBlock(blockFenceGate1, i2, i3, i4);
			blockFenceGate1.setBlockBounds(0.875F, 0.75F, 0.1875F, 1.0F, 0.9375F, 0.4375F);
			this.renderStandardBlock(blockFenceGate1, i2, i3, i4);
		}

		blockFenceGate1.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
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
		int i12 = block1.func_35275_c(this.blockAccess, i2, i3, i4);
		tessellator5.func_35835_b(block1.minY > 0.0D ? i12 : block1.func_35275_c(this.blockAccess, i2, i3 - 1, i4));
		tessellator5.setColorOpaque_F(f8, f8, f8);
		this.renderBottomFace(block1, (double)i2, (double)i3, (double)i4, block1.getBlockTexture(this.blockAccess, i2, i3, i4, 0));
		z7 = true;
		tessellator5.func_35835_b(block1.maxY < 1.0D ? i12 : block1.func_35275_c(this.blockAccess, i2, i3 + 1, i4));
		tessellator5.setColorOpaque_F(f9, f9, f9);
		this.renderTopFace(block1, (double)i2, (double)i3, (double)i4, block1.getBlockTexture(this.blockAccess, i2, i3, i4, 1));
		z7 = true;
		tessellator5.func_35835_b(block1.minZ > 0.0D ? i12 : block1.func_35275_c(this.blockAccess, i2, i3, i4 - 1));
		tessellator5.setColorOpaque_F(f10, f10, f10);
		int i14 = block1.getBlockTexture(this.blockAccess, i2, i3, i4, 2);
		if(i14 < 0) {
			this.flipTexture = true;
			i14 = -i14;
		}

		this.renderEastFace(block1, (double)i2, (double)i3, (double)i4, i14);
		z7 = true;
		this.flipTexture = false;
		tessellator5.func_35835_b(block1.maxZ < 1.0D ? i12 : block1.func_35275_c(this.blockAccess, i2, i3, i4 + 1));
		tessellator5.setColorOpaque_F(f10, f10, f10);
		i14 = block1.getBlockTexture(this.blockAccess, i2, i3, i4, 3);
		if(i14 < 0) {
			this.flipTexture = true;
			i14 = -i14;
		}

		this.renderWestFace(block1, (double)i2, (double)i3, (double)i4, i14);
		z7 = true;
		this.flipTexture = false;
		tessellator5.func_35835_b(block1.minX > 0.0D ? i12 : block1.func_35275_c(this.blockAccess, i2 - 1, i3, i4));
		tessellator5.setColorOpaque_F(f11, f11, f11);
		i14 = block1.getBlockTexture(this.blockAccess, i2, i3, i4, 4);
		if(i14 < 0) {
			this.flipTexture = true;
			i14 = -i14;
		}

		this.renderNorthFace(block1, (double)i2, (double)i3, (double)i4, i14);
		z7 = true;
		this.flipTexture = false;
		tessellator5.func_35835_b(block1.maxX < 1.0D ? i12 : block1.func_35275_c(this.blockAccess, i2 + 1, i3, i4));
		tessellator5.setColorOpaque_F(f11, f11, f11);
		i14 = block1.getBlockTexture(this.blockAccess, i2, i3, i4, 5);
		if(i14 < 0) {
			this.flipTexture = true;
			i14 = -i14;
		}

		this.renderSouthFace(block1, (double)i2, (double)i3, (double)i4, i14);
		z7 = true;
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
			d12 = (double)(((float)i10 + 0.0F) / 256.0F);
			d14 = (double)(((float)i10 + 15.99F) / 256.0F);
		}

		if(block1.minZ < 0.0D || block1.maxZ > 1.0D) {
			d16 = (double)(((float)i11 + 0.0F) / 256.0F);
			d18 = (double)(((float)i11 + 15.99F) / 256.0F);
		}

		double d20 = d14;
		double d22 = d12;
		double d24 = d16;
		double d26 = d18;
		if(this.uvRotateBottom == 2) {
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
		} else if(this.uvRotateBottom == 1) {
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
		} else if(this.uvRotateBottom == 3) {
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
			tessellator9.func_35835_b(this.field_35943_ak);
			tessellator9.addVertexWithUV(d28, d32, d36, d22, d26);
			tessellator9.setColorOpaque_F(this.colorRedBottomLeft, this.colorGreenBottomLeft, this.colorBlueBottomLeft);
			tessellator9.func_35835_b(this.field_35944_al);
			tessellator9.addVertexWithUV(d28, d32, d34, d12, d16);
			tessellator9.setColorOpaque_F(this.colorRedBottomRight, this.colorGreenBottomRight, this.colorBlueBottomRight);
			tessellator9.func_35835_b(this.field_35947_am);
			tessellator9.addVertexWithUV(d30, d32, d34, d20, d24);
			tessellator9.setColorOpaque_F(this.colorRedTopRight, this.colorGreenTopRight, this.colorBlueTopRight);
			tessellator9.func_35835_b(this.field_35948_an);
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
			d12 = (double)(((float)i10 + 0.0F) / 256.0F);
			d14 = (double)(((float)i10 + 15.99F) / 256.0F);
		}

		if(block1.minZ < 0.0D || block1.maxZ > 1.0D) {
			d16 = (double)(((float)i11 + 0.0F) / 256.0F);
			d18 = (double)(((float)i11 + 15.99F) / 256.0F);
		}

		double d20 = d14;
		double d22 = d12;
		double d24 = d16;
		double d26 = d18;
		if(this.uvRotateTop == 1) {
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
		} else if(this.uvRotateTop == 2) {
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
		} else if(this.uvRotateTop == 3) {
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
			tessellator9.func_35835_b(this.field_35943_ak);
			tessellator9.addVertexWithUV(d30, d32, d36, d14, d18);
			tessellator9.setColorOpaque_F(this.colorRedBottomLeft, this.colorGreenBottomLeft, this.colorBlueBottomLeft);
			tessellator9.func_35835_b(this.field_35944_al);
			tessellator9.addVertexWithUV(d30, d32, d34, d20, d24);
			tessellator9.setColorOpaque_F(this.colorRedBottomRight, this.colorGreenBottomRight, this.colorBlueBottomRight);
			tessellator9.func_35835_b(this.field_35947_am);
			tessellator9.addVertexWithUV(d28, d32, d34, d12, d16);
			tessellator9.setColorOpaque_F(this.colorRedTopRight, this.colorGreenTopRight, this.colorBlueTopRight);
			tessellator9.func_35835_b(this.field_35948_an);
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
			d12 = (double)(((float)i10 + 0.0F) / 256.0F);
			d14 = (double)(((float)i10 + 15.99F) / 256.0F);
		}

		if(block1.minY < 0.0D || block1.maxY > 1.0D) {
			d16 = (double)(((float)i11 + 0.0F) / 256.0F);
			d18 = (double)(((float)i11 + 15.99F) / 256.0F);
		}

		d20 = d14;
		double d22 = d12;
		double d24 = d16;
		double d26 = d18;
		if(this.uvRotateEast == 2) {
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
		} else if(this.uvRotateEast == 1) {
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
		} else if(this.uvRotateEast == 3) {
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
			tessellator9.func_35835_b(this.field_35943_ak);
			tessellator9.addVertexWithUV(d28, d34, d36, d20, d24);
			tessellator9.setColorOpaque_F(this.colorRedBottomLeft, this.colorGreenBottomLeft, this.colorBlueBottomLeft);
			tessellator9.func_35835_b(this.field_35944_al);
			tessellator9.addVertexWithUV(d30, d34, d36, d12, d16);
			tessellator9.setColorOpaque_F(this.colorRedBottomRight, this.colorGreenBottomRight, this.colorBlueBottomRight);
			tessellator9.func_35835_b(this.field_35947_am);
			tessellator9.addVertexWithUV(d30, d32, d36, d22, d26);
			tessellator9.setColorOpaque_F(this.colorRedTopRight, this.colorGreenTopRight, this.colorBlueTopRight);
			tessellator9.func_35835_b(this.field_35948_an);
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
			d12 = (double)(((float)i10 + 0.0F) / 256.0F);
			d14 = (double)(((float)i10 + 15.99F) / 256.0F);
		}

		if(block1.minY < 0.0D || block1.maxY > 1.0D) {
			d16 = (double)(((float)i11 + 0.0F) / 256.0F);
			d18 = (double)(((float)i11 + 15.99F) / 256.0F);
		}

		d20 = d14;
		double d22 = d12;
		double d24 = d16;
		double d26 = d18;
		if(this.uvRotateWest == 1) {
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
		} else if(this.uvRotateWest == 2) {
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
		} else if(this.uvRotateWest == 3) {
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
			tessellator9.func_35835_b(this.field_35943_ak);
			tessellator9.addVertexWithUV(d28, d34, d36, d12, d16);
			tessellator9.setColorOpaque_F(this.colorRedBottomLeft, this.colorGreenBottomLeft, this.colorBlueBottomLeft);
			tessellator9.func_35835_b(this.field_35944_al);
			tessellator9.addVertexWithUV(d28, d32, d36, d22, d26);
			tessellator9.setColorOpaque_F(this.colorRedBottomRight, this.colorGreenBottomRight, this.colorBlueBottomRight);
			tessellator9.func_35835_b(this.field_35947_am);
			tessellator9.addVertexWithUV(d30, d32, d36, d14, d18);
			tessellator9.setColorOpaque_F(this.colorRedTopRight, this.colorGreenTopRight, this.colorBlueTopRight);
			tessellator9.func_35835_b(this.field_35948_an);
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
			d12 = (double)(((float)i10 + 0.0F) / 256.0F);
			d14 = (double)(((float)i10 + 15.99F) / 256.0F);
		}

		if(block1.minY < 0.0D || block1.maxY > 1.0D) {
			d16 = (double)(((float)i11 + 0.0F) / 256.0F);
			d18 = (double)(((float)i11 + 15.99F) / 256.0F);
		}

		d20 = d14;
		double d22 = d12;
		double d24 = d16;
		double d26 = d18;
		if(this.uvRotateNorth == 1) {
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
		} else if(this.uvRotateNorth == 2) {
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
		} else if(this.uvRotateNorth == 3) {
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
			tessellator9.func_35835_b(this.field_35943_ak);
			tessellator9.addVertexWithUV(d28, d32, d36, d20, d24);
			tessellator9.setColorOpaque_F(this.colorRedBottomLeft, this.colorGreenBottomLeft, this.colorBlueBottomLeft);
			tessellator9.func_35835_b(this.field_35944_al);
			tessellator9.addVertexWithUV(d28, d32, d34, d12, d16);
			tessellator9.setColorOpaque_F(this.colorRedBottomRight, this.colorGreenBottomRight, this.colorBlueBottomRight);
			tessellator9.func_35835_b(this.field_35947_am);
			tessellator9.addVertexWithUV(d28, d30, d34, d22, d26);
			tessellator9.setColorOpaque_F(this.colorRedTopRight, this.colorGreenTopRight, this.colorBlueTopRight);
			tessellator9.func_35835_b(this.field_35948_an);
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
			d12 = (double)(((float)i10 + 0.0F) / 256.0F);
			d14 = (double)(((float)i10 + 15.99F) / 256.0F);
		}

		if(block1.minY < 0.0D || block1.maxY > 1.0D) {
			d16 = (double)(((float)i11 + 0.0F) / 256.0F);
			d18 = (double)(((float)i11 + 15.99F) / 256.0F);
		}

		d20 = d14;
		double d22 = d12;
		double d24 = d16;
		double d26 = d18;
		if(this.uvRotateSouth == 2) {
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
		} else if(this.uvRotateSouth == 1) {
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
		} else if(this.uvRotateSouth == 3) {
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
			tessellator9.func_35835_b(this.field_35943_ak);
			tessellator9.addVertexWithUV(d28, d30, d36, d22, d26);
			tessellator9.setColorOpaque_F(this.colorRedBottomLeft, this.colorGreenBottomLeft, this.colorBlueBottomLeft);
			tessellator9.func_35835_b(this.field_35944_al);
			tessellator9.addVertexWithUV(d28, d30, d34, d14, d18);
			tessellator9.setColorOpaque_F(this.colorRedBottomRight, this.colorGreenBottomRight, this.colorBlueBottomRight);
			tessellator9.func_35835_b(this.field_35947_am);
			tessellator9.addVertexWithUV(d28, d32, d34, d20, d24);
			tessellator9.setColorOpaque_F(this.colorRedTopRight, this.colorGreenTopRight, this.colorBlueTopRight);
			tessellator9.func_35835_b(this.field_35948_an);
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
		boolean z5 = block1.blockID == Block.grass.blockID;
		int i6;
		float f7;
		float f8;
		float f9;
		if(this.useInventoryTint) {
			i6 = block1.getRenderColor(i2);
			if(z5) {
				i6 = 0xFFFFFF;
			}

			f7 = (float)(i6 >> 16 & 255) / 255.0F;
			f8 = (float)(i6 >> 8 & 255) / 255.0F;
			f9 = (float)(i6 & 255) / 255.0F;
			GL11.glColor4f(f7 * f3, f8 * f3, f9 * f3, 1.0F);
		}

		i6 = block1.getRenderType();
		int i11;
		if(i6 != 0 && i6 != 16) {
			if(i6 == 1) {
				tessellator4.startDrawingQuads();
				tessellator4.setNormal(0.0F, -1.0F, 0.0F);
				this.renderCrossedSquares(block1, i2, -0.5D, -0.5D, -0.5D);
				tessellator4.draw();
			} else if(i6 == 19) {
				tessellator4.startDrawingQuads();
				tessellator4.setNormal(0.0F, -1.0F, 0.0F);
				block1.setBlockBoundsForItemRender();
				this.func_35928_a(block1, i2, block1.maxY, -0.5D, -0.5D, -0.5D);
				tessellator4.draw();
			} else if(i6 == 13) {
				block1.setBlockBoundsForItemRender();
				GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
				f7 = 0.0625F;
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
				tessellator4.setTranslationF(0.0F, 0.0F, f7);
				this.renderEastFace(block1, 0.0D, 0.0D, 0.0D, block1.getBlockTextureFromSide(2));
				tessellator4.setTranslationF(0.0F, 0.0F, -f7);
				tessellator4.draw();
				tessellator4.startDrawingQuads();
				tessellator4.setNormal(0.0F, 0.0F, 1.0F);
				tessellator4.setTranslationF(0.0F, 0.0F, -f7);
				this.renderWestFace(block1, 0.0D, 0.0D, 0.0D, block1.getBlockTextureFromSide(3));
				tessellator4.setTranslationF(0.0F, 0.0F, f7);
				tessellator4.draw();
				tessellator4.startDrawingQuads();
				tessellator4.setNormal(-1.0F, 0.0F, 0.0F);
				tessellator4.setTranslationF(f7, 0.0F, 0.0F);
				this.renderNorthFace(block1, 0.0D, 0.0D, 0.0D, block1.getBlockTextureFromSide(4));
				tessellator4.setTranslationF(-f7, 0.0F, 0.0F);
				tessellator4.draw();
				tessellator4.startDrawingQuads();
				tessellator4.setNormal(1.0F, 0.0F, 0.0F);
				tessellator4.setTranslationF(-f7, 0.0F, 0.0F);
				this.renderSouthFace(block1, 0.0D, 0.0D, 0.0D, block1.getBlockTextureFromSide(5));
				tessellator4.setTranslationF(f7, 0.0F, 0.0F);
				tessellator4.draw();
				GL11.glTranslatef(0.5F, 0.5F, 0.5F);
			} else if(i6 == 22) {
				ChestItemRenderHelper.field_35611_a.func_35609_a(block1, i2, f3);
				GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			} else if(i6 == 6) {
				tessellator4.startDrawingQuads();
				tessellator4.setNormal(0.0F, -1.0F, 0.0F);
				this.func_1245_b(block1, i2, -0.5D, -0.5D, -0.5D);
				tessellator4.draw();
			} else if(i6 == 2) {
				tessellator4.startDrawingQuads();
				tessellator4.setNormal(0.0F, -1.0F, 0.0F);
				this.renderTorchAtAngle(block1, -0.5D, -0.5D, -0.5D, 0.0D, 0.0D);
				tessellator4.draw();
			} else if(i6 == 10) {
				for(i11 = 0; i11 < 2; ++i11) {
					if(i11 == 0) {
						block1.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.5F);
					}

					if(i11 == 1) {
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
			} else if(i6 == 11) {
				for(i11 = 0; i11 < 4; ++i11) {
					f8 = 0.125F;
					if(i11 == 0) {
						block1.setBlockBounds(0.5F - f8, 0.0F, 0.0F, 0.5F + f8, 1.0F, f8 * 2.0F);
					}

					if(i11 == 1) {
						block1.setBlockBounds(0.5F - f8, 0.0F, 1.0F - f8 * 2.0F, 0.5F + f8, 1.0F, 1.0F);
					}

					f8 = 0.0625F;
					if(i11 == 2) {
						block1.setBlockBounds(0.5F - f8, 1.0F - f8 * 3.0F, -f8 * 2.0F, 0.5F + f8, 1.0F - f8, 1.0F + f8 * 2.0F);
					}

					if(i11 == 3) {
						block1.setBlockBounds(0.5F - f8, 0.5F - f8 * 3.0F, -f8 * 2.0F, 0.5F + f8, 0.5F - f8, 1.0F + f8 * 2.0F);
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
			} else if(i6 == 21) {
				for(i11 = 0; i11 < 3; ++i11) {
					f8 = 0.0625F;
					if(i11 == 0) {
						block1.setBlockBounds(0.5F - f8, 0.3F, 0.0F, 0.5F + f8, 1.0F, f8 * 2.0F);
					}

					if(i11 == 1) {
						block1.setBlockBounds(0.5F - f8, 0.3F, 1.0F - f8 * 2.0F, 0.5F + f8, 1.0F, 1.0F);
					}

					f8 = 0.0625F;
					if(i11 == 2) {
						block1.setBlockBounds(0.5F - f8, 0.5F, 0.0F, 0.5F + f8, 1.0F - f8, 1.0F);
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
		} else {
			if(i6 == 16) {
				i2 = 1;
			}

			block1.setBlockBoundsForItemRender();
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
			tessellator4.startDrawingQuads();
			tessellator4.setNormal(0.0F, -1.0F, 0.0F);
			this.renderBottomFace(block1, 0.0D, 0.0D, 0.0D, block1.getBlockTextureFromSideAndMetadata(0, i2));
			tessellator4.draw();
			if(z5 && this.useInventoryTint) {
				i11 = block1.getRenderColor(i2);
				f8 = (float)(i11 >> 16 & 255) / 255.0F;
				f9 = (float)(i11 >> 8 & 255) / 255.0F;
				float f10 = (float)(i11 & 255) / 255.0F;
				GL11.glColor4f(f8 * f3, f9 * f3, f10 * f3, 1.0F);
			}

			tessellator4.startDrawingQuads();
			tessellator4.setNormal(0.0F, 1.0F, 0.0F);
			this.renderTopFace(block1, 0.0D, 0.0D, 0.0D, block1.getBlockTextureFromSideAndMetadata(1, i2));
			tessellator4.draw();
			if(z5 && this.useInventoryTint) {
				GL11.glColor4f(f3, f3, f3, 1.0F);
			}

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
		return i0 == 0 ? true : (i0 == 13 ? true : (i0 == 10 ? true : (i0 == 11 ? true : (i0 == 22 ? true : (i0 == 21 ? true : i0 == 16)))));
	}
}
