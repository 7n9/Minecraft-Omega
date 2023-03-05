package net.minecraft.src;

import net.minecraft.client.Minecraft;

public class PlayerControllerMP extends PlayerController {
	private int currentBlockX = -1;
	private int currentBlockY = -1;
	private int currentblockZ = -1;
	private float curBlockDamageMP = 0.0F;
	private float prevBlockDamageMP = 0.0F;
	private float field_9441_h = 0.0F;
	private int blockHitDelay = 0;
	private boolean isHittingBlock = false;
	private boolean field_35649_k;
	private NetClientHandler netClientHandler;
	private int currentPlayerItem = 0;

	public PlayerControllerMP(Minecraft minecraft1, NetClientHandler netClientHandler2) {
		super(minecraft1);
		this.netClientHandler = netClientHandler2;
	}

	public void func_35648_a(boolean z1) {
		this.field_35649_k = z1;
		if(this.field_35649_k) {
			PlayerControllerTest.func_35646_d(this.mc.thePlayer);
		} else {
			PlayerControllerTest.func_35645_e(this.mc.thePlayer);
		}

	}

	public void flipPlayer(EntityPlayer entityPlayer1) {
		entityPlayer1.rotationYaw = -180.0F;
	}

	public boolean shouldDrawHUD() {
		return !this.field_35649_k;
	}

	public boolean sendBlockRemoved(int i1, int i2, int i3, int i4) {
		if(this.field_35649_k) {
			return false;
		} else {
			int i5 = this.mc.theWorld.getBlockId(i1, i2, i3);
			boolean z6 = super.sendBlockRemoved(i1, i2, i3, i4);
			ItemStack itemStack7 = this.mc.thePlayer.getCurrentEquippedItem();
			if(itemStack7 != null) {
				itemStack7.onDestroyBlock(i5, i1, i2, i3, this.mc.thePlayer);
				if(itemStack7.stackSize == 0) {
					itemStack7.onItemDestroyedByUse(this.mc.thePlayer);
					this.mc.thePlayer.destroyCurrentEquippedItem();
				}
			}

			return z6;
		}
	}

	public void clickBlock(int i1, int i2, int i3, int i4) {
		if(this.field_35649_k) {
			this.netClientHandler.addToSendQueue(new Packet14BlockDig(0, i1, i2, i3, i4));
			PlayerControllerTest.func_35644_a(this.mc, this, i1, i2, i3, i4);
			this.blockHitDelay = 5;
		} else if(!this.isHittingBlock || i1 != this.currentBlockX || i2 != this.currentBlockY || i3 != this.currentblockZ) {
			this.netClientHandler.addToSendQueue(new Packet14BlockDig(0, i1, i2, i3, i4));
			int i5 = this.mc.theWorld.getBlockId(i1, i2, i3);
			if(i5 > 0 && this.curBlockDamageMP == 0.0F) {
				Block.blocksList[i5].onBlockClicked(this.mc.theWorld, i1, i2, i3, this.mc.thePlayer);
			}

			if(i5 > 0 && Block.blocksList[i5].blockStrength(this.mc.thePlayer) >= 1.0F) {
				this.sendBlockRemoved(i1, i2, i3, i4);
			} else {
				this.isHittingBlock = true;
				this.currentBlockX = i1;
				this.currentBlockY = i2;
				this.currentblockZ = i3;
				this.curBlockDamageMP = 0.0F;
				this.prevBlockDamageMP = 0.0F;
				this.field_9441_h = 0.0F;
			}
		}

	}

	public void resetBlockRemoving() {
		this.curBlockDamageMP = 0.0F;
		this.isHittingBlock = false;
	}

	public void sendBlockRemoving(int i1, int i2, int i3, int i4) {
		this.syncCurrentPlayItem();
		if(this.blockHitDelay > 0) {
			--this.blockHitDelay;
		} else if(this.field_35649_k) {
			this.blockHitDelay = 5;
			this.netClientHandler.addToSendQueue(new Packet14BlockDig(0, i1, i2, i3, i4));
			PlayerControllerTest.func_35644_a(this.mc, this, i1, i2, i3, i4);
		} else {
			if(i1 == this.currentBlockX && i2 == this.currentBlockY && i3 == this.currentblockZ) {
				int i5 = this.mc.theWorld.getBlockId(i1, i2, i3);
				if(i5 == 0) {
					this.isHittingBlock = false;
					return;
				}

				Block block6 = Block.blocksList[i5];
				this.curBlockDamageMP += block6.blockStrength(this.mc.thePlayer);
				if(this.field_9441_h % 4.0F == 0.0F && block6 != null) {
					this.mc.sndManager.playSound(block6.stepSound.stepSoundDir2(), (float)i1 + 0.5F, (float)i2 + 0.5F, (float)i3 + 0.5F, (block6.stepSound.getVolume() + 1.0F) / 8.0F, block6.stepSound.getPitch() * 0.5F);
				}

				++this.field_9441_h;
				if(this.curBlockDamageMP >= 1.0F) {
					this.isHittingBlock = false;
					this.netClientHandler.addToSendQueue(new Packet14BlockDig(2, i1, i2, i3, i4));
					this.sendBlockRemoved(i1, i2, i3, i4);
					this.curBlockDamageMP = 0.0F;
					this.prevBlockDamageMP = 0.0F;
					this.field_9441_h = 0.0F;
					this.blockHitDelay = 5;
				}
			} else {
				this.clickBlock(i1, i2, i3, i4);
			}

		}
	}

