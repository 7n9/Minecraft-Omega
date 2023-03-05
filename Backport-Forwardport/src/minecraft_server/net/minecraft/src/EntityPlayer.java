package net.minecraft.src;

import java.util.Iterator;
import java.util.List;

public abstract class EntityPlayer extends EntityLiving {
	public InventoryPlayer inventory = new InventoryPlayer(this);
	public Container personalCraftingInventory;
	public Container currentCraftingInventory;
	protected FoodStats field_35217_m = new FoodStats();
	protected int field_35216_n = 0;
	public byte field_9152_am = 0;
	public int score = 0;
	public float prevCameraYaw;
	public float cameraYaw;
	public boolean isSwinging = false;
	public int swingProgressInt = 0;
	public String username;
	public int dimension;
	public int field_35218_w = 0;
	public double field_20047_ay;
	public double field_20046_az;
	public double field_20051_aA;
	public double field_20050_aB;
	public double field_20049_aC;
	public double field_20048_aD;
	protected boolean sleeping;
	public ChunkCoordinates playerLocation;
	private int sleepTimer;
	public float field_22066_z;
	public float field_22067_A;
	private ChunkCoordinates spawnChunk;
	private ChunkCoordinates startMinecartRidingCoordinate;
	public int timeUntilPortal = 20;
	protected boolean inPortal = false;
	public float timeInPortal;
	public PlayerCapabilities field_35214_K = new PlayerCapabilities();
	public int field_35210_L;
	public int field_35211_M;
	public int field_35212_N;
	private ItemStack field_34908_d;
	private int field_34909_e;
	protected float field_35213_O = 0.1F;
	protected float field_35215_P = 0.02F;
	private int damageRemainder = 0;
	public EntityFish fishEntity = null;

