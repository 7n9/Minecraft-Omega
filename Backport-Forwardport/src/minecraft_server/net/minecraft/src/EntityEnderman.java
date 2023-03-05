package net.minecraft.src;

public class EntityEnderman extends EntityMob {
	private static boolean[] field_35234_b = new boolean[256];
	public boolean field_35235_a = false;
	private int field_35233_g = 0;
	private int field_35236_h = 0;

	public EntityEnderman(World world1) {
		super(world1);
		this.texture = "/mob/enderman.png";
		this.moveSpeed = 0.2F;
		this.attackStrength = 5;
		this.setSize(0.6F, 2.9F);
		this.stepHeight = 1.0F;
	}

	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(16, new Byte((byte)0));
		this.dataWatcher.addObject(17, new Byte((byte)0));
	}

	public void writeEntityToNBT(NBTTagCompound nBTTagCompound1) {
		super.writeEntityToNBT(nBTTagCompound1);
		nBTTagCompound1.setShort("carried", (short)this.func_35225_x());
		nBTTagCompound1.setShort("carriedData", (short)this.func_35231_y());
	}

	public void readEntityFromNBT(NBTTagCompound nBTTagCompound1) {
		super.readEntityFromNBT(nBTTagCompound1);
		this.func_35226_b(nBTTagCompound1.getShort("carried"));
		this.func_35229_d(nBTTagCompound1.getShort("carryingData"));
	}

	protected Entity findPlayerToAttack() {
		EntityPlayer entityPlayer1 = this.worldObj.getClosestPlayerToEntity(this, 64.0D);
		if(entityPlayer1 != null) {
			if(this.func_35232_c(entityPlayer1)) {
				if(this.field_35236_h++ == 5) {
					this.field_35236_h = 0;
					return entityPlayer1;
				}
			} else {
				this.field_35236_h = 0;
			}
		}

		return null;
	}

	public float getEntityBrightness(float f1) {
		return super.getEntityBrightness(f1);
	}

	private boolean func_35232_c(EntityPlayer entityPlayer1) {
		ItemStack itemStack2 = entityPlayer1.inventory.armorInventory[3];
		if(itemStack2 != null && itemStack2.itemID == Block.pumpkin.blockID) {
			return false;
		} else {
			Vec3D vec3D3 = entityPlayer1.getLook(1.0F).normalize();
			Vec3D vec3D4 = Vec3D.createVector(this.posX - entityPlayer1.posX, this.boundingBox.minY + (double)(this.height / 2.0F) - entityPlayer1.posY + (double)entityPlayer1.getEyeHeight(), this.posZ - entityPlayer1.posZ);
			double d5 = vec3D4.lengthVector();
			vec3D4 = vec3D4.normalize();
			double d7 = vec3D3.func_35570_a(vec3D4);
			return d7 > 1.0D - 0.025D / d5 ? entityPlayer1.canEntityBeSeen(this) : false;
		}
	}

	public void onLivingUpdate() {
		if(this.isWet()) {
			this.attackEntityFrom(DamageSource.field_35088_e, 1);
		}

		this.field_35235_a = this.entityToAttack != null;
		this.moveSpeed = this.entityToAttack != null ? 4.5F : 0.3F;
		int i1;
		if(!this.worldObj.singleplayerWorld) {
			int i2;
			int i3;
			int i4;
			if(this.func_35225_x() == 0) {
				if(this.rand.nextInt(20) == 0) {
					i1 = MathHelper.floor_double(this.posX - 2.0D + this.rand.nextDouble() * 4.0D);
					i2 = MathHelper.floor_double(this.posY + this.rand.nextDouble() * 3.0D);
					i3 = MathHelper.floor_double(this.posZ - 2.0D + this.rand.nextDouble() * 4.0D);
					i4 = this.worldObj.getBlockId(i1, i2, i3);
					if(field_35234_b[i4]) {
						this.func_35226_b(this.worldObj.getBlockId(i1, i2, i3));
						this.func_35229_d(this.worldObj.getBlockMetadata(i1, i2, i3));
						this.worldObj.setBlockWithNotify(i1, i2, i3, 0);
					}
				}
			} else if(this.rand.nextInt(2000) == 0) {
				i1 = MathHelper.floor_double(this.posX - 1.0D + this.rand.nextDouble() * 2.0D);
				i2 = MathHelper.floor_double(this.posY + this.rand.nextDouble() * 2.0D);
				i3 = MathHelper.floor_double(this.posZ - 1.0D + this.rand.nextDouble() * 2.0D);
				i4 = this.worldObj.getBlockId(i1, i2, i3);
				int i5 = this.worldObj.getBlockId(i1, i2 - 1, i3);
				if(i4 == 0 && i5 > 0 && Block.blocksList[i5].isACube()) {
					this.worldObj.setBlockAndMetadataWithNotify(i1, i2, i3, this.func_35225_x(), this.func_35231_y());
					this.func_35226_b(0);
				}
			}
		}

		for(i1 = 0; i1 < 2; ++i1) {
			this.worldObj.spawnParticle("portal", this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height - 0.25D, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, (this.rand.nextDouble() - 0.5D) * 2.0D, -this.rand.nextDouble(), (this.rand.nextDouble() - 0.5D) * 2.0D);
		}

		if(this.worldObj.isDaytime() && !this.worldObj.singleplayerWorld) {
			float f6 = this.getEntityBrightness(1.0F);
			if(f6 > 0.5F && this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)) && this.rand.nextFloat() * 30.0F < (f6 - 0.4F) * 2.0F) {
				this.fire = 300;
			}
		}

		this.isJumping = false;
		if(this.entityToAttack != null) {
			this.faceEntity(this.entityToAttack, 100.0F, 100.0F);
		}

		if(!this.worldObj.singleplayerWorld) {
			if(this.entityToAttack != null) {
				if(this.entityToAttack instanceof EntityPlayer && this.func_35232_c((EntityPlayer)this.entityToAttack)) {
					this.moveStrafing = this.moveForward = 0.0F;
					this.moveSpeed = 0.0F;
					if(this.entityToAttack.getDistanceSqToEntity(this) < 16.0D) {
						this.func_35227_w();
					}

					this.field_35233_g = 0;
				} else if(this.entityToAttack.getDistanceSqToEntity(this) > 256.0D && this.field_35233_g++ >= 30 && this.func_35230_e(this.entityToAttack)) {
					this.field_35233_g = 0;
				}
			} else {
				this.field_35233_g = 0;
			}
		}

		super.onLivingUpdate();
	}

	protected boolean func_35227_w() {
		double d1 = this.posX + (this.rand.nextDouble() - 0.5D) * 64.0D;
		double d3 = this.posY + (double)(this.rand.nextInt(64) - 32);
		double d5 = this.posZ + (this.rand.nextDouble() - 0.5D) * 64.0D;
		return this.func_35228_a(d1, d3, d5);
	}

	protected boolean func_35230_e(Entity entity1) {
		Vec3D vec3D2 = Vec3D.createVector(this.posX - entity1.posX, this.boundingBox.minY + (double)(this.height / 2.0F) - entity1.posY + (double)entity1.getEyeHeight(), this.posZ - entity1.posZ);
		vec3D2 = vec3D2.normalize();
		double d3 = 16.0D;
		double d5 = this.posX + (this.rand.nextDouble() - 0.5D) * 8.0D - vec3D2.xCoord * d3;
		double d7 = this.posY + (double)(this.rand.nextInt(16) - 8) - vec3D2.yCoord * d3;
		double d9 = this.posZ + (this.rand.nextDouble() - 0.5D) * 8.0D - vec3D2.zCoord * d3;
		return this.func_35228_a(d5, d7, d9);
	}

	protected boolean func_35228_a(double d1, double d3, double d5) {
		double d7 = this.posX;
		double d9 = this.posY;
		double d11 = this.posZ;
		this.posX = d1;
		this.posY = d3;
		this.posZ = d5;
		boolean z13 = false;
		int i14 = MathHelper.floor_double(this.posX);
		int i15 = MathHelper.floor_double(this.posY);
		int i16 = MathHelper.floor_double(this.posZ);
		int i18;
		if(this.worldObj.blockExists(i14, i15, i16)) {
			boolean z17 = false;

			while(true) {
				while(!z17 && i15 > 0) {
					i18 = this.worldObj.getBlockId(i14, i15 - 1, i16);
					if(i18 != 0 && Block.blocksList[i18].blockMaterial.getIsSolid()) {
						z17 = true;
					} else {
						--this.posY;
						--i15;
					}
				}

				if(z17) {
					this.setPosition(this.posX, this.posY, this.posZ);
					if(this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).size() == 0 && !this.worldObj.getIsAnyLiquid(this.boundingBox)) {
						z13 = true;
					}
				}
				break;
			}
		}

		if(!z13) {
			this.setPosition(d7, d9, d11);
			return false;
		} else {
			short s30 = 128;

			for(i18 = 0; i18 < s30; ++i18) {
				double d19 = (double)i18 / ((double)s30 - 1.0D);
				float f21 = (this.rand.nextFloat() - 0.5F) * 0.2F;
				float f22 = (this.rand.nextFloat() - 0.5F) * 0.2F;
				float f23 = (this.rand.nextFloat() - 0.5F) * 0.2F;
				double d24 = d7 + (this.posX - d7) * d19 + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.0D;
				double d26 = d9 + (this.posY - d9) * d19 + this.rand.nextDouble() * (double)this.height;
				double d28 = d11 + (this.posZ - d11) * d19 + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.0D;
				this.worldObj.spawnParticle("portal", d24, d26, d28, (double)f21, (double)f22, (double)f23);
			}

			return true;
		}
	}

	protected String getLivingSound() {
		return "mob.zombie";
	}

	protected String getHurtSound() {
		return "mob.zombiehurt";
	}

	protected String getDeathSound() {
		return "mob.zombiedeath";
	}

	protected int getDropItemId() {
		return Item.field_35411_bl.shiftedIndex;
	}

	protected void dropFewItems(boolean z1) {
		int i2 = this.getDropItemId();
		if(i2 > 0) {
			int i3 = this.rand.nextInt(2);

			for(int i4 = 0; i4 < i3; ++i4) {
				this.dropItem(i2, 1);
			}
		}

	}

	public void func_35226_b(int i1) {
		this.dataWatcher.updateObject(16, (byte)(i1 & 255));
	}

	public int func_35225_x() {
		return this.dataWatcher.getWatchableObjectByte(16);
	}

	public void func_35229_d(int i1) {
		this.dataWatcher.updateObject(17, (byte)(i1 & 255));
	}

	public int func_35231_y() {
		return this.dataWatcher.getWatchableObjectByte(17);
	}

	static {
		field_35234_b[Block.stone.blockID] = true;
		field_35234_b[Block.grass.blockID] = true;
		field_35234_b[Block.dirt.blockID] = true;
		field_35234_b[Block.cobblestone.blockID] = true;
		field_35234_b[Block.planks.blockID] = true;
		field_35234_b[Block.sand.blockID] = true;
		field_35234_b[Block.gravel.blockID] = true;
		field_35234_b[Block.oreGold.blockID] = true;
		field_35234_b[Block.oreIron.blockID] = true;
		field_35234_b[Block.oreCoal.blockID] = true;
		field_35234_b[Block.wood.blockID] = true;
		field_35234_b[Block.leaves.blockID] = true;
		field_35234_b[Block.sponge.blockID] = true;
		field_35234_b[Block.glass.blockID] = true;
		field_35234_b[Block.oreLapis.blockID] = true;
		field_35234_b[Block.blockLapis.blockID] = true;
		field_35234_b[Block.sandStone.blockID] = true;
		field_35234_b[Block.cloth.blockID] = true;
		field_35234_b[Block.plantYellow.blockID] = true;
		field_35234_b[Block.plantRed.blockID] = true;
		field_35234_b[Block.mushroomBrown.blockID] = true;
		field_35234_b[Block.mushroomRed.blockID] = true;
		field_35234_b[Block.blockGold.blockID] = true;
		field_35234_b[Block.blockSteel.blockID] = true;
		field_35234_b[Block.brick.blockID] = true;
		field_35234_b[Block.tnt.blockID] = true;
		field_35234_b[Block.bookShelf.blockID] = true;
		field_35234_b[Block.cobblestoneMossy.blockID] = true;
		field_35234_b[Block.oreDiamond.blockID] = true;
		field_35234_b[Block.blockDiamond.blockID] = true;
		field_35234_b[Block.workbench.blockID] = true;
		field_35234_b[Block.oreRedstone.blockID] = true;
		field_35234_b[Block.oreRedstoneGlowing.blockID] = true;
		field_35234_b[Block.ice.blockID] = true;
		field_35234_b[Block.cactus.blockID] = true;
		field_35234_b[Block.blockClay.blockID] = true;
		field_35234_b[Block.pumpkin.blockID] = true;
		field_35234_b[Block.netherrack.blockID] = true;
		field_35234_b[Block.slowSand.blockID] = true;
		field_35234_b[Block.glowStone.blockID] = true;
		field_35234_b[Block.pumpkinLantern.blockID] = true;
		field_35234_b[Block.field_35052_bn.blockID] = true;
		field_35234_b[Block.field_35053_bo.blockID] = true;
		field_35234_b[Block.field_35054_bp.blockID] = true;
		field_35234_b[Block.field_35048_bs.blockID] = true;
	}
}
