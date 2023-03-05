package net.minecraft.src;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public abstract class EntityLiving extends Entity {
	public int heartsHalvesLife = 20;
	public float field_9365_p;
	public float field_9363_r;
	public float renderYawOffset = 0.0F;
	public float prevRenderYawOffset = 0.0F;
	protected float field_9362_u;
	protected float field_9361_v;
	protected float field_9360_w;
	protected float field_9359_x;
	protected boolean field_9358_y = true;
	protected String texture = "/mob/char.png";
	protected boolean field_9355_A = true;
	protected float field_9353_B = 0.0F;
	protected String entityType = null;
	protected float field_9349_D = 1.0F;
	protected int scoreValue = 0;
	protected float field_9345_F = 0.0F;
	public boolean isMultiplayerEntity = false;
	public float field_35169_bv = 0.1F;
	public float field_35168_bw = 0.02F;
	public float prevSwingProgress;
	public float swingProgress;
	public int health = 10;
	public int prevHealth;
	private int livingSoundTime;
	public int hurtTime;
	public int maxHurtTime;
	public float attackedAtYaw = 0.0F;
	public int deathTime = 0;
	public int attackTime = 0;
	public float prevCameraPitch;
	public float cameraPitch;
	protected boolean unused_flag = false;
	protected int field_35171_bJ;
	public int field_9326_T = -1;
	public float field_9325_U = (float)(Math.random() * (double)0.9F + (double)0.1F);
	public float field_705_Q;
	public float field_704_R;
	public float field_703_S;
	private EntityPlayer field_34904_b = null;
	private int field_34905_c = 0;
	public int field_35172_bP = 0;
	public int field_35173_bQ = 0;
	protected HashMap field_35170_bR = new HashMap();
	protected int newPosRotationIncrements;
	protected double newPosX;
	protected double newPosY;
	protected double newPosZ;
	protected double newRotationYaw;
	protected double newRotationPitch;
	float field_9348_ae = 0.0F;
	protected int field_9346_af = 0;
	protected int entityAge = 0;
	protected float moveStrafing;
	protected float moveForward;
	protected float randomYawVelocity;
	protected boolean isJumping = false;
	protected float defaultPitch = 0.0F;
	protected float moveSpeed = 0.7F;
	private Entity currentTarget;
	protected int numTicksToChaseTarget = 0;

	public EntityLiving(World world1) {
		super(world1);
		this.preventEntitySpawning = true;
		this.field_9363_r = (float)(Math.random() + 1.0D) * 0.01F;
		this.setPosition(this.posX, this.posY, this.posZ);
		this.field_9365_p = (float)Math.random() * 12398.0F;
		this.rotationYaw = (float)(Math.random() * (double)(float)Math.PI * 2.0D);
		this.stepHeight = 0.5F;
	}

	protected void entityInit() {
	}

	public boolean canEntityBeSeen(Entity entity1) {
		return this.worldObj.rayTraceBlocks(Vec3D.createVector(this.posX, this.posY + (double)this.getEyeHeight(), this.posZ), Vec3D.createVector(entity1.posX, entity1.posY + (double)entity1.getEyeHeight(), entity1.posZ)) == null;
	}

	public String getEntityTexture() {
		return this.texture;
	}

	public boolean canBeCollidedWith() {
		return !this.isDead;
	}

	public boolean canBePushed() {
		return !this.isDead;
	}

	public float getEyeHeight() {
		return this.height * 0.85F;
	}

	public int getTalkInterval() {
		return 80;
	}

	public void playLivingSound() {
		String string1 = this.getLivingSound();
		if(string1 != null) {
			this.worldObj.playSoundAtEntity(this, string1, this.getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
		}

	}

	public void onEntityUpdate() {
		this.prevSwingProgress = this.swingProgress;
		super.onEntityUpdate();
		if(this.rand.nextInt(1000) < this.livingSoundTime++) {
			this.livingSoundTime = -this.getTalkInterval();
			this.playLivingSound();
		}

		if(this.isEntityAlive() && this.isEntityInsideOpaqueBlock()) {
			this.attackEntityFrom(DamageSource.field_35538_d, 1);
		}

		if(this.isImmuneToFire || this.worldObj.multiplayerWorld) {
			this.fire = 0;
		}

		int i1;
		if(this.isEntityAlive() && this.isInsideOfMaterial(Material.water) && !this.canBreatheUnderwater() && !this.field_35170_bR.containsKey(Potion.field_35680_o.field_35670_H)) {
			--this.air;
			if(this.air == -20) {
				this.air = 0;

				for(i1 = 0; i1 < 8; ++i1) {
					float f2 = this.rand.nextFloat() - this.rand.nextFloat();
					float f3 = this.rand.nextFloat() - this.rand.nextFloat();
					float f4 = this.rand.nextFloat() - this.rand.nextFloat();
					this.worldObj.spawnParticle("bubble", this.posX + (double)f2, this.posY + (double)f3, this.posZ + (double)f4, this.motionX, this.motionY, this.motionZ);
				}

				this.attackEntityFrom(DamageSource.field_35539_e, 2);
			}

			this.fire = 0;
		} else {
			this.air = this.maxAir;
		}

		this.prevCameraPitch = this.cameraPitch;
		if(this.attackTime > 0) {
			--this.attackTime;
		}

		if(this.hurtTime > 0) {
			--this.hurtTime;
		}

		if(this.heartsLife > 0) {
			--this.heartsLife;
		}

		if(this.health <= 0) {
			++this.deathTime;
			if(this.deathTime > 20) {
				if(this.field_34905_c > 0 || this.func_35163_av()) {
					i1 = this.a(this.field_34904_b);

					while(i1 > 0) {
						int i8 = EntityXPOrb.func_35121_b(i1);
						i1 -= i8;
						this.worldObj.entityJoinedWorld(new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, i8));
					}
				}

				this.onEntityDeath();
				this.setEntityDead();

				for(i1 = 0; i1 < 20; ++i1) {
					double d9 = this.rand.nextGaussian() * 0.02D;
					double d10 = this.rand.nextGaussian() * 0.02D;
					double d6 = this.rand.nextGaussian() * 0.02D;
					this.worldObj.spawnParticle("explode", this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d9, d10, d6);
				}
			}
		}

		if(this.field_34905_c > 0) {
			--this.field_34905_c;
		} else {
			this.field_34904_b = null;
		}

		this.dropFewItems();
		this.field_9359_x = this.field_9360_w;
		this.prevRenderYawOffset = this.renderYawOffset;
		this.prevRotationYaw = this.rotationYaw;
		this.prevRotationPitch = this.rotationPitch;
	}

	protected int a(EntityPlayer entityPlayer1) {
		return this.field_35171_bJ;
	}

	protected boolean func_35163_av() {
		return false;
	}

	public void spawnExplosionParticle() {
		for(int i1 = 0; i1 < 20; ++i1) {
			double d2 = this.rand.nextGaussian() * 0.02D;
			double d4 = this.rand.nextGaussian() * 0.02D;
			double d6 = this.rand.nextGaussian() * 0.02D;
			double d8 = 10.0D;
			this.worldObj.spawnParticle("explode", this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width - d2 * d8, this.posY + (double)(this.rand.nextFloat() * this.height) - d4 * d8, this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width - d6 * d8, d2, d4, d6);
		}

	}

	public void updateRidden() {
		super.updateRidden();
		this.field_9362_u = this.field_9361_v;
		this.field_9361_v = 0.0F;
	}

	public void setPositionAndRotation2(double d1, double d3, double d5, float f7, float f8, int i9) {
		this.yOffset = 0.0F;
		this.newPosX = d1;
		this.newPosY = d3;
		this.newPosZ = d5;
		this.newRotationYaw = (double)f7;
		this.newRotationPitch = (double)f8;
		this.newPosRotationIncrements = i9;
	}

	public void onUpdate() {
		super.onUpdate();
		if(this.field_35172_bP > 0) {
			if(this.field_35173_bQ <= 0) {
				this.field_35173_bQ = 60;
			}

			--this.field_35173_bQ;
			if(this.field_35173_bQ <= 0) {
				--this.field_35172_bP;
			}
		}

		this.onLivingUpdate();
		double d1 = this.posX - this.prevPosX;
		double d3 = this.posZ - this.prevPosZ;
		float f5 = MathHelper.sqrt_double(d1 * d1 + d3 * d3);
		float f6 = this.renderYawOffset;
		float f7 = 0.0F;
		this.field_9362_u = this.field_9361_v;
		float f8 = 0.0F;
		if(f5 > 0.05F) {
			f8 = 1.0F;
			f7 = f5 * 3.0F;
			f6 = (float)Math.atan2(d3, d1) * 180.0F / (float)Math.PI - 90.0F;
		}

		if(this.swingProgress > 0.0F) {
			f6 = this.rotationYaw;
		}

		if(!this.onGround) {
			f8 = 0.0F;
		}

		this.field_9361_v += (f8 - this.field_9361_v) * 0.3F;

		float f9;
		for(f9 = f6 - this.renderYawOffset; f9 < -180.0F; f9 += 360.0F) {
		}

		while(f9 >= 180.0F) {
			f9 -= 360.0F;
		}

		this.renderYawOffset += f9 * 0.3F;

		float f10;
		for(f10 = this.rotationYaw - this.renderYawOffset; f10 < -180.0F; f10 += 360.0F) {
		}

		while(f10 >= 180.0F) {
			f10 -= 360.0F;
		}

		boolean z11 = f10 < -90.0F || f10 >= 90.0F;
		if(f10 < -75.0F) {
			f10 = -75.0F;
		}

		if(f10 >= 75.0F) {
			f10 = 75.0F;
		}

		this.renderYawOffset = this.rotationYaw - f10;
		if(f10 * f10 > 2500.0F) {
			this.renderYawOffset += f10 * 0.2F;
		}

		if(z11) {
			f7 *= -1.0F;
		}

		while(this.rotationYaw - this.prevRotationYaw < -180.0F) {
			this.prevRotationYaw -= 360.0F;
		}

		while(this.rotationYaw - this.prevRotationYaw >= 180.0F) {
			this.prevRotationYaw += 360.0F;
		}

		while(this.renderYawOffset - this.prevRenderYawOffset < -180.0F) {
			this.prevRenderYawOffset -= 360.0F;
		}

		while(this.renderYawOffset - this.prevRenderYawOffset >= 180.0F) {
			this.prevRenderYawOffset += 360.0F;
		}

		while(this.rotationPitch - this.prevRotationPitch < -180.0F) {
			this.prevRotationPitch -= 360.0F;
		}

		while(this.rotationPitch - this.prevRotationPitch >= 180.0F) {
			this.prevRotationPitch += 360.0F;
		}

		this.field_9360_w += f7;
	}

	protected void setSize(float f1, float f2) {
		super.setSize(f1, f2);
	}

	public void heal(int i1) {
		if(this.health > 0) {
			this.health += i1;
			if(this.health > 20) {
				this.health = 20;
			}

			this.heartsLife = this.heartsHalvesLife / 2;
		}
	}

	public boolean attackEntityFrom(DamageSource damageSource1, int i2) {
		if(this.worldObj.multiplayerWorld) {
			return false;
		} else {
			this.entityAge = 0;
			if(this.health <= 0) {
				return false;
			} else {
				this.field_704_R = 1.5F;
				boolean z3 = true;
				if((float)this.heartsLife > (float)this.heartsHalvesLife / 2.0F) {
					if(i2 <= this.field_9346_af) {
						return false;
					}

					this.b(damageSource1, i2 - this.field_9346_af);
					this.field_9346_af = i2;
					z3 = false;
				} else {
					this.field_9346_af = i2;
					this.prevHealth = this.health;
					this.heartsLife = this.heartsHalvesLife;
					this.b(damageSource1, i2);
					this.hurtTime = this.maxHurtTime = 10;
				}

				this.attackedAtYaw = 0.0F;
				Entity entity4 = damageSource1.func_35532_a();
				if(entity4 != null) {
					if(entity4 instanceof EntityPlayer) {
						this.field_34905_c = 60;
						this.field_34904_b = (EntityPlayer)entity4;
					} else if(entity4 instanceof EntityWolf) {
						EntityWolf entityWolf5 = (EntityWolf)entity4;
						if(entityWolf5.isWolfTamed()) {
							this.field_34905_c = 60;
							this.field_34904_b = null;
						}
					}
				}

				if(z3) {
					this.worldObj.setEntityState(this, (byte)2);
					this.setBeenAttacked();
					if(entity4 != null) {
						double d9 = entity4.posX - this.posX;

						double d7;
						for(d7 = entity4.posZ - this.posZ; d9 * d9 + d7 * d7 < 1.0E-4D; d7 = (Math.random() - Math.random()) * 0.01D) {
							d9 = (Math.random() - Math.random()) * 0.01D;
						}

						this.attackedAtYaw = (float)(Math.atan2(d7, d9) * 180.0D / (double)(float)Math.PI) - this.rotationYaw;
						this.knockBack(entity4, i2, d9, d7);
					} else {
						this.attackedAtYaw = (float)((int)(Math.random() * 2.0D) * 180);
					}
				}

				if(this.health <= 0) {
					if(z3) {
						this.worldObj.playSoundAtEntity(this, this.getDeathSound(), this.getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
					}

					this.onDeath(damageSource1);
				} else if(z3) {
					this.worldObj.playSoundAtEntity(this, this.getHurtSound(), this.getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
				}

				return true;
			}
		}
	}

	public void performHurtAnimation() {
		this.hurtTime = this.maxHurtTime = 10;
		this.attackedAtYaw = 0.0F;
	}

	protected void b(DamageSource damageSource1, int i2) {
		this.health -= i2;
	}

	protected float getSoundVolume() {
		return 1.0F;
	}

	protected String getLivingSound() {
		return null;
	}

	protected String getHurtSound() {
		return "random.hurt";
	}

	protected String getDeathSound() {
		return "random.hurt";
	}

	public void knockBack(Entity entity1, int i2, double d3, double d5) {
		this.field_35118_ao = true;
		float f7 = MathHelper.sqrt_double(d3 * d3 + d5 * d5);
		float f8 = 0.4F;
		this.motionX /= 2.0D;
		this.motionY /= 2.0D;
		this.motionZ /= 2.0D;
		this.motionX -= d3 / (double)f7 * (double)f8;
		this.motionY += (double)0.4F;
		this.motionZ -= d5 / (double)f7 * (double)f8;
		if(this.motionY > (double)0.4F) {
			this.motionY = (double)0.4F;
		}

	}

	public void onDeath(DamageSource damageSource1) {
		Entity entity2 = damageSource1.func_35532_a();
		if(this.scoreValue >= 0 && entity2 != null) {
			entity2.addToPlayerScore(this, this.scoreValue);
		}

		if(entity2 != null) {
			entity2.onKillEntity(this);
		}

		this.unused_flag = true;
		if(!this.worldObj.multiplayerWorld) {
			this.a(this.field_34905_c > 0);
		}

		this.worldObj.setEntityState(this, (byte)3);
	}

	protected void a(boolean z1) {
		int i2 = this.getDropItemId();
		if(i2 > 0) {
			int i3 = this.rand.nextInt(3);

			for(int i4 = 0; i4 < i3; ++i4) {
				this.dropItem(i2, 1);
			}
		}

	}

	protected int getDropItemId() {
		return 0;
	}

	protected void fall(float f1) {
		super.fall(f1);
		int i2 = (int)Math.ceil((double)(f1 - 3.0F));
		if(i2 > 0) {
			this.attackEntityFrom(DamageSource.field_35549_h, i2);
			int i3 = this.worldObj.getBlockId(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY - (double)0.2F - (double)this.yOffset), MathHelper.floor_double(this.posZ));
			if(i3 > 0) {
				StepSound stepSound4 = Block.blocksList[i3].stepSound;
				this.worldObj.playSoundAtEntity(this, stepSound4.stepSoundDir2(), stepSound4.getVolume() * 0.5F, stepSound4.getPitch() * 0.75F);
			}
		}

	}

	public void moveEntityWithHeading(float f1, float f2) {
		double d3;
		if(this.isInWater()) {
			d3 = this.posY;
			this.moveFlying(f1, f2, 0.02F);
			this.moveEntity(this.motionX, this.motionY, this.motionZ);
			this.motionX *= (double)0.8F;
			this.motionY *= (double)0.8F;
			this.motionZ *= (double)0.8F;
			this.motionY -= 0.02D;
			if(this.isCollidedHorizontally && this.isOffsetPositionInLiquid(this.motionX, this.motionY + (double)0.6F - this.posY + d3, this.motionZ)) {
				this.motionY = (double)0.3F;
			}
		} else if(this.handleLavaMovement()) {
			d3 = this.posY;
			this.moveFlying(f1, f2, 0.02F);
			this.moveEntity(this.motionX, this.motionY, this.motionZ);
			this.motionX *= 0.5D;
			this.motionY *= 0.5D;
			this.motionZ *= 0.5D;
			this.motionY -= 0.02D;
			if(this.isCollidedHorizontally && this.isOffsetPositionInLiquid(this.motionX, this.motionY + (double)0.6F - this.posY + d3, this.motionZ)) {
				this.motionY = (double)0.3F;
			}
		} else {
			float f8 = 0.91F;
			if(this.onGround) {
				f8 = 0.54600006F;
				int i4 = this.worldObj.getBlockId(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ));
				if(i4 > 0) {
					f8 = Block.blocksList[i4].slipperiness * 0.91F;
				}
			}

			float f9 = 0.16277136F / (f8 * f8 * f8);
			float f5 = this.onGround ? this.field_35169_bv * f9 : this.field_35168_bw;
			this.moveFlying(f1, f2, f5);
			f8 = 0.91F;
			if(this.onGround) {
				f8 = 0.54600006F;
				int i6 = this.worldObj.getBlockId(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ));
				if(i6 > 0) {
					f8 = Block.blocksList[i6].slipperiness * 0.91F;
				}
			}

			if(this.isOnLadder()) {
				float f11 = 0.15F;
				if(this.motionX < (double)(-f11)) {
					this.motionX = (double)(-f11);
				}

				if(this.motionX > (double)f11) {
					this.motionX = (double)f11;
				}

				if(this.motionZ < (double)(-f11)) {
					this.motionZ = (double)(-f11);
				}

				if(this.motionZ > (double)f11) {
					this.motionZ = (double)f11;
				}

				this.fallDistance = 0.0F;
				if(this.motionY < -0.15D) {
					this.motionY = -0.15D;
				}

				if(this.isSneaking() && this.motionY < 0.0D) {
					this.motionY = 0.0D;
				}
			}

			this.moveEntity(this.motionX, this.motionY, this.motionZ);
			if(this.isCollidedHorizontally && this.isOnLadder()) {
				this.motionY = 0.2D;
			}

			this.motionY -= 0.08D;
			this.motionY *= (double)0.98F;
			this.motionX *= (double)f8;
			this.motionZ *= (double)f8;
		}

		this.field_705_Q = this.field_704_R;
		d3 = this.posX - this.prevPosX;
		double d10 = this.posZ - this.prevPosZ;
		float f7 = MathHelper.sqrt_double(d3 * d3 + d10 * d10) * 4.0F;
		if(f7 > 1.0F) {
			f7 = 1.0F;
		}

		this.field_704_R += (f7 - this.field_704_R) * 0.4F;
		this.field_703_S += this.field_704_R;
	}

	public boolean isOnLadder() {
		int i1 = MathHelper.floor_double(this.posX);
		int i2 = MathHelper.floor_double(this.boundingBox.minY);
		int i3 = MathHelper.floor_double(this.posZ);
		return this.worldObj.getBlockId(i1, i2, i3) == Block.ladder.blockID;
	}

	public void writeEntityToNBT(NBTTagCompound nBTTagCompound1) {
		nBTTagCompound1.setShort("Health", (short)this.health);
		nBTTagCompound1.setShort("HurtTime", (short)this.hurtTime);
		nBTTagCompound1.setShort("DeathTime", (short)this.deathTime);
		nBTTagCompound1.setShort("AttackTime", (short)this.attackTime);
		if(!this.field_35170_bR.isEmpty()) {
			NBTTagList nBTTagList2 = new NBTTagList();
			Iterator iterator3 = this.field_35170_bR.values().iterator();

			while(iterator3.hasNext()) {
				PotionEffect potionEffect4 = (PotionEffect)iterator3.next();
				NBTTagCompound nBTTagCompound5 = new NBTTagCompound();
				nBTTagCompound5.setByte("Id", (byte)potionEffect4.func_35799_a());
				nBTTagCompound5.setByte("Amplifier", (byte)potionEffect4.func_35801_c());
				nBTTagCompound5.setInteger("Duration", potionEffect4.func_35802_b());
				nBTTagList2.setTag(nBTTagCompound5);
			}

			nBTTagCompound1.setTag("ActiveEffects", nBTTagList2);
		}

	}

	public void readEntityFromNBT(NBTTagCompound nBTTagCompound1) {
		this.health = nBTTagCompound1.getShort("Health");
		if(!nBTTagCompound1.hasKey("Health")) {
			this.health = 10;
		}

		this.hurtTime = nBTTagCompound1.getShort("HurtTime");
		this.deathTime = nBTTagCompound1.getShort("DeathTime");
		this.attackTime = nBTTagCompound1.getShort("AttackTime");
		if(nBTTagCompound1.hasKey("ActiveEffects")) {
			NBTTagList nBTTagList2 = nBTTagCompound1.getTagList("ActiveEffects");

			for(int i3 = 0; i3 < nBTTagList2.tagCount(); ++i3) {
				NBTTagCompound nBTTagCompound4 = (NBTTagCompound)nBTTagList2.tagAt(i3);
				byte b5 = nBTTagCompound4.getByte("Id");
				byte b6 = nBTTagCompound4.getByte("Amplifier");
				int i7 = nBTTagCompound4.getInteger("Duration");
				this.field_35170_bR.put(Integer.valueOf(b5), new PotionEffect(b5, i7, b6));
			}
		}

	}

	public boolean isEntityAlive() {
		return !this.isDead && this.health > 0;
	}

	public boolean canBreatheUnderwater() {
		return false;
	}

	public void onLivingUpdate() {
		if(this.newPosRotationIncrements > 0) {
			double d1 = this.posX + (this.newPosX - this.posX) / (double)this.newPosRotationIncrements;
			double d3 = this.posY + (this.newPosY - this.posY) / (double)this.newPosRotationIncrements;
			double d5 = this.posZ + (this.newPosZ - this.posZ) / (double)this.newPosRotationIncrements;

			double d7;
			for(d7 = this.newRotationYaw - (double)this.rotationYaw; d7 < -180.0D; d7 += 360.0D) {
			}

			while(d7 >= 180.0D) {
				d7 -= 360.0D;
			}

			this.rotationYaw = (float)((double)this.rotationYaw + d7 / (double)this.newPosRotationIncrements);
			this.rotationPitch = (float)((double)this.rotationPitch + (this.newRotationPitch - (double)this.rotationPitch) / (double)this.newPosRotationIncrements);
			--this.newPosRotationIncrements;
			this.setPosition(d1, d3, d5);
			this.setRotation(this.rotationYaw, this.rotationPitch);
			List list9 = this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox.contract(8.0D / 256D, 0.0D, 8.0D / 256D));
			if(list9.size() > 0) {
				double d10 = 0.0D;

				for(int i12 = 0; i12 < list9.size(); ++i12) {
					AxisAlignedBB axisAlignedBB13 = (AxisAlignedBB)list9.get(i12);
					if(axisAlignedBB13.maxY > d10) {
						d10 = axisAlignedBB13.maxY;
					}
				}

				d3 += d10 - this.boundingBox.minY;
				this.setPosition(d1, d3, d5);
			}
		}

		if(this.isMovementBlocked()) {
			this.isJumping = false;
			this.moveStrafing = 0.0F;
			this.moveForward = 0.0F;
			this.randomYawVelocity = 0.0F;
		} else if(!this.isMultiplayerEntity) {
			this.updateEntityActionState();
		}

		boolean z14 = this.isInWater();
		boolean z2 = this.handleLavaMovement();
		if(this.isJumping) {
			if(z14) {
				this.motionY += (double)0.04F;
			} else if(z2) {
				this.motionY += (double)0.04F;
			} else if(this.onGround) {
				this.jump();
			}
		}

		this.moveStrafing *= 0.98F;
		this.moveForward *= 0.98F;
		this.randomYawVelocity *= 0.9F;
		float f15 = this.field_35169_bv;
		this.field_35169_bv *= this.func_35166_t_();
		this.moveEntityWithHeading(this.moveStrafing, this.moveForward);
		this.field_35169_bv = f15;
		List list4 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand((double)0.2F, 0.0D, (double)0.2F));
		if(list4 != null && list4.size() > 0) {
			for(int i16 = 0; i16 < list4.size(); ++i16) {
				Entity entity6 = (Entity)list4.get(i16);
				if(entity6.canBePushed()) {
					entity6.applyEntityCollision(this);
				}
			}
		}

	}

	protected boolean isMovementBlocked() {
		return this.health <= 0;
	}

	public boolean func_35162_ad() {
		return false;
	}

	protected void jump() {
		this.motionY = (double)0.42F;
		if(this.func_35117_Q()) {
			float f1 = this.rotationYaw * 0.017453292F;
			this.motionX -= (double)(MathHelper.sin(f1) * 0.2F);
			this.motionZ += (double)(MathHelper.cos(f1) * 0.2F);
		}

		this.field_35118_ao = true;
	}

	protected boolean canDespawn() {
		return true;
	}

	protected void despawnEntity() {
		EntityPlayer entityPlayer1 = this.worldObj.getClosestPlayerToEntity(this, -1.0D);
		if(this.canDespawn() && entityPlayer1 != null) {
			double d2 = entityPlayer1.posX - this.posX;
			double d4 = entityPlayer1.posY - this.posY;
			double d6 = entityPlayer1.posZ - this.posZ;
			double d8 = d2 * d2 + d4 * d4 + d6 * d6;
			if(d8 > 16384.0D) {
				this.setEntityDead();
			}

			if(this.entityAge > 600 && this.rand.nextInt(800) == 0) {
				if(d8 < 1024.0D) {
					this.entityAge = 0;
				} else {
					this.setEntityDead();
				}
			}
		}

	}

	protected void updateEntityActionState() {
		++this.entityAge;
		EntityPlayer entityPlayer1 = this.worldObj.getClosestPlayerToEntity(this, -1.0D);
		this.despawnEntity();
		this.moveStrafing = 0.0F;
		this.moveForward = 0.0F;
		float f2 = 8.0F;
		if(this.rand.nextFloat() < 0.02F) {
			entityPlayer1 = this.worldObj.getClosestPlayerToEntity(this, (double)f2);
			if(entityPlayer1 != null) {
				this.currentTarget = entityPlayer1;
				this.numTicksToChaseTarget = 10 + this.rand.nextInt(20);
			} else {
				this.randomYawVelocity = (this.rand.nextFloat() - 0.5F) * 20.0F;
			}
		}

		if(this.currentTarget != null) {
			this.faceEntity(this.currentTarget, 10.0F, (float)this.getVerticalFaceSpeed());
			if(this.numTicksToChaseTarget-- <= 0 || this.currentTarget.isDead || this.currentTarget.getDistanceSqToEntity(this) > (double)(f2 * f2)) {
				this.currentTarget = null;
			}
		} else {
			if(this.rand.nextFloat() < 0.05F) {
				this.randomYawVelocity = (this.rand.nextFloat() - 0.5F) * 20.0F;
			}

			this.rotationYaw += this.randomYawVelocity;
			this.rotationPitch = this.defaultPitch;
		}

		boolean z3 = this.isInWater();
		boolean z4 = this.handleLavaMovement();
		if(z3 || z4) {
			this.isJumping = this.rand.nextFloat() < 0.8F;
		}

	}

	protected int getVerticalFaceSpeed() {
		return 40;
	}

	public void faceEntity(Entity entity1, float f2, float f3) {
		double d4 = entity1.posX - this.posX;
		double d8 = entity1.posZ - this.posZ;
		double d6;
		if(entity1 instanceof EntityLiving) {
			EntityLiving entityLiving10 = (EntityLiving)entity1;
			d6 = this.posY + (double)this.getEyeHeight() - (entityLiving10.posY + (double)entityLiving10.getEyeHeight());
		} else {
			d6 = (entity1.boundingBox.minY + entity1.boundingBox.maxY) / 2.0D - (this.posY + (double)this.getEyeHeight());
		}

		double d14 = (double)MathHelper.sqrt_double(d4 * d4 + d8 * d8);
		float f12 = (float)(Math.atan2(d8, d4) * 180.0D / (double)(float)Math.PI) - 90.0F;
		float f13 = (float)(-(Math.atan2(d6, d14) * 180.0D / (double)(float)Math.PI));
		this.rotationPitch = -this.updateRotation(this.rotationPitch, f13, f3);
		this.rotationYaw = this.updateRotation(this.rotationYaw, f12, f2);
	}

	public boolean hasCurrentTarget() {
		return this.currentTarget != null;
	}

	public Entity getCurrentTarget() {
		return this.currentTarget;
	}

	private float updateRotation(float f1, float f2, float f3) {
		float f4;
		for(f4 = f2 - f1; f4 < -180.0F; f4 += 360.0F) {
		}

		while(f4 >= 180.0F) {
			f4 -= 360.0F;
		}

		if(f4 > f3) {
			f4 = f3;
		}

		if(f4 < -f3) {
			f4 = -f3;
		}

		return f1 + f4;
	}

	public void onEntityDeath() {
	}

	public boolean getCanSpawnHere() {
		return this.worldObj.checkIfAABBIsClear(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).size() == 0 && !this.worldObj.getIsAnyLiquid(this.boundingBox);
	}

	protected void kill() {
		this.attackEntityFrom(DamageSource.field_35550_i, 4);
	}

	public float getSwingProgress(float f1) {
		float f2 = this.swingProgress - this.prevSwingProgress;
		if(f2 < 0.0F) {
			++f2;
		}

		return this.prevSwingProgress + f2 * f1;
	}

	public Vec3D getPosition(float f1) {
		if(f1 == 1.0F) {
			return Vec3D.createVector(this.posX, this.posY, this.posZ);
		} else {
			double d2 = this.prevPosX + (this.posX - this.prevPosX) * (double)f1;
			double d4 = this.prevPosY + (this.posY - this.prevPosY) * (double)f1;
			double d6 = this.prevPosZ + (this.posZ - this.prevPosZ) * (double)f1;
			return Vec3D.createVector(d2, d4, d6);
		}
	}

	public Vec3D getLookVec() {
		return this.getLook(1.0F);
	}

	public Vec3D getLook(float f1) {
		float f2;
		float f3;
		float f4;
		float f5;
		if(f1 == 1.0F) {
			f2 = MathHelper.cos(-this.rotationYaw * 0.017453292F - (float)Math.PI);
			f3 = MathHelper.sin(-this.rotationYaw * 0.017453292F - (float)Math.PI);
			f4 = -MathHelper.cos(-this.rotationPitch * 0.017453292F);
			f5 = MathHelper.sin(-this.rotationPitch * 0.017453292F);
			return Vec3D.createVector((double)(f3 * f4), (double)f5, (double)(f2 * f4));
		} else {
			f2 = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * f1;
			f3 = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * f1;
			f4 = MathHelper.cos(-f3 * 0.017453292F - (float)Math.PI);
			f5 = MathHelper.sin(-f3 * 0.017453292F - (float)Math.PI);
			float f6 = -MathHelper.cos(-f2 * 0.017453292F);
			float f7 = MathHelper.sin(-f2 * 0.017453292F);
			return Vec3D.createVector((double)(f5 * f6), (double)f7, (double)(f4 * f6));
		}
	}

	public float func_35159_aC() {
		return 1.0F;
	}

	public MovingObjectPosition rayTrace(double d1, float f3) {
		Vec3D vec3D4 = this.getPosition(f3);
		Vec3D vec3D5 = this.getLook(f3);
		Vec3D vec3D6 = vec3D4.addVector(vec3D5.xCoord * d1, vec3D5.yCoord * d1, vec3D5.zCoord * d1);
		return this.worldObj.rayTraceBlocks(vec3D4, vec3D6);
	}

	public int getMaxSpawnedInChunk() {
		return 4;
	}

	public ItemStack getHeldItem() {
		return null;
	}

	public void handleHealthUpdate(byte b1) {
		if(b1 == 2) {
			this.field_704_R = 1.5F;
			this.heartsLife = this.heartsHalvesLife;
			this.hurtTime = this.maxHurtTime = 10;
			this.attackedAtYaw = 0.0F;
			this.worldObj.playSoundAtEntity(this, this.getHurtSound(), this.getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
			this.attackEntityFrom(DamageSource.field_35547_j, 0);
		} else if(b1 == 3) {
			this.worldObj.playSoundAtEntity(this, this.getDeathSound(), this.getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
			this.health = 0;
			this.onDeath(DamageSource.field_35547_j);
		} else {
			super.handleHealthUpdate(b1);
		}

	}

	public boolean isPlayerSleeping() {
		return false;
	}

	public int getItemIcon(ItemStack itemStack1) {
		return itemStack1.getIconIndex();
	}

	protected void dropFewItems() {
		Iterator iterator1 = this.field_35170_bR.keySet().iterator();

		while(iterator1.hasNext()) {
			Integer integer2 = (Integer)iterator1.next();
			PotionEffect potionEffect3 = (PotionEffect)this.field_35170_bR.get(integer2);
			if(!potionEffect3.func_35798_a(this) && !this.worldObj.multiplayerWorld) {
				iterator1.remove();
				this.func_35158_d(potionEffect3);
			}
		}

	}

	public boolean func_35160_a(Potion potion1) {
		return this.field_35170_bR.containsKey(potion1.field_35670_H);
	}

	public PotionEffect func_35167_b(Potion potion1) {
		return (PotionEffect)this.field_35170_bR.get(potion1.field_35670_H);
	}

	public void func_35165_a(PotionEffect potionEffect1) {
		if(this.field_35170_bR.containsKey(potionEffect1.func_35799_a())) {
			((PotionEffect)this.field_35170_bR.get(potionEffect1.func_35799_a())).func_35796_a(potionEffect1);
			this.func_35161_c((PotionEffect)this.field_35170_bR.get(potionEffect1.func_35799_a()));
		} else {
			this.field_35170_bR.put(potionEffect1.func_35799_a(), potionEffect1);
			this.func_35164_b(potionEffect1);
		}

	}

	public void damageEntity(int i1) {
		this.field_35170_bR.remove(i1);
	}

	protected void func_35164_b(PotionEffect potionEffect1) {
	}

	protected void func_35161_c(PotionEffect potionEffect1) {
	}

	protected void func_35158_d(PotionEffect potionEffect1) {
	}

	protected float func_35166_t_() {
		float f1 = 1.0F;
		if(this.func_35160_a(Potion.field_35677_c)) {
			f1 *= 1.0F + 0.2F * (float)(this.func_35167_b(Potion.field_35677_c).func_35801_c() + 1);
		}

		if(this.func_35160_a(Potion.field_35674_d)) {
			f1 *= 1.0F - 0.15F * (float)(this.func_35167_b(Potion.field_35674_d).func_35801_c() + 1);
		}

		return f1;
	}
}
