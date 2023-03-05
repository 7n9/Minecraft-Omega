package net.minecraft.src;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Container {
	public List inventoryItemStacks = new ArrayList();
	public List inventorySlots = new ArrayList();
	public int windowId = 0;
	private short field_20917_a = 0;
	protected List crafters = new ArrayList();
	private Set field_20918_b = new HashSet();

	protected void addSlot(Slot slot1) {
		slot1.slotNumber = this.inventorySlots.size();
		this.inventorySlots.add(slot1);
		this.inventoryItemStacks.add((Object)null);
	}

	public void updateCraftingResults() {
		for(int i1 = 0; i1 < this.inventorySlots.size(); ++i1) {
			ItemStack itemStack2 = ((Slot)this.inventorySlots.get(i1)).getStack();
			ItemStack itemStack3 = (ItemStack)this.inventoryItemStacks.get(i1);
			if(!ItemStack.areItemStacksEqual(itemStack3, itemStack2)) {
				itemStack3 = itemStack2 == null ? null : itemStack2.copy();
				this.inventoryItemStacks.set(i1, itemStack3);

				for(int i4 = 0; i4 < this.crafters.size(); ++i4) {
					((ICrafting)this.crafters.get(i4)).updateCraftingInventorySlot(this, i1, itemStack3);
				}
			}
		}

	}

	public Slot getSlot(int i1) {
		return (Slot)this.inventorySlots.get(i1);
	}

	public ItemStack getStackInSlot(int i1) {
		Slot slot2 = (Slot)this.inventorySlots.get(i1);
		return slot2 != null ? slot2.getStack() : null;
	}

	public ItemStack slotClick(int i1, int i2, boolean z3, EntityPlayer entityPlayer4) {
		ItemStack itemStack5 = null;
		if(i2 > 1) {
			return null;
		} else {
			if(i2 == 0 || i2 == 1) {
				InventoryPlayer inventoryPlayer6 = entityPlayer4.inventory;
				if(i1 == -999) {
					if(inventoryPlayer6.getItemStack() != null && i1 == -999) {
						if(i2 == 0) {
							entityPlayer4.dropPlayerItem(inventoryPlayer6.getItemStack());
							inventoryPlayer6.setItemStack((ItemStack)null);
						}

						if(i2 == 1) {
							entityPlayer4.dropPlayerItem(inventoryPlayer6.getItemStack().splitStack(1));
							if(inventoryPlayer6.getItemStack().stackSize == 0) {
								inventoryPlayer6.setItemStack((ItemStack)null);
							}
						}
					}
				} else if(z3) {
					ItemStack itemStack7 = this.getStackInSlot(i1);
					if(itemStack7 != null) {
						int i8 = itemStack7.itemID;
						itemStack5 = itemStack7.copy();
						Slot slot9 = (Slot)this.inventorySlots.get(i1);
						if(slot9 != null && slot9.getStack() != null && slot9.getStack().itemID == i8) {
							this.func_35373_b(i1, i2, z3, entityPlayer4);
						}
					}
				} else {
					if(i1 < 0) {
						return null;
					}

					Slot slot12 = (Slot)this.inventorySlots.get(i1);
					if(slot12 != null) {
						slot12.onSlotChanged();
						ItemStack itemStack13 = slot12.getStack();
						ItemStack itemStack14 = inventoryPlayer6.getItemStack();
						if(itemStack13 != null) {
							itemStack5 = itemStack13.copy();
						}

						int i10;
						if(itemStack13 == null) {
							if(itemStack14 != null && slot12.isItemValid(itemStack14)) {
								i10 = i2 == 0 ? itemStack14.stackSize : 1;
								if(i10 > slot12.getSlotStackLimit()) {
									i10 = slot12.getSlotStackLimit();
								}

								slot12.putStack(itemStack14.splitStack(i10));
								if(itemStack14.stackSize == 0) {
									inventoryPlayer6.setItemStack((ItemStack)null);
								}
							}
						} else if(itemStack14 == null) {
							i10 = i2 == 0 ? itemStack13.stackSize : (itemStack13.stackSize + 1) / 2;
							ItemStack itemStack11 = slot12.decrStackSize(i10);
							inventoryPlayer6.setItemStack(itemStack11);
							if(itemStack13.stackSize == 0) {
								slot12.putStack((ItemStack)null);
							}

							slot12.onPickupFromSlot(inventoryPlayer6.getItemStack());
						} else if(slot12.isItemValid(itemStack14)) {
							if(itemStack13.itemID != itemStack14.itemID || itemStack13.getHasSubtypes() && itemStack13.getItemDamage() != itemStack14.getItemDamage()) {
								if(itemStack14.stackSize <= slot12.getSlotStackLimit()) {
									slot12.putStack(itemStack14);
									inventoryPlayer6.setItemStack(itemStack13);
								}
							} else {
								i10 = i2 == 0 ? itemStack14.stackSize : 1;
								if(i10 > slot12.getSlotStackLimit() - itemStack13.stackSize) {
									i10 = slot12.getSlotStackLimit() - itemStack13.stackSize;
								}

								if(i10 > itemStack14.getMaxStackSize() - itemStack13.stackSize) {
									i10 = itemStack14.getMaxStackSize() - itemStack13.stackSize;
								}

								itemStack14.splitStack(i10);
								if(itemStack14.stackSize == 0) {
									inventoryPlayer6.setItemStack((ItemStack)null);
								}

								itemStack13.stackSize += i10;
							}
						} else if(itemStack13.itemID == itemStack14.itemID && itemStack14.getMaxStackSize() > 1 && (!itemStack13.getHasSubtypes() || itemStack13.getItemDamage() == itemStack14.getItemDamage())) {
							i10 = itemStack13.stackSize;
							if(i10 > 0 && i10 + itemStack14.stackSize <= itemStack14.getMaxStackSize()) {
								itemStack14.stackSize += i10;
								itemStack13.splitStack(i10);
								if(itemStack13.stackSize == 0) {
									slot12.putStack((ItemStack)null);
								}

								slot12.onPickupFromSlot(inventoryPlayer6.getItemStack());
							}
						}
					}
				}
			}

			return itemStack5;
		}
	}

	protected void func_35373_b(int i1, int i2, boolean z3, EntityPlayer entityPlayer4) {
		this.slotClick(i1, i2, z3, entityPlayer4);
	}

	public void onCraftGuiClosed(EntityPlayer entityPlayer1) {
		InventoryPlayer inventoryPlayer2 = entityPlayer1.inventory;
		if(inventoryPlayer2.getItemStack() != null) {
			entityPlayer1.dropPlayerItem(inventoryPlayer2.getItemStack());
			inventoryPlayer2.setItemStack((ItemStack)null);
		}

	}

	public void onCraftMatrixChanged(IInventory iInventory1) {
		this.updateCraftingResults();
	}

	public void putStackInSlot(int i1, ItemStack itemStack2) {
		this.getSlot(i1).putStack(itemStack2);
	}

	public void putStacksInSlots(ItemStack[] itemStack1) {
		for(int i2 = 0; i2 < itemStack1.length; ++i2) {
			this.getSlot(i2).putStack(itemStack1[i2]);
		}

	}

	public void func_20112_a(int i1, int i2) {
	}

	public short func_20111_a(InventoryPlayer inventoryPlayer1) {
		++this.field_20917_a;
		return this.field_20917_a;
	}

	public void func_20113_a(short s1) {
	}

	public void func_20110_b(short s1) {
	}

	public abstract boolean canInteractWith(EntityPlayer entityPlayer1);

	protected void a(ItemStack itemStack1, int i2, int i3, boolean z4) {
		int i5 = i2;
		if(z4) {
			i5 = i3 - 1;
		}

		Slot slot6;
		ItemStack itemStack7;
		if(itemStack1.isStackable()) {
			while(itemStack1.stackSize > 0 && (!z4 && i5 < i3 || z4 && i5 >= i2)) {
				slot6 = (Slot)this.inventorySlots.get(i5);
				itemStack7 = slot6.getStack();
				if(itemStack7 != null && itemStack7.itemID == itemStack1.itemID && (!itemStack1.getHasSubtypes() || itemStack1.getItemDamage() == itemStack7.getItemDamage())) {
					int i8 = itemStack7.stackSize + itemStack1.stackSize;
					if(i8 <= itemStack1.getMaxStackSize()) {
						itemStack1.stackSize = 0;
						itemStack7.stackSize = i8;
						slot6.onSlotChanged();
					} else if(itemStack7.stackSize < itemStack1.getMaxStackSize()) {
						itemStack1.stackSize -= itemStack1.getMaxStackSize() - itemStack7.stackSize;
						itemStack7.stackSize = itemStack1.getMaxStackSize();
						slot6.onSlotChanged();
					}
				}

				if(z4) {
					--i5;
				} else {
					++i5;
				}
			}
		}

		if(itemStack1.stackSize > 0) {
			if(z4) {
				i5 = i3 - 1;
			} else {
				i5 = i2;
			}

			while(!z4 && i5 < i3 || z4 && i5 >= i2) {
				slot6 = (Slot)this.inventorySlots.get(i5);
				itemStack7 = slot6.getStack();
				if(itemStack7 == null) {
					slot6.putStack(itemStack1.copy());
					slot6.onSlotChanged();
					itemStack1.stackSize = 0;
					break;
				}

				if(z4) {
					--i5;
				} else {
					++i5;
				}
			}
		}

	}
}
