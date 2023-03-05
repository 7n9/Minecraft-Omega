package net.minecraft.src;

import java.util.Iterator;
import java.util.List;

public abstract class EntityPlayer extends EntityLiving {
	public InventoryPlayer inventory = new InventoryPlayer(this);
	public Container inventorySlots;
	public Container craftingInventory;
	protected FoodStats field_35217_av = new FoodStats();
	protected int field_35216_aw = 0;
	public byte field_9371_f = 0;
	public int score = 0;
	public float prevCameraYaw;
	public float cameraYaw;
	public boolean isSwinging = false;
	public int swingProgressInt = 0;
	public String username;
	public int dimension;
	public String playerCloakUrl;
	public int field_35214_aG = 0;
	public double field_20066_r;
	public double field_20065_s;
	public double field_20064_t;
	public double field_20063_u;
	public double field_20062_v;
	public double field_20061_w;
	protected boolean sleeping;
	public ChunkCoordinates bedChunkCoordinates;
	private int sleepTimer;
	public float field_22063_x;
	public float field_22062_y;
	public float field_22061_z;
	private ChunkCoordinates playerSpawnCoordinate;
	private ChunkCoordinates startMinecartRidingCoordinate;
	public int timeUntilPortal = 20;
	protected boolean inPortal = false;
	public float timeInPortal;
	public float prevTimeInPortal;
	public PlayerCapabilities field_35212_aW = new PlayerCapabilities();
	public int field_35211_aX;
	public int field_35210_aY;
	public int field_35209_aZ;
	private ItemStack field_34907_d;
	private int field_34906_e;
	protected float field_35215_ba = 0.1F;
	protected float field_35213_bb = 0.02F;
	private int damageRemainder = 0;
	public EntityFish fishEntity = null;