	public EntityPlayer(World world1) {
		super(world1);
		this.personalCraftingInventory = new ContainerPlayer(this.inventory, !world1.singleplayerWorld);
		this.currentCraftingInventory = this.personalCraftingInventory;
		this.yOffset = 1.62F;
		ChunkCoordinates chunkCoordinates2 = world1.getSpawnPoint();
		this.setLocationAndAngles((double)chunkCoordinates2.posX + 0.5D, (double)(chunkCoordinates2.posY + 1), (double)chunkCoordinates2.posZ + 0.5D, 0.0F, 0.0F);
		this.health = 20;
		this.entityType = "humanoid";
		this.field_9117_aI = 180.0F;
		this.fireResistance = 20;
		this.texture = "/mob/char.png";
	}

	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(16, (byte)0);
		this.dataWatcher.addObject(17, (byte)0);
	}

	public boolean func_35209_o_() {
		return this.field_34908_d != null;
	}

	public void func_35196_E() {
		if(this.field_34908_d != null) {
			this.field_34908_d.func_35613_a(this.worldObj, this, this.field_34909_e);
		}

		this.func_35205_F();
	}

	public void func_35205_F() {
		this.field_34908_d = null;
		this.field_34909_e = 0;
		if(!this.worldObj.singleplayerWorld) {
			this.func_35148_h(false);
		}

	}

	public boolean func_35180_G() {
		return this.func_35209_o_() && Item.itemsList[this.field_34908_d.itemID].func_35406_b(this.field_34908_d) == EnumAction.block;
	}

	public void onUpdate() {
		if(this.field_34908_d != null) {
			ItemStack itemStack1 = this.inventory.getCurrentItem();
			if(itemStack1 != this.field_34908_d) {
				this.func_35205_F();
			} else {
				if(this.field_34909_e <= 25 && this.field_34909_e % 4 == 0) {
					this.func_35208_b(itemStack1, 5);
				}

				if(--this.field_34909_e == 0 && !this.worldObj.singleplayerWorld) {
					this.func_35199_C();
				}
			}
		}

		if(this.field_35218_w > 0) {
			--this.field_35218_w;
		}

		if(this.isPlayerSleeping()) {
			++this.sleepTimer;
			if(this.sleepTimer > 100) {
				this.sleepTimer = 100;
			}

			if(!this.worldObj.singleplayerWorld) {
				if(!this.isInBed()) {
					this.wakeUpPlayer(true, true, false);
				} else if(this.worldObj.isDaytime()) {
					this.wakeUpPlayer(false, true, true);
				}
			}
		} else if(this.sleepTimer > 0) {
			++this.sleepTimer;
			if(this.sleepTimer >= 110) {
				this.sleepTimer = 0;
			}
		}

		super.onUpdate();
		if(!this.worldObj.singleplayerWorld && this.currentCraftingInventory != null && !this.currentCraftingInventory.canInteractWith(this)) {
			this.usePersonalCraftingInventory();
			this.currentCraftingInventory = this.personalCraftingInventory;
		}

		if(this.field_35214_K.field_35658_b) {
			for(int i9 = 0; i9 < 8; ++i9) {
			}
		}

		if(this.fire > 0 && this.field_35214_K.field_35660_a) {
			this.fire = 0;
		}

		this.field_20047_ay = this.field_20050_aB;
		this.field_20046_az = this.field_20049_aC;
		this.field_20051_aA = this.field_20048_aD;
		double d10 = this.posX - this.field_20050_aB;
		double d3 = this.posY - this.field_20049_aC;
		double d5 = this.posZ - this.field_20048_aD;
		double d7 = 10.0D;
		if(d10 > d7) {
			this.field_20047_ay = this.field_20050_aB = this.posX;
		}

		if(d5 > d7) {
			this.field_20051_aA = this.field_20048_aD = this.posZ;
		}

		if(d3 > d7) {
			this.field_20046_az = this.field_20049_aC = this.posY;
		}

		if(d10 < -d7) {
			this.field_20047_ay = this.field_20050_aB = this.posX;
		}

		if(d5 < -d7) {
			this.field_20051_aA = this.field_20048_aD = this.posZ;
		}

		if(d3 < -d7) {
			this.field_20046_az = this.field_20049_aC = this.posY;
		}

		this.field_20050_aB += d10 * 0.25D;
		this.field_20048_aD += d5 * 0.25D;
		this.field_20049_aC += d3 * 0.25D;
		this.addStat(StatList.minutesPlayedStat, 1);
		if(this.ridingEntity == null) {
			this.startMinecartRidingCoordinate = null;
		}

		if(!this.worldObj.singleplayerWorld) {
			this.field_35217_m.func_35584_a(this);
		}

	}

	protected void func_35208_b(ItemStack itemStack1, int i2) {
		if(itemStack1.func_35615_m() == EnumAction.eat) {
			for(int i3 = 0; i3 < i2; ++i3) {
				Vec3D vec3D4 = Vec3D.createVector(((double)this.rand.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);
				vec3D4.func_35571_a(-this.rotationPitch * (float)Math.PI / 180.0F);
				vec3D4.func_35572_b(-this.rotationYaw * (float)Math.PI / 180.0F);
				Vec3D vec3D5 = Vec3D.createVector(((double)this.rand.nextFloat() - 0.5D) * 0.3D, (double)(-this.rand.nextFloat()) * 0.6D - 0.3D, 0.6D);
				vec3D5.func_35571_a(-this.rotationPitch * (float)Math.PI / 180.0F);
				vec3D5.func_35572_b(-this.rotationYaw * (float)Math.PI / 180.0F);
				vec3D5 = vec3D5.addVector(this.posX, this.posY + (double)this.getEyeHeight(), this.posZ);
				this.worldObj.spawnParticle("iconcrack_" + itemStack1.getItem().shiftedIndex, vec3D5.xCoord, vec3D5.yCoord, vec3D5.zCoord, vec3D4.xCoord, vec3D4.yCoord + 0.05D, vec3D4.zCoord);
			}

			this.worldObj.playSoundAtEntity(this, "mob.eat", 0.5F + 0.5F * (float)this.rand.nextInt(2), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
		}

	}

	protected void func_35199_C() {
		if(this.field_34908_d != null) {
			this.func_35208_b(this.field_34908_d, 16);
			int i1 = this.field_34908_d.stackSize;
			ItemStack itemStack2 = this.field_34908_d.func_35617_b(this.worldObj, this);
			if(itemStack2 != this.field_34908_d || itemStack2 != null && itemStack2.stackSize != i1) {
				this.inventory.mainInventory[this.inventory.currentItem] = itemStack2;
				if(itemStack2.stackSize == 0) {
					this.inventory.mainInventory[this.inventory.currentItem] = null;
				}
			}

			this.func_35205_F();
		}

	}

	protected boolean isMovementBlocked() {
		return this.health <= 0 || this.isPlayerSleeping();
	}

	protected void usePersonalCraftingInventory() {
		this.currentCraftingInventory = this.personalCraftingInventory;
	}

	public void updateRidden() {
		double d1 = this.posX;
		double d3 = this.posY;
		double d5 = this.posZ;
		super.updateRidden();
		this.prevCameraYaw = this.cameraYaw;
		this.cameraYaw = 0.0F;
		this.addMountedMovementStat(this.posX - d1, this.posY - d3, this.posZ - d5);
	}

	private int func_35204_o() {
		return this.func_35184_a(Potion.field_35452_e) ? 6 - (1 + this.func_35187_b(Potion.field_35452_e).func_35652_c()) * 1 : (this.func_35184_a(Potion.field_35449_f) ? 6 + (1 + this.func_35187_b(Potion.field_35449_f).func_35652_c()) * 2 : 6);
	}

	protected void updateEntityActionState() {
		int i1 = this.func_35204_o();
		if(this.isSwinging) {
			++this.swingProgressInt;
			if(this.swingProgressInt >= i1) {
				this.swingProgressInt = 0;
				this.isSwinging = false;
			}
		} else {
			this.swingProgressInt = 0;
		}

		this.swingProgress = (float)this.swingProgressInt / (float)i1;
	}

	public void onLivingUpdate() {
		if(this.field_35216_n > 0) {
			--this.field_35216_n;
		}

		if(this.worldObj.difficultySetting == 0 && this.health < 20 && this.ticksExisted % 20 * 12 == 0) {
			this.heal(1);
		}

		this.inventory.decrementAnimations();
		this.prevCameraYaw = this.cameraYaw;
		super.onLivingUpdate();
		this.field_35194_aj = this.field_35213_O;
		this.field_35193_ak = this.field_35215_P;
		if(this.func_35149_at()) {
			this.field_35194_aj = (float)((double)this.field_35194_aj + (double)this.field_35213_O * 0.3D);
			this.field_35193_ak = (float)((double)this.field_35193_ak + (double)this.field_35215_P * 0.3D);
		}

		float f1 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
		float f2 = (float)Math.atan(-this.motionY * (double)0.2F) * 15.0F;
		if(f1 > 0.1F) {
			f1 = 0.1F;
		}

		if(!this.onGround || this.health <= 0) {
			f1 = 0.0F;
		}

		if(this.onGround || this.health <= 0) {
			f2 = 0.0F;
		}

		this.cameraYaw += (f1 - this.cameraYaw) * 0.4F;
		this.cameraPitch += (f2 - this.cameraPitch) * 0.8F;
		if(this.health > 0) {
			List list3 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(1.0D, 0.0D, 1.0D));
			if(list3 != null) {
				for(int i4 = 0; i4 < list3.size(); ++i4) {
					Entity entity5 = (Entity)list3.get(i4);
					if(!entity5.isDead) {
						this.collideWithPlayer(entity5);
					}
				}
			}
		}

	}

	private void collideWithPlayer(Entity entity1) {
		entity1.onCollideWithPlayer(this);
	}

	public void onDeath(DamageSource damageSource1) {
		super.onDeath(damageSource1);
		this.setSize(0.2F, 0.2F);
		this.setPosition(this.posX, this.posY, this.posZ);
		this.motionY = (double)0.1F;
		if(this.username.equals("Notch")) {
			this.dropPlayerItemWithRandomChoice(new ItemStack(Item.appleRed, 1), true);
		}

		this.inventory.dropAllItems();
		if(damageSource1 != null) {
			this.motionX = (double)(-MathHelper.cos((this.attackedAtYaw + this.rotationYaw) * (float)Math.PI / 180.0F) * 0.1F);
			this.motionZ = (double)(-MathHelper.sin((this.attackedAtYaw + this.rotationYaw) * (float)Math.PI / 180.0F) * 0.1F);
		} else {
			this.motionX = this.motionZ = 0.0D;
		}

		this.yOffset = 0.1F;
		this.addStat(StatList.deathsStat, 1);
	}

	public void addToPlayerScore(Entity entity1, int i2) {
		this.score += i2;
		if(entity1 instanceof EntityPlayer) {
			this.addStat(StatList.playerKillsStat, 1);
		} else {
			this.addStat(StatList.mobKillsStat, 1);
		}

	}

	public void dropCurrentItem() {
		this.dropPlayerItemWithRandomChoice(this.inventory.decrStackSize(this.inventory.currentItem, 1), false);
	}

	public void dropPlayerItem(ItemStack itemStack1) {
		this.dropPlayerItemWithRandomChoice(itemStack1, false);
	}

	public void dropPlayerItemWithRandomChoice(ItemStack itemStack1, boolean z2) {
		if(itemStack1 != null) {
			EntityItem entityItem3 = new EntityItem(this.worldObj, this.posX, this.posY - (double)0.3F + (double)this.getEyeHeight(), this.posZ, itemStack1);
			entityItem3.delayBeforeCanPickup = 40;
			float f4 = 0.1F;
			float f5;
			if(z2) {
				f5 = this.rand.nextFloat() * 0.5F;
				float f6 = this.rand.nextFloat() * (float)Math.PI * 2.0F;
				entityItem3.motionX = (double)(-MathHelper.sin(f6) * f5);
				entityItem3.motionZ = (double)(MathHelper.cos(f6) * f5);
				entityItem3.motionY = (double)0.2F;
			} else {
				f4 = 0.3F;
				entityItem3.motionX = (double)(-MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI) * f4);
				entityItem3.motionZ = (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI) * f4);
				entityItem3.motionY = (double)(-MathHelper.sin(this.rotationPitch / 180.0F * (float)Math.PI) * f4 + 0.1F);
				f4 = 0.02F;
				f5 = this.rand.nextFloat() * (float)Math.PI * 2.0F;
				f4 *= this.rand.nextFloat();
				entityItem3.motionX += Math.cos((double)f5) * (double)f4;
				entityItem3.motionY += (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F);
				entityItem3.motionZ += Math.sin((double)f5) * (double)f4;
			}

			this.joinEntityItemWithWorld(entityItem3);
			this.addStat(StatList.dropStat, 1);
		}
	}

	protected void joinEntityItemWithWorld(EntityItem entityItem1) {
		this.worldObj.entityJoinedWorld(entityItem1);
	}

	public float getCurrentPlayerStrVsBlock(Block block1) {
		float f2 = this.inventory.getStrVsBlock(block1);
		if(this.isInsideOfMaterial(Material.water)) {
			f2 /= 5.0F;
		}

		if(!this.onGround) {
			f2 /= 5.0F;
		}

		if(this.func_35184_a(Potion.field_35452_e)) {
			f2 *= 1.0F + (float)(this.func_35187_b(Potion.field_35452_e).func_35652_c() + 1) * 0.2F;
		}

		if(this.func_35184_a(Potion.field_35449_f)) {
			f2 *= 1.0F - (float)(this.func_35187_b(Potion.field_35449_f).func_35652_c() + 1) * 0.2F;
		}

		return f2;
	}

	public boolean canHarvestBlock(Block block1) {
		return this.inventory.canHarvestBlock(block1);
	}

	public void readEntityFromNBT(NBTTagCompound nBTTagCompound1) {
		super.readEntityFromNBT(nBTTagCompound1);
		NBTTagList nBTTagList2 = nBTTagCompound1.getTagList("Inventory");
		this.inventory.readFromNBT(nBTTagList2);
		this.dimension = nBTTagCompound1.getInteger("Dimension");
		this.sleeping = nBTTagCompound1.getBoolean("Sleeping");
		this.sleepTimer = nBTTagCompound1.getShort("SleepTimer");
		this.field_35210_L = nBTTagCompound1.getInteger("Xp");
		this.field_35211_M = nBTTagCompound1.getInteger("XpLevel");
		this.field_35212_N = nBTTagCompound1.getInteger("XpTotal");
		if(this.sleeping) {
			this.playerLocation = new ChunkCoordinates(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ));
			this.wakeUpPlayer(true, true, false);
		}

		if(nBTTagCompound1.hasKey("SpawnX") && nBTTagCompound1.hasKey("SpawnY") && nBTTagCompound1.hasKey("SpawnZ")) {
			this.spawnChunk = new ChunkCoordinates(nBTTagCompound1.getInteger("SpawnX"), nBTTagCompound1.getInteger("SpawnY"), nBTTagCompound1.getInteger("SpawnZ"));
		}

		this.field_35217_m.func_35589_a(nBTTagCompound1);
	}

	public void writeEntityToNBT(NBTTagCompound nBTTagCompound1) {
		super.writeEntityToNBT(nBTTagCompound1);
		nBTTagCompound1.setTag("Inventory", this.inventory.writeToNBT(new NBTTagList()));
		nBTTagCompound1.setInteger("Dimension", this.dimension);
		nBTTagCompound1.setBoolean("Sleeping", this.sleeping);
		nBTTagCompound1.setShort("SleepTimer", (short)this.sleepTimer);
		nBTTagCompound1.setInteger("Xp", this.field_35210_L);
		nBTTagCompound1.setInteger("XpLevel", this.field_35211_M);
		nBTTagCompound1.setInteger("XpTotal", this.field_35212_N);
		if(this.spawnChunk != null) {
			nBTTagCompound1.setInteger("SpawnX", this.spawnChunk.posX);
			nBTTagCompound1.setInteger("SpawnY", this.spawnChunk.posY);
			nBTTagCompound1.setInteger("SpawnZ", this.spawnChunk.posZ);
		}

		this.field_35217_m.func_35588_b(nBTTagCompound1);
	}

	public void displayGUIChest(IInventory iInventory1) {
	}

	public void displayWorkbenchGUI(int i1, int i2, int i3) {
	}

	public void onItemPickup(Entity entity1, int i2) {
	}

	public float getEyeHeight() {
		return 0.12F;
	}

	protected void resetHeight() {
		this.yOffset = 1.62F;
	}

	public boolean attackEntityFrom(DamageSource damageSource1, int i2) {
		if(this.field_35214_K.field_35660_a && !damageSource1.func_35077_d()) {
			return false;
		} else {
			this.entityAge = 0;
			if(this.health <= 0) {
				return false;
			} else {
				if(this.isPlayerSleeping() && !this.worldObj.singleplayerWorld) {
					this.wakeUpPlayer(true, true, false);
				}

				Entity entity3 = damageSource1.func_35080_a();
				if(entity3 instanceof EntityMob || entity3 instanceof EntityArrow) {
					if(this.worldObj.difficultySetting == 0) {
						i2 = 0;
					}

					if(this.worldObj.difficultySetting == 1) {
						i2 = i2 / 3 + 1;
					}

					if(this.worldObj.difficultySetting == 3) {
						i2 = i2 * 3 / 2;
					}
				}

				if(i2 == 0) {
					return false;
				} else {
					Entity entity4 = entity3;
					if(entity3 instanceof EntityArrow && ((EntityArrow)entity3).shootingEntity != null) {
						entity4 = ((EntityArrow)entity3).shootingEntity;
					}

					if(entity4 instanceof EntityLiving) {
						this.alertWolves((EntityLiving)entity4, false);
					}

					this.addStat(StatList.damageTakenStat, i2);
					return super.attackEntityFrom(damageSource1, i2);
				}
			}
		}
	}

	protected boolean isPVPEnabled() {
		return false;
	}

	protected void alertWolves(EntityLiving entityLiving1, boolean z2) {
		if(!(entityLiving1 instanceof EntityCreeper) && !(entityLiving1 instanceof EntityGhast)) {
			if(entityLiving1 instanceof EntityWolf) {
				EntityWolf entityWolf3 = (EntityWolf)entityLiving1;
				if(entityWolf3.isTamed() && this.username.equals(entityWolf3.getOwner())) {
					return;
				}
			}

			if(!(entityLiving1 instanceof EntityPlayer) || this.isPVPEnabled()) {
				List list7 = this.worldObj.getEntitiesWithinAABB(EntityWolf.class, AxisAlignedBB.getBoundingBoxFromPool(this.posX, this.posY, this.posZ, this.posX + 1.0D, this.posY + 1.0D, this.posZ + 1.0D).expand(16.0D, 4.0D, 16.0D));
				Iterator iterator4 = list7.iterator();

				while(true) {
					EntityWolf entityWolf6;
					do {
						do {
							do {
								do {
									if(!iterator4.hasNext()) {
										return;
									}

									Entity entity5 = (Entity)iterator4.next();
									entityWolf6 = (EntityWolf)entity5;
								} while(!entityWolf6.isTamed());
							} while(entityWolf6.getEntityToAttack() != null);
						} while(!this.username.equals(entityWolf6.getOwner()));
					} while(z2 && entityWolf6.getIsSitting());

					entityWolf6.setIsSitting(false);
					entityWolf6.setTarget(entityLiving1);
				}
			}
		}
	}

	protected void damageEntity(DamageSource damageSource1, int i2) {
		if(!damageSource1.func_35083_b() && this.func_35180_G()) {
			i2 = 1 + i2 >> 1;
		}

		if(!damageSource1.func_35083_b()) {
			int i3 = 25 - this.inventory.getTotalArmorValue();
			int i4 = i2 * i3 + this.damageRemainder;
			this.inventory.damageArmor(i2);
			i2 = i4 / 25;
			this.damageRemainder = i4 % 25;
		}

		this.func_35198_b(damageSource1.func_35074_c());
		super.damageEntity(damageSource1, i2);
	}

	public void displayGUIFurnace(TileEntityFurnace tileEntityFurnace1) {
	}

	public void displayGUIDispenser(TileEntityDispenser tileEntityDispenser1) {
	}

	public void displayGUIEditSign(TileEntitySign tileEntitySign1) {
	}

	public void useCurrentItemOnEntity(Entity entity1) {
		if(!entity1.interact(this)) {
			ItemStack itemStack2 = this.getCurrentEquippedItem();
			if(itemStack2 != null && entity1 instanceof EntityLiving) {
				itemStack2.useItemOnEntity((EntityLiving)entity1);
				if(itemStack2.stackSize <= 0) {
					itemStack2.onItemDestroyedByUse(this);
					this.destroyCurrentEquippedItem();
				}
			}

		}
	}

	public ItemStack getCurrentEquippedItem() {
		return this.inventory.getCurrentItem();
	}

	public void destroyCurrentEquippedItem() {
		this.inventory.setInventorySlotContents(this.inventory.currentItem, (ItemStack)null);
	}

	public double getYOffset() {
		return (double)(this.yOffset - 0.5F);
	}

	public void swingItem() {
		if(!this.isSwinging || this.swingProgressInt >= this.func_35204_o() / 2 || this.swingProgressInt < 0) {
			this.swingProgressInt = -1;
			this.isSwinging = true;
		}

	}

	public void attackTargetEntityWithCurrentItem(Entity entity1) {
		int i2 = this.inventory.getDamageVsEntity(entity1);
		if(i2 > 0) {
			boolean z3 = this.motionY < 0.0D && !this.onGround && !this.isOnLadder() && !this.isInWater();
			if(z3) {
				i2 = i2 * 3 / 2 + 1;
			}

			boolean z4 = entity1.attackEntityFrom(DamageSource.func_35076_b(this), i2);
			if(z4) {
				if(this.func_35149_at()) {
					entity1.addVelocity((double)(-MathHelper.sin(this.rotationYaw * (float)Math.PI / 180.0F) * 1.0F), 0.1D, (double)(MathHelper.cos(this.rotationYaw * (float)Math.PI / 180.0F) * 1.0F));
					this.motionX *= 0.6D;
					this.motionZ *= 0.6D;
					this.func_35146_g(false);
				}

				if(z3) {
					this.func_35202_e(entity1);
				}
			}

			ItemStack itemStack5 = this.getCurrentEquippedItem();
			if(itemStack5 != null && entity1 instanceof EntityLiving) {
				itemStack5.hitEntity((EntityLiving)entity1, this);
				if(itemStack5.stackSize <= 0) {
					itemStack5.onItemDestroyedByUse(this);
					this.destroyCurrentEquippedItem();
				}
			}

			if(entity1 instanceof EntityLiving) {
				if(entity1.isEntityAlive()) {
					this.alertWolves((EntityLiving)entity1, true);
				}

				this.addStat(StatList.damageDealtStat, i2);
			}

			this.func_35198_b(0.3F);
		}

	}

	public void func_35202_e(Entity entity1) {
	}

	public void onItemStackChanged(ItemStack itemStack1) {
	}

	public void setEntityDead() {
		super.setEntityDead();
		this.personalCraftingInventory.onCraftGuiClosed(this);
		if(this.currentCraftingInventory != null) {
			this.currentCraftingInventory.onCraftGuiClosed(this);
		}

	}

	public boolean isEntityInsideOpaqueBlock() {
		return !this.sleeping && super.isEntityInsideOpaqueBlock();
	}

	public EnumStatus sleepInBedAt(int i1, int i2, int i3) {
		if(!this.worldObj.singleplayerWorld) {
			label53: {
				if(!this.isPlayerSleeping() && this.isEntityAlive()) {
					if(this.worldObj.worldProvider.canSleepInWorld) {
						return EnumStatus.NOT_POSSIBLE_HERE;
					}

					if(this.worldObj.isDaytime()) {
						return EnumStatus.NOT_POSSIBLE_NOW;
					}

					if(Math.abs(this.posX - (double)i1) <= 3.0D && Math.abs(this.posY - (double)i2) <= 2.0D && Math.abs(this.posZ - (double)i3) <= 3.0D) {
						break label53;
					}

					return EnumStatus.TOO_FAR_AWAY;
				}

				return EnumStatus.OTHER_PROBLEM;
			}
		}

		this.setSize(0.2F, 0.2F);
		this.yOffset = 0.2F;
		if(this.worldObj.blockExists(i1, i2, i3)) {
			int i4 = this.worldObj.getBlockMetadata(i1, i2, i3);
			int i5 = BlockBed.getDirectionFromMetadata(i4);
			float f6 = 0.5F;
			float f7 = 0.5F;
			switch(i5) {
			case 0:
				f7 = 0.9F;
				break;
			case 1:
				f6 = 0.1F;
				break;
			case 2:
				f7 = 0.1F;
				break;
			case 3:
				f6 = 0.9F;
			}

			this.func_22059_e(i5);
			this.setPosition((double)((float)i1 + f6), (double)((float)i2 + 0.9375F), (double)((float)i3 + f7));
		} else {
			this.setPosition((double)((float)i1 + 0.5F), (double)((float)i2 + 0.9375F), (double)((float)i3 + 0.5F));
		}

		this.sleeping = true;
		this.sleepTimer = 0;
		this.playerLocation = new ChunkCoordinates(i1, i2, i3);
		this.motionX = this.motionZ = this.motionY = 0.0D;
		if(!this.worldObj.singleplayerWorld) {
			this.worldObj.updateAllPlayersSleepingFlag();
		}

		return EnumStatus.OK;
	}

	private void func_22059_e(int i1) {
		this.field_22066_z = 0.0F;
		this.field_22067_A = 0.0F;
		switch(i1) {
		case 0:
			this.field_22067_A = -1.8F;
			break;
		case 1:
			this.field_22066_z = 1.8F;
			break;
		case 2:
			this.field_22067_A = 1.8F;
			break;
		case 3:
			this.field_22066_z = -1.8F;
		}

	}

	public void wakeUpPlayer(boolean z1, boolean z2, boolean z3) {
		this.setSize(0.6F, 1.8F);
		this.resetHeight();
		ChunkCoordinates chunkCoordinates4 = this.playerLocation;
		ChunkCoordinates chunkCoordinates5 = this.playerLocation;
		if(chunkCoordinates4 != null && this.worldObj.getBlockId(chunkCoordinates4.posX, chunkCoordinates4.posY, chunkCoordinates4.posZ) == Block.bed.blockID) {
			BlockBed.setBedOccupied(this.worldObj, chunkCoordinates4.posX, chunkCoordinates4.posY, chunkCoordinates4.posZ, false);
			chunkCoordinates5 = BlockBed.getNearestEmptyChunkCoordinates(this.worldObj, chunkCoordinates4.posX, chunkCoordinates4.posY, chunkCoordinates4.posZ, 0);
			if(chunkCoordinates5 == null) {
				chunkCoordinates5 = new ChunkCoordinates(chunkCoordinates4.posX, chunkCoordinates4.posY + 1, chunkCoordinates4.posZ);
			}

			this.setPosition((double)((float)chunkCoordinates5.posX + 0.5F), (double)((float)chunkCoordinates5.posY + this.yOffset + 0.1F), (double)((float)chunkCoordinates5.posZ + 0.5F));
		}

		this.sleeping = false;
		if(!this.worldObj.singleplayerWorld && z2) {
			this.worldObj.updateAllPlayersSleepingFlag();
		}

		if(z1) {
			this.sleepTimer = 0;
		} else {
			this.sleepTimer = 100;
		}

		if(z3) {
			this.setSpawnChunk(this.playerLocation);
		}

	}

	private boolean isInBed() {
		return this.worldObj.getBlockId(this.playerLocation.posX, this.playerLocation.posY, this.playerLocation.posZ) == Block.bed.blockID;
	}

	public static ChunkCoordinates verifyBedCoordinates(World world0, ChunkCoordinates chunkCoordinates1) {
		IChunkProvider iChunkProvider2 = world0.getChunkProvider();
		iChunkProvider2.loadChunk(chunkCoordinates1.posX - 3 >> 4, chunkCoordinates1.posZ - 3 >> 4);
		iChunkProvider2.loadChunk(chunkCoordinates1.posX + 3 >> 4, chunkCoordinates1.posZ - 3 >> 4);
		iChunkProvider2.loadChunk(chunkCoordinates1.posX - 3 >> 4, chunkCoordinates1.posZ + 3 >> 4);
		iChunkProvider2.loadChunk(chunkCoordinates1.posX + 3 >> 4, chunkCoordinates1.posZ + 3 >> 4);
		if(world0.getBlockId(chunkCoordinates1.posX, chunkCoordinates1.posY, chunkCoordinates1.posZ) != Block.bed.blockID) {
			return null;
		} else {
			ChunkCoordinates chunkCoordinates3 = BlockBed.getNearestEmptyChunkCoordinates(world0, chunkCoordinates1.posX, chunkCoordinates1.posY, chunkCoordinates1.posZ, 0);
			return chunkCoordinates3;
		}
	}

	public boolean isPlayerSleeping() {
		return this.sleeping;
	}

	public boolean isPlayerFullyAsleep() {
		return this.sleeping && this.sleepTimer >= 100;
	}

	public void addChatMessage(String string1) {
	}

	public ChunkCoordinates getSpawnChunk() {
		return this.spawnChunk;
	}

	public void setSpawnChunk(ChunkCoordinates chunkCoordinates1) {
		if(chunkCoordinates1 != null) {
			this.spawnChunk = new ChunkCoordinates(chunkCoordinates1);
		} else {
			this.spawnChunk = null;
		}

	}

	public void triggerAchievement(StatBase statBase1) {
		this.addStat(statBase1, 1);
	}

	public void addStat(StatBase statBase1, int i2) {
	}

	protected void jump() {
		super.jump();
		this.addStat(StatList.jumpStat, 1);
		if(this.func_35149_at()) {
			this.func_35198_b(0.8F);
		} else {
			this.func_35198_b(0.2F);
		}

	}

	public void moveEntityWithHeading(float f1, float f2) {
		double d3 = this.posX;
		double d5 = this.posY;
		double d7 = this.posZ;
		if(this.field_35214_K.field_35658_b) {
			double d9 = this.motionY;
			float f11 = this.field_35193_ak;
			this.field_35193_ak = 0.05F;
			super.moveEntityWithHeading(f1, f2);
			this.motionY = d9 * 0.6D;
			this.field_35193_ak = f11;
		} else {
			super.moveEntityWithHeading(f1, f2);
		}

		this.addMovementStat(this.posX - d3, this.posY - d5, this.posZ - d7);
	}

	public void addMovementStat(double d1, double d3, double d5) {
		if(this.ridingEntity == null) {
			int i7;
			if(this.isInsideOfMaterial(Material.water)) {
				i7 = Math.round(MathHelper.sqrt_double(d1 * d1 + d3 * d3 + d5 * d5) * 100.0F);
				if(i7 > 0) {
					this.addStat(StatList.distanceDoveStat, i7);
					this.func_35198_b(0.015F * (float)i7 * 0.01F);
				}
			} else if(this.isInWater()) {
				i7 = Math.round(MathHelper.sqrt_double(d1 * d1 + d5 * d5) * 100.0F);
				if(i7 > 0) {
					this.addStat(StatList.distanceSwumStat, i7);
					this.func_35198_b(0.015F * (float)i7 * 0.01F);
				}
			} else if(this.isOnLadder()) {
				if(d3 > 0.0D) {
					this.addStat(StatList.distanceClimbedStat, (int)Math.round(d3 * 100.0D));
				}
			} else if(this.onGround) {
				i7 = Math.round(MathHelper.sqrt_double(d1 * d1 + d5 * d5) * 100.0F);
				if(i7 > 0) {
					this.addStat(StatList.distanceWalkedStat, i7);
					if(this.func_35149_at()) {
						this.func_35198_b(0.099999994F * (float)i7 * 0.01F);
					} else {
						this.func_35198_b(0.01F * (float)i7 * 0.01F);
					}
				}
			} else {
				i7 = Math.round(MathHelper.sqrt_double(d1 * d1 + d5 * d5) * 100.0F);
				if(i7 > 25) {
					this.addStat(StatList.distanceFlownStat, i7);
				}
			}

		}
	}

	private void addMountedMovementStat(double d1, double d3, double d5) {
		if(this.ridingEntity != null) {
			int i7 = Math.round(MathHelper.sqrt_double(d1 * d1 + d3 * d3 + d5 * d5) * 100.0F);
			if(i7 > 0) {
				if(this.ridingEntity instanceof EntityMinecart) {
					this.addStat(StatList.distanceByMinecartStat, i7);
					if(this.startMinecartRidingCoordinate == null) {
						this.startMinecartRidingCoordinate = new ChunkCoordinates(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ));
					} else if(this.startMinecartRidingCoordinate.getSqDistanceTo(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)) >= 1000.0D) {
						this.addStat(AchievementList.onARail, 1);
					}
				} else if(this.ridingEntity instanceof EntityBoat) {
					this.addStat(StatList.distanceByBoatStat, i7);
				} else if(this.ridingEntity instanceof EntityPig) {
					this.addStat(StatList.distanceByPigStat, i7);
				}
			}
		}

	}

	protected void fall(float f1) {
		if(!this.field_35214_K.field_35659_c) {
			if(f1 >= 2.0F) {
				this.addStat(StatList.distanceFallenStat, (int)Math.round((double)f1 * 100.0D));
			}

			super.fall(f1);
		}
	}

	public void onKillEntity(EntityLiving entityLiving1) {
		if(entityLiving1 instanceof EntityMob) {
			this.triggerAchievement(AchievementList.killEnemy);
		}

	}

	public void setInPortal() {
		if(this.timeUntilPortal > 0) {
			this.timeUntilPortal = 10;
		} else {
			this.inPortal = true;
		}
	}

	public void func_35195_d(int i1) {
		this.field_35210_L += i1;
		this.field_35212_N += i1;

		while(this.field_35210_L >= this.func_35203_U()) {
			this.field_35210_L -= this.func_35203_U();
			this.updateHeldItem();
		}

	}

	public int func_35203_U() {
		return (this.field_35211_M + 1) * 10;
	}

	private void updateHeldItem() {
		++this.field_35211_M;
	}

	public void func_35198_b(float f1) {
		if(!this.field_35214_K.field_35660_a) {
			if(!this.worldObj.singleplayerWorld) {
				this.field_35217_m.func_35583_a(f1);
			}

		}
	}

	public FoodStats func_35207_V() {
		return this.field_35217_m;
	}

	public boolean func_35197_c(boolean z1) {
		return (z1 || this.field_35217_m.func_35587_b()) && !this.field_35214_K.field_35660_a;
	}

	public boolean func_35206_W() {
		return this.health > 0 && this.health < 20;
	}

	public void func_35201_a(ItemStack itemStack1, int i2) {
		if(itemStack1 != this.field_34908_d) {
			this.field_34908_d = itemStack1;
			this.field_34909_e = i2;
			if(!this.worldObj.singleplayerWorld) {
				this.func_35148_h(true);
			}

		}
	}

	public boolean func_35200_c(int i1, int i2, int i3) {
		return true;
	}

	protected int a(EntityPlayer entityPlayer1) {
		return this.field_35212_N >> 1;
	}

	protected boolean func_35188_X() {
		return true;
	}

	public String func_35150_Y() {
		return this.username;
	}
}
