package net.minecraft.src;

public class ItemInWorldManager {
	public World thisWorld;
	public EntityPlayer thisPlayer;
	private int field_35699_c = -1;
	private float field_672_d = 0.0F;
	private int field_22055_d;
	private int curBlockX;
	private int curBlockY;
	private int curBlockZ;
	private int field_22051_j;
	private boolean field_22050_k;
	private int field_22049_l;
	private int field_22048_m;
	private int field_22047_n;
	private int field_22046_o;

	public ItemInWorldManager(World world1) {
		this.thisWorld = world1;
	}

	public void func_35696_a(int i1) {
		this.field_35699_c = i1;
		if(i1 == 0) {
			this.thisPlayer.field_35214_K.field_35659_c = false;
			this.thisPlayer.field_35214_K.field_35658_b = false;
			this.thisPlayer.field_35214_K.field_35657_d = false;
			this.thisPlayer.field_35214_K.field_35660_a = false;
		} else {
			this.thisPlayer.field_35214_K.field_35659_c = true;
			this.thisPlayer.field_35214_K.field_35657_d = true;
			this.thisPlayer.field_35214_K.field_35660_a = true;
		}

	}

	public int func_35697_a() {
		return this.field_35699_c;
	}

	public boolean func_35698_b() {
		return this.field_35699_c == 1;
	}

	public void func_35695_b(int i1) {
		if(this.field_35699_c == -1) {
			this.field_35699_c = i1;
		}

		this.func_35696_a(this.field_35699_c);
	}

	public void func_328_a() {
		++this.field_22051_j;
		if(this.field_22050_k) {
			int i1 = this.field_22051_j - this.field_22046_o;
			int i2 = this.thisWorld.getBlockId(this.field_22049_l, this.field_22048_m, this.field_22047_n);
			if(i2 != 0) {
				Block block3 = Block.blocksList[i2];
				float f4 = block3.blockStrength(this.thisPlayer) * (float)(i1 + 1);
				if(f4 >= 1.0F) {
					this.field_22050_k = false;
					this.blockHarvessted(this.field_22049_l, this.field_22048_m, this.field_22047_n);
				}
			} else {
				this.field_22050_k = false;
			}
		}

	}

	public void blockClicked(int i1, int i2, int i3, int i4) {
		this.thisWorld.onBlockHit((EntityPlayer)null, i1, i2, i3, i4);
		if(this.func_35698_b()) {
			this.blockHarvessted(i1, i2, i3);
		} else {
			this.field_22055_d = this.field_22051_j;
			int i5 = this.thisWorld.getBlockId(i1, i2, i3);
			if(i5 > 0) {
				Block.blocksList[i5].onBlockClicked(this.thisWorld, i1, i2, i3, this.thisPlayer);
			}

			if(i5 > 0 && Block.blocksList[i5].blockStrength(this.thisPlayer) >= 1.0F) {
				this.blockHarvessted(i1, i2, i3);
			} else {
				this.curBlockX = i1;
				this.curBlockY = i2;
				this.curBlockZ = i3;
			}

		}
	}

	public void blockRemoving(int i1, int i2, int i3) {
		if(i1 == this.curBlockX && i2 == this.curBlockY && i3 == this.curBlockZ) {
			int i4 = this.field_22051_j - this.field_22055_d;
			int i5 = this.thisWorld.getBlockId(i1, i2, i3);
			if(i5 != 0) {
				Block block6 = Block.blocksList[i5];
				float f7 = block6.blockStrength(this.thisPlayer) * (float)(i4 + 1);
				if(f7 >= 0.7F) {
					this.blockHarvessted(i1, i2, i3);
				} else if(!this.field_22050_k) {
					this.field_22050_k = true;
					this.field_22049_l = i1;
					this.field_22048_m = i2;
					this.field_22047_n = i3;
					this.field_22046_o = this.field_22055_d;
				}
			}
		}

		this.field_672_d = 0.0F;
	}

	public boolean removeBlock(int i1, int i2, int i3) {
		Block block4 = Block.blocksList[this.thisWorld.getBlockId(i1, i2, i3)];
		int i5 = this.thisWorld.getBlockMetadata(i1, i2, i3);
		boolean z6 = this.thisWorld.setBlockWithNotify(i1, i2, i3, 0);
		if(block4 != null && z6) {
			block4.onBlockDestroyedByPlayer(this.thisWorld, i1, i2, i3, i5);
		}

		return z6;
	}

	public boolean blockHarvessted(int i1, int i2, int i3) {
		int i4 = this.thisWorld.getBlockId(i1, i2, i3);
		int i5 = this.thisWorld.getBlockMetadata(i1, i2, i3);
		this.thisWorld.playAuxSFXAtEntity(this.thisPlayer, 2001, i1, i2, i3, i4 + this.thisWorld.getBlockMetadata(i1, i2, i3) * 256);
		boolean z6 = this.removeBlock(i1, i2, i3);
		if(this.func_35698_b()) {
			((EntityPlayerMP)this.thisPlayer).playerNetServerHandler.sendPacket(new Packet53BlockChange(i1, i2, i3, this.thisWorld));
		} else {
			ItemStack itemStack7 = this.thisPlayer.getCurrentEquippedItem();
			if(itemStack7 != null) {
				itemStack7.onDestroyBlock(i4, i1, i2, i3, this.thisPlayer);
				if(itemStack7.stackSize == 0) {
					itemStack7.onItemDestroyedByUse(this.thisPlayer);
					this.thisPlayer.destroyCurrentEquippedItem();
				}
			}

			if(z6 && this.thisPlayer.canHarvestBlock(Block.blocksList[i4])) {
				Block.blocksList[i4].harvestBlock(this.thisWorld, this.thisPlayer, i1, i2, i3, i5);
			}
		}

		return z6;
	}

	public boolean itemUsed(EntityPlayer entityPlayer1, World world2, ItemStack itemStack3) {
		int i4 = itemStack3.stackSize;
		int i5 = itemStack3.getItemDamage();
		ItemStack itemStack6 = itemStack3.useItemRightClick(world2, entityPlayer1);
		if(itemStack6 == itemStack3 && (itemStack6 == null || itemStack6.stackSize == i4) && (itemStack6 == null || itemStack6.func_35614_l() <= 0)) {
			return false;
		} else {
			entityPlayer1.inventory.mainInventory[entityPlayer1.inventory.currentItem] = itemStack6;
			if(this.func_35698_b()) {
				itemStack6.stackSize = i4;
				itemStack6.setItemDamage(i5);
			}

			if(itemStack6.stackSize == 0) {
				entityPlayer1.inventory.mainInventory[entityPlayer1.inventory.currentItem] = null;
			}

			return true;
		}
	}

	public boolean activeBlockOrUseItem(EntityPlayer entityPlayer1, World world2, ItemStack itemStack3, int i4, int i5, int i6, int i7) {
		int i8 = world2.getBlockId(i4, i5, i6);
		if(i8 > 0 && Block.blocksList[i8].blockActivated(world2, i4, i5, i6, entityPlayer1)) {
			return true;
		} else if(itemStack3 == null) {
			return false;
		} else if(this.func_35698_b()) {
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

	public void func_35694_a(WorldServer worldServer1) {
		this.thisWorld = worldServer1;
	}
}