	public void setPartialTime(float f1) {
		if(this.curBlockDamageMP <= 0.0F) {
			this.mc.ingameGUI.damageGuiPartialTime = 0.0F;
			this.mc.renderGlobal.damagePartialTime = 0.0F;
		} else {
			float f2 = this.prevBlockDamageMP + (this.curBlockDamageMP - this.prevBlockDamageMP) * f1;
			this.mc.ingameGUI.damageGuiPartialTime = f2;
			this.mc.renderGlobal.damagePartialTime = f2;
		}

	}

	public float getBlockReachDistance() {
		return this.field_35649_k ? 5.0F : 4.0F;
	}

	public void func_717_a(World world1) {
		super.func_717_a(world1);
	}

	public void updateController() {
		this.syncCurrentPlayItem();
		this.prevBlockDamageMP = this.curBlockDamageMP;
		this.mc.sndManager.playRandomMusicIfReady();
	}

	private void syncCurrentPlayItem() {
		int i1 = this.mc.thePlayer.inventory.currentItem;
		if(i1 != this.currentPlayerItem) {
			this.currentPlayerItem = i1;
			this.netClientHandler.addToSendQueue(new Packet16BlockItemSwitch(this.currentPlayerItem));
		}

	}

	public boolean sendPlaceBlock(EntityPlayer entityPlayer1, World world2, ItemStack itemStack3, int i4, int i5, int i6, int i7) {
		this.syncCurrentPlayItem();
		this.netClientHandler.addToSendQueue(new Packet15Place(i4, i5, i6, i7, entityPlayer1.inventory.getCurrentItem()));
		int i8 = world2.getBlockId(i4, i5, i6);
		if(i8 > 0 && Block.blocksList[i8].blockActivated(world2, i4, i5, i6, entityPlayer1)) {
			return true;
		} else if(itemStack3 == null) {
			return false;
		} else if(this.field_35649_k) {
			int i9 = itemStack3.getItemDamage();
			int i10 = itemStack3.stackSize;
			boolean z11 = itemStack3.useItem(entityPlayer1, world2, i4, i5, i6, i7);
			itemStack3.setItemDamage(i9);
			itemStack3.stackSize = i10;
			return z11;
		} else {
			return itemStack3.useItem(entityPlayer1, world2, i4, i5, i6, i7);
		}
	}

	public boolean sendUseItem(EntityPlayer entityPlayer1, World world2, ItemStack itemStack3) {
		this.syncCurrentPlayItem();
		this.netClientHandler.addToSendQueue(new Packet15Place(-1, -1, -1, 255, entityPlayer1.inventory.getCurrentItem()));
		boolean z4 = super.sendUseItem(entityPlayer1, world2, itemStack3);
		return z4;
	}

	public EntityPlayer createPlayer(World world1) {
		return new EntityClientPlayerMP(this.mc, world1, this.mc.session, this.netClientHandler);
	}

	public void attackEntity(EntityPlayer entityPlayer1, Entity entity2) {
		this.syncCurrentPlayItem();
		this.netClientHandler.addToSendQueue(new Packet7UseEntity(entityPlayer1.entityId, entity2.entityId, 1));
		entityPlayer1.attackTargetEntityWithCurrentItem(entity2);
	}

	public void interactWithEntity(EntityPlayer entityPlayer1, Entity entity2) {
		this.syncCurrentPlayItem();
		this.netClientHandler.addToSendQueue(new Packet7UseEntity(entityPlayer1.entityId, entity2.entityId, 0));
		entityPlayer1.useCurrentItemOnEntity(entity2);
	}

	public ItemStack windowClick(int i1, int i2, int i3, boolean z4, EntityPlayer entityPlayer5) {
		short s6 = entityPlayer5.craftingInventory.func_20111_a(entityPlayer5.inventory);
		ItemStack itemStack7 = super.windowClick(i1, i2, i3, z4, entityPlayer5);
		this.netClientHandler.addToSendQueue(new Packet102WindowClick(i1, i2, i3, z4, itemStack7, s6));
		return itemStack7;
	}

	public void func_35637_a(ItemStack itemStack1, int i2) {
		if(this.field_35649_k) {
			int i3 = -1;
			int i4 = 0;
			int i5 = 0;
			if(itemStack1 != null) {
				i3 = itemStack1.itemID;
				i4 = itemStack1.stackSize > 0 ? itemStack1.stackSize : 1;
				i5 = itemStack1.getItemDamage();
			}

			this.netClientHandler.addToSendQueue(new Packet107CreativeSetSlot(i2, i3, i4, i5));
		}

	}

	public void func_35639_a(ItemStack itemStack1) {
		if(this.field_35649_k && itemStack1 != null) {
			this.netClientHandler.addToSendQueue(new Packet107CreativeSetSlot(-1, itemStack1.itemID, itemStack1.stackSize, itemStack1.getItemDamage()));
		}

	}

	public void func_20086_a(int i1, EntityPlayer entityPlayer2) {
		if(i1 != -9999) {
			;
		}
	}

	public void func_35638_c(EntityPlayer entityPlayer1) {
		this.syncCurrentPlayItem();
		this.netClientHandler.addToSendQueue(new Packet14BlockDig(5, 0, 0, 0, 255));
		super.func_35638_c(entityPlayer1);
	}

	public boolean func_35642_f() {
		return true;
	}

	public boolean func_35641_g() {
		return !this.field_35649_k;
	}

	public boolean func_35640_h() {
		return this.field_35649_k;
	}

	public boolean func_35636_i() {
		return this.field_35649_k;
	}
}
