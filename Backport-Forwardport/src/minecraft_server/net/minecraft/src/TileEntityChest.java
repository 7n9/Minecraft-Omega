package net.minecraft.src;

public class TileEntityChest extends TileEntity implements IInventory {
	private ItemStack[] chestContents = new ItemStack[36];
	public boolean field_35175_a = false;
	public TileEntityChest field_35172_b;
	public TileEntityChest field_35173_c;
	public TileEntityChest field_35170_d;
	public TileEntityChest field_35171_e;
	public float field_35168_f;
	public float field_35169_g;
	public int field_35176_h;
	private int field_35174_q;

	public int getSizeInventory() {
		return 27;
	}

	public ItemStack getStackInSlot(int i1) {
		return this.chestContents[i1];
	}

	public ItemStack decrStackSize(int i1, int i2) {
		if(this.chestContents[i1] != null) {
			ItemStack itemStack3;
			if(this.chestContents[i1].stackSize <= i2) {
				itemStack3 = this.chestContents[i1];
				this.chestContents[i1] = null;
				this.onInventoryChanged();
				return itemStack3;
			} else {
				itemStack3 = this.chestContents[i1].splitStack(i2);
				if(this.chestContents[i1].stackSize == 0) {
					this.chestContents[i1] = null;
				}

				this.onInventoryChanged();
				return itemStack3;
			}
		} else {
			return null;
		}
	}

	public void setInventorySlotContents(int i1, ItemStack itemStack2) {
		this.chestContents[i1] = itemStack2;
		if(itemStack2 != null && itemStack2.stackSize > this.getInventoryStackLimit()) {
			itemStack2.stackSize = this.getInventoryStackLimit();
		}

		this.onInventoryChanged();
	}

	public String getInvName() {
		return "Chest";
	}

	public void readFromNBT(NBTTagCompound nBTTagCompound1) {
		super.readFromNBT(nBTTagCompound1);
		NBTTagList nBTTagList2 = nBTTagCompound1.getTagList("Items");
		this.chestContents = new ItemStack[this.getSizeInventory()];

		for(int i3 = 0; i3 < nBTTagList2.tagCount(); ++i3) {
			NBTTagCompound nBTTagCompound4 = (NBTTagCompound)nBTTagList2.tagAt(i3);
			int i5 = nBTTagCompound4.getByte("Slot") & 255;
			if(i5 >= 0 && i5 < this.chestContents.length) {
				this.chestContents[i5] = ItemStack.func_35618_a(nBTTagCompound4);
			}
		}

	}

	public void writeToNBT(NBTTagCompound nBTTagCompound1) {
		super.writeToNBT(nBTTagCompound1);
		NBTTagList nBTTagList2 = new NBTTagList();

		for(int i3 = 0; i3 < this.chestContents.length; ++i3) {
			if(this.chestContents[i3] != null) {
				NBTTagCompound nBTTagCompound4 = new NBTTagCompound();
				nBTTagCompound4.setByte("Slot", (byte)i3);
				this.chestContents[i3].writeToNBT(nBTTagCompound4);
				nBTTagList2.setTag(nBTTagCompound4);
			}
		}

		nBTTagCompound1.setTag("Items", nBTTagList2);
	}

	public int getInventoryStackLimit() {
		return 64;
	}

	public boolean canInteractWith(EntityPlayer entityPlayer1) {
		return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : entityPlayer1.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}

	public void func_35164_g() {
		super.func_35164_g();
		this.field_35175_a = false;
	}

	public void func_35167_h() {
		if(!this.field_35175_a) {
			this.field_35175_a = true;
			this.field_35172_b = null;
			this.field_35173_c = null;
			this.field_35170_d = null;
			this.field_35171_e = null;
			if(this.worldObj.getBlockId(this.xCoord - 1, this.yCoord, this.zCoord) == Block.chest.blockID) {
				this.field_35170_d = (TileEntityChest)this.worldObj.getBlockTileEntity(this.xCoord - 1, this.yCoord, this.zCoord);
			}

			if(this.worldObj.getBlockId(this.xCoord + 1, this.yCoord, this.zCoord) == Block.chest.blockID) {
				this.field_35173_c = (TileEntityChest)this.worldObj.getBlockTileEntity(this.xCoord + 1, this.yCoord, this.zCoord);
			}

			if(this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord - 1) == Block.chest.blockID) {
				this.field_35172_b = (TileEntityChest)this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord - 1);
			}

			if(this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord + 1) == Block.chest.blockID) {
				this.field_35171_e = (TileEntityChest)this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord + 1);
			}

			if(this.field_35172_b != null) {
				this.field_35172_b.func_35164_g();
			}

			if(this.field_35171_e != null) {
				this.field_35171_e.func_35164_g();
			}

			if(this.field_35173_c != null) {
				this.field_35173_c.func_35164_g();
			}

			if(this.field_35170_d != null) {
				this.field_35170_d.func_35164_g();
			}

		}
	}

	public void updateEntity() {
		super.updateEntity();
		this.func_35167_h();
		if(++this.field_35174_q % 20 * 4 == 0) {
			this.worldObj.playNoteAt(this.xCoord, this.yCoord, this.zCoord, 1, this.field_35176_h);
		}

		this.field_35169_g = this.field_35168_f;
		float f1 = 0.1F;
		double d2;
		double d4;
		if(this.field_35176_h > 0 && this.field_35168_f == 0.0F && this.field_35172_b == null && this.field_35170_d == null) {
			d2 = (double)this.xCoord + 0.5D;
			d4 = (double)this.zCoord + 0.5D;
			if(this.field_35171_e != null) {
				d4 += 0.5D;
			}

			if(this.field_35173_c != null) {
				d2 += 0.5D;
			}

			this.worldObj.playSoundEffect(d2, (double)this.yCoord + 0.5D, d4, "random.door_open", 1.0F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
		}

		if(this.field_35176_h == 0 && this.field_35168_f > 0.0F || this.field_35176_h > 0 && this.field_35168_f < 1.0F) {
			if(this.field_35176_h > 0) {
				this.field_35168_f += f1;
			} else {
				this.field_35168_f -= f1;
			}

			if(this.field_35168_f > 1.0F) {
				this.field_35168_f = 1.0F;
			}

			if(this.field_35168_f < 0.0F) {
				this.field_35168_f = 0.0F;
				if(this.field_35172_b == null && this.field_35170_d == null) {
					d2 = (double)this.xCoord + 0.5D;
					d4 = (double)this.zCoord + 0.5D;
					if(this.field_35171_e != null) {
						d4 += 0.5D;
					}

					if(this.field_35173_c != null) {
						d2 += 0.5D;
					}

					this.worldObj.playSoundEffect(d2, (double)this.yCoord + 0.5D, d4, "random.door_close", 1.0F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
				}
			}
		}

	}

	public void func_35163_b(int i1, int i2) {
		if(i1 == 1) {
			this.field_35176_h = i2;
		}

	}

	public void func_35161_e() {
		++this.field_35176_h;
		this.worldObj.playNoteAt(this.xCoord, this.yCoord, this.zCoord, 1, this.field_35176_h);
	}

	public void func_35162_t_() {
		--this.field_35176_h;
		this.worldObj.playNoteAt(this.xCoord, this.yCoord, this.zCoord, 1, this.field_35176_h);
	}

	public void invalidate() {
		this.func_35164_g();
		this.func_35167_h();
		super.invalidate();
	}
}
