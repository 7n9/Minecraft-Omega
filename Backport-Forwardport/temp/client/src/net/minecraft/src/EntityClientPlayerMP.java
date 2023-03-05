package net.minecraft.src;

import net.minecraft.client.Minecraft;

public class EntityClientPlayerMP extends EntityPlayerSP {
	public NetClientHandler sendQueue;
	private int field_9380_bx = 0;
	private boolean field_21093_bH = false;
	private double oldPosX;
	private double field_9378_bz;
	private double oldPosY;
	private double oldPosZ;
	private float oldRotationYaw;
	private float oldRotationPitch;
	private boolean field_9382_bF = false;
	private boolean field_35227_cs = false;
	private boolean wasSneaking = false;
	private int field_12242_bI = 0;

	public EntityClientPlayerMP(Minecraft minecraft1, World world2, Session session3, NetClientHandler netClientHandler4) {
		super(minecraft1, world2, session3, 0);
		this.sendQueue = netClientHandler4;
	}

	public boolean attackEntityFrom(DamageSource damageSource1, int i2) {
		return false;
	}

	public void heal(int i1) {
	}

	public void onUpdate() {
		World world10000 = this.worldObj;
		int i10001 = MathHelper.floor_double(this.posX);
		this.worldObj.getClass();
		if(world10000.blockExists(i10001, 128 / 2, MathHelper.floor_double(this.posZ))) {
			super.onUpdate();
			this.func_4056_N();
		}
	}

	public void func_4056_N() {
		if(this.field_9380_bx++ == 20) {
			this.sendInventoryChanged();
			this.field_9380_bx = 0;
		}

		boolean z1 = this.func_35117_Q();
		if(z1 != this.wasSneaking) {
			if(z1) {
				this.sendQueue.addToSendQueue(new Packet19EntityAction(this, 4));
			} else {
				this.sendQueue.addToSendQueue(new Packet19EntityAction(this, 5));
			}

			this.wasSneaking = z1;
		}

		boolean z2 = this.isSneaking();
		if(z2 != this.field_35227_cs) {
			if(z2) {
				this.sendQueue.addToSendQueue(new Packet19EntityAction(this, 1));
			} else {
				this.sendQueue.addToSendQueue(new Packet19EntityAction(this, 2));
			}

			this.field_35227_cs = z2;
		}

		double d3 = this.posX - this.oldPosX;
		double d5 = this.boundingBox.minY - this.field_9378_bz;
		double d7 = this.posY - this.oldPosY;
		double d9 = this.posZ - this.oldPosZ;
		double d11 = (double)(this.rotationYaw - this.oldRotationYaw);
		double d13 = (double)(this.rotationPitch - this.oldRotationPitch);
		boolean z15 = d5 != 0.0D || d7 != 0.0D || d3 != 0.0D || d9 != 0.0D;
		boolean z16 = d11 != 0.0D || d13 != 0.0D;
		if(this.ridingEntity != null) {
			if(z16) {
				this.sendQueue.addToSendQueue(new Packet11PlayerPosition(this.motionX, -999.0D, -999.0D, this.motionZ, this.onGround));
			} else {
				this.sendQueue.addToSendQueue(new Packet13PlayerLookMove(this.motionX, -999.0D, -999.0D, this.motionZ, this.rotationYaw, this.rotationPitch, this.onGround));
			}

			z15 = false;
		} else if(z15 && z16) {
			this.sendQueue.addToSendQueue(new Packet13PlayerLookMove(this.posX, this.boundingBox.minY, this.posY, this.posZ, this.rotationYaw, this.rotationPitch, this.onGround));
			this.field_12242_bI = 0;
		} else if(z15) {
			this.sendQueue.addToSendQueue(new Packet11PlayerPosition(this.posX, this.boundingBox.minY, this.posY, this.posZ, this.onGround));
			this.field_12242_bI = 0;
		} else if(z16) {
			this.sendQueue.addToSendQueue(new Packet12PlayerLook(this.rotationYaw, this.rotationPitch, this.onGround));
			this.field_12242_bI = 0;
		} else {
			this.sendQueue.addToSendQueue(new Packet10Flying(this.onGround));
			if(this.field_9382_bF == this.onGround && this.field_12242_bI <= 200) {
				++this.field_12242_bI;
			} else {
				this.field_12242_bI = 0;
			}
		}

		this.field_9382_bF = this.onGround;
		if(z15) {
			this.oldPosX = this.posX;
			this.field_9378_bz = this.boundingBox.minY;
			this.oldPosY = this.posY;
			this.oldPosZ = this.posZ;
		}

		if(z16) {
			this.oldRotationYaw = this.rotationYaw;
			this.oldRotationPitch = this.rotationPitch;
		}

	}

	public void dropCurrentItem() {
		this.sendQueue.addToSendQueue(new Packet14BlockDig(4, 0, 0, 0, 0));
	}

	private void sendInventoryChanged() {
	}

	protected void joinEntityItemWithWorld(EntityItem entityItem1) {
	}

	public void sendChatMessage(String string1) {
		this.sendQueue.addToSendQueue(new Packet3Chat(string1));
	}

	public void swingItem() {
		super.swingItem();
		this.sendQueue.addToSendQueue(new Packet18Animation(this, 1));
	}

	public void respawnPlayer() {
		this.sendInventoryChanged();
		NetClientHandler netClientHandler10000 = this.sendQueue;
		byte b10003 = (byte)this.dimension;
		byte b10004 = (byte)this.worldObj.difficultySetting;
		long j10005 = this.worldObj.getRandomSeed();
		this.worldObj.getClass();
		netClientHandler10000.addToSendQueue(new Packet9Respawn(b10003, b10004, j10005, 128, 0));
	}

	protected void b(DamageSource damageSource1, int i2) {
		this.health -= i2;
	}

	public void closeScreen() {
		this.sendQueue.addToSendQueue(new Packet101CloseWindow(this.craftingInventory.windowId));
		this.inventory.setItemStack((ItemStack)null);
		super.closeScreen();
	}

	public void setHealth(int i1) {
		if(this.field_21093_bH) {
			super.setHealth(i1);
		} else {
			this.health = i1;
			this.field_21093_bH = true;
		}

	}

	public void addStat(StatBase statBase1, int i2) {
		if(statBase1 != null) {
			if(statBase1.isIndependent) {
				super.addStat(statBase1, i2);
			}

		}
	}

	public void func_27027_b(StatBase statBase1, int i2) {
		if(statBase1 != null) {
			if(!statBase1.isIndependent) {
				super.addStat(statBase1, i2);
			}

		}
	}
}