	public EntityPlayer(World world1) {
		super(world1);
		this.inventorySlots = new ContainerPlayer(this.inventory, !world1.multiplayerWorld);
		this.craftingInventory = this.inventorySlots;
		this.yOffset = 1.62F;
		ChunkCoordinates chunkCoordinates2 = world1.getSpawnPoint();
		this.setLocationAndAngles((double)chunkCoordinates2.posX + 0.5D, (double)(chunkCoordinates2.posY + 1), (double)chunkCoordinates2.posZ + 0.5D, 0.0F, 0.0F);
		this.health = 20;
		this.entityType = "humanoid";
		this.field_9353_B = 180.0F;
		this.fireResistance = 20;
		this.texture = "/mob/char.png";
	}

	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(16, (byte)0);
		this.dataWatcher.addObject(17, (byte)0);
	}

	public ItemStack func_35195_X() {
		return this.field_34907_d;
	}

	public int func_35205_Y() {
		return this.field_34906_e;
	}

	public boolean func_35196_Z() {
		return this.field_34907_d != null;
	}

	public int func_35192_aa() {
		return this.func_35196_Z() ? this.field_34907_d.func_35866_m() - this.field_34906_e : 0;
	}

	public void func_35206_ab() {
		if(this.field_34907_d != null) {
			this.field_34907_d.func_35862_a(this.worldObj, this, this.field_34906_e);
		}

		this.func_35207_ac();
	}

	public void func_35207_ac() {
		this.field_34907_d = null;
		this.field_34906_e = 0;
		if(!this.worldObj.multiplayerWorld) {
			this.func_35116_d(false);
		}

	}

	public boolean func_35162_ad() {
		return this.func_35196_Z() && Item.itemsList[this.field_34907_d.itemID].func_35412_b(this.field_34907_d) == EnumAction.block;
	}

	public void onUpdate() {
		if(this.field_34907_d != null) {
			ItemStack itemStack1 = this.inventory.getCurrentItem();
			if(itemStack1 != this.field_34907_d) {
				this.func_35207_ac();
			} else {
				if(this.field_34906_e <= 25 && this.field_34906_e % 4 == 0) {
					this.func_35201_a(itemStack1, 5);
				}

				if(--this.field_34906_e == 0 && !this.worldObj.multiplayerWorld) {
					this.func_35208_ae();
				}
			}
		}

		if(this.field_35214_aG > 0) {
			--this.field_35214_aG;
		}

		if(this.isPlayerSleeping()) {
			++this.sleepTimer;
			if(this.sleepTimer > 100) {
				this.sleepTimer = 100;
			}

			if(!this.worldObj.multiplayerWorld) {
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
		if(!this.worldObj.multiplayerWorld && this.craftingInventory != null && !this.craftingInventory.canInteractWith(this)) {
			this.closeScreen();
			this.craftingInventory = this.inventorySlots;
		}

		if(this.field_35212_aW.field_35757_b) {
			for(int i9 = 0; i9 < 8; ++i9) {
			}
		}

		if(this.fire > 0 && this.field_35212_aW.field_35759_a) {
			this.fire = 0;
		}

		this.field_20066_r = this.field_20063_u;
		this.field_20065_s = this.field_20062_v;
		this.field_20064_t = this.field_20061_w;
		double d10 = this.posX - this.field_20063_u;
		double d3 = this.posY - this.field_20062_v;
		double d5 = this.posZ - this.field_20061_w;
		double d7 = 10.0D;
		if(d10 > d7) {
			this.field_20066_r = this.field_20063_u = this.posX;
		}

		if(d5 > d7) {
			this.field_20064_t = this.field_20061_w = this.posZ;
		}

		if(d3 > d7) {
			this.field_20065_s = this.field_20062_v = this.posY;
		}

		if(d10 < -d7) {
			this.field_20066_r = this.field_20063_u = this.posX;
		}

		if(d5 < -d7) {
			this.field_20064_t = this.field_20061_w = this.posZ;
		}

		if(d3 < -d7) {
			this.field_20065_s = this.field_20062_v = this.posY;
		}

		this.field_20063_u += d10 * 0.25D;
		this.field_20061_w += d5 * 0.25D;
		this.field_20062_v += d3 * 0.25D;
		this.addStat(StatList.minutesPlayedStat, 1);
		if(this.ridingEntity == null) {
			this.startMinecartRidingCoordinate = null;
		}

		if(!this.worldObj.multiplayerWorld) {
			this.field_35217_av.func_35768_a(this);
		}

	}

	protected void func_35201_a(ItemStack itemStack1, int i2) {
		if(itemStack1.func_35865_n() == EnumAction.eat) {
			for(int i3 = 0; i3 < i2; ++i3) {
				Vec3D vec3D4 = Vec3D.createVector(((double)this.rand.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);
				vec3D4.rotateAroundX(-this.rotationPitch * (float)Math.PI / 180.0F);
				vec3D4.rotateAroundY(-this.rotationYaw * (float)Math.PI / 180.0F);
				Vec3D vec3D5 = Vec3D.createVector(((double)this.rand.nextFloat() - 0.5D) * 0.3D, (double)(-this.rand.nextFloat()) * 0.6D - 0.3D, 0.6D);
				vec3D5.rotateAroundX(-this.rotationPitch * (float)Math.PI / 180.0F);
				vec3D5.rotateAroundY(-this.rotationYaw * (float)Math.PI / 180.0F);
				vec3D5 = vec3D5.addVector(this.posX, this.posY + (double)this.getEyeHeight(), this.posZ);
				this.worldObj.spawnParticle("iconcrack_" + itemStack1.getItem().shiftedIndex, vec3D5.xCoord, vec3D5.yCoord, vec3D5.zCoord, vec3D4.xCoord, vec3D4.yCoord + 0.05D, vec3D4.zCoord);
			}

			this.worldObj.playSoundAtEntity(this, "mob.eat", 0.5F + 0.5F * (float)this.rand.nextInt(2), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
		}

	}

	protected void func_35208_ae() {
		if(this.field_34907_d != null) {
			this.func_35201_a(this.field_34907_d, 16);
			int i1 = this.field_34907_d.stackSize;
			ItemStack itemStack2 = this.field_34907_d.func_35863_b(this.worldObj, this);
			if(itemStack2 != this.field_34907_d || itemStack2 != null && itemStack2.stackSize != i1) {
				this.inventory.mainInventory[this.inventory.currentItem] = itemStack2;
				if(itemStack2.stackSize == 0) {
					this.inventory.mainInventory[this.inventory.currentItem] = null;
				}
			}

			this.func_35207_ac();
		}

	}

	public void handleHealthUpdate(byte b1) {
		if(b1 == 9) {
			this.func_35208_ae();
		} else {
			super.handleHealthUpdate(b1);
		}

	}

	protected boolean isMovementBlocked() {
		return this.health <= 0 || this.isPlayerSleeping();
	}

	protected void closeScreen() {
		this.craftingInventory = this.inventorySlots;
	}

	public void updateCloak() {
		this.playerCloakUrl = "http://s3.amazonaws.com/MinecraftCloaks/" + this.username + ".png";
		this.cloakUrl = this.playerCloakUrl;
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

	public void preparePlayerToSpawn() {
		this.yOffset = 1.62F;
		this.setSize(0.6F, 1.8F);
		super.preparePlayerToSpawn();
		this.health = 20;
		this.deathTime = 0;
	}

	private int func_35202_aE() {
		return this.func_35160_a(Potion.field_35675_e) ? 6 - (1 + this.func_35167_b(Potion.field_35675_e).func_35801_c()) * 1 : (this.func_35160_a(Potion.field_35672_f) ? 6 + (1 + this.func_35167_b(Potion.field_35672_f).func_35801_c()) * 2 : 6);
	}

	protected void updateEntityActionState() {
		int i1 = this.func_35202_aE();
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
		if(this.field_35216_aw > 0) {
			--this.field_35216_aw;
		}

		if(this.worldObj.difficultySetting == 0 && this.health < 20 && this.ticksExisted % 20 * 12 == 0) {
			this.heal(1);
		}

		this.inventory.decrementAnimations();
		this.prevCameraYaw = this.cameraYaw;
		super.onLivingUpdate();
		this.field_35169_bv = this.field_35215_ba;
		this.field_35168_bw = this.field_35213_bb;
		if(this.func_35117_Q()) {
			this.field_35169_bv = (float)((double)this.field_35169_bv + (double)this.field_35215_ba * 0.3D);
			this.field_35168_bw = (float)((double)this.field_35168_bw + (double)this.field_35213_bb * 0.3D);
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

	public int getScore() {
		return this.score;
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

		if(this.func_35160_a(Potion.field_35675_e)) {
			f2 *= 1.0F + (float)(this.func_35167_b(Potion.field_35675_e).func_35801_c() + 1) * 0.2F;
		}

		if(this.func_35160_a(Potion.field_35672_f)) {
			f2 *= 1.0F - (float)(this.func_35167_b(Potion.field_35672_f).func_35801_c() + 1) * 0.2F;
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
		this.field_35211_aX = nBTTagCompound1.getInteger("Xp");
		this.field_35210_aY = nBTTagCompound1.getInteger("XpLevel");
		this.field_35209_aZ = nBTTagCompound1.getInteger("XpTotal");
		if(this.sleeping) {
			this.bedChunkCoordinates = new ChunkCoordinates(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ));
			this.wakeUpPlayer(true, true, false);
		}

		if(nBTTagCompound1.hasKey("SpawnX") && nBTTagCompound1.hasKey("SpawnY") && nBTTagCompound1.hasKey("SpawnZ")) {
			this.playerSpawnCoordinate = new ChunkCoordinates(nBTTagCompound1.getInteger("SpawnX"), nBTTagCompound1.getInteger("SpawnY"), nBTTagCompound1.getInteger("SpawnZ"));
		}

		this.field_35217_av.func_35766_a(nBTTagCompound1);
	}

	public void writeEntityToNBT(NBTTagCompound nBTTagCompound1) {
		super.writeEntityToNBT(nBTTagCompound1);
		nBTTagCompound1.setTag("Inventory", this.inventory.writeToNBT(new NBTTagList()));
		nBTTagCompound1.setInteger("Dimension", this.dimension);
		nBTTagCompound1.setBoolean("Sleeping", this.sleeping);
		nBTTagCompound1.setShort("SleepTimer", (short)this.sleepTimer);
		nBTTagCompound1.setInteger("Xp", this.field_35211_aX);
		nBTTagCompound1.setInteger("XpLevel", this.field_35210_aY);
		nBTTagCompound1.setInteger("XpTotal", this.field_35209_aZ);
		if(this.playerSpawnCoordinate != null) {
			nBTTagCompound1.setInteger("SpawnX", this.playerSpawnCoordinate.posX);
			nBTTagCompound1.setInteger("SpawnY", this.playerSpawnCoordinate.posY);
			nBTTagCompound1.setInteger("SpawnZ", this.playerSpawnCoordinate.posZ);
		}

		this.field_35217_av.func_35763_b(nBTTagCompound1);
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
		if(this.field_35212_aW.field_35759_a && !damageSource1.func_35529_d()) {
			return false;
		} else {
			this.entityAge = 0;
			if(this.health <= 0) {
				return false;
			} else {
				if(this.isPlayerSleeping() && !this.worldObj.multiplayerWorld) {
					this.wakeUpPlayer(true, true, false);
				}

				Entity entity3 = damageSource1.func_35532_a();
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
				if(entityWolf3.isWolfTamed() && this.username.equals(entityWolf3.getWolfOwner())) {
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
								} while(!entityWolf6.isWolfTamed());
							} while(entityWolf6.getEntityToAttack() != null);
						} while(!this.username.equals(entityWolf6.getWolfOwner()));
					} while(z2 && entityWolf6.isWolfSitting());

					entityWolf6.setIsSitting(false);
					entityWolf6.setEntityToAttack(entityLiving1);
				}
			}
		}
	}

	protected void b(DamageSource damageSource1, int i2) {
		if(!damageSource1.func_35534_b() && this.func_35162_ad()) {
			i2 = 1 + i2 >> 1;
		}

		if(!damageSource1.func_35534_b()) {
			int i3 = 25 - this.inventory.getTotalArmorValue();
			int i4 = i2 * i3 + this.damageRemainder;
			this.inventory.damageArmor(i2);
			i2 = i4 / 25;
			this.damageRemainder = i4 % 25;
		}

		this.func_35198_d(damageSource1.func_35533_c());
		super.b(damageSource1, i2);
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
		if(!this.isSwinging || this.swingProgressInt >= this.func_35202_aE() / 2 || this.swingProgressInt < 0) {
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

			boolean z4 = entity1.attackEntityFrom(DamageSource.func_35527_a(this), i2);
			if(z4) {
				if(this.func_35117_Q()) {
					entity1.addVelocity((double)(-MathHelper.sin(this.rotationYaw * (float)Math.PI / 180.0F) * 1.0F), 0.1D, (double)(MathHelper.cos(this.rotationYaw * (float)Math.PI / 180.0F) * 1.0F));
					this.motionX *= 0.6D;
					this.motionZ *= 0.6D;
					this.func_35113_c(false);
				}

				if(z3) {
					this.func_35200_b(entity1);
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

			this.func_35198_d(0.3F);
		}

	}

	public void func_35200_b(Entity entity1) {
	}

	public void respawnPlayer() {
	}

	public abstract void func_6420_o();

	public void onItemStackChanged(ItemStack itemStack1) {
	}

	public void setEntityDead() {
		super.setEntityDead();
		this.inventorySlots.onCraftGuiClosed(this);
		if(this.craftingInventory != null) {
			this.craftingInventory.onCraftGuiClosed(this);
		}

	}

	public boolean isEntityInsideOpaqueBlock() {
		return !this.sleeping && super.isEntityInsideOpaqueBlock();
	}

	public EnumStatus sleepInBedAt(int i1, int i2, int i3) {
		if(!this.worldObj.multiplayerWorld) {
			label53: {
				if(!this.isPlayerSleeping() && this.isEntityAlive()) {
					if(this.worldObj.worldProvider.isNether) {
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

			this.func_22052_e(i5);
			this.setPosition((double)((float)i1 + f6), (double)((float)i2 + 0.9375F), (double)((float)i3 + f7));
		} else {
			this.setPosition((double)((float)i1 + 0.5F), (double)((float)i2 + 0.9375F), (double)((float)i3 + 0.5F));
		}

		this.sleeping = true;
		this.sleepTimer = 0;
		this.bedChunkCoordinates = new ChunkCoordinates(i1, i2, i3);
		this.motionX = this.motionZ = this.motionY = 0.0D;
		if(!this.worldObj.multiplayerWorld) {
			this.worldObj.updateAllPlayersSleepingFlag();
		}

		return EnumStatus.OK;
	}

	private void func_22052_e(int i1) {
		this.field_22063_x = 0.0F;
		this.field_22061_z = 0.0F;
		switch(i1) {
		case 0:
			this.field_22061_z = -1.8F;
			break;
		case 1:
			this.field_22063_x = 1.8F;
			break;
		case 2:
			this.field_22061_z = 1.8F;
			break;
		case 3:
			this.field_22063_x = -1.8F;
		}

	}

	public void wakeUpPlayer(boolean z1, boolean z2, boolean z3) {
		this.setSize(0.6F, 1.8F);
		this.resetHeight();
		ChunkCoordinates chunkCoordinates4 = this.bedChunkCoordinates;
		ChunkCoordinates chunkCoordinates5 = this.bedChunkCoordinates;
		if(chunkCoordinates4 != null && this.worldObj.getBlockId(chunkCoordinates4.posX, chunkCoordinates4.posY, chunkCoordinates4.posZ) == Block.bed.blockID) {
			BlockBed.setBedOccupied(this.worldObj, chunkCoordinates4.posX, chunkCoordinates4.posY, chunkCoordinates4.posZ, false);
			chunkCoordinates5 = BlockBed.getNearestEmptyChunkCoordinates(this.worldObj, chunkCoordinates4.posX, chunkCoordinates4.posY, chunkCoordinates4.posZ, 0);
			if(chunkCoordinates5 == null) {
				chunkCoordinates5 = new ChunkCoordinates(chunkCoordinates4.posX, chunkCoordinates4.posY + 1, chunkCoordinates4.posZ);
			}

			this.setPosition((double)((float)chunkCoordinates5.posX + 0.5F), (double)((float)chunkCoordinates5.posY + this.yOffset + 0.1F), (double)((float)chunkCoordinates5.posZ + 0.5F));
		}

		this.sleeping = false;
		if(!this.worldObj.multiplayerWorld && z2) {
			this.worldObj.updateAllPlayersSleepingFlag();
		}

		if(z1) {
			this.sleepTimer = 0;
		} else {
			this.sleepTimer = 100;
		}

		if(z3) {
			this.setPlayerSpawnCoordinate(this.bedChunkCoordinates);
		}

	}

	private boolean isInBed() {
		return this.worldObj.getBlockId(this.bedChunkCoordinates.posX, this.bedChunkCoordinates.posY, this.bedChunkCoordinates.posZ) == Block.bed.blockID;
	}

	public static ChunkCoordinates verifyRespawnCoordinates(World world0, ChunkCoordinates chunkCoordinates1) {
		IChunkProvider iChunkProvider2 = world0.getIChunkProvider();
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

	public float getBedOrientationInDegrees() {
		if(this.bedChunkCoordinates != null) {
			int i1 = this.worldObj.getBlockMetadata(this.bedChunkCoordinates.posX, this.bedChunkCoordinates.posY, this.bedChunkCoordinates.posZ);
			int i2 = BlockBed.getDirectionFromMetadata(i1);
			switch(i2) {
			case 0:
				return 90.0F;
			case 1:
				return 0.0F;
			case 2:
				return 270.0F;
			case 3:
				return 180.0F;
			}
		}

		return 0.0F;
	}

	public boolean isPlayerSleeping() {
		return this.sleeping;
	}

	public boolean isPlayerFullyAsleep() {
		return this.sleeping && this.sleepTimer >= 100;
	}

	public int func_22060_M() {
		return this.sleepTimer;
	}

	public void addChatMessage(String string1) {
	}

	public ChunkCoordinates getPlayerSpawnCoordinate() {
		return this.playerSpawnCoordinate;
	}

	public void setPlayerSpawnCoordinate(ChunkCoordinates chunkCoordinates1) {
		if(chunkCoordinates1 != null) {
			this.playerSpawnCoordinate = new ChunkCoordinates(chunkCoordinates1);
		} else {
			this.playerSpawnCoordinate = null;
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
		if(this.func_35117_Q()) {
			this.func_35198_d(0.8F);
		} else {
			this.func_35198_d(0.2F);
		}

	}

	public void moveEntityWithHeading(float f1, float f2) {
		double d3 = this.posX;
		double d5 = this.posY;
		double d7 = this.posZ;
		if(this.field_35212_aW.field_35757_b) {
			double d9 = this.motionY;
			float f11 = this.field_35168_bw;
			this.field_35168_bw = 0.05F;
			super.moveEntityWithHeading(f1, f2);
			this.motionY = d9 * 0.6D;
			this.field_35168_bw = f11;
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
					this.func_35198_d(0.015F * (float)i7 * 0.01F);
				}
			} else if(this.isInWater()) {
				i7 = Math.round(MathHelper.sqrt_double(d1 * d1 + d5 * d5) * 100.0F);
				if(i7 > 0) {
					this.addStat(StatList.distanceSwumStat, i7);
					this.func_35198_d(0.015F * (float)i7 * 0.01F);
				}
			} else if(this.isOnLadder()) {
				if(d3 > 0.0D) {
					this.addStat(StatList.distanceClimbedStat, (int)Math.round(d3 * 100.0D));
				}
			} else if(this.onGround) {
				i7 = Math.round(MathHelper.sqrt_double(d1 * d1 + d5 * d5) * 100.0F);
				if(i7 > 0) {
					this.addStat(StatList.distanceWalkedStat, i7);
					if(this.func_35117_Q()) {
						this.func_35198_d(0.099999994F * (float)i7 * 0.01F);
					} else {
						this.func_35198_d(0.01F * (float)i7 * 0.01F);
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
		if(!this.field_35212_aW.field_35758_c) {
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

	public int getItemIcon(ItemStack itemStack1) {
		int i2 = super.getItemIcon(itemStack1);
		if(itemStack1.itemID == Item.fishingRod.shiftedIndex && this.fishEntity != null) {
			i2 = itemStack1.getIconIndex() + 16;
		} else if(this.field_34907_d != null && itemStack1.itemID == Item.bow.shiftedIndex) {
			int i3 = itemStack1.func_35866_m() - this.field_34906_e;
			if(i3 >= 18) {
				return 133;
			}

			if(i3 > 13) {
				return 117;
			}

			if(i3 > 0) {
				return 101;
			}
		}

		return i2;
	}

	public void setInPortal() {
		if(this.timeUntilPortal > 0) {
			this.timeUntilPortal = 10;
		} else {
			this.inPortal = true;
		}
	}

	public void func_35204_c(int i1) {
		this.field_35211_aX += i1;
		this.field_35209_aZ += i1;

		while(this.field_35211_aX >= this.func_35193_as()) {
			this.field_35211_aX -= this.func_35193_as();
			this.func_35203_aG();
		}

	}

	public int func_35193_as() {
		return (this.field_35210_aY + 1) * 10;
	}

	private void func_35203_aG() {
		++this.field_35210_aY;
	}

	public void func_35198_d(float f1) {
		if(!this.field_35212_aW.field_35759_a) {
			if(!this.worldObj.multiplayerWorld) {
				this.field_35217_av.func_35762_a(f1);
			}

		}
	}

	public FoodStats func_35191_at() {
		return this.field_35217_av;
	}

	public boolean func_35197_b(boolean z1) {
		return (z1 || this.field_35217_av.func_35770_c()) && !this.field_35212_aW.field_35759_a;
	}

	public boolean func_35194_au() {
		return this.health > 0 && this.health < 20;
	}

	public void func_35199_b(ItemStack itemStack1, int i2) {
		if(itemStack1 != this.field_34907_d) {
			this.field_34907_d = itemStack1;
			this.field_34906_e = i2;
			if(!this.worldObj.multiplayerWorld) {
				this.func_35116_d(true);
			}

		}
	}

	public boolean func_35190_e(int i1, int i2, int i3) {
		return true;
	}

	protected int a(EntityPlayer entityPlayer1) {
		return this.field_35209_aZ >> 1;
	}

	protected boolean func_35163_av() {
		return true;
	}
}
